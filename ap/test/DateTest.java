 
 
 
 
 
 
 
 
 
 
 
 
package ap.test; 

import java.io.File; 
import java.io.IOException; 
import java.text.DecimalFormat; 
import java.util.Date; 
import java.util.List; 
import java.util.Locale; 

import ap.global.gl; 
import ap.global.gl.AL; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public class DateTest { 

	 
	public static boolean filesTranslate(String source_dir) 
	{ 
	 
		boolean[]  bl_result = {true}; 
		 
		int[] cnt = {0}; 
		 
		try { 
			 
			List<File> files = Fu.getFileListInDir(new File(source_dir),"txt"); 
			 
			files.forEach( 
					a-> 
					{ 
						 
						String file_source = a.getAbsolutePath(); 
								 
						bl_result[0] = fileTranslate(file_source); 
						 
						if(bl_result[0]) 
							cnt[0]++; 
						 
					} 
					); 
			 
		} catch (IOException e) { 
			 
			return false; 
			 
		} 
		 
			gl.tracex(new Object(){},String.format("Suite completed...%s...count of files ...%04d",bl_result[0],cnt[0])); 
		 
			return bl_result[0]; 
		 
	} 
	 
	public static boolean fileTranslate(String fileSource) 
	{ 
		 
		boolean[] bl_result = {true}; 
		 
		if(!Fu.isaFile(fileSource) || Fu.getFileSize(fileSource) == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("File is not exists or empty...%s",fileSource)); 
			 
			return false; 
		} 
		 
		if(!checkFormat(fileSource,6)) 
		{ 
			gl.tracex(new Object(){},String.format("Check format...%s...%s",fileSource,gl.S_ERROR)); 
			 
			if(Fu.get_pure_file_name(fileSource).equalsIgnoreCase("gold")) 
			{ 
				gl.tracex(new Object(){},String.format("Re-Check format...%s",fileSource)); 
				 
			 	 if(!checkFormat(fileSource,5)) 
			 	 { 
			 		 return false; 
			 	 } 
			} 
			else 
			{ 
					return false; 
			} 
		} 
		 
		List<String> list = Fu.getListFromFile(fileSource); 
		 
		if(list.size()==gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("No valid rows in file...%s",fileSource)); 
			 
			return false; 
		} 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(a->{ 
			 
			String target = rowTranslate(a); 
			 
			if (target != null) 
			{ 
				sb.append(target); 
			} 
			else 
			{ 
				bl_result[0] = false; 
				 
				 
			} 
			 
		}); 
		 
		 
		if(bl_result[0]== false) 
		{ 
			 
			gl.tracex(new Object(){},String.format("Bad row occurred...%s...file source...%s",gl.S_ERROR,fileSource)); 
			 
			return false; 
		} 
		 
		// Save rescue copy. 
		 
		String bak_file_name =  Fu.getBakName(fileSource,"bak"); 
		 
		if(!Fu.deleteFile(bak_file_name)) 
		{ 

			gl.tracex(new Object(){},String.format("Get error while delete bak file...%s",bak_file_name)); 
			 
			return false; 
		} 
		 
		if(!Fu.rename(fileSource,bak_file_name )) 
		{ 
			gl.tracex(new Object(){},String.format("Get error while rename file...%s...to...%s",fileSource,bak_file_name)); 
			 
			return false; 
		} 
		 
		if(!Fu.to_file(fileSource,sb.toString())) 
		{ 
			gl.tracex(new Object(){},String.format("Get error while write to file...%s",fileSource)); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},String.format("Successfull completed...%s",fileSource)); 
		 
			return true; 
		 
	} 
	 
	public static void Test_fileTranslate(String fileSource) 
	{ 
		if(!fileTranslate(fileSource)) 
		{ 
			gl.tracex(new Object(){},String.format("Get error while test...%s",fileSource)); 
		} 
		else 
			gl.tracex(new Object(){},String.format("Test completed successfull...%s...%s",fileSource,gl.S_OK)); 
			 
		 
	} 
	 
	public static boolean checkFormat(String source_file, int count) 
	{ 
		String firstRow = Fu.get_first_row(source_file); 
		 
		String[] arr = firstRow.split(","); 
		 
		return ((count+1)==arr.length); 
		 
	} 
	 
	public static String rowTranslate(String value) 
	{ 
		int DATE=0,TIME=1,O=2,H=3,L=4,C=5; 
		 
		String[] arr = value.split(","); 
		 
		final int WAIT_COUNT_OF_FIELDS = 7; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		for(int i = DATE;i<= C;i++) 
		{ 
			if( i != TIME) 
			{ 
				 
				if(i==DATE) 
				{ 
					Date date = DateUtil.to_date_from_fibo_string(arr[i]); 
					 
					sb.append(DateUtil.to_string(date)); 
				} 
				else 
				sb.append(arr[i]); 
				 
				if(i != C) 
				sb.append(","); 
			} 
		} 
		 
				sb.append(System.lineSeparator()); 
	 
				return sb.toString(); 
	} 

	 
	public static String rowTranslateGold(String value) 
	{ 
		int DATE=0,O=1,H=2,L=3,C=4; 
		 
		String[] arr = value.split(","); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		for(int i = DATE;i<= C;i++) 
		{ 
				if(i==DATE) 
				{ 
					Date date = DateUtil.to_date_from_fibo_string(arr[i]); 
					 
					sb.append(DateUtil.to_string(date)); 
				} 
				else 
				sb.append(arr[i]); 
				 
				if(i != C) 
				sb.append(","); 
		} 
		 
				sb.append(System.lineSeparator()); 
	 
				return sb.toString(); 
	} 

	 
	 
	 
	public DateTest() { 
		 
	} 

	public static void testSimple() 
	{ 
		String  fiboDate = "1999.12.01"; 
		 
		Date date = DateUtil.to_date_from_fibo_string(fiboDate); 
		 
		String msg = String.format("Input value : %s Output value : %s ", 
				fiboDate,DateUtil.to_string(date)); 
		 
		gl.smn(msg); 
	} 
	public static void testRow(String value) 
	{ 
		gl.smn(String.format("Output row value : %s",rowTranslate(value))); 
		 
	} 
	 
	public static void testFormat(double value) 
	{ 
		 
		Locale.setDefault(new Locale("en", "US")); 
		 
		DecimalFormat df = new DecimalFormat("#0.00"); 
		 
		gl.smn(gl.format(df.format(value),AL.RIGHT,10)); 
		 
	} 
	 
	public static void testFormatEx(double value) 
	{ 
		 
		gl.smn(gl.formatd(value,AL.RIGHT,10)); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		//testSimple(); 
		 
		String inputRow = "1997.12.12,00:00,89.74,89.75,89.76,89.77"; 
		 
		//testRow(inputRow); 
		 
		String input_file = "e:\\bin\\fibo\\test.txt"; 
		 
		//Test_fileTranslate(input_file); 
		 
		// filesTranslate("e:\\bin\\fibo\\"); 
		 
		//gl.smn(FileUtil.getFilesHeadersInDir("e:\\bin\\fibo\\",new String[]{"txt","bak"})); 
			 
		//FileUtil.getFilesHeadersInDirToList("e:\\bin\\fibo\\",new String[]{"txt","bak"}).forEach(a->{gl.smn(a);}); 
		 
		 
		 
		 
	} 

} 
// Revision : 10.09.2018 12:49:16 
