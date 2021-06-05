 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.io.IOException; 
import java.util.Iterator; 
import java.util.LinkedHashMap; 
import java.util.Map; 

public class AProperty { 
	 
	private String name; 
	 
	private Map<Integer,String> value_map; 
	 
	public AProperty() 
	{ 
		value_map = new LinkedHashMap(); 
	} 
	 
	 
	public AProperty(String name) 
	{ 
		this(); 
		 
		this.setName(name.toLowerCase().trim()); 
	} 
	 

	public AProperty(String name,String pvalue) 
	{ 
		this(); 
		 
		this.setName(name); 
		 
		String a[] = pvalue.split(","); 
		 
		for(int i=0; i < a.length;i++) 
		{ 
			value_map.put(i,(String)a[i]); 
		 
		} 
		 
	} 
	 
	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 

	/* 
	public String getValue() { 

		return this.getValue(); 
	} 
	*/ 
	 

	public void setValue(Map value) { 
		this.value_map = value; 
	} 

	public boolean parse(String p_value) 
	{ 
		 
		//gl.smn("Parse input :" + p_value); 
		 
		if(p_value == null) 
		{ 
			gl.smn("<AProperty.parse(null after parse items)>"); 
			 
			return false; 
		} 
		 
		int equal_position = p_value.indexOf('='); 
		 
		if(equal_position == gl.E_ERROR ) 
		{ 
		 
			//gl.smn("Equal error"); 
			 
			return false; 
		} 
		 
		if(!p_value.startsWith(this.name)) 
		{ 
			//gl.smn("Name error"); 
			 
			return false; 
		} 
		 
		//this.setName(value.substring(0,equal_position)); 
		 
		String r_value = p_value.substring(equal_position+1,p_value.length()); 
		 
		//gl.smn("R-value  for  : " + this.getName() + "="+ r_value); 
		 
		if(r_value.contains(",")) 
		{ 
		 
		String arr[] = r_value.split(","); 
		 
			for(int i=0;i< arr.length;i++) 
			{ 
				value_map.put(i,arr[i]); 
			} 
		} 
		else 
		{ 
			value_map.put(0,r_value.trim()); 
			 
		} 
				 
			return (value_map.size() != gl.E_EMPTY); 
	} 
	 
		 
	public String toString() 
	{ 
		// Get pair, like this : name=value 
		 
		Iterator it = value_map.entrySet().iterator(); 
			 
		String s_value = ""; 
		 
		int count = 0; 
		 
		int size = value_map.size(); 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,String> pair = (Map.Entry<Integer,String>)it.next(); 
			 
			int key = pair.getKey(); 
			 
			String value = pair.getValue(); 
			 
			s_value += value ; 
			 
			if(count < (size-1)) 
			   s_value += ","; 
			 
			   count++; 
			 
		} 
			 
			return this.getName()+"="+ s_value+";"; 
	} 
	 
	public String toStringValueOnly() 
	{ 
		// Get value , like this : value,[value1,value2,....valueN] 
		 
		Iterator it = value_map.entrySet().iterator(); 
			 
		String s_value = ""; 
		 
		int count = 0; 
		 
		int size = value_map.size(); 
		 
		while(it.hasNext()) 
		{ 
			Map.Entry<Integer,String> pair = (Map.Entry<Integer,String>)it.next(); 
			 
			int key = pair.getKey(); 
			 
			String value = pair.getValue(); 
			 
			s_value += value ; 
			 
			if(count < (size-1)) 
			   s_value += ","; 
			 
			   count++; 
			 
		} 
			 
			return s_value; 
	} 
	 
	 
	public static void TestLoad() 
	{ 
		 
		// const 
		 
		final int PID=0,ID=1,COLOR=2,BOUNDS=3; 
		 
		// Load properties 
		Map<Integer,String> raw_map = new LinkedHashMap(); 
		 
		Map<Integer,APanelj> map = new LinkedHashMap(); 
		 
		 
		String source = ""; 
		 
		try { 
			 
			source = gl.getFileAsStringScanner(APanelj.home); 
			 
			String arr[] = source.split("\r\n"); 
			 
			gl.smn("Loaded : " + arr.length + " rows."); 

			 
			for(int i=0;i < arr.length;i++) 
			{ 
				 
				raw_map.put(i,arr[i]); 
				 
				String a[] = arr[i].split(";"); 

				APid pid = new APid(); 

				if (pid.parse(a[PID])) 
					gl.sm(pid.toString()); 

				AId id = new AId(); 

				if (id.parse(a[ID])) 
					gl.sm(id.toString()); 

				AColor color = new AColor(); 

				if (color.parse(a[COLOR])) 
					gl.sm(color.toString()); 

				ABounds bounds = new ABounds(); 

				if (bounds.parse(a[BOUNDS])) 
					gl.sm(bounds.toString()); 
				 
				APanelj apj = new APanelj(); 
				 
						//apj.setProperties(pid, id, color, bounds); 
						 
						map.put(i,apj); 
									 
						gl.smn(""); 
			} 
			 
						gl.smn("Map items : " + map.size()); 
			 
		} catch (IOException e) { 
			 
			e.printStackTrace(); 
		} 
		 
	} 
	 
	public static void Test() 
	{ 
		 
		/* 
		APid pid = new APid(); 
		 
		if(pid.parseString("pid=1220")) 
		   gl.smn(pid.toString() + " value : " + pid.getValue()); 
		else 
			gl.smn("Error while parsing"); 
		 
		*/ 
		 
		/* 
		 
		AColor color = new AColor(); 
		 
		if(color.parseString("color=111,112,113")) 
		{ 
			if(color.getValue()) 
			gl.smn(color.toString() + " value : " + color.getRed()+","+ color.getGreen() + "," + color.getBlue()); 
			 
		} 
		else 
			gl.smn("Error while parsing"); 
		 
		*/ 
		 
		/* 
		AColorAlpha acolor = new AColorAlpha(); 
		 
		if(acolor.parseString("color=110,111,112,113")) 
		{ 
			if(acolor.getValue()) 
			gl.smn(acolor.toString() + " value : " + acolor.getAlpha()+"," + acolor.getRed()+","+ acolor.getGreen() + "," + acolor.getBlue()); 
			 
		} 
		else 
			gl.smn("Error while parsing"); 
		*/ 
		 
		ABounds bounds = new ABounds(); 
		 
		if(bounds.parse("bounds=1,2,3,4")) 
		{ 
			if(bounds.getValue()) 
			gl.smn(bounds.toString() + " value : " + bounds.getRect().toString()); 
			 
		} 
		else 
			gl.smn("Error while parsing"); 
				 
		 
	 
	} 
	 
	 
	public static void main(String[] args) 
	{ 
	 
			Test(); 
		 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
