 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.force; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.util.HashSet; 


public class Controller implements KeyListener 
{	 
	public Controller() 
	{ 
		Force.render.addKeyListener(this); 
	} 
	 
	public void step() 
	{ 
		HashSet<Actable> act = Force.stage.getActables(); 
		 
		for(Actable a : act) 
			a.act(); 
		 
		Force.stage.update(); 
		 
	} 

	@Override 
	public void keyPressed(KeyEvent k) 
	{ 
		if (k.getKeyCode() == KeyEvent.VK_ESCAPE) 
			System.exit(0); 
	} 

	@Override 
	public void keyReleased(KeyEvent arg0) {} 

	@Override 
	public void keyTyped(KeyEvent arg0) {} 
	 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
