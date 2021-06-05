 
 
 
 
 
 
/** 
* 
*/ 
package ap.uat; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.area.AreaManager; 

public interface IDecorateA { 
	 
	public String get_text(); 
	 
	public void set_text(String text); 
	 
	 
	//public Color getColor(); 
	 
	//public void setColor(Color color); 
	 
	 
	public Color getTextColor(); 
	 
	public void setTextColor(Color color); 
	 
	 
	public Rectangle getTextAlign(); 
	 
	public void setTextAlign(Rectangle align); 
	 
	 
	public AreaManager getAreaManager(); 
	 
	public void setAreaManager(AreaManager mng); 
	 
	 
	public void setSize(Dimension size); 
	 
	public Dimension getSize(); 
	 
	 
	public Rectangle getFrameBounds(); 
	 
	public void setFrameBounds(Rectangle bounds); 
	 
	 
	public void set_align(int align); 
	 
	public int get_align(); 
	 
	 

		 
	 
		 
	 
} 
