 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.util.List; 

import ap.global.gl; 
import ap.log.Logger; 

public class ReplaceUtils { 

	 
	public static boolean deleteRow(String source,String target) 
	{ 
		 
		if (!Fu.isaFile(source)) 
			return false; 
		 
		List<String> list = Fu.get_list_from_file(source); 
		 
		if(list.size() == gl.E_EMPTY) 
		{ 
			return false; 
		} 
		 
		Logger log = new Logger(target); 
		 
		list.forEach(a->{ 
			 
			 
			if(a.trim().length() != gl.E_EMPTY) 
			{ 
				log.awrite(a); 
			} 
		}); 
		 
			return Fu.isaFile(target); 
		 
		 
	} 
	 
	public static boolean deleteChar(String source,String target,String find,String replace) 
	{ 
		 
		if (!Fu.isaFile(source)) 
			return false; 
		 
		List<String> list = Fu.get_list_from_file(source); 
		 
		if(list.size() == gl.E_EMPTY) 
		{ 
			return false; 
		} 
		 
		Logger log = new Logger(target); 
		 
		list.forEach(a->{ 
			 
			 
			if(a.trim().length() != gl.E_EMPTY) 
			{ 
				String a_tmp = a.replace(find,replace); 
				 
				log.awrite(a_tmp); 
			} 
		}); 
		 
			return Fu.isaFile(target); 
		 
		 
	} 
	 
	 
	public ReplaceUtils() { 
		 
	} 

	public static void main(String[] args) { 
		 
		String source = "e:\\test.txt"; 
		 
		String target = "e:\\test.bak"; 
		 
		if(!deleteChar(source,target,"\r\n","\t")) 
			gl.smn("Error."); 
		else 
			gl.smn("Ok."); 
		 
	} 

} 
