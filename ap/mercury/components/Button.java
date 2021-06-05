package ap.mercury.components;

import java.awt.Graphics;
import ap.orion.*;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import ap.mercury.intf.IMoving;

@SuppressWarnings("serial")
public class Button extends JButton implements 
MouseListener,
MouseMotionListener,
IMoving
{
	
	private Orion owner;
	
	
	
	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}

	public Button() {
			
		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
		
	}

	public Button(Rectangle rect)
	{
		this();
		
		this.setBounds(rect);
	}
	
	public Button(Icon icon) {
		super(icon);
		
	}

	public Button(String text) {
		super(text);
		
	}

	public Button(Action a) {
		super(a);
		
	}

	public Button(String text, Icon icon) {
		super(text, icon);
		
	}

	
	public static void main(String[] args) {
		

	}
	
	

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mouseMoved(this,e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
	

	@Override
	public void mouseEntered(MouseEvent e) {
		
		mouseEntered(this);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		mouseExited(this);
		
	}
	
	public static Button get_instance(Rectangle rect)
	{
		return new Button(rect);
	}
	
	

}
