 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.impexp; 

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 

import ap.global.gl; 
import ap.utils.*; 



/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 11 ????. 2016 ?. 15:15:19 
* Project  name : Organizer 
* Package  name : ap.impexp 
* File     name : yahoo.java 
* 
*/ 
public class Yahoo { 
	 
	/** 
	 * @param args 
	 */ 
	 

	public static boolean process(String path) 
	{ 
		return process(Su.BeforeAtPeriod(Fu.get_file_name(path)),path); 
	} 
	 
	@SuppressWarnings("resource") 
	public static boolean process(String ticker,String path) 
	{ 
		File file = new File(path); 
		 
		if(!file.exists()) 
			return false; 
		 
		long start_time = System.currentTimeMillis(); 
		 
		String fileOut =Fu.get_path(path)+ticker.concat(".etl"); 
		 
		String Brew = Su.getBrew(fileOut); 
		 
		String source; 
		 
		try { 
			source = Fu.get_str_from_file(path); 
		} catch (IOException e) { 
			 
			return false; 
		} 
				 
		String[] arr = source.split(System.lineSeparator()); 
				 
		if (arr.length == gl.E_EMPTY) 
			return false; 
		 
			YRow yr = new YRow(); 
		 
			 yr.setTicker(ticker); 
			 
			 yr.setBrew(Brew); 
		 
			 FileWriter fw = null; 
			 
	    try { 
			fw = new FileWriter(fileOut); 
		} catch (IOException e) { 
			 
			return false; 
		} 
	 
		for(int i=1;i< arr.length;i++) 
		{ 
			yr.setValue(arr[i]); 
			 
			if(yr.split(false)) 
			{ 
				//gl.smn(yr.toString()); 
				 
				try { 
					 
					fw.write(yr.toString()); 
					 
					fw.write(System.lineSeparator()); 
					 
				} catch (IOException e) { 
					 
					return false; 
				} 
			} 
			 
		} 
		 
					try { 
						 
						fw.flush(); 
						 
						fw.close(); 
						 
					} catch (IOException e) { 
						 
						return false; 
					} 
					 
					long end_time = System.currentTimeMillis(); 
					 
					gl.smn(fileOut.concat("...Done..." + (end_time - start_time) + " ms.")); 
		 
		return true; 
	} 
	 
	 
	public static void Test_process() 
	{ 
		if(process("AACC.txt")) 
			gl.smn("Test Ok."); 
		else 
			gl.smn("Test Error."); 
	} 
	 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 
		 
		//Test_process(); 
		 
		if (args.length != 2) 
		{ 
			gl.smn("Usage :  java -jar yahoo.jar FileNameForParsing"); 
			 
			return; 
		} 
		 
			Yahoo.process(args[0],args[1]); 
			 

	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
