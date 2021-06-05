 
package ap.btn; 


import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.stream.*; 
import ap.config.TConfig;
import ap.frame.TFrame; 
import ap.global.gl; 
import ap.prop.BaseProperty; 
import ap.prop.SDimension;
import ap.shape.Ru;
import ap.utils.CU;
import ap.utils.IntArrayUtil; 
import ap.utils.MapUtils; 

public class TChart extends TPanel { 
	 
	
	private static final long serialVersionUID = 1L;

	public static final String HEADER_PREFIX = "#@#"; 
	 
	public static int MIN_WIDTH  			= 7; 
	 
	public static int MIN_HEIGHT 			= 7; 
	 
	public static int START_OFFSET_X 		= TChart.MIN_WIDTH; 
	 
	public static int END_OFFSET_X 	 		= TChart.MIN_WIDTH; 
	 
	public static int X_SPREAD 				= TChart.MIN_WIDTH/2; 
	 
	public static int START_OFFSET_Y 		= 32; 
	 
	public static int END_OFFSET_Y 			= 32; 
	 
	public static double SCALE_FACTOR 		= 1.0; 
	 
	public static double BASE_SCREEN_HEIGHT = 480; 
		 
	public static final String HIGH 		= "HIGH_LINE"; 
	 
	public static final String LOW 			= "LOW_LINE"; 
	 
	public static final String MIDDLE 		= "MIDDLE_LINE"; 
		 
	public static final String MIDDLE_HIGH 	= "MIDDLE_HIGH_LINE"; 
	 
	public static final String MIDDLE_LOW 	= "MIDDLE_LOW_LINE"; 
	 
	public static final String LEFT  		= "LEFT_LINE"; 
	 
	public static final String RIGHT 		= "RIGHT_LINE"; 
	 
	public static final int PAGE_SIZE 		= 15; 
	 
	public static final int EUR_MULTIPLIER 	= 10000; 
	
	public static final int JPY 			= 1000; 
	
	 
	private boolean 						bl_order_production = false; 
	 
	public static final int 				DIRECTION_NONE 		= gl.E_ERROR; 
	 
	public static final int 				DIRECTION_FORWARD  	= gl.E_OK; 
	 
	public static final int 				DIRECTION_BACKWARD 	= gl.E_EMPTY; 

	 
	// Primary series index. 
	
	private Map<String,Integer> 			series_index 	= new HashMap<String,Integer>(); 
	 
	//Collections. 
	
	private Map<String,List<Bar>> 			bars 			= new HashMap<String,List<Bar>>(); 
	 
	private Map<String,List<Bar>> 			show_bars 		= new HashMap<String,List<Bar>>(); 
	 
	private Map<String,List<TMa>> 			ma 				= new HashMap<String,List<TMa>>(); 
	 
	private Map<String,List<TMaPoint>> 		ma_points 		= new HashMap<String,List<TMaPoint>>(); 
	 
	
	// Switch map paint. 
	private Map<String,Boolean> 			paint_trigger 		= new HashMap<String,Boolean>();	 
	 
	
	// XY-Metric 
	private Map<String,List<TGridRect>>		xy_metric 		= new HashMap<String,List<TGridRect>>(); 
	 
	
	// Index range. 
	private Map<String,TIndexRange>     	index_range 	= new HashMap<String,TIndexRange>(); 
	 
	
	// HighLow . 
	private Map<String,THighLowRange> 	    high_low_range 	= new HashMap<String,THighLowRange> (); 
	 
	
	// Orders map. 
	private Map<String,List<Order>> 		orders 			= new HashMap<String,List<Order>> (); 
	 
	
	// Frame base. 
	private TFrameBase 						frame_base ; 
	 
	
	// Start dimension. 
	private Dimension 						start_dimension = new Dimension(0,0); 
	 
	
	// Last series. 
	private  String series; 
	 
	
	// Capacity. 
	private int 							capacity_on_the_form 			= gl.E_EMPTY; 
	 
	
	// Startup capacity. 
	private int 							startup_capacity_on_the_form 	= gl.E_EMPTY; 
	 
	
	//Direction metric. 
	private Dimension 						direction_metric 				= new Dimension(gl.E_ERROR,gl.E_ERROR); 
	 
	// Direction type. 
	private  int 							direction_type 					= TChart.DIRECTION_NONE; 
	 
	
	

	public int getDirection_type() { 
		return direction_type; 
	} 

	public void setDirection_type(int direction_type) { 
		this.direction_type = direction_type; 
	} 

	public Dimension getDirection_metric() { 
		return direction_metric; 
	} 

	public void setDirection_metric(Dimension direction_metric) { 
		this.direction_metric = direction_metric; 
		 
		 
		if(this.getDirection_metric().width > this.getDirection_metric().height) 
			this.setDirection_type(TChart.DIRECTION_BACKWARD); 
		else if(this.getDirection_metric().width < this.getDirection_metric().height) 
			this.setDirection_type(TChart.DIRECTION_FORWARD); 
		else if(this.getDirection_metric().width == this.getDirection_metric().height) 
			this.setDirection_type(TChart.DIRECTION_NONE); 
		 
		 
	} 

	public Map<String, List<Order>> getOrders() { 
		return orders; 
	} 


	public void setOrders(Map<String, List<Order>> orders) { 
		this.orders = orders; 
	} 


	public boolean isBl_order_production() { 
		return bl_order_production; 
	} 


	public void setBl_order_production(boolean bl_order_production) { 
		this.bl_order_production = bl_order_production; 
	} 


	public Map<String, Integer> getSeries_index() { 
		return series_index; 
	} 

	 
	public Map<String, Boolean> getPaint_map() { 
		return paint_trigger; 
	} 


	public void setPaint_map(Map<String, Boolean> paint_map) { 
		this.paint_trigger = paint_map; 
	} 


	public void setSeries_index(Map<String, Integer> series_index) { 
		this.series_index = series_index; 
	} 

	public int getStartup_capacity_on_the_form() { 
		return startup_capacity_on_the_form; 
	} 

	public void setStartup_capacity_on_the_form(int startup_capacity_on_the_form) { 
		this.startup_capacity_on_the_form = startup_capacity_on_the_form; 
	} 

	 	 
	public int getCapacity_on_the_form() { 
		return capacity_on_the_form; 
	} 

	public void setCapacity_on_the_form(int capacity_on_the_form) { 
		this.capacity_on_the_form = capacity_on_the_form; 
	} 

	public String getSeries() { 
		return series; 
	} 

	public void setSeries(String series) { 
		this.series = series; 
	} 

	public Dimension getStart_dimension() { 
		return start_dimension; 
	} 

	public void setStart_dimension(Dimension start_dimension) { 
		this.start_dimension = start_dimension; 
	} 
	 
	 
	public Map<String, List<TGridRect>> getXy_metric() { 
		return xy_metric; 
	} 

	public void setXy_metric(Map<String, List<TGridRect>> xy_metric) { 
		this.xy_metric = xy_metric; 
	} 

	public TFrameBase getFrame_base() { 
		return frame_base; 
	} 

	public void setFrame_base(TFrameBase frame_base) { 
		this.frame_base = frame_base; 
	} 

	public Map<String, THighLowRange> getHigh_low_range() { 
		return high_low_range; 
	} 

	public void setHigh_low_range(Map<String, THighLowRange> high_low_range) { 
		this.high_low_range = high_low_range; 
	} 

	public Map<String,TIndexRange> getIndex_range() { 
		return index_range; 
	} 

	public void setIndex_range(Map<String,TIndexRange> index_range) { 
		this.index_range = index_range; 
	} 

	public Map<String, List<Bar>> getBars() { 
		return bars; 
	} 

	public void setBars(Map<String, List<Bar>> bars) { 
		this.bars = bars; 
	} 

	public Map<String, List<TMa>> getMa() { 
		return ma; 
	} 

	public void setMa(Map<String, List<TMa>> ma) { 
		this.ma = ma; 
	} 

	public Map<String, List<TMaPoint>> getMa_points() { 
		return ma_points; 
	} 

	public void setMa_points(Map<String, List<TMaPoint>> ma_points) { 
		this.ma_points = ma_points; 
	} 
	 
	 

	public Map<String, List<Bar>> getShow_bars() { 
		return show_bars; 
	} 

	public void setShow_bars(Map<String, List<Bar>> show_bars) { 
		this.show_bars = show_bars; 
	} 

	public TChart() { 
		 
	} 

	public TChart(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TChart(Rectangle rect) { 
		super(rect); 
	}	 

	public TChart(Rectangle rect, int index) { 
		super(rect, index); 
	} 

	public TChart(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TChart(String payload) { 
		super(payload); 
		 
	} 

	public TChart(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	 
	 
	// TODO STEP1.
	
	public boolean load_quotes_suite(TChart owner) { 
	
		String msg = "Load quotes suite.";
				
		List<String> list = new ArrayList<String>(); 
		 
		if(!owner.getManager().openFiles(list)) 
		{ 
			gl.tx_e(new Object() {} , gl.sf("%s..Bad input params.",msg) );
			
			return false; 
		} 
			 
		List<Boolean> state = new ArrayList<Boolean>(); 
				 
		
		// Отрисовка БАРов.
		
		list.forEach(a->{ 
			 
			state.add(load_bars(owner,a)); 

			owner.getSeries_index().put(owner.getSeries(),owner.getSeries_index().size()); 
		 
			state.add(load_ma_series(owner) && update_paint_map() && refresh_chart_series(owner)); 
			 
		}); 
		 
			// TODO STEP2.
		
			// Отрисовка остальных графических объектов. 
		
			this.getChart_helper().init(owner); 
			 
			return  (!state.contains(false)); 
			 
	} 
			 
	public boolean load_bars(TChart owner,String source_file) 
	{ 
		String msg = "BARS load"; 

		List<Bar> bar_items = new ArrayList<Bar>(); 

		long start = System.currentTimeMillis(); 

		 
		if (!Bar.readFromFile(owner,source_file, bar_items)) { 
			gl.tracex(new Object() { 
			}, String.format("%s...%s", gl.S_ERROR, msg)); 

			return false; 
		} 

		long end = System.currentTimeMillis(); 

		String sr = owner.getSeries(); 

		if (this.getBars().containsKey(sr)) 
			this.getBars().replace(sr, bar_items); 
		else 
			this.getBars().put(sr, bar_items); 

		int size = this.getBars().get(sr).size(); 

		boolean bl_result = (size == bar_items.size()); 

		if (bl_result) 
			gl.tracex(new Object() { 
			}, String.format("%s...%s...series...%s...count...%d...duration...%d ms.", gl.S_OK, msg, sr.toUpperCase(), 
					size, (end - start) / 1000000)); 
		else 
			gl.tracex(new Object() { 
			}, String.format("%s...%s", gl.S_ERROR, msg)); 

		return bl_result; 

	} 

	 

	public static boolean load_ma_series(TChart owner) 
	{ 		 
		String series = owner.getSeries(); 
		 	
		if (!gl.tx(new Object(){},
			(owner.getBars().get(series).size() != gl.E_EMPTY),
			 gl.sf("Нет БАРов для создания MAШек")))
			return false;	
			 
			List<TMa> 	ma_list = Bar.get_ma_old(owner.getBars().get(series)); 
		
			owner.getMa().put(series,ma_list); 
			 	
			return  gl.tx(new Object(){},
				    (owner.getBars().get(series).size() == owner.getMa().get(series).size()),
					gl.sf("Обновление МАШек.")); 
				 
	} 

	
	@Override 
	public String toStringForToolTip() 
	{ 
		String msg = ""; 
		 
		if(this.getDate_range() != null) 
		msg = String.format("Date range is %s",this.getDate_range().toString()); 
		else 
		msg = String.format( 
				"pid=%d,id=%d,type=%s,function=%s", 
				this.getPid(), 
				this.getId(), 
				this.getType(), 
				this.getFunction() 
				); 
				 
			 
		 
		return msg; 
		 
	} 
	 
	 
	public static void startUp() { 
		 
		TConfig.start(TChart.getInstances(10)); 

	} 
	 
	public static TChart getInstance(Rectangle rect) { 

		return new TChart(rect, gl.getRandomColor()); 

	} 
	 
	 
	 
	public void paintLines(Graphics g) 
	{ 

		Graphics2D g2 = (Graphics2D) g; 
	 	 
		if(this.getDate_range() != null) 
		{ 
			if(this.getPaint_map().containsKey("switch_tick_paint")) 
			{ 
			   if(this.getPaint_map().get("switch_tick_paint")) 
				   this.drawOHLC(g2); 
			} 
			 
			// Draw caption. 
			this.updateTextSuite(g2); 
			 
			if(this.getPaint_map().containsKey("switch_metric_paint")) 
			{ 
			   if(this.getPaint_map().get("switch_metric_paint")) 
				   this.getChart_helper().drawMetrics(g2); 
			} 
			 
			if(this.getPaint_map().containsKey("switch_ma_paint")) 
			{ 
			   if(this.getPaint_map().get("switch_ma_paint")) 
				   this.getChart_helper().drawMas(g2); 
			} 
			 
			if(this.getPaint_map().containsKey("switch_pivot_paint")) 
			{ 
			   if(this.getPaint_map().get("switch_pivot_paint")) 
				   this.getChart_helper().drawPivots(g2); 
			} 
		} 
	} 

	public boolean refresh_chart_series(TChart owner) 
	{ 
		try 
		{ 
			 
		 if(this.getBars() == null && this.getBars().size() == gl.E_EMPTY) 
			  return false; 
		 
		owner.setTicksCapacity(); 
	 
		List<Boolean>  state = new ArrayList<Boolean>(); 
		 
		this.getBars().entrySet() 
					.stream() 
					.map(Map.Entry::getKey) 
					.collect(Collectors.toList()).forEach(a -> { 

					state.add(this.refresh_chart(owner, a)); 

			}); 
	
				TFrame.HDC.repaint(); 
				 
				return ( !state.contains(false)); 
			 
			} 
			catch(Exception e) 
			{ 
				return false; 
			} 
		 
	} 
	 

	public boolean set_view_frame (TChart owner,String series) 
	{ 
		 
		if ( !
				gl.tx(new Object(){},owner != null,
				gl.sf("Формирование фрейма.Нет владельца.")) 
			)
			return false;


		try 
		{ 
				 
			double low_y 	= owner.getChart_helper().get_by_name("COMMON","COMMON",TChart.LOW).getStart().getY(); 
		 
			double high_y 	= owner.getChart_helper().get_by_name("COMMON","COMMON",TChart.HIGH).getStart().getY(); 
				 
			double y_zoom 	=	(low_y - high_y ) / owner.getHigh_low_range().get(series).getGap(); 
		 
			 
			double w 		= owner.getBounds().width; 
			 
			double s 		= owner.getStart_dimension().width; 
			 
			 
			double x_zoom 	= w /s; 
					 
			TFrameBase fb 	= new TFrameBase((int)high_y,(int)low_y,x_zoom,y_zoom); 
			 
			owner.setFrame_base(fb); 
			 
			return gl.tx(new Object(){},true,gl.sf("Параметры фрейма...%s", 
												owner.getFrame_base().toString()		 
												)); 
			 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
	 
		 
	} 
	 
	 
	public boolean set_items_metric(TChart owner,String series) 
	{ 
		 
		try 
		 
		{ 
		
		
			int  				STYLE 		= MapUtils.findValueByKey(owner.getSeries_index(),series); 
			 
			List<TGridRect>  	metric_list = new ArrayList<TGridRect>(); 
			 
			int 		[] 		index 		= {0}; 
			 
		
		owner.getShow_bars().get(series).forEach(a-> 
		{ 
			double dx =  (TChart.START_OFFSET_X  + 
					 ( index[0] * (TChart.MIN_WIDTH+TChart.X_SPREAD))) * owner.getFrame_base().getX_zoom() ; 
			 
			int x =  (int)dx; 
						 
			double y =  owner.getFrame_base().getHigh() + 
					    ((owner.getHigh_low_range().get(series).getHigh() - a.getH()) * TChart.EUR_MULTIPLIER)* 
					    owner.getFrame_base().getY_zoom(); 

			int y_base = (int)y; 
			 

			double high 	= ((a.getHighShadow() * TChart.EUR_MULTIPLIER)*owner.getFrame_base().getY_zoom()); 
			 
			double body 	= ((a.getBody() * TChart.EUR_MULTIPLIER)*owner.getFrame_base().getY_zoom()); 
			 
			double low  	= ((a.getLowShadow()* TChart.EUR_MULTIPLIER)*owner.getFrame_base().getY_zoom()); 
			 
			double d_width 	= (TChart.MIN_WIDTH * owner.getFrame_base().getX_zoom()); 
			 
			int i_width = (int)d_width; 
					 
			if(i_width == gl.E_EMPTY) 
			{ 
				i_width = gl.E_OK; 
			} 
			 
			if(body <= gl.E_OK) 
			   body = gl.E_OK; 
			
			 
			Rectangle high_rect = null;			 
			 
			Rectangle body_rect = null; 
			 
			Rectangle low_rect 	= null; 
			
			 
			int INDEX = a.getSign(); 
			 
			if(INDEX == gl.E_ERROR) 
				INDEX = gl.E_EMPTY; 
			 
			 
			if (Bar.DRAW_TYPE == gl.CANDLE) 
			{ 
				high_rect = new Rectangle((x+i_width/2),y_base,gl.E_OK,(int)high);			 
				 
				body_rect = new Rectangle(x,y_base+(int)high,i_width,(int)body); 
				 
				low_rect = new Rectangle((x+i_width/2),y_base+(int)high+(int)body,gl.E_OK,(int)low); 

				 
			} else if (Bar.DRAW_TYPE == gl.BAR) 
			{ 
				 
				int bar_high = (int)(high+body+low); 
				 
				if(INDEX==gl.E_EMPTY) 
					high_rect = new Rectangle(x,y_base+(int)high,i_width/2,2); 
				else 
					high_rect = new Rectangle(x,y_base+(int)high+(int)body,i_width/2,2); 
					 
				 
				body_rect = new Rectangle((x+i_width/2),y_base,2,bar_high); 
				 
				 
				if(INDEX==gl.E_EMPTY) 
					low_rect = new Rectangle((x+i_width/2)+2,y_base+(int)high+(int)body,i_width/2,2); 
				else 
					low_rect = new Rectangle((x+i_width/2)+2,y_base+(int)high,i_width/2,2); 
					 
				 
				 
			} 
			 
			   // TODO Установка аттрибутов БАРа.
			 
				TGridRect gr_high = TGridRect.get_instance(high_rect, 
						series, 
						"HIGH", 
						String.format("%d",a.getHighShadowM()), 
						gl.bar_styles[STYLE][INDEX]); 
			
				TGridRect gr_body = TGridRect.get_instance(body_rect, 
						series, 
						"BODY", 
						// ToolTip info.
						String.format("%s %d [%d]",
								a.toStringDateOHLC(),
								a.getBodyM(),a.getType()), 
						gl.bar_styles[STYLE][INDEX]); 

				int  	enable_type = gl.E_EMPTY;
				
				if(this.getPaint_map().containsKey("switch_type_paint")) 
				{ 
						enable_type = this.getPaint_map().get("switch_type_paint") ? 1 : 0;
				}
						
				TGridRect gr_low = TGridRect.get_instance(low_rect, 
					series, 
					"LOW", 
					String.format("%d",a.getLowShadowM()),
					gl.bar_styles[STYLE][INDEX]); 

					
						metric_list.add(gr_high); 
						 
						metric_list.add(gr_body); 
						 
						metric_list.add(gr_low); 
					
					 
						index[0]++; 
			 
		}); 
		 
					owner.getXy_metric().put(series,metric_list); 
			
					return gl.tx(new Object(){},
							( owner.getXy_metric().get(series).size()/3 == owner.getShow_bars().get(series).size()),
					gl.sf("Установка аттрибутов БАРов."));
			} 
		
		catch(Exception e) 
		{ 
					e.printStackTrace();
			
					return false; 
		} 
		 
		 
	} 
	 


	 
	 

	public boolean refresh_chart(TChart owner,String series) 
	{ 
		 
		return ( 
				 
				set_range_index(owner,series) 		&& 
				 
				slice_show_bars(owner,series)  		&& 
				 
				update_high_low(owner,series) 	 	&& 
				 
				set_view_frame(owner,series) 		&& 
				 
				set_items_metric(owner,series)  	&& 
				 
				update_ma_points(owner,series) 		&& 
				 
				update_pivot_points(owner,series) 
				 
				); 
		 
	} 
	 
	public boolean update_high_low(TChart owner,String series) 
	{ 
		
	     if (!gl.tx(new Object(){},(owner.getShow_bars().get(series).size() != gl.E_EMPTY),
	    		 gl.sf("Проверка готовности БАРонов на входе.")))
			 return false;
		 
		 	THighLowRange hl = new THighLowRange( 
		 			Bar.get_max_high(owner.getShow_bars().get(series)), 
		 			Bar.get_min_low(owner.getShow_bars().get(series)) 
		 			); 
			 
			if(owner.getHigh_low_range().containsKey(series)) 
				owner.getHigh_low_range().replace(series,hl); 
			else 
				owner.getHigh_low_range().put(series,hl); 
			 
			return gl.tx(new Object(){},(owner.getHigh_low_range().get(series)  != null),gl.sf("Обновление High&Low"));
			 
	} 

	 
	
	public boolean set_range_index(TChart owner,String series) 
	{ 
	 
			// Set IndexRange. 
		 
			int primary_index = gl.E_EMPTY; 
			 
			// Get Base. 
			if(owner.getIndex_range().get(series) != null) 
				primary_index = owner.getIndex_range().get(series).getOffset(); 
			 
			// Get Offset. 
			int second_index = primary_index +  (owner.getCapacity_on_the_form()-1); 
			
			// Under construction.
			
			// Control of max index. 
			int valid_max_index = owner.getBars().get(series).size();//(owner.getBars().get(series).size() -1); 
			 
			// Get last_right_index. 
			int last_right_index = TIndexRange.last_right_index; 
			 
			// Set index range. 
			TIndexRange tir = new TIndexRange(primary_index,second_index,valid_max_index); 
			 
			// Set direction metric. 
			// Update direction type too. 
			 
			this.setDirection_metric(new Dimension(last_right_index,TIndexRange.last_right_index)); 
		 
			if(this.getIndex_range().containsKey(series)) 
				this.getIndex_range().replace(series,tir); 
			else 
				this.getIndex_range().put(series, tir); 
				 
			gl.tx_k(new Object(){},String.format("SET RANGE INDEX...[%s]...max...%d...direction metric...%s...direction type...%d", 
			this.getIndex_range().get(series).toString(), 
			valid_max_index, 
			SDimension.toString(this.getDirection_metric()), 
			this.getDirection_type() 
			)); 
			 
			return (this.getIndex_range().get(series) != null); 
			 
	} 

	 	 
	public boolean slice_show_bars(TChart owner,String series) { 
		 
	    String msg = "SLICE BARS"; 
			 
	    // Extract bars subset. 
	    
		List<Bar> bars = owner.getBars().get(series).subList( 
				owner.getIndex_range().get(series).getStart(), 
				owner.getIndex_range().get(series).getEnd() 
				); 
		 
		 
		// Update show bars. 
		
		if( owner.getShow_bars().containsKey(series)) 
			owner.getShow_bars().replace(series,bars); 
		else 
			owner.getShow_bars().put(series,bars); 
		 
		// Set date range of the show_bars. 
		// Take of first and last items from bar list. 
		 
		TDateRange date_range = new TDateRange( 
				Bar.get_min_date(owner.getShow_bars().get(series)), 
				Bar.get_max_date(owner.getShow_bars().get(series)) 
				); 

		owner.setDate_range(date_range); 
		 
		// Update subscriber(s) on change date range event. 
		TPanel range_panel = this.getManager().getCollector().get_by_pid_and_function(this.getId(),"date_range_text"); 
		 
		if(range_panel != null) 
		   range_panel.setText(gl.sf("%s",date_range.toString())); 
				 
		return gl.tx(new Object(){},(owner.getShow_bars().get(series).size() == bars.size()),gl.sf("Фрейм БАРонов."));

		 
} 

	 
	 
	 
	 
	@Override 
	public void paintComponent(Graphics g) { 

		super.paintComponent(g); 
		 
		paintLines(g); 
	 
	} 
	 
	public void drawOHLC(Graphics2D g2) 
	{ 
		 
		this.getXy_metric().entrySet() 
		.stream() 
		.map(Map.Entry::getKey) 
		.collect(Collectors.toList()).forEach( 
				 
		sr -> { 
				 
			this.getXy_metric().get(sr).forEach(a->{ 
				 
			a.draw(g2); 
			 
			}); 
			 
		}); 
		 
	} 
	 
		 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getChart_helper().init(this); 
						 
	} 
	 
	public int setTicksCapacity() 
	{ 

		for(int i=0;i<3;i++) 
		gl.tracex(new Object(){},gl.sf("%s...[->.]}",gl.S_OK)); 
		 
		String msg = "TICKS CAPACITY"; 
		 
		if(this.getSeries() == null) 
		{ 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",gl.S_ERROR,msg,"not ready")); 
			 
			return gl.E_ERROR; 
		} 

		 
		int new_capacity_value = ( (this.getBounds().width - (TChart.START_OFFSET_X + TChart.END_OFFSET_X))/ (TChart.MIN_WIDTH +TChart.X_SPREAD)); 
		 
		// Check max capacity value. 
		 
		int size = this.getBars().get(this.getSeries()).size() ; 
		 
		if(size < new_capacity_value) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...%s...from...%d...to...%d", 
					gl.S_OK, 
					msg, 
					"SHIFT DOWN capacity", 
					new_capacity_value, 
					this.getBars().get(this.getSeries()).size() 
					)); 
			 
			new_capacity_value = size; 
		} 
		 
		this.setCapacity_on_the_form(new_capacity_value); 
	 	 
		this.setStart_dimension(this.getSize());	 

		gl.tracex(new Object(){},String.format("%s...%s...CAPACITY...%d...%d",gl.S_OK,msg,this.getCapacity_on_the_form(),new_capacity_value)); 
				 
		return this.getCapacity_on_the_form(); 
			 
	} 

	public boolean update_pivot_points(TChart owner,String series) 
	{ 
		 
		String msg = "PIVOTS UPDATE"; 
		 
		List<Bar> items = owner.getShow_bars().get(series); 
		 
		if(items.size() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_ERROR,msg,"BARS empty.")); 
			 
			return false; 
		} 
		 
		 
		if(owner.getXy_metric().get(series).size() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_ERROR,msg,"Empty METRIC.")); 
			 
			return false; 
		} 
		 
		List<TGridRect> metrics = owner.getXy_metric().get(series).stream().filter( 
				(b-> ( 
						b.getLine().getType().trim().equalsIgnoreCase("BODY") 
					))).collect(Collectors.toList()); 

		 
		double max_high= owner.getHigh_low_range().get(series).getHigh(); 
		 
		double base_line_y = owner.getFrame_base().getHigh(); 
		 
		double y_zoom = owner.getFrame_base().getY_zoom(); 
		 
		 
		List<TPivotSuite> pivots =  new ArrayList<TPivotSuite>(); 
		 
		Bar [] bar = {null}; 
		 
		int[] index = {0}; 
		 
		int[] x = {0}; 
		 
		items.forEach(a-> 
		{ 
				Rectangle rect = metrics.get(index[0]).getBounds(); 
			 
				Bar work_bar = bar[gl.E_EMPTY]; 
			 
				if(work_bar != null) 
				{ 

					x[gl.E_EMPTY] = rect.x; 
					 
					TPivotSuite add = TPivotSuite.get_instance(work_bar, x[gl.E_EMPTY], max_high, base_line_y,owner.getBounds().y,y_zoom); 
					 
					pivots.add(add); 
					 
				} 
				 
					bar[gl.E_EMPTY] = a; 
					 
					index[0]++; 
					 
		}); 
	 
				// Last bar processing. 
				x[gl.E_EMPTY] +=  TChart.MIN_WIDTH *2; 

				TPivotSuite add = TPivotSuite.get_instance(bar[gl.E_EMPTY], x[gl.E_EMPTY],max_high, base_line_y,owner.getBounds().y,y_zoom); 

				pivots.add(add); 
				 
		 		if( owner.getPivots().containsKey(series)) 
		 			owner.getPivots().replace(series,pivots); 
				else 
					owner.getPivots().put(series,pivots); 
		 		 
		 		boolean bl_result = (owner.getPivots().get(series).size() == items.size()); 
				 
				if(bl_result) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_OK,msg)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
				} 
				 
				return bl_result; 
				 
	} 
	 
	// TODO Обновление компонент после смены типа графика.
	
	public boolean updateComponents() 
	{ 
	  if( 
			  this.getBars()  == null 				|| 
			  this.getBars().size() == gl.E_EMPTY  	|| 
			  this.getSeries() == null 
		) 
	  {
		  return false; 
	  }
		  
	    return gl.tx(new Object(){},this.refresh_chart_series(this),gl.sf("Обновление компонентов.")); 
	   		 
	} 
	 
	
	// TODO STEP3 Чтение состояний типов графиков и др.
		 
	public boolean update_paint_map() 
	{ 
		 
		if(this.getManager() == null || this.getManager().getCollector() == null) 
			return false; 
		 
		List<TPanel> sws = this .getManager().getCollector().get_list_by_pid_and_action_starts_with(this.getId(),"switch"); 
		 
		
		// Читаем состояния в карту, для индексированного доступа.
		 
		for(int i=0; i< sws.size();i++) 
		{ 
		 
			 
			String 		action = sws.get(i).getAction().trim(); 
			 
			boolean 	bl_state = ((TPushBtn)sws.get(i)).getState() == 1 ? true : false; 
			
			this.getPaint_map().put(action,bl_state); 
			 
		} // for. 
		 
	
		// Оптимизация.
	
			Bar.DRAW_TYPE = this.getPaint_map().get("switch_bar_paint") ? gl.BAR : gl.CANDLE; 
			   
			   //this.getPaint_map().get("switch_candle_paint") ? gl.CANDLE : gl.LINE; 
		
		
			this.getChart_helper().init(this); 
		 
		 
			return true; 
	} 
	 
	 	 
	@Override 
	public void mouseDragged(MouseEvent me) { 
		super.mouseDragged(me); 
		 
		updateComponents(); 
	 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent me) { 
		 
		super.mouseMoved(me); 
		 
		if (this.getChart_helper().getLines().size() == gl.E_EMPTY) 
			return; 
		 
		// Create target group of lines. 
		
		List<TGridLine> gli_all = new ArrayList<TGridLine>(); 
		 
		List<String> 	series = this.getChart_helper().get_series("MA"); 
		
		 
		series.forEach(sr->{ 
			 
			List<TGridLine> gli = this.getChart_helper().getLines().get("MA").get(sr); 
		 
			List<TGridLine> pli = this.getChart_helper().getLines().get("PIVOT").get(sr); 
			 
			
			gli_all.addAll(gli); 
			
			
			if(pli != null) 
			{ 
				gli_all.addAll(pli); 
				 
				//gl.smn("Pivot list size : " + pli.size()); 
			} 
			 
		}); 
		 
		gli_all.addAll(this.getChart_helper().getLines().get("COMMON").get("COMMON")); 
		 
		 
		// Catch. 
		
		gli_all.forEach(li-> { 
			 
		Point p = me.getPoint(); 
			 
		Rectangle target = new Rectangle(li.getStart().x,li.getStart().y-TPanel.MW,(li.getEnd().x - li.getStart().x),TPanel.MW*2); 
		 
				if(target.contains(p)) 
				{ 
					this.setToolTipText(li.getName()); 
				} 
		}); 
		 
		Point pp = me.getPoint(); 
		 
		this.getXy_metric().entrySet() 
		.stream() 
		.map(Map.Entry::getKey) 
		.collect(Collectors.toList()).forEach(sr -> { 
			 
			this.getXy_metric().get(sr).forEach(r->{ 
				 
				if(r.getBounds().contains(pp)) 
				{ 
					this.setToolTipText(gl.sf("%s",r.getLine().getName())); 
				} 
			}); 

		}); 
		 

	} 
	 
	 
	public boolean update_ma_points_v(TChart owner,String series) 
	{ 
		 
		String msg = "MA POINTS UPDATE"; 
		 
		List<Bar> items = owner.getShow_bars().get(series); 
		 
		if(items.size() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_ERROR,msg,"Empty BAR.")); 
			 
			return false; 
		} 
		 
		if(owner.getXy_metric().get(series).size() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_ERROR,msg,"Empty METRIC.")); 
			 
			return false; 
		} 
			 
		List<TGridRect> metrics = owner.getXy_metric().get(series).stream().filter( 
				(b-> ( 
						b.getLine().getType().trim().equalsIgnoreCase("BODY") 
					))).collect(Collectors.toList()); 
		 
		double max_high= owner.getHigh_low_range().get(series).getHigh(); 
		 
		int[] index = {0}; 
		 
		List<TMaPoint> m_ma_screen_items =  new ArrayList<TMaPoint>(); 
				 
		items.forEach(a-> 
		{ 
			 
			TGridRect gr = metrics.get(index[0]); 
			 
			Rectangle rect = gr.getBounds(); 
			 
			// Inject some offset. 
			 
			//rect.x = rect.x + rect.width; 
			 
			TMa[] response = {new TMa()}; 
			 
			double base_line_y = owner.getFrame_base().getHigh(); 
			 
			double y_zoom = owner.getFrame_base().getY_zoom(); 
					 
			if (TMa.lookUpByDate(owner.getMa().get(series),a.getDate(),response)) 
			{ 
				double m3_1 =((max_high - response[gl.E_EMPTY].getM3())*TChart.EUR_MULTIPLIER); 
				 
				double m3_2 = owner.getBounds().y + 
						base_line_y + 
						(m3_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m3 = (int)m3_2; 
				 
				Point p3 =  new Point(rect.x,m3); 
				 
				double m5_1 =((max_high - response[gl.E_EMPTY].getM5())*TChart.EUR_MULTIPLIER); 
				 
				double m5_2 = owner.getBounds().y + 
						base_line_y + 
						(m5_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m5 = (int)m5_2; 
				 
				Point p5 =  new Point(rect.x,m5); 
				 
				double m7_1 =((max_high - response[gl.E_EMPTY].getM7())*TChart.EUR_MULTIPLIER); 
				 
				double m7_2 = owner.getBounds().y + 
						base_line_y + 
						(m7_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m7 = (int)m7_2; 
				 
				Point p7 =  new Point(rect.x,m7); 
				 
				 
				double m10_1 =((max_high - response[gl.E_EMPTY].getM10())*TChart.EUR_MULTIPLIER); 
				 
				double m10_2 = owner.getBounds().y + 
						base_line_y + 
						(m10_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m10 = (int)m10_2; 
				 
				Point p10 =  new Point(rect.x,m10); 
				 
				 
				double m14_1 =((max_high - response[gl.E_EMPTY].getM14())*TChart.EUR_MULTIPLIER); 
				 
				double m14_2 = owner.getBounds().y + 
						base_line_y + 
						(m14_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m14 = (int)m14_2; 
				 
				Point p14 =  new Point(rect.x,m14); 
				 
				 
				double m21_1 =((max_high - response[gl.E_EMPTY].getM21())*TChart.EUR_MULTIPLIER); 
				 
				double m21_2 = owner.getBounds().y + 
						base_line_y + 
						(m21_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m21 = (int)m21_2; 
				 
				Point p21 =  new Point(rect.x,m21); 
				 
				 
				double m33_1 =((max_high - response[gl.E_EMPTY].getM33())*TChart.EUR_MULTIPLIER); 
				 
				double m33_2 = owner.getBounds().y + 
						base_line_y + 
						(m33_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m33 = (int)m33_2; 
				 
				Point p33 =  new Point(rect.x,m33); 
				 
				 
				double m55_1 =((max_high - response[gl.E_EMPTY].getM55())*TChart.EUR_MULTIPLIER); 
				 
				double m55_2 = owner.getBounds().y + 
						base_line_y + 
						(m55_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m55 = (int)m55_2; 
				 
				Point p55 =  new Point(rect.x,m55); 
				 
				 
				double m100_1 =((max_high - response[gl.E_EMPTY].getM100())*TChart.EUR_MULTIPLIER); 
				 
				double m100_2 = owner.getBounds().y + 
						base_line_y + 
						(m100_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m100 = (int)m100_2; 
				 
				Point p100 =  new Point(rect.x,m100); 
				 
				 
				double m144_1 =((max_high - response[gl.E_EMPTY].getM144())*TChart.EUR_MULTIPLIER); 
				 
				double m144_2 = owner.getBounds().y + 
						base_line_y + 
						(m144_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m144 = (int)m144_2; 
				 
				Point p144 =  new Point(rect.x,m144); 
				 
				double m200_1 =((max_high - response[gl.E_EMPTY].getM200())*TChart.EUR_MULTIPLIER); 
				 
				double m200_2 = owner.getBounds().y + 
						base_line_y + 
						(m200_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m200 = (int)m200_2; 
				 
				Point p200 =  new Point(rect.x,m200); 
				 
				 
				TMaPoint mp = TMaPoint.getInstance(response[gl.E_EMPTY].getDt(),p3,p5,p7,p10,p14,p21,p33,p55,p100,p144,p200); 
		 
				m_ma_screen_items.add(mp); 
				 
				index[0]++; 
				 
			} 
			 
				 
				 
		}); 
		 
		 
				if (owner.getMa_points().containsKey(series)) 
					owner.getMa_points().replace(series,m_ma_screen_items); 
				else 
					owner.getMa_points().put(series,m_ma_screen_items); 
			 
					boolean bl_result = (owner.getMa_points().get(series).size() == items.size()); 
				 
				if(bl_result) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_OK,msg)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
				} 
				 
					return bl_result; 
		 	 
	} 
		 

	public boolean update_ma_points(TChart owner,String series) 
	{ 
		 
		String msg = "MA POINTS UPDATE"; 
		 
		List<Bar> items = owner.getShow_bars().get(series); 
		
		
		List<TGridRect> metrics = owner.getXy_metric().get(series).stream().filter( 
				(b-> ( 
						b.getLine().getType().trim().equalsIgnoreCase("BODY") 
					))).collect(Collectors.toList()); 
		 
		double max_high= owner.getHigh_low_range().get(series).getHigh(); 
		 
		int[] index = {0}; 
		 
		List<TMaPoint> m_ma_screen_items =  new ArrayList<TMaPoint>(); 
				 
		items.forEach(a-> 
		{ 
			 
			TGridRect gr = metrics.get(index[0]); 
			 
			Rectangle rect = gr.getBounds(); 
			 
			// Inject some offset. 
			 
			//rect.x = rect.x + rect.width; 
			 
			TMa[] response = {new TMa()}; 
			 
			double base_line_y = owner.getFrame_base().getHigh(); 
			 
			double y_zoom = owner.getFrame_base().getY_zoom(); 
					 
			if (TMa.lookUpByDate(owner.getMa().get(series),a.getDate(),response)) 
			{ 
				double m3_1 =((max_high - response[gl.E_EMPTY].getM3())*TChart.EUR_MULTIPLIER); 
				 
				double m3_2 = owner.getBounds().y + 
						base_line_y + 
						(m3_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m3 = (int)m3_2; 
				 
				Point p3 =  new Point(rect.x,m3); 
				 
				double m5_1 =((max_high - response[gl.E_EMPTY].getM5())*TChart.EUR_MULTIPLIER); 
				 
				double m5_2 = owner.getBounds().y + 
						base_line_y + 
						(m5_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m5 = (int)m5_2; 
				 
				Point p5 =  new Point(rect.x,m5); 
				 
				double m7_1 =((max_high - response[gl.E_EMPTY].getM7())*TChart.EUR_MULTIPLIER); 
				 
				double m7_2 = owner.getBounds().y + 
						base_line_y + 
						(m7_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m7 = (int)m7_2; 
				 
				Point p7 =  new Point(rect.x,m7); 
				 
				 
				double m10_1 =((max_high - response[gl.E_EMPTY].getM10())*TChart.EUR_MULTIPLIER); 
				 
				double m10_2 = owner.getBounds().y + 
						base_line_y + 
						(m10_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m10 = (int)m10_2; 
				 
				Point p10 =  new Point(rect.x,m10); 
				 
				 
				double m14_1 =((max_high - response[gl.E_EMPTY].getM14())*TChart.EUR_MULTIPLIER); 
				 
				double m14_2 = owner.getBounds().y + 
						base_line_y + 
						(m14_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m14 = (int)m14_2; 
				 
				Point p14 =  new Point(rect.x,m14); 
				 
				 
				double m21_1 =((max_high - response[gl.E_EMPTY].getM21())*TChart.EUR_MULTIPLIER); 
				 
				double m21_2 = owner.getBounds().y + 
						base_line_y + 
						(m21_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m21 = (int)m21_2; 
				 
				Point p21 =  new Point(rect.x,m21); 
				 
				 
				double m33_1 =((max_high - response[gl.E_EMPTY].getM33())*TChart.EUR_MULTIPLIER); 
				 
				double m33_2 = owner.getBounds().y + 
						base_line_y + 
						(m33_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m33 = (int)m33_2; 
				 
				Point p33 =  new Point(rect.x,m33); 
				 
				 
				double m55_1 =((max_high - response[gl.E_EMPTY].getM55())*TChart.EUR_MULTIPLIER); 
				 
				double m55_2 = owner.getBounds().y + 
						base_line_y + 
						(m55_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m55 = (int)m55_2; 
				 
				Point p55 =  new Point(rect.x,m55); 
				 
				 
				double m100_1 =((max_high - response[gl.E_EMPTY].getM100())*TChart.EUR_MULTIPLIER); 
				 
				double m100_2 = owner.getBounds().y + 
						base_line_y + 
						(m100_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m100 = (int)m100_2; 
				 
				Point p100 =  new Point(rect.x,m100); 
				 
				 
				double m144_1 =((max_high - response[gl.E_EMPTY].getM144())*TChart.EUR_MULTIPLIER); 
				 
				double m144_2 = owner.getBounds().y + 
						base_line_y + 
						(m144_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m144 = (int)m144_2; 
				 
				Point p144 =  new Point(rect.x,m144); 
				 
				double m200_1 =((max_high - response[gl.E_EMPTY].getM200())*TChart.EUR_MULTIPLIER); 
				 
				double m200_2 = owner.getBounds().y + 
						base_line_y + 
						(m200_1 * y_zoom)*TChart.SCALE_FACTOR ; 
				 
				int m200 = (int)m200_2; 
				 
				Point p200 =  new Point(rect.x,m200); 
				 
				 
				TMaPoint mp = TMaPoint.getInstance(response[gl.E_EMPTY].getDt(),p3,p5,p7,p10,p14,p21,p33,p55,p100,p144,p200); 
		 
				m_ma_screen_items.add(mp); 
				 
				index[0]++; 
				 
			} 
			 
				 
				 
		}); 
		 
		 
				if (owner.getMa_points().containsKey(series)) 
					owner.getMa_points().replace(series,m_ma_screen_items); 
				else 
					owner.getMa_points().put(series,m_ma_screen_items); 
			 
					boolean bl_result = (owner.getMa_points().get(series).size() == items.size()); 
				 
				if(bl_result) 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_OK,msg)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},String.format("%s...%s",gl.S_ERROR,msg)); 
				} 
				 
					return bl_result; 
		 	 
	} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 
		 
		 
		for (int i = 0; i < count; i++) { 

			TChart sp = new TChart(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(gl.getRandomColor()); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	 


	public static void main(String[] args) { 
		 
		startUp() ; 
		 
	} 

} 
