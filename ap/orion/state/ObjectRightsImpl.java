package ap.orion.state;


import ap.orion.ResizableBorder;
import ap.utils.Biu;

public class ObjectRightsImpl {

	private int rights = 0x0;
	
	public ObjectRightsImpl ()
	{
	
	}
	
	public int get_rights() {
		return rights;
	}



	public void set_rights(int state) {
		this.rights = state;
	}


	public boolean is_accessable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.ACCESSABLE); 
	} 
	 
	public void set_accessable(boolean accessed) { 

		if (accessed) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.ACCESSABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.ACCESSABLE)); 
		} 
	} 
	
	public boolean is_selectable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.SELECTABLE); 
	} 
	 
	public void set_selectable(boolean selected) { 

		if (selected) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.SELECTABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.SELECTABLE)); 
		} 
		
		
	} 
	

	
	public boolean is_focusable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.FOCUSABLE); 
	} 
	 
	public void set_focusable(boolean focused) { 

		if (focused) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.FOCUSABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.FOCUSABLE)); 
		} 
	} 
	
	public boolean is_dragable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.DRAGABLE); 
	} 
	 
	
	public void set_dragable(boolean selected) { 

		if (selected) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.DRAGABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.DRAGABLE)); 
		} 
	} 
	
	public boolean is_selectorable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.SELECTORABLE); 
	} 
	 
	
	public void set_selectorable(boolean selected) { 

		if (selected) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.SELECTORABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.SELECTORABLE)); 
		} 
	} 
	
	
	
	public boolean is_sizeable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.RESIZEABLE); 
	} 
	 
	public void set_sizeable(boolean selected) { 

		if (selected) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.RESIZEABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.RESIZEABLE)); 
		} 
	} 
	
	
	public boolean is_visible() { 

		return Biu.ISA(this.get_rights(),ObjectRights.VISIBLE); 
	} 
	 
	public void set_visible(boolean selected) { 

		if (selected) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.VISIBLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.VISIBLE)); 
		} 
	} 
	
	
	
	public boolean is_dropable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.DROPABLE); 
	} 
	 
	public void set_dropable(boolean dropable) { 

		if (dropable) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.DROPABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.DROPABLE)); 
		} 
	} 
	
	
	
	
	public static ObjectRightsImpl get_instance() 
	{ 
		return new ObjectRightsImpl(); 
	} 

	public boolean is_deletable() { 

		return Biu.ISA(this.get_rights(),ObjectRights.DELETABLE); 
	} 
	 
	public void set_deletable(boolean deletable) { 

		if (deletable) { 
			this.set_rights(Biu.ON(this.get_rights(), ObjectRights.DELETABLE)); 
		} else { 
			this.set_rights(Biu.OFF(this.get_rights(),ObjectRights.DELETABLE)); 
		} 
		
		
	} 
	
	
	public static void main(String[] args) {
		

	}

}
