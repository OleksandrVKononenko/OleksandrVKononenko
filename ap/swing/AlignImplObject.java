 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import ap.global.gl; 

public class AlignImplObject { 

	public AlignImplObject() { 
		 
	} 
	 
	public static AlignImplObject get_instance() 
	{ 
		return new AlignImplObject(); 
	} 
	 
	public static void test() 
	{ 
		 
		int [] index = {0}; 
		 
		IAlign.align.forEach(a-> 
		{ 
			gl.smn(gl.sf("Source...%s...indexOf(String)...%03d...IndexOf(int)...%s",gl.format(a,gl.AL.LEFT,6),IAlign.indexOf(a),IAlign.indexOf(index[0]))); 
			 
			index[0]++; 
		}); 
	} 

	public static void main(String[] args) { 
		 
		test(); 

	} 

} 
