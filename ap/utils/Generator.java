 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

public class Generator { 

	 
	public static String getOrderedNumberSequence(int low,int high,char delimeter) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(int i=low;i<high;i++) 
		{ 
			if(i <= (high-1)) 
			   sb.append(String.format("%d%s",i,delimeter)); 
			else 
			   sb.append(String.format("%d%s",i,"")); 
				 
		} 
		 
			return sb.toString(); 
		 
	} 
	 
	public Generator() { 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
