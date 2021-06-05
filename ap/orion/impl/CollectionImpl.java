package ap.orion.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ap.gen.IDGen;
import ap.global.gl;
import ap.global.gl.AL;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.state.ObjectRights;
import ap.orion.state.ObjectState;
import ap.prop.SBounds;
import ap.prop.SDimension;
import ap.prop.SPoint;
import ap.shape.Ru;
import ap.uat.AtOm;
import ap.utils.Biu;
import ap.utils.Su;


public class CollectionImpl { 

	 
	public  static List<Orion> items 	= new ArrayList<Orion>(); 
	 
	public  static List<Orion> selected = new ArrayList<Orion>(); 
		 
	public  static List<Orion> deleted 	= new ArrayList<Orion>(); 
	
	public  static List<Orion> childs 	= new ArrayList<Orion>(); 
	
		 
	
	public static List<Orion> get_items() { 
		return items; 
	} 

	public static void set_items(List<Orion> items) { 
		CollectionImpl.items = items; 
	} 

	public static List<Orion> get_selected() { 
		return selected; 
	} 

	public static void set_selected(List<Orion> selected) { 
		CollectionImpl.selected = selected; 
	}
	
	
	public static List<Orion> getDeleted() {
		return deleted;
	}

	public static void setDeleted(List<Orion> deleted) {
		CollectionImpl.deleted = deleted;
	}

	public CollectionImpl() { 
	 
	} 
	
	public static List<Orion> get_by_state(List<Orion> items_list,int state) 
	{ 
		return items_list.stream().filter(b->( Biu.ISA(b.getState().get_state(),state))).collect(Collectors.toList()); 
	} 

	public static List<Orion> get_by_state_not(List<Orion> items_list,int state) 
	{ 
		return items_list.stream().filter(b->(! Biu.ISA(b.getState().get_state(),state))).collect(Collectors.toList()); 
	} 
	
	public static List<Orion> get_by_right(List<Orion> items_list,int right) 
	{ 
		return items_list.stream().filter(b->( Biu.ISA(b.getRights().get_rights(),right))).collect(Collectors.toList()); 
	}
	
	public static List<Orion> get_by_right_not(List<Orion> items_list,int right) 
	{ 
		return items_list.stream().filter(b->( ! Biu.ISA(b.getRights().get_rights(),right))).collect(Collectors.toList()); 
	}
	
	public static int get_max_id(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getIdo().getId()).max().getAsInt(); 
	}
	
	public static int get_min_id(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getIdo().getId()).min().getAsInt(); 
	}
	
	public static Orion get_by_distribution(List<Orion> items_list,Dimension index) 
	{ 
		return items_list.stream().filter(b->(
				
				b.getDecoro().getDistribution().width  == index.width  &&
				b.getDecoro().getDistribution().height == index.height 
				
				
				)).findFirst().orElse(null); 
	}
	
	public static int get_min_x(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getBounds().x).min().getAsInt(); 
	}
	
	public static int get_min_y(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getBounds().y).min().getAsInt(); 
	}
	
	public static int get_max_x(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getBounds().x).max().getAsInt(); 
	}
	
	public static int get_max_y(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->b.getBounds().y).max().getAsInt(); 
	}
	
	public static int get_max_x_width(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->(b.getBounds().x + b.getBounds().width)).max().getAsInt(); 
	}
	
	public static int get_max_y_height(List<Orion> items_list) 
	{ 
		return items_list.stream().mapToInt(b->( b.getBounds().y + b.getBounds().height) ).max().getAsInt(); 
	}
	
	public static Orion get_by_id(List<Orion> items_list,int id) 
	{ 
		return items_list.stream().filter(b->(b.getIdo().getId() == id )).findFirst().orElse(null); 
	}
	
	public static Orion get_by_class_name(List<Orion> items_list,String class_name) 
	{ 
		return items_list.stream().filter(b->(b.get_class().equalsIgnoreCase(class_name))).findFirst().orElse(null); 
	}
	
	public static List<Orion> get_by_class_name_list(List<Orion> items_list,String class_name) 
	{ 
		return items_list.stream().filter(b->(b.get_class().equalsIgnoreCase(class_name))).collect(Collectors.toList()); 
	}
	
	public static List<Orion> get_by_class_name(List<Orion> items_list,String [] class_names) 
	{ 
		List<Orion> m_result = new ArrayList<Orion>();
		
		Arrays.asList(class_names).forEach(a->{
			
			m_result.addAll(
					
					items_list.stream().filter(b->(b.get_class().equalsIgnoreCase(a))).collect(Collectors.toList()) 
			);
		});
		
		return m_result;
	}
	
	public static Orion get_by_name(List<Orion> items_list,String name) 
	{ 			
		return items_list.stream().filter(b->(b.getName().equalsIgnoreCase(name))).findFirst().orElse(null); 
	}
	
	
	public static Orion get_tops_except_me(List<Orion> items_list,int id) 
	{ 
		return items_list.stream().filter(b->(b.getIdo().getId() != id  && b.getIdo().getIndex() == gl.E_EMPTY)).findFirst().orElse(null); 
	}
	
	public static List<Orion> get_by_id_list(List<Orion> items_list,int id) 
	{ 
		return items_list.stream().filter(b-> (b.getIdo().getId() == id)).collect(Collectors.toList());
	}
	
	public static CollectionImpl get_instance() { 
		 
		return new CollectionImpl(); 
	} 
	

	public static List<Orion> get_target_items(Orion orion)
	{
		
		return CollectionImpl.items.stream().filter(b->(
				
				// Removed but error.
				//( b.getBounds().contains(orion.getBounds()) || b.get_abs_bounds().contains(orion.getBounds()) ) &&
				
				// Tray 1. Error prone.
				// (b.get_abs_bounds().contains(orion.getBounds()) ) &&
				
				// Tray 2. Total abs.
				(b.get_abs_bounds().contains(orion.get_abs_bounds()) ) &&
				
				!b.getName().equalsIgnoreCase(orion.getName()) &&
				
				!b.is_desk_top() && 
				
				b.get_class().equalsIgnoreCase("Orion") 
				
				// Common parent container.
				
				)
				
				).collect(Collectors.toList()).stream().sorted(Comparator.comparing(s -> ((Orion)s).getIdo().getLevel()).reversed()).collect(Collectors.toList());
		
		
	}
	
	public static  List<Orion> get_selected_items(Orion orion)
	{
		
		List<Orion> m_return  = CollectionImpl.get_by_state(orion.get_all_childs(),ObjectState.SELECTED);
		
		List<Orion> m_not_deletable = CollectionImpl.get_by_right_not(orion.get_all_childs(),ObjectRights.DELETABLE);
		
					m_return.removeAll(m_not_deletable);
					
					return m_return;
	}

	
	public static  void get_selection_items(Orion orion)
	{
		
		List<Orion> m_scope = orion.get_components();
		
		m_scope.removeAll(CollectionImpl.get_by_right_not(orion.get_components(),ObjectRights.DELETABLE));
		
		m_scope.forEach(a->{
			
			boolean bl_catch = orion.getDecoro().get_selector().contains(a.getBounds());
			
			/*
			gl.sfn("%s...Объект...[%s]...Селектор...[%s]...Результат...[%s]",
					gl.format(a.getName(),AL.RIGHT,24),
					SBounds.toString(a.getBounds()),
					SBounds.toString(orion.getDecoro().get_selector()),
					bl_catch
					
					);
			*/
			
			a.getState().set_selected(bl_catch);
			
			a.repaint();
			
			
		});
		
			CollectionImpl.update_selected();
		
	}

	
	public static Orion get_target_item(Orion orion)
	{
			
		List<Orion> items = new ArrayList<Orion>();
		
		List<Orion> work  = new ArrayList<Orion>();
		
		work.addAll(CollectionImpl.get_all_components());
		
		work.forEach(a->{
	
			if(
			a.get_abs_bounds().contains(
					
					orion.getIdo().getPid() == gl.E_OK ? orion.getBounds() : orion.get_abs_bounds()
					
					)  &&
			
			(a.get_class().equalsIgnoreCase("Orion"))  &&
			
			a.getIdo().getId() != orion.getIdo().getId()
			
			)
			{
			
					items.add(a);
					
					//gl.tx_k(new Object() {},gl.sf("Potential target...%s",a.getName()));
			
					
			}
			/*
			else
			{
			
				gl.sfn("Target NOT candidat...%s...%s...<>...%s",a.getName(),
						SBounds.toString(
								a.get_abs_bounds()
								),
							SPoint.toString(
								
								new Point(
										orion.get_abs_bounds().x,
										orion.get_abs_bounds().y
									)

								)
						);
				
			
				
			}
					
			*/
				
		});
		
		
		//return items.stream().sorted(Comparator.comparing(s -> 
		//((Orion)s).getIdo().getLevel()).reversed()).collect(Collectors.toList()).get(gl.E_EMPTY);
		//
		

		return items.stream().sorted(Comparator.comparing(s -> 
		((Orion)s).getIdo().getLevel()).reversed()).findFirst().orElse(null);
		
	}

	
	public static List<Orion> isa_unique_pid(List<Orion> items)
	{
		final List<Orion> l = new ArrayList<Orion>();
		
		items.forEach(a->{
			
			if(	
					!l.contains(a.getIdo().getOwner())
			  )
			{
					l.add(a.getIdo().getOwner());
			}
			
			
		});
	
					return l;
	}
	
	public static boolean check_owners_unique_rule(List<Orion> source,List<Orion> owners)
	{
		

		List<Orion> m_owners = CollectionImpl.isa_unique_pid(source);
	
		int 		m_owners_size = m_owners.size();
		
		String 		msg = gl.sf("Проверка селекции объектов...[%d]",m_owners_size);
		
		if(!gl.tx(new Object(){},(m_owners_size == gl.E_OK),msg))
		{
			
					return gl.show_msg(gl.sf("Проверка объектов...[%d]",m_owners_size),gl.sf(msg),JOptionPane.WARNING_MESSAGE,false);
			
		}
	
					owners.addAll(m_owners);
					
					return (owners.size() == gl.E_OK);
	}
	
	public static boolean clean_only_selected()
	{
		
		Orion owner = null;
		
		try
		{
			
			
		// Проверка корректности селектированных для удаления объектов.
			
		List<Orion> owners = new ArrayList<Orion>();
			
		if (!check_owners_unique_rule(CollectionImpl.selected,owners))
			return false;
			
		
		// Устанавливаем владельца селектированных объектов.
		
		owner =  owners.get(gl.E_EMPTY);
		
		// Сбор метрик объектов. 
		
		// Общее количество объектов перед удалением.
		
		List<Orion> m_all_components 	= owner.get_components(); 
		
		int 		m_count_all_objects = owner.getComponentCount();
		
		// Количество селектированных объектов.
		
		int m_selected_components = CollectionImpl.selected.size();

		
		// Первый шаг.
		
		// Удаляем селектированные объекты из общего списка компонентов владельца.
		
		m_all_components.removeAll(CollectionImpl.selected);
				
		// Второй шаг.
		
		// Удаляем компоненты у владельца.
		
		owner.removeAll();
		
		final Orion o = owner;
		
		m_all_components.forEach(co->
		{
			o.add(co);
		});
		
		// Третий шаг.
		
		// Проверки.
		
		int 		m_count_all_objects_after = owner.getComponentCount();
		
		// Выполняем проверку удаления.
		
				
		boolean bl_result = ((m_count_all_objects_after + m_selected_components )  == m_count_all_objects );
		
		if(!gl.tx(new Object(){},bl_result,gl.sf(""
				+ "Объектов перед удалением...[%d]"
				+ "...планируется...[%d]"
				+ "...остаток после...[%d]"
				+ "...итого [%d] == [%d]",
				
				m_count_all_objects,
				m_selected_components,
				m_count_all_objects_after,
			   (m_count_all_objects_after + m_selected_components),
				m_count_all_objects
				
				)))
		{
			return false;
		}
		
		
			return bl_result;
			
		} // try.
		
		catch(Exception e)
		{
			return gl.tx(new Object() {},1==0,gl.sf("[%s]",e.toString().substring(0,32)));
		}
		finally
		{
			owner.revalidate();
			
			owner.repaint();


		}
		
		
	}

	
	public static boolean clean_all()
	{
		try
		{
			
		Orion dt = Application.getDio().get_desk_top();
		
		// For verbose option.
	
		List<Orion> 	m_will_deleted  = CollectionImpl.get_by_right(
				
						dt.get_components(),
				
		ObjectRights.DELETABLE);
		
		CollectionImpl.deleted.clear();
		
		deleted.addAll(m_will_deleted);
		
		
		// Save immortals.
		
		List<Orion> m_keep  = CollectionImpl.get_by_right_not(
				
				dt.get_components(),
				
				ObjectRights.DELETABLE);
		
		// Remove all.
		
		dt.removeAll();
		
		// Synchro with repositary.
		
		items.clear();
		
		selected.clear();
		
		dt.repaint();
		
		// Check of removing.
		
		if(!
		gl.tx(new Object() {},(
										dt.getComponentCount()	== gl.E_EMPTY &&
										items.size() 			== gl.E_EMPTY &&
										selected.size() 		== gl.E_EMPTY
										
							  ),
		gl.sf("Проверка удаления всех объектов")))
		{
			return false;
		}
		
		// Restore mr.IMMORTALIO.
		
		dt.insert(m_keep);
		
		// Check of restoring.
		
		if(!
				gl.tx(new Object() {},
						(dt.getComponentCount()==m_keep.size()) && 
						CollectionImpl.check_iid_integrity(),
						gl.sf("Проверка восстановления объектов...%d == %d",
						dt.getComponentCount(),m_keep.size()
						)))
		{
			return false;
		}
		
			return true;
			
		}
		catch(Exception e)
		{
			return gl.tx(new Object() {},1==0,gl.sf("Ошибка...%s",e.toString()));
		}
	}
	
	public static int update_iid()
	{
		int m_iid = CollectionImpl.get_max_id(Application.getDio().get_desk_top().get_all_childs());

		gl.tx_k(new Object() {},gl.sf("Обновление IID...%d",m_iid));

		IDGen.reset(m_iid);
		
		return m_iid;
		
	}
	
	public static boolean check_iid_integrity()
	{
		
		return  (
				
				CollectionImpl.get_max_id(Application.getDio().get_desk_top().get_all_childs()) ==
				
				CollectionImpl.update_iid()
				
				);
	
	}

	public static int update_selected()
	{
		CollectionImpl.selected.clear();
		
		CollectionImpl.selected.addAll(
	
				get_by_state(
								Application.getDio().get_desk_top().get_all_childs()
								,ObjectState.SELECTED)
						);
				

		//gl.tx_k(new Object() {},gl.sf("Селектировано объектов...%d",CollectionImpl.selected.size()));
	 	
		return CollectionImpl.selected.size();

		
	}

	public static List<Orion> get_components()
	{
		
		return Application.getDio().get_desk_top().get_components();
		
	}
	
	public static List<Orion> get_all_components()
	{
		
		return Application.getDio().get_desk_top().get_all_childs();
		
	}

	public static List<Orion> get_all_components_pure()
	{
		
		return get_all_components().stream().filter(b->(b.getRights().is_deletable())).collect(Collectors.toList());
		
	}
	
	public static List<Orion> get_writable_components()
	{
	
		return get_components()
				.stream()
				.filter(b->(b.getIdo().getPid()==gl.E_OK) && b.getRights().is_deletable()).collect(Collectors.toList());
	
	}
	
	public static List<Orion> get_writable_components(List<Orion> list)
	{
	
		return list
				.stream()
				.filter(b->(b.getIdo().getPid()==gl.E_OK) && b.getRights().is_deletable()).collect(Collectors.toList());
	
	}
	
	public static List<Orion> inject_child_items()
	{
		// Необходимо добавить в items.
		// Дочерние элементы.
		
		List<Orion> m_new_set = new ArrayList<Orion>();
		
		
		CollectionImpl.items.forEach(a->{
				
				List<Orion> m_new_item = a.get_all_childs();
				
				m_new_item.forEach(b->{
			
					if(CollectionImpl.get_by_id(CollectionImpl.items,b.getIdo().getId()) == null)
					{
						gl.tx_k(new Object() {},gl.sf("Объекта нет в индексе...%s...образ...%d",b.getName(),b.getImage().getImageText().length()));
						
						if(!m_new_set.contains(b))
							m_new_set.add(b);
					}
					else
					{
						gl.tx_k(new Object() {},gl.sf("Объект в индексе...%s",b.getName()));
					}
						
				});
			
		});
					
				CollectionImpl.show("New set of items",m_new_set);
		
				//CollectionImpl.show("After update items",CollectionImpl.items);
		
				gl.tx_k(new Object() {},gl.sf("Добавлено объектов...%d",
						m_new_set.size()));
			 	
				return m_new_set;
		
	}
	

	public static void show(String caption,List<Orion> value)
	{
		gl.sfn("%s%s%s",caption,
				System.lineSeparator(),
				gl.replicate("-",80)
				);
		
		value.forEach(a->
		{
			gl.tx_k(new Object() {},gl.sf("%s...%s",
					a.getName(),
					a.getClass().getSimpleName()
					));
			
		});
		
		gl.sfn("%s",gl.replicate("-",80));
		
	}
	
	public static String toString(String caption,List<Orion> value)
	{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
		gl.sf("%s%s%s",caption,
				System.lineSeparator(),
				gl.replicate("-",32)
				));
		
		sb.append(System.lineSeparator());
		
		value.forEach(a->
		{

			sb.append(
			
			gl.sf("%s...%s",
					a.getName(),
					a.getClass().getSimpleName()
				 )
			);
					sb.append(System.lineSeparator());
			
		});
		
		sb.append(
				gl.sf("%s",gl.replicate("-",32))
		);
		
		return sb.toString();
		
	}
	
	public static boolean refresh() 
	{ 
		CollectionImpl.items.clear();
		
		CollectionImpl.items.addAll(Application.getDio().get_desk_top().get_all_childs());
		
		return (CollectionImpl.items.size() == Application.getDio().get_desk_top().get_all_childs().size()); 
	} 
	
	public static Dimension get_pref_size_fut(Orion owner)
	{
		
		List<Orion> l = owner.get_components();
	 
		return new Dimension(
				
				CollectionImpl.get_max_x_width(l),
				CollectionImpl.get_max_y_height(l)
				
				);
	}

	
	public static boolean  reset_selected(Orion owner)
	{

		Orion m_owner =  owner == null ? Application.getDio().get_desk_top() : owner;
		
		List<Orion> m_selected = m_owner.get_all_childs().stream().filter(b->(b.getState().is_selected())).collect(Collectors.toList());
		
		m_selected.forEach(a->
		{
			a.getState().set_selected(false);
			 
			 a.repaint();
			
		});
		
		m_selected.clear();
		
		m_selected.addAll(m_owner.get_all_childs().stream().filter(b->(b.getState().is_selected())).collect(Collectors.toList()));
		
		boolean bl_result = (m_selected.size() == gl.E_EMPTY);
		
		m_selected.clear();
				
		return gl.tx(new Object() {},bl_result,gl.sf("Сброс состояния -селектирован- объектов...[%d]",m_selected.size()));
		
	}

	
	public static boolean  reset_focused(Orion owner)
	{
		
		
		Orion m_owner =  (owner == null) ? Application.getDio().get_desk_top() : owner;
				
		// List<Orion> m_focused = m_owner.get_all_childs().stream().filter(b->(b.getState().is_focused())).collect(Collectors.toList());
		
		List<Orion> m_focused = CollectionImpl.get_by_state(m_owner.get_all_childs(),ObjectState.FOCUSED);
		
		m_focused.forEach(a->
		{
			a.getState().set_focused(false);
			 
			 a.repaint();
			 
			 gl.sfn("Reset focused...[%s]",a.getName());
			
		});
		

		m_focused.clear();
		
		m_focused.addAll(m_owner.get_all_childs().stream().filter(b->(b.getState().is_focused())).collect(Collectors.toList()));
		
		boolean bl_result = (m_focused.size() == gl.E_EMPTY);
		
		m_focused.clear();
		
		return gl.tx(new Object() {},bl_result,gl.sf("Сброс состояния -фокусирован- объектов...[%d]",m_focused.size()));
		
	}
	
	public static List<String> get_items_from_combo(ap.mercury.components.ComboBox<String> owner)
	{
		List<String> l = new ArrayList<String>();
		
        int count = owner.getItemCount();
        
        for (int i = 0; i < count; i++) {
         
        	l.add((String)owner.getItemAt(i));
        }
        
        	return l;
	}
	
	public static List<String> get_tickers_from_headers(String headers)
	{
		List<String> l = Su.get_as_list_for_data(headers);
		
		List<String> t = new ArrayList<String>();
		
        l.forEach(a->
        {
        	
        	String item = Su.BeforeAt(Su.BeforeAtFirst(a,gl.sf("%s",Parser.FIELD_DLM)),
        			gl.sf("%s",Parser.ATTR_DLM)			
        			);
        	
        	if(item.trim().length() > gl.E_EMPTY)
        		t.add(item);
        	
        });
		
        	return t;
        
	}
	
	public static List<String> get_series_from_headers(String headers)
	{
		List<String> l = Su.get_as_list_for_data(headers);
		
		List<String> t = new ArrayList<String>();
		
        l.forEach(a->
        {
        	String index = Su.AfterAt(a,gl.sf("%s",Parser.ATTR_DLM));
        	
        	String item = Su.BeforeAtFirst(a,gl.sf("%s",Parser.FIELD_DLM));
        	
        	if(item.trim().length() > gl.E_EMPTY)
        		t.add(gl.sf("%s%s%s",item,gl.sf("%s",Parser.ATTR_DLM),index));
        	
        });
		
        	return t;
        
	}
	
	public static boolean update_preferred_size(Orion orion)
	{
		
		// Расчет преферед в реал тайме.
		
		Orion owner = orion.getIdo().getOwner();
		
		Dimension dim_cur = new Dimension(320,240);
		
		Dimension dim_fut = CollectionImpl.get_pref_size_fut(owner);
		
		Dimension dim_res = dim_cur;
		
		boolean bl_need_update = Ru.dim_greate_than(dim_fut,dim_cur);
		
		if(bl_need_update)
		{
			dim_res = Ru.get_preffered_dim(dim_cur,dim_fut);
		}
		else
		{
			dim_res = dim_cur;
		}
		
		gl.tx_k(new Object(){},gl.sf("Текущий преферед...[%s]..."
				+ "Расчетный...[%s]..."
				+ "Результат...[%s]..."
				+ "Необходимость обновления...[%s]",
				SDimension.toString(dim_cur),
				SDimension.toString(dim_fut),
				SDimension.toString(dim_res),
				bl_need_update
				));
		
		if(bl_need_update)
		{
			owner.setPreferredSize(dim_res);
			
			owner.getData().setPrefered_size(SDimension.toString(dim_res));
		}
		return bl_need_update;
	}

	public static void main(String[] args) { 
	 

	} 

} 
