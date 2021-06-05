
package ap.utils; 

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ap.btn.Bar;
import ap.explorer.Range;
import ap.global.*;
import ap.mercury.explorer.Util;

public class CandleUtil { 

	public static List<String> types = Arrays.asList(
	new String[] {
	"NONE",
	"ZBNS", 
	"ESNE", 
	"ESSE", 
	"ESNL", 
	"ESSL", 
	"ESNG", 
	"ESSG", 
	"NHNE", 
	"NHSE", 
	"NHNL", 
	"NHSL",
	"NHNG", 
	"NHSG",
	"NSN", 
	"NSS",
	"NLNE", 
	"NLSE", 
	"NLNG", 
	"NLSG", 
	"NLNL", 
	"NLSL", 
	"ZBNL", 
	"ZBNH", 
	"ZBES", 
	"ZBHG",
	"ZBLG",
	"CHLNBE", 
	"CHLSBE",
	"CHLNBL", 
	"CHLSBL",
	"CHLNBG", 
	"CHLSBG",
	"CHGNBE", 
	"CHGSBE",
	"CHGNBL", 
	"CHGSBL",
	"CHGNBG", 
	"CHGSBG"
	});
	
	public static String indexOf(int type)
	{
		return types.get(type);
	}
	

	public static int indexOf(String type)
	{
		return types.indexOf(type.toUpperCase());
	}
	
	
	public static int MULTIPLIER = 10000; 
	 
	
    public static int getTypeOf(Bar tbi) { 
		
		double Z 	= 0L; 

		int P 		= 1; 

		int N 		= -1; 

		if (    tbi.getHighShadow() == tbi.getLowShadow() && 
				tbi.getBody() == Z 						  && 
				tbi.getLowShadow()==tbi.getBody()
			) 
			return indexOf("ZBNS"); 

		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() == tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("ESNE"); 
		
		 
		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() == tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("ESSE"); 
		
		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() < tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("ESNL"); 
		
		 
		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() < tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("ESSL"); 
		 
		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() > tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("ESNG"); 
		
		 
		else if (tbi.getHighShadow() == tbi.getLowShadow() 
				&& tbi.getHighShadow() != Z 
				&& tbi.getHighShadow() > tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("ESSG"); 
		
		 
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() == tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NHNE"); 
		
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() == tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NHSE"); 
		
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() < tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NHNL"); 
		 
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() < tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NHSL");
		
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() > tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NHNG"); 
		 
		else if (tbi.getHighShadow() == Z 
				&& tbi.getLowShadow() > Z 
				&& tbi.getLowShadow() > tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NHSG");
		
		else if (tbi.getHighShadow() == Z && tbi.getLowShadow() == Z 
				&& tbi.getSign() == P) 
			return indexOf("NSN"); 
		
		else if (tbi.getHighShadow() == Z && tbi.getLowShadow() == Z 
				&& tbi.getSign() == N) 
			return indexOf("NSS");
		
		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() == tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NLNE"); 
		 
		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() == tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NLSE"); 
		
		
		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() > tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NLNG"); 
		 
		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() > tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NLSG"); 
		

		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() < tbi.getBody() 
				&& tbi.getSign() == P) 
			return indexOf("NLNL"); 
		 
		else if (tbi.getHighShadow() > Z 
				&& tbi.getLowShadow() == Z 
				&& tbi.getHighShadow() < tbi.getBody() 
				&& tbi.getSign() == N) 
			return indexOf("NLSL"); 
		 
		else if (
				 tbi.getHighShadow() > Z && 
				 tbi.getLowShadow() == Z && 
				 tbi.getSign() == Z
				 ) 
			return indexOf("ZBNL"); 
		
		else if (
				 tbi.getHighShadow() == Z && 
				 tbi.getLowShadow()  >  Z &&
				 tbi.getSign() == Z
				 ) 
			return indexOf("ZBNH"); 
		 
		else if (
				 tbi.getHighShadow() == tbi.getLowShadow() && 
				 tbi.getLowShadow() > Z && 
				 tbi.getSign() == Z
				 ) 
			return indexOf("ZBES"); 
		 
		else if (
				tbi.getHighShadow() > Z && 
				tbi.getLowShadow()  > Z &&
				(tbi.getHighShadow() > tbi.getLowShadow()) &&
				tbi.getSign() == Z
				) 
			return indexOf("ZBHG");
		else if (
				tbi.getHighShadow() > Z && 
				tbi.getLowShadow()  > Z &&
				(tbi.getHighShadow() < tbi.getLowShadow()) &&
				tbi.getSign() == Z
				) 
			return indexOf("ZBLG");
		 
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow() == tbi.getBody()) 
				&& tbi.getSign() == P) 
			return indexOf("CHLNBE"); 
		 
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow() == tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHLSBE");
		
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow() > tbi.getBody()) 
				&& tbi.getSign() == P) 
			return indexOf("CHLNBL"); 
		 
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow() > tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHLSBL");
		
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow()  < tbi.getBody()) 
				&& tbi.getSign() == P) 
			return indexOf("CHLNBG"); 
		 
		else if ( 
				   (tbi.getHighShadow() < tbi.getLowShadow()) 
				&& (tbi.getLowShadow()  < tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHLSBG");
		
		// ---
		
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow() == tbi.getBody()) 
				&& tbi.getSign() == P) 
			return indexOf("CHGNBE"); 
		 
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow() == tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHGSBE");
		
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow() > tbi.getBody()) 
				&& (tbi.getBody() != Z) 
				&& tbi.getSign() == P) 
			return indexOf("CHGNBL"); 
		 
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow() > tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHGSBL");
		
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow()  < tbi.getBody()) 
				&& tbi.getSign() == P) 
			return indexOf("CHGNBG"); 
		 
		else if ( 
				   (tbi.getHighShadow() > tbi.getLowShadow()) 
				&& (tbi.getHighShadow()  < tbi.getBody()) 
				&& tbi.getSign() == N) 
			return indexOf("CHGSBG");
		else 
			return indexOf("NONE"); 

	} 
	public static String getCTypeOf(int type) { 

			return indexOf(type);
	} 

	public CandleUtil() { 

	} 
	
	
	public static boolean get_bars_by_dlg(Range range,int candle_type)
	{
				// Открываем входные файлы для теста.
		
				List<String> m_files = Fu.files_open_dlg(".");
				
				List<Boolean> state = new ArrayList<Boolean>();
				
				// Глобальная мап для статистики.
				
				
				// Первый БАР взять за базу.
				
				Bar [] base = {null};
				
				m_files.forEach(file->{
					
					gl.tx(new Object() {},Fu.isaFile(file),gl.sf("Файл для чтения...[%s]",file));
				
					// Формируем БАРы.
				
					List<Bar> bars = new ArrayList<Bar>();
					
					String [] params = new String[]{file,"","" /* Это для хэдэра.*/};
				
					state.add(Util.make_bar_items_full(params,bars));
					
						
					// Фильтруем по необходимой типизации.
				
					List<Bar> filter = Bar.get_by_type_and_date_range(bars,range,candle_type);
					
					gl.tx(new Object() {},filter.size() != gl.E_EMPTY,
							gl.sf("Фильтр по дате...[%s]...и типу...[%2d]...записей...[%4d]",range.toString(),candle_type,filter.size()));
					
					// Конвертировать БАРы.
					
					// Поставить их на одном уровне.
					
					// Инициализируем базовый БАР.
					
					if(base[0] == null)
					  base[0]  = filter.get(gl.E_EMPTY);
				
					
					filter.forEach(f->{
					
						// Находим разницу по L.
						
						double dL = f.getL() - base[0].getL();
						
						//gl.sfn("Base...[%.4f]...Diff...[%.4f]",base[0].getH(),dH);
						
						/*
						if(dL != 0.00)
						{
							// Вычитаем разницу.
							
							f.setO(f.getO() - dL);
							
							f.setH(f.getH() - dL);
							
							f.setL(f.getL() - dL);
							
							f.setC(f.getC() - dL);
							
						}
						*/
						
								
						
					});
					
					// Пишем в файл.
					
					//String export = gl.sf("%s_T_%02d_%s.txt",params[1],candle_type,DateUtil.get_stamp_for_zip());
					
					String export = gl.sf("%s_R.txt",params[1]);
					
					// Пока эмулируем.
					
					params[2] = "#@#eurusd,d1";
					
					Fu.to_file(export, gl.sf("%s%s",params[2],System.lineSeparator()));
					
					gl.tx(new Object() {},Bar.to_file(export,filter,true),
							gl.sf("Запись фильтра в файл...[%s]",
									export)
							);
					
					
					//filter.forEach(f->{
						
						//gl.sfn("%s",f.to_str_for_export());
						
					//});
					
					filter.clear();
					
					
				});
				
					return (!state.contains(false)); 
	}
				
	
	public static Map<String,Map<Integer,Rectangle2D>> get_stat_map()
	{
				// Открываем входные файлы для теста.
		
				List<String> m_files = Fu.files_open_dlg(".");
				
				List<Boolean> state = new ArrayList<Boolean>();
				
				// Глобальная мап для статистики.
				
				Map<String,Map<Integer,java.awt.geom.Rectangle2D>> gl_map = new LinkedHashMap<String,Map<Integer,java.awt.geom.Rectangle2D>>();  
				
				m_files.forEach(file->{
					
					gl.tx(new Object() {},Fu.isaFile(file),gl.sf("Файл для чтения...[%s]",file));
				
					// Формируем БАРы.
					
					List<Bar> bars = new ArrayList<Bar>();
					
					String [] param = new String[]{file,""};
				
					state.add(Util.make_bar_items_full(param,bars));
					
					// Группировка.
					
					Map<Integer, Long> map = bars.stream().collect(
			                Collectors.groupingBy(Bar::getType, Collectors.counting()));
					
					
					Map<Integer, Long> sort_map = new LinkedHashMap<Integer, Long>();
					
					Map<Integer, java.awt.geom.Rectangle2D> final_map = new LinkedHashMap<Integer,java.awt.geom.Rectangle2D>();
					
					map.entrySet().stream()
		            .sorted(Map.Entry.<Integer, Long>comparingByValue()
		                    .reversed()).forEachOrdered(e -> sort_map.put(e.getKey(), e.getValue()));

					sort_map.forEach((k,v)->{
						
						double a = v;
						
						double b = bars.size();
								
						final_map.put(k,new java.awt.geom.Rectangle2D.Double(a,(a / b)*100,0d,0d));
						
					});
					
					final_map.forEach((k,v)->{
						
						//gl.sfn("%6s %2d  %5d %2s ",param[1],k,(int)v.getX() ,gl.fmt(v.getY()));
						
					});
					
					gl_map.put(param[1],final_map);
				});
				
				
					gl.tx(new Object() {},!state.contains(false),gl.sf("Завершение формирования."));
			
					return gl_map;
	}
	
	public static String stat_map_to_str(Map<String,Map<Integer,Rectangle2D>> map)
	{	
		// Формируем справочник по тикерам.
		
		List<String> tickers = map.keySet().stream().collect(Collectors.toList());
		
		Map.Entry<String,Map<Integer,Rectangle2D>> c = map.entrySet().stream().findFirst().get();
		
		Map<Integer,Rectangle2D> c1  = c.getValue();
		
		List<Integer> types    = c1.keySet().stream().collect(Collectors.toList());
		
		
		StringBuilder sb = new StringBuilder();
		
		int HEADER_WIDTH = 18;
		
		// Report header.
		
		// 1th row.
		
		sb.append('+');
		
		sb.append(gl.replicate('-',tickers.size()*HEADER_WIDTH));
		
		sb.append('+');
		
		sb.append(System.lineSeparator());
		

		// 2th row with caption.
		
		tickers.forEach(t->{
			
			sb.append(gl.sf("|%6s%6s%5s","",t,""));
			
		});
	
		sb.append(gl.sf(" |"));
		
		sb.append(System.lineSeparator());
		
		
		// 3th row.
		
		sb.append('+');
		
		sb.append(gl.replicate('-',tickers.size()*HEADER_WIDTH));
		
		sb.append('+');
		
		sb.append(System.lineSeparator());
		
		
		// Body row.
		
		types.forEach(tp->{
			
			tickers.forEach(t->{
				
				int 	cnt = map.get(t).containsKey(tp) ? (int)map.get(t).get(tp).getX() : 0;
				
				double 	prc = map.get(t).containsKey(tp)  ? map.get(t).get(tp).getY() : 0.0;
						
				sb.append(gl.sf("|%2d %4d %2s ",tp,cnt,gl.fmt(prc)));
				
			});
			
				sb.append(gl.sf(" |"));
			
				sb.append(System.lineSeparator());
				
			
		});
		
		// Bottom row.
		
				sb.append('+');
				
				sb.append(gl.replicate('-',tickers.size()*HEADER_WIDTH));
				
				sb.append('+');
				
				sb.append(System.lineSeparator());
		
		return 	sb.toString();
		
	}
	
	public static void test_type_of()
	{
		types.forEach(t->{
		
			gl.sfn("Type...%s...%2d...%s...[%s]",
					t,indexOf(t),
					indexOf(indexOf(t)),
					indexOf(indexOf(t)).equals(t)
					);
		
		});
	}
	public static void test()
	{
		
		String out = stat_map_to_str(get_stat_map());
				
		gl.sfn("%s",out);
		
		Fu.to_file(gl.sf("a_stat_%s.txt",DateUtil.get_stamp_for_zip()),out);
		
		gl.sfn("Simple tests(13/20)...%d...24/10...%d...%s",13 /20,24/10,Bau.to_hex_str_from_int(115/10) );
		
	}
	
	
	public static void test_sec_meta()
	{
				// Открываем входные файлы для теста.
		
				List<String> m_files = Fu.files_open_dlg(".");
				
				m_files.forEach(file->{
					
					gl.tx(new Object() {},Fu.isaFile(file),gl.sf("Файл для чтения...[%s]",file));
				
					String meta = Fu.read_first_str(file);//Fu.get_first_row(file);
					
					int []  i = {0};
						
					Arrays.asList(meta.split("\t")).forEach(f->{
							
						// gl.sfn("%2d.[%s]",i[0],f);
						
						 // Для МЕТА информации.
						
						 gl.sfn("\t%s%s = %2d,",gl.replicate(' ',20 - f.trim().length()),f.toUpperCase(),i[0]);
						
					    i[0]++;
					});
					
				});
	}
	
	
	
	public static void test_bars(Range range,int candle_type)
	{
		gl.tx(new Object(){},get_bars_by_dlg(range,candle_type),gl.sf("Тестирование БАРов."));
	}

	public static void main(String[] args) { 

		//test();
		
	    //test_type_of();
	    
		//test_bars(Range.get_instance("01.01.1998","01.01.2018"),CandleUtil.indexOf(CandleUtil.indexOf(0)));
		
		
		//test_sec_data();
		
		//test_sec_meta();
		
		//test_sec_pre();
	} 

} 
// Revision : 10.09.2018 12:49:16 
