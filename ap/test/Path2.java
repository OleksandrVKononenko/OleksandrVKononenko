 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.test; 

import java.awt.Graphics; 

import javax.swing.JFrame; 
import javax.swing.JPanel; 

public class Path2 extends JPanel { 

public void paint(Graphics g) { 
int xpoints[] = {25, 145, 145, 25}; 
int ypoints[] = {25, 25,145,145}; 
int npoints = 4; 
 
g.drawPolygon(xpoints, ypoints, npoints); 
} 

public static void main(String[] args) { 
JFrame frame = new JFrame(); 
frame.getContentPane().add(new Path2()); 

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
frame.setSize(200,200); 
frame.setVisible(true); 
} 
} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
