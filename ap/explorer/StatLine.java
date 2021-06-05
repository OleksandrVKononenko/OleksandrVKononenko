 
package ap.explorer; 

import ap.btn.TDateRange; 

public class StatLine { 

	private int type; 
	 
	private TDateRange range; 
	 
	private int days; 
	 
	private OHLC ohlc; 
	 
	private double value; 
	 
	 
	 
	public OHLC getOhlc() { 
		return ohlc; 
	} 

	public void setOhlc(OHLC ohlc) { 
		this.ohlc = ohlc; 
	} 

	public int getDays() { 
		return days; 
	} 

	public void setDays(int days) { 
		this.days = days; 
	} 

	public double getValue() { 
		return value; 
	} 

	public void setValue(double value) { 
		this.value = value; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	public TDateRange getRange() { 
		return range; 
	} 

	public void setRange(TDateRange range) { 
		this.range = range; 
	} 

	public StatLine() { 
		 
	} 
	 
	public StatLine(int type,TDateRange range) { 
		 
		this.setType(type); 
		 
		this.setRange(range); 
	} 
	 
	public StatLine(int type,TDateRange range,OHLC ohlc) { 

		this(type,range); 
		 
		this.setOhlc(ohlc); 
	} 
	 
	public static StatLine getInstance(int type,TDateRange range,OHLC ohlc) { 
		 
		return new StatLine(type,range,ohlc); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%d %s %d %.4f", 
				this.getType(), 
				this.getRange().toString(), 
				this.getDays(), 
				this.getValue() 
				); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
