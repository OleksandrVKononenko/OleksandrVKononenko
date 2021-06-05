 
 
package ap.swing; 

import java.awt.Font; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.BorderFactory; 
import javax.swing.BoundedRangeModel; 
import javax.swing.JSlider; 

public class SliderImpl extends JSlider implements MouseListener,MouseMotionListener { 

	 
	 
	private XSlider proxy; 
	 
	public XSlider getProxy() { 
		return proxy; 
	} 

	public void setOwner(XSlider owner) { 
		this.proxy = owner; 
	} 

	public SliderImpl() { 
		 
		super(JSlider.VERTICAL,0,100,50); 
		 
		setup_listeners(); 
		 
		add_defaults(); 
		 
	} 
	 
	public void add_defaults() 
	{ 
		 
		this.setMajorTickSpacing(10); 
 
		this.setMinorTickSpacing(1); 
 
		this.setPaintTicks(true); 
 
		this.setPaintLabels(true); 
 
		this.setBorder(BorderFactory.createEmptyBorder(0,0,10,0)); 
 
		Font font = new Font("Serif", Font.ITALIC, 10); 
 
		this.setFont(font); 
		 
	} 

	public SliderImpl(int orientation) { 
		super(orientation); 
		 
	} 

	public SliderImpl(BoundedRangeModel brm) { 
		super(brm); 
		 
	} 

	public SliderImpl(int min, int max) { 
		super(min, max); 
		 
	} 

	public SliderImpl(int min, int max, int value) { 
		super(min, max, value); 
		 
	} 

	public SliderImpl(int orientation, int min, int max, int value) { 
		super(orientation, min, max, value); 
		 
	} 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		if(this.getProxy().isSelected()) 
		   this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		this.getProxy().mouseClicked(e); 
		 
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
	 
	public SliderImpl get_instance() 
	{ 
		return new SliderImpl(); 
	} 
	 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	 

	public static void main(String[] args) { 
		 

	} 

} 
