 
 
package ap.nio; 

import java.nio.channels.SocketChannel; 
import java.util.LinkedList; 
import java.util.List; 

public class EchoWorker implements Runnable { 
	private List queue = new LinkedList(); 
	 
	public void processData(NioServer server, SocketChannel socket, byte[] data, int count) { 
		byte[] dataCopy = new byte[count]; 
		System.arraycopy(data, 0, dataCopy, 0, count); 
		synchronized(queue) { 
			queue.add(new ServerDataEvent(server, socket, dataCopy)); 
			queue.notify(); 
		} 
	} 
	 
	public void run() { 
		ServerDataEvent dataEvent; 
		 
		while(true) { 
			// Wait for data to become available 
			synchronized(queue) { 
				while(queue.isEmpty()) { 
					try { 
						queue.wait(); 
					} catch (InterruptedException e) { 
					} 
				} 
				dataEvent = (ServerDataEvent) queue.remove(0); 
			} 
			 
			// Return to sender 
			dataEvent.server.send(dataEvent.socket, dataEvent.data); 
		} 
	} 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:45 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
