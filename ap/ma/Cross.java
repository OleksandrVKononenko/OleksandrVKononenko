 
package ap.ma; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import ap.btn.Bar; 
import ap.global.gl; 
import ap.utils.DateUtil; 

public class Cross { 

	private Date dt; 
	 
	private int offset; 
	 
	private Map<Integer,Map<Integer,List<Integer>>>  cross_map = new LinkedHashMap<Integer,Map<Integer,List<Integer>>>(); 
	 
	private Map<Integer,Map<Integer,List<Integer>>>  cross_mas_map = new LinkedHashMap<Integer,Map<Integer,List<Integer>>>(); 

	private List<Integer> cross_list = new ArrayList<Integer>(); 
		
	 
	public int getDays_offset() { 
		return offset; 
	} 

	public void setDays_offset(int days_offset) { 
		this.offset = days_offset; 
	} 

	public Date getDt() { 
		return dt; 
	} 

	public void setDt(Date dt) { 
		this.dt = dt; 
	} 

	 
	 
	public List<Integer> getCross_list() { 
		return cross_list; 
	} 

	public void setCross_list(List<Integer> cross_list) { 
		this.cross_list = cross_list; 
	} 

	public Map<Integer, Map<Integer, List<Integer>>> getCross_mas_map() { 
		return cross_mas_map; 
	} 

	public void setCross_mas_map( 
			Map<Integer, Map<Integer, List<Integer>>> cross_mas_map) { 
		this.cross_mas_map = cross_mas_map; 
	} 

	public Map<Integer,Map<Integer,List<Integer>>> getCross_map() { 
		return cross_map; 
	} 

	public void setCross_map(Map<Integer,Map<Integer, List<Integer>>> cross_map) { 
		this.cross_map = cross_map; 
	} 
	 
	public Cross() { 
		 
		init_map(); 
		 
		init_mas_map(); 
	} 
	 
	public Cross(Date dt) { 
	 
		this(); 
		 
		this.setDt(dt); 
	} 
	 
	public Cross(Bar bar,Ma ma) { 
		 
		this(bar.getDate()); 
		 
		this.setDays_offset(bar.getId()); 
		 
		if(!make_cross(bar,ma)) 
		{ 
			gl.tracex(new Object(){},gl.sf("cross dtor...make cross...%02d...<>...%02d...%s",this.getCross_map().size(),ICross.cross.size(),gl.S_ERROR)); 
			 
			return; 
		} 
		 
		if(!make_cross_mas(ma)) 
		{ 
			gl.tracex(new Object(){},gl.sf("cross dtor...make cross...%02d...<>...%02d...%s",this.getCross_mas_map().size(),ICross.cross.size(),gl.S_ERROR)); 
			 
			return; 
		} 
	 
		// Load to cross_list; 
		 
		List<Integer> i_list = this.make_cross_list(this.getCross_map()); 
		 
		this.getCross_list().addAll( 
					  i_list 
		); 
		 
		List<Integer> i_list_mas = this.make_cross_list(this.getCross_mas_map()); 
		 
		this.getCross_list().addAll( 
				i_list_mas 
		); 
		 
		/* 
		if ( DateUtil.is_equals(ma.getDt(),DateUtil.to_date("23.01.1998"))) 
		{ 
			gl.smn("dtor..." + this.toString()); 
			 
			gl.smn(gl.sf("Dtor common()...%s...%s",show_i_list(i_list),show_i_list(i_list_mas))); 
			 
		} 
		*/ 
		 
	} 
	 
	 
		 
	public void init_map() 
	{ 
		ICross.cross.forEach(cr-> 
		{ 
			Map<Integer,List<Integer>> local_map = new LinkedHashMap<Integer,List<Integer>>(); 
			 
				IMa.ma.forEach(ma-> 
				{ 
					// 0,1,2...10 
					int m_ma_type = IMa.indexOf(ma); 
					 
					local_map.put(m_ma_type,new ArrayList<Integer> ()); 
					 
					 
				}); 
				 
					this.getCross_map().put(ICross.indexOf(cr),local_map); 
		}); 
		 
	} 
	 
	public void init_mas_map() 
	{ 
		ICross.cross.forEach(cr-> 
		{ 
			Map<Integer,List<Integer>> local_map = new LinkedHashMap<Integer,List<Integer>>(); 
			 
				IMa.ma.forEach(ma-> 
				{ 
					// 0,1,2...10 
					int m_ma_type = IMa.indexOf(ma); 
					 
					local_map.put(m_ma_type,new ArrayList<Integer> ()); 
					 
					 
				}); 
				 
					this.getCross_mas_map().put(ICross.indexOf(cr),local_map); 
		}); 
	} 
		 
	 
	public boolean make_cross(Bar bar,Ma ma) 
	{ 
		 
		try 
		{ 
		 
		// Calculate. 
		ma.getAvg_map().keySet().forEach(ohlc -> 
		{ 
			 
			this.getCross_map().get(ohlc).keySet().forEach(ma_type ->{ 
			 
			double m_value = bar.get_by_type(ohlc); 
			 
			double m_avg_value = ma.getAvg_map().get(ohlc).get(ma_type).doubleValue(); 
			 
			// Get Type of cross; 
			 
			int m_cross_type = ICross.get_type_of_cross(m_value,m_avg_value); 
			 
			this.getCross_map().get(ohlc).get(ma_type).add(m_cross_type); 
				 
			}); // ma 
			 
		}); // ohlc 
		 
			return true; 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
	} 
	 
	 
	public boolean make_cross_mas(Ma ma) 
	{ 
						 
		try 
		{ 
		 
		// Calculate. 
		ma.getAvg_map().keySet().forEach(ohlc -> 
		{ 
			 
					this.getCross_mas_map().get(ohlc).keySet().forEach(ma_primary_type ->{ 
			 
						double m_left = ma.getAvg_map().get(ohlc).get(ma_primary_type).doubleValue(); 
						 
							this.getCross_mas_map().get(ohlc).keySet().forEach(ma_secondary_type ->{ 
				 
									double m_right = ma.getAvg_map().get(ohlc).get(ma_secondary_type).doubleValue(); 
									 
									if ((IMa.ma.get(ma_primary_type) > this.getDays_offset()-1) || 
											(IMa.ma.get(ma_secondary_type) > this.getDays_offset()-1) || 
										     ma_primary_type >= ma_secondary_type 
										)	 
									{ 
											m_right = m_left; 
									} 
									 
											int m_cross_type = ICross.get_type_of_cross(m_left,m_right); 
								 
											this.getCross_mas_map().get(ohlc).get(ma_primary_type).add(m_cross_type); 
											 
			 
				}); // secondary_type 
				 
			}); // primary_type 
			 
			 
		}); // ohlc 
		 
									return true; 
		} 
		catch(Exception e) 
		{ 
									return false; 
		} 
		 
	} 

	public boolean make_cross_mas_resc(Ma ma) 
	{ 
			 
		try 
		{ 
		 
		// Calculate. 
		ma.getAvg_map().keySet().forEach(ohlc -> 
		{ 
			 
					this.getCross_mas_map().get(ohlc).keySet().forEach(ma_primary_type ->{ 
			 
						double m_left = ma.getAvg_map().get(ohlc).get(ma_primary_type).doubleValue(); 
						 
							this.getCross_mas_map().get(ohlc).keySet().forEach(ma_secondary_type ->{ 
				 
									double m_right = ma.getAvg_map().get(ohlc).get(ma_secondary_type).doubleValue(); 
									 
									if ((IMa.ma.get(ma_secondary_type) > this.getDays_offset()-1) || 
											(IMa.ma.get(ma_primary_type) > this.getDays_offset()-1) || 
										ma_primary_type <= ma_secondary_type )	 
									{ 
											m_right = m_left; 
									} 
											int m_cross_type = ICross.get_type_of_cross(m_left,m_right); 
											 
											if ( DateUtil.is_equals(ma.getDt(),DateUtil.to_date("17.02.1998"))) 
													{ 
														gl.smn(gl.sf("Primary...%d...Secondary...%d...Left...%.4f...Right...%.4f...Result...%d", 
																ma_primary_type,ma_secondary_type,m_left,m_right,m_cross_type)); 
													} 
										 
											this.getCross_mas_map().get(ohlc).get(ma_secondary_type).add(m_cross_type); 
											 
											if ( DateUtil.is_equals(ma.getDt(),DateUtil.to_date("17.02.1998"))) 
											{ 
								 
												int m_size = this.getCross_mas_map().get(ohlc).get(ma_secondary_type).size(); 
												 
											this.getCross_mas_map().get(ohlc).get(ma_secondary_type).forEach(a->{ 
												 
												gl.smn(gl.sf("Size...%d...List source...%d",m_size,a)); 
												//gl.sm(gl.sf("%d ",a)); 
											}); 
											 
											} 
											 
			 
				}); // secondary_type 
				 
			}); // primary_type 
			 
			 
		}); // ohlc 
		 
									return true; 
		} 
		catch(Exception e) 
		{ 
									return false; 
		} 
		 
	} 

	 
	public static Cross get_instance(Bar bar,Ma ma) 
	{ 
		return new Cross(bar,ma); 
	} 
	 
	 
	public static Cross get_instance(Date dt) 
	{ 
		return new Cross(dt); 
	} 
	 
	public static Cross get_instance() 
	{ 
		return new Cross(); 
	} 
	 

	public static void test() 
	{ 
			Cross cross = Cross.get_instance(Bar.get_sample_instance(),Ma.get_sample_instance()); 
			 
			gl.smn(cross.toString()); 
			 
	} 
	 
	@Override 
	public String toString() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(gl.sf("%4d %s ",this.getDays_offset(),DateUtil.to_string(this.getDt()))); 
		 
		int [] index = {0}; 
		 
		int [] size  = {IMa.ma.size()}; 
				 
		this.getCross_list().forEach(cr-> 
		{ 
			if((index[0] % size[0]) == gl.E_EMPTY) 
				sb.append(gl.sf("%s"," ")); 
			 
				sb.append(gl.sf("%1d",cr)); 
			 
				index[0]++; 
		}); 
			 
			return sb.toString(); 
	} 
	 
	 
	public List<Integer> make_cross_list(Map<Integer,Map<Integer,List<Integer>>> source_map) 
	{ 
		 
		List<Integer> cross_list_loc = new ArrayList<Integer>(); 
		 
		source_map.keySet().forEach(ohlc-> 
		{ 
					 
			source_map.get(ohlc).keySet().forEach(ma_type->{ 
				source_map.get(ohlc).get(ma_type).forEach(cr->{ 
					cross_list_loc.add(cr); 
				}); 
			}); 
		}); 
			 
			 
			return cross_list_loc; 
	} 
	 
	public String  show_i_list(List<Integer> int_list) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		int_list.forEach(a->{ 
			 
			sb.append(gl.sf("%1d",a)); 
		}); 
		 	 
			return sb.toString(); 
	} 
	 
	public static void main(String[] args) { 
		 
			test(); 

	} 

} 
