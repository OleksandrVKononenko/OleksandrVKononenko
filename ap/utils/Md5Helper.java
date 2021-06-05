 
 
package ap.utils; 

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 


public class Md5Helper { 
	 
	protected static byte[] data = null; 
	 
	protected static MessageDigest md = null; 
		 
	protected static long Delay = 0; 

	 
	public static String md5a(String pdata) { 
		 
		   if (pdata==null) return null; 
		 
		    long start = System.nanoTime(); 
		 
		    if (md==null) 
				try { 
					md = MessageDigest.getInstance("MD5"); 
				} catch (NoSuchAlgorithmException e) { 

					System.out.println("Exception : " + e.toString()); 
				} 
		 
			 
			data = pdata.getBytes(); 
			 
			md.update(data); 
			 
			byte[] byteData = md.digest(); 
			 
			StringBuffer sb = new StringBuffer(); 
			 
			for(int i=0; i < byteData.length;i++){ 
				sb.append(Integer.toString((byteData[i] &0xff)+0x100,16).substring(1)); 
			} 
				 
			Delay = (System.nanoTime() - start); 
			 
			return sb.toString(); 
		} 


	public static long getDelay() { 
		return Delay; 
	} 

	public static void main(String[] args) { 
		 
		System.out.println("Test md5  : " + Md5Helper.md5a("Test")); 
		 
		 
	} 

} 

