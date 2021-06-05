 
 
 
 
package ap.prop; 

import java.awt.Point; 

import ap.global.gl; 

public class SPoint { 

	public SPoint() { 
		 
	} 

	public static String toString(Point point) 
	{ 
		return gl.sf("%3d,%3d",point.x,point.y); 
	} 
	 
	public static void main(String[] args) { 
		 
	} 

} 
