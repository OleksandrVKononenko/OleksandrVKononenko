 
package ap.gen; 

import java.util.concurrent.atomic.AtomicLong; 

import ap.global.gl; 

public class IDGen { 

	public static AtomicLong counter = new AtomicLong(gl.E_EMPTY); 

	public static int nextId() { 

		long m_next = counter.incrementAndGet();
		
		//gl.tx_k(new Object(){},gl.sf("[[ Gen ^^^NEXT^^^...%s ]]",m_next));
		
		return (int) m_next;
		
	} 
	
	public static int nextId(String trace) { 

		long m_next = counter.incrementAndGet();
		
		//gl.tx_k(new Object(){},gl.sf("[[ Gen ^^^NEXT^^^...%s...%s ]]",m_next,trace));
		
		return (int) m_next;
		
	} 

	public static void reset() { 
		
		counter = new AtomicLong(gl.E_EMPTY); 
	
		//gl.tx_k(new Object(){},gl.sf("[[ Gen ^^^RESET TO^^^...%d]]",counter));
		
	} 

	public static void reset(int value) { 
		
		counter = new AtomicLong(value);
		
	} 
	public static void reset(String place,int value) { 
		
		counter = new AtomicLong(value);
		
		//gl.tx_k(new Object(){},gl.sf("%s...RESET...to...%d]]",place,counter.get()));
		
	} 
	
	public static int get() { 
		
		return (int)counter.get();
		
	} 

	public static void main(String[] args) { 

		gl.smn(gl.sf("Next id...%05d", IDGen.nextId())); 
		 
		for(int i=0;i<100;i++) 
		{ 
			gl.smn(gl.sf("Next id...%05d", IDGen.nextId())); 
			 
			if((i%2)== gl.E_EMPTY) 
				IDGen.reset(); 
			 
		} 

	} 

} 
