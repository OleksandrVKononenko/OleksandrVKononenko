 
/** 
* 
*/ 
package ap.shape; 

import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 

import ap.global.gl; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 17 ???. 2017 ?. 14:39:27 
* Project  name : Organizer 
* Package  name : ap.shape 
* File     name : TRectR.java 
* 
*/ 
public class TRect extends Rectangle { 

	/** 
	 * 
	 */ 
	 
	public static final int BODY = 3; 
	 
	public static final int SHA0 = 0; 
	 
	public static final int SHA1 = 1; 
	 
	public static final int SHA2 = 2; 
	 
	public static final int ARCW = 12; 
	 
	public static final int ARCH = 12; 
	 
	 
	 
	private static final long serialVersionUID = 1L; 

	private int arc_width; 
	 
	private int arc_height; 
	 
	 
	 
	 
	public int getArc_width() { 
		return arc_width; 
	} 

	public void setArc_width(int arc_width) { 
		this.arc_width = arc_width; 
	} 

	public int getArc_height() { 
		return arc_height; 
	} 

	public void setArc_height(int arc_height) { 
		this.arc_height = arc_height; 
	} 

	/** 
	 * 
	 */ 
	public TRect() { 
		 
	} 

	/** 
	 * @param r 
	 */ 
	public TRect(Rectangle r) { 
		super(r); 
		 
	} 

	/** 
	 * @param p 
	 */ 
	public TRect(Point p) { 
		super(p); 
		 
	} 

	/** 
	 * @param d 
	 */ 
	public TRect(Dimension d) { 
		super(d); 
		 
	} 

	/** 
	 * @param width 
	 * @param height 
	 */ 
	public TRect(int width, int height) { 
		super(width, height); 
		 
	} 

	/** 
	 * @param p 
	 * @param d 
	 */ 
	public TRect(Point p, Dimension d) { 
		super(p, d); 
		 
	} 

	/** 
	 * @param x 
	 * @param y 
	 * @param width 
	 * @param height 
	 */ 
	public TRect(int x, int y, int width, int height) { 
		super(x, y, width, height); 
		 
	} 
	 
	public TRect(int x, int y, int width, int height,int arcw,int arch) { 
		this(x, y, width, height); 
		 
		this.setArc_width(arcw); 
		 
		this.setArc_height(arch); 
		 
	} 
	 
	 
	public TRect(Rectangle r,int arcw,int arch) { 
		 
		this(r); 
		 
		this.setArc_width(arcw); 
		 
		this.setArc_height(arch); 
		 
	} 
	 
	@Override 
	public TRect getBounds() 
	{ 
		Rectangle rect = super.getBounds(); 
		 
		TRect result_rect = new TRect(rect,this.getArc_width(),this.getArc_height()); 
		 
		return result_rect; 
	} 

	public TRect getBounds4g() 
	{ 
		Rectangle rect = new Rectangle(0,0,getBounds().width,getBounds().height); 
				   	 
		TRect result_rect = new TRect(rect,this.getArc_width(),this.getArc_height()); 
		 
		return result_rect; 
	} 

	 
	public Rectangle getBoundsRect() 
	{ 
		return super.getBounds(); 
	} 
	public Rectangle getBoundsRect4g() 
	{ 
		return new Rectangle(0,0,this.getBounds().width,this.getBounds().height); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		if(this.getArc_width()== gl.E_EMPTY && this.getArc_height()==gl.E_EMPTY) 
		return String.format("%d,%d,%d,%d",this.x,this.y,this.width,this.height); 
		else 
		return String.format("%d,%d,%d,%d,%d,%d", 
				this.x, 
				this.y, 
				this.width, 
				this.height, 
				this.getArc_width(), 
				this.getArc_height() 
				); 
	} 

	public static void main(String[] args) { 
		 
		gl.smn("Test 1: " + new TRect(0,0,0,0,1,1).toString()); 
		 
		gl.smn("Test 2: " + new TRect(new Rectangle(0,0,0,0),2,2).toString()); 
	} 

} 
// Revision : 10.09.2018 12:49:16 
