 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
package ap.prop; 

import java.io.Serializable; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import ap.global.gl; 
import ap.ray.APanelj; 
import ap.utils.Fu; 

public class SDouble extends SProperty { 

	private double value; 
	 
	public SDouble() { 
		super("color"); 
	} 

	public SDouble(String name) { 
		super(name); 

	} 

	public SDouble(String name, String pvalue) { 
		super(name, pvalue); 

	} 

	public SDouble(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 

	} 

	 

	public double getValue() { 
		return value; 
	} 

	public void setValue(double value) { 
		this.value = value; 
	} 

	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		 
		try 
		{ 
			this.setValue(Double.parseDouble(this.getValue_map().get(gl.E_EMPTY))); 
		} 
		catch(NumberFormatException e) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("NumberFormatException"),"Error",e.toString())); 
			 
				return false; 
		} 
		 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing is"),"Ok",this.toString())); 
	 
				return true; 
		 
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
	 				 
					SDouble sb = new SDouble("some_double"); 
					 
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

	@Override 
	public String toString() 
	{ 
		//Not parsing 
		if(this.getValue_map().size()==gl.E_EMPTY) 
					return String.format("%s=%d%s",this.getName(),this.getValue(),this.getAttr_delimeter()); 
		else 
			return super.toString(); 
			 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		doTest(APanelj.home); 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
