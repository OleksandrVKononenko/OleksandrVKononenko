 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.codepage; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 16 ??? 2016 ?. 12:36:49 
* Project  name : Organizer 
* Package  name : ap.codepage 
* File     name : TestCharset.java 
* 
*/ 

import java.util.SortedMap; 
import java.util.Iterator; 
import java.nio.charset.Charset; 

public class TestCharset { 

	public static void main(String[] args) { 

		SortedMap<String, Charset> charsetsMap = Charset.availableCharsets(); 

		System.out.println("Charsets available: " + charsetsMap.size()); 

		for (String name : charsetsMap.keySet()) { 
			 
			Charset charset = charsetsMap.get(name); 
			 
			StringBuffer sb = new StringBuffer(); 
			 
			sb.append(name); 
			 
			sb.append(" ("); 
			 
			for (Iterator<String> aliases = charset.aliases().iterator(); aliases 
					.hasNext();) { 
				sb.append(aliases.next()); 
				if (aliases.hasNext()) 
					sb.append(","); 
			} 
			 
			sb.append(")"); 
			 
			System.out.println(sb.toString()); 
			 
		} 
	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:40 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
