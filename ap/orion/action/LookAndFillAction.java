package ap.orion.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.live.Live;
import ap.orion.lsnr.BrewActionListener;
import ap.orion.response.Response;
import ap.utils.DU;
import ap.utils.Su;

public class LookAndFillAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"look","look()","lf","lf()","стиль","тема"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/i","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public LookAndFillAction() {
		
	}

	public LookAndFillAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public LookAndFillAction(String text) {
		super(text);
		
	}

	public LookAndFillAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public static String get_current_lf()
	{
		
		return UIManager.getLookAndFeel().getName();
	}
	
	public boolean isa_usage_info_impl()
	{
		
		List<String> 	m_list = get_look_and_feel_info();
			
		String			m_curr_look = gl.sf("Current mode is...[%s]",get_current_lf());
				
						m_list.add(m_curr_look);
		
						this.setResponse(m_list);
		
						gl.sfn("%s%s%s",
								Response.get_response(this.getResponse()),
								System.lineSeparator(),
								m_curr_look);
		
						return true;
	
	}
	
	public boolean isa_out_option_impl()
	{		
			if(!this.check_selected())
			{
				return false;
			}
		
			
			CollectionImpl.selected.forEach(a->{

				gl.tx(  new Object(){},	
						a.set_data(get_look_and_feel_list(),BrewActionListener.get_instance("lf")),
						gl.sf("Експорт коллекции в...%s",
						a.getName()
						));		
				
						a.repaint();
				
			});
			
						return true;
		
		
	
	}
	
	public  boolean isa_set_option_impl()
	{
		
		List<String> 	m_params = this.getProcess().get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_param = m_params.get(gl.E_EMPTY);
		
		
		// Check type of parameter.
		
		if (Su.isAlpha(m_param))
		{
			
			int m_index = get_look_and_fill_index(m_param);
			
			if(m_index == gl.E_ERROR)
			{
				
				this.setResponse(Arrays.asList(new String[] {gl.sf("Bad parameter value...%s",m_param)}));
			
				return false;
				
			}
				String m_new_mode = get_look_and_feel_list().get(m_index); 
				 
				try {
					
					UIManager.setLookAndFeel(m_new_mode);
				
					SwingUtilities.updateComponentTreeUI(Application.getFio().get_frame()); 
					
					this.setResponse(Arrays.asList(new String[] {gl.sf("Current mode...[%s]",
							get_current_lf()
							)}));
					
					
					return true;
					
				} catch (Exception e) {
					
					gl.tracex(new Object() {},gl.sf("Exception...%s",e.toString()));
					
					return false;
				} 
				
		}
		else if (Su.isNumber(m_param))
		{
			
			int m_look_index = DU.to_int(m_param); 
			 
			if(m_look_index < gl.E_OK || m_look_index > get_look_and_feel_list().size()) 
			{ 
				
				this.setResponse(Arrays.asList(new String[] {gl.sf("Bad mode index value...%2d",m_look_index)}));
				
				return false; 
			} 
			 
			//Set L&F mode. 
			String m_new_mode = get_look_and_feel_list().get(m_look_index-1); 
									 
			try {
				
				UIManager.setLookAndFeel(m_new_mode);
			
				SwingUtilities.updateComponentTreeUI(Application.getFio().get_frame()); 
				
				this.setResponse(Arrays.asList(new String[] {gl.sf("Current mode...%s",UIManager.getLookAndFeel().getName())}));
				
				return true;
				
			} catch (Exception e) {
				
				return false;
			} 
			 
		}
				return false;
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		welcome();
		
		// Get a process.
		
		CmdProcess p = this.getProcess();
		
		this.setResponse(null);
		
		
		if(
				p.get_count_of_headers() != gl.E_OK
		  )
		{
			this.setResponse(Arrays.asList(new String[] {gl.sf("Invalid count of headers.")}));
			
			return false;
		}
		
		
		if(p.isa_no_params() && p.isa_no_switches())
		{
			return isa_usage_info_impl();
			
		} else if(p.isa_set_option() && p.get_count_of_params() == gl.E_OK) 	
		{
		   return isa_set_option_impl();
		   
		} else if(p.isa_out_option() && p.get_count_of_params() == gl.E_EMPTY)
		{
			return isa_out_option_impl();
		}
		else
		{
			
			this.setResponse(Arrays.asList(new String[] {gl.sf("Invalid  params.")}));
			
			return false;
			
		}
		
		 
	} 
	
	
	
	public static int get_look_and_fill_index(String value)
	{
		int [] m_result = { gl.E_ERROR };
		
		int [] m_index = {gl.E_EMPTY};
		
		get_look_and_feel_list().forEach(a->{
			
			String 	m_find = Su.AfterAtPeriod(a);
			
					m_find = m_find.replace("LookAndFeel","");
			
			//gl.sfn("%s %s %s",m_find,m_find.equalsIgnoreCase(value),value);
			
			if(m_find.equalsIgnoreCase(value))
				m_result [0] = m_index[0];
			
				m_index[0]++;
			
		});
		
			//gl.sfn("Result...%d",m_result [0]);
			
			return m_result[0];
	}

	public static LookAndFillAction get_instance()
	{
		LookAndFillAction lafa = new LookAndFillAction(); 
		
		return new LookAndFillAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	


	
	
	public static List<String> get_look_and_feel_list() 
	{ 
		 
		List<String> m_list = new ArrayList<String>(); 
		 
		try { 
			 
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 

				m_list.add(gl.sf("%s",info.getClassName()));
			} 
			 
				return m_list; 
		 
		} catch (Exception e) { 

				return null; 
		 
		} 

			 
	} 

	public static List<String> get_look_and_feel_info() 
	{ 
		 
		List<String> m_list = new ArrayList<String>(); 
		 
		try { 

			 int index = gl.E_OK;
			 
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 

				m_list.add(gl.sf("%d.%s",index,info.getClassName()));
				
				index++;
			} 
			 
				return m_list; 
		 
		} catch (Exception e) { 

				return null; 
		 
		} 
	} 
	
	
	public static void main(String[] args) {
		

	}

}
