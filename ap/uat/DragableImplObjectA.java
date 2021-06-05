 
 
 
 
 
 
package ap.uat; 

import java.awt.Cursor; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.uat.scrollbar.ScrBarX; 
import ap.uat.scrollbar.ScrBarY; 

public class DragableImplObjectA implements IDragableA { 

	private int 		dx; 
	 
	private int 		dy; 
	 
	private Point 		start_pos 			= null; 
	 
	private int 		drag_type 			= Cursor.DEFAULT_CURSOR; 
	 
	private int 		service_type 		= Cursor.DEFAULT_CURSOR; 
			 
	private List<Ru> 	service_rects 		=  new ArrayList<Ru>(); 
	 
	private AtOm 		drop_target 		= null; 
	 
	private AtOm 		prev_drop_target 	= null; 
	 
	private int			x_delta				= gl.E_EMPTY; 
	 
	private int			y_delta				= gl.E_EMPTY; 
	 
	private Rectangle 	scroll_metric; 
	 
	/*
	private ScrBarX		x_scroll_bar		= null; 
	 
	private ScrBarY		y_scroll_bar		= null; 
		 
	 
	public ScrBarX getX_scroll_bar() { 
		return x_scroll_bar; 
	} 

	public void setX_scroll_bar(ScrBarX x_scroll_bar) { 
		this.x_scroll_bar = x_scroll_bar; 
	} 

	public ScrBarY getY_scroll_bar() { 
		return y_scroll_bar; 
	} 

	public void setY_scroll_bar(ScrBarY y_scroll_bar) { 
		this.y_scroll_bar = y_scroll_bar; 
	} 
	*/

	public Rectangle getScroll_metric() { 
		return scroll_metric; 
	} 

	public void setScroll_metric(Rectangle scroll_metric) { 
		this.scroll_metric = scroll_metric; 
	} 
	 
	 
	public int getX_delta() { 
		return x_delta; 
	} 

	public void setX_delta(int x_delta) { 
		this.x_delta = x_delta; 
	} 

	public int getY_delta() { 
		return y_delta; 
	} 

	public void setY_delta(int y_delta) { 
		this.y_delta = y_delta; 
	} 

	 

	public DragableImplObjectA() { 
		 
		this.setScroll_metric(Ru.get_init_rect(gl.E_EMPTY)); 
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
	public AtOm get_drop_target() { 
		 
		return this.drop_target; 
	} 

	@Override 
	public void set_drop_target(AtOm drop_target) { 
		 
		this.drop_target = drop_target; 
	} 

	 
	@Override 
	public AtOm getPrevDropTarget() { 
		 
		return this.prev_drop_target; 
	} 

	 
	@Override 
	public void setPrevDropTarget(AtOm prev_drop_target) { 
	 
		this.prev_drop_target = prev_drop_target; 
	} 
	 
	public static DragableImplObjectA get_instance() 
	{ 
		return new DragableImplObjectA(); 
	} 
	 
	 

	public static void main(String[] args) { 
		 

	} 



} 

