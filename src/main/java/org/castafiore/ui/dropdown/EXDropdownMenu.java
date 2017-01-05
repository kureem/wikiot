package org.castafiore.ui.dropdown;

import org.castafiore.ui.EXContainer;
import org.castafiore.ui.toolbar.ToolBarItem;

public class EXDropdownMenu extends EXContainer implements ToolBarItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EXDropdownMenu(String name) {
		super(name, "ul");
		addClass("dropdown-menu");
	}

	
	public EXDropdownMenu addItem(ToolBarItem item){
		addChild(item);
		return this;
	}
	
	public EXDropdownMenu addHeader(String title){
		addChild(new EXContainer("header-", "li").addClass("dropdown-header").setText(title));
		return this;
	}
	
	public EXDropdownMenu addDivider(){
		addChild(new EXContainer("div-", "li").addClass("divider"));
		return this;
	}
	
	public EXDropdownMenu setAlignment(Alignment align){
		for(Alignment al : Alignment.values()){
			removeClass(al.styleClass());
		}
		
		addClass(align.styleClass());
		return this;
	}
}
