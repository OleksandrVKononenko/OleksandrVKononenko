 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.EventQueue; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.image.BufferStrategy; 

import javax.swing.JFrame; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 


public class XEngine { 

	 
	private boolean running = true; 
	 
	private Rectangle box = new Rectangle(0,0,10,200); 
	 
	private int dx = 1; 
	 
	protected static final int WIDTH  = 200; 
	 
	protected static final int HEIGHT = 200; 
	 
	public static BufferStrategy buffer_strategy = null; 
	 
	public static Graphics2D surface = null; 
	 
	public Thread gt = new Thread(new GThread()); 
	 
	 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
	 
		new XEngine(); 
	} 

	public XEngine() 
	{ 
		 
		EventQueue.invokeLater(new Runnable() 
		{ 
		@Override 
		public void run() { 
			try { 
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
			} 
			catch(ClassNotFoundException  ex) 
			{ 
				System.out.println("Some ClassNotFoundException exception."); 
			} 
			 
			catch(UnsupportedLookAndFeelException ex) 
			{ 
				System.out.println("Some UnsupportedLookAndFeelException exception."); 
			} 
			 
			catch(IllegalAccessException ex) 
			{ 
				System.out.println("Some IllegalAccessException exception."); 
			} 
			 
			catch(InstantiationException ex) 
			{ 
				System.out.println("Some InstantiationException exception."); 
			} 
			 
			JFrame frame = new JFrame(); 
			 
			frame.setIgnoreRepaint(true); 
			 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				 
			frame.setLayout(new BorderLayout()); 
			 
			frame.setSize(WIDTH,HEIGHT); 
			 
			frame.setLocationRelativeTo(null); 
			 
			frame.setVisible(true); 
			 
			frame.createBufferStrategy(2); 
			 
			 
			 
			buffer_strategy = frame.getBufferStrategy(); 
			 
			Graphics2D g = (Graphics2D) buffer_strategy.getDrawGraphics(); 
			 
			 
			new Thread (new Runnable() 
			{ 
				 
			@Override 
			public void run() 
			{ 
				long tock = 1000/100; 
				 
				while(running) 
				{ 
					box.x += dx; 
					 
					if(box.x + box.width > WIDTH) 
					{ 
						box.x = WIDTH - box.width; 
						dx *= -1; 
					} 
					else if (box.x < 0) { 
						box.x = 0; 
						 
						dx *= -1; 
					} 
					 
					surface = (Graphics2D) buffer_strategy.getDrawGraphics(); 
					 
					 
					gt.start(); 
					 
					 
					try { 
						Thread.sleep(tock); 
					} 
					catch(InterruptedException ex) 
					{ 
						 
					} 
				} 
					 
						buffer_strategy.dispose(); 
					} 
					}).start(); 
					 
		} 
		}); 
	} 
	 
	class GThread extends Thread 
	{ 
		 
		private boolean run_ning = true; 
		 
		 
		public boolean isRunning() { 
			return running; 
		} 

		public void setRunning(boolean running) { 
			this.run_ning = running; 
		} 

		 

		@Override 
		public void run() { 
			long tock = 1000/100; 
			 
			while(run_ning) 
			{ 
				box.x += dx; 
				 
				if(box.x + box.width > WIDTH) 
				{ 
					box.x = WIDTH - box.width; 
					dx *= -1; 
				} 
				else if (box.x < 0) { 
					box.x = 0; 
					 
					dx *= -1; 
				} 
				 
				surface = (Graphics2D) buffer_strategy.getDrawGraphics(); 
				 
				surface.setColor(Color.BLACK); 
				 
				surface.fillRect(0, 0, WIDTH,HEIGHT); 
				 
				surface.setColor(Color.WHITE); 
				 
				surface.fill(box); 
				 
				surface.dispose(); 
				 
				buffer_strategy.show(); 
				 
				 
				try { 
					Thread.sleep(tock); 
				} 
				catch(InterruptedException ex) 
				{ 
					 
				} 
		 
			 
		} 
	} 


}} 
