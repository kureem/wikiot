package org.castafiore.iot.definitions;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Object model for configuring a Device definition.<br>
 * The device definition is submitted by a device during handshaking.
 * 
 * @author Kureem Rossaye
 *
 */
public class DeviceDefinition {

	/**
	 * A unique universal id for a definition.<br>
	 * The definitionId is set by the creator of the definition . <br>
	 * It should be unique in the context it is being used.<br>
	 * It should be in format "xx.xx.xx"
	 */
	private String id;

	/**
	 * Arbitrary specifications set by the device.<br>
	 * The specifications can be used to validate conformity
	 */
	private List<Property> props = new LinkedList<Property>();

	private List<EventDefinition> evts = new LinkedList<EventDefinition>();

	private List<FunctionDefinition> fns = new LinkedList<FunctionDefinition>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Property> getProps() {
		return props;
	}

	public void setProps(List<Property> props) {
		this.props = props;
	}

	public List<EventDefinition> getEvts() {
		return evts;
	}

	public void setEvts(List<EventDefinition> evts) {
		this.evts = evts;
	}

	public List<FunctionDefinition> getFns() {
		return fns;
	}

	public void setFns(List<FunctionDefinition> fns) {
		this.fns = fns;
	}

}
