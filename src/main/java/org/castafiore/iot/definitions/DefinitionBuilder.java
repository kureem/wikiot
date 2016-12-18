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
package org.castafiore.iot.definitions;

public class DefinitionBuilder {
	
	
	private DeviceDefinition def;
	private DefinitionBuilder(DeviceDefinition definition){
		this.def = definition;
	}
	public static DefinitionBuilder create(String definitionId, String groupId, String versionId){
		DeviceDefinition def = new DeviceDefinition();
		def.setDefinitionId(definitionId);
		def.setGroupId(groupId);
		def.setVersionId(versionId);
		return new DefinitionBuilder(def);
	}
	
	public DefinitionBuilder addEvent(String name, String description){
		EventDefinition evt = new EventDefinition();
		evt.setDescription(description);
		evt.setName(name);
		def.getEvents().add(evt);
		return this;
	}
	
	public DefinitionBuilder addFunction(String name, String description){
		FunctionDefinition func = new FunctionDefinition();
		func.setDescription(description);
		func.setName(name);
		def.getFunctions().add(func);
		return this;
	}
	
	public DefinitionBuilder name(String name){
		def.setName(name);
		return this;
	}

	/**
	 * A human readable label
	 */
	public DefinitionBuilder label(String label){
		def.setLabel(label);
		return this;
	}

	/**
	 * An icon representing the device.<br>
	 * This should be a simple url accessible by the server
	 */
	public DefinitionBuilder icon(String icon){
		def.setIcon(icon);
		return this;
	}
	
	/**
	 * Arbitrary specifications set by the device.<br>
	 * The specifications can be used to validate conformity
	 */
	public DefinitionBuilder specs(String label, String value){
		def.getSpecs().put(label, value);
		return this;
	}
	
	public DeviceDefinition build(){
		return def;
	}

}
