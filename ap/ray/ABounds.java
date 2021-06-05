 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Rectangle; 

public class ABounds extends AProperty { 

	 
	private Rectangle rect; 
	 
	 
	public ABounds() 
	{ 
		super("bounds"); 
	} 
	 
	public ABounds(String name) 
	{ 
		super(name); 
		 
	} 

	 
	public ABounds(String name , String value) 
	{ 
		super(name,value); 
		 
	} 

	public Rectangle getRect() { 
		return rect; 
	} 

	public void setRect(Rectangle rect) { 
		this.rect = rect; 
	} 

	 
	public boolean getValue() 
	{ 
		int value = gl.E_ERROR; 
		 
		String s_value = toStringValueOnly(); 
				 
		String a[] = s_value.split(","); 
				 
		 
		try 
		{ 
			setRect( 
					new Rectangle ( 
							 
			Integer.parseInt(a[0]), 
			 
			Integer.parseInt(a[1]), 
			 
			Integer.parseInt(a[2]), 
			 
			Integer.parseInt(a[3]) 
			) 
			 
			); 
			 
		} 
		catch(NumberFormatException ex) 
		{ 
			gl.smn("<ABounds.getValue(Exception)"); 
			 
			return false; 
		} 
		 
		   	return true; 
	} 
	 
	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
