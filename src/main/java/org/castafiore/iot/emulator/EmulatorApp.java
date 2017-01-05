/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castafiore.iot.emulator;

import java.util.Map;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.Event;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.form.EXInput;
import org.castafiore.ui.layout.EXMigLayout;
import org.castafiore.ui.toolbar.EXToolBar;
import org.castafiore.utils.JavascriptUtil;
import org.castafiore.ui.ServerEvent;

public class EmulatorApp extends EXContainer implements Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EXButton addNew = new EXButton("addNew", "Add new device");

	private EXMigLayout main = new EXMigLayout("", "12;12");

	private Container devices = new EXContainer("devices", "div").addClass("loading-emulation");

	public EmulatorApp() {
		super("emulator", "div");
		addClass("container");
		EXToolBar tb = new EXToolBar("sda");
		tb.setStyle("margin", "12px");
		tb.addItem(addNew);
		tb.setDisplay(false);
		EXButton connectAll = new EXButton("connectAll", "Connect All");
		
		Container restart = new EXContainer("restart", "a").addClass("btn btn-danger").setText("Reset").setAttribute("href", "logout.jsp?to=index.html");
		
		tb.addItem(connectAll);
		tb.addChild(restart.setDisplay(false));
		connectAll.setDisplay(false);
		main.setStyle("width", "100%");
		addNew.addEvent(this, CLICK);
		connectAll.addEvent(this, CLICK);
		main.addChild(tb, "0:0");

		Container devicec = new EXContainer("sda", "div").addClass("container");
		devicec.addChild(devices);
		main.addChild(devices, "0:1");

		// addChild(new Emulator());
		//addDevice();

		addEvent(new ServerEvent() {


			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean ServerAction(Container container,
					Map<String, String> request) throws UIException {
				return true;
			}

			@Override
			public void ClientAction(JQuery container) {
				container.eval("setInterval('"
						+ JavascriptUtil.javaScriptEscape(container.clone()
								.server(this).getCompleteJQuery()) + "', "
						+ 2000 + ")");

			}
		}, Event.READY);
		
		
		addChild(main);
		
		
		
		//addChild(panel);
	}

	public void addDevice(String namespace, String serialNo) {

			Emulator e = new Emulator();
			devices.addChild(new EXContainer("", "div").addClass("col-md-12").addChild(e));
			
			//Application app = getRoot();
			String contextPath = "iot";//app.getContextPath();
			String serverPort = "80";
			String servaerName = "72.13.93.222";
			
			if(!contextPath.startsWith("/")){
				contextPath = "/" + contextPath;
			}
			
			String store = "http://" + servaerName + ":" + serverPort  + contextPath;
			String server = "ws://" + servaerName + ":" + serverPort  + contextPath + "/websockets/iot";
			((EXInput)e.getDescendentByName("deviceStore")).setValue(store);
			((EXInput)e.getDescendentByName("server")).setValue(server);
			((EXInput)e.getDescendentByName("namespace")).setValue(namespace);
			((EXInput)e.getDescendentByName("serialNo")).setValue(serialNo);
			((EXInput)e.getDescendentByName("deviceStore")).setDisplay(false);
			((EXInput)e.getDescendentByName("server")).setDisplay(false);
			e.getDescendentByName("groupId").setDisplay(false);
			e.getDescendentByName("versionId").setDisplay(false);
			if(devices.getChildren().size() > 1){
				getDescendentByName("connectAll").setStyle("display", "inline");
			}
			
			if(devices.getChildren().size() >0){
				getDescendentByName("restart").setStyle("display", "inline");
			}
			
			

	}


	@Override
	public void ClientAction(JQuery container) {
		container.server(this);

	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {

		if(container.getName().equals("connectAll")){
			for(Container c : devices.getChildren()){
				c.getDescendentOfType(Emulator.class).reconnect();
			}
		}else{
		addDevice("","");
		}
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		// TODO Auto-generated method stub

	}

}