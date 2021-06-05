package ap.explorer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ap.btn.Bar;
import ap.btn.TMa;
import ap.global.gl;
import ap.utils.DateUtil;
import ap.utils.Fu;
import ap.utils.MaUtil;
import ap.utils.MapUtils;
import ap.utils.Su;

public class Ma {
	
	public static List<Integer> mas = Arrays.asList(new Integer [] {3,7,10,14,21,33,55,100,144,200});
	
	public static int  indexOf(int index)
	{
		return mas.get(index);
	}
	
	private Date date;
	
	private List<Double> averages;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Double> getAverages() {
		return averages;
	}

	public void setAverages(List<Double> averages) {
		this.averages = averages;
	}

	public Ma()
	{
		this.setAverages(new ArrayList<Double>());
	}
	
	// For One item.
	public boolean make(Bar bar,List<Bar> bars)
	{
					//gl.tx_d(new Object() {},gl.sf("Input bar...%s...bars size...%6d",DateUtil.to_string(bar.getDate()),bars.size()));
					
					this.setDate(bar.getDate());
			
			for(int i=0;i<mas.size();i++)
			{
				for(int o = Bar.O; o <= Bar.C;o++)
				{

					double avg = MaUtil.get_avg(bar.getId(),bars,indexOf(i),o);

					this.getAverages().add(avg);
					
				}
			}
			
					return this.getAverages().size() == (mas.size() * 4);
	}
	
	public static boolean make(List<Bar> bars,List<Ma> mas)
	{
		
		if((bars == null || bars.size() == gl.E_EMPTY) || mas == null)
		{
			gl.tx_e(new Object() {},gl.sf("Bad input params."));
			
			return false;
		}	
		
		String msg = "Create MA V3.";
				
		long start = System.nanoTime();
		
		List<Boolean> m_result = new ArrayList<Boolean>();
		
		bars.forEach(bar->{
			
					 
					 Ma m = Ma.get_instance();
					 
					 m_result.add(m.make(bar,bars));
					 
					 mas.add(m);
		});
		
		long end = System.nanoTime();
		
		gl.tx_k(new Object() {},gl.sf("%s...Size...%d...Done in...%d ms.",msg,mas.size(),(end - start)/1000000));
		
		return  (m_result.indexOf(false) == gl.E_ERROR); 
		
	}
	
	public static Ma get_instance()
	{
		return new Ma();
	}

	@Override
	public String toString()
	{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(gl.sf("%s ",DateUtil.to_string(this.getDate())));
		
		for(int i=0;i< mas.size();i++)
		{
	
			sb.append(gl.sf("M%s %s ",indexOf(i),Su.fmt6d4(this.getAverages().get(i*4))));
			
		}
		
			return sb.toString();
		
	}
	
	public static Ma get_by_date(List<Ma> items,Date date) 
	{ 
		 
		return  items.stream().filter( 
						( 
								b -> ( 
										b.getDate().compareTo(date) == gl.E_EMPTY 
								))) 
						.findFirst().orElse(null); 
		 	 
	} 
	
	public static boolean get_ma_prev(
			List<Bar> input_list,
			List<Integer> mas,
			Map<Date,Map<Integer,Map<Integer,Double>>> out 
	) 
	{ 
		Object v = new Object(){}; 
		 
		String msg = "Create MA suite by map"; 
		
		 
		long start = System.nanoTime(); 
		 
		List<TMa> target_list = new ArrayList<TMa>(); 
		
					 
		if(input_list.size()== gl.E_EMPTY) 
		{ 
			gl.tx_e(v,gl.sf("%s...input param list is empty.",msg)); 
			 
			return false; 
		} 
		
			gl.tx_k(v,gl.sf("%s...input param list size is...%d.",msg,input_list.size())); 
		
			
		// Init section.
		
		// Result proxy map.
		Map<Integer,Map<Integer,Double>> m_map = new LinkedHashMap<Integer,Map<Integer,Double>>();
				
		// Work map.
		Map<Integer,Map<Integer,List<Double>>> map = new LinkedHashMap<Integer,Map<Integer,List<Double>>>();
		 
		mas.forEach(m->
		{
			// Work map.
			Map<Integer,List<Double>> intro = new LinkedHashMap<Integer,List<Double>>();
			
			// Proxy map.
			Map<Integer,Double> intro_r = new LinkedHashMap<Integer,Double>();
			
			for (int o = Bar.O; o <= Bar.C;o++)
	 		{
				
				intro.put(o,new ArrayList<Double>());
				
				intro_r.put(o,0.0d);
				
	 		}
			
				map.put(m,intro);
			
				m_map.put(m,intro_r);
				
		});
		
			// Re check of init.
		
			gl.tx_k(new Object() {},gl.sf("Init check...size...%d",map.size()));
			
			Fu.to_file("trace_ma_map_x.log",".\r\n");
		
		 	input_list.forEach(b->{
		 		
		 		
		 		
		 		mas.forEach(m->
				{
					
				 		
		 		for (int o = Bar.O; o <= Bar.C;o++)
		 		{
		 			double avg= 0.0d;
			 			 
						if(map.get(m).get(o).size() == m) 
						{ 
							 
							avg = map.get(m).get(o).stream().mapToDouble(p-> p).average().getAsDouble(); 
							 
							map.get(m).get(o).remove(gl.E_EMPTY); 
							 
							map.get(m).get(o).add(b.get_by_type(o));
							
							m_map.get(m).put(o,avg);
							
							Fu.to_file_append("trace_ma_map_x.log",gl.sf("%s...%2d...%2d..%s\r\n",
									DateUtil.to_string(b.getDate()),
									m,
									o,
									Su.fmt6d4(avg)
									));
							 
						}else 
						{ 
							avg = b.get_by_type(o);
							
							map.get(m).get(o).add(avg); 
							 
						} 
							
			 			
		 			} // o
				});// mas
		 		
		 			
		 			Map<Integer,Map<Integer,Double>> m_copy = new LinkedHashMap<Integer,Map<Integer,Double>>();
		 			
		 			m_map.forEach((k,v1)->
		 			{
		 				m_copy.put(k,v1);
		 			});
		 			
		 			// Export data to result param.
		 			out.put(b.getDate(),m_copy);
		 			
		 				
		 	}); // bar
			
				 	long end = System.nanoTime(); 
		 			 
					gl.tx_k(new Object(){},gl.sf("Size...%d...Done in...%d ms.",out.size(),(end-start)/1000000)); 
			 
					return (out.size() == input_list.size()); 
	} 
	 

	
	public static boolean get_ma(
			List<Bar> input_list,
			List<Integer> mas,
			Map<Date,Map<Dimension,Double>> out 
	) 
	{ 
		Object v = new Object(){}; 
		 
		String msg = "Create MA V4."; 
		 
		long start = System.nanoTime(); 
					 
		if(input_list.size()== gl.E_EMPTY) 
		{ 
			gl.tx_e(v,gl.sf("%s...Bad input param.",msg)); 
			 
			return false; 
		} 
		
			gl.tx_k(v,gl.sf("%s...Input list size is...%d.",msg,input_list.size())); 
		
			
		// Init section.
		
		// Result proxy map.
		Map<Dimension,Double> m_map = new LinkedHashMap<Dimension,Double>();
				
		// Work map.
		Map<Dimension,List<Double>> map = new LinkedHashMap<Dimension,List<Double>>();
		 
		mas.forEach(m->
		{
				
			for (int o = Bar.O; o <= Bar.C;o++)
	 		{
				Dimension key = new Dimension(m,o);
						
				map.put(key,new ArrayList<Double>());
				
				m_map.put(key,0.0d);
				
	 		}
				
		});
		
			//Fu.to_file("trace_ma_map_x.log",".\r\n");
		
		 	input_list.forEach(b->{
		 		
		 		mas.forEach(m->
				{
					
				 		
		 		for (int o = Bar.O; o <= Bar.C;o++)
		 		{
		 					double avg= b.get_by_type(o);
			
		 					Dimension key = new Dimension(m,o);
		 				
						if(map.get(key).size() == m) 
						{ 
							 
							avg = map.get(key).stream().mapToDouble(p-> p).average().getAsDouble(); 
							 
							map.get(key).remove(gl.E_EMPTY); 
							 
							map.get(key).add(b.get_by_type(o));
							
							m_map.put(key,avg);
							
							/*
							Fu.to_file_append("trace_ma_map_x.log",gl.sf("%s...%2d...%2d..%s\r\n",
									DateUtil.to_string(b.getDate()),
									m,
									o,
									Su.fmt6d4(avg)
									));
							*/
							 
						}else 
						{ 
							avg = b.get_by_type(o);
							
							map.get(key).add(avg); 
							
							m_map.put(key,avg);
							 
						} 
							
		 			} // o
		 		
				});// mas
		 		
		 			
		 			Map<Dimension,Double> m_copy = new LinkedHashMap<Dimension,Double>();
		 			
		 			m_map.forEach((k,v1)->
		 			{
		 				m_copy.put(k,v1);
		 			});
		 		
		 			
		 			// Export data to result param.
		 			out.put(b.getDate(),m_copy);
		 			
		 				
		 	}); // bar
			
				 	long end = System.nanoTime(); 
		 			 
					gl.tx_k(new Object(){},gl.sf("Size...%d...Done in...%d ms.",out.size(),(end-start)/1000000)); 
			 
					return true;//(out.size() == input_list.size()); 
	} 
	
	public static void main(String[] args) {
		

	}

}
