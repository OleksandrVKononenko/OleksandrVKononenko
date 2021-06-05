 
package ap.btn; 

import java.util.Date; 
import java.util.List;

import ap.explorer.Range;
import ap.global.gl; 
import ap.utils.Fu; 

public class TOrderStat { 

	 
	private int cntUp; 
	 
	private int cntDown; 
	 
	private int cnt; 
	 
	private double Ev; 
	 
	private double Profit; 
	 
	private double Lose; 
			 
	private double Result; 
	 
	private String ticker; 
	 
	private int rating; 
	 
	private double lost_profit; 
	 
	private double avg_utilization_rate; 
	 
	 
	 
	 
	public double getLost_profit() { 
		return lost_profit; 
	} 

	public void setLost_profit(double lost_profit) { 
		this.lost_profit = lost_profit; 
	} 

	public double getAvg_utilization_rate() { 
		return avg_utilization_rate; 
	} 

	public void setAvg_utilization_rate(double avg_utilization_rate) { 
		this.avg_utilization_rate = avg_utilization_rate; 
	} 

	public int getRating() { 
		return rating; 
	} 

	public void setRating(int rating) { 
		this.rating = rating; 
	} 

	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public int getCnt() { 
		return cnt; 
	} 

	public void setCnt(int cnt) { 
		this.cnt = cnt; 
	} 

	public int getCntUp() { 
		return cntUp; 
	} 

	public void setCntUp(int cntUp) { 
		this.cntUp = cntUp; 
	} 

	public int getCntDown() { 
		return cntDown; 
	} 

	public void setCntDown(int cntDown) { 
		this.cntDown = cntDown; 
	} 

	public double getEv() { 
		return Ev; 
	} 

	public void setEv(double ev) { 
		Ev = ev; 
	} 

	 
	public double getProfit() { 
		return Profit; 
	} 

	public void setProfit(double profit) { 
		Profit = profit; 
	} 

	public double getLose() { 
		return Lose; 
	} 

	public void setLose(double lose) { 
		Lose = lose; 
	 
	} 

	public double getResult() { 
		return this.Result; 
	} 

	public void setResult(double result) { 
		Result = result; 
	} 

	@Override 
	public String toString() 
	{ 
		 
		return String.format("%s %s,%s,%s,%s,%3d,%3d,%4d,%s,%s,%s", 
							  (this.getRating()==gl.E_EMPTY)?"": String.format("%3d",this.getRating()), 
							  gl.format(this.getTicker().trim(),gl.AL.RIGHT,6,'.'), 
							  gl.fmt8(this.getProfit()), 
							  gl.fmt8(this.getLose()), 
							  gl.fmt8(this.getResult()), 
							  this.getCntUp(), 
							  this.getCntDown(), 
							  (this.getCntUp() + this.getCntDown()), 
							  gl.fmt2(this.getEv()), 
							  gl.fmt8(this.getLost_profit()), 
							  gl.fmt8(this.getAvg_utilization_rate()) 
							 
							  ); 
	} 
	 
	public TOrderStat() { 
		 
	} 
	 
	public TOrderStat(List<TOrder> list) { 
		 
		this.setProfit(list.stream().filter(a->a.getProfit()>0).mapToDouble(p->p.getProfit()).sum()); 

		this.setLose(list.stream().filter(a->a.getProfit()<0).mapToDouble(p->p.getProfit()).sum()); 
		 
		this.setResult(this.getProfit()+this.getLose()); 
		 
		this.setCntUp((int)list.stream().filter(a->a.getProfit()>0).count()); 

		this.setCntDown((int)list.stream().filter(a->a.getProfit()<0).count()); 
		 
		this.setCnt(this.getCntUp()+this.getCntDown()); 
		 
		this.setTicker(list.get(gl.E_EMPTY).getTicker()); 
 
		double last_rate = list.get(list.size()-1).getRate_close(); 
		 
		if(this.getResult() > gl.E_EMPTY) 
		this.setEv(Math.abs(this.getResult()/last_rate)*100); 
		else 
		this.setEv(gl.E_EMPTY); 
			 
		this.setLost_profit(list.stream().mapToDouble(a->(a.getLost_profit())).sum()); 
		 
		this.setAvg_utilization_rate(list.stream().mapToDouble(a->(a.getUtilization_rate())).average().getAsDouble()); 
				 
	} 
	 
	public static TOrderStat getInstance(List<TOrder> list) 
	{ 
		return new TOrderStat(list); 
	} 
		 
	 
	 
	 
	public static String getTotalRow(List<TOrder> list,Range range,boolean to_file) 
	{ 
		 
		Date dt_begin = range.getDate_from(); 
		 
		Date dt_end =  range.getDate_to(); 
		 
		int[] ma = {0,0}; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(System.lineSeparator()); 
		 
		if(list.size()== gl.E_EMPTY) 
		{ 
			gl.tx_e(new Object(){},"Orders collection is empty...%s\n");			 
			 
			return null; 
		} 
			 
		String ticker = list.get(gl.E_EMPTY).getTicker(); 
		 
		list.forEach(c-> 
		{ 
			 
			if( c.getOpen().before(dt_end) && c.getOpen().after(dt_begin)) 
			{ 
				 
				sb.append(c.toString()); 
				 
				sb.append(System.lineSeparator()); 
				 
				ma[0]++; 
				 
			} 
		} 
		); 
		 
		 
		 
		TOrderStat tos = TOrderStat.getInstance(list); 
		 
		TConfiguration.ratings.add(tos); 
		 
		String msg = tos.toString(); 
		 
		sb.append(System.lineSeparator()); 
		 
		sb.append(msg); 
		 
		sb.append(System.lineSeparator()); 
		 
		gl.tracex(new Object(){},msg); 
		 
		String result = sb.toString(); 
		 
		 
		if(to_file) 
		{ 
		 	String fileExport = Fu.getInjectedFileName(TConfiguration.ORDERS_REPORT_FILE, ticker,range); 
			 
			if(!Fu.saveStringToFile(fileExport,result,TConfiguration.REPORT_FILE_APPEND_MODE,true)) 
			{ 
				gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport)); 
			} 
		} 
		 
			return result; 
		 
	} 

	 
	public static String toString(List<TOrderStat> list) 
	{ 
		 
		//String msg = gl.tx_k(new Object(){},"Create report from list"); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		//sb.append(msg); 
		 
		sb.append(System.lineSeparator()); 
		 
		list.forEach(a->{ 
			sb.append(a.toString()); 
			 
			sb.append(System.lineSeparator()); 
			 
		}); 
		 
			return sb.toString(); 
	} 
	 
	 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
