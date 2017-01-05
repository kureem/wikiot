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

import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.ui.Container;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;

public class EXDesignableDevice extends EXDefinition implements Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private DeviceDefinition device;

	public EXDesignableDevice(DeviceDefinition device) {
		super(device);
		//this.device = device;
		setTemplateLocation("templates/EXDesignableDevice.xhtml");
		addEvent(this, CLICK);
	}

	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		 
		getAncestorOfType(IOTBuilder.class).getDescendentOfType(EXLayout.class).addDevice(this.getId(),this);
		
		
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		
	}

}
