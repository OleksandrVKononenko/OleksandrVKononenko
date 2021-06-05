 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 

 
package ap.btn; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 
import ap.prop.SText; 
import ap.utils.Fu; 
import ap.utils.Su; 

public class TConfiguration { 

	 
	public static String DISK = ""; 
	 
	public static final String ORA_WORK  = "quotes/fcafkmn@orcltera:10.1.8.142:1521:ORACLE"; 
	 
	public static final String ORA_HOME  = "quotes/fcafkmn@orcl:192.168.1.103:1521:ORACLE"; 
	 
	public static final String ORA_CONNECTION_STRING = DISK.equalsIgnoreCase("D") ? ORA_HOME : ORA_WORK; 
	 
	public static String PORTFOLIO_NAME = ""; 
	 
	public static List<String> PORTFOLIO_MEMBERS = new ArrayList<String>(); 
	 
	public static String DATA_DIR = ""; 
	 
	public static String REPORT_DIR = ""; 
	 
	public static String LIST_DIR = ""; 
	 
	public static String LIST_FILE = ""; 
	 
	public static String ORDERS_REPORT_FILE = ""; 
	 
	public static final String REPORT_COLUMN_DELIMITER = ""; 
	 
	public static final String EXTRACT_SQL = "select t.dt,t.o,t.h,t.l,t.c from t_stock t where t.ticker='%s' and t.brew = 'nyse' order by 1"; 
	 
	// Period . 
	public static String PERIOD_BEGIN = ""; 
			 
	public static String PERIOD_END   = ""; 
	 
	// Re-write flag. 
	public static boolean REPORT_FILE_APPEND_MODE = false; 
	 
	//Create detail report about deals. 
	 
	public static boolean REPORT_DETAIL_MODE = false; 
	 
	// Data flow direct db or file. 
	 
	public static boolean DATA_STREAM_FROM_DB = false; 
	 
	// days_skip_before_open_the_deal 
	 
	public static int SKIP_DAYS_BEFORE_OPEN_THE_DEAL = gl.E_EMPTY; 
	 
	// TCrossMetric common set. 
	public static List<Integer> metrics = Arrays.asList(new Integer[]{ 
			TCrossMetric.M3, 
			TCrossMetric.M7, 
			TCrossMetric.M10, 
			TCrossMetric.M14, 
			TCrossMetric.M21, 
			TCrossMetric.M55, 
			TCrossMetric.M100, 
			TCrossMetric.M200 
			}); 
	 
	 
	public static List<Integer> open = null;//Arrays.asList(new Integer[]{0}); 
	 
	public static List<Integer> close = null;//Arrays.asList(new Integer[]{0}); 
		 
	// Profit optimization. 
	 
	public static int  PROFIT_OPTIMIZATION = 0; 
	 
	public static double TAKE_PROFIT  =  0.0d; 
	 
	public static double STOP_LOSS    =  0.0d; 
	 
	public static double PROFIT_DROPDOWN    =  0.0d; 
	 
	public static String VERSION    =  ""; 
	 
	public static int PROFIT_HOLD_JUMP_BUY = 1; 
	 
	public static int PROFIT_HOLD_JUMP_SELL = 1; 
	 
	public static int SHOW_CROSS_XREF = 0; 
	 
	public static int DIRECTION = 0; // 0 - set by default  1 - revers 
	 
	public static TDoublePair TAKE_PROFIT_BOUND = new TDoublePair(); // 
	 
	public static TDoublePair STOP_LOSS_BOUND = new TDoublePair(); // 
	 
	public static double RANGE_STEP = 0.0; 
	 
	public static int KEY_TOOL = 0; 
	 
	public static TJob raw_job = null; 
	 	 	 
	public static List<TOrderStat> ratings = new ArrayList<TOrderStat>(); 
	 
		public TConfiguration() { 
		 
	} 


	public static boolean isaRange(TJob bat) 
	{ 
		 
		String msg = String.format("Check TP&SL ranges"); 
		 
		if( bat.getTake_profit().indexOf("-") != gl.E_ERROR && 
			    bat.getStop_loss().indexOf("-") != gl.E_ERROR 
			    ) 
			{ 
				gl.tx_k(new Object(){},msg); 
				 
				return true; 
			} 
		 
				gl.tracex(new Object(){},String.format("%s...%s",msg,"this time range is not represented")); 
		 
				return false; 
		 
	} 
	public static boolean loadRange(TJob bat, TDoublePair dp_take_profit,TDoublePair dp_stop_loss) 
	{ 
		 
		String r_msg = "Parse double pair"; 
					 
	if ( 
			dp_take_profit.parse(bat.getTake_profit()) && 
			dp_stop_loss.parse(bat.getStop_loss()) 
		) 
		{ 
		 
				String msg = String.format("%s...SL %s...TP %s",r_msg,dp_stop_loss.toString(),dp_take_profit.toString()); 
				 
				gl.tx_k(new Object(){},msg); 
			 
				return true; 

		} 
	 
				String msg = String.format("%s...%s...%s",r_msg,bat.getStop_loss(),bat.getTake_profit()); 
				 
				gl.tx_e(new Object(){},msg); 
						 
				return false; 
		 
				 
	} 
		 
	public static boolean loadConfiguration(TJob bat) 
	{ 
		 
		TConfiguration.DISK = bat.getDisk(); 
		 
		TConfiguration.DATA_DIR = String.format(bat.getData_dir(),TConfiguration.DISK); 
		 
		TConfiguration.REPORT_DIR = String.format(bat.getReport_dir(),TConfiguration.DISK);; 
		 
		TConfiguration.LIST_DIR = String.format(bat.getList_dir(),TConfiguration.DISK); 
				 
		TConfiguration.LIST_FILE = String.format("%s\\%s",TConfiguration.LIST_DIR,bat.getList_file()); 
		 
		TConfiguration.PERIOD_BEGIN = bat.getStart_date(); 
		 
		TConfiguration.PERIOD_END  = bat.getEnd_date(); 

		TConfiguration.ORDERS_REPORT_FILE = String.format("%s\\%s",TConfiguration.REPORT_DIR,bat.getReport_file()); 

		TConfiguration.REPORT_FILE_APPEND_MODE = (	bat.getReport_file_append_mode().trim().equalsIgnoreCase("1") || 
													bat.getReport_file_append_mode().trim().equalsIgnoreCase("true") || 
													bat.getReport_file_append_mode().trim().equalsIgnoreCase("on") ) ? true:false; 
				 
		TConfiguration.REPORT_DETAIL_MODE = ( 
											bat.getReport_detail_mode().trim().equalsIgnoreCase("1") 	|| 
											bat.getReport_detail_mode().trim().equalsIgnoreCase("on") 	|| 
											bat.getReport_detail_mode().trim().equalsIgnoreCase("true") 
											); 
				 
		TConfiguration.PORTFOLIO_NAME = bat.getPortfolio_name(); 
		 
		TConfiguration.PORTFOLIO_MEMBERS = Fu.getListFromFile(TConfiguration.LIST_FILE); 
		 
		TConfiguration.DATA_STREAM_FROM_DB = 
						( 
								bat.getData_stream_from_db().trim().equalsIgnoreCase("1") || 
								bat.getData_stream_from_db().trim().equalsIgnoreCase("on") || 
								bat.getData_stream_from_db().trim().equalsIgnoreCase("true") 
						); 

		 
		if(TConfiguration.isaRange(bat)) 
		{ 
			// Ranges is presents. 
				 
			if(TConfiguration.loadRange(bat, 
					TConfiguration.TAKE_PROFIT_BOUND, 
					TConfiguration.STOP_LOSS_BOUND 
					)) 
			{ 
				gl.tx_k(new Object(){},String.format("Parse bound")); 
				 
				TConfiguration.RANGE_STEP = Double.parseDouble(bat.getRange_step()); 
			} 
		} 
		else 
		{ 
			TConfiguration.TAKE_PROFIT = Double.parseDouble(bat.getTake_profit()); 
		 
		    TConfiguration.STOP_LOSS = Double.parseDouble(bat.getStop_loss())*gl.E_ERROR; 
		 
		} 
		 
		 
		TConfiguration.KEY_TOOL = Integer.parseInt(bat.getKey_tool()); 
		 
		TConfiguration.VERSION = bat.getVersion().trim(); 
		 
		TConfiguration.SHOW_CROSS_XREF = Integer.parseInt(bat.getShow_cross_xref()); 
		 
		TConfiguration.DIRECTION = Integer.parseInt(bat.getDirection()); 
		 
		TConfiguration.open =  Su.get_as_list_of_int(bat.getOpen_list()); 
		 
		TConfiguration.close =  Su.get_as_list_of_int(bat.getClose_list()); 
		 
		TConfiguration.SKIP_DAYS_BEFORE_OPEN_THE_DEAL = Integer.parseInt(bat.getSkip_days_before_open_the_deal()); 
		 
		TConfiguration.raw_job = bat; 
		 
		return true; 
		 
	} 
	 
	 
	 
	 
	public static void main(String[] args) { 
		 
		String job_file =  "d:\\exp\\stat\\jobs\\start_ava.job"; 
		 
		TJob bat = new TJob(job_file); 
		 
		String msg = "Load configuration"; 
		 
		if(!bat.parse()) 
		{ 
			gl.tx_e(new Object(){},msg); 
			 
			return ; 
		} 
		 
			TConfiguration.loadConfiguration(bat); 
			 
			TConfiguration.open.forEach(a-> 
			{ 
				gl.smn(a); 
			} 
			); 
			 
			gl.smn(""); 
			 
			TConfiguration.close.forEach(a-> 
			{ 
				gl.smn(a); 
			} 
			); 

	} 

} 
// Revision : 10.09.2018 12:49:13 
