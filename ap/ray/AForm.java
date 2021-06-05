 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.BasicStroke; 
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GridLayout; 
import java.awt.KeyboardFocusManager; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.util.HashSet; 
import java.util.Iterator; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import java.util.Set; 

import javax.swing.JFrame; 
import javax.swing.JMenu; 
import javax.swing.JMenuItem; 
import javax.swing.JPanel; 
import javax.swing.JPopupMenu; 
import javax.swing.JScrollPane; 
import javax.swing.ScrollPaneConstants; 
import javax.swing.SwingUtilities; 


public class AForm extends APanelj implements ActionListener,KeyListener { 
 
		 
	 
	public static Dimension SCREEN_DIMENSION; 
	 
	boolean bl_write_ready = true; 
	 
	public static ARect selection = new ARect(); 
	 
	public static ARect selection_prev = new ARect(); 
 
	public AForm() { 
		 
		this.setBl_h(true); 

		this.setBl_v(true); 
		 
		this.setName("Desktop"); 
		 
	} 
 

 

public APanelj  getObjectById(Map<Integer,APanelj> p_map, int pid) 
{ 
		 
		 
		 
		Iterator it = p_map.entrySet().iterator(); 
		 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,APanelj> pair = (Map.Entry<Integer,APanelj>)it.next(); 
			 
			APanelj apj = pair.getValue(); 
			 
			if(apj.getId()== pid) 
			{ 
				return apj; 
			} 
			 
		} 
		 
				 
			return null; 
	} 

public APanelj  getObjectByIdSet(HashSet<Actable> p_map, int pid) 
{ 
		 
		 
		 
		for(Actable a : p_map) 
		{ 
			APanelj b = (APanelj)a; 
			 
			if(b.getId()== pid) 
			{ 
				return b; 
			} 
			 
		} 
						 
			return null; 
	} 

public APanelj  findParent(HashSet<Actable> p_map,Rectangle p_rect) 
{ 

	    Rectangle r = this.getBounds(); 
	 
		for (Actable a : p_map) { 
			APanelj b = (APanelj) a; 

			if (r.contains(p_rect)) { 
				return b; 
			} 

		} 

		return null; 
} 


public int  getNextId(Map<Integer,APanelj> p_map) 
{ 
		 
		 
		 
		int max_id = gl.E_EMPTY; 
		 
		Iterator it = p_map.entrySet().iterator(); 
		 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,APanelj> pair = (Map.Entry<Integer,APanelj>)it.next(); 
			 
			APanelj apj = pair.getValue(); 
			 
			if(apj.getId() > max_id) 
			{ 
				max_id = apj.getId(); 
			} 
			 
		} 
		 
				 
			return max_id+1; 
	} 

public int  getNextIdSet(HashSet<Actable> p_map) 
{ 
		 
		 
		 
		int max_id = gl.E_EMPTY; 
		 
		 
		for(Actable a : p_map) 
		{ 
			APanelj b = (APanelj)a; 
			 
			if(b.getId() > max_id) 
			{ 
				max_id = b.getId(); 
			} 
			 
			 
		} 
				 
				 
			return max_id+1; 
	} 



public int  getNextKey(Map<Integer,APanelj> p_map) 
{ 
		 
		 
		 
		int max_id = gl.E_EMPTY; 
		 
		Iterator it = p_map.entrySet().iterator(); 
		 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,APanelj> pair = (Map.Entry<Integer,APanelj>)it.next(); 
			 
			Integer key = pair.getKey(); 
			 
			if(key   > max_id) 
			{ 
				max_id = key; 
			} 
			 
		} 
		 
				 
			return max_id+1; 
	} 


	public  static void  writeComponents() 
	{ 
		 
		if (gl.DeleteFile(home)) 
		 { 
			// gl.smn("<AForm.writeComponents(Store file : " + home  + " is successfully deleted . )>"); 
		 } 
		 
		for(Actable a : items) 
		{ 
			APanelj b = (APanelj)a; 
			 
			b.write(); 
		} 
	} 

	 

	public boolean clearSelectedComponents() { 

		for (Actable a : items) { 
			APanelj b = (APanelj) a; 

			b.setSelected(false); 
			 
		} 
		 

		 
		this.repaint(); 

		return true; 
	} 
	 

public void drawStroke(Graphics pg, float pwidth) 
	{ 

	 
	  Graphics2D g = (Graphics2D)pg; 
	 
	  Color old = g.getColor(); 

	  g.setColor(Color.white); 

	  Stroke oldStroke = g.getStroke(); 

	  float dash1[] = { 10.0f }; 

	  BasicStroke dashed =   new BasicStroke(pwidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f); 

	  g.setStroke(dashed); 
	 
	  Rectangle rect = this.selection; 
	 
	  g.drawRect(rect.x,rect.y,rect.width,rect.height); 
	 
	  g.setStroke(oldStroke); 

	  g.setColor(old); 


	} 
public void paint(Graphics g) { 
 
	  super.paint(g); 
	 
	  this.drawStroke(g,2.0f); 
	 
 
} 
public void mouseDragged(MouseEvent e) { 
	 
	 
	  	super.mouseDragged(e); 
	  	 
	 
	 
	  if(!this.isCaptured()) 
		{ 
			this.setCaptured(true); 
			 
			this.setFocusable(true); 
			 
			this.mouseStart = e.getPoint(); 
			 
			this.setDx(gl.E_EMPTY); 
			 
			this.setDy(gl.E_EMPTY); 
			 
			 
			this.selection.x = gl.E_EMPTY; 
			 
			this.selection.y = gl.E_EMPTY; 
			 
			this.selection.width = gl.E_EMPTY; 
			 
			this.selection.height = gl.E_EMPTY; 
			 
						 
		} 
		else 
		{ 
			int dx = (e.getX() - this.mouseStart.x) ; 
			 
			int dy = (e.getY() - this.mouseStart.y) ; 
			 

			this.selection.x = this.mouseStart.x; 
			 
			this.selection.y = this.mouseStart.y; 
			 
			this.selection.width = dx; 
			 
			this.selection.height = dy; 
			 
			 
			this.repaint(); 
			 
		} 
	 
	 
} 
		 
public void mouseClicked(MouseEvent e) { 
	 
	  	 
	 
	  if (SwingUtilities.isRightMouseButton(e)) 
		{ 
			gl.smn("mouseClicked(isRightMouseButton)"); 
			 
			this.setSelected(false); 
			 
		} 
		else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			gl.smn("mouseClicked(isLeftMouseButton)"); 
			 
			this.clearSelectedComponents(); 
			 
			this.setSelected(true); 

		} 
	 
} 
	public void mouseReleased(MouseEvent e) 
	{ 
		 
		if (SwingUtilities.isRightMouseButton(e)) 
		{ 
			gl.smn("The best place for popup ..."); 
			 
			JPopupMenu popup = this.createPopup(); 
			 
			this.setComponentPopupMenu(popup); 
		} 
		else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			gl.smn("The best place for check selection ..."); 
			 
			 
			this.selection_prev.setBounds(this.selection.getBounds()); 
			 
			this.selection.clear(); 
			 
			 
			getSelectedComponents(true); 
			 
			if(selected_items.size()==gl.E_EMPTY && !ARect.isEmpty(selection_prev.getBounds())) 
			{ 
				gl.smn("The try to create NEW object ..."); 
				 
				AreaManagerEx amex = new AreaManagerEx(selection_prev.getBounds().width,selection_prev.getBounds().height, 3,3,3,3,0,0); 
				 
				APanelj fly = new APanelj(amex,gl.getRandomColor()); 
				 
					    fly.setBounds(selection_prev.getBounds()); 
					 
					    //gl.smn("Bounds for new object : " + fly.getBounds().toString()); 
					 
					    fly.setId(getNextIdSet(items)); 
					 
					    fly.setPid(gl.E_ERROR); 
					 
					    items.add(fly); 
					 
					    if(!ARect.isEmpty(fly.getBounds())) 
					    { 
					    	this.addPure(fly); 
					 
					    	fly.write(); 
					    } 
					 
					    this.setComponentZOrder(fly,gl.E_EMPTY); 
					 
			} 
			else 
			{ 
			    gl.smn("<mouseReleased(Left)> SELECTED : " + selected_items.size() + " objects."); 
				 
				// For objects set flag @selected. 
			 
			    this.setSelectedComponents(); 
			} 
			 
		} 
		 
		 
		this.repaint();	 
		 
				 
	} 

	 public boolean isBl_write_ready() { 
			return bl_write_ready; 
		} 


		public void setBl_write_ready(boolean bl_write_ready) { 
			this.bl_write_ready = bl_write_ready; 
		} 


public static void main(String[] args) { 

	    String home = System.getProperty("user.dir") + "\\panel_collector.xml"; 
		 
		JFrame frame = new JFrame("Template"); 

		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		 
frame.addWindowListener( new WindowAdapter() { 
@Override 
public void windowClosing(WindowEvent we) { 
 
	 
	//gl.smn("<frame.windowClosing(Exit event)>"); 
	 
	AForm.writeComponents(); 
	 
System.exit(gl.E_EMPTY); 
	 
 
} 
} ); 
		 
		AForm mainForm = new AForm(); 
		 
		mainForm.setBackground(Color.cyan); 
		 
		mainForm.setLayout(null); 
		 
if (mainForm.loadItemsSet()) 
	mainForm.loadComponents(mainForm); 
		 
		frame.add(mainForm); 
		 
		APanelj.aform = mainForm; 
		 
		 
		SCREEN_DIMENSION = gl.getScreenDimension(); 
		 
		Dimension f_dim = new Dimension(SCREEN_DIMENSION.width/4,SCREEN_DIMENSION.height/4); 
		 
		frame.setSize(f_dim); 
		 
		frame.setResizable(true); 
		 
		frame.setLocationRelativeTo(null); 
		 
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
		frame.setVisible(true); 
 
} 
 
 

@Override 
public void actionPerformed(ActionEvent e) { 
	//  Auto-generated method stub 
	//gl.smn("AForm popup invoked : " + e.getActionCommand()); 
	 
	if(e.getActionCommand().equalsIgnoreCase("delete_item")) 
	{ 
		    gl.smn("<mn_Delete> Delete action invoked .."); 
		 
			deleteSelectedComponents(); 
			 
			this.repaint(); 
			 
			writeComponents(); 
			 
			 
		 
	} 
	 
} 




@Override 
public void keyTyped(KeyEvent e) { 
	//  Auto-generated method stub 
	 
} 




@Override 
public void keyPressed(KeyEvent e) { 
	//  Auto-generated method stub 
	 
} 




@Override 
public void keyReleased(KeyEvent e) { 
	//  Auto-generated method stub 
	 
} 

	 
	public static boolean loadItemsSet() { 
		 
		 
		try { 
			if(!gl.isaFile(APanelj.home)) 
			{ 
				gl.smn("File repositary is not exists."); 
				 
				return false; 
				 
			} 
		} catch (FileNotFoundException e1) { 
			 
			return false; 
		} 
		 
		 
		String source = ""; 
		 
		int PID=0,ID=1,COLOR=2,BOUNDS=3,AMEX=4,CLASS_NAME=5,OBJECT_NAME=6,DENY_H=7,DENY_V=8,DENY_SCROLL=9,DENY_BOUNDS=10,GUM=11; 

		try { 

			source = gl.getFileAsStringScanner(APanelj.home); 

			String arr[] = source.split("\r\n"); 

			for (int i = 0; i < arr.length; i++) { 
				 
				String a[] = arr[i].split(";"); 

				APid pid = new APid(); 

				AId id = new AId(); 

				AColor color = new AColor(); 

				ABounds bounds = new ABounds(); 
				 
				AreaManagerEx amex = new AreaManagerEx(); 

				ADeny deny_h = new ADeny(); 

				ADeny deny_v = new ADeny("deny_v"); 

				ADeny deny_scroll = new ADeny("scroll"); 

				ADeny deny_bounds = new ADeny("deny_out_of_bounds"); 

				ADeny class_name = new ADeny("class_name"); 
				 
				ADeny object_name = new ADeny("name"); 

				ADeny gum = new ADeny("gum"); 
				 
				if ( 
					(!pid.parse(a[PID]) || 
					(!id.parse(a[ID]) || 
					(!color.parse(a[COLOR]) || 
					(!bounds.parse(a[BOUNDS]) || 
					(!amex.parse(a[AMEX]) || 
					(!class_name.parse(a[CLASS_NAME]) || 
					(!object_name.parse(a[OBJECT_NAME]) || 
					(!deny_h.parse(a[DENY_H]) || 
					(!deny_v.parse(a[DENY_V]) || 
					(!deny_bounds.parse(a[DENY_BOUNDS]) ||							 
					(!deny_scroll.parse(a[DENY_SCROLL]) || 
					(!gum.parse(a[GUM]) 
							 
					)))))))))))) 
					 
					) 
					gl.sm("<APanelj.loadITems(Error while parsing properties from strings.)>"); 
				 
				 
				if(!amex.getValue()) 
					gl.smn("Get error while getValue() from AreaManagerEx"); 
				else if(!color.getValue()) 
					gl.smn("Get error while getValue() from AColor"); 
				 
				 
				APanelj apj = new APanelj(amex,new Color(color.getRed(),color.getGreen(),color.getBlue())); 

				 
				//gl.smn("Read out :" + deny_bounds.toStringValueOnly()); 
				 
				apj.setProperties(pid, id,bounds,object_name, deny_h,deny_v, deny_scroll,deny_bounds, gum); 
 
				items.add(apj); 
			} 

			gl.smn("<AForm.loadItemsSet(Ok)>Loaded : " + arr.length + " rows."); 

		} catch (IOException e) { 

			gl.smn("<APanelj.loadItems(Exception)> items : " + e.toString()); 

		} 
		 
			return true; 

	} 

public JPopupMenu createPopup() 
{ 
	 
			JPopupMenu popup = new JPopupMenu(); 

			JMenu mn_align = new JMenu("Aligns.."); 

			JMenu mn_intervals = new JMenu("Intervals.."); 

			JMenu mn_align_h = new JMenu("Horizontal .."); 

			JMenu mn_align_v = new JMenu("Vertical .."); 

			JMenu mn_int_h = new JMenu("Horizontal .."); 

			JMenu mn_int_v = new JMenu("Vertical .."); 

			// Horizontal 

			JMenuItem mn_align_left = new JMenuItem("Left"); 

			mn_align_left.setActionCommand("align_left"); 

			mn_align_left.addActionListener(this); 

			mn_align_h.add(mn_align_left); 

			JMenuItem mn_center_h = new JMenuItem("Center"); 

			mn_center_h.setActionCommand("align_center_h"); 

			mn_center_h.addActionListener(this); 

			mn_align_h.add(mn_center_h); 

			JMenuItem mn_align_right = new JMenuItem("Right"); 

			mn_align_right.setActionCommand("align_right"); 

			mn_align_right.addActionListener(this); 

			mn_align_h.add(mn_align_right); 

			// Vertical 
			JMenuItem mn_align_top = new JMenuItem("Top"); 

			mn_align_top.setActionCommand("align_top"); 

			mn_align_top.addActionListener(this); 

			mn_align_v.add(mn_align_top); 

			JMenuItem mn_center_v = new JMenuItem("Center"); 

			mn_center_v.setActionCommand("align_center_v"); 

			mn_center_v.addActionListener(this); 

			mn_align_v.add(mn_center_v); 

			JMenuItem mn_align_bottom = new JMenuItem("Bottom"); 

			mn_align_bottom.setActionCommand("align_bottom"); 

			mn_align_bottom.addActionListener(this); 

			mn_align_v.add(mn_align_bottom); 

			// Intervals horizontal 

			JMenuItem mn_int_h_min = new JMenuItem("Min"); 

			mn_int_h_min.setActionCommand("interval_h_min"); 

			mn_int_h_min.addActionListener(this); 

			mn_int_h.add(mn_int_h_min); 

			JMenuItem mn_int_h_avg = new JMenuItem("Avg"); 

			mn_int_h_avg.setActionCommand("interval_h_avg"); 

			mn_int_h_avg.addActionListener(this); 

			mn_int_h.add(mn_int_h_avg); 

			JMenuItem mn_int_h_max = new JMenuItem("Max"); 

			mn_int_h_max.setActionCommand("interval_h_max"); 

			mn_int_h_max.addActionListener(this); 

			mn_int_h.add(mn_int_h_max); 

			// Intervals vertical 

			JMenuItem mn_int_v_min = new JMenuItem("Min"); 

			mn_int_v_min.setActionCommand("interval_v_min"); 

			mn_int_v_min.addActionListener(this); 

			mn_int_v.add(mn_int_v_min); 

			JMenuItem mn_int_v_avg = new JMenuItem("Avg"); 

			mn_int_v_avg.setActionCommand("interval_v_avg"); 

			mn_int_v_avg.addActionListener(this); 

			mn_int_v.add(mn_int_v_avg); 

			JMenuItem mn_int_v_max = new JMenuItem("Max"); 

			mn_int_v_max.setActionCommand("interval_v_max"); 

			mn_int_v_max.addActionListener(this); 

			mn_int_v.add(mn_int_v_max); 


			mn_intervals.add(mn_int_h); 

			mn_intervals.add(mn_int_v); 

			popup.add(mn_intervals); 

			 
			mn_align.add(mn_align_h); 

			mn_align.add(mn_align_v); 

			popup.add(mn_align); 
			 
			popup.addSeparator(); 
			 
			JMenuItem mn_delete = new JMenuItem("Delete"); 

			mn_delete.setActionCommand("delete_item"); 

			mn_delete.addActionListener(this); 

			popup.add(mn_delete);			 
			 

			return popup; 
	 

} 
			 
} 

// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
