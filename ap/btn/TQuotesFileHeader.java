 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 
import java.util.Arrays; 
import java.util.Date; 
import java.util.List; 
import java.util.Locale; 

import ap.global.gl; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 
import ap.utils.Su; 

public class TQuotesFileHeader { 

	private String source; 
	 
	private String ticker; 
	 
	private String type; 
	 
	private String raw_text; 
	 
	 
	 
	public String getRaw_text() { 
		return raw_text; 
	} 

	public void setRaw_text(String raw_text) { 
		this.raw_text = raw_text; 
	} 

	public String getSource() { 
		return source; 
	} 

	public void setSource(String source) { 
		this.source = source; 
	} 

	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public String getType() { 
		return type; 
	} 

	public void setType(String type) { 
		this.type = type; 
	} 

	public TQuotesFileHeader() { 
		 
	} 

	public TQuotesFileHeader(String source) { 
		 
		this.setSource(source); 
		 
	} 
	 
	public static boolean isaQuoteRow(String row,String [] response) 
	{ 
		 String txt = Su.BeforeAtFirst(row,",").trim(); 
			 
	 	 boolean bl_ordinary_date = DateUtil.isa_normal_date_format(txt); 
		 
	 	 if(!bl_ordinary_date) 
	 	 { 
	 		 	boolean bl_fibo_date = DateUtil.is_fibo_date(txt); 
	 		 
	 		if(bl_fibo_date) 
	 		{ 
	 			response[gl.E_EMPTY] = "fibo"; 
	 			 
	 			return true; 
	 		} 
	 	 } 
	 	 else 
	 	 { 
	 		 	response[gl.E_EMPTY] = "normal"; 
	 		 	 
	 		 	return true; 
	 	 } 
	 		 			 
	 		return false; 
	 	 		 
	} 
	 
	public boolean read() 
	{ 
		 
		String msg = "Read"; 
		 
		try 
		{ 
		if(!Fu.isaFile(this.getSource())) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...source file...%s...not found",gl.S_ERROR,msg,this.getSource())); 
			 
			return false; 
		} 
		 
		String raw = Fu.get_first_row(this.getSource()); 
		 
		this.setRaw_text(raw); 
		 
		gl.tracex(new Object(){},String.format("%s...%s",gl.S_OK,msg)); 
		 
		return (this.getRaw_text().trim().length() != gl.E_EMPTY); 
		 
		} 
		catch(Exception ex) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...raw text...%s",gl.S_ERROR,msg,this.getRaw_text())); 

			return false; 
		} 
		 
		 
	} 
	 
	public boolean parse() 
	{ 
		int WAIT_COUNT_OF_ITEMS = 2; 
		 
		int TICKER = gl.E_EMPTY; 
		 
		int TYPE = gl.E_OK; 

		String [] arr = this.getRaw_text().split(","); 
				 
		int count = arr.length; 
				 
		if(count != WAIT_COUNT_OF_ITEMS) 
		{ 
			gl.tracex(new Object(){},String.format("%s...check header...%d...get...%d",gl.S_ERROR,WAIT_COUNT_OF_ITEMS,count)); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},String.format("%s...Check count items...waiting of...%d...get...%d",gl.S_OK,WAIT_COUNT_OF_ITEMS,count)); 
		 
			this.setTicker(arr[TICKER]); 
			 
			if(!this.getTicker().startsWith(TChart.HEADER_PREFIX)) 
			{ 
				gl.tracex(new Object(){},String.format("%s...header format..[%s]...read...%s",gl.S_ERROR,TChart.HEADER_PREFIX,this.getTicker())); 
				 
				return false; 
			} 
			 
			gl.tracex(new Object(){},String.format("%s...header format..[%s]...read...%s",gl.S_OK,TChart.HEADER_PREFIX,this.getTicker())); 
			 
			String types = "hdwmqy"; 
			 
			this.setType(arr[TYPE].trim()); 
			 
			String read_type = this.getType().trim().substring(0,1); 
					 
			if(types.indexOf(read_type) == gl.E_ERROR) 
			{ 
			 
				gl.tracex(new Object(){},String.format("%s...type format..[h,d,w,m,q,y]...read type...%s",gl.S_ERROR,read_type)); 
				 
				return false; 
			} 
			 
			gl.tracex(new Object(){},String.format("%s...type format..[h,d,w,m,q,y]...read type...%s",gl.S_OK,read_type)); 
			 
			// Re set ticker; 
			 
			this.setTicker(Su.AfterAt(this.getTicker(),"#")); 
			 
			boolean bl_result = (this.getTicker().length() != gl.E_EMPTY && this.getType().length() != gl.E_EMPTY); 
			 
			gl.tracex(new Object(){},String.format("%s...result series...[%s]...%d/%d...result...%s",gl.S_OK,this.toString(),this.getTicker().length(),this.getType().length(),bl_result)); 
					 
			return bl_result; 
			 
	} 
	 
	public boolean get(TChart owner) 
	{ 
		if (this.read() && this.parse()) 
		{ 
			owner.setSeries(this.toString()); 

			return true; 
		} 
		 
			return false; 
	} 
	 
	public boolean get(String [] series) 
	{ 
		if (this.read() && this.parse()) 
		{ 
			series[gl.E_OK] = this.toString(); 

			return true; 
		} 
		 
			return false; 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%s,%s",this.getTicker(),this.getType()); 
	} 
	 
	 
	public static void Test_isaQuoteRow(String source) 
	{ 
		String []  response = {""}; 
		 
		String msg = String.format("Input...%s...Result...%s",source,TQuotesFileHeader.isaQuoteRow(source,response)); 
		 
		gl.smn(msg); 
	} 
	 
	public static void Test_read(String source) 
	{ 
		TQuotesFileHeader qfh = new TQuotesFileHeader(source); 
		 
		String result = ""; 
		 
		if(!qfh.read() || !qfh.parse()) 
		{ 
			gl.smn("Test error"); 
			 
			return; 
		} 
		 
		result = qfh.getRaw_text(); 
		 
		String msg = String.format("Input...%s...Raw...%s...Result...%s",source,qfh.getRaw_text(),qfh.toString()); 
				 
		gl.smn(msg); 
		 
		 
		 
		 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		String source = "e:\\bin\\spider\\look\\data\\ava\\eurusd_test.txt"; 
		 
		// Test_read(source); 
		 
		Test_isaQuoteRow("12.12.2012"); 
	} 

} 
