package org.castafiore.ui.button;

public enum Size {
	
	SMALL("btn-sm"),
	EXTRA_SMALL("btn-xs"),
	NORMAL("btn-n"),
	LARGE("btn-lg");
	
	private String styleClass;

	Size(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public String styleClass(){
		return styleClass;
	}

}
