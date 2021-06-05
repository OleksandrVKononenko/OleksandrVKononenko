 
 
package ap.nio; 

import java.nio.channels.SocketChannel; 

class ServerDataEvent { 
	
	public NioServer server; 
	
	public SocketChannel socket; 
	
	public byte[] data; 
	 
	public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) { 
		this.server = server; 
		this.socket = socket; 
		this.data = data; 
	} 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:45 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
