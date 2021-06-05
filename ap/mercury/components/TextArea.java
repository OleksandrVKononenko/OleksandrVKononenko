package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import ap.global.gl;
import ap.mercury.intf.IMoving;

public class TextArea extends JTextArea  implements 
MouseListener,
MouseMotionListener,
IMoving{

	public TextArea() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public TextArea(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		
		
	}

	public static TextArea get_instance(Rectangle rect)
	{
		return new TextArea(rect);
	}
	

	public TextArea(String text) {
		super(text);
		 
	}

	public TextArea(Document doc) {
		super(doc);
		 
	}

	public TextArea(int rows, int columns) {
		super(rows, columns);
		 
	}

	public TextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		 
	}

	public TextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		 
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
