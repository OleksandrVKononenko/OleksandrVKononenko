package ap.mercury.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import ap.global.gl;
import ap.mercury.intf.IMoving;
import ap.orion.Orion;

@SuppressWarnings("serial")
public class Tree extends JTree implements 
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


	public Tree() {

		this.addMouseListener(this);
		
		this.addMouseMotionListener(this);
	}

	public Tree(Rectangle rect) {
		
		this();
		
		this.setBounds(rect);
		
		this.setBackground(gl.getRandomColor());
		
	}

	public static Tree get_instance(Rectangle rect)
	{
		return new Tree(rect);
	}
	

	public Tree(Object[] value) {
		super(value);
		 
	}

	public Tree(Vector<?> value) {
		super(value);
		 
	}

	public Tree(Hashtable<?, ?> value) {
		super(value);
		 
	}

	public Tree(TreeNode root) {
		super(root);
		 
	}

	public Tree(TreeModel newModel) {
		super(newModel);
		 
	}

	public Tree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		 
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
