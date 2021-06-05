 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.SwingUtilities; 
import javax.swing.table.DefaultTableModel; 

import ap.global.gl; 


public class XList extends PanelXml { 

	private static final long serialVersionUID = 1L; 
	 
	private ListImpl list ; 
	 
	private PanelXml scroll_pane; 
	 
	 
	public PanelXml getScroll_pane() { 
		 
		return scroll_pane; 
	} 

	public void setScroll_pane(PanelXml scroll_pane) { 
		 
		this.scroll_pane = scroll_pane; 
	} 
	 
	 
	public ListImpl getTable() { 
		return list; 
	} 

	public void setTable(ListImpl list) { 
		this.list = list; 
	} 

	public XList() { 
		addComponents(); 
	} 

	public XList(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XList(Dimension size) { 
		super(size); 
		 
	} 
	 
	public void addComponents() 
	{ 
		//Application.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("List_%d",this.getIdo().getId())); 
		 
		this.setTable(ListImpl.get_instance()); 
		 
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
	 
		 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				Application.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 


} 

