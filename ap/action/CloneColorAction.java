 
 
 
 
 
 
 
package ap.action; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.prop.SColor; 
import ap.swing.PanelXml; 
import ap.utils.DU; 
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class CloneColorAction extends BaseAction { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"clonecolor","clonecolor()","ccolor","ccolor()","cc","cc()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/b","/f","-b","-f"}); 


	public CloneColorAction() { 
		 
	} 

	public CloneColorAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public CloneColorAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public CloneColorAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static CloneColorAction getInstance() 
	{ 
		CloneColorAction cmp = new CloneColorAction(); 
		 
		return new CloneColorAction(cmp.getClass().getSimpleName()); 
		 
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
				(m_args_count > gl.FOUR) && 
				(m_params_count != gl.E_EMPTY 	|| m_params_count != gl.E_OK) 
		   ) 
		{ 
			 
				gl.tracex(new Object(){},gl.sf("%s...Bad params count check...%s",msg,gl.S_ERROR)); 
			 
				return false; 
		} 
			 
		 
		try 
		{ 
				boolean m_background = CmdParams.isaParam(cmd,"/b"); 
				 
				boolean m_foreground = CmdParams.isaParam(cmd,"/f"); 
				 
				String m_color = ""; 
			 
			 
			if(m_params_count == gl.ZERO  &&  (m_background == m_foreground)) 
			{ 
				// Show section. 
				 
				m_color = (m_background) ? SColor.toString(owner.getBackground()) : SColor.toString(owner.getForeground()); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"show current state ",m_color,gl.S_OK)); 
				 
				return  true; 
				 
			} else if(m_params_count == gl.ONE ) 
			{ 
				//Set section. 
				 
				 
						if(m_background) 
						{ 
							 
							// For selected items. 
							owner.getSelected().values().forEach(v->{ 
								 
								v.setBackground(owner.getBackground()); 
								 
								v.repaint(); 
								 
							}); 
							 
							 
						} 
						else if(m_foreground) 
						{ 
							 
							// For selected items. 
							owner.getSelected().values().forEach(v->{ 
								 
								v.setForeground(owner.getForeground()); 
																 
								v.repaint(); 
								 
							}); 
							 
						} 
								owner.repaint(); 
						 
								gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"set color ",SColor.toString((m_background) ? owner.getBackground():owner.getForeground()),gl.S_OK)); 
									 
								return  true; 
			} 
			 
								return false; 
		} 
		catch(Exception e) 
		{ 
						gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage().toString(),gl.S_ERROR)); 
						 
						return false; 
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

