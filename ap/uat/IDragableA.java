 
 
 
 
 
 
package ap.uat; 

import java.awt.Point; 
import java.util.List; 

import javax.swing.JScrollPane; 

import ap.shape.Ru; 
import ap.swing.PanelXml; 

public interface IDragableA { 
	 
	 
	public void  setStartPos(Point point); 
	 
	public Point getStartPos(); 
	 
	 
	public int getDx(); 
	 
	public void setDx(int dx); 
	 
	 
	public int getDy(); 
	 
	public void setDy(int dy); 
	 
	 
	public int getDragType(); 
	 
	public void setDragType(int type); 
	 
	 
	public int getServiceType(); 
	 
	public void setServiceType(int type); 
	 
	 
	public List<Ru> getServiceRects(); 
		 
	public void setServiceRects(List<Ru> rects); 

	 
	public AtOm get_drop_target(); 
	 
	public void set_drop_target(AtOm drop_target); 
		 
	 
	public AtOm getPrevDropTarget(); 
	 
	public void setPrevDropTarget(AtOm prev_drop_target); 
		 

} 
