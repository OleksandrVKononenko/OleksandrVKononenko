 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.test; 

import ap.force.Odin; 
import ap.force.Render; 
import ap.global.gl; 
import ap.mng.SPanelManager; 


public class TestControls { 

	 
	public static Odin odin ; 
	 
	public TestControls() { 
		 
	} 
	 
	 
	public static void startUp() 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		if(!mng.createGUI()) 
			gl.tracex(v,String.format("Try to create GUI...Error.")); 
		 
		 	 
	} 

	public static void main(String[] args) { 
		 
		odin = new Odin(Render.FRAMES_PER_SECOND); 
		 
		startUp(); 
		 
	} 

} 

// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
