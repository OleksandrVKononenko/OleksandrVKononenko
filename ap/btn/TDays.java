 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.btn; 

import java.util.Date; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TDays { 

	//DOM,DOW,DOY,WOM,WOY,MOY 

	private Date date; 
	 
	private int dom; 
	 
	private int dow; 
	 
	private int doy; 
	 
	private int wom; 
	 
	private int woy; 
	 
	private int moy; 

	 
	 
	public Date getDate() { 
		return date; 
	} 

	public void setDate(Date date) { 
		this.date = date; 
	} 

	public int getDom() { 
		return dom; 
	} 

	public void setDom(int dom) { 
		this.dom = dom; 
	} 

	public int getDow() { 
		return dow; 
	} 

	public void setDow(int dow) { 
		this.dow = dow; 
	} 

	public int getDoy() { 
		return doy; 
	} 

	public void setDoy(int doy) { 
		this.doy = doy; 
	} 

	public int getWom() { 
		return wom; 
	} 

	public void setWom(int wom) { 
		this.wom = wom; 
	} 

	public int getWoy() { 
		return woy; 
	} 

	public void setWoy(int woy) { 
		this.woy = woy; 
	} 

	public int getMoy() { 
		return moy; 
	} 

	public void setMoy(int moy) { 
		this.moy = moy; 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%d,%d,%d,%d,%d,%d",this.getDom(),this.getDow(),this.getDoy(),this.getWom(),this.getWoy(),this.getMoy()); 
	} 
	 
	public TDays() { 
		 
	} 

	public TDays(Date date) { 
		 
		this.setDate(date); 
		 
		this.setRestThings(); 
	} 
	 
	public void setRestThings() 
	{ 
		this.setDom(DateUtil.day_of_month(this.getDate())); 
		 
		this.setDow(DateUtil.day_of_week(this.getDate())); 
		 
		this.setDoy(DateUtil.day_of_year(this.getDate())); 
		 
		this.setWom(DateUtil.week_of_month(this.getDate())); 
		 
		this.setWoy(DateUtil.week_of_year(this.getDate())); 
		 
		this.setMoy(DateUtil.month_of_year(this.getDate())); 
	} 

	 
	public static TDays getInstance(Date date) 
	{ 
		return new TDays(date); 
	} 
	 
	public static TDays getInstance() 
	{ 
		return new TDays(DateUtil.to_date("01.01.1900")); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
