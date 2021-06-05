 
package ap.uat.bean; 


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
public class LabelImplA extends JLabel  implements MouseListener,MouseMotionListener/*,MouseWheelListener*/{ 

	private XLabelA proxy; 
	 
	public XLabelA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XLabelA owner) { 
		this.proxy = owner; 
	} 

	public LabelImplA() { 
		 
	} 

	public LabelImplA(Icon icon) { 
		super(icon); 
		 
	} 

	public LabelImplA(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		//this.addMouseWheelListener(this); 
		 
	} 
	 
	 
	public LabelImplA get_instance(XLabelA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new LabelImplA(text); 
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

