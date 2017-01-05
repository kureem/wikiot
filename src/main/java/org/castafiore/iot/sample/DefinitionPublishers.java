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
package org.castafiore.iot.sample;

import org.castafiore.iot.definitions.DefinitionBuilder;
import org.castafiore.iot.definitions.DeviceDefinition;

public class DefinitionPublishers {
	
	
	
	public static void main(String[] args) {
		
		
		DeviceDefinition lamp = DefinitionBuilder.create("lmp.0.0")
				.addFunction("SwitchOn", "Switch on lamp")
				.addFunction("SwitchOff", "Switch off lamp")
			.build();
		
		DeviceDefinition remote = DefinitionBuilder.create("ctr.0.0")
				.addEvent("OnSwitchOn", "When switch on is pressed on remote")
				.addEvent("OnSwitchOff", "When Switch off is pressed on remote")
			.build();
		
		DeviceDefinition motor = DefinitionBuilder.create("mtr.0.0")
				.addFunction("RotateClockwise", "Rotate this motor clockwise.")
				.addFunction("RotateAntiClockwise", "Rotate this motor anti clockwise")
			.build();
		
//		publishDefinition(lamp);
//		publishDefinition(remote);
//		publishDefinition(motor);
		
		
//		DeviceDefinition alarm = DefinitionBuilder.create("Alarm", "eucleed.iot", "1.0")
//				.name("Alarm")
//				.label("Alarm")
//				.icon("http://icons.iconarchive.com/icons/aha-soft/large-time/48/Alarm-clock-icon.png")
//				.addEvent("OnAlarm", "Event when alarm is ringing")
//				.addFunction("StopAlarm", "Stop the alarm")
//			.build();
//		
//		
//		DeviceDefinition cofee = DefinitionBuilder.create("Coffee machine", "eucleed.iot", "1.0")
//				.name("coffee")
//				.label("coffee")
//				.icon("http://icons.iconarchive.com/icons/archigraphs/oldies/48/Coffee-Cup-icon.png")
//			//	.addFunction("RotateClockwise", "Rotate this motor clockwise.")
//				//.addFunction("RotateAntiClockwise", "Rotate this motor anti clockwise")
//				.addEvent("OnWaterHeated", "Event when water properly heated")
//				.addFunction("SwitchOn", "Function to make the coffee")
//				.addFunction("MakeCoffee", "Function to make the coffee")
//				.addFunction("SwitchOff", "Function to switch off machine")
//				.addEvent("OnCoffeeReady", "When coffee is ready")
//			.build();
		
		Util.client.publishDefinition(lamp);
		
		Util.client.publishDefinition(remote);
		
		Util.client.publishDefinition(motor);
	}

}
