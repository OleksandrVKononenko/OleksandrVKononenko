package ap.orion.action;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ap.btn.Bar;
import ap.explorer.Brew;
import ap.explorer.MarketOrder;
import ap.explorer.Quote;
import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.uat.ApplicationA;
import ap.utils.DU;
import ap.utils.DialogUtil;
import ap.utils.Fu;
import ap.utils.Nu;
import ap.utils.Su;
import java.awt.geom.*;


public class ReadAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"read","read()","читать"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public ReadAction() {
		
	}

	public ReadAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public ReadAction(String text) {
		super(text);
		
	}

	public ReadAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	

	public boolean isa_for_impl(int count)
	{
	
		try
		{
		
			List<Boolean> state = new ArrayList<Boolean>();
			
			for(int i=0;i<count;i++)
			{
	
				boolean bl_run = Application.getRio().read();
						
						state.add(bl_run);
				
		    	gl.tx(new Object() {},bl_run,gl.sf("Клонирование...Цикл...[%d]",i));
		    	
		    	
			}
		
				return !state.contains(false);
		}
		catch(Exception e)
		{

				return gl.tx(new Object() {},false,gl.sf("%s",e.toString()));
		
		}

	}
	

	public boolean isa_quote_impl(List<String> m_files)
	{
		
		try
		{
			
	
			// Загрузка в секцию данных селектированного объекта.
			
			if(!check_selected())
			{
				
				return false;
			}
			
			List<Boolean> state = new ArrayList<Boolean>();
			
			// Цикл.
			
			//m_files.forEach(source -> {
			
			for (String source : m_files)
			{
			
			// Файл  источник данных должен существовать.
			
			if(!check_file(source))
				return false;
			
			// Массив строк из файла.
			
			List<String> ds = Fu.get_list_from_file(source);
			
			if(!check_list(ds))
				return false;
			
			// Проверка заголовка файла данных.
		
			if(!check_header_syntax(ds.get(gl.E_EMPTY)))
				return false;
			
			final String data = ds.stream().collect(Collectors.joining(gl.sf("%s",Parser.ITEMS_DLM)));
			
			if(!check_str(data))
				return false;
			
			// Заголовок данных.
			
			final String header = Su.AfterAt(ds.get(gl.E_EMPTY),Parser.HEADER);
			
			// Установка.
			
				try
				{
			
					CollectionImpl.selected.forEach(a->{
						
						if(a.getData().getHeaders() == null || !a.getData().getHeaders().contains(header))
						{
									
							a.getData().setHeaders(Su.append_header_record(a,header));
							
							a.getData().setData(Su.append_data_record(a.getData().getData(), data));
								
						}
						else
						{
							String msg = gl.sf("Заголовок уже обработан...[%s]...файл...[%s]%s",header,source,System.lineSeparator());
							
							Fu.to_file_x("a_dublicate.txt",msg,true);
					
							gl.tx_k(new Object() {},msg);
		
						}
						
						
					}); // Цикл по селектированным объектам.
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
					return false;
				}
			
			} //); // Цикл по файлам.
			
			// Тестовая выгрузка в файл.

			
			CollectionImpl.selected.forEach(a->{
		
				a.getDecoro().set_text(
						Su.get_str_from_list(CollectionImpl.get_tickers_from_headers(a.getData().getHeaders())," ")
						);
				
				a.setFont(new Font("Tahoma",Font.BOLD,28));
				
				a.repaint();
				
				Fu.to_file(gl.sf("a_check_%d.txt",a.getIdo().getId()),a.getData().getData());

				Fu.to_file(gl.sf("a_headers_%d.txt",a.getIdo().getId()),a.getData().getHeaders());
			
				// Тестирование серий.
				/*
				Fu.to_file(gl.sf("a_series_%d.txt",a.getIdo().getId()),
						Su.get_str_from_list_for_data(
								CollectionImpl.get_series_from_headers(a.getData().getHeaders())
								));
				*/
				
				// Тестирование БАРонов.
			
				
				CollectionImpl.get_series_from_headers(a.getData().getHeaders()).forEach(b->{
					
					int 	index = DU.to_int(Su.AfterAt(b,gl.sf("%s",Parser.ATTR_DLM)));
					
					String 	series = Su.BeforeAt(b,gl.sf("%s",Parser.ATTR_DLM));
					
					String 	data = Su.AfterAtFirst( Su.get_as_list_for_quote_file(
							
							a.getData().getData()
							
							).get(index),gl.sf("%s",Parser.ITEMS_DLM));

					List<String> 	data_in_param = Su.get_as_list_for_data(data);
										 
					List<Bar> 		bars = new ArrayList<Bar>();
					
					gl.sfn("-----> [%s]...type...[%s]",data_in_param.get(1),Su.get_date_format(data_in_param.get(1)) );
					
					String  data_probe = data_in_param.get(1);
					
					String file_fmt = Su.get_date_format(data_probe);  
					
					/*
					if(Su.in(file_fmt,new String[]{"fibo","normal"}))
					{
						
					
					long 			st = System.nanoTime();
					
					gl.tx(  new Object(){},
							 
							 ap.mercury.explorer.Util.make_bar_items(data_in_param,bars,file_fmt),
					
							 gl.sf("Формирование БАРов...[%5d][%3d] ms.",bars.size(),gl.t_end(st))
							 
							);
					 
					// Bar.write(gl.sf("a_bars_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),bars);
					
					Map<Date,Map<Dimension,Double>> mas = new LinkedHashMap<Date,Map<Dimension,Double>>();
					
					st = System.nanoTime();
							
					gl.tx(  new Object(){},
							 
							ap.mercury.explorer.Util.make_mas(
									bars,
									Arrays.asList(new Integer[] {0,3,21,55}),
									mas
									),
									 
							 gl.sf("Формирование МАШек...[%5d][%3d] ms.",bars.size(),gl.t_end(st))
							 );
					
					
					Map<Date,Map<Rectangle,Integer>> lvls =  new LinkedHashMap<Date,Map<Rectangle,Integer>>();  
					 
					st = System.nanoTime();
							
					gl.tx(  new Object(){},
							 
							ap.mercury.explorer.Util.make_levels(
										mas,
										lvls
									),
									 
							 gl.sf("Формирование УРОей...[%5d][%3d] ms.",lvls.size(),gl.t_end(st))
							 );
					
					Map<Date,Map<Rectangle,Dimension>> crss =  new LinkedHashMap<Date,Map<Rectangle,Dimension>>();  
					
					st = System.nanoTime();
							
					gl.tx(  new Object(){},
							 
							ap.mercury.explorer.Util.make_cross(
									
										lvls,
										crss
										
									),
									 
							 gl.sf("Формирование КРОов...[%5d][%3d] ms.",crss.size(),gl.t_end(st))
							 );
					
					List<MarketOrder> orders = new ArrayList<MarketOrder>();
					
					st = System.nanoTime();
					
					gl.tx(  new Object(){},
							 
							ap.mercury.explorer.Util.make_orders(
									    Quote.get_instance(Su.BeforeAt(series,Parser.ATTR_DLM),"path",0L,null,4,Brew.indexOf("AVA")),
										bars,
										crss,
										orders
										
									),
							 gl.sf("Формирование ОРДов...[%5d][%3d] ms.",orders.size(),gl.t_end(st))
							 
							 );
					
					
					// Выгрузка МАШ в файл.
					
					Fu.to_file(gl.sf("a_mas_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),
							
							ap.mercury.explorer.Util.mas_to_str(mas)
							
							);
					
					// Выгрузка УРОВНЕЙ в файл.
					
					Fu.to_file(gl.sf("a_lvl_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),
							
							ap.mercury.explorer.Util.mas_to_level_debug(mas)
							
							);
					
					
					// Выгрузка КРОССОВ в файл.
					
					Fu.to_file(gl.sf("a_crs_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),
							
							ap.mercury.explorer.Util.cross_to_str(crss)
							
							);
					
					// Выгрузка ОРДЕРОВ в файл.
					
					Fu.to_file(gl.sf("a_orders_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),
							
							ap.mercury.explorer.Util.orders_to_str(orders)
							
							);
					
					// Добавляем ИТОГОВУЮ  строку в файл.
					
					Fu.to_file(gl.sf("a_orders_%s_%d.txt",series.replace(",","_"),a.getIdo().getId()),
							
							ap.mercury.explorer.Util.orders_total_row_to_str(orders),
							
							// Флаг добавления в файл.
							
							true
							
							);
					
					} // Проверка формата файла.	
					else
					{
						DialogUtil.doError(
								gl.sf("Не поддерживаемый формат данных файла...[%s][%s]",
								file_fmt,
								data_probe
								));
					}
					
					*/
					
			
				});
			});
			
					return !state.contains(false);
			
		}
		catch(Exception e)
		{
				
				e.printStackTrace();
				
				return false;//gl.tx(new Object() {},false,gl.sf("%s",e.toString().substring(1, 32)));
			
		}
	
	}
	
	public boolean isa_main_impl()
	{
		

			CmdProcess p = this.getProcess();
		
			String m_home_prev = Fu.get_file_name(Application.getCio().getHome());

		try
		{
	
			List<String> m_files = new ArrayList<String>();
			
			if(p.isa_dlg_option())
			{
				m_files = Fu.files_open_dlg(m_home_prev);
			
				if(!check_list_caption("Проверка открытых файлов с диалога",m_files))
					return false;
			}
			
			// Check startup option.
			
			if(p.isa_startup_option())
			{

				if(m_files.get(gl.E_EMPTY) != null)
				{
					Application.getCio().setStartup_composite(m_files.get(gl.E_EMPTY));
				
					gl.tx_k(
		    			new Object() {},gl.sf("Активация стартап формы...[%s]",Application.getCio().getStartup_composite())
		    			);
				}
			}
			
			
			String m_home 		=  (p.isa_dlg_option() && m_files.size() == gl.E_OK) ? 
					                m_files.get(gl.E_EMPTY) : 
					                p.get_param_by_index(gl.E_EMPTY);
		
			// Второй параметр,- количество чтений, используется для клонирования объекта.
			
			int m_count_of_repeat = p.isa_for_option() ? (DU.to_int(p.get_param_by_index(gl.E_OK))) == gl.E_ERROR ? (DU.to_int(p.get_param_by_index(gl.E_EMPTY))) : DU.to_int(p.get_param_by_index(gl.E_OK)) : 1;
			
		
			if(p.isa_quote_option())
			{
				m_count_of_repeat = gl.E_OK;
				
			}
			
			// Подмена файла.
    	
	    	gl.tx_k(new Object() {},gl.sf("Файл для чтения...[%s]..Циклов...[%d]",
	    			m_home == null ? Application.getCio().update(m_home_prev) : 
	    			Fu.isa_abs_path(m_home) ? Application.getCio().update_abs(m_home) : Application.getCio().update(m_home),
	    			m_count_of_repeat
	    			));
		
	    	
	    	if(p.isa_quote_option())
	    	{
	    		return isa_quote_impl(m_files);
	    		
	    	} else 
	        {
	    		
	    		// Проверяем наличие в параметре разделителей,- значение типа [t1,t2,t3...]
	    		
	    		// В случае одного параметра ,- данные уже в m_home.
	    		
	    		if(p.get_count_of_params() == gl.E_OK)
	    		{
	    			if(Su.isa_any_of_b(m_home,CmdProcess.delim_switches))
	    			{
	    				String m_clear = Su.remove_delims(m_home,CmdProcess.delim_switches,' ');
	    		
	    				gl.sfn("...Очищеная строка...[%s]",m_clear);
	    				
	    				if(Su.isa_any_of_b(m_clear,CmdProcess.delim_switches))
		    			{

		    				gl.sfn("...Ошибка очистки строки...[%s]",m_clear);
		    		
		    				return false;
		    			}
	    				
	    				String [] arr = m_clear.split(" ");
	    				
	    				// Добавляем файлы в коллекцию m_files.
	    	    		
	    				m_files.clear();
	    				
	    				m_files.addAll(Arrays.asList(arr));
	    			}
	    			else
	    			{	
	    				m_files.clear();
	    				
	    				m_files.addAll(Su.clear_params(p.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"))));
	    		
	    			}
	    			
	    		} else if(p.get_count_of_params() > gl.E_OK)
	    		{
	    			// Если строка параметров содержит значения без разделителей,- это файл.
	    			
	    			List<String> 	m_params = p.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

	    			String params   = m_params.stream().collect(Collectors.joining(" "));
	    			
	    			if(Su.isa_any_of_b(params,CmdProcess.delim_switches))
	    			{
	    				m_params = Su.clear_params(m_params);
	    			}
	    			else
	    			{
	    				// Работаем с файлом с пробелами в наименовании.
	    				
	    				m_params.clear();
	    				
	    				m_params.add(params);
	    			}
	    			
	    			//m_params.forEach(a->{
	    				
	    			//	gl.sfn("---> Params...[%s]",a);
	    				
	    			//});
	    			
	    			// Добавляем файлы в коллекцию m_files.
	    	    		
	    				m_files.clear();
	    				
	    				m_files.addAll(m_params);
			
	    		}
	    		
	    		// Контроль ошибок.
	    		
	    		List<Boolean> state = new ArrayList<Boolean>();
	    		
	    		// Сохраняем.
	    		
	    		String m_prev_home = Application.getCio().getHome();
	    	
	    		int [] count = { m_count_of_repeat };
	    		
	    		m_files.forEach(a->{
	    		
		    		Application.getCio().setHome(a);
		    		
		    		state.add(isa_for_impl(count[0]));
		    		
	    		});
	    		
	    		// Восстанавливаем.
	    		
	    		Application.getCio().setHome(m_prev_home);
	    		
	    		return !state.contains(false);
	        }
	    		
	    		
		}
		
		catch(Exception e)
		{
				e.printStackTrace();
				
				return gl.tx(new Object() {},false,gl.sf("[%s]",e.toString()));
			
		}
		
		finally
		{
				gl.tx_k(new Object() {},gl.sf("Восстановление имени репозитария...%s",Application.getCio().update(m_home_prev)));
		}
			
		
	
	}
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
		this.setProcess(process);

		welcome();		
	
		if(
				this.getProcess().get_count_of_headers() == gl.E_OK
		  )
		
		{	
				return isa_main_impl();
		} 
		
		
				return this.check_flow("Level::0",false);
		
		 
	} 
	
	

	public static ReadAction get_instance()
	{
		ReadAction lafa = new ReadAction(); 
		
		return new ReadAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
