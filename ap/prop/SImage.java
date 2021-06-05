 
package ap.prop; 

import java.awt.image.BufferedImage; 
import java.io.ByteArrayOutputStream; 
import java.io.IOException; 
import java.util.List; 

import javax.imageio.ImageIO; 

import ap.global.gl; 
import ap.utils.Bau; 
import ap.utils.Fu; 

public class SImage extends SProperty { 

	private SBytes source; 
	 
	private SDimension size; 
	 
	 
	public SBytes getSource() { 
		return source; 
	} 

	public void setSource(SBytes source) { 
		this.source = source; 
	} 

	public SDimension getSize() { 
		return size; 
	} 

	public void setSize(SDimension size) { 
		this.size = size; 
	} 

	public SImage() { 
		 
	} 

	public SImage(String name) { 
		super(name); 
		 
	} 

	public SImage(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SImage(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		this.source = new SBytes(); 
		 
		this.size = new SDimension("img_size"); 
		 
				 
		if (! 
			 ( 
			  this.getSource().parse(value) && 
			  this.getSize().parse(value) 
			 
			 ) 
			) 
			 
			return false; 
				 
		try 
		{ 
			 
			return true; 
			 
		} 
		catch(NumberFormatException e) 
		{ 
			if(this.isDebug()) 
			gl.tracex(v,String.format("%s...%s...%s","NumberFormatException","Error",e.toString())); 
			 
			return false; 
		} 
	} 

	 
	@Override 
	public String toString() 
	{ 
		return String.format("%s%s", 
				this.getSize().toString(), 
				this.getSource().toString() 
				); 
	} 

	 
	public static void test_ImageFromFile(String path) 
	{ 
		Object v  = new Object(){}; 
		 
		SImage image = new SImage(); 
		 
			   //image.setDebug(true); 
		 
		List<String> list = Fu.getListOfStringFromFile(path); 

		 
		if(list != null && list.size() != gl.E_EMPTY) 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Ok",list.size())); 
		else 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Error"));			 
		 
		list.forEach(a-> 
		{ 
			if(image.parse(a)) 
			{ 
				gl.tracex(v,String.format("%s...%s...%s","Parse data","Ok",image.toString())); 
			} 
			else 
			{ 
				gl.tracex(v,String.format("%s...%s","Parse data","Error")); 
			} 
		 
		} 
		); 
	} 

	 
		 

	public static void main(String[] args) { 

		String path = "e:/bin/eclipse/wsp/organizer/data/byte_1302_1.txt"; 
		 
		test_ImageFromFile(path); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
