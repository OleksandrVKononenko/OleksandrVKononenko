 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Stroke; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 
import javax.swing.JLabel; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 



@SuppressWarnings("serial") 
public class LabelImpl extends JLabel  implements MouseListener,MouseMotionListener/*,MouseWheelListener*/{ 

	private XLabel proxy; 
	 
	public XLabel getProxy() { 
		return proxy; 
	} 

	public void setOwner(XLabel owner) { 
		this.proxy = owner; 
	} 

	public LabelImpl() { 
		 
	} 

	public LabelImpl(Icon icon) { 
		super(icon); 
		 
	} 

	public LabelImpl(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		//this.addMouseWheelListener(this); 
		 
	} 
	 
	 
	public LabelImpl get_instance(XLabel owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new LabelImpl(text); 
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
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		this.getProxy().mouseExited(e); 
	} 

	/* 
	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
	 
		gl.smn(gl.sf("Class...%s...wheel event...%3d",this.getClass().getSimpleName(),gl.getRandomInt(999))); 
		 
		this.getProxy().mouseWheelMoved(e); 
		 
	} 
	*/ 
	 
	public static void main(String[] args) { 
		 

	} 

	 
	 
} 

