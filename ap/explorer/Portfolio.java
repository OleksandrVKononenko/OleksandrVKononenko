package ap.explorer;

import java.util.ArrayList;
import java.util.List;

import ap.btn.Order;
import ap.global.gl;
import ap.shape.Ru;
import ap.utils.Fu;

public class Portfolio {
	
	
	public static final int MAC  = gl.E_EMPTY;
	
	public static final int WIN  = gl.E_OK;
	
	private String 					portfolio;
	
	private List<Ticker> 			tickers;
	
	private List<Group> 			groups;
	
	private List<GroupTotal> 		total;
	
	private GroupTotal 				portfolio_total;
	
	private List<Order> 			open_orders;
	
	public List<Order> getOpen_orders() {
		return open_orders;
	}

	public void setOpen_orders(List<Order> open_orders) {
		this.open_orders = open_orders;
	}

	public GroupTotal getPortfolio_total() {
		return portfolio_total;
	}

	public void setPortfolio_total(GroupTotal portfolio_total) {
		this.portfolio_total = portfolio_total;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public List<GroupTotal> getTotal() {
		return total;
	}

	public void setTotal(List<GroupTotal> total) {
		this.total = total;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Ticker> getMembers() {
		return tickers;
	}

	public void setMembers(List<Ticker> members) {
		this.tickers = members;
	}

	public Portfolio(String portfolio)
	{
		this.setMembers(new ArrayList<Ticker>());
		
		this.setGroups(new ArrayList<Group>());
		
		this.setTotal(new ArrayList<GroupTotal>());
		
		this.setOpen_orders(new ArrayList<Order>());
		
		this.setPortfolio(portfolio);
	}
	
	public Portfolio(String portfolio,List<Ticker> members)
	{
		this(portfolio);
		
		this.setMembers(members);
	}
	

	public Portfolio(String portfolio,Ticker ... members)
	{
		this(portfolio);
		
		this.add(members);
	}

	
	public static Portfolio get_instance(String portfolio,List<Ticker> members)
	{
		return new Portfolio(portfolio,members);
	}
	
	public static Portfolio get_instance(String portfolio,Ticker ... members)
	{
		return new Portfolio(portfolio,members);
	}

	public static Portfolio get_instance(String portfolio)
	{
		return new Portfolio(portfolio);
	}

	public void add(Ticker member)
	{
		this.getMembers().add(member);
	}
	
	public void add(Ticker ... members) 
	{ 
		for (Ticker member : members) 
		{ 
			this.add(member); 
		} 
	} 
	
	public void clear()
	{
		this.getMembers().clear();
		
		this.getGroups().clear();
	}
	
	public void perform()
	{
			this.getGroups().clear();
		
			this.getMembers().forEach(m->
			{
			
				m.perform();
			
				this.getGroups().addAll(m.getGroups());
			
				this.getOpen_orders().addAll(m.getOpen_orders());
	
			});
		
			Order.show_orders(this.getOpen_orders());
		
			this.get_group_total(this.getGroups());
		
			this.get_portfolio_total(this.getTotal());
		
	}
	
	
	
	public boolean get_portfolio_total(List<GroupTotal> gr) 
	{ 
		 
		String msg = "Make group total"; 
		
		if(gr == null || gr.size() == gl.E_EMPTY)
		{
			
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,
					"Empty input group.")); 
			
			return false;
		}
		 
		long start = System.nanoTime(); 
		 
		try {
			
				
				double profit = gr.stream() 
										   .mapToDouble(p->p.getProfit()).sum();
				
				double loss   = gr.stream() 
						   				   .mapToDouble(p->p.getLoss()).sum(); 
	
				double result = gr.stream() 
		   				   				   .mapToDouble(p->p.getResult()).sum(); 
				
				double efficiency   = gr.stream() 
		   				   .mapToDouble(p->p.getEfficiency()).average().getAsDouble(); 
				
				
				// Deals counters.
				
				long   profit_deals = gr.stream() 
						   .mapToLong(p->p.getProfit_deals()).sum();
				
				long   loss_deals = gr.stream() 
						   .mapToLong(p->p.getLoss_deals()).sum();
				
				long   result_deals = gr.stream() 
						   .mapToLong(p->p.getResult_deals()).sum();
				
				// Averages.
				
				double profit_avg = gr.stream() 
										   .mapToDouble(p->p.getProfit_avg()).average().getAsDouble();
				
				double loss_avg   = gr.stream() 
						   				   .mapToDouble(p->p.getLoss_avg()).average().getAsDouble(); 
	
				// Days in deal.
				
				long   profit_days_dur_avg = (long)gr.stream() 
						   .mapToLong(p->p.getProfit_dur_avg()).average().getAsDouble();
				
				long   loss_days_dur_avg = (long)gr.stream() 
						   .mapToLong(p->p.getLoss_dur_avg()).average().getAsDouble();
				
				long   result_factor = (long)gr.stream() 
						   .mapToDouble(p->p.getResult_factor()).average().getAsDouble();
				
				
				this.setPortfolio_total(
						GroupTotal.get_instance(
						this.getPortfolio(),
						
						profit,
						(int)profit_deals,
						profit_avg,
						(int)profit_days_dur_avg,
						
						loss,
						(int)loss_deals,
						loss_avg,
						(int)loss_days_dur_avg,
						
						result,
						(int)result_deals,
						
						(int)result_factor,
						efficiency
						
						));
					
			
			
		boolean bl_result = (this.getTotal().size() != gl.E_EMPTY); 
		 
		if(bl_result) 
			gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,this.getTotal().size(),(System.nanoTime() - start)/1000000)); 
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

	
	public boolean get_group_total(List<Group> gr) 
	{ 
		 
		String msg = "Make group total"; 
		
		if(gr == null || gr.size() == gl.E_EMPTY)
		{
			
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,
					"Empty input group.")); 
			
			return false;
		}
		 
		long start = System.nanoTime(); 
		 
		try {
			
		
		// Dictionaries.
		// Ticker.
			
		List<String> tickers = new ArrayList<String>();
		
		gr.forEach(g-> 
		{ 
			if(!tickers.contains(g.getQuote().getPaper()))
			   tickers.add(g.getQuote().getPaper());
			
		});
			
		
			tickers.stream().sorted().forEach(t->{
				
				// Values.
				
				double profit = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
										   .mapToDouble(p->p.getProfit()).sum();
				
				double loss   = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   				   .mapToDouble(p->p.getLose()).sum(); 
	
				double result = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
		   				   				   .mapToDouble(p->p.getResult()).sum(); 
				
				double efficiency = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
		   				   					.mapToDouble(p->p.getEfficiency()).average().getAsDouble(); 
				  
				// Deals counters.
				
				long   profit_deals = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToLong(p->p.getProfit_deals()).sum();
				
				long   loss_deals = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToLong(p->p.getLose_deals()).sum();
				
				long   result_deals = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToLong(p->p.getResult_deals()).sum();
				
				// Averages.
				
				double profit_avg = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
										   .mapToDouble(p->p.getProfit_avg()).average().getAsDouble();
				
				double loss_avg   = gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   				   .mapToDouble(p->p.getLoss_avg()).average().getAsDouble(); 
	
				// Days in deal.
				
				long   profit_days_dur_avg = (long)gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToLong(p->p.getProfit_duration_avg()).average().getAsDouble();
				
				long   loss_days_dur_avg = (long)gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToLong(p->p.getLoss_duration_avg()).average().getAsDouble();
				
				
				// Profit factor.
				long   result_factor = (long)gr.stream().filter(a->a.getQuote().getPaper().equalsIgnoreCase(t))
						   .mapToDouble(p->p.getProfit_factor()).average().getAsDouble();
				
						
				this.getTotal().add(
						GroupTotal.get_instance(
						t,
						
						profit,
						(int)profit_deals,
						profit_avg,
						(int)profit_days_dur_avg,
						
						loss,
						(int)loss_deals,
						loss_avg,
						(int)loss_days_dur_avg,
						
						result,
						(int)result_deals,
						
						(int)result_factor,
						
						efficiency
						
						));
					
			});
			
			
			
		boolean bl_result = (this.getTotal().size() != gl.E_EMPTY); 
		 
		if(bl_result) 
			gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,this.getTotal().size(),(System.nanoTime() - start)/1000000)); 
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
	
	public boolean write_open_orders() {
		
		String m_file_name = gl.sf("%s_OPEN_ORDER_BOOK.log",this.getPortfolio());
		
		if(this.getOpen_orders().size() != gl.E_EMPTY)
		   Order.to_file(this.getOpen_orders(),m_file_name);
		
		return Fu.isaFile(m_file_name);
		
	}
	
	public void show_total()
	{
			
		gl.sfn("\n%s",GroupTotal.get_group_total_as_text(this.getTotal()));
			
	}
	
	public void show_portfolio_total()
	{		
		
		gl.sfn("\n%s",this.getPortfolio_total().toString());
			
	}
}
