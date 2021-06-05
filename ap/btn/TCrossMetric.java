 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.List; 
import java.util.Locale; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TCrossMetric { 


	public static final int M3 = 1; 
	 
	public static final int M5 = 2; 
	 
	public static final int M7 = 3; 
	 
	public static final int M10 = 4; 
	 
	public static final int M14 = 5; 
	 
	public static final int M21 = 6; 
	 
	public static final int M33 = 7; 
	 
	public static final int M55 = 8; 
	 
	public static final int M100 = 9; 
	 
	public static final int M144 = 10; 
	 
	public static final int M200 = 11; 
	 
	 
	private int type; 
	 
	private int source; 
	 
	private int target; 
	 
	private Date begin; 
	 
	private Date end; 
	 
	private long durability; 
	 

	public int getSource() { 
		return source; 
	} 

	public void setSource(int source) { 
		this.source = source; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	public int getTarget() { 
		return target; 
	} 

	public void setTarget(int target) { 
		this.target = target; 
	} 

	public Date getBegin() { 
		return begin; 
	} 

	public void setBegin(Date begin) { 
		this.begin = begin; 
	} 

	public Date getEnd() { 
		return end; 
	} 

	public void setEnd(Date end) { 
		this.end = end; 
	} 

	public long getDurability() { 
		return durability; 
	} 

	public void setDurability(long durability) { 
		this.durability = durability; 
	} 

	public TCrossMetric() { 
		 
	} 
	 
	public TCrossMetric(int type,int source,int target,Date begin) { 
		 
		this.setType(type); 
		 
		this.setSource(source); 
		 
		this.setTarget(target); 
		 
		this.setBegin(begin); 
		 
		this.setEnd(null); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%01d,%02d,%02d,%s,%s,%d", 
				this.getType(), 
				this.getSource(), 
				this.getTarget(), 
				DateUtil.to_string(this.getBegin()), 
				(this.getEnd()==null)? "" : DateUtil.to_string(this.getEnd()), 
				(this.getDurability()==gl.E_EMPTY) ? "," : this.getDurability() 
				); 
	} 
	 
	// Setup of type of cross 0->1 || 1->0 
	public static void getInstances(TCross a ,TCross b,List<TCrossMetric> list) 
	{ 
		 
		if(a==null || b==null) 
			return; 
		 
		Date c = b.getDt(); 
		 
		final int NONE = 3; 
		 
		int i_cross_type[] = {NONE}; 
		 
		 
			 
		TConfiguration.metrics.forEach(aa-> 
		{ 
			 
			if (TCross.getValueByType(a,aa) == gl.E_OK && TCross.getValueByType(b,aa) == gl.E_EMPTY) 
			{ 
				i_cross_type[0] = TCross.DOWN; 
			} 
			else if (TCross.getValueByType(a,aa) == gl.E_EMPTY && TCross.getValueByType(b,aa) == gl.E_OK) 
			{ 
				i_cross_type[0] = TCross.UP; 
			} 
		 
			 
		if(i_cross_type[0] != NONE ) 
		{ 
			 
			TCrossMetric tcm = new TCrossMetric(i_cross_type[0],Bar.O,aa,c); 
			 
			list.add(tcm); 
			 
			i_cross_type[0] = NONE; 
			 
		} 
		 
		});// for 

	}		 
	 
	public static Date getDateByType(List<TCrossMetric> list , int target,int type,Date input_date) 
	{ 

		 
		for(int i=0;i<list.size();i++) 
		{ 
			int tp = (type == TCross.UP ) ? TCross.DOWN : TCross.UP ; 
			 
			TCrossMetric cm = list.get(i); 
			 
			if(cm.getTarget() == target && cm.getType() == tp &&  cm.getBegin().after(input_date)) 
			{ 
				return cm.getBegin(); 
			} 
			 
		} 
				return null; 
	} 
		 
		 
		 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
