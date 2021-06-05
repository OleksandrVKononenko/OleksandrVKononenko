 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.prop; 

import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 22 ???. 2017 ?. 17:21:50 
* Project  name : Organizer 
* Package  name : ap.prop 
* File     name : SAlign.java 
* 
*/ 
public class SAlign extends SBounds { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	/** 
	 * 
	 */ 
	 
	private Dimension align; 
	 
	 
	 
	public Dimension getAlign() { 
		return align; 
	} 

	public void setAlign(Dimension align) { 
		this.align = align; 
	} 

	public SAlign() { 
		 
	} 

	/** 
	 * @param rect 
	 */ 
	public SAlign(Rectangle rect) { 
		super(rect); 
		 
	} 

	/** 
	 * @param name 
	 */ 
	public SAlign(String name) { 
		super(name); 
		 
	} 

	/** 
	 * @param name 
	 * @param pvalue 
	 */ 
	public SAlign(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	/** 
	 * @param name 
	 * @param pvalue 
	 * @param dlm 
	 */ 
	public SAlign(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
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
			 
			if ( this.getValue_map().size() != 4 && this.getValue_map().size() != 6) 
			{ 
					gl.tracex(v,String.format("%s...%s...%s","salign parsing","error param count,4 or 6 ",this.getValue_map().size())); 
					 
					return false; 
			} 
			 
				rect = new Rectangle( 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++))); 
				 
			this.setBounds(rect); 
			 
			this.setAlign(new Dimension( 
					Integer.parseInt(this.getValue_map().get(k++)), 
					Integer.parseInt(this.getValue_map().get(k++))) 
			); 
			 
			 
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
	 
	@Override 
	public String toString() 
	{ 
		 
		return String.format("%s,%s",super.toString(),SDimension.toString(this.getAlign())); 
	} 
	 
	/** 
	 * @param args 
	 */ 
	 
	public static void Test_main() 
	{ 
	 
		SAlign sa = new SAlign("align"); 
		 
		if(!sa.parse("align=1,1,1,1,1,1;")) 
			gl.smn("Error"); 
		else 
			gl.smn("Ok" + sa.toString()); 
		 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		Test_main(); 

	} 

} 
// Revision : 10.09.2018 12:49:15 
