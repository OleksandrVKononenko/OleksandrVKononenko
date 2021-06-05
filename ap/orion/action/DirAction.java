package ap.orion.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.area.AreaManager;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.prop.SBounds;
import ap.prop.SColor;
import ap.prop.SDimension;
import ap.utils.CU;
import ap.utils.DU;
import ap.utils.Su;


public class DirAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"prop","prop()","p","p()","проп"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public DirAction() {
		
	}

	public DirAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public DirAction(String text) {
		super(text);
		
	}

	public DirAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_set_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		int m_selected_size = CollectionImpl.update_selected();
	
		gl.tx_k(new Object(){},gl.sf("Выбранных объектов...%d",m_selected_size));		
		
		if(m_selected_size == gl.E_EMPTY)
		{
			return true;
		}
		
		
		// Читаем параметр.

		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_param = m_params.get(gl.E_EMPTY);

		
		if(proc.isa_bkg_option())
		{
			try
			{
				
						gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s",m_param));		

						Color bkg = CU.toColor(m_param);
			
			if(bkg == null)
			{

						gl.tx_e(new Object(){},gl.sf("Парсинг параметра...%s",m_param));		
				
						return false;
			}
			
			gl.tx_k(new Object(){},gl.sf("Параметр...%s...цвет...%s",m_param,SColor.toString(bkg)));		
			
			CollectionImpl.selected.forEach(a->{
				
						a.setBackground(bkg);
				
			});
			
						Application.getDio().get_desk_top().repaint();
				
						return true;
			}
			catch(Exception e)
			{
						return false;
			}
		}
		else if(proc.isa_text_option())
		{
			try
			{
				
						gl.tx_k(new Object(){},gl.sf("Установка текста представлен параметр...%s",m_param));		
			
			CollectionImpl.selected.forEach(a->{
				
						a.getDecoro().set_text(m_param);
						
						a.repaint();
				
			});
			
						Application.getDio().get_desk_top().repaint();
				
						return true;
			}
			catch(Exception e)
			{
						return false;
			}
		}

		
						return false;
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		gl.tx_k(new Object(){},gl.sf("Activate action...%s",this.get_action_name()));		
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(p.get_count_of_headers() 	== gl.E_OK 	&&
					
		    	 p.get_count_of_switches() 	== gl.E_OK  &&
		
		    	 p.get_count_of_params() 	== gl.E_OK 
				)
		 {
	
		 	// Check of the switch.
		 
			if(p.isa_set_option())
			{ 
				return isa_set_impl();
				
			}		else
			{
				return false;
			}
		 }
		else
		{	
				return false;
		}
	 
	 		
	 
	} // try.
	catch(Exception e)
	{
				return false;
	}
	 
	} 
	
	

	public static DirAction get_instance()
	{
		DirAction lafa = new DirAction(); 
		
		return new DirAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}

