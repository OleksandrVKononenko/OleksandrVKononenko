 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.MultipleGradientPaint; 
import java.awt.Paint; 
import java.awt.RadialGradientPaint; 
import java.awt.Toolkit; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.event.ComponentAdapter; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.Point2D; 
import java.util.ArrayList; 

import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 

import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.global.gl; 

import java.math.*; 

public class TestPaint extends TPanel { 
 
 
	private SystemTimer render_timer; 
	 
	public static ArrayList<TPanel> m ; 
	 
	public static int mv[] ; 
	 
	public static Dimension dim = null; 
	 
	public static Dimension frame_dim ; 
	 
	public static Dimension objects_dim ; 
	 
	public static JFrame frame ; 
	 
	public static boolean running; 
	 
	public static long render_number = 0; 
	 
	public static long sync_number = 0; 
	 
	public static long frequency = 0; 
	 
	public static Graphics G ; 
	 
	public static long START_FREQUENCY = 55; 
	 
	public static long RENDER_PORT_SLEEP_CONST = 1000/START_FREQUENCY; 
	 
	public static long RENDER_PORT_SLEEP_MAIN = RENDER_PORT_SLEEP_CONST; 
	 
	public static int  RENDER_PORT_SLEEP_NANO = 100000; 
	 
	public static long SYNC_PORT_SLEEP = 1000; 
	 
	public static long REAL_RENDER_PORT_SLEEP = RENDER_PORT_SLEEP_CONST; 
	 
	 
	 

	public TestPaint() 
	{ 
		this.addComponentListener( new ComponentAdapter() 
		{ 
			public void componentResized(ComponentEvent e) 
			{ 
				gl.smn("Component resized ..."); 
				 
				Dimension frame_dim = TestPaint.frame.getSize(); 
				 
				loadComponents(frame_dim,objects_dim); 
				 
								 
			} 
		} 
		); 
	} 
	 
	public void addComponents() 
	{	 
		for(TPanel pj :  m) 
			this.add(pj); 
	} 
	 
	 
	public void clearQueue() 
	{ 
		  long start = System.nanoTime(); 
		 
		  m.clear(); 
		 
		  long end = System.nanoTime(); 
			 
		  long dif = (end - start)/1000; 
				 
		  gl.smn("Clear queue : " + dif + " mcs"); 
			 
	} 
	public void loadComponents(Dimension p_dim,Dimension p_size) 
	{	 
	    this.setLayout(null); 
	 
	    dim = p_size; 
	 
	    //AreaManager am = new AreaManager(p_dim,new I3((int)p_size.getWidth(),(int)p_size.getHeight(),0)); 
		 
	    AreaManager am = null; 
	    		 
	    int size = (int)(p_size.getWidth()*p_size.getHeight()); 
	 
	    if(m != null) 
	    clearQueue(); 
	    else 
	    m = new ArrayList<TPanel>(); 
		 
	    for(int i=0; i < size;i++) 
	    { 
	    	TPanel p= new TPanel(); 
	    	 
	    	Color color = Color.white ;//gl.getRandomColor();//gl.getRandomBasicColor(); 
	    	 
	    	p.setBackground(color); 
	    	 
	    	p.setBounds(am.get(i)); 
	    	 
	    	//p.getBounds(p.OriginArea); 
	    	 
	    	//p.setIndex(i); 
	    	 
	    	//p.setToolTipText(p.getToolTipHeader() + " " + p.getIndex() +":" + color.toString() ); 
	    	 
	  		m.add(p); 
	    } 
	} 
	 
public void paint(Graphics g) { 
 
	   super.paint(g); 
	 
	   G = this.getGraphics(); 
	 
	   //G = g; 
	 
	   long start = System.nanoTime(); 
	 
		for(TPanel pj :  m) 
		{ 
			  g.setColor(pj.getBackground()); 
			  		 
			  g.fillRect(pj.getX(),pj.getY(),pj.getWidth(),pj.getHeight()); 
			 
		} 
		 
	 
	    long end = System.nanoTime(); 
		 
		long dif = (end - start)/1000; 
		 
		//gl.smn("Paint  : " + dif + " mcs."); 
 
} 
 
public void mouseMoved(MouseEvent e) { 
		 
	 
	} 

class Render implements Runnable { 

		public Render() { 

		} 

		public void run() { 

			try { 
				 
				while (running) { 

					render_number++; 

					repaint(); 
					 
					drawDiagnostic(G,Color.black); 
					 
					Thread.sleep(RENDER_PORT_SLEEP_MAIN,RENDER_PORT_SLEEP_NANO); 
					 
			} 
		} 
			 catch (Exception e) { 
					e.printStackTrace(); 
				} 
	} 
} 

class Sync implements Runnable { 

		public Sync() { 

		} 

		public void run() { 

			try { 

				long start  = 0, stop = 0 ; 
				 
				while (running) { 

					// nop 

					stop = render_number ; 
					 
					if(start != 0) 
					{ 
						frequency = (stop - start) + 1; 
						 
						if(frequency != 0) 
						REAL_RENDER_PORT_SLEEP = 1000 / frequency; 
						 
						if(frequency != 0 ) 
						{ 
							if ((REAL_RENDER_PORT_SLEEP != RENDER_PORT_SLEEP_CONST) && Math.abs(frequency - START_FREQUENCY)> 1) 
							{ 
								 
								if(frequency < START_FREQUENCY ) 
								{ 
									 
									if(RENDER_PORT_SLEEP_MAIN >= 1) 
									RENDER_PORT_SLEEP_MAIN -= 1; 
									 
									gl.sm("-"); 
								} 
								else if(frequency > START_FREQUENCY ) 
								{ 
									RENDER_PORT_SLEEP_MAIN += 1; 

									gl.sm("+"); 
								} 
								else 
								{ 
									gl.sm("=="); 
								} 
								 
							} 
							else if(REAL_RENDER_PORT_SLEEP == RENDER_PORT_SLEEP_CONST) 
							{ 
								gl.sm("==="); 
							} 
						} 
						 
						 
						 
						sync_number++; 
					} 
					else 
						frequency = 0 ; 
					 
					 
					start = render_number; 
					 
					Thread.sleep(SYNC_PORT_SLEEP); 
					 
					gl.smn(" Sync cycle : " + sync_number + " Frequency : " + frequency); 

				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
	} 
} 

	 
	public void drawDiagnostic(Graphics G ,Color pcolor) { 

		//Font old_font = G.getFont(); 

		//Color old_color = G.getColor(); 

		if (G != null) 
		{ 
		G.setFont(new Font("Courier new", Font.BOLD, 12)); 

		G.setColor(pcolor); 

		int row = 10; 

		G.drawString(String.format("Sync : %s", sync_number), 20,	row += 10); 

		G.drawString(String.format("Beat : %s", render_number), 20, row += 10); 

		G.drawString(String.format("Freq : %s", frequency), 20, row += 10); 
		 
		G.drawString(String.format("R1   : %s", RENDER_PORT_SLEEP_CONST), 20, row += 10); 
		 
		G.drawString(String.format("R2   : %s", REAL_RENDER_PORT_SLEEP), 20, row += 10); 
		 
		G.drawString(String.format("R3   : %s", RENDER_PORT_SLEEP_MAIN), 20, row += 10); 
		 
		if(sync_number > 0) 
		G.drawString(String.format("R4   : %s", ""+(int)(render_number/sync_number)), 20, row += 10); 
		 
		} 
		 
		//G.setFont(old_font); 

		//G.setColor(old_color); 

	} 

	public void wakeUp() { 

		running = true; 

		try { 

			Render r = new Render(); 
			 
			Sync s = new Sync(); 

			Thread main_thread = new Thread(r); 
			 
			Thread sync_thread = new Thread(s); 
			 

			main_thread.start(); 
			 
			sync_thread.start(); 

			main_thread.join(); 
			 
			sync_thread.join(); 
			 
			 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 

		finally { 
			//render_timer.stopTimer(); 
		} 

	} 
	 
	 
public static void main(String[] args) { 

	    TestPaint.frame = new JFrame("Template"); 
		 
		frame_dim =  new Dimension(350, 350); 
		 
		objects_dim =  new Dimension(10,10); 
				 
		TestPaint.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 
		TestPaint tp = new TestPaint(); 
		 
		tp.loadComponents(frame_dim,objects_dim); 
		 
		TestPaint.frame.add(tp); 
		 
		TestPaint.frame.setSize(frame_dim); 
		 
		TestPaint.frame.setLocationRelativeTo(null); 
		 
		TestPaint.frame.setVisible(true); 
		 
		tp.wakeUp(); 
 
} 
} 
 

// Revision : 10.09.2018 12:49:14 
