package ap.uat.bean;


import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 
import javax.swing.JTextArea; 


public class TextAreaImpl extends JTextArea  implements MouseListener,MouseMotionListener,MouseWheelListener{ 

	private XTextAreaA proxy; 
	 
	public XTextAreaA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTextAreaA owner) { 
		this.proxy = owner; 
	} 

	public TextAreaImpl() { 
		 
	} 

	 

	public TextAreaImpl(String text) { 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addMouseWheelListener(this); 
		 
	} 
	 
	 
	public TextAreaImpl getInstance(XTextAreaA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new TextAreaImpl(text); 
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

	 
	 
	public static void main(String[] args) { 
		 

	} 

	 
	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
		 
		this.getProxy().mouseWheelMoved(e); 
		 
	} 

	 
	 
} 

