package org.castafiore.iot;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.castafiore.iot.definitions.DefinitionBuilder;
import org.castafiore.iot.definitions.DefinitionRegistry;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.springframework.stereotype.Component;

@Component
public class MemoryDefinitionRegistry implements DefinitionRegistry {

	private Map<String, DeviceDefinition> definitions = new LinkedHashMap<String, DeviceDefinition>();

	public MemoryDefinitionRegistry() {
		DeviceDefinition lamp = DefinitionBuilder.create("lmp.0.0")
				.addFunction("SwitchOn", "Switch on lamp")
				.addFunction("SwitchOff", "Switch off lamp").build();

		DeviceDefinition remote = DefinitionBuilder.create("ctr.0.0")
				.addEvent("OnSwitchOn", "When switch on is pressed on remote")
				.addEvent("OnSwitchOff", "When Switch off is pressed on remote").build();

		DeviceDefinition motor = DefinitionBuilder.create("mtr.0.0")
				.addFunction("RotateClockwise", "Rotate this motor clockwise.")
				.addFunction("RotateAntiClockwise", "Rotate this motor anti clockwise").build();

		publishDefinition(lamp);
		publishDefinition(remote);
		publishDefinition(motor);
	}

	@Override
	public DeviceDefinition getDefinition(String definitionId, String groupId, String versionId) {
		return definitions.get(getKey(definitionId));
	}

	protected String getKey(String id) {
		return id;
	}

	@Override
	public DeviceDefinition publishDefinition(DeviceDefinition definition) {

		definitions.put(getKey(definition.getId()),
				definition);
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
