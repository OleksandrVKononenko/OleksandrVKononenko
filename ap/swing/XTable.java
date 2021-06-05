 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.SwingUtilities; 
import javax.swing.table.DefaultTableModel; 

import ap.global.gl; 


public class XTable extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private TableImpl table ; 
	 
	private PanelXml scroll_pane; 
	 
	 
	public PanelXml getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(PanelXml scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 
	 
	 
	public TableImpl getTable() { 
		return table; 
	} 

	public void setTable(TableImpl button) { 
		this.table = button; 
	} 

	public XTable() { 
		addComponents(); 
	} 

	public XTable(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XTable(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		//Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Table_%d",this.getIdo().getId())); 
		 
		this.setTable(TableImpl.get_instance()); 
		 
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
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	 /* 
	public static XScrollPane get_instance(Rectangle rect) 
	{ 
		return XScrollPane.get_instance(new XTable(rect),rect); 
	} 
	 
	public static XScrollPane get_instance(PanelXml view,Rectangle rect) 
	{ 
		return XScrollPane.get_instance(view,rect); 
		 
	} 

	*/ 
} 

