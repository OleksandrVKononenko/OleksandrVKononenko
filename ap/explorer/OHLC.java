 
package ap.explorer; 

import ap.global.gl; 

public class OHLC { 

	private double o; 
	 
	private double h; 
	 
	private double l; 
	 
	private double c; 
	 
	private byte[] state; 
	 
	 
	 
	public byte[] getState() { 
		return state; 
	} 

	public void setState(byte[] state) { 
		this.state = state; 
	} 

	public OHLC() { 
		 
	} 
	 
	public OHLC(double o,double h,double l,double c) { 
		 
		this.setO(o); 
		 
		this.setO(h); 
		 
		this.setO(l); 
		 
		this.setO(c); 
	 
	} 
	 
	public OHLC(double o,double h,double l,double c,byte [] states) { 
		 
		this.setO(o); 
		 
		this.setO(h); 
		 
		this.setO(l); 
		 
		this.setO(c); 
		 
		this.setState(states); 
	 
	} 
	 
	 
	public OHLC(OHLC ohlc) { 
		 
		this(ohlc.o,ohlc.h,ohlc.l,ohlc.c); 
		 
	 
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


	public String toString() 
	{ 
		return gl.sf("%.4f %.4f %.4f %.4f ", 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC()); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
