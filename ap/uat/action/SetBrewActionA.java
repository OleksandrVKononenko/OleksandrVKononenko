 
package ap.uat.action; 


import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class SetBrewActionA extends BaseActionA { 

	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"br","br()","brew","brew()"}); 
		 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {""}); 

		 
	public SetBrewActionA() { 
		 
	} 

	public SetBrewActionA(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetBrewActionA(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public SetBrewActionA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static SetBrewActionA getInstance() 
	{ 
		SetBrewActionA cmp = new SetBrewActionA(); 
		 
		return new SetBrewActionA(cmp.getClass().getSimpleName()); 
	} 
	 
	public boolean router(CmdA cmd) 
	{ 
		String msg = gl.sf("Action %s",this.getActionName()); 
		 
		int m_args_count = CmdParamsA.getArgsCount(cmd); 
		 
		int m_params_count = CmdParamsA.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		try 
		{ 
			 
			if(m_args_count == gl.ONE) 
			{ 
				// NO args presents. 
				// Show current brew object. 
				gl.tracex(new Object(){},gl.sf("%s...Current brew object class...%s...%s",msg,ApplicationA.cio.getActive_brew_object(),gl.S_OK)); 
				 
				 
			} 
			else if(m_args_count == gl.TWO) 
			{ 
				// One param presents. 
				// Set new class in object brew. 
				 
				String arg = CmdParamsA.get_parameter(cmd,"a001"); 
				 
				if(!ApplicationA.cio.getActive_brew_object().equalsIgnoreCase(arg)) 
				{ 
					ApplicationA.cio.setActive_brew_object(arg); 
				 
					gl.tracex(new Object(){},gl.sf("%s...Set current brew object class...%s...%s",msg,ApplicationA.cio.getActive_brew_object(),gl.S_OK)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...Try set the same brew object class...%s...%s",msg,ApplicationA.cio.getActive_brew_object(),gl.S_OK)); 
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
	public boolean actionPerformed(AtOm owner) { 
		 
		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		CmdA cmd = owner.getFire_cmd(); 
		 
		if(CmdParamsA.get_bad_params_list_count_b(cmd,cmd.get_all_valid_switch())) 
		{ 
			gl.tracex(new Object() {},gl.sf("%s...bad params...%s...%s", msg,Su.get_str_from_list(CmdParamsA.get_bad_params_list(cmd,cmd.get_all_valid_switch())), gl.S_ERROR)); 
			 
			return false; 
		} 

			return router(cmd); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
