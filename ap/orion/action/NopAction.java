package ap.orion.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
import ap.utils.Nu;
import ap.utils.Su;


public class NopAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"nop","nop()","n","n()","ноп"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public NopAction() {
		
	}

	public NopAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public NopAction(String text) {
		super(text);
		
	}

	public NopAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_set_impl()
	{
		
		CmdProcess proc = this.getProcess();
			
		// Читаем параметр.

		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			[] m_param = {""};
		
						if(m_params.size() != gl.E_EMPTY)
						{
							m_param[0] = m_params.get(gl.E_EMPTY);
						}
						else
						{
							m_param[0] = "5";
						}
		
		int				m_steps = 5; 
				
		int				m_tmp 	= DU.to_int(m_param[0]);
		
						if(m_tmp != gl.E_ERROR)
						{
							m_steps = m_tmp; 
						}		
				
						gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s...циклов...%d",m_param[0],m_steps));		
		
						for(int i=0;i<m_steps;i++)
							gl.tx_k(new Object(){},gl.sf("."));
						
						return true;
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
		
		    	 Nu.in(p.get_count_of_params(), 0,1 )  
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
	
	

	public static NopAction get_instance()
	{
		NopAction lafa = new NopAction(); 
		
		return new NopAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}

