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
 * File created: 14/03/2013 at 00:38:25 by antonio
 */
package com.iniesta.sshjfx;

/**
 * @author antonio
 *
 */
public class Command {

	private String name;
	private String command;
	private boolean sudo;
	
	public Command(String command) {
		this(command, command, false);
	}
	/**
	 * @param command
	 * @param command2 
	 * @param sudo
	 */
	public Command(String name, String command, boolean sudo) {
		super();
		this.name = name;
		this.command = command;
		this.sudo = sudo;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	/**
	 * @return the sudo
	 */
	public boolean isSudo() {
		return sudo;
	}
	/**
	 * @param sudo the sudo to set
	 */
	public void setSudo(boolean sudo) {
		this.sudo = sudo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (sudo ? 1231 : 1237);
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
		if (!(obj instanceof Command))
			return false;
		Command other = (Command) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sudo != other.sudo)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Command [name=" + name + ", command=" + command + ", sudo=" + sudo + "]";
	}
		
}
