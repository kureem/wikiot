/*
 * Copyright (C) 2007-2008 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

package org.castafiore.ui.dnd;

import java.util.Map;

import org.castafiore.ui.Container;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.js.JSMap;
/**
 * 
 * 
 * @author Kureem Rossaye<br>
 *          kureem@gmail.com
 * Oct 22, 2008
 */
public class SimpleResizableEvent implements Event {
	
	private static final long serialVersionUID = 1L;
	
	private JSMap options = null;
	
	

	public SimpleResizableEvent(JSMap options) {
		super();
		this.options = options;
	}

	public void ClientAction(JQuery application) {

//		JMap jmap = new JMap();
//		//jmap.append("autoHide", "true");
//		jmap.put("knobHandles", "true");
//		jmap.put("handles", "se");
//		
//		
//		ClientProxy knob = new ClientProxy(".ui-resizable-se");
//		knob.setStyle("background-image", "url('"+ResourceUtil.getDownloadURL("classpath", "org/castafiore/resource/resize-handle.gif")+"')");
//		knob.setStyle("width", "12px");
//		knob.setStyle("height", "12px");
//		knob.setStyle("border-width", "0px");
		
		
		
		application.resizeable(options);
		//application.appendJSFragment(knob.getCompleteJQuery());
		
		//application.click()
	}

	public boolean ServerAction(Container component,
			Map<String, String> requestParameters) throws UIException {
		
		return false;
	}

	public void Success(JQuery component, Map<String, String> requestParameters) throws UIException {
		// TODO Auto-generated method stub
		
	}

}
