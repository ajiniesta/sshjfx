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
 * File created: 18/03/2013 at 20:28:03 by antonio
 */
package com.iniesta.sshjfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @author antonio
 *
 */
public class ButtonSSH {

	private Button button;
	private ConnectionData connData;
	private Command command;
	protected ExecutionResult result;
	private ButtonPostAction postAction;
	private ButtonPreAction preAction;

	public ButtonSSH(ConnectionData connData, Command command){
		this(connData, command, 100, 100);
	}	
	
	public ButtonSSH(ConnectionData connData, Command command, int height, int width){
		this.connData = connData;
		this.command = command;
		button = new Button(command.getName());
		button.setPrefWidth(width);
		button.setPrefHeight(height);
		button.setOnAction(eventHandler());		
	}

	public void setLayoutX(double x){
		button.setLayoutX(x);
	}
	
	public void setLayoutY(double y){
		button.setLayoutY(y);
	}
	
	public Node getNode(){
		return button;
	}
	
	private EventHandler<ActionEvent> eventHandler() {
		final ButtonSSH finalThis = this;
		return new EventHandler<ActionEvent>() {			
			public void handle(ActionEvent event) {
				preAction.execute(finalThis);
				ExecHandler handler = new ExecHandler(connData, command);				
				Service<ExecutionResult> service = generateSerivceHandler(handler);
				extractResult(service);
				service.start();								
			}			
		};
	}
	
	private void extractResult(final Service<ExecutionResult> service){		
		final ButtonSSH finalThis = this;
		service.runningProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue){
					result = service.getValue();
					if(result!=null){
						postAction.execute(finalThis, result);
						System.out.println("Exit Code: "+result.getExitCode());
						System.out.println(result.getResult());
					}
				}
			}
		});
	}
	
	/**
	 * Generate the service handler
	 * @param handler
	 * @return
	 */
	private Service<ExecutionResult> generateSerivceHandler(final ExecHandler handler) {
		return new Service<ExecutionResult>() {			
			@Override
			protected Task<ExecutionResult> createTask() {
				return new Task<ExecutionResult>() {					
					@Override
					protected ExecutionResult call() throws Exception {
						ExecutionResult executionResult = null;
						executionResult = handler.execCommand();
						return executionResult;
					}
				};
			}
		};
	}

	public void setPrePostActions(ButtonPreAction preAction, ButtonPostAction postAction) {
		this.preAction = preAction;
		this.postAction = postAction;
	}
}
