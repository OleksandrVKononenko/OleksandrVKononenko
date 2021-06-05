 
package ap.ma; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Collection; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import ap.btn.Bar; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public interface ICross { 
	 
public static final List<String> cross 		= Arrays.asList(new String[]{"O","H","L","C"}); 

public static final List<String> cross_type = Arrays.asList(new String[]{"U","D","E"}); 

	 
	public static int indexOf(String cross_tp) 
	{ 
		return cross.indexOf(cross_tp); 
	} 
	 
	public static int indexOfCt(String cross_tp) 
	{ 
		return cross_type.indexOf(cross_tp); 
	} 
	 
	public static String indexOf(int cross_tp) 
	{ 
		return cross.get(cross_tp); 
	} 
	 
	public static String indexOfCt(int cross_tp) 
	{ 
		return cross_type.get(cross_tp); 
	} 
	 
	 
	public static boolean get_cross(Map<Date,Bar> bar_map,Map<Date,Ma> ma_map,Map<Date,Cross> cross_map) 
	{ 
		 
			String msg = "Create CROSS suite"; 
				 
		try 
		{ 
		 
				long start = System.nanoTime(); 
				 
				if(bar_map.size() == gl.E_EMPTY || ma_map.size() == gl.E_EMPTY  || ma_map.size() != bar_map.size() ) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..input params is empty or input collection is out of sync.",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
				 
				ma_map.forEach((k,v) -> 
				{ 
					 
					Date date_proxy = k; 
							 
					Bar bar_proxy =  bar_map.get(k); 
					 
					Ma  ma_proxy = v; 
					 
					Cross cross_proxy = Cross.get_instance(bar_proxy,ma_proxy); 
					 
					if(cross_proxy == null) 
					{ 
						gl.smn("Cross proxy is NULL."); 
					} 
					 
					 
					if(cross_map.containsKey(date_proxy)) 
					gl.smn(gl.sf("---> %s...%s",DateUtil.to_string(date_proxy),DateUtil.to_string(date_proxy)));						 
					else 
					cross_map.put(date_proxy,cross_proxy); 
					 

					 
				}); 
				 
					long end = System.nanoTime(); 
				 			 
					gl.tracex(new Object(){},String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,cross_map.size(),(end-start)/1000000)); 
					 		 
					return true; 
				 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
					return false; 
		} 
	} 

	public static boolean get_cross(List<Bar> bar_list,List<Ma> ma_list,List<Cross> cross_list) 
	{ 
		 
			String 	msg = "Create CROSS suite"; 
				 
		try 
		{ 
		 
					long start = System.nanoTime(); 
				 
				if(bar_list.size() == gl.E_EMPTY || ma_list.size() == gl.E_EMPTY  || ma_list.size() != bar_list.size() ) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..input params is empty or input collection is out of sync.",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
				 
					ma_list.forEach(ma -> 
				 
				{ 
					 
					cross_list.add(Cross.get_instance(Bar.get_by_date(bar_list, ma.getDt()),ma)); 
					 
				}); 
				 
					long end = System.nanoTime(); 
				 			 
					gl.tracex(new Object(){},String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,cross_list.size(),(end-start)/1000000)); 
					 		 
					return true; 
				 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
					return false; 
		} 
	} 
	 
	public static void write(String file,Map<Date,Cross> map) 
	{ 
		 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 map.values().forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
	} 
	 
	public static void write(String file,Collection<Cross> collection) 
	{ 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 collection.forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
		 
		 
	} 
	 
	public static int get_type_of_cross(double left,double right) 
	{ 
		int 	m_return = gl.TWO; 
		 
		if (left > right) 
		{ 
				m_return = ICross.indexOfCt("U"); 
				 
		}else if (left < right) 
		{ 
				m_return = ICross.indexOfCt("D"); 
		} 
		 
				return m_return; 
	} 

} 
