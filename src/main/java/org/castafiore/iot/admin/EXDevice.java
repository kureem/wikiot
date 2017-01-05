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

import org.castafiore.iot.Device;
import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.layout.EXXHTMLFragment;

public class EXDevice extends EXXHTMLFragment{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Device device ;
	
	private Container deviceId = new EXContainer("deviceId", "label");
	private Container status = new EXContainer("status", "div");
	
	
	
	public EXDevice(Device device) {
		super(device.getDeviceId(), "templates/EXDevice.xhtml");
		setDevice(device);
		addChild(deviceId);
		addChild(status);
	}
	
	public void setDevice(Device device){
		this.device = device;
		deviceId.setText(device.getDeviceId());
		if(device.isConnected()){
			status.setText("<span class=\"badge\">Connected</span>");
		}else{
			status.setText("<span class=\"badge off\">Disconnected</span>");
		}
	}
	
	public Device getDevice(){
		return device;
	}
	

}
