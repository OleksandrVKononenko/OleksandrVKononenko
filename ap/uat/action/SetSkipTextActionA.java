 
 
 
package ap.uat.action; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.uat.StoreImplObject; 
import ap.utils.Su; 


public class SetSkipTextActionA extends BaseActionA { 

	private static final long serialVersionUID = -7873062210281142997L; 
	 
	public static final List <String> CMDS = Arrays.asList(new String[] {"fst","fst()"}); 
	 
	public static  List <String> VALID_PARAMS = Arrays.asList(new String[] {""}); 
	 
	public SetSkipTextActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetSkipTextActionA(String text) { 
		super(text,CMDS); 
		 
	} 
	 
	public SetSkipTextActionA() { 
				 
	} 

				 
	public static SetSkipTextActionA getInstance() 
	{ 
		SetSkipTextActionA cmp = new SetSkipTextActionA(); 
		 
		return new SetSkipTextActionA(cmp.getClass().getSimpleName()); 
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
		 
		 		 
		try { 
			 
					 
			boolean bl_scope_all = owner.getFire_cmd().isScope_all(); 
			 
			boolean bl_scope_selected = owner.getFire_cmd().isScope_selected(); 
			 
			boolean bl_selected_items_size = (StoreImplObject.get_selected().size() != gl.E_EMPTY); 
						 
			 
			boolean bl_op_on = owner.getFire_cmd().isOp_On(); 
			 
			boolean bl_op_off = owner.getFire_cmd().isOp_Off(); 
			 
			boolean bl_op_reversed = owner.getFire_cmd().isOp_Reverse(); 
			 
		 
			List<AtOm> items =  ApplicationA.getDio().get_desk_top().get_all_childs(); 
					 
			List<AtOm> selected_items =  StoreImplObject.get_selected(); 
			 
			 
			if(bl_scope_all) 
			{ 
				if(bl_op_on) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"ALL scope detected","ON",gl.S_OK)); 
					 
					items.forEach(a->{ 
						 
						a.getStio().set_skip_text(true); 
						 
						a.repaint(); 
					}); 
					 
				} 
				else if(bl_op_off) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"ALL scope detected","OFF",gl.S_OK)); 
					 
					items.forEach(a->{ 
						 
						a.getStio().set_skip_text(false); 
						 
						a.repaint(); 
					}); 

					 
				}else if(bl_op_reversed) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"ALL scope detected","REVERSED",gl.S_OK)); 
					 
					items.forEach(a->{ 
						 
						a.getStio().set_skip_text(!a.getStio().is_skip_text()); 
						 
						a.repaint(); 
					}); 

				} 
				 
			}else if(bl_scope_selected || bl_selected_items_size) 
			{ 
				 
				 
				if(bl_op_on) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"SELECTED scope detected","ON",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(true); 
						 
						a.repaint(); 
					}); 
					 
				} 
				else if(bl_op_off) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"SELECTED scope detected","OFF",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(false); 
						 
						a.repaint(); 
					}); 

					 
				}else if(bl_op_reversed) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"SELECTED scope detected","REVERSED",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(!a.getStio().is_skip_text()); 
						 
						a.repaint(); 
					}); 

				} 
				 
				 
			}else 
			{ 
				selected_items.clear(); 
				 
				selected_items.add(owner); 
				 
				if(bl_op_on) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"OWNER scope detected","ON",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(true); 
						 
						a.repaint(); 
					}); 
					 
				} 
				else if(bl_op_off) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"OWNER scope detected","OFF",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(false); 
						 
						a.repaint(); 
					}); 

					 
				}else if(bl_op_reversed) 
				{ 
					gl.tracex(new Object() {}, gl.sf("%s...%s...operation...%s...%s", msg,"OWNER scope detected","REVERSED",gl.S_OK)); 
					 
					selected_items.forEach(a->{ 
						 
						a.getStio().set_skip_text(!a.getStio().is_skip_text()); 
						 
						a.repaint(); 
					}); 

				} 
				 
				 
			} 
			 
				return true; 
			 
		} catch (Exception e) { 

			gl.tracex(new Object() {},gl.sf("%s...%s...%s",msg,e.toString().substring(1,32), gl.S_ERROR)); 

			return false; 
		} 

	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
