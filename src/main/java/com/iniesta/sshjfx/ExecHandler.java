/** 
 * Copyright [2013] Antonio J. Iniesta
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 * File created: 13/03/2013 at 21:42:46 by antonio
 */
package com.iniesta.sshjfx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author antonio
 *
 */
public class ExecHandler {
	
	public static ExecutionResult execCommand(ConnectionData connectionData, Command command) throws JSchException, IOException{
		ExecutionResult result = null;
		if(connectionData!=null && command!=null){
			JSch jsch = new JSch();
			Session session = jsch.getSession(
					connectionData.getUsername(), 
					connectionData.getHost(), 
					connectionData.getPort());					
			session.setUserInfo(connectionData.getUserInfo());
			session.connect();
			Channel channel = session.openChannel("exec");
			
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			if(command.isSudo()){
				((ChannelExec)channel).setCommand("sudo -S -p '' "+command.getCommand());
				OutputStream out=channel.getOutputStream();
				channel.connect();				
				//And put the sudo password
				out.write((connectionData.getUserInfo().getPassword()+"\n").getBytes());
			    out.flush();
			}else{				
				((ChannelExec) channel).setCommand(command.getCommand());
				
				channel.connect();
			}

			String total = "";
			int exitCode = -1;
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					String aux = new String(tmp, 0, i);
//					System.out.print(aux);
					total += aux + "\n";
				}
				if (channel.isClosed()) {
//					System.out.println("exit-status: " + channel.getExitStatus());
					exitCode = channel.getExitStatus();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}			
			channel.disconnect();
			session.disconnect();
			
			result = new ExecutionResult(total, exitCode);
		}
		return result;
	}
}
