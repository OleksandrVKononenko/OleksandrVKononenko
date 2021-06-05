 
 
 
 
 
package ap.prop; 

import java.awt.Rectangle; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import ap.global.gl; 
import ap.utils.Fu; 


public class SBounds extends SProperty { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	private Rectangle bounds; 
	 
	public SBounds() { 
		super("bounds"); 
		 
	} 

	public SBounds(Rectangle rect) { 

		this(); 
 
		this.setBounds(rect); 
	} 
	 
	public SBounds(String name) { 
		super(name); 
		 
	} 

	public SBounds(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SBounds(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 

	 
	 
	public Rectangle getBounds() { 
		return bounds; 
	} 

	public void setBounds(Rectangle bounds) { 
		this.bounds = bounds; 
	} 
	 
	public static Rectangle parse(String name, String payload) 
	{ 
		SBounds i = new SBounds(name); 
		 
		if(!i.parse(payload)) 
			return SBounds.getNullBounds(); 
		 
		return i.getBounds(); 
	} 
	 
	public static Rectangle getNullBounds() 
	{ 
		return new Rectangle(gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR); 
	} 
	 
	public static boolean equal(Rectangle rect1,Rectangle rect2) 
	{ 
		return  ( 
				rect1.x==rect2.x && 
				rect1.y == rect2.y  && 
				rect1.width == rect2.width && 
				rect1.height == rect2.height 
				) ; 
				 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		int k = 0; 
		 
		try 
		{ 
			Rectangle rect = null; 
			 
			if ( this.getValue_map().size() != 4) 
			{ 
					gl.tracex(v,String.format("%s...%s...%s","sbounds parsing","error param count ",this.getValue_map().size())); 
					 
					return false; 
			} 
			 
				rect = new Rectangle( 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++))); 
			 
			 
			this.setBounds(rect); 
			 
			 
		} 
		catch(NumberFormatException e) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s","NumberFormatException","Error.",e.toString())); 
			 
				return false; 
		} 
	 
				if(this.isDebug()==true) 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing is"),"Ok",this.toStringValue())); 
		 
		 
		return true; 
	 
	} 
	 
	public static void doTest_doForm(String theme,String def) 
	{ 
		SBounds chk = new SBounds(); 
		 
		if (SBounds.doForm(theme,chk,def)) 
			gl.smn("Test Ok ,input value : " + chk.toStringShort()); 
		else 
			gl.smn("Test error."); 
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
	 				 
					SBounds sb = new SBounds("bounds"); 
					 
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
	 
	public static void doTest_toString(Rectangle rect) 
	{ 
		SBounds b = new SBounds(rect); 
		 
		gl.smn("Test bounds info :" + b.toString()); 
		 
	} 
	 
	 
	public static String toString(Rectangle rect) { 
		 
		return  String.format("%s",(rect==null) ?"null" :  String.format("%d,%d,%d,%d", rect.x, rect.y, rect.width,rect.height)); 

	} 

	public String toStringShort() 
	{ 
		return String.format("%s",toString(this.getBounds())); 
	} 

	 
	@Override 
	public String toString() 
	{ 

		if(this.getValue_map().size()==gl.E_EMPTY) 
				return String.format("%s=%s%s",this.getName(), 
						this.toStringShort(), 
						this.getAttr_delimeter() 
						); 
			else 
				return super.toString(); 
	} 

	public static boolean doForm(String legenda,SBounds value,String defaultValue) 
	{ 
		Object v = new Object() {}; 
		 
		String s_input = ap.utils.DlgInteger.getString(legenda,defaultValue); 
		 
		if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("Input data is error prone ...")); 
			 
			return false; 
			 
		} 
		 
		if(defaultValue.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s...%s",defaultValue,s_input,"Warning")); 
		} 
		 
		 
		SBounds sa = new SBounds("temp"); 
		 
		gl.tracex(v,String.format("Input value is : %s ",s_input)); 
		 
		String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
		 
		if (sa.parse(payload)) 
		{ 
			gl.tracex(v,String.format("Input value is : %s...%s ","Ok",sa.toStringShort())); 
			 
			value.setBounds(sa.getBounds()); 
			 
			return true; 
			 
		} 
			return false; 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		//doTest(APanelj.home); 
		 
		//doTest_toString(new Rectangle(10,11,12,13)); 
		 
		doTest_doForm("Some test","1,1,1,1"); 
		 
		 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
