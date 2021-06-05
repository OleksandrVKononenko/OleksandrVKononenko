 
package ap.btn; 

 
import java.util.List;

import ap.explorer.Range;
import ap.global.gl; 
import ap.utils.DateUtil; 


public class TPortfolioStat { 

	 
	public static int YEAR  	= 1; 
	 
	public static int MONTH   	= 2; 
	 
	public static int QUARTER 	= 3; 
	 
	 
	private String ticker; 
	 
	private  int initiator; 
	 
	private  int closer; 
	 
	private Range date_range; 
	 
	private double stop_loss; 
	 
	private double take_profit; 
	 

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


	private double result; 
	 
	 
	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public int getInitiator() { 
		return initiator; 
	} 

	public void setIntitiator(int intitiator) { 
		this.initiator = intitiator; 
	} 

	public int getCloser() { 
		return closer; 
	} 

	public void setCloser(int closer) { 
		this.closer = closer; 
	} 

	public Range getDate_range() { 
		return date_range; 
	} 

	public void setDate_range(Range date_range) { 
		this.date_range = date_range; 
	} 

	public double getResult() { 
		return result; 
	} 

	public void setResult(double result) { 
		this.result = result; 
	} 

	public TPortfolioStat() { 
		 
	} 

	 
	public TPortfolioStat(int intiator, int closer, Range range,double result) { 
		 
		this.setIntitiator(intiator); 
		 
		this.setCloser(closer); 
		 
		this.setDate_range(range); 
		 
		this.setResult(result); 
		 
	} 
	 
	 
	public TPortfolioStat(String ticker,int initiator, int closer, Range range,double result) { 
		 
		this(initiator,closer,range,result); 
		 
		this.setTicker(ticker); 
	} 
	 
	public TPortfolioStat(String ticker,int initiator, int closer,double stop_loss,double take_profit, Range range,double result) { 
		 
		this(initiator,closer,range,result); 
		 
		this.setStop_loss(stop_loss); 
		 
		this.setTake_profit(take_profit); 
		 
		this.setTicker(ticker); 
	} 
	 
	public static TPortfolioStat getInstance(String ticker,int initiator, int closer,Range range,double result) 
	{ 
		return new TPortfolioStat(ticker,initiator,closer,range,result); 
	} 
	 
	public static TPortfolioStat getInstance(String ticker,int initiator, int closer,double stop,double take, Range range,double result) 
	{ 
		return new TPortfolioStat(ticker,initiator,closer,stop,take,range,result); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%s %2d %2d %s %s %s %s ", 
				this.getTicker(), 
				this.getInitiator(), 
				this.getCloser(), 
				gl.fmt(this.getStop_loss()), 
				gl.fmt(this.getTake_profit()), 
				this.getDate_range().toString(), 
				gl.fmt(this.getResult()) 
				); 
	} 
	 
	public String toString(int format) 
	{ 
	 
		String data = ""; 
		 
		if(format == YEAR) 
		data = String.format("%2d %2d %4d %s ", 
				this.getInitiator(), 
				this.getCloser(), 
				DateUtil.year(this.getDate_range().getDate_to()), 
				gl.fmt(this.getResult()) 
				); 
		 
		return data; 
		 
	} 
	 
	 
	public static String toString(List<TPortfolioStat> list) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(a-> 
		{ 
			sb.append(a.toString(YEAR)); 
			 
			sb.append(System.lineSeparator()); 
			 
		}); 
		 
			return sb.toString(); 
	} 
	 
	public static String getPortfolioMembers() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		TConfiguration.PORTFOLIO_MEMBERS.forEach(a->{ 
			 
			sb.append(a); 
			 
			sb.append(" "); 
			 
		}); 
		 
			return sb.toString(); 
		 
	} 
	 
	public static double getPortfolioResult() 
	{ 
		return TConfiguration.ratings.stream().mapToDouble(p->p.getResult()).sum(); 
		 
	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
