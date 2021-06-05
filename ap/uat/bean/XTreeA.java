 
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



public class XTreeA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private TreeImplA button ; 
	 
	private AtOm scroll_pane; 
	 
	 
	public AtOm getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(AtOm scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 

	public TreeImplA getTree() { 
		 
		return button; 
	} 

	public void setTree(TreeImplA button) { 
		 
		this.button = button; 
	} 

	public XTreeA() { 
		addComponents(); 
	} 

	public XTreeA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	 
	 
	public void addComponents() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Tree_%d",this.getIdo().getId())); 
		 
		this.setTree(new TreeImplA()); 
		 
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
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	 
	public static XTreeA get_instance(Rectangle rect) 
	{ 
		return new XTreeA(rect); 
	} 
	 
	 
	public static XTreeA get_instance(Rectangle rect,int align) 
	{ 
	 
		XTreeA 	atom = XTreeA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 

} 

