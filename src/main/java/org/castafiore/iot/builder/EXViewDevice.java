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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.js.Expression;
import org.castafiore.ui.layout.EXXHTMLFragment;

public class EXViewDevice extends EXXHTMLFragment implements Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,String> events = new HashMap<String, String>();
	
	private Container serialNo = new EXContainer("serialNo", "label");
	private Container deviceId = new EXContainer("deviceId", "label");
	
	private Container delete = new EXContainer("delete", "button").addClass("btn btn-xs").setText("<i class=\"fa fa-times-circle\"></i>").setStyle("float", "right");
	
	
	private DeviceDefinition definition;

	public EXViewDevice(final String sDeviceId, DeviceDefinition definition) {
		super(sDeviceId, "templates/EXViewDevice.xhtml");
		setDefinition(sDeviceId,definition);
		addChild(deviceId);
		addChild(delete.addEvent(new Event() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void Success(JQuery container, Map<String, String> request)
					throws UIException {
				
			}
			
			@Override
			public boolean ServerAction(Container container, Map<String, String> request)
					throws UIException {
				container.getAncestorOfType(EXLayout.class).getDevices().remove(this);
				container.getAncestorOfType(EXViewDevice.class).getParent().getChildren().remove(this);
				List<EXViewDevice> devices = container.getAncestorOfType(EXLayout.class).getDevices();
				if(devices.size() > 0){
					getAncestorOfType(EXLayout.class).selectDevice(devices.get(0));
				}
				setDisplay(false);
				return true;
			}
			
			@Override
			public void ClientAction(JQuery container) {
				container.IF(new Expression("confirm(\"Do you really want to delete this device?\")"), container.clone().server(this), container.clone());
				
			}
		}, Event.CLICK));
		setTemplateLocation("templates/EXViewDevice.xhtml");
		addEvent(this, CLICK);
		
	}
	
	public void setDefinition(String sDeviceId, DeviceDefinition device){
		this.definition = device;
		//this.device = dev;
		serialNo.setText(device.getId());
		deviceId.setText(sDeviceId);
		
	}
	
	
	
	
	public Map<String, String> getEventScripts() {
		return events;
	}

	public void setEvents(Map<String, String> events) {
		this.events = events;
	}

	public DeviceDefinition getDefinition(){
		return definition;
	}
	
	public String getDeviceId(){
		return deviceId.getText();
	}

	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		
		if(container.equals(delete)){
			//Util.getApplication().getDevices().remove(device);
			getAncestorOfType(EXLayout.class).getDevices().remove(this);
			getParent().getChildren().remove(this);
			List<EXViewDevice> devices = getAncestorOfType(EXLayout.class).getDevices();
			if(devices.size() > 0){
				getAncestorOfType(EXLayout.class).selectDevice(devices.get(0));
			}
			setDisplay(false);
		}else{
		
			getAncestorOfType(EXWorkspace.class).getDescendentOfType(EXLayout.class).selectDevice(this);
		}
		
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		
	}
	
	

}
