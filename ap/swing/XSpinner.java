 
 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.CU; 


public class XSpinner extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private SpinnerImpl button ; 
	 
	 
	public SpinnerImpl getButton() { 
		return button; 
	} 

	public void setButton(SpinnerImpl button) { 
		this.button = button; 
	} 

	public XSpinner() { 
		addComponents(); 
	} 

	public XSpinner(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XSpinner(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Spinner_%d",this.getIdo().getId())); 

		SpinnerImpl si = SpinnerImpl.get_instance(this,new String[] {"1","2","3"}); 
				 
		si.setName(this.getName()); 
		 
		this.setButton(si); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
		 
		this.add(this.getButton()); 
		 
		 
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
	 
	public static XSpinner get_instance(Rectangle rect) 
	{ 
	 
		return new XSpinner(rect); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 

