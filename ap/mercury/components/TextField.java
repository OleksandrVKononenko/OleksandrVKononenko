package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.Document;

import ap.global.gl;
import ap.mercury.intf.IMoving;

@SuppressWarnings("serial")
public class TextField extends JTextField implements 
MouseListener,
MouseMotionListener,
IMoving{

	public TextField() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public TextField(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		//this.setBackground(gl.getRandomColor());
		
		this.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
		
	}

	public static TextField get_instance(Rectangle rect)
	{
		return new TextField(rect);
	}
	
	public TextField(String text) {
		super(text);
		
	}

	public TextField(int columns) {
		super(columns);
		
	}

	public TextField(String text, int columns) {
		super(text, columns);
		
	}

	public TextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		
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
