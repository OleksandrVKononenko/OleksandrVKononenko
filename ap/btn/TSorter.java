 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Comparator; 
import java.util.List; 

public class TSorter { 

	public static List<TOrderStat> getSortedByResult(boolean asc) 
	{ 
		Comparator<TOrderStat> tos = null; 
		 
		int rate[] = {1}; 

		if (asc) 
			tos = ( TOrderStat a1,TOrderStat  b1) -> Double.compare(a1.getResult(), b1.getResult()); 
		else 
			tos = (a1,b1) -> Double.compare(b1.getResult(), a1.getResult()); 
		 
		List<TOrderStat> result = new ArrayList<TOrderStat> (); 
		 
		TConfiguration.ratings.stream().sorted(tos).forEach(s-> 
		{ 
			s.setRating(rate[0]++); 
		 
			result.add(s); 
			 
		} 
		); 
				 
		return result; 
		 
	} 

	public static List<TOrderStat> getSortedByEv(boolean asc) 
	{ 
		Comparator<TOrderStat> tos = null; 
		 
		int rate[] = {1}; 

		if (asc) 
			tos = ( TOrderStat a1,TOrderStat  b1) -> Double.compare(a1.getEv(), b1.getEv()); 
		else 
			tos = (TOrderStat a1,TOrderStat  b1) -> Double.compare(b1.getEv(), a1.getEv()); 
		 
		List<TOrderStat> result = new ArrayList<TOrderStat> (); 
		 
		TConfiguration.ratings.stream().sorted(tos).forEach(s-> 
		{ 
			s.setRating(rate[0]++); 
		 
			result.add(s); 
			 
		} 
		); 
				 
						 
		return result; 
		 
	} 
	 
	public static List<TLightOrderStat> getSortedByRslt(List<TLightOrderStat> list,boolean asc) 
	{ 
		Comparator<TLightOrderStat> tos = null; 
		 
		int rate[] = {1}; 

		if (asc) 
			tos = ( TLightOrderStat a1,TLightOrderStat  b1) -> Double.compare(a1.getResult(), b1.getResult()); 
		else 
			tos = (a1,b1) -> Double.compare(b1.getResult(), a1.getResult()); 
		 
		List<TLightOrderStat> result = new ArrayList<TLightOrderStat> (); 
		 
		list.stream().sorted(tos).forEach(s-> 
		{ 
			s.setRating(rate[0]++); 
		 
			result.add(s); 
			 
		} 
		); 
				 
		return result; 
		 
	} 
	 
	 
	 
	public TSorter() { 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
