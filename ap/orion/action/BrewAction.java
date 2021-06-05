package ap.orion.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.live.Live;
import ap.orion.lsnr.BrewActionListener;
import ap.utils.Nu;



public class BrewAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"brew","brew()","set","set()","тип"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public BrewAction() {
		
	}

	public BrewAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public BrewAction(String text) {
		super(text);
		
	}

	public BrewAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
			this.setProcess(process);
		
			welcome();
		
			CmdProcess p = this.getProcess();
						
			this.setResponse(null);
		
		try 
		
		{
		
		if( ! (
				p.get_count_of_headers() == gl.E_OK 	&&
				
				Nu.in(p.get_count_of_switches(),0,1) 	&&
				
				Nu.in(p.get_count_of_params(),0,1)
				)
		  )
		
		{		
			
						this.setResponse(Arrays.asList(new String[] {gl.sf("Недопустимый набор опций в командной строке...%s",p.getText())}));
									
						return false;

		}
			
					// Без  ключей и параметров
					// отображаем текущий тип инкарнации.
					
					if(	p.get_count_of_switches() 	== gl.E_EMPTY &&
						p.get_count_of_params() 	== gl.E_EMPTY)
					{

							List<String> m_response = Arrays.asList(new String[] {gl.sf("Текущий тип инкарнации...%s",Live.brew_type)}); 
									
							this.setResponse(m_response);
							
							return true;
									
					}
					
					// Отображаем список возможных аргументов. 
					
					if(p.isa_help_option())
					{
							List<String> m_response = new ArrayList<String>();
									
							m_response.add("Доступные типы инкарнаций");
									
							m_response.add("-------------------------");
									
							m_response.addAll(Live.types);
									
							m_response.remove(2);
									
							this.setResponse(m_response);
									
							return true;
							
					}  else if(p.isa_set_option())
						
					{ 
						
							if(p.get_count_of_params()== gl.E_EMPTY)
							{
							
									this.setResponse(Arrays.asList(new String[] {gl.sf("Отсутствует обязательный параметр в выражении...%s",p.getText())}));
							
									return false;
							}
						
							String	m_param = p.get_of(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
						
									m_param = m_param.trim();
								
						
							if (!Live.types.contains(m_param))
							{
							
									this.setResponse(Arrays.asList(new String[] {gl.sf("Неизвестный тип инкарнации...%s",m_param)}));
								
									return false;
							}
						
									Live.brew_type = m_param;
							
									this.setResponse(Arrays.asList(new String[] {gl.sf("Установлен тип инкарнации...%s",Live.brew_type)}));
							
									return true;
							
					}else if(p.isa_out_option())
					{ 
						
						if(!this.check_selected())
							return false;
						
						
						CollectionImpl.selected.forEach(a->{
			
							gl.tx(  new Object(){},	
									a.set_data(Live.types,BrewActionListener.get_instance("brew")),
									gl.sf("Експорт коллекции в...%s",
									a.getName()
									));		
							
									a.repaint();
							
						});
						
									return true;
					
					
					}
					else
					{
					
									this.setResponse(Arrays.asList(new String[] {gl.sf("Недопустимая опция в командной строке...%s",p.getText())}));
							
									return false;
					}
 
					
		} 
		catch(Exception e)
		{
									return false;
		}
		 
	} 
	
	

	public static BrewAction get_instance()
	{
		BrewAction lafa = new BrewAction(); 
		
		return new BrewAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
