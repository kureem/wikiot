package org.castafiore.ui.js;

import org.castafiore.ui.JQuery;

public class Ajax implements JSObject{
	
	private JSMap options = new JSMap();
	public Ajax(String url, JSMap options){
		this.options = options;
		options.put("url", url);
	}
	
	
	public Ajax setData(JSMap data){
		options.put("data", data);
		return this;
	}
	
	
	public Ajax onSuccess(JQuery function){
		options.put("success", function, "data", "textStatus", "jqXHR");
		return this;
	}
	
	public Ajax onComplete(JQuery function){
		options.put("complete", function, "data", "textStatus", "jqXHR");
		return this;
	}
	
	public Ajax onError(JQuery function){
		options.put("error", function, "data", "textStatus", "jqXHR");
		return this;
	}
	public JSMap getOptions(){
		return options;
	}

	@Override
	public String getJavascript() {
		
		String ajax = "$.ajax(" + options.getJavascript() + ");";
		
		return ajax;
	}

}
