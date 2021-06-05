package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.text.Document;

import ap.global.gl;
import ap.mercury.intf.IMoving;

public class PasswordField extends JPasswordField implements 
MouseListener,
MouseMotionListener,
IMoving {

	public PasswordField() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public PasswordField(Rectangle rect) {
		
		this();
		
		this.setBorder(BorderFactory.createEmptyBorder(IMoving.DIST,IMoving.DIST,IMoving.DIST,IMoving.DIST));
	
		this.setBounds(rect);
		
	}

	public static PasswordField get_instance(Rectangle rect)
	{
		return new PasswordField(rect);
	}
	
	

	public PasswordField(String text) {
		super(text);
		
	}

	public PasswordField(int columns) {
		super(columns);
		
	}

	public PasswordField(String text, int columns) {
		super(text, columns);
		
	}

	public PasswordField(Document doc, String txt, int columns) {
		super(doc, txt, columns);
		
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
