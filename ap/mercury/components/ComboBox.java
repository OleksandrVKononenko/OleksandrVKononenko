package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

@SuppressWarnings("serial")
public class ComboBox<String> extends JComboBox implements 
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


	public ComboBox() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public ComboBox(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		this.setBackground(gl.getRandomColor());
		
		this.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
		
	}

	public static ComboBox get_instance(Rectangle rect)
	{
		return new ComboBox(rect);
	}
	

	public ComboBox(ComboBoxModel aModel) {
		super(aModel);
		
	}

	public ComboBox(Object[] items) {
		super(items);
		
	}

	public ComboBox(Vector<String> items) {
		super(items);
		
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
