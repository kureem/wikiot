/*
 * Copyright (C) 2007-2010 Castafiore
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
package org.castafiore.ui.button;

import org.castafiore.ui.EXContainer;
import org.castafiore.ui.toolbar.ToolBarItem;

public class EXButtonGroup extends EXContainer implements ToolBarItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXButtonGroup(String name) {
		super(name, "div");

		addClass("btn-group");
	}

	public EXButtonGroup addItem(ToolBarItem b) {
		addChild(b);
		return this;
	}
	
	public EXButtonGroup setSize(Size size) {
		for (Size s : Size.values()) {
			removeClass(s.styleClass().replace("btn-", "btn-group-"));
		}

		addClass(size.styleClass().replace("btn-", "btn-group-"));

		return this;
	}
}
