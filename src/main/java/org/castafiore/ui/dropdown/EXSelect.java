/*
 * Copyright (C) 2007-2008 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

package org.castafiore.ui.dropdown;

import org.castafiore.SimpleKeyValue;
import org.castafiore.ui.form.AbstractFormComponent;

/**
 * 
 * @author Kureem Rossaye<br>
 *         kureem@gmail.com June 27 2008
 */
@SuppressWarnings("serial")
public class EXSelect<T> extends AbstractFormComponent<T> {

	private DataModel<T> model_;

	public EXSelect(String name, DataModel<T> model) {
		super(name, "select");

		this.model_ = model;
		addClass("form-control");
		setAttribute("value", "0");
	}

	public EXSelect(String name, DataModel<T> model, T value) {
		super(name, "select");
		setValue(value);
		addClass("form-control");
		setAttribute("value", "0");
	}

	public void setModel(DataModel<T> model) {
		this.model_ = model;
		setRendered(false);
	}

	public DataModel<T> getModel() {
		return model_;
	}

	public void setEnabled(boolean enabled) {
		if (enabled) {
			setAttribute("disabled", (String) null);
		} else {
			setAttribute("disabled", "disabled");
		}
	}

	public boolean isEnabled() {
		return getAttribute("disabled").equals("disabled");
	}

	@Override
	public T getValue() {
		int index = getSelectedIndex();
		return model_.getValue(index);

	}

	public void setSelectedValue(int index) {
		setAttribute("value", index + "");
	}

	public int getSelectedIndex() {
		try {
			return Integer.parseInt(getAttribute("value"));
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void setValue(T value) {
		int size = model_.getSize();
		if (value == null) {
			setSelectedValue(0);
			return;
		}
		for (int i = 0; i < size; i++) {

			T data = model_.getValue(i);
			if (data == null) {
				return;
			}
			if (data.getClass().equals(value.getClass())) {
				if (data.equals(value)) {
					setAttribute("value", i + "");
					break;
				}
			} else if (value != null) {
				SimpleKeyValue kv = new SimpleKeyValue(value.toString(), value.toString());
				if (data.equals(kv)) {
					setAttribute("value", i + "");
					break;
				}
			}
		}
	}

}
