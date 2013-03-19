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
 * File created: 19/03/2013 at 00:28:41 by antonio
 */
package com.iniesta.sshjfx;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * @author antonio
 *
 */
public class ButtonPaneler {

	private TextArea output;
	private ProgressBar progressBar;
	private AnchorPane paneButtons;	

	public ButtonPaneler(AnchorPane paneButtons, TextArea output, ProgressBar progressBar){
		this.paneButtons = paneButtons;
		this.output = output;
		this.progressBar = progressBar;
	}
	
	public void addButtonSSH(ButtonSSH button){
		int numberButtons = paneButtons.getChildren().size();
		paneButtons.getChildren().add(button.getNode());
		button.setPrePostActions(preAction(), postAction());
		button.setLayoutX(0);
		button.setLayoutY(100*numberButtons);
	}

	private ButtonPostAction postAction() {
		return new ButtonPostAction() {			
			@Override
			public void execute(ButtonSSH buttonExecuted, ExecutionResult result) {
				progressBar.setVisible(false);
				output.setText(result.getResult());
			}
		};
	}

	private ButtonPreAction preAction() {
		return new ButtonPreAction() {			
			@Override
			public void execute(ButtonSSH buttonExecuted) {
				progressBar.setVisible(true);
			}
		};
	}
}
