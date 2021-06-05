 
package ap.uat.bean; 

import java.awt.event.ActionListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Vector; 
import javax.swing.ComboBoxModel; 
import javax.swing.JComboBox; 
import javax.swing.SwingUtilities; 
import ap.uat.ApplicationA; 


public class ComboBoxImplA extends JComboBox  implements MouseListener,MouseMotionListener,ActionListener{ 

	 
	private static final long serialVersionUID = 1L; 
	 
	private XComboBoxA proxy; 
	 
	public XComboBoxA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XComboBoxA owner) { 
		this.proxy = owner; 
	} 

	public ComboBoxImplA() { 
		 
		super(); 
		 
		setup_listeners() ; 
	} 
	
	
	public ComboBoxImplA(ComboBoxModel model) { 
		 
		super(model); 
		 
		setup_listeners() ; 
		 
	} 

	 
	public ComboBoxImplA(String [] text) { 
		 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public ComboBoxImplA(Vector<String> vector) { 
		 
		super(vector); 
		 
		setup_listeners() ; 
		 
		this.setOwner(this.getProxy()); 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		//this.addActionListener(this); 
				 
	} 
	 
	 
	public void add_action_listener(ActionListener listener) 
	{ 
		this.addActionListener(listener); 
	} 
		 
	 
	public ComboBoxImplA get_instance(XComboBoxA owner,String [] text) 
	{ 
		this.setOwner(owner); 
		 
		return new ComboBoxImplA(text); 
	} 
	 
	public ComboBoxImplA get_instance(Vector<String> vector) 
	{ 
		return new ComboBoxImplA(vector); 
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
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				 
						ApplicationA.get_instance(); 
						 
						 
			} 
		}); 

	} 

	 
	 
} 
