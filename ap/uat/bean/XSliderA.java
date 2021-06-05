 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.JSlider; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XSliderA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private SliderImplA Slider ; 
	 
	 
	public SliderImplA getSlider() { 
		return Slider; 
	} 

	public void setSlider(SliderImplA Slider) { 
		this.Slider = Slider; 
	} 

	public XSliderA() 
	{ 
		super(); 
	} 
	public XSliderA(Rectangle rect) { 
		 
		super(rect); 
		 
		//this.addMouseListener(this); 
		 
		//this.addMouseMotionListener(this); 
		 
		add_components(JSlider.VERTICAL,0,100,50); 
		 

	} 

	public XSliderA(Rectangle rect,int align,int orientation, int min, int max, int value) { 
		 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		this.addMouseMotionListener(this); 
		 
		add_components(orientation,min,max,value); 
		 
		this.getDecoro().set_align(align); 
		 
	} 

	public void add_components(int orientation, int min, int max, int value) 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Slider_%d",this.getIdo().getId())); 
		 
		this.setSlider(SliderImplA.get_instance(orientation,min,max,value)); 
		 
		this.getSlider().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getSlider()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getSlider().revalidate(); 
	} 
	 
	/* 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		//this.getSlider().setToolTipText(this.toString()); 
		 
	} 
	*/ 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		//this.revalidate(); 
		 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		super.mouseEntered(e); 
		 
	} 
		 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	 
	public static XSliderA get_instance(Rectangle rect) 
	{ 
		return new XSliderA(rect); 
	} 
	 
	 
	 
	public static XSliderA get_instance(Rectangle rect,int align) 
	{ 
	 
		XSliderA	atom = XSliderA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return 		atom; 
	 
	} 
	 
	public static XSliderA get_instance(Rectangle rect,int align ,int orientation, int min, int max, int value) { 
		 
		XSliderA	atom = new XSliderA( rect,align,orientation,min,max,value); 
		 
					atom.setBackground(gl.getRandomColor()); 
		 
					atom.getStio().set_deny_drag(false); 
	 
					atom.getStio().set_move_bottom_right(true); 
		 
		return 		atom; 
		 
					 
		 
	} 

} 
