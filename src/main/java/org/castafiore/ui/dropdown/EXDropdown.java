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

import org.castafiore.ui.button.EXButton;
import org.castafiore.ui.button.EXButtonGroup;
import org.castafiore.ui.button.EXIconButton;
import org.castafiore.ui.button.Size;
import org.castafiore.ui.button.Type;
import org.castafiore.ui.toolbar.ToolBarItem;

/**
 * 
 * 
 * @author Kureem Rossaye<br>
 *         kureem@gmail.com Oct 22, 2008
 */
public class EXDropdown extends EXButtonGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 829549132119614823L;

	private EXButton button = new EXButton("btn", "Button");

	private EXIconButton iconButton = new EXIconButton("iconButton", "caret");

	private EXDropdownMenu menu;

	public EXDropdown(String name, String label) {
		super(name);
		iconButton.setAttribute("data-toggle", "dropdown").setAttribute("aria-haspopup", "true").setAttribute("aria-expanded", "false");
		button.setText(label);
		addChild(button);

		addChild(iconButton.addClass("dropdown-toggle"));
		menu = new EXDropdownMenu("menu");
		addChild(menu);

	}

	public EXButton getButton() {
		return button;
	}

	public EXIconButton getIconButton() {
		return iconButton;
	}

	public EXDropdown addItem(ToolBarItem item) {
		menu.addItem(item);
		return this;
	}

	public EXDropdownMenu getMenu() {
		return menu;
	}

	public EXDropdown setType(Type type) {
		iconButton.setType(type);
		button.setType(type);
		return this;
	}

	public EXDropdown setLabel(String label) {
		button.setText(label);
		return this;
	}

	public EXDropdown setSize(Size size) {

		button.setSize(size);
		iconButton.setSize(size);

		return this;
	}

}
