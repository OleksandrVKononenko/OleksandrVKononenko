 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.ite; 

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStream; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLConnection; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List; 
import java.util.Scanner; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipInputStream; 

import ap.global.*; 
import ap.impexp.Yahoo; 
import ap.log.Logger; 
import ap.utils.Fu; 
import ap.utils.Su; 

public class IteSuite { 

	public static String mask = "%s%shttps://www.forexite.com/free_forex_quotes/20%s/%s/%s%s%s.zip"; 
	 
	public static List<String> items = new ArrayList<String>(); 

	public static int Unzip(String zipFile, String outputFolder) { 

		byte[] buffer = new byte[4096]; 

		int len_sum = 0; 

		try { 

			File file = new File(zipFile); 
			 
			if(file.length() < 10000) 
			{ 
				gl.smn("Delete file : " + zipFile); 
				 
				file.delete(); 
				 
				return gl.E_OK; 
				 
			} 

			String trgFolder = file.getParent(); 

			// create output directory is not exists 
			File folder = null; 

			if (outputFolder != null) { 
				folder = new File(outputFolder); 

				trgFolder = outputFolder; 

				if (!folder.exists()) { 
					folder.mkdir(); 
				} 
			} else 
				folder = new File(trgFolder); 

			// get the zip file content 
			ZipInputStream zis = new ZipInputStream( 
					new FileInputStream(zipFile)); 

			// get the zipped file list entry 
			ZipEntry ze = zis.getNextEntry(); 

			String strFileName = ""; 
			 
			while (ze != null) { 

				String fileName = ze.getName(); 

				strFileName = trgFolder + File.separator + fileName; 
				 
				File newFile = new File(strFileName); 

				System.out.print("Unzip : " + newFile.getAbsoluteFile()); 
				 
				// create all non exists folders 
				// else you will hit FileNotFoundException for compressed folder 
				new File(newFile.getParent()).mkdirs(); 

				FileOutputStream fos = new FileOutputStream(newFile); 

				int len; 

				while ((len = zis.read(buffer)) > 0) { 
					fos.write(buffer, 0, len); 

					len_sum += len; 
				} 
				 
				fos.close(); 

				ze = zis.getNextEntry(); 

			} 

			zis.closeEntry(); 

			zis.close(); 

			System.out.print(" " + (len_sum / 1024) + " kB."); 
			 
			File filen = new File(strFileName); 
			 
			if((filen.length()/1024) < 200) 
			{ 
				gl.sm("...Delete"); 
				 
				filen.delete(); 
			} 
			 
			gl.smn(""); 
			 


		} catch (IOException ex) { 
			return gl.E_EMPTY; 
		} 
		return len_sum; 
	} 

	 
	 
	public static void unzipAll(String outputDir) 
	{ 
		int i = 1; 
		 
		//gl.smn("Count for input : " + items.size()); 
		 
		for(String s : items) 
		{ 
			gl.sm(a(i)+"."); 
			 
			Unzip(s,outputDir); 
			 
			i++; 
		} 
	} 
	 
	public static boolean downloadAll(String outputDir) 
	{ 
	 
		for(String s : items) 
		{ 
			String fileName = outputDir+s.substring(s.lastIndexOf("/")+1,s.trim().length()); 
			 
			try { 
				download(new URL(s),fileName,null); 
			} catch (MalformedURLException e) { 
				return false; 
			} catch (IOException e) { 
				return false; 
			} 
		} 
				return true; 
	} 
	 
	 
	public static void download(URL url, String localFilename,Logger Log) 
			throws IOException { 

		InputStream ins = null; 

		FileOutputStream fos = null; 
		 
		String logDir = ""; 
		 
		boolean bl_remote = false; 
		 
		if(localFilename.contains("\\")) 
		{ 
			logDir = Fu.get_path(Log.getStore()).concat("\\"); 
			 
			bl_remote = true; 
			 
		} 
		 
		Logger errorLog = new Logger(logDir.concat("a_error.list")); 
		 
		String err = ""; 
		 
		try { 

			Thread.sleep(100); 
			 
			URLConnection urlConn = url.openConnection();// connect 

			ins = urlConn.getInputStream(); // get connection inputstream 

			gl.smn("Connection Ok."); 
			 
			fos = new FileOutputStream(localFilename); // open outputstream to 

			byte[] buffer = new byte[4096]; // declare 4KB buffer 

			int len; 

			int len_sum = 0; 
			 
			// while we have available data, continue downloading and storing to 
			// local file 
			int i = 1 ; 

			while ((len = ins.read(buffer)) > 0) { 
				fos.write(buffer, 0, len); 
				 
				len_sum += len; 
				 
				gl.sm("."); 
				 
				if(i==100) 
				{ 
					 
					i = 0; 
					 
					gl.sm(System.lineSeparator()); 
					 
				} 
				 
				 
					i++; 
				 
			} 
					gl.smn(""); 
			 
			String msg = "Download is done. " + localFilename + " Size : " + (len_sum/1024) + " kB.\n"; 

			if(Log != null) 
			Log.write(msg); 
			 
			gl.smn(msg); 
			 
		} 
		catch(Exception e) 
		{ 
			 
			err = "Error while get connection.\n"; 
			 
			gl.smn(err); 
			 
			if(Log != null) 
			Log.write(err); 
			 
			String ticker = ""; 
			 
			if(bl_remote) 
				ticker = Su.BeforeAtPeriod(Fu.get_file_name(localFilename)); 
			else 
				ticker = Su.BeforeAtPeriod(localFilename); 
			 
			errorLog.awrite(ticker); 
			 
			return ; 
		} 
		finally { 
			try { 
				if (ins != null) { 
					ins.close(); 
				} 
			} finally { 
				if (fos != null) { 
					fos.close(); 
				} 
			} 
		} 
	} 

	 
	public static String a(int value) 
	{ 
		return (value < 10) ? "0".concat(""+value) : ""+value; 
	} 
	 
	public static boolean getURLListToFile(String pmask,String outputFile) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		FileWriter writer = null; 
		 
		try { 
			writer = new FileWriter(outputFile, false); 
		} catch (IOException e1) { 
			 
			return false; 
		} 
		 
		@SuppressWarnings("unused") 
		int i = 1; 
		 
		for(int year=1;year<=15;year++) 
		{ 
			for(int month=1;month<=12;month++) 
			{ 
				for(int day=1;day<=31;day++) 
				{ 
					String o = String.format(pmask,"","",a(year),a(month),a(day),a(month),a(year)); 
					 
					sb.append(o); 
					 
					sb.append(System.lineSeparator()); 
										 
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
				return false; 
			} 
			 
			 	return true; 
	} 

	 
	/** 
	 * @param pmask ????? ???? 
	 * @param file  ???? ????????? ??? ??????????? ? ??? 
	 * @return ?????? ????? ? ????????????? ?????????? 
	 */ 
	 
	public static List<String> getURLList(String pmask,String path) 
	{ 
		 
		File file = new File(path); 
		 
		if(!file.exists()) 
		{ 

			gl.smn("Input file is not exist : " + path); 
			 
			return null; 
		} 
		 
		 
		 
		String source; 
		 
		try { 
			source = Fu.get_str_from_file(path); 
			 
			gl.smn("Read source : " + source); 
			 
		} catch (IOException e) { 
			 
			gl.smn("Read source file : " + path + " error : " + e.toString() + " in  dir " + System.getProperty("user.dir"))  ; 
			 
			return null; 
		} 
		 
		if(source.length()==gl.E_EMPTY) 
			return null; 
		 
		String[] arr = source.split(System.lineSeparator()); 
		 
		if(arr.length==gl.E_EMPTY) 
			return null; 
		 
		List<String> items = new ArrayList<String>(); 
		 
		for(int i=0;i<arr.length;i++) 
		{ 
			String value = String.format(pmask,arr[i]); 
			 
			items.add(value); 
		} 
		 
			return items; 
	} 
	 
	 
	public static List<String> getURLList(String pmask,int pmonth_from,int pmonth_to, int pyear_from,int pyear_to) 
	{ 
	 
		List<String> items = new ArrayList<String>(); 
		 
		if(pyear_from < 1 ||  pyear_to > 15) 
		{ 
			gl.smn("< getURLList() > Wrong period of date, years , from " + pyear_from + " or to  " + pyear_to); 
			 
			return null; 
		} 
		 
		if(pmonth_from < 1 ||  pmonth_to > 12) 
		{ 
			gl.smn("< getURLList() > Wrong period of date, month , from " + pyear_from + " or to  " + pyear_to); 
			 
			return null; 
		} 
		 
		for(int year=pyear_from;year<=pyear_to;year++) 
		{ 
			for(int month=pmonth_from;month<=pmonth_to;month++) 
			{ 
				for(int day=1;day<=31;day++) 
				{ 
					String o = String.format(pmask,"","",a(year),a(month),a(day),a(month),a(year)); 
					 
					items.add(o); 
					 
					gl.smn(o); 
					 
				} 
			} 
		} 
		 
				return items; 
	} 
	 
	 
	 
	public static void TestUnzip(String input, String output) { 
		Unzip(input, output); 
	} 
	 
	public static void Test_getURLListToFile(String mask, String output) { 
		 
		getURLList(mask,1,12,13,15); 
		 
	} 
	 
	 
	 
	public static void doDownload(String pmask,int pmonth_from,int pmonth_to, int pyear_from,int pyear_to,String outputDir) 
	{ 
	 
				// Create url list 
				getURLList(pmask,pmonth_from,pmonth_to,pyear_from,pyear_to); 
				 
				//Download of files 
				downloadAll(outputDir); 
				 
	} 
	 
	 
	public static void doUnzip(String inputDir,String outputDir) 
	{ 
	 
		items.clear(); 
		 
		Fu.getFileList(new File(inputDir),items); 
	 
		unzipAll(outputDir); 
		 
		Fu.deleteFirstRow(outputDir); 
		 
	} 
	 
	public static void doMerge(String inputDir,String mrDataFile) 
	{ 
		 
		Fu.mergeFiles(inputDir,mrDataFile); 
		 
	} 
	 
	 
	 
	public static void doTest(int pyear) 
	{ 
		//Download of files 
		String zipDir = "c:\\bin\\explorer\\quotes\\"; 
		 
		doDownload(mask,1,12,pyear,pyear,zipDir); 
		 
		String txtDir = String.format("c:\\bin\\explorer\\txt\\20%s\\",a(pyear)); 
		 
		doUnzip(zipDir,txtDir); 
		 
		String mrgFile = String.format("c:\\bin\\explorer\\merge\\mrData_20%s.txt",a(pyear)); 
		 
		doMerge(txtDir,mrgFile); 
		 
	} 
	 
	 
	public static void downloadSuite(String targetZipDir ,String targetTxtDir ,String targetMergeFile ,int monthFrom ,int monthTo, int yearFrom,int yearTo) 
	{ 
		 
		doDownload(mask,monthFrom,monthTo,yearFrom,yearTo,targetZipDir); 
		 
		doUnzip(targetZipDir,targetTxtDir); 
		 
		doMerge(targetTxtDir,targetMergeFile); 
		 
	} 
	 
	 
	 
	public static void Test_Url_Collector() 
	{ 
		String mask = "http://ichart.finance.yahoo.com/table.csv?s=%s&a=01&b=01&c=1800&d=02&e=10&f=2016"; 
		 
		List<String> items = getURLList(mask,"urls.txt"); 
		 
		String result = items.stream().reduce((t,u)-> t+ "\n" + u).get(); 
				 
		gl.smn("Result: " + result); 
	} 
	 
	public static boolean downloadTickerSuite(String ticker,Date date_from,Date date_to) 
	{ 
		 
		if(ticker.trim().length() == gl.E_EMPTY) 
		{ 
			gl.smn("Ticker value is empty."); 
			 
			return false; 
		} 
		 
		String post_mask = String.format("&a=%s&b=%s&c=%s&d=%s&e=%s&f=%s", 
								Su.getMonthOf(date_from), 
								Su.getDayOf(date_from), 
								Su.getYearOf(date_from), 
								Su.getMonthOf(date_to), 
								Su.getDayOf(date_to), 
								Su.getYearOf(date_to) 
								); 
		 
		String mask = "http://ichart.finance.yahoo.com/table.csv?s=%s".concat(post_mask); 
		 
		String url = ""; 
		 
		String tok = ""; 

		boolean bl_file = ticker.contains(".list"); 
		 
		String logFilename = ""; 
		 
		String strPath = ""; 
		 
		String endLogname = ""; 

		if(bl_file) 
		{ 
			logFilename = Fu.get_file_name(ticker); 
		 
			strPath = Fu.get_path(ticker); 
			 
			endLogname = strPath.concat(Su.BeforeAtPeriod(logFilename)+".log"); 
			 
		} 
		else 
		{ 
			endLogname = String.format("%s.log",ticker); 
		} 
			 
			Logger Log = new Logger(endLogname); 
		 
			 
		try { 
			 
			if(bl_file) 
			{ 
			 
				File file = new File(ticker); 
				 
				if(!file.exists()) 
				{ 
					 
					gl.smn("File not found : " + ticker); 
					 
					return false; 
				} 
				 
				String source = Fu.get_str_from_file(ticker); 
				 
				if(source.length()==gl.E_EMPTY) 
				{ 
					gl.smn("Declare file as param but empty.Error."); 
					 
					return false; 
				} 
				 
				String[] arr = source.split(System.lineSeparator()); 
				 
				String msg = ""; 
				 
				for(int i=0;i<arr.length;i++) 
				{ 
					 
					tok = arr[i].trim(); 
					 
					if(tok.length() != gl.E_EMPTY) 
					{ 
						msg = String.format("%s. Request ticker [%s] ",""+(i+1),tok.toUpperCase(),i+1); 
						 
						gl.smn(msg); 
						 
						Log.write(msg); 
					 
						url = String.format(mask,tok.toUpperCase()); 
					 
						gl.smn("URL : " + url); 
					 
						Log.write(url); 
						 
						download(new URL(url),strPath.concat(tok.toUpperCase().concat(".txt")),Log); 
						 
					} 
				} 
				 
			} 
			else 
			{ 
				url = String.format(mask,ticker.toUpperCase()); 
				 
				gl.smn("URL : " + url); 
				 
				Log.write(url); 

				download(new URL(url),strPath.concat(ticker.toUpperCase().concat(".txt")),Log); 
				 
			} 
			 
		} catch (MalformedURLException e) { 
			 
			gl.smn(e.toString()); 
			 
			return false; 
			 
		} catch (IOException e) { 
			 
			gl.smn(e.toString()); 
			 
			return false; 
		} 
		 
			return false; 
	} 
	 
	public static void Test_Distinct() 
	{ 
		String tstFile = "e:\\bin\\expimp\\orcl44\\dmp\\111115\\mrData_2012.txt"; 
		 
		try { 
			gl.smn(Distinct(tstFile,0)); 
		} catch (IOException e) { 
			 
			e.printStackTrace(); 
		} 
		 
	} 
	 
	public static void Test_SplitToFiles() 
	{ 
		String tstFile = "e:\\bin\\expimp\\orcl44\\dmp\\111115\\mrData_2015.txt"; 
		 
		//String tstFile = "e:\\sql.txt"; 
		 
		//String outputDir = "e:\\split"; 
		 
		String outputDir = "e:\\bin\\expimp\\orcl44\\dmp\\111115\\split\\"; 
		 
		try { 
			splitToFiles(tstFile,0,outputDir); 
		} catch (IOException e) { 
			// REPLACE Auto-generated catch block 
			e.printStackTrace(); 
		} 
		 
	} 
	 
	public static void Test_main() 
	{ 
		 
		// Create url list 
		//getURLList(mask,10,11,13,13); 
		 
		//Download of files 
		String zipDir = "c:\\bin\\explorer\\quotes\\"; 
		 
		//downloadAll(zipDir); 
		 
		//Unzip all 
		 
		Fu.getFileList(new File(zipDir),items); 
		 
		String txtDir = "c:\\bin\\explorer\\txt\\2012\\"; 
				 
		unzipAll(txtDir); 
		 
		Fu.deleteFirstRow(txtDir); 
		 
		String mrgFile = "c:\\bin\\explorer\\merge\\mrData_2012.txt"; 
		 
		Fu.mergeFiles(txtDir,mrgFile); 
		 
	} 
	 
	/** 
	 * @param pathname 
	 * @param pindex 
	 * @return 
	 * @throws IOException 
	 */ 
	public static String Distinct(String pathname , int pindex) throws IOException { 

	 
		 
		  File file = new File(pathname); 
		 
		  String str_distinct =""; 
	 
	      //StringBuilder fileContents = new StringBuilder((int)file.length()); 
	 
	      Scanner scanner = new Scanner(file); 
	 
	      int i = 1 ; 
	      try { 
	          while(scanner.hasNextLine()) { 
	 
	        	  String line = scanner.nextLine(); 
	        	 
	        	  String[] arr = line.split(","); 
	        	 
	        	  if(arr != null && str_distinct.indexOf(arr[pindex].trim()) == gl.E_ERROR) 
	        	  { 
	        		  str_distinct += arr[pindex].trim() + ","; 
	        		 
	        		  gl.smn(i+"."+ arr[pindex].trim()); 
	        		 
	        		  i++; 
	        	  } 
	          } 
	 
	      } finally { 
	          scanner.close(); 
	      } 
	 
	      	return str_distinct; 
	  } 
	 
	/** 
	 * @param pathname 
	 * @param pindex 
	 * @param outputDir 
	 * @throws IOException 
	 */ 
	public static void  splitToFiles(String pathname , int pindex,String outputDir) throws IOException { 

		  File file = new File(pathname); 
		 
		  String str_distinct =""; 
	 
	      Scanner scanner = new Scanner(file); 
	 
	      int i = 1 ; 
	 
	      int j = 1; 
	 
	      try { 
	          while(scanner.hasNextLine()) { 
	 
	        	  String line = scanner.nextLine(); 
	        	 
	        	  String[] arr = line.split(","); 
	        	 
	        	  String findValue = arr[pindex].trim(); 
	        			 
	        	  FileWriter fw = new FileWriter(new File(outputDir+"\\"+ findValue + ".txt"),true); 
	        	 
	        	 
	        	  if(arr != null && str_distinct.indexOf(findValue) == gl.E_ERROR) 
	        	  { 
	        		  str_distinct += arr[pindex].trim() + ","; 
	        		 
	        		  gl.smn("New item :" + i+ "."+ findValue); 
	        		 
	        		  fw.write(line); 
	        		 
	        		  fw.write(System.lineSeparator()); 
	        		 
	        		  i++; 
	        	  } 
	        	  else 
	        	  { 
	        		 
	        		  fw.write(line); 
	        		 
	        		  fw.write(System.lineSeparator()); 

	        	  } 
	        	 
	        	     fw.flush(); 
	        	 
	        	     fw.close(); 
	        	 
	        	     if(j%10000 ==gl.E_EMPTY) 
	        	    	 gl.smn(j); 
	        	 
	        	     j++; 
	          } 
	 
	 
	 
	      } finally { 
	    	 
	          scanner.close(); 
		      } 
	  } 
	 
	 
	 
	/** 
	 * @return 
	 */ 
	public static boolean transformFilesSuite(String dir,String mask) 
	{ 
		List<File> items = new ArrayList<File>(); 
				 
		Path path = Paths.get(dir); 
		 
		Fu.getFilesInDirByMask(path, mask, items); 
		 
		if(items.size()== gl.E_EMPTY) 
		{ 
			gl.smn("No input files in dir : " + dir); 
			 
			return false; 
		} 
		 
		long start_time = System.currentTimeMillis(); 

		  items.forEach( 
				  item->{ 
					 
					  //gl.smn("Processing : " + item.getAbsolutePath()); 
					 
					  Yahoo.process(item.getAbsolutePath()); 
					 
					 
				  }); 
		 
		  long end_time = System.currentTimeMillis(); 
		 
		  gl.smn("Total spending of time : " + (end_time - start_time) + " ms."); 
		 
		  return true; 

		 
	} 
	 
	public static void Test_transformFiles(String dir,String mask) 
	{ 
		transformFilesSuite(dir,mask); 
	} 
	 
	public static boolean mergeFilesSuite(Path path,String mask) 
	{ 
		List<File> items = new ArrayList<File>(); 
		 
		String Brew = Su.getBrew(path.toAbsolutePath().toString().concat("\\")); 
		 
		String targetFile = path.toAbsolutePath().toString().concat(String.format("\\a_%s.txt",Brew)); 
		 
		gl.smn("Target file : " + targetFile); 
				 
		if (!Fu.getFilesInDirByMask(path,mask,items)) 
		{ 
			gl.smn("No Files."); 
			 
			return false; 
		} 
		 
		if(!Fu.mergeFiles(items, targetFile)) 
		{ 
			return false; 
		} 

			return true; 
	} 
	 
	public static void Usage() 
	{ 
		gl.smn("Use :  java -jar prg ticker option"); 
	} 
	 
	public static void Usage_for_Download() 
	{ 
		gl.smn("Use :  java -jar prg ticker option date_from date_to"); 
	} 
	 
	 
	public static void downloadSuite(String[] args) 
	{ 

		 
		String ticker = args[1]; 
		 
		String option = args[0]; 
		 
		// For download mode must be four params 
		// option,ticker,date from, date to. 
		 
		String  date_from = ""; 
		 
		String  date_to	= ""; 
		 
		gl.smn("Processing  ... " + ticker); 
		 
		if(option.equalsIgnoreCase("-d") || option.equalsIgnoreCase("-download")) 
		{ 
			if(args.length != 4) 
			{ 
				Usage_for_Download(); 
				 
				return; 
			} 
			 
			date_from = args[2]; 
			 
			date_to = args[3]; 
			 
			String dotFormat = "dd.mm.yyyy"; 
			 
			String slashFormat = "dd/mm/yyyy"; 
			 
			String defFormat = "dd-mm-yyyy"; 
			 
			String endFormat = ""; 
			 
			if(date_from.indexOf(".") != gl.E_ERROR && date_to.indexOf(".") != gl.E_ERROR) 
				endFormat = dotFormat; 
			else if(date_from.indexOf("/") != gl.E_ERROR && date_from.indexOf("/") != gl.E_ERROR) 
				endFormat = slashFormat; 
			else if(date_from.indexOf("-") != gl.E_ERROR && date_from.indexOf("-") != gl.E_ERROR) 
				endFormat = defFormat; 
			else 
			{ 

				gl.smn(String.format("Error while parsing format of date_from [%s] & date_to [%s]  input params.",date_from,date_to)); 
				 
				return; 
			} 
			 
			gl.smn("Download mode try wake up for action..."); 
			 
			if(downloadTickerSuite(ticker,Su.parseDate(date_from,endFormat),Su.parseDate(date_to,endFormat))) 
				gl.smn("Download Suite successfull completed."); 
			else 
				gl.smn("Download Suite ended with errors."); 
			 
		} 
		else if(option.equalsIgnoreCase("-t") || option.equalsIgnoreCase("-transform")) 
		{ 
			gl.smn("Transform mode."); 
			 
			if(transformFilesSuite(ticker,"*.{txt}")) 
				gl.smn("Ok."); 
				else 
				gl.smn("Error."); 
			 
		} 
		 
		else if(option.equalsIgnoreCase("-m") || option.equalsIgnoreCase("-merge")) 
		{ 
			gl.smn("Merge mode."); 
			 
			if(mergeFilesSuite(Paths.get(ticker),"*.{etl}")) 
				gl.smn("Ok."); 
				else 
				gl.smn("Error."); 
			 
			 
		} 
		 
		 
		else 
		{ 
			Usage(); 
		} 
		 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		downloadSuite(args); 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
