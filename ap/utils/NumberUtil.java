 
 
/** 
* 
*/ 
package ap.utils; 

import java.awt.Dimension; 
import java.util.Arrays; 
import java.util.List; 

import ap.action.CmdParams; 
import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 21 ???. 2017 ?. 14:37:28 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : NumberUtil.java 
* 
*/ 
public class NumberUtil { 

	 
	public static boolean between(int value, Integer[] int_array) 
	{ 
		List<Integer> list = Arrays.asList(int_array); 
		 
		return list.contains(value); 
		 
	} 
	 
	public static boolean in_range(int value,List<Integer> list) 
	{ 
		 
		return list.contains(value); 
		 
	} 
	 
	 
	public static Dimension get_dimension(int count) 
	{ 
		return get_dimension(gl.sf("%d",count)); 
	} 
	 
	public static Dimension get_dimension(double count) 
	{ 
		return get_dimension(gl.sf("%.4f",count)); 
	} 
	 
	public static Dimension get_dimension(String value) 
	{ 
 
		double  m_sqrt = Math.sqrt(Double.parseDouble(value)); 
		 
		return new Dimension((int)m_sqrt,(int)m_sqrt); 
		 
	} 
	 
	public static Dimension get_dimension(String param1,String param2) 
	{ 
		Dimension dim = new Dimension (DU.to_int(param1),DU.to_int(param2)); 
				 
		if(dim.width==gl.E_ERROR || dim.height == gl.E_ERROR) 
		return null; 
		 
		return dim; 
		 
	} 
	 
	public static void Test_between(int value,Integer[] arr) 
	{ 
		if(between(value,arr)) 
			gl.smn(String.format("Value : d in %s",value,arr)); 
		else 
			gl.smn(String.format("Value : d not in %s",value,arr)); 
		 
	} 
	 
	public static void main(String[] args) { 
	 
		Test_between(1,new Integer[]{1,2,3,4,5}); 
		 
		Test_between(77,new Integer[]{1,2,3,4,5}); 

	} 

} 
// Revision : 10.09.2018 12:49:17 
