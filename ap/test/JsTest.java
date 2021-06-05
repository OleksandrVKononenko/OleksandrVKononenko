 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.test; 


import javax.script.*; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 06 ???? 2017 ?. 14:05:50 
* Project  name : Organizer 
* Package  name : ap.test 
* File     name : JsTest.java 
* 
*/ 



public class JsTest { 

		 
	/** 
	 * 
	 */ 
	public JsTest() { 
		// REPLACE Auto-generated constructor stub 
	} 


public static void helloJs() 
{ 

	ScriptEngineManager sem = new ScriptEngineManager(); 
	 
	ScriptEngine eng = sem.getEngineByName("nashorn"); 
	 
	try 
	{ 
		eng.eval("print('Hello World!');"); 
	} 
	catch(final ScriptException sex) {sex.printStackTrace();} 
	 
	 
} 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		helloJs(); 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
