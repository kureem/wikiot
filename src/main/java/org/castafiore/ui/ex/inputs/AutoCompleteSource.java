package org.castafiore.ui.ex.inputs;

import java.io.Serializable;

import org.castafiore.ui.js.JSArray;

public interface AutoCompleteSource extends Serializable{
	
	public JSArray getSource(String param);

}
