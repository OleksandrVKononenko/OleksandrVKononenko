 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Date; 
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import ap.explorer.Group;
import ap.global.gl;
import ap.utils.Biu;
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public class TLightOrder { 

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
		 
		private String 		ticker; 
		 
		private int 		profit_alert; 
		 
		private double 		profit_up =0; 
		 
		private double 		profit_down=0; 
		 
		private String 		track = ""; 
		 
		private TPl 		pl; 
		 
		private int 		round_trip; 
		 
		private Date 		prefered_open_date; 
		 
		private long 		duration; 
		
		private double		account_balance;
		 
		
		 
		public double getAccount_balance() {
			return account_balance;
		}


		public void setAccount_balance(double account_balance) {
			this.account_balance = account_balance;
		}


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

		 
		public void trace(String imsg) 
		{ 
			String msg = String.format("%s%s",this.getTrack(),imsg); 
			 
			this.setTrack(msg); 
			 
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

		public String getTicker() { 
			return ticker; 
		} 

		public void setTicker(String ticker) { 
			this.ticker = ticker; 
		} 

	public TLightOrder() { 
		 
	} 
	 
	public TLightOrder(String ticker,int id,int type,int initiator,Date open,Date close,double o,double c) 
	{ 
		this.setTicker(ticker); 
		 
		this.setId(id); 
		 
		this.setType(type); 
		 
		this.setInitiator(initiator); 
		 
		this.setOpen(open); 
		 
		this.setClose(close); 
		 
		this.setO(o); 
		 
		this.setC(c); 
	} 
	 
	 
	public TLightOrder(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c) 
	{ 
		this.setTicker(ticker); 
		 
		this.setId(id); 
		 
		this.setType(type); 
		 
		this.setInitiator(initiator); 
		 
		this.setCloserator(closerator); 
		 
		this.setOpen(open); 
		 
		this.setClose(close); 
		 
		this.setO(o); 
		 
		this.setC(c); 
	} 
	 
	public TLightOrder(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl,Date prefered_open_date) 
	{ 
		this(ticker,id,type,initiator,closerator,open,close,o,c,pl); 
		 
		this.setPrefered_open_date(prefered_open_date); 
	} 
	 
	 
	public TLightOrder(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl) 
	{ 
		this(ticker,id,type,initiator,closerator,open,close,o,c); 
		 
		this.setPl(pl); 
		 
	} 
		 
	public static TLightOrder getInstance(String ticker,int id,int type,int initiator,Date open,Date close,double o,double c) 
	{ 
		return new TLightOrder(ticker,id,type,initiator,open,close,o,c); 
	} 
	 
	public static TLightOrder getInstance(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c) 
	{ 
		return new TLightOrder(ticker,id,type,initiator,closerator,open,close,o,c); 
	} 
	 
	public static TLightOrder getInstance(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl) 
	{ 
		return new TLightOrder(ticker,id,type,initiator,closerator,open,close,o,c,pl); 
	} 
	 
	public static TLightOrder getInstance(String ticker,int id,int type,int initiator,int closerator,Date open,Date close,double o,double c,TPl pl,Date prefered_open_date) 
	{ 
		return new TLightOrder(ticker,id,type,initiator,closerator,open,close,o,c,pl); 
	} 
	 

	public static List<TLightOrder> get_open_orders(List<TLightOrder> orders) 
	{ 
	
			return orders.stream().filter( 
			
			(o-> o.getClose() == null & o.getDuration() == gl.E_EMPTY)
			
			)
			
			.collect(Collectors.toList()); 

	}
	
	public static String get_group_total(List<TLightOrder> orders) 
	{ 
		StringBuilder store = new StringBuilder(); 
		 
		String total_result = ""; 
		 
		if(orders.size() != gl.E_EMPTY) 
		{ 
			if(TLightOrder.get_total_row(orders,store)) 
				total_result = store.toString(); 
		 
		} 
				return total_result; 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
	 
		return  String.format("%s %04d %1s %2d %2d %4s %s %s %3d %s %s %s %s %s %s %s %s", 
					this.getTicker(), 
					this.getId(), 
					this.getTypeS(), 
					this.getInitiator(), 
					this.getCloserator(), 
					gl.ma_names[7 - (this.getInitiator()/4)], 
					DateUtil.to_string(this.getOpen()), 
					DateUtil.to_string(this.getClose()), 
					(this.getDuration()),		 
					gl.fmt8(this.getO()), 
					gl.fmt8(this.getC()), 
					gl.fmt8(this.getProfit()), 
					gl.fmt8(this.getProfit_down()), 
					gl.fmt8(this.getProfit_up()), 
					gl.fmt8(this.getLost_profit()), 
					this.getTrack() ,
					gl.fmt8(this.getAccount_balance()) 
					
					); 
		 
	} 
	 
	 
	public boolean isClosed() 
	{ 
		return (this.getClose() != null); 
	} 
	 
	public static boolean setupAudit(List<TLightOrder> orders,List<Bar> quotes,String audit_file) 
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

	
	public static boolean get_orders_audit(List<TLightOrder> orders,List<Bar> quotes,String audit_file) 
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
	 
	
	public static boolean get_orders_audit(List<TLightOrder> orders,List<Bar> quotes,StringBuilder audit_log_sb) 
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
	
	
	

	public static boolean get_total_row(List<TLightOrder> lo ,StringBuilder store) 
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
	 
	
	public static boolean get_groups(List<TLightOrder> lo , List<Group> groups) 
	{ 
		 
		String msg = "Make groups"; 
		 
		long start = System.nanoTime(); 
		 
		try {
			
		
		String tk[] = {lo.get(gl.E_EMPTY).getTicker().trim()}; 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> years = new ArrayList<Integer>(); 
		 
		 
		lo.forEach(l-> 
		{ 
			if(!years.contains(DateUtil.year(l.getOpen()))) 
					years.add(DateUtil.year(l.getOpen())); 
			 
			if(!inis.contains(l.getInitiator())) 
				inis.add(l.getInitiator()); 
		 
		}); 
		 
		 	int [] group = {0}; 
		 
		 	inis.stream().sorted().forEach(i -> { 
			 
			years.stream().sorted().forEach(d -> { 
			 
			double profit   	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			
			double profit_avg  	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
			
			
			double profit_peak  	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).max().getAsDouble(); 
			
			int profit_dur_avg  	= (int) lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getDuration()).average().getAsDouble(); 
			
			
			double profit_down_max  = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
			
			double profit_down_avg  = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).average().getAsDouble(); 
			
			
			double loss    		= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d ).mapToDouble(p->p.getProfit()).sum(); 
			
			double loss_avg    	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d ).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
			
			double result    	= lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			
			double 			loss_peak  	= 0; 
			
			OptionalDouble 	op_loss_peak = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).min();
			
			if(op_loss_peak.isPresent())
				loss_peak = op_loss_peak.getAsDouble();
			
			
			double 			loss_up  	= 0; 
			
			OptionalDouble 	op_loss_up = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_up()).max();
			
			if(op_loss_up.isPresent())
				loss_up = op_loss_up.getAsDouble();
			
			double loss_up_avg  = 0; 

			OptionalDouble op_loss_up_avg = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_up()).average();
			
			if(op_loss_up_avg.isPresent())
				loss_up_avg = op_loss_up_avg.getAsDouble();
			
			
			double loss_down  	= 0; 

			OptionalDouble op_loss_down = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).max();
			
			if(op_loss_down.isPresent())
				loss_down = op_loss_down.getAsDouble();
			
			double loss_down_avg  = 0; 
			
			OptionalDouble op_loss_down_avg   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).average();
			
			if(op_loss_down_avg.isPresent())
				loss_down_avg = op_loss_down_avg.getAsDouble();
			
			 
			long profit_cnt   	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long loss_cnt    	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long result_cnt    	= lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen())== d).count(); 
		 
			double profit_factor   	= 0.0d;//(profit*10000) / ((loss==0.0) ? (profit*10000) : (loss*(-10000)));
			
			if(loss==gl.E_EMPTY)
			{	
				profit_factor = profit*10000;
			}
			else if(loss <=gl.E_EMPTY)
				profit_factor = (profit*10000)/(loss*(-10000.0));	
				
			
			int loss_dur_avg  	= (int) lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getDuration()).average().getAsDouble(); 
			
			
			group[0] = 7 - (i/4); 
			 
			Group gr = Group.get_instance(
					null,//tk[0], 
					//4 - (group[0]*4 - i),
					//group[0] ,
					i,
					i,
					(int)d, 
					profit,
					(int)profit_cnt,
					profit_avg,
					profit_down_avg,
					profit_down_max,
					
					profit_peak ,
					profit_dur_avg,
					
					profit_factor,
					
					loss, 
					(int)loss_cnt,
					loss_avg,
					loss_dur_avg,
					
					loss_peak,
					loss_up,
					loss_up_avg ,
					loss_down, 
					loss_down_avg,
					
					result, 
					(int)result_cnt,0);
		
			groups.add(gr);
			 
			}); // years. 
			 
		}); // inis. 
			 
		boolean bl_result = (groups.size() != gl.E_EMPTY); 
		 
		if(bl_result) 
			gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,groups.size(),(System.nanoTime() - start)/1000000)); 
		else 
			gl.tracex(new Object() {}, String.format("%s...%s", gl.S_ERROR,msg)); 
				 
		 
			return bl_result; 
		
			
		}
		catch(Exception e)
		{
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,e.toString())); 
			
			return false;
		}
	} 	   

  
	/*
	public static boolean exportToPortfolioLight_v2(List<TLightOrder> lo , List<TPortfolioStat> ps,TDateRange range) 
	{ 
		 
		String msg = "Export to portfolio collection"; 
		 
		long start = System.nanoTime(); 
		 
		String tk[] = {lo.get(gl.E_EMPTY).getTicker().trim()}; 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clo = new ArrayList<Integer>(); 
		 
		List<Integer> days = new ArrayList<Integer>(); 
		 
		List<Integer> years = new ArrayList<Integer>(); 
		 
		 
		lo.forEach(l-> 
		{ 
			if(!years.contains(DateUtil.year(l.getOpen()))) 
					years.add(DateUtil.year(l.getOpen())); 
			 
			if(!inis.contains(l.getInitiator())) 
				inis.add(l.getInitiator()); 
		 
		}); 
		 
		 
		 
		int [] i_max = {0,0}; 
			inis.stream().sorted().forEach(i -> { 
			 
			years.stream().sorted().forEach(d -> { 
			 
			double profit   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			 
			double loss    = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d ).mapToDouble(p->p.getProfit()).sum(); 
			 
			double result    = lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			 
			 
			long profit_cnt   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long loss_cnt    = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long result_cnt    = lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen())== d).count(); 
			 
			 
			String m = gl.sf("Init : %2d Year : %4d Profit : %.4f  %5d Loss : %.4f %5d Result : %.4f %5d ", 
					i, 
					d, 
					profit, 
					profit_cnt, 
					loss, 
					loss_cnt, 
					result, 
					result_cnt); 
			 
			if(profit != gl.E_EMPTY || loss != gl.E_EMPTY) 
				gl.smn(m); 
			 
			}); // years. 
			 
		}); // inis. 
		 
		 
		 
		 
				boolean bl_result = (lo.size() == ps.size()); 
				 
				if(bl_result) 
					gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,ps.size(),(System.nanoTime() - start)/1000000)); 
				else 
					gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,"last level")); 
						 
				 
				return bl_result; 
		 
	} 
	
	*/
	 
	/*
	public static boolean exportToPortfolioLight_v1(List<TLightOrder> lo , List<TPortfolioStat> ps,TDateRange range) 
	{ 
		 
		String msg = "Export to portfolio collection"; 
		 
		long start = System.nanoTime(); 
		 
		String tk[] = {lo.get(gl.E_EMPTY).getTicker().trim()}; 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clo = new ArrayList<Integer>(); 
		 
		List<Integer> days = new ArrayList<Integer>(); 
		 
		 
		int [] i_max = {0,0}; 
		 
		for(int i=20;i<24;i++) 
		{ 
			 
			inis.add(i); 
			 
			i_max[0] = i; 
			 
		} 
		 
		for(int i=0;i<1000;i++) 
			days.add(i); 
				 
		 
		inis.stream().sorted().forEach(i -> { 
			    		 
			 
				double profit   = lo.stream().filter(a->a.getInitiator()==i  && 
														  a.getCloserator()==i 
														  ).mapToDouble(p->p.getProfit()).sum(); 
			 
				String m = gl.sf("Total : %2d %.4f ",i,profit); 
				 
				gl.smn(m); 

		}); // inis. 
		 
		double profit_t   = lo.stream().filter(a-> a.getInitiator() > (i_max[0]-3) && a.getInitiator() <= i_max[0] ).mapToDouble(p->p.getProfit()).sum(); 

		String mt = gl.sf("Group total : %.4f ",profit_t); 

		gl.smn(mt); 

		 
		 
		 
		inis.stream().sorted().forEach(i -> { 
			 
		days.stream().sorted().forEach(d -> { 
		 
		double profit   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>0 && a.getDuration() == d).mapToDouble(p->p.getProfit()).sum(); 
		 
		double loss    = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && a.getDuration() == d ).mapToDouble(p->p.getProfit()).sum(); 
		 
		double result    = lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && a.getDuration() == d).mapToDouble(p->p.getProfit()).sum(); 
		 
		 
		long profit_cnt   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>0 && a.getDuration() == d).count(); 
		 
		long loss_cnt    = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && a.getDuration() == d).count(); 
		 
		long result_cnt    = lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && a.getDuration() == d).count(); 
		 
		 
		String m = gl.sf("Init : %2d Days: %3d Profit : %.4f  %5d Loss : %.4f %5d Result : %.4f %5d ", 
				i, 
				d, 
				profit, 
				profit_cnt, 
				loss, 
				loss_cnt, 
				result, 
				result_cnt); 
		 
		if(profit != gl.E_EMPTY || loss != gl.E_EMPTY) 
			gl.smn(m); 
		 
		}); // days. 
		 
	}); // inis. 
		 

				boolean bl_result = (lo.size() == ps.size()); 
				 
				if(bl_result) 
					gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,ps.size(),(System.nanoTime() - start)/1000000)); 
				else 
					gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,"last level")); 
						 
				 
				return bl_result; 
		 
	} 
*/
	
	
	/*
	
	public static boolean exportToPortfolio(List<TLightOrder> lo , List<TPortfolioStat> ps,TDateRange range) 
	{ 
		 
		String msg = "Export to portfolio collection"; 
		 
		long start = System.nanoTime(); 
		 
		String tk[] = {lo.get(gl.E_EMPTY).getTicker().trim()}; 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clo = new ArrayList<Integer>(); 
		 
		List<Double> stp = new ArrayList<Double>(); 
		 
		List<Double> tkp = new ArrayList<Double>(); 
		 
		 
		lo.forEach(l-> 
		{ 
			if(!inis.contains(l.getInitiator())) 
					inis.add(l.getInitiator()); 
			 
			if(!clo.contains(l.getCloserator())) 
				clo.add(l.getCloserator()); 
			 
			if(!stp.contains(l.getPl().getStop_loss())) 
				stp.add(l.getPl().getStop_loss()); 
			 
			if(!tkp.contains(l.getPl().getTake_profit())) 
				tkp.add(l.getPl().getTake_profit()); 
			 
		 
		}); 
		 
		inis.forEach(i -> { 

			clo.forEach(c -> { 

				stp.forEach(sp -> { 

					tkp.forEach(tp -> { 

						double result = lo.stream().filter( 
								a -> 
								a.getInitiator() == i && 
								a.getCloserator() == c && 
								a.getPl().getStop_loss() == sp && 
								a.getPl().getTake_profit() == tp 
								) 
								.mapToDouble(p -> p.getProfit()).sum(); 

						if (result != gl.E_EMPTY) { 
							ps.add(TPortfolioStat.getInstance(tk[0], i, c,sp,tp,range, result)); 
						} 

					}); // stp 

				}); // tkp 
			}); // clo 

		}); // inis 

				boolean bl_result = (lo.size() == ps.size()); 
				 
				if(bl_result) 
					gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,ps.size(),(System.nanoTime() - start)/1000000)); 
				else 
					gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,"last level")); 
						 
				 
				return bl_result; 
		 
	} 
	 */
	
	public static String toStringTotal(List<TLightOrder> list) 
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
		 
		return sb.toString(); 
	} 
	 
	 
	public static List<TLightOrderStat> toListStat(List<TLightOrder> list) 
	{ 
		 
		List<TLightOrderStat> result = new ArrayList<TLightOrderStat>(); 
		 
		 
		List<Integer> inis = new ArrayList<Integer>(); 
		 
		List<Integer> clos = new ArrayList<Integer>(); 
		 
		List<Double> slos = new ArrayList<Double>(); 
		 
		List<Double> tpos = new ArrayList<Double>(); 
		 
		String ticker = list.get(gl.E_EMPTY).getTicker(); 
				 
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
				
				// Account balance tracking.
				
				this.setAccount_balance(this.getAccount_balance()+this.getProfit());
				 
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
			 
		 } 
		 
				//this.setTrack("_sc_"); 
		 
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


