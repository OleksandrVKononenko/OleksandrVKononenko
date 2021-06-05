 
package ap.force; 


//import java.awt.event.ActionEvent; 
//import java.awt.event.ActionListener; 

//import javax.swing.Timer; 



public class Force { 
	 
	public static Controller controller; 
	public static Render render; 
	public static Stage stage; 
	public static Display display; 
	public static Player player; 
	public static Odin odin; 
	 
	public static void Update() 
	{ 
		controller.step(); 
		 
		render.repaint(); 
	 
		 
	} 


	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

		// order does matter 
		render = new Render(); 
		 
		controller = new Controller(); 
		 
		stage = new Stage(); 
		 
		display = new Display(); 
		 
		player = new Player(); 
		 
		odin = new Odin(Render.FRAMES_PER_SECOND); 
			 
		odin.start(); 
		 
		while(true) 
		{ 
			Force.Update(); 
			 
			try { 
				Thread.sleep(odin.getReal_tock()); 
			} catch (InterruptedException e) { 
				 
				e.printStackTrace(); 
			} 
			 
		} 
	} 
} 


// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
