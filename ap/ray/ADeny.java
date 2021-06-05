 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

public class ADeny extends AProperty { 

	 
	 
	public ADeny() 
	{ 
		super("deny_h"); 
	} 
	 
	 
	public ADeny(String name ) 
	{ 
		super(name); 
		 
	} 
	public ADeny(String name , String value) 
	{ 
		super(name,value); 
		 
	} 
		 
	public String getValue() 
	{ 
		return toStringValueOnly(); 
	} 
	 
	public int getIntValue() 
	{ 
		return Integer.parseInt(toStringValueOnly()); 
	} 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
