 
package ap.btn; 

import java.util.Date; 
import java.util.List; 
import java.util.Locale; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TOrder { 

	 
	public static int BUY_MARKET  = 0; 
	 
	public static int SELL_MARKET  = 1; 
	 
	public static int BUY_LIMIT  = 2; 
	 
	public static int STOP_LIMIT  = 3; 
	 
	public static int BUY_STOP  = 4; 
	 
	public static int SELL_STOP  = 5; 
	 
	private int id; 
	 
	private int type = gl.E_ERROR; 
	 
	private int initiator = gl.E_ERROR; 
	 
	private Date open; 
	 
	private Date close; 
	 
	private double volume; 
	 
	private double rate_open; 
	 
	private double rate_close; 
	 
	private double stop; 
	 
	private double profit; 
	 
	private String ticker; 
	 
	private double drop_down_value; 
	 
	private double pick_up_value; 
	 
	private double lost_profit; 
			 	 
	private double utilization_rate; 
	 
	 
	 
	public double getUtilization_rate() { 
		return utilization_rate; 
	} 

	public void setUtilization_rate(double utilization_rate) { 
		this.utilization_rate = utilization_rate; 
	} 

	public double getLost_profit() { 
		return lost_profit; 
	} 

	public void setLost_profit(double lost_profit) { 
		this.lost_profit = lost_profit; 
	} 

	public double getPick_up_value() { 
		return pick_up_value; 
	} 

	public void setPick_up_value(double pick_up_value) { 
		this.pick_up_value = pick_up_value; 
	} 

	public double getDrop_down_value() { 
		return drop_down_value; 
	} 

	public void setDrop_down_value(double drop_down_value) { 
		this.drop_down_value = drop_down_value; 
	} 

	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public int getInitiator() { 
		return initiator; 
	} 

	public void setInitiator(int initiator) { 
		this.initiator = initiator; 
	} 

	public double getRate_open() { 
		return rate_open; 
	} 

	public void setRate_open(double rate_open) { 
		this.rate_open = rate_open; 
	} 

	public double getRate_close() { 
		return rate_close; 
	} 

	public void setRate_close(double rate_close) { 
		this.rate_close = rate_close; 
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

	public double getVolume() { 
		return volume; 
	} 

	public void setVolume(double volume) { 
		this.volume = volume; 
	} 

	 
	public double getStop() { 
		return stop; 
	} 

	public void setStop(double stop) { 
		this.stop = stop; 
	} 

	 
	public double getProfit() { 
		return profit; 
	} 

	public void setProfit(double profit) { 
		this.profit = profit; 
	} 

	public TOrder() { 
		 
	} 
	 
	 
	 
	public TOrder(String ticker,int id,int type,int initiator,Date open ,Date close,double open_rate,double close_rate) 
	{ 
		this.setTicker(ticker); 
		 
		this.setId(id); 
		 
		this.setType(type); 
		 
		this.setInitiator(initiator); 
		 
		this.setOpen(open); 
		 
		this.setClose(close); 
		 
		this.setRate_open(open_rate); 
		 
		this.setRate_close(close_rate); 
		 
		this.setProfit(this.getTakeProfit()); 
		 
	} 
	 
		 
	public TOrder(String ticker,int id,int type,int initiator,Date open ,Date close,double open_rate,double close_rate,TDoublePair high_low) 
	{ 
		this(ticker,id,type,initiator,open,close,open_rate,close_rate); 
		 
		double drop = 0.0f; 
		 
		double pick = 0.0f; 
		 
		if (this.getType() == TOrder.BUY_MARKET) 
		{ 
			drop = (open_rate - high_low.getLow()); 
			 
			pick = (high_low.getHigh() - open_rate); 
			 
		} 
		else if (this.getType() == TOrder.SELL_MARKET) 
		{ 
			drop = (high_low.getHigh() - open_rate); 
			 
			pick = (open_rate - high_low.getLow()); 
		} 
			 
		//Low of drop. 
		this.setDrop_down_value(drop); 
		 
		// High of profit. 
		this.setPick_up_value(pick); 
		 
		//Lost profit value. 
		 
		if(this.getProfit() < gl.E_EMPTY) 
		this.setLost_profit(this.getPick_up_value());			 
		else 
		this.setLost_profit(this.getPick_up_value() - this.getProfit()); 
		 
		//Utilization rate. 
		 
		if(this.getLost_profit() == gl.E_EMPTY && this.getProfit() > gl.E_EMPTY) 
			this.setUtilization_rate(100.0); 
		else if(this.getProfit() <= gl.E_EMPTY) 
		{ 
			this.setUtilization_rate(0.0); 
			 
			this.setLost_profit(gl.E_EMPTY); 
		} 
		else 
		{ 
			double utilization = (this.getProfit() / this.getPick_up_value()); 
					 
			this.setUtilization_rate(utilization); 
		} 
		 
		// Take profit and stop using. 
			 
		if(TConfiguration.STOP_LOSS != gl.E_EMPTY && this.getProfit() <= TConfiguration.STOP_LOSS) 
		{ 
			this.setProfit(TConfiguration.STOP_LOSS); 
		} 
		 
		if(TConfiguration.TAKE_PROFIT != gl.E_EMPTY && this.getProfit() >= TConfiguration.TAKE_PROFIT) 
		{ 
			this.setProfit(TConfiguration.TAKE_PROFIT); 
		} 
		 
		 
	} 
	 
	public static TOrder getInstance(String ticker,int id,int type,int initiator , Date open ,Date close,double open_rate,double close_rate) 
	{ 
		return new TOrder(ticker,id,type,initiator,open ,close,open_rate,close_rate); 
	} 
	 
	 
	public static TOrder getInstance(String ticker,int id,int type,int initiator , Date open ,Date close,double open_rate,double close_rate,TDoublePair drop_down) 
	{ 
		return new TOrder(ticker,id,type,initiator,open ,close,open_rate,close_rate,drop_down); 
	} 
	 
	 
	 
	 
	public double getTakeProfit() 
	{ 
		double value = 0.0f; 
		 
		if (this.getType() == TOrder.BUY_MARKET) 
			value = (this.getRate_close() - this.getRate_open()); 
		else if (this.getType() == TOrder.SELL_MARKET) 
			value = (this.getRate_open() - this.getRate_close()); 
		 
		return value; 
		 
	} 
	 
	 
		 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%s,%03d,%01d,%02d,%s,%s,%3d,%s,%s,%s,%s,%s,%s,%s %%", 
		this.getTicker(), 
		this.getId(), 
		this.getType(), 
		this.getInitiator(), 
		DateUtil.to_string(this.getOpen()), 
		DateUtil.to_string(this.getClose()), 
		DateUtil.days_between(this.getOpen(),this.getClose()), 
		gl.format4d(this.getRate_open(),gl.AL.RIGHT,8), 
		gl.format4d(this.getRate_close(),gl.AL.RIGHT,8), 
		gl.format4d(this.getProfit(),gl.AL.RIGHT,8), 
		gl.format4d(this.getDrop_down_value(),gl.AL.RIGHT,8), 
		gl.format4d(this.getPick_up_value(),gl.AL.RIGHT,8), 
		gl.format4d(this.getLost_profit(),gl.AL.RIGHT,8), 
		gl.format4d(this.getUtilization_rate(),gl.AL.RIGHT,8) 
		 
		); 
		 

	} 
	 
	public static String getInitiatorsToString(List<Integer> initiators) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		int cnt[] = {1}; 
		 
		//int collection_size = TConfiguration.initiators.size(); 
				 
		int collection_size = initiators.size(); 
		 
		//TConfiguration.initiators.forEach(a-> 
		initiators.forEach(a-> 
		{ 
			sb.append(String.format("%d",a)); 
			 
			if(cnt[0] < collection_size) 
				sb.append(String.format("%s",",")); 
			 
			cnt[0]++; 
		} 
		); 
		 
			return sb.toString(); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
