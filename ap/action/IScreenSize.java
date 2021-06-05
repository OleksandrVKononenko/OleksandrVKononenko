 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 10 ???. 2020 ?. 13:41:30 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : IScreenSize.java 
* 
*/ 
public interface IScreenSize { 
	 
	public static final List<String> params = Arrays.asList(new String[]{"MIN","MAX","MID","CUR"}); 
	 
	public static int indexOf(String param) 
	{ 
		return params.indexOf(param); 
	} 

	public static String indexOf(int index) 
	{ 
		return params.get(index); 
	} 
	 
	public static String isa(String cmd) 
	{ 
		 return indexOf(indexOf(cmd)); 
	} 

} 
