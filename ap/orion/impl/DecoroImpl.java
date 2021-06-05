package ap.orion.impl;

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.util.ArrayList;
import java.util.List;

import ap.area.AreaManager;
import ap.global.gl;
import ap.mercury.components.Gimension;
import ap.orion.Zoom;
import ap.orion.intf.IAlign;
import ap.orion.intf.IDecoro;
import ap.shape.Ru;
import ap.utils.TextureUtil;


public class DecoroImpl implements IDecoro { 

	
	private String 		text; 

	private Color 		text_color; 
	 
	private Rectangle 	text_align; 
	 
	private AreaManager area_manager; 
	 
	private Dimension 	size; 

	private Rectangle 	parents_bounds; 
		 
	private Rectangle 	bounds; 
	 
	private Rectangle 	origin_bounds; 
	
	private int 		align; 
	
	private Rectangle 	selector;
	
	private Zoom 		zoom;
	
	private Gimension   distribution;
	
	private Point 		startPos = null;
    
    private Point 		endPos   = null;
     
    private Color		tmp_color;
    
    private String 		grids;
    
    

	public String getGrids() {
		return grids;
	}

	public void setGrids(String grids) {
		this.grids = grids;
	}

	public Point getStartPos() {
		return startPos;
	}

	public void setStartPos(Point startPos) {
		this.startPos = startPos;
	}

	public Point getEndPos() {
		return endPos;
	}

	public void setEndPos(Point endPos) {
		this.endPos = endPos;
	}

	public Color getTmp_color() {
		return tmp_color;
	}

	public void setTmp_color(Color tmp_color) {
		this.tmp_color = tmp_color;
	}



	public Gimension getDistribution() {
		return distribution;
	}

	public void setDistribution(Gimension distribution) {
		this.distribution = distribution;
	}

	public Zoom getZoom() {
		return zoom;
	}

	public void setZoom(Zoom zoom) {
		this.zoom = zoom;
	}

	
	public DecoroImpl() 
	{ 
		 this.set_selector(Ru.get_init_rect(gl.E_EMPTY));
		 
		 // Выравнивание текста по умолчанию,- центр. 
		 
		 this.setTextAlign(new Rectangle(1,1,1,1));
		 
		 // Компоновка по умолчанию.
		 
		 this.set_align(IAlign.indexOf("none"));
		 
		 this.setAreaManager(new AreaManager());
		 
	
		 
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
	 
	 
	 
	public static DecoroImpl get_instance() 
	{ 
		return new DecoroImpl(); 
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


	@Override
	public Rectangle get_selector() {
		
		return this.selector;
	}

	@Override
	public void set_selector(Rectangle selector) {
		
		this.selector = selector;
	}

	
	 
	 

} 

