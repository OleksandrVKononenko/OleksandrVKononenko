 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.gen; 

import java.util.concurrent.atomic.AtomicLong; 

import ap.global.gl; 

public class GIDGen{ 
 
public static AtomicLong counter = new AtomicLong(0); 
 
public static int nextId() { 
	return (int)counter.incrementAndGet(); 
} 

public static void main(String[] args) { 
		 
		gl.smn(GIDGen.nextId()); 
		 
	 
	} 

} 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
