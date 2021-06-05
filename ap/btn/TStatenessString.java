 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

public class TStatenessString { 

	private String value; 
	 
	private int state; 
		 
	public String getValue() { 
		return value; 
	} 

	public void setValue(String value) { 
		this.value = value; 
	} 

	public int getState() { 
		return state; 
	} 

	public void setState(int state) { 
		this.state = state; 
	} 

	public TStatenessString() { 
		 
	} 

	public TStatenessString(String value,int state) { 
		 
		this.setValue(value); 
		 
		this.setState(state); 
		 
	} 
	 
	public static TStatenessString getInstance(String value,int state) 
	{ 
		return new TStatenessString(value,state); 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%s,d",this.getValue(),this.getState()); 
	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
