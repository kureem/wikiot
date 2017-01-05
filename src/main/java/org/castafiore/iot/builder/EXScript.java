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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.castafiore.KeyValue;
import org.castafiore.SimpleKeyValue;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.iot.definitions.EventDefinition;
import org.castafiore.ui.Container;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.dropdown.DefaultDataModel;
import org.castafiore.ui.dropdown.EXSelect;
import org.castafiore.ui.form.EXTextArea;
import org.castafiore.ui.layout.EXMigLayout;

public class EXScript extends EXMigLayout implements Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EXTextArea script = new EXTextArea("script");
	
	private EXSelect<String> events = new EXSelect<String>("event", new DefaultDataModel<String>());
	
	private EXSelect<KeyValue> devices = new EXSelect<KeyValue>("devices", new DefaultDataModel<KeyValue>());
	
	private EXViewDevice selected;
	
	public EXScript(String name) {
		super(name, "6:6;12");
		addChild(devices,"0:0");
		addChild(events, "1:0"); 
		addChild(script, "0:1");
		devices.addEvent(this,CHANGE);
		script.addEvent(this, BLUR);
		events.addEvent(this,CHANGE);
		
	}
	
	public void setSelected(EXViewDevice device){
		this.selected = device;
		
		doSelect(device);
	}
	
	
	protected void doSelect(EXViewDevice device){
		 DefaultDataModel<String> mm = new DefaultDataModel<String>();
		 List<EventDefinition> defns = new LinkedList<EventDefinition>();
		if(device != null){
			 DeviceDefinition definition = device.getDefinition();
			 
			 defns = definition.getEvts();
			
			 for(EventDefinition def : defns){
				 mm.addItem(def.getName());
			 }
		}
		 
		 this.events.setModel(mm);
		 
		 
		 DefaultDataModel<KeyValue> devs  = new DefaultDataModel<KeyValue>();
		 if(getAncestorOfType(EXWorkspace.class) != null){
			 List<EXViewDevice> devices = getAncestorOfType(EXWorkspace.class).getDevices();
			 for(EXViewDevice dev : devices){
				 String key = dev.getDeviceId();
				 String label = dev.getDeviceId() + " -> " + dev.getDefinition().getId();
				 devs.addItem(new SimpleKeyValue(key, label));
			 }
		 }
		 
		 this.devices.setModel(devs);
		 if(selected != null)
			 this.devices.setValue(new SimpleKeyValue(selected.getDeviceId(), ""));
		 
		 if(defns.size() > 0){
			 events.setSelectedValue(0);
		 }
		 updateScript();
	}
	
	public void updateScript(){
		//String script = "// finds a device in application\ndef device = app.findDevice('Lamp');\n//Invokes a function on the device\ndevice.invoke(\"SwitchOn\")";
		if(devices.getModel().getSize() > 0){
			KeyValue kv = devices.getValue();
			
			String deviceId = kv.getKey();
			
			if(events.getModel().getSize() > 0){
			String event = events.getValue();
			
			 String script = getAncestorOfType(EXWorkspace.class).getDescendentOfType(EXLayout.class).getScript(deviceId, event);
			 this.script.setValue(script);
			 this.script.setEnabled(true);
			}else{
				this.script.setValue("//No event exposed by this device");
				this.script.setEnabled(false);
			}
		}
//		if(script.trim().length() <=0){
//			script = "// finds a device in application\ndef device = app.findDevice('Lamp')\n//Invokes a function on the device\ndevice.invoke(\"SwitchOn\");";
//		}
//		this.script.setText(script);
	}

	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		if(container.getName().equals("devices")){
			KeyValue kv = devices.getValue();
			
			String deviceId = kv.getKey();
			
			
			getAncestorOfType(EXWorkspace.class).getDescendentOfType(EXLayout.class).selectDevice(deviceId);
		}else if(container.getName().equals("event")){
			updateScript();
		}else{
			//
			try{
				KeyValue kv = devices.getValue();
				
				String deviceId = kv.getKey();
				
				if(events.getModel().getSize() > 0){
					String event = events.getValue();
					String script = this.script.getValue();
					getAncestorOfType(EXWorkspace.class).getDescendentOfType(EXLayout.class).setScript(deviceId, event, script);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		
	}

}
