 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 



public class XToolBar extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ToolBarImpl button ; 
	 
	private PanelXml scroll_pane; 
	 
	 
	public PanelXml getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(PanelXml scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 
	 
	 
	 
	public ToolBarImpl getToolBar() { 
		return button; 
	} 

	public void setToolBar(ToolBarImpl button) { 
		this.button = button; 
	} 

	public XToolBar() { 
		addComponents(); 
	} 

	public XToolBar(Rectangle rect) { 
		super(rect); 
		 
		addComponents(); 
		 
	} 

	public XToolBar(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ToolBar_%d",this.getIdo().getId())); 
		 
		this.setToolBar(new ToolBarImpl(this.getName())); 
		 
		this.getToolBar().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getToolBar()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getToolBar().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getToolBar().setToolTipText(this.toString()); 
		 
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
	 
	 
	public static XToolBar get_instance(Rectangle rect) 
	{ 
		return new XToolBar(rect); 
	} 

} 
