 
 
package ap.btn; 

import ap.global.gl; 

public class TLightOrderStat { 


	 
	private int initiator; 
	 
	private int closerator; 
	 
	private double stop_loss; 
	 
	private double take_profit; 
	 
	private double profit; 
	 
	private double lose; 
	 
	private double result; 
	 
	private int profit_cnt; 
	 
	private int loose_cnt; 
	 
	private int rating; 
	 
	private String ticker; 
	 
	 
		 
	public double getStop_loss() { 
		return stop_loss; 
	} 

	public void setStop_loss(double stop_loss) { 
		this.stop_loss = stop_loss; 
	} 

	public double getTake_profit() { 
		return take_profit; 
	} 

	public void setTake_profit(double take_profit) { 
		this.take_profit = take_profit; 
	} 

	public int getInitiator() { 
		return initiator; 
	} 

	public void setInitiator(int initiator) { 
		this.initiator = initiator; 
	} 

	public int getCloserator() { 
		return closerator; 
	} 

	public void setCloserator(int closerator) { 
		this.closerator = closerator; 
	} 

	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public int getRating() { 
		return rating; 
	} 

	public void setRating(int rating) { 
		this.rating = rating; 
	} 

	public double getProfit() { 
		return profit; 
	} 

	public void setProfit(double profit) { 
		this.profit = profit; 
	} 

	public double getLose() { 
		return lose; 
	} 

	public void setLose(double lose) { 
		this.lose = lose; 
	} 

	public double getResult() { 
		return result; 
	} 

	public void setResult(double result) { 
		this.result = result; 
	} 

	public int getProfit_cnt() { 
		return profit_cnt; 
	} 

	public void setProfit_cnt(int profit_cnt) { 
		this.profit_cnt = profit_cnt; 
	} 

	public int getLoose_cnt() { 
		return loose_cnt; 
	} 

	public void setLoose_cnt(int loose_cnt) { 
		this.loose_cnt = loose_cnt; 
	} 

	public TLightOrderStat() { 
		 
	} 
	 
	public TLightOrderStat(String ticker,int initiator,int closerator,double stop_loss,double take_profit, double profit,double lose,double result,int profit_cnt,int loose_cnt) 
	{ 
		this.setTicker(ticker); 
		 
		this.setInitiator(initiator); 
		 
		this.setCloserator(closerator); 
		 
		this.setStop_loss(stop_loss); 
		 
		this.setTake_profit(take_profit); 
		 
		this.setProfit(profit); 
		 
		this.setLose(lose); 
		 
		this.setResult(result); 
		 
		this.setProfit_cnt(profit_cnt); 
		 
		this.setLoose_cnt(loose_cnt); 
		 
	} 
	 
	public static TLightOrderStat getInstance(String ticker, int initiator,int closerator,double stop_loss,double take_profit,double profit,double lose,double result,int profit_cnt,int loose_cnt) 
	{ 
		return new TLightOrderStat(ticker,initiator,closerator,stop_loss,take_profit,profit,lose,result,profit_cnt,loose_cnt); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		 
		 
		String msg = String.format("%s %2d %2d %s %s %3d %3d %3d %s %s %s", 
				this.getTicker(), 
				this.getInitiator(), 
				this.getCloserator(), 
				gl.fmt8(this.getStop_loss()), 
				gl.fmt8(this.getTake_profit()), 
				this.getProfit_cnt(), 
				this.getLoose_cnt(), 
				(this.getProfit_cnt()+this.getLoose_cnt()), 
				gl.fmt2(this.getProfit()), 
				gl.fmt2(this.getLose()), 
				gl.fmt2(this.getResult()) 
				 
				); 
		 
		return msg; 

	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
