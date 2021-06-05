 
 
package ap.swing; 

import java.awt.Cursor; 
import java.awt.Point; 
import java.util.List; 

import ap.shape.Ru; 

public interface IDragable { 
	 
		 
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

	public PanelXml getDropTarget(); 
	 
	public void setDropTarget(PanelXml drop_target); 
		 
	 
	public PanelXml getPrevDropTarget(); 
	 
	public void setPrevDropTarget(PanelXml prev_drop_target); 

} 
