package ap.btn; 

import java.util.ArrayList; 

import java.util.Arrays; 
import java.util.Collection; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Locale; 
import java.util.Map; 
import java.util.stream.Collectors;

import ap.explorer.Range;
import ap.gen.LIDgen; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.ma.Cross; 
import ap.ma.ICross; 
import ap.ma.IMa; 
import ap.ma.Ma; 
import ap.swing.IEvent; 
import ap.swing.IOrder; 
import ap.swing.Order; 
import ap.swing.Event; 
import ap.utils.CandleUtil; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 
import ap.utils.IntArrayUtil;
import ap.utils.MaUtil;
import ap.utils.Su; 

public class Bar { 
	

	public static final int O = 0; 
	 
	public static final int H = 1; 
	 
	public static final int L = 2; 
	 
	public static final int C = 3; 
	 
	 
	private int 	id; 
	 
	private Date 	date; 
	 
	private int 	time; 
	 
	private double  o; 
	 
	private double  h; 
	 
	private double  l; 
	 
	private double  c; 

	private int 	type; 
	 
	public static int DRAW_TYPE = gl.BAR; 
	 
	private List<String> magic;
	
	private List<Double> ma;
	
	private Map<String,Double> ratio ;
	
	
	
	public Map<String, Double> getRatio() {
		return ratio;
	}

	public void setRatio(Map<String, Double> ratio) {
		this.ratio = ratio;
	}
	 
	
	public double get_hl_ratio()
	{
		double a = this.getHighShadowM() == 0 ? 1 : this.getHighShadowM();
		
		double b = this.getLowShadowM()  == 0 ? 1 : this.getLowShadowM();
		
		return a/b;
	}


	public double get_lh_ratio()
	{
		double a = this.getHighShadowM() == 0 ? 1 : this.getHighShadowM();
		
		double b = this.getLowShadowM()  == 0 ? 1 : this.getLowShadowM();
		
		return b/a;
	}
	

	public double get_hb_ratio()
	{
		
		double a = this.getHighShadowM() == 0 ? 1 : this.getHighShadowM();
		
		double b = this.getBodyM()  == 0 ? 1 : this.getBodyM();
		
		return a/b;
	}


	public double get_bh_ratio()
	{
		double a = this.getHighShadowM() == 0 ? 1 : this.getHighShadowM();
		
		double b = this.getBodyM()  == 0 ? 1 : this.getBodyM();
		
		return b/a;
	}

	
	public double get_lb_ratio()
	{
		double a = this.getLowShadowM() == 0 ? 1 : this.getLowShadowM();
		
		double b = this.getBodyM()  == 0 ? 1 : this.getBodyM();
		
		return a/b;
	}


	public double get_bl_ratio()
	{
		double a = this.getLowShadowM() == 0 ? 1 : this.getLowShadowM();
		
		double b = this.getBodyM()  == 0 ? 1 : this.getBodyM();
		
		return b/a;
	}

	
	
	public List<Double> getMa() {
		return ma;
	}

	public void setMa(List<Double> ma) {
		this.ma = ma;
	}

	public List<String> getMagic() {
		return magic;
	}

	public void setMagic(List<String> magic) {
		this.magic = magic;
	}

	
	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 
	 
	public int getSign() 
	{ 
		if (this.getO() < this.getC()) 
			return gl.E_OK; 
		else if (this.getO() > this.getC()) 
			return gl.E_ERROR; 
		else if (this.getO() == this.getC()) 
			return gl.E_EMPTY; 
	 
			return gl.E_ERROR*2; 
				 
	} 
	 
	public double getBody() 
	{ 
		if(this.getSign()== gl.E_OK) 
			return (this.getC()-this.getO()); 
		else if(this.getSign()== gl.E_ERROR) 
			return (this.getO() - this.getC()); 
		 
			return gl.E_EMPTY;
	} 
	 
	 
	public double getHighShadow() 
	{ 
		if(this.getSign()== gl.E_OK) 
			return (this.getH() - this.getC()); 
		if(this.getSign()== gl.E_ERROR) 
			return (this.getH() - this.getO()); 
		else 
			return (this.getH() - this.getO()); 
		 
	} 
	 
	public int getHighShadowM() 
	{ 
		int i_result = (int)(this.getHighShadow()*TChart.EUR_MULTIPLIER); 
				 
		return i_result; 
	} 
	 
	public int getLowShadowM() 
	{ 
		int i_result = (int)(this.getLowShadow()*TChart.EUR_MULTIPLIER); 
				 
		return i_result; 
	} 

	public int getBodyM() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
			
		return Integer.parseInt(Su.BeforeAt(gl.sf("%.4f",getBodyD()),"."));

	} 
	 
	public double getBodyD() 
	{ 
		double m     = TChart.EUR_MULTIPLIER;
		
		return this.getBody()*m;
				
	} 


	public double getLowShadow() 
	{ 
		 
		if(this.getSign()== gl.E_OK) 
			return (this.getO() - this.getL()); 
		if(this.getSign()== gl.E_ERROR) 
			return (this.getC() - this.getL()); 
		else 
			return (this.getC() - this.getL()); 
		 
		 
		 
	} 
	 
	public double getVolume() 
	{ 
		 
		return (this.getHighShadow()+this.getBody()+this.getLowShadow()); 
	} 

	 
	 
	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public Date getDate() { 
		return date; 
	} 

	public void setDate(Date date) { 
		this.date = date; 
	} 

	public int getTime() { 
		return time; 
	} 

	public void setTime(int time) { 
		this.time = time; 
	} 

	public double getO() { 
		return o; 
	} 

	public void setO(double o) { 
		this.o = o; 
	} 

	public double getH() { 
		return h; 
	} 

	public void setH(double h) { 
		this.h = h; 
	} 

	public double getL() { 
		return l; 
	} 

	public void setL(double l) { 
		this.l = l; 
	} 

	public double getC() { 
		return c; 
	} 

	public void setC(double c) { 
		this.c = c; 
	} 

	public Bar() { 
		 
		this.setMagic(new ArrayList<String>());
		
		this.setMa(new ArrayList<Double>());
		
		this.setRatio(new LinkedHashMap<String,Double>());
	} 
	 
	public Bar(double o,double h,double l,double c) { 
		
		this();
		 
		this.setId(gl.E_EMPTY); 
		 
		this.setO(o); 
		 
		this.setH(h); 
		 
		this.setL(l); 
		 
		this.setC(c); 
		 
		this.setType(CandleUtil.getTypeOf(this)); 
		 
		this.set_ratios();
	} 
	
	public void set_ratios()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
		this.getRatio().put("bh", Double.parseDouble(gl.sf("%.2f",this.get_bh_ratio())));
		
		this.getRatio().put("hb", Double.parseDouble(gl.sf("%.2f",this.get_hb_ratio())));
		
		this.getRatio().put("bl", Double.parseDouble(gl.sf("%.2f",this.get_bl_ratio())));
		
		this.getRatio().put("lb", Double.parseDouble(gl.sf("%.2f",this.get_lb_ratio())));
		
		this.getRatio().put("lh", Double.parseDouble(gl.sf("%.2f",this.get_lh_ratio())));
		
		this.getRatio().put("hl", Double.parseDouble(gl.sf("%.2f",this.get_hl_ratio())));
				
	}
	 
	public Bar(String date,double o,double h,double l,double c) { 
		 
		this(o,h,l,c); 
		 
		this.setDate(DateUtil.to_date(date)); 
		 
			 
	} 
	 
	public Bar(Date date,double o,double h,double l,double c) { 
		 
		this(o,h,l,c); 
		 
		this.setDate(date); 
				 
	} 
	 

	public Bar(int id,String date,double o,double h,double l,double c) { 
		 
		this(date,o,h,l,c); 
		 
		this.setId(id); 
	} 
	 
	public Bar(int id,Date date,double o,double h,double l,double c) { 
		 
		this(date,o,h,l,c); 
		 
		this.setId(id); 
	} 
	
	 
	public String toStringFull() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%d,%s,%.4f,%.4f,%.4f,%.4f,%02d", 
				this.getId(), 
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC(), 
				this.getType() 
				); 
	} 
	 
	public String toStringDateOHLC() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%s,%.4f,%.4f,%.4f,%.4f", 
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC() 
 
				); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%4d %s,%.4f,%.4f,%.4f,%.4f,%02d", 
				this.getId(), 
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC(), 
				this.getType() 
				); 
	} 
	
	public String to_str() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%4d %s,%.4f,%.4f,%.4f,%.4f,[%3d,%3d,%3d],%1s,%02d", 
				this.getId(), 
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC(), 
				
				this.getHighShadowM(),
				this.getBodyM(),
				this.getLowShadowM(),
				
				this.getSign() == gl.E_ERROR ? "-" : (this.getSign()== gl.E_OK) ? "+" : "0",
				this.getType()
				); 
	} 
	
	public String to_str_for_export() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%s,%.4f,%.4f,%.4f,%.4f", 
				
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC()
				
				); 
	} 
	 
	public String ratio_to_str()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
		StringBuilder sb = new StringBuilder();
		
		this.getRatio().forEach((k,v)->{
		
			sb.append(gl.sf("%6.2f ",v));
			
		});
		
			return sb.toString();
	}
	
	public String to_str_for_ratio() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%s,%.4f,%.4f,%.4f,%.4f [%3d,%3d,%3d] {%s}", 
				
				DateUtil.to_string(this.getDate()), 
				this.getO(), 
				this.getH(), 
				this.getL(), 
				this.getC(),
				
				this.getHighShadowM(),
				this.getBodyM(),
				this.getLowShadowM(),
			
				this.ratio_to_str()
				
				); 
	}
	public String toStringType() 
	{ 
		 
		return String.format("%02d", 
				this.getType() 
				); 
	} 
	 
	public static Bar getInstance(int id,String date,double o,double h,double l,double c) 
	{ 
		return new Bar(id,date,o,h,l,c); 
	} 
	 
	public static Bar getInstance(Date date,double o,double h,double l,double c) 
	{ 
		return new Bar(date,o,h,l,c); 
	} 
	 
	public static Bar getInstance(String source_row,String begin_date,String end_date) 
	{ 

		if(source_row == null) 
			return null; 
			 
		String[] arr = source_row.split(","); 
		 
		if(arr.length == gl.E_EMPTY) 
			return null; 
		 
		 
		Date ctrlDate = DateUtil.to_date(arr[0]); 
		 
		int DATE=0,O=1,H=2,L=3,C=4; 
		 
		if(DateUtil.in_range(ctrlDate,begin_date,end_date)) 
			return new Bar( 
				arr[0], 
				gl.norm4Double(arr[O]), 
				gl.norm4Double(arr[H]), 
				gl.norm4Double(arr[L]), 
				gl.norm4Double(arr[C]) 
			 
				); 
		else 
		{ 
			return null; 
		} 
		 
		 
	} 
	 
	public static Bar getInstance(int id,String source_row,String source_format) 
	{ 
		if(source_row == null) 
			return null; 
		 
		// MT pure format. 
		// 1994.05.02,00:00,1.18210,1.18860,1.17980,1.18650,133 
		 
		// Normal format. 
		//25.11.2005,1.17790,1.17830,1.17090,1.17220 
		 
		String[] arr = source_row.split(","); 
		 
		if(arr.length == gl.E_EMPTY) 
		{ 
		 
			gl.smn("TBarItem.getInstance()...array is empty."); 
			 
			return null; 
		} 
		 
		int DATE=0,O=1,H=2,L=3,C=4; 
		 
		Date date = null; 
		 
		if(source_format.equalsIgnoreCase("fibo")) 
		{ 
			O=2;H=3;L=4;C=5; 
			 
			date = DateUtil.to_date_from_fibo_string(arr[DATE]); 
			 
		} else if(source_format.equalsIgnoreCase("normal")) 
		{ 
			date = DateUtil.to_date(arr[DATE]); 
		} 
			 
			return new Bar( 
				id, 
				date, 
				gl.norm4Double(arr[O]), 
				gl.norm4Double(arr[H]), 
				gl.norm4Double(arr[L]), 
				gl.norm4Double(arr[C]) 
				); 
		 
		 
	} 

	
	public static Bar get_instance(int id,String source_row,String source_format) 
	{ 
		if(source_row == null) 
			return null; 
		 
		// MT pure format. 
		// 1994.05.02,00:00,1.18210,1.18860,1.17980,1.18650,133 
		 
		// Normal format. 
		//25.11.2005,1.17790,1.17830,1.17090,1.17220 
		 
		String[] arr = source_row.split(","); 
		 
		if (arr.length == gl.E_EMPTY) //if(gl.tx(new Object(){},(arr.length == gl.E_EMPTY),gl.sf("Данные вне формата...[%s]",source_row)))
			return null; 
		 
		int DATE=0,O=1,H=2,L=3,C=4; 
		 
		Date date = null; 
		 
		if(source_format.equalsIgnoreCase("fibo")) 
		{ 
			O=2;H=3;L=4;C=5; 
			 
			date = DateUtil.to_date_from_fibo_string(arr[DATE]); 
			 
		} else if(source_format.equalsIgnoreCase("normal")) 
		{ 
			date = DateUtil.to_date(arr[DATE]); 
		} 
			 
			return new Bar( 
				id, 
				date, 
				gl.norm4Double(arr[O]), 
				gl.norm4Double(arr[H]), 
				gl.norm4Double(arr[L]), 
				gl.norm4Double(arr[C]) 
				); 
		 
		 
	} 

	 
	public static boolean readFromFile(String source_file,Map<Date,Bar> bar_map,String[] response) 
	{ 
		Object v = new Object(){}; 
		 
		String msg = "Read from file"; 
		 
		long start = System.nanoTime(); 

		if (!Fu.isaFile(source_file)) { 
			 
			gl.tracex(v, String.format("%s...%s...source file [%s] is not exists", gl.S_ERROR,msg, source_file)); 

			return false; 
		} 

		if (bar_map == null) { 
			 
			gl.tracex(v, String.format("%s...%s...target is null.",gl.S_ERROR,msg)); 
			 
			return false; 
			 
		} 

		bar_map.clear(); 

		try { 
			 
			// Check out of file header. 
			 
			TQuotesFileHeader qh = new TQuotesFileHeader(source_file); 
			 
			 
			// Set series. 
			if (!qh.get(response)) 
			{ 
				return false; 
			} 
			 
			List<String> source_list = Fu.getFileAsStringListSkipComments(source_file); 

			if (source_list.size() == gl.E_EMPTY) { 
				 
				gl.tracex(new Object() {}, String.format("%s...%s...read source...%s", gl.S_ERROR,msg, source_file)); 

				return false; 
			} 

			gl.tracex(new Object() {}, String.format("%s...%s...read source...%s...items...%06d", gl.S_OK,msg,source_file,source_list.size())); 

			// One time check format of data row. 
			 
			String txt_for_check = source_list.get(gl.E_OK); 
			 
			String [] check_date_format = {""}; 
			 
			if(!TQuotesFileHeader.isaQuoteRow(txt_for_check,check_date_format)) 
			{ 
				gl.tracex(new Object() {}, String.format("%s...%s...unknown row format source...%s", gl.S_ERROR,msg,txt_for_check)); 
		 
				return false; 
			} 
			 
				gl.tracex(new Object() {}, String.format("%s...%s...CHECK...%s...source...%s", gl.S_OK,msg,check_date_format[gl.E_EMPTY],txt_for_check)); 
			 
				int id[] = { 1 }; 

				source_list.forEach(a -> { 

				if(a.trim().length() != gl.E_EMPTY) 
				{ 
					 
					Bar tbi = Bar.getInstance(id[0], a,check_date_format[gl.E_EMPTY]); 
					 
					bar_map.put(tbi.getDate(),tbi); 

					id[0]++; 
				} 

			}); 

		} catch (Exception e) { 

				gl.tracex(new Object() {}, String.format("%s...%s...%s...%s", gl.S_ERROR,msg,source_file,e.toString().substring(1,32))); 

				return false; 

		} 

		long end = System.nanoTime(); 
		 
		boolean bl_result = (bar_map.size() != gl.E_EMPTY); 

		if (bl_result) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s...load from...%s...size...%06d...in...%06d ms.", gl.S_OK,msg, source_file, bar_map.size(),(end-start)/1000000)); 
			 
		} 
		else 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s...load from...%s", gl.S_ERROR,msg, source_file)); 
			 
		} 

			return bl_result; 

	} 

	 
	 
	 
	 
	public static boolean readFromFile(String source_file,List<Bar> bar_items,String[] response) 
	{ 
		Object v = new Object(){}; 
		 
		String msg = "Read from file"; 
		 
		long start = System.nanoTime(); 

		if (!Fu.isaFile(source_file)) { 
			 
			gl.tracex(v, String.format("%s...%s...source file [%s] is not exists", gl.S_ERROR,msg, source_file)); 

			return false; 
		} 

		if (bar_items == null) { 
			 
			gl.tracex(v, String.format("%s...%s...target is null.",gl.S_ERROR,msg)); 
			 
			return false; 
			 
		} 

		bar_items.clear(); 

		try { 
			 
			// Check out of file header. 
			 
			TQuotesFileHeader qh = new TQuotesFileHeader(source_file); 
			 
			 
			// Set series. 
			if (!qh.get(response)) 
			{ 
				return false; 
			} 
			 
			List<String> source_list = Fu.getFileAsStringListSkipComments(source_file); 

			if (source_list.size() == gl.E_EMPTY) { 
				 
				gl.tracex(new Object() {}, String.format("%s...%s...read source...%s", gl.S_ERROR,msg, source_file)); 

				return false; 
			} 

			gl.tracex(new Object() {}, String.format("%s...%s...read source...%s...items...%06d", gl.S_OK,msg,source_file,source_list.size())); 

			// One time check format of data row. 
			 
			String txt_for_check = source_list.get(gl.E_OK); 
			 
			String [] check_date_format = {""}; 
			 
			if(!TQuotesFileHeader.isaQuoteRow(txt_for_check,check_date_format)) 
			{ 
				gl.tracex(new Object() {}, String.format("%s...%s...unknown row format source...%s", gl.S_ERROR,msg,txt_for_check)); 
		 
				return false; 
			} 
			 
				gl.tracex(new Object() {}, String.format("%s...%s...CHECK...%s...source...%s", gl.S_OK,msg,check_date_format[gl.E_EMPTY],txt_for_check)); 
			 
				int id[] = { 1 }; 

				source_list.forEach(a -> { 

				if(a.trim().length() != gl.E_EMPTY) 
				{ 
					bar_items.add(Bar.getInstance(id[0], a,check_date_format[gl.E_EMPTY])); 

					id[0]++; 
				} 

			}); 

		} catch (Exception e) { 

				gl.tracex(new Object() {}, String.format("%s...%s...%s...%s", gl.S_ERROR,msg,source_file,e.toString().substring(1,32))); 

				return false; 

		} 

		long end = System.nanoTime(); 
		 
		boolean bl_result = (bar_items.size() != gl.E_EMPTY); 

		if (bl_result) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s...load from...%s...size...%06d...in...%06d ms.", gl.S_OK,msg, source_file, bar_items.size(),(end-start)/1000000)); 
			 
		} 
		else 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s...load from...%s", gl.S_ERROR,msg, source_file)); 
			 
		} 

			return bl_result; 

	} 

	 
	 
	 
	 
	 
	 
	public static boolean readFromFile(TChart owner,String source_file,List<Bar> items) 
	{ 
			Object v = new Object() {}; 
		 
		if (!Fu.isaFile(source_file)) { 
			gl.tracex(new Object() {}, gl.sf("%s...Source file [%s] is not exists", gl.S_ERROR, source_file)); 

			return false; 
		} 

		if (items == null) { 
			gl.tracex(new Object() {}, gl.sf("%s...Target collection object is null", gl.S_ERROR)); 

			return false; 

		} 

		items.clear(); 

		try { 
			 
			// Check out of file header. 
			 
			TQuotesFileHeader qh = new TQuotesFileHeader(source_file); 
			 
			 
			// Set series. 
			if (!qh.get(owner)) 
			{ 
				return false; 
			} 
			 
			List<String> source_list = Fu.getFileAsStringListSkipComments(source_file); 

			if (source_list.size() == gl.E_EMPTY) { 
				 
				gl.tracex(new Object() {}, gl.sf("%s...read source...%s", gl.S_ERROR, source_file)); 

				return false; 
			} 

			gl.tracex(new Object() {}, gl.sf("%s...READ...%s...records...%d", gl.S_OK, source_file,source_list.size())); 

			// One time check format of data row. 
			 
			String txt_for_check = source_list.get(gl.E_OK); 
			 
			String [] check_date_format = {""}; 
			 
			if(!TQuotesFileHeader.isaQuoteRow(txt_for_check,check_date_format)) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...unknown row format source...%s", gl.S_ERROR,txt_for_check)); 
				 
				return false; 
			} 
			 
				gl.tracex(new Object() {}, gl.sf("%s...CHECK...%s...source...%s", gl.S_OK,check_date_format[gl.E_EMPTY],txt_for_check)); 
			 
				int id[] = { 1 }; 

				source_list.forEach(a -> { 

				if(a.trim().length() != gl.E_EMPTY) 
				{ 
					items.add(Bar.getInstance(id[0], a,check_date_format[gl.E_EMPTY])); 

					id[0]++; 
				} 

			}); 

		} catch (Exception e) { 

			gl.tracex(new Object() {}, gl.sf("%s...EXCEPTION...%s...%s", gl.S_ERROR, source_file,e.toString().substring(1,32))); 

			return false; 

		} 

		boolean bl_result = (items.size() != gl.E_EMPTY); 

		if (bl_result) 
		{ 
			gl.tracex(new Object() {}, gl.sf("%s...LOAD from...%s...size...%d", gl.S_OK, source_file, items.size())); 
		} 
		else 
		{ 
			gl.tracex(new Object() {}, gl.sf("%s...LOAD from...%s", gl.S_ERROR, source_file)); 
		} 

			return bl_result; 

	} 

	 
	 
	public static boolean parseOneItem(String source,List<Bar> items) 
	{ 
	 
		Object v = new Object(){}; 
		 
		if(source == null || source.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("Source is empty...%s ",gl.S_ERROR)); 
			 
			return false; 
		} 
		 
		if(items == null) 
		{ 
			gl.tracex(v,String.format("Received collection must be created before it call...%s",source,gl.S_ERROR)); 
		 
			return false; 
		 
		} 
		 
			items.clear(); 
		 
		try { 
			 
			String[] arr = source.split(System.lineSeparator()); 
			 
			if(arr.length == gl.E_EMPTY) 
			{ 
				gl.tracex(v,String.format("Error while parsing...%s...%s ",source,gl.S_ERROR)); 
				 
				return false; 
			} 
			 
			 
			//gl.tracex(v,String.format("Start load of item...%s",source,gl.S_OK)); 
			 
			List<String> list = Arrays.asList(arr); 

			int id[] = {1}; 
			 
			String [] response = {""}; 
			 
			String txt_for_check = list.get(gl.E_EMPTY); 
			 
			if(!TQuotesFileHeader.isaQuoteRow(txt_for_check,response)) 
			{ 
				gl.tracex(v, String.format("%s...unknown row format...%s", gl.S_ERROR,txt_for_check)); 
				 
				return false; 
			} 
			 
			//gl.tracex(v, String.format("%s...source file format...%s", gl.S_OK,response[gl.E_EMPTY])); 
			 
			 
			list.forEach(a -> { 
			 
					items.add(Bar.getInstance(id[0],a,response[gl.E_EMPTY])); 
					 
					id[0]++; 
				 
			}); 
			 
				return (items.size() != gl.E_EMPTY); 
			 
		} catch (Exception e) { 
			 
			gl.tracex(v,String.format("Error while read...%s...%s ",source,gl.S_ERROR)); 
			 
				return false; 
			 
		} 
	} 
	 
	/* 
	public static boolean readFromDb(String ticker,List<TBarItem> items) 
	{ 
	 
		Object v = new Object(){}; 
		 
		String ora_connection = TConfiguration.ORA_CONNECTION_STRING; 
		 
		DbUser dbu = new DbUser(ora_connection); 
		 
		String msg = String.format("%s...%s","Check connection","%s"); 
		 
		if(!dbu.checkJDBConnectionWrapper()) 
		{ 
			gl.tracex(new Object(){},String.format(msg,"Error")); 
			 
			return false; 
		} 
		 
			gl.tracex(v,String.format(msg,"Ok")); 
			 
			msg = String.format("%s...%s...%s","Run sql","%s","%06d"); 
			 
			return DbUtils.extractToList(dbu ,ticker,items); 
		 
	} 
	 */ 
	public static Bar get_instance() 
	{ 
		return new Bar(-1, "01.01.1900", -1.0, -1.0, -1.0,-1.0); 
	} 
	 
	public static Bar get_sample_instance() 
	{ 
		return new Bar(gl.E_OK, "31.03.2019", 0.9900, 1.0100, 0.9800,1.0000); 
	} 
	 
	public static void get_ma(List<Bar> bars) 
	{ 
		 Object v = new Object(){}; 
		 
		 String msg = "Create MA suite V2"; 
		 	
		 long start = System.nanoTime(); 
		 
		 bars.forEach(b-> 
			{ 
					
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.C;o++ ) 
						{
							 	
								double 	m_avg_value = MaUtil.get_avg(b.getId(),bars,gl.ma_period[i],o); 
						
								b.getMa().add(m_avg_value);
								
						} // OHLC index.
						
						//Fu.to_file_append("trace_index_ohlc_dt.log","\r\n");
												 
				} // MA first index.
											
						
			}); // bars		 
	
		 long end = System.nanoTime(); 
		
		 gl.tx_k(v,gl.sf("%s...Done in %d ms.", msg,(end-start)/1000000));
		 
	}
	 
	
	
	
	public static List<TMa> get_ma_old(List<Bar> input_list) 
	{ 
		Object v = new Object(){}; 
		 
		String msg = "Create MA suite V1."; 
		 
		long start = System.nanoTime(); 
		 
		List<TMa> target_list = new ArrayList<TMa>(); 
					 
		if(input_list.size()== gl.E_EMPTY) 
		{ 
			gl.tx_e(v,gl.sf("%s...Bad input params.",msg)); 
			 
			return null; 
		} 
		 

		List<Bar> m3 = new ArrayList<Bar>(); 
			 
		List<Bar> m5 = new ArrayList<Bar>(); 
			 
		List<Bar> m7 = new ArrayList<Bar>(); 
			 
		List<Bar> m10 = new ArrayList<Bar>(); 
			 
		List<Bar> m14 = new ArrayList<Bar>(); 
			 
		List<Bar> m21 = new ArrayList<Bar>(); 
			 
		List<Bar> m33 = new ArrayList<Bar>(); 
			 
		List<Bar> m55 = new ArrayList<Bar>(); 
			 
		List<Bar> m100 = new ArrayList<Bar>(); 
			 
		List<Bar> m144 = new ArrayList<Bar>(); 
			 
		List<Bar> m200 = new ArrayList<Bar>(); 
			 
		input_list.forEach(a->{ 
				 
				double avg3 = 0.0d; 
				 
				double avg5 = 0.0d; 
				 
				double avg7 = 0.0d; 
				 
				double avg10 = 0.0d; 
				 
				double avg14 = 0.0d; 
				 
				double avg21 = 0.0d; 
				 
				double avg33 = 0.0d; 
				 
				double avg55 = 0.0d; 
				 
				double avg100 = 0.0d; 
				 
				double avg144 = 0.0d; 
				 
				double avg200 = 0.0d; 
				 
				 
				 
				if(m3.size()== 3) 
				{ 
					 
					avg3 = m3.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m3.remove(gl.E_EMPTY); 
					 
					m3.add(a); 
					 
				}else 
				{ 
					avg3 = a.getO(); 
					 
					m3.add(a); 
				} 
				 
				if(m5.size()== 5) 
				{ 
					 
					avg5 = m5.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m5.remove(gl.E_EMPTY); 
					 
					m5.add(a); 
					 
				}else 
				{ 
					avg5 = a.getO(); 
					 
					m5.add(a); 
				} 
				 
				if(m7.size()== 7) 
				{ 
					avg7 = m7.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m7.remove(gl.E_EMPTY); 
					 
					m7.add(a); 
					 
				}else 
				{ 
					avg7 = a.getO(); 
					 
					m7.add(a); 
				} 
				 
				 
				if(m10.size()== 10) 
				{ 
					avg10 = m10.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m10.remove(gl.E_EMPTY); 
					 
					m10.add(a); 
					 
				}else 
				{ 
					avg10 = a.getO(); 
					 
					m10.add(a); 
				} 
				 
				if(m14.size()== 14) 
				{ 
					avg14 = m14.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m14.remove(gl.E_EMPTY); 
					 
					m14.add(a); 
					 
				}else 
				{ 
					avg14 = a.getO(); 
					 
					m14.add(a); 
				} 
				 
				if(m21.size()== 21) 
				{ 
					avg21 = m21.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m21.remove(gl.E_EMPTY); 
					 
					m21.add(a); 
					 
				}else 
				{ 
					avg21 = a.getO(); 
					 
					m21.add(a); 
				} 
				 
				if(m33.size()== 33) 
				{ 
					avg33 = m33.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m33.remove(gl.E_EMPTY); 
					 
					m33.add(a); 
					 
				}else 
				{ 
					avg33 = a.getO(); 
					 
					m33.add(a); 
				} 
				 
				if(m55.size()== 55) 
				{ 
					avg55 = m55.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m55.remove(gl.E_EMPTY); 
					 
					m55.add(a); 
					 
				}else 
				{ 
					avg55 = a.getO(); 
					 
					m55.add(a); 
				} 
				 
				if(m100.size()== 100) 
				{ 
					avg100 = m100.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m100.remove(gl.E_EMPTY); 
					 
					m100.add(a); 
					 
				}else 
				{ 
					avg100 = a.getO(); 
					 
					m100.add(a); 
				} 
				 
				if(m144.size()== 144) 
				{ 
					avg144 = m144.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m144.remove(gl.E_EMPTY); 
					 
					m144.add(a); 
					 
				}else 
				{ 
					avg144 = a.getO(); 
					 
					m144.add(a); 
				} 
				 
				if(m200.size()== 200) 
				{ 
					avg200 = m200.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
					 
					m200.remove(gl.E_EMPTY); 
					 
					m200.add(a); 
					 
				}else 
				{ 
					avg200 = a.getO(); 
					 
					m200.add(a); 
				} 
					 
					TMa 	ma = TMa.getInstance(a.getDate(),avg3,avg5,avg7,avg10,avg14,avg21,avg33,avg55,avg100,avg144,avg200); 
					 
					target_list.add(ma); 
					 
			}); 
		 
		 			long end = System.nanoTime(); 
		 			 
					gl.tx_k(new Object(){},gl.sf("%s...Size...%d...in...%d...ms.",msg,target_list.size(),(end-start)/1000000)); 
			 
					return target_list; 
	} 
	 
	 
	public static void Test() 
	{ 
		 
			Bar tb = new Bar(1,"01.02.2003",1.001,2.002,3.003,4.004); 
			 
			gl.smn(tb.toString()); 
	} 
	 
	public static void Test_LoadFromFile(String file) 
	{ 
		String[] series = {"",""}; 
		 
		String msg = "Test load suite "; 
		 
		List<Bar> list = new ArrayList<Bar>(); 
		 
		if(!Bar.readFromFile(file,list,series)) 
			gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
		else 
			gl.tracex(new Object(){},String.format("%s...%s...items...%06d ",gl.S_OK,msg,list.size())); 
		 
		List<TMa> ma_list = Bar.get_ma_old(list); 	 
		 
		Logger log = new Logger("ma_report_old.txt"); 
		 
		 ma_list.forEach(m3->{ 

			 log.awrite(gl.sf("%s",m3.toString())); 
			 
		 }); 
	 
		 
	} 
	 
	 
	public static void Test_load_from_file_ma(String file) 
	{ 
		String[] series = {"",""}; 
		 
		String msg = "Test load suite bars&ma"; 
		 
		Map<Date,Bar> m_bar_map = new LinkedHashMap<Date,Bar>(); 
		 
		if(!Bar.readFromFile(file,m_bar_map,series)) 
			gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
		else 
			gl.tracex(new Object(){},String.format("%s...%s...items...%06d ",gl.S_OK,msg,m_bar_map.size())); 
		 
		Map<Date,Ma>  m_ma_map = new LinkedHashMap<Date,Ma>(); 
		 
		if (!IMa.get_ma(m_bar_map,m_ma_map)) 
		{ 
				gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
			 
				return; 
		} 
		 
		gl.tracex(new Object(){},String.format("%s...%s...items...%06d ",gl.S_OK,msg,m_ma_map.size())); 
		 
		Logger log = new Logger("ma_report_new.txt"); 
		 
				 m_ma_map.values().forEach(m->{ 
		 
					 log.awrite(gl.sf("%s",m.toString())); 
					 
				 }); 
				 
		 
	} 
	 
	public static void write(String file,Map<Date,Bar> map) 
	{ 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 map.values().forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.toString())); 
			 
		 }); 
	} 

	public static void write(String file,Collection<Bar> collection) 
	{ 
		Fu.delete_suite(file); 
		 
		Logger log = new Logger(file); 
		 
		 collection.forEach(ma->{ 

			 log.awrite(gl.sf("%s",ma.to_str())); 
			 
		 }); 
	} 
	 
	public static boolean to_file(String file,List<Bar> list,boolean mode) 
	{ 
		try
		
		{
		
		StringBuilder sb = new StringBuilder();
		
		list.forEach(a->{
			
			//sb.append(a.to_str_for_export());
			
			sb.append(a.to_str_for_ratio());
			
			sb.append(System.lineSeparator());
			
		});
		
		 	Fu.to_file(file,sb.toString(),mode);
		 
		 	return true;
		}
		catch(Exception e)
		{
			return false;
		}
	} 
	
	
	 
	public static void test_up_bar(String file) 
	{ 
			String[] series = {"",""}; 
		 
			String msg = "Test load suite bars&ma"; 
		 
			Map<Date,Bar> m_bar_map = new LinkedHashMap<Date,Bar>(); 
		 
		if(!Bar.readFromFile(file,m_bar_map,series)) 
			gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
		else 
			gl.tracex(new Object(){},String.format("%s...%s...items...%06d ",gl.S_OK,msg,m_bar_map.size())); 

		gl.smn(gl.sf("Up bar : %s",get_up_bar_in_range(m_bar_map,Range.get_instance(DateUtil.to_date("11.12.1997"),DateUtil.to_date("17.12.1997"))).toStringDateOHLC())); 
		 
		gl.smn(gl.sf("Down bar : %s",get_down_bar_in_range(m_bar_map,Range.get_instance(DateUtil.to_date("11.12.1997"),DateUtil.to_date("17.12.1997"))).toStringDateOHLC())); 
		 
	} 
	 
	
	 
	public static boolean read (String ticker,String path,List<Bar> items) 
	{ 
		if(TConfiguration.DATA_STREAM_FROM_DB) 
			return true;//readFromDb(ticker,items); 
		else 
		{ 
			String m_path = String.format("%s\\%s.txt",path,ticker); 
			 
			String[] series = {"",""}; 
			 
			return readFromFile(m_path,items,series); 
		} 
		 
	} 
	 
	 
	public static boolean get_date_range(List<Bar> items, Range [] range) 
	{ 
		 
		if(items ==null || items.size() == gl.E_EMPTY) 
			return false; 
		 
		Date begin = items.get(gl.E_EMPTY).getDate(); 
		 
		Date end   = items.get(items.size() -1).getDate(); 
		 
		range[gl.E_EMPTY] = Range.get_instance(begin,end); 
		 
		return true; 
		 
	} 
	 
	public static Range get_date_range(List<Bar> items) 
	{ 
		Range [] range = {null,null}; 
		 
		if(get_date_range(items,range)) 
			return range[gl.E_EMPTY]; 
		 
			return range[gl.E_EMPTY]; 
		 
	} 
	 
	public static double get_high_in_range(Map<Date,Bar> bar_map,Range range) 
	{ 

		return bar_map.values().stream().filter( 
					(b-> ( 
							DateUtil.isa_date_is_in_range(b.getDate(),range) 
						))).collect(Collectors.toList()).stream().mapToDouble(a -> a.getH()).max().getAsDouble(); 
	} 
	 
	public static double get_low_in_range(Map<Date,Bar> bar_map,Range range) 
	{ 

		return bar_map.values().stream().filter( 
					(b-> ( 
							DateUtil.isa_date_is_in_range(b.getDate(),range) 
						))).collect(Collectors.toList()).stream().mapToDouble(a -> a.getL()).min().getAsDouble(); 
	} 

	 
	public static Bar get_up_bar_in_range(Map<Date,Bar> bar_map,Range range) 
	{ 

		 
		return bar_map.values().stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range) 
					))).collect(Collectors.toList()).stream() 
				.sorted(Comparator.comparingDouble(Bar::getO).reversed()).findFirst().get(); 
		 
	} 
	 
	public static Bar get_up_bar_in_range(List<Bar> bar_list,Range range) 
	{ 

		 
		return bar_list.stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range) 
					))).collect(Collectors.toList()).stream() 
				.sorted(Comparator.comparingDouble(Bar::getO).reversed()).findFirst().get(); 
		 
	} 
	 
	public static Bar get_down_bar_in_range(Map<Date,Bar> bar_map,Range range) 
	{ 

		 
		return bar_map.values().stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range) 
					))).collect(Collectors.toList()).stream() 
				.sorted(Comparator.comparingDouble(Bar::getO)).findFirst().get(); 
		 
	} 
	 
	public static Bar get_down_bar_in_range(List<Bar> bar_list,Range range) 
	{ 

		 
		return bar_list.stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range) 
					))).collect(Collectors.toList()).stream() 
				.sorted(Comparator.comparingDouble(Bar::getO)).findFirst().get(); 
		 
	} 
	 
	 
	public static double get_open_in_range(List<Bar> items,Range range) 
	{ 
		
		return get_by_date(items,range.getDate_from()).getO();
	
	}
	
	public static double get_close_in_range(List<Bar> items,Range range) 
	{ 
		
		return get_by_date(items,range.getDate_to()).getO();
	
	}
	
	
	public static Bar get_range_bar(List<Bar> items,Range range)
	{
		
		return Bar.get_instance(
				get_open_in_range(items,range), 
				get_high_in_range(items,range), 
				get_low_in_range(items,range), 
				get_close_in_range(items,range) 
				);
	}
	
	public static double get_high_in_range(List<Bar> items,Range range) 
	{ 
	
		return get_bars_by_date_range(items,range).stream().mapToDouble(a -> a.getH()).max().getAsDouble(); 

	} 
	 
	 
	public static double get_low_in_range(List<Bar> items,Range range) 
	{ 
		
		return get_bars_by_date_range(items,range).stream().mapToDouble(a -> a.getL()).min().getAsDouble(); 

	} 
	 
	 
	public static int get_id_by_date(List<Bar> items,String date) 
	{ 
		 
		int i_result = gl.E_ERROR; 
		 
		if(items ==null || items.size() == gl.E_EMPTY) 
			return i_result; 
		 

		Bar bar = items.stream().filter( 
						( 
								b -> ( 
										b.getDate().compareTo(DateUtil.to_date(date)) == gl.E_EMPTY 
								))) 
						.findFirst().orElse(null); 
		 
		if(bar==null) 
			return i_result; 
	 
		 
		return bar.getId(); 
		 
	} 
	 
	public static Bar get_by_date(List<Bar> items,Date date) 
	{ 
		 
		return  items.stream().filter( 
						( 
								b -> ( 
										b.getDate().compareTo(date) == gl.E_EMPTY 
								))) 
						.findFirst().orElse(null); 
		 	 
	} 
	 
	 
	public static boolean accept_filter(List<Bar> items,List<Bar> filter,Range range) 
	{ 
	 
				// Filter by date. 
				 
				if(items.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},"Empty input collection"); 
					 
					return false; 
				} 
				 
				filter.clear(); 
				 
				for(int i=0; i < items.size();i++) 
				{ 
					if(DateUtil.isa_date_is_in_range(items.get(i).getDate(),range)) 
					{ 
						filter.add(items.get(i)); 
					} 
				} 
				 
				 
				if(filter.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},"Empty output collection."); 
					 
					return false; 
				} 
				 
					gl.tx_k(new Object(){},gl.sf("%s...%s...%s...size...%05d", 
							gl.S_OK,
							"Accept filter to date range",
							range.toString(),
							filter.size())); 
				
					return true; 
				 
	} 
	
	public static List<Bar> get_bars_by_date_range(List<Bar> items,Range range) 
	{ 
	 
				// Filter by date. 
				List<Bar> filter = new ArrayList<Bar> ();
		
				if(items.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},"Empty input collection"); 
					 
					return filter; 
				} 
				 
				
				for(int i=0; i < items.size();i++) 
				{ 
					if(DateUtil.isa_date_is_in_range(items.get(i).getDate(),range)) 
					{ 
						filter.add(items.get(i)); 
					} 
				} 
				 
				// Re - id.
				
				/*
				int [] counter = { 1 } ;
				
				filter.forEach(a->{
					
					a.setId(counter[0]);
					
					counter[0]++;
					
				});
				
				*/
				
				 
				if(filter.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},gl.sf("No bars for range...%s",range.toString())); 
					 
					return filter; 
				} 
				 

					//gl.tx_k(new Object(){},gl.sf("Extract...%05d...bars for the range...%s",filter.size(),range.toString())); 
				 
					return filter; 
				 
	} 
	
	 
	public static double get_min_low(List<Bar> items) 
	{ 
			return items.stream().mapToDouble(a->a.getL()).min().getAsDouble(); 
	} 
	 
	public static double get_max_high(List<Bar> items) 
	{ 
			return items.stream().mapToDouble(a->a.getH()).max().getAsDouble(); 
	} 
	 
	 
	 
	public static Date get_min_date(List<Bar> items) 
	{ 
			return Collections.min(items,Comparator.comparing(a->a.getDate())).getDate();	 
	} 
	 
	public static Date get_max_date(List<Bar> items) 
	{ 
			return Collections.max(items,Comparator.comparing(a->a.getDate())).getDate(); 
		 
	} 
	 
	 
	public  synchronized static List<Bar> get_by_type_and_date_range(List<Bar> list,Range range,int type) 
	{ 

		if(type != CandleUtil.indexOf("NONE"))
		{
		 return list.stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range)  &&
						b.getType() == type
					))).collect(Collectors.toList()); 
		}
		
		 return list.stream().filter( 
					(b-> ( 
							DateUtil.isa_date_is_in_range(b.getDate(),range) 
						))).collect(Collectors.toList()); 
			
		
	} 
	
	 
	public synchronized  List<Bar> get_list_by_date_range(List<Bar> list,Range range) 
	{ 

		 list.stream().filter( 
				(b-> ( 
						DateUtil.isa_date_is_in_range(b.getDate(),range) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public double get_by_type(int type) 
	{ 
		double d_result = this.getO(); 
		 
		if (type == Bar.O) 
			return this.getO(); 
		else if (type == Bar.H) 
				return this.getH(); 
		else	if (type == Bar.L) 
					return this.getL(); 
		else		if (type == Bar.C) 
						return this.getC(); 
	 
		return d_result; 
					 
	} 
	 

	
	public static boolean get_state_bars(String source,Map<String,List<Bar>> bars,List<Bar> state_bars,String [] series ) 
	{ 
		 
		String msg = "Load state bars"; 
				 
		if(!load_bars(source,bars,series)) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...%s", gl.S_ERROR,msg)); 
			 
			return false; 
		} 
		 
		
			gl.tracex(new Object() {}, String.format("%s...%s...series...%s...size...%d...range...%s",
					gl.S_OK, 
					msg, 
					series[gl.E_OK], 
					bars.get(series[gl.E_OK]).size(), 
					Bar.get_date_range(bars.get(series[gl.E_OK])).toString())); 
			
			// Average. 
			// Arrays. 
			 
			int CAPACITY =32; 
						 
			byte [] sta = IntArrayUtil.init_byte_array(CAPACITY); 
			 
			gl.tracex(new Object() {}, String.format("%s...%s...%s", gl.S_OK,msg,gl.S_TRY)); 
			 
			long start = System.nanoTime(); 
			 
			bars.get(series[gl.E_OK]).forEach(b-> 
			{ 
				 
					for(int i=TMa.M3; i <= TMa.M200;i++) 
					{ 
						for(int o=Bar.O;o <= Bar.C;o++ ) 
						{ 
						 
						// Get averages. 
						 
						double 	m_avg = MaUtil.get_avg(b.getId(),bars.get(series[gl.E_OK]),gl.ma_period[i],o); 
						 
						double 	m_bar_value = b.get_by_type(o); 
								 
						int	   	m_bit = gl.decode(m_bar_value,m_avg); 
						 
						//Last edition.
						int		m_bit_index_r = 31 - ((i*4)+o);//31 - ((((i+1)*4)-o)-1); 
						 
						// Previous edition.
						int		m_bit_index = 31 - ((((i+1)*4)-o)-1); 
						
						gl.tx_k(new Object(){},gl.sf("Previous...[%2d]...Current...[%2d]",m_bit_index,m_bit_index_r));
						
						if(m_bit == gl.E_OK) 
						sta[m_bit_index] = gl.E_OK; 
						else 
						sta[m_bit_index] = gl.E_EMPTY; 
				 
					} // O 
												 
				} // I 
					 
						//b.setState(sta); 
					 
						state_bars.add(b); 
										 
			}); // bars		 
					
			
			boolean bl_result = (state_bars.size() != gl.E_EMPTY); 

			
			if(bl_result) 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d...%d ms.", gl.S_OK,msg,state_bars.size(),(System.nanoTime() - start)/1000000)); 
			else 
				gl.tracex(new Object() {}, String.format("%s...%s...size...%d", gl.S_ERROR,msg)); 
				 
			return bl_result; 
						 
	} 
	
	public static boolean load_bars(String source_file,Map<String,List<Bar>> map,String[] series) 
	{ 
			String 		msg = "BARS load"; 

			List<Bar> 	bar_items = new ArrayList<Bar>(); 

			long 		start = System.currentTimeMillis(); 

		 
		if (!Bar.readFromFile(source_file,bar_items,series)) {
			
			gl.tx_k(new Object() {}, gl.sf("%s...%s", gl.S_ERROR, msg)); 

			return false; 
		} 

			long 		end = System.currentTimeMillis(); 

			int 		SERIES = gl.E_OK; 
		 
			String 		sr = series[SERIES]; 

		if (map.containsKey(sr)) 
			map.replace(sr, bar_items); 
		else 
			map.put(sr, bar_items); 

			int 		size = map.get(sr).size(); 

			boolean 	bl_result = (size == bar_items.size()); 

		if (bl_result) 
		{
			gl.tx_k(new Object() {}, gl.sf("%s...%s...series...%s...count...%d...duration...%d ms.",
					gl.S_OK, msg, sr.toUpperCase(), 
					size, (end - start) / 1000000));
		}
		else
		{
					gl.tx_e(new Object() {},gl.sf("%s...%s", gl.S_ERROR, msg)); 
		}


					return bl_result; 

	} 
	
	
	 
	public static boolean convert_to_map (List<Bar> bar_list,Map<Date,Bar> bar_map ) 
	{ 
		if(bar_list == null || bar_map == null) 
			return false; 
		 
		bar_list.forEach(a-> 
		{ 
			bar_map.put(a.getDate(),a); 
		}); 
		 
		return (bar_list.size() == bar_map.size()); 
	} 
	 
	public static Bar get_instance(double o,double h,double l,double c) { 
		
		return new Bar(o,h,l,c); 
			 
	}
	
	
	 
	public static void main(String[] args) { 
		 
		//String file = "c:/bin/eclipse/wsp/Organizer/data/quotes/usdchf.txt"; 
		 
		String file = "e:/bin/eclipse/wsp/Organizer/data/quotes/set/usdchf.txt"; 
		 
		 //test_map_based(file); 
		 
		//test_list_based(file); 
		 
		//test_up_bar(file); 
		 
	} 

} 
// Revision : 10.09.2018 12:49:13 
