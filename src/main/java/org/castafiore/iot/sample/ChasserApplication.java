package org.castafiore.iot.sample;

import java.util.Map;

import org.castafiore.iot.Device;
import org.castafiore.iot.EventListener;
import org.castafiore.iot.websocket.GenericIOTApplet;
import org.springframework.stereotype.Component;

@Component
public class ChasserApplication extends GenericIOTApplet{

	@Override
	public void initDevice(Device device) {
		
		if(device.getDeviceId().equals("18")){
			device.addEvent(new EventListener() {
				
				@Override
				public void execute(Device source, String type, Map<String, String> parameters) {
					findDevice("17").invoke("SwitchOn", parameters);
					
				}
			}, "Pitch");
		}
		
	}

	
	
}
