 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.thread; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Rectangle; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.MouseEvent; 

import javax.swing.*; 

import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.global.gl; 

/** 
* Author : Oleksandr V. Kononenko 
* 
* 27 ???. 2017 ?. 9:25:49 Project name : Organizer Package name : ap.test File 
* name : ThreadPanel.java 
* 
*/ 
public class ThreadPanelBase extends TPanel implements ComponentListener { 

	private static final int PREF_W = 320; 
	 
	private static final int PREF_H = 240; 
	 
	private static final int DELAY = 17; 
	 
	private int count = 0; 
	 
	private String text; 
	 
	 
	private Dimension preferedSize; 
	 
	 
	 
	public Dimension getPreferedSize() { 
		return preferedSize; 
	} 

	public void setPreferedSize(Dimension preferedSize) { 
		this.preferedSize = preferedSize; 
	} 

	public String getText() { 
		return text; 
	} 

	public void setText(String text) { 
		this.text = text; 
	} 

	public ThreadPanelBase() { 
		/* 
		new Timer(DELAY, new ActionListener() { 

			@Override 
			public void actionPerformed(ActionEvent e) { 
				count++; 
				 
				repaint(); 
			} 
		}).start(); 
		*/ 
	} 
	 
	public ThreadPanelBase(String text) { 
		 
		this(); 
		 
		this.setText(text); 
	} 
	 
	public ThreadPanelBase(String text,Dimension size) { 
		 
		this(text); 
		 
		this.setPreferredSize(size); 
	} 
	 
	public ThreadPanelBase(String text,Rectangle bounds) { 
		 
		this(text,new Dimension(bounds.width,bounds.height)); 
		 
		this.setBounds(bounds); 
	} 
	 
	 
	public ThreadPanelBase(Dimension size) { 
		 
		this.setPreferedSize(size); 

	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
		super.mouseMoved(e); 
		 
		gl.smn("Moved."); 
	} 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		super.mouseEntered(e); 
		 
		gl.smn("Entered."); 
	} 
	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		super.mouseExited(e); 
		 
		gl.smn("Exited."); 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		super.mouseDragged(e); 
		 
		gl.smn("Dragged."); 
	} 

	public Dimension getPreferredSize() { 
		return this.getPreferedSize(); 
	} 

	@Override 
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		 
		if(this.text != null) 
		g.drawString(String.format("%s %d",this.getText(),count), 20, 20); 
		 
	} 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 

		 
	} 

	private static void createAndShowGUI() { 

		JFrame frame = new JFrame("Test application"); 

		//frame.setLayout(null); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		 
		// Desktop. 

		ThreadPanelBase desktop = new ThreadPanelBase(new Dimension(PREF_W,PREF_H)); 
		 
		//desktop.setBounds(new Rectangle(50,50,100,100)); 
		 
		desktop.setBackground(Color.darkGray); 

		// Insert child items. 
		 
		AreaManager am = new AreaManager(desktop.getPreferedSize(),new Dimension(3,3)); 

		desktop.setLayout(null); 
		 
		int [] index = {0}; 
		 
		am.getRects().forEach(a-> 
		{ 
			ThreadPanelBase cell = new ThreadPanelBase(String.format("%s[%d] :","C",index[0]),a); 

			cell.setBackground(gl.getRandomColor()); 
			 
			desktop.add(cell,index[0]); 
		 
			index[0]++; 
		}); 
		 
		// 
		frame.getContentPane().add(desktop); 

		frame.pack(); 

		frame.setLocationRelativeTo(null); 

		frame.setVisible(true); 
	} 

	public static void main(String[] args) { 

		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				createAndShowGUI(); 
			} 
		}); 
	} 

	@Override 
	public void componentMoved(ComponentEvent e) { 
		 
	} 

	@Override 
	public void componentShown(ComponentEvent e) { 
		 
		 
	} 

	@Override 
	public void componentHidden(ComponentEvent e) { 
		 
	} 

} 
//Revision : 10.09.2018 12:49:16 
