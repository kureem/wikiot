package org.castafiore.ui.later;

import java.util.Map;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.Event;
import org.castafiore.ui.FormInput;
import org.castafiore.ui.JQuery;
import org.castafiore.ui.UIException;
import org.castafiore.ui.js.JSMap;
import org.castafiore.ui.js.JSVar;

public class EXSlider extends EXContainer implements FormInput<Integer>, Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orientation = "horizontal";
	
	private int min =0;;
	
	private int max = 100;
	
	private boolean animate = false;
	
	private int step = 1;
	
	

	@Override
	public void onReady(JQuery proxy) {
		super.onReady(proxy);
		
		String s = "$( '"+proxy.getIdRef()+"' ).slider( 'option', 'value' )";
		JQuery p = proxy.clone().makeServerRequest( new JSMap().put("val", new JSVar(s)),this);
		
		JSMap par = new JSMap().put("origntation", orientation).put("min", min).put("max", max).put("animate", animate).put("step", step).put("value", getAttribute("value"));
		
		par.put("stop",p, "event", "ui");
		proxy.invoke("slider", par);
	}

	public EXSlider(String name) {
		super(name, "div");
		setAttribute("value", "0");
		addEvent(this, Event.MISC);
		
	}

	

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
		setRendered(false);
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
		setRendered(false);
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
		setRendered(false);
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
		setRendered(false);
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
		setRendered(false);
	}

	@Override
	public void ClientAction(JQuery container) {
		String s = "$( '"+container.getIdRef()+"' ).slider( 'option', 'value' )";
		container.makeServerRequest( new JSMap().put("val", new JSVar(s)),this);
		
	}

	@Override
	public boolean ServerAction(Container container, Map<String, String> request)
			throws UIException {
		String val = request.get("val");
		setAttribute("value", val);
		SliderHandler p = getAncestorOfType(SliderHandler.class);
		if(p != null)
			p.propagate();
		return true;
	}

	@Override
	public void Success(JQuery container, Map<String, String> request)
			throws UIException {
		
	}

	@Override
	public void setEnabled(boolean enabled) {
		
	}

	@Override
	public Integer getValue() {
		try{
			return Integer.parseInt(getAttribute("value"));
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public void setValue(Integer value) {
		setAttribute("value", value.toString());
	}

	
}
