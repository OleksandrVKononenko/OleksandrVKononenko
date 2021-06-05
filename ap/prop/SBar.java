 
/** 
* 
*/ 
package ap.prop; 

import java.util.Date; 
import java.util.Locale; 

import ap.global.gl; 
import ap.utils.DateUtil; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 06 ????. 2017 ?. 12:58:52 
* Project  name : Organizer 
* Package  name : ap.prop 
* File     name : SBar.java 
* 
*/ 
public class SBar extends SProperty { 

	private static final long serialVersionUID = 1L; 
	 
	private String ticker; 
	 
	private Date date; 
	 
	private int   time; 
	 
	private double o; 
	 
	private double h; 
	 
	private double l; 
	 
	private double c; 
	 
	 
	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public Date getDate() { 
		return date; 
	} 

	public void setDate(Date date) { 
		this.date = date; 
	} 

	public int getTime() { 
		return time; 
	} 

	public void setTime(int time) { 
		this.time = time; 
	} 

	public double getO() { 
		return o; 
	} 

	public void setO(double o) { 
		this.o = o; 
	} 

	public double getH() { 
		return h; 
	} 

	public void setH(double h) { 
		this.h = h; 
	} 

	public double getL() { 
		return l; 
	} 

	public void setL(double l) { 
		this.l = l; 
	} 

	public double getC() { 
		return c; 
	} 

	public void setC(double c) { 
		this.c = c; 
	} 

	public SBar() { 
		 
	} 

	/** 
	 * @param name 
	 */ 
	public SBar(String name) { 
		super(name); 
		 
	} 

	public SBar(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SBar(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 

	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		// If name not exist insert one. 
		 
		String l_value = value; 
		 
		if(value.indexOf("=")== gl.E_ERROR) 
		{ 
			l_value = String.format("%s=%s",this.getName(),value); 
			 
			gl.tracex(v,String.format("Activation injecting tag name ...%s",this.getName())); 
			 
		} 
		 
		if (!super.parse(l_value)) 
			return false; 
		 
		int must_param_count = 7; 
		 
		if ( this.getValue_map().size() != must_param_count) 
		{ 
					gl.tracex(v,String.format("Param count error...%d...must be %d",this.getValue_map().size(),must_param_count)); 
					 
					return false; 
		} 
		 
		 
		int k = 0; 
		 		 
		try 
		{ 
			this.setTicker(this.getValue_map().get(k++)); 
			 
			this.setDate(DateUtil.to_date(this.getValue_map().get(k++))); 
			 
			String s_time = this.getValue_map().get(k++); 
			 
			int i_time = gl.E_ERROR; 
			 
			if(s_time != null && s_time.trim().length() != gl.E_EMPTY ) 
				i_time  =  Integer.parseInt(s_time); 
				 
			this.setTime(i_time); 
			 
			this.setO(Double.parseDouble(this.getValue_map().get(k++))); 
			 
			this.setH(Double.parseDouble(this.getValue_map().get(k++))); 
			 
			this.setL(Double.parseDouble(this.getValue_map().get(k++))); 
			 
			this.setC(Double.parseDouble(this.getValue_map().get(k++))); 
		} 
		catch(NumberFormatException e) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("NumberFormatException"),"Error",e.toString())); 
			 
				return false; 
		} 
		 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("Parsing is"),"Ok",this.toString())); 
	 
				return true; 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		 
		 
		if(this.getValue_map().size()==gl.E_EMPTY) 
				return String.format("%s=%s",this.toStringShort()); 
		else 
				return super.toString(); 
			 
	} 
	 
	public String toStringShort() 
	{ 

		Locale.setDefault(new Locale("en", "US")); 

		return String.format("%s,%s,%d,%.4f,%.4f,%.4f,%.4f%s", this.getName(), 
				this.getTicker(), DateUtil.to_string(this.getDate(), "."), 
				this.getTime(), this.getO(), this.getH(), this.getL(), 
				this.getC(), this.getAttr_delimeter()); 
	 
	} 
	 
	 
	public static void Test_Init() 
	{ 
		SBar bar = new SBar("bar"); 
		 
		String data = "cat,01.02.2001,,1.001,2.002,3.003,4.004;"; 
		 
		String msg = String.format("Test...%s...%s","%s","%s"); 
		 
		if(bar.parse(data)) 
			gl.tracex(new Object(){},String.format(msg,"Ok",bar.toString() )); 
		else 
			gl.tracex(new Object(){},String.format(msg,"Error","")); 
		 
			 
	} 
	 
	public static void main(String[] args) { 
		 
		Test_Init(); 

	} 

} 
// Revision : 10.09.2018 12:49:15 
