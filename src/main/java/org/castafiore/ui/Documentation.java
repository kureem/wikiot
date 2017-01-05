package org.castafiore.ui;

import java.util.Map;

import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.form.EXFormPanel;
import org.castafiore.ui.form.EXInput;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class Documentation extends EXApplication{

	public Documentation() {
		super("documentation");
		
		addChild(new EXButton("btn", "Start").addEvent(new ServerEvent() {
			
			@Override
			public boolean ServerAction(Container container, Map<String, String> request) throws UIException {
				
				System.out.println(request);
				createFormPanel();
				return true;
			}
		}, Event.CLICK));
	}
	
	
	
	public void createFormPanel(){
		EXFormPanel panel = new EXFormPanel("User Profile", "User Profile");
		
		panel.addField("First name", new EXInput("firstName"));
		panel.addField("Last name", new EXInput("lastName"));
		panel.addField("Address Line 1", new EXInput("addressLine1"));
		panel.addField("Address Line 2", new EXInput("addressLine2"));
		panel.addField("City", new EXInput("city"));
		panel.addField("Postal Code", new EXInput("postalCode"));
		
		addChild(panel);
		
		
		
		
	}

}
