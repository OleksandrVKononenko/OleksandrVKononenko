package ap.orion.action;


import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.prop.SColor;
import ap.utils.CU;
import ap.utils.Nu;


public class ModeAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"mode","mode()","m","m()","режим"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public ModeAction() {
		
	}

	public ModeAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public ModeAction(String text) {
		super(text);
		
	}

	public ModeAction(String text, List<String> activators, List<String> switches) {
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
		
		
			try
			{
	
				// Читаем параметр.

				List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

				String 			m_param = m_params.get(gl.E_EMPTY);
				
				gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s",m_param));		
				
				return true;
				
			}
			catch(Exception e)
			{
				return false;
			}
	
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		gl.tx_k(new Object(){},gl.sf("Activate action...%s",this.get_action_name()));		
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(p.get_count_of_headers() 	== gl.E_OK 	&&
					
		    	 p.get_count_of_switches() 	== gl.E_OK &&
		
		    	 Nu.in(p.get_count_of_params(),0,1)
		    	 
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
	
	

	public static ModeAction get_instance()
	{
		ModeAction lafa = new ModeAction(); 
		
		return new ModeAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}


