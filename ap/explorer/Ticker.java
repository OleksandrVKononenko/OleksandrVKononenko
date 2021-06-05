package ap.explorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ap.btn.Bar;
import ap.btn.Order;
import ap.btn.TMa;
import ap.global.gl;
import ap.utils.Fu;
import ap.utils.IntArrayUtil;
import ap.utils.MaUtil;


public class Ticker {
		
	
	private String 			source;
	
	private  Map<String,List<Bar>> 	bars ; 
	
	private List<Group> 	groups;
	
	private List<Order> 	orders; 
	
	private GroupTotal 		total;
	
	private List<Order> 	open_orders;
	
	private Quote			quote;
	
	
	
	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public List<Order> getOpen_orders() {
		return open_orders;
	}

	public void setOpen_orders(List<Order> open_orders) {
		this.open_orders = open_orders;
	}

	public GroupTotal getTotal() {
		return total;
	}

	public void setTotal(GroupTotal total) {
		this.total = total;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		
		this.groups = groups;
	
	}

	public Map<String, List<Bar>> getBars() {
		return bars;
	}

	public void setBars(Map<String, List<Bar>> bars) {
		this.bars = bars;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public Ticker()
	{
		    this.setBars(new HashMap<String,List<Bar>>());

		    this.setGroups(new ArrayList<Group>());
			
		    this.setOrders(new ArrayList<Order>());
		    
		    this.setOpen_orders(new ArrayList<Order>());
	}		
	
	public Ticker(Quote quote)
	{
		this();
		
		this.setQuote(quote);
		
		this.setSource(gl.sf("%s%s.txt",this.getQuote().getPath(),this.getQuote().getPaper()));
		
	}
	
	
	

	public static Ticker get_instance (Quote quote)
	{
		return new Ticker(quote);
	}
	

	
	
	
	
	public boolean perform() 
	{ 
		
		// Check of input parameters.
		
		if(this.getQuote().getStrategy() == gl.E_EMPTY || this.getQuote().getRange() == null)
		{
			gl.tracex(new Object() {}, gl.sf("%s...%s",gl.S_OK,"No filter or input date range NO works,- we are rest. :-)"));
		
			return true;
		}
		
		
		try
		
		{
		
				List<Bar> 	state_bars = new ArrayList<Bar>(); 
		
				String [] 		series = {null,null};
		
				if (!Bar.get_state_bars(this.getSource(),bars, state_bars,series)) { 
					return false; 
				} 
		 
				if (!Order.get_orders(this.getQuote(),Bar.get_bars_by_date_range(state_bars, this.getQuote().getRange()),this.getOrders(),this.getQuote().getStrategy())) { 
						
					return false; 
					
				}	 
			
				// Export to file of closed orders.
			
				String m_closed_orders_file_name = gl.sf("%s_%d.log",
						this.getQuote().getPaper(),
						this.getQuote().getStrategy());
				
				if (!Fu.to_file(m_closed_orders_file_name, Order.orders_to_string(this.getOrders()))) { 
					return false; 
				}	 
	
		
				// Get group rows.
		
				if (!Group.get_groups(this.getOrders(),this.getGroups())) 
					return false; 
		 	
				// Show groups.
				String m_groups_text = Group.get_groups_as_text(this.getGroups(),Group.FULL_MASK);
				
				gl.sfn("%s",m_groups_text);
				
				// Add text to the statement file.
				
				if(!Fu.to_file_append(m_closed_orders_file_name,gl.sf("%s",m_groups_text)))
					return false;


				if (!get_open_orders(this.getOrders())) { 
					return false; 
				}	 

				if(!write_open_orders())
					return false;
				
	
					return true;
		}
		
		catch(Exception e)
		{
					return false;
		}

	} 
	
	
	
	
	public boolean get_open_orders(List<Order> orders) 
	{ 
			String msg = "Get still open orders.";
	
			List<Order> still = new ArrayList<Order>();
			
			try
			{
		
				still = 
						
				orders.stream().filter( 
			
				(o-> o.getClose() == null & o.getDuration() == gl.E_EMPTY)
			
			)
			.collect(Collectors.toList()); 

				
				int still_size = still.size();
				
				
				if(still_size != gl.E_EMPTY)
				{
					this.getOrders().removeAll(still);
					
					this.setOpen_orders(still);
				}
				
				gl.tracex(new Object() {}, String.format("%s...%s...Open orders...%s", gl.S_OK,msg,still_size)); 
				
				return (this.getOpen_orders().size() != gl.E_EMPTY);
			}
			catch(Exception e)
			{
				gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_ERROR,msg,e.toString())); 
							
				return false;
			}
	}
	
	public boolean write_open_orders() {
		
		String m_file_name = gl.sf("%s_OPEN_ORDERS_BOOK_%d.log",
				this.getQuote().getPaper(),
				this.getQuote().getStrategy());
		
		if(this.getOpen_orders().size() != gl.E_EMPTY)
		   Order.to_file(this.getOpen_orders(),m_file_name);
		
		return Fu.isaFile(m_file_name);
		
	}
	

}
