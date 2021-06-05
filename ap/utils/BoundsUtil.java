 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import ap.global.gl; 

public class BoundsUtil { 

	public static int  in(int low,int high,int value) 
	{ 
		if(value >= low && value  <= high) 
			return gl.E_OK; 
		else if(value < low) 
			return gl.E_ERROR; 
		else 
			return gl.E_EMPTY; 
		 
					 
	} 
	 
	public BoundsUtil() { 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
