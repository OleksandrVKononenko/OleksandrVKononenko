 
package ap.prop; 

import java.io.IOException; 
import java.io.Serializable;
import java.util.Iterator; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import ap.global.gl;
import ap.utils.Fu; 
import ap.utils.Su; 

public class SProperty implements Serializable { 
	 
	String name; 
	 
	Map<Integer,String> value_map; 
	 
	String DELIMETER  =","; 
	 
	String ATTR_DELIMETER  =";"; 
	 
	String payLoad ; 
	 
	boolean debug = false; 
	 
	 
	 
	 
	public SProperty() 
	{ 
		value_map = new LinkedHashMap<Integer,String>(); 
	} 
	 
	 
	public SProperty(String name) 
	{ 
		this(); 
		 
		this.setName(name.toLowerCase().trim()); 
	} 
	 

	public SProperty(String name,String value) 
	{ 
		this(); 
		 
		this.setName(name); 
		 
		// If value is not consist attr name we must recreate payload. 
		String msg = ""; 
		 
		if(value.indexOf("=")== gl.E_ERROR) 
		{ 
			msg = String.format("%s=%s",this.getName(),value); 
		} 
		if(value.indexOf(";")== gl.E_ERROR) 
		{ 
			msg = String.format("%s%s",msg,this.getDelimeter()); 
		} 
		 
		this.setPayLoad(value); 
		 
		this.setRValue(this.getPayLoad()); 
		 
	} 
	 
	public SProperty(String name,String pvalue,String dlm) 
	{ 
		this(name,pvalue); 
		 
		this.setDelimeter(dlm); 
	} 
	 
	 
	 
	 
	public String getAttr_delimeter() { 
		return ATTR_DELIMETER; 
	} 


	public void setAttr_delimeter(String attr_delimeter) { 
		this.ATTR_DELIMETER = attr_delimeter; 
	} 


	public String getPayLoad() { 
		return payLoad; 
	} 


	public void setPayLoad(String payLoad) { 
		this.payLoad = payLoad; 
	} 


	public boolean setRValue(String value) 
	{ 
		String a[] = new String[]{value}; 
		 
		if(value.contains(this.getDelimeter())) 
		{ 
			a = value.split(this.getDelimeter()); 
			 
			value_map.clear(); 
		} 
		 
		for(int i=0; i < a.length;i++) 
		{ 
			value_map.put(i,a[i].trim()); 
		} 
		 
		if((value_map.size() == gl.E_EMPTY)) 
		{ 
			return false; 
		} 

			return true ; 
			 
	} 
	 
	 
	public boolean isDebug() { 
		return debug; 
	} 


	public void setDebug(boolean debug) { 
		this.debug = debug; 
	} 

 
	public String getDelimeter() { 
		return DELIMETER; 
	} 


	public void setDelimeter(String delimeter) { 
		this.DELIMETER = delimeter; 
	} 


	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 


	public void setValue(Map value) { 
		this.value_map = value; 
	} 
	 
	 

	public Map<Integer, String> getValue_map() { 
		return value_map; 
	} 


	public void setValue_map(Map<Integer, String> value_map) { 
		this.value_map = value_map; 
	} 


	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
	 
		String rvalue = Su.getProperty(value,this.getName(),";"); 
		 
		if(rvalue == null) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s",gl.S_ERROR,"RValue is null",this.getName())); 
			 
			return false; 
		} 

		this.setPayLoad(rvalue); 
			 
		return setRValue(this.getPayLoad()); 
			 
	} 
	 
	 
	@Override	 
	public String toString() 
	{ 
		// Get pair, like this : name=value 
		 
		Iterator it = value_map.entrySet().iterator(); 
			 
		StringBuilder sb = new StringBuilder(); 
		 
		int count = 0; 
		 
		int size = value_map.size(); 
		 
		sb.append(this.getName()); 
		 
		sb.append("="); 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,String> pair = (Map.Entry<Integer,String>)it.next(); 
			 
			int key = pair.getKey(); 
			 
			String value = pair.getValue(); 
			 
			sb.append(value); 
			 
			if(count < (size-1)) 
				sb.append(this.getDelimeter()); 
			 
			   count++; 
			 
		} 
		 
			sb.append(this.getAttr_delimeter()); 
		 
			return sb.toString(); 
			 
	} 
	 
	public String toStringValue() 
	{ 
		Iterator it = value_map.entrySet().iterator(); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		int count = 0; 
		 
		int size = value_map.size(); 
		 
		while(it.hasNext()) 
		{ 
			@SuppressWarnings("unchecked") 
			Map.Entry<Integer,String> pair = (Map.Entry<Integer,String>)it.next(); 
			 
			@SuppressWarnings("unused") 
			int key = pair.getKey(); 
			 
			String value = pair.getValue(); 
			 
			sb.append(value); 
			 
			if(count < (size-1)) 
				sb.append(this.getDelimeter()); 
			 
			   count++; 
			 
		} 
			 
			return sb.toString(); 
	 
	} 
	 
	public void updateValue(String value) 
	{ 
		 
		String arr[] = value.split(this.getDelimeter()); 
		 
		if(arr == null) 
			return; 
		 
		this.getValue_map().clear(); 
		 
		if(arr.length != gl.E_EMPTY) 
		{ 
			for(int i=0;i<arr.length;i++) 
			this.getValue_map().put(i,arr[i]); 
		} 
		else 
		{ 
			this.getValue_map().put(0,value); 
		} 
			 
		 
	} 
	 
	 
	 
	public static void doTest(String home) 
	{ 
			 
			Object v  = new Object(){}; 
		 
	 		Map<Integer,String> raw_map = new LinkedHashMap<Integer,String>(); 
	 		 
	 		String source = ""; 
	 		 
	 		int error_count = gl.E_EMPTY; 
	 		 
	 		try { 
				 
	 			source = Fu.get_str_from_file(home); 
	 			 
	 			String arr[] = source.split(System.lineSeparator()); 
	 			 
	 			for(int i=0;i < arr.length;i++) 
	 			{ 
	 				raw_map.put(i,arr[i]); 
	 				 
					SProperty sb = new SProperty("name"); 
					 
					if (!sb.parse(arr[i])) 
						error_count++; 
						 
					 
	 			} 
	 			 
	 			if(error_count==gl.E_EMPTY) 
		 		gl.tracex(v,String.format("%s...%s...",gl.nr("Test done"),"Ok")); 
	 			else 
	 			gl.tracex(v,String.format("%s...%s...%d",gl.nr("Test done with"),"Errors.",error_count)); 

	 			 
	 			 
	 		} 
	 		catch(Exception e) 
	 		{ 
	 			gl.tracex(v,String.format("%s...%s...%s","Exception","Error.",e.toString())); 
				 
	 		} 
		 
		} 
	 
	 
	public static void Test_prop() 
	{ 
		String s_ource = " T{Text=Some Text;Color=255,234,226,223;Font=Tahoma,0,12;}"; 
		 
		String source = Su.extractVar(s_ource,"{","}"); 
		 
		String replace = s_ource.replace(source,""); 
		 
		gl.smn("Replaced value : " + replace); 
		 
		gl.smn("Color: " + SColor.toString(SColor.parse("Color", source))); 
		 
		gl.smn("Font : " + SFont.toString(SFont.parse("Font", source))); 
		 
		SText text = new SText("text"); 
		 
		if (text.parse(source)) 
		{ 
			gl.smn("Text : " + text.getText()); 
		} 
		 
		 
	} 
	public static void Test_prop_file() 
	{ 
		String file = "e:\\exp\\stat\\jobs\\svn_.job"; 
		 
		String source; 
		 
		try { 
			 
			source = Fu.get_str_from_file(file); 
			 
		} catch (IOException e) { 
			 
			gl.smn("Exception :" + e.toString()); 
			 
			return; 
		} 
		 
		String d = System.lineSeparator(); 
		 
		gl.smn("Disk : " + gl.getProperty(source,"disk",d)); 
		 
		gl.smn("svn_source_dir : " + gl.getProperty(source,"svn_source_dir",d)); 
		 
		gl.smn("svn_time_control : " + gl.getProperty(source,"svn_time_control",d)); 
		 
		gl.smn("svn_time_direction : " + gl.getProperty(source,"svn_time_direction",d)); 
		 
		gl.smn("svn_update_file : " + gl.getProperty(source,"svn_update_file",d)); 
		 

		 
	} 
	 
	public static void main(String[] args) 
	{ 
	 
		Test_prop(); 
				 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
