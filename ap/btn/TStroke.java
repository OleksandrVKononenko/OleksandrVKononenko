 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Dimension; 
import java.awt.Stroke; 

import ap.global.gl; 


public class TStroke extends BasicStroke { 

	private Dimension dimension ; 
	 
	public Dimension getDimension() { 
		return dimension; 
	} 

	public void setDimension(Dimension dimension) { 
		this.dimension = dimension; 
	} 

	public TStroke() { 
		 
	} 

	public TStroke(float width) { 
		super(width); 
		 
	} 

	public TStroke(float width, int cap, int join) { 
		super(width, cap, join); 
		 
	} 

	public TStroke(float width, int cap, int join, float miterlimit) { 
		super(width, cap, join, miterlimit); 
		 
	} 

	public TStroke(float width, int cap, int join, float miterlimit, float[] dash, float dash_phase) { 
		super(width, cap, join, miterlimit, dash, dash_phase); 
		 
	} 
	 
	public TStroke(Dimension dimension) 
	{ 
		this.setDimension(dimension); 
	} 
	 
	public static TStroke getInstance(Dimension dimension) 
	{ 
		return new TStroke(dimension); 
	} 
	public static Stroke getStrokeInstance(Dimension dimension) 
	{ 
		if(dimension.width == gl.E_EMPTY) 
		return  new BasicStroke(dimension.height); 
		else 
		return  new BasicStroke(dimension.height, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,5.0f, new float[]{dimension.width},5.0f); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
