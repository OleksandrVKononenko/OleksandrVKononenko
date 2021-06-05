 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 



public class XTabbedPaneA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private TabbedPaneImplA TabbedPane ; 
	 
	 
	public TabbedPaneImplA getTabbedPane() { 
		return TabbedPane; 
	} 

	public void setTabbedPane(TabbedPaneImplA TabbedPane) { 
		this.TabbedPane = TabbedPane; 
	} 

	public XTabbedPaneA() { 
		add_components(); 
	} 

	public XTabbedPaneA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 

	 
	 
	public void add_components() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TabbedPane_%d",this.getIdo().getId())); 
		 
		this.setTabbedPane(TabbedPaneImplA.get_instance()); 
		 
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
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	 
	public static XTabbedPaneA get_instance(Rectangle rect) 
	{ 
		return new XTabbedPaneA(rect); 
	} 
	 
		 
	public static XTabbedPaneA get_instance(Rectangle rect,int align) 
	{ 
	 
		XTabbedPaneA 	atom = XTabbedPaneA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 

} 

