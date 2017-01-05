package org.castafiore.ui.button;

public enum Type {
	
	DEFAULT ("btn-default"),
	PRIMARY ( "btn-primary"),
	SUCCESS ( "btn-success"),
	INFO ( "btn-info"),
	WARNING ( "btn-warning"),
	DANGER ( "btn-danger"),
	LINK ( "btn-link");
	
	private final String styleClass;
	
	Type(String style){
		this.styleClass = style;
	}

	
	public String styleClass(){
		return styleClass;
	}
}
