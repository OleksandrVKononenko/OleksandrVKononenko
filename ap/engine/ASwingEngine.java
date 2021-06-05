 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 


//The base template with key&mouse dispathers. 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.KeyboardFocusManager; 
import java.awt.MultipleGradientPaint; 
import java.awt.Paint; 
import java.awt.RadialGradientPaint; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.Point2D; 
import java.awt.image.BufferedImage; 

import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 

import ap.global.gl; 

public class ASwingEngine extends JFrame implements MouseListener, MouseMotionListener, KeyListener{ 

	 
	 
public static int SCREEN_WIDTH = 640; 

public static int SCREEN_HEIGHT = 480; 


private Rectangle SELECTOR = null; 

public  static BaseTimer ST = null; 

public static BufferedImage surface = null; 

public void keyPressed(KeyEvent evt) 
{ 

int keyCode = evt.getKeyCode(); 

gl.smn("KeyCode :" + keyCode); 

switch (keyCode) 
{ 
case KeyEvent.VK_ESCAPE: 
{ 
 
 
//running = false; 
 
System.exit(0); 
 
} 
break; 

} 
} 

public void keyTyped(KeyEvent evt) 
{ 
 
char c = evt.getKeyChar(); 
 
/*   if (c == '\b') { // if this is a backspace 
if (text.length() > 0) {  // remove last character 
text = text.substring(0, text.length()-1); 
} 
} else { 
text += c; 
} 
*/ 
} 


public void keyReleased(KeyEvent evt) 
{ 

} 


public void mouseDragged(MouseEvent e) 
{ 

} 


public void mouseClicked(MouseEvent e) 
{ 

} 

public void mouseMoved(MouseEvent e) 
{ 

} 


public void mousePressed(MouseEvent e) 
{ 

} 


public void mouseReleased(MouseEvent e) 
{ 

} 

public void mouseExited(MouseEvent e) 
{ 

} 

public void mouseEntered(MouseEvent e) 
{ 

} 

public ASwingEngine() 
{ 
SELECTOR = new Rectangle(0, 0, 0, 0); 

addMouseMotionListener(this); 

addMouseListener(this); 

KeyboardFocusManager manager = 
KeyboardFocusManager.getCurrentKeyboardFocusManager(); 

addKeyListener(this); 

ST = new SystemTimer(); 
 
//surface = gl.makeBufferedImage("d:\\tmp\\jpg\\t3.jpg"); 
 
surface = new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB); 
 
 
ST.setFrequency(100); 

ST.startTimer(); 

ST.refresh(); 

} 

public void paint(Graphics g) { 
//super.paint(g); 

 
 
g.setColor(Color.black); 
 
g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); 
 
 
if(surface != null) 
g.drawImage(surface,0,0,surface.getWidth(),surface.getHeight(),null); 
 
 
g.setColor(Color.white); 
 
g.drawString(String.format("Fps : %s Time : %s ", ST.getCurrentFrequency(),ST.getTime()),10,10); 
 
 
 
redraw(); 
 
 
 
ST.sleep(); 
 
 
} 

public void redraw() 
{ 
Graphics g =  surface.getGraphics(); 
 
g.setColor(Color.black); 
 
g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); 
 
g.setColor(Color.white); 
 
g.drawString(String.format("Fps : %s Time : %s ", ST.getCurrentFrequency(),ST.getTime()),10,10); 
 
repaint(); 
} 

public static void main(String[] args) { 
 
try 
{ 
JFrame frame = new ASwingEngine(); 
 
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT); 

frame.setLocationRelativeTo(null); 
 
frame.setUndecorated(true); 

frame.setVisible(true); 
} 
catch(Exception ex) 
{ 
gl.smn("<main> exception :" + ex.getMessage()); 
} 
 
finally 
{ 
ASwingEngine.ST.stopTimer(); 
} 

} 
} 
// Revision : 10.09.2018 12:49:14 
