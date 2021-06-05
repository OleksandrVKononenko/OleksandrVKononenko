 
 
 
package ap.uat; 

import ap.global.gl; 
import ap.state.Fl; 
import ap.utils.Biu; 

public class StateImplObject { 

	private int state = gl.E_EMPTY; 
	 
	 
	public int get_state() { 
		return state; 
	} 

	public void set_state(int state) { 
		this.state = state; 
	}
	
	

	public StateImplObject() { 
		 
		this.set_deny_drag(true); 
		 
	}
	
	public boolean is_deny_inside_selection() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_INSIDE_SELECTION); 
	} 
	 
	public void set_deny_inside_selection(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_INSIDE_SELECTION)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_INSIDE_SELECTION)); 
		} 
	} 
	
	
	 
	public boolean is_deny_drag() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_DRAG); 
	} 
	 
	public void set_deny_drag(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_DRAG)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_DRAG)); 
		} 
	} 
	 
	
	public boolean is_deny_select() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_SELECT); 
	} 
	 
	public void set_deny_select(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_SELECT)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_DENY_SELECT)); 
		} 
	} 
	
	
	 
	public boolean is_skip_moved() { 

		return Biu.ISA(this.get_state(), Fl.VK_SKIP_MOVED); 
	} 
	 
	public void set_skip_moved(boolean skip_moved) { 

		if (skip_moved) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_SKIP_MOVED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_SKIP_MOVED)); 
		} 

	} 
	 
	 
	public boolean is_debug() { 

		return Biu.ISA(this.get_state(), Fl.VK_DEBUG); 
	} 
	 
	public void set_debug(boolean debug) { 

		if (debug) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DEBUG)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DEBUG)); 
		} 

	} 
	 
	public boolean is_grid() { 

		return Biu.ISA(this.get_state(), Fl.VK_GRID); 
	} 
	 
	public void set_grid(boolean grid) { 

		if (grid) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_GRID)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_GRID)); 
		} 

	} 
	
	
	public boolean is_skip_resizing() { 

		return Biu.ISA(this.get_state(), Fl.VK_SKIP_RESIZING); 
	} 
	 
	public void set_skip_resizing(boolean skip_resizing) { 

		if (skip_resizing) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_SKIP_RESIZING)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_SKIP_RESIZING)); 
		} 

	} 
	 
	 
	 
	public boolean is_selected() { 

		return Biu.ISA(this.get_state(), Fl.VK_SELECTED); 
	} 
	 
	public void set_selected(boolean selected) { 

		if (selected) { 
			this.set_state(Biu.ON(this.get_state(),  Fl.VK_SELECTED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),  Fl.VK_SELECTED)); 
		} 

	} 
	 
	 
	 
	public boolean is_move_inside() { 

		return Biu.ISA(this.get_state(), Fl.VK_MOVE_INSIDE); 
	} 
	 
	public void set_move_inside(boolean move_inside) { 

		if (move_inside) { 
			this.set_state(Biu.ON(this.get_state(),Fl.VK_MOVE_INSIDE)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_MOVE_INSIDE)); 
		} 

	} 
	 
	public boolean is_move_bottom_right() { 

		return Biu.ISA(this.get_state(), Fl.VK_MOVE_BOTTOM_RIGHT); 
	} 
	 
	public void set_move_bottom_right(boolean bottom_right) { 

		if (bottom_right) { 
			this.set_state(Biu.ON(this.get_state(),Fl.VK_MOVE_BOTTOM_RIGHT)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_MOVE_BOTTOM_RIGHT)); 
		} 

	} 
	 
	public boolean is_move_top_left() { 

		return Biu.ISA(this.get_state(), Fl.VK_MOVE_TOP_LEFT); 
	} 
	 
	public void set_move_top_left(boolean top_left) { 

		if (top_left) { 
			this.set_state(Biu.ON(this.get_state(),Fl.VK_MOVE_TOP_LEFT)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_MOVE_TOP_LEFT)); 
		} 

	} 
	 
	 
	public boolean is_draw_selector() { 

		return Biu.ISA(this.get_state(), Fl.VK_DRAW_SELECTOR); 
		 
	} 

	public void set_draw_selector(boolean draw) { 

		if (draw) { 
			this.set_state(Biu.ON(this.get_state(),Fl.VK_DRAW_SELECTOR)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_DRAW_SELECTOR)); 
		} 

	} 
	 
	public boolean is_delete() { 

		return Biu.ISA(this.get_state(), Fl.VK_DELETE); 
		 
	} 

	public void set_delete(boolean delete) { 

		if (delete) { 
			this.set_state(Biu.ON(this.get_state(),Fl.VK_DELETE)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_DELETE)); 
		} 

	} 
	 
	public boolean is_visible() { 

		return Biu.ISA(this.get_state(), Fl.VK_VISIBLE); 
		 
	} 

	public void set_visible(boolean visible) { 

		if (visible) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_VISIBLE)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_VISIBLE)); 
		} 

	} 
	 
	 
	public boolean is_drag() { 

		return Biu.ISA(this.get_state(), Fl.VK_DRAGGED); 
		 
	} 

	public void set_drag(boolean dragged) { 

		if (dragged) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DRAGGED)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_DRAGGED)); 
		} 
	} 
	 
	public boolean is_freeze() { 

		return Biu.ISA(this.get_state(), Fl.VK_FREEZE); 
		 
	} 

	public void set_freeze(boolean freeze) { 

		if (freeze) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_FREEZE)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_FREEZE)); 
		} 

	} 
	 
	 
	public boolean is_resized() { 

		return Biu.ISA(this.get_state(), Fl.VK_RESIZING); 
		 
	} 

	public void set_resized(boolean resizing) { 

		if (resizing) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_RESIZING)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(),Fl.VK_RESIZING)); 
		} 

	} 
	 
	 
	public boolean is_skip_text() { 

		return Biu.ISA(this.get_state(), Fl.VK_SKIP_TEXT); 
		 
	} 

	public void set_skip_text(boolean skip_text) { 

		if (skip_text) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_SKIP_TEXT)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_SKIP_TEXT)); 
		} 

	} 
	 
	public boolean is_deny_x() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_X); 
		 
	} 

	public void set_deny_x(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_X)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_X)); 
		} 
	} 
	 
	public boolean is_deny_width() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_WIDTH); 
		 
	} 

	public void set_deny_width(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_WIDTH)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_WIDTH)); 
		} 
	} 
	 
	public boolean is_deny_height() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_HEIGHT); 
		 
	} 

	public void set_deny_height(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_HEIGHT)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_HEIGHT)); 
		} 
	} 
		 
	 
	 
	public boolean is_deny_y() { 

		return Biu.ISA(this.get_state(), Fl.VK_DENY_Y); 
		 
	} 

	 
	public void set_deny_y(boolean deny) { 

		if (deny) { 
			this.set_state(Biu.ON(this.get_state(), Fl.VK_DENY_Y)); 
		} else { 
			this.set_state(Biu.OFF(this.get_state(), Fl.VK_DENY_Y)); 
		} 

	} 



	public static StateImplObject get_instance() 
	{ 
		return new StateImplObject(); 
	} 
	 
} 
