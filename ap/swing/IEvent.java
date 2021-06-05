 
 
package ap.swing; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.stream.Collectors; 

import ap.btn.Bar; 
import ap.btn.TDateRange; 
import ap.btn.Order; 
import ap.btn.TOrder; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.ma.Cross; 
import ap.ma.ICross; 
import ap.ma.IMa; 
import ap.ma.Ma; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public interface IEvent { 
	 
	 
	 
	//  
	 
	public static boolean get_event_orders(Map<Date,Bar> bar_map,List<Event> event_list,List<Order> orders_list) 
	{ 
		 
					String msg = "Create ORDERS suite on pure line."; 
				 
		try 
		{ 
		 
					long start = System.nanoTime(); 
				 
				if(bar_map.size() == gl.E_EMPTY || event_list.size() == gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..some input params is emptity .",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
					List<Order> m_orders = new ArrayList<Order>(); 
				 
					// Create index map. 

					Map<Integer,List<Date>> map_dic = new LinkedHashMap<Integer,List<Date>>(); 
					 
					// Startup initialization of it. 
					event_list.forEach(ev->{ 
						 
						map_dic.put(ev.getIGid(),new ArrayList<Date> ()); 
						 
					}); 
					 
					// Finally initialization of it. 
					event_list.forEach(ev->{ 
						 
						map_dic.get(ev.getIGid()).add(ev.getDt()); 
						 
					}); 
					 
					 
					gl.tracex(new Object(){},String.format("%s...Init index map size...%d",gl.S_OK,map_dic.size())); 
					 

					event_list.forEach(ev->{ 
						 

						int m_open_by = ev.getIGid(); 
						 
						int m_close_by = ev.getIGid(); 
						 
						int m_order_type = ev.getCross(); 
						 
						 
						if (ev.getCross() == ICross.indexOfCt("D")) 
						{ 
							m_close_by -= 10; 
							 
							m_order_type =  IOrder.indexOf("S"); 
							 
						} else if (ev.getCross() == ICross.indexOfCt("U")) 
						{ 
							m_close_by += 10; 
							 
							m_order_type =  IOrder.indexOf("B"); 
						} 
						 
						 
						Date m_open_date = ev.getDt(); 
								 
						Date m_close_date = map_dic.get(m_close_by).stream().filter( 
										(b-> ( 
												 b.after(m_open_date) 
												))).findFirst().orElse(null); 
						 
						if( m_close_date != null) 
						{ 
							//long m_duration = DateUtil.days_between(m_open_date,m_close_date); 
							 
							//double m_open_price = bar_map.get(ev.getDt()).get_by_type(ev.getGroup()); 
						 
							double m_open_price = bar_map.get(ev.getDt()).getO(); 
							 
							double m_close_price = bar_map.get(m_close_date).get_by_type(ev.getGroup()); 
							 
							TDateRange dr = TDateRange.get_instance(m_open_date,m_close_date); 
							 
							//gl.smn(gl.sf("--> %.4f %.4f",TBarItem.get_low_in_range(bar_map,dr),TBarItem.get_low_bar_in_range(bar_map,dr).getL())); 

							//Order order = Order.get_instance("usdchf",m_order_type,m_open_date,m_close_date,m_open_by,m_close_by,m_open_price,m_close_price,TBarItem.get_up_bar_in_range(bar_map,dr,m_order_type),TBarItem.get_down_bar_in_range(bar_map,dr,m_order_type)); 
							 
							Order order = null;//Order.get_instance("usdchf",m_order_type,m_open_date,m_close_date,m_open_by,m_close_by,m_open_price,m_close_price,TBarItem.get_up_bar_in_range(bar_map,dr),TBarItem.get_down_bar_in_range(bar_map,dr)); 
							 
							if( m_open_by != m_close_by) 
								m_orders.add(order); 
			 
							 
							//gl.smn(gl.sf("%04d...%04d...%s...%s...%d",ev.getIGid(),m_close_by,DateUtil.to_string(m_open_date),DateUtil.to_string(m_close_date),m_duration)); 
							 
						} 
						 
					}); 
					 
					 
					/* 
					map_dic.forEach((k,v)-> 
					{ 
						gl.smn(gl.sf("%04d %3d",k,v.size())); 
					}); 
					 
					*/ 
				 
						orders_list.addAll(m_orders); 
					 
						long end = System.nanoTime(); 
				 			 
						gl.tracex(new Object(){},String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,orders_list.size(),(end-start)/1000000)); 
					 		 
						return true; 
				 
		} 
		catch(Exception e) 
		{ 
						gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
						return false; 
		} 
	} 

	public static boolean get_event_orders(List<Bar> bar_list,List<Event> event_list,List<Order> orders_list) 
	{ 
		 
					String msg = "Create ORDERS suite on pure line."; 
				 
		try 
		{ 
		 
					long start = System.nanoTime(); 
				 
				if(bar_list.size() == gl.E_EMPTY || event_list.size() == gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..some input params is emptity .",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
					List<Order> m_orders = new ArrayList<Order>(); 
				 
					// Create index map. 

					Map<Integer,List<Date>> map_dic = new LinkedHashMap<Integer,List<Date>>(); 
					 
					// Startup initialization of it. 
					event_list.forEach(ev->{ 
						 
						map_dic.put(ev.getIGid(),new ArrayList<Date> ()); 
						 
					}); 
					 
					// Finally initialization of it. 
					event_list.forEach(ev->{ 
						 
						map_dic.get(ev.getIGid()).add(ev.getDt()); 
						 
					}); 
					 
					 
					gl.tracex(new Object(){},String.format("%s...Init index map size...%d",gl.S_OK,map_dic.size())); 
					 

					event_list.forEach(ev->{ 
						 

						int m_open_by = ev.getIGid(); 
						 
						int m_close_by = ev.getIGid(); 
						 
						int m_order_type = ev.getCross(); 
						 
						 
						if (ev.getCross() == ICross.indexOfCt("D")) 
						{ 
							m_close_by -= 10; 
							 
							m_order_type =  IOrder.indexOf("S"); 
							 
						} else if (ev.getCross() == ICross.indexOfCt("U")) 
						{ 
							m_close_by += 10; 
							 
							m_order_type =  IOrder.indexOf("B"); 
						} 
						 
						 
						Date m_open_date = ev.getDt(); 
								 
						Date m_close_date = map_dic.get(m_close_by).stream().filter( 
										(b-> ( 
												 b.after(m_open_date) 
												))).findFirst().orElse(null); 
						 
						if( m_close_date != null) 
						{ 
							//long m_duration = DateUtil.days_between(m_open_date,m_close_date); 
							 
							//double m_open_price = bar_map.get(ev.getDt()).get_by_type(ev.getGroup()); 
						 
							double m_open_price = Bar.get_by_date(bar_list,ev.getDt()).getO();//bar_list.get(ev.getDt()).getO(); 
							 
							double m_close_price = Bar.get_by_date(bar_list,m_close_date).get_by_type(ev.getGroup());//bar_list.get(m_close_date).get_by_type(ev.getGroup()); 
				 
							TDateRange dr = TDateRange.get_instance(m_open_date,m_close_date); 
							 
							//gl.smn(gl.sf("--> %.4f %.4f",TBarItem.get_low_in_range(bar_map,dr),TBarItem.get_low_bar_in_range(bar_map,dr).getL())); 

							//Order order = Order.get_instance("usdchf",m_order_type,m_open_date,m_close_date,m_open_by,m_close_by,m_open_price,m_close_price,TBarItem.get_up_bar_in_range(bar_map,dr,m_order_type),TBarItem.get_down_bar_in_range(bar_map,dr,m_order_type)); 
							 
							Order order = null;//Order.get_instance("usdchf",m_order_type,m_open_date,m_close_date,m_open_by,m_close_by,m_open_price,m_close_price,TBarItem.get_up_bar_in_range(bar_list,dr),TBarItem.get_down_bar_in_range(bar_list,dr)); 
							 
							if( m_open_by != m_close_by) 
								m_orders.add(order); 
			 
							 
							//gl.smn(gl.sf("%04d...%04d...%s...%s...%d",ev.getIGid(),m_close_by,DateUtil.to_string(m_open_date),DateUtil.to_string(m_close_date),m_duration)); 
							 
						} 
						 
					}); 
					 
					 
					/* 
					map_dic.forEach((k,v)-> 
					{ 
						gl.smn(gl.sf("%04d %3d",k,v.size())); 
					}); 
					 
					*/ 
				 
						orders_list.addAll(m_orders); 
					 
						long end = System.nanoTime(); 
				 			 
						gl.tracex(new Object(){},String.format("%s...%s...output list size...%06d...in...%d...ms.",gl.S_OK,msg,orders_list.size(),(end-start)/1000000)); 
					 		 
						return true; 
				 
		} 
		catch(Exception e) 
		{ 
						gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_EXCEPTION,msg,e.toString())); 
			 
						return false; 
		} 
	} 
	 
	public static boolean get_event(Map<Date,Bar> bar_map,Map<Date,Ma> ma_map,Map<Date,Cross> cross_map,List<Event> event_list) 
	{ 
		 
				String msg = "Create EVENT suite"; 
				 
		try 
		{ 
		 
				long start = System.nanoTime(); 
				 
				if(cross_map.size() == gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..input params is empty .",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
				int PREVIOUS = gl.E_EMPTY; 
				 
				Cross [] cross = { null }; 
				 
				 
				// Pass OHLC map. 
				cross_map.forEach((k,v) -> 
				{ 
					 
					// Init load. 
					if(	cross[PREVIOUS] == null) 
						cross[PREVIOUS] = v; 
					 
					 
					// Pass OHLC map. 
					v.getCross_map().keySet().forEach(ohlc-> 
					{ 
								 
						v.getCross_map().get(ohlc).keySet().forEach(ma_type->{ 
						 
							int [] m_index = {gl.E_EMPTY}; 
							 
							// Walk over ma_type. 
							v.getCross_map().get(ohlc).get(ma_type).forEach(cr->{ 

								// Walk over integer list. 
								int m_prev_cross = cross[gl.E_EMPTY].getCross_map().get(ohlc).get(ma_type).get(m_index[0]); 
								 
								// Test of changes. 
								if(m_prev_cross != cr && m_prev_cross != gl.TWO) 
								{ 
												 
									// Emit new instance. 
									Event ev = Event.get_instance(v.getDt(),ohlc,ma_type,cr,false); 
									 
									//event_map.put(v.getDt(),ev); 
									event_list.add(ev); 
									 
								} 
								 
									m_index[0]++; 
								 
							});  // cr 
						}); // ma_type 
					}); // ohlc 
					 
					 
					 
					// Pass MA map. 
					v.getCross_mas_map().keySet().forEach(ohlc-> 
					{ 
								 
						v.getCross_mas_map().get(ohlc).keySet().forEach(ma_type->{ 
						 
							int [] m_index = {gl.E_EMPTY}; 
							 
							// Walk over ma_type. 
							v.getCross_mas_map().get(ohlc).get(ma_type).forEach(cr->{ 

								// Walk over integer list. 
								int m_prev_cross = cross[PREVIOUS].getCross_mas_map().get(ohlc).get(ma_type).get(m_index[0]); 
								 
								// Test of changes. 
								if(m_prev_cross != cr && m_prev_cross != gl.TWO && cr != gl.TWO) 
								{ 
									 
									if(ma_type < m_index[0]) 
									{ 
									 
										Event ev = Event.get_instance(v.getDt(),ma_type,m_index[0],cr,true,ohlc); 
									 
										event_list.add(ev); 
										 
									} 
									 
								} 
								 
									m_index[0]++; 
								 
							});  // cr 
						}); // ma_type 
					}); // ohlc 
					 
				 
					 
									cross[PREVIOUS] = v; 
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

	public static boolean get_event(List<Bar> bar_list,List<Ma> ma_list,List<Cross> cross_list,List<Event> event_list) 
	{ 
		 
				String msg = "Create EVENT suite"; 
				 
		try 
		{ 
		 
				long start = System.nanoTime(); 
				 
				if(cross_list.size() == gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s..input params is empty .",gl.S_ERROR,msg)); 
					 
					return false; 
				} 
				 
				int PREVIOUS = gl.E_EMPTY; 
				 
				Cross [] cross = { null }; 
				 
				 
				// Pass OHLC map. 
				//cross_list.forEach((k,v) -> 
				cross_list.forEach(v -> 
				{ 
					 
					// Init load. 
					if(	cross[PREVIOUS] == null) 
						cross[PREVIOUS] = v; 
					 
					 
					// Pass OHLC map. 
					v.getCross_map().keySet().forEach(ohlc-> 
					{ 
								 
						v.getCross_map().get(ohlc).keySet().forEach(ma_type->{ 
						 
							int [] m_index = {gl.E_EMPTY}; 
							 
							// Walk over ma_type. 
							v.getCross_map().get(ohlc).get(ma_type).forEach(cr->{ 

								// Walk over integer list. 
								int m_prev_cross = cross[gl.E_EMPTY].getCross_map().get(ohlc).get(ma_type).get(m_index[0]); 
								 
								// Test of changes. 
								if(m_prev_cross != cr && m_prev_cross != gl.TWO) 
								{ 
												 
									// Emit new instance. 
									Event ev = Event.get_instance(v.getDt(),ohlc,ma_type,cr,false); 
									 
									//event_map.put(v.getDt(),ev); 
									event_list.add(ev); 
									 
								} 
								 
									m_index[0]++; 
								 
							});  // cr 
						}); // ma_type 
					}); // ohlc 
					 
					 
					 
					// Pass MA map. 
					v.getCross_mas_map().keySet().forEach(ohlc-> 
					{ 
								 
						v.getCross_mas_map().get(ohlc).keySet().forEach(ma_type->{ 
						 
							int [] m_index = {gl.E_EMPTY}; 
							 
							// Walk over ma_type. 
							v.getCross_mas_map().get(ohlc).get(ma_type).forEach(cr->{ 

								// Walk over integer list. 
								int m_prev_cross = cross[PREVIOUS].getCross_mas_map().get(ohlc).get(ma_type).get(m_index[0]); 
								 
								// Test of changes. 
								if(m_prev_cross != cr && m_prev_cross != gl.TWO && cr != gl.TWO) 
								{ 
									 
									if(ma_type < m_index[0]) 
									{ 
									 
										Event ev = Event.get_instance(v.getDt(),ma_type,m_index[0],cr,true,ohlc); 
									 
										event_list.add(ev); 
										 
									} 
									 
								} 
								 
									m_index[0]++; 
								 
							});  // cr 
						}); // ma_type 
					}); // ohlc 
					 
				 
					 
									cross[PREVIOUS] = v; 
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
	 
	 
	public static void write(String file,Map<Date,Event> map) 
	{ 
		 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 map.values().forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
	} 

	public static void write(String file,List<Event> list) 
	{ 
		 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 list.forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
	} 
} 
