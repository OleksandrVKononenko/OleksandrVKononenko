 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
package ap.force; 

import java.awt.Color; 
import java.awt.Graphics2D; 

import ap.global.gl; 


public class Photon extends Bullet 
{ 
	private static Color color = Color.CYAN; 
	 
	public Photon(int x, int y, int destx, int desty) 
	{ 
		super(x, y, destx, desty, 10, 10, 5); 
	} 
	 
	@Override 
	public void draw(Graphics2D g) 
	{ 
		// To do. 
		g.setColor(gl.getRandomColor()); 
		 
		//g.setColor(color); 
		 
		g.fillOval(((int)x)-halfsize,((int)y)-halfsize,size,size); 
	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
