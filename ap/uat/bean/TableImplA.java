 
package ap.uat.bean; 


import java.awt.BorderLayout; 
import java.awt.Graphics; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Vector; 

import javax.swing.JTable; 
import javax.swing.ListSelectionModel; 
import javax.swing.table.DefaultTableModel; 
import javax.swing.table.TableColumnModel; 
import javax.swing.table.TableModel; 

@SuppressWarnings("serial") 
public class TableImplA extends JTable implements MouseListener,MouseMotionListener { 

	private XTableA proxy; 
	 
	public XTableA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTableA owner) { 
		this.proxy = owner; 
	} 

	 
	public TableImplA() { 
		 
	} 

	public TableImplA(TableModel dm) { 
		super(dm); 
		 
		setupListeners(); 
		 
	} 

	public TableImplA(TableModel dm, TableColumnModel cm) { 
		super(dm, cm); 
		 
	} 

	public TableImplA(int numRows, int numColumns) { 
		super(numRows, numColumns); 
		 
	} 

	public TableImplA(Vector rowData, Vector columnNames) { 
		super(rowData, columnNames); 
		 
	} 

	public TableImplA(Object[][] rowData, Object[] columnNames) { 
		super(rowData, columnNames); 
		 
	} 

	public TableImplA(TableModel dm, TableColumnModel cm, ListSelectionModel sm) { 
		super(dm, cm, sm); 
		 
	} 

	 
	 
	public static void main(String[] args) { 
		 

	} 
	 
	 
	public static TableImplA get_instance() 
	{ 
		 
			Object [] model = new Object[] {"a","b","c"}; 
			 
			Object data[][] = new Object[][] { 
			{ "s1", "s1", "s1" }, { "s2", "s2", "s2" }, { "s3", "s3", "s3" } }; 
			 
			DefaultTableModel dtm = new DefaultTableModel(data , model); 
			 
			TableImplA table = new TableImplA(dtm); 
			 
			table.setLayout(new BorderLayout()); 
			 
			table.setAutoCreateRowSorter(true); 
			 
			table.updateUI(); 
			 
			table.revalidate(); 
			 
			//table.setFillsViewportHeight(true); 
			 
			return table; 
		 
		} 

	 
	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseDragged(e); 
		else 
			this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 

		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseMoved(e); 
		else 
			this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseClicked(e); 
		else 
			this.getProxy().mouseClicked(e); 

				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mousePressed(e); 
		else 
			this.getProxy().mousePressed(e); 

	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseReleased(e); 
		else 
			this.getProxy().mouseReleased(e); 

		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseEntered(e); 
		else 
			this.getProxy().mouseEntered(e); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 

		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseExited(e); 
		else 
			this.getProxy().mouseExited(e); 
		 
		this.getProxy().repaint_childs(); 
	} 

	 
	@Override 
	public void paint(Graphics g) 
	{ 
		super.paint(g); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 
	 

	} 


