 
 
 
 
 
 
 
 
 
 
 
package ap.action; 


import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.IAlign; 
import ap.swing.PanelXml; 
import ap.utils.DU; 
import ap.utils.MapUtils; 


@SuppressWarnings("serial") 
public class LayoutAction extends BaseAction { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"layout","layout()","lay","lay()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/any"}); 


	public LayoutAction() { 
		 
	} 

	public LayoutAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public LayoutAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public LayoutAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static LayoutAction getInstance() 
	{ 
		LayoutAction cmp = new LayoutAction(); 
		 
		return new LayoutAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	public boolean router(Cmd cmd) 
	{ 
		String msg = "Layout action."; 
		 
		int m_args_count = CmdParams.getArgsCount(cmd); 
		 
		int m_params_count = CmdParams.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 

		 
		// Check params boundaries. 
		 
		if(m_args_count > gl.THREE ) 
		{ 
			gl.tracex(new Object(){},gl.sf("%s...bad arg count...args...%d...params...%d...%s",msg,m_args_count,m_params_count,gl.S_ERROR)); 
			 
			return false; 
	 
		} 
		 
		try 
		{ 
			PanelXml 	component 	= null; 
			 
			int 		m_align 	= cmd.getOwner().getDecoro().getAlign(); 
					 
			if(m_args_count == gl.ONE) 
			{ 
				// Show current align value; 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...align...%s...%s",msg,"ONE",cmd.getOwner().getName(),(m_align==gl.E_ERROR) ? m_align :IAlign.indexOf(m_align),gl.S_OK)); 
				 
				return true; 
			} 
			else if(m_args_count == gl.TWO) 
			{ 
				// We are waiting of the align value in this case only. 
				// One params presents. 
				String m_arg = CmdParams.get_parameter(cmd,"a001"); 
				 
				// Convert to int. 
				m_align = DU.to_int(m_arg); 
				 
				// Check type. 
				// Must be strong int. 
				if(m_align == gl.E_ERROR) 
				{ 
					// If pointed to the object name then looking for and show of the current align. 
					 
					component = MapUtils.findByName(cmd.getOwner().getItems(),m_arg); 
					 
					if (component == null || !m_arg.equalsIgnoreCase("Desktop")) 
					{ 
						gl.tracex(new Object(){},gl.sf("%s...%s...not find the object by name...%s...%s",msg,"ONE",m_arg,gl.S_ERROR)); 
						 
						return false; 
					} 
					else 
					{ 
						gl.tracex(new Object(){},gl.sf("%s...%s...align is...%d...%s",msg,"ONE",m_arg,cmd.getOwner().getDecoro().getAlign(),gl.S_OK)); 
						 
						return true; 
						 
					} 
					 
				} 
				 
					// Set align on current object. 
				 
					cmd.getOwner().getDecoro().setAlign(m_align); 
				 
					gl.tracex(new Object(){},gl.sf("%s...%s...setup(1) align...%s...%s",msg,cmd.getOwner().getName(),IAlign.indexOf(m_align),gl.S_OK)); 
				 
						 
			} else  if(m_args_count == gl.THREE) 
			{ 
				// Two params presents. 
				// Need setup align for the object that pointed in first arg. 
				// Get the object id or name. 
				 
				String m_arg_first = CmdParams.get_parameter(cmd,"a001"); 
				 
				Integer m_id  = DU.to_int(m_arg_first); 
				 
				if(m_id == gl.E_ERROR) 
				{ 
					// Looking by name. 
					 
					component = MapUtils.findByName(cmd.getOwner().getItems(),m_arg_first); 
					 
					if (component == null) 
					{ 
						gl.tracex(new Object(){},gl.sf("%s...not find the object by name...%s...%s",msg,m_arg_first,gl.S_ERROR)); 
						 
						return false; 
					} 
										 
				} 
				else 
				{ 
					 component = PanelXml.items.get(m_id); 
					 
					 if (component == null) 
						{ 
							gl.tracex(new Object(){},gl.sf("%s...not find the object by id...%03d...%s",msg,m_id,gl.S_ERROR)); 
							 
							return false; 
						} 
				} // check at m_id. 
				 
				// Get the second param. 
				String m_arg_second = CmdParams.get_parameter(cmd,"a002"); 
				 
				// Convert to int. 
				m_align = DU.to_int(m_arg_second); 
				 
				// Check type. 
				// Must be strong int. 
				if(m_align == gl.E_ERROR) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s....align value invalid...%s...%s",msg,m_arg_second,gl.S_ERROR)); 
					 
					return false; 
				} 

				// Update component. 
				component.getDecoro().setAlign(m_align); 
				 
				gl.tracex(new Object(){},gl.sf("%s....setup(s) align value...%d...%s",msg,component.getDecoro().getAlign(),gl.S_OK)); 
				 
			} // check by m_args_count. 
			 
				return true; 
		} 
		catch(Exception e) 
		{ 
				gl.tracex(new Object(){},gl.sf("%s...exception...%s...%s",msg,e.getMessage().toString(),gl.S_ERROR)); 
						 
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
