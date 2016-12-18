package org.castafiore.iot;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.castafiore.iot.definitions.DefinitionBuilder;
import org.castafiore.iot.definitions.DefinitionRegistry;
import org.castafiore.iot.definitions.DeviceDefinition;

public class MemoryDefinitionRegistry implements DefinitionRegistry{
	
	private Map<String, DeviceDefinition> definitions = new LinkedHashMap<String, DeviceDefinition>();

	

	public MemoryDefinitionRegistry(){
		DeviceDefinition lamp = DefinitionBuilder.create("Lamp", "eucleed.iot", "1.0")
				.name("Lamp")
				.label("Simple lamp")
				.icon("http://72.13.93.222/iot/img/lamp.png")
				.addFunction("SwitchOn", "Switch on lamp")
				.addFunction("SwitchOff", "Switch off lamp")
			.build();
		
		DeviceDefinition remote = DefinitionBuilder.create("Remote", "eucleed.iot", "1.0")
				.name("Remote")
				.label("Simple remote")
				.icon("http://72.13.93.222/iot/img/remote.png")
				.addEvent("OnSwitchOn", "When switch on is pressed on remote")
				.addEvent("OnSwitchOff", "When Switch off is pressed on remote")
			.build();
		
		DeviceDefinition motor = DefinitionBuilder.create("Motor", "eucleed.iot", "1.0")
				.name("Motor")
				.label("Simple motor")
				.icon("http://72.13.93.222/iot/img/motor.png")
				.addFunction("RotateClockwise", "Rotate this motor clockwise.")
				.addFunction("RotateAntiClockwise", "Rotate this motor anti clockwise")
			.build();
		
		publishDefinition(lamp);
		publishDefinition(remote);
		publishDefinition(motor);
	}
	
	
	@Override
	public DeviceDefinition getDefinition(String definitionId, String groupId,
			String versionId) {
		return definitions.get(getKey(definitionId, groupId, versionId));
	}
	
	protected String getKey(String definitionId, String groupId,
			String versionId){
		return definitionId + "#" + groupId + "#" + versionId;
	}

	@Override
	public DeviceDefinition publishDefinition(DeviceDefinition definition) {
		
		definitions.put(getKey(definition.getDefinitionId(), definition.getGroupId(), definition.getVersionId()), definition);
		return definition;
		
	}
	
	

	public void setDefinitions(Map<String, DeviceDefinition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public Collection<DeviceDefinition> getDefinitions() {
		return definitions.values();
	}

}
