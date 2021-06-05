 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Stroke; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 
import javax.swing.JCheckBox; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 




public class CheckBoxImpl extends JCheckBox  implements MouseListener,MouseMotionListener{ 

	private XCheckBox proxy; 
	 
	public XCheckBox getProxy() { 
		return proxy; 
	} 

	public void setOwner(XCheckBox owner) { 
		this.proxy = owner; 
	} 

	public CheckBoxImpl() { 
		 
	} 

	public CheckBoxImpl(Icon icon) { 
		super(icon); 
		 
	} 

	public CheckBoxImpl(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	public CheckBoxImpl(Action a) { 
		super(a); 
		 
	} 

	public CheckBoxImpl(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public CheckBoxImpl getInstance(XCheckBox owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new CheckBoxImpl(text); 
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

	 
	@Override 
	public void paint(Graphics g) 
	{ 
		super.paint(g); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

	 
	 
} 
