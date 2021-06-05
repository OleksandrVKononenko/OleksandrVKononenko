 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Dimension; 

public class AreaPointIndex { 

	private Dimension size; 
	 
	private int index; 
	 
	public Dimension getSize() { 
		return size; 
	} 

	public void setSize(Dimension size) { 
		this.size = size; 
	} 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

	 
	public AreaPointIndex() 
	{ 
		 
	} 
	 
	public AreaPointIndex(Dimension size,int index) 
	{ 
		this.setSize(size); 
		 
		this.setIndex(index); 
		 
	} 
	 
	public String toString() 
	{ 
		return this.getSize().toString() + "["+this.getIndex()+"]"; 
	} 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
