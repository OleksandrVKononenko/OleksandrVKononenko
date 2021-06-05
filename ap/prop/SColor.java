 
package ap.prop; 

import java.awt.Color; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import ap.global.gl; 
import ap.utils.CU; 
import ap.utils.Fu; 

public class SColor extends SProperty { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	Map<Integer,String> tag_map; 
	 
	private Color color; 
	 
	 
	 
	public Map<Integer, String> getTag_map() { 
		return tag_map; 
	} 
	public void setTag_map(Map<Integer, String> tag_map) { 
		this.tag_map = tag_map; 
	} 
	public SColor() { 
		 
		super("color"); 
		 
	} 
	public SColor(Color color) { 
		 
		this(); 
		 
		this.setColor(color); 
	} 
	public SColor(String name) { 
		super(name); 

	} 

	public SColor(String name, String pvalue) { 
		super(name, pvalue); 

	} 

	public SColor(String name, String pvalue, String dlm) { 
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
		SColor i = new SColor(name); 
		 
		if(!i.parse(payload)) 
			return CU.getNullColor(); 
		 
		return i.getColor(); 
	} 
	 
	@Override 
	public boolean parse(String value) { 
		Object v = new Object() { 
		}; 

		if (!super.parse(value)) { 
			gl.tracex(v, 
					String.format("%s...Base parser...%s", gl.S_ERROR, value)); 

			return false; 
		} 

		if (this.getValue_map().size() != 4) { 

			gl.tracex(v, String.format("%s...%s...%s...%d", 
					gl.nr("color parsing"), "error.", "wrong number of params", 
					this.getValue_map().size())); 
				 
				return false; 
		} 

		int RED = 0, GREEN = 1, BLUE = 2, ALPHA = 3; 

		// Check params source on #tag 

		int i = 0; 

		if (tag_map == null) 
			tag_map = new HashMap<Integer, String>(); 

		int[] cm = { 0, 0, 0, 0 }; 

		try { 

			for (i = RED; i <= ALPHA; i++) { 
				String rawValue = this.getValue_map().get(i); 

				int tagIndex = rawValue.indexOf("#"); 

				if (tagIndex != gl.E_ERROR ) { 
					// There need to split. 

					String[] arr = rawValue.split("#"); 

					int PAYLOAD = 0, TAG = 1; 

					if (arr.length != gl.E_EMPTY) { 
						 
						String payload = arr[PAYLOAD]; 

						if(arr.length > gl.E_OK) 
						{ 
							String tag = arr[TAG]; 

							if(tag.trim().length() != gl.E_EMPTY) 
								tag_map.put(i, tag); 
						} 

						cm[i] = Integer.parseInt(payload.trim()); 

					} 
				} // check tag 
				else 
					cm[i] = Integer.parseInt(rawValue.trim()); 

				this.setColor(new Color(cm[RED], cm[GREEN], cm[BLUE], cm[ALPHA])); 

			} 
		} catch (NumberFormatException e) { 
			gl.tracex(v, 
					String.format("%s...%s...%s...%s", gl.nr("color parsing"), 
							"error.", "exception", e.toString())); 

			return false; 

		} 

		if(this.isDebug()) 
		gl.tracex( 
				v, 
				String.format("%s...%s...%s", gl.nr("Parsing is"), "Ok.", 
						this.toString())); 

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
	 				 
					SColor sb = new SColor(prop_name); 
					 
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
		SColor c = new SColor(color); 
		 
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
	 
	public static void Test_Single(String property_name,String payload) 
	{ 
		 
		SColor c = new SColor(property_name); 
		 
		if(!c.parse(payload)) 
		{ 
			gl.smn("Test error : " + c.toString()); 
			 
			return; 
		} 
		 
			Color l = c.getColor(); 
			 
			gl.smn("Test Ok : " + SColor.toString(l)); 
			 
			c.getTag_map().forEach((key,value)-> 
			{ 
				String msg = String.format("%3d %s",key,value); 
				 
				gl.smn(msg); 
			} 
			); 
			 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		//String path = "e:/bin/eclipse/wsp/organizer/data/a_test_2103.txt"; 
		 
		//SColor.doTest(path,"background"); 
		 
		//doTest_toString(gl.getRandomColor()); 
		 
		String payload = "background=251#$-%%-$,252#1,253###,0#1-2-3;"; 
		 
		Test_Single("background",payload); 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
