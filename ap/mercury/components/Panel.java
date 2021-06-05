package ap.mercury.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import ap.global.gl;
import ap.mercury.intf.IMoving;

@SuppressWarnings("serial")
public class Panel extends Proxy implements 
MouseListener,
MouseMotionListener,
IMoving {

	public Panel() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
		
		this.setMinimumSize(new Dimension(DIST*4,DIST*4));
	}

	public Panel(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		this.setBackground(gl.getRandomColor());
		
	}

	public static Panel get_instance(Rectangle rect)
	{
		return new Panel(rect);
	}
	
	
	
	public Panel(LayoutManager layout) {
		super(layout);
		
	}

	public Panel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public Panel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}

	public static void main(String[] args) {
		

	}
	


	@Override
	public void mouseDragged(MouseEvent e) {
		
		this.mouseDragged(this, e);
		
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
		

		this.mousePressed(this, e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

		this.mouseReleased(this, e);
		
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
	
		paintComponent(this,g);
	}
	

}
