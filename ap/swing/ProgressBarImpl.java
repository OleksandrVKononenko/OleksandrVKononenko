 
 
 
 
 
 
 
package ap.swing; 

import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.BoundedRangeModel; 
import javax.swing.JProgressBar; 

import ap.global.gl; 

@SuppressWarnings("serial") 
public class ProgressBarImpl extends JProgressBar implements MouseListener,MouseMotionListener { 

	private XProgressBar proxy; 
	 
	public XProgressBar getProxy() { 
		return proxy; 
	} 

	public void setOwner(XProgressBar owner) { 
		this.proxy = owner; 
	} 

	public ProgressBarImpl() { 
		 
		setup_listeners() ; 
	} 

	public ProgressBarImpl(int orient) { 
		 
		super(orient); 
		 
		setup_listeners() ; 
		 
	} 

	public ProgressBarImpl(BoundedRangeModel newModel) { 
		 
		super(newModel); 
		 
		setup_listeners() ; 
		 
	} 

	public ProgressBarImpl(int min, int max) { 
		 
		super(min, max); 
		 
		setup_listeners() ; 
		 
	} 

	public ProgressBarImpl(int orient, int min, int max) { 
		 
		super(orient, min, max); 
		 
		setup_listeners() ; 
		 
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
		 
		int m_value = this.getValue()+1; 
				 
			if(m_value > 100) 
				m_value = gl.E_EMPTY; 
			 
		this.setValue(m_value); 
		 
		//this.getProxy().repaint_childs(); 
				 
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
		 
		//this.getProxy().repaint_childs(); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		this.getProxy().mouseExited(e); 
		 
		//this.getProxy().repaint_childs(); 
		 
	} 
	 
	public static ProgressBarImpl get_instance() { 

		return new ProgressBarImpl(); 

	} 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
			 
	} 


	public static void main(String[] args) { 
		 

	} 

} 
