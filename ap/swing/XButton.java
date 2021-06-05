 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 



public class XButton extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ButtonImpl button ; 
	 
	 
	public ButtonImpl getButton() { 
		return button; 
	} 

	public void setButton(ButtonImpl button) { 
		this.button = button; 
	} 

	public XButton() { 
		addComponents(); 
	} 

	public XButton(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XButton(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Button_%d",this.getIdo().getId())); 
		 
		this.setButton(new ButtonImpl(this.getName())); 
		 
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
	 
	 
	public static XButton get_instance(Rectangle rect) 
	{ 
		return new XButton(rect); 
	} 

} 
