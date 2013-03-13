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
 * File created: 11/03/2013 at 23:05:18 by antonio
 */
package com.iniesta.sshjfx;

import java.io.InputStream;

import javafx.application.Application;
import javafx.stage.Stage;

import com.iniesta.layerfx.Dialog;
import com.iniesta.layerfx.Dialog.Input;
import com.iniesta.layerfx.Dialog.Type;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author antonio
 * 
 */
public class Exec extends Application {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		JSch jsch = new JSch();
		String host = "htpc", username = "antonio", password = null;
		int port = 22;
		MyUserInfo ui = new MyUserInfo(password);
		Session session = jsch.getSession(username, host, port);
//		session.setPassword(password);		
		session.setUserInfo(ui);
		session.connect();

//		String command = "ls";
		String command = Dialog.showInputDialog(Input.TEXTFIELD, "Enter a command");

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		channel.setInputStream(null);

		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();

		channel.connect();

		String total = "";
		byte[] tmp = new byte[1024];
		while (true) {
			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				String aux = new String(tmp, 0, i);
				System.out.print(aux);
				total += aux + "\n";
			}
			if (channel.isClosed()) {
				System.out.println("exit-status: " + channel.getExitStatus());
				Dialog.showAlert(Type.INFO, total);
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ee) {
			}
		}
		channel.disconnect();
		session.disconnect();

	}

}
