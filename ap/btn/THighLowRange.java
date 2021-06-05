 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

public class THighLowRange { 
	 
	 
	private double high; 
	 
	private double low; 
	 
	private int gap; 
	 
	 
	 
	public int getGap() { 
		return gap; 
	} 

	public void setGap(int gap) { 
		this.gap = gap; 
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

	public THighLowRange() { 
		 
	} 
	 
	public THighLowRange(double high,double low) { 
		 
		this.setHigh(high); 
		 
		this.setLow(low); 
		 
		double ga = (this.getHigh() - this.getLow())*TChart.EUR_MULTIPLIER; 
		 
		this.setGap((int)ga); 
		 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%.4f , %.4f %d ",this.getHigh(),this.getLow(),this.getGap()); 
	} 
	 
	 
	public static void main(String[] args) { 
	 
	} 

} 
