 
 
package ap.action; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.prop.SColor; 
import ap.prop.SDimension; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.CU; 
import ap.utils.DU; 
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class SetColorAction extends BaseAction { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"setcolor","setcolor()","color","color()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/b","/f","-b","-f"}); 


	public SetColorAction() { 
		 
	} 

	public SetColorAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetColorAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public SetColorAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static SetColorAction getInstance() 
	{ 
		SetColorAction cmp = new SetColorAction(); 
		 
		return new SetColorAction(cmp.getClass().getSimpleName()); 
		 
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
			 
		 
				Dimension dim = null; 
		 
		 
		try 
		{ 
				boolean m_background = CmdParams.isaParam(cmd,"/b"); 
				 
				boolean m_foreground = CmdParams.isaParam(cmd,"/f"); 
				 
				String m_color = ""; 
			 
			 
			if(m_params_count == gl.ZERO ) 
			{ 
				// Show section. 
				 
				m_color = (m_background) ? SColor.toString(owner.getBackground()) : SColor.toString(owner.getForeground()); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"show current state ",m_color,gl.S_OK)); 
				 
				return  true; 
				 
			} else if(m_params_count == gl.ONE ) 
			{ 
				//Set section. 
				 
				// RGBA 
				int m_red 	= DU.to_int(CmdParams.get_parameter(cmd,"a001")); 
				 
				int m_green = DU.to_int(CmdParams.get_parameter(cmd,"a002")); 
				 
				int m_blue  = DU.to_int(CmdParams.get_parameter(cmd,"a003")); 
				 
				int m_alpha = DU.to_int(CmdParams.get_parameter(cmd,"a004")); 
				 
				Color i_color = new Color(m_red,m_green,m_blue,m_alpha); 
				 
				gl.smn(gl.sf("--> Read color to set...%s",SColor.toString(i_color))); 
				 
						if(m_background) 
						{ 
							owner.setBackground(i_color); 
							 
							// For selected items. 
							owner.getSelected().values().forEach(v->{ 
								 
								v.setBackground(i_color); 
								 
								v.repaint(); 
								 
							}); 
							 
							 
						} 
						else if(m_foreground) 
						{ 
							owner.setBackground(i_color); 
							 
							// For selected items. 
							owner.getSelected().values().forEach(v->{ 
								 
								v.setForeground(i_color); 
								 
								v.repaint(); 
								 
							}); 
							 
						} 
								owner.repaint(); 
						 
								gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"set color ",SColor.toString(i_color),gl.S_OK)); 
									 
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

