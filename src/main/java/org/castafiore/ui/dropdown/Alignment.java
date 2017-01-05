package org.castafiore.ui.dropdown;

public enum Alignment {

	RIGHT("dropdown-menu-right"),
	LEFT("dropdown-menu-left");
	
	
	private String styleClass;

	private Alignment(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public String styleClass(){
		return styleClass;
	}
	
	
}
