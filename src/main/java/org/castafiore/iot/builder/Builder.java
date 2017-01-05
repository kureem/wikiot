/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castafiore.iot.builder;

import java.util.Map;

import org.castafiore.iot.DeviceRegistry;
import org.castafiore.iot.definitions.DefinitionRegistry;
import org.castafiore.iot.emulator.Emulation;
import org.castafiore.iot.emulator.Emulator;
import org.castafiore.iot.emulator.EmulatorApp;
import org.castafiore.ui.Container;
import org.castafiore.ui.EXApplication;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.button.Size;
import org.castafiore.ui.panel.EXPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Builder extends EXApplication implements Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DefinitionRegistry definitionRegistry;
	
	@Autowired
	private DeviceRegistry deviceRegistry;
	
	
	
	public Builder() {
		super("builder");
		
		//EmulatorApp app = new EmulatorApp();
		//addChild(app);
		addChild(new Login("login"));
	}
	
	
	
	public void login(String email){
		setAttribute("user", email);
		this.getChildren().clear();
		setRendered(false);
		EXPanel panel = new EXPanel("panel");
		panel.setTitle("Application builder");
		panel.setShowCloseButton(false);
		panel.setDraggable(false);
		panel.setBody(new IOTBuilder(email, deviceRegistry, definitionRegistry).setStyle("margin-top", "12px"));
		addChild(panel);
		
		panel.getDescendentByName("labelContainer").setStyleClass("panel-title");
		panel.getDescendentByName("widget-head").setStyleClass("panel-heading");
		panel.setStyleClass("panel panel-default container");
		//panel.setStyle("margin", "12px");
		panel.setStyle("width", "100%");
		Emulation emulation = new Emulation();
		addChild(emulation);
		panel.getDescendentByName("labelContainer").setStyle("float", "left");
		
		Container btns = panel.getDescendentByName("buttonsContainer");
		btns.getChildren().clear();
		EXButton emulate = new EXButton("emulate", "Emulate");
		emulate.setSize(Size.EXTRA_SMALL).removeClass("btn-default").addClass("btn-warning");
		btns.addChild(emulate);
		emulate.setAttribute("data-toggle", "modal").setAttribute("data-target", "#" + emulation.getId());
		//data-toggle="modal" data-target="#myModal"
		
		emulate.addEvent(this, CLICK);
		EXButton reset = new EXButton("reset", "Reset");
		reset.setStyle("margin", "1px 19px 0 11px");
		reset.setSize(Size.EXTRA_SMALL).removeClass("btn-default").addClass("btn-danger");
		reset.addEvent(this, CLICK);
		btns.addChild(reset);
	}



	@Override
	public void ClientAction(JQuery container) {
		container.append(new JQuery(".loading-emulation").html("Loading...")).server(this);
		
	}



	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		if(container.getName().equals("reset")){
			getRoot().getDescendentOfType(EXLayout.class).New();
		}else{
			EmulatorApp app = getDescendentOfType(EmulatorApp.class);
			app.setAttribute("style", "max-height: 400px; width: 594px; overflow-x: hidden;");
			app.getDescendentByName("devices").getChildren().clear();
			app.setRendered(false);
			int count =0;
			for(EXViewDevice d :getDescendentOfType(EXLayout.class).getDevices()){
				app.addDevice(d.getDeviceId(), d.getDefinition().getId());
				Emulator e =app.getDescendentByName("devices").getChildByIndex(count).getDescendentOfType(Emulator.class);
				e.connect();
				e.setStyle("margin", "12px");
				//e.getDescendentByName("toolbar").setDisplay(false);
				count++;
			}
		}
		
		return true;
	}



	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		
	}

}
