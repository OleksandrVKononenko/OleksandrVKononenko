 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 

 
 
 

 
 
 

package ap.btn; 

import java.util.ArrayList; 
import java.util.List; 

import ap.global.gl; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public class TPortfolioStatRow { 

	private int rating; 
	 
	private int initiator; 
	 
	private int closer; 
	 
	private double stop_loss; 
	 
	private double take_profit; 
	 
	private int date; 
	 
	private double result; 
	 
	 
	 
	 
	 
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

	public int getRating() { 
		return rating; 
	} 

	public void setRating(int rating) { 
		this.rating = rating; 
	} 

	public int getInitiator() { 
		return initiator; 
	} 

	public void setInitiator(int initiator) { 
		this.initiator = initiator; 
	} 

	public int getCloser() { 
		return closer; 
	} 

	public void setCloser(int closer) { 
		this.closer = closer; 
	} 

	public int getDate() { 
		return date; 
	} 

	public void setDate(int date) { 
		this.date = date; 
	} 

	public double getResult() { 
		return result; 
	} 

	public void setResult(double result) { 
		this.result = result; 
	} 

	public TPortfolioStatRow() { 
		 
	} 
	 
	public TPortfolioStatRow(int initiator,int closer,int date,double result) { 
		 
		this.setInitiator(initiator); 
		 
		this.setCloser(closer); 
		 
		this.setDate(date); 
		 
		this.setResult(result); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%2d %2d %4d %s ", 
				this.getInitiator(), 
				this.getCloser(), 
				this.getDate(), 
				gl.fmt(this.getResult()) 
				); 
	} 

	public static String toStringMap( List<TPortfolioStat> list,TPl pl) 
	{ 
		// Portfolio members list 
		 
		String 	members = String.format("\nPortfolio members : %s",TPortfolioStat.getPortfolioMembers()); 
		 
		String 	take_profit = ""; 
		 
		if(!TConfiguration.TAKE_PROFIT_BOUND.isEmpty()) 
		take_profit = String.format("\n\nTake profit : %.4f...%.4f",TConfiguration.TAKE_PROFIT_BOUND.getLow(),TConfiguration.TAKE_PROFIT_BOUND.getHigh()); 
		else 
		take_profit = String.format("\n\nTake profit : %s",gl.fmt8(pl.getTake_profit())); 
				 
		String 	stop_level = ""; 

		if(!TConfiguration.STOP_LOSS_BOUND.isEmpty()) 
		stop_level = String.format("\n\nStop level  : %.4f...%.4f",TConfiguration.STOP_LOSS_BOUND.getLow(),TConfiguration.STOP_LOSS_BOUND.getHigh()); 
		else 
		stop_level = String.format("\n\nStop level  : %s",gl.fmt8(pl.getStop_loss())); 
					 
		 
		String 	target     = String.format("\n\nTarget    : %01d",pl.getTarget()); 
		 
		String 	direction  = String.format("\n\nDirection : %s",(pl.getDirection()==gl.E_EMPTY) ? "Normal" : "Reversal"); 
		 
		 
		String 	version = String.format("\n\nVersion : %s",TConfiguration.VERSION); 
		 
		 
		// Get unique's indexes 
		 
		List<Integer> it = new ArrayList<Integer>(); 
		 
		List<Integer> cl = new ArrayList<Integer>(); 
		 
		List<Double>  sl = new ArrayList<Double>(); 
		 
		List<Double>  tp = new ArrayList<Double>(); 
		 
		List<Integer> dt = new ArrayList<Integer>(); 
		 
		List<String>  tk = new ArrayList<String>(); 
		 
		List<Double>  year_sum = new ArrayList<Double>(); 
		 
			 
		list.forEach(a-> 
		{ 
				if(!it.contains(a.getInitiator())) 
					it.add(a.getInitiator()); 
				 
					if(!cl.contains(a.getCloser())) 
						cl.add(a.getCloser()); 
			 
					if(!sl.contains(a.getStop_loss())) 
						sl.add(a.getStop_loss()); 
					 
					if(!tp.contains(a.getTake_profit())) 
						tp.add(a.getTake_profit()); 
					 
					if(!tk.contains(a.getTicker())) 
						tk.add(a.getTicker()); 
					 
					 

				int year = DateUtil.year(a.getDate_range().getDate_to()); 
				 
				if(!dt.contains(year)) 
					dt.add(year); 
			 
		} 
		 
		); 
		 
		 
		 
		int split_line_width = ((dt.size()*12)+32); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		double row_sum[] = {0.0d}; 
		 
		tk.forEach(t->{ 
			 
		it.forEach(i->{ 
			 
			cl.forEach(c-> 
			{ 
				 
				sl.forEach(s-> 
				{ 
				 
				tp.forEach(ta-> 
				 
				{ 
				 
				String msg = String.format("%s %2d %2d %s %s ",gl.format(t,gl.AL.RIGHT,6),i,c,gl.fmt8(s),gl.fmt8(ta)); 
				 
				sb.append(msg); 
						 
				dt.forEach(d-> 
				{ 
					 
					 
					double sum_result = list.stream().filter(a->( 
							a.getInitiator() == i && 
							a.getCloser() == c && 
							a.getStop_loss() == s && 
							a.getTake_profit() == ta && 
							DateUtil.year(a.getDate_range().getDate_to()) == d  && 
							a.getTicker().equalsIgnoreCase(t) 
							)).mapToDouble(b->b.getResult()).sum(); 
					 
					 
					 
					row_sum[0] +=  sum_result; 
					 
					String result = String.format("%s",gl.fmt12(sum_result)); 
					 
					sb.append(result); 
					 
					 
					 
				  } 
				);	// dt 
				 
					String result = String.format("%s",gl.fmt12(row_sum[0])); 
				 
					sb.append(" | "); 
					 
					sb.append(result); 
				 
					sb.append(System.lineSeparator()); 
					 
					row_sum[0] = 0.0d; 
					 
					}); // take_profit 
					 
				}); // stop_loss 
				 
			}); // closer 
			 
		}); // initiator 
		}); // tk 
		 
		//Create footer with total sum by year; 
		 
		double year_result[] = {0.0d}; 
		 
		StringBuilder sum_footer = new StringBuilder(); 
		 
		sum_footer.append(gl.replicate(' ',31)); 
		 
		dt.forEach(d->{ 
			double year_total = list.stream().filter(a->( 
					DateUtil.year(a.getDate_range().getDate_to()) == d 
					)).mapToDouble(b->b.getResult()).sum(); 
			 
			year_sum.add(year_total); 
			 
			String result = String.format("%s",gl.fmt12(year_total)); 
			 
			sum_footer.append(result); 
			 
		}); 

			// sum_footer.append(System.lineSeparator()); 
		 
		 
		//Create title for report. 
		 
		String head = String.format("%s %s %s", 
				gl.format("member",gl.AL.RIGHT,6), 
				gl.format("i",gl.AL.RIGHT,2), 
				gl.format("c",gl.AL.RIGHT,2) 
				); 
		 
		 
		StringBuilder header = new StringBuilder(); 
		 
		header.append(members); 
		 
		header.append(take_profit); 
		 
		header.append(stop_level); 
		 
		header.append(target); 
		 
		header.append(direction); 
		 
		header.append(version); 
						 
		header.append(System.lineSeparator()); 
		 
		header.append(System.lineSeparator()); 
		 
		header.append(head); 
		 
		header.append(gl.replicate(' ',19)); 
		 
		dt.forEach(d-> 
		{ 
				header.append(gl.format(String.format("%4d",d),gl.AL.RIGHT,12)); 
		} 
		); 
		 
		 
				double year_total_sum = year_sum.stream().mapToDouble(y->(y.floatValue())).sum(); 
		 
				 
				header.append(System.lineSeparator()); 
				 
				header.append(gl.replicate('-',split_line_width)); 
				 
				header.append('+'); 
				 
				header.append(System.lineSeparator()); 
				 
				 
				StringBuilder r = new StringBuilder(); 
				 
				r.append(header.toString()); 
				 
				r.append(sb.toString()); 
				 
				r.append(gl.replicate('-',split_line_width)); 
				 
				r.append('+'); 
				 
				r.append(gl.replicate('-',13)); 
				 
				r.append(System.lineSeparator()); 

				r.append(sum_footer.toString()); 
				 
				// Add total sum. 
				 
				// TOD0 16.04.2018 totalSum 
				 
				r.append(" | "); 
				 
				String result = String.format("%s",gl.fmt12(year_total_sum)); 
				 
				r.append(result); 
				 
				r.append(System.lineSeparator()); 
				 
				r.append(gl.replicate('-',split_line_width)); 
				 
				r.append('+'); 
				 
				r.append(gl.replicate('-',13)); 
				 
				r.append(System.lineSeparator()); 
												 
				 
				 
				return 	r.toString(); 
		 
	} 
	 
	
	public static String toStringMap( List<TPortfolioStat> list) 
	{ 
		// Portfolio members list 
		 
		TPl pl = new TPl(TConfiguration.TAKE_PROFIT,TConfiguration.STOP_LOSS,(TConfiguration.KEY_TOOL&0x000F),((TConfiguration.KEY_TOOL&0x00F0)>>4)); 
		 
		String 	members = String.format("\nPortfolio members : %s",TPortfolioStat.getPortfolioMembers()); 
		 
		String 	take_profit = ""; 
		 
		if(!TConfiguration.TAKE_PROFIT_BOUND.isEmpty()) 
		take_profit = String.format("\n\nTake profit : %.4f...%.4f",TConfiguration.TAKE_PROFIT_BOUND.getLow(),TConfiguration.TAKE_PROFIT_BOUND.getHigh()); 
		else 
		take_profit = String.format("\n\nTake profit : %s",gl.fmt8(pl.getTake_profit())); 
				 
		String 	stop_level = ""; 

		if(!TConfiguration.STOP_LOSS_BOUND.isEmpty()) 
		stop_level = String.format("\n\nStop level  : %.4f...%.4f",TConfiguration.STOP_LOSS_BOUND.getLow(),TConfiguration.STOP_LOSS_BOUND.getHigh()); 
		else 
		stop_level = String.format("\n\nStop level  : %s",gl.fmt8(pl.getStop_loss())); 
					 
		String 	open_list = String.format("\n\nOpen   list    : %s",TConfiguration.raw_job.getOpen_list()); 

		String 	close_list = String.format("\n\nClose list    : %s",TConfiguration.raw_job.getClose_list()); 
		 
		String 	direction  = String.format("\n\nDirection  : %s",(pl.getDirection()==gl.E_EMPTY) ? "Normal" : "Reversal"); 
		 
		String 	skip_days   = String.format("\n\nSkip days : %s",TConfiguration.raw_job.getSkip_days_before_open_the_deal()); 
				 
		String 	version = String.format("\n\nVersion : %s",TConfiguration.VERSION); 
		 
		 
		// Get unique's indexes 
		 
		List<Integer> it = new ArrayList<Integer>(); 
		 
		List<Integer> cl = new ArrayList<Integer>(); 
		 
		List<Double>  sl = new ArrayList<Double>(); 
		 
		List<Double>  tp = new ArrayList<Double>(); 
		 
		List<Integer> dt = new ArrayList<Integer>(); 
		 
		List<String>  tk = new ArrayList<String>(); 
		 
		List<Double>  year_sum = new ArrayList<Double>(); 
		 
			 
		list.forEach(a-> 
		{ 
				if(!it.contains(a.getInitiator())) 
					it.add(a.getInitiator()); 
				 
					if(!cl.contains(a.getCloser())) 
						cl.add(a.getCloser()); 
			 
					if(!sl.contains(a.getStop_loss())) 
						sl.add(a.getStop_loss()); 
					 
					if(!tp.contains(a.getTake_profit())) 
						tp.add(a.getTake_profit()); 
					 
					if(!tk.contains(a.getTicker())) 
						tk.add(a.getTicker()); 
					 
					 

				int year = DateUtil.year(a.getDate_range().getDate_to()); 
				 
				if(!dt.contains(year)) 
					dt.add(year); 
			 
		} 
		 
		); 
		 
		 
		 
		int split_line_width = ((dt.size()*12)+32); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		double row_sum[] = {0.0d}; 
		 
		tk.forEach(t->{ 
			 
		it.forEach(i->{ 
			 
			cl.forEach(c-> 
			{ 
				 
				sl.forEach(s-> 
				{ 
				 
				tp.forEach(ta-> 
				 
				{ 
				 
				String msg = String.format("%s %2d %2d %s %s ",gl.format(t,gl.AL.RIGHT,6),i,c,gl.fmt8(s),gl.fmt8(ta)); 
				 
				sb.append(msg); 
						 
				dt.forEach(d-> 
				{ 
					 
					 
					double sum_result = list.stream().filter(a->( 
							a.getInitiator() == i && 
							a.getCloser() == c && 
							a.getStop_loss() == s && 
							a.getTake_profit() == ta && 
							DateUtil.year(a.getDate_range().getDate_to()) == d  && 
							a.getTicker().equalsIgnoreCase(t) 
							)).mapToDouble(b->b.getResult()).sum(); 
					 
					 
					 
					row_sum[0] +=  sum_result; 
					 
					String result = String.format("%s",gl.fmt12(sum_result)); 
					 
					sb.append(result); 
					 
					 
					 
				  } 
				);	// dt 
				 
					String result = String.format("%s",gl.fmt12(row_sum[0])); 
				 
					sb.append(" | "); 
					 
					sb.append(result); 
				 
					sb.append(System.lineSeparator()); 
					 
					row_sum[0] = 0.0d; 
					 
					}); // take_profit 
					 
				}); // stop_loss 
				 
			}); // closer 
			 
		}); // initiator 
		}); // tk 
		 
		//Create footer with total sum by year; 
		 
		double year_result[] = {0.0d}; 
		 
		StringBuilder sum_footer = new StringBuilder(); 
		 
		sum_footer.append(gl.replicate(' ',31)); 
		 
		dt.forEach(d->{ 
			double year_total = list.stream().filter(a->( 
					DateUtil.year(a.getDate_range().getDate_to()) == d 
					)).mapToDouble(b->b.getResult()).sum(); 
			 
			year_sum.add(year_total); 
			 
			String result = String.format("%s",gl.fmt12(year_total)); 
			 
			sum_footer.append(result); 
			 
		}); 

			// sum_footer.append(System.lineSeparator()); 
		 
		 
		//Create title for report. 
		 
		String head = String.format("%s %s %s", 
				gl.format("member",gl.AL.RIGHT,6), 
				gl.format("i",gl.AL.RIGHT,2), 
				gl.format("c",gl.AL.RIGHT,2) 
				); 
		 
		 
		StringBuilder header = new StringBuilder(); 
		 
		header.append(members); 
		 
		header.append(take_profit); 
		 
		header.append(stop_level); 
		 
		header.append(open_list); 
		 
		header.append(close_list); 
				 
		header.append(direction); 
		 
		header.append(skip_days); 
				 
		header.append(version); 
						 
		header.append(System.lineSeparator()); 
		 
		header.append(System.lineSeparator()); 
		 
		header.append(head); 
		 
		header.append(gl.replicate(' ',19)); 
		 
		dt.forEach(d-> 
		{ 
				header.append(gl.format(String.format("%4d",d),gl.AL.RIGHT,12)); 
		} 
		); 
		 
		 
				double year_total_sum = year_sum.stream().mapToDouble(y->(y.floatValue())).sum(); 
		 
				 
				header.append(System.lineSeparator()); 
				 
				header.append(gl.replicate('-',split_line_width)); 
				 
				header.append('+'); 
				 
				header.append(System.lineSeparator()); 
				 
				 
				StringBuilder r = new StringBuilder(); 
				 
				r.append(header.toString()); 
				 
				r.append(sb.toString()); 
				 
				r.append(gl.replicate('-',split_line_width)); 
				 
				r.append('+'); 
				 
				r.append(gl.replicate('-',13)); 
				 
				r.append(System.lineSeparator()); 

				r.append(sum_footer.toString()); 
				 
				// Add total sum. 
				 
				// TOD0 16.04.2018 totalSum 
				 
				r.append(" | "); 
				 
				String result = String.format("%s",gl.fmt12(year_total_sum)); 
				 
				r.append(result); 
				 
				r.append(System.lineSeparator()); 
				 
				r.append(gl.replicate('-',split_line_width)); 
				 
				r.append('+'); 
				 
				r.append(gl.replicate('-',13)); 
				 
				r.append(System.lineSeparator()); 
												 
				 
				 
				return 	r.toString(); 
		 
	} 
	 
	 
	public static boolean write(String data) 
	{ 
		 
		String fileExport = String.format("%s\\%s",TConfiguration.REPORT_DIR,TConfiguration.PORTFOLIO_NAME); 
		 
		if(!Fu.saveStringToFile(fileExport,data,TConfiguration.REPORT_FILE_APPEND_MODE,true)) 
		{ 
			gl.tx_e(new Object(){},String.format("Error while write to file...%s",fileExport)); 
			 
			return false; 
		} 
		 
			return true; 
		 
	} 
	 
	 
	 
	public static boolean writeRatings(List<TLightOrderStat> data) 
	{ 
		 
		List<TLightOrderStat> rating = TSorter.getSortedByRslt(data,false); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		int index[] = {0}; 
		 
		rating.forEach(sta->{ 
			 
			if(index[0] < 10) 
			{ 
				sb.append(sta); 
			 
				sb.append(System.lineSeparator()); 
			} 
			 
		}); 
				 
		String fileExport = String.format("%s\\%s_top20.rate",TConfiguration.REPORT_DIR,TConfiguration.PORTFOLIO_NAME); 
		 
		if(!Fu.saveStringToFile(fileExport,sb.toString(),TConfiguration.REPORT_FILE_APPEND_MODE,true)) 
		{ 
			gl.tx_e(new Object(){},String.format("Error while write to file...%s",fileExport)); 
			 
			return false; 
		} 
		 
			return true; 
		 
	} 
	 
	 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
