 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.prop; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.io.Serializable; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import ap.global.gl; 
import ap.ray.APanelj; 
import ap.utils.Fu; 

public class SInt extends SProperty  { 

	private int value = gl.E_ERROR; 
	 
	public SInt() { 
		super("color"); 
	} 

	public SInt(String name) { 
		super(name); 

	} 

	public SInt(String name,int value) { 
		super(name); 
		 
		this.setValue(value); 

	} 

	public SInt(String name, String pvalue) { 
		super(name, pvalue); 

	} 

	public SInt(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 

	} 

	 

	public int getValue() { 
		return value; 
	} 

	public void setValue(int value) { 
		this.value = value; 
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
	 
	public static int parse(String name, String payload) 
	{ 
		gl.smn("---> input Error parse :" + payload); 
		 
		SInt i = new SInt(name); 
		 
		if(!i.parse(payload)) 
		{ 
			gl.smn("---> Error parse :" + payload); 
			 
			return gl.E_ERROR; 
		} 
		 
		return i.getValue(); 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		 
		try 
		{ 
			 
			int i_value = Integer.parseInt(this.getValue_map().get(gl.E_EMPTY)); 
			 
			this.setValue(i_value);	 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing for int"),"error value",this.getValue_map().get(gl.E_EMPTY))); 
			 
			return false; 
		} 
		 
		if(this.isDebug()) 
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
	 				 
					SInt sb = new SInt("pid"); 
					 
					if (!sb.parse(arr[i])) 
						error_count++; 
					else 
						gl.tracex(v,String.format("%s...%s","Test suite value :",sb.toString())); 
						 
						 
					 
	 			} 
	 			 
	 			if(error_count==gl.E_EMPTY) 
		 		gl.tracex(v,String.format("%s...%s","Test suite done  ","successfull.")); 
	 			else 
	 			gl.tracex(v,String.format("%s...%s...%d","Test suite done with ","errors.",error_count)); 

	 			 
	 			 
	 		} 
	 		catch(Exception e) 
	 		{ 
	 			gl.tracex(v,String.format("%s...%s...%s","Exception","Error.",e.toString())); 
				 
	 		} 
		 
		} 

	public static void main(String[] args) { 
		 
		doTest("data/a_s.txt"); 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
