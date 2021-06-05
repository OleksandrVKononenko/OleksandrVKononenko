package ap.explorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.btn.Order;
import ap.btn.TMa;
import ap.btn.Bar;
import ap.btn.TOrder;
import ap.global.gl;
import ap.log.Logger;
import ap.utils.Bau;
import ap.utils.DateUtil;
import ap.utils.Fu;
import ap.utils.MaUtil;

public class Audit {
	
	private Range 	date_range;
	
	private Bar 	range_bar;
	
	private MarketOrder 		order;
	
	

	public MarketOrder getOrder() {
		return order;
	}



	public void setOrder(MarketOrder order) {
		this.order = order;
	}



	public Bar getRange_bar() {
		return range_bar;
	}



	public void setRange_bar(Bar range_bar) {
		this.range_bar = range_bar;
	}



	public Range getDate_range() {
		return date_range;
	}



	public void setDate_range(Range date_range) {
		this.date_range = date_range;
	}



	public Audit()
	{
		
	}
	
	public Audit(MarketOrder order,List<Bar> quotes)
	{
		this.setOrder(order);
		
		this.setRange_bar(Bar.get_range_bar(
				quotes,
				Range.get_instance(this.getOrder().getOpen(),this.getOrder().getClose())));
				 
	}
	
	public static Audit get_instance(MarketOrder order,List<Bar> quotes)
	{

		return new Audit(order,quotes);
			           
	}
	
	
	
	public static Audit get_instance(Order order,List<Bar> quotes)
	{

		return null;
			           
	}
	
	public double get_drop()
	{
			if(this.getOrder().getType() == MarketOrder.BUY_MARKET) 
				return ( this.getOrder().getO() - this.getRange_bar().getL()); 
			else 
				return ( this.getRange_bar().getH() - this.getOrder().getO());
				 
		
	}
	
	public double get_peak()
	{
			if(this.getOrder().getType() == MarketOrder.BUY_MARKET) 
				return ( this.getRange_bar().getH() - this.getOrder().getO()); 
			else 
				return ( this.getOrder().getO() - this.getRange_bar().getL() );
				 
		
	}
	
	public double get_profit()
	{
			
			double m_body = this.getRange_bar().getBody();
			
			if(this.getOrder().getProfit()  < gl.E_EMPTY)
			   m_body *= gl.E_ERROR;
		
			return m_body;
	}
	
	
	@Override
	public String toString()
	{
			
		return gl.sf("[%s]",
				(
				(this.getOrder().getProfit() 		== this.get_profit()) &&
				(this.getOrder().getProfit_down() 	== get_drop())  &&
				(this.getOrder().getProfit_up() 	== get_peak())
				) ? gl.S_OK : 
					gl.sf("Profit:[%s %s] Drop:[%s %s] Peak :[%s %s] ",
							
							gl.fmt8(this.getOrder().getProfit()),
							gl.fmt8(get_profit()),
							
							gl.fmt8(this.getOrder().getProfit_down()),
							gl.fmt8(get_drop()),	
							
							gl.fmt8(this.getOrder().getProfit_up()),
							gl.fmt8(get_peak())
							)
							
				);
		
	}
	
	public String toString_debug()
	{
			
		return gl.sf("Profit:[%s %s] Drop:[%s %s] Peak :[%s %s] ",
				
				gl.fmt8(this.getOrder().getProfit()),
				gl.fmt8(get_profit()),
				
				gl.fmt8(this.getOrder().getProfit_down()),
				gl.fmt8(get_drop()),	
				
				gl.fmt8(this.getOrder().getProfit_up()),
				gl.fmt8(get_peak())
				
				);
		
	}
	

	 
	public static void doIt_last_ok() 
	{ 
		String source =  "e:\\bin\\spider\\look\\data\\ava\\usdchf.txt"; 
		 
		String [] series = {"",""}; 
		 
		String msg = "Load bars"; 
		
		Map<String,List<Bar>> bars = new HashMap<String,List<Bar>>();
		
		//List<Bar> 	bars = new ArrayList<Bar>(); 
				 
		if(!Bar.load_bars(source,bars,series)) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s", gl.S_ERROR,msg)); 
			 
			return; 
		} 

			gl.tracex(new Object() {}, String.format("%s...%s...series...%s...size...%d", gl.S_OK,msg,series[gl.E_OK],bars.get(series[gl.E_OK]).size())); 

		// Average. 
			 
			int PREV=gl.E_OK,CURRENT=gl.E_EMPTY; 
			 
			int [] index = {0}; 
			 
			long [] STATES = {0x0000,0x0000}; 
			 
			List<Integer> state = new ArrayList<Integer>(); 
			 
			List<String>  tetra = new ArrayList<String>(); 
			 
			 
			StringBuilder sb = new StringBuilder(); 
			 
			StringBuilder sb_e = new StringBuilder(); 
			 
			StringBuilder sba = new StringBuilder(); 
			 
			 
			// Arrays. 
			 
			byte [] sta = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
			 
			byte [] pre = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
			 
			 
			Logger log = Fu.get_log_file("explorer.log",true); 
			 
			Logger stat_log = Fu.get_log_file("stat.log",true); 
			 
			bars.get(series[gl.E_OK]).forEach(b-> 
			{ 
				    OHLC ohlc = new OHLC(b.getO(),b.getH(),b.getL(),b.getC()); 
				 
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.C;o++ ) 
						{ 
						 
						// Get averages. 
						 
						double 	mA = MaUtil.get_avg(b.getId(),bars.get(series[gl.E_OK]),gl.ma_period[i],o); 
						 
						double 	mE = b.get_by_type(o); 
								 
						int	   	mR = gl.decode(mE,mA); 
						 
						int		idx = 31 - ((((i+1)*4)-o)-1); 
						 
					 
						 
						if(mR == gl.E_OK) 
						sta[idx] = gl.E_OK; 
						else 
						sta[idx] = gl.E_EMPTY; 
						 
						 
				 
					} // O 
												 
				} // I 
						 
					 
					 
					 
					for(int i=0;i<sta.length;i++) 
					{ 
						sb.append(sta[i]); 
						 
							 
						if(pre[i] != sta[i]) 
						{ 
							// Change event. 
							if(pre[i] == gl.E_OK) 
							{ 
								 
								sba.append('-'); 
								 
								// Close previous +. 
															 
							} // Change to "-" 
							else 
							{ 
								 
								sba.append('+'); 
								 
							} // Change event to "+". 
						} 
						else 
						{ 
								// No change event. 
								sba.append('.'); 
						} 
					} 
					 
					// Add action. 
					 
						String STATE = sb.toString(); 
						 
						String ACTION = sba.toString(); 
						 
						STATES[CURRENT] = Bau.to_long_from_binary_str(STATE); 
							 
						String ms = String.format("%04d %s" 
								 
						+ "  %.4f %.4f %.4f %.4f " 
						 
						+ "  %s " 
						 
						+ "  %s " 
						 
						+ "  %10d ", 
						b.getId(),DateUtil.to_string(b.getDate()), 
						b.getO(),b.getH(),b.getL(),b.getC(), 
						 
						STATE, 
						 
						ACTION, 
						 
						STATES[CURRENT]); 
				 
				 
				gl.smn(ms); 
				 
				log.awrite(ms); 
				 
				// Begin recognition block. 
				 
				// End recognition block. 
				 
				// Clearing. 
				 
				tetra.clear(); 
				 
				sb_e.setLength(gl.E_EMPTY); 
				 
				sb.setLength(gl.E_EMPTY); 
				 
				sba.setLength(gl.E_EMPTY); 
				 
				 
				 
				STATES[PREV] = STATES[CURRENT]; 
				 
				for(int i=0;i<sta.length;i++) 
				{ 
					pre[i] = sta[i]; 
				} 
				 
				//index[0]++; 
				 
			});  // Cycle by bars. 
			 
			 
	} 
	 

}
