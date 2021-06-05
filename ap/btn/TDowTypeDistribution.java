 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.btn; 

public class TDowTypeDistribution { 

	private int dow; 
	 
	private int type; 
	 
	public int getDow() { 
		return dow; 
	} 

	public void setDow(int dow) { 
		this.dow = dow; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	public TDowTypeDistribution() { 
		 
	} 
	 
	public TDowTypeDistribution(int dow,int type) { 
		 
		this.setDow(dow); 
		 
		this.setType(type); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%01d %02d",this.getDow(),this.getType()); 
	} 

	@Override 
	public boolean equals(Object value) 
	{ 
		return ( 
				this.getDow()==((TDowTypeDistribution)value).getDow() && 
				this.getType() == ((TDowTypeDistribution)value).getType() 
				); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
