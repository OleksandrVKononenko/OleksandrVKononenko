package ap.orion.action;


import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;


public class ShowAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"show","show()","ls","ls()","показать"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {""}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public ShowAction() {
		
	}

	public ShowAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public ShowAction(String text) {
		super(text);
		
	}

	public ShowAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	
	public boolean isa_selected_impl()
	{
		
		try
		{		
			CollectionImpl.update_selected();
			
			 if(CollectionImpl.update_selected()== gl.E_EMPTY)
				 return  gl.tx(new Object(){},false,gl.sf("Нет селектированных объектов."));
			 
			 CollectionImpl.selected.forEach(a->
			 {
				 gl.tx_k(new Object(){},gl.sf("%s",a.toString()));
				 
			 });
			 
			 	return true;
					
		}
		catch(Exception e)
		{
				return false;
		}
	}


	public boolean isa_all_impl()
	{
				
		try
		{		
			 if(!CollectionImpl.refresh())
				 return  gl.tx(new Object(){},false,gl.sf("Ошибка синхронизации объектов."));
			 
			 CollectionImpl.items.forEach(a->
			 {
				 gl.tx_k(new Object(){},gl.sf("%s",a.toString()));
				 
			 });
			 
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
		
		this.welcome();
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(
				 p.get_count_of_headers() 	== gl.E_OK 
				)
		 {
	
			 if (p.isa_all_option() && !p.isa_selection_option())
				 return isa_all_impl();
			 else if (p.isa_selection_option() && !p.isa_all_option())
				 return isa_selected_impl();
			 else
				 return false;
					 
			 
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
	
	

	public static ShowAction get_instance()
	{
		ShowAction lafa = new ShowAction(); 
		
		return new ShowAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
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

