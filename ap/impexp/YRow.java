 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.impexp; 

import ap.global.gl; 
import ap.utils.Su; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 11 ????. 2016 ?. 16:27:42 
* Project  name : Organizer 
* Package  name : ap.impexp 
* File     name : YRow.java 
* 
*/ 
//Date,Open,High,Low,Close,Volume,Adj Close 

	public class YRow 
	{ 
		private String Ticker; 
		 
		private String Date; 
		 
		private String Open; 
		 
		private String High; 
		 
		private String Low; 
		 
		private String Close; 
		 
		private String Volume; 
		 
		private String Value; 
		 
		private String Brew; 
		 
		public static final int TICKER=0; 
		 
		public static final int DATE=0; 
		 
		public static final int OPEN=1; 
		 
		public static final int HIGH=2; 
		 
		public static final int LOW=3; 
		 
		public static final int CLOSE=4; 
		 
		public static final int VOLUME=5; 
		 

		 
		/** 
		 * @return the brew 
		 */ 
		public String getBrew() { 
			return Brew; 
		} 

		/** 
		 * @param brew the brew to set 
		 */ 
		public void setBrew(String brew) { 
			Brew = brew; 
		} 

		/** 
		 * @return the value 
		 */ 
		public String getValue() { 
			return Value; 
		} 

		/** 
		 * @param value the value to set 
		 */ 
		public void setValue(String value) { 
			Value = value; 
		} 

		/** 
		 * 
		 */ 
		public YRow() 
		{ 
			 
		} 
		 
		public String toString() { 
			String result = String.format("%s,%s,%s,%s,%s,%s,%s,%s", 
					this.getTicker().toUpperCase(), this.getDate(), this.getOpen(), 
					this.getHigh(), this.getLow(), this.getClose(), 
					this.getVolume(),this.getBrew()); 

			return result; 
		} 

		public YRow(String value) 
		{ 
			this.setValue(value); 
		} 

		public boolean split(boolean trunc) 
		{ 
			 
			if(this.getValue()==null) 
				return false; 
				 
			String[] arr = this.getValue().split(","); 
			 
			if (arr.length== gl.E_EMPTY) 
				return false; 
			 
			this.setDate(arr[DATE].replace("-","")); 
			 
			if(trunc) 
			{ 
				this.setOpen(Su.BeforeAtPeriod(arr[OPEN])); 

				this.setHigh(Su.BeforeAtPeriod(arr[HIGH])); 

				this.setLow(Su.BeforeAtPeriod(arr[LOW])); 

				this.setClose(Su.BeforeAtPeriod(arr[CLOSE])); 
			} 
			else 
			{ 
				this.setOpen(Su.StringRound(arr[OPEN],2)); 

				this.setHigh(Su.StringRound(arr[HIGH],2)); 

				this.setLow(Su.StringRound(arr[LOW],2)); 

				this.setClose(Su.StringRound(arr[CLOSE],2)); 
			} 
			 
			this.setVolume(arr[VOLUME]); 
			 
			this.setBrew(this.getBrew()); 
			 
			 
			return true; 
			 
			 
		} 
		 
		/** 
		 * @return the name 
		 */ 
		public String getTicker() { 
			return Ticker; 
		} 

		/** 
		 * @param name the name to set 
		 */ 
		public void setTicker(String ticker) { 
			Ticker = ticker; 
		} 

		/** 
		 * @return the date 
		 */ 
		public String getDate() { 
			return Date; 
		} 

		/** 
		 * @param date the date to set 
		 */ 
		public void setDate(String date) { 
			Date = date; 
		} 

		/** 
		 * @return the open 
		 */ 
		public String getOpen() { 
			return Open; 
		} 

		/** 
		 * @param open the open to set 
		 */ 
		public void setOpen(String open) { 
			Open = open; 
		} 

		/** 
		 * @return the high 
		 */ 
		public String getHigh() { 
			return High; 
		} 

		/** 
		 * @param high the high to set 
		 */ 
		public void setHigh(String high) { 
			High = high; 
		} 

		/** 
		 * @return the low 
		 */ 
		public String getLow() { 
			return Low; 
		} 

		/** 
		 * @param low the low to set 
		 */ 
		public void setLow(String low) { 
			Low = low; 
		} 

		/** 
		 * @return the close 
		 */ 
		public String getClose() { 
			return Close; 
		} 

		/** 
		 * @param close the close to set 
		 */ 
		public void setClose(String close) { 
			Close = close; 
		} 

		/** 
		 * @return the volume 
		 */ 
		public String getVolume() { 
			return Volume; 
		} 

		/** 
		 * @param volume the volume to set 
		 */ 
		public void setVolume(String volume) { 
			Volume = volume; 
		} 
		 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
