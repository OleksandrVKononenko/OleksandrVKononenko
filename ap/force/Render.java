 
package ap.force; 

import java.awt.BasicStroke; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GraphicsDevice; 
import java.awt.GraphicsEnvironment; 
import java.awt.RenderingHints; 
import java.awt.Toolkit; 
import java.awt.image.BufferedImage; 
import java.util.HashSet; 

import javax.swing.JFrame; 

@SuppressWarnings("serial") 
public class Render extends JFrame { 

	public static final int FRAMES_PER_SECOND = 25; 

	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; 
	 
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; 

	private BufferedImage buffer; 
	 
	private Graphics2D graphic; 

	Scene scene; 

	private HashSet<Drawable> draw = new HashSet<Drawable>(); 
	 
	private HashSet<Drawable> add = new HashSet<Drawable>(); 
	 
	private HashSet<Drawable> remove = new HashSet<Drawable>(); 

	static final BasicStroke s = new BasicStroke(2, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);// default stroke 

	public Render() { 
		super("ap"); 

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); 

		if (gd.isFullScreenSupported()) { 
			setUndecorated(true); // removes window title bar, close button, etc 
			gd.setFullScreenWindow(this); 
		} else { 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			setResizable(false); 
			setSize(WIDTH, HEIGHT); 
		} 

		setVisible(true); 

		// compatible images are much faster 
		buffer = gd.getDefaultConfiguration().createCompatibleImage(WIDTH, 
				HEIGHT); 

		graphic = (Graphics2D) buffer.getGraphics(); 

		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 

		scene = new Scene(); 
	} 

	public void paint(Graphics screen) { 
		 
		  draw.addAll(add); 
		 
		  add.clear(); 
		 
		  draw.removeAll(remove); 
		 
		  remove.clear(); 
		 
		  if(graphic != null) 
		  scene.draw(graphic); 
		 
		  for(Drawable d: draw) 
		  { 
			  d.draw(graphic); 
			 
			  //graphic.setStroke(s); 
			 
		  } 
		 
		  screen.drawImage(buffer, 0, 0, null); 
		 
	} 

	 public void add(Drawable d){add.add(d);} 

	 public void remove(Drawable d){remove.add(d);} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
