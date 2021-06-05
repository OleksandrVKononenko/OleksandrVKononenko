package ap.explorer;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import ap.btn.Order;
import ap.global.gl;
import ap.utils.Biu;
import ap.utils.DateUtil;
import ap.utils.Su;
import ap.utils.Bau;

public class Group {
	
	public static  int  SHORT_MASK = 
			(1<< Group.TICKER) 		| 
			(1<< Group.INITITATOR )	| 
			(1<< Group.CLOSERATOR )	| 
			(1<<Group.DATE) 		|
			(1<<Group.PROFIT) 		|
			(1<<Group.PROFIT_DEALS) |
			(1<<Group.LOSE) 		|
			(1<<Group.LOSE_DEALS) 	|
			(1<<Group.RESULT) 		|
			(1<<Group.RESULT_DEALS) |
			(1<<Group.PROFIT_FACTOR |
			(1<<Group.EFFICIENCY)   |
			(1<<Group.LOST_PROFIT));   
				
	public static  int  
			FULL_MASK = SHORT_MASK 		|
			(1<<Group.PROFIT_PEAK) 		|
			(1<<Group.PROFIT_AVG) 		|
			(1<<Group.PROFIT_AVG_DAYS) 	|
			(1<<Group.PROFIT_DOWN_MAX) 	|
			(1<<Group.PROFIT_DOWN_AVG) 	|
			(1<<Group.LOSE_PEAK) 		|
			(1<<Group.LOSE_UP) 			|
			(1<<Group.LOSE_UP_AVG) 		|
			(1<<Group.LOSE_DOWN) 		|
			(1<<Group.LOSE_DOWN_AVG) 	|
			(1<<Group.LOSE_AVG) 		|
			(1<<Group.LOSE_AVG_DAYS);
			

	public static final int TICKER 			= 0;

	public static final int INITITATOR 		= 1;

	public static final int CLOSERATOR 		= 2;

	public static final int DATE 			= 3;

	public static final int PROFIT 			= 4;

	public static final int PROFIT_DEALS 	= 5;

	public static final int PROFIT_AVG 		= 6;

	public static final int PROFIT_DOWN_AVG = 7;

	public static final int PROFIT_DOWN_MAX = 8;

	public static final int PROFIT_PEAK 	= 9;

	public static final int PROFIT_AVG_DAYS = 10;

	public static final int LOSE 			= 11;

	public static final int LOSE_DEALS 		= 12;

	public static final int LOSE_PEAK 		= 13;

	public static final int LOSE_UP 		= 14;

	public static final int LOSE_UP_AVG 	= 15;

	public static final int LOSE_DOWN 		= 16;

	public static final int LOSE_DOWN_AVG 	= 17;

	public static final int RESULT 			= 18;

	public static final int RESULT_DEALS 	= 19;
	
	public static final int PROFIT_FACTOR 	= 20;
	
	public static final int LOSE_AVG 		= 21;
	
	public static final int LOSE_AVG_DAYS 	= 22;
	
	public static final int EFFICIENCY 		= 23;
	
	public static final int LOST_PROFIT 	= 24;
	
	
	private Quote 	quote;
	
	private int 	initiator;
	
	private int 	closerator;
	
	private int 	year;
	
	private double 	profit;
	
	private int 	profit_deals;
	
	private double 	profit_avg;
	
	private double 	profit_down_avg;
	
	private double 	profit_down_max;
	
	private double 	profit_peak;
	
	private int    	profit_duration_avg;
	
	private double 	profit_factor;
	
	private double 	lose;
	
	private int  	lose_deals;
	
	private double 	lose_peak;
	
	
	private double 	loss_up;  	 

	private double 	loss_up_avg;   

	private double 	loss_down  ;	 

	private double 	loss_down_avg; 

	
	private double 	result;
	
	private int 	result_deals;
	
	
	private double 	loss_avg;

	private int    	loss_duration_avg;
	
	
	private double efficiency;
	
	
	
	
	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public int getLoss_duration_avg() {
		return loss_duration_avg;
	}

	public void setLoss_duration_avg(int loss_duration_avg) {
		this.loss_duration_avg = loss_duration_avg;
	}

	public double getLoss_avg() {
		return loss_avg;
	}

	public void setLoss_avg(double loss_avg) {
		this.loss_avg = loss_avg;
	}

	public double getProfit_factor() {
		return profit_factor;
	}

	public void setProfit_factor(double profit_factor) {
		this.profit_factor = profit_factor;
	}

	public double getLose_peak() {
		return lose_peak;
	}

	public void setLose_peak(double lose_peak) {
		this.lose_peak = lose_peak;
	}

	public double getLoss_up() {
		return loss_up;
	}

	public void setLoss_up(double loss_up) {
		this.loss_up = loss_up;
	}

	public double getLoss_up_avg() {
		return loss_up_avg;
	}

	public void setLoss_up_avg(double loss_up_avg) {
		this.loss_up_avg = loss_up_avg;
	}

	public double getLoss_down() {
		return loss_down;
	}

	public void setLoss_down(double loss_down) {
		this.loss_down = loss_down;
	}

	public double getLoss_down_avg() {
		return loss_down_avg;
	}

	public void setLoss_down_avg(double loss_down_avg) {
		this.loss_down_avg = loss_down_avg;
	}

	public double getProfit_peak() {
		return profit_peak;
	}

	public void setProfit_peak(double profit_peak) {
		this.profit_peak = profit_peak;
	}

	public int getProfit_duration_avg() {
		return profit_duration_avg;
	}

	public void setProfit_duration_avg(int profit_duration_avg) {
		this.profit_duration_avg = profit_duration_avg;
	}

	public double getProfit_down_avg() {
		return profit_down_avg;
	}

	public void setProfit_down_avg(double profit_down_avg) {
		this.profit_down_avg = profit_down_avg;
	}

	public double getProfit_down_max() {
		return profit_down_max;
	}

	public void setProfit_down_max(double profit_down_max) {
		this.profit_down_max = profit_down_max;
	}

	public double getProfit_avg() {
		return profit_avg;
	}

	public void setProfit_avg(double profit_avg) {
		this.profit_avg = profit_avg;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public double getLose() {
		return lose;
	}

	public void setLose(double lose) {
		this.lose = lose;
	}

	public int getLose_deals() {
		return lose_deals;
	}

	public void setLose_deals(int lose_deals) {
		this.lose_deals = lose_deals;
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

	public Group(Quote quote,int initiator,int closerator,int year,
			double profit,
			int profit_deals,
			
			double profit_avg,
			double profit_down_avg,
			double profit_down_max,
			
			double profit_peak,
			int    profit_avg_dur,
			
			double profit_factor,
			  
			double lose,
			int    lose_deals,
			double lose_avg,
			int    lose_avg_dur,
			
			double lose_peak,
			
			double loss_up,
			double loss_up_avg,
			
			double loss_down,
			double loss_down_avg,
			
			double result,
			int result_deals,
			
			double efficiency
			
			)
	{
		this.setQuote(quote);
		
		this.setInitiator(initiator);
		
		this.setCloserator(closerator);
		
		this.setYear(year);
		
		this.setProfit(profit);
		
		this.setProfit_deals(profit_deals);
		
		this.setLose(lose);
		
		this.setLose_deals(lose_deals);
		
		this.setResult(result);
		
		this.setResult_deals(result_deals);
		
		
		this.setProfit_avg(profit_avg);
		
		this.setProfit_down_avg(profit_down_avg);
		
		this.setProfit_down_max(profit_down_max);
		
		this.setProfit_peak(profit_peak);
		
		this.setProfit_duration_avg(profit_avg_dur);
		
		this.setProfit_factor(profit_factor);
		
		
		this.setLoss_up(loss_up);
		
		this.setLoss_up_avg(loss_up_avg);
		
		this.setLoss_down(loss_down);
		
		this.setLoss_down_avg(loss_down_avg);
		
		this.setLose_peak(lose_peak);
		
		this.setLoss_avg(lose_avg);

		this.setLoss_duration_avg(lose_avg_dur);
		
		this.setEfficiency(efficiency);
		
	}
	
  public static Group get_instance(
		  Quote quote,
		  int initiator,
		  int closerator,
		  int year,
		  double profit,
		  int profit_deals,
		  double profit_avg, 
		  double profit_down_avg,
		  double profit_down_max,
		  
		  double profit_peak,
		  int  	 profit_avg_dur,
		  double profit_factor,
		  
		  double lose,
		  int    lose_deals,
		  double lose_avg,
		  int  	 lose_avg_dur,
		  
		  double lose_peak,
		  
		  double loss_up,
		  double loss_up_avg,
		  double loss_down,
		  double loss_down_avg,
			
		  double result,
		  int result_deals,
		  
		  double efficiency
		  
		  )
  {
	  return new Group(
			  quote,  
			  initiator,  
			  closerator,  
			  year,  
			  profit,  
			  profit_deals,  
			  profit_avg,
			  profit_down_avg,
			  profit_down_max,
			  
			  profit_peak,
			  profit_avg_dur,
			  
			  profit_factor,
			 
			  lose,   
			  lose_deals,
			  lose_avg,
			  lose_avg_dur,
			  lose_peak,
			  
			  loss_up,
			  loss_up_avg,
			  loss_down,
			  loss_down_avg,
			
			  result,  
			  result_deals,
			  
			  efficiency
			  
			  );
  }
	
  
  public static String get_groups_as_text(List<Group> groups,int mask)
  {
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(System.lineSeparator());
	  
	  groups.forEach(g->
	  {
		
		  sb.append(g.toString(mask));
		  
		  sb.append(System.lineSeparator());
		  
	  });
			  
	  	  return sb.toString();
  }
  
  
  public String get_mask_for_to_string(int value)
  {
	  return gl.replicate("%s " , Bau.countOfEnabledBits(value,RESULT_DEALS));
  }
  
  
  public String toString(int mask) {
	 
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(Biu.ISA(mask, TICKER) 			? gl.sf("%s",	this.getQuote().getPaper()):"");
	  sb.append(Biu.ISA(mask, INITITATOR) 		? gl.sf("%3d", this.getInitiator()) : "");
	  sb.append(Biu.ISA(mask, CLOSERATOR) 		? gl.sf("%3d", this.getCloserator()) : "");
	  sb.append(Biu.ISA(mask, DATE) 			? gl.sf("%5d", this.getYear()) : "");
	  
	  sb.append(Biu.ISA(mask, PROFIT) 			? Su.fmt10d2(this.getProfit()) : "");
	  sb.append(Biu.ISA(mask, PROFIT_DEALS) 	? gl.sf("%3d", this.getProfit_deals()) : "");
	  
	  //Peak.
	  sb.append(Biu.ISA(mask, PROFIT_PEAK) 		? Su.fmt10d2(this.getProfit_peak()) : "");
	  sb.append(Biu.ISA(mask, PROFIT_AVG) 		? Su.fmt10d2(this.getProfit_avg()) : "");
	  
	  //Drop.
	  sb.append(Biu.ISA(mask, PROFIT_DOWN_MAX) 	? Su.fmt10d2(this.getProfit_down_max()) : "");
	  sb.append(Biu.ISA(mask, PROFIT_DOWN_AVG) 	? Su.fmt10d2(this.getProfit_down_avg()) : "");
	  sb.append(Biu.ISA(mask, PROFIT_AVG_DAYS) 	? Su.fmt10d2(this.getProfit_duration_avg()) : "");
	  
	  sb.append(Biu.ISA(mask, LOSE) 			? Su.fmt10d2(this.getLose()) : "");
	  sb.append(Biu.ISA(mask, LOSE_DEALS) 		? gl.sf("%3d", this.getLose_deals()) : "");
	  sb.append(Biu.ISA(mask, LOSE_AVG) 		? Su.fmt10d2(this.getLoss_avg()) : "");
	  sb.append(Biu.ISA(mask, LOSE_AVG_DAYS) 	? gl.sf("%3d", this.getLoss_duration_avg()) : "");
	  
	  // Down.
	  sb.append(Biu.ISA(mask, LOSE_PEAK) 		? Su.fmt10d2(this.getLose_peak()) : "");
	  sb.append(Biu.ISA(mask, LOSE_UP) 			? Su.fmt10d2(this.getLoss_up()) : "");
	  sb.append(Biu.ISA(mask, LOSE_UP_AVG) 		? Su.fmt10d2(this.getLoss_up_avg()) : "");
	  
	  // Drop.
	  sb.append(Biu.ISA(mask, LOSE_DOWN)	 	? Su.fmt10d2(this.getLoss_down()) : "");
	  sb.append(Biu.ISA(mask, LOSE_DOWN_AVG) 	? Su.fmt10d2(this.getLoss_down_avg()): "");								
				
	  sb.append(Biu.ISA(mask, RESULT) 			? Su.fmt10d2(this.getResult()) : "");
	  sb.append(Biu.ISA(mask, RESULT_DEALS) 	? gl.sf("%4d", this.getResult_deals()) : "" );
	  
	  sb.append(Biu.ISA(mask, PROFIT_FACTOR) 	? Su.fmt8d2(this.getProfit_factor()) : "" );	  
	  sb.append(Biu.ISA(mask, EFFICIENCY) 		? Su.fmt8d2(this.getEfficiency()) : "" );
		 
	  return sb.toString();

	}
  
  @Override
  public String toString()
  {
	  return gl.sf("%s %2d %3d %4d %s %3d avg : %s avg drop : %s drop max : %s peak :%s avg dur: %3d | %s %3d peak %s avg %s down %s avg %s | %s %3d",
			  
			  this.getQuote().getPaper(),
			  this.getInitiator(),
			  this.getCloserator(),
			  this.getYear(),
			  gl.fmt8(this.getProfit()),
			  this.getProfit_deals(),

			  gl.fmt8(this.getProfit_avg()),
			  gl.fmt8(this.getProfit_down_avg()),
			  gl.fmt8(this.getProfit_down_max()),
			  
			  gl.fmt8(this.getProfit_peak()),
			  this.getProfit_duration_avg(),
			  
			  
			  gl.fmt8(this.getLose()),
			  this.getLose_deals(),
			  
			  gl.fmt8(this.getLoss_up()),
			  gl.fmt8(this.getLoss_up_avg()),
			  gl.fmt8(this.getLoss_down()),
			  gl.fmt8(this.getLoss_down_avg()),
			  
			  
			  gl.fmt8(this.getResult()),
			  this.getResult_deals()
			  );
  }
  
  public static boolean get_groups(List<Order> lo , List<Group> groups) 
	{ 
		 
		String msg = "Make groups"; 
		 
		long start = System.nanoTime(); 
		 
		try {
			 
		Quote quote = lo.get(gl.E_EMPTY).getQuote();
				
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
		 	
		 // Default multiplicator.
			
		 			int [] MULTIPLICATOR = {10000};
		 			
		 			if(
		 					quote.getPaper().equalsIgnoreCase("usdjpy") ||
		 					quote.getPaper().equalsIgnoreCase("eurjpy") 
		 					
		 			  )
		 				{
		 					MULTIPLICATOR[0] = 100;
		 				}
		 			
		 			if(quote.getPaper().equalsIgnoreCase("btcusd"))
		 				{
		 					MULTIPLICATOR[0] = 1;
		 				}
		 			
		 
		 	inis.stream().sorted().forEach(i -> { 
			 
			years.stream().sorted().forEach(d -> { 
			 
				
			double 	profit   		= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			
			double 	profit_avg  	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
				
			double 	profit_peak  	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).max().getAsDouble(); 
			
			int 	profit_dur_avg  = (int) lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getDuration()).average().getAsDouble(); 
			
			double 	profit_down_max = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
			
			double 	profit_down_avg = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).average().getAsDouble(); 
			
			double 	loss    		= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d ).mapToDouble(p->p.getProfit()).sum(); 
			
			double 	loss_avg = 0; 
			
			OptionalDouble  op_loss_avg    	=  lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d ).mapToDouble(p->p.getProfit()).average(); 
			
			if(op_loss_avg.isPresent())
					loss_avg = op_loss_avg.getAsDouble();
			
			double 	result    	= lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).sum(); 
			
			double 	loss_peak  	= 0; 
			
					
			OptionalDouble 	op_loss_peak = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit()).min();
			
			if(op_loss_peak.isPresent())
					loss_peak = op_loss_peak.getAsDouble();
			
			double 	loss_up  	= 0; 
			
			OptionalDouble 	op_loss_up = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_up()).max();
			
			if(op_loss_up.isPresent())
					loss_up = op_loss_up.getAsDouble();
			
			double 	loss_up_avg  = 0; 

			OptionalDouble op_loss_up_avg = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_up()).average();
			
			if(op_loss_up_avg.isPresent())
					loss_up_avg = op_loss_up_avg.getAsDouble();
			
			double 	loss_down  	= 0; 

			OptionalDouble op_loss_down = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).max();
			
			if(op_loss_down.isPresent())
					loss_down = op_loss_down.getAsDouble();
				
			double 	loss_down_avg  = 0; 
			
			OptionalDouble op_loss_down_avg   = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_down()).average();
			
			if(op_loss_down_avg.isPresent())
					loss_down_avg = op_loss_down_avg.getAsDouble();
			 
			long 	profit_cnt   	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long 	loss_cnt    	= lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).count(); 
			 
			long 	result_cnt    	= lo.stream().filter(a-> a.getInitiator()==i && a.getCloserator()==i && DateUtil.year(a.getOpen())== d).count(); 
		 
			double 	profit_factor   	= 0.0d;//(profit*10000) / ((loss==0.0) ? (profit*10000) : (loss*(-10000)));
			
			if(loss==gl.E_EMPTY)
			{	
				profit_factor = 1.27;
			}
			else 
				profit_factor = (profit*MULTIPLICATOR[0])/(loss*(-MULTIPLICATOR[0]));	
			
			
			OptionalDouble op_loss_dur_avg  = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()<0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getDuration()).average();
			
			int loss_dur_avg  	= 0; 
			
			if(op_loss_dur_avg.isPresent())
				loss_dur_avg  	= (int)op_loss_dur_avg.getAsDouble();
			
			group[0] = 7 - (i/4); 
			
			// Efficiency.
			
			double 	m_peak_total = lo.stream().filter(a->a.getInitiator()==i && a.getCloserator()==i && a.getProfit()>=0 && DateUtil.year(a.getOpen()) == d).mapToDouble(p->p.getProfit_up()).sum(); 
			
			double 	m_efficiency  = (profit / m_peak_total)*100;
			
			
			Group gr = Group.get_instance(
					quote, 
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
					profit_peak,
					profit_dur_avg,
					
					profit_factor,
		
					loss,
					(int)loss_cnt,

					loss_avg,
					loss_dur_avg,
					loss_peak,
					loss_up,
					loss_up_avg,
					loss_down,
					loss_down_avg,
					
					result,
					(int)result_cnt,
					
					m_efficiency
							
					);
		
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

  

}
