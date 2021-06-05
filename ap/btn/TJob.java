 
package ap.btn; 

import java.io.IOException; 

import ap.global.gl; 
import ap.utils.Fu; 

public class TJob { 

	private String home; 

	private String source; 

	private String disk; 

	private String data_dir; 

	private String report_dir; 

	private String report_file; 

	private String list_dir; 

	private String list_file; 

	private String start_date; 

	private String end_date; 

	private String output_forms; 

	private String report_column_delimiter; 
	 
	private String report_file_append_mode; 
	 
	private String open_list; 
	 
	private String close_list; 
	 
	private String portfolio_name; 
	 
	private String report_detail_mode; 
	 
	private String data_stream_from_db; 
	 
	private String stop_loss; 
		 
	private String take_profit; 
	 
	private String version; 
	 
	private String show_cross_xref; 
	 
	private String direction; 
	 
	private String range_step; 
	 
	private String key_tool; 
	 
	private String skip_days_before_open_the_deal; 
	 	 
	public String getSkip_days_before_open_the_deal() { 
		return skip_days_before_open_the_deal; 
	} 

	public void setSkip_days_before_open_the_deal( 
			String skip_days_before_open_the_deal) { 
		this.skip_days_before_open_the_deal = skip_days_before_open_the_deal; 
	} 

	public String getKey_tool() { 
		return key_tool; 
	} 

	public void setKey_tool(String key_tool) { 
		this.key_tool = key_tool; 
	} 

	public String getRange_step() { 
		return range_step; 
	} 

	public void setRange_step(String range_step) { 
		this.range_step = range_step; 
	} 

	public String getDirection() { 
		return direction; 
	} 

	public void setDirection(String direction) { 
		this.direction = direction; 
	} 

	public String getShow_cross_xref() { 
		return show_cross_xref; 
	} 

	public void setShow_cross_xref(String show_cross_xref) { 
		this.show_cross_xref = show_cross_xref; 
	} 

	public String getVersion() { 
		return version; 
	} 

	public void setVersion(String version) { 
		this.version = version; 
	} 

	public String getStop_loss() { 
		return stop_loss; 
	} 

	public void setStop_loss(String stop_loss) { 
		this.stop_loss = stop_loss; 
	} 

	public String getTake_profit() { 
		return take_profit; 
	} 

	public void setTake_profit(String take_profit) { 
		this.take_profit = take_profit; 
	} 


	public String getData_stream_from_db() { 
		return data_stream_from_db; 
	} 

	public void setData_stream_from_db(String data_stream_from_db) { 
		this.data_stream_from_db = data_stream_from_db; 
	} 

	public String getReport_detail_mode() { 
		return report_detail_mode; 
	} 

	public void setReport_detail_mode(String report_detail_mode) { 
		this.report_detail_mode = report_detail_mode; 
	} 

	public String getPortfolio_name() { 
		return portfolio_name; 
	} 

	public void setPortfolio_name(String portfolio_name) { 
		this.portfolio_name = portfolio_name; 
	} 

	public String getOpen_list() { 
		return open_list; 
	} 

	public void setOpen_list(String open_list) { 
		this.open_list = open_list; 
	} 

	public String getClose_list() { 
		return close_list; 
	} 

	public void setClose_list(String close_list) { 
		this.close_list = close_list; 
	} 

	public String getReport_file_append_mode() { 
		return report_file_append_mode; 
	} 

	public void setReport_file_append_mode(String report_file_append_mode) { 
		this.report_file_append_mode = report_file_append_mode; 
	} 

	public String getSource() { 
		return source; 
	} 

	public void setSource(String source) { 
		this.source = source; 
	} 

	public String getDisk() { 
		return disk; 
	} 

	public void setDisk(String disk) { 
		this.disk = disk; 
	} 

	public String getData_dir() { 
		return data_dir; 
	} 

	public void setData_dir(String data_dir) { 
		this.data_dir = data_dir; 
	} 

	public String getReport_dir() { 
		return report_dir; 
	} 

	public void setReport_dir(String report_dir) { 
		this.report_dir = report_dir; 
	} 

	public String getReport_file() { 
		return report_file; 
	} 

	public void setReport_file(String report_file) { 
		this.report_file = report_file; 
	} 

	public String getList_dir() { 
		return list_dir; 
	} 

	public void setList_dir(String list_dir) { 
		this.list_dir = list_dir; 
	} 

	public String getList_file() { 
		return list_file; 
	} 

	public void setList_file(String list_file) { 
		this.list_file = list_file; 
	} 

	public String getStart_date() { 
		return start_date; 
	} 

	public void setStart_date(String start_date) { 
		this.start_date = start_date; 
	} 

	public String getEnd_date() { 
		return end_date; 
	} 

	public void setEnd_date(String end_date) { 
		this.end_date = end_date; 
	} 

	public String getOutput_forms() { 
		return output_forms; 
	} 

	public void setOutput_forms(String output_forms) { 
		this.output_forms = output_forms; 
	} 

	public String getReport_column_delimiter() { 
		return report_column_delimiter; 
	} 

	public void setReport_column_delimiter(String report_column_delimiter) { 
		this.report_column_delimiter = report_column_delimiter; 
	} 

	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public boolean read() { 
		String source_text = null; 

		try { 
			source_text = Fu.get_str_from_file(this.getHome()); 
		} catch (IOException e) { 
			gl.tracex(new Object() { 
			}, String.format("Error while read from file...%s", this.getHome())); 

			return false; 
		} 

		if (source_text == null || source_text.trim().length() == gl.E_EMPTY) { 
			gl.tracex(new Object() { 
			}, String.format("Error while read from file...%s", this.getHome())); 

			return false; 
		} 

		gl.tracex(new Object() { 
		}, String.format("Read from file...%s...%s", this.getHome(), gl.S_OK)); 

		this.setSource(source_text.trim()); 

		return true; 
	} 

	public boolean parse() { 

		String msg = "Read source file...%s...%s"; 

		if (!read()) { 
			gl.tracex(new Object() { 
			}, String.format(msg, gl.S_ERROR, this.getSource())); 

			return false; 
		} 

		gl.tracex(new Object() { 
		}, String.format(msg, gl.S_OK, "")); 

		String temp = this.getSource(); 

		try { 

			String d = System.lineSeparator(); 

			this.setDisk(gl.getProperty(temp, "disk", d)); 
			 
			this.setData_dir(gl.getProperty(temp, "data_dir", d)); 

			this.setReport_dir(gl.getProperty(temp, "report_dir", d)); 

			this.setReport_file(gl.getProperty(temp, "report_file", d)); 

			this.setList_dir(gl.getProperty(temp, "list_dir", d)); 

			this.setList_file(gl.getProperty(temp, "list_file", d)); 

			this.setStart_date(gl.getProperty(temp, "start_date", d)); 

			this.setEnd_date(gl.getProperty(temp, "end_date", d)); 

			this.setOutput_forms(gl.getProperty(temp, "output_forms", d)); 

			 this.setReport_column_delimiter(gl.getProperty(temp,"report_column_delimiter", d)); 
			 
			 this.setReport_file_append_mode((gl.getProperty(temp,"report_file_append_mode", d)==null) ? "off" : gl.getProperty(temp,"report_file_append_mode", d)); 
			 
			 this.setOpen_list((gl.getProperty(temp,"open", d)==null) ? "0" : gl.getProperty(temp,"open", d)); 
			 
			 this.setClose_list((gl.getProperty(temp,"close", d)==null) ? "0" : gl.getProperty(temp,"close", d)); 
			 
			 this.setPortfolio_name((gl.getProperty(temp, "portfolio_name", d)==null ) ? "default": gl.getProperty(temp, "portfolio_name", d)); 

			 this.setReport_detail_mode((gl.getProperty(temp, "report_detail_mode", d)==null ) ? "0":gl.getProperty(temp, "report_detail_mode", d)); 

			 this.setData_stream_from_db((gl.getProperty(temp, "data_stream_from_db", d) ==null) ? "0" : gl.getProperty(temp, "data_stream_from_db", d)) ; 
			 
			 this.setStop_loss((gl.getProperty(temp, "stop_loss", d) ==null) ? "0.0" : gl.getProperty(temp, "stop_loss", d)); 
			 
			 this.setTake_profit((gl.getProperty(temp, "take_profit", d) ==null) ? "0.0" : gl.getProperty(temp, "take_profit", d)); 
			 
			 this.setVersion((gl.getProperty(temp, "version", d) ==null) ? "raw" : gl.getProperty(temp, "version", d)); 
			 
			 this.setShow_cross_xref((gl.getProperty(temp, "show_cross_xref", d) ==null) ? "0" : gl.getProperty(temp, "show_cross_xref", d)); 
			 
			 this.setDirection((gl.getProperty(temp, "direction", d) ==null) ? "0" : gl.getProperty(temp, "direction", d)); 
			 
			 this.setRange_step((gl.getProperty(temp, "range_step", d) ==null) ? "0.0009" : gl.getProperty(temp, "range_step", d)); 
			 
			 this.setKey_tool((gl.getProperty(temp, "key_tool", d) ==null) ? "0" : gl.getProperty(temp, "key_tool", d)); 
			 
			 this.setSkip_days_before_open_the_deal((gl.getProperty(temp, "skip_days_before_open_the_deal", d) ==null) ? "0" : gl.getProperty(temp, "skip_days_before_open_the_deal", d)); 
			 
			 
		} catch (NullPointerException e) { 

			gl.tracex(new Object() { 
			}, String.format("%s...%s", "a parsing variable is not initialize", 
					gl.S_ERROR)); 

			return false; 
		} 

		return true; 
	} 

	@Override 
	public String toString() { 

		String NL = System.lineSeparator(); 

		StringBuilder sb = new StringBuilder(); 

		sb.append(this.getDisk()); 

		sb.append(NL); 

		sb.append(this.getData_dir()); 

		sb.append(NL); 

		sb.append(this.getReport_dir()); 

		sb.append(NL); 

		sb.append(this.getList_dir()); 

		sb.append(NL); 

		sb.append(this.getList_file()); 

		sb.append(NL); 

		sb.append(this.getReport_file()); 

		sb.append(NL); 

		sb.append(this.getStart_date()); 

		sb.append(NL); 

		sb.append(this.getEnd_date()); 

		sb.append(NL); 

		sb.append(this.getOutput_forms()); 

		sb.append(NL); 

		sb.append(this.getReport_column_delimiter()); 
		 
		sb.append(NL); 

		sb.append(this.getReport_file_append_mode()); 
		 
		sb.append(NL); 

		sb.append(this.getOpen_list()); 

		sb.append(NL); 

		sb.append(this.getClose_list()); 

		sb.append(NL); 

		sb.append(this.getPortfolio_name()); 
		 
		sb.append(NL); 

		sb.append(this.getSkip_days_before_open_the_deal()); 
		 
		sb.append(NL); 
		 
		sb.append(this.getReport_detail_mode()); 
		 
		sb.append(NL); 

		sb.append(this.getData_stream_from_db()); 
		 
		sb.append(NL); 

		sb.append(this.getTake_profit()); 
		 
		sb.append(NL); 

		sb.append(this.getStop_loss()); 
		 
		sb.append(NL); 

		sb.append(this.getVersion()); 
		 
		sb.append(NL); 

		sb.append(this.getShow_cross_xref()); 
		 
		sb.append(NL); 

		sb.append(this.getDirection()); 
		 
		sb.append(NL); 
		 
		sb.append(this.getRange_step()); 
		 
		sb.append(NL); 
		 
		sb.append(this.getKey_tool()); 
		 
		sb.append(NL); 

		 
		return String.format("%s", sb.toString()); 

	} 

	public TJob() { 

	} 

	public TJob(String home) { 
		this(); 

		this.setHome(home); 

	} 

	public static void Test(String file_source) { 
		 
		TJob bat = new TJob(file_source); 

		String msg = "Test completed...%s"; 

		if (!bat.parse()) 
			gl.tracex(new Object() { 
			}, String.format(msg, gl.S_ERROR)); 
		else { 
			gl.tracex(new Object() { 
			}, String.format(msg, gl.S_OK)); 

			gl.smn(bat.toString()); 
		} 

	} 

	public static void main(String[] args) { 

		String source = "e:\\exp\\stat\\jobs\\start_raw.job"; 

		Test(source); 

	} 

} 
// Revision : 10.09.2018 12:49:13 
