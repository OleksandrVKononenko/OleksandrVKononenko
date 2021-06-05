 
 
 
package ap.uat.action; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 
import javax.swing.JFrame; 

import ap.global.gl; 
import ap.prop.SBounds; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class ScreenSizeA extends BaseActionA { 
	 
	public static final List<String> VALID_CMDS = Arrays.asList(new String[] {"ss","ss()","size","size()"}); 
	 
	public static final List<String> VALID_PARAMS = Arrays.asList(new String[] {"/mx","/mn","/md","-mx","-mn","-md","/cr","-cr","mid","min","max","cur","set","-s"}); 

	 
	 
	public ScreenSizeA() { 
		 
	} 

	public ScreenSizeA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ScreenSizeA(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public ScreenSizeA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static ScreenSizeA getInstance() 
	{ 
		ScreenSizeA cmp = new ScreenSizeA(); 
		 
		return new ScreenSizeA(cmp.getClass().getSimpleName()); 
		 
	} 

	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		CmdA cmd = owner.getFire_cmd(); 
		 
		try { 
			 
			int m_args_count = CmdParamsA.getArgsCount(cmd) - 1; 
			 
			int m_params_count = CmdParamsA.getParamsCount(cmd); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
			 
			List<String> isa_bad = new ArrayList<String>(); 
			 
			if (CmdParamsA.isa_bad_params(cmd,VALID_PARAMS,isa_bad)) 
			{ 
				gl.tracex(new Object() {},gl.sf("%s...not valid params...[%s]...%s",msg,Su.get_str_from_list(isa_bad),gl.S_ERROR)); 
				 
				return false; 
			} 
		 
		 
			Dimension size =  gl.getScreenSize(); 
			 
			Rectangle max_rect = new Rectangle(0,0,size.width,size.height); 
					 
			Rectangle min_rect = new Rectangle(size.width /2 - 160,size.height /2 - 120,320,240); 
			 
			Rectangle mid_rect = new Rectangle(size.width /2 - 320,size.height /2 - 240,640,480); 
			 
			JFrame f = ApplicationA.getFio().get_frame(); 
			 
			Rectangle cur_rect = ApplicationA.getFio().getOriginal_bounds(); 
					 
			 
			if(m_args_count == gl.E_EMPTY  && m_params_count == gl.E_EMPTY) 
			{ 
				// Show the current bounds of the frame. 
				 
				gl.tracex(new Object() {},gl.sf("%s...Current frame bounds...[%s]...%s",msg,SBounds.toString(cur_rect),gl.S_OK)); 
				 
				return true; 
				 
			} if(m_args_count != gl.E_EMPTY && m_params_count != gl.E_EMPTY) 
			{ 

				gl.tracex(new Object() {},gl.sf("%s...Please do choice by args or params will operates...%s",msg,gl.S_ERROR)); 
				 
				return false; 

			} 
			 
			if(m_args_count == gl.E_OK) 
			{ 
				String arg = CmdParamsA.get_parameter(cmd,"a001"); 
				 
				if(arg.equalsIgnoreCase("max")) 
				{ 
					f.setBounds(max_rect); 
					 
				} else if(arg.equalsIgnoreCase("min")) 
				{ 
					f.setBounds(min_rect); 
					 
				}   else if(arg.equalsIgnoreCase("mid")) 
				{ 
					f.setBounds(mid_rect); 
					 
				} else if(arg.equalsIgnoreCase("cur")) 
				{ 
					f.setBounds(cur_rect); 
				}else if(arg.equalsIgnoreCase("set")) 
				{ 
					ApplicationA.getFio().setOriginal_bounds(ApplicationA.getFio().get_frame().getBounds()); 
					 
					gl.tracex(new Object() {},gl.sf("%s...Update frame bounds...[%s]...%s",msg,SBounds.toString(ApplicationA.getFio().getOriginal_bounds()),gl.S_OK)); 
										 
				} 
				 
			} 
		 
			 
				gl.tracex(new Object() {},gl.sf("%s...Set current frame bounds...[%s]...%s",msg,SBounds.toString(f.getBounds()),gl.S_OK)); 
			 
			 
			/* 
			 
			if(m_args_count != gl.E_OK && m_params_count == gl.E_EMPTY) 
			{ 
				if(CmdParams.isaParam(cmd,"min")) 
				{ 
					f.setBounds(min_rect); 
				} 
				else if(CmdParams.isaParam(cmd,"max")) 
				{ 
					f.setBounds(max_rect); 
				} 
				else if(CmdParams.isaParam(cmd,"mid")) 
				{ 
					f.setBounds(middle_rect); 
				} 
			} 
			else if(m_args_count == gl.TWO && m_params_count != gl.E_EMPTY) 
			{ 
				if(CmdParams.isaParam(cmd,"/mn")) 
				{ 
					f.setBounds(min_rect); 
				} 
				else if(CmdParams.isaParam(cmd,"/mx")) 
				{ 
					f.setBounds(max_rect); 
				} 
				else if(CmdParams.isaParam(cmd,"/md")) 
				{ 
					f.setBounds(middle_rect); 
				} 
			} else if(m_args_count != gl.E_OK && m_params_count != gl.E_EMPTY) 
			{ 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,"Skip command.Please do choice arg or switch mode.",gl.S_OK)); 
			}else if(m_args_count == gl.E_OK && m_params_count == gl.E_EMPTY) 
			{ 
					boolean bl_save = gl.BL_SKIP_TRACE; 
						 
					gl.BL_SKIP_TRACE = false; 
				 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"Show current desktop  size mode.",SDimension.toString(owner.getDio().getDeskTop().getSize()),gl.S_OK)); 
				 
					gl.BL_SKIP_TRACE = bl_save; 
			} 
			 */ 
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
