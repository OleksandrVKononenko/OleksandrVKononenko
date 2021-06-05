 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.btn; 

import java.util.ArrayList; 
import java.util.List; 

import ap.global.gl; 


public class TRange { 

	 
	public static final int 
		R35 	= 1, 
		R510 	= 2, 
		R1014 	= 3, 
		R1421 	= 4, 
		R2133 	= 5, 
		R3355 	= 6, 
		R55100 	= 7, 
		R100144 = 8, 
		R144200 = 9, 
		R200730 = 10, 
		R1421_141516 	= 11, 
		R1421_1718 		= 12, 
		R1421_192021	= 13, 
		 
		R2133_212223 	= 14, 
		R2133_242526 	= 15, 
		R2133_272829 	= 16, 
		R2133_30313233 	= 17; 
		 
		// 
		 
	private int from; 
	 
	private int to; 
	 
	 
	public int getFrom() { 
		return from; 
	} 

	public void setFrom(int from) { 
		this.from = from; 
	} 

	public int getTo() { 
		return to; 
	} 

	public void setTo(int to) { 
		this.to = to; 
	} 

	 
	@Override 
	public String toString() 
	{ 
		return String.format("%03d/%03d", 
				this.getFrom(), 
				this.getTo() 
				); 
	} 
	 
	 
	public TRange() { 
		 
	} 

	public TRange(int from,int to) { 
		 
		this.setFrom(from); 
		 
		this.setTo(to); 
	} 
	 
	public static TRange getInstance(int type) 
	{ 
		switch(type) 
		{ 
		case TRange.R35: 
			return new TRange(3,5); 
		 
		case TRange.R510: 
			return new TRange(5,10); 
		 
		case TRange.R1014: 
			return new TRange(10,14); 
		 
		case TRange.R1421 	: 
			return new TRange(14,21); 
			 
		case TRange.R2133   : 
			return new TRange(21,33); 
			 
		case TRange.R3355 	: 
			return new TRange(33,55); 
			 
		case TRange.R55100 	: 
			return new TRange(55,100); 
			 
		case TRange.R100144 : 
			return new TRange(100,144); 
			 
		case TRange.R144200 : 
			return new TRange(144,200); 
			 
		case TRange.R200730 : 
			return new TRange(200,730); 
			 
		case TRange.R1421_141516 : 
			return new TRange(14,16); 
			 
		case TRange.R1421_1718 : 
			return new TRange(17,18); 
			 
		case TRange.R1421_192021	 : 
			return new TRange(19,21); 
			 
		case TRange.R2133_212223: 
			return new TRange(21,23); 
			 
		case TRange.R2133_242526 : 
			return new TRange(24,26); 
			 
		case TRange.R2133_272829 : 
			return new TRange(27,29); 
			 
		case TRange.R2133_30313233 : 
			return new TRange(30,33); 
			 
		} 
			return null; 
	} 
	 
	public static TRange getInstance(int from,int to) 
	{ 
		return new TRange(from,to); 
	} 
	 
	 
	public int getType() 
	{ 
		int f = this.getFrom(); 
		 
		int t = this.getTo(); 
		 
		if (f==3 && t ==5) 
			return TRange.R35; 
		 else if (f==5 && t ==10) 
			return TRange.R510; 
		 else if (f==10 && t ==14) 
				return TRange.R1014; 
		 else if (f==14 && t ==21) 
				return TRange.R1421; 
		 else if (f==21 && t ==33) 
				return TRange.R2133; 
		 else if (f==33 && t ==55) 
				return TRange.R3355; 
		 else if (f==55 && t ==100) 
				return TRange.R55100; 
		 else if (f==100 && t ==144) 
				return TRange.R100144; 
		 else if (f==144 && t ==200) 
				return TRange.R144200; 
		 else if (f==200 && t ==730) 
				return TRange.R200730; 
		 else if (f==14 && t ==16) 
				return TRange.R1421_141516; 
		 else if (f==17 && t ==18) 
				return TRange.R1421_1718; 
		 else if (f==19 && t ==21) 
				return TRange.R1421_192021; 
		 else if (f==21 && t == 23)		  	 
				return TRange.R2133_212223; 
		 else if (f==24 && t ==26) 
				return TRange.R2133_242526; 
		 else if (f==27 && t ==29) 
				return TRange.R2133_272829; 
		 else if (f==30 && t == 33) 
				return TRange.R2133_30313233 ; 
			 
		return gl.E_ERROR; 
	} 
	 
	public static List<TRange> getShortListOfInstances() 
	{ 
		List<TRange> list = new ArrayList<TRange>(); 
		 
		list.add(TRange.getInstance(14,21)); 
		 
		list.add(TRange.getInstance(21,33)); 
		 
		 
		return list; 
	} 
	 
	public static List<TRange> getDetailedListOfInstances() 
	{ 
		List<TRange> list = new ArrayList<TRange>(); 
		 
		list.add(TRange.getInstance(TRange.R1421_141516)); 
		 
		list.add(TRange.getInstance(TRange.R1421_1718)); 
		 
		list.add(TRange.getInstance(TRange.R1421_192021)); 
		 
		list.add(TRange.getInstance(21,23)); 
		 
		list.add(TRange.getInstance(24,26)); 
		 
		list.add(TRange.getInstance(27,29)); 
		 
		list.add(TRange.getInstance(30,33)); 
		 
		return list; 
	} 
	 
	public static List<TRange> getFullListOfInstances() 
	{ 
		List<TRange> list = getListOfInstances(); 
		 
		list.addAll(getDetailedListOfInstances()); 
		 
		return list; 
	} 
	 
	public static List<TRange> getListOfInstances() 
	{ 
		List<TRange> list = new ArrayList<TRange>(); 
		 
		list.add(TRange.getInstance(3,5)); 
		 
		list.add(TRange.getInstance(5,10)); 
		 
		list.add(TRange.getInstance(10,14)); 
		 
		list.add(TRange.getInstance(14,21)); 
		 
		list.add(TRange.getInstance(21,33)); 
		 
		list.add(TRange.getInstance(33,55)); 
		 
		list.add(TRange.getInstance(55,100)); 
		 
		list.add(TRange.getInstance(100,144)); 
		 
		list.add(TRange.getInstance(144,200)); 
		 
		list.add(TRange.getInstance(200,730)); 
		 
		return list; 
		 
	} 
	 
	 
	public boolean in(int value) 
	{ 
		return ( this.getFrom() <= value && this.getTo() >= value); 
	} 

	public boolean in(long value) 
	{ 
		return this.in((int)value); 
	} 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
