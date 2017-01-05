package org.castafiore.ui.form;

import java.util.ArrayList;
import java.util.List;

import org.castafiore.ui.Container;
import org.castafiore.ui.FormInput;
import org.castafiore.ui.navigation.EXTabPanel;
import org.castafiore.ui.navigation.TabModel;
import org.castafiore.ui.navigation.TabPanel;

public class EXTabFieldSet extends EXTabPanel implements TabModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> labels = new ArrayList<String>();

	private List<EXFieldSet> fieldsets = new ArrayList<EXFieldSet>();

	public EXTabFieldSet(String name) {
		super(name);
	}

	@Override
	public String getTabLabelAt(TabPanel pane, int index, boolean selected) {
		return labels.get(index);
	}

	@Override
	public Container getTabContentAt(TabPanel pane, int index) {
		return fieldsets.get(index);
	}

	@Override
	public int getSelectedTab() {
		return 0;
	}

	@Override
	public int size() {
		return labels.size();
	}

	public EXTabFieldSet addTab(String name, String label, boolean doublecol) {
		labels.add(label);
		fieldsets.add(new EXFieldSet(name, label, doublecol));
		return this;
	}

	public void refresh() {
		super.refresh();
		setModel(this);

		setShowTabs(labels.size() > 1);

		for (EXFieldSet fs : fieldsets) {
			fs.setShowTitle(labels.size() == 1);
		}
	}

	@SuppressWarnings("rawtypes")
	public void addField(String formName, String label, FormInput field) {
		for (EXFieldSet f : fieldsets) {
			if (f.getName().equals(formName)) {
				f.addField(label, field);
			}
		}
	}

}
