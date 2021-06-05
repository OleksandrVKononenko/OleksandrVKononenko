package ap.orion.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

import ap.gen.IDGen;
import ap.global.gl;
import ap.mercury.components.Cmd;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.utils.Fu;
import ap.utils.Nu;


public class RunAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"run","run()","выполнить"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public RunAction() {
		
	}

	public RunAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public RunAction(String text) {
		super(text);
		
	}

	public RunAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_main_impl()
	{
		CmdProcess p = this.getProcess();
		
		 if (!gl.tx(new Object(){},
				 (p.get_count_of_headers() != gl.E_EMPTY),
				 gl.sf("Отсутствует команда.")
				)
				 ||
			  !gl.tx(new Object(){},
					 Nu.iif(p.get_count_of_params(),p.get_count_of_switches(),gl.E_OK),
					 gl.sf("Отсутствуют аргументы и параметры команды.")
				)
			  )
			{		
				return false;
			}

		 if (p.isa_set_option())
		 {
			// Первый параметр,- название файла.
				
			String m_script 		= p.get_param_by_index(gl.E_EMPTY).trim();

			 if (!gl.tx(new Object(){},
					 Fu.isaFile(m_script),
					 gl.sf("Проверка доступа к файлу...%s",m_script)
					))
			 {
				 return false;
			 }	  
			
			 List<String> actions = Fu.get_list_from_file(m_script);
			 
			 if (!gl.tx(new Object(){},
					 actions.size()>gl.E_OK,
					 gl.sf("Проверка листинга команд...%d",actions.size())
					))
			 {
				 return false;
			 }	 
			 
			 List<Boolean> response = new ArrayList<Boolean>();
			 
			 actions.forEach(a->{
				 
				 boolean result = (boolean)Cmd.exec(a).get("result");
				 
				 response.add(result);
				 
				 gl.tx(new Object(){},result,gl.sf("%s %s",a,result));
				 
			 });
			 
			 	return !response.contains(false);
		 }
		 
		 		return false;
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
	
	

	public static RunAction get_instance()
	{
		RunAction lafa = new RunAction(); 
		
		return new RunAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}

