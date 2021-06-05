package ap.explorer;

import java.awt.Dimension;
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import ap.btn.Bar;
import ap.btn.Order;
import ap.btn.TOrder;
import ap.explorer.Audit;
import ap.explorer.Group;
import ap.explorer.Quote;
import ap.global.gl;
import ap.log.Logger;
import ap.prop.SDimension;
import ap.utils.Biu;
import ap.utils.DateUtil; 
import ap.utils.Fu;
import ap.utils.IntArrayUtil;
import ap.utils.Su; 

public class MarketOrder { 

		public static int BUY_MARKET  	= 0; 
		 
		public static int SELL_MARKET  	= 1; 
		 
		public static int BUY_LIMIT  	= 2; 
		 
		public static int STOP_LIMIT  	= 3; 
		 
		public static int BUY_STOP  	= 4; 
		 
		public static int SELL_STOP  	= 5; 
	 
		private int 		id; 
		 
		private int 		type = gl.E_ERROR; 
		
		private Dimension 	initiator;
		
		private Dimension 	closerator;
		
		
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
		
		private int 		round_trip; 
		 
		private Date 		prefered_open_date; 
		 
		private long 		duration; 
		
	
		
		public Dimension getInitiator() {
			return initiator;
		}


		public void setInitiator(Dimension initiator) {
			this.initiator = initiator;
		}


		public Dimension getCloserator() {
			return closerator;
		}


		public void setCloserator(Dimension closerator) {
			this.closerator = closerator;
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


	public MarketOrder() { 
		 
	} 
	 
	public MarketOrder(Quote quote ,int id,int type,Dimension initiator,Date open,Date close,double o,double c) 
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
	 
	 
	public MarketOrder(Quote quote,int id,int type,Dimension initiator,Dimension closerator,Date open,Date close,double o,double c) 
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
	 
	public MarketOrder(Quote quote,int id,int type,Dimension initiator,Dimension closerator,Date open,Date close,double o,double c,Date prefered_open_date) 
	{ 
		this(quote,id,type,initiator,closerator,open,close,o,c); 
		 
		this.setPrefered_open_date(prefered_open_date); 
	} 
	 
	
	
	public static MarketOrder get_instance(Quote quote,int id,int type,Dimension initiator,Dimension closerator,Date open,Date close,double o,double c) 
	{ 
		return new MarketOrder(quote,id,type,initiator,closerator,open,close,o,c); 
	} 
	 


	public static List<MarketOrder> get_open_orders(List<MarketOrder> orders) 
	{ 
	
			return orders.stream().filter( 
			
			(o-> o.getClose() == null & o.getDuration() == gl.E_EMPTY)
			
			)
			
			.collect(Collectors.toList()); 

	}
	

		
	public boolean closeOrder(Bar a) 
	{ 
	 
			if(this.getClose() != null) 
				return true; 
				 
			 
				this.setClose(a.getDate()); 
				 
				this.setC(a.getO()); 
				 
			 	double profit_result = gl.E_EMPTY; 
				 
				if(this.getType() == BUY_MARKET) 
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
	 
	
	 
	 
	 
	public void set_peak_and_drop(Bar a) 
	{ 
		if(this.getClose() != null) 
			return; 
		 
		if(this.getO() == gl.E_EMPTY) 
			return; 
		 
		 
		if (this.getType() == BUY_MARKET) 
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
			 
			 
			 
		 } else if (this.getType() == SELL_MARKET) 
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
		
				this.setC(a.getO());
		  
	} 
	
	public boolean auditOrder(List<Bar> bars)
	{
		try 
		{
			
			Audit audit = Audit.get_instance(this,bars);
			
			this.setTrack(audit.toString());
			
			//gl.tx_k(new Object(){},gl.sf("%s",audit.toString()));
			
			return true;
		} 
		catch(Exception e)
		{
			gl.tx_e(new Object(){},gl.sf("Exception...%s",e.toString())); 
			
			return false;
		}
	}
	
	@Override 
	public String toString() 
	{ 
	 
		return  String.format("%s %04d %1s [%s %s] [%s %s] %3d %s %s [%s] %s %s %s %s ", 
					this.getQuote().getPaper(), 
					this.getId(), 
					this.getTypeS(), 
					
					gl.sf("%2d,%1d",this.getInitiator().width,this.getInitiator().height),
					gl.sf("%2d,%1d",this.getCloserator().width,this.getCloserator().height),
					
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
	
	
	 
	public static void main(String[] args) { 
		 
	
	} 

} 

