 
 
package ap.nio; 

public class RspHandler { 
	private byte[] rsp = null; 
	 
	public synchronized boolean handleResponse(byte[] rsp) { 
		this.rsp = rsp; 
		this.notify(); 
		return true; 
	} 
	 
	public synchronized void waitForResponse() { 
		while(this.rsp == null) { 
			try { 
				this.wait(); 
			} catch (InterruptedException e) { 
			} 
		} 
		 
		System.out.println(new String(this.rsp)); 
	} 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:45 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
