 
package ap.uat.bean; 


import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import javax.swing.table.DefaultTableModel; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XTableA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private TableImplA table ; 
	 
	private AtOm scroll_pane; 
	 
	 
	public AtOm getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(AtOm scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 
	 
	 
	public TableImplA getTable() { 
		return table; 
	} 

	public void setTable(TableImplA button) { 
		this.table = button; 
	} 

	public XTableA() { 
		addComponents(); 
	} 

	public XTableA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	 
	public void addComponents() 
	{ 
		//ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Table_%d",this.getIdo().getId())); 
		 
		this.setTable(TableImplA.get_instance()); 
		 
		this.getTable().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getTable()); 
		 
		this.add(BorderLayout.NORTH,this.getTable().getTableHeader()); 
		 
		 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		//this.getTable().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getTable().setToolTipText(this.toString()); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		//this.revalidate(); 
		 
	} 
	 
		 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	public static XTableA get_instance(Rectangle rect) 
	{ 
		return new XTableA(rect); 
	} 
	 
	public static XTableA get_instance(Rectangle rect,int align) 
	{ 
	 
		XTableA 	atom = XTableA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 
	 

	 
} 

