 
package ap.uat; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.area.AreaManager; 
import ap.global.gl; 
import ap.shape.Ru; 
import ap.swing.IAlign; 

public class DecorateImplObjectA implements IDecorateA { 

	private String 		text; 

	private Color 		text_color; 
	 
	private Rectangle 	text_align; 
	 
	private AreaManager area_manager; 
	 
	private Dimension 	size; 
	 
	private Dimension 	wishes_size; 
		 
	private Rectangle 	parents_bounds; 
		 
	private Rectangle 	bounds; 
	 
	private Rectangle 	origin_bounds; 
	
	private int 		align = IAlign.indexOf("NONE"); 

	private double		zoom_width; 
	 
	private double		zoom_height; 
	
	private Dimension	grid;
	
	
	public Dimension getGrid() {
		return grid;
	}

	public void setGrid(Dimension grid) {
		this.grid = grid;
	}

	public DecorateImplObjectA() 
	{ 
		 
	} 
		 
	@Override 
	public String get_text() { 
		return text; 
	} 

	@Override 
	public void set_text(String text) { 
		this.text = text; 
	} 
	 
	 
	@Override 
	public Rectangle getTextAlign() { 
		return text_align; 
	} 

	@Override 
	public void setTextAlign(Rectangle text_align) { 
		this.text_align = text_align; 
	} 
	 
	 
	 
	public static DecorateImplObjectA get_instance() 
	{ 
		return new DecorateImplObjectA(); 
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
		this.size = size; 
	} 
	 
	@Override 
	public Dimension getSize() 
	{ 
		return this.size; 
	} 
	 
	@Override 
	public Rectangle getFrameBounds() { 

		return this.parents_bounds; 
	} 

	@Override 
	public void setFrameBounds(Rectangle bounds) { 
		 
		this.parents_bounds = bounds; 
		 
	} 

	 
	@Override 
	public void set_align(int align) { 

		this.align = align; 
		 
	} 

	 
	@Override 
	public int get_align() { 

		return this.align; 
	} 
	 
	public Dimension getWishes_size() { 
		return wishes_size; 
	} 

	public void setWishes_size(Dimension wishes_size) { 
		this.wishes_size = wishes_size; 
	} 

	 
	public Rectangle getParents_bounds() { 
		return parents_bounds; 
	} 

	public void setParents_bounds(Rectangle parents_bounds) { 
		this.parents_bounds = parents_bounds; 
	} 

	public Rectangle getBounds() { 
		return bounds; 
	} 

	public void setBounds(Rectangle bounds) { 
		this.bounds = bounds; 
	} 

	public Rectangle getOrigin_bounds() { 
		return origin_bounds; 
	} 

	public void setOrigin_bounds(Rectangle origin_bounds) { 
		this.origin_bounds = origin_bounds; 
	} 

	 
	public double getZoom_width() { 
		return zoom_width; 
	} 

	public void setZoom_width(double zoom_width) { 
		this.zoom_width = zoom_width; 
	} 

	public double getZoom_height() { 
		return zoom_height; 
	} 

	public void setZoom_height(double zoom_height) { 
		this.zoom_height = zoom_height; 
	} 
	 
	 

} 

