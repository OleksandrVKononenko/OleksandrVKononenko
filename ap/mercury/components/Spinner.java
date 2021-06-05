package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import ap.mercury.intf.IMoving;

@SuppressWarnings("serial")
public class Spinner extends JSpinner implements 
MouseListener,
MouseMotionListener,
IMoving {

	public Spinner() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
		
		this.setModel(new SpinnerNumberModel(8, 0, 16, 2));
	}

	public Spinner(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
	}

	public static Spinner get_instance(Rectangle rect)
	{
		return new Spinner(rect);
	}
	

	public Spinner(SpinnerModel model) {
		super(model);
		
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
