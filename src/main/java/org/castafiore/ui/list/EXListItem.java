package org.castafiore.ui.list;



import org.castafiore.ui.EXContainer;
import org.castafiore.ui.button.Type;

@SuppressWarnings("serial")
public class EXListItem<T> extends EXContainer implements ListItem<T>{

	private T data = null;
	
	private boolean selected = false;
	
	
	public EXListItem(String name) {
		super(name, "li");
		
	}
	
	

	public EXListItem<T> setType(Type t){
		
		for(Type tt : Type.values()){
			removeClass(tt.styleClass().replace("btn-",  "list-group-item-"));
		}
		
		addClass(t.styleClass().replace("btn-",  "list-group-item-"));
		
		return this;
	}
	
	public void setBadge(String badge) {
		if(getChild("badge") == null){
			addChild(new EXContainer("badge", "span").addClass("badge").setText(badge));
		}else{
			getChild("badge").setText(badge);
		}
	}


	@Override
	public T getData() {
		
		return data;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setData(T data) throws UnSupportedTypeException {
		
		this.data = data;
	}

	@Override
	public void setSelected(boolean selected) {
		
		this.selected = selected;
		if(selected)
			addClass("active");
		else 
			removeClass("active");
	}

}
