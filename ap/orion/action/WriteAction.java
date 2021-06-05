package ap.orion.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import ap.gen.IDGen;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.utils.Fu;
import ap.utils.Su;


public class WriteAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"write","write()","записать"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public WriteAction() {
		
	}

	public WriteAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public WriteAction(String text) {
		super(text);
		
	}

	public WriteAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_main_impl()
	{
		CmdProcess p = this.getProcess();
		
		if (
				  p.get_count_of_headers() 	== gl.E_OK
		   )
		{
					String m_home_prev = Fu.get_file_name(Application.getCio().getHome());
					
					boolean bl_return_home = false;
			
			    if(p.get_count_of_params() >= gl.E_OK && ! p.isa_dlg_option())
			    {
			    	// Подмена файла.
			    	
			    	String m_home = Su.clear_params(p.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"))).
			    	
			    	stream().collect(Collectors.joining(" "));
			    	
			    	if(m_home != null)
			    	{
						bl_return_home = true;
			    		
			    		gl.tx_k(new Object() {},gl.sf("Файл для записи...%s",Application.getCio().update(m_home)));
						
			    	}
			    	
			    } else if(p.get_count_of_params() == gl.E_EMPTY && p.isa_dlg_option())
			    {
			    	String m_save_file = Fu.file_save_dlg(m_home_prev);
			    	
			    	if(m_save_file == null)
			    	{
			    	   m_save_file = m_home_prev;
			    	   
			    	   gl.tx_k(new Object() {},gl.sf("Реинициализация файла проекта...[%s]",
			    			   m_save_file));
					    		
			    	}
			    	
			  		gl.tx_k(new Object() {},gl.sf("Файл для записи с диалога...[%s][%s]",
		    				Application.getCio().update_abs(m_save_file),
		    				Application.getCio().getHome()
		    				));
					
		    		if(!this.check_flow(gl.sf("Файл для сохранения...[%s]",m_save_file),
		    				(m_save_file != null && m_save_file.trim().length() != gl.E_EMPTY)))
		    		{
		    			return false;
		    		}
		    		
			    }
			    //else
			    //{
			    //	return this.check_flow("Level::10", false);
			    //}
				
			    // Два типа записи.
			    
			    // 1. Полная,- все объекты.
			    
			    // 2. Только селектированные.
			    
			    // Refresh selected.
			    
			    CollectionImpl.update_selected();
			    
			    boolean bl_selected = ( CollectionImpl.selected.size() > gl.E_EMPTY ); 
			    
			    if(bl_selected)
			    {
			    	return gl.tx(new Object() {},(isa_write_selected_impl() && update_home(bl_return_home,m_home_prev)),gl.sf("Запись селектированных объектов"));
				} else
					return gl.tx(new Object() {},(isa_write_all_impl() && update_home(bl_return_home,m_home_prev)),gl.sf("Запись объектов завершена"));
			
		}
		
				return this.check_flow("Level::11",false);
	}
	
	public boolean update_home(boolean action,String home_name)
	{
			try
			{
				if(action)
				   Application.getCio().update(home_name);
				
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
	}
	
	public boolean isa_write_selected_impl()
	{
			CollectionImpl.update_selected();
		
			int m_selected      = CollectionImpl.selected.size();
				
			if(!gl.tx(new Object() {},(m_selected > gl.E_EMPTY ),gl.sf("Объектов для записи...%d",m_selected)))
				return false;
			
			if(!gl.tx(new Object() {},Application.getRio().write(false),gl.sf("Запись в файл.")))
				return false;
				
			 	return true;
	}
	
	public boolean isa_write_all_impl()
	{
		
			CollectionImpl.update_selected();
			
			int m_count_before 	= CollectionImpl.get_all_components().size();
			
			if(!gl.tx(new Object() {},(m_count_before > gl.E_EMPTY ),gl.sf("Объектов для записи...%d",m_count_before-1)))
				return false;
			
			if(!gl.tx(new Object() {},Application.getRio().write(true),gl.sf("Запись в файл.")))
				return false;
			
			CmdProcess pr 		= CmdProcess.get_instance(".del /. --a");

			if(!gl.tx(new Object() {},pr.parse(),gl.sf("Парсинг для подготовки клининга.")))
				return false;
		
		     if(!gl.tx(new Object() {},CleanAction.get_instance().action_performed(pr),gl.sf("Клининг.")))
				return false;
		
		     if(!gl.tx(new Object() {},Application.getRio().read(),gl.sf("Чтение.")))
				return false;
		
		     if(!gl.tx(new Object() {},CollectionImpl.refresh(),gl.sf("Проверка синхронизации.")))
					return false;
			 
		     int m_count_after = CollectionImpl.items.size();
		     
		     return gl.tx(new Object() {},m_count_before==m_count_after,gl.sf("Завершение команды...%s...Before...%d...After...%d",
		    		 this.get_action_name(),
		    		 m_count_before,
		    		 m_count_after
		    		 ));
		    
	}
	
	
	public boolean isa_pure_impl_(boolean mode)
	{
		
			CollectionImpl.update_selected();
			
			int m_count_before 	= CollectionImpl.get_components().size();
			
			int m_selected      = CollectionImpl.selected.size();
			
			if(gl.tx(new Object() {},(m_count_before == gl.E_OK && m_selected == gl.E_EMPTY ),gl.sf("Объектов для записи...%d",m_count_before-1)))
				return false;
			
			if(!gl.tx(new Object() {},Application.getRio().write(mode),gl.sf("Запись в файл.")))
				return false;
			
			CmdProcess pr 		= CmdProcess.get_instance(".clean --a");

			if(!gl.tx(new Object() {},pr.parse(),gl.sf("Парсинг для подготовки клининга.")))
				return false;
		
		     if(!gl.tx(new Object() {},CleanAction.get_instance().action_performed(pr),gl.sf("Клининг.")))
				return false;
		
		     if(!gl.tx(new Object() {},Application.getRio().read(),gl.sf("Чтение.")))
				return false;
		
		     
		     int m_count_after = CollectionImpl.items.size();
		     
		     return gl.tx(new Object() {},m_count_before==m_count_after,gl.sf("Завершение команды...%s",this.get_action_name()));
		    
	}
	
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
			welcome();
		
			this.setProcess(process);
		
		try
		{
			return isa_main_impl();
		}
		catch(Exception e)
		{

			return gl.tx(new Object() {},false,gl.sf("%s",e.toString()));
		
		}
	} 
	
	

	public static WriteAction get_instance()
	{
		WriteAction lafa = new WriteAction(); 
		
		return new WriteAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
