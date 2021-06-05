 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Dimension; 

import ap.global.gl; 
import ap.prop.SDimension; 

public class TFrameBase { 


	private int high; 
	 
	private int low; 
	 
	private double x_zoom; 
	 
	private double y_zoom; 
	 
	public int getHigh() { 
		return high; 
	} 

	public void setHigh(int high) { 
		this.high = high; 
	} 

	public int getLow() { 
		return low; 
	} 

	public void setLow(int low) { 
		this.low = low; 
	} 
	 
		public double getX_zoom() { 
		return x_zoom; 
	} 

	public void setX_zoom(double x_zoom) { 
		this.x_zoom = x_zoom; 
	} 

	public double getY_zoom() { 
		return y_zoom; 
	} 

	public void setY_zoom(double y_zoom) { 
		this.y_zoom = y_zoom; 
	} 

	 
	public TFrameBase() { 
	} 
	 
	 
	public TFrameBase(int high,int low,double x_zoom,double y_zoom) { 
	 
		this.setHigh(high); 
		 
		this.setLow(low); 
	 
		this.setX_zoom(x_zoom); 
		 
		this.setY_zoom(y_zoom); 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		return  String.format("%d,%d x_zoom : %.4f y_zoom : %.4f ",this.getHigh(),this.getLow(),this.getX_zoom(),this.getY_zoom()); 
	} 
	 
	public static void main(String[] args) { 
	 
	} 

} 
