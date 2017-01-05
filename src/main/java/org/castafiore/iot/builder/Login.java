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
package org.castafiore.iot.builder;

import java.util.Map;
import java.util.Random;

import org.castafiore.ui.Container;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.form.EXInput;
import org.castafiore.ui.layout.EXXHTMLFragment;
import org.castafiore.utils.StringUtil;

public class Login extends EXXHTMLFragment implements Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EXInput email = new EXInput("email");
	
	private EXButton button = new EXButton("enter", "Enter");
	
	private EXButton generate = new EXButton("generate", "Generate");
	Random ran = new Random();
	
	public Login(String name) {
		super(name, "templates/Login.xhtml");
		
		addChild(email);
		email.setEnabled(false);
		
		addChild(button.addEvent(this, CLICK).setStyle("margin", "0 12px"));
		
		addChild(generate.addEvent(this, CLICK));
	}

	@Override
	public void ClientAction(JQuery container) {
		container.server(this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		
		if(container.equals(generate)){
			email.setValue(generateString(ran, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 5));
			return true;
		}
		
		String email = this.email.getValue();
		
		if(StringUtil.isNotEmpty(email) ){
			getAncestorOfType(Builder.class).login(email);
		}else{
			this.email.addClass("err");
		}
		
		
		
		return true;
	}

	
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
