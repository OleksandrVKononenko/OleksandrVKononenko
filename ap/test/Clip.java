 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.test; 

import java.awt.Graphics; 

import javax.swing.JFrame; 
import javax.swing.JPanel; 

public class Clip extends JPanel { 

public void paint(Graphics g) { 
	 
g.drawString(g.getClipBounds().toString(), 10, 30); 

g.clipRect(10, 40, getSize().width - 20, getSize().height - 80); 

g.fillOval(0, 0, getSize().width, getSize().height); 

String newClip = g.getClipBounds().toString(); 

g.setClip(0, 0, getSize().width, getSize().height); 

g.drawString(newClip, 10, getSize().height - 10); 
} 

public static void main(String[] args) { 
JFrame frame = new JFrame(); 
frame.getContentPane().add(new Clip()); 

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
frame.setSize(200, 200); 
frame.setVisible(true); 
} 
} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
