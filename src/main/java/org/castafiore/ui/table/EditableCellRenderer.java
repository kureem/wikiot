package org.castafiore.ui.table;

import org.castafiore.ui.FormInput;

public interface EditableCellRenderer extends CellRenderer{
	@SuppressWarnings("rawtypes")
	public FormInput getInputAt(int row, int column,int page, EditableTableModel model, EXEditableTable table);
	
}
