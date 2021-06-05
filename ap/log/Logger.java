 
 
 
 
 
 
 
 
 
 
 
package ap.log; 


import java.io.FileWriter; 
import java.io.IOException; 

import ap.global.*; 
import ap.utils.DateUtil; 

public class Logger { 

	public  String message ; 
	 
	public String store; 
	 
	private boolean append ; 
	 
	private boolean deny; 
	 
	 
	public boolean isDeny() { 
		return deny; 
	} 
	public void setDeny(boolean deny) { 
		this.deny = deny; 
	} 
	public boolean isAppend() { 
		return append; 
	} 
	public void setAppend(boolean append) { 
		this.append = append; 
	} 
	public String getMessage() { 
		return message; 
	} 
	public void setMessage(String message) { 
		this.message = message; 
	} 
	public String getStore() { 
		return store; 
	} 
	public void setStore(String store) { 
		this.store = store; 
	} 
	 
	public  Logger() 
	{ 
		 
	} 
	 
	public  Logger(String store) 
	{ 
		this.setStore(store); 
		 
		this.setAppend(true); 
		 
	} 
	 
	public  Logger(String store,boolean append) 
	{ 
		this.setStore(store); 
		 
		this.setAppend(append); 
		 
	} 

	public void write(String message,boolean newline) 
	{ 
		if(this.isDeny()) 
			return; 
		 
		FileWriter file_writer = null; 

		try { 
		 
			file_writer = new FileWriter(this.getStore(), this.isAppend()); 

			file_writer.write(message); 
			 
			if(newline) 
			file_writer.write(System.lineSeparator()); 
			 
	 
		} catch (IOException e) { 
			 
			return; 
		} 
		finally 
		{ 
			try { 
				 
				file_writer.flush(); 
				 
				file_writer.close(); 
				 
			} catch (IOException e) { 
				return; 
	}	 
		} 
		 
	} 

	public void awrite(String message) 
	{ 
	 
		if(this.isDeny()) 
			return; 
		 
		FileWriter file_writer = null; 

		try { 
		 
			file_writer = new FileWriter(this.getStore(), this.isAppend()); 

			file_writer.write(message); 
			 
			file_writer.write(System.lineSeparator()); 
			 
	 
		} catch (IOException e) { 
			 
			return; 
		} 
		finally 
		{ 
			try { 
				 
				file_writer.flush(); 
				 
				file_writer.close(); 
				 
			} catch (IOException e) { 
				return; 
	}	 
		} 
		 
	} 

	public void write(String message) 
	{ 
		if(this.isDeny()) 
			return; 
		 
		FileWriter file_writer = null; 

		try { 
		 
			file_writer = new FileWriter(this.getStore(),this.isAppend()); 

			String str_date_time = DateUtil.date_time_stamp(); 
					 
			file_writer.write("\n"); 
			 
			file_writer.write(str_date_time); 
			 
			file_writer.write(" "); 
			 
			file_writer.write(message); 
	 
		} catch (IOException e) { 
			 
			e.printStackTrace(); 
		} 
		finally 
		{ 
			try { 
				 
				file_writer.flush(); 
				 
				file_writer.close(); 
				 
			} catch (IOException e) { 
				// REPLACE Auto-generated catch block 
				e.printStackTrace(); 
			}	 
		} 
		 
	} 

	public void writec(String message) 
	{ 
		if(this.isDeny()) 
			return; 
		 
		this.write(message); 
		 
		gl.smn(message); 
	 
	} 
	 
	public void awritec(String message) 
	{ 
	 
		this.awrite(message); 
		 
		gl.smn(message); 
	 
	} 

} 

// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
