 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

//The base template with key&mouse dispathers. 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GraphicsConfiguration; 
import java.awt.GraphicsEnvironment; 
import java.awt.KeyboardFocusManager; 
import java.awt.MultipleGradientPaint; 
import java.awt.Paint; 
import java.awt.Point; 
import java.awt.RadialGradientPaint; 
import java.awt.Stroke; 
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
import ap.shape.Ru; 
import ap.swing.PanelXml; 
import ap.utils.GU; 

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
	 
	public Point startPos = null; 
	 
	public static Thread topThread = null; 
	 
	private int dx; 
	 
	private int dy; 
	 
	public JPanel test_panel = new JPanel(); 
	 
	 
	public int getDx() { 
		return dx; 
	} 

	public void setDx(int dx) { 
		this.dx = dx; 
	} 

	public int getDy() { 
		return dy; 
	} 

	public void setDy(int dy) { 
		this.dy = dy; 
	} 

	public void dragSetDxDy(MouseEvent e) 
	 { 
		 if(e==null) 
			return; 
			 
			this.setDx((e.getX() - startPos.x)); 
			 
			this.setDy((e.getY() - startPos.y)); 
		 
	 } 
	 
	public void mouseDragged(MouseEvent e) { 

		dragSetDxDy(e); 
		 
		 
		SELECTOR = new Rectangle( 
				this.startPos.x,this.startPos.y, 
				this.getDx(), 
				this.getDy() 
				); 
		 
		repaint(); 
		 
	} 

	public void paintTest(Graphics g) { 
		 
		PanelXml desktop = new PanelXml(new Dimension(100,100)); 

		desktop.setBackground(Color.DARK_GRAY); 

		desktop.paint(g); 
		 
	} 
	public void paintSelector(Graphics g) { 
		 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(gl.dashed_stroke); 
		 
		GU.drawAlphaRect(g2,SELECTOR,Color.white); 
		 
		GU.fillAlphaRect(g2,SELECTOR,Color.white,0.1f); 
		 
		g2.setStroke(p_stroke); 
		 

	} 
	public void mouseClicked(MouseEvent e) { 

	} 

	public void mouseMoved(MouseEvent e) { 

			mouseX = e.getX(); 
			 
			mouseY = e.getY(); 
			 
			repaint(); 
	} 

	public void mousePressed(MouseEvent e) { 

		startPos = e.getPoint(); 
	} 

	public void mouseReleased(MouseEvent e) { 

		startPos = null; 
		 
		SELECTOR = Ru.get_init_rect(gl.E_ERROR); 
		 
		repaint(); 
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
		 
		surface = toCompatibleImage(new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB)); 
		 
		test_panel.setBounds(new Rectangle(10,10,200,200)); 
		 
		test_panel.setBackground(Color.green); 
		 
		test_panel.setVisible(true); 
	} 

	public void paint(Graphics g) { 
	 
		paintOverBufferImage(surface.getGraphics()); 
		 
		paintTest(surface.getGraphics()); 
		 
		paintSelector(surface.getGraphics()); 
		 
		 
		if (surface != null && g != null) 
		g.drawImage(surface, 0, 0, surface.getWidth(), surface.getHeight(),null); 

			 
		} 

	public void paintOverBufferImage(Graphics g) { 
		 
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
			 
			frame.setIgnoreRepaint(true); 
			 
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
			//gl.smn("<main> exception :" + ex.getMessage()); 
		} 

		finally { 

		} 

	} 
	 
	private BufferedImage toCompatibleImage(BufferedImage image) 
	{ 
	        // obtain the current system graphical settings 
	        GraphicsConfiguration gfx_config = GraphicsEnvironment. 
	                getLocalGraphicsEnvironment().getDefaultScreenDevice(). 
	                getDefaultConfiguration(); 

	        if (image.getColorModel().equals(gfx_config.getColorModel())) 
	                return image; 

	        // image is not optimized, so create a new image that is 
	        BufferedImage new_image = gfx_config.createCompatibleImage( 
	                        image.getWidth(), image.getHeight(), image.getTransparency()); 

	        // get the graphics context of the new image to draw the old image on 
	        Graphics2D g2d = (Graphics2D) new_image.getGraphics(); 

	        // actually draw the image and dispose of context no longer needed 
	        g2d.drawImage(image, 0, 0, null); 
	 
	        g2d.dispose(); 

	        // return the new optimized image 
	        return new_image; 
	} 
	 
} 
