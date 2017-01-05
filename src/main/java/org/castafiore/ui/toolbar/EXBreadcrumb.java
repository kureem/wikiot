package org.castafiore.ui.toolbar;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;

public class EXBreadcrumb extends EXContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXBreadcrumb(String name) {
		super(name, "ol");
	}
	
	public Container addItem(String label){
		Container li = new EXContainer("", "li");
		addChild(li);
		Container item = new EXContainer("", "a").setAttribute("href", "#").setText(label);
		li.addChild(item);
		return item;
	}

}
