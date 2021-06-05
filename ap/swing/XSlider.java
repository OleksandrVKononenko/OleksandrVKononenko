 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 


public class XSlider extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private SliderImpl Slider ; 
	 
	 
	public SliderImpl getSlider() { 
		return Slider; 
	} 

	public void setSlider(SliderImpl Slider) { 
		this.Slider = Slider; 
	} 

	public XSlider() { 
		add_components(); 
	} 

	public XSlider(Rectangle rect) { 
		 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 

	public XSlider(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void add_components() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Slider_%d",this.getIdo().getId())); 
		 
		this.setSlider(new SliderImpl()); 
		 
		this.getSlider().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getSlider()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getSlider().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getSlider().setToolTipText(this.toString()); 
		 
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
	 
	 
	public static XSlider get_instance(Rectangle rect) 
	{ 
		return new XSlider(rect); 
	} 

} 
