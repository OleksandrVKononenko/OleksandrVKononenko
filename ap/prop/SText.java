 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.prop; 

import ap.global.gl; 
import ap.utils.Su; 

public class SText extends SProperty { 

	public SText() { 
		 super("text"); 
	} 

	public SText(String name) { 
		super(name); 
		 
	} 

	public SText(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SText(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 
	 
	public String getText() 
	{ 
		return this.toStringShort(); 
	} 
	 
	public String toStringShort() 
	{ 
		return this.toStringValue(); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		//for not parsing case. 
		if(this.getValue_map().size()==gl.E_EMPTY) 
			return String.format("%s=%s%s",this.getName(),this.toStringShort(),this.getAttr_delimeter()); 
		else 
			return super.toString(); 
			 
	} 
	 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		 
		if (!super.parse(value)) 
			return false; 
		 
	 		return true; 
	} 
	 
	 
	 
		 
	public static boolean doTest_toString(String value) 
	{ 
		String property_name = Su.BeforeAtFirst(value,'='); 
		 
		SText st = new SText(property_name); 
		 
		if(st.parse(value)) 
		{ 
			 
			gl.smn("Input : " + value + " Output : " + st.toString() + " Short : " + st.toStringShort()); 
			 
			return true; 
		} 
		 
			return false; 
		 
	} 

	public static void main(String[] args) { 
		 
		//doTest_toString("text=1,2,3s,4b;"); 
		 
		SText s = new SText("text","some_text"); 
		 
		gl.smn(String.format("ToString() : %s ToStringShort() : %s  ToStringValue() : %s ",s.toString(),s.toStringShort(),s.toStringValue())); 
		 
		//s.parse("Some text 2"); 
		 
		//s.getValue_map().clear(); 
		 
		//s.getValue_map().put(0, "Some text 2"); 
		 
		s.updateValue("sss,aaa,bbb,ggg,jjj"); 
		 
		gl.smn(String.format("After set payload ... ToString() : %s ToStringShort() : %s  ToStringValue() : %s ",s.toString(),s.toStringShort(),s.toStringValue())); 
		 
		 
		 
		 
		 
	} 

} 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
