package org.castafiore.iot.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.castafiore.iot.definitions.DefinitionRegistry;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.iot.definitions.EventDefinition;
import org.castafiore.iot.definitions.Property;
import org.castafiore.iot.io.DisconnectedDeviceHandler;
import org.castafiore.iot.io.OnConnectedListener;
import org.castafiore.iot.io.OnEvent;
import org.castafiore.iot.io.UpStreamMessage;
import org.castafiore.iot.io.WebSocketLayer;

/**
 * 
 * This class is a generic wrapper for a device.<br>
 * This implementation is in java and intended to run on any device that can
 * executes a jvm.<br>
 * See other projects for implementation of a device wrapper in other<br>
 * languages. <br>
 * The device is associated with an iot server.<br>
 * This class helps for the following:<br>
 * <ul>
 * <li>Create a device definition dynamically</li>
 * <li>Manage websocket connection with server</li>
 * <li>Encapsulates the complexities of constructing websocket messages
 * according to the iot protocol</li>
 * <li>Manage life cycle of device</li>
 * </ul>
 * 
 * <pre>
 * 
 * {
 * 	&#64;code
 * 	final Device mydevice = new Device("BasicSwitch", "Switch", "Switch", "switch.png");
 * 
 * 	mydevice.registerFunction("SwitchOn", "Function exposed to server");
 * 
 * 	mydevice.registerFunction("SwitchOff", "Function exposed to server for switching off the device");
 * 
 * 	mydevice.setWebsocketLayer(new JavaWebsocketLayer(mydevice));
 * 
 * 	final JLabel state = new JLabel();
 * 
 * 	mydevice.addFunctionHandler(new FunctionHandler() {
 * 
 * 		public void execute(String name, Map<String, String> input) {
 * 			if (name.equals("SwitchOn")) {
 * 				state.setText("Switched on");
 * 			} else {
 * 				state.setText("Switched off");
 * 			}
 * 		}
 * 	});
 * 
 * 	mydevice.onReady(new OnReady() {
 * 
 * 		public void ready() {
 * 			JFrame frame = new JFrame("My switch");
 * 
 * 			frame.getContentPane().add(state, BorderLayout.NORTH);
 * 
 * 			frame.setSize(200, 200);
 * 
 * 			frame.setVisible(true);
 * 
 * 		}
 * 	});
 * 
 * 	mydevice.connect("ws://localhost:8080/iot/websockets/iot");
 * 
 * }
 * </pre>
 * 
 * @author Kureem Rossaye
 * 
 */
public class Device implements OnConnectedListener {

	private DeviceDefinition definition;

	private String server;

	private WebSocketLayer websocket;

	private OnReady onready = null;

	private boolean initialised = false;

	private String deviceId;

	public Device(String deviceId, String definitionId, String groupId, String versionId, DefinitionRegistry registry) {
		definition = registry.getDefinition(definitionId, groupId, versionId);
		this.deviceId = deviceId;
	}

	public Device(String deviceId, DeviceDefinition definition) {

		this.definition = definition;
		this.deviceId = deviceId;

	}

	public void setWebsocketLayer(WebSocketLayer layer) {
		this.websocket = layer;
		websocket.addOnConnectedListener(this);
	}

	public Device setSpec(String key, String value) {
		for (Property p : definition.getProps()) {
			if (p.getName().equals(key)) {
				p.setValue(value);
				return this;
			}
		}
		Property p = new Property();
		p.setName(key);
		p.setValue(value);
		definition.getProps().add(p);
		return this;
	}

	public Device registerEvent(String name, String description) {
		EventDefinition event = new EventDefinition();
		event.setName(name);
		event.setDescription(description);
		definition.getEvts().add(event);
		return this;
	}

	public void handshake() {
		UpStreamMessage up = new UpStreamMessage();
		up.setDeviceId(deviceId);
		up.setType(UpStreamMessage.HANDSHAKE);
		websocket.sendRequest(up, definition);
	}

	public void propagateEvent(String event, Map<String, String> parameters) {
		UpStreamMessage request = new UpStreamMessage();
		request.setType(UpStreamMessage.IO);
		request.setDeviceId(deviceId);
		OnEvent e = new OnEvent();
		e.setName(event);
		e.setParameters(parameters);
		websocket.sendRequest(request, e);

	}

	public void propagateEvent(String event) {
		propagateEvent(event, new HashMap<String, String>());

	}

	public Device addFunctionHandler(FunctionHandler handler) {
		websocket.addFunctionHandler(handler);
		return this;
	}

	public Device setDisconnectedDeviceHandler(DisconnectedDeviceHandler handler) {
		websocket.setDisconnectedDeviceHandler(handler);
		return this;
	}

	public Device connect(String server) {
		System.out.println(server);
		this.server = server;
		websocket.connect(server);
		return this;
	}

	public Device reconnect() {
		connect(server);
		return this;
	}

	public boolean isConnected() {
		return websocket.isConnected();
	}

	public Device disconnect() throws IOException {
		websocket.disconnect();
		return this;
	}

	@Override
	public void onConnected(Device device) {
		UpStreamMessage stream = new UpStreamMessage();
		stream.setDeviceId(deviceId);
		stream.setType(UpStreamMessage.HANDSHAKE);
		websocket.sendRequest(stream, definition);
		if (!initialised) {
			initialised = true;
			if (onready != null) {
				onready.ready();
			}
		} else {

		}

	}

	public Device onReady(OnReady function) {
		this.onready = function;
		return this;
	}

}
