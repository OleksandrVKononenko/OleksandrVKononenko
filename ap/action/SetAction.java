 
 
package ap.action; 


import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 
import ap.utils.Biu; 
import ap.utils.DU; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class SetAction extends BaseAction { 
	 
	public static final String [] CMDS = new String[] {"set","set()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/a","/s","-a","-s"}; 


	public SetAction() { 
		 
	} 

	public SetAction(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 

	public SetAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	public static SetAction getInstance() 
	{ 
		SetAction cmp = new SetAction(); 
		 
		return new SetAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		String [] NOT_VALID_PARAMS = {""}; 
		 
		Cmd cmd = owner.getFire_cmd(); 
		 
		try { 

			if (!CmdParams 
					.checkParams(cmd, VALID_PARAMS, NOT_VALID_PARAMS)) { 
				gl.tracex(new Object() { 
				}, gl.sf("Action...%s...not valid params list...%s...%s", 
						this.getActionName(), NOT_VALID_PARAMS[gl.E_EMPTY], 
						gl.S_ERROR)); 

				return false; 
			} 
			 
			int m_args_count = CmdParams.getArgsCount(cmd); 
			 
			int m_params_count = CmdParams.getParamsCount(cmd); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
			String s_flag_name = ""; 
			 
			String s_mode_value = ""; 
			 
			// Too many args ban. 
			if(m_args_count > gl.THREE) 
			{ 
				gl.tracex(new Object(){},gl.sf("%s...Too many args...[%s]",msg,CmdParams.show_params(cmd))); 
						 
				return false; 
			} 
			 
			// Show of the current state. 
			if(m_args_count == gl.ONE) 
			{ 
				gl.smn(ap.state.Fl.show_flags(owner.getState())); 
				 
				return true; 
			} 
			 
			 
			if(m_args_count == gl.THREE) 
			{ 

				 
				s_flag_name = CmdParams.get_parameter(cmd,"a001"); 
				 
				boolean bl_flag_name_is_number = Su.isNumber(s_flag_name); 
				 
				 
				s_mode_value = CmdParams.get_parameter(cmd,"a002"); 
					 
				boolean bl_flag_value_is_number = Su.isNumber(s_mode_value); 
				 
				 
				gl.tracex(new Object(){},gl.sf("%s...arg...%s...%s...arg...%s...%s...%s",msg,s_flag_name,bl_flag_name_is_number,s_mode_value,bl_flag_value_is_number, gl.S_OK)); 
				 
				 
				 
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
						 
						if(m_mode_value == gl.E_ERROR) 
						{ 
							gl.tracex(new Object(){},gl.sf("%s...accept flag name...[%s]...to...%s...%s",msg,s_flag_name.trim(),s_mode_value,gl.S_ERROR)); 

							return false; 
						} 
						 
						if(m_mode_value > gl.E_EMPTY) 
						{ 
							owner.setState(Biu.ON(owner.getState(),m_flag_index)); 
							 
							gl.BL_SKIP_TRACE = owner.isDebug(); 
						} 
						else 
						{ 
							owner.setState(Biu.OFF(owner.getState(),m_flag_index)); 
						} 
					 
							gl.smn(ap.state.Fl.show_flags(owner.getState())); 
				} 
				else if (bl_flag_name_is_number && bl_flag_value_is_number) 
				{ 
							gl.smn("Case 2...first arg in numeric format then mode in numeric format" ); 
					 
				} 
				else if (!bl_flag_name_is_number && !bl_flag_value_is_number) 
				{ 
							gl.smn("Case 3...first arg in string format then mode in string format" ); 
					 
				}else if (bl_flag_name_is_number && !bl_flag_value_is_number) 
				{ 
							gl.smn("Case 4...first arg in numeric format then mode in string format" ); 
					 
				} 
				 
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
		 
		public boolean actionPerformed_(PanelXml owner) { 

			String msg = gl.sf("Action...%s", this.getValue(NAME)); 

			String [] NOT_VALID_PARAMS = {""}; 
			 
			Cmd cmd = owner.getFire_cmd(); 
			 
			try { 

				if (!CmdParams 
						.checkParams(cmd, VALID_PARAMS, NOT_VALID_PARAMS)) { 
					gl.tracex(new Object() { 
					}, gl.sf("Action...%s...not valid params list...%s...%s", 
							this.getActionName(), NOT_VALID_PARAMS[gl.E_EMPTY], 
							gl.S_ERROR)); 

					return false; 
				} 
				 
				int m_args_count = CmdParams.getArgsCount(cmd); 
				 
				int m_params_count = CmdParams.getParamsCount(cmd); 
				 
				gl.tracex(new Object(){},gl.sf("%s...Count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
			 
				String s_flag_name = ""; 
				 
				String s_mode_value = ""; 
				 
				// Too many args ban. 
				if(m_args_count > gl.THREE) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...Too many args...[%s]",msg,CmdParams.show_params(cmd))); 
							 
					return false; 
				} 
				 
				// Show of the current state. 
				if(m_args_count == gl.ONE) 
				{ 
					gl.smn(ap.state.Fl.show_flags(owner.getState())); 
					 
					return true; 
				} 
				 
				 
				if(m_args_count == gl.THREE) 
				{ 
					// Enable. No switchs - work with current object. 
					// Extract flag name. 
					// Check order of processing 
					s_flag_name = CmdParams.get_parameter(cmd,"a000"); 
					 
					boolean bl_flag_name_is_number = Su.isNumber(s_flag_name); 
					 
					s_mode_value = CmdParams.get_parameter(cmd,"a001"); 
						 
					boolean bl_flag_value_is_number = Su.isNumber(s_mode_value); 
					 
					gl.tracex(new Object(){},gl.sf("%s...arg...%s...%s...arg...%s...%s...%s",msg,s_flag_name,bl_flag_name_is_number,s_mode_value,bl_flag_value_is_number, gl.S_OK)); 
					 
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
							 
							if(m_mode_value == gl.E_ERROR) 
							{ 
								gl.tracex(new Object(){},gl.sf("%s...accept flag name...[%s]...to...%s...%s",msg,s_flag_name.trim(),s_mode_value,gl.S_ERROR)); 

								return false; 
							} 
							 
							if(m_mode_value > gl.E_EMPTY) 
							{ 
								owner.setState(Biu.ON(owner.getState(),m_flag_index)); 
								 
								gl.BL_SKIP_TRACE = owner.isDebug(); 
							} 
							else 
							{ 
								owner.setState(Biu.OFF(owner.getState(),m_flag_index)); 
							} 
						 
								gl.smn(ap.state.Fl.show_flags(owner.getState())); 
					} 
					else if (bl_flag_name_is_number && bl_flag_value_is_number) 
					{ 
						gl.smn("Case 2...first arg in numeric format then mode in numeric format" ); 
						 
					} 
					else if (!bl_flag_name_is_number && !bl_flag_value_is_number) 
					{ 
						gl.smn("Case 3...first arg in string format then mode in string format" ); 
						 
					}else if (bl_flag_name_is_number && !bl_flag_value_is_number) 
					{ 
						gl.smn("Case 4...first arg in numeric format then mode in string format" ); 
						 
					} 
					 
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
	 

	public static void main(String[] args) { 
		 

	} 

} 
