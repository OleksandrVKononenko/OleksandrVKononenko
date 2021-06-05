 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
package ap.ray; 

import java.awt.Rectangle; 



public class ARect extends Rectangle { 

	private static final long serialVersionUID = 1L; 

	public ARect() { 

	} 

	public void clear() { 
		this.x = this.y = this.width = this.height = 0; 
		} 
	 
	public static boolean isEmpty(Rectangle rect) { 
		 
		 
		if(rect.x == 0 && rect.y == 0 && rect.width == 0 && rect.height == 0) 
			return true; 
		 
		return false; 
	 
	} 
	 
	 
	 
	public static boolean isNeedNormalize(Rectangle rect) { 
		 
		 
		if(rect.width < gl.E_EMPTY) 
			return true; 
		 
		return false; 

	} 
	 
	public static Rectangle Normalize(Rectangle rect) { 
		 
		return  new Rectangle(rect.x - (rect.width*gl.E_ERROR),rect.y,rect.width*gl.E_ERROR,rect.height); 

	} 
	 
	 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
