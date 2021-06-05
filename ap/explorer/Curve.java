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
import ap.utils.Bau;
import ap.utils.Biu;
import ap.utils.DateUtil;
import ap.utils.Fu;
import ap.utils.IntArrayUtil;
import ap.utils.MaUtil;
import ap.utils.Su;

public class Curve {
	
	public static List<String> curves = Arrays.asList(new String[] {"3","5","7","10","14","21","33","55","100","144","200"});

	
	//public static Map<String,Map<Date,Map<Integer,Long>>> states = new HashMap<String,Map<Date,Map<Integer,Long>>>();

	
	
	public static String indexOf(int index)
	{
		return curves.get(index);
	}

	public static int indexOf(String value)
	{
		return curves.indexOf(value);
	}
	
	public static void test()
	{
		
		int [] count = {gl.E_EMPTY};
		
		curves.forEach(a->{
			
			gl.sfn("Test of...%10s...%1d",Curve.indexOf(count[0]),Curve.indexOf(Curve.indexOf(count[0])));
			
			count[0]++;
			
		});
		
	}
	

	public static void test_bars() 
	{ 
		
		String m_source =  "c:\\bin\\spider\\look\\data\\2020\\eurusd.txt"; 
		
		
		Map<String,List<Bar>> 	m_bars = new HashMap<String,List<Bar>>();
	
		List<Bar> 			  	m_state_bars = new ArrayList<Bar>();
		
		String [] 				m_series = new String[] {"",""};
		
		
		 
		if(!Bar.load_bars(m_source,m_bars,m_series)) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s", gl.S_ERROR,"Load bars")); 
			 
			return ; 
		} 
		
		// Filter.
		
		Range range = Range.get_instance("01.12.2019","23.06.2020");
		
		gl.tx_d(new Object() {},gl.sf("Original range...%s",range.toString()));
					
		List<Bar> filtered_bars = Bar.get_bars_by_date_range(m_bars.get(m_series[gl.E_OK]),range);
		
		if (!get_state_bars(filtered_bars,m_bars.get(m_series[gl.E_OK]),m_state_bars,m_series))
		{
			gl.tx_e(new Object() {},"Get state bars by filter.");
			
			return;
		}
		
			gl.tx_k(new Object() {},"Get state bars.");
			
			m_state_bars.forEach(m->
			{
				//gl.sfn("%s %s",m.getDate().toString(),Bau.to_bin_str_from_long(m.getMagic().get(TMa.M3)));
				
			});
	
	}
	
	public static void test_ma_map() 
	{ 
		
		String m_source =  "c:\\bin\\spider\\look\\data\\2020\\eurusd.txt"; 
		
		
		Map<String,List<Bar>> 	m_bars = new HashMap<String,List<Bar>>();
	
		List<Bar> 			  	m_state_bars = new ArrayList<Bar>();
		
		String [] 				m_series = new String[] {"",""};
		
		
		// Load bars. 
		if(!Bar.load_bars(m_source,m_bars,m_series)) 
		{ 
			gl.tx_e(new Object() {}, String.format("%s...%s", gl.S_ERROR,"Load bars")); 
			 
			return ; 
		} 
		
		List<Bar> bars = m_bars.get(m_series[gl.E_OK]);
		
		// Load Mas v4 by HashMap.
		
		Map<Date,Map<Dimension,Double>> m_map = new LinkedHashMap<Date,Map<Dimension,Double>>();
		
		if(!Ma.get_ma(bars,Ma.mas, m_map))
		{
			gl.tx_e(new Object() {}, gl.sf("%s","Load Ma by map.")); 
		 
			return ;
		}
		
		
		// Checking.
		
		Fu.to_file("trace_ma_map.log","Start.\r\n");
		
		m_map.forEach((k,v) ->{
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(gl.sf("%s ", DateUtil.to_string(k)));
			
			for(int i=TMa.M3; i <= TMa.M200;i++) 
			{
				sb.append(gl.sf("[%s] ",
						gl.ma_names[i]));	
						
				for(int o = Bar.O; o <= Bar.C;o++)
				{
					sb.append(gl.sf("%s ",
					Su.fmt6d4(v.get(new Dimension(Ma.indexOf(i),o)))));

				}	
			}
			
				sb.append(System.lineSeparator());
			
				Fu.to_file_append("trace_ma_map.log",sb.toString());
				
				
			
		});
		
		
	}
	
	
	// TODO test_mas().
	public static void test_mas() 
	{ 
		
		String m_source =  "c:\\bin\\spider\\look\\data\\2020\\eurusd.txt"; 
		
		//String m_source = "/Users/alexplus/eclipse/wsp/look/data/2020/eurusd.txt";
		
		Map<String,List<Bar>> 	m_bars = new HashMap<String,List<Bar>>();
	
		List<Bar> 			  	m_state_bars = new ArrayList<Bar>();
		
		String [] 				m_series = new String[] {"",""};
		
		
		// Load bars. 
		if(!Bar.load_bars(m_source,m_bars,m_series)) 
		{ 
			gl.tx_e(new Object() {}, String.format("%s...%s", gl.S_ERROR,"Load bars")); 
			 
			return ; 
		} 
		
		List<Bar> bars = m_bars.get(m_series[gl.E_OK]);
		
		
		
		// Load MAs v1.
		
		List<TMa> m_mas_old = Bar.get_ma_old(bars);
		
		
		// Load MAs v2 intro BARs ma.
		
		//Bar.get_ma(bars);
		
		// Load MAs v3.
		
		/*
		List<Ma>  m_mas = new ArrayList<Ma>();
		
		if(!Ma.make(bars, m_mas))
		{
			gl.tx_e(new Object() {}, gl.sf("%s","Load Ma new.")); 
		 
			return ;
		}
		*/
		
		// Load Mas v4 by HashMap.
		
		Map<Date,Map<Dimension,Double>> m_map = new HashMap<Date,Map<Dimension,Double>>();
		
		if(!Ma.get_ma(bars,Ma.mas, m_map))
		{
			gl.tx_e(new Object() {}, gl.sf("%s","Load Ma by map.")); 
							
			return ;
		}
		
		
		// Checking.
		/*
		gl.tx_k(new Object() {},gl.sf("Start checking...V1 size...%6d...V2 size...%6d",m_mas_old.size(),bars.size()));
		
		Fu.to_file("trace_ma.log","Start.\r\n");
		
		m_mas_old.forEach(m->{
		
			StringBuilder sb = new StringBuilder();
			
			Bar bar = Bar.get_by_date(bars,m.getDt());
			
			// MA v.3
			
			Ma ma = Ma.get_by_date(m_mas,m.getDt());
			
			// MA v.4
			
			Map<Dimension,Double> ma_4 = m_map.get(m.getDt());
			
			sb.append(gl.sf("%s ",DateUtil.to_string(m.getDt())));
			
			for(int i=TMa.M3; i <= TMa.M200;i++) 
			{
			
				sb.append(gl.sf("[%4s]  %s %s %s %s %s ",
					gl.ma_names[i],	
					Su.fmt6d4(m.get_by_type(i)),
					Su.fmt6d4(bar.getMa().get(i*4)),
					Su.fmt6d4(ma.getAverages().get(i*4)),
					Su.fmt6d4(ma_4.get(new Dimension(Ma.indexOf(i),Bar.O))),
					
							(m.get_by_type(i) == bar.getMa().get(i*4)) && 
							(m.get_by_type(i) == ma.getAverages().get(i*4)) &&
							(m.get_by_type(i) == ma_4.get(new Dimension(Ma.indexOf(i),Bar.O)))
					));
			
			} // MA type.
				sb.append(System.lineSeparator());
				
				Fu.to_file_append("trace_ma.log",sb.toString());
		
				sb.setLength(gl.E_EMPTY);
		});
		
		*/
	}
	
	public static void test_mas_new() 
	{ 
		
		String m_source =  "c:\\bin\\spider\\look\\data\\2020\\eurusd.txt"; 
		
		
		Map<String,List<Bar>> 	m_bars = new HashMap<String,List<Bar>>();
	
		List<Bar> 			  	m_state_bars = new ArrayList<Bar>();
		
		String [] 				m_series = new String[] {"",""};
		
		
		// Load bars. 
		if(!Bar.load_bars(m_source,m_bars,m_series)) 
		{ 
			gl.tx_e(new Object() {}, String.format("%s...%s", gl.S_ERROR,"Load bars")); 
			 
			return ; 
		} 
		
		List<Bar> bars = m_bars.get(m_series[gl.E_OK]);
		
		List<Ma>  m_mas = new ArrayList<Ma>();
		
		gl.tx_d(new Object() {}, gl.sf("Input bars size...%6d",bars.size())); 
		
		if(!Ma.make(bars, m_mas))
		{
			gl.tx_e(new Object() {}, gl.sf("%s","Load Ma new.")); 
		 
			return ;
		}

			gl.tx_k(new Object() {}, gl.sf("%s...size...%06d","Load Ma new.",m_mas.size())); 
	 
			m_mas.forEach(m->{
				
				gl.sfn("%s",m.toString());
				
			});
		
	}
	
	
	/*
	public static boolean get_state_bars_16(String source,Map<String,List<Bar>> bars,List<Bar> state_bars,String [] series ) 
	{ 
		 
		String msg = "Load state bars"; 
				 
		if(!Bar.load_bars(source,bars,series)) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s", gl.S_ERROR,msg)); 
			 
			return false; 
		} 
		 
		
			gl.tx_k(new Object() {}, gl.sf("%s...%s...series...%s...size...%d...range...%s",
					gl.S_OK, 
					msg, 
					series[gl.E_OK], 
					bars.get(series[gl.E_OK]).size(), 
					Bar.get_date_range(bars.get(series[gl.E_OK])).toString())); 
			
			 
			gl.tx_k(new Object() {}, gl.sf("%s...%s...%s", gl.S_OK,msg,gl.S_TRY)); 
			 
			
			long start = System.nanoTime(); 
			
			bars.get(series[gl.E_OK]).forEach(b-> 
			{ 
					int magic = 0;
					
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.H;o++ ) 
						{
							// Get MAs. 
							
							double 	m_avg = MaUtil.get_avg(b.getId(),bars.get(series[gl.E_OK]),gl.ma_period[i],o==0?0:3); 
						 
							double 	m_bar_value = b.get_by_type(o==0?0:3); 
								 
							int	   	m_bit = gl.decode(m_bar_value,m_avg); 
						 
							int		m_bit_index = 15 - ((i*2)+o);
							 
							
							int m_before = magic;
							
							if(m_bit == gl.E_OK) 
					    		magic = Biu.ON(magic,m_bit_index);
							
							//else
								//magic &= (0 << m_bit_index);
							
							gl.sfn("Date...%s...Ma...%2d...Ohlc...%2d...Index...%2d...Value...%2d...Before...%d...Magic...%d", 
									DateUtil.to_string(b.getDate()),
									i,
									o,
									m_bit_index,
									m_bit,
									m_before,
									magic);
						
				 
						} // OHLC 
												 
				} // MA 

						gl.sfn("\n");
						
						gl.sfn("Date...%s...Magic...%s",DateUtil.to_string(b.getDate()),Bau.to_bin_str_from_long(magic));
						
						gl.sfn("\n");
						
						
						b.getMagic().add((long)magic);
						
						state_bars.add(b);		 
					
						
			}); // bars		 
					
			
			boolean bl_result = (state_bars.size() != gl.E_EMPTY); 

			
			if(bl_result) 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,state_bars.size(),(System.nanoTime() - start)/1000000)); 
			else 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d", gl.S_ERROR,msg)); 
				 
			return bl_result; 
						 
	} 
	
	*/
	
	public static boolean get_state_bars_complete(List<Bar> bars,List<Bar> base_bars,List<Bar> state_bars,String [] series ) 
	{ 
		 
		String msg = "Load state bars"; 
		 
		
			gl.tx_k(new Object() {}, gl.sf("%s...%s...series...%s...size...%d...range...%s",
					gl.S_OK, 
					msg, 
					series[gl.E_OK], 
					bars.size(), 
					Bar.get_date_range(bars).toString())); 
			
			 
			gl.tx_k(new Object() {}, gl.sf("%s...%s...%s", gl.S_OK,msg,gl.S_TRY)); 
			 
			
			long start = System.nanoTime(); 
			
			/*
			// For OHLC states.
			bars.forEach(b-> 
			{ 
					String magic = gl.replicate('0',32);
					
					StringBuilder sb = new StringBuilder();
					
					sb.append(gl.sf("%s ",DateUtil.to_string(b.getDate())));
					
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.C;o++ ) 
						{
							// Get MAs. 
							
							double 	m_avg = MaUtil.get_avg(b.getId(),bars,gl.ma_period[i],o); 
						 
							double 	m_bar_value = b.get_by_type(o); 
								 
							int	   	m_bit = gl.decode(m_bar_value,m_avg); 
						 
							int		m_bit_index = (i*4)+o;//31 - ((i*4)+o);
							 
							String  m_sign = "-";
							
							if(m_bit == gl.E_OK) 
							{
					    		magic = Su.replace(magic,m_bit_index,'1');
					    		
					    		m_sign = ">";
							}
							else
							{
								m_sign = "<";
							}
							
							String m_inject = gl.sf("{[%02d]%s%s%s} ",
									m_bit_index,
									Su.fmt8d4(m_bar_value),
									m_sign,
									Su.fmt8d4(m_avg));
					
							sb.append(m_inject);
				 
						} // OHLC 
												 
				} // MA 

						sb.append(magic);
					
						//sb.append(magic);
						
						//gl.sfn("\n");
						
						//gl.sfn("Date...%s...Magic...%32s",DateUtil.to_string(b.getDate()),magic);
						
						gl.sfn("%s",sb.toString());
						
						sb.setLength(gl.E_EMPTY);
						
						//gl.sfn("\n");
						
						b.getMagic().add(magic);
						
						state_bars.add(b);		 
					
						
			}); // bars		 
					
			*/
						// For MA states.
			
						Fu.to_file("trace_index.log","Start.\r\n");
						
						bars.forEach(b-> 
						{ 
								String magic = gl.replicate('0',256);
								
								StringBuilder sb = new StringBuilder();
								
								sb.append(gl.sf("%s ",DateUtil.to_string(b.getDate())));
								
								int [] cnt = {0};
								
								for(int i=TMa.M3; i <= TMa.M200;i++) 
								{ 
									for(int i1=TMa.M3; i1 <= TMa.M200;i1++) 
									{ 
										for(int o=Bar.O;o <= Bar.C;o++ ) 
										{
										
											//String m_msg = gl.sf("Indexes...[%6d]...[%6d]...[%1d%1d%1d]\r\n",b.getId(),cnt[0],i,i1,o);
									
											//Fu.to_file_append("trace_index.log",m_msg);
											
											
										// Get MAs. 
										
										double 	m_avg_1 = MaUtil.get_avg(b.getId(),base_bars,gl.ma_period[i],o); 
									 	
										double 	m_avg_2 = MaUtil.get_avg(b.getId(),base_bars,gl.ma_period[i1],o); 
											 
										int	   	m_bit = gl.decode(m_avg_1,m_avg_2); 
									 
										//int		m_bit_index = (i*8)+i1;
										 
										//String  m_sign = "-";
										
										if(m_bit == gl.E_OK) 
										{
								    		magic = Su.replace(magic,cnt[0],'1');
								    		
								    		//m_sign = ">";
										}
										//else
										//{
										//	m_sign = "<";
										//}
										/*
										String m_inject = gl.sf("{[%02d]%s%s%s} ",
												m_bit_index,
												Su.fmt8d4(m_avg_1),
												m_sign,
												Su.fmt8d4(m_avg_2));
										*/
										//sb.append(m_inject);
										
										/*
										gl.sfn("Date...%s...Ma...%2d...Ohlc...%2d...Index...%2d...Value...%2d...Magic...%s", 
												DateUtil.to_string(b.getDate()),
												i,
												o,
												m_bit_index,
												m_bit,
												magic);
										*/
										

										cnt[0]++;
										
							 
									} // OHLC index.
										
								} //MA second index.		
															 
							} // MA first index.

									//sb.append(magic);
								
									//sb.append(magic);
									
									//gl.sfn("\n");
									
									 Fu.to_file_append("trace_index.log",gl.sf("Date...%s...Magic...%32s\r\n",DateUtil.to_string(b.getDate()),magic));
								
									//gl.sfn("Date...%s...Magic...%32s",DateUtil.to_string(b.getDate()),magic);
									
									//gl.sfn("%s",sb.toString());
									
									//sb.setLength(gl.E_EMPTY);
									
									//gl.sfn("\n");
									
									//b.getMagic().add(magic);
									
									//state_bars.add(b);		 
								
									
						}); // bars		 
			
			boolean bl_result = true;//(state_bars.size() != gl.E_EMPTY); 

			
			if(bl_result) 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,state_bars.size(),(System.nanoTime() - start)/1000000)); 
			else 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d", gl.S_ERROR,msg)); 
				 
			return bl_result; 
						 
	} 
	public static boolean get_state_bars(List<Bar> bars,List<Bar> base_bars,List<Bar> state_bars,String [] series ) 
	{ 
		 
		String msg = "Load state bars"; 
		 
		
			gl.tx_k(new Object() {}, gl.sf("%s...%s...series...%s...size...%d...range...%s",
					gl.S_OK, 
					msg, 
					series[gl.E_OK], 
					bars.size(), 
					Bar.get_date_range(bars).toString())); 
			
			 
			gl.tx_k(new Object() {}, gl.sf("%s...%s...%s", gl.S_OK,msg,gl.S_TRY)); 
			 
			long start = System.nanoTime(); 
			
			// For OHLC states.
			
			Fu.to_file("trace_index_ohlc.log","Start.\r\n");
			
			Fu.to_file("trace_index_ohlc_dt.log","Start.\r\n");
			
			bars.forEach(b-> 
			{ 
					String magic = gl.replicate('0',32);
					
					StringBuilder sb = new StringBuilder();
					
					sb.append(gl.sf("%s ",DateUtil.to_string(b.getDate())));
					
					int [] cnt = {0};
					
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.C;o++ ) 
							{
								
								double 	m_bar_value  = b.get_by_type(o); 
							 	
								double 	m_avg_value = MaUtil.get_avg(b.getId(),base_bars,gl.ma_period[i],o); 
									 
								int	   	m_bit = gl.decode(m_bar_value,m_avg_value); 
								
								String m_sign = "";
								
								String m_bit_s = "";
								
								
								if(m_bit == gl.E_OK) 
								{
						    		magic = Su.replace(magic,cnt[0],'1');
						    		
						    		m_sign = ">";
						    		
						    		m_bit_s = "1";
								}
								else
								{
									m_sign = "<";
									
									m_bit_s = "0";
								}
								
								Fu.to_file_append("trace_index_ohlc_dt.log",gl.sf("%s %s%s%s %s\r\n",
										DateUtil.to_string(b.getDate()),
										Su.fmt8d4(m_bar_value),
										m_sign,
										Su.fmt8d4(m_avg_value),
										m_bit_s));
								
								
					
								cnt[0]++;
								
						} // OHLC index.
						
						Fu.to_file_append("trace_index_ohlc_dt.log","\r\n");
												 
				} // MA first index.
						
						Fu.to_file_append("trace_index_ohlc.log",gl.sf("Date...%s...Magic...%32s\r\n",DateUtil.to_string(b.getDate()),magic));

						Fu.to_file_append("trace_index_ohlc_dt.log",gl.sf("\r\nDate...%s...Magic...%32s\r\n",DateUtil.to_string(b.getDate()),magic));

						
						b.getMagic().add(magic);
						 
						state_bars.add(b);		 
						
						Fu.to_file_append("trace_index_ohlc_dt.log","\r\n");
					
						
			}); // bars		 

			
						// For MA states.
			
						Fu.to_file("trace_index.log","Start.\r\n");
						
						bars.forEach(b-> 
						{ 
								String magic = gl.replicate('0',256);
								
								StringBuilder sb = new StringBuilder();
								
								sb.append(gl.sf("%s ",DateUtil.to_string(b.getDate())));
								
								int [] cnt = {0};
								
								for(int i=TMa.M3; i <= TMa.M200;i++) 
								{ 
									for(int i1=TMa.M3; i1 <= TMa.M200;i1++) 
									{ 
										for(int o=Bar.O;o <= Bar.C;o++ ) 
										{
										
											
										// Get MAs. 
										
										double 	m_avg_1 = MaUtil.get_avg(b.getId(),base_bars,gl.ma_period[i],o); 
									 	
										double 	m_avg_2 = MaUtil.get_avg(b.getId(),base_bars,gl.ma_period[i1],o); 
										
											 
										int	   	m_bit = gl.decode(m_avg_1,m_avg_2); 
										
										if(m_bit == gl.E_OK) 
										{
								    		magic = Su.replace(magic,cnt[0],'1');
										}
							
										cnt[0]++;
										
							 
									} // OHLC index.
										
								} //MA second index.		
															 
							} // MA first index.
									
									Fu.to_file_append("trace_index.log",gl.sf("Date...%s...Magic...%256s\r\n",DateUtil.to_string(b.getDate()),magic));

									b.getMagic().add(magic);
									 
									state_bars.add(b);		 
								
									
						}); // bars		 
			
			boolean bl_result = (state_bars.size() != gl.E_EMPTY); 

			
			if(bl_result) 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,state_bars.size(),(System.nanoTime() - start)/1000000)); 
			else 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d", gl.S_ERROR,msg)); 
				 
			return bl_result; 
						 
	} 
	public static void  main(String [] args)
	{
		// test();
		
		// test_bars();
		
		//test_mas_new();
		
		//test_ma_map();
		
		test_mas();
		
	}


}
