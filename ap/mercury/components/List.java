package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

@SuppressWarnings("serial")
public class List<String> extends JList<String> implements 
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

	

	public List() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public List(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
	}

	public static List get_instance(Rectangle rect)
	{
		return new List(rect);
	}
	
	

	public List(ListModel dataModel) {
		super(dataModel);
		
	}

	/*
	public List(Object[] listData) {
		super(listData);
		
	}
	*/
	public List(Vector listData) {
		super(listData);
		
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
