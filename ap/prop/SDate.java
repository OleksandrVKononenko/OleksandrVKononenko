 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.prop; 

import java.util.Date; 

import ap.global.gl; 
import ap.utils.DateUtil; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 06 ????. 2017 ?. 14:26:37 
* Project  name : Organizer 
* Package  name : ap.prop 
* File     name : SDate.java 
* 
*/ 
public class SDate extends SProperty { 

	private static final long serialVersionUID = 1L; 
	 
	private Date value ; 
	 
	 
	public Date getValue() { 
		return value; 
	} 


	public void setValue(Date value) { 
		this.value = value; 
	} 


	public SDate() { 
		 
	} 

	 
	public SDate(String name) { 
		super(name); 
		 
	} 

	 
	public SDate(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	 
	public SDate(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 

	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		 
		try 
		{ 
			this.setValue(DateUtil.to_date(this.getValue_map().get(gl.E_EMPTY))); 
		} 
		catch(NumberFormatException e) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("NumberFormatException"),"Error",e.toString())); 
			 
				return false; 
		} 
				if(this.isDebug()) 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing is"),"Ok",this.toString())); 
	 
				return true; 
	} 
	 
	@Override 
	public String toString() 
	{ 
 
		if(this.getValue_map().size()==gl.E_EMPTY) 
					return String.format("%s=%s%s",this.getName(), 
							DateUtil.to_string(this.getValue()), 
							this.getAttr_delimeter()); 
		else 
			return super.toString(); 
			 
	} 
	 
	public static String toString(Date date) 
	{ 
		return DateUtil.to_string(date); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:15 
