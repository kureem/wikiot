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

package org.castafiore.ui;

import java.util.List;
import java.util.Set;

import org.castafiore.ui.js.JSMap;

/**
 * 
 * 
 * @author : Kureem Rossaye kureem@gmail.com Oct 22, 2008
 * @param <T>
 */

public interface Container extends DynamicHTMLTag {

	public void onReady(JQuery proxy);

	/**
	 * appends a styleclass to the container
	 * 
	 * @param styleClass
	 */
	public Container addClass(String styleClass);

	/**
	 * removes the specified class from the tag
	 * 
	 * @param sclass
	 */
	public Container removeClass(String sclass);

	/**
	 * returns the style class set on the component
	 * 
	 * @return
	 */
	public String getStyleClass();

	/**
	 * sets the style class on the component
	 */
	public Container setStyleClass(String styleClass);

	/**
	 * removes a child
	 * 
	 * @param id
	 */
	public Container removeChild(String id);

	/**
	 * verifies if this child is valid
	 * 
	 * @param child
	 * @return
	 */
	public boolean isValidChild(Container child);

	/**
	 * returns the children of the container
	 * 
	 * @return
	 */
	public List<Container> getChildren();

	/**
	 * returns a child by name
	 * 
	 * @param name
	 * @return
	 */
	public Container getChild(String name);

	/**
	 * adds a child
	 * 
	 * @param component
	 */
	public Container addChild(Container component);

	public Container addChildAt(Container component, int position);

	/**
	 * return readonly attributes
	 * 
	 * @return
	 */
	public String[][] getReadonlyAttributes();

	/**
	 * the unique id generated by container
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * returns the name of the component
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * sets the name of the component
	 * 
	 * @param name
	 */
	public Container setName(String name);

	/**
	 * remove the component upon removal of a component,
	 */
	public void remove();

	/**
	 * returns ancestor of type
	 * 
	 * @param classType
	 * @return
	 */

	public <T extends Container> T getAncestorOfType(Class<T> type);

	/**
	 * returns the anscestor with the specified id
	 * 
	 * @param id
	 * @return
	 */
	public Container getAncestorById(String id);

	/**
	 * returns the ancestor with the specified name
	 * 
	 * @param name
	 * @return
	 */
	public Container getAncestorByName(String name);

	/**
	 * returns the parent
	 * 
	 * @return
	 */
	public Container getParent();

	public Container setDraggable(boolean draggable);

	public Container setDraggable(boolean draggable, JSMap options);

	public Container setResizable(boolean res);

	public Container setResizable(boolean res, JSMap options);

	/**
	 * 
	 * @return returns the root of the application. The root is always an
	 *         instance of application
	 */
	public <T extends Application> T getRoot();

	/**
	 * 
	 * @return if the component is rendered or not
	 */
	public boolean rendered();

	/**
	 * reset the redered value
	 * 
	 * @param rendered
	 */
	public Container setRendered(boolean rendered);

	/**
	 * verifies of the container has children
	 * 
	 * @return
	 */
	public boolean hasChildren();

	/**
	 * returns a child in the specified index
	 * 
	 * @param index
	 * @return
	 */
	public Container getChildByIndex(int index);

	/**
	 * sets the parent of the container
	 * 
	 * @param container
	 */
	public void setParent(Container container);

	/**
	 * finds the first descendent with name
	 * 
	 * @param name
	 * @return
	 */
	public Container getDescendentByName(String name);

	/**
	 * finds the first descendent that matches the specified id
	 * 
	 * @param id
	 * @return
	 */
	public Container getDescendentById(String id);

	/**
	 * find the first descendent with the specified type
	 * 
	 * @param type
	 * @return
	 */

	public <T extends Container> T getDescendentOfType(Class<T> type);

	/**
	 * sets the component visible or invisible
	 * 
	 * @param display
	 */
	public Container setDisplay(boolean display);

	/**
	 * returns if the container is visible or not
	 * 
	 * @return
	 */
	public boolean isVisible();

	/**
	 * use this method to lazily load a script only when this component is
	 * available<br/>
	 * Note that you can add as many script in any component. Each script will
	 * be loaded only once
	 * 
	 * @param script
	 */
	public Container addScript(String scripturl);

	/**
	 * adds a stylesheet to the application.<br/>
	 * Note that you can add as many stylesheet as you wish. Each stylesheet
	 * will be loaded only once in the container
	 * 
	 * @param stylesheeturl
	 */
	public Container addStyleSheet(String stylesheeturl);

	public Set<String> getResources();

	public boolean hasEvent(Class<?> event, int type);

	public void removeEvent(Class<?> event, int type);

	public void refresh();

	public void setReadOnlyAttribute(String key, String value);

}
