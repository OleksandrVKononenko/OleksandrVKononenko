 
package ap.uat.bean; 


import java.awt.BorderLayout; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 
import java.util.Vector; 
import javax.swing.DefaultListModel; 
import javax.swing.JList; 
import javax.swing.ListModel; 
import javax.swing.table.DefaultTableModel; 

import ap.global.gl; 

public class ListImplA extends JList implements MouseListener,MouseMotionListener,MouseWheelListener{ 

	private XListA proxy; 
	 
	public XListA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XListA owner) { 
		this.proxy = owner; 
	} 
	 
	public ListImplA() { 
		 
	} 

	public ListImplA(ListModel dataModel) { 
		super(dataModel); 
		 
		setup_listeners(); 
		 
	} 

	public ListImplA(Object[] listData) { 
		super(listData); 
		 
	} 

	public ListImplA(Vector listData) { 
		super(listData); 
		 
	} 

	public static void main(String[] args) { 
		 
	} 
	 
	public static ListImplA get_instance() 
	{ 
		 
		 
			DefaultListModel<String> dlm = new DefaultListModel<String>(); 
			 
			for(int i=0;i<100;i++) 
				dlm.add(i,gl.sf("List_item_%03d",i)); 
			 
			ListImplA table = new ListImplA(dlm); 
						 
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

	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseWheelMoved(e); 
		//else 
		//	this.getProxy().mouseWheelMoved(e); 
	 
		 
	} 

	 

} 
