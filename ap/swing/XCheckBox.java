 
 
/** 
* 
*/ 
package ap.swing; 
 

import java.awt.BorderLayout; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 


public class XCheckBox extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private CheckBoxImpl button ; 
	 
	 
	public CheckBoxImpl getButton() { 
		return button; 
	} 

	public void setButton(CheckBoxImpl button) { 
		this.button = button; 
	} 

	public XCheckBox() { 
		add_components(); 
	} 

	public XCheckBox(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 

	public XCheckBox(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void add_components() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("CheckBox_%d",this.getIdo().getId())); 
		 
		this.setButton(new CheckBoxImpl(this.getName())); 
		 
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
