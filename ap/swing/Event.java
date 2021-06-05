 
 
 
 
 
 
 
 
 
 
package ap.swing; 


import java.util.Date; 
import java.util.Locale; 

import ap.gen.EventId; 
import ap.global.gl; 
import ap.ma.ICross; 
import ap.ma.IMa; 
import ap.utils.DU; 
import ap.utils.DateUtil; 

public class Event { 
	 
	private int id; 
	 
	private Date dt; 
	 
	private int left; 
	 
	private int right; 
	 
	private int cross; 
	 
	private boolean bl_ma; 
	 
	private int group; 
	 
	private int igid; 
	 
	private String sgid; 
	 
	private String guid; 
	 
	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	 
	public String getSgid() { 
		return sgid; 
	} 

	public void setSgid(String sgid) { 
		this.sgid = sgid; 
	} 

	public String getGuid() { 
		return guid; 
	} 

	public void setGuid(String guid) { 
		this.guid = guid; 
	} 

	public int getIGid() { 
		return igid; 
	} 

	public void setIGid(int gID) { 
		igid = gID; 
	} 
	 

	public int getGroup() { 
		return group; 
	} 

	public void setGroup(int group) { 
		this.group = group; 
	} 

	public boolean isBl_ma() { 
		return bl_ma; 
	} 

	public void setBl_ma(boolean bl_ma) { 
		this.bl_ma = bl_ma; 
	} 

	public int getLeft() { 
		return left; 
	} 

	public void setLeft(int left) { 
		this.left = left; 
	} 

	public int getRight() { 
		return right; 
	} 

	public void setRight(int right) { 
		this.right = right; 
	} 

	public int getCross() { 
		return cross; 
	} 

	public void setCross(int cross) { 
		this.cross = cross; 
	} 

	 
	public Date getDt() { 
		return dt; 
	} 

	public void setDt(Date dt) { 
		this.dt = dt; 
	} 

	 
	public  Event () 
	{ 
		this.setId(EventId.nextId()); 
	} 

	public  Event (Date dt) 
	{ 
		this(); 
		 
		this.setDt(dt); 
	} 

	public  Event (Date dt,int left,int right,int cross) 
	{ 
		this(dt); 
		 
		this.setLeft(left); 
		 
		this.setRight(right); 
		 
		this.setCross(cross); 
		 
	} 
	 
	public  Event (Date dt,int left,int right,int cross,int group) 
	{ 
		this(dt,left,right,cross); 
		 
		this.setGroup(group); 
		 
	} 
	 
	public  Event (Date dt,int left,int right,int cross,boolean bl_ma) 
	{ 
		this(dt,left,right,cross); 
		 
		this.setBl_ma(bl_ma); 
		 
		init_guides(); 
	} 
	 
	public  Event (Date dt,int left,int right,int cross,boolean bl_ma,int group ) 
	{ 
		this(dt,left,right,cross,bl_ma); 
		 
		this.setGroup(group); 
		 
		init_guides(); 
	} 
	 
	 
	public static Event get_instance() 
	{ 
		return new Event(); 
	} 
	 
	public static Event get_instance(Date dt) 
	{ 
		return new Event(dt); 
	} 

	 
	public static Event get_instance(Date dt,int left,int right,int cross) 
	{ 
		return new Event(dt,left,right,cross); 
	} 
	 
	public static Event get_instance(Date dt,int left,int right,int cross,int group) 
	{ 
		return new Event(dt,left,right,cross,group); 
	} 
	 
	 
	public static Event get_instance(Date dt,int left,int right,int cross,boolean bl_ma) 
	{ 
		return new Event(dt,left,right,cross,bl_ma); 
	} 
	 
	public static Event get_instance(Date dt,int left,int right,int cross,boolean bl_ma,int group) 
	{ 
		return new Event(dt,left,right,cross,bl_ma,group); 
	} 
	 
	 
	public String make_s_guid() 
	{ 
		return gl.sf("%s %d %d", 
				this.isBl_ma() ? gl.sf("%d %d",this.getGroup(),this.getLeft()) : gl.sf("%d %d",this.getLeft(),this.getLeft()), 
						this.getCross(), 
						this.getRight() 
						); 
		 
	} 
	public String make_guid() 
	{ 
	 
	return gl.sf("%s%1s%s", 
			this.isBl_ma() ? gl.sf("%s%d",ICross.indexOf(this.getGroup()),IMa.indexOfget(this.getLeft())) : ICross.indexOf(this.getLeft()), 
			ICross.indexOfCt(this.getCross()), 
			IMa.indexOfget(this.getRight())); 
			 
	} 

	 
	public int make_i_guid() 
	{ 
		 
		return DU.to_int(this.getSgid().replace(" ","")); 
			 
	} 
	 
	private void init_guides() 
	{ 
		this.setSgid(make_s_guid()); 
		 
		this.setIGid(make_i_guid()); 
		 
		this.setGuid(make_guid()); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return gl.sf("%6d %s %04d %6s", 
				 
				this.getId(), 
				DateUtil.to_string(this.getDt()), 
				this.getIGid(), 
				this.getGuid() 
				 
				); 
		 
		 
		} 

} 
