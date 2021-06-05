 
/** 
* 
*/ 
package ap.utils; 

import java.io.File; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 15 ???? 2016 ?. 14:20:55 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : DdlUtil.java 
* 
*/ 
public class DdlUtil { 

	/** 
	 * 
	 */ 
	public DdlUtil() { 
		 
	} 

	 
	public static boolean doTestFiles(String inputFile,String targetRootDir,String strDelimiter) 
	{ 
		String source = null; 
		 
		try { 
			source = Fu.get_str_from_file(inputFile); 
		} catch (IOException e) { 
			 
			gl.smn("ReduceFiles exception : " +  e.getMessage()); 
			 
			return false; 
		} 
		 
		String[] arr  = source.split(strDelimiter); 
		 
		gl.smn("doTestFiles, files count : "  + arr.length); 
		 
		 
		// Clear of target Dir		 
		 
		if (Fu.deleteFiles(new File(targetRootDir))) 
			gl.smn("deleteDirs() Successfull delete of : " + targetRootDir); 
		else 
		{ 
			gl.smn("deleteDirs() Error while delete of : " + targetRootDir); 
			 
			return false; 
		} 
	 
		String className = ""; 
		 
		String packageName  = ""; 
		 
		String canonicalDir = ""; 
		 
		String targetDir = ""; 
		 
		String targetFile = "" ; 
		 
		 
		 
		 
		for(int i=0;i<arr.length;i++) 
		{ 
			 
			className = Su.ExtractClassNameEx(arr[i]); 
			 
			packageName  = Su.ExtractPackageName(arr[i]); 
			 
			canonicalDir =  Fu.getCanonicalDir(packageName); 
			 
			targetDir = targetRootDir.concat(canonicalDir); 
			 
					 
			 
			if(className.equalsIgnoreCase("__none__")) 
				className = "package-info"; 
			 
			String fullclassFilename = className.concat(".java"); 
			 
			targetFile = targetDir.concat("\\").concat(fullclassFilename); 
			 
						 
			if(packageName.trim().length() != gl.E_EMPTY) 
			gl.smn(packageName+ " " + className + " " + targetDir + " " + targetFile); 
			 
			 
			Path pth = Paths.get(targetDir); 
			 
			if(!Files.exists(pth)) 
			{ 
				try { 
					 
					Files.createDirectories(pth); 
					 
				} catch (IOException e) { 

					gl.smn("Error while create dir : " + pth); 
					 
					return false; 
				} 
			} 
			 
						 
				if(packageName.trim().length() != gl.E_EMPTY) 
				if (!Fu.to_file(targetFile,arr[i])) 
				{ 
					gl.smn("Error while write to file : " + targetFile); 
					 
					return false; 
				} 
		 
			 
				className = ""; 
				 
				packageName  = ""; 
				 
				canonicalDir = ""; 
				 
				targetDir = ""; 
				 
				targetFile = "" ; 
							 
			 
		} // end for. 
		 
		 
				return true; 
		 
		 
	} 
	 
	 
	public static boolean doTest(String[] args) { 

		String inputFile = ""; 

		String strDelimeter = ""; 

		CmdUtils cu = new CmdUtils(args); 

		int i_if = cu.findEx("-if"); 

		int i_dl = cu.findEx("-dl"); 

		if ( 

		i_if != gl.E_ERROR && i_dl != gl.E_ERROR) { 

			inputFile = cu.getData()[i_if]; 

			strDelimeter = cu.getData()[i_dl]; 

			gl.smn("(doTest()) Ok , while accepted of input params."); 

		} else { 
			gl.smn("(doTest()) Error, while accepted of input params."); 

			cu.show(); 

			return false; 
		} 

		cu.show(); 
		 
		gl.smn("Additional check of file ..."); 
		 
		File file = new File(inputFile); 
		 
		if(!file.isFile()) 
		{ 
			gl.smn(String.format("Input file [%s] is not found. ",inputFile)); 
			 
			return false; 
		} 
		 
		gl.smn(String.format("Input file [%s] is valid. ",inputFile)); 
		 
		gl.smn("Start application ... "); 
		 
		 
		String source = null; 
		 
		try { 
			 
			source = Fu.get_str_from_file(inputFile); 
			 
		} catch (IOException e) { 
			 
			gl.smn("Reduce exception : " +  e.getMessage()); 
			 
			return false; 
		} 
		 
		 
		String[] arr  = source.split(System.lineSeparator()); 
		 
		//String[] arr  = source.split(" "); 
		 
		gl.smn("Reduce, items count : "  + arr.length); 
		 
		List<String> list = new ArrayList<String>(); 
		 
		list = Arrays.asList(arr); 
		 
		String outputFile = Fu.get_path(inputFile) + Fu.get_pure_file_name(inputFile).concat("$").concat(".").concat(Fu.get_file_extension(inputFile)); 
		 
		gl.smn("Target file : " + outputFile); 
		 
		 
		 
		if (Fu.isaFile(outputFile)) 
			  if (!Fu.deleteFiles(new File(outputFile))) 
				  return false; 
		 
		 
		gl.smn("Reduce, items count  in list : "  + list.size()); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		 
		list.forEach(obj-> 
		{ 
				sb.append(obj); 
				 
				sb.append(System.lineSeparator()); 
				 
		} 
		); 
		 
		if (!Fu.saveStringToFileNL(outputFile,sb.toString(),true)) 
			gl.smn("Error while write to file: " + outputFile); 
	 
		 

		return true; 

	} 
	 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
	 
			 
			String appType = ""; 
			 
			CmdUtils cu = new CmdUtils(args); 
			 
			int i_app = cu.findEx("-app"); 
			 
			if(i_app != gl.E_ERROR) 
				appType = cu.getData()[i_app]; 
			else 
			{ 
				gl.smn("App type param is not presents."); 
				 
				return; 
			} 
					 
					 
			boolean result = false; 
			 
			 
			if(appType.equalsIgnoreCase("parse")) 
				result = doTest(args); 
			else 
			{ 
				gl.smn(String.format("App type param [%s] is not valid.",appType)); 
				 
				return; 
			} 
				 
			if (result) 
			gl.smn("\nProccessing Ok."); 
			else 
			gl.smn("\nProccessing Error."); 
			 
		} 
		 
	} 

// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
