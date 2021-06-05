 
package ap.swing; 


import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.JTextField; 



public class TextFieldImpl extends JTextField  implements MouseListener,MouseMotionListener{ 

	private XTextField proxy; 
	 
	public XTextField getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTextField owner) { 
		this.proxy = owner; 
	} 

	public TextFieldImpl() { 
		 
	} 

	 

	public TextFieldImpl(String text) { 
		super(text); 
		 
		setupListeners() ; 
	} 
	 
	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	 
	public TextFieldImpl getInstance(XTextField owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new TextFieldImpl(text); 
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

	 
	 
} 

