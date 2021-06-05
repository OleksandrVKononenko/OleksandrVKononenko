 
package ap.uat.bean; 

import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton;

import ap.global.gl; 


@SuppressWarnings("serial") 
public class ButtonImplA extends JButton  implements MouseListener,MouseMotionListener{ 

	private XButtonA proxy; 
	 
	public XButtonA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XButtonA owner) { 
		this.proxy = owner; 
	} 

	public ButtonImplA() { 
		 
	} 

	public ButtonImplA(Icon icon) { 
		super(icon); 
		 
	} 

	public ButtonImplA(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	 
		 
	} 
	 
	public ButtonImplA(Action a) { 
		super(a); 
		 
	} 

	public ButtonImplA(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public ButtonImplA getInstance(XButtonA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new ButtonImplA(text); 
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
		
		gl.sfn("Revalidate desktop from...%s",this.getProxy().getIdo().getName());
				 
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

