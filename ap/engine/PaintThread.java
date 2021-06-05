 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

import java.awt.Graphics; 
import java.awt.image.BufferedImage; 

public class PaintThread  extends Thread{ 

public Graphics g = null; 

public Graphics getG() { 
	return g; 
} 

public void setG(Graphics g) { 
	g = g; 
} 

public BufferedImage getSurface() { 
	return surface; 
} 

public void setSurface(BufferedImage surface) { 
	this.surface = surface; 
} 

public BufferedImage surface = null; 

public PaintThread() 
{ 
} 

public PaintThread(BufferedImage surface) 
{ 

	surface = surface; 
} 

public PaintThread(Graphics g , BufferedImage surface) 
{ 
	g = g; 
	 
	surface = surface; 
} 

public void run() { 

	if (surface != null && g != null) 
		g.drawImage(surface, 0, 0, surface.getWidth(), surface.getHeight(),null); 

} 

} 
