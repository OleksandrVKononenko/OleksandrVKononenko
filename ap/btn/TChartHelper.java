 
package ap.btn; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.stream.Collectors; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 

public class TChartHelper { 
	 
	Map<String,Map<String,List<TGridLine>>> lines = new HashMap<String,Map<String,List<TGridLine>>>(); 
	 
	private TChart owner; 
	 
	public TGridLine get_by_name(String index,String section,String name) 
	{ 
		return 
				 
				this.getLines().get(index).get(section).stream() 
						.filter((b-> 
						( 
								b.getName().equalsIgnoreCase(name) 
						))).findFirst().orElse(null); 
		 
	} 
	 
	 
	public synchronized  List<String> get_series() 
	{ 
	 
		return this.getLines().entrySet() 
				.stream() 
				.map(Map.Entry::getKey) 
				.collect(Collectors.toList()); 
	 
	} 
	 
	public synchronized  List<String> get_series(String index) 
	{ 
	 
		return this.getLines().get(index).entrySet() 
				.stream() 
				.map(Map.Entry::getKey) 
				.collect(Collectors.toList()); 
	 
	} 

	public void init(TChart owner) 
	{ 

		this.setOwner(owner); 
		 
		 
		this.init_base_lines(); 
		 
		this.init_ma_lines(); 
		 
		this.initPivotLines(); 

		 
		 
	} 
	 
	 
	
	public boolean  init_base_lines() 
	{ 
		 
		
		try
		
		{
		 
		String map_section = "COMMON"; 
				 
		ArrayList<TGridLine> 	g_list 	= new ArrayList<TGridLine>(); 
		 
		Rectangle 				bounds 	= this.getOwner().getBounds(); 
		 
		int 					y_min 	=  TChart.START_OFFSET_Y; 
		 
		g_list.add( 
		new TGridLine( 
				new Point(0,TChart.START_OFFSET_Y), 
				new Point(bounds.width,y_min), 
				TChart.HIGH) 
		); 
				 
		int y_max = (bounds.height - TChart.END_OFFSET_Y); 
				 
		g_list.add( 
 
				new TGridLine( 
				new Point(0,bounds.height - TChart.END_OFFSET_Y), 
				new Point(bounds.width,y_max), 
				TChart.LOW) 
				); 
				 
		 
		int y_middle = TChart.START_OFFSET_Y + ((bounds.height - TChart.END_OFFSET_Y) - TChart.START_OFFSET_Y)/2; 
				 
		g_list.add( 
 
				new TGridLine( 
				new Point(0,y_middle), 
				new Point(bounds.width,y_middle), 
				TChart.MIDDLE) 
				); 
		 
		int y_middle_high = y_min + (y_middle - y_min) / 2 ; 
		 
		g_list.add( 
				new TGridLine( 
				new Point(0,y_middle_high), 
				new Point(bounds.width,y_middle_high), 
				TChart.MIDDLE_HIGH) 
				); 
				 
		int y_middle_low = y_middle + (y_max - y_middle)  / 2 ; 
		 
		g_list.add( 
				new TGridLine( 
				new Point(0,y_middle_low), 
				new Point(bounds.width,y_middle_low), 
				TChart.MIDDLE_LOW) 
				); 
		
		 
		if(!this.getLines().containsKey(map_section)) 
		{ 
				this.getLines().put(map_section,null); 
		} 
		
		//if ( this.getLines().get(map_section) != null) 
		//	if(this.getLines().get(map_section).containsKey(map_section)) 
		//		this.getLines().get(map_section).replace(map_section,g_list); 
		//	else 
				this.getLines().get(map_section).put(map_section,g_list); 
		 
		
			return gl.tx(new Object(),true,gl.sf("Инициализация БАЗовых уровней.")); 
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
			return false;
		}
		
	} 
		 
	public boolean init_ma_lines() 
	{ 
		 
		String msg = "INIT MA LINES"; 
		 
		if(this.getOwner() == null) 
		{ 
		 
			gl.tx_e(new Object(){},gl.sf("%s...%s",msg,"Owner is NULL.")); 
					 
			return false; 
		} 
		 
		if(this.getOwner().getMa_points()  == null) 
		{ 
		 
			gl.tx_e(new Object(){},gl.sf("%s...%s",msg,"MA points is NULL.")); 
					 
			return false; 
		} 
		 
		if(this.getOwner().getManager()  == null) 
		{ 
		 
			gl.tx_e(new Object(){},gl.sf("%s...%s",msg,"Manager is NULL.")); 
					 
			return false; 
		} 
		 
		 
		List<String> 	series = this.getOwner().getManager().getCollector().get_list_series_points(this.getOwner().getMa_points()); 
		 
		Rectangle 		bounds = this.getOwner().getBounds(); 
		 
		String 			map_section = "MA"; 
		 
		if(!this.getLines().containsKey(map_section)) 
		{ 
			this.getLines().put(map_section,null); 
		} 
		 
		 
		boolean [] bl_ma = {false,false,false,false,false,false,false,false}; 
		 
		// Init Ma names. 
		 
		for(int i=TMa.M3;i <= TMa.M200;i++) 
		{ 
			String qualified_action_name = gl.sf("switch_ma_%s",gl.ma_names[i].toLowerCase()); 
			 
			if(this.getOwner().getPaint_map().containsKey(qualified_action_name)) 
			{ 
				bl_ma[i] = this.getOwner().getPaint_map().get(qualified_action_name); 
			} 
			
		} // for Init Ma names. 
		 
		 
		series.forEach(sr-> 
		{ 
			 
				List<TGridLine> 	lines = new ArrayList<TGridLine>(); 
			 
				List<TMaPoint> 		points = this.getOwner().getMa_points().get(sr); 
				 
				TMaPoint[]  		tmp = {null}; 
				 
				// Формируем линии средних по точкам.
				
				points.forEach(p->{ 
					 
					TMaPoint base_point = tmp[gl.E_EMPTY]; 
					 
					if(base_point != null) 
					{ 
						 
						gl.ma_types.forEach(ma_type->{ 

							
							if(bl_ma[ma_type]) 
							{ 
							
								lines.add(TGridLine.getInstance( 
									Ru.normPointY(bounds,base_point.getValueByType(ma_type)), 
									Ru.normPointY(bounds,p.getValueByType(ma_type)), 
									sr, 
									gl.ma_names[ma_type], 
									gl.ma_styles[ma_type] 
									) 
									); 
							} 
							
						}); 
						 
					} // r != null. 
					 
							tmp[gl.E_EMPTY] = p; 
					 
				}); 
		 
				this.getLines().get(map_section).put(sr,lines); 

				int size = this.getLines().get(map_section).get(sr).size(); 
				 
				gl.tx_k(new Object(){},gl.sf("%s...section...%s...series...%s...count...%d",msg,map_section.toUpperCase(),sr,size)); 
				 
				 
		}); 
		 
				return true; 
		 
	} 
	 
	public void initPivotLines() 
	{ 
		 
		String msg = "INIT PIVOT LINES"; 
		 
		if(this.getOwner() == null) 
		{ 
		 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_OK,msg,"Owner is NULL.")); 
					 
			return; 
		} 
		 
		if(this.getOwner().getMa_points() == null) 
		{ 
		 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_OK,msg,"MA points is NULL.")); 
					 
			return; 
		} 
		 
		if(this.getOwner().getManager()  == null) 
		{ 
		 
			gl.tracex(new Object(),String.format("%s...%s...%s",gl.S_OK,msg,"Manager is NULL.")); 
					 
			return; 
		} 
		 
		 
		List<String> series = this.getOwner().getManager().getCollector().get_list_series_points(this.getOwner().getMa_points()); 
		 
		Rectangle bounds 	= this.getOwner().getBounds(); 
		 
		String map_section 	= "PIVOT"; 
		 
		series.forEach(sr-> 
		{ 
			 
				List<TGridLine> 	lines 	= new ArrayList<TGridLine>(); 
			 
				List<TPivotSuite> 	points 	= this.getOwner().getPivots().get(sr); 
				 
				TPivotSuite []  	tmp 	= {null}; 
				 
				points.forEach(p->{ 
					 
					TPivotSuite r = tmp[gl.E_EMPTY]; 
					 
					if(r != null) 
					{ 
						int pivot_type = TPivot.STANDARD; 
						 
						//gl.pivot_types.forEach(pivot_type->{ 
							 
							TGridLine PP = TGridLine.getInstance( 
									Ru.normPointY(bounds,r.getValueByType(pivot_type).getP().getPoint()), 
									Ru.normPointY(bounds,p.getValueByType(pivot_type).getP().getPoint()), 
									sr, 
									gl.pivot_type_names[pivot_type], 
									"P", 
									gl.pivot_styles[gl.P] 
									) ; 
							 
							 
							TGridLine R1 = TGridLine.getInstance( 
									Ru.normPointY(bounds,r.getValueByType(pivot_type).getR1().getPoint()), 
									Ru.normPointY(bounds,p.getValueByType(pivot_type).getR1().getPoint()), 
									sr, 
									gl.pivot_type_names[pivot_type], 
									"R1", 
									gl.pivot_styles[gl.R1] 
									) ; 
					 
							TGridLine S1 = TGridLine.getInstance( 
									Ru.normPointY(bounds,r.getValueByType(pivot_type).getS1().getPoint()), 
									Ru.normPointY(bounds,p.getValueByType(pivot_type).getS1().getPoint()), 
									sr, 
									gl.pivot_type_names[pivot_type], 
									"S1", 
									gl.pivot_styles[gl.S1] 
									) ; 
							 
							 
							lines.add(PP); 
							 
							lines.add(R1); 
							 
							lines.add(S1); 
							 
							if ( pivot_type == TPivot.STANDARD) 
							{ 
								 
								TGridLine R2 = TGridLine.getInstance( 
										Ru.normPointY(bounds,r.getValueByType(pivot_type).getR2().getPoint()), 
										Ru.normPointY(bounds,p.getValueByType(pivot_type).getR2().getPoint()), 
										sr, 
										gl.pivot_type_names[pivot_type], 
										"R2", 
										gl.pivot_styles[gl.R2] 
										) ; 
						 
								TGridLine S2 = TGridLine.getInstance( 
										Ru.normPointY(bounds,r.getValueByType(pivot_type).getS2().getPoint()), 
										Ru.normPointY(bounds,p.getValueByType(pivot_type).getS2().getPoint()), 
										sr, 
										gl.pivot_type_names[pivot_type], 
										"S2", 
										gl.pivot_styles[gl.S2] 
										) ; 

										lines.add(R2); 
								 
										lines.add(S2); 

								 
							} 
							 
							if ( pivot_type == TPivot.FIBONACCI) 
							{ 
								 
								TGridLine R3 = TGridLine.getInstance( 
										Ru.normPointY(bounds,r.getValueByType(pivot_type).getR3().getPoint()), 
										Ru.normPointY(bounds,p.getValueByType(pivot_type).getR3().getPoint()), 
										sr, 
										gl.pivot_type_names[pivot_type], 
										"R3", 
										gl.pivot_styles[gl.R3] 
										) ; 
						 
								TGridLine S3 = TGridLine.getInstance( 
										Ru.normPointY(bounds,r.getValueByType(pivot_type).getS2().getPoint()), 
										Ru.normPointY(bounds,p.getValueByType(pivot_type).getS2().getPoint()), 
										sr, 
										gl.pivot_type_names[pivot_type], 
										"S3", 
										gl.pivot_styles[gl.S3] 
										) ; 

										lines.add(R3); 
								 
										lines.add(S3); 

								 
							} 
							 
						//});  // pivot_type 
						 
						 
					} // r != null. 
					 
					tmp[gl.E_EMPTY] = p; 
					 
				}); 
		 


				if(this.getLines().get(map_section).containsKey(sr)) 
					this.getLines().get(map_section).replace(sr,lines); 
				else 
					this.getLines().get(map_section).put(sr,lines); 
				 
		}); 
		 
		 
	} 
	 

	 
	public void drawMetrics(Graphics2D g2) 
	{ 
		drawSection(g2,"COMMON","COMMON"); 
	} 
	 
	public void drawMas(Graphics2D g2) 
	{ 
		drawSection(g2,"MA"); 
	} 
	 
	public void drawPivots(Graphics2D g2) 
	{ 
		drawSection(g2,"PIVOT"); 
	} 
	 
	public void drawSection(Graphics2D g2,String index,String section) 
	{ 
		List<TGridLine> li = this.getLines().get(index).get(section); 
		 
		if(index.equalsIgnoreCase("COMMON")) 
		{ 
		li.forEach(l->{ 
			 
			if(l.getName().equalsIgnoreCase(TChart.HIGH)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(0,1)), 
						l, 
						Color.white,0.8f); 

			} else 	if(l.getName().equalsIgnoreCase(TChart.LOW)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(0,1)), 
						l, 
						Color.white,0.8f); 
			} else	if(l.getName().equalsIgnoreCase(TChart.MIDDLE)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(4,1)), 
						l, 
						Color.blue,0.8f); 

			} else	if(l.getName().equalsIgnoreCase(TChart.MIDDLE_HIGH)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(3,1)), 
						l, 
						Color.yellow,0.7f); 

			}else	if(l.getName().equalsIgnoreCase(TChart.MIDDLE_LOW)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(3,1)), 
						l, 
						Color.yellow,0.7f); 

			} else	if(l.getName().equalsIgnoreCase(TChart.LEFT)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(2,1)), 
						l, 
						Color.white,0.7f); 

			} else	if(l.getName().equalsIgnoreCase(TChart.RIGHT)) 
			{ 
				GU.drawAlphaLine(g2, 
						TStroke.getInstance(new Dimension(2,1)), 
						l, 
						Color.white,0.7f); 

			} 

			 

		}); 
		} // COMMON 
					 
	} 
	 
	public void drawSection(Graphics2D g2,String index) 
	{ 
			this.get_series(index).forEach( sr->{ 
				 
				this.getLines().get(index).get(sr).forEach(line->{ 
					 
					line.draw(g2); 
					 
				}); //sr 
					 
			}); // line 
	} 
				 
	 
	public TChart getOwner() { 
		return owner; 
	} 


	public void setOwner(TChart owner) { 
		this.owner = owner; 
	} 

	public Map<String,Map<String, List<TGridLine>>> getLines() { 
		return lines; 
	} 


	public void setLines(Map<String,Map<String, List<TGridLine>>> lines) { 
		this.lines = lines; 
	} 


	public TChartHelper() { 
		 
		Map<String,List<TGridLine>> metrics_map = new HashMap<String,List<TGridLine>>(); 
		 
		Map<String,List<TGridLine>> ma_map = new HashMap<String,List<TGridLine>>(); 
		 
		Map<String,List<TGridLine>> pivot_map = new HashMap<String,List<TGridLine>>(); 
		 
		 
		metrics_map.put("COMMON",new ArrayList<TGridLine>()); 
		 
		ma_map.put("MA",new ArrayList<TGridLine>()); 
		 
		pivot_map.put("PIVOT",new ArrayList<TGridLine>()); 
		 
				 
		this.getLines().put("COMMON",metrics_map); 
		 
		this.getLines().put("MA",ma_map); 
		 
		this.getLines().put("PIVOT",pivot_map); 
		 
	} 

	 
	
	public static void main(String[] args) { 
		 

	} 

} 
