 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
/** 
* 
*/ 
package ap.id; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 15 ??? 2016 ?. 1:13:43 
* Project  name : Organizer 
* Package  name : ap.id 
* File     name : Tid.java 
* 
*/ 

import java.util.concurrent.atomic.AtomicLong; 

import ap.global.gl; 

public class Tid { 
 
	private static AtomicLong counter = new AtomicLong(0); 
 
public static long nextId() { 
return counter.incrementAndGet(); 
} 

public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	gl.smn(Tid.nextId()); 
	 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
