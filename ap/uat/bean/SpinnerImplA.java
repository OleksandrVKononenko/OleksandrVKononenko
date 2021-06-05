 
package ap.uat.bean; 

import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.JSpinner; 
import javax.swing.SpinnerListModel; 



@SuppressWarnings("serial") 
public class SpinnerImplA extends JSpinner  implements MouseListener,MouseMotionListener{ 

	 
	public static MouseListener ML; 
	 
	private XSpinnerA proxy; 
	 
	public XSpinnerA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XSpinnerA owner) { 
		this.proxy = owner; 
	} 

	public SpinnerImplA() { 
		 
	} 
	 

	public SpinnerImplA(SpinnerListModel model) { 
		 
		super(model); 
		 
		setup_listeners(); 
	} 
	 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		ML = this; 
		 
	} 
	 
	 
	public static SpinnerImplA get_instance(XSpinnerA owner,String [] text) 
	{ 
		 
		SpinnerListModel model = new SpinnerListModel(text); 
		 
		SpinnerImplA si  = new SpinnerImplA(model); 
		 
		si.setOwner(owner); 
		 
		 
		return si; 
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
