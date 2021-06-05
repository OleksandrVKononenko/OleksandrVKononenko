 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.awt.Point; 
import java.awt.geom.Point2D; 

public class PointUtil { 


	public static Point toPoint(Point2D p2d) 
	{ 
		return new Point((int)p2d.getX(),(int)p2d.getY()); 
	} 
	public PointUtil() { 
	} 

	public static void main(String[] args) { 
	} 

} 
