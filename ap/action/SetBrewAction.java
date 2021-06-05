 
 
 
 
 
 
 
 
 
package ap.action; 

import java.awt.Dimension; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.prop.SDimension; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.DU; 
import ap.utils.Nu; 

public class SetBrewAction extends BaseAction { 

	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"brew","brew()"}); 
		 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/a"}); 

		 
	public SetBrewAction() { 
		 
	} 

	public SetBrewAction(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetBrewAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public SetBrewAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static SetBrewAction getInstance() 
	{ 
		SetBrewAction cmp = new SetBrewAction(); 
		 
		return new SetBrewAction(cmp.getClass().getSimpleName()); 
	} 
	 
	public boolean router(Cmd cmd) 
	{ 
		String msg = gl.sf("Action %s",this.getActionName()); 
		 
		int m_args_count = CmdParams.getArgsCount(cmd); 
		 
		int m_params_count = CmdParams.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		try 
		{ 
			boolean m_additional_mode = CmdParams.isaParam(cmd,"/a"); 
			 
			if(m_additional_mode) 
			{ 
				gl.tracex(new Object(){},gl.sf("%s...Activate ADDITIONAL mode...%s",msg,m_additional_mode)); 
			} 
			 
			if(m_args_count == gl.ONE) 
			{ 
				// NO args presents. 
				// Show current brew object. 
				gl.tracex(new Object(){},gl.sf("%s...Current brew object class...%s...%s",msg,Application.cio.getActive_brew_object(),gl.S_OK)); 
				 
				 
			} 
			else if(m_args_count == gl.TWO) 
			{ 
				// One param presents. 
				// Set new class in object brew. 
				 
				String arg = CmdParams.get_parameter(cmd,"a001"); 
				 
				 
				if(!Application.cio.getActive_brew_object().equalsIgnoreCase(arg)) 
				{ 
					Application.cio.setActive_brew_object(arg); 
				 
					gl.tracex(new Object(){},gl.sf("%s...Set current brew object class...%s...%s",msg,Application.cio.getActive_brew_object(),gl.S_OK)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...Try set the same brew object class...%s...%s",msg,Application.cio.getActive_brew_object(),gl.S_OK)); 
				} 
			} 
			 
					return true; 
			 
		} 
		catch(Exception e) 
		{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage().toString(),gl.S_ERROR)); 
						 
					return false; 
		} 
		 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 
		 
		 
		String [] m_list_bad_params = {""}; 
		 
		Cmd cmd = owner.getFire_cmd(); 
		 
		if (cmd == null || !cmd.checkParams(VALID_PARAMS,m_list_bad_params)) 
	    { 
			gl.tracex(new Object() {},gl.sf("Action...%s...bad params or cmd null...%s...%s", this.getActionName(),m_list_bad_params[gl.E_EMPTY], gl.S_ERROR)); 
		 
			return false; 
	    } 

			return router(cmd); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
