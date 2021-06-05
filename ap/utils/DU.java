 
package ap.utils; 

import java.awt.Dimension; 

import ap.global.gl; 
import ap.prop.SDimension; 

public class DU { 

	 
	public static int to_int(String value) 
	{ 
		int  d_int = gl.E_ERROR; 
		 
		try 
		{ 
		 
			d_int = Integer.parseInt(value); 
	 
			return d_int; 
			 
		} 
		catch(NumberFormatException e) 
		{ 

			return d_int; 
		} 
		 
	} 
	 
	public static float to_float(String value) 
	{ 
		float  f = gl.E_ERROR; 
		 
		try 
		{ 
		 
			f = Float.parseFloat(value); 
	 
			return f; 
			 
		} 
		catch(NumberFormatException e) 
		{ 

			return f; 
		} 
		 
	} 
	
	public static void main(String[] args) { 
	 

	} 

	 
	 
} 
