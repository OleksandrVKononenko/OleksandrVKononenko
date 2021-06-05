 
package ap.prop; 

import java.awt.Color; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import ap.global.gl; 
import ap.utils.CU; 
import ap.utils.Fu; 

public class SLayerColor extends SProperty { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	private Color color; 
	 
	private int  ali = gl.ALI.NONE; 
	 
	 
	 
	public int getAli() { 
		return ali; 
	} 
	public void setAli(int ali) { 
		this.ali = ali; 
	} 
	public SLayerColor() { 
		 
		super("layer_color"); 
		 
	} 
	public SLayerColor(Color color) { 
		 
		this(); 
		 
		this.setColor(color); 
	} 
	public SLayerColor(String name) { 
		super(name); 

	} 

	public SLayerColor(String name, String pvalue) { 
		super(name, pvalue); 

	} 

	public SLayerColor(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 

	} 

	public Color getColor() { 
		return color; 
	} 

	public void setColor(Color color) { 
		this.color = color; 
	} 

	public static Color parse(String name, String payload) 
	{ 
		SLayerColor i = new SLayerColor(name); 
		 
		if(!i.parse(payload)) 
			return CU.getNullColor(); 
		 
		return i.getColor(); 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		if(this.getValue_map().size() != 5) 
		{ 

			gl.tracex(v,String.format("%s...%s...%s...%d",gl.nr("color parsing"),"error.","wrong number of params",this.getValue_map().size())); 
			 
			return false; 
		} 
		 
		 int RED=0,GREEN=1,BLUE=2,ALPHA=3; 
		 
try 
{ 
	 this.setColor( 
			 
			 new Color( 
			 Integer.parseInt(this.getValue_map().get(RED)), 
			 Integer.parseInt(this.getValue_map().get(GREEN)), 
			 Integer.parseInt(this.getValue_map().get(BLUE)), 
			 Integer.parseInt(this.getValue_map().get(ALPHA))) 
			 ); 
	 
} 
		 catch(NumberFormatException e) 
{ 
			 gl.tracex(v,String.format("%s...%s...%s...%s",gl.nr("color parsing"),"error.","exception",e.toString())); 
				 
			 return false; 
} 
 
		gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing is"),"Ok.",this.toString())); 
	 
		return true; 
		 
	} 
	 
	public static void doTest(String home,String prop_name) 
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
	 				 
					SLayerColor sb = new SLayerColor(prop_name); 
					 
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
	public static void doTest_toString(Color color) 
	{ 
		SLayerColor c = new SLayerColor(color); 
		 
		gl.smn("Color test : " + c.toString()); 
		 
	} 
	 
	public static String toString(Color color) 
	{ 
		return CU.toString(color); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		 
			if(this.getValue_map().size()==gl.E_EMPTY) 
				return String.format("%s=%s%s",this.getName(), 
						CU.toString(this.getColor()), 
						this.getAttr_delimeter() 
						); 
			else 
				return super.toString(); 
	} 
	 
			 
	public static void main(String[] args) { 
		 
		String path = "e:/bin/eclipse/wsp/organizer/data/a_test_2103.txt"; 
		 
		SLayerColor.doTest(path,"background"); 
		 
		//doTest_toString(gl.getRandomColor()); 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
