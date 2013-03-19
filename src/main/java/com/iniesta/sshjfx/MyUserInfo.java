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
 * File created: 11/03/2013 at 23:10:33 by antonio
 */
package com.iniesta.sshjfx;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;

import com.iniesta.layerfx.Dialog;
import com.iniesta.layerfx.Dialog.Input;
import com.iniesta.layerfx.Dialog.Type;
import com.jcraft.jsch.UserInfo;

/**
 * @author antonio
 *
 */
public class MyUserInfo implements UserInfo{
	
	private String passwd;
	
	public MyUserInfo(String password) {
		this.passwd = password;
	}

	public String getPassphrase() {
		return null;
	}

	public String getPassword() {	
		return passwd;
	}

	public boolean promptPassphrase(String arg0) {
		return true;
	}

	public boolean promptPassword(final String msg) {		
		boolean prompt = passwd!=null;
		if(!prompt){
			if(Platform.isFxApplicationThread()){
				this.passwd = Dialog.showInputDialog(Input.PASSWORDFIELD, msg);
			}else{
				final CountDownLatch latch = new CountDownLatch(1);
				Platform.runLater(new Runnable() {					
					@Override
					public void run() {
						passwd = Dialog.showInputDialog(Input.PASSWORDFIELD, msg);
						latch.countDown();
					}
				});
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		prompt = passwd!=null;
		return prompt;
	}

	public boolean promptYesNo(String msg) {		
//		return Dialog.showConfirm(msg);
		return true;
	}

	public void showMessage(final String msg) {
		if(Platform.isFxApplicationThread()){
			Dialog.showAlert(Type.INFO, msg);
		}else{
			Platform.runLater(new Runnable() {				
				@Override
				public void run() {
					Dialog.showAlert(Type.INFO, msg);					
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MyUserInfo))
			return false;
		MyUserInfo other = (MyUserInfo) obj;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyUserInfo [passwd=" + passwd + "]";
	}
}
