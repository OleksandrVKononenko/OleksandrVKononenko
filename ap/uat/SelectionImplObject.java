 
 
 
 
 
 
package ap.uat; 

import java.awt.Rectangle; 

import ap.global.gl; 
import ap.shape.Ru; 

public class SelectionImplObject { 

	 
	private Rectangle 		selector 	= Ru.get_init_rect(gl.E_EMPTY); 
	 
	public Rectangle get_selector() { 
		return selector; 
	} 

	public void set_selector(Rectangle selector) { 
		this.selector = selector; 
	} 

	public SelectionImplObject() { 
		 
	} 
	 
	public static SelectionImplObject get_instance() { 
		 
		return new SelectionImplObject(); 
		 
	} 

	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
