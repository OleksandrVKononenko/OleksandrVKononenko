 
package ap.swing; 

import java.awt.Dimension; 
import java.awt.GridLayout; 
import java.awt.event.KeyEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.ImageIcon; 
import javax.swing.JComponent; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTabbedPane; 

public class TabbedPaneImpl extends JTabbedPane implements MouseListener,MouseMotionListener{ 

	private XTabbedPane proxy; 
	 
	public XTabbedPane getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTabbedPane owner) { 
		this.proxy = owner; 
	} 

	 
	public TabbedPaneImpl() { 
		 
		setup_listeners() ; 
		 
		ImageIcon icon = null; 
		 
		JComponent panel1 = makeTextPanel("Panel #1"); 
		 
this.addTab("Tab 1", icon, panel1, 
"Does nothing"); 
this.setMnemonicAt(0, KeyEvent.VK_1); 
 
JComponent panel2 = makeTextPanel("Panel #2"); 
this.addTab("Tab 2", icon, panel2, 
"Does twice as much nothing"); 
this.setMnemonicAt(1, KeyEvent.VK_2); 
 
JComponent panel3 = makeTextPanel("Panel #3"); 
this.addTab("Tab 3", icon, panel3, 
"Still does nothing"); 
this.setMnemonicAt(2, KeyEvent.VK_3); 
 
JComponent panel4 = makeTextPanel( 
"Panel #4 (has a preferred size of 410 x 50)."); 
panel4.setPreferredSize(new Dimension(410, 50)); 
this.addTab("Tab 4", icon, panel4, 
"Does nothing at all"); 
this.setMnemonicAt(3, KeyEvent.VK_4); 
 
//The following line enables to use scrolling tabs. 
this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); 
		 
	} 

	public TabbedPaneImpl(int tabPlacement) { 
		super(tabPlacement); 
		 
	} 

	public TabbedPaneImpl(int tabPlacement, int tabLayoutPolicy) { 
		super(tabPlacement, tabLayoutPolicy); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		this.getProxy().mouseDragged(e); 
		 
		 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		this.getProxy().mouseClicked(e); 
		 
		this.getProxy().repaint_childs(); 
				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		this.getProxy().mousePressed(e); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		this.getProxy().mouseReleased(e); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		this.getProxy().mouseEntered(e); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		this.getProxy().mouseExited(e); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
			 
	} 

	public static TabbedPaneImpl get_instance() { 

		return new TabbedPaneImpl(); 

	} 

	protected JComponent makeTextPanel(String text) { 
JPanel panel = new JPanel(false); 
JLabel filler = new JLabel(text); 
filler.setHorizontalAlignment(JLabel.CENTER); 
panel.setLayout(new GridLayout(1, 1)); 
panel.add(filler); 
return panel; 
} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
