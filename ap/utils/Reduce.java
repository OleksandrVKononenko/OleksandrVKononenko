 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.utils; 

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List; 

import ap.global.gl; 



/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 06 ???. 2016 ?. 17:10:22 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : Reduce.java 
* 
*/ 
public class Reduce { 

	/** 
	 * 
	 */ 
	 
	public static List<String> dirs = new ArrayList<String>(); 
	 
	public Reduce() { 
		// REPLACE Auto-generated constructor stub 
	} 

	/** 
	 * @param args 
	 */ 
	 

	public static boolean appendToFile(String fileName,String data) 
	{ 
		 
			FileWriter fw = null; 
		 
			File file = new File(fileName); 
			 
			if(!file.exists()) 
			{ 
				try { 
					fw = new FileWriter(file,false); 
				} catch (IOException e) { 

					return false; 
				} 
			} 
			else 
			{ 
				try { 
					fw = new FileWriter(file,true); 
				} catch (IOException e) { 

					return false; 
				} 
			} 
				 
		try { 

			 
			fw.write(data); 
			 
			fw.write(System.lineSeparator()); 

			fw.flush(); 

			fw.close(); 

			 
		} catch (IOException e) { 

			return false; 
		} 
		 
			return true; 
	} 

	 
	 
	 
	public static boolean saveToFile(String file, StringBuilder sb) 
	{ 
		 
			FileWriter fw = null; 

		try { 

			fw = new FileWriter(file); 

			fw.write(sb.toString()); 

			fw.flush(); 

			fw.close(); 

			 
		} catch (IOException e) { 

			return false; 
		} 
		 
			return true; 
	} 
	 
	 
	public static boolean doIt_1(String path) 
	{ 

		String find_pkg   = "package"; 
		 
		String find_class = "class "; 
		 
		String pkg_name = ""; 
		 
		String pkg_name_orig = ""; 
		 
		String pkg_name_dir = ""; 
						 
		String fileName  = ""; 
		 
		String class_name = ""; 
		 
		String root_path = Fu.get_path(path); 
		 
		String user_dir = System.getProperty("user.dir"); 
		 
		String wrk_dir = ""; 
		 
		 
		if(path.contains(":")) 
		{ 
			wrk_dir = root_path; 
		} 
		else 
		{ 
			wrk_dir = user_dir + "\\" + root_path; 
		} 
		 
		 
		boolean open_gate = false; 
		 
		String source = ""; 
		 
		 
		gl.smn("ROOT path : " + root_path); 
		 
		gl.smn("USER dir  : " + user_dir); 
		 
		 
		try { 
			source = Fu.get_str_from_file(path); 
		} catch (IOException e) { 

			gl.smn("Reduce.doIt() Error while read source file"); 
			 
			return false; 
		} 
		 
				 
		String[] arr = source.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int pkg_count = gl.E_OK; 
		 
		int clazz_count = gl.E_OK; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		String real_dir = ""; 
		 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			// Create file name 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
	 
				if (pkg_count > gl.E_OK) 
				{ 
					if (!appendToFile(fileName,sb.toString())) 
					{ 
						gl.smn("Error while write to file : " + fileName); 
					} 
						sb.setLength(gl.E_EMPTY); 
						 
				} 
				 
				pkg_name_orig = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name_orig = pkg_name_orig.replace(";"," ").trim(); 
				 
				pkg_name = pkg_name_orig.replace(".","_").replace(";"," "); 
				 
				pkg_name_dir = pkg_name_orig.replace(".","\\").replace(";"," "); 
				 
				fileName = pkg_name; 
				 
				msg = String.format("%d.%s",pkg_count,pkg_name_orig); 
				 
				real_dir = wrk_dir + pkg_name_dir; 
				 
				gl.smn("Package : " + msg + " Template wrk_dir : "  + real_dir); 
				 
				 
						 
				if(!Files.exists(Paths.get(real_dir))) 
				{ 
					try { 
						 
						Files.createDirectories(Paths.get(real_dir)); 
						 
					} catch (IOException e) { 

						gl.smn("Error while create dir : " + real_dir); 
						 
						return false; 
					} 
								 
					 
				} 
				 
				 
				if(pkg_count>1) 
				{ 
					 
				} 
				 
				pkg_count++; 
				 
				 
			} // if package header 
			 
			 
			if (arr[i].contains(find_class)==true && (!( arr[i].contains("*") || arr[i].contains("String ") 
					 
					|| arr[i].contains("System.")))) 
			{ 
				 
				class_name = Su.AfterAtTerm(arr[i],"class"," "); 
				 
				class_name = class_name.trim().replace("{",""); 
				 
				class_name = class_name.trim().replace(";",""); 
				 
				if(class_name.trim().length() != gl.E_EMPTY) 
				{ 
					//gl.smn(String.format("%d.Found clazz %s.%s  Debug : %s ",clazz_count,pkg_name_orig,class_name,arr[i])); 
					 
					gl.smn(String.format("%d.Found clazz %s.%s ",clazz_count,pkg_name_orig,class_name)); 
					 
					clazz_count++; 
					 
					gl.smn("Target class path : " + real_dir); 
					 
					gl.smn("Target class  : " + class_name); 
					 
					gl.smn("Target class name : " + real_dir+"\\"+class_name.concat(".java")); 
					 
					fileName = real_dir+"\\"+class_name.concat(".java"); 
				} 
				 
			} 
			 
				 
				sb.append(arr[i]); 
			 
			 
			 
			if(fileName.trim().length() != gl.E_EMPTY) 
			{ 
				 
				if (!appendToFile(fileName,arr[i])) 
				{ 
					gl.smn("Error while write to file : " + fileName); 
				} 
				 
				 
			} 
			 
			 
		} // end for. 
		 
				return true; 
		 
		 
		 
} 

	public static boolean doIt_2(String path) 
	{ 

		String find_pkg   = "package"; 
		 
		String find_class = "class "; 
		 
		String pkg_name = ""; 
		 
		String pkg_name_orig = ""; 
		 
		String pkg_name_dir = ""; 
						 
		String fileName  = ""; 
		 
		String class_name = ""; 
		 
		String root_path = Fu.get_path(path); 
		 
		String user_dir = System.getProperty("user.dir"); 
		 
		String wrk_dir = ""; 
		 
		 
		if(path.contains(":")) 
		{ 
			wrk_dir = root_path; 
		} 
		else 
		{ 
			wrk_dir = user_dir + "\\" + root_path; 
		} 
		 
		 
		boolean open_gate = false; 
		 
		String source = ""; 
		 
		 
		gl.smn("ROOT path : " + root_path); 
		 
		gl.smn("USER dir  : " + user_dir); 
		 
		 
		try { 
			source = Fu.get_str_from_file(path); 
		} catch (IOException e) { 

			gl.smn("Reduce.doIt() Error while read source file"); 
			 
			return false; 
		} 
		 
				 
		String[] arr = source.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int pkg_count = gl.E_OK; 
		 
		int clazz_count = gl.E_OK; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		String real_dir = ""; 
		 
		boolean bl_reset_buffer = false; 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			 
			// ?????? ???????? ???????????? ??????. 
			// ?????????? ?????????? ??? ?????? ? ??????? ? ??????. 
			// ???? ?????? ?? ?????? ?? ????????????? ??? ???????????? ?????? ??? ???????????. 
			// ????????? ???? ????????? ??????????? ?? ???????????? ??????, 
			// ?????? ??? ????? ???? ????????, ??? ???? ???????? ????????? ??????? ? ????????? ?? ??? 
			// ?? ?????????. 
			 
			// ???????? ? ??????, ??? ? ????? ?????? ??????? ?? ????? ??????? ??????????? ? ???? ??????, 
			// ??????? ?????? ??????. 
						 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
	 
				//if (pkg_count == gl.E_OK) 
				//{ 
					// ???????? ??? ??????? ?????? 
					// ???? ?????? ??????? ?? ???????????. 
					// ?????? ??????????? ? ???????. 
				//} 
				//{ 
					// ?????? ?? ???????????? ????? ?????? ???????. 
					// ??????????????? ?? ?????????? ???????????? ??????(??????????) ? 
					// ???????????? ??????(?????) ????? ?? ??????????????? ??????? ???? ?????????. 
					 
					if(sb.toString().length() > 50) 
					bl_reset_buffer = true; 
					else 
					sb.setLength(gl.E_EMPTY); 
					 
										 
				//} 
			 
				 
				pkg_name_orig = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name_orig = pkg_name_orig.replace(";"," ").trim(); 
				 
				pkg_name = pkg_name_orig.replace(".","_").replace(";"," "); 
				 
				pkg_name_dir = pkg_name_orig.replace(".","\\").replace(";"," "); 
				 
				msg = String.format("%d.%s",pkg_count,pkg_name_orig); 
				 
				real_dir = wrk_dir + pkg_name_dir; 
				 
				gl.smn("Package name : " + pkg_name); 
				 
				pkg_count++; 
				 
				 
			} // if package header 
			 
			 
			// ?????? ????????????? ???????????? ??????. 
			 
			if (arr[i].contains(find_class)==true && (!( arr[i].contains("*") || arr[i].contains("String ") 
					 
					|| arr[i].contains("System.")))) 
			{ 
				 
				class_name = Su.AfterAtTerm(arr[i],"class"," "); 
				 
				class_name = class_name.trim().replace("{",""); 
				 
				class_name = class_name.trim().replace(";",""); 
				 
				if(class_name.trim().length() != gl.E_EMPTY) 
				{ 
					 
					clazz_count++; 
					 
				} 
				 
			}// clazz_name trim() <> 0 
			 
			 
			 
				// ?????? ???????? ???????. 
			 
			    if(bl_reset_buffer==true) 
			    { 
			    	//????????? ?????????? ???????????? ?????? ? ?????? ??? ???????? ???????. 
			    	// ?????????? fileName. 
			    	 
			    	String txt = sb.toString(); 
			    	 
			    	int len = txt.length(); 
			    	 
			    	// Clear of the buffer. 
			    	sb.setLength(gl.E_EMPTY); 
			    	 
			    	// Reset flag. 
			    	bl_reset_buffer = false; 
			    	 
			    	String file_name = real_dir+"\\"+class_name.concat(".java"); 
			    	 
			    	gl.smn("Target class name : " + file_name); 
			    	 
			    	gl.smn("???? : " + fileName + " size : " + len); 
			    	 
			    	String dir = Fu.get_path(file_name); 
			    	 
			    	gl.smn("Create dir : " + dir); 
			    	 
			    	// ??????? ??????????. 
			    	if(!Files.exists(Paths.get(dir))) 
					{ 
						try { 
							 
							Files.createDirectories(Paths.get(dir)); 
							 
						} catch (IOException e) { 

							gl.smn("Error while create dir : " + dir); 
							 
							return false; 
						} 
					} 
			    	 
			    	// ????? ? ????. 
					if (!appendToFile(file_name,txt)) 
					{ 
						gl.smn("Error while write to file : " + fileName); 
					} 
			    	 
			    	 
			    } 
			 
			 
				 
			    	// ??? ??????? ? ??????. 
				 
					sb.append(arr[i]); 
					 
					sb.append(System.lineSeparator()); 
				 
			 
			 
			/* 
			if(fileName.trim().length() != gl.E_EMPTY) 
			{ 
				 
				if (!appendToFile(fileName,arr[i])) 
				{ 
					gl.smn("Error while write to file : " + fileName); 
				} 
			} 
			*/ 
			 
			 
		} // end for. 
		 
				return true; 
		 
		 
		 
} 

	public static boolean doIt(String path) 
	{ 

		String find_pkg   = "package"; 
		 
		String find_class = "class "; 
		 
		String pkg_name = ""; 
		 
		String pkg_name_orig = ""; 
		 
		String pkg_name_dir = ""; 
						 
		String fileName  = ""; 
		 
		String class_name = ""; 
		 
		String root_path = Fu.get_path(path); 
		 
		String user_dir = System.getProperty("user.dir"); 
		 
		String wrk_dir = ""; 
		 
		 
		if(path.contains(":")) 
		{ 
			wrk_dir = root_path; 
		} 
		else 
		{ 
			wrk_dir = user_dir + "\\" + root_path; 
		} 
		 
		 
		boolean open_gate = false; 
		 
		String source = ""; 
		 
		 
		gl.smn("ROOT path : " + root_path); 
		 
		gl.smn("USER dir  : " + user_dir); 
		 
		 
		try { 
			source = Fu.get_str_from_file(path); 
		} catch (IOException e) { 

			gl.smn("Reduce.doIt() Error while read source file"); 
			 
			return false; 
		} 
		 
				 
		String[] arr = source.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int pkg_count = gl.E_OK; 
		 
		int clazz_count = gl.E_OK; 
		 
				 
		//StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		String real_dir = ""; 
		 
		boolean bl_reset_buffer = false; 
		 
		String[] arr_pkg = new String[length]; 
		 
		String dir = ""; 
		 
		String file_name = ""; 
		 
		List<String>  list = new ArrayList<String>(); 
		 
		for(int i=0;i < length;i++) 
		{ 
						 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
				 
				bl_reset_buffer = true; 
				 
				pkg_name_orig = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name_orig = pkg_name_orig.replace(";"," ").trim(); 
				 
				pkg_name = pkg_name_orig.replace(".","_").replace(";"," "); 
				 
				pkg_name_dir = pkg_name_orig.replace(".","\\").replace(";"," "); 
				 
				msg = String.format("%d.%s",pkg_count,pkg_name_orig); 
				 
				real_dir = wrk_dir + pkg_name_dir; 
				 
				gl.smn("Package name : " + pkg_name); 
				 
				if (!appendToFile("a_marker.sql","Jump package : " + pkg_name)) 
				{ 
					gl.smn("Error while write to file : "); 
				} 
				 
				class_name = ""; 
				 
				 
				pkg_count++; 
				 
				 
			} // if package header 
			 
			 
			// ?????? ????????????? ???????????? ??????. 
			 
			if (arr[i].contains(find_class)==true && (!( arr[i].contains("*") || arr[i].contains("String ") 
					 
					|| arr[i].contains("System.")))) 
			{ 
				 
				class_name = Su.AfterAtTerm(arr[i],"class"," "); 
				 
				class_name = class_name.trim().replace("{",""); 
				 
				class_name = class_name.trim().replace(";",""); 
				 
				if(class_name.trim().length() != gl.E_EMPTY) 
				{ 
					 
					clazz_count++; 
					 
				} 
				 
			}// clazz_name trim() <> 0 
			 
			 
				// ?????? ???????? ???????. 
			 
			    if(bl_reset_buffer==true) 
			    { 
			    	//????????? ?????????? ???????????? ?????? ? ?????? ??? ???????? ???????. 
			    	// ?????????? fileName. 
			    	 
			    	 
			    	// Clear of the buffer. 
			    	 
			    	// Reset flag. 
			    	bl_reset_buffer = false; 
			    	 
			    	file_name = real_dir+"\\"+class_name.concat(".java"); 
			    	 
			    	gl.smn("Target class name : " + file_name); 
			    				    	 
			    	dir = Fu.get_path(file_name); 
			    	 
			    	gl.smn("Create dir : " + dir); 
			    	 
			    	// ??????? ??????????. 
			    	 
			    	if(!Files.exists(Paths.get(dir))) 
					{ 
						try { 
							 
							Files.createDirectories(Paths.get(dir)); 
							 
						} catch (IOException e) { 

							gl.smn("Error while create dir : " + dir); 
							 
							return false; 
						} 
					} 
			    	 
			    	// ????? ? ????. 
					 
			    	final String fn = file_name; 
			    	 
			    	 list.forEach(obj->{appendToFile(fn,obj);}); 
			    	 
			    	if (!appendToFile(file_name,arr[i])) 
					{ 
						gl.smn("Error while write to file : " + fileName); 
					} 
					 
			    	 
			    	 
			    } // end decision maker. 
			 
			 
			    	arr_pkg[i] = dir; 
			 
			        String msg_t = String.format("%s %s %s %s",arr_pkg[i],"...",class_name , arr[i]); 
			 
			 
			        /* 
			        if (!appendToFile("a_marker.sql",msg_t)) 
					{ 
						gl.smn("Error while write to file : "); 
					} 
					*/ 
				 
			 
			    	// ??? ??????? ? ??????. 
				 
		    				    	 
					//sb.append(arr[i]); 
					 
					//sb.append(System.lineSeparator()); 
			 
			        list.add(arr[i]); 
			 
			        list.add(System.lineSeparator()); 
										 
				 
			 
			 
			/* 
			if(fileName.trim().length() != gl.E_EMPTY) 
			{ 
				 
				if (!appendToFile(fileName,arr[i])) 
				{ 
					gl.smn("Error while write to file : " + fileName); 
				} 
			} 
			*/ 
			 
			 
		} // end for. 
		 
				return true; 
		 
		 
		 
} 

	public static boolean doIt_(String path) 
	{ 

		String find_pkg   = "package"; 
		 
		String find_class = "public class"; 
		 
		String pkg_name = ""; 
		 
		String pkg_name_orig = ""; 
				 
		String fileName  = ""; 
		 
		String class_name = ""; 
		 
		boolean open_gate = false; 
		 
				 
		String[] arr = path.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int count = gl.E_EMPTY; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			// Create file name 
			 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
				 
				/* 
				pkg_name = StringUtil.AfterAt(arr[i],find); 
				 
				pkg_name = pkg_name.replace(".","_").replace(";"," "); 
				 
				*/ 
				 
				pkg_name_orig = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name_orig = pkg_name_orig.replace(";"," ").trim(); 
				 
				pkg_name = pkg_name_orig.replace(".","_").replace(";"," "); 
				 
				fileName = pkg_name; 
				 
				class_name = getNearClass(arr,i,find_class); 
				 
				msg = String.format("%s.%s  [%d.%s] ",pkg_name_orig,class_name,i,arr[i]); 
				 
				count++; 
				 
				if(find_class.trim().length() != gl.E_ERROR) 
				gl.smn(msg); 
				 
				//class_name =""; 
				 
			} // if header 
			 
			 
			// msg = String.format("%s %s",arr[i],fileName); 
			 
			 
			 
			if (!appendToFile(fileName,arr[i])) 
			{ 
				gl.smn("Error while write to file : " + fileName); 
			} 
			 
			 
		} // end for. 
		 
				return true; 
		 
		 
		 
} 

	public static boolean deleteDirs(String path,List<String> items) 
	{ 
	 
		/* 
		if (items.size()==gl.E_EMPTY) 
		{ 
			gl.smn("createDirs() No input items for processing."); 
			 
			return false; 
		} 
		*/ 
		 
		String root = System.getProperty("user.dir"); 
		 
		String full_path = ""; 
		 
		if(path.contains(":")) 
		{ 
		   full_path = Fu.get_path(path);	 
		} 
		else 
		{ 
			full_path = root.concat("\\").concat(path); 
		} 
		 
			gl.smn("(D) Work path : " + full_path); 
			 
			 
			if (Fu.deleteFiles(new File(full_path))) 
				gl.smn("deleteDirs() Successfull delete of : " + full_path); 
			else 
				gl.smn("deleteDirs() Error while delete of : " + full_path); 
			 
			/* 
		Path pth = Paths.get(full_path); 
		 
		if(!Files.exists(pth)) 
		{ 
			try { 
				 
				Files.createDirectories(pth); 
				 
			} catch (IOException e) { 

				gl.smn("Error while create dir : " + pth); 
				 
				return false; 
			} 
						 
			 
		} 
		*/ 
		return true; 
		 
	} 


	public static boolean createDirs(String path,List<String> items) 
	{ 
	 
		/* 
		if (items.size()==gl.E_EMPTY) 
		{ 
			gl.smn("createDirs() No input items for processing."); 
			 
			return false; 
		} 
		*/ 
		 
		String root = System.getProperty("user.dir"); 
		 
		String full_path = ""; 
		 
		if(path.contains(":")) 
		{ 
		   full_path = Fu.get_path(path);	 
		} 
		else 
		{ 
			full_path = root.concat("\\").concat(path); 
		} 
		 
			gl.smn("Work path : " + full_path); 
			 
		Path pth = Paths.get(full_path); 
		 
		if(!Files.exists(pth)) 
		{ 
			try { 
				 
				Files.createDirectories(pth); 
				 
			} catch (IOException e) { 

				gl.smn("Error while create dir : " + pth); 
				 
				return false; 
			} 
						 
			 
		} 
		 
		return true; 
		 
	} 
	 
	public static boolean extractDirs(String path) 
	{ 

		String find_pkg = "package"; 
		 
		String find_class = "public class"; 
		 
		String pkg_name = ""; 
		 
		String fileName  = ""; 
		 
		 
		dirs.clear(); 
		 
				 
		String[] arr = path.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int count = gl.E_EMPTY; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			// Create file name 
			 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
			 
				 
				 
				pkg_name = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name = pkg_name.replace(".","_").replace(";"," "); 
				 
				fileName = pkg_name; 
				 
				count++; 
				 
			} // if header 
			 
			 
			 msg = String.format("%s %s",arr[i],fileName); 
			 
			 if(!dirs.contains(fileName) && fileName.trim().length() != gl.E_EMPTY) 
				 dirs.add(fileName); 
			 
		} // end for. 
		 
				return true; 
		 
		 
} 
	 
	public static boolean extractDirsEx(String path) 
	{ 

		String find_pkg = "package"; 
		 
		String find_class = "public class"; 
		 
		String pkg_name = ""; 
		 
		String pkg_name_orig = ""; 
		 
		String class_name = ""; 
		 
		String fileName  = ""; 
		 
		 
		dirs.clear(); 
		 
				 
		String[] arr = path.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		int count_of_clazz = gl.E_EMPTY; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int count = gl.E_EMPTY; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		int row_num = gl.E_EMPTY; 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			// Lookup package name. 
			 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
				pkg_name_orig = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name_orig = pkg_name_orig.replace(";"," ").trim(); 
				 
				pkg_name = pkg_name_orig.replace(".","_").replace(";"," "); 
				 
				fileName = pkg_name; 
				 
				class_name = getNearClass(arr,i,find_class); 
			 
				msg = String.format("%s.%s ",pkg_name_orig,class_name); 
				 
				count++; 
				 
			} 
			 
			 if(!dirs.contains(msg) && msg.trim().length() != gl.E_EMPTY) 
				 //dirs.add(fileName+" " + class_name); 
				 dirs.add(msg); 
			 
			 row_num++; 
			 
		} // end for. 
		 
				return true; 
} 

	 
	 
	public static String getNearClass(String[] input,int start_index,String what) 
	{ 

		String return_value = ""; 
		 
		boolean flag = true; 
		 
		int j = start_index; 
			 
		int len = input.length; 
				 
		while(flag && j < len) 
		{ 
			if(input[j].contains(what)) 
			{ 
				return_value = input[j]; 
				 
				//gl.smn(input[j] + "===" + what); 
				 
				flag = false; 
			} 
			else 
			{ 
				//gl.smn(input[j] + "<>" + what); 
			} 
			 
			j++; 
		} 
		 
		return_value = Su.AfterAtTerm(return_value,"class"," "); 
		 
		return_value = return_value.trim().replace("{",""); 
		 
		 
		return return_value; 
	} 
	 
	public static boolean extractClasses(String path) 
	{ 

		String find_pkg = "package"; 
		 
		String find_class = "public class"; 
		 
		String pkg_name = ""; 
		 
		String fileName  = ""; 
		 
		 
		dirs.clear(); 
		 
				 
		String[] arr = path.split(System.lineSeparator()); 
		 
		int length = arr.length; 
		 
		 
		if(length==gl.E_EMPTY) 
		{ 
			gl.smn("Input source is empty."); 
			 
			return false; 
		} 
		 
		int count = gl.E_EMPTY; 
		 
				 
		StringBuilder sb = new StringBuilder(); 
		 
		String msg = ""; 
		 
		for(int i=0;i < length;i++) 
		{ 
			 
			// Create file name 
			 
			if (arr[i].startsWith(find_pkg)==true ) 
			{ 
				 
				pkg_name = Su.AfterAt(arr[i],find_pkg); 
				 
				pkg_name = pkg_name.replace(".","_").replace(";"," "); 
				 
				fileName = pkg_name; 
				 
				count++; 
				 
			} // if header 
			 
			 
			 msg = String.format("%s %s",arr[i],fileName); 
			 
			 if(!dirs.contains(fileName) && fileName.trim().length() != gl.E_EMPTY) 
				 dirs.add(fileName); 
			 
		} // end for. 
		 
				return true; 
		 
		 
} 
	 
	public static void main(String[] args) { 

			String template = "package ap.class ; " + System.lineSeparator() + 
							  "import  ap.class ; " + System.lineSeparator() + 
							  "package ap.class2 ;" +  System.lineSeparator() + 
							  "import  ap.class2 ;" ; 
			 
			 
			String inputFile = ""; 
			 
			try 
			{ 
			inputFile = args[0]; 
			} 
			catch(java.lang.ArrayIndexOutOfBoundsException e) 
			{ 
				gl.smn(" Usage  :  program inputParam[fileName for parsing.]"); 
				 
				return; 
			} 
			 
			 
			if(inputFile.trim().length() == gl.E_EMPTY) 
			{ 
				gl.smn(" Usage  :  program inputParam[fileName fro parsing.]"); 
				 
				return; 
			} 
			 
									 
			if (doIt(inputFile)) 
				gl.smn(String.format("Process %s done successfull.",inputFile)); 
			else 
				gl.smn(String.format("Process %s done with some errors.",inputFile)); 
				 
					 
			/* 
			if (extractDirsEx(source)) 
			{ 
				int i = gl.E_OK; 
				 
				for(String s : dirs) 
				{ 
					gl.smn(""+i+"."+s); 
					 
					i++; 
					 
				} 
			} 
				 
				createDirs("e:\\bin\\tmp1\\tmp2\\tmp3\\kkk.txt",dirs); 
				 
				createDirs("iin",dirs); 
				 
				deleteDirs("iin",dirs); 
				 
				deleteDirs("e:\\bin\\tmp1\\",dirs); 
				 
				*/ 
				 
							 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
