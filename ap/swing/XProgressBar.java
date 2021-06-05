 
 
 
 
 
 
 
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


public class XProgressBar extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ProgressBarImpl ProgressBar ; 
	 
	 
	public ProgressBarImpl getProgressBar() { 
		return ProgressBar; 
	} 

	public void setProgressBar(ProgressBarImpl ProgressBar) { 
		this.ProgressBar = ProgressBar; 
	} 

	public XProgressBar() { 
		add_components(); 
	} 

	public XProgressBar(Rectangle rect) { 
		 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 
	 
	 

	public XProgressBar(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void add_components() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ProgressBar_%d",this.getIdo().getId())); 
		 
		this.setProgressBar(new ProgressBarImpl()); 
		 
		this.getProgressBar().setOwner(this); 
		 
		this.getProgressBar().setStringPainted(true); 
		 
		this.getProgressBar().setMinimum(0); 
		 
		this.getProgressBar().setMaximum(100); 
		 
		 
		this.add(BorderLayout.CENTER,this.getProgressBar()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getProgressBar().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getProgressBar().setToolTipText(this.toString()); 
		 
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
	 
	 
	public static XProgressBar get_instance(Rectangle rect) 
	{ 
			return new XProgressBar(rect); 
	} 
	 
	public static XProgressBar get_instance_inf(Rectangle rect) 
	{ 
		XProgressBar xpb = new XProgressBar(rect); 
				 
		xpb.getProgressBar().setMaximum(gl.E_EMPTY); 
		 
		xpb.getProgressBar().setStringPainted(false); 
		 
		xpb.getProgressBar().setIndeterminate(true); 
		 
		return xpb; 
	} 
	 
	 

} 
