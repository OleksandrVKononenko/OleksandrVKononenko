 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.helpers; 

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import ap.global.*; 

public class IteGenerator { 

	public static String mask = "%s.https://www.forexite.com/free_forex_quotes/20%s/%s/%s%s%s.zip"; 
	 
	public static List<String> fileList = new ArrayList<String>(); 
	 
	public static void getText(String pmask) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		//DbLogger log = new DbLogger("e:\\temp\\list_of_url.txt"); 
		 
		FileWriter writer = null; 
		 
		try { 
			writer = new FileWriter("e:\\temp\\list_of_url.txt", false); 
		} catch (IOException e1) { 
			 
			e1.printStackTrace(); 
		} 
		 
		int i = 1; 
		 
		for(int year=1;year<=15;year++) 
		{ 
			for(int month=1;month<=12;month++) 
			{ 
				for(int day=1;day<=31;day++) 
				{ 
					String o = String.format(pmask,a(i),a(year),a(month),a(day),a(month),a(year)); 
					 

					sb.append(o); 
					 
					sb.append(System.lineSeparator()); 
										 
					 
					//String f = String.format("%s%s%s.zip",a(day),a(month),a(year)); 
					 
					//DbLogger log = new DbLogger("e:\\temp\\gen\\"+f); 
					 
					//log.write("Some info."); 
					 
					gl.smn(o); 
					 
					i++; 
					 
				} 
			} 
		} 
		 
			 try { 
				writer.write(sb.toString()); 
				 
				writer.flush(); 
				 
				writer.close(); 
				 
			} catch (IOException e) { 
				 
				e.printStackTrace(); 
			} 
			 
			 
	} 
	 
	public static void generateFileList(File node) { 

		// add file only 
		if (node.isFile()) { 
			 
			String s = node.getAbsoluteFile().toString(); 
			 
			System.out.println(s); 
			 
			fileList.add(s); 
		} 

		if (node.isDirectory()) { 
			 
			String[] subNote = node.list(); 

			for (String filename : subNote) { 
				generateFileList(new File(node, filename)); 
			} 
		} 

	} 

	 
	 
	public static String a(int value) 
	{ 
		return (value < 10) ? "0".concat(""+value) : ""+value; 
	} 
	 
	public static void main(String[] args) { 
		// Auto-generated method stub 
		 
		//getText(mask); 
		 
		generateFileList(new File("e:\\temp\\gen\\")); 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
