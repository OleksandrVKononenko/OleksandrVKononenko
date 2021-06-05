 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

public class APid extends AProperty { 

	public APid() 
	{ 
		super("pid"); 
	} 
	 
	 
	public APid(String name) 
	{ 
		super(name); 
		 
	} 
	 
		 
	public APid(String name , String value) 
	{ 
		super(name,value); 
		 
	} 
	 
	public int getValue() 
	{ 
		int value = gl.E_ERROR; 
		 
		try 
		{ 
			value = Integer.parseInt(toStringValueOnly()); 
		} 
		 
		catch(NumberFormatException ex) 
		{ 
			gl.smn("<APid.getValue(Exception)"); 
			 
			return value; 
		} 
		 
		   return value; 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
