 
 
 
 
 
 
 
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


public class XScrollPane extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ScrollPaneImpl button ; 
	 
	 
	public ScrollPaneImpl getButton() { 
		return button; 
	} 

	public void setButton(ScrollPaneImpl button) { 
		this.button = button; 
	} 

	public XScrollPane() { 
		addComponents(); 
	} 

	public XScrollPane(Rectangle rect) { 
		super(rect); 
		 
		addComponents(); 
		 
	} 
	 
	public XScrollPane(PanelXml view,Rectangle rect) { 
		 
		this(rect); 
		 
		addComponents(view); 
		 
		if(view instanceof XTree ) 
		{ 
					((XTree)view).setScroll_pane(this); 
		} else if( view instanceof XTable)			 
			{ 
					((XTable)view).setScroll_pane(this); 
			} else if(view instanceof XToolBar) 
			{ 
					((XToolBar)view).setScroll_pane(this); 
			} else if(view instanceof XList) 
	{ 
			((XList)view).setScroll_pane(this); 
	} 

				 
				 
		 
	} 

	public XScrollPane(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		//Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ScrollPane_%d",this.getIdo().getId())); 
		 
		ScrollPaneImpl spi = new ScrollPaneImpl(); 
		 
		spi.setProxy(this); 
				 
		this.setButton(spi); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
		 
	} 
	 
	public void addComponents(PanelXml view) 
	{ 
		Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ScrollPane_%d",this.getIdo().getId())); 
		 
		ScrollPaneImpl sp = new ScrollPaneImpl(view); 
		 
		this.setButton(sp); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(this.getButton()); 
		 
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
	 
	 
	public static XScrollPane get_instance(Rectangle rect) 
	{ 
		return new XScrollPane(rect); 
	} 

	public static XScrollPane get_instance(PanelXml view,Rectangle rect) 
	{ 
		 
		return new XScrollPane(view,rect); 
	} 
} 
