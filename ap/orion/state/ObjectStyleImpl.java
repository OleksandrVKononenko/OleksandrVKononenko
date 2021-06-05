package ap.orion.state;

import ap.utils.Biu;

public class ObjectStyleImpl {

	private int style = 0x0;

	
	public boolean is_squared() { 

		return Biu.ISA(this.get_style(),ObjectStyle.SQUARED); 
	} 
	 
	public void set_squared(boolean created) { 
		
		if (created) { 
			this.set_style(Biu.ON(this.get_style(),ObjectStyle.SQUARED)); 
		} else { 
			this.set_style(Biu.OFF(this.get_style(),ObjectStyle.SQUARED)); 
		} 
		
	}

	public boolean is_rounded() { 

		return Biu.ISA(this.get_style(),ObjectStyle.ROUNDED); 
	} 
	 
	public void set_rounded(boolean created) { 
		
		if (created) { 
			this.set_style(Biu.ON(this.get_style(),ObjectStyle.ROUNDED)); 
		} else { 
			this.set_style(Biu.OFF(this.get_style(),ObjectStyle.ROUNDED)); 
		} 
		
	}
	
	
	public int get_style() {
		return style;
	}

	public void set_style(int style) {
		this.style = style;
	}

	public static ObjectStyleImpl get_instance() 
	{ 
		return new ObjectStyleImpl(); 
	} 
	
	public ObjectStyleImpl() {
		
	}

	public static void main(String[] args) {
		

	}

}
