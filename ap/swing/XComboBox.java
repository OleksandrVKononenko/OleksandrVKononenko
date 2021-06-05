 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.util.Vector; 

import javax.swing.ComboBoxModel; 
import javax.swing.DefaultListModel; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 


public class XComboBox extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ComboBoxImpl button ; 
	 
	 
	public ComboBoxImpl getButton() { 
		return button; 
	} 

	public void setButton(ComboBoxImpl button) { 
		this.button = button; 
	} 

	public XComboBox() { 
		addComponents(); 
	} 

	public XComboBox(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XComboBox(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ComboBox_%d",this.getIdo().getId())); 
		 
		Vector<String> vector = new Vector<String>(); 
		 
		for(int i=0;i<1000;i++) 
			vector.add(i,gl.sf("ComboBoxItem_%03d",i)); 
				 
		ComboBoxImpl cmb = new ComboBoxImpl(vector); 
				 
		cmb.setName(this.getName()); 
		 
		this.setButton(cmb); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getButton().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getButton().setToolTipText(this.toString()); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 
