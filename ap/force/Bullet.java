 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.force; 

import java.awt.Graphics2D; 


public abstract class Bullet implements Actable, Drawable 
{ 
	double x, y, xvel, yvel; 
	int size, halfsize, power; 
	 
	public Bullet(int x, int y, int destx, int desty, int speed, int size, int power) 
	{ 
		this.x=x; 
		this.y=y; 
		this.size=size; 
		halfsize=size/2; 
		this.power=power; 
		 
		double dx = destx - x; 
		double dy = desty - y; 
		double incr = speed; 
		double dh = Math.sqrt(dx*dx + dy*dy); 
		 
		xvel = dx * incr / dh; 
		yvel = dy * incr / dh; 
		 
		Force.render.add(this); 
		Force.stage.add(this); 
	} 
	 

	@Override 
	public abstract void draw(Graphics2D g); 

	@Override 
	public void act() 
	{ 
		//insert enemy collision code 
		 
		//go straight 
		x+=xvel; 
		y+=yvel; 
		 
		if(x<0 || x>Render.WIDTH || y<0 || y>Render.HEIGHT) 
		{ 
			Force.render.remove(this); 
			Force.stage.remove(this); 
		} 
	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
