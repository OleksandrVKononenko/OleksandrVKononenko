 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 


import ap.global.gl; 
import ap.utils.CandleUtil; 

public class TSbhl { 

	private int dt; 
	 
	private int st; 
	 
	private int sign; 
	 
	private double body; 
	 
	private double high; 
	 
	private double low; 
	 

	public int getDt() { 
		return dt; 
	} 




	public void setDt(int dt) { 
		this.dt = dt; 
	} 




	public int getSt() { 
		return st; 
	} 




	public void setSt(int st) { 
		this.st = st; 
	} 




	public int getSign() { 
		return sign; 
	} 




	public void setSign(int sign) { 
		this.sign = sign; 
	} 




	public double getBody() { 
		return body; 
	} 




	public void setBody(double body) { 
		this.body = body; 
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




	public TSbhl() { 
		 
	} 
	 
	public TSbhl(int sign, double body, double high,double low ) { 
		 
		this.setDt(gl.E_ERROR); 
		 
		this.setSt(gl.E_ERROR); 
		 
		this.setSign(sign); 
		 
		this.setBody(body); 
		 
		this.setHigh(high); 
		 
		this.setLow(low); 
		 
		 
		 
	} 
	 
	public TSbhl(int dt,int st,int sign, double body, double high,double low ) { 
		 
		 
		this(sign,body,high,low ); 
		 
		this.setDt(dt); 
		 
		this.setSt(st); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%d,%.4f,%.4f,%.4f", 
				this.getSign(), 
				this.getBody(), 
				this.getHigh(), 
				this.getLow() 
				); 
	} 
	 
	public static TSbhl getInstance(int sign, double body, double high,double low ) 
	{ 
		return new TSbhl(sign, body, high, low); 
	} 
	 
	public static TSbhl getInstance() 
	{ 
		return new TSbhl(-1,-1,-1,-1.0d,-1.0d,-1.0d); 
	} 
	 
	 
	public static TSbhl getInstance(int dt,int st,int sign, double body, double high,double low ) 
	{ 
		return new TSbhl(dt, st, sign, body, high, low); 
	} 
	 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
