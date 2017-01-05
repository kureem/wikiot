package org.castafiore.ui.later;

import java.io.Serializable;

import org.castafiore.ui.js.JSArray;

public interface AutoCompleteSource extends Serializable{
	
	public JSArray getSource(String param);

}
