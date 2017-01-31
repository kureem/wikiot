package org.castafiore.robot2d;

import java.util.Map;

import org.castafiore.iot.Device;
import org.castafiore.iot.EventListener;
import org.castafiore.iot.websocket.GenericIOTApplet;
import org.springframework.stereotype.Component;

@Component
public class Robot2D extends GenericIOTApplet{
	
	public Robot2D(){
		addRequiredDevice("rb.0.0", "5");
		addRequiredDevice("rbc.0.0", "6");		
	}

	@Override
	public void initDevice(Device device) {
		if(device.getDeviceId().endsWith("6")){
			device.addEvent(new OnReset(), "OnReset");
			device.addEvent(new OnMoveXBy(), "OnMoveX");
			device.addEvent(new OnMoveYBy(),"OnMoveY");
		}
	}
	
	
	public class OnReset implements EventListener{

		@Override
		public void execute(Device source, String type, Map<String, String> parameters) {
			findDevice("5").invoke("reset");
		}
		
	}
	
	public class OnMoveXBy implements EventListener{

		@Override
		public void execute(Device source, String type, Map<String, String> parameters) {
			findDevice("5").invoke("MoveX",parameters);
		}
		
	}
	
	public class OnMoveYBy implements EventListener{

		@Override
		public void execute(Device source, String type, Map<String, String> parameters) {
			findDevice("5").invoke("MoveY",parameters);
		}
		
	}
	

}
