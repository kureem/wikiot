/*
 * Copyright (C) 2007-2010 Castafiore
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
package org.castafiore.ui.panel;

import java.util.List;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.Event;
import org.castafiore.ui.button.Type;
import org.castafiore.ui.js.JSMap;

public class EXPanel extends EXContainer implements  Panel {
	private static final long serialVersionUID = 1L;
	private Container labelContainer = new EXContainer("labelContainer", "div").setText("Panel");

	//private Container closeButton = new EXContainer("closeButton", "a").setAttribute("href", "#").addClass("wclose")
		//	.setText("<img src='js1/themes/icons/edit_remove.png'></img>");//.addEvent(CLOSE_EVENT, Event.CLICK);

	//private Container mimimizeButton = new EXContainer("minimizeButton", "a").setAttribute("href", "#")
		//	.addClass("wminimize").setText("<i class=\"icon-chevron-up\"></i>");

	private Container bodyContainer = new EXContainer("bodyContainer", "div").addClass("panel-body");

	private Container widgetHead = new EXContainer("widget-head", "div").addClass("panel-heading");

	private Container widgetFoot = new EXContainer("widgetFoot", "div").addClass("panel-footer");

	public EXPanel(String name, String title) {
		super(name, "div");
		addClass("panel");
		addChild(widgetHead);
		widgetHead.addChild(labelContainer.setText(title));

	//	Container buttonsContainer = new EXContainer("buttonsContainer", "div").addClass("pull-right")
			//	.addClass("widget-icons");
		//widgetHead.addChild(buttonsContainer);

		//buttonsContainer.addChild(mimimizeButton);
		//buttonsContainer.addChild(closeButton);

		addChild(bodyContainer);

		addChild(widgetFoot);
		setType(Type.DEFAULT);

	}

	public void setCloseButtonEvent(Event event) {
	//	closeButton.getEvents().get(Event.CLICK).clear();
		//closeButton.addEvent(event, Event.CLICK);
	}
	
	public EXPanel setType(Type t){
		for(Type tt : Type.values()){
			removeClass(tt.styleClass().replace("btn-",  "panel-"));
		}
		
		addClass(t.styleClass().replace("btn-",  "panel-"));
		
		return this;
	}

	public EXPanel(String name) {
		this(name, "");
	}

	public Container getBody() {
		List<Container> childBody = getBodyContainer().getChildren();
		if (childBody.size() > 0) {
			return childBody.get(0);
		} else {
			return null;
		}
	}

	public Panel setBody(Container container) {
		getBodyContainer().getChildren().clear();
		getBodyContainer().setRendered(false);
		getBodyContainer().addChild(container);
		return this;
	}

	public Panel setShowCloseButton(boolean b) {
		//closeButton.setDisplay(b);
		return this;
	}

	public Panel setShowHeader(boolean showHeader) {
		widgetHead.setDisplay(showHeader);
		return this;

	}

	public Panel setTitle(String title) {
		labelContainer.setText(title);
		return this;

	}

	public void addPopup(Container popup) {
		addChild(popup);
	}

	public Panel setShowFooter(boolean display) {
		widgetFoot.setDisplay(display);
		return this;
	}

	public Container getFooterContainer() {
		return widgetFoot;
	}

	protected Container getBodyContainer() {
		return bodyContainer;
	}

	public Container setDraggable(boolean draggable) {
		if (draggable) {
			JSMap options = new JSMap().put("opacity", 0.35).put("handle", "#" + widgetHead.getId());
			options.put("containment", "document");
			setDraggable(true, options);
			setStyle("position", "absolute");
			setStyle("top", "10%");
			setStyle("left", "10%");
		} else {
			super.setDraggable(false);
			setStyle("position", "static");
		}
		return this;
	}

}
