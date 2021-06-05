 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.image.BufferedImage; 
import java.io.IOException; 
import java.io.Serializable; 
import java.util.HashSet; 
import java.util.Iterator; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import java.util.Vector; 

import javax.swing.JPanel; 
import javax.swing.JRootPane; 
import javax.swing.SwingUtilities; 



public class APanelj extends JPanel implements MouseListener,MouseMotionListener,ActionListener,Serializable,Actable{ 


	public static int MIN_WIDTH =5; 
	 
	public static int MIN_HEIGHT =5; 
	 
	public Rectangle captured_rect; 
	 
	public int drag_type = gl.E_ERROR; 
	 
	public static Rectangle RECTS[] = null; 
	 
	public static final int SENSITIVITY_WIDTH = 5; 
	 
	public static AForm aform = null; 
	 
	protected static HashSet<Actable> items = new HashSet<Actable>(); 
	 
	protected static HashSet<Actable> selected_items = new HashSet<Actable>(); 
	 
	protected static HashSet<Actable> deleted_items = new HashSet<Actable>(); 
	 
	private boolean link = false; 
	 
	public Rectangle parent_rect = null; 
	 
	public static String home = "panel_collector.xml"; 
	 
	private Map<Integer,AProperty> properties; 
	 
	private static final long serialVersionUID = 1L; 
	 
	public int action  = gl.ACTION_NONE; 
	 
	public int id = gl.E_ERROR; 
	 
	public int pid = gl.E_ERROR; 
	 
	public int index = gl.E_ERROR; 
	 
	public int target = gl.E_ERROR; 
	 
	public boolean captured = false; 
	 
	public Rectangle Area = new Rectangle(0,0,0,0); 
	 
	public Rectangle OriginArea = new Rectangle(0,0,0,0); 
	 
	public static Vector<APanel> list = null; 
	 
	public Point dragStart ; 
	 
	public Point mouseStart ; 
	 
	public String ToolTipHeader = " Index "; 
	 
	public APanelInfo api; 
	 
	public AreaManager areamanager; 
	 
	private boolean bl_v = false; 
	 
	private boolean bl_h = false; 
			 
	private boolean bl_scroll = false; 
	 
	private int dx = 0; 
	 
	private int dy = 0; 
	 
	private boolean gum = false; 
	 
	private boolean selected = false; 
	 
	private boolean deleted = false; 
	 
	private boolean bl_out_of_bounds = false; 
	 
	public AreaManagerEx area_manager; 
	 
	 
	 
	public boolean isLink() { 
		return link; 
	} 

	public void setLink(boolean link) { 
		this.link = link; 
	} 

	 
	public AreaManagerEx getArea_manager() { 
		return area_manager; 
	} 

	public void setArea_manager(AreaManagerEx area_manager) { 
		this.area_manager = area_manager; 
	} 

	public APanelj() 
	{ 
		properties = new LinkedHashMap(); 
		 
		this.setId(gl.getNextId()); 
		 
		addMouseListener(this); 
		 
		addMouseMotionListener(this); 
		 
		this.setBackground(Color.gray); 
		 
		 
	} 
	 
	public APanelj(AreaManager manager) 
	{ 

		this(); 
		 
		this.setLayout(null); 
		 
		this.setName("Atom"); 
		 
		this.setAreamanager(manager); 
		 
		this.setPlace(); 
		 
	} 
	 
	public APanelj(AreaManagerEx manager) 
	{ 

		this(); 
		 
		this.setLayout(null); 
		 
		this.setName("Atom"); 
		 
		this.setArea_manager(manager); 
		 
		 
		//gl.smn("Dtor(manager) chk bounds : " + manager.getBounds()); 
		 
		this.setBounds(manager.getBounds()); 
		 
		this.OriginArea = this.getBounds(); 
		 
	} 
	 
	public APanelj(AreaManagerEx manager,Color color) 
	{ 
		 this(manager); 
		 
		 this.setBackground(color); 
		 
		 //gl.smn("Dtor(manager,color) color  : " + color.toString()); 
		 
		 
	} 
	 
	public APanelj(int p_area_width,int p_area_hight,int p_area_cell_x, int p_area_cell_y , int p_object_size_in_cell_x,int p_object_size_in_cell_y,int p_object_start_index) 
	{ 
				 
		this(new AreaManager( 
				gl.getAligmentDimension(new Dimension(p_area_width,p_area_hight),new Dimension(p_area_cell_x,p_area_cell_y)), 
				new Dimension(p_area_cell_x,p_area_cell_y), 
				new AreaPointIndex( 
						new Dimension(p_object_size_in_cell_x,p_object_size_in_cell_y),p_object_start_index)) 
				); 
		 
	} 

	public APanelj(int p_area_width,int p_area_hight,int p_area_cell_x, int p_area_cell_y , int p_object_size_in_cell_x,int p_object_size_in_cell_y,int p_object_start_index,Color p_color) 
	{ 
		this(p_area_width,p_area_hight,p_area_cell_x,p_area_cell_y ,p_object_size_in_cell_x,p_object_size_in_cell_y,p_object_start_index); 
		 
		this.setBackground(p_color); 
				 
	} 
	 
	 
	public APanelj(AreaManager manager,Color color) 
	{ 
		this(manager); 
		 
		this.setBackground(color); 
		 
		 
	} 
	 
	 
	public boolean isOut_of_bounds() { 
		return bl_out_of_bounds; 
	} 

	public void setOut_of_bounds(boolean bl_bounds) { 
		this.bl_out_of_bounds = bl_bounds; 
	} 
	 
	 
	public boolean isDeleted() { 
		return deleted; 
	} 

	public void setDeleted(boolean deleted) { 
		this.deleted = deleted; 
	} 

	 
	public boolean isSelected() { 
		return selected; 
	} 

	public void setSelected(boolean selected) { 
		this.selected = selected; 
	} 
	 
	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public void setNoMove() 
	{ 
		this.setBl_h(true); 
		 
		this.setBl_v(true); 
	} 
	 
	 
	public boolean isBl_scroll() { 
		return bl_scroll; 
	} 

	public void setBl_scroll(boolean bl_scroll) { 
		this.bl_scroll = bl_scroll; 
	} 
	 

	public void setSizeEx(Rectangle rect) 
	{ 
		this.setSize(new Dimension(rect.width,rect.height)); 
		 
		this.setLocation(new Point(rect.x,rect.y)); 
	} 
	 
	public void setPlace() 
	{ 
		 
		int index = this.getAreamanager().getIndex().getC(); 
				 
		this.setSize(this.getAreamanager().get(index).getSize()); 
		 
		//gl.smn("Debug : x : " +  this.getSize().width  + " y : " + this.getSize().height); 
		 
		this.setLocation(this.getAreamanager().get(index).getLocation()); 
		 
		this.OriginArea = this.getBounds(); 
		 
	} 
	 

	public void setPlace(APanelInfo api) 
	{ 

		this.setSize(api.getSize()); 
		 
		this.setLocation(api.getLocation()); 
	} 

	 
	public void setPlace(Dimension size,Point location) 
	{ 
		this.setSize(size); 
		 
		this.setLocation(location); 
	} 
	 
	public int getDx() { 
		return dx; 
	} 

	public void setDx(int dx) { 
		this.dx = dx; 
	} 

	public int getDy() { 
		return dy; 
	} 

	public void setDy(int dy) { 
		this.dy = dy; 
	} 

	 
	public int getAction() { 
		return action; 
	} 

	public AreaManager getAreamanager() { 
		return areamanager; 
	} 

	public void setAreamanager(AreaManager areamanager) { 
		this.areamanager = areamanager; 
	} 
	 

	public void setAction(int action) { 
		this.action = action; 
	} 

	public boolean isBl_v() { 
		return bl_v; 
	} 

	public void setBl_v (boolean bl_v) { 
		this.bl_v = bl_v; 
	} 

	public boolean isBl_h() { 
		return bl_h; 
	} 

	public void setBl_h(boolean bl_h) { 
		this.bl_h = bl_h; 
	} 

	 
	public int getId() { 
		return id; 
	} 


	public void setId(int id) { 
		this.id = id; 
	} 


	public int getPid() { 
		return pid; 
	} 


	public void setPid(int pid) { 
		this.pid = pid; 
	} 


	public APanelInfo getApi() { 
		return api; 
	} 

	public void setApi(APanelInfo api) { 
		this.api = api; 
	} 
	 
	public void setProperties(APid apid,AId aid,ABounds abounds,ADeny aname , ADeny adeny_h,ADeny adeny_v,ADeny adeny_scroll,ADeny adeny_out_of_bounds,ADeny agum) 
	{ 
		 
		this.setPid(apid.getValue()); 
		 
		this.setId(aid.getValue()); 
		 
		if(abounds.getValue()) 
		{ 
			this.setBounds(abounds.getRect()); 
			 
			this.OriginArea = this.getBounds(); 
		} 
		 
		 
		if(adeny_h.getValue().equalsIgnoreCase("1") || 
				adeny_h.getValue().equalsIgnoreCase("true") 
				) 
			this.setBl_h(true); 
		else 
			this.setBl_h(false); 
			 
		 
		if(adeny_v.getValue().equalsIgnoreCase("1") || 
				adeny_v.getValue().equalsIgnoreCase("true") 
				) 
			this.setBl_v(true); 
		else 
			this.setBl_v(false); 
			 
		if(adeny_scroll.getValue().equalsIgnoreCase("1") || 
				adeny_scroll.getValue().equalsIgnoreCase("true") 
				) 
			this.setBl_scroll(true); 
		else 
			this.setBl_scroll(false); 
	 
		this.setName(aname.getValue()); 
		 
	 
		 
		if(adeny_out_of_bounds.getValue().equalsIgnoreCase("1") || 
		   adeny_out_of_bounds.getValue().equalsIgnoreCase("true") 
				) 
		{ 
			this.setOut_of_bounds(true); 
			 
			//gl.smn("Set out  true."); 
		} 
		else 
			this.setOut_of_bounds(false); 
	 
		 
		if(agum.getValue().equalsIgnoreCase("1") || 
				agum.getValue().equalsIgnoreCase("true") 
				) 
			this.setGum(true); 
		else 
			this.setGum(false); 
	 
			 
		 
	} 

	 
	public void mouseEntered(MouseEvent e) { 
		 
		 
	} 

	public void mouseExited(MouseEvent e) { 
		 
	} 
	public void mousePressed(MouseEvent e) { 
		 
	} 
	 
	public void mouseReleased(MouseEvent e) 
	{ 
		 
		OnMouseDraggedEnd(e); 
		 
		Rectangle r = this.getBounds(); 
		 
		APanelj ap = this.findParent(AForm.items,r); 
		 
		if(ap != null) 
		{ 
			// Full intro in 
			gl.smn("Component is landing to : " + ap.getId()); 
			 
			APanelj parent = ap; 
			 
			 
			parent.setComponentZOrder(this,gl.E_EMPTY); 
			 
			if(parent != null) 
			{ 
			 	 
						parent.add(this); 
						 
						Point point = new Point(Math.abs(this.getBounds().x - parent.getBounds().x),this.getBounds().y  - parent.getBounds().y); 
						 
						// Location correction 
						this.setLocation(point); 
				 
						gl.smn("Pid after add : " + this.getPid()); 
						 
						gl.smn("Point after add : " + point.toString()); 
								 
			} 
			 
			 
		} 
		else 
		{ 
			// Partially intro in 
			 
			r.width = 1; 
			 
			r.height = 1; 

			ap = this.findParent(AForm.items,r); 
			 
			if(ap != null) 
				gl.smn("Component is landing by LefTop on : " + ap.getId()); 
			 
			r = this.getBounds(); 
			 
			r.x += r.width; 
			 
			r.y += r.height + r.width; 
			 
			r.width = 1; 
			 
			r.height = 1; 

			ap = this.findParent(AForm.items,r); 
			 
			if(ap != null) 
				gl.smn("Component is landing by RightBottom on : " + ap.getId()); 
			 
			 
		} 
			 
		 
	} 
	 
	 
	public APanelj  findParent(int p_id) 
	{ 

		 
		 
			for (Actable a : items) { 
				APanelj b = (APanelj) a; 
				 
				if (b.getId() == p_id) { 
					return b; 
				} 

			} 

			return null; 
	} 
	 
	 
	public APanelj  findParent(HashSet<Actable> p_map,Rectangle p_rect) 
	{ 

		 
		 
			for (Actable a : p_map) { 
				APanelj b = (APanelj) a; 
				 
				Rectangle r = b.getBounds(); 

				if (r.contains(p_rect) && b.getId() != this.getId() ) { 
					return b; 
				} 

			} 

			return null; 
	} 
	 
	 
	public boolean  updatePid(int id,int pid) 
	{ 

		for (Actable a : items) { 
			APanelj b = (APanelj) a; 

			if (b.getId() == id) { 
				b.setPid(pid); 

				return true; 
			} 
		} 

		return false; 
	} 
	 
	public APanelj  findParentByAny(HashSet<Actable> p_map,Rectangle p_rect) 
	{ 

		 
		 
			for (Actable a : p_map) { 
				APanelj b = (APanelj) a; 
				 
				Point p = new Point(p_rect.x,p_rect.y); 
				 
				if (b.contains(p)) { 
					return b; 
				} 
					else 
					{ 
						gl.smn("Target :" + b.getBounds()); 
						 
						gl.smn("Source :" + p.toString()); 
						 
					} 
				} 

			 

			return null; 
	} 


	public void select() 
	{ 
		 
		selected_items.add(this); 
		 
		for(Actable a : items) 
		{ 
			APanelj b = (APanelj)a; 
			 
			if(b.getPid() == this.getId()) 
			{ 
				selected_items.add(b); 
				 
				b.select(); 
			} 
		} 

		 
	} 

	 
	public void Delete() 
	{ 

		Component t = this.getParent(); 
		 
		((APanelj)t).remove(this); 
		 
		 t.repaint(); 
	} 

	public void DeleteAll() 
	{ 

		Component t = this.getParent(); 
		 
		Component t_prev; 
		 
		/*((APanelj)t).remove(this); 
		 
		 t.repaint(); 
		 */ 
		while(t != null) 
		{ 
			gl.smn(" Parent component : " + t.getName()); 
			 
			t_prev = t; 
			 
			t = t.getParent(); 
			 
			if(t.getName().equalsIgnoreCase("Desktop")) 
			{ 
				((APanelj)t).remove(t_prev); 
				 
				gl.smn("Delete action in action"); 
				 
				t.repaint(); 
				 
				return ; 
			} 
				 
		} 
	} 

	 
	 
	public void mouseClicked(MouseEvent e) { 
	 
		if (this.getAction() == gl.ACTION_WM_CLOSE) 
		{ 
			gl.smn(" Closed action event."); 

			this.DeleteAll(); 
		} 
		 
		 
		if (SwingUtilities.isRightMouseButton(e)) 
		{ 
			gl.smn("mouseClicked(isRightMouseButton)"); 
			 
		} 
		else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			gl.smn("mouseClicked(isLeftMouseButton)"); 
			 
			if(this.getId() != gl.E_ERROR) 
			{ 
				//gl.smn("Clicked ..."); 
				 
				if(this.isSelected()) 
					this.setSelected(false); 
				else 
				{ 
					this.setSelected(true); 
				} 
			 
				//this.getSelectedComponents(true); 
				 
				this.select(); 
				 
				this.repaint(); 
			} 
			 
	} 
		 
	} 
	 
	public void OnMouseDraggedEnd(MouseEvent e) 
	{ 
		this.setCaptured(false); 
		 
		this.drag_type = gl.E_ERROR; 
		 
		if(this.isGum()) 
			this.setBounds(this.OriginArea); 
		 
	} 
	 
	public void OnMouseDraggedStart(MouseEvent e) 
	{ 
				 
		this.setCaptured(true); 
		 
		this.setFocusable(true); 
		 
		this.dragStart = this.getLocation(); 
		 
		this.mouseStart = e.getPoint(); 
		 
		Rectangle owner = this.getBounds(); 
		 
		owner.x = 0; 
		 
		owner.y = 0; 
		 
		//APanelj.RECTS = gl.getRectsforDragType(owner); 
		 
		drag_type = gl.getDragType(owner,this.mouseStart); 
		 
	 
		//gl.smn(gl.decodeDragType(drag_type)); 
				 
		//gl.smn("Owner rect : " + owner.toString()); 
		 
		//gl.smn("Mouse point : " + this.mouseStart.toString()); 
	 
		 
		this.setDx(gl.E_EMPTY); 
		 
		this.setDy(gl.E_EMPTY); 
		 
		parent_rect = ((APanelj)this.getParent()).getBounds(); 
		 
		parent_rect.x =	parent_rect.y = 0; 
		 
		APanelj parent = (APanelj)this.getParent(); 
		 
		if( parent != null ) 
		{ 
			// Reconnect from the owner 
			//this.getParent().setComponentZOrder(this,gl.E_EMPTY); 
			 
			 
			this.setPid(gl.E_ERROR); 
			 
			Point location = this.getLocation(); 

			if(parent.getId() != gl.E_ERROR) 
			{ 
				location.x += parent.getBounds().x ; 
				 
				location.y += parent.getBounds().y; 

				 
				if(drag_type == gl.DRAG_TYPE_BODY) 
				{ 
					this.setLocation(location); 
				 
					this.setOut_of_bounds(true); 
				} 
				else 
				{ 
				    	gl.smn("Drag type constraint activated(under parent) ..."); 
				    	 
				    	captured_rect = this.getBounds(); 
				    	 
				} 
				 
			} 
						 
			this.aform.addPure(this); 

			this.aform.setComponentZOrder(this, gl.E_EMPTY); 
			 
			 
		} 
		 

		 
		 
	} 
	 
	public void OnMouseDragged(MouseEvent e) { 
	{ 
		 
		int dx = (e.getX() - this.mouseStart.x) ; 
		 
		int dy = (e.getY() - this.mouseStart.y) ; 
		 
					 
		this.setDx(dx); 
		 
		this.setDy(dy); 

		 
		int result_x = this.getX(); 
		 
		int result_y = this.getY(); 
		 
		 
		if(!this.isBl_v()) 
		{ 
			result_y += dy; 
		} 
		 
		if(!this.isBl_h()) 
		{ 
			result_x += dx; 
		} 
		 
					 
		Point point =  new Point(result_x,result_y); 
			 
		Rectangle this_future_rect = new Rectangle(point.x,point.y,this.getWidth(),this.getHeight()); 
		 
			if (parent_rect.contains(this_future_rect) 
					|| this.isOut_of_bounds()) 
				if (drag_type == gl.DRAG_TYPE_BODY) { 
					this.setLocation(point); 
				} else { 

					 
					//Rectangle sized_rect = gl.getSizedRect(drag_type,captured_rect, dx, dy); 
					 
					//gl.smn("dx : " + dx + " dy: " + dy); 
					 
					Rectangle selection = new Rectangle(this.getX(),this.getY(),dx,dy); 
					 
					 
					if(ARect.isNeedNormalize(selection)) 
						selection = ARect.Normalize(selection); 
					 
					APanelj.aform.selection.setBounds(selection); 
										 
								 
					APanelj.aform.repaint(); 
					 
					//Rectangle sized_rect = gl.getSizedRectEx(drag_type,captured_rect,e.getPoint()); 
					 
					/* 
					if(ARect.isValidSize(sized_rect)) 
					{ 
						this.setLocation(sized_rect.getLocation()); 
						 
						this.setSize(sized_rect.getSize()); 
					} 
					*/ 
					//this.setBounds(sized_rect); 
					 
					 
				} 
		} 

	} 
	 
	public void mouseDragged(MouseEvent e) { 

		if(!this.isCaptured()) 
		{ 
			OnMouseDraggedStart(e); 
		} 
		 
			OnMouseDragged(e); 
		 
		 
		/* 
		if(!this.isCaptured()) 
		{ 
			this.dragStart = this.getLocation(); 
			 
			this.mouseStart = e.getPoint(); 
			 
			this.setCaptured(true); 
			 
		} 

		int dx = (e.getX() - this.mouseStart.x) ; 
		 
		int dy = (e.getY() - this.mouseStart.y) ; 
		 
					 
		this.setDx(dx); 
		 
		this.setDy(dy); 


		//	OnMouseDragged(e); 
		 
		Rectangle rect = this.getBounds(); 
		 
		rect.x += dx; 
		 
		rect.y += dy; 
		 
		this.setBounds(rect); 
		 
		*/ 
		 
	} 
	 
	public void mouseMoved(MouseEvent e) { 
		 
	} 

	public int isResizeMode(MouseEvent e) 
	{ 
		if(this.getBounds().contains(e.getPoint())) 
		{ 
			gl.smn("Pointed..."); 
		} 
		 
		return gl.E_EMPTY; 
	} 
	 
	public boolean isCaptured() { 
		return captured; 
	} 

	public void setCaptured(boolean captured) { 
		this.captured = captured; 
	} 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

	public void OnPaintIdText(Graphics g) 
	{ 

		gl.drawAlphaString((Graphics2D)g,""+this.getPid()+":"+this.getId(),this.getBounds().width/3,this.getBounds().height/2, Color.white,new Font("Courier New", Font.BOLD, 9), 0.75f); 
		 
	} 
	 
	public void OnPaintSizedRects(Graphics g) 
	{ 

		if (APanelj.RECTS == null) 
			return; 

		Color old_color = g.getColor(); 

		g.setColor(Color.BLUE); 

		int i = 0; 

		for (Rectangle r : APanelj.RECTS) { 
			/* 
			 * if(i==gl.DRAG_TYPE_LEFT_TOP || i==gl.DRAG_TYPE_RIGHT_TOP || i== 
			 * gl.DRAG_TYPE_RIGHT_BOTTOM || i== gl.DRAG_TYPE_BOTTOM_LEFT) 
			 */ 

			if (i == gl.DRAG_TYPE_LEFT || i == gl.DRAG_TYPE_RIGHT 
					|| i == gl.DRAG_TYPE_BOTTOM || i == gl.DRAG_TYPE_TOP) 
				g.drawRect(r.x, r.y, r.width, r.height); 

			if (i < 8) 
				i++; 
		} 

		g.setColor(old_color); 

		if (captured_rect != null) 
			return; 

	} 
	 
	 

	public void OnPaintSelected(Graphics g) 
	{ 
		 
			Color prev_color = g.getColor(); 
			 
			g.setColor(Color.white); 
			 
			Rectangle rect = this.getBounds(); 

			g.fillRect(0,0,5,5); 
			 
			g.setColor(prev_color); 
		 
	} 
	 
	public void paintComponent(Graphics g) 
	{ 
		if(isDeleted()) return ; 
		 
		super.paintComponent(g); 
		 
		if(this.isSelected()) 
			this.OnPaintSelected(g); 

	} 
	 
	public String getToolTipHeader() { 
		return ToolTipHeader; 
	} 


	public void setToolTipHeader(String toolTipHeader) { 
		ToolTipHeader = toolTipHeader; 
	} 

	 
	 
	public void sendMessage(int x , int y) 
	{ 
		int count = this.getComponentCount(); 
		 
		if(count == 0 ) return; 
			 
		for(int i=0;i < count;i++) 
		{ 
			Component component = this.getComponent(i); 
			 
			if(component instanceof APanelj) 
			{ 
				if(((APanelj)(component)).isBl_scroll()) 
				((APanelj)(component)).setLocation(((APanelj)(component)).getLocation().x + x,((APanelj)(component)).getLocation().y + y); 
			} 
		} 
	} 
	 
	public void add(APanelj...objs) 
	{ 
		for (int i=0;i<objs.length;i++) 
		//for( APanelj a : objs) 
		{ 
			add(objs[i]); 
		} 
	} 
	 
	 
	public boolean scanComponents(APanelCollector pc) 
	  { 
		  	 
		  for (Component child : ((Container)this).getComponents()) 
		  { 
			  if(child instanceof APanelj) 
			  { 
				 
				  pc.add((APanelj)child); 
				 
				  ((APanelj) child).scanComponents(pc); 
				 
			  } 
		  } 
				return true; 
		  		 
	  } 
	 
	public boolean add(APanelCollector store, APanelj value) 
	{ 
		super.add(value); 
		 
		return store.add(value); 
	} 
	 
	public boolean addIn(APanelCollector store) 
	{ 
		 
		return store.add(this); 
		 
	} 
	 
	public boolean isGum() { 
		return gum; 
	} 

	public void setGum(boolean gum) { 
		this.gum = gum; 
	} 

	public boolean gatherProps() 
	{ 
		int i = 0; 
		 
		properties.put(i++,new AId("pid",""+this.getPid())); 
		 
		properties.put(i++,new AId("id",""+this.getId())); 
		 
		properties.put(i++,new AId("color", 
				this.getBackground().getRed()+","+ 
			    this.getBackground().getGreen()+","+ 
			    this.getBackground().getBlue()) 
		); 
		 
		 
		properties.put(i++,new AId("bounds", 
								this.getBounds().x+","+ 
								this.getBounds().y+","+ 
								this.getBounds().width+","+ 
								this.getBounds().height)); 
		 
		properties.put(i++,new AId("area_manager",this.getArea_manager().toString())); 
				 
		properties.put(i++,new AId("class_name",this.getClass().getSimpleName())); 
		 
		properties.put(i++,new AId("name",this.getName())); 
		 
		properties.put(i++,new AId("deny_h",(this.isBl_h()?"1":"0"))); 
		 
		properties.put(i++,new AId("deny_v",(this.isBl_v()?"1":"0"))); 
		 
		properties.put(i++,new AId("scroll",(this.isBl_scroll()?"1":"0"))); 
		 
		properties.put(i++,new AId("deny_out_of_bounds",(this.isOut_of_bounds()?"1":"0"))); 
		 
		properties.put(i++,new AId("gum",(this.isGum()?"1":"0"))); 
				 

		return (properties.size() != gl.E_EMPTY); 
		 
	} 
	 
	public boolean write() 
	{ 
				 
if(isDeleted()) return true; 
 
if(!gatherProps()) 
		{ 
			gl.smn("gatherProps(FALSE)"); 
			 
			return false; 
		} 
		 
		 
		 
		try{ 
		 
		Iterator it = properties.entrySet().iterator(); 

		int size = properties.size(); 
		 
		String  acc = ""; 
		 
		String lineSeparator = System.getProperty("line.separator"); 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,AProperty> pair = (Map.Entry<Integer,AProperty>)it.next(); 
			 
			int key = pair.getKey(); 
			 
			AProperty ap = pair.getValue(); 
			 
			acc += ap.toString(); 
			 
		} 
			acc+=lineSeparator; 
		 
			gl.trace(this.getHome(),acc); 
			 
		} 
		catch(Exception ex) 
		{ 
			gl.smn("APanelj.write(FALSE)"); 
		} 
		 
			return true; 
	} 
	 
	 
	public void add(APanelj apj) 
	{ 
		super.add(apj); 
		 
		if(apj.getPid()==gl.E_ERROR) 
		   apj.setPid(this.getId());	 
	} 
	 
	public void addPure(APanelj apj) 
	{ 
		super.add(apj); 
	} 
	 
	 
	public static void main(String[] args) 
	{ 
		 
		AreaManagerEx am = new AreaManagerEx(121, 61, 3, 3, 3, 3, 0, 0); 

		APanelj root = new APanelj(am, Color.red); 

		root.write(); 

		AreaManagerEx am_left = new AreaManagerEx(am.get().width, 
				am.get().height, 10, 10, 1, 10, 0, 0); 

		APanelj child = new APanelj(am_left, gl.getRandomColor()); 

		root.add(child); 

		child.write(); 

		gl.smn("."); 

	} 

	@Override 
	public void actionPerformed(ActionEvent e) { 
		// REPLACE Auto-generated method stub 
		 
	} 

	@Override 
	public void act() { 
		// REPLACE Auto-generated method stub 
		 
	} 

	@Override 
	public boolean inRect(Rectangle rect) { 
		// REPLACE Auto-generated method stub 
		 
		if (this.getPid() != gl.E_ERROR) return false; 
		 
		return rect.contains(this.getBounds()); 
	} 
	 
	public static boolean loadComponents(APanelj owner) { 

	 
		gl.smn("<AForm.loadComponents()> Ready items : " + items.size()); 

		for (Actable a : items) { 
			APanelj b = (APanelj) a; 

			if (b.getPid() == gl.E_ERROR) 
				owner.addPure(b); 
			else { 

				APanelj p_apj = owner.findParent(b.getPid());//getObjectByIdSet(AForm.items, b.getPid()); 

				if (p_apj != null) 
					p_apj.add(b); 
			} 

		} 

		return true; 
	} 
 
	public static boolean updateComponents(APanelj owner) { 

		if(owner == null) 
			return false; 
		else 
			owner.removeAll(); 
		 
		int pid = owner.getId(); 
		 
		for (Actable a : items) { 
			APanelj b = (APanelj) a; 

			if (b.getPid() == pid ) 
			{ 
				owner.addPure(b); 
			} 
			 
		} 

		return true; 
	} 
	 
public boolean  getSelectedComponents(boolean debug) 
{ 

		selected_items.clear(); 

		for (Actable a : items) { 
			APanelj b = (APanelj) a; 

			if (b.getPid() == gl.E_ERROR) { 
				Rectangle rect = b.getBounds(); 

				if (AForm.selection_prev.contains(rect)) { 

					selected_items.add(b); 
					 
					for(Actable c : items) 
					{ 
						APanelj d = (APanelj) c; 
						 
						if(d.getPid()==b.getId()) 
							selected_items.add(d); 
							 
					} 

				} 

			} 
		} 

		if(debug) gl.smn("<getSelectedComponents()> " + selected_items.size()); 
			 
		return (selected_items.size() > 0); 
	} 

public boolean  setSelectedComponents() 
{ 
		 
		 
		for(Actable a : selected_items) 
		{ 
			APanelj b = (APanelj)a; 
			 
			if(!b.isSelected()) 
			b.setSelected(true); 
			else 
			b.setSelected(false); 
				 
			 
		} 
				 
			return true; 
	} 

public boolean  deleteSelectedComponents() 
{ 
	 		for (Actable a : selected_items) { 
			APanelj b = (APanelj) a; 

			if(b.isSelected()) 
			b.setDeleted(true); 
			 
			// delete childs items 
			for(Actable c : items) 
			{ 
				APanelj d = (APanelj) c; 
				 
				if(d.getPid()==b.getId()) 
					d.setDeleted(true); 
					 
			} 
		} 
			 
			return true; 
			 
			 
	} 


} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
