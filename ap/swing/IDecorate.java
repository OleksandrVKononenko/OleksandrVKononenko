 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.area.AreaManager; 

public interface IDecorate { 
	 
	public String getText(); 
	 
	public void setText(String text); 
	 
	 
	public Color getColor(); 
	 
	public void setColor(Color color); 
	 
	 
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
	 
	 
	public void setAlign(int align); 
	 
	public int getAlign(); 
	 
	 
	public boolean isBl_accept_area(); 
	 
	public void setBl_accept_area(boolean bl_accept_area); 
	 

	public boolean isBl_accept_scale_area_initialization(); 

	public void setBl_accept_scale_area_initialization(boolean bl_accept_scale_area_initialization); 
	 
	 
	public double getZoom_at_width(); 
	 
	public void setZoom_at_width(double zoom_at_width); 

	 
	public double getZoom_at_height(); 

	public void setZoom_at_height(double zoom_at_height); 

	 
	public double getZoom_at_x(); 

	public void setZoom_at_x(double zoom_at_x); 

	 
	public double getZoom_at_y(); 
	 
	public void setZoom_at_y(double zoom_at_y); 
	 
	 
	public double getStart_delta_x(); 

	public void setStart_delta_x(double start_delta_x); 

	 
	public double getStart_delta_y(); 

	public void setStart_delta_y(double start_delta_y) ; 

	 
	 
	 
		 
	 
} 
