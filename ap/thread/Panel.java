 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.thread; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Collections; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import java.util.Set; 
import java.util.Map.Entry; 
import java.util.stream.Collectors; 

import javax.swing.*; 

import ap.area.AreaManager; 
import ap.btn.Event; 
import ap.btn.Fire; 
import ap.btn.TPanel; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.utils.Biu; 
import ap.utils.MapUtils; 

import static java.util.stream.Collectors.*; 
import static java.util.Map.Entry.*; 
 


@SuppressWarnings("serial") 
public class Panel extends JPanel implements MouseListener, MouseMotionListener, KeyListener,ComponentListener, Comparable<Panel> { 

	 
	public static final int VISIBLE = 0; 
	 
	public static final int ENTERED = 1; 
	 
	public static final int EXITED = 2; 
	 
	public static final int MOVED = 3; 
	 
	public static final int DRAGGED = 4; 
	 
	public static final int LEFT_PRESSED = 5; 
	 
	public static final int RIGHT_PRESSED = 6; 
	 
	public static final int LEFT_CLICKED = 7; 
	 
	public static final int RIGHT_CLICKED = 8; 
	 
	public static final int LEFT_RELEASED = 9; 
	 
	public static final int RIGHT_RELEASED = 10; 
	 
	public static final int KEY_PRESSED 	= 11; 
	 
	public static final int KEY_RELEASED 	= 12; 
	 
	public static final int KEY_TYPED 		= 13; 
	 
	public static final int SELECTED		= 14; 
	 
	public static final int CLEAR_LOG 		= 99; 
	 
	public static int REPLICATE_WIDTH 		= 27; 
	 
	public static int PANEL_CLASS = 0; 
	 
	public static int CELL_PANEL_CLASS = 1; 
	 
	 
	 
	public static Map<Integer,Panel> items 	= new HashMap<Integer,Panel>(); 
	 
	public Map<Integer,Event> events  		= new HashMap<Integer,Event>(); 
	 
	public Map<Integer,Event> ops  			= new HashMap<Integer,Event>(); 
	 
	public static Map<Integer,Event> tasks 	= new HashMap<Integer,Event>(); 
	 
	public static Map<Integer,Event> points = new HashMap<Integer,Event>(); 
	 
	 
	 
	public static final int PREF_W = 640; 
	 
	public static final int PREF_H = 480; 
	 
	 
	private int state =gl.E_EMPTY; 
	 
	private int id; 
	 
	private int pid; 
	 
	private int gid; 
	 
	private int index; 
		 
	private String name; 
	 
	private String text; 
	 
	private Dimension preferedSize; 
	 
	private Panel desktop; 
	 
	 
	public Panel getDesktop() { 
		return desktop; 
	} 

	public void setDesktop(Panel desktop) { 
		this.desktop = desktop; 
	} 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

	public int getState() { 
		return state; 
	} 

	public void setState(int state) { 
		this.state = state; 
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

	public int getGid() { 
		return gid; 
	} 

	public void setGid(int gid) { 
		this.gid = gid; 
	} 

	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 

	public Dimension getPreferedSize() { 
		return preferedSize; 
	} 

	public void setPreferedSize(Dimension preferedSize) { 
		this.preferedSize = preferedSize; 
	} 

	public String getText() { 
		return text; 
	} 

	public void setText(String text) { 
		this.text = text; 
	} 

	public static String getEventName(int event) 
	{ 
		String r ; 

		switch (event) { 
		case VISIBLE: 
			r = "VISIBLE"; 
			break; 
		case ENTERED: 
			r = "ENTERED"; 
			break; 
		case EXITED: 
			r = "EXITED"; 
			break; 
		case MOVED: 
			r = "MOVED"; 
			break; 
		case DRAGGED: 
			r = "DRAGGED"; 
			break; 

		case LEFT_PRESSED: 
			r = "LEFT_PRESSED"; 
			break; 

		case RIGHT_PRESSED: 
			r = "RIGHT_PRESSED"; 
			break; 

		case LEFT_CLICKED: 
			r = "LEFT_CLICKED"; 
			break; 

		case RIGHT_CLICKED: 
			r = "RIGHT_CLICKED"; 
			break; 

		case LEFT_RELEASED: 
			r = "LEFT_RELEASED"; 
			break; 

		case RIGHT_RELEASED: 
			r = "RIGHT_RELEASED"; 
			break; 
			 
		case KEY_PRESSED: 
			r = "KEY_PRESSED"; 
			break; 

		case KEY_RELEASED: 
			r = "KEY_RELEASED"; 
			break; 
			 
		case KEY_TYPED: 
			r = "KEY_TYPED"; 
			break; 

		case SELECTED: 
			r = "SELECTED"; 
			break; 

			 
		default: 
			r = "NONE"; 
			break; 

		} 

		return r; 

	} 
	 
	public Panel() { 
		 
		 
		addMouseListener(this); 

		addMouseMotionListener(this); 

		addComponentListener(this); 

		addKeyListener(this); 

		 
		this.setId(IDGen.nextId()); 
		 
		} 
	 
	public Panel(String text) { 
		 
		this(); 
		 
		this.setText(text); 
	} 
	 
	public Panel(String text,Dimension size) { 
		 
		this(text); 
		 
		this.setPreferredSize(size); 
	} 
	 
	public Panel(Rectangle bounds) { 
		 
		this(new Dimension(bounds.width,bounds.height)); 
		 
		this.setBounds(bounds); 
	} 
	 
	public Panel(String text,Rectangle bounds) { 
		 
		this(text,new Dimension(bounds.width,bounds.height)); 
		 
		this.setBounds(bounds); 
	} 
	 
	 
	public Panel(Dimension size) { 
		 
		this.setPreferedSize(size); 

	} 
	 
	 
	public Dimension getPreferredSize() { 
		return this.getPreferedSize(); 
	} 

	@Override 
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		 
		if(this.text != null) 
		g.drawString(String.format("%s",this.getText()),5,this.getBounds().height/2); 
		 
	} 
	 
	 
		 
	public void fire(Event event) 
	{ 
		int in = this.getState(); 
		 
		Fire fire = event.getFire(); 
		 
		if(fire.isMode()) 
			in = Biu.ON(in,fire.getState()); 
		else 
			in = Biu.OFF(in,fire.getState()); 
				 
		this.setState(in); 
		 
		String msg = String.format("%s...fire...%s...state...%d...id...%d", 
				gl.S_OK, 
				getEventName(fire.getState()), 
				this.getState(), 
				this.getId()); 
		 
		 
		//gl.smn(msg); 
		 
		if(fire.isMode()) 
		{ 
					 
			// All events captured. 
			if(!ops.containsValue(event)) 
				ops.put(fire.getId(),event); 
		 
				// For each objects. 
				//showOps(MapUtils.compareByKey(ops)); 
			 
			 
			// 
			if(fire.getState() == Panel.LEFT_PRESSED) 
			{ 
				if(points.size() != gl.E_EMPTY) 
				{ 
					points.clear(); 
				} 
					points.put(this.getId(),event); 
					 
			} 
			else if(fire.getState() == Panel.DRAGGED) 
			{ 
				 
				/* 
				showOps(String.format("Points by  %s size map %d %s ", 
						Panel.getEventName(fire.getState()), 
						points.size(), 
						event.getMouse_event().getPoint().toString() 
						),MapUtils.compareByKey(points)); 
				*/ 
				 
				if(this.getIndex() != gl.E_EMPTY) 
				{ 
					 
				 
					int p0 = Panel.findKeyByIndex(gl.E_EMPTY); 
					 
					if(p0 != gl.E_ERROR) 
					{ 
					 
						 
						Panel p = items.get(p0); 
						 
						p.setIndex(this.getIndex()); 
						 
						this.putClientProperty("prev_index",this.getIndex()); 
						 
						this.setIndex(gl.E_EMPTY); 
						 
						this.getDesktop().setComponentZOrder(p,this.getIndex()); 
						 
						this.getDesktop().setComponentZOrder(this,gl.E_EMPTY); 
						 
						this.getDesktop().repaint(); 
						 
					 
					} 
					 
				} 
				 
				 
					//showOps(MapUtils.compareByKey(ops)); 
				 
					if(points.size() == gl.E_EMPTY) 
						return; 

					// Dragged. 
					int x =  points.get(this.getId()).getMouse_event().getPoint().x; 
					 
					int y = points.get(this.getId()).getMouse_event().getPoint().y; 
							 
					int dx = event.getMouse_event().getPoint().x -  x; 
					 
					int dy = event.getMouse_event().getPoint().y -  y; 
					 
					Rectangle rect = this.getBounds(); 
					 
							  rect.x += dx; 
							 
							  rect.y += dy; 
							 
					 this.setBounds(rect); 
					 
					 //gl.smn("Dx : " + dx + " Dy : " + dy); 
				 
			} 
			else if(fire.getState() == Panel.ENTERED) 
			{ 
				 
				tasks.put(fire.getId(),event); 
				 
				//showOps(MapUtils.compareByKey(tasks)); 
				 
			}else if(fire.getState() == Panel.EXITED) 
			{ 
				 
					 
			}else if(fire.getState() == Panel.LEFT_RELEASED) 
			{ 
		 
				ops.forEach((k,v)->{ 
					 
					tasks.put(k,v); 
				}); 
				 
				 
				// Clear of the tasks package. 
				tasks.clear(); 
				 
				ops.clear(); 
				 	 
			  }else if(fire.getState() == Panel.LEFT_CLICKED) 
			  { 
				 
				  Event l_event = event; 
				 
				  boolean bl_target = !Biu.TESTB(this.getState(),Panel.SELECTED); 
				 
				  l_event.setFire(new Fire(this.getId(),Panel.SELECTED,bl_target)); 
						 
				  fire(l_event); 
				 
				  //showStates(); 
				 
				  on_change_state(bl_target); 
				 
			  } 
			 
				// End triggers. 
					 
			 
			} 
		} 
	 
	 
	public void showOps(String header,Map<Integer,Event> map) 
	{ 
		gl.smn(""); 

		gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 
		 
		gl.smn(header); 
		 
		//gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 
		 
		showOps(map); 
		 
	} 
	public void showOps(Map<Integer,Event> map) 
	{ 
		 
		gl.smn(""); 

		gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 

		map.forEach((k,v)->{ 
			 
			gl.smn(v.toString()); 
			 
		}); 
		 
		gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 
		 
		gl.smn(""); 
		 
	} 
	 
	public static void showItems(Map<Integer,Panel> map) 
	{ 
		 
		gl.smn(""); 

		gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 

		map.forEach((k,v)->{ 
			 
			gl.smn(v.toString()); 
			 
		}); 
		 
		gl.smn(gl.replicate('-',REPLICATE_WIDTH)); 
		 
		gl.smn(""); 
		 
	} 
	 
	 
	public void showStates() 
	{ 
		String matrix = gl.replicate('0',13); 
		 
		for(int i=Panel.VISIBLE;i <= Panel.SELECTED;i++) 
		{ 
			String msg = String.format(" %s %s ",Panel.getEventName(i),Biu.TEST(this.getState(),i)); 
						 
			gl.smn(msg); 
			 
		} 
		 
	} 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 

		 
	} 

	public static void createAndShowGUI() { 

		JFrame frame = new JFrame("Test application"); 

		//frame.setLayout(null); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		 
		// Desktop. 

		Panel desktop = new Panel(new Dimension(PREF_W,PREF_H)); 
		 
		//desktop.setBounds(new Rectangle(50,50,100,100)); 
		 
		desktop.setBackground(Color.darkGray); 

		// Insert child items. 
		 
		AreaManager am = new AreaManager(desktop.getPreferedSize(),new Dimension(10,10)); 

		desktop.setLayout(null); 
		 
		int [] index = {0}; 
		 
		// Add to items collection. 
		am.getRects().forEach(a-> 
		{ 
			Panel cell = new Panel("",a); 

			cell.setBackground(gl.getRandomColor()); 
			 
			cell.setIndex(index[0]); 
			 
			cell.setText(String.format("%s [%d][%d] ","C",cell.getId(),cell.getIndex())); 
	 
			items.put(cell.getId(),cell); 
			 
			cell.setDesktop(desktop); 
		 
			index[0]++; 
			 
		}); 
		 
		 
		 
		 
		desktop.removeAll(); 
		 
		items.forEach((k,v)->{ 
		 
			desktop.add(v,v.getIndex()); 
		}); 
		 
		Map<Integer,Panel> sorted = new HashMap<Integer,Panel>(); 
		 
		items.forEach((k,v)->{ 
			 
			sorted.put(v.getIndex(),v); 
			 
		}); 
		 
		Map<Integer,Panel> sorted1 = sorted 
			        .entrySet() 
			        .stream() 
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) 
			        .collect( 
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, 
			                LinkedHashMap::new)); 
			 
	 
		 
		 
		showItems(sorted); 
		 
		showItems(sorted1); 
		 
		 
		 
		// 
		frame.getContentPane().add(desktop); 

		frame.pack(); 

		frame.setLocationRelativeTo(null); 

		frame.setVisible(true); 
		 
	} 
	 

	public static void add(int  id) 
	{ 
		 
		 
	} 
	 
	 
	public static void main(String[] args) { 

		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				createAndShowGUI(); 
			} 
		}); 
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

	@Override 
	public void keyTyped(KeyEvent e) { 
		 
		fire(new Event(new Fire(this.getId(),Panel.KEY_TYPED,true),e)); 
	} 

	@Override 
	public void keyPressed(KeyEvent e) { 

		 
		fire(new Event(new Fire(this.getId(),Panel.KEY_PRESSED,true),e)); 
		 
	} 

	@Override 
	public void keyReleased(KeyEvent e) { 

		 
		fire(new Event(new Fire(this.getId(),Panel.KEY_TYPED,false),e)); 
		 
		fire(new Event(new Fire(this.getId(),Panel.KEY_PRESSED,false),e)); 

		fire(new Event(new Fire(this.getId(),Panel.KEY_RELEASED,true),e)); 
		 
		 
	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 

		 
		if (SwingUtilities.isRightMouseButton(e)) { 
			 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_CLICKED,true),e)); 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_CLICKED,false),e)); 
			 
		} 
		else if (SwingUtilities.isLeftMouseButton(e)) { 

			fire(new Event(new Fire(this.getId(),Panel.LEFT_CLICKED,true),e)); 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_CLICKED,false),e)); 
			 
			} 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 

		 
		if (SwingUtilities.isRightMouseButton(e)) { 
			 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_RELEASED,false),e)); 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_PRESSED,true),e)); 
			 
		} 
		else if (SwingUtilities.isLeftMouseButton(e)) { 
			 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_RELEASED,false),e)); 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_PRESSED,true),e)); 
			 
			} 
		 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 

		if (SwingUtilities.isRightMouseButton(e)) { 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_RELEASED,true),e)); 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_RELEASED,false),e)); 
			fire(new Event(new Fire(this.getId(),Panel.RIGHT_PRESSED,false),e)); 
		} 
		else if (SwingUtilities.isLeftMouseButton(e)) { 
			 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_RELEASED,true),e)); 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_RELEASED,false),e)); 
			fire(new Event(new Fire(this.getId(),Panel.LEFT_PRESSED,false),e)); 
		 
			} 
		 
		fire(new Event(new Fire(this.getId(),Panel.DRAGGED,false),e)); 

	} 

	@Override 
	public void mouseDragged(MouseEvent e) { 

		fire(new Event(new Fire(this.getId(),Panel.DRAGGED,true),e)); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		fire(new Event(new Fire(this.getId(),Panel.MOVED,true),e)); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		 
		fire(new Event(new Fire(this.getId(),Panel.EXITED,false),e)); 
		 
		fire(new Event(new Fire(this.getId(),Panel.ENTERED,true),e)); 
		 
		this.requestFocus(); 
		 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		fire(new Event(new Fire(this.getId(),Panel.EXITED,true),e)); 
		 
		fire(new Event(new Fire(this.getId(),Panel.ENTERED,false),e)); 
		 
		 
	} 
	 
	@Override 
	public boolean equals(Object obj) 
	{ 
		Panel evt = (Panel)obj; 
		 
		if( 
			evt.getIndex() == this.getIndex()  && 
			evt.getId() == this.getId() 
		   ) 
		return true; 
		else 
		return false; 
			 
	} 
	@Override 
	public int hashCode() 
	{ 
		String msg = String.format("%d%d",this.getId(),this.getIndex()); 
		 
		return msg.hashCode(); 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%d %d %s ",this.getId(),this.getIndex(),this.getText() ); 
	} 

	 
	@Override 
	public int compareTo(Panel o) { 
		return this.getIndex() > o.getIndex() ? 1 
				: (this.getIndex() < o.getIndex() ? -1 : 0); 
	} 
	 
	 
	public static int findKeyByIndex(int index) 
	{ 
		int []  result = { gl.E_ERROR }; 
		 
		items.forEach((k,v)->{ 
			 
			if(v.getIndex()== index) 
			{ 
				result[0] = k; 
			} 
		}); 
		 
			return result[0]; 
	} 
	 
	public void on_change_state(boolean mode) 
	{ 
		//gl.smn("State : " + mode); 
		 
		this.repaint(); 
		 
	} 
	 
	 

} 
//Revision : 10.09.2018 12:49:16 
