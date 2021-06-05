 
 
package ap.swing; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Date; 
import java.util.List; 
import java.util.Map; 

import ap.btn.Bar; 
import ap.btn.Order; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.utils.Fu; 

public interface IOrder { 
	 
	public static final List<String> orders		= Arrays.asList(new String[]{"B","S","BL","SL","BS","SS","TP","NONE"}); 
	 
	 
	public static int indexOf(String order) 
	{ 
		return orders.indexOf(order); 
	} 

	 
	public static String indexOf(int order) 
	{ 
		return orders.get(order); 
	} 
	 
	public static int make_orders(Map<Date,Bar> p_bars,List<Event> m_event_list) 
	{ 
		 
		List<Order> market_orders = new ArrayList<Order>(); 
		 
		return gl.E_OK; 
	 
	} 
	 
	public static void write(String file,List<Order> list) 
	{ 
		 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 list.forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
		 
		 	log.awrite("IOrder.toTotalString(list)"); 
	} 
	 
	/*
	public static String toTotalString(List<Order> m_list) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		int ROW_LEN = 115; 
		 
		sb.append(gl.sf("+%s+",gl.replicate('-', ROW_LEN))); 
		 
		sb.append(System.lineSeparator()); 
		 
		String  m_blank = gl.sf("%s",gl.replicate(' ',ROW_LEN - 52)); 
		 
		sb.append(m_blank); 
		 
		String  m_row = gl.sf(" %.4f %.4f %.4f", 
				m_list.stream().mapToDouble(o->o.getProfit_commit()).sum(), 
				m_list.stream().mapToDouble(o->o.getProfit_up()).sum(), 
				m_list.stream().mapToDouble(o->o.getProfit_down()).sum() 
				); 
		 
		sb.append(m_row); 
		 
		return sb.toString(); 
	} 
	*/
	 
	 
} 
