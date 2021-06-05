 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Vector; 

import javax.swing.DefaultListModel; 
import javax.swing.JList; 
import javax.swing.ListModel; 
import javax.swing.table.DefaultTableModel; 

import ap.global.gl; 

public class ListImpl extends JList implements MouseListener,MouseMotionListener{ 

	private XList proxy; 
	 
	public XList getProxy() { 
		return proxy; 
	} 

	public void setOwner(XList owner) { 
		this.proxy = owner; 
	} 
	 
	public ListImpl() { 
		 
	} 

	public ListImpl(ListModel dataModel) { 
		super(dataModel); 
		 
		setup_listeners(); 
		 
	} 

	public ListImpl(Object[] listData) { 
		super(listData); 
		 
	} 

	public ListImpl(Vector listData) { 
		super(listData); 
		 
	} 

	public static void main(String[] args) { 
		 
	} 
	 
	public static ListImpl get_instance() 
	{ 
		 
		 
			DefaultListModel<String> dlm = new DefaultListModel<String>(); 
			 
			for(int i=0;i<100;i++) 
				dlm.add(i,gl.sf("List_item_%03d",i)); 
			 
			ListImpl table = new ListImpl(dlm); 
						 
			table.updateUI(); 
					 
			table.setLayout(new BorderLayout()); 
				 
			return table; 
		 
		} 
	 
	public void setup_listeners() 
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
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 

		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseExited(e); 
		else 
			this.getProxy().mouseExited(e); 
	} 

	 

} 
