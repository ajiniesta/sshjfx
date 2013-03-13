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
 * File created: 13/03/2013 at 23:50:37 by antonio
 */
package com.iniesta.sshjfx;

/**
 * @author antonio
 *
 */
public class ExecutionResult {
	private String result;
	private int exitCode;
	/**
	 * @param result
	 * @param exitCode
	 */
	public ExecutionResult(String result, int exitCode) {
		super();
		this.result = result;
		this.exitCode = exitCode;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the exitCode
	 */
	public int getExitCode() {
		return exitCode;
	}
	/**
	 * @param exitCode the exitCode to set
	 */
	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exitCode;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		if (!(obj instanceof ExecutionResult))
			return false;
		ExecutionResult other = (ExecutionResult) obj;
		if (exitCode != other.exitCode)
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExecutionResult [result=" + result + ", exitCode=" + exitCode + "]";
	}
	
}
