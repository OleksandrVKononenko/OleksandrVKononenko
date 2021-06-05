 
 
package ap.swing; 


import java.awt.event.ActionEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Vector; 

import javax.swing.ComboBoxModel; 
import javax.swing.JComboBox; 
import javax.swing.ListModel; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 



public class ComboBoxImpl extends JComboBox  implements MouseListener,MouseMotionListener{ 

	 
	private static final long serialVersionUID = 1L; 
	 
	private XComboBox proxy; 
	 
	public XComboBox getProxy() { 
		return proxy; 
	} 

	public void setOwner(XComboBox owner) { 
		this.proxy = owner; 
	} 

	public ComboBoxImpl() { 
		 
		super(); 
		 
		setup_listeners() ; 
	} 

	public ComboBoxImpl(ComboBoxModel model) { 
		 
		super(model); 
		 
		setup_listeners() ; 
		 
	} 

	 
	public ComboBoxImpl(String [] text) { 
		 
		super(text); 
		 
		setup_listeners() ; 
	} 
	 
	public ComboBoxImpl(Vector<String> vector) { 
		 
		super(vector); 
		 
		setup_listeners() ; 
		 
		this.setOwner(this.getProxy()); 
	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addActionListener(this); 
	} 
	 
	 
	public ComboBoxImpl get_instance(XComboBox owner,String [] text) 
	{ 
		this.setOwner(owner); 
		 
		return new ComboBoxImpl(text); 
	} 
	 
	public ComboBoxImpl get_instance(Vector<String> vector) 
	{ 
		return new ComboBoxImpl(vector); 
	} 
	 
	 
	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
			JComboBox box = (JComboBox)e.getSource(); 
			 
			String item = (String)box.getSelectedItem(); 
 
			gl.tracex(new Object(){},gl.sf("Owner...%s...selected...%s",this.getName(),item)); 
 
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
				 
						Application.get_instance(); 
						 
						 
			} 
		}); 

	} 

	 
	 
} 
