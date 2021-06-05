 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 



public class XTree extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private TreeImpl button ; 
	 
	private PanelXml scroll_pane; 
	 
	 
	public PanelXml getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(PanelXml scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 

	public TreeImpl getTree() { 
		 
		return button; 
	} 

	public void setTree(TreeImpl button) { 
		 
		this.button = button; 
	} 

	public XTree() { 
		addComponents(); 
	} 

	public XTree(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XTree(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Tree_%d",this.getIdo().getId())); 
		 
		this.setTree(new TreeImpl()); 
		 
		this.getTree().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getTree()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getTree().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getTree().setToolTipText(this.toString()); 
		 
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
	 
	 
	public static XTree get_instance(Rectangle rect) 
	{ 
		return new XTree(rect); 
	} 

} 
