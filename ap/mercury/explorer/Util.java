package ap.mercury.explorer;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ap.btn.Bar;
import ap.btn.Order;
import ap.explorer.MarketOrder;
import ap.explorer.Quote;
import ap.global.gl;
import ap.orion.action.BaseAction;
import ap.shape.Ru;
import ap.utils.DateUtil;
import ap.utils.Fu;
import ap.utils.Su;

public class Util {

	    // С проверкой формата данных.
	
	public static boolean make_bar_items_full(String [] source,List<Bar> bars) 
	{ 
		
		    // Для ясности.
		
			int FILE = 0,TICKER = 1, FILE_HEADER = 2;
			
			BaseAction ba = BaseAction.get_instance();
		
			List<String> ds = Fu.get_list_from_file(source[FILE]);
			
			if(!ba.check_list(ds))
				return false;
			
			// Проверка заголовка файла данных.
		
			if(!ba.check_header_syntax(ds.get(gl.E_EMPTY)))
				return false;
			
			// Извлекаем тикер , для сохранения файлов.
			
			source[TICKER] = Su.extract_ticker(ds.get(gl.E_EMPTY));
			
			// Если инициировано то извлекаем хэдер файла для перекомпоновки.
			
			if(source.length == 3)
			{
				source[FILE_HEADER] = ds.get(gl.E_EMPTY);
			}
		
			String  data_probe = ds.get(1);
	
			String 	file_fmt = Su.get_date_format(data_probe);  
	
			if(!gl.tx(new Object(){}, Su.in(file_fmt,new String[]{"fibo","normal"}),
					gl.sf("Формат данных файла...[%s]",source)))
			 {
				return false;
			 }
			
			
			List<String> items = new ArrayList<String>(ds);
			
						 items.remove(gl.E_EMPTY);
	
			long 			st = System.nanoTime();
	
			return gl.tx(  new Object(){},
			 
			 ap.mercury.explorer.Util.make_bar_items(items,bars,file_fmt),
	
			 gl.sf("Формирование БАРов...[%5d]...format...[%s]...in...[%3d] ms.",
					 bars.size(),file_fmt,gl.t_end(st))
			 
			);
	
	}
		
		public static boolean make_bar_items(List<String> source_items,List<Bar> bar_items,String date_fmt) 
		{ 
			if(	bar_items.size() != gl.E_EMPTY)
				bar_items.clear(); 

			try { 
					
				int [] id = { gl.E_OK }; 

					source_items.forEach(a -> { 

					if(a.trim().length() != gl.E_EMPTY) 
					{ 
						bar_items.add(Bar.get_instance(id[0], a,date_fmt)); 

						id[0]++; 
					} 

				}); 

					return (bar_items.size() != gl.E_EMPTY); 
					
			} catch (Exception e) { 

					return false; 

			} 
		} 

		
		// Интегрируем OHLC  в MAS.
		
		public static boolean make_mas(
				List<Bar> bars,
				List<Integer> mas,
				Map<Date,Map<Dimension,Double>> out 
		) 
		{ 
			try
			
			{
			
			// Рабочие.
			
			Map<Dimension,Double> 		m_map 	= new LinkedHashMap<Dimension,Double>();
			
			// Накопитель для расчета.
			
			Map<Dimension,List<Double>> map_work 	= new LinkedHashMap<Dimension,List<Double>>();
	
			// Инициализация.

			mas.forEach(m->
			{
					
				for (int o = Bar.O; o <= Bar.O;o++)
		 		{
					Dimension key = new Dimension(m,o);
							
					map_work.put(key,new ArrayList<Double>());
					
					m_map.put(key,0.0d);
					
		 		}
					
			});
			
			
			// Формирование.
			
				bars.forEach(b->{
			 		
			 		mas.forEach(m->
					{
						
					 		
			 		for (int o = Bar.O; o <= Bar.O;o++)
			 		{
			 					double avg= b.get_by_type(o);
				
			 					Dimension key = new Dimension(m,o);
			 				
			 					// Необходимо вставить OHLC.
			 					
			 					// Обходим основной цикл.
			 					
			 				if(m != gl.E_EMPTY)
			 				{
				 					
								if(map_work.get(key).size() == m) 
								{ 
									// Формируем значение средней на рассчетном массиве.
									 
									avg = map_work.get(key).stream().mapToDouble(p-> p).average().getAsDouble(); 
									 
									// Удаляем вершину из массива расчета.
									
									map_work.get(key).remove(gl.E_EMPTY); 
									
									// Добавляем в расчетный массив следующего БАРона.
									
									map_work.get(key).add(b.get_by_type(o));
									
									// Сохраняем результат на индексе.
									
									m_map.put(key,avg);
										 
								}   else 
								{ 
									// Аккумулятор.
									
									// Берем цену.
									
									avg = b.get_by_type(o);
									
									// Добавляем в массив для дальнейшего расчета.
									
									map_work.get(key).add(avg); 
									
									
									m_map.put(key,avg);
									 
								}
								
				 			} // Обход для OHLC.
			 				else
			 				{
			 						// Берем прайсы у БАРонов.
			 					
			 					 	m_map.put(key,avg);
			 				}
								
			 			} // o
			 		
					});// mas
			 		
			 			// Формируем копию для экспорта в глобальную таблицу.
			 		
			 			Map<Dimension,Double> m_copy = new LinkedHashMap<Dimension,Double>();
			 			
			 			m_map.forEach((k,v1)->
			 			{
			 				m_copy.put(k,v1);
			 			});
			 		
			 			
			 			// Экспорт.
			 			
			 			out.put(b.getDate(),m_copy);
			 			
			 				
			 	}); // bars
				 
						return true;
			}
			catch(Exception e)
			{
						e.printStackTrace();
						
						return false;
			}
		} 
		 
	
		public static String mas_to_str(Map<Date,Map<Dimension,Double>> mas )
		{


			StringBuilder sb = new StringBuilder();
		
			mas.forEach((k,v)->{
			
				
				Map<Dimension,Double> child_map = v;
				
				sb.append(gl.sf("%s ",DateUtil.to_string(k)));
				
				
				child_map.forEach((k1,v1)->{
					
					 String s = gl.sf("%d ",k1.width); 
					 
					 // Добавляем только типа МАШки без субтипа ПРАЙСа.
					 
					 if(k1.height == gl.E_EMPTY)
					    sb.append(s);
					 
					 String p = gl.sf("%s ",Su.fmt6d4(v1));
					 
					    sb.append(p);
					    
				});  // child_map.
				
					// Завершаем строку.
				
				 	sb.append(System.lineSeparator());
				
			});   // date walker.
					
					return sb.toString();
		}
		
		public static String cross_to_str(Map<Date,Map<Rectangle,Dimension>> crs )
		{


			StringBuilder sb = new StringBuilder();
		
			crs.forEach((k,v)->{
			
				
				Map<Rectangle,Dimension> child_map = v;
				
				sb.append(gl.sf("%s ",DateUtil.to_string(k)));
				
				
				child_map.forEach((k1,v1)->{
					
					 String s = gl.sf("%2d%2d %2d%2d [%1d %1d] | ",k1.x,k1.y,k1.width,k1.height,
							 v1.width,
							 v1.height
							 ); 
					 
					 sb.append(s);
					    
				});  // child_map.
				
					// Завершаем строку.
				
				 	sb.append(System.lineSeparator());
				
			});   // date walker.
					
					return sb.toString();
		}
		
		
		public static String mas_to_level_debug(Map<Date,Map<Dimension,Double>> mas )
		{

			if(mas.size() == gl.E_EMPTY)
			   return null;
			
			
			// Создаем индексную таблицу.
			
			List<Integer> kx = new ArrayList<Integer>();
			
			List<Integer> ki = Arrays.asList(new Integer [] {Bar.O/*,Bar.H,Bar.L,Bar.C*/});
			
			
			// Для этого нам необходима только одна запись.
			
			Map.Entry<Date,Map<Dimension,Double>> main_map  = mas.entrySet().stream().findFirst().get();

			Map<Dimension,Double> child_map = main_map.getValue();
			
			// Инициализация.
			
			child_map.keySet().forEach(k->{
			
				if(!kx.contains(k.width))
					kx.add(k.width);
				
			});
			
			StringBuilder sb = new StringBuilder();
		
			mas.forEach((k,v)->{
			
				
				Map<Dimension,Double> c_map = v;
				
				sb.append(gl.sf("%s ",DateUtil.to_string(k)));
				
				// Перебор ключей 
								
				kx.forEach(a->{
				
					ki.forEach(i->{
				 
					kx.forEach(b->
					{
							  	
							if (a < b)
							{
							
								double left = c_map.get(new Dimension(a,i));
					
								double right = c_map.get(new Dimension(b,i));
							
								int r = left > right ? 1 : left < right ? 2 : 0;
								
								String s = gl.sf("%d,%d,%d,%d,%1d ",
										
										a,i,b,i,r );
							
								sb.append(s);
							
							
							}
		
					 }); // kx
					
				   }); // ki
					    
				}); // kx
				
				
					// Завершаем строку.
				
				 	sb.append(System.lineSeparator());
				
			});   // date walker.
					
					return sb.toString();
		}


		public static boolean  make_levels(Map<Date,Map<Dimension,Double>> mas , Map<Date,Map<Rectangle,Integer>>  out )
		{

			
			try
			{
				
			
			if(mas.size() == gl.E_EMPTY)
			   return false;
			
			
			// Создаем индексную таблицу.
			
			List<Integer> kx = new ArrayList<Integer>();
			
			List<Integer> ki = Arrays.asList(new Integer [] {Bar.O/*,Bar.H,Bar.L,Bar.C*/});
			
			
			// Для этого нам необходима только одна запись.
			
			Map.Entry<Date,Map<Dimension,Double>> main_map  = mas.entrySet().stream().findFirst().get();

			Map<Dimension,Double> child_map = main_map.getValue();
			
			// Инициализация.
			
			child_map.keySet().forEach(k->{
			
				if(!kx.contains(k.width))
					kx.add(k.width);
				
			});
			
			
			// Инициализация рабочей мапы.
			
			Map<Rectangle,Integer> work_map  = new LinkedHashMap<Rectangle,Integer>();
	
			mas.forEach((k,v)->{
			
				
				Map<Dimension,Double> c_map = v;
				
				// Перебор ключей 
	
				kx.forEach(a->{
				
					ki.forEach(i->{
				 
					kx.forEach(b->
					{
							  	
							if (a < b)
							{
							
								double left  = c_map.get(new Dimension(a,i));
					
								double right = c_map.get(new Dimension(b,i));
							
								int r = left > right ? 1 : left < right ? 2 : 0;
					
								work_map.put(Ru.get_instance(a,i,b,i),r);
							
							}
		
					 }); // kx
					
				   }); // ki
					    
				}); // kx
				
				
					// Завершаем строку.
				
					// Формируем копию для экспорта в глобальную таблицу.
			
					Map<Rectangle,Integer> m_copy  = new LinkedHashMap<Rectangle,Integer>();
		 			
		 			work_map.forEach((k1,v1)->
		 			{
		 				m_copy.put(k1,v1);
		 			});
			 		
		 			
		 			// Экспорт.
		 			
		 			out.put(k,m_copy);
		 	
				 	
				
			});   // date walker.
					
					return (out.size() == mas.size());
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				return false;
			}
		}

		// TODO 09.01.2021 Under Construction.
		

		public static boolean  make_cross_v1(List<Bar> bars ,Map<Date,Map<Dimension,Double>> mas, Map<Date,Map<Rectangle,Integer>>  lvl, Map<Date,Map<Rectangle,Rectangle2D>>  out )
		{

			
			try
			{
				
			
			if(lvl.size() == gl.E_EMPTY)
			   return false;
			
			
			// Создаем индексную таблицу.
			
			// Для этого нам необходима только одна запись.
			
			Map.Entry<Date,Map<Rectangle,Integer>> main_map  = lvl.entrySet().stream().findFirst().get();

			Map<Rectangle,Integer> child_map = main_map.getValue();
			
			// Инициализация.
		
			// Кэш для предыдущей записи.
			
			List<Map<Rectangle,Integer>> p = new ArrayList<Map<Rectangle,Integer>>();
			
			// Инициализируем кэш первой записью из МАПы.
			
			p.add(child_map);
			
			// Инициализация рабочей мапы.
			
			Map<Rectangle,Rectangle2D> work_map  = new LinkedHashMap<Rectangle,Rectangle2D>();
	
			lvl.forEach((k,v)->{
			
				Map<Rectangle,Integer>  c_map = v;
				
				// Извлекаем цену открытия.

				double O = Bar.get_by_date(bars, k).getO();

				// Ходим по внутренней МАПе. 
	
				c_map.forEach((k1,v1)->{
					
					// Берем значение из КЭШа.
					
					int a = p.get(gl.E_EMPTY).get(k1);
					
					// Берем значение текущее.
					
					int b = c_map.get(k1);

									
					// Извлекаем  значение средней, активатора, левый аргумент.
					
					double A = mas.get(k).get(new Dimension(k1.x,k1.y));
					
							
					// Проводим сравнение.
					
					if( a != b)
					{
						// Поскольку событием является смена трэнда по указанному инструменту.
						
						// Фиксируем это , добавлением записи в таблицу пересечений.
						
						// В значении отмечаем в 1-м параметре, направление трэнда, во втором флаг наличия смены.
						
						work_map.put(k1,new Rectangle2D.Double(b,1,O,(A-O)) /* Флаг смены.*/);
						
					}
					else
					{
					
						work_map.put(k1,new Rectangle2D.Double(b,0,O,(A-O)) /*Изменений нет.*/);
						
					}
					
				});
		
					// Завершаем строку.
				
					// Обновляем КЭШ предыдущей записи.
				
					p.remove(gl.E_EMPTY);
					
					p.add(v);
					
					//p.add(gl.E_EMPTY,v);
				
				
					// Формируем копию для экспорта в глобальную таблицу.
			
					Map<Rectangle,Rectangle2D> m_copy  = new LinkedHashMap<Rectangle,Rectangle2D>();
		 			
		 			work_map.forEach((k1,v1)->
		 			{
		 				m_copy.put(k1,v1);
		 			});
			 		
		 			
		 			// Экспорт.
		 			
		 			out.put(k,m_copy);
		 	
				 	
				
			});   // date walker.
					
					return (out.size() == lvl.size());
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				return false;
			}
		}


		public static boolean  make_cross(Map<Date,Map<Rectangle,Integer>>  lvl, Map<Date,Map<Rectangle,Dimension>>  out )
		{

			
			try
			{
				
			
			if(lvl.size() == gl.E_EMPTY)
			   return false;
			
			
			// Создаем индексную таблицу.
			
			// Для этого нам необходима только одна запись.
			
			Map.Entry<Date,Map<Rectangle,Integer>> main_map  = lvl.entrySet().stream().findFirst().get();

			Map<Rectangle,Integer> child_map = main_map.getValue();
			
			// Инициализация.
		
			// Кэш для предыдущей записи.
			
			List<Map<Rectangle,Integer>> p = new ArrayList<Map<Rectangle,Integer>>();
			
			// Инициализируем кэш первой записью из МАПы.
			
			p.add(child_map);
			
			// Инициализация рабочей мапы.
			
			Map<Rectangle,Dimension> work_map  = new LinkedHashMap<Rectangle,Dimension>();
	
			lvl.forEach((k,v)->{
			
				Map<Rectangle,Integer>  c_map = v;
				
				
				// Ходим по внутренней МАПе. 
	
				c_map.forEach((k1,v1)->{
					
					// Берем значение из КЭШа.
					
					int a = p.get(gl.E_EMPTY).get(k1);
					
					// Берем значение текущее.
					
					int b = c_map.get(k1);
			
					// Проводим сравнение.
					
					if( a != b)
					{
						// Поскольку событием является смена трэнда по указанному инструменту.
						
						// Фиксируем это , добавлением записи в таблицу пересечений.
						
						// В значении отмечаем в 1-м параметре, направление трэнда, во втором флаг наличия смены.
						
						work_map.put(k1,new Dimension(b,1) /* Флаг смены.*/);
						
					}
					else
					{
					
						work_map.put(k1,new Dimension(b,0) /*Изменений нет.*/);
						
					}
					
				});
		
					// Завершаем строку.
				
					// Обновляем КЭШ предыдущей записи.
				
					p.remove(gl.E_EMPTY);
					
					p.add(v);
					
					//p.add(gl.E_EMPTY,v);
				
				
					// Формируем копию для экспорта в глобальную таблицу.
			
					Map<Rectangle,Dimension> m_copy  = new LinkedHashMap<Rectangle,Dimension>();
		 			
		 			work_map.forEach((k1,v1)->
		 			{
		 				m_copy.put(k1,v1);
		 			});
			 		
		 			
		 			// Экспорт.
		 			
		 			out.put(k,m_copy);
		 		
			});   // date walker.
					
					return (out.size() == lvl.size());
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				return false;
			}
		}

		public static boolean  make_cross_old(Map<Date,Map<Rectangle,Integer>>  lvl, Map<Date,Map<Rectangle,Dimension>>  out )
		{

			
			try
			{
				
			
			if(lvl.size() == gl.E_EMPTY)
			   return false;
			
			
			// Создаем индексную таблицу.
			
			// Для этого нам необходима только одна запись.
			
			Map.Entry<Date,Map<Rectangle,Integer>> main_map  = lvl.entrySet().stream().findFirst().get();

			Map<Rectangle,Integer> child_map = main_map.getValue();
			
			// Инициализация.
		
			// Кэш для предыдущей записи.
			
			List<Map<Rectangle,Integer>> p = new ArrayList<Map<Rectangle,Integer>>();
			
			// Инициализируем кэш первой записью из МАПы.
			
			p.add(child_map);
			
			// Инициализация рабочей мапы.
			
			Map<Rectangle,Dimension> work_map  = new LinkedHashMap<Rectangle,Dimension>();
	
			lvl.forEach((k,v)->{
			
				
				Map<Rectangle,Integer>  c_map = v;
				
				// Ходим по внутренней МАПе. 
	
				c_map.forEach((k1,v1)->{
					
					// Берем значение из КЭШа.
					
					int a = p.get(gl.E_EMPTY).get(k1);
					
					// Берем значение текущее.
					
					int b = c_map.get(k1);
					
					
					// Проводим сравнение.
					
					if( a != b)
					{
						// Поскольку событием является смена трэнда по указанному инструменту.
						
						// Фиксируем это , добавлением записи в таблицу пересечений.
						
						// В значении отмечаем в 1-м параметре, направление трэнда, во втором флаг наличия смены.
						
						work_map.put(k1,new Dimension(b,1 /* Флаг смены.*/));
						
					}
					else
					{
					
						work_map.put(k1,new Dimension(b,0 /*Изменений нет.*/));
						
					}
					
				});
		
					// Завершаем строку.
				
					// Обновляем КЭШ предыдущей записи.
				
					p.remove(gl.E_EMPTY);
					
					p.add(v);
					
					//p.add(gl.E_EMPTY,v);
				
				
					// Формируем копию для экспорта в глобальную таблицу.
			
					Map<Rectangle,Dimension> m_copy  = new LinkedHashMap<Rectangle,Dimension>();
		 			
		 			work_map.forEach((k1,v1)->
		 			{
		 				m_copy.put(k1,v1);
		 			});
			 		
		 			
		 			// Экспорт.
		 			
		 			out.put(k,m_copy);
		 	
				 	
				
			});   // date walker.
					
					return (out.size() == lvl.size());
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				return false;
			}
		}

		public static boolean make_orders(Quote quote,List<Bar> bars,Map<Date,Map<Rectangle,Dimension>> crs,List<MarketOrder> orders )
		{

			crs.forEach((k,v)->{
			
				Map<Rectangle,Dimension> child_map = v;
				
				child_map.forEach((k1,v1)->{
					
					Dimension state = v1;
					
					// Направление движения.
					
					int i_direction = state.width;
					
					int i_key 		= state.height;
					
					 Bar b = Bar.get_by_date(bars,k);
				
					// Фильтр.
					 
					// Ограничиваем выборку только типом [O].
					 
					 if(k1.height == 0 && k1.y == 0)
					
					 {
						 
					// Событие.
					
					if(i_key == 1 )
					{
						
						if(i_direction == 2 )
						{	
							// Инструмент проходит среднюю ВВЕРХ.
							
							// Все ранее открытые ордера на продажу д.б. закрыты.
							
							List<MarketOrder> sell_orders_list =  orders.stream().filter( 
									(s-> ( 
											 s.getInitiator().equals(new Dimension(k1.x,k1.y)) &&
											 s.getCloserator().equals(new Dimension(k1.width,k1.height)) &&
										
											 s.getType() == MarketOrder.SELL_MARKET && 
											 s.getOpen() != null && 
											 s.getOpen().before(k) && 
											 s.getClose() == null 
											))).collect(Collectors.toList()); 
														
							sell_orders_list.forEach(so-> 
							{ 			 
									 so.set_peak_and_drop(b);
								
									 // Strong close. 
									 
									 so.closeOrder(b);
								
									 // Audit audience.
									 
									 //so.auditOrder(bars);
									 
									
							}); 
							
							// Открываем ордера на покупку.
							
							MarketOrder order = MarketOrder.get_instance(quote, 
									  orders.size()+1, 
									  MarketOrder.BUY_MARKET, 
									  new Dimension(k1.x,k1.y), 
									  new Dimension(k1.width,k1.height), 
									  k, 
									  null, 
									  b.getO(), 
									  0.0
									  ); 
							 
							  order.set_peak_and_drop(b); 
								 
							  orders.add(order); 
							  
						}
						else if(i_direction == 1 )
						{
							// Инструмент проходит среднюю ВНИЗ.
							
							// Все ранее открытые ордера на покупку д.б. закрыты.
							
							List<MarketOrder> by_orders_list =  orders.stream().filter( 
									(bo-> ( 
											 bo.getInitiator().equals(new Dimension(k1.x,k1.y)) &&
											 bo.getCloserator().equals(new Dimension(k1.width,k1.height)) &&
											 
											 bo.getType() == MarketOrder.BUY_MARKET && 
											 bo.getOpen() != null 			 		&& 
											 bo.getOpen().before(k) 			 	&& 
											 bo.getClose() == null 
											))).collect(Collectors.toList()); 
														
							by_orders_list.forEach(bo-> 
							{ 			 
									 bo.set_peak_and_drop(b);
								
									 // Strong close. 
									 
									 bo.closeOrder(b);
								
									 // Audit audience.
									 
									// bo.auditOrder(bars);
									 
									
							}); 
							
							// Открываем ордера на продажу.
							
							MarketOrder order = MarketOrder.get_instance(quote, 
									  orders.size()+1, 
									  MarketOrder.SELL_MARKET, 
									  new Dimension(k1.x,k1.y), 
									  new Dimension(k1.width,k1.height), 
									  k, 
									  null, 
									  b.getO(), 
									  0.0 
									  ); 
							 
							  order.set_peak_and_drop(b); 
								 
							  orders.add(order); 
							  
							
						}
						
					} else
					{
						// Событий нет, движение по инерции.
						
					    // Обновление массива ордеров.
						
						List<MarketOrder> open_orders =  orders.stream().filter( 
								(o-> (o.getClose() == null) && o.getOpen() != null)).collect(Collectors.toList()); 
			 
						
						open_orders.forEach(o-> 
						{ 
							o.set_peak_and_drop(b); 
							 
						} 
						);  
						
					} // Глобальный If.
					
				} // Фильтр.
					
					
				});  // child_map.
				
					// Завершаем строку.
				
			});   // date walker.
					
					return (orders.size() != gl.E_EMPTY);
		}
		
		public  static String orders_to_str(List<MarketOrder> orders) 
		{ 
		 
			StringBuilder sb = new StringBuilder(); 
			 
			int [] counter = { gl.E_OK };
			
			orders.forEach(b -> { 
			 
				sb.append(gl.sf("%5d %s",counter[gl.E_EMPTY],b.toString())); 
				 
				sb.append(System.lineSeparator());
				
				counter[gl.E_EMPTY]++;
			 
			}); 
				
				return sb.toString(); 
				 
		} 
		
		public static String orders_total_row_to_str(List<MarketOrder> lo ) 
		{ 
			StringBuilder store = new StringBuilder();
			
			try 
			{ 
				
			long start = System.nanoTime(); 
			 
			double profit_t   = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).sum(); 
			 
			 
			double loss_t    = lo.stream().filter(a->a.getProfit()<0 ).mapToDouble(p->p.getProfit()).sum(); 
			
			
			 
			double result_t    = lo.stream().mapToDouble(p->p.getProfit()).sum(); 
			
			// Add-ins.
			
			double profit_avg  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
		
			double profit_max  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit()).max().getAsDouble(); 
			
			double profit_dropdown_max  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
			
			double profit_dropdown_avg  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_down()).average().getAsDouble(); 
			
			double profit_up_max  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_up()).max().getAsDouble(); 
			
			double profit_up_avg  = lo.stream().filter(a->a.getProfit()>=0).mapToDouble(p->p.getProfit_up()).average().getAsDouble(); 
			
			
			int  profit_duration_avg  	= (int)lo.stream().filter(a->a.getProfit() >=0).mapToLong(m->m.getDuration()).average().getAsDouble();
			
			
			/*
			double max_lose_profit  = lo.stream().filter(a->a.getProfit()<0).mapToDouble(p->p.getProfit()).max().getAsDouble(); 
			
			double max_lose_profit_dropdown  = lo.stream().filter(a->a.getProfit()<0).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
			
			double avg_loss	    = (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit()<0 ).mapToDouble(p->p.getProfit()).average().getAsDouble();
			
			double max_lose  	= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit()<0).mapToDouble(p->p.getProfit()).min().getAsDouble(); 
			
			int  avg_duration_lose  	= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : (int)lo.stream().filter(a->a.getProfit() <0).mapToLong(m->m.getDuration()).average().getAsDouble();

			*/
			

			double lose_avg  			= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit()).average().getAsDouble(); 
		
			double lose_max  			= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit()).min().getAsDouble(); 
			
			double lose_dropdown_max  	= lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit_down()).max().getAsDouble(); 
			
			double lose_dropdown_avg  	= lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit_down()).average().getAsDouble(); 
			
			double lose_up_max  = lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit_up()).max().getAsDouble(); 
			
			double lose_up_avg  = lo.stream().filter(a->a.getProfit() < 0).mapToDouble(p->p.getProfit_up()).average().getAsDouble(); 
			
			
			int    lose_duration_avg  	= (loss_t == gl.E_EMPTY) ? gl.E_EMPTY : (int)lo.stream().filter(a->a.getProfit() < 0).mapToLong(m->m.getDuration()).average().getAsDouble();
		
			
			long profit_cnt_t   = lo.stream().filter(a->a.getProfit()>=0).count(); 
			 
			long loss_cnt_t    = lo.stream().filter(a->a.getProfit()<0).count(); 
					 
			long result_cnt_t    = lo.stream().count(); 
			
			double profit_factor   	= 0.0d;
			
			if(loss_t==gl.E_EMPTY)
			{	
				profit_factor = profit_t*10000;
			}
			else if(loss_t <=gl.E_EMPTY)
				profit_factor = (profit_t*10000)/(loss_t*(-10000));	
			
			long l_profit_factor = Math.round(profit_factor);
			
			/*
			String result_msg = gl.sf("\r\nProfit : %s / %05d Avg : %.4f Days : %3d  Max peak : %s Max drop : %s Avg drop : %s\r\nLoss   : %s / %05d Avg : %.4f Days : %3d  Min drop : %s \r\nResult : %s / %05d  \nFactor : %12d\n", 
					gl.norm4f_base_12(profit_t), 
					profit_cnt_t, 
					profit_avg,
					profit_duration_avg,
					gl.norm4f_base_12(profit_max),
					gl.norm4f_base_12(profit_dropdown_max),
					gl.norm4f_base_12(profit_dropdown_avg),
					(loss_t < gl.E_EMPTY) ? gl.norm4f_base_12(loss_t*gl.E_ERROR) : gl.norm4f_base_12(0.0d), 
					loss_cnt_t,
					(avg_loss < gl.E_EMPTY) ? (avg_loss*gl.E_ERROR) : gl.E_EMPTY, 
					avg_duration_lose,
					gl.norm4f_base_12(max_lose),
					gl.norm4f_base_12(result_t), 
					result_cnt_t,
					l_profit_factor); 
			*/
		
			String msg = gl.sf("\r\n"
					+ "Прибыльных : %s / %05d Среднее : %.4f Дней  : %3d Максимум : %s Просадка : %s Средняя просадка : %s Вынос : %s  Средний вынос %s\r\n"
					+ "Упущенных  : %s / %05d Среднее : %.4f Дней  : %3d Максимум : %s Просадка : %s Средняя просадка : %s Вынос : %s  Средний вынос %s\r\n"
					+ "Результат  : %s / %05d Фактор  : %12d",
				
					gl.norm4f_base_12(profit_t), 
					profit_cnt_t, 
					profit_avg,
					profit_duration_avg,
					gl.norm4f_base_12(profit_max),
					
					gl.norm4f_base_12(profit_dropdown_max),
					gl.norm4f_base_12(profit_dropdown_avg),
					
					gl.norm4f_base_12(profit_up_max),
					gl.norm4f_base_12(profit_up_avg),
					
					gl.norm4f_base_12(loss_t*gl.E_ERROR),
					loss_cnt_t,
					lose_avg < 0 ? lose_avg*gl.E_ERROR : lose_avg, 
					lose_duration_avg,
					
					gl.norm4f_base_12(lose_max),
					
					gl.norm4f_base_12(lose_dropdown_max),
					gl.norm4f_base_12(lose_dropdown_avg),
					
					gl.norm4f_base_12(lose_up_max),
					gl.norm4f_base_12(lose_up_avg),
					
					
					gl.norm4f_base_12(result_t), 
					
					result_cnt_t,
					l_profit_factor); 
		
					store.append(msg); 
			
					return store.toString(); 
			
			} 
			catch(Exception e) 
			{ 
				e.printStackTrace();
				
			    return null; 
			} 
			 
		} 
		
}
