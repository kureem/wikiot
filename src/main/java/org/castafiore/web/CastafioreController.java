package org.castafiore.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.castafiore.ComponentNotFoundException;
import org.castafiore.Constant;
import org.castafiore.ui.Application;
import org.castafiore.ui.ApplicationRegistry;
import org.castafiore.ui.CastafioreApplicationContextHolder;
import org.castafiore.ui.CastafioreUIEngine;
import org.castafiore.ui.interceptors.InterceptorRegistry;
import org.castafiore.utils.ComponentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CastafioreController {
	
	@Autowired
	private InterceptorRegistry interceptorRegistry;
	
	
	@Autowired
	private ApplicationRegistry applicationRegistry;
	
	public void doMethod(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> getParameterMap(Map parameters)
	{
		
		Map<String, String> result = new HashMap<String, String>(parameters.size());
		Iterator iter = parameters.keySet().iterator();
		
		while(iter.hasNext())
		{
			String key = iter.next().toString();
			
			String value = ((String[])parameters.get(key))[0].toString();
			
			result.put(key, value);
		}
		
		return result;
	}
	
	
	
	






	/**
	 * handles upload made by EXUpload component
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	private void handleMultipartRequest(HttpServletRequest request, ServletResponse response)throws ServletException
	{
		//logger.debug("handling multipart request. A file is being uploaded....");
	
	}
	
	private CastafioreUIEngine getEngine(HttpServletRequest req){
		CastafioreUIEngine engine = (CastafioreUIEngine)req.getSession().getAttribute("CastafioreEngine");
		if(engine == null){
			engine = new CastafioreUIEngine(interceptorRegistry);
			req.getSession().setAttribute("CastafioreEngine", engine);
			return (CastafioreUIEngine)req.getSession().getAttribute("CastafioreEngine");
		}else{
			return engine;
		}
	}
	
	@RequestMapping("/castafiore/*")
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 
		boolean isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest)request);
		//loads application based on parameters
		ComponentUtil.loadApplication((HttpServletRequest)request, (HttpServletResponse)response, applicationRegistry);
		
		if(isMultipart)
		{
			//handle upload
			handleMultipartRequest((HttpServletRequest)request, response);
		}else if("true".equals(request.getParameter("upload"))){
			//doGeter(request, response);
			
		}
		else
		{
			//if(logger.isDebugEnabled())
				//logger.debug("handling normal action request");
			request.setCharacterEncoding("UTF-8");
			
			Map<String, String> params = this.getParameterMap(request.getParameterMap());
			
			String componentId = request.getParameter("casta_componentid");
			String eventId = request.getParameter("casta_eventid");
			String applicationId = request.getParameter("casta_applicationid");
			
			Assert.notNull(applicationId, "cannot execute a castafiore request when the applicationid is null. Please verify that the parameter casta_applicationid has been set correctly in tag, jsp or whatever");

			//gets the already loaded application
			Application applicationInstance = CastafioreApplicationContextHolder.getCurrentApplication();
			String script = "";

			if((componentId == null && eventId == null && applicationInstance != null))
			{ 
				applicationInstance.setRendered(false);
				script = getEngine(request).getJQuery(applicationInstance, "root_" + applicationId , applicationInstance, new ListOrderedMap());
				script = script + "hideloading();";
				
			}
			else if((componentId != null && eventId != null)&& applicationInstance != null){	
				//if(logger.isDebugEnabled())
					//logger.debug("executing server action: componentId:" + componentId + " applicationid:" + applicationId);
				try{
					//long start = System.currentTimeMillis();
					script = getEngine(request).executeServerAction(componentId,  applicationInstance, "root_" + applicationId, params);
					script = script + "hideloading();";
				}catch(ComponentNotFoundException cnfe){
					script = "window.location.reload( false );";
				}
			}else if((componentId != null && eventId != null)&& applicationInstance == null){
			//	if(logger.isDebugEnabled())
					//logger.debug("session must have been timed out");
				script = "alert('Your session has expired. Browser will refresh');window.location.reload( false );";
			}
			
			Set<String> requiredScript = applicationInstance.getBufferedResources();
			if(requiredScript != null && requiredScript.size() > 0)
			{
				StringBuilder reqScript = new StringBuilder();
				reqScript.append(Constant.NO_CONFLICT+ ".plugin('"+applicationInstance.getId()+"',{").append("files:[");
				int scount = 0;
				for(String s : requiredScript)
				{
					reqScript.append("'").append(s).append("'");
					scount++;
					if(scount < requiredScript.size())
					{
						reqScript.append(",");
					}
				}
				reqScript.append("],").append("selectors:['"+Constant.ID_PREF+"root_"+applicationId+"'],");
				reqScript.append("callback:function(){").append(script).append("}").append("});");
				reqScript.append(Constant.NO_CONFLICT + ".plugin('"+applicationInstance.getId()+"').get();");
				script = reqScript.toString();
			}
			applicationInstance.flush(12031980);
			//script =  Constant.NO_CONFLICT +"(document).ready(function(){" + script + "});";
			script ="<script>" + Constant.NO_CONFLICT +"(document).ready(function(){" + script + "});</script>";
			
		}
	}
	
	
	
	public void doExecute(HttpServletRequest request, HttpServletResponse response){
		
	}

}
