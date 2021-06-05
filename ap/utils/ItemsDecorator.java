 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
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
* File     name : ItemsDecorator.java 
* 
*/ 
public class ItemsDecorator { 

	/** 
	 * 
	 */ 
	 
	public static String leftPart 	= ""; 
	 
	public static String rightPart 	= ""; 
	 
	 

	public ItemsDecorator() { 
		 
	} 

	 
	 
	 
	 
	public static boolean doParse(String[] args) { 

		String inputFile 	= ""; 

		String strDelimeter = ""; 
		 
		CmdUtils cu = new CmdUtils(args); 

		int i_if = cu.findEx("-if"); 

		int i_dl = cu.findEx("-dl"); 
		 
		int i_lp = cu.findEx("-lp"); 
		 
		int i_rp = cu.findEx("-rp"); 
		 
		int i_verbose = cu.findEx("-verbose"); 
						 

		if 
		( 

			i_if != gl.E_ERROR && 
			 
			i_dl != gl.E_ERROR && 
			 
			i_lp != gl.E_ERROR && 
			 
			i_rp != gl.E_ERROR 
		 
		) { 

			inputFile = cu.getData()[i_if]; 

			strDelimeter = cu.getData()[i_dl]; 
			 
			leftPart 	= cu.getData()[i_lp]; 
			 
			rightPart 	= cu.getData()[i_rp]; 
	 
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
		 
		gl.smn("Reduce, items count : "  + arr.length); 
		 
		List<String> list = new ArrayList<String>(); 
		 
		list = Arrays.asList(arr); 
		 
		String outputFile = Fu.get_path(inputFile) + Fu.get_pure_file_name(inputFile).concat("$").concat(".").concat(Fu.get_file_extension(inputFile)); 
		 
		if (Fu.isaFile(outputFile)) 
			  if (!Fu.deleteFiles(new File(outputFile))) 
				  return false; 
		 
		 
		gl.smn("Reduce, items count  in list : "  + list.size()); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(obj-> 
		{ 
			 
				String resultValue = String.format("%s%s%s",leftPart,obj,rightPart); 
			 
				sb.append(resultValue); 
				 
				sb.append(System.lineSeparator()); 
				 
				if(i_verbose != gl.E_ERROR) 
				gl.smn(resultValue); 
				 
		} 
		); 
		 
		if (!Fu.saveStringToFileNL(outputFile,sb.toString(),true)) 
			gl.smn("Error while write to file: " + outputFile); 
	 
			return true; 

	} 
	 
	 
	public static boolean doReplace(String[] args) { 

		String inputFile 	= ""; 

		String strDelimeter = ""; 
		 
		CmdUtils cu = new CmdUtils(args); 

		int i_if = cu.findEx("-if"); 

		int i_dl = cu.findEx("-dl"); 
		 
		int i_lp = cu.findEx("-lp"); 
		 
		int i_rp = cu.findEx("-rp"); 
		 
		int i_verbose = cu.findEx("-verbose"); 
						 

		if 
		( 

			i_if != gl.E_ERROR && 
			 
			i_dl != gl.E_ERROR && 
			 
			i_lp != gl.E_ERROR && 
			 
			i_rp != gl.E_ERROR 
		 
		) { 

			inputFile = cu.getData()[i_if]; 

			strDelimeter = cu.getData()[i_dl]; 
			 
			leftPart 	= cu.getData()[i_lp]; 
			 
			rightPart 	= cu.getData()[i_rp]; 
	 
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
		 
		gl.smn("Reduce, items count : "  + arr.length); 
		 
		List<String> list = new ArrayList<String>(); 
		 
		list = Arrays.asList(arr); 
		 
		String outputFile = Fu.get_path(inputFile) + Fu.get_pure_file_name(inputFile).concat("$").concat(".").concat(Fu.get_file_extension(inputFile)); 
		 
		if (Fu.isaFile(outputFile)) 
			  if (!Fu.deleteFiles(new File(outputFile))) 
				  return false; 
		 
		 
		gl.smn("Reduce, items count  in list : "  + list.size()); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(obj-> 
		{ 
			 
				//String resultValue = String.format("%s%s%s",leftPart,obj,rightPart); 
			 
				String resultValue = obj.replace(leftPart,rightPart); 
								 
				sb.append(resultValue); 
				 
				sb.append(System.lineSeparator()); 
				 
				if(i_verbose != gl.E_ERROR) 
				gl.smn(resultValue); 
				 
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
				result = doParse(args); 
			else if(appType.equalsIgnoreCase("replace")) 
				result = doReplace(args); 
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
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
