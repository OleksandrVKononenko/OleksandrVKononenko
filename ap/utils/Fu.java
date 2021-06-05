 
package ap.utils; 

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile; 
import java.io.UnsupportedEncodingException; 
import java.nio.ByteOrder; 
import java.nio.MappedByteBuffer; 
import java.nio.channels.FileChannel; 
import java.nio.channels.FileChannel.MapMode; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.DirectoryStream; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Calendar; 
import java.util.Comparator; 
import java.util.Date; 
import java.util.GregorianCalendar; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.LinkedHashMap; 
import java.util.LinkedHashSet; 
import java.util.List; 
import java.util.Map; 
import java.util.Scanner; 
import java.util.Vector; 
import java.util.concurrent.atomic.AtomicInteger; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.util.stream.Collector; 
import java.util.stream.Collectors; 
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter; 

import ap.btn.TConfiguration; 
import ap.btn.TDateRange; 
import ap.btn.TPanel;
import ap.explorer.Range;
import ap.global.gl; 
import ap.log.Logger; 
import ap.mng.SPanelManager;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.components.FileChooser;
import ap.swing.PanelXml; 
import ap.uat.AtOm; 



public class Fu { 

	
	public File[] get_files_dlg(String defaultDir,FileNameExtensionFilter[] filters) { 

		Object 			v = new Object() { }; 

		String 			msg = "Open file(s) dialog"; 

		File[] 			files = new File[] {}; 

		JFileChooser 	fo = new JFileChooser(); 

						fo.setCurrentDirectory(new File(defaultDir)); 

						fo.setAcceptAllFileFilterUsed(true);
						
						fo.setMultiSelectionEnabled(true); 


		List<FileNameExtensionFilter> list = Arrays.asList(filters); 

		list.forEach(a -> 

		{ 
						fo.addChoosableFileFilter(a); 
		}); 

		int 			i_result = fo.showOpenDialog(null); 

		if (i_result != JFileChooser.APPROVE_OPTION) { 
			 
						gl.tx_k(v, gl.sf("%s...%s",msg,"reject dialog")); 

						return null; 
		} 

						files = fo.getSelectedFiles(); 

						gl.tx_k(v, gl.sf("%s...files count...%d",msg,files.length)); 

						return files; 

	}// fileOpenDlgMulti 

	
	

	public static List<File> tmpList = new ArrayList<File>(); 
	 
	public static String isaGetExtension(String file_name,String def_ext) 
	{ 
		if(!isaExtension(file_name)) 
		{ 
			return gl.sf("%s.%s",file_name,def_ext); 
		} 
			return file_name; 
			 
	} 
	 
	public static String get_file_extension(String filename) 
	{ 
		 
		String ext = Su.AfterAtPeriod(filename); 
		 
		String default_result = "txt"; 
		 
		if(ext.trim().length() == gl.E_EMPTY) 
			return default_result; 
		 
			return ext; 
			 
	} 
	 
	public static String inject_time_stamp(String target)
	{
		String m_pure 		= Fu.get_pure_file_name(target);
    	
    	String m_path 		= Fu.get_path(target);
    	
    	String m_ext 		= Fu.get_file_extension(target);
    	
    	String m_stamp		= DateUtil.get_stamp_for_zip();
    			
    	String m_target 	= gl.sf("%s%s_%s.%s",m_path,m_pure,m_stamp,m_ext);
    	
    	return m_target;
   
	}
	
	
	public static boolean isaExtension(String filename) 
	{ 
		 
		String ext = Su.AfterAtPeriod(filename); 
		 
		if(ext.trim().length() == gl.E_EMPTY) 
			return false; 
		 
			return (filename.indexOf(".") != gl.E_ERROR); 
			 
	} 
	 
	public static long getFileSize(String path) 
	{ 
		File file = new File(path); 
		 
		return file.length(); 
	} 
	 
	public static boolean isaFile(String path) 
	{ 
		File file = new File(path); 
		 
		return file.exists(); 
	} 
	 
	public static boolean deleteFile(String path) 
	{ 
		File file = new File(path); 
		 
		if (!file.exists()) 
			return true; 
		 
			return file.delete(); 
	} 
	 
	public static boolean delete_suite(String path) 
	{ 
		boolean bl_result = Fu.deleteFile(path); 
		 
		if(!bl_result) 
			gl.tracex(new Object(){},String.format("Error while delete file...%s...%s",path,gl.S_ERROR)); 
		 
		return bl_result; 
	} 
	 
	 
	public static boolean deleteFiles(File fin) 
	{ 

		if(fin.isDirectory()) 
		{ 
			for(File c : fin.listFiles()) 
			{ 
				deleteFiles(c); 
			} 
		} 
		 
		if(!fin.delete()) 
		{ 
			gl.tracex(new Object(){},"deleteFiles() Error while delete file : " + fin.getName()); 
			 
			return false; 
		} 
		 
			return true; 
	 
	} 
	 
	 
	 
	public static byte[] get_ba_from_file(String fileName) { 

		File file = new File(fileName); 

		if (!Files.isReadable(file.toPath())) { 
			gl.tracex(new Object(){},String 
					.format("getFileAsByteArrayBF File %s is not readable or not is exists.", 
							fileName)); 

			return null; 
		} 

		long start = 0L, end = 0L; 

		ByteArrayOutputStream ous = null; 

		InputStream ios = null; 

		try { 
			byte[] buffer = new byte[4096]; 

			ous = new ByteArrayOutputStream(); 

			try { 
				ios = new FileInputStream(file); 

			} catch (FileNotFoundException e) { 

				return null; 
			} 

			int read = 0; 

			try { 

				start = System.nanoTime(); 

				while ((read = ios.read(buffer)) != -1) { 
					ous.write(buffer, 0, read); 

				} 

				end = System.nanoTime(); 

				return ous.toByteArray(); 

			} catch (IOException e) { 

				return null; 

			} 
		} finally { 

			gl.tracex(new Object(){},String.format("getFileAsByteArrayBF() read %s in  %d ms. ", 
					fileName, (end - start) / 1000000)); 

			try { 
				if (ous != null) 
					ous.close(); 
			} catch (IOException e) { 
				return null; 
			} 

			try { 
				if (ios != null) 
					ios.close(); 
			} catch (IOException e) { 
				return null; 
			} 
		} 

	} 

	public static byte[] getFileAsByteArrayRA(String fileName) { 

		File file_check = new File(fileName); 

		if (!Files.isReadable(file_check.toPath())) { 
			gl.tracex(new Object(){},String 
					.format("getFileAsByteArrayRA File %s is not readable or not is exists.", 
							fileName)); 

			return null; 
		} 

		byte[] byte_array = null; 

		RandomAccessFile file = null; 

		try { 

			long start = System.nanoTime(); 

			file = new RandomAccessFile(fileName, "r"); 

			byte_array = new byte[(int) file.length()]; 
			 
			file.read(byte_array); 
			 
			long end = System.nanoTime(); 

			gl.tracex(new Object(){},String.format( 
					"getFileAsByteArrayRA() read %s %d bytes  in  %d ms. ", 
					fileName, byte_array.length, (end - start) / 1000000)); 

		} catch (IOException e) { 

			//  Auto-generated catch block 
			gl.tracex(new Object(){},"getFileAsByteArrayRA(Exception) : " + e.toString()); 

			return null; 

		} 

		try { 
			file.close(); 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"IOException while close file  : " + e.toString()); 
			 
			return null; 
		} 

		return byte_array; 

	} 

	public static byte[] getFileAsByteArrayJDK7(String fileName) { 
		if (!Files.isReadable(new File(fileName).toPath())) { 
			gl.tracex(new Object(){},String 
					.format("getFileAsByteArrayJDK7() File %s is not readable or not is exists.", 
							fileName)); 

			return null; 
		} 

		byte[] byte_array = null; 

		long start = System.nanoTime(); 

		try { 

			byte_array = Files.readAllBytes(new File(fileName).toPath()); 

			long end = System.nanoTime(); 

			gl.tracex(new Object(){},String.format( 
					"getFileAsByteArrayJDK7() read %s %d bytes  in  %d ms. ", 
					fileName, byte_array.length, (end - start) / 1000000)); 

		} catch (IOException e) { 
			//  Auto-generated catch block 
			gl.tracex(new Object(){},"getFileAsByteArrayJDK7(Exception) : " + e.toString()); 

			return null; 

		} 
		return byte_array; 
	} 

	public static boolean mergeOneFile(String source,String target,String strDelimeter)	{ 

		File file = new File(source); 
		 
		FileWriter fw = null; 
		 
		try { 
			 
			fw = new FileWriter(target,true); 
			 
			if(strDelimeter.trim().length()!= gl.E_EMPTY) 
			{ 
				fw.write(strDelimeter); 
			} 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"mergeOneFile() Error while create file for target : " + target); 
			 
			return false; 
		} 

		Scanner scanner = null ; 

		try { 
				try { 
					 
					scanner = new Scanner(file); 
					 
				} catch (FileNotFoundException e) { 
					 
					gl.tracex(new Object(){},"mergeOneFile() Error while create scanner for  : " + source + " " +  e.getMessage()); 
					 
					return false; 
					 
				} 
			 
			while (scanner.hasNextLine()) { 
				 
				String s = scanner.nextLine(); 
				 
				try { 
					 
					fw.write(s); 
					 
					fw.write(System.lineSeparator()); 
					 
				} catch (IOException e) { 

					gl.tracex(new Object(){},"mergeOneFile() Error while write to target  : " + target + " " +  e.getMessage()); 
					 
					return false; 
				} 
				 
			} 
			 
		} finally { 
			scanner.close(); 
			 
			try { 
				 
				fw.flush(); 
				 
				fw.close(); 
				 
			} catch (IOException e) { 
				 
				gl.tracex(new Object(){},"mergeOneFile() Error while run finalization : " + e.getMessage()); 
				 
				return false; 
			} 
		} 
				return true; 
	} 
	 
	public static boolean mergeOneFile(String source,String target)	{ 

		File file = new File(source); 
		 
		FileWriter fw = null; 
		 
		try { 
			 
			fw = new FileWriter(target,true); 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"mergeOneFile() Error while create file for target : " + target); 
			 
			return false; 
		} 

		Scanner scanner = null ; 

		try { 
				try { 
					 
					scanner = new Scanner(file); 
					 
				} catch (FileNotFoundException e) { 
					 
					gl.tracex(new Object(){},"mergeOneFile() Error while create scanner for  : " + source + " " +  e.getMessage()); 
					 
					return false; 
					 
				} 
			 
			while (scanner.hasNextLine()) { 
				 
				String s = scanner.nextLine(); 
				 
				try { 
					 
					fw.write(s); 
					 
					fw.write(System.lineSeparator()); 
					 
				} catch (IOException e) { 

					gl.tracex(new Object(){},"mergeOneFile() Error while write to target  : " + target + " " +  e.getMessage()); 
					 
					return false; 
				} 
				 
			} 
			 
		} finally { 
			scanner.close(); 
			 
			try { 
				 
				fw.flush(); 
				 
				fw.close(); 
				 
			} catch (IOException e) { 
				 
				gl.tracex(new Object(){},"mergeOneFile() Error while run finalization : " + e.getMessage()); 
				 
				return false; 
			} 
		} 
				return true; 
	} 
	 
	public static String get_str_from_file(String pathname) 
			throws IOException { 

		
		File file = new File(pathname); 

		StringBuilder fileContents = new StringBuilder((int) file.length()); 

		Scanner scanner = new Scanner(file); 

		String lineSeparator = System.lineSeparator(); 
				 
		try { 
			while (scanner.hasNextLine()) { 
				fileContents.append(scanner.nextLine()); 
				 
				fileContents.append(lineSeparator); 
			} 
			return fileContents.toString(); 
		} finally { 
			scanner.close(); 
		} 
	} 
	 
	
	 
	public static Map<Integer,Map<String,String>> getMapFromFileAsStringScannerSkipComments(String pathname) 
			throws IOException { 

		File file = new File(pathname); 

		Map<String,String> items_value = new HashMap<String,String>(); 
		 
		Map<Integer,Map<String,String>>  items_key = new HashMap<Integer,Map<String,String>>(); 
		 
		Scanner scanner = new Scanner(file); 
				 
		int index = gl.E_OK; 
		 
		try { 
			while (scanner.hasNextLine()) { 

				String s = scanner.nextLine(); 
				 
				if (!s.startsWith(":") && 
					!s.startsWith("#") && 
					!s.startsWith("-") && 
					!s.startsWith("/") && 
					!s.startsWith("*") && 
					!s.startsWith("@") && s.trim().length() != gl.E_EMPTY 
					) 
					 
				{ 
					// Create child map. 
					String attr[] = s.split(";"); 
				 
				    List<String> attr_list = Arrays.asList(attr); 
				 
				    attr_list.forEach(a-> 
				    { 
				    	String key = Su.BeforeAtFirst(a,"="); 
				    	 
				    	String value = Su.AfterAt(a,"="); 
				    	 
				    	items_value.put(key,value); 
				    } 
				    ); 
				 
				    	items_key.put(index,items_value); 
				 
				  } // if 
				 
				index++; 
			} 
			 
			return items_key; 
			 
		} finally { 
			scanner.close(); 
		} 
	} 

	 
	public static String getFileAsStringScannerExtractComments(String pathname) 
			throws IOException { 

		File file = new File(pathname); 

		StringBuilder fileContents = new StringBuilder((int) file.length()); 

		Scanner scanner = new Scanner(file); 

		String lineSeparator = System.lineSeparator(); 
				 
		try { 
			while (scanner.hasNextLine()) { 

				String s = scanner.nextLine(); 
				 
				if ( s.startsWith(":") || 
					 s.startsWith("#") || 
					 s.startsWith("-") || 
					 s.startsWith("/") || 
					 s.startsWith("*") || 
					 s.startsWith("@") 
					 
					) 
				fileContents.append(s); 
				 
				fileContents.append(lineSeparator); 
			} 
			return fileContents.toString(); 
		} finally { 
			scanner.close(); 
		} 
	} 
	 
	public static String getFileAsOneRow(String pathname) 
			throws IOException { 

		File file = new File(pathname); 

		StringBuilder fileContents = new StringBuilder((int) file.length()); 

		Scanner scanner = new Scanner(file); 
				 
		try { 
			while (scanner.hasNextLine()) { 
				fileContents.append(scanner.nextLine()); 
			} 
			return fileContents.toString(); 
		} finally { 
			scanner.close(); 
		} 
	} 
	 
	public static String  dlgGetFileSource() 
	{ 
		Object 			v = new Object(){}; 
		 
		String 			fabula = "Open file(s) suite"; 
		 
		String			file_source = null; 
		 
		String 			defDir = Fu.get_path(gl.getHome()); 
				 
		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[]{ 
					 
					new FileNameExtensionFilter("Text files (*.txt)","txt"), 
					new FileNameExtensionFilter("Solutione files (*.sln)","sln") 
					 
					}; 
		 
		 
		File[]			files = new File[]{}; 
		 
		JFileChooser 	fo = new JFileChooser(); 
		 
						fo.setCurrentDirectory(new File(defDir)); 
						 
						fo.setAcceptAllFileFilterUsed(true); 
						 
						List<FileNameExtensionFilter> list = Arrays.asList(filters); 
						 
						list.forEach(a-> 
						 
						{ 
							fo.addChoosableFileFilter(a); 
						}		 
						); 
		 
		 
						fo.setMultiSelectionEnabled(false); 
		 
						int i_result = fo.showOpenDialog(null); 
		 
						if(i_result != JFileChooser.APPROVE_OPTION) 
						{ 
							gl.tracex(v,String.format("%s...%s...%s",fabula,"select files","cancel dialog")); 
						 
							return null; 
						} 
						 
						 
						String file_name = fo.getSelectedFile().getAbsolutePath(); 
			 
						gl.tracex(v,String.format("%s...%s...%s",fabula,"selected files :",file_name)); 
						 
						try { 
							file_source = Fu.get_str_from_file(file_name); 
							 
						} catch (IOException e) { 

							gl.tracex(v,String.format("%s...%s","Read from ","Error")); 
							 
							return null; 
						} 
						 
						gl.tracex(v,String.format("%s...%s...%d bytes.","Read from ",file_name,file_source.length())); 
						 
						return  file_source; 
	} 
	 
	public static String  dlgGetFileName() 
	{ 
		Object 			v = new Object(){}; 
		 
		String 			fabula = "Open for one file "; 
		 
		String			file_source = null; 
		 
		String 			defDir = Fu.get_path(gl.getHome()); 
				 
		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[]{ 
					 
					new FileNameExtensionFilter("Text files (*.txt)","txt"), 
					new FileNameExtensionFilter("Solutione files (*.sln)","sln") 
					 
					}; 
		 
		 
		File[]			files = new File[]{}; 
		 
		JFileChooser 	fo = new JFileChooser(); 
		 
						fo.setCurrentDirectory(new File(defDir)); 
						 
						fo.setAcceptAllFileFilterUsed(true); 
						 
						List<FileNameExtensionFilter> list = Arrays.asList(filters); 
						 
						list.forEach(a-> 
						 
						{ 
							fo.addChoosableFileFilter(a); 
						}		 
						); 
		 
						fo.setMultiSelectionEnabled(false); 
		 
						int i_result = fo.showOpenDialog(null); 
		 
						if(i_result != JFileChooser.APPROVE_OPTION) 
						{ 
							gl.tracex(v,String.format("%s...%s...%s",fabula,"select files","cancel dialog")); 
						 
							return null; 
						} 
						 
						 
						String file_name = fo.getSelectedFile().getAbsolutePath(); 
			 
						return  file_name; 
		 
	} 

	 
	 
	public static boolean to_file_dlg_save_as(SPanelManager owner) { 

		try
		{
		
			JFileChooser saveFile = new JFileChooser(); 

			String defaultDir = Fu.get_path(new File(owner.getHome()).getAbsolutePath()); 

			saveFile.setCurrentDirectory(new File(defaultDir)); 

			gl.tx_k(new Object(){},gl.sf("Попытка сохранить файл как...[%s]",defaultDir)); 

			if (saveFile.showSaveDialog(owner.getFrame()) == JFileChooser.APPROVE_OPTION) 
			{ 
			 
				File file = saveFile.getSelectedFile(); 
	
				String target_file = file.getAbsolutePath(); 
	
				owner.getCollector().setHome(target_file); 
	
	
				return (gl.tx(new Object(){},owner.write(),
					gl.sf("Запись файла...[%s]",target_file)));
			}
				return false;
		}
		catch(Exception e)
		{
				e.printStackTrace();
			
				return false;
		}
			
		
		
	} 

	public static String getFileAsStringScanner(String pathname,int rownum) 
			throws IOException { 

		File file = new File(pathname); 

		StringBuilder fileContents = new StringBuilder((int) file.length()); 

		Scanner scanner = new Scanner(file); 

		String lineSeparator = System.lineSeparator(); 
		 
		int counter = 0; 
		 
		try { 
			while (scanner.hasNextLine() && counter < rownum) { 
				 
				fileContents.append(scanner.nextLine()); 
				 
				fileContents.append(lineSeparator); 
				 
				counter++; 
			} 
			return fileContents.toString(); 
		} finally { 
			scanner.close(); 
		} 
	} 


	public static List<String> get_list_from_file(String path) 
	{ 
		return Arrays.asList(get_str_array_from_file(path)); 
	} 
	 
	public static List<String> getFileAsStringListSkipComments(String path) 
	{ 
		return Arrays.asList(getFileAsStringArraySkipComments(path)); 
	} 
	 
	public static String getFileAsStringScannerSkipComments(String pathname) 
			throws IOException { 

		File file = new File(pathname); 
		
		gl.tx_k(new Object() {},gl.sf("File...%s..Length...%d",pathname,file.length()));

		StringBuilder fileContents = new StringBuilder();//new StringBuilder((int) file.length()); 

		Scanner scanner = new Scanner(file); 
		
		String lineSeparator = System.lineSeparator(); 
				 
		try { 
			while (scanner.hasNextLine()) { 

				String s = scanner.nextLine(); 
				 
				if (!s.startsWith(":") && 
					!s.startsWith("#") && 
					!s.startsWith("-") && 
					!s.startsWith("/") && 
					!s.startsWith("*") && 
					!s.startsWith("@") 
					 
					) 
				fileContents.append(s); 
				 
				fileContents.append(lineSeparator); 
			} 
			return fileContents.toString(); 
		} finally { 
			scanner.close(); 
		} 
	} 

	 
	public static String[] getFileAsStringArraySkipComments(String path) 
	{ 
		String source = ""; 
		 
		 try { 
			 
			 source = Fu.getFileAsStringScannerSkipComments(path); 
			 
			 String[] arr = source.split(System.lineSeparator()); 
			 
			 return arr; 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},String.format("%s...%s",e.getMessage().substring(1,32),gl.S_ERROR)); 
			 
			return null; 
		} 
	} 
	 
	public static String[] get_str_array_from_file(String path) 
	{ 
		String source = ""; 
		 
		 try { 
			 
			 source = Fu.get_str_from_file(path); 
			 
			 String[] arr = source.split(System.lineSeparator()); 
			 
			 return arr; 
			 
		} catch (IOException e) { 
			 
			gl.tx_e(new Object(){},gl.sf("%s",e.toString().substring(1,32))); 
			 
			return null; 
		} 
	} 
	 
	 
	 

	 
	 
	public static boolean Test_readBytesNIO(String fileName,long position,int size) 
	{ 
		 
		byte[] result = readBytesNIO(fileName,position,size); 
		 
		String value = null ; 
		try { 
			//value = new String(result,"UTF-8"); 
			//value = new String(result,"cp866"); 
			value = new String(result,"cp1251"); 
			 
		} catch (UnsupportedEncodingException e) { 

			gl.tracex(new Object(){},"Error : " + e.getMessage()); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},"The result of read : " + value); 
		 
			return false; 
		 
	} 
	 
	public static boolean Test_readBytesNIO(String fileName,long position,int size,String owner) 
	{ 
		 
		byte[] result = readBytesNIO(fileName,position,size); 
		 
		String value = null ; 
		try { 
			//value = new String(result,"UTF-8"); 
			//value = new String(result,"cp866"); 
			value = new String(result,"cp1251"); 
			 
		} catch (UnsupportedEncodingException e) { 

			gl.tracex(new Object(){},"Error : " + e.getMessage()); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},String.format("Thread %s  read : %s ",owner,value)); 
		 
			return false; 
		 
	} 

	 
	public static byte[] readBytesNIO(String fileName,long position,int size) { 

		File file = new File(fileName); 

		if (!Files.isReadable(file.toPath())) { 
			gl.tracex(new Object(){},String 
					.format("readBytesNIO File %s is not readable or not is exists.", 
							fileName)); 

			return null; 
		} 

		long start = 0L, end = 0L; 

		FileInputStream fin = null; 

		FileChannel ch = null; 

		MappedByteBuffer buf = null; 

		byte[] bytes = null; 

		try { 

			start = System.nanoTime(); 

			fin = new FileInputStream(file); 

			ch = fin.getChannel(); 

			buf = ch.map(MapMode.READ_ONLY, 0, size); 
			 
			bytes = new byte[size]; 

			buf.get(bytes); 

			buf.clear(); 

			end = System.nanoTime(); 

			gl.tracex(new Object(){},String.format( 
					"readBytesNIO() read %s %d bytes  in  %d ms. ", 
					fileName, bytes.length, (end - start) / 1000000)); 

		} catch (IOException e) { 
			 

			return null; 

		} finally { 
			try { 

				if (fin != null) { 

					fin.close(); 

					fin = null; 

				} 
				if (ch != null) { 

					ch.close(); 

					ch = null; 

				} 
			} catch (IOException e) { 
				return null; 
			} 

			System.gc(); 
		} 

		return bytes; 
	} 

	 
	public static String get_file_as_string_nio(String source)
	{
		try
		{
		
			byte []  m_bytes  = getFileAsByteArrayNIO(source);
		
			if(m_bytes.length==gl.E_EMPTY)
			{
				gl.tx_e(new Object() {},gl.sf("Read of...%s...Length...%d ",source,m_bytes.length));
				
				return null;
				
			}
		
			gl.tx_k(new Object() {},gl.sf("Read of...%s...Length...%d ",source,m_bytes.length));
		
			String m_txt = Bau.to_str_from_byte_array(m_bytes,"Cp1251");
			
			if(m_txt.length() == gl.E_EMPTY)
			{
				gl.tx_e(new Object() {},gl.sf("Read of...%s...Length...%d ",source,m_txt.length()));
				
			}
			
				gl.tx_k(new Object() {},gl.sf("Read of...%s...Length...%d ",source,m_txt.length()));
		
				return m_txt;
		}
		catch(Exception e)
		{

				gl.tx_e(new Object() {},gl.sf("Exception...%s",e.toString()));
	
				return null;
		}
	}
	
	public static byte[] getFileAsByteArrayNIO(String fileName) { 

		File file = new File(fileName); 

		if (!Files.isReadable(file.toPath())) { 
			gl.tracex(new Object(){},String 
					.format("getFileAsByteArrayNIO File %s is not readable or not is exists.", 
							fileName)); 

			return null; 
		} 

		long start = 0L, end = 0L; 

		FileInputStream fin = null; 

		FileChannel ch = null; 

		MappedByteBuffer buf = null; 

		byte[] bytes = null; 

		try { 

			start = System.nanoTime(); 

			fin = new FileInputStream(file); 

			ch = fin.getChannel(); 

			int size = (int) ch.size(); 

			buf = ch.map(MapMode.READ_ONLY, 0, size); 
			 
			//buf.order(ByteOrder.BIG_ENDIAN); 

			bytes = new byte[size]; 

			buf.get(bytes); 

			buf.clear(); 

			end = System.nanoTime(); 

			gl.tracex(new Object(){},String.format( 
					"getFileAsByteArrayNIO() read %s %d bytes  in  %d ms. ", 
					fileName, bytes.length, (end - start) / 1000000)); 

		} catch (IOException e) { 
			 

			return null; 

		} finally { 
			try { 

				if (fin != null) { 

					fin.close(); 

					fin = null; 

				} 
				if (ch != null) { 

					ch.close(); 

					ch = null; 

				} 
			} catch (IOException e) { 
				return null; 
			} 

			System.gc(); 
		} 

		return bytes; 
	} 

	 
	  public static boolean to_file(String fileName,String value) 
	  { 
			return to_file(fileName,value,false); 
	  } 
		 
	  public static boolean to_file_append(String fileName,String value) 
	  { 
			return to_file(fileName,value,true); 
	  } 
	 
	  public static boolean saveStringToFileNL(String fileName,String value,boolean append) 
	  { 
		  long start = System.nanoTime(); 
		 
		  FileWriter fw = null; 

		  try { 
			 
			fw = new FileWriter(new File(fileName),append); 
			 
			fw.write(value); 
			 
			fw.write(System.lineSeparator()); 
			 
			long end = System.nanoTime(); 
			 
			//gl.tracex(new Object(){},String.format("saveStringToFile (%s) in  %d ms. ",fileName,(end-start)/1000000)); 
			 
		} catch (IOException e) { 
		 
			return false; 
		} 
		  finally 
		  { 
			  try { 
				fw.flush(); 
			} catch (IOException e) { 
		 
				return false; 
			} 
			 
			  try { 
				fw.close(); 
			} catch (IOException e) { 
		 
				return false; 
			} 
		  } 
		 
		  return true; 
	  } 
	 
	 
	  public static boolean dlgSaveStringToFile(String text_source) 
		{ 
			 
			Object v = new Object(){}; 
			 
			JFileChooser saveFile = new JFileChooser(); 
			 
			String current_dir = Fu.get_path(gl.getHome()); 
			 
			saveFile.setCurrentDirectory(new File(current_dir)); 
			 
			gl.tracex(v,String.format("%s...set file default dir...%s",gl.S_OK,current_dir)); 

			 
			if (saveFile.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) 
			{ 
				File file = saveFile.getSelectedFile(); 
				 
				String file_name = file.getAbsolutePath(); 
				 
				return Fu.to_file(file_name,text_source) && Fu.isaFile(file_name); 
					 
			} 
			  return false; 
		} 
	 
	  public static boolean to_file(String fileName,String value,boolean append) 
	  { 
		 
		  FileWriter fw = null; 

		  try { 
			 
			fw = new FileWriter(new File(fileName),append); 
			 
			fw.write(value); 
			 
		} catch (IOException e) { 
		 
			return false; 
		} 
		  finally 
		  { 
			 // try { 
				  
				//fw.flush(); 
				
			//} catch (IOException e) { 
		 
			//	return false; 
			//} 
			 
			  try { 
				  
				fw.close(); 
				
			} catch (IOException e) { 
		 
				e.printStackTrace();
				
				return false; 
			} 
		  } 
		 
		  		return true; 
	  } 
	 
	  public static boolean saveStringToFile(String fileName,String value,boolean append,boolean no_message) 
	  { 
		  long start = System.nanoTime(); 
		 
		  FileWriter fw = null; 

		  try { 
			 
			fw = new FileWriter(new File(fileName),append); 
			 
			fw.write(value); 
			 
			long end = System.nanoTime(); 
		 
			if(!no_message) 
			gl.tracex(new Object(){}, 
					String.format("Operation completed in : %04d ms.", 
							((end-start)/1000000) 
							)); 
			 
			 
			 
		} catch (IOException e) { 
		 
			return false; 
		} 
		  finally 
		  { 
			  try { 
				fw.flush(); 
			} catch (IOException e) { 
		 
				return false; 
			} 
			 
			  try { 
				fw.close(); 
			} catch (IOException e) { 
		 
				return false; 
			} 
		  } 
		 
		  return true; 
	  } 

	 
	 
	  /** 
	   * 
	 * @param pathname 
	 * @return 
	 */ 
	 
	 public static void Test_getListFromFile(String pathname) { 
		 
		 long start = System.nanoTime(); 
		 
		 ArrayList<String>  list  = getListFromFile(pathname); 

		 String msg = String.format("Loaded time : %d mcs. list size : %d rows.", 
				 (System.nanoTime() - start)/1000000, 
				 list.size()); 
		 
		 gl.tracex(new Object(){},msg); 
		 
		 		 
	 } 
	 
	public static ArrayList<String> getListFromFile(String pathname) { 
	 
		  String source = ""; 
		 
		  try { 
			source = get_str_from_file(pathname); 
		} catch (IOException e) { 
			return null; 
		} 
		 
		  String[] arr = source.split(System.lineSeparator()); 
		 
		  ArrayList<String> list = new ArrayList<String>(); 
		 
		  for(int i=0;i < arr.length;i++) 
		  { 
			  String ticker = arr[i].trim(); 
			 
			  if(!(ticker.startsWith(":") || 
					  ticker.startsWith("-") || 
					  	ticker.startsWith("#") || 
					  		ticker.startsWith("*") || 
					  			ticker.startsWith("/") || 
					  				ticker.startsWith("\\") 
					  ) && ticker.trim().length() != gl.E_EMPTY) 
			  { 
				 
				  list.add(ticker); 
			  } 
		  } 
		 
		 
		  return list; 
		 
	  } 
	 
	 
	public static List<String> getListOfStringFromFile(String pathname) { 
		 
		  String source = ""; 
		 
		  try { 
			source = get_str_from_file(pathname); 
		} catch (IOException e) { 
			return null; 
		} 
		 
		  String[] arr = source.split(System.lineSeparator()); 
		 
		  ArrayList<String> list = new ArrayList<String>(); 
		 
		  for(int i=0;i < arr.length;i++) 
		  { 
			  list.add(arr[i]); 
		  } 
		 
		  return list; 
		 
	  } 
	 
	 
	 

		 
	  public static void getFileList(File node,List<String> items) { 
		 
		  // add file only 
			if (node.isFile()) { 

				String s = node.getAbsoluteFile().toString(); 

				items.add(s); 
			} 

			if (node.isDirectory()) { 

				String[] subNote = node.list(); 

				for (String filename : subNote) { 
					getFileList(new File(node, filename),items); 
				} 
			} 
		} 
	 
		  	 
	 
	  	public static boolean mergeFiles_(List<File> items,String targetFile) 
		{ 
	  		 
		    FileWriter writer = null; 
		 
			try { 
				 
				writer = new FileWriter(targetFile, false); 
				 
			} catch (IOException e) { 

				return false; 
			} 
			 
			String s = ""; 
			 
			long start_time = System.currentTimeMillis(); 
			 
		    for(File f : items) 
			{ 
		    	s = f.getAbsolutePath(); 
		    	 
				gl.tracex(new Object(){},String.format("Merge item : %s ...",s)); 
				 
				int indexOf = s.lastIndexOf("\\"); 
				 
				String   strDir = s.substring(0,indexOf); 
				 
				String strFile = s.substring(indexOf+1,s.trim().length()); 
				 
				Path path = Paths.get(strDir,strFile); 
				 
				long l_start_time = System.currentTimeMillis(); 
				 
				try (Stream<String> lines = Files.lines (path, StandardCharsets.UTF_8)) 
				{ 
				    for (String line : (Iterable<String>) lines::iterator) 
				    { 
				    	writer.write(line); 
				    	 
				    	writer.write(System.lineSeparator()); 
				    	 
				    } // end for 
				 
				   long l_end_time = System.currentTimeMillis(); 
				 
				   gl.sm(String.format("%s ms.\n",(l_end_time - l_start_time))); 
				 
				 
				} catch (IOException e) { 
						 
						return false; 
				} 
							 
			} // for 
		 
		    long end_time = System.currentTimeMillis(); 
			 
			gl.tracex(new Object(){},"Total spending of time : " + (end_time - start_time) + " ms."); 
			 
		 
		    try { 
				 
				writer.flush(); 
				 
				writer.close(); 
				 
			} catch (IOException e) { 

				return false; 
			} 

	  			return true; 
		} 
	  	 
	  	public static boolean mergeFiles(List<File> items,String targetFile) 
		{ 
	  			  		 
			String filePath = ""; 
			 
			if (!Fu.deleteFile(targetFile)) 
			{ 
				gl.tracex(new Object(){},"mergeFiles() Error while delete file : " + targetFile); 
				 
				return false; 
			} 
			 
			long start_time = System.currentTimeMillis(); 
			 
		    for(File f : items) 
			{ 
		    	filePath = f.getAbsolutePath(); 
		    	 
				gl.tracex(new Object(){},String.format("Merge item : %s ...",filePath)); 
				 
				long l_start_time = System.currentTimeMillis(); 
			 
				if(!mergeOneFile(filePath,targetFile)) 
				{ 
					return false; 
				} 
							 
			} // for 
		 
		    long end_time = System.currentTimeMillis(); 
			 
			gl.tracex(new Object(){},"Total spending of time : " + (end_time - start_time) + " ms."); 
			 
		 
		    return true; 
		} 
	  	 
	  	 
	  	public static boolean mergeFiles(List<File> items,String targetFile,String strDelimeter) 
		{ 
	  			  		 
			String filePath = ""; 
			 
			if (!Fu.deleteFile(targetFile)) 
			{ 
				gl.tracex(new Object(){},"mergeFiles() Error while delete file : " + targetFile); 
				 
				return false; 
			} 
			 
			long start_time = System.currentTimeMillis(); 
			 
		    for(File f : items) 
			{ 
		    	filePath = f.getAbsolutePath(); 
		    	 
				gl.tracex(new Object(){},String.format("Merge item : %s ...",filePath)); 
				 
				long l_start_time = System.currentTimeMillis(); 
			 
				if(!mergeOneFile(filePath,targetFile,strDelimeter)) 
				{ 
					return false; 
				} 
							 
			} // for 
		 
		    long end_time = System.currentTimeMillis(); 
			 
			gl.tracex(new Object(){},"Total spending of time : " + (end_time - start_time) + " ms."); 
			 
		 
		    return true; 
		} 
	  	 
	  public static void mergeFiles(String inputDir,String targetFile) 
		{ 
		  	List<String> items = new ArrayList<String>(); 
		 
			Fu.getFileList(new File(inputDir),items); 
			 
			 
		    FileWriter writer = null; 
		 
			try { 
				writer = new FileWriter(targetFile, false); 
			} catch (IOException e1) { 
				//  Auto-generated catch block 
				return; 
			} 
			 
		    for(String s : items) 
			{ 
				gl.tracex(new Object(){},s); 
				 
				int indexOf = s.lastIndexOf("\\"); 
				 
				String   strDir = s.substring(0,indexOf); 
				 
				String strFile = s.substring(indexOf+1,s.trim().length()); 
				 
				Path path = Paths.get(strDir,strFile); 
				 
				try (Stream<String> lines = Files.lines (path, StandardCharsets.UTF_8)) 
				{ 
				    for (String line : (Iterable<String>) lines::iterator) 
				    { 
				    	writer.write(line); 
				    	 
				    	writer.write(System.lineSeparator()); 
				    	 
				    } // end for 
				 
				} catch (IOException e) { 
						 
						return; 
				} 
							 
			} // for 
		 
		    try { 
				 
				writer.flush(); 
				 
				writer.close(); 
				 
			} catch (IOException e) { 
				//  Auto-generated catch block 
				return; 
			} 
		} 
	 
	  public static String get_first_row(String source_file) 
	  { 
		  String[] arr = Fu.get_str_array_from_file(source_file); 
		 
		  return arr[gl.E_EMPTY]; 
	  } 
	 
	  public static void deleteFirstRow(String inputDir) 
		{ 
		 
		  		List<String> items = new ArrayList<String>(); 
		 
		  		Fu.getFileList(new File(inputDir),items); 

		  		 
			for(String s : items) 
			{ 
				gl.tracex(new Object(){},s); 
				 
				int indexOf = s.lastIndexOf("\\"); 
				 
				String   strDir = s.substring(0,indexOf); 
				 
				String strFile = s.substring(indexOf+1,s.trim().length()); 
				 
				Path path = Paths.get(strDir,strFile); 
				 
				 
				int i = 0; 
		 
				StringBuilder sb = new StringBuilder(); 
				 
				try (Stream<String> lines = Files.lines (path, StandardCharsets.UTF_8)) 
				{ 
				    for (String line : (Iterable<String>) lines::iterator) 
				    { 
				 
				        if(i != gl.E_EMPTY) 
				        { 
				        	sb.append(line); 
				        	 
				        	sb.append(System.lineSeparator()); 
				        } 
				 
				        i++; 
				        	 
				    } // end for 
				 
				 
				    FileWriter writer = null; 
					 
					try { 
						 
						writer = new FileWriter(s, false); 
						 
						writer.write(sb.toString()); 
						 
						writer.flush(); 
						 
						writer.close(); 
						 
						sb.setLength(gl.E_EMPTY); 
						 
					} catch (IOException e1) { 
						//  Auto-generated catch block 
						return; 
					} 
					 
						 
				    	 
				} catch (IOException e) { 
					//  Auto-generated catch block 
					return; 
				} 
				 
			} 
		} 
	 
	 
	  public static boolean writeToFile(String fileName,String data) 
		{ 

				File file = new File(fileName); 
			 
				FileWriter fw = null; 
			 
				try { 
					fw = new FileWriter(file,false); 
					} catch (IOException e) { 

						return false; 
					} 
				 
					 
			try { 

				 
				fw.write(data); 
				 
				fw.flush(); 

				fw.close(); 

				 
			} catch (IOException e) { 

				return false; 
			} 
			 
				return true; 
		} 

	 
	  public static boolean appendToFile(String fileName,String data) 
		{ 

				File file = new File(fileName); 
			 
				FileWriter fw = null; 
			 
				try { 
					fw = new FileWriter(file,true); 
					} catch (IOException e) { 

						return false; 
					} 
				 
					 
			try { 

				 
				fw.write(data); 
				 
				fw.flush(); 

				fw.close(); 

				 
			} catch (IOException e) { 

				return false; 
			} 
			 
				return true; 
		} 

	 
	 
	  public static String get_path(String path) 
	  { 
		 
		  if(!path.contains("\\")) 
			  return ""; 
		 
		  int indexOf = path.lastIndexOf("\\"); 
		 
		  if(indexOf== gl.E_ERROR) 
			  return path; 
		 
		  //gl.tracex(new Object(){},"Input length : " + path.length() + " IndexOf : " + indexOf); 
		 
		  if(indexOf+1 == path.length()) 
			  return path; 
		 
		  return path.substring(0,indexOf+1); 
		 
	  } 
	 
	 
	 
	  public static  void getFilesInDirByMaskWithRecursion(Path path,String mask) 
	  { 

		  try { 
			Files.walk(path) 
			  .filter(p->p.toString().endsWith(".java")) 
			  .map(p->p.getParent().getParent()) 
			  .distinct() 
			  .forEach(System.out::println); 
		} catch (IOException e) { 
			 
		} 
		 
		 
	  } 
	 
	 
 
	  /** 
	 * @param path 
	 * @param mask "*.{java,class,jar}" 
	 * @param files 
	 * @return 
	 */ 
	 
	public static  boolean getFilesInDirByMask(Path path,String mask,List<File> files) 
	  { 
		 
		  try (DirectoryStream<Path> stream = Files.newDirectoryStream(path,mask)) 
		  { 
			  for(Path entry : stream) 
			  { 
				  files.add(entry.toFile()); 
			  } 
			 
			   return (files.size() != gl.E_EMPTY); 
		  } 
		  catch(IOException e) 
		  { 
			  return false; 
		  } 
	  } 
	 
	 
	public static String get_pure_file_name(String path) 
	{ 
		String pureName = get_file_name(path); 
		 
		int index = pureName.lastIndexOf("."); 
		 
		if(index != gl.E_ERROR) 
			pureName = pureName.substring(gl.E_EMPTY,index--); 
		 
		return pureName;	 
				 
	} 
	 
	public static String getBakName(String file_source,String ext) 
	{ 
		 
		String path = Fu.get_path(file_source); 
		 
		String pure_name = Fu.get_pure_file_name(file_source); 
		 
		return String.format("%s\\%s.%s",path,pure_name,ext); 
		 
	} 
	 
	public static String getTempFile(String dir,String ext) 
	{ 
		return String.format("%s\\temp_%03d.%s",dir,gl.getRandomInt(999),ext); 
	} 
	 
	public static boolean rename(String file_source,String file_dest) 
	{ 
				 
		if (!Fu.isaFile(file_source)) 
		{ 
			gl.tracex(new Object(){},String.format("Input file is not exist...%s",file_source)); 
			 
			return false; 
		} 
		 
		File file = new File(file_source); 
		 
		 
		file.renameTo(new File(file_dest)); 
		 
		 
		return Fu.isaFile(file_dest); 
		 
	} 
	 
	 
	 
	public static String get_file_name(String path) 
	{ 
		int indexOf = path.lastIndexOf("\\"); 
		 
		if (indexOf==gl.E_ERROR) 
			return path; 
		 
		return path.substring(++indexOf,path.length()); 
				 
	} 
	 
	public static String getPackageName(String source) 
	{ 
		 
		String find = "package "; 
				 
		int index = source.indexOf(find); 
		 
		if(index == gl.E_ERROR) return null; 
		 
		int index_end = source.indexOf(";",index); 
		 
		if(index_end == gl.E_ERROR) return null; 
				 
		String package_name = source.substring(index+find.length(),index_end).trim(); 
		 
		return package_name; 
		 
	} 
	 
	/* 
	public static String createMd5String(String source) 
	{ 
		 
		String text = ""; 
		 
		String md5 = ""; 
		 
		try { 
			 
			text = FileUtil.getFileAsStringScanner(source); 
			 
			md5 = ap.crypto.Hash.md5(text); 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		return md5; 
		 
	} 
	*/ 
	 
	/* 
	public static String createSHA1String(String source) 
	{ 
		 
		String text = ""; 
		 
		String sha1 = ""; 
		 
		try { 
			 
			text = FileUtil.getFileAsStringScanner(source); 
			 
			sha1 = ap.crypto.Hash.sha1(text); 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		return sha1; 
		 
	} 
	 */ 
	// Input  value, like [app.xml] 
	// Output  value, must be [/app/xml] 
		public static String getCanonicalDir(String value) 
	{ 
		 
			char findChar = '.'; 
			 
			String result = value; 
			 
			int index = result.indexOf(findChar); 
			 
			if(index != gl.E_ERROR) 
				result = value.replace(findChar,'\\'); 
			 
				 
			return "\\".concat(result); 
				 
		 
	} 
	 
	 
	 
	 
	  public static void Test_getFilesInDir(Path path,String mask) 
	  { 
		  List<File> files = new ArrayList<File>(); 
				 
		  if (getFilesInDirByMask(path,mask,files)) 
		  { 
			  gl.tracex(new Object(){},"Test Ok. " + files.size()); 
			 
			  files.forEach( 
					  item->{ 
						  gl.tracex(new Object(){},item.getAbsoluteFile().getName()); 
					  }); 
		  } 
		  else 
		  { 
			  gl.tracex(new Object(){},"Test Error."); 
		  } 
		 
	  } 
	 
	  public static boolean Test_mergeFiles(String path,String[] mask,String file_out) 
	  { 
		  return mergeFiles(path,mask,file_out); 
	  } 
	 
	 
	  public static List<File> getFilesByMask(String path,String[] mask ,boolean stop_message) 
	  { 

		  List<File> files = new ArrayList<File>(); 
		 
		  try { 
			files = getFilesInDirRecursively(new File(path),mask); 

			if(!stop_message) 
			files.forEach( 
					  item->{ 
						  gl.tracex(new Object(){},item.getAbsoluteFile().getName()); 
					  }); 
					 
			return files; 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"getFilesByMask() : " + e.getMessage()); 
			 
			return null; 
		} 
		 
	  } 

	  public static List<File> getFilesByMask(String path,String[] mask) 
	  { 
		  return getFilesByMask(path,mask,true); 
	  } 
	 
	  public static boolean mergeFiles(String path,String[] mask,String file_out,String strDelimeter) 
	  { 

		  if (Fu.isaFile(file_out)) 
			  if (!Fu.deleteFiles(new File(file_out))) 
				  return false; 
		 
		  		 
		  List<File> files = new ArrayList<File>(); 
		 
		  Fu.files_count = gl.E_EMPTY; 
		 
		  try { 
			files = getFilesInDirRecursively(new File(path),mask); 
			 
			files.forEach( 
					  item->{ 
						  gl.tracex(new Object(){},item.getAbsoluteFile().getName()); 
						 
						  Fu.files_count++; 
					  }); 
		 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"mergeFiles() : " + e.getMessage()); 
			 
			return false; 
		} 

		  gl.tracex(new Object(){},"\n\n"); 
		 
		  gl.tracex(new Object(){},"mergeFiles () count of items proceed : " + Fu.files_count); 
		 
		  gl.tracex(new Object(){},""); 
		 
		  return  Fu.mergeFiles(files,file_out,strDelimeter); 
		 
	  } 
	 
	  public static boolean mergeFiles(String path,String[] mask,String file_out) 
	  { 

		  List<File> files = new ArrayList<File>(); 
		 
		  try { 
			files = getFilesInDirRecursively(new File(path),mask); 
			 
			files.forEach( 
					  item->{ 
						  gl.tracex(new Object(){},item.getAbsoluteFile().getName()); 
					  }); 
		 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"mergeFiles() : " + e.getMessage()); 
			 
			return false; 
		} 

		 
		  return  Fu.mergeFiles(files,file_out); 
		 
	  } 

	 
	  public static List<File> getFilesInDirRecByDateFilter(String path,String[] mask,String filter_date,String cond) 
	  { 
		  List<File> files = null; 
		 
		  List<File> result = new ArrayList<File>(); 
		 
		  try { 
			 
			  files = getFilesInDirRecursively(new File(path),mask); 
			 
			  Date filter_dt = DateUtil.to_date(filter_date) ; 
			 
			  long msc_filter = filter_dt.getTime(); 
			 
			  files.forEach(item->{ 
			 
				 
				  Date file_dt = DateUtil.date_from_file_stamp(item.lastModified()); 
				 
				  long msc_file = file_dt.getTime(); 
				 
				  if(cond.equalsIgnoreCase(">")) 
				  { 
					  if(msc_file > msc_filter) 
						  result.add(item); 
				  } 
				  else if(cond.equalsIgnoreCase("<")) 
				  { 
					  if(msc_file < msc_filter) 
						  result.add(item); 

				  } 
				  else if(cond.equalsIgnoreCase("=")) 
				  { 
					  if(msc_file == msc_filter) 
						  result.add(item); 
				  } 

				 
			  }); 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		  return result; 
		  		 
	   } 

	  public static List<File> getFilesInDirRecByDateTimeFilter(String path,String[] mask,String filter_date_time,String cond) 
	  { 
		  List<File> files = null; 
		 
		  List<File> result = new ArrayList<File>(); 
		 
		  try { 
			 
			  files = getFilesInDirRecursively(new File(path),mask); 
			 
			  Date filter_dt = DateUtil.to_date_from_datetime(filter_date_time) ; 
			 
			  // Clear msec. 
			  long msc_filter = filter_dt.getTime()/1000; 
			 
			  files.forEach(item->{ 
			 
				 
				  Date file_dt = DateUtil.date_from_file_stamp(item.lastModified()); 
				 
				  long msc_file = file_dt.getTime()/1000; 
				 
				  //gl.tracex(new Object(){},String.format("Filter : %s  file : %s msc_filter : %d msc_file : %d ",filter_dt.toString(),file_dt.toString(),msc_filter,msc_file)); 

				 
				  if(cond.equalsIgnoreCase(">")) 
				  { 
					  if(msc_file > msc_filter) 
						  result.add(item); 
				  } 
				  else if(cond.equalsIgnoreCase("<")) 
				  { 
					  if(msc_file < msc_filter) 
						  result.add(item); 

				  } 
				  else if(cond.equalsIgnoreCase("=")) 
				  { 
					  if(msc_file == msc_filter) 
						  result.add(item); 
				  } 

				  msc_file = 0L; 
				 
			  }); 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		  return result; 
		  		 
	   } 
	 
	 
	  public static List<File>  getFilesInDirRecByDateFilterNess(String path,String[] mask,String filter_date) 
	  { 
		  List<File> files = null; 
		 
		  //List<File> result = new ArrayList<File>(); 
		 
		  try { 
			 
			  files = getFilesInDirRecursively(new File(path),mask); 
			 
			  Date filter_dt = DateUtil.to_date(filter_date) ; 
			 
			  long msc_filter = filter_dt.getTime(); 
			 
			  files.forEach(item->{ 
			 
				 
				  Date file_dt = DateUtil.date_from_file_stamp(item.lastModified()); 
				 
				  long msc_file = file_dt.getTime(); 
				 
				 
				  String msg = String.format("%s %s %d %s %d %d",item.getAbsolutePath(),DateUtil.to_string(file_dt),file_dt.compareTo(filter_dt),DateUtil.to_string(filter_dt),msc_file,msc_filter); 
				 
				  String msg_new = String.format("%s %s %s %s",item.getAbsolutePath(),DateUtil.to_string(file_dt),"%s",DateUtil.to_string(filter_dt)); 
				 
				 
				  if(msc_file > msc_filter) 
					  msg_new = String.format(msg_new,">"); 
				  else if(msc_file == msc_filter) 
					  msg_new = String.format(msg_new,"=="); 
				  else if(msc_file < msc_filter) 
				  msg_new = String.format(msg_new,"<"); 
					 
				  gl.tracex(new Object(){},msg_new); 
				 
				  msg = null; 
								 
			  }); 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		 
		  return files; 
		  		 
	   } 

	  public static List<File>  getFilesInDirRecByDateFilterEqual(String path,String[] mask,String filter_date) 
	  { 
		  List<File> files = null; 
		 
		  List<File> result = new ArrayList<File>(); 
		 
		  try { 
			 
			  files = getFilesInDirRecursively(new File(path),mask); 
			 
			 
			  files.forEach(item->{ 
				 
				  Date file_dt = DateUtil.date_from_file_stamp(item.lastModified()); 
				 
				  Date filter_dt = DateUtil.to_date(filter_date) ; 
				 
				  gl.tracex(new Object(){},"Filter : " + item.getAbsolutePath() + " " + DateUtil.to_string(file_dt) + " :: " + DateUtil.to_string(filter_dt)); 
				 
			  }); 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		 
		  return files; 
		  		 
	   } 

	 
	 
	  public static List<File>  getFilesInDirRecByDateFilterLess(String path,String[] mask,String filter_date) 
	  { 
		  List<File> files = null; 
		 
		  //List<File> result = new ArrayList<File>(); 
		 
		  try { 
			 
			  files = getFilesInDirRecursively(new File(path),mask); 
			 
			  for(File f : files) 
			  { 
				  Date file_dt = DateUtil.date_from_file_stamp(f.lastModified()); 
				 
				  Date filter_dt = DateUtil.to_date(filter_date) ; 
				 
				  if (file_dt.after(filter_dt)) 
					  files.remove(f); 
				 
			  } 
				 
		} catch (IOException e) { 
			 
			return null; 
		} 
		  return files; 
	  } 
		 
		 
	  public static void Test_getFilesInDirRec(String path,String[] mask) 
	  { 
		  List<File> files = new ArrayList<File>(); 
			 
		 
			 
		  try { 
			files = getFilesInDirRecursively(new File(path),mask); 
			 
			files.forEach( 
					  item->{ 
						 
						  long msc = item.lastModified(); 
						 
						  Date dt = new Date(msc); 
						 
						  gl.tracex(new Object(){},String.format("%s last modified : %s timestamp now : %s file timestamp : %s",item.getAbsoluteFile(),DateUtil.date_time_stamp_file(msc),DateUtil.get_date_stamp_now(),DateUtil.getDateStampFile(msc))); 
						 
					  }); 
		 
			 
		} catch (IOException e) { 
			return; 
		} 
	  } 
	 
	  public static void Test_getFilesInDirRecByFilter(String path,String[] mask,String filter) 
	  { 
		  List<File> files = new ArrayList<File>(); 
			 
		  try { 
			 
			  files = getFilesInDirRecByDateFilterNess(path,mask,filter); 
			 
			files.forEach( 
					  item->{ 
						 
						  long msc = item.lastModified(); 
						 
						  Date dt = new Date(msc); 
						 
						  //gl.tracex(new Object(){},String.format("%s last modified : %s timestamp now : %s file timestamp : %s",item.getAbsoluteFile(),gl.getDateTimeStampFile(msc),gl.getDateStampNow(),gl.getDateStampFile(msc))); 
						 
					  }); 
		 
			 
		} catch (Exception e) { 
			 
			return; 
		} 
		 
		 
	  } 
	 
	  public static boolean Test_generate_DSA(String file_name,String file_template,String file_main_template,String file_output) 
	  { 

		  Object v = new Object(){}; 
		 
		  gl.tracex(v,String.format("%s...%s...%s...%s...%s...%s","Input",file_name,"Template",file_template,"Output",file_output)); 
		 
		  if(!Fu.isaFile(file_name) || !Fu.isaFile(file_template) || !Fu.isaFile(file_main_template)) 
		  { 
			  gl.tracex(v,String.format("%s...%s...%s...%s","Input or template file ",file_name,file_template,"Error")); 
			 
			  return false; 
		  } 
		 
		  try { 
			 
			  String source = Fu.get_str_from_file(file_name); 
			 
			  String src_template = Fu.get_str_from_file(file_template); 
			 
			  String src_main_template = Fu.get_str_from_file(file_main_template); 
				 
			 
			  if(source.length()==gl.E_EMPTY || src_template.length()==gl.E_EMPTY || src_main_template.length()==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s...%s","Source or template is empty ",file_name,file_template,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			  String[]  arr = source.split(System.lineSeparator()); 
			 
			  if(arr.length ==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Source has no rows ",file_name,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			  StringBuilder sb = new StringBuilder(); 
			 
			  String tmp = ""; 
			 
			  for(int i=0; i < arr.length;i++) 
			  { 
				  if(arr[i].trim().length() != gl.E_EMPTY) 
				  { 
					  tmp = String.format(src_template,arr[i]); 
					 
					  sb.append(tmp); 
				  } 
				 
			  } 
			 
			 
			 
			  String general = String.format(src_main_template,sb.toString()); 
			 
			  //gl.tracex(new Object(){},general.toString()); 
			 
			  if(!Fu.writeToFile(file_output,general)) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s...%s","Process","write to",file_output,"Error")); 
				 
				  return false; 
			  } 
			 
			  sb.setLength(gl.E_EMPTY); 
			 
			  gl.tracex(v,String.format("%s...%s","Process","Ok")); 
			 
			  return true; 
			 
			 
			 
		} catch (IOException e) { 

			  gl.tracex(v,String.format("%s...%s...%s...%s","Input file ",file_name,"Exception",""+e.toString())); 
			 
			  return false; 
		} 
	  } 
	 
	  public static int getCountOfRows(String file_name) 
	  { 
		  return Fu.get_list_from_file(file_name).size(); 
		 
	  } 
	 
	  public static boolean Test_orWell(String file_name) 
	  { 

		  class Item 
		  { 
			 
			  public int id; 
			 
			  public String Name; 
			 
			  public int count; 
			 
			 
			 
			  public int getId() { 
				return id; 
			} 



			public void setId(int id) { 
				this.id = id; 
			} 



			public String getName() { 
				return Name; 
			} 



			public void setName(String name) { 
				Name = name; 
			} 



			public int getCount() { 
				return count; 
			} 



			public void setCount(int count) { 
				this.count = count; 
			} 



			public Item(int id,String name) 
			  { 
				  this.setId(id); 
				 
				  this.setName(file_name); 
			  } 
			 
		  }; 
		 
		  Object v = new Object(){}; 
		 
		  if(!Fu.isaFile(file_name)) 
		  { 
			  gl.tracex(v,String.format("%s...%s...%s","Input file ",file_name,"Error")); 
			 
			  return false; 
		  } 
		 
		  try { 
			 
			  String source = Fu.get_str_from_file(file_name); 
			  	 
			  if(source.length()==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Source or template is empty ",file_name,"Error")); 
				 
				  return false; 
			  } 
			 
			  String[]  arr = source.split(System.lineSeparator()); 
			 
			  if(arr.length ==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Source has no rows ",file_name,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			 
			  String tmp = ""; 
			 
			  Vector<Item> items = new Vector<Item>(); 
			 
			  HashSet<String> words = new LinkedHashSet<String>(); 
			 
			  int counter = gl.E_EMPTY; 
			 
			  for(int i=0; i < arr.length;i++) 
			  { 
				  String[] word = arr[i].split(" "); 
				 
				  for(int j=0;j<word.length;j++) 
				  { 
					  if(!words.contains(word[j])) 
					  { 
						  words.add(word[j]); 
						 
						  counter++; 
						 
						  gl.tracex(new Object(){},"New item : " + word[j] + " cnt : " + counter); 
						 
					  } 
					  else 
					  { 
						  gl.tracex(new Object(){},"Count : " + word[j]); 
					  } 
				  } 
				 
			  } 
			 
			  gl.tracex(v,String.format("%s...%s","Process","Ok")); 
			 
			  words.forEach(a->{gl.tracex(new Object(){},a);}); 
			 
			  return true; 
			 
			 
			 
		} catch (IOException e) { 

			  gl.tracex(v,String.format("%s...%s...%s...%s","Input file ",file_name,"Exception",""+e.toString())); 
			 
			  return false; 
		} 
		 
		 
		 
		 
		 
	  } 
	 
	 
	  public static void Test_extractPath(String[] tests) 
	  { 
		  for(int i=0;i<tests.length;i++) 
		  { 
			  gl.tracex(new Object(){},"Input : " + tests[i] + " Output : " + get_path(tests[i])); 
		  } 
		 
	  } 
	 
	 
		public static ArrayList<File> getFilesInDirRecursively(File rootDirectory,String[] masks) 
				throws IOException { 

			ArrayList<File> m_result = null; 
			
			try { 
				 
				m_result = new ArrayList<File>(); 
				 
				File files[] = rootDirectory.listFiles(); 
				 
				for (int i = 0; i < files.length; i++) { 
						 
					File file = files[i]; 
					 
					 
					if (file.isFile()) 
					{ 
						 
						// Include * mask 
						 
						if( masks.length == gl.E_OK && 
							(masks[gl.E_EMPTY].equalsIgnoreCase("*"))) 
						{ 
							m_result.add(file); 
						} 
						else 
						{ 
							String extension = Su.AfterAtPeriod(file.getName()); 
							 
							for(int j=0;j<masks.length;j++) 
							{ 
									if (masks[j].equalsIgnoreCase(extension)) 
										m_result.add(file);		 
							} 
						} 
						 
					} 

					if (file.isDirectory()) { 
						 
						java.util.List<File> subDirFiles = getFilesInDirRecursively(file,masks); 
						 
						m_result.addAll(subDirFiles); 
					} 
				} 
				 
				return m_result; 
				 
			} catch (IOException e) { 
				 
				gl.tx_e(new Object(){},gl.sf("%s",e.getMessage())); 
				 
				return null; 
			} 
		} 
		 
	public static void showFilesInDirRecursively(File rootDirectory,String[] masks) 
				throws IOException { 

			 int cnt_file = gl.E_EMPTY; 

			try { 
				 
				File files[] = rootDirectory.listFiles(); 
				 
				for (int i = 0; i < files.length; i++) { 
				 
					cnt_file++; 
					 
					File file = files[i]; 
					 
					 
					if (file.isFile()) 
					{ 
						 
						// Include * mask 
						 
						if( masks.length == gl.E_OK && 
							(masks[gl.E_EMPTY].equalsIgnoreCase("*"))) 
						{ 
			 
						} 
						else 
						{ 
							String extension = Su.AfterAtPeriod(file.getName()); 
							 
							for(int j=0;j<masks.length;j++) 
							{ 
									if (masks[j].equalsIgnoreCase(extension)) 
									{ 
										gl.tracex(new Object(){},file.getAbsolutePath()); 
									} 
												 
							} 
						} 
						 
					} 

					if (file.isDirectory()) { 
						 
						java.util.List subDirFiles = getFilesInDirRecursively(file,masks); 
						 
						subDirFiles.forEach(a->{ 
							 
							gl.tracex(new Object(){},((File)a).getAbsolutePath()); 
							 
						}); 
						 
					} 
				} 
				 
				 
			} catch (IOException ex) { 
				 
				gl.tracex(new Object(){},"listFilesRecursively() exception : " + ex.getMessage()); 
				 
			} 
		} 
		 
	public static void showFilesInDirRecToLogger(File rootDirectory,String[] masks,Logger log,String target_dir) 
			throws IOException { 

		 int cnt_file = gl.E_EMPTY; 

		try { 
			 
			File files[] = rootDirectory.listFiles(); 
			 
			int[] cnt = {1}; 
			 
			 
			 
			for (int i = 0; i < files.length; i++) { 
			 
				cnt_file++; 
				 
				File file = files[i]; 
				 
				 
				if (file.isFile()) 
				{ 
					 
					// Include * mask 
					 
					if( masks.length == gl.E_OK && 
						(masks[gl.E_EMPTY].equalsIgnoreCase("*"))) 
					{ 
		 
					} 
					else 
					{ 
						String extension = Su.AfterAtPeriod(file.getName()); 
						 
						for(int j=0;j<masks.length;j++) 
						{ 
								if (masks[j].equalsIgnoreCase(extension)) 
								{ 
									gl.tracex(new Object(){},file.getAbsolutePath()); 
									 
									//log.awrite(file.getAbsolutePath()); 
								} 
											 
						} 
					} 
					 
				} 

				if (file.isDirectory()) { 
					 
					java.util.List<File> subDirFiles = getFilesInDirRecursively(file,masks); 
					 
					 
					subDirFiles.forEach(a->{ 
						 
						String s_file = ((File)a).getAbsolutePath(); 
								 
						gl.tracex(new Object(){},String.format("%05d %s",cnt[0],s_file)); 
						 
						String trg_file = String.format("%s%s%s%s","\"",target_dir,Fu.get_file_name(s_file),"\""); 
						 
						/* 
						if(FileUtil.isaFile(String.format("%s%s",target_dir,FileUtil.getFilename(s_file)))) 
						{ 
							//gl.tracex(new Object(){},"Catch --- > : " + String.format("%s%s",target_dir,FileUtil.getFilename(s_file))); 
							 
							// Void dublicate. 
							String dub_file_name = String.format("%s_%d.%s",FileUtil.getFilenamePure(s_file),1,FileUtil.getFileExt(s_file)); 
							 
							// Re - set. 
							trg_file = String.format("%s%s%s%s","\"",target_dir,dub_file_name,"\""); 
							 
						} 
						*/ 
						 
						String msg = String.format("copy /Y /B %s%s%s %s","\"",s_file,"\"",trg_file); 
						 
						log.awrite(msg); 
						 
						cnt[0]++; 
						 
						 
					}); 
				 
					 
				} 
			} 
			 
			 
		} catch (IOException ex) { 
			 
			gl.tracex(new Object(){},"listFilesRecursively() exception : " + ex.getMessage()); 
			 
		} 
		 
	} 
	 
		 
		public static ArrayList<File> getFileList(File rootDirectory,String[] pext) throws IOException 
		{ 
			return getFilesInDirRecursively(rootDirectory,pext); 
					 
		} 
		 
		public static ArrayList<File> getFileListInDir(File rootDirectory,String pext) 
				throws IOException { 

			int cnt_file = gl.E_EMPTY; 
			 
			ArrayList<File> filesToBeReturned = null; 
			 
			try { 
				 
				filesToBeReturned = new ArrayList<File>(); 
				 
				File files[] = rootDirectory.listFiles(); 
				 
				for (int i = 0; i < files.length; i++) { 
				 
					cnt_file++; 
					 
					File file = files[i]; 

					String fileName = file.getCanonicalPath(); 
					 
					String ext = ""; 
					 
					int iext = file.getName().lastIndexOf("."); 
					 
					if(iext != -1) 
					{ 
						int ilen = file.getName().trim().length(); 
					 
						ext = file.getName().substring(iext+1,iext + (ilen - iext)); 
					 
						 
					} 
					 

					if(pext.trim().equalsIgnoreCase(ext)) 
					{ 
						filesToBeReturned.add(file); 
					} 

					if (file.isDirectory()) { 
						java.util.List subDirFiles = getFileListInDir(file,pext); 
						 
						filesToBeReturned.addAll(subDirFiles); 
					} 
				} 
				 
				return filesToBeReturned; 
			} catch (IOException ex) { 
				System.out.println("Exception :" + ex.getMessage()); 
			} 

			return filesToBeReturned; 
		} 

		/* 
		public static void Test_Md5_SHA1() 
		{ 
			  String targetFile = "e:\\bin\\test\\as.sql"; 
			 
			  //Test_mergeFiles("e:\\bin\\test\\","java",targetFile); 
			 
			  String msgMd5 = "\nMd5 : " + FileUtil.createMd5String(targetFile); 
					 
			  gl.tracex(new Object(){},msgMd5); 
			 
			  String msgSHA1 = "\nSHA1 :" + FileUtil.createSHA1String(targetFile); 
					 
			  gl.tracex(new Object(){},msgSHA1); 
			 
			  if(!FileUtil.saveStringToFile(StringUtil.BeforeAtPeriod(targetFile).concat(".md5"), msgMd5,true)) 
				  gl.tracex(new Object(){},"Error while create md5 file"); 
			 
			  if(!FileUtil.saveStringToFile(StringUtil.BeforeAtPeriod(targetFile).concat(".sha1"), msgSHA1,true)) 
				  gl.tracex(new Object(){},"Error while create sha1 file"); 
					 
			 
			 
		} 
		 
		 */ 
		 
		 
		public static void testCase(String root_dir, String file_ext) throws IOException 
		{ 
			 
			ArrayList<File> array = getFileListInDir(new File(root_dir),file_ext); 
			 
			gl.tracex(new Object(){},"Count of files : " + array.size()); 
			 
			 
			FileOutputStream summary_file = new FileOutputStream(root_dir + "\\" + "a.sql"); 
			 
			StringBuilder sb = new StringBuilder(); 
			 
				for(File f : array) 
				{ 
					sb.append(Fu.get_str_from_file(f.getCanonicalPath())); 
				} 
				 
					summary_file.write(sb.toString().getBytes()); 
					 
					summary_file.close(); 
				 
		} 

	 
		public static void testGetFileIndir() 
		{ 
			List<File> tmp = Fu.getFilesByMask("c:\\bin\\bat\\",new String[]{"bat"}); 
			 
			//gl.tracex(new Object(){},"Count : " + tmp.size()); 
			 
			prevDir = ""; 
			 
			curDir = ""; 
						 
			tmp.forEach(a->{try { 
				 
				curDir = a.getParent(); 
				 
				l_size += a.length(); 
				 
				//gl.sm(""+a.getCanonicalPath() + " " + curDir + " "  + l_size + "\n"); 
				 
				if(!curDir.equalsIgnoreCase(prevDir) && prevDir != "") 
				{ 
					gl.tracex(new Object(){},"["+ prevDir+"]" + ":" + l_size); 
					 
					l_size = 0L; 
					 
				} 
								 
				prevDir = a.getParent(); 
					 
				 
								 
			} catch (Exception e) { 
				//  Auto-generated catch block 
				e.printStackTrace(); 
			}}); 
			 
			gl.tracex(new Object(){},"["+ prevDir+"]" + ":" + l_size); 
			 
		} 
		 
	  public static void Test_simpleOne() 
	  { 
		  String path = "e:\\t\\update\\update_2109.txt"; 
		 
		  File file = new File (path); 
		 
		 
		  Date filter_dt = DateUtil.to_date_from_datetime("21.09.2017 15:31:01") ; 
			 
		  long msc_filter = filter_dt.getTime()/1000; 
			 
		 
		  Date file_dt = DateUtil.date_from_file_stamp(file.lastModified()); 
			 
		  long msc_file = file_dt.getTime()/1000; 
		 
		  if(file_dt.equals(filter_dt)) 
			  gl.tracex(new Object(){},"Equal"); 
		  else if(file_dt.before(filter_dt)) 
			  gl.tracex(new Object(){},"Before"); 
		  else if(file_dt.after(filter_dt)) 
			  gl.tracex(new Object(){},"After"); 
		 
		 
		  String msg = String.format("File_date : %s %d filter_date %s %d ",file_dt,msc_file,filter_dt,msc_filter); 
		 
		  gl.tracex(new Object(){},msg); 
		 
	  } 
		 
	 
	  public static void testBakName(String path) 
	  { 
		  gl.tracex(new Object(){},String.format("Input path : %s Rename path : %s ",path,Fu.getBakName(path,"bak"))); 
		 
	  } 
	 
	  public static String getFilesHeadersInDir(String input_dir,String[] mask) 
	  { 
		List<File> list = Fu.getFilesByMask(input_dir, mask); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(a-> 
		{ 
			String pure_name = Fu.get_pure_file_name(a.getAbsolutePath()); 
			 
			sb.append(pure_name); 
			 
			sb.append(System.lineSeparator()); 
			 
		} 
		); 
		 
		 
			return sb.toString(); 
		 
	  } 
	 
	  public static List<String> getFilesHeadersInDirToList(String input_dir,String[] mask) 
	  { 
		List<File> list = Fu.getFilesByMask(input_dir, mask); 
		 
		List<String> pure_list = new ArrayList<String>(); 
		 
		list.forEach(a-> 
		{ 
			String pure_name = Fu.get_pure_file_name(a.getAbsolutePath()); 
			 
			pure_list.add(pure_name); 
			 
		} 
		); 
		 
			return pure_list; 

		 
	  } 
	 
	 
	 

	  public static String[] getStringArrayFromFile(String source) 
	  { 
		  String data = ""; 
		 
		  try { 
			 
			  data = Fu.get_str_from_file(source); 
			 
			  return data.split(System.lineSeparator()); 
			 
		} catch (IOException e) { 
			 
				return null; 
		} 
		  		 
		 
	  } 
	 

	  public static ArrayList<String> getSqlList(String sql_template,ArrayList value) { 
			 
		String sql = ""; 

		ArrayList<String> list = new ArrayList<String>(); 

		for (int i = 0; i < value.size(); i++) { 
			 
			String s = (String) value.get(i); 

			sql = String.format(sql_template, s); 

			list.add(sql); 
		} 

		gl.tracex(new Object(){},"<gl.getSqlList()> Loaded : " + list.size()); 

		return list; 

	} 

	  /* 
	  public static Map<Integer,TableInfo> get_sql_list(String sql_template,ArrayList names) { 
			 
			String sql = ""; 

			Map<Integer,TableInfo> map = new LinkedHashMap<Integer,TableInfo>(); 

			for (int i = 0; i < names.size(); i++) { 
				 
				String s = (String) names.get(i); 

				sql = String.format(sql_template, s); 

				TableInfo ti = new TableInfo(i,sql,s); 
				 
				map.put(i,ti); 
				 
			} 

			gl.tracex(new Object(){},"<gl.getSqlList()> Loaded : " + map.size()); 

			return map; 

		} 

*/ 
	 
		public static String get_date_time_template() 
		{ 
			 
			Calendar now = GregorianCalendar.getInstance(); 
			 
			java.util.Date creationDate = now.getTime(); 
			 
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss"); 
			 
			String s_date_time = df.format(creationDate); 
			 
			return s_date_time; 

		} 

		public static String get_type_of_form_file_old(String source) 
		{ 
			if(source.contains("class=") && 
			   source.contains("<?xml") && 
			   source.contains("version=") && 
			   source.contains("<") && 
			   source.contains(">")) 
				return "xml"; 
			if(source.contains(";") && source.contains("=")) 
				return "txt"; 
			else 
				return "none"; 
					 
		} 
		 
		
		 
		public static void Test_testSimpleDateConvertFromString(String  sdf,String value) 
		{ 
			SimpleDateFormat  sf = new SimpleDateFormat(sdf); 
			 
			Date dt = null; 
			 
			try { 
				dt =  sf.parse(value); 
				 
			} catch (ParseException e) { 
				 
				gl.tracex(new Object(){},"Error : " + e.toString()); 
				 
				return; 
			} 
					 
			gl.tracex(new Object(){},String.format("Input format :%s  value : %s output : %s",sdf,value,DateUtil.to_string(dt,"."))); 
			 
			 
		} 
	 
		public static void Test_testCompareDate(String date_one,String date_two) 
		{ 
			Date ds = DateUtil.to_date(date_one); 
			 
			Date dt = DateUtil.to_date(date_two); 
			 
			gl.tracex(new Object(){},String.format("Left : %s %s Right : %s",DateUtil.to_string(ds),ds.compareTo(dt),DateUtil.to_string(dt))); 
			 
				 
		} 
		 
		public static void Test_isa(String path) 
		{ 
				gl.tracex(new Object(){},String.format("File : %s  exists : %s",path,Fu.isaFile(path))); 
		} 

		public static void Test_testFilters(String dt,String cond) 
		{ 

			String path = "e:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\"; 

			List<File> files = getFilesInDirRecByDateFilter(path,new String[]{"java"},dt,cond); 
			 
			gl.tracex(new Object(){},String.format("Files count : %s date %s cond %s", files.size(),dt,cond)); 
			 
			files.forEach(a->{ 
				 
				gl.tracex(new Object(){},a.getAbsolutePath() +" " + DateUtil.getDateStampFile(a.lastModified())); 
				 
			}); 
			 
		} 
		 
		public static void Test_testFiltersWithCopy(String dt,String cond,String target_dir) 
		{ 

			String path = "e:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\"; 

			List<File> files = getFilesInDirRecByDateFilter(path,new String[]{"java"},dt,cond); 
			 
			gl.tracex(new Object(){},String.format("Files count : %s date %s cond %s", files.size(),dt,cond)); 
			 
			files.forEach(a->{ 
				 
				gl.tracex(new Object(){},a.getAbsolutePath() +" " + DateUtil.getDateStampFile(a.lastModified())); 
				 
				try { 
					 
					String source = Fu.get_str_from_file(a.getAbsolutePath()); 
					 
					String fileName = Fu.get_file_name(a.getAbsolutePath()); 
					 
					String target_path = String.format("%s\\%s",target_dir,fileName); 
					 
					 
					if (!Fu.to_file(target_path,source,false)) 
						gl.tracex(new Object(){},String.format("Error while write to file :%s",target_path)); 
					else 
						gl.tracex(new Object(){},String.format("Ok while write to file :%s",target_path)); 
					 
				} catch (Exception e) { 
					 
					return; 
				} 
				 
				 
				 
			}); 
			 
		} 
		 
		 
	 public static  void createCopyFilesScriptByMask(String path,String[] mask,String cmd_file,String target_dir) 
	 { 
		 
		 if (Fu.isaFile(cmd_file)) 
			 
		 	if(!Fu.deleteFile(cmd_file)) 
		 	{ 
		 		gl.tracex(new Object(){},"Error while delete : " + cmd_file); 
		 		 
		 		return; 
		 	} 
		 
		 Logger log = new Logger(cmd_file); 
		 
		 
		 try { 
			showFilesInDirRecToLogger(new File(path),mask,log,target_dir); 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},"IOException : " + e.toString()); 
			 
			return ; 
		} 
		 finally 
		 { 
			 
		 } 
		 
		 
	 } 
	 
	 public static String getInjectedFileName(String source_file,String ticker,Range range) 
	 { 
		 String path = Fu.get_path(source_file); 
		 
		 String file_name = Fu.get_file_name(source_file); 
		 
		 String target = String.format("%s\\%s.%s.%s",path,ticker.trim(),range.toString(),file_name); 
		 
		 return target; 
		 
	 } 
	 
	 public static String getInjectedFileName(String source_file,String ticker,String template) 
	 { 
		 String path = Fu.get_path(source_file); 
		 
		 String file_name = Fu.get_file_name(source_file); 
		 
		 String target = String.format("%s\\%s.%s.%s",path,ticker.trim(),file_name,template); 
		 
		 return target; 
		 
	 } 
	 
	 public static String getCurrentDir() 
		{ 
			File file = new File("."); 
			 
			return Su.BeforeAt(file.getAbsolutePath(),"."); 
		} 
		 
	 	public static boolean isaQuoteFile(String path) 
	 	{ 
	 
			try { 
	 			 
	 			 String check_field = Su.BeforeAtFirst(Fu.getFileAsOneRow(path),",").trim(); 
	 			 
	 			 boolean bl_result = DateUtil.isa_normal_date_format(check_field); 
	 			 
	 			 if(bl_result) 
	 				gl.tracex(new Object(){}, String.format("%s...check file format...%s", gl.S_OK, 
							path)); 
	 			 else 
	 			 gl.tracex(new Object(){}, String.format("%s...check file format...%s",gl.S_ERROR, 
	 							path)); 

	 			 return  bl_result; 
	 			 
			} catch (IOException e) { 

		 		gl.tracex(new Object(){}, String.format("%s...check file format...%s",gl.S_ERROR, 
						path)); 
		 		 
		 		DialogUtil.doError(String.format("File %s is not quote file",path)); 

				return false; 
			} 
	 		 
	 	} 
	 	 
	 	public static boolean isaCompositeFile(String path) 
	 	{ 
		 		try { 
					return Fu.getFileAsOneRow(path).startsWith("pid"); 
				} catch (IOException e) { 
					return false; 
				} 
		} 
	 	 
	 	 
	  public static boolean regexp_prev(String file_source,String strob,String filter) 
	  { 
		  if(!Fu.isaFile(file_source)) 
		  { 
			  gl.tracex(new Object(){},String.format("File is not exist...%s...%s",file_source,gl.E_ERROR)); 
			 
			  return false; 
		  } 
		 
		   List<String> list = Fu.get_list_from_file(file_source); 
		 
		   gl.tracex(new Object(){},String.format("Load from...%s...%d...rows.",file_source,list.size(),gl.E_OK)); 
		 
		    StringBuilder sb = new StringBuilder(); 
		 
		    StringBuilder sbr = new StringBuilder(); 
		 
		    String header = "(C) Copyright Corporate Data Model Development Department (CDMDD), SberBank Ukraine, v.30102018.\n\n\n"; 
		 
		    sbr.append(header); 
		 
		 
		    int count[] = {gl.E_OK,gl.E_OK}; 
		 
		    sbr.append(String.format("%05d...%s...%05d\n\n",count[0],gl.replicate('-',80),count[0])); 
		 
		    count[0]++; 
		 
		    boolean []  add_to_result = {false}; 
		 
		    list.forEach(a-> 
		    { 
		    	if(a.indexOf(strob.toLowerCase()) != gl.E_ERROR || a.indexOf(strob.toUpperCase()) != gl.E_ERROR  ) 
		    	{ 
		    		 
		    		// Show cash. 
		    		 
		    		String temp = sb.toString(); 
		    		 
		    		//gl.smn(temp); 
		    		 
		    		if(add_to_result[0]) 
		    		{ 
		    			sbr.append(temp); 
		    			 
		    		    sbr.append(String.format("%05d...%s...%05d\n\n",count[0],gl.replicate('-',80),count[0])); 
		    			 
		    			count[0]++; 
		    			 
		    		} 
		    		 
		    		 
		    		 
		    		// Check filter values. 
		    		 
		    		add_to_result[0] = false; 
		    		 
		    		//for(int i=0;i < filter.length;i++) 
		    		//{ 
		    			if (temp.indexOf(filter) != gl.E_ERROR) 
		    			{ 
		    				add_to_result[0] = true; 
		    				 
		    				count[1]++; 
		    			} 
		    			 
		    			 
		    		//}// end for. 
		    		 
		    		// Add to output buffer. 
		    		 
		    		 
		    		// Clear cash. 
		    		 
		    		sb.setLength(gl.E_EMPTY); 
		    	} 
		    	 
		    	else 
		    	{ 
		    		sb.append(a); 
		    		 
		    		sb.append(System.lineSeparator()); 
		    	}	 
		    		    	 
		    	 
		    } 
		    ); 
		 
		   gl.tracex(new Object(){},String.format("Count global...%05d...detail...%05d",count[0],count[1])); 
		    		 
		   // Output file. 
		 
		   String output_file_name = String.format("%s\\%s.%s",Fu.get_path(file_source),Fu.get_pure_file_name(file_source),filter); 
		 
		   //gl.smn("--->Output file name : " + output_file_name); 
		 
		   Logger log = new Logger(output_file_name,false); 
		 
		   log.awrite(sbr.toString()); 
		 
		   sbr.setLength(gl.E_EMPTY); 
		 
		   return true; 
		 
	  } 
	 
	 	 
	  public static boolean regexp_fresh(String file_source,String strob,String[] filter) 
	  { 
		  if(!Fu.isaFile(file_source)) 
		  { 
			  gl.tracex(new Object(){},String.format("File is not exist...%s...%s",file_source,gl.E_ERROR)); 
			 
			  return false; 
		  } 
		 
		   List<String> list = Fu.get_list_from_file(file_source); 
		 
		   gl.tracex(new Object(){},String.format("Load from...%s...%d...rows.",file_source,list.size(),gl.E_OK)); 
		 
		    StringBuilder sb = new StringBuilder(); 
		 
		    StringBuilder sbr = new StringBuilder(); 
		 
		    String header = "(C) Copyright Corporate Data Model Development Department (CDMDD), SberBank Ukraine, v.30102018.\n\n\n"; 
		 
		    sbr.append(header); 
		 
		 
		    int count[] = {gl.E_OK,gl.E_OK}; 
		 
		    //sbr.append(String.format("%05d...%s...%05d\n\n",count[0],gl.replicate('-',80),count[0])); 
		 
		    //count[0]++; 
		 
		    List<String> items = new ArrayList<String>(); 
		 
		    String [] a_prev = {""}; 
		 
		    list.forEach(a-> 
		    { 
		    	if(a.indexOf(strob.toLowerCase()) != gl.E_ERROR || a.indexOf(strob.toUpperCase()) != gl.E_ERROR  ) 
		    	{ 
		    		 
		    		// Show cash. 
		    		 
		    		String temp = sb.toString(); 
		    		 
		    		int pos = gl.E_ERROR; 
		    		 
		    		for(int i=0;i<filter.length;i++) 
		    		{ 
		    			pos = temp.indexOf(filter[i]); 
		    		} 
		    				    		 
		    		if (pos != gl.E_ERROR) 
	    			{ 
		    			 
			    		count[1]++; 
		    			 
		    			sbr.append(sb.toString()); 
		    			 
		    			String look_at = "("; 
		    			 
		    			String sb_string = a_prev[0]; 
		    			 
		    			sb_string = sb_string.replaceAll("\r\n"," "); 
		    					 
		    			if(strob.trim().equalsIgnoreCase("view")) 
		    			{ 
		    				look_at = " as "; 
		    			} 
		    			 
		    			String pure = Su.AfterAt(Su.BeforeAtFirst(sb_string,look_at)," "); 
			    		 
		    			if(strob.trim().equalsIgnoreCase("view")) 
		    			{ 
		    				 
		    				pure = a.replaceAll("CREATE","").replaceAll("REPLACE","").replaceAll(" VIEW","").trim(); 
		    			 
		    				pure = a.replaceAll("CREATE VIEW","").trim(); 
		    				 
		    				pure = a.replaceAll("REPLACE VIEW","").trim(); 
		    				 
		    				pure = a.replaceAll("create","").replaceAll("replace","").replaceAll("view","").trim(); 
			    			 
		    				pure = Su.BeforeAtFirst(pure," AS"); 
		    				 
		    				pure = Su.BeforeAtFirst(pure," as"); 
		    				 
		    				pure = Su.BeforeAt(pure,"("); 
		    				 
		    				 
		    				pure = Fu.replaceAll(pure,new String[]{"CREATE"}); 
		    				 
		    			} 
		    					    			 
			    		if(!items.contains(pure.trim())) 
			    			items.add(pure); 
			    		 
		    			pos = gl.E_ERROR; 
		    		} 
		    		 
		    			sb.setLength(gl.E_EMPTY); 
		    			 
		    			temp = ""; 
		    			 
		    			a_prev[0] = a; 
		    		 
		    	} 
		    	 
		    			sb.append(a); 
		    		 
		    			sb.append(System.lineSeparator()); 
		    	 
		    } 
		    ); 
		 
		   gl.tracex(new Object(){},String.format("Count global...%05d...detail...%05d",count[0],count[1])); 
		    		 
		   // Output file. 
		 
		   String output_file_name_raw = String.format("%s\\%s.raw",Fu.get_path(file_source),Fu.get_pure_file_name(file_source)); 
		 
		   String output_file_name = String.format("%s\\%s.report",Fu.get_path(file_source),Fu.get_pure_file_name(file_source)); 
		 
		   Logger raw_log = new Logger(output_file_name_raw); 
		 
		   raw_log.awrite(header); 
		 
		   raw_log.awrite(sbr.toString()); 
		 
		   sbr.setLength(gl.E_EMPTY); 
		 
		   Logger log = new Logger(output_file_name); 
		 
		 
		   List<String> list_result = items.stream().sorted(Comparator.comparing(n->n.toString())).collect(Collectors.toList()); 
		 
		   list_result.forEach(a->{ 
			 
			   String msg = String.format("%03d.%s ",count[0],a); 
					 
			   gl.smn(msg); 
			 
			   log.awrite(msg); 
			 
			   count[0]++; 
				  			 
		   }); 
		  		 
		   return true; 
		 
	  } 
	 
	  public static String replaceAll(String source,String[] filter) 
	  { 
		  String[] arr = source.split(" "); 
		 
		  if(arr.length == gl.E_OK) 
			  return source; 
		 
		  String result = ""; 
		 
		  for(int i=0; i< arr.length;i++) 
		  { 
			  for(int j=0; j< filter.length;j++) 
			  { 
				  if(!arr[i].equalsIgnoreCase(filter[j])) 
					  result = arr[i]; 
			  } 
		  } 
		 
		  		return result; 
	  } 
	 
	  public static boolean regexp_prev1(String file_source,String strob,String filter) 
	  { 
		  if(!Fu.isaFile(file_source)) 
		  { 
			  gl.tracex(new Object(){},String.format("File is not exist...%s...%s",file_source,gl.E_ERROR)); 
			 
			  return false; 
		  } 
		 
		   List<String> list = Fu.get_list_from_file(file_source); 
		 
		   gl.tracex(new Object(){},String.format("Load from...%s...%d...rows.",file_source,list.size(),gl.E_OK)); 
		 
		    StringBuilder sb = new StringBuilder(); 
		 
		    StringBuilder sbr = new StringBuilder(); 
		 
		    String header = "(C) Copyright Corporate Data Model Development Department (CDMDD), SberBank Ukraine, v.30102018.\n\n\n"; 
		 
		    sbr.append(header); 
		 
		 
		    int count[] = {gl.E_OK,gl.E_OK}; 
		 
		    sbr.append(String.format("%05d...%s...%05d\n\n",count[0],gl.replicate('-',80),count[0])); 
		 
		    count[0]++; 
		 
		    list.forEach(a-> 
		    { 
		    	if(a.indexOf(strob.toLowerCase()) != gl.E_ERROR || a.indexOf(strob.toUpperCase()) != gl.E_ERROR  ) 
		    	{ 
		    		 
		    		// Show cash. 
		    		 
		    		String temp = sb.toString(); 
		    		 
		    		int pos = temp.indexOf(filter); 
		    				    		 
		    		if (pos != gl.E_ERROR) 
	    			{ 
	    				sbr.append(temp); 
		    			 
		    		    sbr.append(String.format("%05d...%s...%05d\n\n",count[0],gl.replicate('-',80),count[0])); 
		    			 
		    			count[1]++; 
		    			 
		    					    			 
		    			 
		    		} 
		    		 
		    			sb.setLength(gl.E_EMPTY); 
		    		 
		    	} 
		    	 
		    		sb.append(a); 
		    		 
		    		sb.append(System.lineSeparator()); 
		    	 
		    } 
		    ); 
		 
		   gl.tracex(new Object(){},String.format("Count global...%05d...detail...%05d",count[0],count[1])); 
		    		 
		   // Output file. 
		 
		   String output_file_name = String.format("%s\\%s.%s",Fu.get_path(file_source),Fu.get_pure_file_name(file_source),filter); 
		 
		   //gl.smn("--->Output file name : " + output_file_name); 
		 
		   Logger log = new Logger(output_file_name,false); 
		 
		   log.awrite(sbr.toString()); 
		 
		   sbr.setLength(gl.E_EMPTY); 
		 
		   return true; 
		 
	  } 
	 
	  public static void test_regexp() 
	  { 
		  String input =  "Hello java java2 java3 java45"; 
		 
		 
		  //Pattern pat = pat.compile("java33(\\w*)"); 
		 
		  //Pattern pat =  Pattern.compile("(Hello)(\\w*)"); 
		 
		  Pattern pat =  Pattern.compile("Hello(\\w*)"); 
		 
		  // *.(2625)|(2605)|(2655)|(2650)|(2600)|(2620).* 
		 
		 
		  Matcher mat = pat.matcher(input); 
		 
		  boolean found = mat.matches(); 
		 
		  if(found) 
			  gl.smn("Match"); 
		  else 
			  gl.smn("not Match"); 
		 
	  } 
	 
	  public static void Test_regexp() 
	  { 
		  //String source = String.format("%s\\data\\etl\\%s", FileUtil.getCurrentDir(),"proc_ddl.log"); 
		 
		  String [] types = new String[]{"procedure","function","macro","view"}; 
		 
		  List<String> list_types = Arrays.asList(types); 
		 
		  String [] filter = new String[]{"2625","2605","2655","2650","2600","2620"}; 
		 
		  list_types.forEach(t->{ 
			 
			  String source = String.format("%s\\data\\etl\\%s_ddl.log", Fu.getCurrentDir(),t); 
		 
			  if(Fu.regexp(source,String.format(" %s ",t),filter)) 
				  gl.tracex(new Object(){},String.format("Test...%s",gl.S_OK)); 
			  else 
				  gl.tracex(new Object(){},String.format("Test...%s",gl.S_ERROR)); 
			 
		  }); // types. 
		 
		 
	  } 
 
	 
	 
	  public static int files_count = gl.E_EMPTY; 
	 
	  public static long l_size = 0L; 
	 
	  public static String prevDir  =  ""; 
	 
	  public static String curDir  =  ""; 
	 
	 

	  public static boolean regexp(String file_source,String strob,String[] filter) 
	  { 
		  if(!Fu.isaFile(file_source)) 
		  { 
			  gl.tracex(new Object(){},String.format("File is not exist...%s...%s",file_source,gl.E_ERROR)); 
			 
			  return false; 
		  } 
		 
		   List<String> list = Fu.get_list_from_file(file_source); 
		 
		   List<String> items = new ArrayList<String>(); 
		 
		   List<String> result_list = new ArrayList<String>(); 
		 
		   String[] a_header = {"",""}; 
		 
		   int [] count = {1}; 
		 
		   String s_strob_create = String.format("%s%s","create",strob); 
		 
		   String s_strob_replace = String.format("%s%s","replace",strob); 
		 
		 
		   String detail_log = String.format("%s\\%s.detail",Fu.get_path(file_source),Fu.get_pure_file_name(file_source)); 
		 
		   Logger log_detail = new Logger(detail_log); 
		 
		 
	 
		 
		 
		   list.forEach(a-> 
		    { 

		    	if( 
		    	    a.toUpperCase().indexOf(s_strob_create.toUpperCase()) != gl.E_ERROR  || 
		    	    a.toUpperCase().indexOf(s_strob_replace.toLowerCase()) != gl.E_ERROR 
			) 
		    	{ 
		    		 

	    			a_header[1] = a_header[0]; 
		    		 
		    		a_header[0] = a; 
		    		 
		    		//gl.smn(String.format("Prev...%s...Current...%s",a_header[1],a_header[0])); 

		    		String msg = String.format("---> Current header...%s...Previous...%s",a_header[0].trim(),a_header[1]); 
				 
				log_detail.awrite(msg); 

		    			    		 
		    	} 
		    	 
		    	 
		    		for(int i=0;i<filter.length;i++) 
		    		{ 
		    			int pos = a.indexOf(filter[i]); 
		    			 
		    			if(pos != gl.E_ERROR) 
		    			{ 
		    				 
		    				items.add(a_header[0]); 
		    				 
		    				String msg = String.format("%s...[%s]..by filter...%s",a_header[0].trim(),a,filter[i]); 
		    				 
		    				log_detail.awrite(msg); 
		    				 
		    			} 
		    		} 
		    		 
		    	 
		    	 
		    			    	 
		    } 
		    ); 
		 
		   String output_file_name = String.format("%s\\%s.report",Fu.get_path(file_source),Fu.get_pure_file_name(file_source)); 
		 
		   Logger log = new Logger(output_file_name); 
		 
		 
		 
		   items.forEach(a->{ 
			 
			   String pure =a 
					    .replace("CREATE","") 
					    .replace("create","") 
					    .replace("Create","") 
					    .replace("view","") 
					    .replace("VIEW","") 
					    .replace("PROCEDURE","") 
					    .replace("procedure","") 
					    .replace("MACRO","") 
					    .replace("macro","") 
					    .replace("FUNCTION","") 
					    .replace("function","") 
					    .trim(); 
			 
			  if(strob.trim().equalsIgnoreCase("view")) 
			  { 
			   pure = Su.BeforeAtFirst(pure," "); 
			  } 
			 
			  //Common section. 
			  if(a.indexOf("(") != gl.E_ERROR) 
			  { 
			   pure = Su.BeforeAtFirst(pure,"("); 
			  } 
			 
			  			 
			  if(pure.trim().length() != gl.E_EMPTY) 
			  { 
				  if(!result_list.contains(pure)) 
				  { 
					  result_list.add(pure); 
				  } 
				 
			 
			 
			  } 
				  			 
		   }); 
		 
		   // Save to report. 
		 
		   List<String> list_result = result_list.stream().sorted(Comparator.comparing(n->n.toString())).collect(Collectors.toList()); 
		 
		   list_result.forEach(a->{ 

			   String msg = String.format("%03d.%s ",count[0],a); 
			 
			   gl.smn(msg); 
				 
			   log.awrite(msg); 
			 
			   count[0]++; 
				 
		   }); 
		 
		 
		 
		   		return true; 
		 
	  } 


public static String getFileAsStringSkipFirstRow(String path) 
	{ 
		List<String>  list = Arrays.asList(get_str_array_from_file(path)); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		int [] index = {0}; 
		 
		list.forEach(a-> 
		{ 
			if(index[0] > 1) 
			sb.append(a); 
			 
			index[0]++; 
			 
		}); 
		 
		return sb.toString(); 
				 
	} 


	public static Logger get_log_file(String file_name,boolean mode) 
	{ 
		 
		String log_file = String.format("%s%s",Fu.getCurrentDir(),file_name); 
		 
		return new Logger(log_file,mode); 
		 
	} 
	 
	public static Logger get_log_file(String file_name) 
	{ 
		return get_log_file(file_name,false);	 
	} 
	 
	
	public static String file_save_dlg(String home) { 
 
	JFrame parentFrame = new JFrame();
	 
	FileChooser 	fileChooser = new FileChooser();
	
					fileChooser.setCurrentDirectory(new File(Application.getCio().getHome())); 
	
					fileChooser.setDialogTitle("Укажите название файла для сохранения");   
	 
	int userSelection = fileChooser.showSaveDialog(null);
	 
	if (userSelection == JFileChooser.APPROVE_OPTION) {
	    
		File fileToSave = fileChooser.getSelectedFile();
	  	
		gl.tx_k(new Object(){},gl.sf("Файл для записи...[%s]",fileToSave.getAbsolutePath())); 
	    
	    return fileToSave.getAbsolutePath();
	}
	
		return null;
	
	}
	
	
	public static String file_save_dlg_prev(String home) { 

		String msg = "Save to file"; 
				 
		JFileChooser save_file_chooser = new JFileChooser(); 

		String default_directory = Fu.get_path(new File(home).getAbsolutePath()); 

		save_file_chooser.setCurrentDirectory(new File(default_directory)); 
		
		gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,default_directory,gl.S_TRY)); 

		if (save_file_chooser.showSaveDialog(Application.getDio().get_desk_top()) == JFileChooser.APPROVE_OPTION) { 
			 
			File file = save_file_chooser.getSelectedFile(); 

			String target_file = file.getAbsolutePath(); 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,target_file,gl.S_OK)); 

			return target_file; 
		} 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,"cancel",gl.S_ERROR)); 
			 
			return null; 

	} 
		 
	public static String file_open_dlg(String home) { 
		 
		try
		{
			
		
		String def_dir = Fu.get_path(new File(home).getAbsolutePath()); 

		JFileChooser 	fo = new JFileChooser(); 

						fo.setCurrentDirectory(new File(def_dir)); 
						
		int i_result = fo.showOpenDialog(null); 

		if (i_result != JFileChooser.APPROVE_OPTION) { 
			 
			return null; 

		} 

			File 			selected_file = fo.getSelectedFile(); 

			return 			selected_file.getAbsolutePath(); 

		}
		catch(Exception e)
		{
			return null;
		}
		
	}// fileOpenDlg 
	 
	
	public static boolean isa_abs_path(String path)
	{
		return (path.contains("\\") || path.contains(":") || path.contains("//")); 
	}
	
	public static List<String> files_open_dlg(String home) { 

		String msg = "Open files"; 
		 
		String def_dir = Fu.get_path(new File(home).getAbsolutePath()); 

		JFileChooser 	fo = new JFileChooser(); 

						fo.setCurrentDirectory(new File(def_dir)); 
						
						fo.setMultiSelectionEnabled(true); 

		if (fo.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) { 
			 
			return null; 
		} 

		File 	[]		selected_files = fo.getSelectedFiles(); 

		List<String> 	m_result = new ArrayList<String>();
		
		for (File file : selected_files)
		{
			m_result.add(file.getAbsolutePath());
		}
		
			return 			m_result; 

		
	}// files OpenDlg 
	 
	public static boolean import_image_dlg(PanelXml owner, boolean fit_to_image,String home) { 
		 
		Object v = new Object() {}; 

		String fabula = "Import image "; 

		gl.tracex(v, String.format("%s...%s", fabula, "init")); 

		String fileName = Fu.file_open_dlg(home); 

		if (fileName == null) { 
			gl.tracex(v, String.format("%s...%s", fabula, "not selected")); 

			return false; 
		} 

		return Bau.imp_buf_img_as_is(owner, fileName, fit_to_image); 
	} 

	public static boolean import_image_dlg(AtOm owner, boolean fit_to_image,String home) { 
		 
		Object 		v = new Object() {}; 

		String 		msg = "Import image "; 

					gl.tx_k(v, gl.sf("%s...%s", msg, "init")); 

		String 		file_name = Fu.file_open_dlg(home); 

		if (file_name == null) { 
			gl.tx_k(v, gl.sf("%s...%s", msg, "not selected")); 

					return false; 
		} 

					return Bau.imp_buf_img_as_is(owner, file_name, fit_to_image); 
	} 
	
	public static boolean import_image_dlg(Orion owner, boolean fit_to_image,String home) { 
		 
		String fileName = Fu.file_open_dlg(home); 

		if (fileName == null) { 
			gl.tx_e(new Object() {}, gl.sf("%s","File not selected")); 

			return false; 
		} 

		return Bau.imp_buf_img_as_is(owner, fileName, fit_to_image); 
	} 
	
	
	
	public static boolean to_file_list_x(String fileName, List<String> rows) {

        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file);
             
        		OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
     
        		BufferedWriter writer = new BufferedWriter(osw)
        ) {

            for (String line : rows) {
                
            	writer.append(line);
                
                writer.newLine();
            }

            return true;
            
        } catch (IOException e) {
            
        	return gl.tx(new Object() {},false, gl.sf("[%s]",e.toString().substring(0,32))); 

        }
    }
	
	public static boolean to_file_x(String fileName, String data,boolean append) {

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                
        	if(append)
        	writer.append(data);
        	else
        	writer.write(data);
     
        	
        	return true;
        
        }
            	
        catch (IOException e) {
        	
        	e.printStackTrace();

        	return false;
        }

    }	
	
	
	 public static void test_recursive_files(String path)
	 {

		   
		 //String path = "/Users/alexplus/eclipse/wsp/Organizer";
		 
		 try {
			
			 List<File> files = getFilesInDirRecursively(new File(path),new String[] {"java"});
			
			 gl.sfn("Файлов...%d",files.size());
			 
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		  

	 }
	 
	 	 public static String read_utf_8_str(String fileName) {

	        Path path = Paths.get(fileName);
	        
	        try {

	        	return Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
	         
	        } catch (IOException e) {
	            
	        	e.printStackTrace();
	        	
	        	return null;
	        }

	    }

	 	 
	 	 public static String read_first_str(String fileName) {

		        Path path = Paths.get(fileName);
		        
		        try {

		        	return Files.lines(path, StandardCharsets.UTF_8).skip(gl.E_EMPTY).findFirst().get();
		        	
		        } catch (IOException e) {
		            
		        	e.printStackTrace();
		        	
		        	return null;
		        }

		    }


	 public static List<String> read_utf_8(String fileName) {

	        Path path = Paths.get(fileName);
	        
	        try {
	
	        	return Files.readAllLines(path, StandardCharsets.UTF_8);
	     
	        } catch (IOException e) {
	       
	        	e.printStackTrace();
	        	
	        	return null;
	        }

	    }

	 
	 public static long get_lines_count(String source){

		 
		 	File file = new File(source);
		 	
		    try (Stream<String> lines = Files.lines(file.toPath())) {
		        
		    	return lines.count();
		        
		    } catch (IOException e) {
				return 0L;
				
			}
	}
	 
	 public static void main(String[] args)  { 
		 		
	  } 

	 
} 

