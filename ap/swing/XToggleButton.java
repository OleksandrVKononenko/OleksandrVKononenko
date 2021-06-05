 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 


import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.utils.GU; 

public class XToggleButton extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ToggleButtonImpl button ; 
	 
	 
	public ToggleButtonImpl getButton() { 
		return button; 
	} 

	public void setButton(ToggleButtonImpl button) { 
		this.button = button; 
	} 

	public XToggleButton() { 
		addComponents(); 
	} 

	public XToggleButton(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XToggleButton(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 

		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ToggleButton_%d",this.getIdo().getId())); 

		this.setButton(new ToggleButtonImpl(this.getName())); 
		 
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
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 

