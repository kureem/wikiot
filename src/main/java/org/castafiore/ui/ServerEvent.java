package org.castafiore.ui;

import java.util.Map;

public abstract class ServerEvent implements Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	

	@Override
	public void Success(JQuery container, Map<String, String> request) throws UIException {
		
	}

}
