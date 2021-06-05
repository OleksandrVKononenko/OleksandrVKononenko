 
package ap.prop; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.io.Serializable; 
import java.util.Arrays; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import ap.global.gl; 
import ap.global.gl.ALI; 
import ap.ray.APanelj; 
import ap.utils.Fu; 

public class SDimension extends SProperty { 

	private Dimension dimension; 
	 
	public SDimension() { 
		super("child_dim"); 
	} 

	public SDimension(Dimension dim) 
	{ 
		this(); 
		 
		this.setDimension(dim); 
	} 
	public SDimension(String name) { 
		super(name); 

	} 

	public SDimension(String name, String pvalue) { 
		super(name, pvalue); 

	} 

	public SDimension(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 

	} 

	public Dimension getDimension() { 
		return dimension; 
	} 

	public void setDimension(Dimension dimension) { 
		this.dimension = dimension; 
	} 


	public static boolean equal(Dimension dim1,Dimension dim2) 
	{ 
		return ( 
				dim1.width == dim2.width && 
				dim1.height == dim2.height 
				); 
	} 
	 
	public static int equalByAli(Dimension dim1,Dimension dim2) 
	{ 
		 
		if(equal(dim1,dim2)) 
			return gl.ALI.FULL; 
		 
		if( 	dim1.width > dim2.width || 
				dim1.height > dim2.height 
		  ) 
				return  gl.ALI.LEFT; 
		else 
			return  gl.ALI.RIGHT; 
	} 
	 
	 
	 
	 
	public static Dimension getNullDimension() 
	{ 
		return new Dimension(gl.E_ERROR,gl.E_ERROR); 
	} 
	 
	public static Dimension parse(String name, String payload) 
	{ 
		SDimension i = new SDimension(name); 
		 
		if(!i.parse(payload)) 
			return SDimension.getNullDimension(); 
		 
		return i.getDimension(); 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		 
		if ( this.getValue_map().size() != 2) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s","dimension parsing","error param count ",this.getValue_map().size())); 
				 
				return false; 
		} 
		 
		try 

		{ 
			this.setDimension(new Dimension(Integer.parseInt(this 
					.getValue_map().get(gl.E_EMPTY)), Integer.parseInt(this 
					.getValue_map().get(gl.E_OK)))); 

			if(this.isDebug()) 
			gl.tracex(v, String.format("%s...%s...%s", 
					gl.nr("dimension parsing"), "Ok.", this.toString())); 

		} 

		catch (NumberFormatException e) { 
			gl.tracex(v, String.format("%s...%s...%s", "dimension parsing", 
					"exception", e.toString())); 

			return false; 
		} 
		 
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
	 				 
					SDimension sb = new SDimension("size"); 
					 
					if (!sb.parse(arr[i])) 
						error_count++; 
						 
					 
	 			} 
	 			 
	 			if(error_count==gl.E_EMPTY) 
		 		gl.tracex(v,String.format("%s...%s",gl.nr("Test done"),"successfull.")); 
	 			else 
	 			gl.tracex(v,String.format("%s...%s...%d",gl.nr("Test done with"),"Errors.",error_count)); 

	 			 
	 			 
	 		} 
	 		catch(Exception e) 
	 		{ 
	 			gl.tracex(v,String.format("%s...%s...%s","Exception","Error.",e.toString())); 
				 
	 		} 
		} 
	 
	public static String toString(Dimension dim) 
	{ 
		return String.format("%d,%d",dim.width,dim.height); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		 
		if(this.getValue_map().size()==gl.E_EMPTY) 
			return String.format("%s=%s%s",this.getName(),toString(this.getDimension()),this.getAttr_delimeter()); 
		else 
			return super.toString(); 
			 
	} 

	 
	public String toStringShort() 
	{ 
		//Not parsing 
		return String.format("%d,%d",this.getDimension().width,this.getDimension().height); 
	} 
	 
	public static void doTest_toString(Dimension dim) 
	{ 
		SDimension d = new SDimension(dim); 
		 
		gl.smn("Test dimension info : " + d.toString()); 
		 
		 
	} 
	 
	public static void doTest_AnyParams(String name,String value) 
	{ 
		SDimension sd = new SDimension(name); 
		 
		if(!sd.parse(value)) 
			gl.smn(String.format("Attr : %s  Payload : %s  %s",name,value,"Error")); 
		else 
			gl.smn(String.format("Attr : %s  Payload : %s  %s",name,value,"Ok")); 
			 

	} 

	public static void main(String[] args) { 
		 
		//doTest(APanelj.home); 
		 
	//	for(int i=0;i<10;i++) 
	//	doTest_toString(new Dimension(i,i)); 
		 
		doTest_AnyParams("asd","asd=1a,2;"); 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
