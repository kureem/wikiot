package org.castafiore.iot;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CastafioreIOTProtocolHandler extends IOTProtocolHandler implements ApplicationContextAware{
	
	private ApplicationContext context;

	@Autowired
	private DeviceRegistry deviceRegistry;
	

	@Override
	public DeviceRegistry getRegistry() {
		return deviceRegistry;
	}

	@Override
	public Collection<IOTApplet> getApplets() {
		return context.getBeansOfType(IOTApplet.class).values();
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.context = ctx;
	}
	
	

}
