 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import ap.global.gl; 


public class TIndexRange { 

	private int start; 
	 
	private int end; 
	 
	private int max; 
	 
	private int offset = gl.E_EMPTY; 
	 
	public static int last_right_index = gl.E_ERROR; 
	 
	 
	public int getOffset() { 
		return offset; 
	} 

	public void setOffset(int offset) { 
		this.offset = offset; 
	} 

	public int getMax() { 
		return max; 
	} 

	public void setMax(int max) { 
		this.max = max; 
	} 

	public int getStart() { 
		return start; 
	} 

	public void setStart(int start) { 
		this.start = start; 
	} 

	public int getEnd() { 
		return end; 
	} 

	public void setEnd(int end) { 
		this.end = end; 
	} 

	public TIndexRange() { 
		 
	} 
	 
	public TIndexRange(int start,int end) { 
		 
		this.setStart(start); 
		 
		this.setEnd(end); 
	} 
	 
	public TIndexRange(int start,int end ,int max) { 
		 
		this(start,end); 
		 
		this.setMax(max); 
		 
		this.setOffset(start); 
		 
		TIndexRange.last_right_index = end; 
		 
	} 
	 
	/* 
	public TIndexRange(int start,int end ,int max,int offset) { 
		 
		this(start,end,max); 
		 
		this.setOffset(offset); 
		 
	} 
	*/ 
	 
	/* 
	public TIndexRange(int start,int end ,int max) { 
		 
		this(start,end,max); 
		 
		this.setOffset(offset); 
		 
	} 
	*/ 

	 

	public void clear() 
	{ 
		 
		this.setStart(gl.E_EMPTY); 
		 
		this.setEnd(gl.E_EMPTY); 
		 
		this.setMax(gl.E_EMPTY); 
		 
		this.setOffset(gl.E_EMPTY); 
		 
	} 

	 
	@Override 
	public String toString() 
	{ 
		return String.format("%d,%d",this.getStart(),this.getEnd()); 
		 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
