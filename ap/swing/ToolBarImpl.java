 
 
 
 
 
 
 
package ap.swing; 

import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.JToolBar; 

@SuppressWarnings("serial") 
public class ToolBarImpl extends JToolBar  implements MouseListener,MouseMotionListener{ 

	private XToolBar proxy; 
	 
	public XToolBar getProxy() { 
		return proxy; 
	} 

	public void setOwner(XToolBar owner) { 
		this.proxy = owner; 
	} 

	public ToolBarImpl() { 
		 
	} 

	public ToolBarImpl(int orientation) { 
		super(orientation); 
		 
	} 

	public ToolBarImpl(String name) { 
		super(name); 
		 
		this.setup_listeners(); 
	} 

	public ToolBarImpl(String name, int orientation) { 
		super(name, orientation); 
		 
	} 

	public static void main(String[] args) { 
		 

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
