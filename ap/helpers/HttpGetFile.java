 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.helpers; 

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.net.HttpURLConnection; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLConnection; 
import java.security.Permission; 
import ap.global.*; 

public class HttpGetFile { 

	public static void downloadFromUrl(URL url, String localFilename) 
			throws IOException { 

		InputStream is = null; 

		FileOutputStream fos = null; 

		try { 

			URLConnection urlConn = url.openConnection();// connect 

			Permission perm = urlConn.getPermission(); 

			if (perm != null) 
				gl.smn("Permission is not null."); 

			is = urlConn.getInputStream(); // get connection inputstream 

			fos = new FileOutputStream(localFilename); // open outputstream to 
														// local file 

			byte[] buffer = new byte[4096]; // declare 4KB buffer 
			int len; 

			// while we have availble data, continue downloading and storing to 
			// local file 
			while ((len = is.read(buffer)) > 0) { 
				fos.write(buffer, 0, len); 
			} 
		} finally { 
			try { 
				if (is != null) { 
					is.close(); 
				} 
			} finally { 
				if (fos != null) { 
					fos.close(); 
				} 
			} 
		} 
	} 

	public static void downloadFile(String fileURL, String saveDir) 
			throws IOException { 

		URL url = new URL(fileURL); 

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 

		String userPasswd = "akononenko"+":"+"CdeWsxZaq123"; 
		 
		String basicAuth = "Basic "+ javax.xml.bind.DatatypeConverter.printBase64Binary(userPasswd.getBytes()); 
		 
		httpConn.setRequestProperty("Authorization",basicAuth); 
		 
		 
		Permission perm = httpConn.getPermission(); 
		 
		if(perm != null) 
		{ 
			gl.smn("Permission name : " + perm.getName()); 
			 
		} 
		 
		 
		 
		int responseCode = httpConn.getResponseCode(); 

		// always check HTTP response code first 
		if (responseCode == HttpURLConnection.HTTP_OK) { 
			 
			String fileName = ""; 
			 
			String disposition = httpConn.getHeaderField("Content-Disposition"); 
			 
			String contentType = httpConn.getContentType(); 
			 
			int contentLength = httpConn.getContentLength(); 
			 

			if (disposition != null) { 
				// extracts file name from header field 
				int index = disposition.indexOf("filename="); 
				 
				if (index > 0) { 
					fileName = disposition.substring(index + 10, 
							disposition.length() - 1); 
				} 
			} else { 
				// extracts file name from URL 
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, 
						fileURL.length()); 
			} 

			System.out.println("Content-Type = " + contentType); 
			 
			System.out.println("Content-Disposition = " + disposition); 
			 
			System.out.println("Content-Length = " + contentLength); 
			 
			System.out.println("fileName = " + fileName); 

			// opens input stream from the HTTP connection 
			InputStream inputStream = httpConn.getInputStream(); 
			 
			String saveFilePath = saveDir + File.separator + fileName; 

			// opens an output stream to save into file 
			FileOutputStream outputStream = new FileOutputStream(saveFilePath); 

			int bytesRead = -1; 
			 
			byte[] buffer = new byte[1024]; 
			 
			while ((bytesRead = inputStream.read(buffer)) != -1) { 
				outputStream.write(buffer, 0, bytesRead); 
			} 

			outputStream.close(); 
			 
			inputStream.close(); 

			System.out.println("File downloaded"); 
			 
		} else { 
			System.out 
					.println("No file to download. Server replied HTTP code: " 
							+ responseCode); 
		} 
		httpConn.disconnect(); 
	} 

	 
	 
	 
	public static void main(String[] args) { 

		String source = "http://mybank.sbrf.ua.loc/Innovation/DocLib1/???????%20?????????????%20????????????%20?%20??%20?????????%20??????.docx"; 

		try { 
			//downloadFromUrl(new URL(source), "e:\\test.docx"); 
			 
			downloadFile(source, "e:\\"); 
			 
		} catch (MalformedURLException e) { 
			 
			e.printStackTrace(); 
		} catch (IOException e) { 
			 
			e.printStackTrace(); 
		} 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
