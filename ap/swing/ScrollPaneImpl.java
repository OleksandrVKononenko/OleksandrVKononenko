 
 
package ap.swing; 

import java.awt.Component; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 

import javax.swing.JScrollPane; 

import ap.global.gl; 

@SuppressWarnings("serial") 
public class ScrollPaneImpl extends JScrollPane implements MouseListener,MouseMotionListener,MouseWheelListener{ 

	private XScrollPane proxy; 
	 
	public XScrollPane getProxy() { 
		return proxy; 
	} 

	public void setProxy( XScrollPane proxy) { 
		 
		this.proxy = proxy; 
	} 
	 
	public void setOwner(XScrollPane owner) { 
		this.proxy = owner; 
	} 

	public ScrollPaneImpl() { 
		 
		setup_listeners(); 
	} 

	public ScrollPaneImpl(Component view) { 
		super(view); 
		 
	} 

	public ScrollPaneImpl(int vsbPolicy, int hsbPolicy) { 
		super(vsbPolicy, hsbPolicy); 
		 
	} 

	public ScrollPaneImpl(Component view, int vsbPolicy, int hsbPolicy) { 
		super(view, vsbPolicy, hsbPolicy); 
		 
	} 

	 
	 
	public static void main(String[] args) { 
		 

	} 
	public ScrollPaneImpl get_instance(XScrollPane owner) 
	{ 
		ScrollPaneImpl spi = new ScrollPaneImpl(); 
		 
		spi.setOwner(owner); 
				 
		return spi; 
	} 
	 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		this.getProxy().mouseDragged(e); 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		this.getProxy().mouseClicked(e); 
				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		this.getProxy().mousePressed(e); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		this.getProxy().mouseReleased(e); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		this.getProxy().mouseEntered(e); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		this.getProxy().mouseExited(e); 
	} 

	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addMouseWheelListener(this); 
	} 

		@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 
		 
			this.getProxy().mouseWheelMoved(e); 
		 
	} 
	 
	 
	 
} 
