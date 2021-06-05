 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import ap.explorer.Audit;
import ap.explorer.Group;
import ap.explorer.Quote;
import ap.global.gl;
import ap.log.Logger;
import ap.utils.Biu;
import ap.utils.DateUtil; 
import ap.utils.Fu;
import ap.utils.IntArrayUtil;
import ap.utils.Su; 

public class Order { 

		public static int BUY_MARKET  = 0; 
		 
		public static int SELL_MARKET  = 1; 
		 
		public static int BUY_LIMIT  = 2; 
		 
		public static int STOP_LIMIT  = 3; 
		 
		public static int BUY_STOP  = 4; 
		 
		public static int SELL_STOP  = 5; 
	 
		private int 		id; 
		 
		private int 		type = gl.E_ERROR; 
		 
		private int 		initiator = gl.E_ERROR; 
		 
		private int 		closerator = gl.E_ERROR; 
		 
		private Date 		open; 
		 
		private Date 		close; 
		 
		private double 		o; 
		 
		private double 		c; 
		 
		private double 		profit; 
		 
		private double 		lost_profit = 0.0; 
		 
		private Quote 		quote; 
		 
		private int 		profit_alert; 
		 
		private double 		profit_up =0; 
		 
		private double 		profit_down=0; 
		 
		private String 		track = ""; 
		 
		private TPl 		pl; 
		 
		private int 		round_trip; 
		 
		private Date 		prefered_open_date; 
		 
		private long 		duration; 
		
	
		public double getLost_profit() { 
			return lost_profit; 
		} 


		public void setLost_profit(double lost_profit) { 
			this.lost_profit = lost_profit; 
		} 


		public long getDuration() { 
			return duration; 
		} 


		public void setDuration(long duration) { 
			this.duration = duration; 
		} 


		public Date getPrefered_open_date() { 
			return prefered_open_date; 
		} 


		public void setPrefered_open_date(Date prefered_open_date) { 
			this.prefered_open_date = prefered_open_date; 
		} 


		public int getRound_trip() { 
			return round_trip; 
		} 


		public void setRound_trip(int round_trip) { 
			this.round_trip = round_trip; 
		} 


		public TPl getPl() { 
			return pl; 
		} 


		public void setPl(TPl pl) { 
			this.pl = pl; 
		} 


		public String getTrack() { 
			return track; 
		} 


		public void setTrack(String track) { 
			this.track = track; 
		} 

		 
		public void trace(String msg) 
		{ 
			 
			this.setTrack(gl.sf("%s%s",this.getTrack(),msg)); 
			 
		} 

		public double getProfit_up() { 
			return profit_up; 
		} 


		public void setProfit_up(double profit_up) { 
			this.profit_up = profit_up; 
		} 


		public double getProfit_down() { 
			return profit_down; 
		} 


		public void setProfit_down(double profit_down) { 
			this.profit_down = profit_down; 
		} 


		public int getCloserator() { 
			return closerator; 
		} 


		public void setCloserator(int closerator) { 
			this.closerator = closerator; 
		} 


		public void alertInc() { 
			 
			profit_alert++; 
		} 
	 
	 
		public int getProfit_alert() { 
			return profit_alert; 
		} 

		public void setProfit_alert(int profit_alert) { 
			this.profit_alert = profit_alert; 
		} 

		public int getId() { 
			return id; 
		} 

		public void setId(int id) { 
			this.id = id; 
		} 

		public int getType() { 
			return type; 
		} 
		 
		public String getTypeS() { 
			return (this.type==gl.E_EMPTY) ? "B" : "S"; 
		} 
		 

		public void setType(int type) { 
			this.type = type; 
		} 

		public int getInitiator() { 
			return initiator; 
		} 

		public void setInitiator(int initiator) { 
			this.initiator = initiator; 
		} 

		public Date getOpen() { 
			return open; 
		} 

		public void setOpen(Date open) { 
			this.open = open; 
		} 

		public Date getClose() { 
			return close; 
		} 

		public void setClose(Date close) { 
			this.close = close; 
		} 

		public double getO() { 
			return o; 
		} 

		public void setO(double o) { 
			this.o = o; 
		} 

		public double getC() { 
			return c; 
		} 

		public void setC(double c) { 
			this.c = c; 
		} 

		public double getProfit() { 
			return profit; 
		} 

		public void setProfit(double profit) { 
			this.profit = profit; 
		} 

		

		public Quote getQuote() {
			return quote;
		}


		public void setQuote(Quote quote) {
			this.quote = quote;
		}


	public Order() { 
		 
	} 
	 
	public Order(Quote quote ,int id,int type,int initiator,Date open,Date close,double o,double c) 
	{ 
		this.setQuote(quote);
		 
		this.setId(id); 
		 
		this.setType(type); 
		 
		this.setInitiator(initiator); 
		 
		this.setOpen(open); 
		 
		this.setClose(close); 
		 
		this.setO(o); 
		 
		this.setC(c); 
	} 
	 
	 
	public Order(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c) 
	{ 
		this.setQuote(quote);
		 
		this.setId(id); 
		 
		this.setType(type); 
		 
		this.setInitiator(initiator); 
		 
		this.setCloserator(closerator); 
		 
		this.setOpen(open); 
		 
		this.setClose(close); 
		 
		this.setO(o); 
		 
		this.setC(c); 
	} 
	 
	public Order(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl,Date prefered_open_date) 
	{ 
		this(quote,id,type,initiator,closerator,open,close,o,c,pl); 
		 
		this.setPrefered_open_date(prefered_open_date); 
	} 
	 
	 
	public Order(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl) 
	{ 
		this(quote,id,type,initiator,closerator,open,close,o,c); 
		 
		this.setPl(pl); 
		 
	} 
		 
	public static Order getInstance(Quote quote,int id,int type,int initiator,Date open,Date close,double o,double c) 
	{ 
		return new Order(quote,id,type,initiator,open,close,o,c); 
	} 
	 
	public static Order getInstance(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c) 
	{ 
		return new Order(quote,id,type,initiator,closerator,open,close,o,c); 
	} 
	 
	public static Order getInstance(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl) 
	{ 
		return new Order(quote,id,type,initiator,closerator,open,close,o,c,pl); 
	} 
	 
	public static Order getInstance(Quote quote,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl,Date prefered_open_date) 
	{ 
		return new Order(quote,id,type,initiator,closerator,open,close,o,c,pl); 
	} 
	 

	public static List<Order> get_open_orders(List<Order> orders) 
	{ 
	
			return orders.stream().filter( 
			
			(o-> o.getClose() == null & o.getDuration() == gl.E_EMPTY)
			
			)
			
			.collect(Collectors.toList()); 

	}
	
	public static boolean get_orders(Quote quote,List<Bar> state_bars,List<Order> market_orders,long filter) 
	{ 
				 
		String msg = "Create market orders"; 
		 
		gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_OK,msg,gl.S_TRY)); 
		 
		if(state_bars.size() == gl.E_EMPTY) 
		{ 	 
			gl.tx_e(new Object() {}, gl.sf("%s %s",msg,"No input bars"));
			
			return false; 
			 
		} 
		 
		int CAPACITY = 32; 
		 
		byte [] pre = IntArrayUtil.init_byte_array(CAPACITY); 

		long start = System.nanoTime(); 
		
		state_bars.forEach(b-> 
		{ 
				byte [] sta = {1};//b.getState(); 
			 
			 	for(int i=0;i<sta.length;i++) 
				{ 
					 
					int initiator = i; 
					 		
			
					if(pre[i] != sta[i] && ((filter >> i) & 1 ) != gl.E_EMPTY) 
					{ 
						 
						// Change event. 
						if(pre[i] == gl.E_OK) 
						{ 
							 
							// Close buy orders. 
							// Get all orders that was open by its initiator before its date. 
							List<Order> buy_orders_list =  market_orders.stream().filter( 
									(bol-> ( 
											 bol.getInitiator()== initiator && 
											 bol.getType() == Order.BUY_MARKET && 
											 bol.getOpen() != null && 
											 bol.getOpen().before(b.getDate()) && 
											 bol.getClose() == null 
											))).collect(Collectors.toList()); 
							
							buy_orders_list.forEach(bo-> 
							{ 
								 bo.set_peak_and_drop(b);
								
								 // Strong close. 
								 
								 bo.closeOrder(b);
								 
								 // Audit audience.
								 
								 bo.auditOrder(state_bars);
								 
								
							}); 
							 
							// Open new SELL deals. 
							// gl.sfn("OHLC...[%s]...%d",gl.ohlc_names[(((31 - i) % 4))]);
							
							Order order = Order.getInstance(quote, 
									  market_orders.size()+1, 
									  Order.SELL_MARKET, 
									  initiator, 
									  initiator, 
									  b.getDate(), 
									  null, 
									  b.getO(), 
									  0.0, 
									  null, /*TPl*/ 
									  null 
									  ); 
							 
							  order.set_peak_and_drop(b); 
								 
							  market_orders.add(order); 
														 
						} // Change to "-" 
						else 
						{ 
							 
							// Close sell orders before buy order create. 
							 
							List<Order> sell_orders_list =  market_orders.stream().filter( 
									(s-> ( 
											 s.getInitiator()== initiator && 
											 s.getType() == Order.SELL_MARKET && 
											 s.getOpen() != null && 
											 s.getOpen().before(b.getDate()) && 
											 s.getClose() == null 
											))).collect(Collectors.toList()); 
								 
							sell_orders_list.forEach(so-> 
							{ 
								
									 so.set_peak_and_drop(b);
								
									 // Strong close. 
									 so.closeOrder(b);
								
									 // Audit audience.
									 
									 so.auditOrder(state_bars);
									 
									
							}); 
							 
							// Open new BUY deals. 
							 
							Order order = Order.getInstance(quote, 
									  market_orders.size()+1, 
									  Order.BUY_MARKET, 
									  initiator, 
									  initiator, 
									  b.getDate(), 
									  null, 
									  b.getO(), 
									  0.0, 
									  null, /*TPl*/ 
									  null 
									  ); 
							 
							  order.set_peak_and_drop(b); 
								 
							  market_orders.add(order); 
							 
						} // Change event to "+". 
					} 
					else 
					{ 
							// No change event. 

						    // Update open orders. 
							List<Order> open_orders =  market_orders.stream().filter( 
									(o-> (o.getClose() == null) && o.getOpen() != null)).collect(Collectors.toList()); 
				 
							// Updater. 
							// Set spike and drop_down. 
							 
							open_orders.forEach(o-> 
							{ 
								o.set_peak_and_drop(b); 
								 
							} 
							);  // Updater. 
					} 
				} // Global for(by Initiator). 
				 
			 	  // Set previous values. 
			 	 
				  for(int j=0;j<sta.length;j++) 
				  { 
					 pre[j] = sta[j]; 
				  } 
				 
		});  // Cycle by bars. 
		 
				boolean bl_result = (market_orders.size() != gl.E_EMPTY); 

				if(bl_result) 
					gl.tracex(new Object() {}, String.format("%s...%s...ticker...%s...size...%d...%d ms.", gl.S_OK,msg,quote.getPaper(),market_orders.size(),(System.nanoTime() - start)/1000000)); 
				else 
					gl.tracex(new Object() {}, String.format("%s...%s...ticker...%s...%s", gl.S_OK,msg,quote.getPaper(),"empty array")); 
						 
				return bl_result; 
	} 
	
	
	
	public static String get_group_total(List<Order> orders) 
	{ 
		StringBuilder store = new StringBuilder(); 
		 
		String total_result = ""; 
		 
		if(orders.size() != gl.E_EMPTY) 
		{ 
			if(Order.get_total_row(orders,store)) 
				total_result = store.toString(); 
		 
		} 
				return total_result; 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
	 
		return  String.format("%s %04d %1s %2d %2d %4s %s %s %3d %s %s %s %s %s %s %s ", 
					this.getQuote().getPaper(), 
					this.getId(), 
					this.getTypeS(), 
					this.getInitiator(), 
					this.getCloserator(), 
					gl.ma_names[7 - (this.getInitiator()/4)], 
					
					DateUtil.to_string(this.getOpen()), 
					DateUtil.to_string(this.getClose()), 
					
					(this.getDuration()),		 
					
					Su.fmt8d4(this.getO()),
					Su.fmt8d4(this.getC()),
					
					Su.fmt8d4(this.getProfit()),
					
					Su.fmt8d4(this.getProfit_down()),
					Su.fmt8d4(this.getProfit_up()),
					Su.fmt8d4(this.getLost_profit()),
					
					this.getTrack()); 
				
	} 
	

	
	public static boolean orders_to_file(String target_file,String source) 
	{ 
		Logger orders_log = Fu.get_log_file(target_file); 
		 
		orders_log.awrite(source); 
		 
		String msg = "Orders to file"; 
		 
		boolean bl_result = Fu.isaFile(target_file); 
		 
		if(bl_result) 
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_OK,msg,target_file)); 
		else 
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,target_file)); 
		 
		return bl_result; 
		 
	} 
	
	public  static String orders_to_string(List<Order> market_orders) 
	{ 
	 
		StringBuilder sb = new StringBuilder(); 
		 
		int [] counter = { gl.E_OK };
		
		market_orders.forEach(b -> { 
		 
			sb.append(gl.sf("%5d %s",counter[gl.E_EMPTY],b.toString())); 
			 
			sb.append(System.lineSeparator());
			
			counter[gl.E_EMPTY]++;
		 
		}); 
			
			return sb.toString(); 
			 
	} 
	
	public static boolean to_file(List<Order> orders,String target_file_name)
	{
	
		return Fu.to_file(target_file_name, orders_to_string(orders)); 
		
	}
	
	
	public static void show_orders(List<Order> orders)
	{
		
		if(orders == null)
			return;
		
		if(orders.size() != gl.E_EMPTY)
		{
			gl.sfn("%s\n%s%s\n",gl.replicate("-",114),orders_to_string(orders),gl.replicate("-",114));
		
		}

	}
	
	 
	 
	public boolean isClosed() 
	{ 
		return (this.getClose() != null); 
	} 
	 
	public static boolean setupAudit(List<Order> orders,List<Bar> quotes,String audit_file) 
	{ 
		StringBuilder sb = new StringBuilder(); 

		orders.forEach(o-> 
		{ 
			 
			if(o.getOpen() != null    &&  o.getClose() != null) 
			{ 
			 
				double	max_high = quotes.stream().filter( 
					a -> 
					DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
					) 
					.mapToDouble(a -> a.getH()).max().getAsDouble(); 
				 
				double	min_low = quotes.stream().filter( 
						a -> 
						DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
						) 
						.mapToDouble(a -> a.getL()).min().getAsDouble(); 

			 
			 
			String msg = ""; 
			 
			 
			if(o.getType() == TOrder.BUY_MARKET) 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_up()); 
				 
				double order_low = (o.getO()-o.getProfit_down()); 
			 
				 
				 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(min_low <= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_OK"); 
				} 
				 
			} 
			else 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_down()); 
				 
				double order_low = (o.getO()-o.getProfit_up()); 
					 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(max_high >= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_OK"); 
				} 
			 } // Check low. 
					 
					sb.append(o.toString()); 
					 
					sb.append(msg); 
					 
					sb.append(System.lineSeparator()); 
					 
			} // Check not null. 
		}); // Main cycle. 
		 
		return Fu.saveStringToFile(audit_file,sb.toString(),TConfiguration.REPORT_FILE_APPEND_MODE,true); 
					 
	} 

	 
	public static boolean get_orders_audit(List<Order> orders,List<Bar> quotes,String audit_file) 
	{ 
		StringBuilder sb = new StringBuilder(); 

		orders.forEach(o-> 
		{ 
			 
			if(o.getOpen() != null    &&  o.getClose() != null) 
			{ 
			 
				double	max_high = quotes.stream().filter( 
					a -> 
					DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
					) 
					.mapToDouble(a -> a.getH()).max().getAsDouble(); 
				 
				double	min_low = quotes.stream().filter( 
						a -> 
						DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
						) 
						.mapToDouble(a -> a.getL()).min().getAsDouble(); 

			 
			 
			String msg = ""; 
			 
			 
			if(o.getType() == TOrder.BUY_MARKET) 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_up()); 
				 
				double order_low = (o.getO()-o.getProfit_down()); 
			 
				 
				 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(min_low <= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_OK"); 
				} 
				 
			} 
			else 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_down()); 
				 
				double order_low = (o.getO()-o.getProfit_up()); 
					 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(max_high >= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_OK"); 
				} 
			 } // Check low. 
					 
					sb.append(o.toString()); 
					 
					sb.append(msg); 
					 
					sb.append(System.lineSeparator()); 
					 
			} // Check not null. 
		}); // Main cycle. 
		 
		return Fu.saveStringToFile(audit_file,sb.toString(),TConfiguration.REPORT_FILE_APPEND_MODE,true); 
					 
	} 
	 
	
	public static boolean get_orders_audit(List<Order> orders,List<Bar> quotes,StringBuilder audit_log_sb) 
	{ 
		
		StringBuilder audit_log = new StringBuilder(); 

		orders.forEach(o-> 
		{ 
			 
			if(o.getOpen() != null    &&  o.getClose() != null) 
			{ 
			 
				double	max_high = quotes.stream().filter( 
					a -> 
					DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
					) 
					.mapToDouble(a -> a.getH()).max().getAsDouble(); 
				 
				double	min_low = quotes.stream().filter( 
						a -> 
						DateUtil.in_range(a.getDate(),o.getOpen(),o.getClose()) 
						) 
						.mapToDouble(a -> a.getL()).min().getAsDouble(); 

			 
			 
			String msg = ""; 
			 
			 
			if(o.getType() == TOrder.BUY_MARKET) 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_up()); 
				 
				double order_low = (o.getO()-o.getProfit_down()); 
			 	 
				 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(min_low <= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_BUY_OK"); 
				} 
				 
			} 
			else 
			{ 
				 
				double order_high = (o.getO()+o.getProfit_down()); 
				 
				double order_low = (o.getO()-o.getProfit_up()); 
					 
				msg = String.format("%.4f %.4f %.4f %.4f...%.4f %.4f ",order_high,max_high,order_low,min_low,(order_high - max_high),(order_low - min_low)); 
				 
				if(max_high >= (o.getPl().getStop_loss() + o.getO()) && !o.getTrack().equalsIgnoreCase("_SL_") 
						&& !o.getTrack().equalsIgnoreCase("_TP_") 
						&& !o.getTrack().equalsIgnoreCase("_CR_") 
						) 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_ERROR"); 
				} 
				else 
				{ 
					msg = String.format("%s...%s",msg,"AU_SELL_OK"); 
				} 
			 } // Check low. 
					 
					audit_log.append(o.toString()); 
					 
					audit_log.append(msg); 
					 
					audit_log.append(System.lineSeparator()); 
					 
			} // Check not null. 
		}); // Main cycle. 
		 
		return true;	 
	} 
	
	
	
	
	public static boolean get_total_row(List<Order> lo ,StringBuilder store) 
	{ 
		 
		String msg = "Global Total Row"; 

		try 
		{ 
			
		long start = System.nanoTime(); 
		 
		double profit_t   = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).sum(); 
		 
		 
		double loss_t    = lo.stream().filter(a->a.getProfit()<0 ).mapToDouble(p->p.getProfit()).sum(); 
		
		
		 
		double result_t    = lo.stream().mapToDouble(p->p.getProfit()).sum(); 
		
		// Add-ins.
		
		double avg_profit  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
	
		double max_profit  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).max().getAsDouble(); 
		
		double max_profit_dropdown  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
		
		double avg_profit_dropdown  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_down()).average().getAsDouble(); 
		
		
		int  avg_duration_profit  	= (int)lo.stream().filter(a->a.getProfit() >=0).mapToLong(m->m.getDuration()).average().getAsDouble();
		
		
		double avg_loss	    = (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit()<0 ).mapToDouble(p->p.getProfit()).average().getAsDouble();
		
		double max_lose  	= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit()<0).mapToDouble(p->p.getProfit()).min().getAsDouble(); 
		
		int  avg_duration_lose  	= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : (int)lo.stream().filter(a->a.getProfit() <0).mapToLong(m->m.getDuration()).average().getAsDouble();

		
		long profit_cnt_t   = lo.stream().filter(a->a.getProfit()>=0).count(); 
		 
		long loss_cnt_t    = lo.stream().filter(a->a.getProfit()<0).count(); 
				 
		long result_cnt_t    = lo.stream().count(); 
		
		double profit_factor   	= 0.0d;
		
		if(loss_t==gl.E_EMPTY)
		{	
			profit_factor = profit_t*10000;
		}
		else if(loss_t <=gl.E_EMPTY)
			profit_factor = (profit_t*10000)/(loss_t*(-10000));	
		
		long l_profit_factor = Math.round(profit_factor);
		
		String result_msg = gl.sf("\r\nProfit : %s / %05d Avg : %.4f Days : %3d  Max peak : %s Max drop : %s Avg drop : %s\r\nLoss   : %s / %05d Avg : %.4f Days : %3d  Min drop : %s \r\nResult : %s / %05d  \nFactor : %12d\n", 
				gl.norm4f_base_12(profit_t), 
				profit_cnt_t, 
				avg_profit,
				avg_duration_profit,
				gl.norm4f_base_12(max_profit),
				gl.norm4f_base_12(max_profit_dropdown),
				gl.norm4f_base_12(avg_profit_dropdown),
				(loss_t < gl.E_EMPTY) ? gl.norm4f_base_12(loss_t*gl.E_ERROR) : gl.norm4f_base_12(0.0d), 
				loss_cnt_t,
				(avg_loss < gl.E_EMPTY) ? (avg_loss*gl.E_ERROR) : gl.E_EMPTY, 
				avg_duration_lose,
				gl.norm4f_base_12(max_lose),
				gl.norm4f_base_12(result_t), 
				result_cnt_t,
				l_profit_factor); 
		 
				store.append(result_msg); 
		 
				gl.tracex(new Object() {}, String.format("%s...%s...%d ms.", gl.S_OK,msg,(System.nanoTime() - start)/1000000)); 
	 
				return true; 
		
		} 
		catch(Exception e) 
		{ 
				gl.tracex(new Object() {}, gl.sf("%s...%s", gl.S_ERROR,msg)); 
			 
				return false; 
		} 
		 
	} 
	/*
	public static String toStringTotal(List<Order> list) 
	{ 
		 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clos = new ArrayList<Integer>(); 
		 
		List<Double> slos = new ArrayList<Double>(); 
		 
		List<Double> tpos = new ArrayList<Double>(); 
		 
		 
		list.forEach(l-> 
		{ 
			if(!inis.contains(l.getInitiator())) 
					inis.add(l.getInitiator()); 
			 
			if(!clos.contains(l.getCloserator())) 
				clos.add(l.getCloserator()); 
			 
			if(!slos.contains(l.getPl().getStop_loss())) 
				slos.add(l.getPl().getStop_loss()); 
		 
			if(!tpos.contains(l.getPl().getTake_profit())) 
				tpos.add(l.getPl().getTake_profit()); 
		 
		}); 
		 
		 
		inis.forEach(i-> 
		{ 
		 
			 
			clos.forEach(c->{ 
				 
			slos.forEach(s->{ 	 
			 
			tpos.forEach(t->{ 
				 
			double profit   = list.stream().filter(a->a.getProfit()>0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			double lose     = list.stream().filter(a->a.getProfit()<0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			double result   = list.stream().filter(a->a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			long  cnt_profit = list.stream().filter(a->a.getProfit()>0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).count(); 
			 
			long  cnt_lose   = list.stream().filter(a->a.getProfit()<0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).count(); 
			 
			long  cnt_all    = list.stream().filter( a->( a.getProfit() != 0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t) ).count(); 
		 
			String msg = String.format("%s %2d %2d %s %s %3d %3d %3d %s %s %s", 
					list.get(gl.E_EMPTY).getTicker(), 
					i, 
					c, 
					gl.fmt8(s), 
					gl.fmt8(t), 
					cnt_profit, 
					cnt_lose, 
					cnt_all, 
					gl.fmt2(profit), 
					gl.fmt2(lose), 
					gl.fmt2(result) 
					 
					); 
			 
					sb.append(msg); 
					 
				}); // tlos 
			  }); // slos 
			}); // clos 
		}); //inis 
	 
			return sb.toString(); 
	} 
	 */
	 
	public static List<TLightOrderStat> toListStat(List<Order> list) 
	{ 
		 
		List<TLightOrderStat> result = new ArrayList<TLightOrderStat>(); 
		 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clos = new ArrayList<Integer>(); 
		 
		List<Double> slos = new ArrayList<Double>(); 
		 
		List<Double> tpos = new ArrayList<Double>(); 
		 
		String ticker = list.get(gl.E_EMPTY).getQuote().getPaper(); 
				 
		list.forEach(l-> 
		{ 
			if(!inis.contains(l.getInitiator())) 
					inis.add(l.getInitiator()); 
			 
			if(!clos.contains(l.getCloserator())) 
				clos.add(l.getCloserator()); 
			 
			if(!slos.contains(l.getPl().getStop_loss())) 
				slos.add(l.getPl().getStop_loss()); 
		 
			if(!tpos.contains(l.getPl().getTake_profit())) 
				tpos.add(l.getPl().getTake_profit()); 
		 
		}); 
		 
		 
		inis.forEach(i-> 
		{ 
		 
			 
			clos.forEach(c->{ 
				 
			slos.forEach(s->{ 	 
			 
			tpos.forEach(t->{ 
				 
			double profit   = list.stream().filter(a->a.getProfit()>0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			double loose     = list.stream().filter(a->a.getProfit()<0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			double rslt   = list.stream().filter(a->a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).mapToDouble(p->p.getProfit()).sum(); 
			 
			long  cnt_profit = list.stream().filter(a->a.getProfit()>0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).count(); 
			 
			long  cnt_loose   = list.stream().filter(a->a.getProfit()<0 && a.getInitiator()==i && a.getCloserator()==c && a.getPl().getStop_loss()==s && a.getPl().getTake_profit()==t).count(); 

			TLightOrderStat oro = TLightOrderStat.getInstance(ticker,i,c,s,t,profit, loose, rslt, (int)cnt_profit,(int)cnt_loose); 
			 
			result.add(oro); 
				 
				}); // tlos 
			  }); // slos 
			}); // clos 
		}); //inis 
	 
		 /* 
		 
		double avg_drop = list.stream().filter( a->( a.getClose() != null)).mapToDouble(p-> p.getProfit_down()).average().getAsDouble(); 
		 
		double avg_up = list.stream().filter( a->( a.getClose() != null)).mapToDouble(p-> p.getProfit_up()).average().getAsDouble(); 
		 
		double max_profit = list.stream().filter( a->( a.getClose() != null)).mapToDouble(p-> p.getProfit_up()).sum(); 
		 
		double using_per_cent = list.stream().filter( a->( a.getClose() != null)).mapToDouble(p->p.getProfit()).sum(); 
		 
		double coef = (using_per_cent/max_profit)*100; 
		 
		String msg = String.format("%s %s \n%s %s \n%s %s \n%s %s \n%s %s %%", 
									gl.nr("Total profit"), 
									gl.fmt4(using_per_cent), 
									gl.nr("Average drop"), 
									gl.fmt4(avg_drop), 
									gl.nr("Average profit"), 
									gl.fmt4(avg_up), 
									gl.nr("Profit storage"), 
									gl.fmt4(max_profit), 
									gl.nr("Power using"), 
									gl.fmt2(coef)); 
		 
		sb.append(System.lineSeparator()); 
		 
		sb.append(System.lineSeparator()); 
		 
		sb.append(msg); 
		 
		 */ 
		 
		return result; 
	} 
	 
	public boolean auditOrder(List<Bar> bars)
	{
		try 
		{
			
			Audit audit = Audit.get_instance(this,bars);
			
			this.setTrack(audit.toString());
			
			gl.tx_k(new Object(){},gl.sf("%s",audit.toString()));
			
			return true;
		} 
		catch(Exception e)
		{
			gl.tx_e(new Object(){},gl.sf("Exception...%s",e.toString())); 
			
			return false;
		}
	}
	
	public boolean closeOrder(Bar a) 
	{ 
	 
			if(this.getClose() != null) 
				return true; 
				 
			 
				this.setClose(a.getDate()); 
				 
				this.setC(a.getO()); 
				 
			 	double profit_result = gl.E_EMPTY; 
				 
				if(this.getType() == TOrder.BUY_MARKET) 
					profit_result = (this.getC() - this.getO()); 
				else 
					profit_result = (this.getO() - this.getC()); 
				 
				 
				this.setProfit(profit_result); 
				
				 
				// Lost profit initialization. 
				 
				if(this.getProfit() >= gl.E_EMPTY) 
				{ 
					this.setLost_profit(this.getProfit_up() - this.getProfit()); 
				} else 
				{ 
					this.setLost_profit(this.getProfit_up()+( this.getProfit()* gl.E_ERROR)); 
				} 
				 
				this.setDuration(DateUtil.days_between(this.getOpen(),this.getClose())); 
				 
				this.trace("_CR_"); 
				 
				return (this.getClose() != null); 
				 
	} 
	 
	public boolean closeByStop(Bar a) 
	{ 
		 
		if ( this.getClose() != null) 
			return true; 
		 
		 
		double SL = (this.getPl().getStop_loss() * gl.E_ERROR); 
		 
		if(this.getPl().getStop_loss() != gl.E_EMPTY) 
		{ 
		 
			if((this.getProfit_down() * gl.E_ERROR ) <= SL) 
			{ 
				this.setProfit(SL); 
				 
				this.setClose(a.getDate()); 
				 
				this.setC(a.getO()); 
				 
				this.trace("_SL_"); 
				 
				return true; 
		 
			} 
		} 
				 	 
				return false; 
	} 
	 
	 
	public boolean closeByTakeProfit(Bar a) 
	{ 
		 
		if ( this.getClose() != null) 
			return true; 
		 
		 
		 double profit_result = 0.0; 
				 
		if(this.getPl().getTake_profit() != gl.E_EMPTY) 
		{ 
		 
			if(this.getProfit_up() >= this.getPl().getTake_profit()) 
			{ 
				this.setProfit(this.getPl().getTake_profit()); 
				 
				this.setClose(a.getDate()); 
				 
				this.setC(a.getO()); 
				 
				 
				if(this.getType() == TOrder.BUY_MARKET) 
					profit_result = (this.getO() + this.getPl().getTake_profit()); 
				else 
					profit_result = (this.getC() - this.getPl().getTake_profit()); 
				 
				this.setC(profit_result); 
				 
				this.trace("_TP_"); 
				 
				return true; 
		 
			} 
		} 
				 	 
				//this.trace("+"); 
			 
				return false; 
	} 
	 
	 
	 
	public void set_peak_and_drop(Bar a) 
	{ 
		if(this.getClose() != null) 
			return; 
		 
		if(this.getO() == gl.E_EMPTY) 
			return; 
		 
		 
		if (this.getType() == TOrder.BUY_MARKET) 
		 { 
			 double spike = (a.getH() - this.getO()); 
			 
			 if( spike > this.getProfit_up()) 
			 { 
				 this.setProfit_up(spike); 
			 } 
			 
			 double drop = (this.getO() - a.getL()); 
			 
			 if( drop > this.getProfit_down()) 
			 { 
				 this.setProfit_down(drop); 
			 } 
			 
			 
			 
		 } else if (this.getType() == TOrder.SELL_MARKET) 
		 { 
			 double spike = (this.getO() - a.getL()); 
			 
			 if( spike > this.getProfit_up()) 
			 { 
				 this.setProfit_up(spike); 
			 } 
			 
			 double drop = (a.getH() - this.getO()); 
			 
			 if( drop > this.getProfit_down()) 
			 { 
				 this.setProfit_down(drop); 
			 }
			 

			 //this.setProfit(this.getO() - a.getO());
			 
			 
		 } 
		
				this.setC(a.getO());
		  
	} 
	public static List<Double> getDoubleSeries(TDoublePair dp,double step) 
	{ 
	 
			List<Double> series = new ArrayList<Double>(); 
			 
			double step_count = ((dp.getHigh() - dp.getLow())/step)+1 ; 
			 
			for(int i=0; i < step_count;i++) 
			{ 
				double next = dp.getLow() + (step * i); 
				 
				series.add(next); 
			} 
			 
				return series; 
		 
	} 

	public static void Test_getDoubleSeries(TDoublePair dp,double step) 
	{ 
		List<Double> series = getDoubleSeries(dp,step); 
		 
		series.forEach(a-> 
		{ 
			gl.smn(gl.fmt8(a)); 
		} 
		); 
	} 
	 
	public static void main(String[] args) { 
		 
		Test_getDoubleSeries(new TDoublePair(0.0100,0.1000),0.0010); 

	} 

} 
// Revision : 10.09.2018 12:49:14 


