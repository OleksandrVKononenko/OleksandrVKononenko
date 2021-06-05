 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
package ap.btn; 

import ap.global.gl; 

public class TDowDistribution { 

	private int year; 
	 
	private int month; 
	 
	private int dow; 
	 
	private int white; 
	 
	private int black; 
	 
	private int zero; 
	 
	private String note; 
	 
	 
	 
	 
	public String getNote() { 
		return note; 
	} 

	public void setNote(String note) { 
		this.note = note; 
	} 

	public void incWhite() 
	{ 
		white++; 
	} 
	 
	public void incBlack() 
	{ 
		black++; 
	} 
	 
	public void incZero() 
	{ 
		zero++; 
	} 
	 
	 
	 
	public int getYear() { 
		return year; 
	} 

	public void setYear(int year) { 
		this.year = year; 
	} 

	public int getMonth() { 
		return month; 
	} 

	public void setMonth(int month) { 
		this.month = month; 
	} 

	public int getDow() { 
		return dow; 
	} 

	public void setDow(int dow) { 
		this.dow = dow; 
	} 

	public int getWhite() { 
		return white; 
	} 

	public void setWhite(int white) { 
		this.white = white; 
	} 

	public int getBlack() { 
		return black; 
	} 

	public void setBlack(int black) { 
		this.black = black; 
	} 

	public int getZero() { 
		return zero; 
	} 

	public void setZero(int zero) { 
		this.zero = zero; 
	} 

	public TDowDistribution() { 
		 
	} 

	public TDowDistribution(int dow,int white,int black,int zero) { 
		 
		this.setDow(dow); 
		 
		this.setWhite(white); 
		 
		this.setBlack(black); 
		 
		this.setZero(zero);; 
		 
	} 
	 
	public TDowDistribution(int month , int dow,int white,int black,int zero) { 
		 
		this( dow, white, black, zero); 
		 
		this.setMonth(month); 
		 
	} 

	public TDowDistribution(int year ,int month , int dow,int white,int black,int zero) { 
		 
		this(month ,dow,white,black,zero); 
		 
		this.setYear(year); 
		 
	} 
	 
	public static TDowDistribution getInstance() 
	{ 
		return new TDowDistribution(gl.E_EMPTY,gl.E_EMPTY,gl.E_EMPTY,gl.E_EMPTY,gl.E_EMPTY,gl.E_EMPTY); 
	} 

	 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%02d %04d %04d %04d ",this.getDow(),this.getWhite(),this.getBlack(),this.getZero()); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
