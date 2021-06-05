 
 
package ap.swing; 

import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 


public class ButtonImpl extends JButton  implements MouseListener,MouseMotionListener{ 

	private XButton proxy; 
	 
	public XButton getProxy() { 
		return proxy; 
	} 

	public void setOwner(XButton owner) { 
		this.proxy = owner; 
	} 

	public ButtonImpl() { 
		 
	} 

	public ButtonImpl(Icon icon) { 
		super(icon); 
		 
	} 

	public ButtonImpl(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	 
		 
	} 
	 
	public ButtonImpl(Action a) { 
		super(a); 
		 
	} 

	public ButtonImpl(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public ButtonImpl getInstance(XButton owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new ButtonImpl(text); 
	} 
	 
	 
	@Override 
	public void paint(Graphics g) 
	{ 
		super.paint(g); 
		 
		this.getProxy().repaint_childs(); 
		 
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

	 
	 
	public static void main(String[] args) { 
		 

	} 

	 
	 
} 
