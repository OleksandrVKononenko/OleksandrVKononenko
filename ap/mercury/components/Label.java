package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

public class Label extends JLabel  implements 
MouseListener,
MouseMotionListener,
IMoving{
	
	private Orion owner;

	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}


	public Label() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public Label(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		this.setBackground(gl.getRandomColor());
		
	}

	public static Label get_instance(Rectangle rect)
	{
		return new Label(rect);
	}
	
	

	public Label(Icon image) {
		super(image);
		 
	}

	public Label(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		 
	}

	public Label(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		 
	}

	public Label(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		 
	}
	


	@Override
	public void mouseMoved(MouseEvent e) {
		
		mouseMoved(this,e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
	public void mouseDragged(MouseEvent e) {
		
		
		if(this.getOwner() != null)
			this.getOwner().mouseDragged(e);
		else
		    this.mouseDragged(this, e);
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		

		
		if(this.getOwner() != null)
			this.getOwner().mousePressed(e);
		else
		    this.mousePressed(this, e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

		
		if(this.getOwner() != null)
			this.getOwner().mouseReleased(e);
		else
			this.mouseReleased(this, e);
		
	}
	

	@Override 
	public void paintComponent(Graphics g) { 
			
		super.paintComponent(g);
	
		if(this.getOwner() != null)
			return;
		
		paintComponent(this,g);
	}
	

}
