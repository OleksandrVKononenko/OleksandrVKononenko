 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.config; 

import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 

import ap.global.gl; 
import ap.utils.Fu; 

public class TConfigEx { 
	 
	private String  home = String.format( 
			"%s\\%s.config", Fu.get_path(Fu.getCurrentDir()), 
			"application"); 
	 
	private Map<Integer,Map<String,String>> items = new HashMap<Integer,Map<String,String>>(); 
	 
	private String 	payload; 
	 
	 
	public String getPayload() { 
		return payload; 
	} 

	public void setPayload(String payload) { 
		this.payload = payload; 
	} 
	 
	 
	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public Map<Integer,Map<String,String>>  getItems() { 
		return items; 
	} 

	public void setItems(Map<Integer,Map<String,String>>  items) { 
		this.items = items; 
	} 

	public TConfigEx() { 
		 
	} 

	public TConfigEx(String param) { 
		 
		this.setHome(param); 
	} 

	public boolean read() 
	{ 
		 
		if(!Fu.isaFile(this.getHome())) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...%s","Check point of home file,not exists yet",this.getHome(),gl.S_WARN )); 
			 
			return false; 
		} 
		 
		 
		try { 
			this.setItems(Fu.getMapFromFileAsStringScannerSkipComments(this.getHome())); 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},String.format("%s...%s...%s...%s","Exception while read",this.getHome(),e.toString().substring(1,32),gl.S_ERROR )); 
			 
			return false; 
		} 
		 
			return true; 
	} 

	public String toString() 
	{ 
	 
	StringBuilder sb = new StringBuilder(); 
	 
	for(Entry<Integer,Map<String,String>> e : items.entrySet()) 
	{	 
		Map<String,String> map_value = e.getValue(); 
		 
		sb.append(String.format("%05d ",e.getKey())); 
				 
		for(Entry<String,String> c : map_value.entrySet()) 
		{ 
			sb.append(String.format("%s=%s;",c.getKey(),c.getValue())); 
		} 
				 
			sb.append(System.lineSeparator()); 
		 
	} 
	 
		return sb.toString(); 
		 
	}	 
	 
	public static void Test_read() 
	{ 
		TConfigEx app = new TConfigEx("E:\\bin\\eclipse\\wsp\\Organizer\\data\\Base.txt"); 
		 
		String msg = "Testing of read method"; 

		if(!app.read()) 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_ERROR )); 
		else 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...\n\n%s",msg,gl.S_OK,app.toString() )); 
		} 
				 
	} 
		 
	 
	public static void main(String[] args) { 
		 
		Test_read(); 
		 
	} 
	 

} 
