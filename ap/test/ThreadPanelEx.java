 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.test; 

import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.*; 

import ap.btn.TPanel; 

/** 
* Author : Oleksandr V. Kononenko 
* 
* 27 ???. 2017 ?. 9:25:49 Project name : Organizer Package name : ap.test File 
* name : ThreadPanel.java 
* 
*/ 
public class ThreadPanelEx extends TPanel { 

	private static final int PREF_W = 600; 
	private static final int PREF_H = 400; 
	private static final int DELAY = 17; 
	private int count = 0; 

	public ThreadPanelEx() { 
		new Timer(DELAY, new ActionListener() { 

			@Override 
			public void actionPerformed(ActionEvent e) { 
				count++; 
				repaint(); 
			} 
		}).start(); 
	} 

	public Dimension getPreferredSize() { 
		return new Dimension(PREF_W, PREF_H); 
	} 

	@Override 
	public void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		g.drawString("Count = " + count, 20, 20); 
	} 

	private static void createAndShowGUI() { 

		ThreadPanelEx paintEg = new ThreadPanelEx(); 

		JFrame frame = new JFrame("MyApp"); 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		frame.getContentPane().add(paintEg); 

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

} 
// Revision : 10.09.2018 12:49:16 
