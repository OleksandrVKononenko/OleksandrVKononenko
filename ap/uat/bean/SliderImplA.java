 
package ap.uat.bean; 


import java.awt.Font; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.BorderFactory; 
import javax.swing.BoundedRangeModel; 
import javax.swing.JSlider; 

@SuppressWarnings("serial") 
public class SliderImplA extends JSlider implements MouseListener,MouseMotionListener { 

	 
	 
	private XSliderA proxy = new XSliderA(); 
	 
	 
	public XSliderA getProxy() { 
		return proxy; 
	} 

	 
	public void setOwner(XSliderA owner) { 
		this.proxy = owner; 
	} 
	 
	public SliderImplA() { 
		 
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

	public SliderImplA(int orientation) { 
		super(orientation); 
		 
	} 

	public SliderImplA(BoundedRangeModel brm) { 
		super(brm); 
		 
	} 

	public SliderImplA(int min, int max) { 
		super(min, max); 
		 
	} 

	public SliderImplA(int min, int max, int value) { 
		super(min, max, value); 
		 
	} 

	public SliderImplA(int orientation, int min, int max, int value) { 
		 
		super(orientation, min, max, value); 
		 
		setup_listeners(); 
		 
		add_defaults(); 
		 
	} 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		if(this.getProxy() == null) 
			return; 
	 
		if(this.getProxy().getStio().is_selected()) 
		   this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		if(this.getProxy() == null) 
			return; 
	 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		//if(this.getProxy() == null)	return; 
	 
		this.getProxy().mouseClicked(e); 
		 
		//this.getProxy().repaint_childs(); 
				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		//if(this.getProxy() == null)			return; 
	 
		this.getProxy().mousePressed(e); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		//if(this.getProxy() == null)			return; 
	 
		this.getProxy().mouseReleased(e); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		//if(this.getProxy() == null)			return; 
		 
		this.getProxy().mouseEntered(e); 
		 
		//this.getProxy().repaint_childs(); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		//if(this.getProxy() == null)			return; 
	 
		this.getProxy().mouseExited(e); 
		 
		//this.getProxy().repaint_childs(); 
		 
	} 
	 
	public SliderImplA get_instance() 
	{ 
		return new SliderImplA(); 
	} 
	 
	 
	public void setup_listeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	public static SliderImplA get_instance(int orientation, int min, int max, int value) { 
		 
		return new SliderImplA(orientation,min,max,value); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 

