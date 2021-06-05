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
import ap.orion.response.Response;
import ap.utils.DU;
import ap.utils.Su;

public class CloseApplicationAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"close","close()","exit","exit()","выход","закрыть"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/i","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public CloseApplicationAction() {
		
	}

	public CloseApplicationAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public CloseApplicationAction(String text) {
		super(text);
		
	}

	public CloseApplicationAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
		this.setProcess(process);
		
		welcome();
		
		// Get a process.
		
		ap.orion.cmd.CmdProcess p = this.getProcess();
		
		
		if(
				p.get_count_of_headers() == gl.E_OK 	&&
				
				p.get_count_of_switches() == gl.E_EMPTY &&
				
				p.get_count_of_params() == gl.E_EMPTY
				
		  )
		
		{
			// Null off.
			this.setResponse(Arrays.asList(new String[] {gl.sf("%s","Application close")}));
			
			try {
				
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				
				this.setResponse(Arrays.asList(new String[] {gl.sf("%s",e.toString())}));
				
				return false;
			}
			
				System.exit(gl.E_EMPTY);
			
				return true;
			
		} 
		
		
				return false;
		
		 
	} 
	
	

	public static CloseApplicationAction get_instance()
	{
		CloseApplicationAction lafa = new CloseApplicationAction(); 
		
		return new CloseApplicationAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	


	public static String get_look_and_feel_info_() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		try { 

			int [] index = {1}; 
			 
			get_look_and_feel_list().forEach(l->{ 
				 
				sb.append(gl.sf("%2d.%s",index[0],l)); 
				 
				sb.append(System.lineSeparator()); 
				 
				index[0]++; 
				 
			}); 
			 
			return sb.toString(); 
		 
		} catch (Exception e) { 

			return null; 
		 
		} 
	} 

	/*
	public String get_look_and_feel_info() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		try { 

			int [] index = {1}; 
			 
			this.getResponse().forEach(l->{ 
				 
				sb.append(gl.sf("%2d.%s",index[0],l)); 
				 
				sb.append(System.lineSeparator()); 
				 
				index[0]++; 
				 
			}); 
			 
			return sb.toString(); 
		 
		} catch (Exception e) { 

			return null; 
		 
		} 
	} 
	*/
	
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

	
	public static void main(String[] args) {
		

	}

}
