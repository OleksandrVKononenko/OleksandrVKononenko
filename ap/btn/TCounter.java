 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import ap.global.gl; 


public class TCounter { 

	private int counter; 
	 
		 
	public int getCounter() { 
		return counter; 
	} 

	public void setCounter(int counter) { 
		this.counter = counter; 
	} 

	public TCounter() { 
		 
		this.setCounter(gl.E_EMPTY); 
		 
	} 
	 
	public void inc() 
	{ 
		counter++; 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
