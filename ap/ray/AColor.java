 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

public class AColor extends AProperty { 


	private int red ; 
	 
	private int green ; 
	 
	private int blue ; 
	 
	 
	public AColor() 
	{ 
		super("color"); 
	} 
	 
	public AColor(String name) 
	{ 
		super(name); 
		 
	} 
	 
	 
	public AColor(String name , String value) 
	{ 
		super(name,value); 
		 
	} 
	 
	public int getRed() { 
		return red; 
	} 

	public void setRed(int red) { 
		this.red = red; 
	} 

	public int getGreen() { 
		return green; 
	} 

	public void setGreen(int green) { 
		this.green = green; 
	} 

	public int getBlue() { 
		return blue; 
	} 

	public void setBlue(int blue) { 
		this.blue = blue; 
	} 

	public boolean getValue() 
	{ 
		int value = gl.E_ERROR; 
		 
		String s_value = toStringValueOnly(); 
				 
		String a[] = s_value.split(","); 
				 
		 
		try 
		{ 
			 
			this.setRed(Integer.parseInt(a[0])); 
			 
			this.setGreen(Integer.parseInt(a[1])); 
			 
			this.setBlue(Integer.parseInt(a[2])); 
			 
		} 
		catch(NumberFormatException ex) 
		{ 
			gl.smn("<AColor.getValue(Exception)"); 
			 
			return false; 
		} 
		 
		   	return true; 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
