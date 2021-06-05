 
package ap.btn; 

import java.awt.Color; 

public class TLineStyle { 

	private TStroke stroke; 
	 
	private Color color; 
	 
	private float alpha; 
	 
	 
	 
	public TStroke getStroke() { 
		return stroke; 
	} 

	public void setStroke(TStroke stroke) { 
		this.stroke = stroke; 
	} 

	public Color getColor() { 
		return color; 
	} 

	public void setColor(Color color) { 
		this.color = color; 
	} 

	public float getAlpha() { 
		return alpha; 
	} 

	public void setAlpha(float alpha) { 
		this.alpha = alpha; 
	} 

	public TLineStyle() { 
		 
	} 
	 
	public TLineStyle(TStroke stroke,Color color,float alpha) 
	{ 
	 
			this.setStroke(stroke); 
			 
			this.setColor(color); 
			 
			this.setAlpha(alpha); 
	} 
	 
	public static TLineStyle getInstance(TStroke stroke,Color color,float alpha) 
	{ 
		return new TLineStyle(stroke,color,alpha); 
	} 
	 

	public static void main(String[] args) { 
		 

	} 

} 
