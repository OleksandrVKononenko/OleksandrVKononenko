
package ap.explorer; 


import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import ap.btn.Bar; 
import ap.btn.Order; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.utils.Fu;
import ap.utils.Su; 





public class Explorer { 
	
	public Explorer() { 
		 
	} 
	 
	 
	public static List<Ticker> get_tickers(int platform)
	{
			
				String win =  "c:\\bin\\spider\\look\\data\\2020\\"; 
						
				String mac = "/Users/alexplus/eclipse/wsp/look/data/2020/";
						
				
				String path = gl.sf("%s", (platform == Portfolio.MAC) ? mac : win);
				
				Range date_range = new Range("01.01.2010","31.12.2012"); 
				
				String m_some 		= Su.exclude("[m3] 0000 [m7] 0000 [m10] 1000 [m14] 0000 [m21] 1000 [m55] 0000 [m100] 0000 [m200] 0000","[","]");
				
				String m_any		= Su.exclude("[m3] 0000 [m7] 0000 [m10] 1000 [m14] 0000 [m21] 0000 [m55] 0000 [m100] 0000 [m200] 0000","[","]");
				
				String m_power 		= Su.exclude("[m3] 1000 [m7] 1000 [m10] 1000 [m14] 1000 [m21] 1000 [m55] 1000 [m100] 1000 [m200] 1000","[","]");

				
				long	the_strategy = Brew.decode(m_any);//2147483648L;

				
				List<Quote> quotes = Arrays.asList( new Quote[] {
						Quote.get_instance("usdchf", path,the_strategy, date_range,4,Brew.indexOf("AVA"))
				});
					
				List<Ticker> tickers = new ArrayList<Ticker>();
				
				quotes.forEach(q->
				{
					
					Ticker ticker = Ticker.get_instance(q); 
					
					tickers.add(ticker);
					
				});
							
					return tickers;
		
	}
	
	
	public static void main(String[] args) { 
		 
			
		Portfolio pf = Portfolio.get_instance("Trio_2018_2020",get_tickers(Portfolio.WIN));
		
				  pf.perform();
				  
				  //pf.show_total();
				  
				  //pf.show_portfolio_total();
				  
		} 

} 
