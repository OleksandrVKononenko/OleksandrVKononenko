 
 
 
 
 
 
 
 
 
 
 
 
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

import javax.swing.JScrollPane; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 


public class XTextArea extends PanelXml  { 

	private static final long serialVersionUID = 1L; 
	 
	private TextAreaImpl button ; 
	 
	private JScrollPane sp ; 
	 
	public TextAreaImpl getButton() { 
		return button; 
	} 

	public void setButton(TextAreaImpl button) { 
		this.button = button; 
	} 

	public XTextArea() { 
		addComponents(); 
	} 

	public XTextArea(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XTextArea(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TextArea_%d",this.getIdo().getId())); 
			 
		this.setButton(new TextAreaImpl(this.getName())); 
		 
		this.getButton().setEditable(true); 
		 
		this.getButton().setOwner(this); 
		 
		sp = new JScrollPane(this.getButton()); 
		 
		this.add(BorderLayout.CENTER,sp); 
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
