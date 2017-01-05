package org.castafiore.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class EXWebServletAwareApplication extends EXApplication implements WebServletAwareApplication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXWebServletAwareApplication(String name) {
		super(name);
	}

	private HttpServletRequest request_;
	
	private HttpServletResponse response_;
	
	@Override
	public void setRequest(HttpServletRequest request) {
		this.request_ = request;
		
	}

	@Override
	public void setResponse(HttpServletResponse response) {
		this.response_ = response;
		
	}
	
	
	public HttpServletRequest getRequest(){
		return this.request_;
	}
	
	
	public HttpServletResponse getResponse(){
		return this.response_;
	}

}
