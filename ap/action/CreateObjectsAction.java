 
 
 
 
 
 
 
 
 
 
 
 
 
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

@SuppressWarnings("serial") 
public class CreateObjectsAction extends BaseAction { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"create","make","build","create()","make()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/a","/s","-s","$s"}); 

	 
	public CreateObjectsAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public CreateObjectsAction() { 
		 
	} 
	 
	public CreateObjectsAction(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public static CreateObjectsAction getInstance() 
	{ 
		CreateObjectsAction cmp = new CreateObjectsAction(); 
		 
		return new CreateObjectsAction(cmp.getClass().getSimpleName()); 
	} 
	 
	 
	public boolean router(Cmd cmd) 
	{ 
		String msg = "Action flow."; 
		 
		int m_args_count = CmdParams.getArgsCount(cmd); 
		 
		int m_params_count = CmdParams.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		Dimension dim = null; 
		 
		 
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
				 
				dim = new Dimension(2,2); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept DEFAULT arg ",SDimension.toString(dim),gl.S_OK)); 
				 
				return Application.getRio().make(dim,m_additional_mode); 
				 
			} 
			else if(m_args_count == gl.TWO) 
			{ 
				// One param presents. 
				 
				String arg = CmdParams.get_parameter(cmd,"a001"); 
				 
				// Convert to int. 
				int i_col_rows = DU.to_int(arg); 
						 
				if(i_col_rows > gl.TWO) 
				{ 
					dim = Nu.get_dimension(arg); 
							 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept ONE arg case",SDimension.toString(dim),gl.S_OK)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...bad count of object request...%d...must be more than 2...%s",msg,i_col_rows,gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept ONE arg ",SDimension.toString(dim),gl.S_OK)); 
				 
				return Application.getRio().make(dim,m_additional_mode); 
				 
			} else if(m_args_count == gl.THREE) 
			{ 
				 
				dim  = Nu.get_dimension(CmdParams.get_parameter(cmd,"a001"),CmdParams.get_parameter(cmd,"a002")); 
								 
				if (dim.width <= gl.E_EMPTY || dim.height <=  gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...dim sizing...%s...%s",msg,dim.toString(),gl.S_ERROR)); 
					 
					return false; 
				} 
				 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept of TWO arg ",SDimension.toString(dim),gl.S_OK)); 
					 
					return Application.getRio().make(dim,m_additional_mode); 
				 
			} else if(m_args_count == gl.E_OK*4) 
			{ 
				// Param1 : count 
				// Param2,Param3 : Dimension of size 
				 
				String arg = CmdParams.get_parameter(cmd,"a001"); 
				 
				// Convert to int. 
				int i_col_rows = DU.to_int(arg); 
						 
				if(i_col_rows > gl.TWO) 
				{ 
					dim = Nu.get_dimension(arg); 
							 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept ONE arg case",SDimension.toString(dim),gl.S_OK)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...bad count of object request...%d...must be more than 2...%s",msg,i_col_rows,gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				Dimension dim2  = Nu.get_dimension(CmdParams.get_parameter(cmd,"a002"),CmdParams.get_parameter(cmd,"a003")); 
				 
				if (dim2.width <= gl.E_EMPTY || dim2.height <=  gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...dim sizing...%s...%s",msg,dim2.toString(),gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s...%s",msg,"accept FOUR arg ",SDimension.toString(dim),SDimension.toString(dim2),gl.S_OK)); 
				 
				return Application.getRio().make(dim,dim2,m_additional_mode); 
					 
			} else if(m_args_count == gl.E_OK*5) 
			{ 
	 
				// First dim. 
				 
				dim  = Nu.get_dimension(CmdParams.get_parameter(cmd,"a001"),CmdParams.get_parameter(cmd,"a002")); 
				 
				if (dim.width <= gl.E_EMPTY || dim.height <=  gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...dim sizing...%s...%s",msg,dim.toString(),gl.S_ERROR)); 
					 
					return false; 
				} 
				 
				// Second dim. 
				 
				Dimension dim_add  = Nu.get_dimension(CmdParams.get_parameter(cmd,"a003"),CmdParams.get_parameter(cmd,"a004")); 
				 
				if (dim_add.width <= gl.E_EMPTY || dim_add.height <=  gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...dim add sizing...%s...%s",msg,dim_add.toString(),gl.S_ERROR)); 
					 
					return false; 
				} 
				 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s...%s",msg,"accept FOUR arg ",SDimension.toString(dim),SDimension.toString(dim_add),gl.S_OK)); 
				 
					return Application.getRio().make(dim,dim_add,m_additional_mode); 
					 
			}else if(m_args_count > gl.E_OK*5) 
			{ 
					gl.tracex(new Object(){},gl.sf("%s...%d...%s",msg,"Bad count args for execute.",m_args_count,gl.S_ERROR)); 
					 
					return false; 
					 
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
