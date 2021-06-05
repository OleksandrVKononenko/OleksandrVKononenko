 
 
package ap.swing; 


import java.awt.Color; 
import java.awt.Component; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Comparator; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Map.Entry; 
import java.util.stream.Collectors; 

import javax.swing.JPanel; 
import javax.swing.SwingUtilities; 

import ap.action.Cmd; 
import ap.action.DeleteAction; 
import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.prop.SDimension; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.utils.Biu; 
import ap.utils.Fu; 
import ap.utils.GU; 
import ap.utils.Su; 

public class PanelXml extends JPanel implements 
MouseListener, 
MouseMotionListener, 
ComponentListener, 
KeyListener, 
MouseWheelListener, 
Comparable<PanelXml>	{ 
	 

		 
	private static final long 	serialVersionUID 	= 1L; 
	 
	public static Map<Integer,PanelXml> items 		= new HashMap<Integer,PanelXml>(); 
	 
	public static Map<Integer,PanelXml> selected 	= new HashMap<Integer,PanelXml>(); 
	 
	public static Map<Integer,PanelXml> moved	 	= new HashMap<Integer,PanelXml>(); 
	 
	 
	private  List<Cmd> 					cmds	 		= null; 
	 
	private  IdentityImplObject  		ido; 
	 
	private  DragableImplObject  		drago; 
	 
	private  DecorateImplObject  		decoro; 
	 
	private  ImageImplObject  			imgo; 
	 
	 
	 
	private int 			state 		= 0; 
	 	 
	private Rectangle 		selector 	= Ru.get_init_rect(gl.E_EMPTY); 
	 
	 
		 
	private StringBuilder 	cmd 		= null; 
	 
	private Cmd 			fire_cmd  ; 
	 	 
	private int 			cmd_scroll 	= gl.E_EMPTY; 
	 
	private boolean			bl_reconstruction_complete; 
	 
	public static boolean 	bl_skip_child_accept; 
	 
	public boolean			bl_repaint_complete; 
	 
	private Map<Integer,Boolean> keys = new LinkedHashMap<Integer,Boolean> (); 
	 
	 
	 
	public Map<Integer, Boolean> getKeys() { 
		return keys; 
	} 

	public void setKeys(Map<Integer, Boolean> keys) { 
		this.keys = keys; 
	} 

	public boolean is_repaint_complete() { 
		return bl_repaint_complete; 
	} 

	public void set_repaint_complete(boolean bl_repaint_complete) { 
		this.bl_repaint_complete = bl_repaint_complete; 
	} 

	public  FrameImplObject getFio() { 
		return Application.getFio(); 
	} 
	 
	public  DesktopImplObject getDio() { 
		return Application.getDio(); 
	} 
	 
	 
	public ImageImplObject getImgo() { 
		return imgo; 
	} 
	public void setImgo(ImageImplObject imgo) { 
		this.imgo = imgo; 
	} 
	public DecorateImplObject getDecoro() { 
		return decoro; 
	} 
	public void setDecoro(DecorateImplObject decoro) { 
		this.decoro = decoro; 
	} 
	public IdentityImplObject getIdo() { 
		return ido; 
	} 
	public void setIdo(IdentityImplObject ido) { 
		this.ido = ido; 
	} 
	public DragableImplObject getDrago() { 
		return drago; 
	} 
	public void setDrago(DragableImplObject drago) { 
		this.drago = drago; 
	} 
		 
		 
	public boolean isBl_skip_child_accept() { 
		return bl_skip_child_accept; 
	} 

	public void setBl_skip_child_accept(boolean bl_skip_child_accept) { 
		PanelXml.bl_skip_child_accept = bl_skip_child_accept; 
	} 

	private int wheel_index ; 
	 
	public int getWheel_index() { 
		return wheel_index; 
	} 

	public void setWheel_index(int wheel_index) { 
		this.wheel_index = wheel_index; 
	} 
	 
	public boolean isBl_reconstruction_complete() { 
		return bl_reconstruction_complete; 
	} 

	public void setBl_reconstruction_complete(boolean bl_reconstruction_complete) { 
		this.bl_reconstruction_complete = bl_reconstruction_complete; 
	} 

	public Cmd getFire_cmd() { 
		return fire_cmd; 
	} 

	 
	public void setFire_cmd(Cmd fire_cmd) { 
		this.fire_cmd = fire_cmd; 
	} 
	 

	public int getCmd_scroll() { 
		return cmd_scroll; 
	} 

	public void setCmd_scroll(int cmd_scroll) { 
		this.cmd_scroll = cmd_scroll; 
	} 

	public  List<Cmd> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<Cmd> cmds) { 
		this.cmds = cmds; 
	} 

	 
	public StringBuilder getCmd() { 
		return cmd; 
	} 

	public void setCmd(StringBuilder cmd) { 
		this.cmd = cmd; 
	} 

	public  Map<Integer, PanelXml> getMoved() { 
		return moved; 
	} 

	public  void setMoved(Map<Integer, PanelXml> moved) { 
		PanelXml.moved = moved; 
	} 

	public Map<Integer, PanelXml> getItems() { 
		return items; 
	} 
	 
	public Map<Integer, PanelXml> getSelected() { 
		return selected; 
	} 
	 
	public Rectangle getSelector() { 
		return selector; 
	} 
	public void setSelector(Rectangle selector) { 
		this.selector = selector; 
	} 
	 
		 
	public int getState() { 
		return state; 
	} 


	public void setState(int state) { 
		this.state = state; 
	} 


	public void setBoundsImpl(Rectangle bounds ) { 
		 
		int MW = Application.getCio().getMinWidthHeight(); 
		 
		if(bounds.height <= MW*4) 
			bounds.height = MW*4; 
		 
		if(bounds.width <= MW*4) 
			bounds.width = MW*4; 
		 
		this.setBounds(bounds); 
	} 
	 
	 
	public PanelXml() { 

		setup_intf(); 

		setup_default_attrs(); 

		setup_listeners(); 

	} 

	public PanelXml(Rectangle rect) { 
		 
		this(); 
		 
		this.setBounds(rect); 
		 
				 
	} 
	 
	public PanelXml(Dimension size) { 
		 
		this(); 
		 
		this.setSize(size); 
	 
	} 


	@Override 
	public int compareTo(PanelXml o) { 
		 
		IdentityImplObject i = this.getIdo(); 
		 
		return i.getIndex() > o.getIdo().getIndex() ? 1 : (i.getIndex() < 
				o.getIdo().getIndex() ? -1 : 0); 
	} 

	public boolean acceptSelector(MouseEvent e) 
	{ 
		try 
		{ 
			Point p_start_pos = this.getDrago().getStartPos(); 
			 
		this.setSelector(new Rectangle( 
				p_start_pos.x,p_start_pos.y, 
				(e.getPoint().x - p_start_pos.x),(e.getPoint().y - p_start_pos.y) 
				)); 
		 
			repaint(); 
			 
			return true; 
		} 
		catch(Exception x) 
		{ 
			return false; 
		} 
		 
	} 
	 
	public boolean acceptSelectedItems() 
	{ 
		try 
		{ 
			this.getItems().forEach((k,v)-> 
			{ 
				if (this.getSelector().contains(v.getBounds())) 
				{ 
					if(!v.isSelected()) 
					{ 
						v.setSelected(true); 
					 
						if(!this.getSelected().containsKey(v.getIdo().getId())) 
							this.getSelected().put(v.getIdo().getId(),v); 
					 
					} 
				} 
				else 
				{ 
						v.setSelected(false); 
					 
					if(this.getSelected().containsKey(v.getIdo().getId())) 
						this.getSelected().remove(v.getIdo().getId()); 
				 
				} 
					v.repaint(); 
			}); 
			 
			return true; 
		 
	} 
	catch(Exception x) 
	{ 
		return false; 
	} 
			 
	} 
	 
	public boolean select_all_items() 
	{ 
		try { 
			this.getItems().forEach((k, v) -> { 
			 
				boolean m_current_state = v.isSelected(); 
				 
				 
				if(m_current_state) 
				{ 
					v.setSelected(false); 
					 
					this.getSelected().remove(v.getIdo().getId()); 
				} 
				else 
				{ 
					v.setSelected(true); 
					 
					this.getSelected().put(v.getIdo().getId(), v); 
				} 
			}); 

				    this.getDio().getDeskTop().repaint(); 
				 
					return true; 

		} catch (Exception x) { 
					return false; 
		} 

} 
	 
	public void mouseDraggedHelper(MouseEvent e) { 
		 
		// Reset flag. 
		if(	this.isBl_skip_child_accept()) 
			this.setBl_skip_child_accept(false); 
		 
		// Draw selector only. 
		if(this.isDenyDrag()) 
		{ 
			if(acceptSelector(e)) 
			   acceptSelectedItems(); 
			 
			return; 
		} 
		 
		// Set dragged flag. 
		this.setDragged(true); 
		 
		// Primary dance. 
		 
		setDxDy(e); 
		 
		pullOnTop(e); 
		 
		//Moving or resizing. 
		if(!dragMoveByBodyHelper(e)) 
		{ 
			dragResizingByOtherHelper(e); 
		} 
		 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
			mouseDraggedHelper(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		this.getDrago().getServiceRects().forEach(name -> 
		{ 

			Ru ar = (Ru) name; 

			if (ar.contains(e.getPoint())) { 

				this.getDrago().setDragType(ar.getServiceType()); 

				this.setCursor(new Cursor(ar.getServiceType())); 

			} 

		}); 

		this.setToolTipText(this.toString()); 
		 
	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		 
		 
		if (SwingUtilities.isRightMouseButton(e)) { 
			if(!Biu.ISA(this.getState(),Fl.VK_MOUSE_RIGHT_CLICK)) 
				this.setState(Biu.ON(this.getState(),Fl.VK_MOUSE_RIGHT_CLICK)); 
		} 

		if (SwingUtilities.isLeftMouseButton(e)) { 
			if(!Biu.ISA(this.getState(),Fl.VK_MOUSE_LEFT_CLICK)) 
			   this.setState(Biu.ON(this.getState(),Fl.VK_MOUSE_LEFT_CLICK)); 
		} 
		 
		mouseClickHelper(e); 
		 
		this.requestFocus(); 
		 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		this.getDrago().setStartPos(e.getPoint()); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
			 
		if (this.getDrago().getDragType() != Cursor.DEFAULT_CURSOR) 
		{ 
			mouseReleaseHelperEnd(e); 
			 
			return; 
		} 

			mouseReleaseHelper(e); 
		 
		 
	} 

	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		 
		if (!addRectComponents()) 
			gl.tracex(new Object() {}, String.format("%s...add ctrl rects...%d...id...%d", 
					gl.S_ERROR,this.getDrago().getServiceRects().size(), this.getIdo().getId())); 
		 
		//this.requestFocus();	 
		 
		 
	} 

	 
	@Override 
	public void mouseExited(MouseEvent e) { 
	 
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
		 
		this.getDrago().getServiceRects().clear(); 
		 
	} 
	 
	 
	public int findKeyByIndex(Map<Integer,PanelXml> map,int index) 
	{ 
		int []  result = { gl.E_ERROR }; 
		 
		map.forEach((k,v)->{ 
			 
			if(v.getIdo().getIndex()== index) 
			{ 
				result[0] = k; 
			} 
		}); 
		 
			return result[0]; 
	} 

	 
		 
	public boolean addRectComponents() { 

		Rectangle rect = this.getBounds(); 

		if (rect == null) 
			return true; 

		int OFF = TPanel.MW; 

		 
		if(this.getDrago().getServiceRects() == null) 
			this.getDrago().setServiceRects(new ArrayList<Ru>()); 
		 
		this.getDrago().getServiceRects().clear(); 

		int w = rect.width; 

		int h = rect.height; 

		// 1 
		Ru rec_north_west = new Ru(new Rectangle(0, 0, OFF, OFF), 

		Cursor.NW_RESIZE_CURSOR); 

		// 2 
		Ru rec_north_east = new Ru(new Rectangle((w - OFF), 0, OFF, OFF), 

		Cursor.NE_RESIZE_CURSOR); 

		// 3 
		Ru rec_south_east = new Ru(new Rectangle((w - OFF), (h - OFF), 

		OFF, OFF), Cursor.SE_RESIZE_CURSOR); 

		// 4 
		Ru rec_south_west = new Ru(new Rectangle(0, (h - OFF), OFF, OFF), 

		Cursor.SW_RESIZE_CURSOR); 

		// 5 
		Ru rec_west = new Ru(new Rectangle(0, OFF, OFF, (h - OFF * 2)), 

		Cursor.W_RESIZE_CURSOR, Color.red); 

		// 6 
		Ru rec_north = new Ru(new Rectangle(OFF, 0, (w - OFF * 2), OFF), 

		Cursor.N_RESIZE_CURSOR, Color.red); 

		// 7 
		Ru rec_east = new Ru(new Rectangle((w - OFF), OFF, OFF, 

		(h - OFF * 2)), Cursor.E_RESIZE_CURSOR, Color.red); 

		// 8 
		Ru rec_south = new Ru(new Rectangle(OFF, h - OFF, (w - OFF * 2), 

		OFF), Cursor.S_RESIZE_CURSOR, Color.red); 

		// 9 
		Ru rec_body = new Ru(new Rectangle(OFF, OFF, (w - OFF * 2), 

		(h - OFF * 2)), Cursor.DEFAULT_CURSOR, Color.blue); 

		this.addRect(rec_north_west, rec_north_east, rec_south_east, 

		rec_south_west, rec_west, rec_north, rec_east, rec_south, 

		rec_body); 

		return (this.getDrago().getServiceRects().size() == 9); 

	} 
	 
	 
	public void addRect(Object ... args) 
	{ 
		for (Object arg : args) 
		{ 
			this.getDrago().getServiceRects().add((Ru)arg); 
		} 
	} 

	public boolean isaChilds() 
	{ 
		return (this.getChildsCount(this.getIdo().getId()) != gl.E_EMPTY); 
	} 
	 
	public boolean isaTop() 
	{ 
		return ( this.getIdo().getIndex() == gl.E_EMPTY); 
	} 
	 
	 
	 
	public boolean stay_on_top() 
	{ 
		String msg = "Stay on Top"; 
		 
		DesktopImplObject dio = Application.getDio(); 
		 
		try 
		{ 
			 
		if (this.isBl_reconstruction_complete()) 
		{ 
			return true; 
		} 
		 
		 
		//Get list of childs components. 
		List<PanelXml> m_childs = this.get_childs_native_reversed_by_level(this.getIdo().getId()); 
		 
		// Add self. 
		m_childs.add(this); 
		 
		// Get a common list of components in the suite. 
		Component [] co_list = dio.getDeskTop().getComponents(); 
		 
		//Convert one to list for useful using 
		List<Component> cl = Arrays.asList(co_list); 
		 
		// Add to common list for processing. 
		List<PanelXml> la = new ArrayList<PanelXml>(); 
		 
		cl.forEach(c-> 
		{ 
			la.add((PanelXml)c); 
		}); 
		 
		 
		// Unique index for z-order. 
		int [] index = {0}; 
		 
		// Firstly, processing of base list childs objects. 
		m_childs.forEach(a-> 
		{ 
			dio.getDeskTop().setComponentZOrder(a, index[0]); 
			 
			index[0]++; 
			 
		}); 
		 
		 
		// Secondary, processing of common list components . 
		la.forEach(a-> 
		{ 
			 
			// Exclude of components that was added on primary step of processing. 
			if(!m_childs.contains(a)) 
			{ 
				dio.getDeskTop().setComponentZOrder(a, index[0]); 
				 
				index[0]++; 
			 
			} 

		} 
		); 
		 
			dio.getDeskTop().revalidate(); 
			 
			this.setBl_reconstruction_complete(true); 
			 
			// gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
					 
			return this.isBl_reconstruction_complete(); 
			 
	} 
	catch(Exception e) 
	{ 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage(),gl.S_ERROR)); 
			 
			return false; 

	} 

	} 
	 
	 
	public boolean pullOnTop(MouseEvent me) 
	{ 
		 
		 //if(!this.isaChilds() && !this.isaTop()) 
		//	return refreshComponents(this); 
		 
			return stay_on_top(); 
		 												 
	} 

	 
	public void moveByDxDy(int dx,int dy) 
	{ 
		this.setLocation(this.getLocation().x+dx,this.getLocation().y+dy); 
	} 

	 
	public void moveChildsByDxDy(int dx,int dy) 
	{ 
		 
		String msg = "moveChildsByDxDy()"; 
		 
		List<PanelXml> c_hilds = get_childs_native(this.getIdo().getId()); 
			 
		long start = System.currentTimeMillis(); 
		 
		c_hilds.forEach(a-> 
		{ 
			a.moveByDxDy(dx,dy); 
		}); 
		 
		long end = System.currentTimeMillis(); 
				 
		//gl.tracex(new Object(){},gl.sf("%s...Count of items...%03d...Elapsed...%05d...ms.",msg,c_hilds.size(),(end - start))); 
		 
	} 
	 
	public boolean moveSelectedByDxDy(int dx,int dy) 
	{ 

		try 
		{ 
			if(!this.isSelected()) 
				return false; 
		 
			this.getSelected().remove(this.getIdo().getId()); 
			 
			this.get_childs_native(this.getIdo().getId()).forEach(a-> 
			{ 
				this.getSelected().remove(a.getIdo().getId()); 
			}); 
			 
			this.getSelected().forEach((k,v)-> 
			{ 
				v.moveByDxDy(dx,dy); 
			}); 
				 
			 
			return true; 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
	} 

	public void moveGroup(int dx,int dy) 
	{ 
		// Byself. 
		moveByDxDy(dx,dy); 
		 
		// Childs. 
		//moveChildsByDxDy(dx,dy); 
		 
		// Selected. 
		moveSelectedByDxDy(dx,dy); 
		 
	} 
	 
	public boolean dragMoveByBodyHelper(MouseEvent me) 
	{ 
		 
		if (this.getDrago().getDragType() != Cursor.DEFAULT_CURSOR) 
			return false; 

				// Drago mangling SECTION. 
				if(this.getDecoro().getAlign() != gl.E_EMPTY) 
				{ 
					this.getIdo().getOwner().getDrago().setDx(this.getDrago().getDx()); 
					 
					this.getIdo().getOwner().getDrago().setDy(this.getDrago().getDy()); 
					 
					this.getIdo().getOwner().dragMoveByBodyHelper(me); 
					 
					return true; 
				} 
		 
		 
				 
		moveGroup(this.getDrago().getDx(),this.getDrago().getDy()); 
		 
		this.getDrago().setDropTarget(this.getTarget()); 
			 
		// Global reset flags. 
		this.resetDragTypeTargetFlags(); 
		 
		// State machine. 
		if(this.getDrago().getPrevDropTarget() == null && this.getDrago().getDropTarget() != null && this.getDrago().getDropTarget().isDesktop()) 
		{ 
						 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_INIT_OVER_DESKTOP)); 
				 
		}else if(this.getDrago().getPrevDropTarget() == null && this.getDrago().getDropTarget() != null && !this.getDrago().getDropTarget().isDesktop()) 
		{ 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_INIT_OTHER_OBJECT_FROM_DESKTOP)); 
		} 
		else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() == gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId()==gl.E_OK) 
		{ 
						 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_REDRAG_OVER_DESKTOP)); 
			 
		} else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId()==gl.E_OK) 
		{ 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_DESKTOP)); 
			 
		}else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() == gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId() != gl.E_OK) 
		{ 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_FROM_DESKTOP_TO_OBJECT)); 
			 
		}else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getPrevDropTarget().getIdo().getLevel() > 
				  this.getDrago().getDropTarget().getIdo().getLevel() 
				 
				) 
		{ 
			 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_DOWN)); 
		 
		}else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getPrevDropTarget().getIdo().getLevel() < this.getDrago().getDropTarget().getIdo().getLevel() 
				 
				) 
		{ 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_UP)); 
			 
		} else if(this.getDrago().getPrevDropTarget() != null && 
				  this.getDrago().getPrevDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getDropTarget() != null && 
				  this.getDrago().getDropTarget().getIdo().getId() != gl.E_OK && 
				  this.getDrago().getPrevDropTarget().getIdo().getLevel() ==  this.getDrago().getDropTarget().getIdo().getLevel() 
				 
				) 
		{ 
			//this.setState(BitUtils.ON(this.getState(),Fl.VK_DRAG_TYPE_IN_THE_SAME_PARENT_OBJECT_AREA)); 
		} 
		 
		 		//String msg = gl.sf("Target...%s..drag type flag...%s",this.getDrago().getDropTarget().getName().toUpperCase(),Fl.get_drag_type_name(this.getState())); 
		 		 
		 		//gl.tracex(new Object(){},gl.sf("%s",msg)); 
		 		 
				return true; 
	} 
	 
	public void dragResizingByOtherHelper(MouseEvent me) 
	{ 
		 int MW = Application.getCio().getMinWidthHeight();; 
				 
		 Rectangle tr = null; 
		 
		 if (this.getDrago().getStartPos() != null) { 

			 int x = getX(); 

			 int y = getY(); 
			 
			 int w = getWidth(); 
			 
			 int h = getHeight(); 
			 
			 int dx = this.getDrago().getDx(); 
			 
			 int dy = this.getDrago().getDy(); 
			 
			 //Rectangle  parent_area = this.getIdo().getOwner().getBounds(); 
			 
			 
			switch (this.getDrago().getDragType()) { 

			case Cursor.N_RESIZE_CURSOR: 

				if (!(h - dy < MW)) { 

					tr = new Rectangle(x, y + dy, w, h - dy); 
				} 

				break; 

			case Cursor.S_RESIZE_CURSOR: 

				if (!(h + dy < MW)) { 

					this.getDrago().setStartPos(me.getPoint()); 
					 
					tr = new Rectangle(x, y, w, h + dy); 
				} 

				break; 

			case Cursor.W_RESIZE_CURSOR: 

				if (!(w - dx < MW)) { 
	 
					tr = new Rectangle(x + dx, y, w - dx, h); 

				} 

				break; 

			case Cursor.E_RESIZE_CURSOR: 

				if (!(w + dx < MW)) { 

					this.getDrago().setStartPos(me.getPoint()); 
			 
					tr = new Rectangle(x, y, w + dx, h); 

				} 

				break; 

			case Cursor.NW_RESIZE_CURSOR: 

				if (!(w - dx < MW) && !(h - dy < MW)) { 

					tr = new Rectangle(x + dx, y + dy, w - dx, h - dy); 
				} 

				break; 

			case Cursor.NE_RESIZE_CURSOR: 

				if (!(w + dx < MW) && !(h - dy < MW)) { 

					this.getDrago().setStartPos(new Point(me.getX(), this.getDrago().getStartPos().y)); 
					 
					tr = new Rectangle(x, y + dy, w + dx, h - dy); 
					 
				} 

				break; 

			case Cursor.SW_RESIZE_CURSOR: 

				if (!(w - dx < MW) && !(h + dy < MW)) { 

					 
					this.getDrago().setStartPos(new Point(this.getDrago().getStartPos().x, me.getY())); 
					 
					tr = new Rectangle(x + dx, y, w - dx, h + dy); 
 

				} 

				break; 

			case Cursor.SE_RESIZE_CURSOR: 

				if (!(w + dx < MW) && !(h + dy < MW)) { 

					this.getDrago().setStartPos(me.getPoint()); 
					 
					tr = new Rectangle(x, y, w + dx, h + dy); 

				} 
				break; 

			} // switch 
			 
				// Global Acceptor. 
			 
				if(tr != null) 
				this.setBounds(tr); 
				 
		 
	}// dragHelper 
} 

	 
	 
	 
	 
	public void mouseReleaseHelper(MouseEvent e) { 
		 
		PanelXml t = this.getDrago().getDropTarget(); 
		 
		if(t == null) 
		{ 
			mouseReleaseHelperEnd(e); 
			 
			return; 
		} 
		 
		if(!connect(t)) 
		{ 
			// The place to catch connection,maybe. 
			mouseReleaseHelperEnd(e); 
			 
			return; 
		} 
		 
		// Additional operation by type. 
		dragTypeLandingHelper(this.getState(),t); 

		// Set prev drop targets. 
		this.getDrago().setPrevDropTarget(this.getDrago().getDropTarget()); 
				 
		// Post operations. 
		mouseReleaseHelperEnd(e); 
		 
		//gl.smn("Parent..." + this.get_root_owner(this.getId())); 
		 
	} 
	 
	 
	public void dragTypeLandingHelper(int state,PanelXml target) 
	{ 
		/* 
		if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_INIT_OVER_DESKTOP)) 
	    { 
	    	 
	    } 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_INIT_OTHER_OBJECT_FROM_DESKTOP)) 
		{ 
	    	 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_REDRAG_OVER_DESKTOP)) 
		{ 
	    	 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_DESKTOP)) 
		{ 
			 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_DESKTOP_TO_OBJECT)) 
		{ 
	    	 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_IN_THE_SAME_PARENT_OBJECT_AREA)) 
		{ 
	    	 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_DOWN)) 
		{ 
			 
		} 
		else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_UP)) 
		{ 
			 
		} 
		*/ 
	} 

	public  boolean  set_childs_zorder() 
	{ 
		 
		int		[] index = { 0 }; 
		 
		this.get_childs_native_reversed_by_level(this.getIdo().getId()) 
				.forEach( 
						a -> { 
									 
							a.getIdo().setIndex(index[0]); 
							 
							gl.tracex(new Object() {},gl.sf("Set child z-order...id...%d...level...%d...z-order...%d",a.getIdo().getId(),a.getIdo().getLevel(),a.getIdo().getIndex())); 
							 
							getDio().getDeskTop().setComponentZOrder(a, a.getIdo().getIndex()); 

							index[0]++; 

						}); 
		 
							this.getIdo().setIndex(index[0]); 
							 
							getDio().getDeskTop().setComponentZOrder(this,this.getIdo().getIndex()); 

							getDio().getDeskTop().repaint(); 

							return check_childs_zorder(); 
	} 
	 
	public  boolean  check_childs_zorder() 
	{ 
		 
		int		[] index = { 0 }; 
		 
		boolean [] bl_check = {false}; 

		int 	[] error_count =  {0}; 


		this.get_childs_native_reversed_by_level(this.getIdo().getId()) 
				.forEach( 
						a -> { 

							bl_check[0] = (a.getIdo().getIndex() == index[0]); 
							 
							if(!bl_check[0]) 
								error_count[0]++; 
									 
							gl.tracex(new Object() {},gl.sf("Check child z-order...real...%d...must...%d...check...%s",a.getIdo().getIndex(), index[0],bl_check[0])); 

							index[0]++; 

						}); 

		return (error_count[0] == gl.E_EMPTY); 
	} 
	 

	public 	boolean  check_childs_level() 
	{ 
		int [] base_level = {this.getIdo().getLevel()}; 
		 
		int [] index = {1}; 
		 
		boolean [] bl_check = {false}; 
		 
		int [] error_count =  {0}; 
		 
		this.get_childs_native_reversed_by_level(this.getIdo().getId()) 
		.forEach( 
				a -> { 
					 
					int i_lvl = base_level[0] + index[0]; 
							 
					bl_check[0] =  (a.getIdo().getLevel() == i_lvl); 
					 
					if(!bl_check[0]) 
						error_count[0]++; 
					 
					gl.tracex(new Object() {},gl.sf("%s...check level...%d...<>...%d...%s...errors...%d","check_childs_level()", a.getIdo().getLevel(),i_lvl,bl_check[0],error_count[0])); 
					 
				   	base_level[0]++; 
					 
				   	index[0]++; 
				   	 
				}); 
		 
				return (error_count[0] == gl.E_EMPTY); 
	} 

	public 	boolean  set_childs_level() 
	{ 

		int [] base_level = {this.getIdo().getLevel()+1}; 
		 
		this.get_childs_native_reversed_by_level(this.getIdo().getId()) 
		.forEach( 
				a -> { 
					 
					a.getIdo().setLevel(base_level[0]); 
					 
					gl.tracex( 
							new Object() { 
							}, 
							gl.sf("%s...Add level from...%d...to...%d...for id...%d","set_childs_level()", base_level[0],a.getIdo().getLevel(),a.getIdo().getId())); 

					   base_level[0]++; 
				}); 

				return check_childs_level(); 
	} 
	 
	 
	 
	public boolean connect(PanelXml target) 
	{ 
		try 
		{ 
			 
			boolean bl_result = false; 
		 
			this.getIdo().setPid(target.getIdo().getId()); 
			 
			this.getIdo().setOwner(target); 

		if (target.getIdo().getId() != gl.E_OK) { 
			 
			this.getIdo().setLevel(target.getIdo().getLevel() + 1); 
			 
			//this.setBackground(target.getBackground()); 
			 
			bl_result = (this.getIdo().getPid() == target.getIdo().getId() && (this.getIdo().getLevel() == target.getIdo().getLevel()+1)); 
		 
		} 
		else 
		{ 
			bl_result = (this.getIdo().getPid() == getDio().getDeskTop().getIdo().getId()); 
		} 
			 
			if(!bl_result) 
				this.getIdo().setOwner(null); 
			 
			//this.setBounds(x, y, width, height); 
			 
			target.add(this); 
			 
			gl.tracex(new Object(){},gl.sf("Connect...%s",bl_result)); 
			 
			return bl_result; 
		 
		 } 
		catch(Exception e) 
		{ 
			gl.tracex(new Object(){},gl.sf("Connect...%s...%s",e.getMessage(),gl.S_OK)); 
			 
			this.getIdo().setOwner(null); 
			 
			return false; 
		} 
		 
	} 
		 
	public void mouseReleaseLandingHelper(PanelXml target,MouseEvent e) { 
		 
		if(!connect(target)) 
		{ 
			return; 
		} 
	} 
	 
	 
	 
	public void mouseReleaseHelperEnd(MouseEvent me) 
	{ 
		if(this.isDrawSelector()) 
		{ 
			 
			// Reset selector area. 
			this.setSelector(Ru.get_init_rect(gl.E_EMPTY)); 

			this.repaint(); 

		} 
		 
		if(this.isDragged()) 
		{ 
			 
			if(this.getDrago().getDragType() == Cursor.DEFAULT_CURSOR) 
			{ 
				//Drag for the body. 
				 
			} 
			else 
			{ 
				// Resize. 
			} 
			 
		} 
		else 
		{ 
			// Just released the button... 
			 
		} 
		 
		// Reset drag type flags. 
		resetDragTypeTargetFlags(); 
		 
		// Reset rest flags. 
		resetRestFlags(); 
	 
	} 
	 
	public void resetRestFlags() { 

		this.getDrago().setStartPos(null); 

		this.setState(Biu.OFF(this.getState(), Fl.VK_DRAGGED)); 
		this.setState(Biu.OFF(this.getState(), Fl.VK_MOUSE_LEFT_CLICK)); 
		this.setState(Biu.OFF(this.getState(), Fl.VK_MOUSE_RIGHT_CLICK)); 
		 
		this.set_repaint_complete(false); 

	} 
	 
	public void resetDragTypeTargetFlags() 
	{ 
			/* 

			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_INIT_OVER_DESKTOP)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_INIT_OTHER_OBJECT_FROM_DESKTOP)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_REDRAG_OVER_DESKTOP)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_DESKTOP)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_FROM_DESKTOP_TO_OBJECT)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_IN_THE_SAME_PARENT_OBJECT_AREA)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_DOWN)); 
			this.setState(BitUtils.OFF(this.getState(),Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_UP)); 
			 */ 
			 
			// Reset flag, for support of the stay_on_top functionality. 
			this.setBl_reconstruction_complete(true); 
			 
			 
			 
	} 
	 
	 
	public void selectedResetHelper() 
	{ 
		this.getSelected().forEach((k,v)->{ v.setSelected(false);v.repaint();}); 
		 
		this.getSelected().clear(); 
		 
		this.getMoved().clear(); 
		 
		this.getItems().forEach((k,v)->{ 
		 
			if(v.isSelected()) 
			{ 
				v.setSelected(false); 
				 
				v.repaint(); 
			} 
			 
		}); 
		 
		 
	} 
	 
	public void selectedHelper() 
	{ 
		this.setSelected(!this.isSelected()); 
		 
		if(this.isSelected()) 
			{ 
				// Add to "selected" collection. 
			if(!this.getSelected().containsKey(this.getIdo().getId())) 
				this.getSelected().put(this.getIdo().getId(),this); 
			} 
			else 
			{ 
				//Remove from "selected" collection. 
				if(this.getSelected().containsKey(this.getIdo().getId())) 
				   this.getSelected().remove(this.getIdo().getId()); 
			} 
		 
			this.getMoved().clear(); 
			 
		 	this.getMoved().putAll(this.getSelected()); 
		 
			this.repaint(); 
			 
					 
	} 
	public void mouseClickHelper(MouseEvent me) 
	{ 
		int m_click_count = me.getClickCount(); 
		 
		int m_bloat_increment = 2; 
		 
		if(me.isControlDown()) 
		{ 
			m_bloat_increment *= 10; 
		} 
		 
		if(Biu.ISA(this.getState(),Fl.VK_MOUSE_LEFT_CLICK)) 
		{ 
			if(m_click_count == gl.ONE) 
			{ 
				if(this.isDesktop()) 
				selectedResetHelper();	 
				else 
				selectedHelper(); 
				 
			} else if(m_click_count == gl.TWO) 
			{ 
				if(this.isSelected()) 
					this.setSelected(false); 
				 
				this.bloat(new Dimension(m_bloat_increment,m_bloat_increment)); 
			} 
			 
		}else if (Biu.ISA(this.getState(),Fl.VK_MOUSE_RIGHT_CLICK)) 
		{ 
			if(m_click_count == gl.ONE) 
			{ 
				 
			} else if(m_click_count == gl.TWO) 
			{ 
				 
				this.bloat(new Dimension(gl.E_ERROR*m_bloat_increment,gl.E_ERROR*m_bloat_increment)); 
			} 
			 
			 
		} 

	} 
	public boolean isDesktop() { 

		return (this.getIdo().getId()== gl.E_OK); 
		 
	} 
	 
	public boolean isSelected() { 

		return Biu.ISA(this.getState(), Fl.VK_SELECTED); 
		 
	} 

	public void setSelected(boolean selected) { 

		if (selected) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_SELECTED)); 
		} else { 
			this.setState(Biu.OFF(this.getState(), Fl.VK_SELECTED)); 
		} 

	} 
	 
	public boolean isDrawSelector() { 

		return Biu.ISA(this.getState(), Fl.VK_DRAW_SELECTOR); 
		 
	} 

	public void setDrawSelector(boolean DrawSelector) { 

		if (DrawSelector) { 
			this.setState(Biu.ON(this.getState(),Fl.VK_DRAW_SELECTOR)); 
		} else { 
			this.setState(Biu.OFF(this.getState(),Fl.VK_DRAW_SELECTOR)); 
		} 

	} 
	 
	 
	public boolean isImageFit() { 

		return Biu.ISA(this.getState(), Fl.VK_IMAGE_FIT); 
		 
	} 

	public void setImageFit(boolean fit) { 

		if (fit) { 
			this.setState(Biu.ON(this.getState(),Fl.VK_IMAGE_FIT)); 
		} else { 
			this.setState(Biu.OFF(this.getState(),Fl.VK_IMAGE_FIT)); 
		} 

	} 
	 
	public boolean isDragged() { 

		return Biu.ISA(this.getState(), Fl.VK_DRAGGED); 
		 
	} 

	public void setDragged(boolean dragged) { 

		if (dragged) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_DRAGGED)); 
		} else { 
			this.setState(Biu.OFF(this.getState(),Fl.VK_DRAGGED)); 
		} 

	} 
	 

	public boolean isDropTarget() { 

		return Biu.ISA(this.getState(), Fl.VK_DROP_TARGET); 
		 
	} 

	public void setDropTarget(boolean drop) { 

		if (drop) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_DROP_TARGET)); 
		} else { 
			this.setState(Biu.OFF(this.getState(),Fl.VK_DROP_TARGET)); 
		} 

	} 
	 
	public boolean isDenyDrop() { 

		return Biu.ISA(this.getState(), Fl.VK_DENY_DROP); 
		 
	} 
	 
	public void setDenyDrop(boolean deny) { 

		if (deny) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_DENY_DROP)); 
		} else { 
			this.setState(Biu.OFF(this.getState(), Fl.VK_DENY_DROP)); 
		} 

	} 

	 
	 
	public boolean isDenyDrag() { 

		return Biu.ISA(this.getState(), Fl.VK_DENY_DRAG); 
		 
	} 
	 
	public void setDenyDrag(boolean deny) { 

		if (deny) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_DENY_DRAG)); 
		} else { 
			this.setState(Biu.OFF(this.getState(), Fl.VK_DENY_DRAG)); 
		} 

	} 
	 
	public boolean isDebug() { 

		return Biu.ISA(this.getState(), Fl.VK_DEBUG); 
		 
	} 
	 
	public boolean isDelete() { 

		return Biu.ISA(this.getState(), Fl.VK_DELETE); 
		 
	} 
	 
	public void setDelete(boolean delete) { 

		if (delete) { 
			 
			this.setState(Biu.ON(this.getState(), Fl.VK_DELETE)); 
			 
		} else { 
			this.setState(Biu.OFF(this.getState(), Fl.VK_DELETE)); 
		} 

	} 
	 
	 
	public boolean isShowKeyCode() { 

		return Biu.ISA(this.getState(), Fl.VK_SHOW_KEY_CODE); 
		 
	} 
	 
	public void setShowKeyCode(boolean show) { 

		if (show) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_SHOW_KEY_CODE)); 
		} else { 
			this.setState(Biu.OFF(this.getState(),Fl.VK_SHOW_KEY_CODE)); 
		} 

	} 
	 

	 
	public boolean isSkipText() { 

		return Biu.ISA(this.getState(), Fl.VK_SKIP_TEXT); 
		 
	} 

	public void setSkipText(boolean skip_text) { 

		if (skip_text) { 
			this.setState(Biu.ON(this.getState(), Fl.VK_SKIP_TEXT)); 
		} else { 
			this.setState(Biu.OFF(this.getState(), Fl.VK_SKIP_TEXT)); 
		} 

	} 
	 
	public void paintOverBufferImage(Graphics g) { 
	 
		paintComponentHelper(g); 
		 
			 
	} 

	 
	@Override 
	public void paintComponent(Graphics g) { 

		 
		 	super.paintComponent(g); 
		 	 
		 	paintComponentHelper(g); 
			 
	} 
	 
	public void paintComponentHelper(Graphics g) 
	{ 
		if (this.getBounds() == null) { 
			return; 
		} 
	 
		paintImage(g); 
		 
		paintTextHelper(g); 
		 
		paintSelector(g); 
		 
		paintSelected(g); 
		 
		paintGrid(g); 
	} 

	public void paintImage(Graphics g) { 

		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		if (this.getImgo().getImage() != null) { 
			GU.drawImage(g2, this.getImgo().getImage(), wr); 
		} 
	} 
	 
	public void paintSelector(Graphics g) { 
	 
		if(!this.isDrawSelector()) 
			return; 
		 		 		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(gl.dashed_stroke_thin); 
		 
		GU.drawAlphaRect(g2,this.getSelector(),Color.white); 
		 
		GU.fillAlphaRect(g2,this.getSelector(),Color.white,0.1f); 
		 
		g2.setStroke(p_stroke); 

	} 
	 
	public void paintSelected(Graphics g) { 
		 
		if(!this.isSelected()) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(gl.dashed_stroke_thin); 
		 
		GU.drawAlphaRect(g2,Ru.norm4g(this.getBounds()),Color.white); 
		 
		g2.setStroke(p_stroke); 
		 

	} 

	 
	public void paintBody(Graphics g) { 
		 
		Graphics2D g2 = (Graphics2D)g; 
		 
		GU.fillAlphaRect(g2, new Rectangle(0,0,this.getBounds().width,this.getBounds().height),this.getBackground()); 
		 
	} 
	 
	public void paintTextHelper(Graphics g) { 
	 
		 
		if (this.isSkipText()) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		Dimension font_dim = gl.getFontDim(g2, this.getFont(), this.getDecoro().getText()); 


		Rectangle text_align = this.getDecoro().getTextAlign(); 

		AreaManager am = new AreaManager(new Dimension(wr.width, wr.height), 
				new Dimension(3, 3)); 

		Rectangle am_rect = am.get(new Dimension(text_align.x, text_align.y)); 

		Rectangle text_rect = null; 

		AreaManager am_intro = new AreaManager(new Dimension(am_rect.width, 
				am_rect.height), new Dimension(3, 3)); 

		text_rect = am_intro.get(new Dimension(text_align.width, 
				text_align.height)); 

		if (font_dim.width > text_rect.width) { 
			text_rect = am_rect; 
		} else { 
			text_rect.x += am_rect.x; 

			text_rect.y += am_rect.y; 
		} 

		GU.drawAlphaString(g2, this.getDecoro().getText(), text_rect.x 
				+ (text_rect.width - font_dim.width) / 2, 
				(text_rect.y + text_rect.height / 2) + font_dim.height / 2, 
				this.getDecoro().getTextColor(), this.getFont()); 


	} 
		 
	public void paintGrid(Graphics g) { 

		if (!this.isDebug() || this.getDecoro().getAreaManager()	==	null) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 
		 
		int m_width  = wr.width / (Application.getCio().getMinWidthHeight()*2); 
		 
		int m_height = wr.height / (Application.getCio().getMinWidthHeight()*2); 

		AreaManager grid_am = new AreaManager(new Dimension(wr.width, wr.height), new Dimension(m_width,m_height)); 

		grid_am.getGridLinesV().forEach(a -> { 
			 
			GU.drawAlphaLine(g2, a, Color.white, 0.4f); 

		}); 

		grid_am.getGridLinesH().forEach(a -> { 

			GU.drawAlphaLine(g2, a, Color.white, 0.4f); 

		}); 

	} 
	 
	 
	 
	public void setup_default_attrs() 
	{ 
		this.getIdo().setId(IDGen.nextId()); 
		 
		this.getIdo().setPid(gl.E_ERROR); 
		 
		 
		this.setDoubleBuffered(true); 
		 
		this.setName(gl.sf("Panel_%d",this.getIdo().getId())); 
		 
		this.getIdo().setName(this.getName()); 
				 
		 
		this.getDecoro().setTextAlign(new Rectangle(1,1,1,1)); 
		 
		this.getDecoro().setText(gl.sf("%s",this.getName())); 
		 
		this.getDecoro().setTextColor(Color.white); 
		 
		 
		this.setBackground(gl.getRandomColor()); 
		 
		this.setCmd(new StringBuilder()); 
		 
		this.setLayout(null); 
		 
		 
		this.setCmds(new ArrayList<Cmd>()); 
				 
		 
			 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addMouseWheelListener(this); 
		 
		this.addComponentListener(this); 
		 
		this.addKeyListener(this); 
		 
	} 
	 

	 
	public void setup_intf() 
	{ 
		if(this.getDrago() == null) 
		this.setDrago(DragableImplObject.get_instance()); 
		 
		if(this.getIdo() == null) 
		this.setIdo(IdentityImplObject.get_instance()); 
		 
		if(this.getDecoro() == null) 
		this.setDecoro(DecorateImplObject.get_instance()); 
		 
		if(this.getImgo() == null) 
		this.setImgo(ImageImplObject.get_instance()); 
		 
		 
		 
	} 
	 
	 
	 
	@Override 
	public String toString() 
	{ 
		 
		return gl.sf("%s pid %d id %d z-order %d level %d childs %d align %s clazzname %s", 
				this.getName(), 
				 
				this.getIdo().getPid(), 
				this.getIdo().getId(), 
				this.getIdo().getIndex(), 
				this.getIdo().getLevel(), 
				this.getChildsCount(this.ido.getId()), 
				 
				IAlign.indexOf(this.getDecoro().getAlign()), 
						 
				this.getClass().getSimpleName() 
				); 
	} 
	 
	 
	 
	public boolean refreshItems() 
	{ 
		String msg = "Refresh items by GUI components"; 
		 
		DesktopImplObject dio = getDio(); 
		 
		try { 
			PanelXml.items.clear(); 

			for (int i = 0; i < dio.getDeskTop().getComponentCount(); i++) { 
				 
				Object   obj = dio.getDeskTop().getComponent(i); 
				 
				if(obj instanceof PanelXml) 
				{ 
					PanelXml.items.put(((PanelXml)obj).getIdo().getId(), (PanelXml)obj); 
				}else if(obj instanceof XButton) 
				{ 
					PanelXml.items.put(((XButton)obj).getIdo().getId(), (XButton)obj); 
				} 
			} 
			 
			boolean bl_result = (PanelXml.items.size() ==  dio.getDeskTop().getComponentCount()); 
					 
			gl.tracex(new Object(){},gl.sf("%s...counts check...items...%d...gui...%d...%s",msg,PanelXml.items.size(),dio.getDeskTop().getComponentCount(),bl_result)); 
			 
			return bl_result; 
			 
		} catch (Exception e) { 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage(),gl.S_ERROR)); 
			 
			return false; 
		} 
	} 

	 
	public int get_childs_native(int id,Map<Integer,PanelXml> p_childs) 
	{ 
			 
		items.forEach((k,v)-> 
		{ 
			if(v.getIdo().getPid()== id) 
			{ 
				if(!p_childs.containsKey(v.getIdo().getId())) 
					p_childs.put(v.getIdo().getId(),v); 
				 
					v.get_childs_native(v.getIdo().getId(),p_childs); 
			} 
		}); 
		 
			return p_childs.size(); 
				 
	} 
	 
	 
	public List<PanelXml> get_childs_native_reversed_by_level(int id) 
	{ 
		 
		return get_childs_native(id) 
			.stream() 
			.sorted(Comparator.comparing(s -> ((PanelXml) s).getIdo().getLevel()).reversed()) 
			.collect(Collectors.toList()); 
	 
	} 
	 
		 
	public List<PanelXml> get_childs_native(int id) 
	{ 
		Map<Integer,PanelXml> p_childs = new HashMap<Integer,PanelXml>(); 
		 
		List<PanelXml> result = new ArrayList<PanelXml>(); 
				 
		get_childs_native(id,p_childs); 
		 
		p_childs.forEach((k,v)-> 
		{ 
			if(!result.contains(v)) 
				result.add(v); 
		}); 
		 
			return result; 
		 
	} 
	 
	public List<Integer> get_childs_native_int(int id) 
	{ 
		Map<Integer,PanelXml> p_childs = new HashMap<Integer,PanelXml>(); 
		 
		List<Integer> result = new ArrayList<Integer>(); 
				 
		get_childs_native(id,p_childs); 
		 
		p_childs.forEach((k,v)-> 
		{ 
			if(!result.contains(v)) 
				result.add(v.getIdo().getId()); 
		}); 
		 
			return result; 
		 
	} 
	 
	 
	public List<Integer> get_childs(int pid) 
	{ 
		 
		List<Integer> result = new ArrayList<Integer>(); 
				 
		this.getItems().forEach((k,v)-> 
		{ 
			if(v.getIdo().getPid() == pid) 
			{ 
				result.add(v.getIdo().getId()); 
			} 
		}); 
		 
			return result; 
			 
	} 
	 
	public List<PanelXml> getChildsNearOwner(int pid) 
	{ 
		List<PanelXml> result = new ArrayList<PanelXml>(); 
		 
		Component[] co_list = getDio().getDeskTop().getComponents(); 

		for (Component c : co_list) { 
			 
			PanelXml px = (PanelXml)c; 
			 
			if(	px.getIdo().getPid() == this.getIdo().getId()) 
			{ 
				result.add(px); 
			} 
			 
		} 
		 
			return result; 
	} 
	 
	 
	public int get_root_owner(int id) { 

		int[] i_result = { gl.E_ERROR }; 

		// Get root parents list. 
		List<Integer> parents = get_childs(gl.E_OK); 

		parents.forEach(a -> { 
			PanelXml c = this.getItems().get(a); 

			if (c != null) { 
				List<Integer> ch = c.get_childs_native_int(a); 

				if (ch.contains(id)) { 
					i_result[gl.E_EMPTY] = a; 
				} 
			} // c != null. 

		}); 

		return i_result[gl.E_EMPTY]; 

	} 
	 
	public int getChildsCount(int id) 
	{ 
		Map<Integer,PanelXml> p_childs = new HashMap<Integer,PanelXml>(); 
	 
		return get_childs_native(id,p_childs); 
		 
	} 
	 
	 
	 
	 
	public boolean isaComponent(PanelXml panel,Component component) 
	{ 
		 
		boolean b_result = false; 

		Component[] co_list = panel.getComponents(); 

		for (Component c : co_list) { 
			 
			 
			if (c.getName().equalsIgnoreCase(component.getName())) { 
				b_result = true; 
			} 
		} 
				return b_result; 
	} 
	 
	public  boolean removeComponent(PanelXml panel,Component component) 
	{ 
		Component [] co_list = panel.getComponents(); 
		 
		for(Component c : co_list) 
		{ 
			if(c.getName().equalsIgnoreCase(component.getName())) 
			{ 
				panel.remove(c); 
			} 
		} 
		 
			return !isaComponent(panel,component); 
	} 

	 
	public Dimension getNormalizedAlign(int components_count,Dimension align) 
	{ 
		 
		Dimension align_result = new Dimension(align.width,align.height); 
		 
		int m_capacity_request = align.width * align.height; 
		 
		// gl.smn("---> components_count :  " + components_count + "...m_capacity_request : " + m_capacity_request); 
		 
		if(components_count > m_capacity_request) 
		{ 
			 
			// Calculate different. 
			 
			int m_difference =  (components_count - m_capacity_request); 
			 
			// How many additional rows we needs. 
			 
			int m_additional_rows_we_need = (m_difference / align.width) + 1; 
			 
			align_result.height = align_result.height + m_additional_rows_we_need; 
			 
			// gl.smn("---> " + m_difference + " " + m_additional_rows_we_need + " ... " + align_result.height); 
					 
		} 
		 
		 
			return align_result; 
		 
	} 
	 
	 
	 
	 
	public PanelXml getComponentById(PanelXml owner , int pid) 
	{ 
		 
		Component[] co_list = owner.getComponents(); 
		 
		for (Component c : co_list) { 
		 
			if(((PanelXml)c).getIdo().getId() == pid) 
			{ 
				return (PanelXml)c; 
			} 
		} 
		 
			return null; 
		 
	} 
	 
	 
	public boolean alignComponents(PanelXml panel,Dimension align) 
	{ 
		 
			String msg = "Align components"; 
			 
			List<PanelXml> comp_list = new ArrayList<PanelXml>(); 
		 
		try 
		{ 
			Component[] co_list = panel.getComponents(); 
	 
			for (Component c : co_list) { 
			 
				comp_list.add((PanelXml)c); 
			 
			} 
			 
			// Create a rectangle list. 
			 
			List<Rectangle> rect_list = new ArrayList<Rectangle>(); 
			 
			int m_gui_components_count = comp_list.size(); 
			 
			Dimension align_normalized = getNormalizedAlign(m_gui_components_count,align); 
			 
			gl.tracex(new Object() {} , gl.sf("%s...Input...%s...Normalized...%s",msg,SDimension.toString(align),SDimension.toString(align_normalized))); 
			 
			// Create area manager. 
			AreaManager am = new AreaManager(getDio().getDeskTop().getSize(),align_normalized); 
			 
			// We are used the same object size as while firstly initialization. 
			int i_x_cells = am.getAreaInCells().width; 
			 
			int i_y_cells = am.getAreaInCells().height; 
			 
			 
			// Cell sizing. 
			Rectangle cell_rect = getDio().getDeskTop().getDecoro().getAreaManager().get(gl.E_EMPTY); 
			 
			// Calculate new size for area manager by new requirements from align operation. 
			 
			Dimension  new_area = new Dimension(align_normalized.width*cell_rect.width,align_normalized.height*cell_rect.height); 
						 
			am.setArea(new_area); 
			 
			int size = (i_x_cells*i_y_cells); 
			 
			// Calculate shift on x. 
			 
			int i_shift_x_pre = (getDio().getDeskTop().getSize().width - am.getArea().width) / 2; 
			 
			int i_shift_x = gl.E_EMPTY; 
			 
			if(i_shift_x_pre > gl.E_EMPTY) 
			{ 
				i_shift_x = i_shift_x_pre; 
			} 
			 
			// Calculate shift on y. 
			 
			int i_shift_y_pre = (getDio().getDeskTop().getSize().height - am.getArea().height) / 2; 
						 
			int i_shift_y = gl.E_EMPTY; 
						 
			if(i_shift_y_pre > gl.E_EMPTY) 
			{ 
				i_shift_y = i_shift_y_pre; 
			} 
						 
			// Set sizing to the objects. 
			for(int i=0; i < size;i++) 
			{ 
				Rectangle rect = am.get(i); 
				 
				rect.x += i_shift_x; 
				 
				rect.y += i_shift_y; 
						 
				rect_list.add(rect); 
				 
			} 
			 
			//Reposition of component regarding new requirements. 
			 
			int [] index = {0}; 
					 
			comp_list.forEach(c-> 
			{ 
				c.setBounds(rect_list.get(index[0])); 
				 
				index[0]++; 
				 
			}); 
			 
			 
			 
			return true; 
		 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 

	public boolean alignComponents(PanelXml owner,Dimension align,boolean mode) 
	{ 
		 
			String msg = "Align components"; 
			 
			List<PanelXml> comp_list = new ArrayList<PanelXml>(); 
		 
		try 
		{ 
			Component[] co_list = owner.getComponents(); 
	 
			for (Component c : co_list) { 
			 
				comp_list.add((PanelXml)c); 
			 
			} 
			 
			// Create a rectangle list. 
			 
			List<Rectangle> rect_list = new ArrayList<Rectangle>(); 
			 
			int m_gui_components_count = comp_list.size(); 
			 
			Dimension align_normalized = getNormalizedAlign(m_gui_components_count,align); 
			 
			gl.tracex(new Object() {} , gl.sf("%s...Input...%s...Normalized...%s",msg,SDimension.toString(align),SDimension.toString(align_normalized))); 
			 
			// Create area manager. 
			AreaManager am = null; 
			 
			if (mode) 
				am = new AreaManager(Application.getFio().getFrame().getSize(),align_normalized); 
			else 
				am = new AreaManager(owner.getSize(),align_normalized); 
				 
			// We are used the same object size as while firstly initialization. 
			int i_x_cells = am.getAreaInCells().width; 
			 
			int i_y_cells = am.getAreaInCells().height; 
			 
			 
			// Cell sizing. 
			Rectangle cell_rect = getDio().getDeskTop().getDecoro().getAreaManager().get(gl.E_EMPTY); 
			 
			// Calculate new size for area manager by new requirements from align operation. 
			 
			Dimension  new_area = new Dimension(align_normalized.width*cell_rect.width,align_normalized.height*cell_rect.height); 
						 
			am.setArea(new_area); 
			 
			int size = (i_x_cells*i_y_cells); 
			 
			// Calculate shift on x. 
			 
			int i_shift_x_pre = (getDio().getDeskTop().getSize().width - am.getArea().width) / 2; 
			 
			int i_shift_x = gl.E_EMPTY; 
			 
			if(i_shift_x_pre > gl.E_EMPTY) 
			{ 
				i_shift_x = i_shift_x_pre; 
			} 
			 
			// Calculate shift on y. 
			 
			int i_shift_y_pre = (getDio().getDeskTop().getSize().height - am.getArea().height) / 2; 
						 
			int i_shift_y = gl.E_EMPTY; 
						 
			if(i_shift_y_pre > gl.E_EMPTY) 
			{ 
				i_shift_y = i_shift_y_pre; 
			} 
						 
			// Set sizing to the objects. 
			for(int i=0; i < size;i++) 
			{ 
				Rectangle rect = am.get(i); 
				 
				rect.x += i_shift_x; 
				 
				rect.y += i_shift_y; 
						 
				rect_list.add(rect); 
				 
			} 
			 
			//Reposition of component regarding new requirements. 
			 
			int [] index = {0}; 
					 
			comp_list.forEach(c-> 
			{ 
				c.setBounds(rect_list.get(index[0])); 
				 
				index[0]++; 
				 
			}); 
			 
			 
			 
			return true; 
		 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 

	 
	public  boolean refreshComponents(PanelXml panel) 
	{ 
		String msg = "Refresh GUI components"; 
		 
		try 
		{	 
			 
					PanelXml d = getDio().getDeskTop(); 
					 
					Component [] co_list = getDio().getDeskTop().getComponents(); 
					 
					d.setComponentZOrder(panel,gl.E_EMPTY); 
					 
					d.repaint(); 
					 
					d.revalidate(); 
					 
					panel.getIdo().setIndex(d.getComponentZOrder(panel)); 
								 
					int index = gl.E_OK; 
					 
			for(Component c : co_list) 
			{ 
				if(!c.getName().equalsIgnoreCase(panel.getName())) 
				{ 
					d.setComponentZOrder(c,index); 
					 
					((PanelXml)c).getIdo().setIndex(index); 
					 
					index++; 
				} 
			} 
			 	 
					d.repaint(); 
					 
					d.revalidate(); 
					 
					gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
					 
					return true; 
		} 
		 
		catch(Exception e) 
		{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage(),gl.S_ERROR)); 
			 
					return false; 
		} 
			 
	} 
	 
	public String showComponents(PanelXml panel) 
	{ 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		 
		Component[] co_list = panel.getComponents(); 

		for (Component c : co_list) { 
		 
			sb.append(c.toString()); 
			 
			sb.append(System.lineSeparator()); 
		 
		} 
				return sb.toString(); 
	} 
	 
	public int get_min_ex() 
	{ 
			 
		int min = 1000; 
			 
		for(Entry<Integer,PanelXml> e : items.entrySet()) 
		{ 
			//Initialization. 
			// Equaling. 
					 
				if (e.getValue().getIdo().getIndex() < min) 
				{ 
					min = e.getValue().getIdo().getIndex(); 
				} 
		} 
				return min; 
	} 
	 

	public int get_min(int pid) 
	{ 
		 
		int min = 1000; 
			 
		for(Entry<Integer,PanelXml> e : items.entrySet()) 
		{ 
			//Initialization. 
			// Equaling. 
					 
				if (e.getValue().getIdo().getIndex() < min && e.getValue().getIdo().getPid()==pid) 
				{ 
					min = e.getValue().getIdo().getIndex(); 
				} 
		} 
			return min; 
	} 

	 
	public int get_max() 
	{ 
		 
		int max = gl.E_ERROR; 
			 
		for(Entry<Integer,PanelXml> e : items.entrySet()) 
		{ 
			//Initialization. 
			if(max==gl.E_ERROR) 
				max = e.getKey(); 
			else 
			{ 
				// Equaling. 
				 
				if (e.getKey() > max) 
					max = e.getKey(); 
			} 
			 
		} 
			return max; 
	} 

	public int get_max(int pid) 
	{ 
		 
		int max = gl.E_ERROR; 
			 
		for(Entry<Integer,PanelXml> e : items.entrySet()) 
		{ 
			//Initialization. 
			if(max==gl.E_ERROR) 
				max = e.getKey(); 
			else 
			{ 
				// Equaling. 
				 
				if (e.getKey() > max && e.getValue().getIdo().getPid()==pid) 
					max = e.getKey(); 
			} 
			 
		} 
			return max; 
	} 
	 
	 public void setDxDy(MouseEvent e) 
	 { 
		 if( e == null) 
			return; 
			 
		 	 this.getDrago().setDx((e.getX() - this.getDrago().getStartPos().x)); 
		 	 
			 this.getDrago().setDy((e.getY() - this.getDrago().getStartPos().y)); 
		 
	 } 

	 
	public void childsResized(ComponentEvent e) 
	{ 
		 
		//String msg = gl.sf("%sCall childs resized...Owner...%s",gl.replicate('.',this.getIdo().getLevel()),this.getName()); 
		 
		//gl.smn(msg); 
		 
		 
		Component[] co_list = getDio().getDeskTop().getComponents(); 

		for (Component c : co_list) { 
			 
			PanelXml px = (PanelXml)c; 
			 
			if(	px.getIdo().getPid() == this.getIdo().getId()) 
			{ 
				px.componentResized(e); 
				 
				px.acceptArea(); 
			} 
			 
			 
			} 
	} 

	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
			String msg = "Component resized"; 
			 
			 
			 if (this.isBl_skip_child_accept()) 
			 { 
				 
				 return; 
			 } 
			 
			 	this.getDecoro().setSize(this.getSize()); 
		 	 	 
		 		this.childsResized(e);	 
		 		 
		} 
		 
	 

	@Override 
	public void componentMoved(ComponentEvent e) { 
		 
	} 

	@Override 
	public void componentShown(ComponentEvent e) { 
		 
	} 


	@Override 
	public void componentHidden(ComponentEvent e) { 
		 
	} 
	 
	public void translate(PanelXml obj, int dx, int dy) { 

		Rectangle 	b = obj.getBounds(); 

					b.translate(dx, dy); 

					obj.setBounds(b); 

	} 
	 
	public PanelXml getTarget() { 

		PanelXml m_result = null; 

		// Potentially targets. 
		List<PanelXml> targets = new ArrayList<PanelXml>(); 

		items.forEach((k, v) -> { 

			if (v.getIdo().getId() != this.getIdo().getId()) { 
				 
				Rectangle target = v.getBounds(); 

				if (target.contains(this.getBounds())) { 
					targets.add(v); 
				} 
			} 

		}); 

		List<PanelXml> 	t_list = targets 
			.stream() 
			.filter((b -> (b.getIdo().getId() != this.getIdo().getId()))) 
			.sorted(Comparator.comparing(s -> ((PanelXml) s).getIdo().getLevel()).reversed()) 
			.collect(Collectors.toList()); 
		 
		if (t_list != null && t_list.size() != gl.E_EMPTY) { 
			m_result = t_list.get(gl.E_EMPTY); 
		} 

		 
		if( m_result == null ) 
			m_result = getDio().getDeskTop(); 
		 
		return m_result; 

	} 

	 
	public void moveChilds(int id,int p_dx,int p_dy) { 

		this.get_childs_native(id).forEach(a-> 
		{ 
			Rectangle b = a.getBounds(); 

			b.translate(p_dx,p_dy); 
			 
			a.setBounds(b); 
			 
			a.moveChilds(a.getIdo().getId(),p_dx, p_dy); 
			 
		}); 
	} 

	 
	@Override 
	public void keyTyped(KeyEvent e) { 
		 
		 
	} 

	 
	public void cmd_add(char c) 
	{ 
		this.getCmd().append(c); 
	} 
	 
	public String prompt() 
	{ 
		String msg = gl.sf("%s",Fu.getCurrentDir()); 
		 
		return msg; 
	} 
	 
	 
	public boolean mgmt_key_event(KeyEvent e) 
	{ 
		String m_ch = gl.sf("%s",e.getKeyChar()); 
				 
		if ( Su.isAlpha(m_ch) || 
				Su.isNumber(m_ch) || 
					Su.isWhitespace(m_ch) || 
					m_ch.equalsIgnoreCase(".") || 
					m_ch.equalsIgnoreCase("/") || 
					m_ch.equalsIgnoreCase("-") || 
					e.getKeyCode() == KeyEvent.VK_BACK_SPACE 
			) 
				return false; 
		 
		 
		/* 
		gl.smn(gl.sf("Alpha...%s...Number...%s...WhiteSpace...%s", 
				StringUtil.isAlpha(m_ch), 
				StringUtil.isNumber(m_ch), 
				StringUtil.isWhitespace(m_ch) 
				)); 
		*/ 
		 
		int m_code = e.getKeyCode(); 
		 
		this.getKeys().put(m_code,true); 
		 
		 
		if(this.getKeys().containsKey(KeyEvent.VK_CONTROL) && 
					this.getKeys().containsKey(KeyEvent.VK_A) 
				) 
		{ 
			//Selection. 
			 
			return this.select_all_items(); 
			 
		}else if(this.getKeys().containsKey(KeyEvent.VK_CONTROL) && 
					this.getKeys().containsKey(KeyEvent.VK_D) 
				) 
		{ 
			// Delete. 
			return DeleteAction.getInstance().actionPerformed(this); 
			 
		} 
				 
		if(m_code == KeyEvent.VK_LEFT) 
			this.move_x_line(e,false); 
		else if(m_code == KeyEvent.VK_RIGHT) 
			this.move_x_line(e,true); 
		else if(m_code == KeyEvent.VK_UP) 
			this.move_y_line(e,false); 
		else if(m_code == KeyEvent.VK_DOWN) 
			this.move_y_line(e,true); 
		 
		return true; 
	} 
	 
		 
	public void cmd(KeyEvent e) 
	{ 
		int key_code = e.getKeyCode(); 

		if (this.isShowKeyCode()) 
			gl.smn("->" + key_code); 

		char ch = e.getKeyChar(); 
		 
		if ( mgmt_key_event(e)) 
			 return; 
				 
		String START = "."; 

		// Fire command. 
		if (key_code == KeyEvent.VK_ENTER) { 
		 
			String m_cmd_line = this.getCmd().toString().trim(); 
			 
			gl.smn(gl.sf("Command line...[%s]",m_cmd_line)); 
			 
			String m_response = Cmd.get_instance(this,m_cmd_line).get_state_message(); 
					 
			gl.smn(m_response); 
			 
			return; 
			 
		} 

		// BackSpace processing. 
		if (key_code == KeyEvent.VK_BACK_SPACE) { 
			int lg = this.getCmd().toString().length(); 

			if (lg > gl.E_OK) 
				this.getCmd().setLength(--lg); 

			gl.sm(System.lineSeparator()); 

			gl.sm(this.getCmd().toString()); 

		} else if (key_code == KeyEvent.VK_SHIFT) { 
			// Deny for Shift. 
		} else if (key_code == KeyEvent.VK_ALT) { 
			// Deny for Alt. 
		} else if (key_code == KeyEvent.VK_CONTROL) { 
			// Deny for Control. 
		} else if (key_code == KeyEvent.VK_UP) { 
			if (cmd_scroll > gl.E_EMPTY) 
				cmd_scroll--; 
			else 
				cmd_scroll = this.getCmds().size() - 1; 

			this.getCmd().setLength(gl.E_EMPTY); 

			this.getCmd().append( 
					this.getCmds().get(this.getCmd_scroll()).getCmd()); 

			gl.smn(this.getCmd().toString()); 

		} else if (key_code == KeyEvent.VK_DOWN) { 
			if (cmd_scroll < this.getCmds().size() - 1) 
				cmd_scroll++; 
			else 
				cmd_scroll = gl.E_EMPTY; 

			this.getCmd().setLength(gl.E_EMPTY); 

			this.getCmd().append( 
					this.getCmds().get(this.getCmd_scroll()).getCmd()); 

			gl.smn(this.getCmd().toString()); 

		} else if (key_code == KeyEvent.VK_LEFT) { 
			this.getCmd().setLength(gl.E_EMPTY); 

			this.getCmd().append(START); 

			gl.sm(this.getCmd().toString()); 
		} else if (key_code == KeyEvent.VK_RIGHT) { 
			this.getCmd().setLength(gl.E_EMPTY); 

			this.getCmd().append(START); 

			gl.sm(this.getCmd().toString()); 
		} else { 
			cmd_add(ch); 

			gl.sm(ch); 

		} 

	} 
	 
		 
	@Override 
	public void keyPressed(KeyEvent e) { 
		 
		cmd(e);		 
	} 
	 
	 

	@Override 
	public void keyReleased(KeyEvent e) { 
	 
		this.getKeys().remove(e.getKeyCode()); 
		 
		/* 
		gl.smn("(R)Key state..."); 
		 
		this.getKeys().forEach((k,v)-> 
		{ 
			gl.smn(gl.sf("%3d",k)); 
			 
		}); 
		*/ 
		 
	 
	} 

	public void move_x_line(KeyEvent e,boolean right) 
	{ 
		int m_dx = 0; 
		 
		int m_dy = 0; 
 
		int m_inc = 10; 
 
		if (e.isControlDown()) { 
			m_inc = 50; 
		} 
 
			m_dx = ((right)? 1 : -1) * m_inc; 
		 
			 
		int[] m_value = { m_dx }; 
 
		if (this.getSelected().size() != gl.E_EMPTY) { 
			this.getSelected().values().stream().collect(Collectors.toList()) 
					.forEach(a -> { 
						 
						a.moveByDxDy(m_value[0],gl.E_EMPTY); 
						 
					}); 
 
			return; 
		} 
 
		if (this.getIdo().getPid() == gl.E_ERROR ) { 
			// Desktop. 
			this.getDio().getDeskTop().moveChildsByDxDy(m_dx, m_dy); 
		} 
	} 
	 
	public void move_y_line(KeyEvent e,boolean down) 
	{ 
		int m_dx = 0; 
		 
		int m_dy = 0; 
 
		int m_inc = 10; 
 
		if (e.isControlDown()) { 
			m_inc = 50; 
		} 
 
			m_dy = ((down)? 1 : -1) * m_inc; 
		 
			 
		int[] m_value = { m_dy }; 
 
		if (this.getSelected().size() != gl.E_EMPTY) { 
			this.getSelected().values().stream().collect(Collectors.toList()) 
					.forEach(a -> { 
						 
						a.moveByDxDy(gl.E_EMPTY,m_value[0]); 
						 
					}); 
 
			return; 
		} 
 
		if (this.getIdo().getPid() == gl.E_ERROR ) { 
			// Desktop. 
			this.getDio().getDeskTop().moveChildsByDxDy(m_dx, m_dy); 
		} 
	} 
		 
	public void mouseWheelProcessing(MouseWheelEvent e) 
	{ 
				int dx = 0; 
		 
				int dy = 0; 
		 
				int amp = 10; 
		 
				if (e.isControlDown()) { 
					amp = 50; 
				} 
		 
				if (e.getWheelRotation() < 0) { 
					dy = -1 * amp; 
				} else { 
					dy = 1 * amp; 
				} 
		 
				int[] a_dy = { dy }; 
		 
				if (this.getSelected().size() != gl.E_EMPTY) { 
					this.getSelected().values().stream().collect(Collectors.toList()) 
							.forEach(a -> { 
								a.moveByDxDy(0, a_dy[0]); 
							}); 
		 
					return; 
				} 
		 
				if (this.getIdo().getPid() == gl.E_ERROR ) { 
					// Desktop. 
					this.getDio().getDeskTop().moveChildsByDxDy(dx, dy); 
				} 

	} 
	 
	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
	 
		//gl.smn(gl.sf("Class...%s...wheel event...%3d",this.getClass().getSimpleName(),gl.getRandomInt(999))); 
		 
		this.getDio().getDeskTop().mouseWheelProcessing(e); 
		 
	} 
	 
	public void bloat(Dimension dim) 
	{ 
		this.setBounds(Ru.Scale(this.getBounds(),dim)); 
	} 
	 
	public boolean acceptArea_new() { 

		String msg = "Accept area"; 
				 
		if (this.getDecoro().isBl_accept_area()) { 

			gl.tracex(new Object() {},gl.sf("%s...%s...d",msg,"already accepted area",this.getIdo().getId())); 
			 
			return true; 

		} 
		 
		AreaManager area_manager = this.getDecoro().getAreaManager(); 

		if (area_manager == null || area_manager.isEmpty()) { 

			gl.tracex(new Object() {}, gl.sf("%s...%s....id...%03d ", gl.S_OK,"Unmanaged area presents.", this.getIdo().getId())); 

			return true; 
		} 
		 
		if (this.getIdo().getOwner() == null	|| this.getIdo().getOwner().isDesktop()) 
		{ 
			gl.tracex(new Object() {}, gl.sf("%s...%s....id...%03d ", gl.S_OK,"No owner no problem.", this.getIdo().getId())); 

			return true; 
			 
		} 
		 
		// Push new parent area into child manager. 
		area_manager.setArea(this.getIdo().getOwner().getSize()); 
		 
		// Calculate new area distribution. 

		Rectangle  parent_rect 		= this.getIdo().getOwner().getBounds(); 

		Rectangle  client_area 		= this.getDecoro().getAreaManager().getBounds(); 

		Rectangle  shift 			= this.getDecoro().getAreaManager().getShiftParamRect(); 
		 
		Rectangle  area				= client_area.getBounds(); 

		 
		if (!this.getDecoro().isBl_accept_scale_area_initialization()) 
		{ 

			double wp = parent_rect.width; 

			double wc = client_area.width; 

			double hp = parent_rect.height; 

			double hc = client_area.height; 

			 
			this.getDecoro().setZoom_at_width(wc / wp); 

			this.getDecoro().setZoom_at_height(hc / hp); 
			 

			int dx = (client_area.x - parent_rect.x); 

			if (dx == gl.E_EMPTY) 
				dx = gl.E_OK; 

			this.getDecoro().setStart_delta_x(dx); 
			 

			int dy = (client_area.y - parent_rect.y); 

			if (dy == gl.E_EMPTY) 
				dy = gl.E_OK; 
			 
			this.getDecoro().setStart_delta_y(dy); 

			this.getDecoro().setZoom_at_y(this.getDecoro().getStart_delta_y() / parent_rect.height); 
			 
			this.getDecoro().setZoom_at_x(this.getDecoro().getStart_delta_y()/ parent_rect.width); 

			this.getDecoro().setBl_accept_scale_area_initialization(true); 
			 

		} // if this.isBl_accept_area_initialization() 

		if (area_manager.getDistributionType() == gl.ALI.NONE) { 

			/* 
			area.x += parent_rect.x; 

			area.y += parent_rect.y; 

			this.setBounds(area); 
			*/ 
			 
			// this.setBounds(IAlign.get_aligned_rect(getDecoro().getAlign(),area, parent_rect, client_area, shift,this.getDecoro())); 
			 
			this.setBounds(client_area); 

		} else if (area_manager.getDistributionType() == gl.ALI.FULL) { 

			Ru.clear(area); 

			area.x = parent_rect.x + shift.x; 

			area.y = parent_rect.y + shift.y; 

			area.width = parent_rect.width + shift.width; 

			area.height = parent_rect.height + shift.height; 

			this.setBounds(area); 

		} else if (area_manager.getDistributionType() == gl.ALI.CENTER) { 

			Ru.clear(area); 

			area.x = ((parent_rect.x + parent_rect.width / 2) - client_area.width / 2) 
					+ shift.x; 

			area.y = ((parent_rect.y + parent_rect.height / 2) - client_area.height / 2) 
					+ shift.y; 

			area.width = client_area.width + shift.width; 
			; 

			area.height = client_area.height + shift.height; 
			; 

			this.setBounds(area); 

		} 

		else if (area_manager.getDistributionType() == gl.ALI.TOP) { 

			//aliTop(area, parent_rect, client_area, shift); 

		} else if (area_manager.getDistributionType() == gl.ALI.TOP_SYNC) { 

			//aliTopSync(area, parent_rect, client_area, shift); 

		} else if (area_manager.getDistributionType() == gl.ALI.GLUED_TOP) { 

			//aliGluedTop(area, parent_rect, client_area, shift); 

		} else if (area_manager.getDistributionType() == gl.ALI.GLUED_BOTTOM) { 

			//aliGluedBottom(area, parent_rect, client_area, shift); 

		} else if (area_manager.getDistributionType() == gl.ALI.BOTTOM) { 

			//aliBottom(area, parent_rect, client_area, shift); 

		} else if (area_manager.getDistributionType() == gl.ALI.BOTTOM_SYNC) { 

			Ru.clear(area); 

			double d_height = parent_rect.height * this.getDecoro().getZoom_at_height(); 

			int i_height = (int) d_height; 

			area.x = parent_rect.x + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - i_height) 
					+ shift.y; 

			area.width = parent_rect.width + shift.width; 

			area.height = i_height; 

			this.setBounds(area); 

		} else if (area_manager.getDistributionType() == gl.ALI.BOTTOM_LEFT) { 

			Ru.clear(area); 

			area.x = parent_rect.x + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBounds(area); 

		} else if (area_manager.getDistributionType() == gl.ALI.BOTTOM_CENTER) { 

			Ru.clear(area); 

			area.x = (parent_rect.x + (parent_rect.width / 2)) 
					+ client_area.width / 2 + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBounds(area); 

		} else if (area_manager.getDistributionType() == gl.ALI.BOTTOM_RIGHT) { 

			Ru.clear(area); 

			area.x = ((parent_rect.x + parent_rect.width) - client_area.width) 
					+ shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBounds(area); 

		} 

		/* 
		else if (am.getDistributionType() == gl.ALI.LEFT) { 

			aliLeft(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.RIGHT) { 

			 
			aliRight(area, parent_rect, client_area, shift); 

		} 
		// Glued's section. 
		else if (am.getDistributionType() == gl.ALI.GLUED_LEFT) { 

			// 
			aliGluedLeft(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT 
		else if (am.getDistributionType() == gl.ALI.GLUED_LEFT_SYNC) { 

			aliGluedLeftSync(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT_SYNC 
		else if (am.getDistributionType() == gl.ALI.GLUED_RIGHT) { 

			// 
			aliGluedRight(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT 
		else if (am.getDistributionType() == gl.ALI.GLUED_RIGHT_SYNC) { 

			aliGluedRightSync(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_RIGHT_SYNC 

		*/ 
		 
		return true; 

	} 
	 
	 
		 
	public boolean acceptArea() { 

		String msg = "Accept area"; 
		 
		try 
		 
		{ 
			AreaManager am = this.getDecoro().getAreaManager(); 

			if (am == null || am.isEmpty()) { 

				//gl.tracex(new Object() {}, gl.sf("%s...%s....id...%03d ", gl.S_OK,"Unmanaged area presents.", this.getIdo().getId())); 

				return true; 
			} 
			 
			if (this.getIdo().getOwner() == null	|| this.getIdo().getOwner().isDesktop()) 
				return true; 
			 
			// Push new parent area into child manager. 
			am.setArea(this.getIdo().getOwner().getSize()); 
			 
			 
			// Calculate new area distribution. 
			Rectangle area = am.getBounds(); 
			 
			Rectangle parent_rect = this.getIdo().getOwner().getBounds(); 
			 
			area.x += parent_rect.x ; 
				 
			area.y += parent_rect.y; 
			 
				 
			this.setBounds(area); 
			 
						 
			gl.smn(gl.sf("%s...%d...%s", msg, this.getIdo().getId(), gl.S_OK)); 
				 
			return true; 
		} 
		catch(Exception e) 
		{ 
				gl.smn(gl.sf("%s...%d...%s",msg,this.getIdo().getId(),gl.S_ERROR)); 
			 
				return false; 
		} 
	} 
		 


		 
	public boolean acceptArea_useless_may_be() { 

		String msg = "Accept area"; 
		 
		try 
		 
		{ 
			 
			int m_align = this.getDecoro().getAlign(); 

			if (m_align == IAlign.indexOf("NONE")) { 

				gl.tracex(new Object() {}, gl.sf("%s...%s....id...%03d ", gl.S_OK,"Unmanaged area presents.", this.getIdo().getId())); 

				return true; 
			} 
			 
			if (this.getIdo().getOwner() == null	|| this.getIdo().getOwner().isDesktop()) 
				return true; 
			 
			Rectangle client_area = this.getBounds(); 

			Rectangle parent_area = this.getIdo().getOwner().getBounds(); 
			 
			Rectangle target_area = client_area.getBounds(); 			 
			 
			 
			if(m_align == IAlign.indexOf("LEFT")) 
			{ 
				 
				target_area.x = parent_area.x; 
				 
				target_area.y = parent_area.y; 
				 
				target_area.height = parent_area.height; 
				 
			} else if(m_align == IAlign.indexOf("RIGHT")) 
			{ 
				 
				target_area.x = (parent_area.x + parent_area.width) - client_area.width; 
				 
				target_area.y = parent_area.y; 
				 
				target_area.height = parent_area.height; 
			} 
				 
				this.setBounds(target_area); 
						 
				//gl.smn(gl.sf("%s...%s...set area...%s...%s", msg, this.getIdo().getName(),SBounds.toString(target_area),gl.S_OK)); 
				 
				return true; 
		} 
		catch(Exception e) 
		{ 
				gl.smn(gl.sf("%s...%d...%s",msg,this.getIdo().getId(),gl.S_ERROR)); 
			 
				return false; 
		} 
	} 

	public boolean reinvalidate() 
	{ 
		this.revalidate(); 
		 
		return true; 
	} 
	 
	public boolean rerepaint() 
	{ 
		this.repaint(); 
		 
		return true; 
	} 
	 
	public static PanelXml get_instance(Rectangle rect) 
	{ 
		return new PanelXml(rect); 
	} 
	 
	 
	public void repaint_childs() 
	{ 
		 
			this.get_childs_native(this.getIdo().getId()).forEach(ch->{ 

			ch.revalidate(); 
				 
			ch.repaint(); 
		 
	}); 
			 
	 
	 
	} 
	 
	 
	 
	 
} 
