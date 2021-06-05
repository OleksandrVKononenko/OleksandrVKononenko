 
 
 
 
 
 
 
 
 
 
 
 
package ap.ma; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import ap.btn.Bar; 
import ap.global.gl; 
import ap.utils.DateUtil; 

public class Ma { 

	private Date dt; 
	 
	private Map<Integer,Map<Integer,Double>>  avg_map = new LinkedHashMap<Integer,Map<Integer,Double>>(); 
		 
	 
	public Map<Integer,Map<Integer,Double>> getAvg_map() { 
		return avg_map; 
	} 

	public void setAvg_map(Map<Integer,Map<Integer,Double>> avg_map) { 
		this.avg_map = avg_map; 
	} 

	public Date getDt() { 
		return dt; 
	} 

	public void setDt(Date dt) { 
		this.dt = dt; 
	} 

	public Ma() 
	{ 
	} 

	public Ma(Map<Integer,Map<Integer,Double>> avg_map) 
	{ 
		 
		avg_map.keySet().forEach(ohlc->{ 
			 
			Map<Integer,Double> m_map = new LinkedHashMap<Integer,Double>(); 
			 
			avg_map.get(ohlc).forEach((k,v)->{ 
				 
					m_map.put(k,v); 
				 
			}); 
		 
				this.getAvg_map().put(ohlc,m_map); 
			}); 
			 
	} 
	 
	 
	public Ma(Date date, Map<Integer,Map<Integer,Double>> avg_map) 
	{ 
		this(avg_map); 
		 
		this.setDt(date); 
	} 
	 		 
	public static Ma get_instance(Date date,Map<Integer,Map<Integer,Double>> avg_map) 
	{ 
		return new Ma(date,avg_map); 
	} 
	 
	 
	public static Ma get_instance(Map<Integer,Map<Integer,Double>> avg_map) 
	{ 
		return new Ma(avg_map); 
	} 
	 
	 
	public static Ma get_instance() 
	{ 
		 
		return new Ma(); 
	} 
	 
	public static Ma get_sample_instance() 
	{ 
	 
		List<Double> list = Arrays.asList(new Double[]{0.9760,0.9807,0.9831,0.9842,0.9856,0.9875,0.9858,0.9892,0.9837,0.9769,0.9781}); 
	 
		Map<Integer,Map<Integer,Double>>  ma_avg = new LinkedHashMap <Integer,Map<Integer,Double>>(); 
		 
		// Init ohlc. 
		 
		ICross.cross.forEach(ohlc-> 
		{ 
			int m_ohlc_type = ICross.indexOf(ohlc); 
			 
			Map<Integer,Double>  m_map = new LinkedHashMap<Integer,Double>(); 
		 
			for ( Integer i : IMa.ma) 
			{ 
				m_map.put(IMa.indexOf(i),list.get(IMa.indexOf(i))); 
			 
			} 
				//gl.smn("Put  inline " + m_ohlc_type  + " type " + ohlc); 
				 
				ma_avg.put(m_ohlc_type,m_map); 
		}); 
		 
				//gl.smn("Map size " + ma_avg.size() ); 
		 
				return Ma.get_instance(DateUtil.to_date("01.01.2019"),ma_avg); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
			StringBuilder sb = new StringBuilder(); 
		 
			String m_dt =  DateUtil.to_string(this.getDt()); 
			 
			int GAP = 11; 
			 
			sb.append(gl.sf("%s ",m_dt)); 
			 
		this.getAvg_map().keySet().forEach(ohlc-> 
		{ 
			String m_intro = (ohlc == gl.E_EMPTY)?"":gl.replicate(" ",GAP); 
			 
			sb.append(gl.sf("%s%s",m_intro,ICross.indexOf(ohlc))); 
			 
			// Here insert data from child collection. 
			 
				this.getAvg_map().get(ohlc).forEach((k,v)-> 
				{ 
							 
					sb.append(gl.sf("%3d %.4f ",k,v)); 
					 
				}); 
			 
					sb.append(System.lineSeparator()); 
		}); 
		 
			return 	sb.toString(); 
	} 
	 
	 
	 
	 
	 
	public static boolean lookUpByDate(List<Ma> items,Date filter,Ma[] out) 
	{ 
		 
		Ma r = 
				items.stream().filter( 
						(b-> ( 
								b.getDt().compareTo(filter) == gl.E_EMPTY 
						))) 
						.findFirst() 
						.orElse(null); 
		 
		out[gl.E_EMPTY] = r; 
		 
		return (r != null); 
		 
	} 
	 
	public double get_by_type(int ma_type) 
	{ 
		try{ 
			return this.getAvg_map().get(ma_type)/*-->from list*/.get(ma_type); 
		} 
		catch(Exception e) 
		{ 
			return 0.0d; 
		} 
	} 
	 
	public static void test_sample() 
	{ 
		 
		gl.smn(gl.sf("%s",Ma.get_sample_instance().toString())); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		test_sample(); 

	} 

} 
 
