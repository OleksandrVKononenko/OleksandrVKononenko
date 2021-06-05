 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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
* 24 ???? 2016 ?. 12:49:10 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : TestReduce.java 
* 
*/ 
public class TestReduce { 

	/** 
	 * 
	 */ 
	 
	public static int files_count = gl.E_EMPTY; 
	 
	public TestReduce() { 

	} 

	public static void TestSuite_Reducefiles(String inputFile,String targetRootDir,String strDelimiter) 
	{ 
		doReduceFiles(inputFile,targetRootDir,strDelimiter); 
	} 
	public static boolean doReduceFiles(String inputFile,String targetRootDir,String strDelimiter) 
	{ 
		String source = null; 
		 
		try { 
			source = Fu.get_str_from_file(inputFile); 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"ReduceFiles exception : " +  e.getMessage()); 
			 
			return false; 
		} 
		 
		String[] arr  = source.split(strDelimiter); 
		 
		gl.tracex(new Object(){},"Reduce, files count : "  + arr.length); 
		 
		 
		// Clear of target Dir		 
		 
		if (Fu.deleteFiles(new File(targetRootDir))) 
			gl.tracex(new Object(){},"deleteDirs() Successfull delete of : " + targetRootDir); 
		else 
		{ 
			gl.tracex(new Object(){},"deleteDirs() Error while delete of : " + targetRootDir); 
			 
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
			gl.tracex(new Object(){},packageName+ " " + className + " " + targetDir + " " + targetFile); 
			 
			 
			Path pth = Paths.get(targetDir); 
			 
			if(!Files.exists(pth)) 
			{ 
				try { 
					 
					Files.createDirectories(pth); 
					 
				} catch (IOException e) { 

					gl.tracex(new Object(){},"Error while create dir : " + pth); 
					 
					return false; 
				} 
			} 
			 
						 
				if(packageName.trim().length() != gl.E_EMPTY) 
				if (!Fu.to_file(targetFile,arr[i])) 
				{ 
					gl.tracex(new Object(){},"Error while write to file : " + targetFile); 
					 
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
	public static boolean TestSuite_Mergefiles(String inputDir,String[]  mask,String targetFile,String strDelimeter) 
	{ 
		return doMergeFiles(inputDir,mask,targetFile,strDelimeter); 
		 
	} 
	 
	 
	public static boolean doMergeFiles(String inputDir,String[] mask,String targetFile,String delimeter) 
	{ 
		 
		return Fu.mergeFiles(inputDir,mask,targetFile,delimeter); 
	 
	} 
	 
	 
	 
	public static boolean doTestDir() 
	{ 
		List<File> list = null; 
		 
		files_count = gl.E_EMPTY; 
		 
		try { 

			// "e:\\bin\\eclipse\\wsp\\helper\\" 
			 
			 
			list = Fu.getFilesInDirRecursively(new File("e:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\"),new String[]{"java"}); 
			 
			list.forEach(obj-> 
			{ 
				try { 
					String source = 
							Fu.get_str_from_file( 
							obj.getAbsolutePath() 
							); 
					 
					String className = Su.ExtractClassNameEx(source); 
					 
					String packageName  = Su.ExtractPackageName(source); 
					 
					if(className.equalsIgnoreCase("__none__")) 
						className = "package-info"; 
					 
				 
					gl.tracex(new Object(){},Fu.get_path(obj.getAbsolutePath()) + " " + obj.getAbsolutePath() + " " + className + " " + packageName); 
					 
					files_count++; 
					 
				} catch (Exception e) { 
					 
					return ; 
				} 
			}); 
				 
			gl.tracex(new Object(){},"doTestDir() Files proceed : " + files_count); 
			 
			return true; 
			 
		} catch (IOException e) { 
			 
			return false; 
		} 
	 
	} 
	 
	 
	 
	public static void doTest() 
	{ 
		String template = "#@#fn:file1.txt package ap.class ; f1;f1;" + System.lineSeparator() + 
				  "#@#fn:file1.txt import  ap.class ; f2;f2;" + System.lineSeparator() + 
				  "#@#fn:file1.txt package ap.class2 f3;f3;" +  System.lineSeparator() + 
				  "#@#fn:file1.txt import  ap.class2 ;f4;f4;" ; 

		List<String> list =  Arrays.asList(template.split("#@#fn")); 
		 
		list.forEach(obj->{gl.tracex(new Object(){},obj);}); 
		 
	} 
	 
	public static boolean doReduce(String[] args) { 
		 
		 
		String inputFile = ""; 
		 
		String targetRootDir = ""; 
		 
		String strDelimeter = ""; 
		 
		CmdUtils cu = new CmdUtils(args); 
		 
		 
		int i_if = cu.findEx("-if"); 
		 
		int i_tr = cu.findEx("-tr"); 
						 
		int i_dl = cu.findEx("-dl"); 
		 
				 
		if(i_if != gl.E_ERROR && 
				i_tr != gl.E_ERROR && 
					i_dl != gl.E_ERROR 
		  ) 
		{ 
			 
			inputFile =  cu.getData()[i_if]; 
			 
			targetRootDir = cu.getData()[i_tr]; 
			 
			strDelimeter = cu.getData()[i_dl]; 
			 
			 
			gl.tracex(new Object(){},"(doReduce()) Ok , while accepted of input params."); 
			 
		} 
		else 
		{ 
			gl.tracex(new Object(){},"(doReduce()) Error, while accepted of input params."); 
			 
			cu.show(); 
			 
			return false; 
		} 
		 
			cu.show(); 
			 
			gl.tracex(new Object(){},"Start application ... "); 
			 
			return doReduceFiles(inputFile,targetRootDir,strDelimeter); 
		 
	} 
	 
	 
	public static boolean doMerge(String[] args) { 
		 
		String inputDir = ""; 
		 
		String mask[]  = new String[]{"temp"}; 
		 
		String targetFile = ""; 
		 
		String strDelimeter = ""; 
		 
		 
		 
		CmdUtils cu = new CmdUtils(args); 
		 
		 
		int i_id = cu.findEx("-id"); 
		 
		int i_msk = cu.findEx("-msk"); 
				 
		int i_tf    = cu.findEx("-tf"); 
		 
		int i_dl = cu.findEx("-dl"); 
		 
				 
		if(i_id != gl.E_ERROR && 
				i_msk != gl.E_ERROR && 
						i_tf != gl.E_ERROR && 
							i_dl != gl.E_ERROR 
		  ) 
		{ 
			 
			inputDir =  cu.getData()[i_id]; 
			 
			mask[0] = cu.getData()[i_msk]; 
			 
			targetFile = cu.getData()[i_tf]; 
			 
			strDelimeter = cu.getData()[i_dl]; 
			 
			 
			gl.tracex(new Object(){},"(doMerge()) Ok , while accepted of input params."); 
			 
		} 
		else 
		{ 
			gl.tracex(new Object(){},"(doMerge()) Error, while accepted of input params."); 
			 
			cu.show(); 
			 
			return false; 
		} 
		 
			cu.show(); 
			 
			gl.tracex(new Object(){},"Start application ... "); 
			 
			return doMergeFiles(inputDir,mask,targetFile,strDelimeter); 
		 
		 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		String appType = ""; 
		 
		CmdUtils cu = new CmdUtils(args); 
		 
		int i_app = cu.findEx("-app"); 
		 
		if(i_app != gl.E_ERROR) 
			appType = cu.getData()[i_app]; 
		else 
		{ 
			gl.tracex(new Object(){},"App type param is not presents."); 
			 
			return; 
		} 
				 
				 
		boolean result = false; 
		 
		 
		if(appType.equalsIgnoreCase("merge")) 
			result = doMerge(args); 
		else if(appType.equalsIgnoreCase("reduce")) 
			result = doReduce(args); 
		else 
		{ 
			gl.tracex(new Object(){},String.format("App type param [%s] is not valid.",appType)); 
			 
			return; 
		} 
			 
		if (result) 
		gl.tracex(new Object(){},"\nProccessing Ok."); 
		else 
		gl.tracex(new Object(){},"\nProccessing Error."); 
		 
	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
