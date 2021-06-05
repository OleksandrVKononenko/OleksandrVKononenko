 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream; 
import java.nio.file.CopyOption; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.nio.file.StandardCopyOption; 
import java.util.ArrayList; 
import java.util.List; 

import ap.global.gl; 

public class Replacer { 

	public Replacer() { 
		 
	} 

	public static boolean process(File path,String mask,String source,String target) 
	{ 
		 
			List<File> list = null; 
			 
			try { 
				list = Fu.getFilesInDirRecursively(path, 
						new String[]{mask} 
				); 
			} catch (IOException e) { 
				// REPLACE Auto-generated catch block 
				e.printStackTrace(); 
			} 
			 
			//list.forEach(a->{gl.smn(a.getAbsoluteFile());}); 
			 
			//gl.smn("Size : " + list.size()); 
			 
			list.forEach(obj->{ 
				 
					try { 
						 
						String src_prev = Fu.get_str_from_file(obj.getAbsolutePath()); 
						 
						String src = src_prev.replace(source,target); 
						 
						// Rename to *.bak 
						String bak = String.format("%s%s.%s", 
								Fu.get_path(obj.getAbsolutePath()), 
								Fu.get_pure_file_name(obj.getAbsolutePath()), 
								"bak"); 
						 
						//FileOutputStream fos = new FileOutputStream(bak); 
						 
						//Files.copy(Paths.get(obj.getAbsolutePath()),fos); 
						 
						if(Fu.isaFile(obj.getAbsolutePath())) 
							if(!Fu.deleteFile(obj.getAbsolutePath())) 
								gl.smn("Error while delete file : " + obj.getAbsolutePath()); 
						 
						Fu.to_file(obj.getAbsolutePath(),src); 
						 
						String post = String.format("// Revision : %s",DateUtil.date_time_stamp()); 
						 
						Fu.to_file(obj.getAbsolutePath(),post,true); 
						 
						if(Fu.isaFile(obj.getAbsolutePath())) 
							gl.smn(String.format("%s...%s",obj.getAbsolutePath(),"Ok")); 
						else 
							gl.smn(String.format("%s...%s",obj.getAbsolutePath(),"Error")); 
							 
					} catch (Exception e) { 

						 
					} 
				 
			}); 
			 
			 
			 
		return false; 
		 
	} 
	public static void main(String[] args) { 
		 
		//Replacer.process(Paths.get("e:\\bin\\eclipse\\wsp\\organizer\\src\\ap\\"),"*.{*}"); 
		 
		// Replacer.process(new File("e:/bin/eclipse/wsp/Organizer/src/ap/"),"java","// REPLACE" , "// REPLACE"); 
		 
		//Replacer.process(new File("e:\test.txt"),"txt",";\r\n" , ";\r"); 
	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
