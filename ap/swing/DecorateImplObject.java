 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.area.AreaManager; 
import ap.global.gl; 

public class DecorateImplObject implements IDecorate { 

	private String text; 
	 
	private Color color; 
	 
	private Color text_color; 
	 
	private Rectangle text_align; 
	 
	private AreaManager area_manager; 
	 
	private Dimension client_size; 
	 
	private Rectangle frame_bounds; 
	 
	private int align = gl.E_EMPTY; 
	 
	private boolean bl_accept_area; 
	 
	private boolean bl_accept_scale_area_initialization; 
	 
	private double zoom_at_width; 

	private double zoom_at_height; 

	private double zoom_at_x; 

	private double zoom_at_y; 
	 
	private double start_delta_x; 

	private double start_delta_y; 

	public DecorateImplObject() { 
		 

		 
	} 
	 
	@Override 
	public boolean isBl_accept_area() { 
		return bl_accept_area; 
	} 

	@Override 
	public void setBl_accept_area(boolean bl_accept_area) { 
		this.bl_accept_area = bl_accept_area; 
	} 

	@Override 
	public boolean isBl_accept_scale_area_initialization() { 
		return bl_accept_scale_area_initialization; 
	} 

	@Override 
	public void setBl_accept_scale_area_initialization( 
			boolean bl_accept_scale_area_initialization) { 
		this.bl_accept_scale_area_initialization = bl_accept_scale_area_initialization; 
	} 

	 
	@Override 
	public String getText() { 
		return text; 
	} 

	@Override 
	public void setText(String text) { 
		this.text = text; 
	} 
	 
	@Override 
	public Color getColor() { 
		 
		return this.color; 
	} 

	@Override 
	public void setColor(Color color) { 
		 
		this.color = color; 
		 
	} 

	@Override 
	public Rectangle getTextAlign() { 
		return text_align; 
	} 

	@Override 
	public void setTextAlign(Rectangle text_align) { 
		this.text_align = text_align; 
	} 
	 
	 
	 
	public static DecorateImplObject get_instance() 
	{ 
		return new DecorateImplObject(); 
	} 

	 
	@Override 
	public AreaManager getAreaManager() { 
		 
		return this.area_manager; 
	} 

	@Override 
	public void setAreaManager(AreaManager mng) { 
		 
		this.area_manager = mng; 
		 
	} 

	public Color getTextColor() { 
		return this.text_color; 
	} 

	public void setTextColor(Color text_color) { 
		this.text_color = text_color; 
	} 
	 
	@Override 
	public void setSize(Dimension size) 
	{ 
		this.client_size = size; 
	} 
	 
	@Override 
	public Dimension getSize() 
	{ 
		return this.client_size; 
	} 
	 
	@Override 
	public Rectangle getFrameBounds() { 

		return this.frame_bounds; 
	} 

	@Override 
	public void setFrameBounds(Rectangle bounds) { 
		 
		this.frame_bounds = bounds; 
		 
	} 

	 
	@Override 
	public void setAlign(int align) { 

		this.align = align; 
		 
	} 

	 
	@Override 
	public int getAlign() { 

		return this.align; 
	} 

	@Override 
	public double getZoom_at_width() { 
		return zoom_at_width; 
	} 

	@Override 
	public void setZoom_at_width(double zoom_at_width) { 
		this.zoom_at_width = zoom_at_width; 
	} 

	@Override 
	public double getZoom_at_height() { 
		return zoom_at_height; 
	} 

	@Override 
	public void setZoom_at_height(double zoom_at_height) { 
		this.zoom_at_height = zoom_at_height; 
	} 

	@Override 
	public double getZoom_at_x() { 
		return zoom_at_x; 
	} 

	@Override 
	public void setZoom_at_x(double zoom_at_x) { 
		this.zoom_at_x = zoom_at_x; 
	} 

	@Override 
	public double getZoom_at_y() { 
		return zoom_at_y; 
	} 
	 
	@Override 
	public void setZoom_at_y(double zoom_at_y) { 
		this.zoom_at_y = zoom_at_y; 
	} 

	@Override 
	public double getStart_delta_x() { 
		return start_delta_x; 
	} 

	@Override 
	public void setStart_delta_x(double start_delta_x) { 
		this.start_delta_x = start_delta_x; 
	} 

	@Override 
	public double getStart_delta_y() { 
		return start_delta_y; 
	} 

	@Override 
	public void setStart_delta_y(double start_delta_y) { 
		this.start_delta_y = start_delta_y; 
	} 
		 
	 

} 
