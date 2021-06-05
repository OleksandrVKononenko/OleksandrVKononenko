 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.util.Date; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TDateRange { 
	 
	 
	private Date date_from; 
	 
	private Date date_to; 
	 
	 
	 
	public Date getDate_from() { 
		return date_from; 
	} 

	public void setDate_from(Date date_from) { 
		this.date_from = date_from; 
	} 

	public Date getDate_to() { 
		return date_to; 
	} 

	public void setDate_to(Date date_to) { 
		this.date_to = date_to; 
		 
		 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%s %s", 
				DateUtil.to_string(this.getDate_from()), 
				DateUtil.to_string(this.getDate_to()) 
				); 
	} 

	public TDateRange() { 
		 
	} 
	 
	public TDateRange(Date from,Date to) { 
		 
		this.setDate_from(from); 
		 
		this.setDate_to(to); 
		 
	} 

	public TDateRange(String from,String to) { 
		 
		this(DateUtil.to_date(from),DateUtil.to_date(to)); 
 
	} 
	 
	public static TDateRange get_instance(Date from, Date to) 
	{ 
		 
		return new TDateRange(from,to); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
