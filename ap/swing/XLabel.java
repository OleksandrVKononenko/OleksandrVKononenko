 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 

import javax.swing.SwingUtilities; 

import ap.global.gl; 


public class XLabel extends PanelXml /*implements MouseWheelListener*/{ 

	private static final long serialVersionUID = 1L; 
	 
	private LabelImpl button ; 
	 
	 
	public LabelImpl getButton() { 
		return button; 
	} 

	public void setButton(LabelImpl button) { 
		this.button = button; 
	} 

	public XLabel() { 
		addComponents(); 
	} 

	public XLabel(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XLabel(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Label_%d",this.getIdo().getId())); 
		 
		this.setButton(new LabelImpl(this.getName())); 
		 
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
	 
	/* 
	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
	 
		super.mouseWheelMoved(e); 
		 
		gl.smn(gl.sf("*Class...%s...wheel event...%3d",this.getClass().getSimpleName(),gl.getRandomInt(999))); 
		 
	} 
	 
	*/ 
	 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 
