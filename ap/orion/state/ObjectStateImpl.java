package ap.orion.state;



import ap.utils.Biu;

public class ObjectStateImpl {

	private int state = 0x0;
	
	public ObjectStateImpl ()
	{
	
	}
	
	public int get_state() {
		return state;
	}



	public void set_state(int value) {
		this.state = value;
	}


	
	public boolean is_created() { 

		return Biu.ISA(this.get_state(),ObjectState.CREATED); 
	} 
	 
	public void set_created(boolean created) { 

		if (created) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.CREATED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.CREATED)); 
		} 
		
		
	} 
	
	public boolean is_selected() { 

		return Biu.ISA(this.get_state(),ObjectState.SELECTED); 
	} 
	 
	public void set_selected(boolean selected) { 

		if (selected) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.SELECTED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.SELECTED)); 
		} 
		
	} 
	
	public boolean is_focused() { 

		return Biu.ISA(this.get_state(),ObjectState.FOCUSED); 
	} 
	 
	public void set_focused(boolean focused) { 

		if (focused) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.FOCUSED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.FOCUSED)); 
		} 
		
	} 

	public boolean is_dragged() { 

		return Biu.ISA(this.get_state(),ObjectState.DRAGGED); 
	} 
	public void set_dragged(boolean dragged) { 
	 

		if (dragged) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.DRAGGED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.DRAGGED)); 
		} 
		
	} 



	public boolean is_resized() { 

		return Biu.ISA(this.get_state(),ObjectState.RESIZED); 
	} 
	 
	public void set_resized(boolean sized) { 

		if (sized) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.RESIZED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.RESIZED)); 
		} 
	} 

	public boolean is_deleted() { 

		return Biu.ISA(this.get_state(),ObjectState.DELETED); 
	} 
	 
	public void set_deleted(boolean deleted) { 

		if (deleted) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.DELETED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.DELETED)); 
		} 
	} 

	
	public boolean is_dropped() { 

		return Biu.ISA(this.get_state(),ObjectState.DROPPED); 
	} 
	 
	public void set_dropped(boolean dropped) { 

		if (dropped) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.DROPPED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.DROPPED)); 
		} 
	} 
	
	public boolean is_disabled() { 

		return Biu.ISA(this.get_state(),ObjectState.DISABLED); 
	} 
	 
	public void set_disabled(boolean disabled) { 

		if (disabled) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.DISABLED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.DISABLED)); 
		} 
	} 
		
	public boolean is_visibled() { 

		return Biu.ISA(this.get_state(),ObjectState.VISIBLED); 
	} 
	 
	public void set_visibled(boolean visibled) { 

		if (visibled) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.VISIBLED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.VISIBLED)); 
		} 
	} 

	public boolean is_skip_component_resized() { 

		return Biu.ISA(this.get_state(),ObjectState.SKIP_COMPONENT_RESIZED); 
	} 
	 
	public void set_skip_component_resized(boolean skip) { 

		if (skip) { 
			this.set_state(Biu.ON(this.get_state(),ObjectState.SKIP_COMPONENT_RESIZED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),ObjectState.SKIP_COMPONENT_RESIZED)); 
		} 
	} 
	
	
	
	public static ObjectStateImpl get_instance() 
	{ 
		return new ObjectStateImpl(); 
	} 

	
	public static void main(String[] args) {
		

	}

}
