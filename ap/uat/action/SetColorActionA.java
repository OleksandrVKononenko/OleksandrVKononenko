package ap.uat.action;

import java.awt.Color; 
import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.prop.SColor;
import ap.uat.ApplicationA;
import ap.uat.AtOm;
import ap.uat.BaseActionA;
import ap.uat.CmdA;
import ap.uat.CmdParamsA;
import ap.uat.StoreImplObject;
import ap.utils.DU;
import ap.utils.DialogUtil;
import ap.utils.Su; 


@SuppressWarnings("serial") 
public class SetColorActionA extends BaseActionA { 
	 
	public static final  List <String> VALID_CMDS = Arrays.asList(new String[] {"setcolor","setcolor()","color","color()"}); 
	 
	public static final  List <String> VALID_PARAMS = Arrays.asList(new String[] {"/b","/f","-b","-f"}); 


	public SetColorActionA() { 
		 
	} 

	public SetColorActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetColorActionA(String text) { 
		super(text,VALID_CMDS); 
		 
	} 

	public SetColorActionA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static SetColorActionA getInstance() 
	{ 
		SetColorActionA cmp = new SetColorActionA(); 
		 
		return new SetColorActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	
	public boolean router(AtOm owner) 
	{ 
		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		
		CmdA cmd  = owner.getFire_cmd(); 
				 
		int m_args_cnt = CmdParamsA.getArgsCount(cmd) - 1; 
		 
		int m_params_cnt = CmdParamsA.getParamsCount(cmd); 
		 
		if( 
				 m_args_cnt > gl.FOUR 		
		  ) 
		{ 
			 
				gl.tracex(new Object(){},gl.sf("%s...Bad params count check...params...%d...args...%d...%s",
						msg,
						m_args_cnt,
						m_params_cnt,
						gl.S_ERROR)); 
			 
				return false; 
		} 
			 
				List<AtOm> all_items =  ApplicationA.getDio().get_desk_top().get_all_childs(); 
		
				List<AtOm> selected_items =  StoreImplObject.get_selected();
				
				
				boolean bl_all = cmd.isScope_all(); 
				 
				boolean bl_selected = cmd.isScope_selected(); 
				 
				boolean bl_selected_items_size = (StoreImplObject.get_selected().size() != gl.E_EMPTY); 
							 
				 
				boolean bl_back  = cmd.isOp_Back();
				
				boolean bl_front = cmd.isOp_Front();
				
				boolean bl_info  = cmd.isOp_List();
				
				boolean bl_dialog = cmd.isOp_DialogForm();
				
				boolean bl_setup  = cmd.isOp_Setup();
				
				
				if(m_args_cnt == gl.E_EMPTY && m_params_cnt == gl.E_EMPTY && !bl_info)
				{
					bl_info = true;
				}
		 
		 
		try 
		{ 
				AtOm desktop = ApplicationA.getDio().get_desk_top();
				
				Color [] m_color = {null};
			
				if(bl_dialog)
				{
					m_color[0] = DialogUtil.get_color(desktop.getBackground());
				}
				
				
				if(m_color[0] == null && bl_setup && m_args_cnt == gl.FOUR)
				{
					
					// RGBA 
					int m_red 	= DU.to_int(CmdParamsA.get_parameter(cmd,"a001")); 
					 
					int m_green = DU.to_int(CmdParamsA.get_parameter(cmd,"a002")); 
					 
					int m_blue  = DU.to_int(CmdParamsA.get_parameter(cmd,"a003")); 
					 
					int m_alpha = DU.to_int(CmdParamsA.get_parameter(cmd,"a004")); 
					 
					m_color[0] = new Color(m_red,m_green,m_blue,m_alpha); 
					 
					gl.sfn("Read color to set...[%s]",SColor.toString(m_color[0])); 
					
				}
				
			
			//Define scope.
			if(bl_all) 
			{
				if(bl_setup){
					
					all_items.forEach(a->
					{
						if(bl_back)
							a.setBackground(m_color[0]);
						else if (bl_front)
							a.setForeground(m_color[0]);
						
						a.repaint();
					});
					
					
				} else if(bl_info)
				{
					all_items.forEach(a->
					{
					
						if(bl_back)
						{
							gl.sfn("Background color...%s...is...%s",a.getIdo().getName(),SColor.toString(a.getBackground()));
							
						}
						else if(bl_front)
						{
	
							gl.sfn("Foreground color...%s...is...%s",a.getIdo().getName(),SColor.toString(a.getForeground()));
						
						}
					});
					
					
				} // bl_info
				
			} else if(bl_selected) 
			{
				
				if(bl_setup){
					
					selected_items.forEach(a->
					{
						if(bl_back)
							a.setBackground(m_color[0]);
						else if (bl_front)
							a.setForeground(m_color[0]);
						
						a.repaint();
					});
					
				} else if (bl_info)
				{
					selected_items.forEach(a->
					{
					
						if(bl_back)
						{
							gl.sfn("Background color...%s...is...%s",a.getIdo().getName(),SColor.toString(a.getBackground()));
							
						}
						else if(bl_front)
						{
							gl.sfn("Foreground color...%s...is...%s",a.getIdo().getName(),SColor.toString(a.getForeground()));
						}
					});
			
				}
			} 
						desktop.repaint();
							 
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
		 
		gl.tracex(new Object() {}, gl.sf("%s...%s", msg,gl.S_TRY)); 

		CmdA cmd = owner.getFire_cmd(); 
		 
		if(CmdParamsA.get_bad_params_list_count_b(cmd,cmd.get_all_valid_switch())) 
		{ 
			gl.tracex(new Object() {},gl.sf("%s...bad params...%s...%s", msg,Su.get_str_from_list(CmdParamsA.get_bad_params_list(cmd,cmd.get_all_valid_switch())), gl.S_ERROR)); 
			 
			return false; 
		} 
		 
			return router(owner); 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 

