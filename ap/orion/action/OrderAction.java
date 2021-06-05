package ap.orion.action;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

import ap.area.AreaManager;
import ap.gen.IDGen;
import ap.global.gl;
import ap.mercury.components.Gimension;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.prop.SBounds;
import ap.prop.SDimension;
import ap.utils.DU;
import ap.utils.Su;


public class OrderAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"order","order()","ордер"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public OrderAction() {
		
	}

	public OrderAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public OrderAction(String text) {
		super(text);
		
	}

	public OrderAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_set_impl(CmdProcess proc)
	{
		
		if(!check_selected())
		{
			return false;
		}
		
		// Проверка корректности селектированных объектов.
		
		List<Orion> owners = new ArrayList<Orion>();
					
		if (!CollectionImpl.check_owners_unique_rule(CollectionImpl.selected,owners))
			return false;
		

		// Устанавливаем владельца селектированных объектов.
		
		Orion owner =  owners.get(gl.E_EMPTY);
		
		// Текущий преферед на владельце.
		
		Dimension m_dim = owner.getPreferredSize();
	
		// Количество селектированных объектов.
		
		int m_selected_size = CollectionImpl.selected.size();
		
		// Шаблон размерности для расчета емкости.
		
		Rectangle m_preffered_bounds = CollectionImpl.selected.get(gl.E_EMPTY).getBounds();
		
		gl.sfn("----> Шаблон...[%s]",SBounds.toString(m_preffered_bounds));
		
		// Х - размерность.
		
		int m_x_capacity = m_dim.width / m_preffered_bounds.width;
		
		// Y - размерность.
		
		int m_y_capacity = m_dim.height / m_preffered_bounds.height;
		
		// Общая емкость.
		
		int m_total_capacity = m_x_capacity * m_y_capacity;
		
		// Значение параметра распределения с командной строки .[10:10]
		
		List<String> m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
		
		// Парсинг параметров.
		
		int m_columns = DU.to_int(
				Su.BeforeAt(
				m_params.get(gl.E_EMPTY)
				.replace(","," ")
				.replace("."," ")
				.replace(":"," "),
				" "
				));
		
		
		int m_rows = DU.to_int(
				Su.AfterAt(
				m_params.get(gl.E_EMPTY)
				.replace(","," ")
				.replace("."," ")
				.replace(":"," "),
				" "
				));
		
		
		if (!gl.tx(new Object(){},(m_columns != gl.E_ERROR && m_rows != gl.E_ERROR),gl.sf("Текущий преферед...%s...Шаблон...%s...eмкость x...%d...y...%d...всего...%d...колонок...%d...строк...%d",
				SDimension.toString(m_dim),
				SBounds.toString(m_preffered_bounds),
				m_x_capacity,
				m_y_capacity,
				m_total_capacity,
				m_columns,
				m_rows
				)))		
			{
				return false;
			}
			
			// Запрашиваемая емкость.
			
				int m_request_capacity = m_columns*m_rows;
			
				if(m_request_capacity < m_selected_size)
				{
					
					// Расчет дополнительных строк.
					
					int m_add_rows = ((m_selected_size - m_request_capacity)/m_columns)+1;
					
					m_rows += m_add_rows;
				
					gl.tx_k(new Object(){},gl.sf("Коррекция емкости добавлено строк...%d...итого строк...%d",m_add_rows,m_rows));
						
				}
				
				
				
				// Распределение.
				
				// Устанавливаем размер области согласно скорректированного распределения.
				
				m_dim.width  = m_columns * m_preffered_bounds.width;
				
				m_dim.height = m_rows * m_preffered_bounds.height;
				

				gl.tx_k(new Object(){},gl.sf("Область распределения...%s...колонок...%d...строк...%d",
						SDimension.toString(m_dim),
						m_columns,
						m_rows
						));
				
				
				AreaManager am = new AreaManager(m_dim,new Dimension(m_columns,m_rows));
		
				//int [] index = {gl.E_EMPTY};
							
				for(int j=0;j<am.getAreaInCells().height;j++) 
					for(int i=0;i<am.getAreaInCells().width;i++) 
					{
						int m_index = am.getLinearIndex(i, j);
						
						if(m_index < m_selected_size)
						{
								Rectangle 	t_rect = am.get(i,j);
							
											t_rect.x += m_preffered_bounds.x;
							  
											t_rect.y += m_preffered_bounds.y;
											
							Orion 	orion = CollectionImpl.selected.get(m_index); 
					
									orion.setBounds(t_rect);
									
									orion.getDecoro().setDistribution(new Gimension(i,j));
						}
					}
				
				
		
				// Обновляем менеджера компоновки.
			
				owner.getDecoro().setAreaManager(am);
				
				gl.tx(new Object(){},owner.getDecoro().getAreaManager() != null,
						gl.sf("Установка менеджера областей...[%s]",am.toStringShort()));
				
				
				// Обновление преферед владельца.
				
				int m_max_x = m_dim.width + m_preffered_bounds.x;
				
				int m_max_y = m_dim.height + m_preffered_bounds.y;
				
				if(m_max_x > m_dim.width || m_max_y > m_dim.height)
				{
					owner.setPreferredSize(new Dimension(m_max_x,m_max_y));
					
					owner.revalidate();
					
					gl.tx_k(new Object(){},gl.sf("Установка preffered размерности...%d...%d...%s",
							m_max_x,m_max_y,
							SDimension.toString(m_dim)));
				}
				
					return true;
		
	}
	
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
		this.setProcess(process);
		
		welcome();
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(
				 p.get_count_of_headers() 	== gl.E_OK 
				)
		 {
	
		 	// Check of the switch.
		 
			if(p.isa_set_option())
			{ 
				return isa_set_impl(p);
				
			}	else
			{
				return false;
			}
		 }
		else
		{
				this.setResponse(null);
			
				return false;
		}
	 
	 		
	 
	} // try.
	catch(Exception e)
	{
				return false;
	}
	 
	} 
	
	

	public static OrderAction get_instance()
	{
		OrderAction lafa = new OrderAction(); 
		
		return new OrderAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
