package org.castafiore.iot;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.castafiore.iot.definitions.DeviceDefinition;
import org.castafiore.iot.io.DownStreamMessage;
import org.castafiore.iot.io.OnEvent;
import org.castafiore.iot.io.UpStreamMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is the protocol layer. <br>
 * Its purpose it to interpret raw text message over a websocket layer according
 * to the iot protocol specification and takes necessary action.<br>
 * Although the protocol handler could be written in any language, this
 * implementation is solely for java web containers. It follows the websocket
 * specification.<br>
 * 
 * 
 * @author Kureem Rossaye
 *
 */
public abstract class IOTProtocolHandler extends TextWebSocketHandler {

	protected static ObjectMapper mapper = new ObjectMapper();

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
	}

	/**
	 * Main entry point behind the scene.<br>
	 * This class is used by iot server developers. They should extend this
	 * class to provide more user defined way to load {@link DeviceRegistry} and
	 * {@link IOTApplet}s<br>
	 * Subclass should include the class level annotation {@link ServerEndpoint}
	 * annotation.
	 * 
	 * @param session
	 *            A websocket connection initated by a device
	 * @param msg
	 *            The text json message sent by the device according to the iot
	 *            protocol.
	 * @param last
	 *            Indicates if this is a last message and the session has been
	 *            closed.
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		try {

			logger.debug("OnMessage:{" + session.getUri() + "," + message.getPayload());
			UpStreamMessage request = mapper.readValue(message.getPayload(), UpStreamMessage.class);

			String type = request.getType();

			DownStreamMessage response = new DownStreamMessage();
			String deviceId = request.getDeviceId();

			if (type.equals(UpStreamMessage.HANDSHAKE)) {

				DeviceDefinition definition = mapper.readValue(request.getBody(), DeviceDefinition.class);
				Device device = getRegistry().associate(deviceId, definition);
				logger.info("device registered:{" + device.getDeviceId() + "," + device.getDefinition().getId() + "}");
				device.connect(session);

				for (IOTApplet applet : getApplets()) {
					Collection<String> requiredDevices = applet.getRequiredDevices();
					if (requiredDevices.contains(device.getDeviceId())) {
						applet.onDeviceConnected(device);
						logger.info("Device connected:{" + device.getDeviceId() + "}");
					}
				}

				String result = mapper.writeValueAsString(response);
				session.sendMessage(new TextMessage(result));
				logger.debug("Response sent after handshake:" + result);

			} else if (type.equals(UpStreamMessage.IO)) {
				OnEvent event = mapper.readValue(request.getBody(), OnEvent.class);
				getRegistry().getDevice(deviceId).propageEvents(event.getName(), event.getParameters());
				logger.debug("Event propagated:{" + event.getName() + "," + deviceId + "}");
			}

		} catch (Exception jme) {
			logger.error("Error while executing request:" + message.getPayload(), jme);
			throw new RuntimeException(jme);
		}

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable t) throws Exception {
		logger.error("Error in session:" + session, t);
		super.handleTransportError(session, t);

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		logger.debug("Session closed:" + session + ":" + status.getReason());
		super.afterConnectionClosed(session, status);
	}

	/**
	 * Conventional OnError event executed when any error occures.<br>
	 * TODO Delegate exception handling to application layer.
	 * 
	 * @param session
	 *            The session on which the error has occured
	 * @param t
	 *            The error
	 */

	/**
	 * The loading {@link IOTApplet} configured in the container is left over to
	 * custom implementations.<br>
	 * The {@link IOTApplet} can be configured in a spring application context,
	 * or in a persistent storage system.
	 * 
	 * @return The list of {@link IOTApplet} configured in this container.
	 */
	public abstract Collection<IOTApplet> getApplets();

	/**
	 * The loading of the {@link DeviceRegistry} is left over to custom
	 * implementation<br>
	 * Server implementer can choose how connected devices are stored and
	 * retrieved. Either in memory, or in a persistent storage or in the cloud.
	 * <br>
	 * 
	 * @return {@link DeviceRegistry} associated to this container.
	 */
	public abstract DeviceRegistry getRegistry();

}
