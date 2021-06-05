package ap.orion.impl;

import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import ap.collectors.Collector; 
import ap.gen.IDGen; 
import ap.global.gl;
import ap.mercury.components.Cmd;
import ap.orion.Orion;
import ap.orion.action.ActionBootStrap;
import ap.orion.action.BaseAction;
import ap.orion.app.Application;
import ap.orion.intf.IRoutine;
import ap.orion.live.Live;
import ap.orion.state.ObjectRights;
import ap.utils.Bau;
import ap.utils.Su; 

public class RoutineImpl  implements IRoutine { 
	 
	public static final Map<String,BaseAction> actions_map = new LinkedHashMap<String,BaseAction>(); 
	 
	public RoutineImpl() { 
		 
	} 

	@Override 
	public boolean start() { 
		 
		//return (clear() && load_actions_map() && set_original_bounds_on_the_frame() && Application.cio.show_brew()&& add_dummy_set()); 
		 
		//gl.smn("Add start set");
		
		return add_start_up() && load_actions_map() && cmd_init();
	} 
	 
	// TODO start.
	
	public boolean cmd_init() 
	{

		// return (boolean)Cmd.exec(gl.sf(".read start")).get("result");

		//return (boolean)Cmd.exec(gl.sf(".read C:\\bin\\eclipse\\wsp\\Organizer\\composites\\23012021\\Тест")).get("result");

		if(Application.getCio().getStartup_composite() != null)
		{
			return (boolean)Cmd.exec(gl.sf(".read %s",Application.getCio().getStartup_composite())).get("result");
		}
		
			return true;
	}
	
	 
	
	public boolean add_dummy_set_prev() 
	{ 
		//int m_align = IAlign.indexOf("BASE_ZOOM_ALL"); 
				 
		//XComboBoxA	brew = XComboBoxA.get_brew_choice_instance(new Rectangle(322,30,300,21),m_align,BrewActionListener.get_instance());
		
		//XComboBoxA	lf = XComboBoxA.get_look_and_fill_choice_instance(new Rectangle(322,60,300,21),m_align,LfActionListener.get_instance()); 
				
		//Application.getDio().get_desk_top().insert_ipo(brew,lf); 
				 
		return true; 

		 
	} 
	
	
	public boolean add_start_up_prev() 
	{ 
	
		// Reset preffered size.
		
		int m_height =  100*25;
		
		int m_width  =  100*25;
		
		Dimension m_cur_ps =  Application.getDio().get_desk_top().getPreferredSize();
		
		m_cur_ps.height = m_height;
		
		m_cur_ps.height = m_width;
		
		
		Application.getDio().get_desk_top().setPreferredSize(m_cur_ps);
		
		Application.getDio().get_desk_top().insert(Orion.layoutOf(Live.get_custom_set(10),10,25,50,25));
		
		return true;
		
	}
	
	public boolean add_start_up() 
	{ 
	
		// Reset preffered size.
		
		int m_height =  100*25;
		
		int m_width  =  100*25;
		
		Dimension m_cur_ps =  Application.getDio().get_desk_top().getPreferredSize();
		
		m_cur_ps.height = m_height;
		
		m_cur_ps.height = m_width;
		
		Orion 	desk_top = Application.getDio().get_desk_top(); 
		
				Orion 	cmd = Orion.get_instance("CommandArea");
			
						desk_top.insert(cmd);
		
							
						//desk_top.add(cmd);
						
				//Button btn = Button.get_instance(new Rectangle(0,50,100,50));
				
				//Panel btn1 = Panel.get_instance(new Rectangle(0,0,100,50));
				
					//  btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				
					 // desk_top.add(btn1);
					  
					  //desk_top.add(btn);
				
				//CheckBox btn = CheckBox.get_instance(new Rectangle(0,0,100,25));
		
				//RadioButton btn = RadioButton.get_instance(new Rectangle(0,0,100,25));
		
				//ComboBox btn = ComboBox.get_instance(new Rectangle(0,0,100,25));
		
				//ToggleButton btn = ToggleButton.get_instance(new Rectangle(0,0,100,25));
				
				//TextField btn = TextField.get_instance(new Rectangle(0,0,100,25));
		
				//PasswordField btn = PasswordField.get_instance(new Rectangle(0,0,100,25));
		
				//ap.mercury.components.List btn = ap.mercury.components.List.get_instance(new Rectangle(0,0,100,25));
		
				//Tree btn = Tree.get_instance(new Rectangle(0,0,100,25));
		
				//TextArea btn = TextArea.get_instance(new Rectangle(0,0,200,50));
		
				//SliderH btn = SliderH.get_instance(JSlider.HORIZONTAL,0,100,50,new Rectangle(0,0,100,25));
		
				//Spinner btn = Spinner.get_instance(new Rectangle(0,0,100,25));
				
				/*
				JPanel 	container = new JPanel();
				
						container.setBounds(btn.getBounds());
						
						container.setLayout(new BorderLayout());
					
						container.add(new ScrollPane(btn),BorderLayout.CENTER);
				*/
			
				/*
				Orion 		desktop = Orion.get_instance("Panel",Ru.norm_dim_to_rect(new Dimension(320,240))); 
				 
							desktop.setBackground(Color.red);
						
							desktop.setPreferredSize(new Dimension(640,480));
				
				Orion 		container = Orion.get_instance("Panel",Ru.norm_dim_to_rect(new Dimension(320,240))); 
						
				ScrollPane 	sp = new ScrollPane(desktop);
				
							container.setLayout(new BorderLayout());
						
							container.add(sp,BorderLayout.CENTER);
						
							desk_top.add(container);
							
				*/
							
				//Orion 		btn = ScrollablePanel.get_instance(new Rectangle(0,0,200,50));
				
					//		desk_top.add(btn);
							
							return true;
		
	}
	
	
	
	@Override 
	public boolean clear() { 
		 
		String msg = "Clear of instances."; 

		try { 

			int m_component_count = Application.getFio().get_frame().getContentPane().getComponentCount(); 
			 
			
			// If Desktop is not exists.
			
			if(Application.getFio().get_frame().getContentPane().getComponentCount() == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...While there is darkness and emptiness...%d...%s", msg,m_component_count, gl.S_OK)); 
				 
				return true; 
			} 
			 
			// Delete all that are DELETETABLE is.
			CollectionImpl.items.removeAll( 

			CollectionImpl.get_by_state(CollectionImpl.items,ObjectRights.DELETABLE)
			
			);
			
			
			gl.tracex(new Object() {}, gl.sf("%s...init idgen...%05d...%s", msg,IDGen.counter.get(), gl.S_OK)); 

			return true; 

		} catch (Exception e) { 
			 
			gl.tracex(new Object() {}, gl.sf("%s...exception...%s...%s", msg,e.getMessage(), gl.S_ERROR)); 
			 
			return false; 
		} 

	} 

	public boolean update_image_and_data(List<Orion> guests)
	{

		try
		{
		
			guests.forEach(a-> {
			
		
		// Образ.
		
		if(a.getImage().getImageText() != null) 
		{ 
				 
			byte [] ba = Bau.to_byte_array_from_hex_str(a.getImage().getImageText()); 
			  
			if (ba != null) 
			{ 
				a.getImage().setImage(Bau.to_buf_img_from_byte_array(ba)); 
				 
				a.repaint(); 
			} 
		 
		} // for image. 

		// Данные.
		
		// TODO Загрузка данных при чтении.
				
		if(a.get_class().equalsIgnoreCase("ScrollPane"))
		{
									
					 gl.tx(new Object() {},
					 a.getData().push(),
					 gl.sf("Попытка загрузить данные для компонента...[%s]...размер коллекции...[%d]",
					 a.getName(),
					 a.getData().getData() != null ? a.getData().getData().length() : gl.E_ERROR ));
									
		}
			
		});

					return true;
		}
		catch(Exception e)
		{
					return false;
		}
		
	}
	
	@Override
	public boolean read() 
	{ 
			 
			String msg = gl.sf("Чтение с репозитария...[%s]",Application.getCio().getHome()); 

			try { 
			 
					Collector<Orion> cs = new Collector<Orion>(Application.getCio().getHome()); 

					cs.clear(); 

					// Read RAW stream.
					
					int m_save_iid = IDGen.get();
							
					//gl.tx_k(new Object() {}, gl.sf("Before ... ^^^ GET ^^^  ...%d",m_save_iid)); 

				if (!cs.read()) { 

					gl.tx_e(new Object() {}, gl.sf("%s...%s", msg, "Ошибка JAXB.")); 

					return false; 
				} 
				
					CollectionImpl.show("RAW чтение",cs.getData());

					//gl.tx_k(new Object() {}, gl.sf("After ... ^^^ GET ^^^  ...%d",IDGen.get())); 

					
					Orion 	dt = Application.getDio().get_desk_top(); 
					
					// Сохраняем все объекты, что были на рабочем столе.
					
					List<Orion> m_start = CollectionImpl.get_writable_components();
					
					// Это чтение для проверки после записи или добавление нового проекта.
					
					boolean 	bl_append = ( m_start.size() > gl.E_EMPTY );
					
					List<Orion> guests = new ArrayList<Orion>();
					
								guests.addAll(cs.getData());
					
								
					if(!bl_append)
					{
						
						dt.insert(cs.getData());
						
						
						// 
						
						int  m_after = CollectionImpl.get_all_components_pure().size();
						
						int  m_planned_iid =  (m_save_iid + m_after);

						gl.tx_k(new Object() {}, gl.sf("Проверка ИИД...План...%d...Реально...%d",
								m_planned_iid,
								IDGen.get())); 
						
						IDGen.reset("Полное чтение",m_planned_iid);
						
						boolean bl_check_iid = (m_planned_iid == IDGen.get());
						
						if(! 
								gl.tx(new Object() {},bl_check_iid, gl.sf("Пере проверка ИИД...План...%d...Реально...%d",
										m_planned_iid,
										IDGen.get())))
						{
							
								return false;
						}
 
						
						if(update_image_and_data(dt.get_all_childs()) && CollectionImpl.refresh())
						{
						
							dt.revalidate();
						
							dt.repaint();
					
							return true;
						}
						
						
					}
					
					
					int m_start_check 	= CollectionImpl.get_all_components_pure().size();
					
					
					CollectionImpl.check_iid_integrity();
					
					// Удаляем с рабочего стола.
					
					int m_iid = IDGen.get();
						
					if(!gl.tx(new Object() {},
							CollectionImpl.clean_all(),
							gl.sf("Временная очистка рабочего стола")))
					{
						
						return false;
					}
					

					// Восстанавливаем ИД генератор.
					
					IDGen.reset(m_iid);
					
					if(!gl.tx(new Object() {},
							(m_iid == IDGen.get()),
							gl.sf("Проверка состояния ИД синхронизации...%d...%d",m_iid,IDGen.get())))
							{
							  return false;
							}
				
					
					// Обрабатываем входной поток.
					
					// Временно на рабочий стол для обработки. 
					
					dt.insert(cs.getData());
					
					dt.revalidate();
					
					dt.repaint();
					
					CollectionImpl.show("Гости перед обновлением",CollectionImpl.get_all_components_pure());
						
					// Обновление гостей.
					
					CollectionImpl.get_all_components_pure().forEach(a->{
								a.update_id_pid_after_load();
						});
					
					if(!update_image_and_data(dt.get_all_childs()))
					{
						return false;
					}
					
					int m_guests = CollectionImpl.get_all_components_pure().size();

					CollectionImpl.show("Гости после обновления",CollectionImpl.get_all_components_pure());
					
					// Сохраняем обработанные объекты в кэш.
					
					guests.clear();
								
					guests.addAll(CollectionImpl.get_writable_components());
					
					
					if(!gl.tx(new Object() {},
										CollectionImpl.clean_all(),
										gl.sf("Временная очистка рабочего стола")))
					{
									
						return false;
					}
					
					// Восстанавливаем первичный массив объектов.
					
					if(m_start.size() != gl.E_EMPTY)
					{
						dt.insert(m_start);
						
						int m_after  = CollectionImpl.get_all_components_pure().size();
						
						if(!gl.tx(new Object() {},
							(m_start_check == m_after),
							gl.sf("Проверка восстановления первичного массива...%d...%d",m_start_check,m_after)))
							{
							  return false;
							}
						
						// Добавляем новые объекты.
						
						dt.insert(guests);

						guests.clear();
						
						dt.revalidate();
						
						dt.repaint();

						
						int m_planned = m_start_check + m_guests;
						
							m_after   = CollectionImpl.get_all_components_pure().size();
															
						if(!gl.tx(new Object() {},
							(m_planned == m_after),
							gl.sf("Финальная проверка массива...%d...%d",
									m_planned,
									m_after
									)))
							{
								 return false;
							}
							
					}
					
					if(!gl.tx(new Object(){},refresh(),gl.sf("Синхронизация данных...%s",refresh())))
						return false;
					
					if (!
						gl.tx(new Object() {},
							dt.get_all_childs().size() ==
							CollectionImpl.items.size(),
							gl.sf("%s...Компонент...%d...Объектов...%d", 
							msg,
							dt.get_all_childs().size(),
							CollectionImpl.items.size()
							))) 
						{
						
					
						return false;
					}
							
					if (!gl.tx(new Object() {},
							  CollectionImpl.check_iid_integrity(),
							  gl.sf("Синхронизация ИД модели")))
					{
						return false;
					}
					
					int m_planned_iid = (m_save_iid + m_guests);
					
					if(m_planned_iid != IDGen.get())
					{
						IDGen.reset(m_planned_iid);
					}
					
					if (!gl.tx(new Object() {},
							  (m_planned_iid == IDGen.get()),
							  gl.sf("Проверка ИД целостности...Ожидание...%d...Реальность...%d",
									  m_planned_iid,
									  IDGen.get())))
					{
						return false;
					}
					
					
						return true; 
					

			} catch (Exception e) { 
			 
					return gl.tx(new Object() {},false, gl.sf("%s...%s...%s", msg, "Ошибка",e.toString())); 
			 
			} 

		} 


	public boolean read_(boolean gap) 
	{ 
			 
			String msg = "Чтение с репозитария."; 

			try { 
			 
					Collector<Orion> cs = new Collector<Orion>(Application.getCio().getHome()); 

					cs.clear(); 

				// Read RAW stream.
					
				if (!cs.read()) { 

					gl.tx_e(new Object() {}, gl.sf("%s...%s", msg, "Ошибка JAXB.")); 

					return false; 
				} 

					Orion 	dt = Application.getDio().get_desk_top(); 
					
					
					// Re - check of gap state.
					
					boolean bl_gap = (CollectionImpl.items.size() > gl.E_OK);
					
					gl.tx_k(new Object() {},gl.sf("Необходимость гэпа локально...%s...в параметре...%s",bl_gap,gap));
					
					// Обработка гэпа.
					
					if(bl_gap)
					{
								
							
							int m_gap = CollectionImpl.get_max_id(CollectionImpl.items);
							
							IDGen.reset(m_gap);
							
							gl.tx_k(new Object() {},gl.sf("Будет применен гэп...%3d",m_gap));
							
							cs.getData().forEach(a->{
								
								int m_pid = a.getIdo().getPid();
								
								int m_id = a.getIdo().getId();
								
								if (m_pid != gl.E_ERROR)
									m_pid = m_pid + m_gap;
								
								if (m_id != gl.E_ERROR)
									m_id = m_id + m_gap;
									
								a.getIdo().setId(m_id);
								
								a.getIdo().setPid(m_pid);
								
								a.setName(a.update_name());
								
							});
							
								//int m_max_id_i = CollectionImpl.get_max_id(cs.getData());
							
								//IDGen.reset(m_max_id_i);
							
					
					} // gap section end.
					
				
					// Здесь необходим фильтр для исключения дублирования.
					
					// В фильтр необходимо включить объекты которые не удаляемы и уже на столе.
					
					List<Orion>  m_filter = new ArrayList<Orion>();
					
					m_filter.addAll(dt.get_components());
					
					m_filter.forEach(s->{
						
						gl.sfn("Первичный массив объектов стола...%s",s.getName());
						
					});
					
					
					// ?
					
					cs.getData().forEach(v->
					{	
						if(CollectionImpl.get_by_id(m_filter,v.getIdo().getId()) == null)
						{
							gl.sfn("+++:::----> Добавляется что-то непонятное...%s",v.getName());
							
							m_filter.add(v);
						}
					});
					
					m_filter.forEach(s->{
						
						gl.sfn("Итоговый массив...%s",s.getName());
						
					});
					
						dt.insert(m_filter);
										
						dt.revalidate();
						
						dt.repaint(); 
								
								
					// Добавить объекты в индекс.
								
					List<Orion> m_add = new ArrayList<Orion>(); 
										
					dt.get_components().forEach(a->{
									
						if(CollectionImpl.items.contains(a))
						{
							gl.tx_k(new Object() {},gl.sf("Компонент уже в индексе...%s",a.getName()));
						}
						else
						{
							gl.tx_k(new Object() {},gl.sf("Компонета еще нет в индексе...%s",a.getName()));
										
							if(!CollectionImpl.items.contains(a))
							{
							  gl.tx_k(new Object() {},gl.sf("Компонет будет добавлен в индекс...%s",a.getName()));
											
							  m_add.add(a);
											
							 // Загрузка данных для компонента.
											
							 if(a.get_class().equalsIgnoreCase("ScrollPane"))
							 {
												
												gl.tx(new Object() {},
														a.getData().push(),
														gl.sf("Попытка загрузить данные для компонента...%s...размер коллекции...%d",
														a.getName(),
														a.getData().getData() != null ? a.getData().getData().length() : gl.E_ERROR ));
												
							 }
											
							}
										
								// Дочерние элементы.
										

								List<Orion> m_childs = a.get_all_childs(); 
										
								m_childs.forEach(c->{
											
											if(!CollectionImpl.items.contains(c))
											{
												gl.tx_k(new Object() {},gl.sf("Дочерний компонет будет добавлен в индекс...%s",c.getName()));
											
												m_add.add(c);
											}
											
										});
										
						}
				 });
				 
								CollectionImpl.items.addAll(m_add);
								
								// Update image.
								
								CollectionImpl.items.forEach(a->{
									if(a.getImage().getImageText() != null) 
									{ 
											 
										byte [] ba = Bau.to_byte_array_from_hex_str(a.getImage().getImageText()); 
										  
										if (ba != null) 
										{ 
											a.getImage().setImage(Bau.to_buf_img_from_byte_array(ba)); 
											 
											a.repaint(); 
										} 
									 
									} // for image. 
									
								});
								
								
								CollectionImpl.show("Добавлены в индекс",m_add);
					
								gl.tx_k(new Object() {}, gl.sf("%s...Компонент...%d...Объектов...%d", msg,dt.get_components().size(),CollectionImpl.items.size())); 
								
								CollectionImpl.show("Список объектов",CollectionImpl.items);
					
								CollectionImpl.show("Список компонент",dt.get_components());
								
								m_add.clear();
								
								return true; 
					

			} catch (Exception e) { 
			 
								gl.tx_e(new Object() {}, gl.sf("%s...%s...%s", msg, "exception",e.toString())); 
			 
								return false; 

			} 

		} 

	public boolean read_base(boolean gap) 
	{ 
			 
			String msg = "Чтение с репозитария."; 

			try { 
			 
					Collector<Orion> cs = new Collector<Orion>(Application.getCio().getHome()); 

					cs.clear(); 

				if (!cs.read()) { 

					gl.tx_e(new Object() {}, gl.sf("%s...%s", msg, "Ошибка JAXB.")); 

					return false; 
				} 

					Orion 	dt = Application.getDio().get_desk_top(); 
					
					
					// Re - check of gap state.
					
					boolean bl_gap = (CollectionImpl.items.size() > 1);
					
					gl.tx_k(new Object() {},gl.sf("Gap state checked...%s...request...%s",bl_gap,gap));
					
					
					// Gap implementation section.
					
					if(bl_gap)
					{
						
					
					int m_max_id = CollectionImpl.get_max_id(CollectionImpl.items);
					
					IDGen.reset(m_max_id);
					
					int m_gap = m_max_id;
					
					gl.tx_k(new Object() {},gl.sf("Gap in ACTION...%3d",m_gap));
					
					
					cs.getData().forEach(a->{
						
						int m_pid = a.getIdo().getPid();
						
						int m_id = a.getIdo().getId();
						
						//gl.sm( gl.sf("Old pid...%d...id...%d...gap...%d",m_pid,m_id,m_gap) );
						
						if (m_pid != gl.E_ERROR)
							m_pid = m_pid + m_gap;
						
						if (m_id != gl.E_ERROR)
							m_id = m_id + m_gap;
						

						//gl.sm( gl.sf("...New pid...%d...id...%d",m_pid,m_id) );
						
							
						a.getIdo().setId(m_id);
						
						a.getIdo().setPid(m_pid);
						
						a.setName(a.update_name());
						
					});
					
						// Setup max id.
					
					int m_max_id_i = CollectionImpl.get_max_id(cs.getData());
					
					IDGen.reset(m_max_id_i);
					
					
					} // gap section end.
					
					List<Orion> 	m_work_set = new ArrayList<Orion>();
					
					List<Boolean> 	m_result = new ArrayList<Boolean>();
					
					m_work_set.addAll(cs.getData());
						
					// Image implementation section.
					
					m_work_set.forEach(a->{
						if(a.getImage().getImageText() != null) 
						{ 
								 
							byte [] ba = Bau.to_byte_array_from_hex_str(a.getImage().getImageText()); 
							 
							
							m_result.add( ba  == null);
							 
							if (ba != null) 
							{ 
								a.getImage().setImage(Bau.to_buf_img_from_byte_array(ba)); 
								 
								a.repaint(); 
							} 
						 
						} // for image. 
					});
					
					
					boolean bl_work_set = true;//!m_result.contains(true);
					
					dt.insert(m_work_set);
					
					CollectionImpl.items.addAll(m_work_set);
					
					m_work_set.clear();
					
					dt.revalidate();
					
					dt.repaint(); 
				 
					gl.tx_k(new Object() {}, gl.sf("%s...Компонент...%d...Объектов...%d", msg,dt.get_components().size(),CollectionImpl.items.size())); 
					
					CollectionImpl.show("After read",CollectionImpl.items);
					
					return bl_work_set; 
					

			} catch (Exception e) { 
			 
					gl.tx_e(new Object() {}, gl.sf("%s...%s...%s", msg, "exception",e.toString())); 
			 
					return false; 

			} 

		} 
		 

	 
	 

	@Override 
	public boolean make(Dimension dim, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear()) /* && add_items(dim)*/); 
	} 
	 
	 
	@Override 
	public boolean make(Dimension dim, Dimension dim_size, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear())/*&& add_items(dim,dim_size)*/); 
	} 

	 
	@Override 
	public  boolean aclean() 
	{ 

		try { 

			Orion dt = Application.getDio().get_desk_top(); 
			 
			dt.removeAll(); 
			 
			dt.repaint(); 
			 
			 
			gl.tracex(new Object() {}, gl.sf("%s...%s","Clean of the Desktop.",gl.S_OK)); 

			return true; 

		} catch (Exception e) { 
			 
			return false; 
		} 

	} 
	 
	@Override 
	public boolean add_items(int count) 
	{ 
		return true; 
		 
	} 
	 
	 
	@Override 
	public boolean refresh_index_suite(int top_id,int gap) 
	{ 
		return true; 
	} 
		 
	@Override 
	public boolean refresh() 
	{ 
		CollectionImpl.items.clear();
		
		CollectionImpl.items.addAll(Application.getDio().get_desk_top().get_all_childs());
		
		return (CollectionImpl.items.size() == Application.getDio().get_desk_top().get_all_childs().size()); 
	} 
	 
	public boolean gc() 
	{ 
		CollectionImpl.get_selected().forEach( 
				a->{ 
			 
					//if(a.getStio().is_delete() && a.getIdo().getOwner() != null) 
					{ 
						
						a.getIdo().getOwner().remove(a);
						
						//if(a.getIdo().getOwner() instanceof PanelSmile && a instanceof PanelPoint)
						//{
						//	((PanelSmile)a.getIdo().getOwner()).update_type();
						//}
						
						 
					} 
		}); 
		 
			CollectionImpl.get_selected().clear(); 
		 
			Application.getDio().get_desk_top().repaint(); 
		 
		return (CollectionImpl.get_selected().size() == gl.E_EMPTY); 
	} 
	
	 
	@Override 
	public boolean write(boolean all_objects) 
	{ 
		 	String msg = "Запись в файл";
		 	 		 
			Collector<Orion> cs = new Collector<Orion>(Application.getCio().getHome()); 
	 	
			// Separate write set. 
			
			List<Orion> m_write_set = new ArrayList<Orion>();
			
			
			if(all_objects)
			{

				m_write_set.addAll(
						CollectionImpl.get_writable_components()
						);
				
			}
			else
			{
				// Только объекты первого уровня.
					
				CollectionImpl.get_writable_components(CollectionImpl.selected).forEach(
				d->{
							m_write_set.add(d);
				 });
			
			}
			
			//gl.sfn("---> Объектов для записи...%d",m_write_set.size());
			
			if(!gl.tx(new Object(){},(m_write_set.size() > gl.E_EMPTY),gl.sf("Объектов для записи.")))
				return false;
			
			// Image prepare.
			
			List<Orion> m_images = new ArrayList<Orion>();
			
			m_write_set.forEach(v->{ 
				{ 
				 
						if(v.getImage().getImage() != null) 
						{ 
							 
							String image_text = Bau.to_hex_string_from_buffered_image(v.getImage().getImage(),v.getImage().getImageType()); 
							 
							v.getImage().setImageText(image_text); 
							 
							/*
							gl.tx_k(new Object(){},gl.sf("%s...%s...%d...%s",
									msg,
									"размер",
									v.getImage().getImageText().length(),
									v.getName())); 
							*/
							
						}
						

						// Дочерние объекты.
						
						v.get_all_childs().forEach(i->{
						
							
										if(i.getImage().getImage() != null)
										{
											if(!m_images.contains(i))
											{
											    m_images.add(i);

												gl.tx_k(new Object(){},gl.sf("Добавлен в индекс для подготовки образа к записи...%s",i.getName()));
																   
											}
										}
						});

				
				}}
				); //  

			
				// 
			
				CollectionImpl.show("Объекты для обновления образа",m_images);
			
				// Обновление образа.
			
				m_images.forEach(g->{
					 
					g.getImage().setImageText(Bau.to_hex_string_from_buffered_image(g.getImage().getImage(),g.getImage().getImageType())); 
					 
					/*
					if(mode)
					gl.tx_k(new Object(){},gl.sf("Подготовка образа к записи...%s...установлен размер...%d",
							g.getName(),
							g.getImage().getImageText().length()
							));
					
					*/
					
				});
						
		
			//  Сохраняем метку генератора.
				
			int m_save_id = IDGen.nextId() -1;
			
			IDGen.reset(m_save_id);
			
			gl.tx_k(new Object() {},gl.sf("Сохранение ИД...%d",m_save_id));
			
				
			m_write_set.forEach(a->{ 
				 
				a.getImage().setImage(null);
				
				a.setToolTipText(null); 
				
				a.setTarget(null);
				
				ToolTipManager.sharedInstance().setEnabled(false);
				
				ToolTipManager.sharedInstance().unregisterComponent(a);
				
				ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
					
				gl.tx(new Object() {},a.getData().pop(),gl.sf("Обнуление перед записью...%s",a.getName()));
				
				cs.add(a); 
				 
			});
			
				m_write_set.clear();
				 
			 
		if (!cs.write()) {gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg, "while write object", gl.S_ERROR)); 

			return false; 
		} 
		
			// Восстанавливаем ИД.

			gl.tx_k(new Object() {},gl.sf("Восстановление ИД...%d",m_save_id));
		
			IDGen.reset(m_save_id);

			ToolTipManager.sharedInstance().setEnabled(true);

			gl.tx_k(new Object() {}, gl.sf("%s...%s", msg,Application.getCio().getHome())); 

			return true; 
			
			

	} 
	 
		 
	@Override 
	public void post_clearing_suite(Orion target) 
	{ 
		clear_tool_tips(target); 
	} 
	 
	@Override 
	public void clear_tool_tips(Orion panel) 
	{ 
		 
		panel.setToolTipText(null); 
		 
		Component[] cos = panel.getComponents(); 

		for (Component c : cos) { 
		 
			((Orion)c).setToolTipText(null); 
			 
		} 
	} 
	 
	public static RoutineImpl get_instance() 
	{ 
		return new RoutineImpl(); 
	} 
 
	public boolean load_actions_map() { 
	 
			String msg = "Load actions map"; 
			 
			// Keys. 
			
			List<BaseAction> acts = Arrays.asList(ActionBootStrap.actions); 
			 
				actions_map .clear(); 
		 
			acts.forEach(a-> 
			{ 
				actions_map.put(Su.get_delim_str_from_list(a.getActivators(),","),a); 
			}); 
			 
			boolean bl_result = (actions_map.size() == ActionBootStrap.actions.length); 
			 
			if(bl_result) 
				gl.smn(show_actions_map(actions_map)); 
			 
			 
			gl.tx_k(new Object() {}, gl.sf("%s...items...%d", msg,actions_map.size())); 
			 
			return bl_result; 
	 
	} 
	 
	 
	 
	
	public String show_actions_map(Map<String,BaseAction> actions_map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		int [] npp = {1}; 
		 
		actions_map.forEach((k,v)-> 
		{ 
			String m = gl.sf("%3d.%s %s",npp[gl.E_EMPTY],gl.format(k,gl.AL.LEFT,64,'.'),v.getValue(Action.NAME)); 
			 
			sb.append(m); 
			 
			sb.append(System.lineSeparator()); 
			 
			npp[gl.E_EMPTY]++; 
			 
		}); 
		 
			return sb.toString(); 
	} 

	
	 
	@Override 
	public Orion get_brew_instance(String brew,Rectangle bounds)
	{
		Orion cell = null; 
		 
		if(brew.equalsIgnoreCase("panel") || brew.equalsIgnoreCase("pnl")) 
		{ 
			cell = Orion.get_instance(new JPanel(),bounds); 
			
		}
		
		return cell;
		
	}
	
	/*
	public Orion get_brew_instance(String brew,Rectangle rect) { 
		 
		Orion [] cell = {null}; 
		 
		if(brew.equalsIgnoreCase("panel") || brew.equalsIgnoreCase("pnl")) 
		{ 
			cell[0] = Orion.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}if(brew.equalsIgnoreCase("point_start") ) 
		{ 
			cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.START); 
			 
		} else  
		if(brew.equalsIgnoreCase("point_end") ) 
		{ 
			cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.END); 
			 
		} else  
		if(brew.equalsIgnoreCase("point_ctrl1") ) 
		{ 
				cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.CTRL1); 
				 
		} else  
		if(brew.equalsIgnoreCase("point_ctrl2") ) 
		{ 
				cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.CTRL2); 
					 
		}
		else if(brew.equalsIgnoreCase("panelsmile")) 
		{ 
			cell[0] = PanelSmile.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
		}
		else if(brew.equalsIgnoreCase("button") || brew.equalsIgnoreCase("btn")) 
		{ 
			cell[0] = XButtonA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("radiobutton") || brew.equalsIgnoreCase("rbtn")) 
		{ 
			cell[0] = XRadioButtonA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("checkbox") || brew.equalsIgnoreCase("chk")) 
		{ 
			cell[0] = XCheckBoxA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("combobox") || brew.equalsIgnoreCase("cmb")) 
		{ 
			cell[0] = XComboBoxA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("scrbarx") ) 
		{ 
			cell[0] = ScrBarX.get_instance(rect,IAlign.indexOf("SCROLLBARX")); 
			 
		} else if(brew.equalsIgnoreCase("scrbary") ) 
		{ 
			cell[0] = ScrBarY.get_instance(rect,IAlign.indexOf("SCROLLBARY")); 
			 
		}else if(brew.equalsIgnoreCase("label") || brew.equalsIgnoreCase("lbl")) 
		{ 
			cell[0] = XLabelA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("vslider") || brew.equalsIgnoreCase("vsldr")) 
		{ 
			cell[0] = XSliderA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),JSlider.VERTICAL,0,255,50); 
			 
		} else if(brew.equalsIgnoreCase("hslider") || brew.equalsIgnoreCase("hsldr")) 
		{ 
			cell[0] = XSliderA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),JSlider.HORIZONTAL,0,255,50); 
			 
		} else if(brew.equalsIgnoreCase("spinner") || brew.equalsIgnoreCase("spn")) 
		{ 
			cell[0] = XSpinnerA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}   else if(brew.equalsIgnoreCase("tabpane") || brew.equalsIgnoreCase("tpane")) 
		{ 
			cell[0] = XTabbedPaneA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("tree")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XTreeA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("list")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XListA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("table")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XTableA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("editfield")) 
		{ 
			cell[0] = XTextFieldA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			
		} else if(brew.equalsIgnoreCase("textarea")) 
		{ 
			cell[0] = XTextAreaA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
		} 
		 
		 
	 	return cell[0]; 
	} 
	 */
	
	@Override 
	public boolean set_original_bounds_on_the_frame() { 
	 
		try 
		{ 
			Application.getFio().setOriginal_bounds(Application.getFio().get_frame().getBounds()); 
				 
			return true; 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 
	 

	@Override 
	public boolean nop() { 
		 
		return true; 
	} 
	
	


} 

