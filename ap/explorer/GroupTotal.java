package ap.explorer;

import java.util.ArrayList;
import java.util.List;

import ap.global.gl;


public class GroupTotal {

	private String 	ticker;
	
	
	private double 	profit;
	
	private int 	profit_deals;
	
	private double 	profit_avg;
	
	private int 	profit_dur_avg;
	

	private double 	loss;
	
	private int 	loss_deals;
	
	private double 	loss_avg;
	
	private int 	loss_dur_avg;
	
	
	private double 	result;
	
	private int 	result_deals;
	
	private int 	result_factor;
	
	
	private double 	efficiency;
	
	
	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public int getResult_factor() {
		return result_factor;
	}

	public void setResult_factor(int result_factor) {
		this.result_factor = result_factor;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public int getProfit_deals() {
		return profit_deals;
	}

	public void setProfit_deals(int profit_deals) {
		this.profit_deals = profit_deals;
	}

	public double getProfit_avg() {
		return profit_avg;
	}

	public void setProfit_avg(double profit_avg) {
		this.profit_avg = profit_avg;
	}

	public int getProfit_dur_avg() {
		return profit_dur_avg;
	}

	public void setProfit_dur_avg(int profit_dur_avg) {
		this.profit_dur_avg = profit_dur_avg;
	}

	public double getLoss() {
		return loss;
	}

	public void setLoss(double loss) {
		this.loss = loss;
	}

	public int getLoss_deals() {
		return loss_deals;
	}

	public void setLoss_deals(int loss_deals) {
		this.loss_deals = loss_deals;
	}

	public double getLoss_avg() {
		return loss_avg;
	}

	public void setLoss_avg(double loss_avg) {
		this.loss_avg = loss_avg;
	}

	public int getLoss_dur_avg() {
		return loss_dur_avg;
	}

	public void setLoss_dur_avg(int loss_dur_avg) {
		this.loss_dur_avg = loss_dur_avg;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public int getResult_deals() {
		return result_deals;
	}

	public void setResult_deals(int result_deals) {
		this.result_deals = result_deals;
	}
	
	public GroupTotal(
		String 	ticker,
	  double 	profit,
	  int 		profit_deals,
	  double 	profit_avg,
	  int 		profit_dur_avg,
	  double 	loss,
	  int 		loss_deals,
	  double 	loss_avg,
	  int 		loss_dur_avg,
	  double 	result,
	  int 		result_deals,
	  int 		result_factor,
	  double 	efficiency
	  )
	{
		this.setTicker(ticker);

		
		this.setProfit(profit);
		
		this.setProfit_deals(profit_deals);
		
		this.setProfit_avg(profit_avg);
		
		this.setProfit_dur_avg(profit_dur_avg);
		
		
		this.setLoss(loss);
		
		this.setLoss_deals(loss_deals);
		
		this.setLoss_avg(loss_avg);
		
		this.setLoss_dur_avg(loss_dur_avg);
		
		
		this.setResult(result);
		
		this.setResult_deals(result_deals);
		
		this.setResult_factor(result_factor);
		
		this.setEfficiency(efficiency);
		
	}

	public static GroupTotal get_instance (
			  String 	ticker,
			  double 	profit,
			  int 		profit_deals,
			  double 	profit_avg,
			  int 		profit_dur_avg,
			  double 	loss,
			  int 		loss_deals,
			  double 	loss_avg,
			  int 		loss_dur_avg,
			  double 	result,
			  int 		result_deals,
			  int		result_factor,
			  double 	efficiency
			)
			{

		return new GroupTotal(
				ticker, 
				profit, 
				profit_deals, 
				profit_avg, 
				profit_dur_avg, 
				loss, 
				loss_deals, 
				loss_avg,
				loss_dur_avg, 
				result, 
				result_deals,
				result_factor,
				efficiency
				);
			
			}
	
	@Override
	public String toString()
	{
		return gl.sf("%16s %s %3d %s %3d | %s %3d %s %3d | %s %3d | %5d | %s",
				
				this.getTicker(),
				
				gl.fmt8(this.getProfit()),
				this.getProfit_deals(),
				gl.fmt8(this.getProfit_avg()),
				this.getProfit_dur_avg(),
				

				gl.fmt8(this.getLoss()),
				this.getLoss_deals(),
				gl.fmt8(this.getLoss_avg()),
				this.getLoss_dur_avg(),

				gl.fmt8(this.getResult()),
				this.getResult_deals(),
				this.getResult_factor(),
				gl.fmt2(this.getEfficiency())
				
				);
	}
	
	 public static String get_group_total_as_text(List<GroupTotal> groups)
	  {
		  StringBuilder sb = new StringBuilder();
		  
		  sb.append(System.lineSeparator());
		  
		  groups.forEach(g->
		  {
			
			  sb.append(g.toString());
			  
			  sb.append(System.lineSeparator());
			  
		  });
				  
		  	  return sb.toString();
	  }
	 
	 
		
	
}
