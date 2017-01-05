package org.castafiore.iot.websocket;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.castafiore.iot.Device;
import org.castafiore.iot.DeviceRegistry;
import org.castafiore.iot.IOTApplet;
import org.springframework.beans.factory.annotation.Autowired;

public abstract  class GenericIOTApplet implements IOTApplet {

	@Autowired
	private DeviceRegistry registry;

	private Map<String, String> devices = new LinkedHashMap<String,String>();

	private Map<String, String> connectedDevices = new LinkedHashMap<String,String>();

	@Override
	public DeviceRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(DeviceRegistry registry) {
		this.registry = registry;
	}

	public IOTApplet addRequiredDevice(String definitionId, String deviceId) {

		if (!devices.containsKey(deviceId)) {
			devices.put(deviceId,definitionId);
		}else{
			throw new RuntimeException("There is already a device with id:" + deviceId + " Please choose another Id");
		}
		return this;
	}

	public Device findDevice(String Id) {
		//check if device is connected first
		if(connectedDevices.containsKey(Id)){
			return getRegistry().getDevice(Id);
		}
		return null;
	}

//	public Device findDeviceByName(String deviceName) {
//		for (String d : connectedDevices) {
//			Device device = getRegistry().getDevice(d);
//			if (device.getName().equals(deviceName)) {
//				return device;
//			}
//		}
//		
//		throw new RuntimeException("The device " + deviceName + " does not seem to be registered. Please switch on the device. And check if it working properly");
//	}

	@Override
	public void onDeviceConnected(Device device) {
		
		connectedDevices.remove(device.getDeviceId());
		connectedDevices.put(device.getDeviceId(), device.getDefinition().getId());
		initDevice(device);

	}
	
	public abstract void initDevice(Device device);

	@Override
	public Collection<String> getRequiredDevices() {
		return devices.keySet();
	}

}
