package ap.uat.bean; 


import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.JTextField; 



public class TextFieldImplA extends JTextField  implements MouseListener,MouseMotionListener{ 

	private XTextFieldA proxy; 
	 
	public XTextFieldA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTextFieldA owner) { 
		this.proxy = owner; 
	} 

	public TextFieldImplA() { 
		 
	} 

	 

	public TextFieldImplA(String text) { 
		super(text); 
		 
		setupListeners() ; 
	} 
	 
	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	 
	public TextFieldImplA getInstance(XTextFieldA owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new TextFieldImplA(text); 
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

