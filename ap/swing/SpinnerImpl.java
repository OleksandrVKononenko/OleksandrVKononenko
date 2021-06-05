 
package ap.swing; 

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Stroke; 
import java.awt.event.ActionEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JButton; 
import javax.swing.JSpinner; 
import javax.swing.SpinnerListModel; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 




@SuppressWarnings("serial") 
public class SpinnerImpl extends JSpinner  implements MouseListener,MouseMotionListener{ 

	 
	public static MouseListener ML; 
	 
	private XSpinner proxy; 
	 
	public XSpinner getProxy() { 
		return proxy; 
	} 

	public void setOwner(XSpinner owner) { 
		this.proxy = owner; 
	} 

	public SpinnerImpl() { 
		 
	} 
	 

	public SpinnerImpl(SpinnerListModel model) { 
		 
		super(model); 
		 
		setup_listeners(); 
	} 
	 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		ML = this; 
		 
	} 
	 
	 
	public static SpinnerImpl get_instance(XSpinner owner,String [] text) 
	{ 
		 
		SpinnerListModel model = new SpinnerListModel(text); 
		 
		SpinnerImpl si  = new SpinnerImpl(model); 
		 
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
