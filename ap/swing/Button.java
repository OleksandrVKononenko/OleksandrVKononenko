 
package ap.swing; 

import static java.util.stream.Collectors.toMap; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Collections; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.SwingUtilities; 

import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.thread.Panel; 

@SuppressWarnings("serial") 
public class Button extends JButton implements MouseListener,MouseMotionListener,Comparable<Button>{ 
	 
public static Map<Integer,Button> items 	= new HashMap<Integer,Button>(); 
	 
	private int id; 
	 
	private int pid; 
	 
	private int index; 
	 
	private Panel desktop; 
	 
	private TPanel owner; 
	 
	private Point startPos = null; 
	 
	private int dx; 
	 
	private int dy; 
	 

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

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

	public Panel getDesktop() { 
		return desktop; 
	} 

	public void setDesktop(Panel desktop) { 
		this.desktop = desktop; 
	} 

	public TPanel getOwner() { 
		return owner; 
	} 

	public void setOwner(TPanel owner) { 
		this.owner = owner; 
	} 

	public Point getStartPos() { 
		return startPos; 
	} 

	public void setStartPos(Point startPos) { 
		this.startPos = startPos; 
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

	public Button() { 
		 
		this.setId(IDGen.nextId()); 
		 
		this.addMouseListener(this); 
		 
		this.addMouseMotionListener(this); 
		 
		//this.setContentAreaFilled(false); 
		 
		this.setDoubleBuffered(true); 
	} 

	public Button(Rectangle rect) { 
		 
		this(); 
		 
		this.setBounds(rect); 
	} 

	public Button(Icon icon) { 
		super(icon); 
		 
	} 

	public Button(String text) { 
		super(text); 
		 
	} 

	public Button(Action a) { 
		super(a); 
		 
	} 

	public Button(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public static void main(String[] args) { 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				createAndShowGUI(); 
			} 
		}); 

	} 
		 
	@Override 
	public int compareTo(Button o) { 
		return this.getIndex() > o.getIndex() ? 1 : (this.getIndex() < o 
				.getIndex() ? -1 : 0); 
	} 

	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		if(this.getIndex() != gl.E_EMPTY) 
		{ 
			 
			int p0 = findKeyByIndex(gl.E_EMPTY); 
			 
			if(p0 != gl.E_ERROR) 
			{ 
			 
				 
				Button p = items.get(p0); 
				 
				p.setIndex(this.getIndex()); 
				 
				this.putClientProperty("prev_index",this.getIndex()); 
				 
				this.setIndex(gl.E_EMPTY); 
				 
				this.getDesktop().setComponentZOrder(p,this.getIndex()); 
				 
				this.getDesktop().setComponentZOrder(this,gl.E_EMPTY); 
						 
								 			 
			} 
			 
		} 
		 
		 
		this.setDx((e.getX() - startPos.x)); 
		 
		this.setDy((e.getY() - startPos.y)); 
		 
		 
		 
		if(this.getOwner() != null) 
		{ 
			Rectangle rect = this.getOwner().getBounds(); 
			 
			 
			rect.x += this.getDx() ; 
			 
			rect.y += this.getDy(); 
			 
			this.getOwner().setBounds(rect); 
			 
		} 
		else 
		{ 
			Rectangle rect = this.getBounds(); 
						 
			rect.x += this.getDx() ; 
			 
			rect.y += this.getDy(); 
			 
			this.setBounds(rect); 
			 
		} 
	 
				 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		 
	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		startPos = e.getPoint(); 
		 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		startPos = null; 
		 
	} 

	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
			 
	} 

	 
	@Override 
	public void mouseExited(MouseEvent e) { 
	 
		 
	} 
	 
	 
	 
	public int findKeyByIndex(int index) 
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
	 
	public static void createAndShowGUI() { 

		JFrame frame = new JFrame("Test application"); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		 
		// Desktop. 

		Panel desktop = new Panel(new Dimension(Panel.PREF_W,Panel.PREF_H)); 
		 
		desktop.setBackground(Color.darkGray); 

		// Insert child items. 
			 
		AreaManager am = new AreaManager(new Dimension(desktop.getPreferedSize().width*10,desktop.getPreferedSize().height*10),new Dimension(10,10)); 

		//desktop.setLayout(null); 
		 
		desktop.setLayout(new BorderLayout()); 

		JScrollPane js = new JScrollPane(desktop); 
		 
		//js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		 
		//js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		 
		 
		int [] index = {0}; 
		 
		// Add to items collection. 
		am.getRects().forEach(a-> 
		{ 
			Button cell = new Button(a); 
 
			cell.setIndex(index[0]); 
			 
			cell.setText(gl.sf("Btn_%d",index[0])); 
	 
			items.put(cell.getId(),cell); 
			 
			cell.setDesktop(desktop); 
		 
			index[0]++; 
			 
		}); 
		 
	 
		desktop.removeAll(); 
		 
		items.forEach((k,v)->{ 
		 
			desktop.add(v,v.getIndex()); 
		}); 
		 
		Map<Integer,Button> sorted = new HashMap<Integer,Button>(); 
		 
		items.forEach((k,v)->{ 
			 
			sorted.put(v.getIndex(),v); 
			 
		}); 
		 
		Map<Integer,Button> sorted1 = sorted 
			        .entrySet() 
			        .stream() 
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) 
			        .collect( 
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, 
			                LinkedHashMap::new)); 
			 
	 
		 
		 
		 
		// 
		 
		 
		frame.getContentPane().add(BorderLayout.CENTER,js); 

		frame.pack(); 

		frame.setLocationRelativeTo(null); 

		frame.setVisible(true); 
		 
		 
	} 

} 
