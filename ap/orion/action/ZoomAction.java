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
import ap.shape.Ru;
import ap.utils.DU;
import ap.utils.Nu;
import ap.utils.Su;


public class ZoomAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"zoom","zoom()","z","z()","зум"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public ZoomAction() {
		
	}

	public ZoomAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public ZoomAction(String text) {
		super(text);
		
	}

	public ZoomAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_out_impl()
	{

			CmdProcess proc = this.getProcess();
		
		try
		{
			List<String> m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
			
			int m_step = DU.to_int(m_params.get(gl.E_EMPTY));
					
			if(!gl.tx(new Object(){},(m_step > gl.E_EMPTY),gl.sf("Значение параметра инкремента ...%d",m_step)))
			{
				return false;
			}
			
	
			Orion dt = Application.getDio().get_desk_top();
			
			AreaManager am = dt.getDecoro().getAreaManager();
					
			Dimension p_dim = dt.getPreferredSize();
			
			if (!
			gl.tx(new Object(){},am != null,
					gl.sf("Проверка менеджера областей."))
			)
				return false;
			
			int m_selected_size = CollectionImpl.update_selected();

			if (!
					gl.tx(new Object(){},m_selected_size > gl.E_EMPTY,
							gl.sf("Проверка селектированных объектов."))
					)
						return false;
			
			// Уменьшаем область распределения на величину шага.
			
			int i_width  = -((am.getAreaInCells().width  - 1 ) * m_step);
			
			int i_height = -((am.getAreaInCells().height - 1 ) * m_step);
			
			// Корректировка менеджера.
			
			Dimension 	dim = am.getArea();
			
						dim.width  = dim.width  + i_width;
						
						dim.height = dim.height + i_height;
						
			am.setArea(dim);
			
			// Место притяжения,- верхний левый объект.
			
			Orion base = CollectionImpl.get_by_distribution(CollectionImpl.selected,new Dimension(0,0));
			
			if (!
					gl.tx(new Object(){},base != null,
							gl.sf("Выбор маяка."))
					)
						return false;
			
			
			// 
			
			boolean [] bl_constraint = {false};
			
			// Перераспределение.
			
			for(int j=0;j<am.getAreaInCells().height;j++) 
				for(int i=0;i<am.getAreaInCells().width;i++) 
				{
					int m_index = am.getLinearIndex(i, j);
					
					if(m_index < m_selected_size)
					{
							Rectangle 	t_rect = am.get(i,j);
							
							// Корректируем по маяку.
							
							t_rect.x = t_rect.x + base.getBounds().x;
							
							t_rect.y = t_rect.y + base.getBounds().y;
							
							// Контроль минимального размера.
							
							if(t_rect.width > Orion.MIN_HEIGHT && t_rect.height > Orion.MIN_HEIGHT)
							{
							
												
								Orion 	orion = CollectionImpl.selected.get(m_index); 
						
										orion.setBounds(t_rect);
										
										orion.getDecoro().setDistribution(new Gimension(i,j));
							}
							else
							{
								bl_constraint[0] = true;
							}
					}
				}
			
			// Update area manager of the desk top.
			
			if(!bl_constraint[0])
			{
				Application.getDio().get_desk_top().getDecoro().setAreaManager(am);
				
				
				if((am.getArea().width + base.getBounds().x) < p_dim.width)
					p_dim.width = (am.getArea().width + base.getBounds().x);
				
				if((am.getArea().height + base.getBounds().y) < p_dim.height)
					p_dim.height = (am.getArea().height + base.getBounds().y);
				
				dt.setPreferredSize(p_dim);
				
				dt.revalidate();
	
			}
			else
			{
				gl.tx_k(new Object(){},gl.sf("Были достигнуты МИНИМАЛЬНЫЕ ограничения по размеру...[%d,%d]",Orion.MIN_HEIGHT,Orion.MIN_HEIGHT));
			}
			
				return true;

		}
		catch(Exception e)
		{
					return gl.tx(new Object(){},false,gl.sf("%s",e.toString()));
		}
	
	}
	

	// Under construction.
	
	public boolean isa_in_impl()
	{


		CmdProcess proc = this.getProcess();
	
	try
	{
		List<String> m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
		
		int m_step = DU.to_int(m_params.get(gl.E_EMPTY));
				
		if(!gl.tx(new Object(){},(m_step > gl.E_EMPTY),gl.sf("Значение параметра инкремента ...%d",m_step)))
		{
			return false;
		}
		
		Orion 		dt = Application.getDio().get_desk_top();
				
		AreaManager am = dt.getDecoro().getAreaManager();
				
		Dimension 	p_dim = dt.getPreferredSize(); 
		
		if (!
		gl.tx(new Object(){},am != null,
				gl.sf("Проверка менеджера областей."))
		)
			return false;
		
			
		int m_selected_size = CollectionImpl.update_selected();

		if (!
				gl.tx(new Object(){},m_selected_size > gl.E_EMPTY,
						gl.sf("Проверка селектированных объектов."))
				)
					return false;
		
		// Увеличиваем область распределения на величину шага.
		
		int i_width  = ((am.getAreaInCells().width  - 1 ) * m_step);
		
		int i_height = ((am.getAreaInCells().height - 1 ) * m_step);
		
		// Корректировка менеджера.
		
		Dimension 	dim = am.getArea();
		
					dim.width  = dim.width  + i_width;
					
					dim.height = dim.height + i_height;
					
		am.setArea(dim);
		
		
		// Место притяжения,- верхний левый объект.
		
		Orion base = CollectionImpl.get_by_distribution(CollectionImpl.selected,new Dimension(0,0));
		
		if (!
				gl.tx(new Object(){},base != null,
						gl.sf("Выбор маяка."))
				)
					return false;
		
		// Ограничения д.б.
		
		Dimension max_dim = Application.getDio().get_desk_top().getPreferredSize();
		
		boolean [] bl_constraint = {false};
		
		// Перераспределение.
		
		for(int j=0;j<am.getAreaInCells().height;j++) 
			for(int i=0;i<am.getAreaInCells().width;i++) 
			{
				int m_index = am.getLinearIndex(i, j);
				
				if(m_index < m_selected_size)
				{
						Rectangle 	t_rect = am.get(i,j);
						
						// Корректируем по маяку.
						
						t_rect.x = t_rect.x + base.getBounds().x;
						
						t_rect.y = t_rect.y + base.getBounds().y;
						
						// Контроль максимального размера.
						
						if(t_rect.width < max_dim.width && t_rect.height < max_dim.height)
						{
						
											
							Orion 	orion = CollectionImpl.selected.get(m_index); 
					
									orion.setBounds(t_rect);
									
									orion.getDecoro().setDistribution(new Gimension(i,j));
						}
						else
						{
							bl_constraint[0] = true;
							
						}
				}
			}
		
						// Update area manager of the desk top.
						
						if(!bl_constraint[0])
						{
							dt.getDecoro().setAreaManager(am);
							
							if((am.getArea().width + base.getBounds().x) > p_dim.width)
								p_dim.width = (am.getArea().width + base.getBounds().x);
							
							if((am.getArea().height + base.getBounds().y) > p_dim.height)
								p_dim.height = (am.getArea().height + base.getBounds().y);
							
							dt.setPreferredSize(p_dim);
							
							dt.revalidate();
						}
						else
						{
							gl.tx_k(new Object(){},gl.sf("Были достигнуты МАКСИМАЛЬНЫЕ ограничения по размеру...[%d,%d]",max_dim.width,max_dim.height));
						}
						
					
						return true;

	}
	catch(Exception e)
	{
						return gl.tx(new Object(){},false,gl.sf("%s",e.toString()));
	}

		}

	
	public boolean isa_set_impl(CmdProcess p)
	{
		
		int m_selected_size = CollectionImpl.update_selected();
		
		gl.tx_k(new Object(){},gl.sf("Выбранных объектов...%d",m_selected_size));		
				
		if(m_selected_size == gl.E_EMPTY)
		{
			return true;
		}
		
		// Тип операции.
		
		if (p.isa_in_option())
		{
			List<Boolean> state = new ArrayList<Boolean>();
			
			int m_count_of_repeat = p.isa_for_option() ? (DU.to_int(p.get_param_by_index(gl.E_OK))) == gl.E_ERROR ? 1 : DU.to_int(p.get_param_by_index(gl.E_OK)) : 1;
			
			for(int i=0;i<m_count_of_repeat;i++)
			{
				state.add(this.isa_in_impl());
			}
			
				return !state.contains(false);
			
			
		}
		else if (p.isa_out_option())
		{
			List<Boolean> state = new ArrayList<Boolean>();
			
			int m_count_of_repeat = p.isa_for_option() ? (DU.to_int(p.get_param_by_index(gl.E_OK))) == gl.E_ERROR ? 1 : DU.to_int(p.get_param_by_index(gl.E_OK)) : 1;
			
			for(int i=0;i<m_count_of_repeat;i++)
			{
				state.add(this.isa_out_impl());
			}
			
				return !state.contains(false);
			
		}
		
				return false;
				
	}
	
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
		this.setProcess(process);
		
		welcome();
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(p.get_count_of_headers() 	== gl.E_OK 		&&
					
		    	 p.get_count_of_switches() 	!= gl.E_EMPTY 	&&
		
		    	 Nu.in(p.get_count_of_params(),1,2) 
				)
		 {
	
		 	// Check of the switch.
		 
			 this.setResponse(null);
			 
			if(p.isa_set_option())
			{ 
				return isa_set_impl(p);
				
			}		else
			{
				return false;
			}
		 }
		else
		{
		
			gl.tx_e(new Object(){},gl.sf("Level:0...%s",this.get_action_name()));		
			
			return false;
		}
	 
	 		
	 
	} // try.
	catch(Exception e)
	{
				return false;
	}
	 
	} 
	
	

	public static ZoomAction get_instance()
	{
		ZoomAction lafa = new ZoomAction(); 
		
		return new ZoomAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
