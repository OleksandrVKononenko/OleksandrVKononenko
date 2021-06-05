 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 



public class XTabbedPane extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private TabbedPaneImpl TabbedPane ; 
	 
	 
	public TabbedPaneImpl getTabbedPane() { 
		return TabbedPane; 
	} 

	public void setTabbedPane(TabbedPaneImpl TabbedPane) { 
		this.TabbedPane = TabbedPane; 
	} 

	public XTabbedPane() { 
		add_components(); 
	} 

	public XTabbedPane(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 

	public XTabbedPane(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void add_components() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TabbedPane_%d",this.getIdo().getId())); 
		 
		this.setTabbedPane(TabbedPaneImpl.get_instance()); 
		 
		this.getTabbedPane().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getTabbedPane()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getTabbedPane().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getTabbedPane().setToolTipText(this.toString()); 
		 
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
	 
	 
	public static XTabbedPane get_instance(Rectangle rect) 
	{ 
		return new XTabbedPane(rect); 
	} 

} 

