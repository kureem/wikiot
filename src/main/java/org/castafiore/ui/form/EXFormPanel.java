package org.castafiore.ui.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;
import org.castafiore.ui.FormInput;
import org.castafiore.ui.button.Button;
import org.castafiore.ui.panel.EXPanel;
import org.castafiore.utils.ComponentUtil;

public class EXFormPanel extends EXPanel implements Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EXFieldSet body = null;

	public EXFormPanel(String name, String title, int columns) {
		super(name, title);

		body = new EXFieldSet(name, title, columns);
		body.setShowTitle(false);
		setBody(body);
	}

	public EXFormPanel(String name, String title) {
		this(name, title, 1);

	}

	public Form addButton(Button button) {
		setShowFooter(true);
		getFooterContainer().addChild(button);
		return this;
	}

	@SuppressWarnings("rawtypes")
	public Form addField(String label, FormInput input) {
		body.addField(label, input);
		return this;
	}

	public Form addOtherItem(Container input) {

		body.addField(new EXContainer("", "label"), input, 1);
		return this;
	}

	public Form hideField(String name) {
		body.hideField(name);
		return this;
	}

	public Form showField(String name) {
		body.showField(name);
		return this;
	}

	@SuppressWarnings("rawtypes")
	public void setLabelFor(String label, FormInput input) {
		body.setLabelFor(label, input);

	}

	@SuppressWarnings("rawtypes")
	public Map<String, FormInput> getFieldsMap() {
		Map<String, FormInput> result = new HashMap<String, FormInput>();
//		List<Container> children = getBody().getChildren();
//		for (Container c : children) {
//			StatefullComponent stf = c
//					.getDescendentOfType(StatefullComponent.class);
//			if (stf != null) {
//				result.put(stf.getName(), stf);
//			}
//		}
//		return result;
		for(FormInput stf : this.body.getFields()){
			result.put(stf.getName(), stf);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public FormInput getField(String name) {
		return getFieldsMap().get(name);
	}

	@SuppressWarnings("rawtypes")
	public List<FormInput> getFields() {
		List<FormInput> result = new ArrayList<FormInput>();
		List<Container> children = getBody().getChildren();

		for (Container c : children) {
			FormInput stf = c
					.getDescendentOfType(FormInput.class);
			if (stf != null) {
				result.add(stf);
			}
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, String> getFieldValues() {
		Map<String, String> result = new HashMap<String, String>();
		List<Container> children = getBody().getChildren();
		for (Container c : children) {
			FormInput stf = c
					.getDescendentOfType(FormInput.class);
			if (stf != null) {
				result.put(stf.getName(), stf.getValue().toString());
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Button> getButtons() {
		Container footer = getFooterContainer();
		List result = new ArrayList();
		ComponentUtil.getDescendentsOfType(footer, result, Button.class);

		return result;
	}

}
