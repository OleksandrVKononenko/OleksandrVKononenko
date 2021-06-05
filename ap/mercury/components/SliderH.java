package ap.mercury.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoundedRangeModel;
import javax.swing.JSlider;

import ap.area.AreaManager;
import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

@SuppressWarnings("serial")
public class SliderH extends JSlider implements 
MouseListener,
MouseMotionListener,
IMoving {

	private Orion owner;
	
	
	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}

	public SliderH() {

		this.add_lsnrs();
		
		this.add_attrs();
	}

	public SliderH(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
	}

	public static SliderH get_instance(Rectangle rect)
	{
		return new SliderH(rect);
	}
	
	public static SliderH get_instance(int orientation, int min, int max, int value,Rectangle rect)
	{
	
		return new SliderH(orientation,min,max,value,rect);
	
	}

	public SliderH(int orientation) {
		super(orientation);
		 
	}

	public SliderH(BoundedRangeModel brm) {
		super(brm);
		 
	}

	public SliderH(int min, int max) {
		super(min, max);
		 
	}

	public SliderH(int min, int max, int value) {
		super(min, max, value);
		 
	}

	public SliderH(int orientation, int min, int max, int value) {
		super(orientation, min, max, value);
		
		add_lsnrs();
		
		add_attrs();      
		 
	}
	
	public SliderH(int orientation, int min, int max, int value,Rectangle rect) {
		
		this(orientation, min, max, value);
		
		this.setBounds(rect);
		
		add_lsnrs();
		
		add_attrs();
	}

	public void add_attrs()
	{
		 this.setMajorTickSpacing(10); 
         
		 this.setMinorTickSpacing(1); 
  
		 this.setPaintTicks(true); 
  
		 this.setPaintLabels(true);
       
	}
	
	public void add_lsnrs()
	{

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
		
	}
	
	public static void main(String[] args) {
		

	}
	


	@Override
	public void mouseDragged(MouseEvent e) {
		
		AreaManager am = new AreaManager(new Dimension(this.getSize()),new Dimension(1,3));

		if(am.get(2).contains(e.getPoint()))
		{
			this.getOwner().mouseDragged(e);
		
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mouseMoved(this,e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		gl.sfn("Owner is...%s",this.getOwner() != null);
		
		AreaManager am = new AreaManager(new Dimension(this.getSize()),new Dimension(1,3));

		if(am.get(2).contains(e.getPoint()))
		{
		
			this.getOwner().mousePressed(e);
	
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

		AreaManager am = new AreaManager(new Dimension(this.getSize()),new Dimension(3,1));

		if(am.get(2).contains(e.getPoint()))
		{
		
			this.getOwner().mouseReleased(e);
	
		}
		

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		mouseEntered(this);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		mouseExited(this);
		
	}
	
	
	
	@Override 
	public void paintComponent(Graphics g) { 
			
		super.paintComponent(g);
		
		if(this.getOwner() != null)
			return;
		
		paintComponent(this,g);
	}
	

}
