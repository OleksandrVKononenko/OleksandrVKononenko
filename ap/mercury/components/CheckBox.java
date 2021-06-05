package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

@SuppressWarnings("serial")
public class CheckBox extends JCheckBox implements
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
	
	
	public CheckBox() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public CheckBox(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		this.setBackground(gl.getRandomColor());
		
	}

	public static CheckBox get_instance(Rectangle rect)
	{
		return new CheckBox(rect);
	}
	

	public CheckBox(Icon icon) {
		super(icon);
		
	}

	public CheckBox(String text) {
		super(text);
		
	}

	public CheckBox(Action a) {
		super(a);
		
	}

	public CheckBox(Icon icon, boolean selected) {
		super(icon, selected);
		
	}

	public CheckBox(String text, boolean selected) {
		super(text, selected);
		
	}

	public CheckBox(String text, Icon icon) {
		super(text, icon);
		
	}

	public CheckBox(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		
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
}
