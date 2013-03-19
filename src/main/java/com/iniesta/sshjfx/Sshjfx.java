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
 * File created: 16/03/2013 at 22:01:47 by antonio
 */
package com.iniesta.sshjfx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.iniesta.layerfx.HandlingView;

/**
 * @author antonio
 *
 */
public class Sshjfx implements Initializable, HandlingView {

	@FXML
	private AnchorPane mainPane;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private TextArea textAreaOutput;
	@FXML
	private AnchorPane paneButtons;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		progressBar.setVisible(false);
		ConnectionData connData = new ConnectionData("antonio", null, "htpc");
		Command command = new Command("ls");
		ButtonSSH button = new ButtonSSH(connData, command);
		ButtonPaneler paneler = new ButtonPaneler(paneButtons, textAreaOutput, progressBar);
		paneler.addButtonSSH(button);
		command = new Command("Power Off", "poweroff", true);
		button = new ButtonSSH(connData, command);
		paneler.addButtonSSH(button);
	}

	/* (non-Javadoc)
	 * @see com.iniesta.layerfx.HandlingView#setStage(javafx.stage.Stage)
	 */
	public void setStage(Stage stage) {
	}

	/* (non-Javadoc)
	 * @see com.iniesta.layerfx.HandlingView#getMainPane()
	 */
	public Pane getMainPane() {
		return mainPane;
	}
	
	@FXML
	void handleSaveConnectionDataAction(ActionEvent event){
		
	}
	
	@FXML
	void handleEditConnectionAction(ActionEvent event){
		
	}

	@FXML
	void handleRemoveConnectionAction(ActionEvent event){
		
	}
	
	@FXML
	void handleEditCommandAction(ActionEvent event){
		
	}	
	
	@FXML
	void handleRemoveCommandAction(ActionEvent event){
		
	}
	
	@FXML
	void handleSaveCommandAction(ActionEvent event){
		
	}

}
