 
/** 
* 
*/ 
package ap.swing; 
 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.CU; 


public class XTextField extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private TextFieldImpl button ; 
	 
	 
	public TextFieldImpl getButton() { 
		return button; 
	} 

	public void setButton(TextFieldImpl button) { 
		this.button = button; 
	} 

	public XTextField() { 
		addComponents(); 
	} 

	public XTextField(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XTextField(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 

		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TextField_%d",this.getIdo().getId())); 
			 
		this.setButton(new TextFieldImpl(this.getName())); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		super.mouseEntered(e); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getButton().setToolTipText(this.toString()); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 
