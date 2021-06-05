package ap.orion.action;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.live.Live;
import ap.orion.lsnr.BrewActionListener;
import ap.utils.DU;
import ap.utils.Fu;
import ap.utils.Su;


public class MaAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"ma","ma()","среднее"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public MaAction() {
		
	}

	public MaAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public MaAction(String text) {
		super(text);
		
	}

	public MaAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
		
	public boolean isa_main_impl()
	{
			CmdProcess p = this.getProcess();

		try
		{
				List<String> 	params 			= p.get_params();
				
				int 			m_params_count 	= params.size();
				
				if(!this.check_selected())
					return false;
				
				if( !this.check_flow("Проверка наличия ключей и параметров.",
						(p.get_count_of_switches() == gl.E_OK)  && (p.get_count_of_params() != gl.E_EMPTY)
					))
				{	
					return false;
				}
				
				List<String> m_items = new ArrayList<String>();
				
				if(p.isa_out_option() )
				{
					
					// Загрузка типов МА.
					
					if(m_params_count == gl.E_OK)
					{
						// Все в одном параметре.
						
						String m_param = params.get(gl.E_EMPTY);
						
						m_items.addAll(Su.clear_param_one(m_param));
					
						
					}
					else if(p.get_count_of_params() > gl.E_OK)
					{
						
						m_items.addAll(Su.clear_params_many(params));
						
						if (!this.check_list_no_empty(gl.sf("Обработка списка параметров...[%d]",
								m_items.size()),m_items))
						{
							return false;
						}
					}
				}
				else
					return this.check_flow("Отсутствует необходимый параметр",false);
				
						 Su.show("Список параметров",m_items);
						 
						 
						 CollectionImpl.selected.forEach(a->{
								
								gl.tx(  new Object(){},	
										a.set_data(m_items,BrewActionListener.get_instance("ma")),
										gl.sf("Експорт коллекции в...[%s]",
										a.getName()
										));		
								
										a.repaint();
								
							});
						 
						 return true;
		}
		
		catch(Exception e)
		{
						e.printStackTrace();
						
						return gl.tx(new Object() {},false,gl.sf("[%s]",e.toString()));
			
		}
			
		
	
	}
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
				this.setProcess(process);

				welcome();		
				
				this.setResponse(null);
	
		if(
				this.getProcess().get_count_of_headers() == gl.E_OK
		  )
		
		{	
				return isa_main_impl();
		} 
		
		
				return this.check_flow("Welcome",false);
		
		 
	} 
	
	

	public static MaAction get_instance()
	{
		MaAction lafa = new MaAction(); 
		
		return new MaAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}

