package org.castafiore.ui.list;

import org.castafiore.ui.Container;

public interface List<T> extends Container{
	
	public List<T> addItem(ListItem<T> item);
	
	public ListItem<T> getItem(int index);
	
	public int getSize();
	
	public List<ListItem<T>> getItems();

}
