 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.utils; 

import java.util.Arrays; 
import java.util.List; 

import ap.global.*; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 12 ???? 2016 ?. 9:43:32 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : CmdUtils.java 
* 
*/ 
public class CmdUtils { 

	private String[] data =  null; 
	 
	public void show() { 
		List<String> list = Arrays.asList(this.getData()); 

		list.forEach(obj -> 

		{ 
			gl.smn(obj); 
		} 

		); 

	} 

	 
	public int findEx(String value) 
	{ 
		int index = find(value); 
		 
		if(index != gl.E_ERROR) 
		   index++; 
		 
		if(index >= this.getData().length) 
			index = gl.E_ERROR; 
		 
		if(index!= gl.E_ERROR) 
		{ 
			if ( 
					this.getData()[index].startsWith(":") || 
					this.getData()[index].startsWith("-") || 
					this.getData()[index].startsWith("?") || 
					this.getData()[index].startsWith("<") || 
					this.getData()[index].startsWith(">") 
				) 
				index = gl.E_ERROR; 
		} 
		 
		return index; 
	} 
	 
	public int find(String value) 
	{ 
		 
		if(this.getData() == null || value == null || value.trim().length()== gl.E_EMPTY) 
		{ 
			gl.smn("<find> Error input value"); 
			 
			return gl.E_ERROR; 
			 
		} 
			 
		 
		int i_result = gl.E_ERROR; 
		 
		 
		for(int i=gl.E_EMPTY;i < this.getData().length;i++) 
		{ 
			if(this.getData()[i].trim().equalsIgnoreCase(value.trim())) 
			{ 
				i_result = i; 
				 
				return i_result; 
				 
			} 
			 
			gl.smn(this.getData()[i].trim() + "<>" + value.trim()); 
		} 
				 
			return gl.E_ERROR; 
		 
	} 
	 
	/** 
	 * @return the data 
	 */ 
	public String[] getData() { 
		return data; 
	} 

	/** 
	 * @param data the data to set 
	 */ 
	public void setData(String[] data) { 
		this.data = data; 
	} 

	/** 
	 * 
	 */ 
	public CmdUtils() { 
		 
	} 

	public CmdUtils(String[] value) { 
		 
		this.setData(value); 
	} 
	 
	public static void Test_find(String[] value ,String[] params) 
	{ 
		CmdUtils 	cu = new CmdUtils(value); 
		 
					cu.show(); 
					 
		List<String> list = Arrays.asList(params); 

		list.forEach(obj -> 

		{ 
			int index = cu.find(obj) + 1 ; 
			 
			if(index != gl.E_EMPTY) 
			System.out.println(" Test Ok for value : " + obj + " " + cu.getData()[index]); 
			else 
			System.out.println(" Test Error for value : " + obj ); 
			 
		} 

		);		 
		 
	} 
	 
	public static void Test_getValue(String[] cmd ,String param_name) 
	{ 
		CmdUtils 	cu = new CmdUtils(cmd); 
		 
					cu.show(); 
					 
		//List<String> list = Arrays.asList(cmd); 
		 
		int index =	cu.find(param_name); 
		 
		if(index == gl.E_ERROR) 
		{ 
			gl.smn("Error for param_name : " + param_name); 
			 
			return; 
		} 
				 
		gl.smn(cu.getData()[index+1]); 
		 
	} 
	 
	 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		//Test_find(args , new String[]{"-if","-td","-dlm","-test"}); 
		 
		Test_getValue(new String[]{"-if 2324","-td 2423","-dlm","-test"},"if"); 
		 
		//Test_getValue(new String[]{"-if 2324","-td 2423","-dlm","-test"},"td"); 
		 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
