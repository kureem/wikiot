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
 package org.castafiore.ui.form;

import java.util.List;
import java.util.Map;

import org.castafiore.ui.Container;
import org.castafiore.ui.FormInput;
import org.castafiore.ui.button.Button;

public interface Form extends Container{
	
	@SuppressWarnings("rawtypes")
	public Map<String, FormInput> getFieldsMap();
	
	@SuppressWarnings("rawtypes")
	public FormInput getField(String name);
	
	public Form addField(String label, @SuppressWarnings("rawtypes") FormInput input);
	
	public Form addButton(Button button);
	
	@SuppressWarnings("rawtypes")
	public List<FormInput> getFields();
	
	
	@SuppressWarnings("rawtypes")
	public void setLabelFor(String label, FormInput c);
	
	

}
