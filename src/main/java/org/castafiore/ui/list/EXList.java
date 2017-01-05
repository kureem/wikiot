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

package org.castafiore.ui.list;

import org.castafiore.ui.Container;
import org.castafiore.ui.EXContainer;

/**
 * 
 * 
 * @author Kureem Rossaye<br>
 *         kureem@gmail.com Oct 22, 2008
 */
public class EXList<T> extends EXContainer implements List<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXList(String name) {
		super(name, "div");
		addClass("list-group");
	}

	@SuppressWarnings("unchecked")
	public T getData() {
		for (Container i : getChildren()) {
			if (i.getDescendentOfType(EXListItem.class).isSelected()) {
				return (T) i.getDescendentOfType(EXListItem.class).getData();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public EXList<T> setSelected(EXListItem<T> item) {
		item.setSelected(true);
		for (Container i : getChildren()) {
			EXListItem ii = i.getDescendentOfType(EXListItem.class);
			if (ii.getId().equals(item.getId())) {

			} else {
				ii.setSelected(false);
			}
		}
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ListItem<T>> getItems() {
		java.util.List l = getChildren();

		return (List<ListItem<T>>) l;
	}

	@Override
	public List<T> addItem(ListItem<T> item) {
		if (item.isSelected()) {
			for (Container i : getChildren()) {
				i.getDescendentOfType(EXListItem.class).setSelected(false);
			}
		}
		addChild(item);
		return this;

	}

	@SuppressWarnings("unchecked")
	@Override
	public ListItem<T> getItem(int index) {
		return (ListItem<T>)getChildByIndex(index);
	}

	@Override
	public int getSize() {
		return getChildren().size();
	}

}
