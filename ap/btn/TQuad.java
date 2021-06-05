 
package ap.btn; 


import java.awt.geom.Point2D; 
import java.awt.geom.QuadCurve2D; 

import ap.gen.IDGen; 

public class TQuad { 

	private int id; 
	 
	private Point2D start; 
	 
	private Point2D end; 
	 
	private Point2D ctrl; 
	 
	private QuadCurve2D.Double quad = new QuadCurve2D.Double(); 
	 
	 
	public QuadCurve2D.Double getQuad() { 
		return quad; 
	} 

	public void setQuad() { 
		this.quad.setCurve( 
				this.getStart(), 
				this.getCtrl(), 
				this.getEnd() 
				); 
		 
	} 

	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	 
	public Point2D getStart() { 
		return start; 
	} 

	public void setStart(Point2D start) { 
		this.start = start; 
	} 

	public Point2D getEnd() { 
		return end; 
	} 

	public void setEnd(Point2D end) { 
		this.end = end; 
	} 

	public Point2D getCtrl() { 
		return ctrl; 
	} 

	public void setCtrl(Point2D ctrl) { 
		this.ctrl = ctrl; 
	} 

	public TQuad(Point2D start,Point2D end,Point2D ctrl) { 
		 
		this(); 
		 
		this.setStart(start); 
		 
		this.setEnd(end); 
		 
		this.setCtrl(ctrl); 
	} 
	 
	public TQuad() { 
		this.setId(IDGen.nextId()); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("id=%d,start=%s,ctrl=%s,end=%s",this.getId(),this.getStart(),this.getCtrl(),this.getEnd()); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
