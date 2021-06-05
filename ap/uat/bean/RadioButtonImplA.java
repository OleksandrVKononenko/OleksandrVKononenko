 
package ap.uat.bean; 


import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JRadioButton; 



@SuppressWarnings("serial") 
public class RadioButtonImplA extends JRadioButton  implements MouseListener,MouseMotionListener{ 

	private XRadioButtonA proxy; 
	 
	public XRadioButtonA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XRadioButtonA owner) { 
		this.proxy = owner; 
	} 

	public RadioButtonImplA() { 
		 
	} 

	public RadioButtonImplA(Icon icon) { 
		super(icon); 
		 
	} 

	public RadioButtonImplA(String text) { 
		super(text); 
		 
		setupListeners() ; 
	} 
	 
	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	public RadioButtonImplA(Action a) { 
		super(a); 
		 
	} 

	public RadioButtonImplA(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public RadioButtonImplA getInstance(XRadioButtonA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new RadioButtonImplA(text); 
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
