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

import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.iot.definitions.EventDefinition;
import org.castafiore.iot.definitions.FunctionDefinition;
import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.layout.EXXHTMLFragment;

public class EXDeviceInfo extends EXContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container definitionId = new EXContainer("definitionId", "label");
	private Container deviceId = new EXContainer("deviceId", "label");
	
	
	public EXDeviceInfo() {
		super("deviceInfo", "div");
		
		EXXHTMLFragment fragment = new EXXHTMLFragment("fragment", "templates/EXDeviceInfo.xhtml");
		addChild(fragment);
		fragment.addChild(definitionId);
		fragment.addChild(deviceId);
		
		Container events = new EXContainer("deviceEvents", "ul").addClass("device-info");
		
		Container functions = new EXContainer("deviceFunctions", "ul").addClass("device-info");
		
		fragment.addChild(events);
		fragment.addChild(functions);
				
		
	}
	
	public void setDefinition(String sdeviceId,DeviceDefinition device){
		definitionId.setText(device.getId());
		deviceId.setText(sdeviceId);
		
		
		Container events = getChild("fragment").getChild("deviceEvents");
		String text = "";
		if(device.getEvts().size() > 0){
			for(EventDefinition d : device.getEvts()){
				text = text + "<li><i>"+d.getName()+"</i><hr><p>"+d.getDescription()+"</p></li>";
			}
		}else{
			text = "<li><i>No events</i><hr><p>This device does not publish any events</p></li>";
		}
		events.setText(text);
		
		
		Container functions = getChild("fragment").getChild("deviceFunctions");
		 text = "";
		if(device.getFns().size() > 0){
			for(FunctionDefinition d : device.getFns()){
				text = text + "<li><i>"+d.getName()+"</i><hr><p>"+d.getDescription()+"</p></li>";
			}
		}else{
			text = "<li><i>No events</i><hr><p>This device does not recieve any functions</p></li>";
		}
		functions.setText(text);
		
	}
	
	public void setSelected(EXViewDevice device){
		doSelect(device);
	}
	
	protected void doSelect(EXViewDevice device){
		setDefinition(device.getDeviceId(), device.getDefinition());
	}
	
	

}
