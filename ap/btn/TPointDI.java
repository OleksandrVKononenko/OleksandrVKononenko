 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Point; 

import ap.global.gl; 

public class TPointDI { 

	 
	private double data; 
	 
	private Point point; 
	 
	 
	public double getData() { 
		return data; 
	} 

	public void setData(double data) { 
		this.data = data; 
	} 

	public Point getPoint() { 
		return point; 
	} 

	public void setPoint(Point point) { 
		this.point = point; 
	} 

	public TPointDI() { 
		 
	} 
	 
	public TPointDI(double data) { 
		 
		this.setData(data); 
	} 
	 
	public TPointDI(double data,Point point) { 
		 
		this(data); 
		 
		this.setPoint(point); 
	} 
	 
	 
	public static TPointDI getInstance(double data) 
	{ 
		return new TPointDI(data); 
	} 
	 

	public static TPointDI getInstance(double data,Point point) 
	{ 
		return new TPointDI(data,point); 
	} 
	 
	 
	public String toString() 
	{ 
		return String.format("%.4f,%3d,%3d",this.getData(),this.getPoint().x,this.getPoint().y); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
