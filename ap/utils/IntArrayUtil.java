 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.util.ArrayList; 
import java.util.List; 
import ap.global.gl; 

public class IntArrayUtil { 

	 
public static int[] init_int_array(int capacity) 
{ 
	 
	List<Byte> lb = new ArrayList<Byte>(); 
	 
	for(int i=0;i<capacity;i++) 
		lb.add((byte)0); 
	 
	 
	return lb.stream().mapToInt(i->i).toArray(); 
	 
} 
 
public static byte[] init_byte_array(int capacity) 
{ 
	byte [] arr = new byte[capacity]; 
	 
	for(int i=0;i< arr.length;i++) 
		arr[i] = (byte)0; 
	 
	return arr; 
	 
} 
 
public static int parseInt(String value) 
{ 
	int i_result = gl.E_ERROR; 
	 
	try 
	{ 
		i_result = Integer.parseInt(value); 
	} 
	catch(NumberFormatException e) 
	{ 
		return i_result; 
	} 
	 
		return i_result; 
} 

public static String get_keys_sign(int [] key_codes) 
{ 
	StringBuilder sb = new StringBuilder(); 
	 
	for(int i=0;i<key_codes.length;i++) 
	{ 
		String msg = gl.sf("%d",key_codes[i]); 
		 
		sb.append(msg); 
		 
	} 
	 
		String md5 = sb.toString(); //Hash.md5(sb.toString()); 
		 
		return md5; 
} 



} 
