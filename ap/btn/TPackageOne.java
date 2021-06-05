 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.util.Date; 
import java.util.List; 

import ap.global.gl; 



public class TPackageOne { 

	private int id; 
	 
	private Bar  	tbi ; 
	 
	private TSbhl 		sbhl ; 
	 
	private TMa 		ma ; 
	 
	private TDays 		days ; 
	 
	private TCross 		cross ; 
		 
	 
	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public Bar getTbi() { 
		return tbi; 
	} 

	public void setTbi(Bar tbi) { 
		this.tbi = tbi; 
	} 

	public TSbhl getSbhl() { 
		return sbhl; 
	} 

	public void setSbhl(TSbhl sbhl) { 
		this.sbhl = sbhl; 
	} 

	public TMa getMa() { 
		return ma; 
	} 

	public void setMa(TMa ma) { 
		this.ma = ma; 
	} 

	public TDays getDays() { 
		return days; 
	} 

	public void setDays(TDays days) { 
		this.days = days; 
	} 

	public TCross getCross() { 
		return cross; 
	} 

	public void setCross(TCross cross) { 
		this.cross = cross; 
	} 

	public TPackageOne() { 
		 
		this.setTbi(Bar.get_instance()); 
		 
		this.setSbhl(TSbhl.getInstance()); 
		 
		this.setMa(TMa.getInstance()); 
		 
		this.setDays(TDays.getInstance()); 
		 
		this.setCross(TCross.getInstance()); 

	} 
	 
	public TPackageOne(int id) 
	{ 
		this(); 
		 
		this.setId(id); 
	} 
	 
	 
	 
	 
	public TPackageOne(Bar tbi,TSbhl sbhl,TMa ma,TDays days,TCross cross) { 
		 
		this.setTbi(tbi); 
		 
		this.setSbhl(sbhl); 
		 
		this.setMa(ma); 
		 
		this.setDays(days); 
		 
		this.setCross(cross); 

	} 
	 
	@Override 
	public String toString() 
	{ 
		String 	ms = String.format("%s, %s, %s, %s, %s", 
				this.getTbi().toString(), 
				this.getMa().toString(), 
				this.getSbhl().toString(), 
				this.getDays().toString(), 
				this.getCross().toString() 
				); 
		 
		return ms; 
	} 
	 
	 
	public static double getOByDate(List<TPackageOne>  items,Date date) 
	{ 
		for(int i=0;i< items.size();i++) 
		{ 
			if(items.get(i).getTbi().getDate().equals(date)) 
			{ 
				return items.get(i).getTbi().getO(); 
			} 
		} 
		 
			return 0.0f; 
	} 
	 
	public static int getIdByDate(List<TPackageOne>  items,Date date) 
	{ 
		for(int i=0;i< items.size();i++) 
		{ 
			if(items.get(i).getTbi().getDate().equals(date)) 
			{ 
				return items.get(i).getId(); 
			} 
		} 
		 
			return gl.E_ERROR; 
	} 
	 
	public static double getOById(List<TPackageOne>  items,int id) 
	{ 
		for(int i=0;i< items.size();i++) 
		{ 
			if(items.get(i).getId() == id) 
			{ 
				return items.get(i).getTbi().getO(); 
			} 
		} 
		 
			return 0.0f; 
	} 
	 
	public static TPackageOne getPkoById(List<TPackageOne>  items,int id) 
	{ 
		for(int i=0;i< items.size();i++) 
		{ 
			if(items.get(i).getId() == id) 
			{ 
				return items.get(i); 
			} 
		} 
		 	 
				return null; 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
