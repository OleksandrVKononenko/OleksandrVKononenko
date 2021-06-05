 
 
 
package ap.uat.action; 


import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA;
import ap.uat.StoreImplObject;
import ap.utils.Biu; 
import ap.utils.DU; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class SetActionA extends BaseActionA { 
	 
	public static final String [] CMDS = new String[] {"set","set()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/a","/s","-a","-s"}; 


	public SetActionA() { 
		 
	} 

	public SetActionA(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 

	public SetActionA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	public static SetActionA getInstance() 
	{ 
		SetActionA cmp = new SetActionA(); 
		 
		return new SetActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		gl.tracex(new Object() {}, gl.sf("%s...%s", msg,gl.S_TRY)); 

		CmdA cmd = owner.getFire_cmd(); 
		 
		if(CmdParamsA.get_bad_params_list_count_b(cmd,cmd.get_all_valid_switch())) 
		{ 
			gl.tracex(new Object() {},gl.sf("%s...bad params...%s...%s", msg,Su.get_str_from_list(CmdParamsA.get_bad_params_list(cmd,cmd.get_all_valid_switch())), gl.S_ERROR)); 
			 
			return false; 
		} 
		 
		
		try
		{
			int m_args_count 	=	CmdParamsA.getArgsCount(cmd) - 1; 
			 
			int m_params_count 	= 	CmdParamsA.getParamsCount(cmd); 
			
			List<AtOm> selected_items =  StoreImplObject.get_selected();
			
			int m_selected_cnt = selected_items.size(); 
						
			
			gl.tracex(new Object(){},gl.sf("%s...Count...args...%d...params...%d...selected items...%d",
					msg,
					m_args_count,
					m_params_count,
					m_selected_cnt
					)); 
		 
			String s_flag_name = ""; 
			 
			String s_mode_value = ""; 
			
			//Check selected.
			if(m_selected_cnt == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object(){},gl.sf("%s...No selected items...%s",msg,gl.S_ERROR)); 
						 
				return false; 
			} 
			
			// Too many args ban. 
			if(m_args_count > gl.TWO) 
			{ 
				gl.tracex(new Object(){},gl.sf("%s...Too many args...[%s]",msg,CmdParamsA.show_params(cmd))); 
						 
				return false; 
			} 
			 
			// Show of the current state. 
			if(m_args_count == gl.E_EMPTY) 
			{ 
				gl.sfn("%s",ap.state.Fl.show_flags(owner.getStio().get_state())); 
				 
				return true; 
			} 
			 
			 
			if(m_args_count == gl.TWO) 
			{ 

				 
				s_flag_name = CmdParamsA.get_parameter(cmd,"a001").toUpperCase(); 
				 
				boolean bl_flag_name_is_number = Su.isNumber(s_flag_name); 
				 
				 
				s_mode_value = CmdParamsA.get_parameter(cmd,"a002"); 
					 
				boolean bl_flag_value_is_number = Su.isNumber(s_mode_value); 
				 
				 
				gl.tracex(new Object(){},gl.sf("%s...arg...%s...%s...arg...%s...%s...%s",
						msg,
						s_flag_name,
						bl_flag_name_is_number,
						s_mode_value,
						bl_flag_value_is_number, gl.S_OK)); 
				 
				 
				 
				if (!bl_flag_name_is_number && bl_flag_value_is_number) 
				{ 
					// Main case. 
					// Find flag in repositary. 
					 
					int m_flag_index = ap.state.Fl.FLAGS.indexOf(s_flag_name); 
					 
					if(m_flag_index == gl.E_ERROR) 
					{ 
					 
						gl.tracex(new Object(){},gl.sf("%s...invalid flag name...[%s]...%s",msg,s_flag_name.trim(),gl.S_ERROR)); 

						return false; 
					} 
					 
						gl.tracex(new Object(){},gl.sf("%s...accept flag name...[%s]...to...%s...%s",msg,s_flag_name.trim(),s_mode_value,gl.S_OK)); 

						int m_mode_value  = DU.to_int(s_mode_value); 
						 
						return set_up(selected_items,m_flag_index,m_mode_value);
											 
						 
				} 
				else if (bl_flag_name_is_number && bl_flag_value_is_number) 
				{ 
					
					return set_up(selected_items,DU.to_int(s_flag_name),DU.to_int(s_mode_value));
					 
				} 
				else if (!bl_flag_name_is_number && !bl_flag_value_is_number) 
				{ 
					int m_flag_index = ap.state.Fl.FLAGS.indexOf(s_flag_name); 
					 
					if(m_flag_index == gl.E_ERROR) 
					{ 
					 
						gl.tracex(new Object(){},gl.sf("%s...invalid flag name...[%s]...%s",msg,s_flag_name.trim(),gl.S_ERROR)); 

						return false; 
					} 
					 
						gl.tracex(new Object(){},gl.sf("%s...accept flag name...[%s]...to...%s...%s",msg,s_flag_name.trim(),s_mode_value,gl.S_OK)); 

						int m_mode_value  = s_mode_value.trim().equalsIgnoreCase("on") ? 1 : 0; 
						 
						return set_up(selected_items,m_flag_index,m_mode_value);
											 
						  
					 
				} else if (bl_flag_name_is_number && !bl_flag_value_is_number) 
				{
					
					int m_mode_value  = s_mode_value.trim().equalsIgnoreCase("on") ? 1 : 0; 
					
					return set_up(selected_items,DU.to_int(s_flag_name),m_mode_value); 
					 
				} 
				
				 
					gl.smn(ap.state.Fl.show_flags(owner.getStio().get_state()));
			} 
			 
			 
					return true; 

		} catch (Exception e) { 
			gl.tracex( 
					new Object() { 
					}, 
					gl.sf("Action...%s...%s...%s", this.getActionName(), 
							e.toString(), gl.S_ERROR)); 

			return false; 
		} 
	} 
	public boolean set_up(List<AtOm> owners,int index, int value)
	{
		owners.forEach(a->{
			
			set_up(a,index,value);
		});
	
		return true;
	}	 
	
	
	public boolean set_up(AtOm owner,int index, int value)
	{
		if(value == gl.E_ERROR) 
		{ 
			gl.tracex(new Object(){},gl.sf("Invalid switch value...%d",value,gl.S_ERROR)); 

			return false; 
		} 
		 
		if(value > gl.E_EMPTY) 
		{ 
			owner.getStio().set_state(Biu.ON(owner.getStio().get_state(),index)); 
			 
			gl.BL_SKIP_TRACE = owner.getStio().is_debug(); 
		} 
		else 
		{ 
			owner.getStio().set_state(Biu.OFF(owner.getStio().get_state(),index)); 
		} 

		return true;
	}
	
	 

	public static void main(String[] args) { 
		 

	} 

} 
