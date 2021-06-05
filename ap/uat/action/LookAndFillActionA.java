 
 
 
package ap.uat.action; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 
import javax.swing.UIManager.LookAndFeelInfo; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.utils.DU; 
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class LookAndFillActionA extends BaseActionA { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"look","look()","lf","lf()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/i","/s","-s"}); 


	public LookAndFillActionA() { 
		 
	} 

	public LookAndFillActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public LookAndFillActionA(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public LookAndFillActionA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static LookAndFillActionA getInstance() 
	{ 
		LookAndFillActionA cmp = new LookAndFillActionA(); 
		 
		return new LookAndFillActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	 
	public boolean router(AtOm owner) 
	{ 
		String msg = "Action flow"; 
		 
		CmdA cmd  = owner.getFire_cmd(); 
				 
		int m_args_count = CmdParamsA.getArgsCount(cmd) - 1; 
		 
		int m_params_count = CmdParamsA.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		if( 
				(m_args_count > gl.ONE) && 
				(m_params_count != gl.E_EMPTY 	|| m_params_count != gl.E_OK) 
		   ) 
		{ 
			 
				gl.tracex(new Object(){},gl.sf("%s...Bad params count check...%s",msg,gl.S_ERROR)); 
			 
				return false; 
		} 
		 
		 
		try 
		{ 
				 
				String m_look = UIManager.getLookAndFeel().getName(); 
			 
			 
			if(m_params_count == gl.ZERO ) 
			{ 
				// Show info about current L&F mode. 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...[%s]",msg,"Current L&F",m_look,gl.S_OK)); 
				 
			} 	 
			else if (m_params_count == gl.ONE  && CmdParamsA.isaParam(cmd,"/i")) 
			{ 
				//Show info about available modes. 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...\n%s...[%s]",msg,"Available modes",get_look_and_feel_info(),gl.S_OK)); 
			}					 
			else if (m_params_count == gl.ONE && m_params_count == gl.ONE  && CmdParamsA.isaParam(cmd,"/s")) 
			{ 
				// Extract mode index from list. 
				 
				int m_look_index = DU.to_int(CmdParamsA.get_parameter(cmd,"a001")); 
				 
				if(m_look_index < gl.E_OK) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...[%d]...%s",msg,"Waiting a value > 0",m_look_index,gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				//Set L&F mode. 
				String m_new_mode = get_look_and_feel_list().get(m_look_index-1); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...[%s]...%s",msg,"Try to set mode ",m_new_mode,gl.S_OK)); 
										 
				UIManager.setLookAndFeel(m_new_mode); 
				 
				SwingUtilities.updateComponentTreeUI(ApplicationA.getFio().get_frame()); 
				 
				// Re -  read. 
				m_look = UIManager.getLookAndFeel().getName(); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...[%s]",msg,"Update L&F ",m_look,gl.S_OK)); 
								 
			} 
		 
			 
				return true; 
			 
		} 
		catch(Exception e) 
		{ 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage().toString().substring(1,32),gl.S_ERROR)); 
						 
				return false; 
				 
		} 
		 
	} 

	public static String get_look_and_feel_info() 
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

	 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 
	 
		String 			msg = gl.sf("Action...%s", this.getValue(NAME)); 

		CmdA 			cmd = owner.getFire_cmd(); 
		 
		List<String> 	isa_bad = new ArrayList<String>(); 
		
		 
		if (CmdParamsA.isa_bad_params(cmd,VALID_PARAMS,isa_bad)) 
		{ 
			gl.tracex(new Object() {},gl.sf("%s...not valid params...[%s]...%s",msg,Su.get_str_from_list(isa_bad),gl.S_ERROR)); 
			 
			return false; 
		} 
			return router(owner); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 

