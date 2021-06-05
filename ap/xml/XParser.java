 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.xml; 

import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 01 ???? 2016 ?. 16:00:51 
* Project  name : Organizer 
* Package  name : ap.xml 
* File     name : XParser.java 
* 
*/ 
public class XParser { 

	/** 
	 * 
	 */ 
	public XParser() { 
		// REPLACE Auto-generated constructor stub 
	} 

	public static boolean doMarker(String source) 
	{ 
		for(int i=0; i < source.length();i++) 
		{ 
			char c = source.charAt(i); 
			 
			gl.sm(c); 
			 
			if(c=='<') 
				gl.sm("O"); 
			else if (c=='>') 
				gl.sm("C"); 
			 
			gl.smn(""); 
							 
				 
		} 
		 
		return true; 
		 
		 
	} 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		String value = "<tag1>tagName</tag1>"; 
		 
		if(!doMarker(value)) 
			gl.smn("Error while testing of : " + value); 
			 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
