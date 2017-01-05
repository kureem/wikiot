package org.castafiore.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Data {

	private String id;

	private String html;

	private Map<String, String> styles = new HashMap<String, String>();

	private Map<String, String> attributes = new HashMap<String, String>();


	private List<JEvent> events = new LinkedList<JEvent>();
	
	private String creation;
	
	private boolean rendered;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Map<String, String> getStyles() {
		return styles;
	}

	public void setStyles(Map<String, String> styles) {
		this.styles = styles;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}


	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	public List<JEvent> getEvents() {
		return events;
	}

	public void setEvents(List<JEvent> events) {
		this.events = events;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	
	
	

}
