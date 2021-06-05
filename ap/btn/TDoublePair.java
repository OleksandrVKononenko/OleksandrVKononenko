 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.List;

import ap.explorer.Range;
import ap.global.gl; 
import ap.utils.DateUtil; 

public class TDoublePair { 

	private double open; 
	 
	private double high; 
	 
	private double low; 
	 
	private double close; 
	 
	private Date date; 
	 
	 

	public Date getDate() { 
		return date; 
	} 

	public void setDate(Date date) { 
		this.date = date; 
	} 

	public double getOpen() { 
		return open; 
	} 

	public void setOpen(double open) { 
		this.open = open; 
	} 

	public double getClose() { 
		return close; 
	} 

	public void setClose(double close) { 
		this.close = close; 
	} 

	public double getHigh() { 
		return high; 
	} 

	public void setHigh(double high) { 
		this.high = high; 
	} 

	public double getLow() { 
		return low; 
	} 

	public void setLow(double low) { 
		this.low = low; 
	} 

	public TDoublePair() { 
		 
		this.setLow(gl.E_EMPTY); 
		 
		this.setHigh(gl.E_EMPTY); 
		 
	} 

	public TDoublePair(double low,double high) { 
	 
		this.setLow(low); 
		 
		this.setHigh(high); 
		 
	} 
	 
	public TDoublePair(double open,double high,double low,double close) { 
	 
		this.setOpen(open); 
		 
		this.setHigh(high); 
		 
		this.setLow(low); 
		 
		this.setClose(close); 
				 
	} 

	@Override 
	public String toString() 
	{ 
		 
		return String.format("%s %s", 
				gl.format4d(this.getLow(),gl.AL.RIGHT,8), 
				gl.format4d(this.getHigh(),gl.AL.RIGHT,8) 
				); 
	} 
	 
	public String toStringDeal() 
	{ 
		 return String.format("%s %s", 
				 DateUtil.to_string(this.getDate()), 
				gl.format4d(this.getClose(),gl.AL.RIGHT,8) 
				); 
	} 
	 
	public static void Test_HighAndLow(String ticker,Range range) 
	{ 
		List<TPackageOne>  pkos = new ArrayList<TPackageOne>(); 
		 
		List<TPackageOne>  filter = new ArrayList<TPackageOne>(); 
		 
		// Load quotes of ticker. 
		if(!TPackageOneManager.createPackageOne(ticker,false,pkos)) 
		{ 
			gl.tx_e(new Object(){},"Error while load quotes."); 
			 
			return ; 
		} 
			 
		if(!TPackageOneManager.acceptFilter(pkos,filter,range)) 
			return; 
		 
		TDoublePair dp = TPackageOneManager.getHighLow(pkos,range); 
		 
		gl.smn("Result is : " + dp.toString()); 
		 
		 
	} 
	 
	public  boolean isEmpty() 
	{ 
		return (getLow() == 0.0 && getHigh() == 0.0); 
	} 
	 
	 
	public static void Test_closeDealByHigh(String ticker,Range range) 
	{ 
		List<TPackageOne>  pkos = new ArrayList<TPackageOne>(); 
		 
		List<TPackageOne>  filter = new ArrayList<TPackageOne>(); 
		 
		// Load quotes of ticker. 
		if(!TPackageOneManager.createPackageOne(ticker,false,pkos)) 
		{ 
			gl.tx_e(new Object(){},"Error while load quotes."); 
			 
			return ; 
		} 
			 
		if(!TPackageOneManager.acceptFilter(pkos,filter,range)) 
			return; 
		 
		TDoublePair dp = TPackageOneManager.getDealCloseDateByHigh(filter); 
		 
		gl.smn("Result is : " + dp.toStringDeal()); 
		 
		 
	} 
	 
	public static void Test_closeDealByLow(String ticker,Range range) 
	{ 
		List<TPackageOne>  pkos = new ArrayList<TPackageOne>(); 
		 
		List<TPackageOne>  filter = new ArrayList<TPackageOne>(); 
		 
		// Load quotes of ticker. 
		if(!TPackageOneManager.createPackageOne(ticker,false,pkos)) 
		{ 
			gl.tx_e(new Object(){},"Error while load quotes."); 
			 
			return ; 
		} 
			 
		if(!TPackageOneManager.acceptFilter(pkos,filter,range)) 
			return; 
		 
		TDoublePair dp = TPackageOneManager.getDealCloseDateByLow(filter); 
		 
		gl.smn("Result is : " + dp.toStringDeal()); 
		 
		 
	} 
	 
	 
	public boolean  parse(String value) 
	{ 
		String [] arr = value.split("-"); 
		 
		if(arr.length != 2) 
			return false; 
		 
		int  LOW = 0, HIGH =1; 
		 
		try 
		{ 
			this.setLow(Double.parseDouble(arr[LOW])); 
		 
			this.setHigh(Double.parseDouble(arr[HIGH])); 
			 
			return true; 
		} 
		 
		 catch ( java.lang.NumberFormatException e) 
		{ 
			 return false; 
		} 

	} 
	 
	 
	public static void Test_parse(String take_value,String stop_value) 
	{ 
		TDoublePair dp_take = new TDoublePair(); 
		 
		TDoublePair dp_stop = new TDoublePair(); 
		 
		 
		if(! 
				( dp_take.parse(take_value) && 
			      dp_stop.parse(stop_value) 
			    )	 
			) 
		{ 
			String take_msg = String.format("Take Input : %s Output : %s ",take_value,gl.S_ERROR); 
			 
			gl.smn(take_msg); 
			 
			String stop_msg = String.format("Take Stop : %s Output : %s ",stop_value,gl.S_ERROR); 
			 
			gl.smn(stop_msg); 
			 
			return; 
		} 

		String take_msg = String.format("Take Input : %s Output : %s ",take_value,dp_take.toString()); 
		 
		gl.smn(take_msg); 
		 
		String stop_msg = String.format("Take Stop : %s Output : %s ",stop_value,dp_stop.toString()); 
		 
		gl.smn(stop_msg); 
		 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		//Test_HighAndLow("e:\\exp\\data\\cat",new Range("05.06.1997","19.06.1997")); 
		 
		//Test_closeDealByLow("e:\\exp\\data\\usdchf",new Range("16.01.2015","21.01.2015")); 
		 
		//Test_closeDealByHigh("d:\\exp\\data\\usdchf",new Range("01.01.1998","15.01.1998")); 
		 
		Test_parse("156.0022 - 157.0080","156.0022 - 157.0080"); 
		 
		Test_parse("156.0022 - ","156.0022 - "); 
		 
		Test_parse("0.0010 - 0.0090","0.0110 - 0.0290"); 
		 
		Test_parse("0.0010 - 0.0110","0.0210 - 0.0310"); 
		 
		 
	} 

} 
// Revision : 10.09.2018 12:49:13 
