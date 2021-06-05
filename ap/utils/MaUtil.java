 
package ap.utils; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Date; 
import java.util.List; 


import java.util.stream.Collectors; 
import ap.btn.Bar; 
import ap.log.Logger; 

public class MaUtil { 

	 
	 
	public static double getAvgExTest(int id,List<Bar>  items,int metric,Logger log) 
	{ 
		 
		//gl.smn(String.format("Condition id >=  %d and id < %d",(id - metric),id)); 
		 
		List<Bar> filter = 
				items.stream().filter( 
						(a-> ( 
								a.getId() >= (id - metric) && 
								 
								a.getId() < id 
								 
								 
						))).collect(Collectors.toList()); 
				 
			log.awrite("---"); 
		 
		filter.forEach(a->{ 
		 
			log.awrite(String.format("%.4f",a.getO())); 

		}); 
		 
			log.awrite("+-+"); 
			 
			double d_result = 0.0; 
			 
			if(filter.size() == metric) 
			{ 
				d_result = filter.stream().mapToDouble(b->b.getO()).average().getAsDouble(); 
				 
			} 
			else 
			{ 
				d_result = items.get(id-1).getO(); 
			} 
			 
			 
			log.awrite(String.format("%.4f",d_result)); 
			 
			log.awrite("+-+"); 
			 
		 
			return d_result; 
				 
				 
	} 

	 
	public static double get_avg(int id,List<Bar>  items,int metric,int type) 
	{ 
		 
		List<Bar> filter = 
				items.stream().filter( 
						(a-> ( 
								a.getId() >= (id - metric) && 
								 
								a.getId() < id 
								 
								 
						))).collect(Collectors.toList()); 
				 
			double d_result = 0.0; 
			 
			if(filter.size() == metric) 
			{ 
				if(type==Bar.O) 
					d_result = filter.stream().mapToDouble(b->b.getO()).average().getAsDouble(); 
				else if(type==Bar.H) 
					d_result = filter.stream().mapToDouble(b->b.getH()).average().getAsDouble(); 
				else if(type==Bar.L) 
					d_result = filter.stream().mapToDouble(b->b.getL()).average().getAsDouble(); 
				else if(type==Bar.C) 
					d_result = filter.stream().mapToDouble(b->b.getC()).average().getAsDouble(); 
				 
			} 
			else 
			{ 
				d_result = items.get(id-1).getO(); 
				 
				if(type==Bar.O) 
					d_result = items.get(id-1).getO(); 
					else if(type==Bar.H) 
						d_result = items.get(id-1).getH(); 
					else if(type==Bar.L) 
						d_result = items.get(id-1).getL(); 
					else if(type==Bar.C) 
						d_result = items.get(id-1).getC(); 
					 
			} 
			 
		 
			return d_result; 
				 
				 
	} 
	 
	 
	public static double getAvgEx(int id,List<Bar>  items,int metric) 
	{ 
		try 
		{ 
			 
			double d_result = items.stream().filter( 
					(a-> ( 
							a.getId() >= (id - metric) && 
							 
							a.getId() < id 
							 
							 
					)) 
					).mapToDouble(b->b.getO()).average().getAsDouble(); 
			 
				return d_result; 
			 
		 
		} 
		catch( java.util.NoSuchElementException e) 
		{ 
			    return items.get(id-1).getO(); 
		} 
		 
		 
		 
	} 

	public static double getAvg(int id,List<Bar>  items,int metric) 
	{ 
		 
		return  items.stream().filter( 
				(a-> ( 
						a.getId() >= (id - metric) && 
						 
						a.getId() < id 
						 
				)) 
				).mapToDouble(b->b.getO()).average().getAsDouble(); 
		 
		 
	} 
	 
	
	 
	public static void trace(int [] v,StringBuilder sb) 
	{ 
		sb.append(" "); 
		 
		for(int j=(v.length -1); j >=0 ;j--) 
		{ 
			sb.append(v[j]); 
		} 
		 
	} 
	 
	
	 
	 
	
	 
	public MaUtil() { 
		 
	} 

	public static void main(String[] args) { 
	 
		//doV2_1("e:\\exp\\data\\eurusd",new TDateRange("01.01.2015","31.12.2015")); 

	} 

} 
// Revision : 10.09.2018 12:49:17 
