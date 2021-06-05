 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.util.Date; 
import java.util.List; 

import ap.btn.Bar; 
import ap.gen.OrderId; 
import ap.global.gl; 
import ap.utils.DateUtil; 

public class Order { 
	 
	 
	private int 		id; 
	 
	private String 		ticker; 
	 
	private int 		type 	  = IOrder.indexOf("NONE"); 
	 
	private int 		open_by   = gl.E_ERROR; 
	 
	private int 		close_by  = gl.E_ERROR; 
	 
	private Date 		open_date; 
	 
	private Date 		close_date; 
	 
	private double 		open_price; 
	 
	private double 		close_price; 
	 
	private double 		profit_commit; 
	 
	private double 		profit_up; 
	 
	private double 		profit_down; 
	 
	private long 		duration; 
	 
	private Bar	bar_up; 
	 
	private Bar	bar_down; 
	 
	 

	public Bar getBar_up() { 
		return bar_up; 
	} 

	public void setBar_up(Bar bar_up) { 
		this.bar_up = bar_up; 
	} 

	public Bar getBar_down() { 
		return bar_down; 
	} 

	public void setBar_down(Bar bar_down) { 
		this.bar_down = bar_down; 
	} 

	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	public int getOpen_by() { 
		return open_by; 
	} 

	public void setOpen_by(int open_by) { 
		this.open_by = open_by; 
	} 

	public int getClose_by() { 
		return close_by; 
	} 

	public void setClose_by(int close_by) { 
		this.close_by = close_by; 
	} 

	public Date getOpen_date() { 
		return open_date; 
	} 

	public void setOpen_date(Date open_date) { 
		this.open_date = open_date; 
	} 

	public Date getClose_date() { 
		return close_date; 
	} 

	public void setClose_date(Date close_date) { 
		this.close_date = close_date; 
	} 

	public double getOpen_price() { 
		return open_price; 
	} 

	public void setOpen_price(double open_price) { 
		this.open_price = open_price; 
	} 

	public double getClose_price() { 
		return close_price; 
	} 

	public void setClose_price(double close_price) { 
		this.close_price = close_price; 
	} 

	public double getProfit_commit() { 
		return profit_commit; 
	} 

	public void setProfit_commit(double profit_commit) { 
		this.profit_commit = profit_commit; 
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

	public long getDuration() { 
		return duration; 
	} 

	public void setDuration(long duration) { 
		this.duration = duration; 
	} 
	 
	public Order() 
	{ 
		this.setId(OrderId.nextId()); 
		 
		this.setClose_date(null); 
	} 
	 
	public Order(String ticker,int type,Date open_date,int open_by,double open_price) 
	{ 
		this(); 
		 
		this.setTicker(ticker); 
		 
		this.setType(type); 
		 
		this.setOpen_date(open_date); 
		 
		this.setOpen_by(open_by); 
				 
		this.setOpen_price(open_price); 
		 
	} 
	 
	public Order(String ticker,int type,Date open_date,int open_by,int close_by,double open_price) 
	{ 
		this(); 
		 
		this.setTicker(ticker); 
		 
		this.setType(type); 
		 
		this.setOpen_date(open_date); 
		 
		this.setOpen_by(open_by); 
		 
		this.setClose_by(close_by); 
		 
		this.setOpen_price(open_price); 
		 
	} 
	 
	public Order(String ticker,int type,Date open_date,Date close_date,int open_by,int close_by,double open_price,double close_price,Bar bar_up,Bar bar_down) 
	{ 
		this(); 
		 
		this.setTicker(ticker); 
		 
		this.setType(type); 
		 
		this.setOpen_date(open_date); 
		 
		this.setClose_date(close_date); 
		 
		this.setOpen_by(open_by); 
		 
		this.setClose_by(close_by); 
		 
		this.setOpen_price(open_price); 
		 
		this.setClose_price(close_price); 
		 
		this.setDuration(DateUtil.days_between(this.getOpen_date(),this.getClose_date())); 
		 
		this.setBar_up(bar_up); 
		 
		this.setBar_down(bar_down); 
		 
		 
		this.setProfit_commit(calculate_fin_result()); 
		 
		this.calculate_profit_range(); 
		 
	} 

	public void calculate_profit_range_() 
	{ 

		double up = 0.0d; 

		double down = 0.0d; 

		 
		if (this.getType() == IOrder.indexOf("B")) 
		{ 
			//up = (this.getBar_up().getH() - this.getOpen_price()) ; 
			up = (this.getBar_up().getO() - this.getOpen_price()) ; 
			 
			//down = 	(this.getOpen_price() - this.getBar_down().getL()); 
			down = 	(this.getOpen_price() - this.getBar_down().getO()); 
			 
		} else if (this.getType() == IOrder.indexOf("S")) 
		{ 

			//up = (this.getOpen_price() - this.getBar_down().getL()) ; 
			up = (this.getBar_down().getO() - this.getOpen_price()) ; 
			 
			//down = 	(this.getBar_up().getH() - this.getOpen_price()); 
			 
			down = 	(this.getBar_down().getO() - this.getOpen_price()); 
		} 
		 
			this.setProfit_up(up); 
		 
			this.setProfit_down(down); 
	} 

	public void calculate_profit_range() 
	{ 

		double up = 0.0d; 

		double down = 0.0d; 

		 
		if (this.getType() == IOrder.indexOf("B")) 
		{ 
			//up = (this.getBar_up().getH() - this.getOpen_price()) ; 
			up = (this.getBar_up().getO() - this.getOpen_price()) ; 
			 
			//down = 	(this.getOpen_price() - this.getBar_down().getL()); 
			down = 	(this.getOpen_price() - this.getBar_down().getO()); 
			 
		} else if (this.getType() == IOrder.indexOf("S")) 
		{ 

			up = (this.getOpen_price() - this.getBar_down().getO()) ; 
			//up = (this.getBar_down().getO() - this.getOpen_price()) ; 
			 
			down = 	(this.getBar_up().getO() - this.getOpen_price()); 
			 
			//down = 	(this.getBar_down().getO() - this.getOpen_price()); 
		} 
		 
			this.setProfit_up(up); 
		 
			this.setProfit_down(down); 

	} 

	 
	 
		 
	public double calculate_fin_result() 
	{ 
		 
		return  (this.getType() == IOrder.indexOf("B")) ? 
				(this.getClose_price() - this.getOpen_price()) : 
				(this.getOpen_price() - this.getClose_price()); 
		 
	} 
	 
	public static Order get_instance() 
	{ 
		return new Order(); 
	} 
	 
	public static Order get_instance(String ticker,int type,Date open_date,int open_by,double open_price) 
	{ 
		return new Order(ticker,type,open_date,open_by,open_price); 
	} 
	 
	public static Order get_instance(String ticker,int type,Date open_date,int open_by,int close_by,double open_price) 
	{ 
		return new Order(ticker,type,open_date,open_by,close_by,open_price); 
	} 
	 
	public static Order get_instance(String ticker,int type,Date open_date,Date close_date,int open_by,int close_by,double open_price,double close_price,Bar bar_up,Bar bar_down) 
	{ 
		return new Order(ticker,type,open_date,close_date,open_by,close_by,open_price,close_price,bar_up,bar_down); 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		return gl.sf("%5d %s %s %s %3d %04d %04d %s %.4f %.4f %7s %7s %7s %1s %s %s", 
				this.getId(), 
				this.getTicker(), 
				DateUtil.to_string(this.getOpen_date()), 
				DateUtil.to_string(this.getClose_date()), 
				this.getDuration(), 
				this.getOpen_by(), 
				this.getClose_by(), 
				IOrder.indexOf(this.getType()), 
				this.getOpen_price(), 
				this.getClose_price(), 
				 
				gl.sf("%.4f",this.getProfit_commit()), 
				 
				gl.sf("%.4f",this.getProfit_up()), 
				 
				gl.sf("%.4f",this.getProfit_down()), 
				 
				this.getBar_up().getDate().after(this.getBar_down().getDate()) ? "-" : "+", 
						 
				DateUtil.to_string(this.getBar_up().getDate()), 
				 
				DateUtil.to_string(this.getBar_down().getDate()) 
				 
				); 
		 
	} 

	 
	 
	 
} 
