 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import ap.gen.IDGen; 
import ap.global.gl; 

public interface IDentity { 
	 
	public int id = gl.E_ERROR; 
	 
	public int pid = gl.E_ERROR; 
	 
	public int index = gl.E_ERROR; 
	 

	default int getId() 
	{ 
		return IDGen.nextId(); 
	} 
	 
	 
	 
} 
