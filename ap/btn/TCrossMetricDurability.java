 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.btn; 

import java.util.Date; 

import ap.global.gl; 

public class TCrossMetricDurability { 

	private int type; 
	 
	private int source; 
	 
	private int target; 
	 
	 
	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	public int getSource() { 
		return source; 
	} 

	public void setSource(int source) { 
		this.source = source; 
	} 

	public int getTarget() { 
		return target; 
	} 

	public void setTarget(int target) { 
		this.target = target; 
	} 


	public TCrossMetricDurability() { 
		 
	} 

	public TCrossMetricDurability(int type,int source,int target) { 
		 
		this.setType(type); 
		 
		this.setSource(source); 
		 
		this.setTarget(target); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%01d,%02d,%02d,%s", 
					this.getType(), 
					this.getSource(), 
					this.getTarget() 
				); 
	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
