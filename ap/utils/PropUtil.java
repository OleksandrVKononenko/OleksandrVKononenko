 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import ap.area.AreaManager; 
import ap.global.gl; 
import ap.prop.SAreaManager; 

public class PropUtil { 

	public PropUtil() { 
		 
	} 

	public static AreaManager extractAreaManager(String propname,String text) 
	{ 
		SAreaManager sa = new SAreaManager(); 
		 
		String payload = String.format("%s=%s%s", sa.getName(),text,sa.getAttr_delimeter()); 
		 
		if (sa.parse(payload)) 
		{ 
			return sa.getArea_manager(); 
			 
		} 
		 
			gl.tracex(new Object(){},String.format("Error source : [%s] for parsing",text)); 
		 
			return null; 
	} 
	 
	public static void test_extractAreaManager(String propname,String text) 
	{ 
		 
		AreaManager am = extractAreaManager(propname,text); 
		 
		if(am !=null) 
		gl.smn("Unit test ok : " + am.toStringShort()); 
		else 
		gl.smn("Unit test error "); 
			 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		String test = "-1,-1,-1,-1,  -1,  -1,-1,-1, NONE,  0,0,0,0"; 
		 
		test_extractAreaManager("action",test); 

	} 

} 
// Revision : 10.09.2018 12:49:17 
