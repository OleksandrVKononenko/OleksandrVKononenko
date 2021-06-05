 
package ap.uat.bean; 

import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 
import javax.swing.JCheckBox; 


@SuppressWarnings("serial") 
public class CheckBoxImplA extends JCheckBox  implements MouseListener,MouseMotionListener{ 

	private XCheckBoxA proxy; 
	 
	public XCheckBoxA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XCheckBoxA owner) { 
		this.proxy = owner; 
	} 

	public CheckBoxImplA() { 
		 
	} 

	public CheckBoxImplA(Icon icon) { 
		super(icon); 
		 
	} 

	public CheckBoxImplA(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	 
		 
	} 
	 
	public CheckBoxImplA(Action a) { 
		super(a); 
		 
	} 

	public CheckBoxImplA(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public CheckBoxImplA getInstance(XCheckBoxA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new CheckBoxImplA(text); 
	} 
	 
	 
	@Override 
	public void paint(Graphics g) 
	{ 
		super.paint(g); 
		 
		//this.getProxy().repaint_childs(); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mouseClicked(e); 
		 
		this.getProxy().repaint_childs(); 
				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mousePressed(e); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mouseReleased(e); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		 
		if(this.getProxy() == null) 
			return; 
		 
		 
			this.getProxy().mouseEntered(e); 
		 
			this.getProxy().repaint_childs(); 
		 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		if(this.getProxy() == null) 
			return; 
		 
		this.getProxy().mouseExited(e); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 

	 
	 
	public static void main(String[] args) { 
		 

	} 

	 
	 
} 

