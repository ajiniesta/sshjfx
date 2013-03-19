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
 * File created: 16/03/2013 at 21:58:36 by antonio
 */
package com.iniesta.sshjfx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.iniesta.layerfx.FXMLHandler;

/**
 * @author antonio
 *
 */
public class CommanderSsh extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLHandler handler = new FXMLHandler(Sshjfx.class);
		handler.getHandlingView();		
		handler.setStageStyle(StageStyle.DECORATED);
		handler.showWindow("SSH Jfx");
	}

}
