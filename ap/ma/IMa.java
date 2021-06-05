 
 
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
import ap.prop.SDate; 
import ap.utils.Fu; 

public interface IMa { 
	 
	//public static final List<Integer> ma = Arrays.asList(new Integer[]{10,20,30,40,50,60,70,80,90,100,120,130,140,150,160,170,180,190,200,210,220,230,250}); 
	 
	//public static final List<Integer> ma = Arrays.asList(new Integer[]{3,5,7,10,14,21,33,55,100,144,200}); 
	 
	public static final List<Integer> ma = Arrays.asList(new Integer[]{3,7,14,21,55,100,144,200}); 
	 
	//public static final List<Integer> ma = Arrays.asList(new Integer[]{3}); 
	 
	public static int indexOf(int ma_type) 
	{ 
		return ma.indexOf(ma_type); 
	} 
	 
	public static int indexOfget(int ma_type) 
	{ 
		return ma.get(ma_type); 
	} 
	 
		 
	public static boolean get_ma(Map<Date,Bar> bar_map,Map<Date,Ma> ma_map_ref ) 
	{ 

			Object v = new Object(){}; 
		 
			String msg = "Create MA suite"; 
				 
		try 
		{ 

			long start = System.nanoTime(); 
		 
		if(bar_map.size()== gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("%s...%s..input param list is empty",gl.S_ERROR,msg)); 
			 
			return false; 
		} 
		 
		// Insert leading key by ohlc type because there as need a four type of ma. 
		Map<Integer,Map<Integer,List<Bar>>>  ma_map = new LinkedHashMap<Integer,Map<Integer,List<Bar>>>(); 
		 
		Map<Integer,Map<Integer,Double>>  ma_avg = new LinkedHashMap <Integer,Map<Integer,Double>>(); 
		 
		// Init ohlc. 
		 
		ICross.cross.forEach(ohlc-> 
		{ 
			//gl.smn("OHLC " + ohlc); 
			 
			int m_ohlc_type = ICross.indexOf(ohlc); 
			 
			Map<Integer,List<Bar>>  m_map = new LinkedHashMap<Integer,List<Bar>>(); 
		 
			for ( Integer i : IMa.ma) 
			{ 
				//gl.smn("OHLC  ma " + i); 
				 
				m_map.put(IMa.indexOf(i),new ArrayList<Bar>()); 
			 
			} 
			   ma_map.put(m_ohlc_type,m_map); 
		}); 
		 
			//gl.smn("Ok"); 
		 
			ICross.cross.forEach(ohlc-> 
			{ 
				//gl.smn("OHLC 2 " + ohlc); 
				 
				int m_ohlc_type = ICross.indexOf(ohlc); 
				 
				Map<Integer,Double>  m_avg = new LinkedHashMap<Integer,Double>(); 
			 
				for ( Integer i : IMa.ma) 
				{ 
					//gl.smn("OHLC  ma 2 " + i); 
					 
					m_avg.put(IMa.indexOf(i),(Double)0.0d); 
				 
				} 
				   ma_avg.put(m_ohlc_type,m_avg); 
				 
			}); 
			 
			//gl.smn("Ok 2"); 
			 
			bar_map.values().forEach(bar-> 
			{ 
			 
				ICross.cross.forEach(ohlc-> { 
					 
				int m_ohlc_type = ICross.indexOf(ohlc); 
				 
				//gl.smn("OHLC  " + m_ohlc_type); 
				 
					 
			ma_avg.get(m_ohlc_type).keySet().forEach(type-> 
			{ 
				 
					int ma_type_in_days = IMa.ma.get(type); 
					 
					//gl.smn("MA type : " + type + " m_type : " + m_type ); 
				 
					// Get type by OHLC then work array for calculate avg ma. 
					// Fix 12.12.2019 
					ma_map.get(m_ohlc_type).get(type).add(bar); 
				 
					//gl.smn(gl.sf("get ma_map...ohlc...%d...type...%d",m_ohlc_type,m_type)); 
					 
					// If we achive for peak then calculate avg. 
				if(ma_map.get(m_ohlc_type).get(type).size() == ma_type_in_days) 
				{ 
					double proxy_double = ma_map.get(m_ohlc_type).get(type).stream().mapToDouble(p-> p.get_by_type(m_ohlc_type)).average().getAsDouble(); 
					 
					ma_avg.get(m_ohlc_type).put(type,proxy_double); 
			 
					// Strong remove. 
					ma_map.get(m_ohlc_type).get(type).remove(gl.E_EMPTY); 
					 
				} 
				else 
				{ 
					 
					 
					ma_avg.get(m_ohlc_type).put(type,bar.get_by_type(m_ohlc_type)); 
				} 
								 
			}); 
			 
					// Create ma. 
					Ma ma = Ma.get_instance(bar.getDate(),ma_avg); 
					 
					ma_map_ref.put(ma.getDt(),ma); 
			 
		}); // for 
			 
	}); //ohlc 
		 
		 			long end = System.nanoTime(); 
		 			 
					gl.tracex(v,String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,ma_map_ref.size(),(end-start)/1000000)); 
			 		 
					return true; 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(v,String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
					return false; 
		} 
	} 
	 
	 
	 
	public static boolean get_ma(Map<Date,Bar> bar_map,Map<Date,Ma> ma_map_ref,List<String> ohlc_list ) 
	{ 

			Object v = new Object(){}; 
		 
			String msg = "Create MA suite"; 
				 
		try 
		{ 

			long start = System.nanoTime(); 
		 
		if(bar_map.size()== gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("%s...%s..input param list is empty",gl.S_ERROR,msg)); 
			 
			return false; 
		} 
		 
		// Insert leading key by ohlc type because there as need a four type of ma. 
		Map<Integer,Map<Integer,List<Bar>>>  ma_stage = new LinkedHashMap<Integer,Map<Integer,List<Bar>>>(); 
		 
		Map<Integer,Map<Integer,Double>>  ma_avg = new LinkedHashMap <Integer,Map<Integer,Double>>(); 
		 
		// Init ohlc. 
		 
		//ICross.cross.forEach(ohlc-> 
		ohlc_list.forEach(ohlc-> 
		{ 
		 
			int m_ohlc_type = ICross.indexOf(ohlc); 
			 
			Map<Integer,List<Bar>>  m_stage_map = new LinkedHashMap<Integer,List<Bar>>(); 
		 
			for ( Integer i : IMa.ma) 
			{ 
				 
				m_stage_map.put(IMa.indexOf(i),new ArrayList<Bar>()); 
			 
			} 
			   ma_stage.put(m_ohlc_type,m_stage_map); 
		}); 
		 
			ohlc_list.forEach(ohlc-> 
			{ 
					 
				int m_ohlc_type = ICross.indexOf(ohlc); 
				 
				Map<Integer,Double>  m_avg_map = new LinkedHashMap<Integer,Double>(); 
			 
				for ( Integer i : IMa.ma) 
				{ 
					m_avg_map.put(IMa.indexOf(i),(Double)0.0d); 
				 
				} 
				   ma_avg.put(m_ohlc_type,m_avg_map); 
				 
			}); 
			 
			 
			bar_map.values().forEach(bar-> 
			{ 
			 
				ohlc_list.forEach(ohlc-> { 
					 
				int m_ohlc_type = ICross.indexOf(ohlc); 
						 
			ma_avg.get(m_ohlc_type).keySet().forEach(ma_index-> 
			{ 
				 
					// type - index {0,1,2...10} 
				 
					// m_ma_days - subtype in days. 
					int m_ma_days = IMa.ma.get(ma_index); 
					 
					// Get type by OHLC then work array for calculate avg ma. 
					//ma_map.get(m_ohlc_type).get(ma_index).add(bar); 
			 
					// If we achive for peak then calculate avg. 
					// Fix 12.12.2019 
				if(ma_stage.get(m_ohlc_type).get(ma_index).size() == m_ma_days) 
				{ 
					double proxy_double = ma_stage.get(m_ohlc_type).get(ma_index).stream().mapToDouble(p-> p.get_by_type(m_ohlc_type)).average().getAsDouble(); 
					 
					ma_avg.get(m_ohlc_type).put(ma_index,proxy_double); 
		 
					// Strong remove. 
					ma_stage.get(m_ohlc_type).get(ma_index).remove(gl.E_EMPTY); 
					 
					ma_stage.get(m_ohlc_type).get(ma_index).add(bar); 
					 
				} 
				else 
				{ 
					ma_stage.get(m_ohlc_type).get(ma_index).add(bar); 
					 
					ma_avg.get(m_ohlc_type).put(ma_index,bar.get_by_type(m_ohlc_type)); 
				} 
								 
			}); // ma 

		}); // ohlc 
				 
					// Create ma. 
					Ma ma = Ma.get_instance(bar.getDate(),ma_avg); 
				 
					ma_map_ref.put(ma.getDt(),ma); 
			 

			 
	}); //bar 
		 
		 			long end = System.nanoTime(); 
		 			 
					gl.tracex(v,String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,ma_map_ref.size(),(end-start)/1000000)); 
			 		 
					return true; 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(v,String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
					return false; 
		} 
	} 
	 
	public static boolean get_ma(List<Bar> bar_list,List<Ma> ma_list_ref,List<String> ohlc_list ) 
	{ 

			Object v = new Object(){}; 
		 
			String msg = "Create MA suite"; 
				 
		try 
		{ 

			long start = System.nanoTime(); 
		 
		if(bar_list.size()== gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("%s...%s..input param list is empty",gl.S_ERROR,msg)); 
			 
			return false; 
		} 
		 
		// Insert leading key by ohlc type because there as need a four type of ma. 
		Map<Integer,Map<Integer,List<Bar>>>  ma_stage = new LinkedHashMap<Integer,Map<Integer,List<Bar>>>(); 
		 
		Map<Integer,Map<Integer,Double>>  ma_avg = new LinkedHashMap <Integer,Map<Integer,Double>>(); 
		 
		// Init ohlc. 
		 
		//ICross.cross.forEach(ohlc-> 
		ohlc_list.forEach(ohlc-> 
		{ 
		 
			int m_ohlc_type = ICross.indexOf(ohlc); 
			 
			Map<Integer,List<Bar>>  m_stage_map = new LinkedHashMap<Integer,List<Bar>>(); 
		 
			for ( Integer i : IMa.ma) 
			{ 
				 
				m_stage_map.put(IMa.indexOf(i),new ArrayList<Bar>()); 
			 
			} 
			   ma_stage.put(m_ohlc_type,m_stage_map); 
		}); 
		 
			ohlc_list.forEach(ohlc-> 
			{ 
					 
				int m_ohlc_type = ICross.indexOf(ohlc); 
				 
				Map<Integer,Double>  m_avg_map = new LinkedHashMap<Integer,Double>(); 
			 
				for ( Integer i : IMa.ma) 
				{ 
					m_avg_map.put(IMa.indexOf(i),(Double)0.0d); 
				 
				} 
				   ma_avg.put(m_ohlc_type,m_avg_map); 
				 
			}); 
			 
			 
			bar_list.forEach(bar-> 
			{ 
			 
				ohlc_list.forEach(ohlc-> { 
					 
				int m_ohlc_type = ICross.indexOf(ohlc); 
						 
			ma_avg.get(m_ohlc_type).keySet().forEach(ma_index-> 
			{ 
				 
					// type - index {0,1,2...10} 
				 
					// m_ma_days - subtype in days. 
					int m_ma_days = IMa.ma.get(ma_index); 
					 
					// Get type by OHLC then work array for calculate avg ma. 
					//ma_map.get(m_ohlc_type).get(ma_index).add(bar); 
			 
					// If we achive for peak then calculate avg. 
					// Fix 12.12.2019 
				if(ma_stage.get(m_ohlc_type).get(ma_index).size() == m_ma_days) 
				{ 
					double proxy_double = ma_stage.get(m_ohlc_type).get(ma_index).stream().mapToDouble(p-> p.get_by_type(m_ohlc_type)).average().getAsDouble(); 
					 
					ma_avg.get(m_ohlc_type).put(ma_index,proxy_double); 
		 
					// Strong remove. 
					ma_stage.get(m_ohlc_type).get(ma_index).remove(gl.E_EMPTY); 
					 
					ma_stage.get(m_ohlc_type).get(ma_index).add(bar); 
					 
				} 
				else 
				{ 
					ma_stage.get(m_ohlc_type).get(ma_index).add(bar); 
					 
					ma_avg.get(m_ohlc_type).put(ma_index,bar.get_by_type(m_ohlc_type)); 
				} 
								 
			}); // ma 

		}); // ohlc 
				 
					// Create ma. 
					Ma ma = Ma.get_instance(bar.getDate(),ma_avg); 
				 
					ma_list_ref.add(ma); 
			 

			 
	}); //bar 
		 
		 			long end = System.nanoTime(); 
		 			 
					gl.tracex(v,String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,ma_list_ref.size(),(end-start)/1000000)); 
			 		 
					return true; 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(v,String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
					return false; 
		} 
	} 
	 
	 
	public static void write(String file,Map<Date,Ma> map) 
	{ 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 map.values().forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
		 
	} 

	public static void write(String file,Collection<Ma> collection) 
	{ 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 collection.forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
	} 
	 
} 
