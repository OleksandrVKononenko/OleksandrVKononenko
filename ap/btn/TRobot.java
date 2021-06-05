 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.AWTException; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Robot; 
import java.awt.event.InputEvent; 

import ap.global.gl; 
import ap.prop.SBounds; 


public class TRobot { 

	private Point point; 
	 
	 
	public Point getPoint() { 
		return point; 
	} 

	public void setPoint(Point point) { 
		this.point = point; 
	} 

	public TRobot() { 
		 
	} 
	 
	public TRobot(Point point) { 
		 
		this.setPoint(point); 
	} 

	public boolean mouseDrag() 
	{ 
		 
		 
		Robot r = null; 
		 
		try { 
			 
			 r = new Robot(); 
			 
			 r.mouseMove(this.getPoint().x,this.getPoint().y); 
			 
			 r.mousePress(InputEvent.BUTTON1_MASK); 
			 
			 r.mouseMove(-this.getPoint().x,-this.getPoint().y); 
			 
			 r.mousePress(InputEvent.BUTTON1_MASK); 
			 		 
			 gl.tracex( 
						new Object(){}, 
						String.format("Robot action...%s",gl.S_OK)); 
								 
			 
			 
		} catch (AWTException e) { 
			 
			return false; 
		} 
		 
			return true; 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
