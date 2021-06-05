 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
package ap.force; 
import java.awt.Color; 
import java.awt.Graphics2D; 

//manages the background 

/** 
* @author Administrator 
* 
*/ 
public class Scene implements Drawable 
{ 
	 
	final Color background = new Color(225,255,255,120); 
		 
	public Scene() 
	{ 
		 
	} 
	 
	@Override 
	public void draw(Graphics2D g) 
	{ 
		// Erase background. 
		//g.setColor(background); 
		 
		//g.setColor(Color.BLACK); 
		 
		//g.fillRect(0, 0, Render.WIDTH, Render.HEIGHT); 
	} 
	 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
