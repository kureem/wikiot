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

import org.castafiore.iot.admin.EXDevice;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.ui.EXContainer;

public class EXLayout extends EXContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EXViewDevice selected;

	private List<EXViewDevice> devices = new LinkedList<EXViewDevice>();

	public EXLayout(String name) {
		super(name, "div");
		New();
	}

	public void addDevice(String deviceId, EXDefinition device) {

		EXViewDevice dev = new EXViewDevice(deviceId, device.getDefinition());
		devices.add(dev);
		addChild(dev);
		selectDevice(dev);
		getParent().setStyle("overflow", "auto");
	}

	protected String generateId(DeviceDefinition def) {
		int count = 0;
		for (EXViewDevice d : devices) {
			if (d.getDefinition().getId().equals(def.getId())) {
				count++;
			}
		}

		count++;
		if (count == 1) {
			return def.getId() + "#";// + Util.getApplication().getName();
		} else {
			return def.getId() + count;// + "#" +
											// Util.getApplication().getName() ;
		}

	}

	public String getScript(String deviceId, String event) {
		for (EXViewDevice d : devices) {
			if (d.getDeviceId().equals(deviceId)) {
				if (d.getEvents().containsKey(event)) {
					return d.getEventScripts().get(event);
				}
			}
		}

		return "";
	}

	public void setScript(String deviceId, String event, String script) {
		for (EXViewDevice d : devices) {
			if (d.getDeviceId().equals(deviceId)) {
				d.getEventScripts().put(event, script);
			}
		}

	}

	public EXViewDevice getDevice(String deviceId) {
		for (EXViewDevice d : devices) {
			if (d.getDeviceId().equals(deviceId)) {
				return d;
			}
		}

		return null;
	}

	public void removeDevice(EXDevice device) {
		getChildren().remove(device);
		devices.remove(device);
		setRendered(false);
	}

	public void selectDevice(String deviceId) {
		for (EXViewDevice d : devices) {
			if (d.getDeviceId().equals(deviceId)) {
				selectDevice(d);
			}
		}
	}

	public void selectDevice(EXViewDevice device) {
		if (selected != null) {
			selected.removeClass("device-selected");
		}
		this.selected = device;
		device.addClass("device-selected");
		getAncestorOfType(EXWorkspace.class).setSelected(device);

	}

	public void New() {
		devices.clear();
		getChildren().clear();
		setRendered(false);
	}

	public void refreshLayout() {
		for (EXViewDevice device : devices) {
			device.setDefinition(device.getDeviceId(), device.getDefinition());
		}
	}

	public void close() {

	}

	public List<EXViewDevice> getDevices() {
		return devices;
	}

	public EXViewDevice getSelected() {
		return selected;
	}

}
