 
 
 
 
 
 
 
package ap.action; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 
import javax.swing.UIManager.LookAndFeelInfo; 
import javax.swing.UnsupportedLookAndFeelException; 

import ap.global.gl; 
import ap.prop.SColor; 
import ap.prop.SDimension; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.CU; 
import ap.utils.DU; 
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class LookAndFillAction extends BaseAction { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"look","look()","lf","lf()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/i","/s","-s"}); 


	public LookAndFillAction() { 
		 
	} 

	public LookAndFillAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public LookAndFillAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public LookAndFillAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static LookAndFillAction getInstance() 
	{ 
		LookAndFillAction cmp = new LookAndFillAction(); 
		 
		return new LookAndFillAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean checkParams(PanelXml owner) { 
		 
		return true; 
	} 
	 
	public boolean router(PanelXml owner) 
	{ 
		String msg = "Action flow"; 
		 
		Cmd cmd  = owner.getFire_cmd(); 
				 
		int m_args_count = CmdParams.getArgsCount(cmd) - 1; 
		 
		int m_params_count = CmdParams.getParamsCount(cmd); 
		 
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
			else if (m_params_count == gl.ONE  && CmdParams.isaParam(cmd,"/i")) 
			{ 
				//Show info about available modes. 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...[%s]",msg,"Available modes",get_look_and_feel_info(),gl.S_OK)); 
			}					 
			else if (m_params_count == gl.ONE && m_params_count == gl.ONE  && CmdParams.isaParam(cmd,"/s")) 
			{ 
				// Extract mode index from list. 
				 
				int m_look_index = DU.to_int(CmdParams.get_parameter(cmd,"a001")); 
				 
				if(m_look_index < gl.E_OK) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...[%d]...%s",msg,"Waiting a value > 0",m_look_index,gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				//Set L&F mode. 
				String m_new_mode = get_look_and_feel_list().get(m_look_index-1); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...[%s]...%s",msg,"Try to set mode ",m_new_mode,gl.S_OK)); 
										 
				UIManager.setLookAndFeel(m_new_mode); 
				 
				SwingUtilities.updateComponentTreeUI(Application.getFio().getFrame()); 
				 
				// Re -  read. 
				m_look = UIManager.getLookAndFeel().getName(); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...[%s]",msg,"Update L&F ",m_look,gl.S_OK)); 
								 
			} 
		 
			 
				return true; 
			 
		} 
		catch(Exception e) 
		{ 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage().toString(),gl.S_ERROR)); 
						 
				return false; 
				 
		} 
		 
	} 

	public String get_look_and_feel_info() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		try { 

			int [] index = {0}; 
			 
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
	 
	public List<String> get_look_and_feel_list() 
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
	public boolean actionPerformed(PanelXml owner) { 
	 
		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		Cmd cmd = owner.getFire_cmd(); 
		 
		List<String> isa_bad = new ArrayList<String>(); 
		 
		if (CmdParams.isa_bad_params(cmd,VALID_PARAMS,isa_bad)) 
		{ 
			gl.tracex(new Object() {},gl.sf("%s...not valid params...[%s]...%s",msg,Su.get_str_from_list(isa_bad),gl.S_ERROR)); 
			 
			return false; 
		} 
			return router(owner); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 

