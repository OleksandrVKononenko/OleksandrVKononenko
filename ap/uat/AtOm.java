 
package ap.uat; 


import java.awt.Color; 
import java.awt.Component; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.LayoutManager; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener;
import java.io.Serializable;
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Comparator; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Vector; 
import java.util.stream.Collectors; 

import javax.swing.JPanel; 
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.prop.SBounds;
import ap.prop.SDimension;
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.swing.IAlign;
import ap.thread.Teurg;
import ap.uat.AtOm; 
import ap.uat.bean.XButtonA; 
import ap.uat.bean.XCheckBoxA; 
import ap.uat.bean.XComboBoxA; 
import ap.uat.bean.XLabelA; 
import ap.uat.bean.XRadioButtonA; 
import ap.uat.bean.XSpinnerA; 
import ap.uat.scrollbar.ScrBarX; 
import ap.uat.scrollbar.ScrBarY; 
import ap.utils.Biu;
import ap.utils.CU;
import ap.utils.DialogUtil;
import ap.utils.GU; 
import ap.utils.Su; 



public class AtOm extends JPanel  implements 
MouseListener, 
MouseMotionListener, 
ComponentListener, 
KeyListener, 
MouseWheelListener,
Serializable { 

	 
	private  		DragableImplObjectA  		drago; 
	 
	private  		IdentityImplObjectA        	ido; 
	 
	private  		StateImplObject        		stio; 
	 
	private  		SelectionImplObject     	slio; 
	 
	private  		DecorateImplObjectA    		decoro; 
	 
	private  		CmdImplObject	    		cmio; 
	 
	private  		ImageImplObjectA  			imgo; 
	 
	private			List<AtOm>					childs = new ArrayList<AtOm>(); 
	 
	//private			Map<Integer,Rectangle>		shadows_map ; 
	 
	private			Rectangle					shift_rect; 
	 
	private			Dimension					shift_dim; 
	 
	public			static 	List<AtOm>			items = new ArrayList<AtOm>(); 
	
	private			Rectangle					abs_bounds;
	 
	private Map<Integer,Boolean> 				keys = new LinkedHashMap<Integer,Boolean> (); 
	 
	private StringBuilder 						cmd 		= new StringBuilder(); 
	 
	private CmdA 								fire_cmd  ; 
	 	 
	private int 								cmd_scroll 	= gl.E_EMPTY; 
	 
	private  List<CmdA> 						cmds	 		= new ArrayList<CmdA>(); 
	 
	private	long call_back_counter;
	
	private int opaque;
	
	private int sign = 1;
	
	private Teurg grok  ;
	
	
	public boolean prev_mode = false;
	
	public boolean mode600 = false;
	
	public int counter = gl.E_EMPTY;
	
	public Rectangle getAbs_bounds() {
	

		List<Integer> 	m_x_acc = new ArrayList<Integer>();
		
		List<Integer> 	m_y_acc = new ArrayList<Integer>();
		
						this.get_root_x(m_x_acc);
				
						this.get_root_y(m_y_acc);
				
		int 			m_sum_x_acc = m_x_acc.stream().mapToInt(i->i).sum();
				
		int 			m_sum_y_acc = m_y_acc.stream().mapToInt(i->i).sum();
		
		if(this.getIdo().getOwner() != null && this.getIdo().getOwner().is_desk_top())
		   return this.getBounds();
		
		   return new Rectangle(m_sum_x_acc+this.getBounds().x,m_sum_y_acc+this.getBounds().y,this.getBounds().width,this.getBounds().height);
	
	}

	

	public Teurg getGrok() {
		return grok;
	}

	public void setGrok(Teurg grok) {
		this.grok = grok;
	}
	
	
	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public int getOpaque() {
		return opaque;
	}

	public void setOpaque(int opaque) {
		this.opaque = opaque;
	}

	 
	 
	public long getCall_back_counter() {
		return call_back_counter;
	}

	public void setCall_back_counter(long call_back_counter) {
		this.call_back_counter = call_back_counter;
	}

	public ImageImplObjectA getImgo() { 
		return imgo; 
	} 

	public void setImgo(ImageImplObjectA imgo) { 
		this.imgo = imgo; 
	} 

	public List<CmdA> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<CmdA> cmds) { 
		this.cmds = cmds; 
	} 

	public StringBuilder getCmd() { 
		return cmd; 
	} 

	public void setCmd(StringBuilder cmd) { 
		this.cmd = cmd; 
	} 

	public CmdA getFire_cmd() { 
		return fire_cmd; 
	} 

	public void setFire_cmd(CmdA fire_cmd) { 
		this.fire_cmd = fire_cmd; 
	} 

	public int getCmd_scroll() { 
		return cmd_scroll; 
	} 

	public void setCmd_scroll(int cmd_scroll) { 
		this.cmd_scroll = cmd_scroll; 
	} 

	public Map<Integer, Boolean> getKeys() { 
		return keys; 
	} 

	public void setKeys(Map<Integer, Boolean> keys) { 
		this.keys = keys; 
	} 
	 
	 
	public static List<AtOm> get_items() { 
		return items; 
	} 

	public static void set_items(List<AtOm> items) { 
		AtOm.items = items; 
	} 

	public Dimension getShift_dim() { 
		return shift_dim; 
	} 

	public void setShift_dim(Dimension shift_dim) { 
		this.shift_dim = shift_dim; 
	} 

	public Rectangle getShift_rect() { 
		return shift_rect; 
	} 

	public void setShift_rect(Rectangle shift_rect) { 
		this.shift_rect = shift_rect; 
	} 


	private int offset; 
	 
	public int get_off_set() { 
		return offset; 
	} 

	public void set_off_set(int offset) { 
		this.offset = offset; 
	} 

	 
	 
	 
	public List<AtOm> get_childs() { 
		return childs; 
	} 

	public void set_childs(List<AtOm> childs) { 
		this.childs = childs; 
	} 

	public CmdImplObject getCmio() { 
		return cmio; 
	} 

	public void setCmio(CmdImplObject cmio) { 
		this.cmio = cmio; 
	} 

	public DecorateImplObjectA getDecoro() { 
		return decoro; 
	} 

	public void setDecoro(DecorateImplObjectA decoro) { 
		this.decoro = decoro; 
	} 

	public SelectionImplObject getSlio() { 
		return slio; 
	} 

	public void setSlio(SelectionImplObject slio) { 
		this.slio = slio; 
	} 

	public StateImplObject getStio() { 
		return stio; 
	} 

	public void setStio(StateImplObject stio) { 
		this.stio = stio; 
	} 

	public IdentityImplObjectA getIdo() { 
		return ido; 
	} 

	public void setIdo(IdentityImplObjectA ido) { 
		this.ido = ido; 
	} 

	public DragableImplObjectA getDrago() { 
		return drago; 
	} 

	public void setDrago(DragableImplObjectA drago) { 
		this.drago = drago; 
	} 

	public AtOm() { 
		 
		super();
		
		this.setup_listeners(); 
		 
		this.setup_impls(); 
		 
		this.setup_default_attrs(); 
	} 
	 
	public AtOm (Rectangle rect) 
	{ 
		this(); 
		 
		this.setBounds(rect); 
		 
		this.getDecoro().setBounds(this.getBounds()); 
			 
		this.getDecoro().setOrigin_bounds(this.getBounds()); 
		 
		this.getDecoro().setZoom_width(gl.E_OK); 
		 
		this.getDecoro().setZoom_height(gl.E_OK); 
		 
	} 

	public AtOm(LayoutManager layout) { 
		super(layout); 
		 
	} 

	public AtOm(boolean isDoubleBuffered) { 
		super(isDoubleBuffered); 
		 
	} 

	public AtOm(LayoutManager layout, boolean isDoubleBuffered) { 
		super(layout, isDoubleBuffered); 
		 
	} 

	public static void main(String[] args) { 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				//create_desktop(); 
			} 
		}); 

	} 
	 
	/* 
	public static void create_desktop() { 

		JFrame frame = new JFrame("Test application"); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 
		frame.addKeyListener(new KeyAdapter() { 
			public void keyPressed(KeyEvent e) { 
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { 
					System.exit(0); 
				} 

			} 
		}); 

		 
		// Desktop. 

		AtOm  	desktop = AtOm.get_instance(); 
		 
				desktop.setBackground(Color.darkGray); 
		 
				desktop.setLayout(null); 
				 
						 
		AtOm 	atom = AtOm.get_instance(new Rectangle(20,20,200,200)); 
		 
				atom.setBackground(Color.blue); 
				 
				atom.getStio().set_deny_drag(false); 
				 
				atom.frame = frame; 
				 
				desktop.add(atom); 
				 
				 
				JScrollPane sp = new JScrollPane(); 
			 
				sp.setViewportView(desktop); 
	 
			 
				 
				frame.getContentPane().setLayout(new BorderLayout()); 
				 
				frame.getContentPane().add(sp,BorderLayout.CENTER); 
				 
				frame.setPreferredSize(new Dimension(320,240)); 
				 
				 
				frame.pack(); 

				frame.setLocationRelativeTo(null); 

				frame.setVisible(true); 
		 
	} 
	*/ 
	 
	 
	public static AtOm get_instance() 
	{ 
		 	return new AtOm(); 
	} 

	public static AtOm get_instance(Rectangle rect) 
	{ 
	 
		AtOm 	atom = new AtOm(rect); 
	 
				atom.getStio().set_deny_drag(false); 
				 
				atom.getStio().set_move_bottom_right(true); 
				 
		return 	atom; 
	 
	} 
	 
	 
	public static AtOm get_instance(Rectangle rect,int align) 
	{ 
	 
		AtOm 	atom = AtOm.get_instance(rect); 
	 
				atom.getDecoro().set_align(align); 
				 
				//atom.getStio().set_deny_drag(true);
		 
		 
		return atom; 
	 
	} 
	 
	 
	public void setup_default_attrs() 
	{ 
		this.getIdo().setId(IDGen.nextId()); 
		 
		this.getIdo().setPid(gl.E_ERROR); 
		 
		 
		this.setDoubleBuffered(true); 
		 
		this.setName(gl.sf("Panel_%d",this.getIdo().getId())); 
		 
		this.getIdo().setName(this.getName()); 
				 
		 
		this.getDecoro().setTextAlign(new Rectangle(1,1,1,1)); 
		 
		this.getDecoro().set_text(gl.sf("%s",this.getName())); 
		 
		this.getDecoro().setTextColor(Color.white); 
		 
		this.getDecoro().setGrid(new Dimension(3,3));
		
		
		this.setBackground(gl.getRandomColor()); 
		
		this.setLayout(null); 
		 
		
		this.getStio().set_visible(true); 
		
		this.getStio().set_skip_text(true);
		 
		List<AtOm> p_list = new ArrayList<AtOm>();
		
		this.set_childs(p_list); 
		 
		//this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
		 
		//this.setShadows_map(new LinkedHashMap<Integer,Rectangle>()); 
		 
		 
		this.setShift_rect(null); 
		 
		this.setShift_dim(null); 
		 
		this.setCmd(new StringBuilder()); 
		
		List<CmdA> p_cmd_list = new ArrayList<CmdA>();
		
		this.setCmds(p_cmd_list); 
				
		if (this.is_desk_top())
		{
			
			//gl.smn("ToolTip initialization.");
			
			ToolTipManager.sharedInstance().setInitialDelay(100);
			
	        ToolTipManager.sharedInstance().setDismissDelay(3000);
			
	        //ToolTipManager.sharedInstance().setEnabled(false);
		}
		
			 
	} 
	 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addMouseWheelListener(this); 
		 
		this.addComponentListener(this); 
		 
		this.addKeyListener(this); 
		
		/*
		this.addMouseListener ( new MouseAdapter ()
		{
		    public void mousePressed ( MouseEvent e )
		    {
		        ApplicationA.getDio().get_desk_top().dispatchEvent ( SwingUtilities.convertMouseEvent ( e.getComponent (), e, ApplicationA.getDio().get_desk_top() ) );
		    }
		} );
		 
		*/
	} 
	 
	public void setup_impls() 
	{ 
		 
		this.setDrago(DragableImplObjectA.get_instance()); 
		 
		this.setIdo(IdentityImplObjectA.get_instance()); 
		 
		this.setStio(StateImplObject.get_instance()); 
		 
		this.setSlio(SelectionImplObject.get_instance()); 
		 
		this.setDecoro(DecorateImplObjectA.get_instance()); 
		 
		this.setCmio(CmdImplObject.get_instance()); 
		 
		this.setImgo(ImageImplObjectA.get_instance()); 

	} 

		 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		
		//gl.sfn("Mouse dragged over...%s", this.getName()); 
		 
		if(!this.getStio().is_visible()) 
		{ 
			gl.tracex(new Object(){},gl.sf("Not visible...%s...%s",this.getName(),gl.S_WARN)); 
			 
			return; 
		} 
	 				 
		 
		// Init start position. 
		
		if(this.getDrago().getStartPos() == null) 
			   this.getDrago().setStartPos(e.getPoint()); 
		
		
		if(this.getStio().is_deny_drag())
		{
			AtOm owner = this.getIdo().getOwner() ;
			
			if(owner != null && !owner.is_desk_top())
			{
			
				if(owner.getDrago().getStartPos() == null)
				{
					owner.getDrago().setStartPos(e.getPoint());
				}
			}
			
			
		}
		
		
		set_dx_dy(e); 
		 
		//Stay on Top. 
		if(!this.isa_top()) 
			pull_on_top(); 
		 
		if (!this.getStio().is_deny_drag()) 
		{ 
			if(!drag_by_body()) 
			{ 
				drag_by_other(e); 
			} 
		} 
		else 
		{ 
			
			if(this.getStio().is_deny_inside_selection())
			{
				AtOm owner = this.getIdo().getOwner() ;
						
				if(owner != null && !owner.is_desk_top())
				{
					owner.mouseDragged(e);
					
					return;
				}
				
			}
			else
			{
				// Create new object on surface.
				ISelection.update_selector(this,e);
			}
			 
		 }
			
					 
	} 
	 
	@Override 
	public void paintComponent(Graphics g) { 

		if(!this.getStio().is_visible()) 
			return; 
		 
			 
			paintBody(g); 
		 	 
		 	paintImage(g); 
		 		 
		 	paintTextHelper(g); 
		 	 
		 	paintSelector(g); 
		 	 
		 	paintSelected(g); 
		 	
		 	paintGrid(g);
		 	 
				 
	} 
	 
	public void paintSelected(Graphics g) { 
		 
		if(!this.getStio().is_selected()) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(gl.dashed_stroke_thin); 
		 
		GU.drawAlphaRect(g2,Ru.norm4g(this.getBounds()),Color.white); 
		 
		g2.setStroke(p_stroke); 
		 

	} 

	 
	public void paintImage(Graphics g) { 

		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		if (this.getImgo().getImage() != null) { 
			GU.drawImage(g2, this.getImgo().getImage(), wr); 
		} 
	} 
	 
	public void paintSelector(Graphics g) { 
		 
		//if(this.getStio().is_move_inside()) 
		//	return; 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(gl.dashed_stroke_thin); 
		 
		Rectangle span = Ru.get_spanned_rect(this.getSlio().get_selector(),new Insets(0,0,0,0)); 
				 
		GU.drawAlphaRect(g2,span,Color.white); 
		 
		GU.fillAlphaRect(g2,span,Color.white,0.1f); 
		 
		g2.setStroke(p_stroke); 

	} 
	 
	public void paintBody(Graphics g) { 
		 
		Graphics2D g2 = (Graphics2D)g; 

		Rectangle wr = new Rectangle(0,0,this.getBounds().width,this.getBounds().height);
		
		GU.fillAlphaRect(g2,wr,this.getBackground());
		
	} 
	 
 
	public boolean drag_by_body() 
	{ 
		//if ( this.getStio().is_deny_drag()) 
		//		   return true; 
		 
		if (this.getDrago().getDragType() != Cursor.DEFAULT_CURSOR) 
			return false; 
		 
			this.getStio().set_drag(true); 
		 
			int m_dx = this.getDrago().getDx(); 
			 
			int m_dy = this.getDrago().getDy(); 
			 
			if(this.getStio().is_deny_x()) 
				m_dx = gl.E_EMPTY; 
			 
			if(this.getStio().is_deny_y()) 
				m_dy = gl.E_EMPTY; 
			 
			this.move_byself_dx_dy(m_dx,m_dy); 
			 
			 
			int [] m_dx_ = {m_dx}; 
			 
			int [] m_dy_ = {m_dy}; 
			 
			StoreImplObject.get_selected().forEach(a-> 
			{ 
				if(a.getIdo().getId() != this.getIdo().getId()) 
				a.move_dx_dy(m_dx_[0], m_dy_[0]); 
			}); 
			 
			return true; 

	} 
	 
	public void  update_skip_resized() 
	{ 
		this.get_items_by_state(Fl.VK_SKIP_RESIZING).forEach(a-> 
		{ 
			a.getStio().set_skip_resizing(false); 
		}); 
	} 
	 
	public void  update_shifts() 
	{ 
		 
		//gl.sfn("Update shifts...%s",this.getIdo().getName());
		
		this.update_childs(); 
		 
		this.setShift_rect(null); 
		 
		this.setShift_dim(null); 
		 
			this.get_childs().forEach(a-> 
			{ 
				a.setShift_rect(null); 
				 
				a.setShift_dim(null); 
				 
				a.update_shifts(); 
			} 
		); 
	} 
	 
	public void update_selected_clear() 
	{ 
		
		gl.tracex(new Object() {},gl.sf("Event owner...%s",this.getIdo().getName()));
		
		List<AtOm> sl = StoreImplObject.get_selected(); 
		 
		if(sl.size() != gl.E_EMPTY) 
		{ 
			sl.forEach(a-> 
			{ 
				a.getStio().set_selected(false); 
				 
				a.repaint(); 
			}); 
			 
				sl.clear(); 
			 
		} 
		 
	} 
	
	public void update_selection_gather() 
	{ 
		
			StoreImplObject.get_selected().clear();
		
			StoreImplObject.get_selected().addAll(this.get_selected_items());
				
	}

	public void update_selected_gather()
	{
		
		if(ApplicationA.getDio() == null)
			return;
		
		StoreImplObject.get_selected().clear();
		
		StoreImplObject.get_selected().addAll(ApplicationA.getDio().get_desk_top().get_selected_items());

		/*
		StoreImplObject.get_selected().forEach(a->
		{
			gl.tracex(new Object() {},gl.sf("...Selected items...%s",a.getIdo().getName()));
		});
		
		*/
		
	}




	 
	public List<AtOm> update_selected_gather_rect(AtOm owner,Rectangle rc) 
	{ 
		 
		List<AtOm> l = new ArrayList<AtOm>(); 
		 
		owner.get_all_childs().forEach(a->{ 
		 
			if(rc.contains(a.getDecoro().getBounds())) 
			{ 
					 
						a.getStio().set_selected(true); 
					 
						l.add(a); 
			} 
			else 
			{ 
						a.getStio().set_selected(false); 
				 
			} 
			
					
		 
		}); 
	 
				return l; 
	} 
	 
	public void  update_items() 
	{ 
		 
		this.update_childs(); 
		 
		AtOm.get_items().addAll(this.get_childs()); 
		 
			this.get_childs().forEach(a-> 
			{ 
				a.update_items(); 
			} 
		); 
			 
	} 
	 
	public List<AtOm> get_all_childs() 
	{ 
		 
		AtOm.get_items().clear(); 
		 
		this.update_items(); 
		 
		return  AtOm.get_items(); 
		 
	} 
	 

	public List<AtOm> get_selected_items() 
	{ 
		return this.get_items_by_state(Fl.VK_SELECTED); 
	} 
	 
	public List<AtOm> get_moved_items_() 
	{ 
		return this.get_all_childs().stream().filter(b->( b.getStio().is_skip_moved() == false)).collect(Collectors.toList()); 
	} 
	
	public List<AtOm> get_moved_items() 
	{ 
		return this.get_all_childs().stream().filter(b->( b.getStio().is_skip_moved() == false && b.getIdo().getLevel() == this.getIdo().getLevel()+1)).collect(Collectors.toList()); 
	} 
	
	
	public List<AtOm> get_items_by_state(int state) 
	{ 
		return this.get_all_childs().stream().filter(b->( Biu.ISA(b.getStio().get_state(),state))).collect(Collectors.toList()); 
	} 
	 
	public static List<AtOm> get_all_items_etalon() 
	{ 
		if(
		ApplicationA.getDio() == null )
			return null;
		
		
		AtOm.get_items().clear(); 
		 
		ApplicationA.getDio().get_desk_top().update_items(); 
		 
		return AtOm.get_items(); 
	} 
	
	
	public  List<AtOm> get_all_items() 
	{ 
		if(ApplicationA.getDio() == null )
		   return null;
		
		AtOm.get_items().clear(); 
		 
		ApplicationA.getDio().get_desk_top().update_items(); 
		
		return AtOm.get_items(); 
	} 
	 
	
	
	
	public  List<AtOm> get_all_items_beta() 
	{ 
		AtOm desktop = this.get_root();
		
		if(desktop == null)
		{
			gl.tracex(new Object() {},gl.sf("No desktop found...%s",gl.S_ERROR));
			
			return null;
			
		}
		
		
		//AtOm.get_items().clear(); 
		 
		//desktop.update_items(); 
		 
		//return AtOm.get_items(); 
		
		
		return desktop.get_all_childs();
	} 
	
	
	
	 
	public AtOm get_scroll_bar_x() 
	{ 
		return this.get_all_childs().stream().filter(b->(b.getClass().getSimpleName().startsWith("ScrBarX"))).findFirst().orElse(null); 
	} 
	 
	public AtOm get_scroll_bar_y() 
	{ 
		return this.get_all_childs().stream().filter(b->(b.getClass().getSimpleName().startsWith("ScrBarY"))).findFirst().orElse(null); 
	} 
	 
	public AtOm get_root() 
	{ 
		AtOm owner = this.getIdo().getOwner(); 
		 
		if( owner == null || owner.is_desk_top()) 
			return this; 
		 
			return owner.get_root(); 
	} 
	 
	public void get_root_x(List<Integer> x_acc) 
	{ 
		AtOm owner = this.getIdo().getOwner(); 
		
		if( owner == null || owner.is_desk_top()) 
			return ; 
		 
		 x_acc.add(owner.getBounds().x);
		 
		 		
		 owner.get_root_x(x_acc); 
	}
	
	public void get_root_y(List<Integer> y_acc) 
	{ 
		AtOm owner = this.getIdo().getOwner(); 
		 
		if( owner == null || owner.is_desk_top()) 
			return ; 
		 
		 y_acc.add(owner.getBounds().y);
		
		 owner.get_root_y(y_acc); 
	} 
	
	
	
	 
	public List<AtOm> get_components() 
	{ 
		Component [] cos = this.getComponents(); 
		 
		List<AtOm>  atoms = new ArrayList<AtOm>(); 
		 
		for(Component co : cos) 
		{ 
			if( co instanceof AtOm) 
			{ 
				atoms.add((AtOm)co); 
			}
		} 
	
			return 
					atoms.stream() 
					.sorted(Comparator.comparing(s -> ((AtOm)s).getIdo().getIndex()).reversed()) 
					.collect(Collectors.toList()); 
	} 
	 
		 
	 
	public AtOm get_component_by_class_name(String clazz) 
	{ 
		return this.get_components().stream().filter(b->(b.getClass().getSimpleName().equalsIgnoreCase(clazz))).findFirst().orElse(null); 
		 
	} 
	 
	public AtOm get_component_by_name(String name) 
	{ 
		return this.get_components().stream().filter(b->(b.getName().equalsIgnoreCase(name))).findFirst().orElse(null); 
		 
	} 
	 
	public List<AtOm> get_components_by_name_start_with(String name) 
	{ 
		return this.get_components().stream().filter(b->(b.get_class_name().startsWith(name))).collect(Collectors.toList()); 
		 
	} 
	 
	public boolean childs_in_bounds_y_max() 
	{ 
		return (this.get_components().stream().filter(b->((b.getBounds().y + b.getBounds().height) > this.getBounds().height)).findFirst().orElse(null) == null); 
	} 

	 
	public boolean childs_in_bounds_x_max() 
	{ 
		return (this.get_components().stream().filter(b->((b.getBounds().x + b.getBounds().width) > this.getBounds().width  && b.getName().startsWith("Acton"))).findFirst().orElse(null) == null); 
	} 

	public boolean childs_in_bounds_x_min() 
	{ 
		return (this.get_components().stream().filter(b->(b.getBounds().x < gl.E_EMPTY && b.getName().startsWith("Acton"))).findFirst().orElse(null) == null); 
	} 
	 
	public boolean childs_in_bounds_x() 
	{ 
		return (this.get_components().stream().filter(b->(!this.getBounds().contains(b.getBounds()))).findFirst().orElse(null) == null); 
	} 
	 
	 

	public boolean childs_in_bounds_y_min() 
	{ 
		return (this.get_components().stream().filter(b->(b.getBounds().y < gl.E_EMPTY)).findFirst().orElse(null) == null); 
	} 

	 
	public String to_string_check_childs() 
	{ 
		 
		return gl.sf("x_min...%s...y_min...%s...x_max...%s...y_max...%s", 
				childs_in_bounds_x_min(), 
				childs_in_bounds_y_min(), 
				childs_in_bounds_x_max(), 
				childs_in_bounds_y_max() 
				); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
			
		//if(under != null)
		//	gl.sfn("---> At...%s",under.getIdo().getName());
			
		
		if(!this.getStio().is_visible()) 
			return; 
				 
		this.getDrago().getServiceRects().forEach(name -> 
		{ 

				Ru ar = (Ru) name; 

			if (ar.contains(e.getPoint())) { 

				this.getDrago().setDragType(ar.getServiceType()); 
				 
				this.setCursor(new Cursor(ar.getServiceType())); 

			} 

		}); 
		 
				this.setToolTipText(this.toString()); 
				 
				// gl.sfn("Mouse move over...%s", this.getName()); 
		 
	} 

	 
	@Override 
	public void mouseClicked(MouseEvent e) { 
		
		if (SwingUtilities.isLeftMouseButton(e)) {

			if (this.is_desk_top()) {
				
				update_selected_clear();

				return;
			}

				this.getStio().set_selected(!this.getStio().is_selected());

				this.update_selected_gather();
			

		} else if (SwingUtilities.isRightMouseButton(e)) {

			this.setBackground(DialogUtil.get_color(this.getBackground()));
		}

	} 

	 
	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		 
	} 
	
	// 
	
	public void setTargetSuite(MouseEvent e)
	{
		if(this.get_all_items() == null)
		{
			gl.tracex(new Object() {},gl.sf("Get all items...NULL"));
			
			return;
		}

		if(this.get_all_items().size() == gl.E_EMPTY)
		{
			gl.tracex(new Object() {},gl.sf("Get all items...EMPTY"));
			
			//return;
		}
		
		List<AtOm> m_list =  new ArrayList<AtOm>();
		
		Rectangle rect = this.getBounds();
		
		this.get_all_items().forEach(b-> 
		{ 
			gl.tracex(new Object() {},gl.sf("Pre check list...%s...%s",b.getIdo().getName(),b.getClass().getSimpleName()));
			
			Rectangle target_rect = b.getBounds();
			
			if(b.getIdo().getOwner() != null  && !b.getIdo().getOwner().is_desk_top())
			{
				target_rect = b.getAbs_bounds();
			}
			
			if(b.getIdo().getId() != this.getIdo().getId() 	&& target_rect.contains(rect))
			{
				gl.tracex(new Object() {},gl.sf("Common list...%s...trg_rect...%s...this...%s...chk...%s...lvl...%3d...root name...%s",
								b.getIdo().getName(),
								SBounds.toString(target_rect),
								SBounds.toString(this.getBounds()),
								 target_rect.contains(this.getBounds()),
								b.getIdo().getLevel(),
								b.get_root().getIdo().getName()
								));
						
								m_list.add(b);
								
					}
		});
			
		
		m_list.forEach(a->
		{
			gl.tracex(new Object() {},gl.sf("Target list...%s...%s...lvl...%3d",
					a.getIdo().getName(),
					SBounds.toString(a.getBounds()),
					a.getIdo().getLevel()
					));
		});
		
		
		AtOm [] m_target = {null};
		
		List<AtOm> m_list_r = 
				
		m_list.stream().sorted(Comparator.comparing(s -> ((AtOm)s).getIdo().getLevel()).reversed()).collect(Collectors.toList());
				
		m_list_r.forEach(a->
		{
			gl.tracex(new Object() {},gl.sf("Sorted target list...%s...%s...lvl...%3d",
					a.getIdo().getName(),
					SBounds.toString(a.getBounds()),
					a.getIdo().getLevel()
					));
			
			if(m_target[0] == null)
				m_target[0] = a;
			
			if(a.getIdo().getLevel() > m_target[0].getIdo().getLevel())
				m_target[0] = a;
		});
		
		
			if(m_target[0] != null)
			{
				gl.tracex(new Object() {},gl.sf("Set target...%s",m_target[0].getIdo().getName()));
				
				this.getDrago().set_drop_target(m_target[0]);
			}
			else
			{

				gl.tracex(new Object() {},gl.sf("Set target...%s","NULL"));
			
			}

		
	}

	 // 
	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		ISelection.update_selector(this, e); 
		
		 
		this.getDrago().setStartPos(null); 
				 
		// Target suite.
		
		setTargetSuite(e);
		
		// Landing section. 
		 
		landing(e); 

		// Reset section. 

		if (this.getStio().is_drag()) { 

			this.getStio().set_drag(false); 
		} 
		 
		 
		this.getStio().set_resized(false); 

			 
		this.update_bounds(); 
		 
		this.update_shifts(); 
		 
		this.setShift_rect(null); 
		 
		this.setShift_dim(null); 
		 
		this.update_skip_resized() ; 
		 
				 
		 
		// Check selector. 
		 
		update_new_object(this); 
		 
		//Reset section. 
		
		this.getDrago().setStartPos(null); 
		
		if(this.getStio().is_deny_drag())
		{
			AtOm owner = this.getIdo().getOwner() ;
			
			if(owner != null && !owner.is_desk_top())
			{
				owner.getDrago().setStartPos(null);
			}
		}
		 
		this.getSlio().set_selector(Ru.get_init_rect(gl.E_EMPTY)); 
		
		if(this.getStio().is_deny_drag())
		{

			this.repaint();
		}

		
		this.getDecoro().setOrigin_bounds(this.getBounds()); 
		 
		this.getDecoro().setZoom_width(gl.E_OK); 
		 
		this.getDecoro().setZoom_height(gl.E_OK); 
		
		this.getDrago().set_drop_target(null); 
		
		StoreImplObject.get_items().clear();
		 
		//gl.sfn("---> Selector size...%s",SBounds.toString(this.getSlio().get_selector())); 
		 
		 
	} 
	 
	 
	public String get_class_name() 
	{ 
		return this.getClass().getSimpleName(); 
	} 
	
	
	
	public boolean is_class_name(String name) 
	{ 
		return this.get_class_name().equalsIgnoreCase(name); 
	} 
	 
	public void landing(MouseEvent e) 
	{ 
		
		
		 
			AtOm m_target = this.getDrago().get_drop_target(); 
		 
			if ( m_target == null ) 
			{ 
				gl.tracex(new Object() {},"Target is null."); 
				 
				return; 
				 
			} 
		 
		 
			if(this.getIdo().getPid() != m_target.getIdo().getId()) 
			{ 
		 
				//this.setBounds(Ru.norm4g(m_target.getBounds(),this.getBounds())); 
			 
				if (StoreImplObject.get_selected().size() == gl.E_EMPTY) 
				m_target.insert(this); 
				else 
				{ 
					StoreImplObject.get_selected().forEach(a-> 
					{ 
						if(a.getIdo().getPid() != m_target.getIdo().getId()) 
							m_target.insert(a); 
						 
					}); 
				} 
				 
			} 
			else 
			{ 
				gl.sfn("Try to insert in the same owner...%3d...pid...%3d",m_target.getIdo().getId(),this.getIdo().getPid()); 
				 
				return; 
			} 
	} 
	 
	public Rectangle get_inside_metrics() 
	{ 
		List<AtOm> childs = this.get_childs(); 
		 
		Rectangle result = Ru.get_init_rect(gl.E_EMPTY); 
		 
		if (childs == null ) 
			return result; 
		 
		Rectangle owner_bounds = Ru.norm4g(this.getDecoro().getBounds()); 
		 
		/* 
		childs.forEach(a->{ 
			 
			//gl.sfn("Before Get_inside_metrics ...%s...Bounds info...%s...Owner bounds...%s",a.getIdo().getName(),SBounds.toString(a.getBounds()),SBounds.toString(owner_bounds)); 
			 
		}); 
		 
		 */
		
		 
		//if (this.getDrago().getX_scroll_bar() != null) 
		//	childs.remove(this.getDrago().getX_scroll_bar()); 
		 
		//if (this.getDrago().getY_scroll_bar() != null) 
		//	childs.remove(this.getDrago().getY_scroll_bar()); 
		
		if(this.get_scroll_bar_x() != null)
		   childs.remove(this.get_scroll_bar_x());
		 
		if(this.get_scroll_bar_y() != null)
			   childs.remove(this.get_scroll_bar_y());
			
		/* 
		childs.forEach(a->{ 
			 
			gl.sfn("After Get_inside_metrics ...%s...Bounds info...%s...Owner bounds...%s",a.getIdo().getName(),SBounds.toString(a.getBounds()),SBounds.toString(owner_bounds)); 
			 
		}); 
		 
		*/ 
		 
		if (childs.size() == gl.E_EMPTY) 
			return result; 
		
		 
		result.x 		= childs.stream().mapToInt(b->b.getBounds().x).min().getAsInt(); 
		 
		result.y 		= childs.stream().mapToInt(b->b.getDecoro().getBounds().y).min().getAsInt(); 
		 
		result.width  	= owner_bounds.width - childs.stream().mapToInt(b->b.getDecoro().getBounds().x+b.getDecoro().getBounds().width).max().getAsInt(); 

		result.height 	= owner_bounds.height - childs.stream().mapToInt(b->b.getBounds().y+b.getDecoro().getBounds().height).max().getAsInt(); 
			 
		 
		result.x 		= (result.x > gl.E_EMPTY) ? gl.E_EMPTY : result.x; 
		 
		result.y 		= (result.y > gl.E_EMPTY) ? gl.E_EMPTY : result.y; 
		 
		result.width 		= (result.width >= gl.E_ERROR) ? gl.E_EMPTY : result.width; 
		 
		result.height 		= (result.height >= gl.E_ERROR) ? gl.E_EMPTY : result.height; 
		 
		 
		return result; 
		 
	} 

	
	public Rectangle get_inside_metrics_pure() 
	{ 
		List<AtOm> childs = this.get_childs(); 
		 
		Rectangle re = Ru.get_init_rect(0);//this.getBounds();
		 
		if (childs == null || childs.size() == gl.E_EMPTY)
		{
			return re; 
		}
		 
		//gl.tracex(new Object() {},gl.sf("Chils items count...%d",childs.size()));
		
		childs.forEach(b->
		{
			Rectangle r = b.getBounds();
			
			if(r.x < re.x)
					re.x = r.x;
			
			if(r.y < re.y)
				re.y = r.y;
		
			if((r.x +r.width) > (re.x + re.width))
				re.width = r.x + r.width;
		
				
			if((r.y+r.height) > (re.y + re.height))
				re.height = r.y + r.height;
			
			// gl.sfn("Childs...%s...%s",b.getIdo().getName(),SBounds.toString(b.getBounds()));
		
		});
		
		 
		return re; 
		 
	} 

		 
	
	/**
	 * 
	 */
	
	public void nextLine()
	{
		
		if(counter > 30)
		{
			counter = gl.E_EMPTY;
			
			gl.smn("");
		}
		else
			counter++;
		
	}
	
	
	public void re_validate_new() 
	{ 
		 
		// Show inside metrics. 
		if(this.get_childs().size() == gl.E_EMPTY) 
		   this.update_childs();	 
		 
		 
		//this.getDrago().setScroll_metric(this.get_inside_metrics()); 
		 
		//Rectangle 	ctrl = this.getDrago().getScroll_metric(); 
		 
		
		//if(!this.is_desk_top()) 
		{
			
			Rectangle child_area = this.get_inside_metrics_pure();
			
			Rectangle this_area = this.getBounds(); 
			
			Rectangle pref = new Rectangle(0,0,this.getPreferredSize().width,this.getPreferredSize().height);
			
			boolean mode = pref.contains(child_area);
			
			boolean bl_in = false;
			
			int d = -1;
			
			if(mode == false &&  prev_mode==true)
			{
				bl_in = true;
				
			} else if(mode == true &&  prev_mode==false )
			{
				bl_in = false;
			}
			
		
			if(bl_in)
			{
		
				this.setPreferredSize(new Dimension(child_area.width+d,child_area.height+d));

				this.revalidate();
				
				this.repaint();
			}
			else
			{
				
				if(!pref.contains(child_area))
				{
					
					this.setPreferredSize(new Dimension(child_area.width+d,child_area.height+d));
						
					this.revalidate();
				
					this.repaint();
				}
				
			}
				prev_mode = mode;
		}
		
		 
	} 


	
	public void re_validate_pre_build() 
	{ 
		 
		// Show inside metrics. 
		if(this.get_childs().size() == gl.E_EMPTY) 
		   this.update_childs();	 
		 
		 
		this.getDrago().setScroll_metric(this.get_inside_metrics()); 
		 
		Rectangle 	ctrl = this.getDrago().getScroll_metric(); 
		 
		boolean 	bl_is_empty = Ru.is_empty(ctrl); 
		 
		//Rectangle 	rc_to_draw =  Ru.norm_metric(this.getDecoro().getBounds(),ctrl); 
		 
		/*s
		gl.sfn("re_validate()  Size...%03d...Metrics...%s...%s...draw...%s...Check...%s", 
				this.get_childs().size(), 
				SBounds.toString(ctrl), 
				this.get_class_name(), 
				SBounds.toString(rc_to_draw), 
				bl_is_empty); 
		
		*/
					 
		if(!this.is_desk_top()) 
		{
			//update_scroll_bar_x(ctrl);
			
			//update_scroll_bar_y(ctrl);
			
			Rectangle child_area = this.get_inside_metrics_pure();
			
			Rectangle this_area = this.getBounds(); 
			
			Rectangle pref = new Rectangle(0,0,this.getPreferredSize().width,this.getPreferredSize().height);
			
			
			boolean mode = pref.contains(child_area);
			
			
			
			gl.sfn("Inside metric...%s....this...%s...pref_size...%s...mode...%s...prev mode...%s" ,
					SBounds.toString(child_area),
					SBounds.toString(this_area),
					SDimension.toString(this.getPreferredSize()),
					mode,
					prev_mode
					
					);
			
			
			boolean bl_in = false;
			
			if(mode == false &&  prev_mode==true)
			{
				
				gl.smn("!>");
				
				bl_in = true;
				
				
			} else if(mode == true &&  prev_mode==false )
			{

				gl.smn("<!");
				
				bl_in = false;
					
			}
			
		
			if(bl_in)
			{
				//this.setPreferredSize(new Dimension(child_area.width,child_area.height));
				
				//this.revalidate();
				
				//this.repaint();
				
				//gl.smn("---> In area ......");
				
				nextLine();
				
				gl.sm(">");
				
				
				this.setPreferredSize(new Dimension(child_area.width,child_area.height));
				
				this.revalidate();
				
				this.repaint();
			
				
				
			}
			else
			{
				
				nextLine();
				
				gl.sm("<");
				
				if(!pref.contains(child_area))
				{
					
					this.setPreferredSize(new Dimension(child_area.width-1,child_area.height-1));
					
					this.revalidate();
					
					this.repaint();
				}
				
			}
			
			//Rectangle pref = new Rectangle(0,0,this.getPreferredSize().width,this.getPreferredSize().height);
			
			//if(!this_area.contains(child_area))
			
			/*
			if(!pref.contains(child_area))
			{
				
				this.setPreferredSize(new Dimension(child_area.width,child_area.height));
				
				this.revalidate();
				
				this.repaint();
				
				mode600 = false;
			}
			else
			{
				
				if(!mode600)
				{
					this.setPreferredSize(new Dimension(800,600));
					
					this.revalidate();
					
					mode600 = true;
					}
				
				
			}
			*/
			
				
				prev_mode = mode;
		}
		else
		{
			Rectangle r = this.get_inside_metrics_pure();
			
			Rectangle rb = this.getBounds(); 
					
			gl.sfn("Preffered size...%s" ,SBounds.toString(r));
			
			if(!bl_is_empty)
			{
				gl.smn("Catch....");
				
				this.setPreferredSize(new Dimension(r.width,r.height));

			    //this.revalidate();
				
			}
			else
				//this.setPreferredSize(new Dimension(rb.width,rb.height));
			this.setPreferredSize(new Dimension(621,438));
			
			this.revalidate();
				
		}
		 
	} 

	 

	public void re_validate() 
	{ 
		 
		// Show inside metrics. 
		if(this.get_childs().size() == gl.E_EMPTY) 
		   this.update_childs();	 
		 
		 
		this.getDrago().setScroll_metric(this.get_inside_metrics()); 
		 
		Rectangle 	ctrl = this.getDrago().getScroll_metric(); 
		 
		boolean 	bl_is_empty = Ru.is_empty(ctrl); 
		 
		//Rectangle 	rc_to_draw =  Ru.norm_metric(this.getDecoro().getBounds(),ctrl); 
		 
		/*s
		gl.sfn("re_validate()  Size...%03d...Metrics...%s...%s...draw...%s...Check...%s", 
				this.get_childs().size(), 
				SBounds.toString(ctrl), 
				this.get_class_name(), 
				SBounds.toString(rc_to_draw), 
				bl_is_empty); 
		
		*/
					 
		if(!this.is_desk_top()) 
		{
			update_scroll_bar_x(ctrl);
			
			update_scroll_bar_y(ctrl);
		}
		
	} 
	 
	public void update_childs() 
	{ 
		this.get_childs().clear(); 
		 
		this.get_childs().addAll(this.get_components()); 
		 
	} 
	 
	public void update_childs(AtOm owner) 
	{ 
		this.get_childs().clear(); 
		 
		this.get_childs().addAll(this.get_components()); 
		 
		this.get_childs().remove(owner); 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		if (!this.add_service_rects()) 
			gl.tracex(new Object() {}, String.format("%s...add ctrl rects...%d...id...%d", 
					gl.S_ERROR,this.getDrago().getServiceRects().size(), this.getIdo().getId())); 
		 
		 // Update childs. 
		this.update_childs(); 
		 
		this.update_skip_resized(); 
		 
		this.requestFocus();	 
		 
		//Scroll suite. 
	 
		/* 
		gl.sfn("ScrollX...%s....ScrollY...%s", 
				(this.get_scroll_bar_x()== null)? null :this.get_scroll_bar_x().getIdo().getName() , 
				(this.get_scroll_bar_y()== null)? null :this.get_scroll_bar_y().getIdo().getName() 
				); 
		 */ 
		//if(this.get_scroll_bar_x() != null) 
		//	this.getDrago().setX_scroll_bar((ap.uat.scrollbar.ScrBarX)this.get_scroll_bar_x()); 
		 
		//if(this.get_scroll_bar_y() != null) 
		//	this.getDrago().setY_scroll_bar((ap.uat.scrollbar.ScrBarY)this.get_scroll_bar_y()); 
			 
		//gl.sfn("Root...%s...for...%s",this.get_root().getIdo().getName(),this.getIdo().getName()); 
	} 

	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		 
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
		 
		this.getDrago().getServiceRects().clear(); 
		 
		this.get_childs().clear(); 
		 
		 
		this.setShift_rect(null); 
		 
		this.setShift_dim(null); 
		 
	} 

	 
	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) 
	{ 
		gl.smn("...Wheel..."); 
	} 
	 
	public boolean mgmt_key_event(KeyEvent e) 
	{ 
		String m_ch = gl.sf("%s",e.getKeyChar()); 
				 
		if ( Su.isAlpha(m_ch) || 
				Su.isNumber(m_ch) || 
					Su.isWhitespace(m_ch) || 
					m_ch.equalsIgnoreCase(".") || 
					m_ch.equalsIgnoreCase(",") || 
					m_ch.equalsIgnoreCase("/") || 
					m_ch.equalsIgnoreCase("-") || 
					e.getKeyCode() == KeyEvent.VK_BACK_SPACE  ||
					e.isShiftDown()
			) 
				return false; 
		 
				 
		int m_code = e.getKeyCode(); 
		 
		this.getKeys().put(m_code,true); 
		 
		 
		if(this.getKeys().containsKey(KeyEvent.VK_CONTROL) && 
					this.getKeys().containsKey(KeyEvent.VK_A) 
				) 
		{ 
			//Selection. 
			 
			//return this.select_all_items(); 
			return true; 
			 
		}else if(this.getKeys().containsKey(KeyEvent.VK_CONTROL) && 
					this.getKeys().containsKey(KeyEvent.VK_D) 
				) 
		{ 
			// Delete. 
			//return DeleteAction.getInstance().actionPerformed(this); 
			 
			return true; 
			 
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

		//if (this.isShowKeyCode()) 
		//	gl.smn("->" + key_code); 

		char ch = e.getKeyChar(); 
		
		if ( mgmt_key_event(e)) 
			 return; 
				 
		String START = "."; 

		// Fire command. 
		if (key_code == KeyEvent.VK_ENTER) { 
		 
			String m_cmd_line = this.getCmd().toString().trim(); 
			 
			//gl.smn(gl.sf("Command line...[%s]",m_cmd_line)); 
			 
			String m_response = CmdA.get_instance(this,m_cmd_line).get_state_message(); 
					 
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
			ch = ' ';
		} 
		else if (key_code == KeyEvent.VK_ALT) { 
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
	 
	public void move_x_line(KeyEvent e,boolean right) 
	{ 
		 
		/* 
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
		 
		*/ 
	} 
	 
	public void move_y_line(KeyEvent e,boolean down) 
	{ 
		/* 
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
		*/ 
	} 
		 
	public void mouseWheelProcessing(MouseWheelEvent e) 
	{ 
		/*	 
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
		*/ 
	} 
	 
	public void cmd_add(char c) 
	{ 
		this.getCmd().append(c); 
	} 
	 
	@Override 
	public void keyTyped(KeyEvent e) {} 
	 
	@Override 
	public void keyPressed(KeyEvent e) { 
		cmd(e); 
	} 
	 
	@Override 
	public void keyReleased(KeyEvent e) { 
		 
		this.getKeys().remove(e.getKeyCode()); 
	} 

		 
	@Override
	public void componentResized(ComponentEvent e) 
	{
		/*
		gl.smn(gl.sf("%s...%d...%s...%s...Parent bounds...%s","Component resized", 
				this.getIdo().getId(), 
				this.getName(), 
				this.getClass().getSimpleName(), 
				(this.getDecoro().getParents_bounds() == null) ? "null" : SBounds.toString( this.getDecoro().getParents_bounds() ) 
				)); 
		*/
		 
		 
		//gl.sfn("Component resized...%s",this.getName()); 
		 
		this.getDecoro().setSize(this.getSize()); 
	 	 
		this.getDecoro().setBounds(this.getBounds()); 
		 
		// Accept area. 
		if(!this.getStio().is_resized() && !this.getStio().is_skip_resizing() ) 
			accept_area_suite(this); 
		 
		 
		//if(this.get_components().size() != gl.E_EMPTY)
		//{ 
		 	this.get_components().forEach(a->
		 	{ 
		 		a.getDecoro().setParents_bounds(this.getBounds()); 
		 			 		
		 		a.componentResized(e);
		 		
		 		
		 	}); 
		//} 
		 
					 
		 
	} 

	@Override 
	public void componentMoved(ComponentEvent e) { 
		 
	
	} 

	@Override 
	public void componentShown(ComponentEvent e) {} 

	@Override 
	public void componentHidden(ComponentEvent e) {} 
	 
	 
	public boolean add_service_rects() { 

		Rectangle rect = this.getBounds(); 

		if (rect == null) 
			return true; 

		int OFF = TPanel.MW; 

		 
		if(this.getDrago().getServiceRects() == null) 
		{
			
			List<Ru> ru_list =  new ArrayList<Ru>();
			
			this.getDrago().setServiceRects(ru_list);
		}
		 
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

		if( 
				this.getClass().getSimpleName().equalsIgnoreCase("ScrollBarY") || 
				this.getClass().getSimpleName().equalsIgnoreCase("ScrollBarX") 
		   ) 
		{ 
			this.add_service_rect(rec_body); 
		} 
		else 
		{ 
			this.add_service_rect(rec_north_west, rec_north_east, rec_south_east, 

					rec_south_west, rec_west, rec_north, rec_east, rec_south, 

					rec_body); 
		} 
		 

		return (this.getDrago().getServiceRects().size() != gl.E_EMPTY); 

	} 
	 
	public void add_service_rect(Object ... args) 
	{ 
		for (Object arg : args) 
		{ 
			this.getDrago().getServiceRects().add((Ru)arg); 
		} 
	} 
	 
	public void set_dx_dy(MouseEvent e) 
	 { 
		 if( e == null) 
			return; 
			 
		 	 this.getDrago().setDx((e.getX() - this.getDrago().getStartPos().x)); 
		 	 
			 this.getDrago().setDy((e.getY() - this.getDrago().getStartPos().y)); 
		 
	 } 
	 
	public void move_dx_dy(int dx,int dy) 
	{ 
		this.setLocation(this.getLocation().x+dx,this.getLocation().y+dy); 
		 
		this.getDecoro().setBounds(this.getBounds()); 
	} 
	 
	 
	 
	public void move_byself_dx_dy(int dx,int dy) 
	{ 
		 
		int m_max_x = this.getLocation().x + dx ; 
		 
		int m_max_y = this.getLocation().y + dy ; 
		 
		if(this.getStio().is_move_inside()) 
		{ 
			if( 
					m_max_x < gl.E_EMPTY  || 
					m_max_x + this.getDecoro().getBounds().width > (this.getDecoro().getParents_bounds().width - 1) 
			  ) 
			{ 
				m_max_x = this.getLocation().x; 
			} 
			 
			if( 
					m_max_y + this.getDecoro().getBounds().height > (this.getDecoro().getParents_bounds().height - 1) || 
					m_max_y < gl.E_EMPTY 
			  ) 
			{ 
				m_max_y = this.getLocation().y; 
			} 
		} else if(this.getStio().is_move_bottom_right()) 
		{ 
			if( 
					m_max_x < gl.E_EMPTY 
			  ) 
			{ 
					m_max_x = this.getLocation().x; 
			} 
			 
			if( 
					m_max_y < gl.E_EMPTY 
			  ) 
			{ 
					m_max_y = this.getLocation().y; 
			} 
		} if(this.getStio().is_move_top_left()) 
		{ 
			if( 
					m_max_x + this.getDecoro().getBounds().width > (this.getDecoro().getParents_bounds().width - 1) 
			  ) 
			{ 
					m_max_x = this.getLocation().x; 
			} 
			 
			if( 
					m_max_y + this.getDecoro().getBounds().height > (this.getDecoro().getParents_bounds().height - 1) 
			  ) 
			{ 
					m_max_y = this.getLocation().y; 
			} 
		} 
				 
		 
		this.setLocation(m_max_x,m_max_y); 
		 
		this.getDecoro().setBounds(this.getBounds()); 
		 
		this.getIdo().getOwner().re_validate(); 
		 
		//this.getParent().revalidate();
 		 
	} 
	 
	public void move_byself_dx_dy(Point point) 
	{ 
		this.setLocation(point); 
		 
		this.getDecoro().setBounds(this.getBounds()); 
		 
	} 
	 
	 public void drag_by_other(MouseEvent me) 
			{ 
				 int MW = ApplicationA.getCio().MW; 
				 
						 
				 Rectangle m_result_rect = Ru.get_init_rect(gl.E_EMPTY); 
				 
				 if (this.getDrago().getStartPos() == null) 
				 { 
					 //gl.sfn("drag_by_other...%s",this.getName()); 
				 
					 return ; 
				 } 
				 
				 					 
					 int m_x = getX(); 

					 int m_y = getY(); 
					 
					 
					 int m_w = getWidth(); 
					 
					 int m_h = getHeight(); 
					 
					 
					 int m_dx = this.getDrago().getDx(); 
					 
					 int m_dy = this.getDrago().getDy(); 
					 
				 
					 // Implementation of Deny. 
					 if(this.getStio().is_deny_width()) 
					 { 
						 m_dx = gl.E_EMPTY; 
						 
						 gl.smn("Deny width ..."); 
					 
					 } 
					 
					 if(this.getStio().is_deny_height()) 
					 { 
						 m_dy = gl.E_EMPTY; 
						 
						 gl.smn("Deny height ..."); 
					 } 
					 
					 
					 
					switch (this.getDrago().getDragType()) { 

					case Cursor.N_RESIZE_CURSOR: 

						if (!(m_h - m_dy < MW)) { 

							m_result_rect = new Rectangle(m_x, m_y + m_dy, m_w, m_h - m_dy); 
						} 

						break; 

					case Cursor.S_RESIZE_CURSOR: 

						if (!(m_h + m_dy < MW)) { 

							this.getDrago().setStartPos(me.getPoint()); 
							 
							m_result_rect = new Rectangle(m_x, m_y, m_w, m_h + m_dy); 
						} 

						break; 

					case Cursor.W_RESIZE_CURSOR: 

						if (!(m_w - m_dx < MW)) { 
			 
							m_result_rect = new Rectangle(m_x + m_dx, m_y, m_w - m_dx, m_h); 

						} 

						break; 

					case Cursor.E_RESIZE_CURSOR: 

						if (!(m_w + m_dx < MW)) { 

							this.getDrago().setStartPos(me.getPoint()); 
					 
							m_result_rect = new Rectangle(m_x, m_y, m_w + m_dx, m_h); 

						} 

						break; 

					case Cursor.NW_RESIZE_CURSOR: 

						if (!(m_w - m_dx < MW) && !(m_h - m_dy < MW)) { 

							m_result_rect = new Rectangle(m_x + m_dx, m_y + m_dy, m_w - m_dx, m_h - m_dy); 
						} 

						break; 

					case Cursor.NE_RESIZE_CURSOR: 

						if (!(m_w + m_dx < MW) && !(m_h - m_dy < MW)) { 

							this.getDrago().setStartPos(new Point(me.getX(), this.getDrago().getStartPos().y)); 
							 
							m_result_rect = new Rectangle(m_x, m_y + m_dy, m_w + m_dx, m_h - m_dy); 
							 
						} 

						break; 

					case Cursor.SW_RESIZE_CURSOR: 

						if (!(m_w - m_dx < MW) && !(m_h + m_dy < MW)) { 

							 
							this.getDrago().setStartPos(new Point(this.getDrago().getStartPos().x, me.getY())); 
							 
							m_result_rect = new Rectangle(m_x + m_dx, m_y, m_w - m_dx, m_h + m_dy); 


						} 

						break; 

					case Cursor.SE_RESIZE_CURSOR: 

						if (!(m_w + m_dx < MW) && !(m_h + m_dy < MW)) { 

							this.getDrago().setStartPos(me.getPoint()); 
							 
							m_result_rect = new Rectangle(m_x, m_y, m_w + m_dx, m_h + m_dy); 

						} 
						break; 

					} // switch 
					 
					 
					 
						// Global Acceptor. 
					 
						if(m_result_rect.width < MW*2) 
							m_result_rect.width = MW*2; 
						 
						if(m_result_rect.height < MW*2) 
							m_result_rect.height = MW*2; 
						 
					 
						this.setBounds(m_result_rect); 
						 
						this.getDecoro().setBounds(m_result_rect); 
						 
						 
						this.getStio().set_resized(true); 
						 
						 
						double m_w_1 = this.getDecoro().getBounds().width; 
						 
						double m_w_2 = this.getDecoro().getOrigin_bounds().width; 
						 
						double zoom_width = m_w_1/m_w_2; 
						 
						 
						this.getDecoro().setZoom_width(zoom_width); 
						 
						 
						double m_h_1 = this.getDecoro().getBounds().height; 
						 
						double m_h_2 = this.getDecoro().getOrigin_bounds().height; 
						 
						double zoom_height = m_h_1/m_h_2; 
						 
						 
						this.getDecoro().setZoom_height(zoom_height); 
						
						this.get_all_childs().forEach(a->{
							
							a.getDecoro().setZoom_height(zoom_height);
							
							a.getDecoro().setZoom_width(zoom_width);
						});
						 
						//gl.sfn("zoom width...%.4f...zoom height...%.4f",zoom_width,zoom_height); 
						 
				 
			//}// dragHelper 
		 
		 
		 
		 
} 
	 
	@Override
	public String toString() 
	{ 
		 
		return gl.sf("%s pid %d id %d  bounds %s inside %s pref  %s", 
				this.getIdo().getName(), 
				
				this.getIdo().getPid(), 
				
				this.getIdo().getId(), 
			
				SBounds.toString(this.getBounds()), 
				
				SBounds.toString(this.get_inside_metrics_pure()),
				 
				SDimension.toString(this.getPreferredSize()) 
				 
				); 
	}
	
	/*
	public String toString() 
	{ 
		 
		return gl.sf("%s pid %d id %d z-order %d level %d childs %d align %s clazzname %s bounds %s abs_bounds %s deny_drag %s", 
				this.getIdo().getName(), 
				this.getIdo().getPid(), 
				this.getIdo().getId(), 
				this.getIdo().getIndex(), 
				this.getIdo().getLevel(), 
				 
				this.getComponentCount(), 
				 
				IAlign.indexOf(this.getDecoro().get_align()), 
						 
				this.getClass().getSimpleName(), 
				 
				SBounds.toString(this.getBounds()), 
				
				SBounds.toString(this.getAbs_bounds()),
				 
				this.getStio().is_deny_drag() 
				 
				); 
	} 
	*/
	 
	public String toString_() 
	{ 
		 
		return gl.sf("%s pid %d id %d z-order %d level %d childs %d align %s clazzname %s offset %3d bounds %s dec_bounds %s", 
				this.getIdo().getName(), 
				 
				this.getIdo().getPid(), 
				this.getIdo().getId(), 
				this.getIdo().getIndex(), 
				this.getIdo().getLevel(), 
				 
				this.getComponentCount(), 
				 
				IAlign.indexOf(this.getDecoro().get_align()), 
						 
				this.getClass().getSimpleName(), 
				 
				this.get_off_set(), 
				 
				SBounds.toString(this.getBounds()), 
				 
				SBounds.toString(this.getDecoro().getBounds()) 
				 
				); 
	} 
	 
	public void addRect(Object ... args) 
	{ 
		for (Object arg : args) 
		{ 
			this.getDrago().getServiceRects().add((Ru)arg); 
		} 
	} 
	 
	public void insert(Object ... args) 
	{ 
		for (Object arg : args) 
		{ 
			this.insert((AtOm)arg); 
		} 
	} 
	 
	public void insert_ipo(Object ... args) 
	{ 
		for (Object arg : args) 
		{ 
			this.insert_soft((AtOm)arg); 
		} 
	} 
	 
	public void accept_constraints() 
	{ 
		 
		 gl.sfn("Accept constraint...%s...owner...%s",this.getName(),this.getIdo().getOwner().getIdo().getName()); 
		 
		 if(this.getStio().is_drag() && this.getDecoro().get_align() != IAlign.indexOf("NONE")) 
		 { 

			 if(this.getDecoro().get_align() == IAlign.indexOf("SCROLLBARX")) 
				 this.getStio().set_deny_y(true); 
			 else if(this.getDecoro().get_align() == IAlign.indexOf("SCROLLBARY")) 
				 this.getStio().set_deny_x(true); 
			 		 				 
			 
			 this.getStio().set_deny_width(true); 
			 
			 this.getStio().set_deny_height(true); 
			 
			 this.getStio().set_move_inside(true); 
			 
		 } 
		 
		 
	} 
	
	public boolean insert(AtOm  value) 
	{ 
	 
		gl.sfn("Insert...%s...to target...%s",value.getName(),this.getName()); 
		 
		if(value.getParent() != null && value.getParent() == this) 
		{ 
			gl.smn("The trying to insert into by self."); 
			 
			return false; 
		} 
		 
		try 
		{ 
			int m_next_index = this.getComponents().length; 
			 
			value.getIdo().setOwner(this); 
						 
			this.add(value,m_next_index); 
			 
			 
			value.getIdo().setIndex(m_next_index); 
			 
			value.getIdo().setPid(this.getIdo().getId());
			
			value.getIdo().setLevel(this.getIdo().getLevel()+1);
	 
			value.getDecoro().setParents_bounds(this.getBounds()); 
			 
			 
			if(value.getDecoro().get_align() == IAlign.indexOf("SCROLLBARX")) 
			{ 
				value.getStio().set_deny_y(true); 
				 
				//this.getDrago().setX_scroll_bar((ap.uat.scrollbar.ScrBarX)value); 
			} 
			 
			 
			if(value.getDecoro().get_align() == IAlign.indexOf("SCROLLBARY")) 
			{ 
				value.getStio().set_deny_x(true); 
				 
				//this.getDrago().setY_scroll_bar((ap.uat.scrollbar.ScrBarY)value); 
			} 
			 
			if( 
					!value.getClass().getSimpleName().equalsIgnoreCase("AtOm")  &&
					
					!value.getClass().getSimpleName().equalsIgnoreCase("PanelPoint")
					
					
			 ) 
			{ 
				 
				value.setBackground(this.getBackground()); 
				 
				value.revalidate(); 
				 
				value.repaint(); 
			} 
			 
			
			Rectangle m_bounds = Ru.norm4g(this.getBounds(),value.getBounds()); 
			
			if(!this.getIdo().getOwner().is_desk_top())
				m_bounds = Ru.norm4g(this.getAbs_bounds(),value.getBounds());
			
			value.setBounds(m_bounds); 
			 
			value.getDecoro().setBounds(m_bounds); 
			
			gl.sfn("---> Bounds...%s",SBounds.toString(value.getBounds()));
			 
			if( value.getStio().is_selected()) 
				value.getStio().set_selected(false); 
			
			// value.getStio().set_deny_drag(true);
			
			// value.getStio().set_deny_inside_selection(true);
			
			return true; 
		 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 

	public boolean insert_soft(AtOm  value) 
	{ 
	 
		//gl.sfn("Insert invocation intro target...%s...from...%s",this.getName(),value.getName()); 
		 
		if(value.getParent() != null && value.getParent() == this) 
		{ 
			gl.smn("The trying to insert into by self."); 
			 
			return false; 
		} 
		 
		try 
		{ 
			int m_next_index = this.getComponents().length; 
			 
			value.getIdo().setOwner(this); 
						 
			this.add(value,m_next_index); 
			 
			 
			value.getIdo().setIndex(m_next_index); 
			 
			value.getIdo().setPid(this.getIdo().getId()); 
	 
			value.getDecoro().setParents_bounds(this.getBounds()); 
			
			
			//if (!value.getClass().getSimpleName().equalsIgnoreCase("AtOm"))
			//    value.setBackground(this.getBackground()); 
			 
			    //value.revalidate(); 
			 
			    //value.repaint(); 
			
			return true; 
		 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 
	 
	 
	 
	public boolean isa_top() 
	{ 
		return ( this.getIdo().getIndex() == gl.E_EMPTY); 
	} 
	 
	public boolean is_desk_top() 
	{ 
		return (this.getIdo().getPid() == gl.E_ERROR); 
	} 
	 
	 
	public  boolean pull_on_top() 
	{ 
		 
					//gl.sfn("Pull on top ...",""); 
		 
					String msg = "Stay top."; 
		 
					AtOm parent = (AtOm)this.getParent(); 
		 
		try 
		{	 	 
					Component [] co_list = parent.getComponents(); 
					 
					parent.setComponentZOrder(this,gl.E_EMPTY); 
					 
					this.getIdo().setIndex(parent.getComponentZOrder(this)); 
								 
					int index = gl.E_OK; 
					 
					 
			for(Component c : co_list) 
			{ 
				if(!c.getName().equalsIgnoreCase(this.getName())) 
				{ 
					parent.setComponentZOrder(c,index); 
					 
					((AtOm)c).getIdo().setIndex(index); 
					 
					index++; 
				} 
			} 
					 
					//gl.tracex(new Object(){},gl.sf("%s...id...%d...%s",msg,this.getIdo().getId(),gl.S_OK)); 
					 
					return true; 
		} 
		 
		catch(Exception e) 
		{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage(),gl.S_ERROR)); 
			 
					return false; 
		} 
			 
	} 
	 
	public void paintTextHelper(Graphics g) { 
		 
		 
		if (this.getStio().is_skip_text()) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		String	m_text = String.format("%s %s %03d",
				this.getIdo().getName(),
				IAlign.indexOf(this.getDecoro().get_align()),
				this.getOpaque()
				); 
				 
		this.getDecoro().set_text(m_text); 
		 
		Dimension font_dim = gl.getFontDim(g2, this.getFont(), this.getDecoro().get_text()); 


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

				 
		GU.drawAlphaString(g2,this.getDecoro().get_text(), text_rect.x 
				+ (text_rect.width - font_dim.width) / 2, 
				(text_rect.y + text_rect.height / 2) + font_dim.height / 2, 
				this.getDecoro().getTextColor(), this.getFont()); 
		
		
		/*
		GraphicsUtil.drawAlphaString(g2,this.getDecoro().get_text(), text_rect.x 
				+ (text_rect.width - font_dim.width) / 2, 
				(text_rect.y + text_rect.height / 2) + font_dim.height / 2, 
				ColorUtil.getAlphaColor(this.getDecoro().getTextColor(),this.getOpaque()), this.getFont()); 
		*/
	} 
	
	public void paintGrid(Graphics g) { 

		if (!this.getStio().is_grid())
			return; 

		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		AreaManager grid_am = new AreaManager( 
				new Dimension(wr.width, wr.height),this.getDecoro().getGrid()); 

		grid_am.getGridLinesV().forEach(a -> { 

			GU.drawAlphaLine(g2, a,Color.white , 0.8f); 

		}); 

		grid_am.getGridLinesH().forEach(a -> { 

			GU.drawAlphaLine(g2, a,Color.white , 0.8f); 

		}); 

	} 
	 
	public void touch() 
	{ 
		gl.smn("Touch..."); 
		 
		Rectangle m_bounds = this.getBounds(); 
		 
		this.setBounds(Ru.Scale(m_bounds,new Dimension(gl.E_ERROR,gl.E_ERROR))); 
		 
		this.setBounds(m_bounds); 
	} 
	 
	public static void remove_scroll_bars(AtOm owner) 
	{ 
		AtOm  sbx = owner.getIdo().getOwner().get_component_by_class_name("ScrollBarX"); 
		 
		if(sbx != null) 
			owner.getIdo().getOwner().get_childs().remove(sbx); 
			 
		AtOm  sby = owner.getIdo().getOwner().get_component_by_class_name("ScrollBarY"); 
		 
		if(sby != null) 
			owner.getIdo().getOwner().get_childs().remove(sby); 
		 
	} 
	
	 
	public int get_max_x() 
	{ 
		return 0; 
	} 
	 
	public Rectangle accept_area_suite(AtOm owner) 
	{ 
		Rectangle rc = accept_area(owner); 
		 
		 
		if(rc != null) 
		{ 
			if (Ru.is_valid_size(rc)) 
			{ 
				this.setBounds(rc); 
			 
				update_bounds(); 
				
				
			} 
			else 
			{ 
				
				 
				return owner.getBounds(); 
			} 
		} 
		 		
		 
				return rc; 
	} 
	 
	 
	public Rectangle accept_area(AtOm owner) 
	{ 
		Rectangle rc = null; 
		 
		// Disable for desktop owner. 
		 
		if(this.getIdo().getOwner() != null && this.getIdo().getOwner().getIdo().getId() == gl.E_OK) 
		{ 
			 // gl.smn("---> Some case");
			
			return rc; 
		} 
		 
		 
		 
		// Set area for by self. 
				 
		if(this.getDecoro().get_align() != IAlign.indexOf("NONE")) 
		{ 
			rc = IAlign.get_area(owner); 
		} 
		 
			return rc; 
	} 
	 
	public void accept_area(int x) 
	{ 
		// Disable for desktop owner. 
		 
		if(this.getIdo().getOwner() != null && this.getIdo().getOwner().getIdo().getId() == gl.E_OK) 
		{ 
			gl.smn("Accept area reject."); 
			 
			return; 
		} 
		 
		// Set area for by self. 
				 
		if(this.getDecoro().get_align() != IAlign.indexOf("NONE")) 
		{ 
			Rectangle rc = IAlign.get_area(this); 
			 
			rc.x = x; 
			 
			this.setBounds(rc); 
		} 
		 
			update_bounds(); 
	} 
	 
	 
	public void update_bounds() 
	{ 

		this.getDecoro().setBounds(this.getBounds()); 
	 
		this.getDecoro().setOrigin_bounds(this.getDecoro().getBounds()); 
	 
	} 
	 
	 public boolean re_repaint() 
	 { 
		 
		 this.repaint(); 
		 
		 return true; 
	 } 
	 
	 public void update_scroll_bar_x(Rectangle metric) 
	 {
		 ScrBarX  sbx = (ScrBarX)this.get_scroll_bar_x();
		 
		 if(sbx == null)
			 return;
		 
		 
		 Dimension dim = Ru.norm_to_state_dim(metric); 	 
			
			
		 boolean 	bl_visible = (dim.width == gl.E_OK);
		 
		 sbx.getStio().set_visible(bl_visible);
		 
		 gl.tracex(new Object() {},gl.sf("Set visible sbar_x...%s...to...%s", sbx.getIdo().getName(),bl_visible));
		
		 
		 if(!bl_visible)
		 {
			 
			 sbx.getIdo().getOwner().repaint();
			 
			 return;
		 }
		 
		 sbx.repaint();
		 
		 // () * -1.
		 Rectangle 	rect = Ru.norm_to_state_rect(metric);
		 
		 int 		m_width  = this.getDecoro().getBounds().width - rect.width; 
		 
		 sbx.getStio().set_skip_resizing(true); 
			 
		 
		 Rectangle 	m_rect = sbx.getBounds();
		 
		 			m_rect.x = rect.x;
		 
		 			m_rect.width = m_width - rect.x;
		 
		 			
		 			sbx.setBounds(m_rect);
		 			
		 			sbx.update_bounds();
		 			
		 			sbx.repaint();
				  		 
	 }
	 
	 public void update_scroll_bar_y(Rectangle metric) 
	 {
		 ScrBarY  sby = (ScrBarY)this.get_scroll_bar_y();
		 
		 if(sby == null)
			 return;
		 
		 
		 Dimension dim = Ru.norm_to_state_dim(metric); 	 
			
			
		 boolean 	bl_visible = (dim.height == gl.E_OK);
		 
		 sby.getStio().set_visible(bl_visible);
		 
		 gl.tracex(new Object() {},gl.sf("Set visible sbar_y...%s...to...%s", sby.getIdo().getName(),bl_visible));
		
		 
		 if(!bl_visible)
		 {
			 
			 sby.getIdo().getOwner().repaint();
			 
			 return;
		 }
		 
		 sby.repaint();
		 
		 // () * -1.
		 Rectangle 	rect = Ru.norm_to_state_rect(metric);
		 
		 int 		m_height  = this.getDecoro().getBounds().height - rect.height; 
		 
		 sby.getStio().set_skip_resizing(true); 
			 
		 
		 Rectangle 	m_rect = sby.getBounds();
		 
		 			m_rect.y = rect.y;
		 
		 			m_rect.height = m_height - rect.y;
		 
		 			
		 			sby.setBounds(m_rect);
		 			
		 			sby.update_bounds();
		 			
		 			sby.repaint();
				  		 
	 }
	 
	 
	 /*
	 public void update_scroll_bars() 
	 { 
		 
		 
		 //gl.sfn("update_scroll_bars(%s)",this.getIdo().getName()); 
		 
		 ScrBarX  sx = this.getDrago().getX_scroll_bar(); 
		 
		 ScrBarY  sy = this.getDrago().getY_scroll_bar(); 
		 
		 
		 if(sx == null && sy == null) 
		 { 
			 return; 
		 } 
		 
		//Shift position if needed. 
		Rectangle state = this.getDrago().getScroll_metric(); 
			 
			 
		// X - position. 
		 
		int m_x = (state.x  * gl.E_ERROR); 
		 
		int m_y = (state.y  * gl.E_ERROR); 
	 
		int m_offset_x = (state.width  * gl.E_ERROR); 
			 
		int m_offset_y = (state.height  * gl.E_ERROR); 
	 
		Dimension dim = Ru.norm_to_state_dim(state); 	 
		
		
		if(sx != null)
		sx.getStio().set_visible(dim.width == gl.E_OK); 
			 
		if(sy != null)
		sy.getStio().set_visible(dim.height == gl.E_OK); 
			 
					 
		int 		m_width  = this.getDecoro().getBounds().width - m_offset_x; 
			 
		int 		m_height = this.getDecoro().getBounds().height - m_offset_y; 
			 
		if(sx != null) 
		sx.getStio().set_skip_resizing(true); 
		 
		 
		Rectangle 	rc_width = sx.getDecoro().getBounds(); 
			 
					rc_width.x = m_x; 
						 
					rc_width.width = m_width - m_x; 
			 
					sx.setBounds(rc_width); 
			 
					sx.update_bounds(); 
			 
					sx.repaint(); 
			 
		 
		Rectangle 	rc_height = sy.getDecoro().getBounds(); 
			 
					rc_height.y = m_y; 
						 
					rc_height.height = m_height - m_y; 
			 
					 
					sy.getStio().set_skip_resizing(true); 
					 
					sy.setBounds(rc_height); 
			 
			 		sy.update_bounds();		 
			 
					sy.repaint(); 
	 } 

*/
	 public void repaint_childs() 
		{ 
		 
			 
				this.get_childs().forEach(ch->{ 

				ch.revalidate(); 
					 
				ch.repaint(); 
			 
		}); 
		 
	} 
	 
	 public void update_new_object(AtOm owner) 
	 { 
			//Rectangle rc = ApplicationA.getDio().get_desk_top().getSlio().get_selector(); 

		 if (ApplicationA.getRio() == null)
			 return;
		 
		 	Rectangle rc = owner.getSlio().get_selector(); 

			if (rc != null && !Ru.is_empty(rc) && StoreImplObject.get_selected().size() == gl.E_EMPTY) { 
				 
				AtOm   atom = ApplicationA.getRio().get_brew_instance(ApplicationA.cio.getActive_brew_object(),rc); 
				 
				if(atom == null) 
				{ 
					gl.sfn("Create new OBJECT ...%s",gl.S_ERROR); 
					 
					return; 
				} 
					 
				owner.insert_ipo(atom);
			} 
			 
	 } 
		
	 public void update_call_back(long counter)
	 {
		 	/*
		 	if(this.getOpaque() >=255)
				this.setOpaque(gl.E_EMPTY);
			else
			{
				int o = this.getOpaque();
				
				this.setOpaque(++o);
			}
		 	
		 	this.setCall_back_counter(counter);
		 	
		 	this.repaint();
		 	
		 	*/
		 	// ApplicationA.getDio().get_desk_top().repaint(this.getBounds());
		 	
			//gl.sfn("Call back...%s...%06d",this.getIdo().getName(),this.getCall_back_counter());
			
	}
} 

// /Users/alexplus/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk