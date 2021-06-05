 
package ap.uat.bean; 


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
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XScrollPaneA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private ScrollPaneImplA button ; 
	 
	 
	public ScrollPaneImplA getButton() { 
		return button; 
	} 

	public void setButton(ScrollPaneImplA button) { 
		this.button = button; 
	} 

	public XScrollPaneA() { 
		addComponents(); 
	} 

	public XScrollPaneA(Rectangle rect) { 
		super(rect); 
		 
		addComponents(); 
		 
	} 
	 
	public XScrollPaneA(AtOm view,Rectangle rect) { 
		 
		this(rect); 
		 
		addComponents(view); 
		 
		 
		if(view instanceof XTreeA ) 
		{ 
					((XTreeA)view).setScroll_pane(this); 
		} 
		else if( view instanceof XTableA)			 
		{ 
					((XTableA)view).setScroll_pane(this); 
		} else if(view instanceof XListA) 
		{ 
			((XListA)view).setScroll_pane(this); 
		} 

		 
				 
				 
		 
	} 

		 
	public void addComponents() 
	{ 
		//Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ScrollPane_%d",this.getIdo().getId())); 
		 
		ScrollPaneImplA spi = new ScrollPaneImplA(); 
		 
		spi.setProxy(this); 
				 
		this.setButton(spi); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
		 
	} 
	 
	public void addComponents(AtOm view) 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ScrollPane_%d",this.getIdo().getId())); 
		 
		ScrollPaneImplA sp = new ScrollPaneImplA(view); 
		 
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
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	 
	public static XScrollPaneA get_instance(Rectangle rect) 
	{ 
		return new XScrollPaneA(rect); 
	} 

	public static XScrollPaneA get_instance(AtOm view,Rectangle rect) 
	{ 
		 
		return new XScrollPaneA(view,rect); 
	} 
	 
	public static XScrollPaneA get_instance(AtOm view,Rectangle rect,int align) 
	{ 
	 
		XScrollPaneA 	atom = XScrollPaneA.get_instance(view,rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 
	 
	public static XScrollPaneA get_instance(Rectangle rect,int align) 
	{ 
	 
		XScrollPaneA 	atom = XScrollPaneA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 
} 
