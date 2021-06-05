package ap.orion.action;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import ap.area.AreaManager;
import ap.global.gl;
import ap.mercury.components.Gimension;
import ap.mercury.components.Golor;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.intf.IAlign;
import ap.orion.live.Live;
import ap.orion.lsnr.BrewActionListener;
import ap.prop.SBounds;
import ap.prop.SColor;
import ap.prop.SDimension;
import ap.utils.CU;
import ap.utils.DU;
import ap.utils.DialogUtil;
import ap.utils.MapUtils;
import ap.utils.Nu;
import ap.utils.ParseUtil;
import ap.utils.Su;


public class PropAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"prop","prop()","p","p()","проп"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public PropAction() {
		
	}

	public PropAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public PropAction(String text) {
		super(text);
		
	}

	public PropAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_bkg_option_impl()
	{
		if(!check_selected())
		{
			return false;
		}
		
		// Читаем параметр.

		List<String> 	m_params = this.getProcess().isa_dlg_option() ? null : this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			param = this.getProcess().isa_dlg_option() ? null : m_params.get(gl.E_EMPTY);

		
		try
		{
					Color [] bkg =  { new Color(0,0,0,0) };
			
					 // Формочка.
					
					if(this.getProcess().isa_dlg_option())
					{
						bkg[0] = DialogUtil.get_color(Color.white);
					}
					else
					{
						gl.tx_k(new Object(){},gl.sf("Обработка параметра...%s",param));		
	
						bkg[0] = ap.mercury.components.parser.Color.get_instance(param).getColor();

					}
					
					
					if (!gl.tx(new Object(){},(bkg[0] != null),gl.sf("Выбран цвет...%s",
							SColor.toString(bkg[0]))))
					{
						
						return false;
					}
		
					
					CollectionImpl.selected.forEach(a->{
			
						a.setBackground(bkg[0]);
						
						a.repaint();
			
					});
			
						return true;
					
		}
		catch(Exception e)
		{
					return false;
		}

	}
	
	public boolean isa_frg_option_impl()
	{
		if(!check_selected())
		{
			return false;
		}
		
		// Читаем параметр.

		List<String> 	m_params = this.getProcess().isa_dlg_option() ? null : this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			param = this.getProcess().isa_dlg_option() ? null : m_params.get(gl.E_EMPTY);

		
		try
		{
					Color [] bkg =  { new Color(0,0,0,0) };
			
					 // Формочка.
					
					if(this.getProcess().isa_dlg_option())
					{
						bkg[0] = DialogUtil.get_color(Color.white);
					}
					else
					{
						gl.tx_k(new Object(){},gl.sf("Обработка параметра...%s",param));		
	
						bkg[0] = ap.mercury.components.parser.Color.get_instance(param).getColor();

					}
					
					
					if (!gl.tx(new Object(){},(bkg[0] != null),gl.sf("Выбран цвет...%s",
							SColor.toString(bkg[0]))))
					{
						
						return false;
					}
		
					
					CollectionImpl.selected.forEach(a->{
			
						a.getDecoro().setTextColor(bkg[0]);
						
						a.setForeground(bkg[0]);
						
						a.getChild().setForeground(bkg[0]);
			
						a.repaint();
						
					});
			
						return true;
					
		}
		catch(Exception e)
		{
					return false;
		}

	}
	

	public boolean isa_text_option_impl()
	{
		if(!check_selected())
		{
			return false;
		}
		
		// Читаем параметр.

		List<String> 	m_params = this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			[] m_param = {m_params.get(gl.E_EMPTY)};
		
		try
		{
					
					if(m_params.size() > gl.E_OK)
					{
						m_param[0] = m_params.stream().collect(Collectors.joining(" "));
						
						gl.tx_k(new Object(){},gl.sf("Упаковка параметров."));		
						
						if(m_param[0].length() > 255)
						{
							m_param[0] = m_param[0].substring(0,255);
									
							gl.tx_k(new Object(){},gl.sf("Ограничение длины...%s",m_param[0]));		
								
						}
					}
			
					gl.tx_k(new Object(){},gl.sf("Установка текста...%s",m_param[0]));		
		
		CollectionImpl.selected.forEach(a->{
			
					if(m_param[0].equalsIgnoreCase("null") || m_param[0].equalsIgnoreCase("empty"))
						m_param[0] = "";
						
					a.set_text_router(m_param[0]);
					
					a.repaint();
			
		});
		
					//Application.getDio().get_desk_top().repaint();
			
					return true;
					
		}
		
		
		catch(Exception e)
		{
					return false;
		}

	}
	
	public boolean isa_action_option_impl()
	{
		if(!check_selected())
		{
			return false;
		}
		
		try
		{
					CmdProcess p = this.getProcess();
	
					List<String> 	m_params = p.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

					String 			[] m_param = {""};//{m_params.get(gl.E_EMPTY)};
					
					// Определяемся в пространстве хэдэров.
					
					int m_headers_count = p.getHeaders_index().size();
					
					if(m_headers_count > gl.E_OK)
					{
						// Сборка команды.
						
						String m_cmd = gl.sf("%s %s",
								
								p.getHeaders_index().get(gl.E_OK),
								Su.get_str_from_list(p.getHeaders().get(p.getHeaders_index().get(gl.E_OK)))
								);
						
						m_cmd = m_cmd.replace(gl.sf("(%d)",0),"");
								
						// gl.sfn("---> Команда [%s]",m_cmd);
						
						m_param[0] = m_cmd;
					}
						
					//gl.tx_k(new Object(){},gl.sf("Установка команды...%s",m_param[0]));	
					
					CollectionImpl.selected.forEach(a->{
						
						gl.tx_k(new Object(){},gl.sf("Установка команды...%s...для...%s...результат...%s",
								m_param[0],
								a.getName(),
								a.set_action_router(m_param[0])));	
						
					});
					
					return true;
		}
		catch(Exception e)
		{
		
					gl.tx_e(new Object(){},gl.sf("%s",e.getMessage()));	
			
					return false;
		}
	}	


	public boolean isa_grid_option_impl()
	{

		if(!check_selected())
		{
			return false;
		}
		
		try
		{
	
					CmdProcess 		p = this.getProcess();
			
					List<String> 	m_params = p.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

					int m_params_cnt   = m_params.size();
					

					/*
					m_params.forEach(a->
					{
						gl.sfn("Parameter...[%s]",a);
						
					});
					*/
					
					boolean bl_null = false;
					
					// По умолчанию 1-й параметр.
					
					String 			m_param_text = m_params.get(gl.E_EMPTY);
					
					
					if(m_params_cnt == gl.E_OK && ( m_param_text.equalsIgnoreCase("0") || m_param_text.equalsIgnoreCase("null")))
					{
						
						bl_null = true;
					}
					
					else
					{
						
						if(m_params_cnt > gl.E_OK && !m_param_text.contains(gl.sf("%s",Parser.FIELD_DLM)))
						{
							m_param_text = m_params.stream().collect(Collectors.joining(gl.sf("%s",ap.mercury.components.parser.Parser.FIELD_DLM)));
						}
						else if (m_params_cnt > gl.E_OK && m_param_text.contains(gl.sf("%s",Parser.FIELD_DLM)))
						{
							m_param_text = m_params.stream().collect(Collectors.joining(""));
						}
					
					}
					
					
					ap.mercury.components.parser.Dimension [] m_target_gim = {null};
					
					// В случае обнуления ,- обновляем параметры.
					
					if(bl_null)
					{
						m_param_text = gl.sf("0,0,0,0");
					}
					
					
					ap.mercury.components.parser.Dimension 	dimension = ap.mercury.components.parser.Dimension.get_instance(m_param_text);
					
					String m_msg = gl.sf("Парсинг команды...%s...параметр...[%s]",p.getText(),m_param_text);
								
					if(!gl.tx(new Object(){},dimension.getState() >  gl.E_EMPTY,m_msg))	
					{		
						this.setResponseHelper(new String[] {m_msg});
						
						return false;
					}

					m_target_gim[0] = dimension;
					
					CollectionImpl.selected.forEach(a->{
						
						/*
						gl.tx_k(new Object(){},gl.sf("Установка грида...%s...для...%s",
								SDimension.toString(m_target_gim[0].getDimension()),
								a.getName()
								));	
						*/
					
						if(m_target_gim[0].is_empty())
						{
							a.getDecoro().setGrids(null);
							
							a.getTemp().getDims().clear();
						}
						else
						{
							List<ap.mercury.components.parser.Dimension> l = new ArrayList<ap.mercury.components.parser.Dimension>();
							
							if (a.getDecoro().getGrids() != null)
							{
								ap.mercury.components.parser.Dimension.parse(a.getDecoro().getGrids(),l);
							
								l.add(m_target_gim[0]);
								
								a.getDecoro().setGrids(ap.mercury.components.parser.Dimension.toString(l));
								
								a.getTemp().getDims().addAll(l);
									
							}
							else
							{
								a.getDecoro().setGrids(m_target_gim[0].toString());
								
								gl.tx_k(new Object(){},gl.sf("Инициализация параметра...%s...для...%s",
										m_target_gim[0].toString(),
										a.getName()
										));	
							}
			
						}	
							
						
					});
					
								// Desktop repaint.
					
								Application.getDio().get_desk_top().repaint();
					
								return true;
		}
		catch(Exception e)
		{
		
			gl.tx_e(new Object(){},gl.sf("Exception...%s",e.toString()));
					
			return false;
		}
	}	


	
	public boolean isa_border_option_impl()
	{

		if(!check_selected())
		{
			return false;
		}
		
		try
		{
	
					List<String> 	m_params = this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

					String 			[] m_param = {m_params.get(gl.E_EMPTY)};
			
					int m_params_count = m_params.size();
					
					// Create pure list params without delimiters.
					
					//  BorderFactory.createEtchedBorder(), "My Demo Table", TitledBorder.LEFT, TitledBorder.TOP)
					
					// Парсинг параметров.
					
					// Ожидаем 4 параметра.
				
					List<String> after = this.getProcess().remove_delims(m_params);
					
					Su.show("Before",m_params);
					
					Su.show("After",after);
					
					
					
					return true;
		}
		catch(Exception e)
		{
					return false;
		}
	}	
	
	public boolean isa_hdr_set_option_impl()
	{
		
					gl.tx(new Object(){},true,gl.sf("Установка глобального заголовка данных"));
		
					// Установка глобального заголовка данных.

		try
		{
	
					List<String> 		m_params 		= this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
				
					String 				m_header_value 	= m_params.get(gl.E_EMPTY).trim();
					
					Live.header_type 		= m_header_value;
					
					boolean bl_result =  Live.header_type.equalsIgnoreCase(m_header_value);/* && Live.header_type.startsWith(Parser.HEADER);*/
							
					String m_msg = gl.sf("Установка заголовка данных...[%s][%s]",Live.header_type,bl_result);
					
					this.setResponseHelper(new String[] {m_msg});
							
					return gl.tx(new Object(){},bl_result,m_msg);
					
		}
		catch(Exception e)
		{
					return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
		}
			
	}
	
	public boolean isa_hdr_show_option_impl()
	{
		
		gl.tx(new Object(){},true,gl.sf("Просмотр текущего глобального заголовка данных"));
		
		// Просмотр текущего глобального заголовка данных.
		
		try
		{
					
					// Не обязательно д.б. селектированы объекты.
					
					if(!this.check_selected() && !this.check_one_selected())
					{
						// Показать текущий глобальный заголовок.
						
						String m_msg = gl.sf("Текущий глобальный заголовок...[%s]",Live.header_type == null ? "[null]" : Live.header_type);
						
						this.setResponseHelper(new String[] {m_msg});
				
						return gl.tx(new Object(){},true,m_msg);		
						
					}
					
					// Из селектированных выбираем потенциального носителя заголовков.
					
					Orion source = CollectionImpl.get_by_class_name(CollectionImpl.selected,"Orion");
					
					// Проверка заголовка. 
					
					if(!this.check_data_header(source))
					{
						return false;
					}
									
					List<String> m_headers = Su.get_as_list(source.getData().getHeaders(),gl.sf("%s",Parser.ITEMS_DLM));
					
					this.setResponse(m_headers);
					
					return true;
		}
		catch(Exception e)
		{
					return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
		}
	

		
	}
	
	public boolean isa_hdr_out_option_impl()
	{

		gl.tx(new Object(){},true,gl.sf("Экспорт заголовка данных"));
		
		try
		{
	
					List<String> 		m_params 		= this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
					
					// Проверки селектирования.
					
					if(!this.check_selected() || ! this.check_two_selected())
						return false;
					
					// Для операции необходимо 2 контрагента.
					
					Orion source = CollectionImpl.get_by_class_name(CollectionImpl.selected,"Orion");
					
					// Заголовок экспортируем в таргет,- ComboBox,List,Table etc.. 
					
					List<Orion> target = CollectionImpl.get_by_class_name(CollectionImpl.selected,new String[] {"ComboBox"});
					
					
					// Проверка объектов.
					
					if(!this.check_object(source) || !this.check_objects(target)  )
						return false;
					
					// Проверка заголовка данных на источнике . 
					
					if(!this.check_data_header(source))
					{
						return false;
					}
					
									
					List<String> m_headers = Su.get_as_list_for_data(source.getData().getHeaders());
						
					// TODO 27.12.2020 set_data_router start
					
					List<Boolean> state = new ArrayList<Boolean>();
					
					target.forEach(a-> {
						
					// Обновление данных объектов, владельцев компонентов. 
						
					gl.tx(  new Object(){},	
								state.add(a.set_data(m_headers,BrewActionListener.get_instance("header"))) && !state.contains(false),
								gl.sf("Експорт заголовков данных в...[%s]",
								a.getName()
								));		
					});
				
					return !state.contains(false);
					
		}
		catch(Exception e)
		{
					return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
		}
	}

	public boolean isa_bind_impl()
	{

		gl.tx(new Object(){},true,gl.sf("Обновление контекста."));
		
		try
		{
	
					List<String> 		m_params 		= this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
					
					
					int m_params_count = m_params.size();
					
					// Селектирование не обязательно, просто проверка.
					
					int m_selected_count = CollectionImpl.update_selected();
					
					
					boolean bl_condition_param = (m_params_count == gl.E_OK*2 && m_selected_count == gl.E_EMPTY);
					
					Orion 	publisher 	= null;
					
					Orion  	subscriber 	= null;
					
					
					if(bl_condition_param)
					{
						// В 1 параметре название объекта издателя.
						
						String m_object_publisher = m_params.get(gl.E_EMPTY);
						
						
						// Во 2 параметре название объекта подписчика.
						
						String m_object_subscriber = m_params.get(gl.E_OK);
						
						
						// Находим.
						
						publisher 	= CollectionImpl.get_by_name(CollectionImpl.get_all_components(),m_object_publisher);
						
						subscriber 	= CollectionImpl.get_by_name(CollectionImpl.get_all_components(),m_object_subscriber);
						
					}
					else
					{
						// Инициалиазация по селектированным.
						
						// Сначала находим провайдера по классу, все остальные подписчики. 
						
						List<Orion> sel  = new ArrayList<Orion>(CollectionImpl.selected); 
								
						List<Orion>  pubs = CollectionImpl.get_by_class_name_list(sel,"ComboBox");
								
						
						if( !this.check_list_one_item("Проверка наличия одного экземпляра класса[продюсера]",pubs))
						{
							return false;
						}
						
						publisher = pubs.get(gl.E_EMPTY);
						
						
						List<Orion>  subs = CollectionImpl.get_by_class_name_list(sel,"ScrollPane");
						
						if( !this.check_list_one_item("Проверка наличия одного экземпляра класса [подписчика]",subs))
						{
							return false;
						}


						subscriber = subs.get(gl.E_EMPTY);
						
						
					}
						
						// Проверяем объекты.
						
						if(
								!this.check_object("[проверка издателя]",publisher) ||
								!this.check_object("[проверка подписчика]",subscriber)
						  )
						{
								return false;
						}
						
						// Проверяем классы объектов.
						
						if(
								!this.check_object_class(publisher, "ComboBox") ||
								!this.check_object_class(subscriber,"ScrollPane")
						  )
						{
								return false;
						}
						
						
						// Лисенер.
						
						ActionListener []  als = ((ap.mercury.components.ComboBox)publisher.getChild()).getActionListeners();
					
						if(!this.check_action_lsnrs(als))
						{
								return false;
						}
						
						BrewActionListener bal = (BrewActionListener)als[gl.E_EMPTY];
						
						if(!this.check_object("Проверка наличия лисенера",bal))
								return false;
						
						// Обновление контекста лисенера.
						
						if(!check_update_lsnr("Обновление контекста лисенера",bal,subscriber.getName()))
							return false;
						
						    return true;
					
		}
		catch(Exception e)
		{
						return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
		}
	}
	
	public boolean isa_max_min_ini_option_impl()
	{

		if(!check_selected())
		{
			return false;
		}
		
		try
		{
	
					List<String> 		m_params 		= this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
					
					List<String> 		m_switches 		= this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"SWITCH"));
					
					// Удаляем свитч активации.
					
					if (m_switches.size() != gl.E_EMPTY)
					{
						m_switches.remove(gl.E_EMPTY);
					}
					
					// Очистка строки параметров.
					
					List<String> after  = new ArrayList<String>(); 	
							
					after.addAll(
							Su.get_as_list(
							Su.remove_delims(m_params.stream().collect(Collectors.joining("")),CmdProcess.delim_switches,' '),  " "
							)
							);
					
					// Маппинг параметров.
					
					Map<String,Integer> map = new LinkedHashMap<String,Integer>(); 
					
					// Анализируем количественные характеристики.
					
					if(
							this.getProcess().isa_slider_option() ||
							(
									
									this.getProcess().isa_min_option() &&
									this.getProcess().isa_max_option() &&
									this.getProcess().isa_ini_option()
							)
					  )
					{
						// Параметров д.б. 3.
						
						gl.sfn("Params...%d...Switches...%d",after.size(),m_switches.size());
						
						if( 
							  (
								
									after.size() != 3 &&  m_switches.size() != 1 && this.getProcess().isa_slider_option()
									
							   )
								
							)
						{
							return this.setResponseHelper(new String[]{gl.sf("Неверное количество параметров и ключей.Ожидается 3.")},false);
						}
					
						// Маппинг параметров.
							
						// Инжекция ключей.
						
						if(this.getProcess().isa_slider_option())
						{
								
								m_switches.clear();
								
								m_switches.add("\\min");
								
								m_switches.add("\\max");
								
								m_switches.add("\\ini");
					

						}	
						
						
						Su.show("Switches...",m_switches);
						
						Su.show("Params...",after);
						
						 int [] index = { 0 };
							
							m_switches.forEach(s->{
									
									//map.put(s,DU.to_int(a));
							
								gl.sfn("Must put %s [%s] %s",s,Su.extract_alpha(s),after.get(index[0]));
								
								map.put(Su.extract_alpha(s).trim(),DU.to_int(after.get(index[0])));
								
								index[0]++;
								
						    });
							
							//gl.sfn("Result map...%s",MapUtils.showMapKV(map));
							
							// Проверка маппинга.
							
							if (map.values().stream().collect(Collectors.toList()).contains(gl.E_ERROR))
							{
								return this.setResponseHelper(new String[]{gl.sf("Ошибка типов параметров в коммандной строке...[%s]",this.getProcess().getText())},false);
							}
							
							// Установка значений.
							try
							{
						
							//CollectionImpl.selected.forEach(a->
							Vector<Orion> vec = new Vector(CollectionImpl.selected);
							
							for(ap.orion.Orion a : vec)
								{
								gl.sfn("----> Update...[%s]",a.getName());
								
								
								if(a.get_class().equalsIgnoreCase("SliderV"))
								{
									
									ap.mercury.components.SliderV sv = (ap.mercury.components.SliderV )a.getChild();
									
									if(sv != null)
									{
										BoundedRangeModel model = sv.getModel();
										
										/*
										model.setExtent(0);
										
										model.setMinimum(map.get("min"));
										
										model.setMaximum(map.get("max"));
										
										model .setValue(map.get("ini"));
										
										model.setValueIsAdjusting(false);
										*/
										
										model.setRangeProperties(map.get("ini"), 0,map.get("min"),map.get("max"),true);
										
										sv.setModel(model);
										
									}
									
								} else if(a.get_class().equalsIgnoreCase("SliderH"))
								{
									
									ap.mercury.components.SliderH sh = (ap.mercury.components.SliderH )a.getChild();
									
									if(sh != null)
									{
										
										BoundedRangeModel model = sh.getModel();
										
										/*
										model.setExtent(0);
										
										model.setMinimum(map.get("min"));
										
										model.setMaximum(map.get("max"));
										
										model .setValue(map.get("ini"));
										
										model.setValueIsAdjusting(true);
										
										*/
										

										model.setRangeProperties(map.get("ini"), 0,map.get("min"),map.get("max"),true);
										
										sh.setModel(model);
										
									}
									
								}
							
							}//);
							
							}
							catch(ConcurrentModificationException e)
							{
								//gl.sfn("[%s]",e.getMessage());
								e.printStackTrace( );
							}
					
							
					 }
					
								return true;
		}
		catch(Exception e)
		{
								return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
		}
	
		
	}	
	
	public boolean isa_font_option_impl()
	{

		if(!check_selected())
		{
			return false;
		}
		
		try
		{
					// Читаем параметр.

					List<String> 	m_params = this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

					String 			[] m_param = {m_params.get(gl.E_EMPTY)};
			
					int m_params_count = m_params.size();
					
					// Проверка на запрос списка фонтов.
					
					if(m_params_count == gl.E_OK && m_param[0].equalsIgnoreCase("list"))
					{
						gl.tx_k(new Object(){},gl.sf("Запрос списка фонтов...%s",m_param[0]));	
						
						try
						{
						
							this.setResponse(get_system_fonts());
							
							return true;
						}
						catch(Exception e)
						{
						
							gl.tx_e(new Object(){},gl.sf("Запрос списка фонтов...%s",m_param[0]));	
							
							return false;
						}
						
						
					} // list.
					
					// Определяем необходимость дополнительной обработки параметра.
					// Ожидаем один параметр типа Tahoma,1,12 без пробелов.
					// Или  три параметра типа Tahoma 1 12
					// Можно конечно отрабатывать и такой зашквар Tahoma , 1 , 12
					// Это конечно уже зашквар но можна, но потом.
			
					Font	[] font		= {null};
					
					boolean	[] state	= {false,false,false};
					
					// Если параметров больще одного.
					// Принудительно приводим к одному.
					
					if(m_params_count == gl.E_OK*2)
					{	
						String m_full_name = gl.sf("%s %s",m_params.get(0),m_params.get(1));
	
						m_param[0] = m_full_name;
						
						m_params_count = gl.E_OK;
						
						gl.tx_k(new Object(){},gl.sf("Приведение к одному параметру...%s",m_param[0]));		

						
					} else if(m_params_count == gl.E_OK*3)
					{ 
						String m_full_name = gl.sf("%s %s %s",m_params.get(0),m_params.get(1),m_params.get(2));
						
						m_param[0] = m_full_name;
						
						m_params_count = gl.E_OK;
						
						gl.tx_k(new Object(){},gl.sf("Приведение к одному параметру...%s",m_param[0]));		

					}else if(m_params_count == gl.E_OK*4)
					{ 
						String m_full_name = gl.sf("%s %s %s %s",
								m_params.get(0),
								m_params.get(1),
								m_params.get(2),
								m_params.get(3)
								);
						
						m_param[0] = m_full_name;
						
						m_params_count = gl.E_OK;
						
						gl.tx_k(new Object(){},gl.sf("Приведение к одному параметру...%s",m_param[0]));		

					} else if(m_params_count == gl.E_OK*5)
					{ 
						String m_full_name = gl.sf("%s %s %s %s %s",
								m_params.get(0),
								m_params.get(1),
								m_params.get(2),
								m_params.get(3),
								m_params.get(4)
								);
						
						m_param[0] = m_full_name;
						
						m_params_count = gl.E_OK;
						
						gl.tx_k(new Object(){},gl.sf("Приведение к одному параметру...%s",m_param[0]));		

					}	


					
					// Все представлено в одном параметре без пробелов.
					
					if(m_params_count == gl.E_OK)
					{
						// Какой разделитель присутствует.
						// Ожидаем запятую.
						
						if(!m_param[0].contains(","))
						{
							// Разделитель непонятен.

							gl.tx_e(new Object(){},gl.sf("Разделитель не установлен...%s",m_param[0]));		
								
							return false;
						}
						
						if (!ParseUtil.parse_font(m_param[0],",",font,state))
						{

							gl.tx_e(new Object(){},gl.sf("Парсинг фонта...%s",m_param[0]));		

							return false;
						}
					}
					
					boolean [] bl_global = {false};
					
					CollectionImpl.selected.forEach(a->{
			
						// Текущий фонт.
						
						Font c_font = a.getFont(); 
						
						/*
						gl.tx_k(new Object(){},gl.sf("Текущий фонт...%s,%d,%d",
								c_font.getName(),
								c_font.getStyle(),
								c_font.getSize()
								));		
								
						*/
						// Анализ флагов парсинга.
						
						boolean bl_check = false;
						
						for(int i=0;i< 3;i++)
						{
							if(state[i])
								bl_check = true;
						}
						//if(!Arrays.asList(state).contains(true))
						if(!bl_check)
						{
							// Все поля для парсера были заполнены.
							
							c_font = font[0];

							gl.tx_k(new Object(){},gl.sf("Все включено...%s,%d,%d",
									c_font.getName(),
									c_font.getStyle(),
									c_font.getSize()
									));		
															
						}
						else
						{
							// Если поле флага false это значит что параметр
							
							// установлен явно из массива font[]
							
							if(!state[0])
							{
								// Название фонта изменяем.
								
								c_font = new Font(font[0].getName(),c_font.getStyle(),c_font.getSize());


								gl.tx_k(new Object(){},gl.sf("Название пропущено...%s,%d,%d",
										c_font.getName(),
										c_font.getStyle(),
										c_font.getSize()
										));		

								
							}
							
							if(!state[1])
							{
								// Стайл изменяем.


								gl.tx_k(new Object(){},gl.sf("Стиль пропущен...%s,%d,%d",
										c_font.getName(),
										c_font.getStyle(),
										c_font.getSize()
										));		

								c_font = new Font(c_font.getName(),font[0].getStyle(),c_font.getSize());
										
							}
							
							if(!state[2])
							{
								// Размер изменяем.
								

								gl.tx_k(new Object(){},gl.sf("Размер пропущен...%s,%d,%d",
										c_font.getName(),
										c_font.getStyle(),
										c_font.getSize()
										));		

								c_font = new Font(c_font.getName(),c_font.getStyle(),font[0].getSize());
								
							}
						}
						
								/*
								gl.tx_k(new Object(){},gl.sf("Финальная установка фонта...%s,%d,%d",
										c_font.getName(),
										c_font.getStyle(),
										c_font.getSize()
										));		
								*/
								// Проверяем по репозитарию фонтов.
								
								if(!get_system_fonts().contains(c_font.getName()))
								{
									gl.tx_k(new Object(){},
											gl.sf("Недопустимый фонт...%s",c_font.getName()));
											
									bl_global[0] = true;
								}
								else
								{
									a.set_font_router(c_font);
									
									a.repaint();
								}
								
					});
		
					
					return !bl_global[0];
					
		}
		
		
		catch(Exception e)
		{
					return false;
		}

	}
	
	public boolean isa_align_option_impl()
	{
		if(!check_selected())
		{
			return false;
		}
		
		// Читаем параметр.

		List<String> 	m_params = this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			param = m_params.get(gl.E_EMPTY).trim();

		
		try
		{
			
						gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s",param));		

					// Проверка по справочнику.
					
					int m_index = IAlign.indexOf(param);
				
					if(m_index == gl.E_ERROR)
					{
						
						String m_msg = gl.sf("Недопустимый тип компоновки...%s",param);
								
						gl.tx_e(new Object(){},m_msg);		
			
						this.setResponseHelper(new String[] {gl.sf(m_msg)});
						
						return false;
					}
					
		
					CollectionImpl.selected.forEach(a->{
			
						a.getDecoro().set_align(m_index);
			
					});
			
						return true;
		}
		catch(Exception e)
		{
						return false;
		}

	}
	
	public boolean isa_set_impl()
	{
		
			CmdProcess 	proc = this.getProcess();
	
			this.setResponse(null);
		
		if(proc.isa_bkg_option())
		{
			return isa_bkg_option_impl();
			
		} else if(proc.isa_foreground_option())
		{
			return isa_frg_option_impl();
		}
		else if(proc.isa_text_option())
		{
			return isa_text_option_impl();
					
		} else if(proc.isa_font_option())
		{
			return isa_font_option_impl();
		
		} else if(proc.isa_align_option())
		{
			return isa_align_option_impl();
			
		} else if(proc.isa_action_option())
		{
			return isa_action_option_impl();
			
		} else if(proc.isa_grid_option())
		{
			return isa_grid_option_impl();
			
		} else if(proc.isa_border_option())
		{
			return isa_border_option_impl();
			
		} else if(proc.isa_max_option() || proc.isa_min_option() || proc.isa_ini_option() || proc.isa_slider_option())
		{
			return isa_max_min_ini_option_impl();
			
		}  else if(proc.isa_header_option() && proc.isa_out_option() && proc.get_count_of_switches()==3)
		{
			return isa_hdr_out_option_impl();
			
		} else if(proc.isa_header_option() && proc.get_count_of_switches()==2 && proc.get_count_of_params() == gl.E_OK)
		{
			return isa_hdr_set_option_impl();
			
		} else if(proc.isa_header_option() && proc.get_count_of_switches()==2 && proc.get_count_of_params() == gl.E_EMPTY)
		{
			return isa_hdr_show_option_impl();
			
		} else if(proc.isa_bind_option() && proc.get_count_of_switches()==2 && Nu.in(proc.get_count_of_params(),0,2))
		{
			return isa_bind_impl();
			
		}
		
			return this.check_level("::impl");
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		this.welcome();
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(
				 p.get_count_of_headers() 	>= gl.E_OK 
				)
		 {
	
		 	// Check of the switch.
		 
			if(p.isa_set_option())
			{ 
				return isa_set_impl();
				
			}	else
			{
				return this.check_level("lvl::0");
			}
		 }
		else
		{	
				return this.check_level("lvl::01");
		}
	 
	 		
	 
	} // try.
	catch(Exception e)
	{
				return false;
	}
	 
	} 
	
	

	public static PropAction get_instance()
	{
		PropAction lafa = new PropAction(); 
		
		return new PropAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	public static List<String> get_system_fonts()
	{
		GraphicsEnvironment 	ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		String []				m_fonts = ge.getAvailableFontFamilyNames();
	
		List<String> 			m_result = new ArrayList<String>();
		
								for(int i=0;i<m_fonts.length;i++)
								{
									m_result.add(m_fonts[i].trim());
								}
		
		return 					m_result;
		
	}
	
	public static void main(String[] args) {
		

	}

}

