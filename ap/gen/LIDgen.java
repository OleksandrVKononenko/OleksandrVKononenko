 
 
 
 
 
 
 
 
 
 
 
 
package ap.gen; 
import java.util.concurrent.atomic.AtomicLong; 

import ap.global.gl; 

public class LIDgen { 

	public static AtomicLong counter = new AtomicLong(gl.E_EMPTY); 

	public static int nextId() { 
		return (int) counter.incrementAndGet(); 
	} 

	public static void reset() { 
		 
		counter = new AtomicLong(gl.E_EMPTY); 
	} 
	 
	public static void main(String[] args) { 

		gl.smn(gl.sf("Next id...%05d", LIDgen.nextId())); 
		 
		for(int i=0;i<100;i++) 
		{ 
			gl.smn(gl.sf("Next id...%05d", LIDgen.nextId())); 
			 
			if((i%2)== gl.E_EMPTY) 
				LIDgen.reset(); 
			 
		} 

	} 

} 
