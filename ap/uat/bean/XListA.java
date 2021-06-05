 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseWheelEvent; 

import javax.swing.SwingUtilities; 
import javax.swing.table.DefaultTableModel; 

import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XListA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private ListImplA list ; 
	 
	private AtOm scroll_pane; 
	 
	 
	public AtOm getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(AtOm scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 
	 
	 
	public ListImplA getTable() { 
		return list; 
	} 

	public void setTable(ListImplA list) { 
		this.list = list; 
	} 

	public XListA() { 
		addComponents(); 
	} 

	public XListA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		this.addMouseMotionListener(this); 
		 
		this.addMouseWheelListener(this); 
				 
		addComponents(); 
		 
	} 

		 
	public void addComponents() 
	{ 
		//Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("List_%d",this.getIdo().getId())); 
		 
		this.setTable(ListImplA.get_instance()); 
		 
		this.getTable().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getTable()); 
		 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getTable().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getTable().setToolTipText(this.toString()); 
		 
	} 
	 
	 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	public static XListA get_instance(Rectangle rect) 
	{ 
		return new XListA(rect); 
	} 
	 
	public static XListA get_instance(Rectangle rect,int align) 
	{ 
	 
		XListA 	atom = XListA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 
		 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 


} 

