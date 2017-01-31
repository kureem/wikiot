package org.castafiore.iot;

import org.castafiore.iot.definitions.DefinitionRegistry;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/definitions")
public class DefinitionRegistryController {
	
	@Autowired
	private DefinitionRegistry definitionRegistry;
	
	@RequestMapping(method=RequestMethod.POST)
	public DeviceDefinition publish(@RequestBody DeviceDefinition definition){
		return definitionRegistry.publishDefinition(definition);
		
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public DeviceDefinition getDefinition(@PathVariable("id") String definitionId){
		return definitionRegistry.getDefinition(definitionId);
	}

}
