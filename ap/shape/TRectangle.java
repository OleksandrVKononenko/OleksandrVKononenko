 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.shape; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 

@SuppressWarnings("serial") 
public class TRectangle extends Rectangle { 

	 
	private boolean raised; 
	 
	private Color background; 
	 
	private String text; 
	 
	 
	 
	 
	public String getText() { 
		return text; 
	} 

	public void setText(String text) { 
		this.text = text; 
	} 

	public boolean isRaised() { 
		return raised; 
	} 

	public void setRaised(boolean raised) { 
		this.raised = raised; 
	} 

	public Color getBackground() { 
		return background; 
	} 

	public void setBackground(Color background) { 
		this.background = background; 
	} 

	public TRectangle() { 
		 
	} 

	public TRectangle(Rectangle r) { 
		super(r); 
		 
	} 
	 
	public TRectangle(Rectangle r,Color color) { 
		 
		this(r); 
		 
		this.setBackground(color); 
	} 
	 
	public TRectangle(Rectangle r,Color color,String text) { 
		 
		this(r,color); 
		 
		this.setText(text); 
	} 
	 
	public TRectangle(Rectangle r,Color color,boolean raised) { 
		 
		this(r,color); 
		 
		this.setRaised(raised); 
		 
	} 


	public TRectangle(Point p) { 
		super(p); 
		 
	} 

	public TRectangle(Dimension d) { 
		super(d); 
		 
	} 

	public TRectangle(int width, int height) { 
		super(width, height); 
		 
	} 

	public TRectangle(Point p, Dimension d) { 
		super(p, d); 
		 
	} 

	public TRectangle(int x, int y, int width, int height) { 
		super(x, y, width, height); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
