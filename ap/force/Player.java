 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
package ap.force; 

import java.awt.Color; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

public class Player implements Actable, Drawable, KeyListener, MouseListener, MouseMotionListener 
{ 

	private boolean leftPressed, rightPressed, upPressed, downPressed; 
	private int mousex, mousey; 
	private boolean mousePressed; 
	 
	private Rectangle area = null; 

	public Player() 
	{ 
		 
		setArea(); 
		 
		Force.stage.add(this); 
		Force.render.add(this); 
		Force.render.addKeyListener(this); 
		Force.render.addMouseListener(this); 
		Force.render.addMouseMotionListener(this); 
	} 

	@Override 
	public void draw(Graphics2D g) 
	{ 
		g.setColor(Color.CYAN); 
	 
		g.fillRect(this.getArea().x,this.getArea().y,this.getArea().width,this.getArea().height); 
	} 

	static final int moveSpeed=1; 
	int timer=0; 

	@Override 
	public void act() 
	{ 
		//moves the player around 
		if(leftPressed) 
			this.getArea().x-=moveSpeed; 
		if(rightPressed) 
			this.getArea().x+=moveSpeed; 
		if(upPressed) 
			this.getArea().y-=moveSpeed; 
		if(downPressed) 
			this.getArea().y+=moveSpeed; 

		if(mousePressed && timer++ == 5) 
		{ 
			new Photon(this.getArea().x,this.getArea().y,mousex,mousey); 
			timer=0; 
		} 
	} 

	@Override 
	public void keyPressed(KeyEvent k) 
	{ 
		if (k.getKeyCode() == KeyEvent.VK_LEFT || k.getKeyCode() == KeyEvent.VK_A) 
			leftPressed=true; 
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT || k.getKeyCode() == KeyEvent.VK_D) 
			rightPressed=true; 
		else if(k.getKeyCode() == KeyEvent.VK_UP || k.getKeyCode() == KeyEvent.VK_W) 
			upPressed=true; 
		else if (k.getKeyCode() == KeyEvent.VK_DOWN || k.getKeyCode() == KeyEvent.VK_S) 
			downPressed=true; 
	} 

	@Override 
	public void keyReleased(KeyEvent k) 
	{ 
		if (k.getKeyCode() == KeyEvent.VK_LEFT || k.getKeyCode() == KeyEvent.VK_A) 
			leftPressed=false; 
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT || k.getKeyCode() == KeyEvent.VK_D) 
			rightPressed=false; 
		else if(k.getKeyCode() == KeyEvent.VK_UP || k.getKeyCode() == KeyEvent.VK_W) 
			upPressed=false; 
		else if (k.getKeyCode() == KeyEvent.VK_DOWN || k.getKeyCode() == KeyEvent.VK_S) 
			downPressed=false; 
	} 

	@Override 
	public void keyTyped(KeyEvent k) { 
		// REPLACE Auto-generated method stub 

	} 

	@Override 
	public void mouseClicked(MouseEvent m) 
	{ 
		 
		 
	} 

	@Override 
	public void mousePressed(MouseEvent m) 
	{ 
		 
		mousePressed=true;		 
	} 

	@Override 
	public void mouseReleased(MouseEvent m) 
	{	 
		 
		mousePressed=false;		 
	} 

	@Override 
	public void mouseEntered(MouseEvent m) {} 

	@Override 
	public void mouseExited(MouseEvent m) {} 


	@Override 
	public void mouseDragged(MouseEvent m) 
	{ 
		mousex=m.getX(); 
		mousey=m.getY(); 
		 
	} 


	@Override 
	public void mouseMoved(MouseEvent m) 
	{ 
		mousex=m.getX(); 
		 
		mousey=m.getY(); 
	} 

	public void setArea() 
	{ 
		this.area = new Rectangle(Render.WIDTH/2,Render.HEIGHT/2,10,10); 
	} 

	public Rectangle getArea() 
	{ 
		return this.area; 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
