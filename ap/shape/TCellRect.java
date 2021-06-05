 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.shape; 

import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 

import ap.global.gl; 

@SuppressWarnings("serial") 
public class TCellRect extends Rectangle { 

	public static int CLEAR  = gl.E_EMPTY; 
	 
	public static int MOVEAT = gl.E_OK; 
	 
	public static int SELECTED = MOVEAT*2; 
		 
	private int index = gl.E_ERROR; 
	 
	private TCellRect rect_text; 
	 
	private int state = CLEAR; 
	 
	 
	 
	public int getState() { 
		return state; 
	} 

	public void setState(int state) { 
		this.state = state; 
	} 

	public TCellRect getRect_text() { 
		return rect_text; 
	} 

	public void setRect_text(TCellRect rect_text) { 
		this.rect_text = rect_text; 
	} 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

	public TCellRect() { 
		 
	} 

	public void clear_state() 
	{ 
		if(this.getState() != TCellRect.SELECTED) 
			this.setState(TCellRect.CLEAR); 
		 
	} 
	 
	public TCellRect(Rectangle r) { 
		super(r); 
		 
	} 
	 
	public TCellRect(int index,Rectangle r) { 
		 
		this(r); 
		 
		this.setIndex(index); 
				 
	} 
	 
	 
	public TCellRect(Point p) { 
		super(p); 
		 
	} 

	public TCellRect(Dimension d) { 
		super(d); 
		 
	} 

	public TCellRect(int width, int height) { 
		super(width, height); 
		 
	} 

	public TCellRect(Point p, Dimension d) { 
		super(p, d); 
		 
	} 

	public TCellRect(int x, int y, int width, int height) { 
		super(x, y, width, height); 
		 
	} 

	 
	public static TCellRect getInstance(int index,Rectangle rect) 
	{ 
		return new TCellRect(index,rect); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
