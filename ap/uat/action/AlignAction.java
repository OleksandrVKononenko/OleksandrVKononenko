 
 
 
package ap.uat.action; 

import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.prop.SDimension; 
import ap.swing.Application; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class AlignAction extends BaseActionA { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"align","align()","format","format()","fmt","fmt()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/a","-a","$a","--a"}); 


	public AlignAction() { 
		 
	} 

	public AlignAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public AlignAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public AlignAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static AlignAction getInstance() 
	{ 
		AlignAction cmp = new AlignAction(); 
		 
		return new AlignAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	 
	public boolean router(AtOm owner) 
	{ 
		String msg = "Action flow"; 
		 
		CmdA cmd  = owner.getFire_cmd(); 
				 
		int m_args_count = CmdParamsA.getArgsCount(cmd) - 1; 
		 
		int m_params_count = CmdParamsA.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		if( 
				m_args_count > gl.TWO && 
				( m_params_count != gl.E_EMPTY || m_params_count != gl.E_OK) 
				 
		   ) 
		{ 
			 
			gl.tracex(new Object(){},gl.sf("%s...Bad params count check...%s",msg,gl.S_ERROR)); 
			 
			return false; 
		} 
			 
			 
		 
		Dimension dim = null; 
		 
		 
		try 
		{ 
			boolean m_mode = CmdParamsA.isaParam(cmd,"/a"); 
			 
			 
			if(m_mode) 
			{ 
				gl.tracex(new Object(){},gl.sf("%s...Activate ADDITIONAL mode...%s",msg,m_mode)); 
			} 
			 
			if(m_params_count == gl.ZERO ) 
			{ 
				dim = new Dimension(2,2); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept DEFAULT arg ",SDimension.toString(dim),gl.S_OK)); 
				 
				//return  owner.alignComponents(ApplicationA.getDio().get_desk_top(),dim,m_mode); 
				return true; 
				 
			} else if(m_params_count == gl.ONE ) 
			{ 
				//return  owner.alignComponents(ApplicationA.getDio().get_desk_top(),CmdParamsA.get_dim(cmd),m_mode); 
				 
				return true; 
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
	public boolean actionPerformed(AtOm owner) { 
	 
		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		CmdA cmd = owner.getFire_cmd(); 
		 
		List<String> isa_bad = new ArrayList<String>(); 
		 
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
