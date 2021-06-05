 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Cursor; 
import java.awt.Point; 
import java.util.List; 

import ap.shape.Ru; 

public class DragableImplObject implements IDragable { 

	private int 		dx; 
	 
	private int 		dy; 
	 
	private Point 		start_pos 			= null; 
	 
	private int 		drag_type 			= Cursor.DEFAULT_CURSOR; 
	 
	private int 		service_type 		= Cursor.DEFAULT_CURSOR; 
			 
	private List<Ru> service_rects 		= null; 
	 
	private PanelXml 	drop_target 		= null; 
	 
	private PanelXml 	prev_drop_target 	= null; 

	 
	public DragableImplObject() { 
		 
	} 

	@Override 
	public void setStartPos(Point point) { 
		 
		this.start_pos = point; 
	} 

	@Override 
	public Point getStartPos() { 
		 
		return start_pos; 
	} 

	@Override 
	public int getDx() { 
		 
		return dx; 
	} 

	@Override 
	public void setDx(int dx) { 
		 
		this.dx = dx; 
	} 

	@Override 
	public int getDy() { 
		 
		return dy; 
	} 

	@Override 
	public void setDy(int dy) { 
		 
		this.dy = dy; 
	} 

	@Override 
	public int getDragType() { 
		 
		return this.drag_type; 
	} 

	@Override 
	public void setDragType(int type) { 
		 
		this.drag_type = type; 
	} 

	@Override 
	public int getServiceType() { 
		 
		return this.service_type; 
	} 

	@Override 
	public void setServiceType(int type) { 
		 
		this.service_type = type; 
	} 

	@Override 
	public List<Ru> getServiceRects() { 
		 
		return this.service_rects; 
	} 

	@Override 
	public void setServiceRects(List<Ru> rects) { 
		 
			this.service_rects = rects; 
	} 

	@Override 
	public PanelXml getDropTarget() { 
		 
		return this.drop_target; 
	} 

	@Override 
	public void setDropTarget(PanelXml drop_target) { 
		 
		this.drop_target = drop_target; 
	} 

	 
	@Override 
	public PanelXml getPrevDropTarget() { 
		 
		return this.prev_drop_target; 
	} 

	 
	@Override 
	public void setPrevDropTarget(PanelXml prev_drop_target) { 
	 
		this.prev_drop_target = prev_drop_target; 
	} 
	 
	public static DragableImplObject get_instance() 
	{ 
		return new DragableImplObject(); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 



} 
