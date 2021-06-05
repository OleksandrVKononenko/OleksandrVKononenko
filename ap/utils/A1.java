 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.utils; 

//The base template with key&mouse dispathers. 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.KeyboardFocusManager; 
import java.awt.MultipleGradientPaint; 
import java.awt.Paint; 
import java.awt.RadialGradientPaint; 
import java.awt.Toolkit; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.Point2D; 
import java.awt.image.BufferedImage; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 

import ap.global.gl; 

//import sample.swing.GradientMiddle; 

public class A1 extends JPanel implements MouseListener, 
		MouseMotionListener { 

	private Rectangle SELECTOR = null; 

	public static BufferedImage surface = null; 
	 
	public static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; 
	 
	public static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; 
	 
	public int mouseX = 0; 
	 
	public int mouseY = 0; 
	 
	public JButton btn = null; 
	 
	public static Thread topThread = null; 
	 
	public void mouseDragged(MouseEvent e) { 

	} 

	public void mouseClicked(MouseEvent e) { 

	} 

	public void mouseMoved(MouseEvent e) { 

			mouseX = e.getX(); 
			 
			mouseY = e.getY(); 
			 
			put(); 
			 
			repaint(); 
	} 

	public void mousePressed(MouseEvent e) { 

	} 

	public void mouseReleased(MouseEvent e) { 

	} 

	public void mouseExited(MouseEvent e) { 

	} 

	public void mouseEntered(MouseEvent e) { 
	} 
	 

	public A1() { 
		SELECTOR = new Rectangle(0, 0, 0, 0); 

		addMouseMotionListener(this); 

		addMouseListener(this); 
		 
		this.setOpaque(true); 
		 
		surface = new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB); 
	 
	} 

	public void paint(Graphics g) { 
		 
		super.paint(g); 

		//put(); 

		if (surface != null) 
			g.drawImage(surface, 0, 0, surface.getWidth(), surface.getHeight(),null); 

		//topThread = new Thread(new PaintThread(g,surface)); 
		 
		//((PaintThread)(topThread)).start(); 
		 
		//if (surface != null && g != null) 
		//g.drawImage(surface, 0, 0, surface.getWidth(), surface.getHeight(),null); 

			 
		} 

	public void put() { 
		Graphics g = surface.getGraphics(); 

		g.setColor(Color.red); 

		g.fillRect(0, 0,SCREEN_WIDTH,SCREEN_HEIGHT); 
		 
		g.setColor(Color.white); 
		 
		g.drawString(String.format("[%s,%s]",mouseX,mouseY),20,20); 
		 
			 

	} 

	public static void main(String[] args) { 

		try { 
			JFrame frame = new JFrame("ap"); 
					 
			frame.add(new A1()); 

			frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT); 

			frame.setLocationRelativeTo(null); 

			frame.setUndecorated(true); 
			 
			frame.setIgnoreRepaint(false); 
			 
			frame.addKeyListener(new KeyAdapter() 
		      { 
		        public void keyPressed(KeyEvent e) 
		        { 
		          if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		          { 
		        	  System.exit(0); 
		          } 
		 
		        } 
		      }); 
			 
			frame.setVisible(true); 
			 
				 
			 
		} catch (Exception ex) { 
			gl.smn("<main> exception :" + ex.getMessage()); 
		} 

		finally { 

		} 

	} 
	 
} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
