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
package org.castafiore.iot.admin;

import java.util.List;
import java.util.Map;

import org.castafiore.iot.Device;
import org.castafiore.iot.DeviceRegistry;
import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.layout.EXXHTMLFragment;

public class EXDevices extends EXXHTMLFragment implements Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Container devices = new EXContainer("devices", "div").addClass("devices");

	
	protected DeviceRegistry deviceRegistry;
	
	public EXDevices(DeviceRegistry deviceRegistry) {
		super("EXDevices", "templates/EXDevices.xhtml");
		this.deviceRegistry = deviceRegistry;
		addChild(devices);
		refreshDevices();
		EXButton btn = new EXButton("refresh", "Refresh");
		addChild(btn);
		btn.addEvent(this, CLICK);
	}
	
	public void refreshDevices(){
		
		
		
		List<Device> dds = deviceRegistry.getDevices();
		
		devices.getChildren().clear();
		devices.setRendered(false);
		
		for(Device device : dds){
			devices.addChild(new EXDevice(device));
		}
	}

	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		refreshDevices();
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
