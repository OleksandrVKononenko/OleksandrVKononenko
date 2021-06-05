 
 
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
import ap.uat.StoreImplObject; 

@SuppressWarnings("serial") 
public class DeleteActionA extends BaseActionA { 

	public static final String [] CMDS = new String[] {"del","rem","del()","rem()"}; 
	 
	public static final String [] VALID_PARAMS =  new String[] {"/s","/a"}; 
	 
	public DeleteActionA() { 
		 
	} 

	public DeleteActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public DeleteActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public DeleteActionA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static DeleteActionA getInstance() 
	{ 
		DeleteActionA cmp = new DeleteActionA(); 
		 
		return new DeleteActionA(cmp.getClass().getSimpleName()); 
		 
	} 

	@Override 
	public boolean after(AtOm owner) 
	{ 
		// Tip a garbage collector. 
		return ApplicationA.getRio().gc() ; 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

			String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
			String [] m_bad_params_list = {""}; 
			 
			CmdA fire_cmd = owner.getFire_cmd(); 
			 		 
			// Check params. 
			 
			if (!CmdParamsA.checkParams(fire_cmd,VALID_PARAMS,m_bad_params_list)) 
		    { 
				gl.tracex(new Object() {},gl.sf("%s...not valid params list...%s...%s", msg,m_bad_params_list[gl.E_EMPTY], gl.S_ERROR)); 
			 
				return false; 
		    } 
			 
			 
			gl.tracex(new Object() {}, gl.sf("%s...%s", msg,gl.S_TRY)); 
			 
			//owner.getStio().set_delete(true); 
			 
		
			 
			if(owner.getFire_cmd().isScope_all()) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,"ALL scope detected",gl.S_OK)); 
				 
				return (owner.getStio().is_delete() && CleanDesktopA.getInstance().actionPerformed(owner)); 
				 
			}else if(owner.getFire_cmd().isScope_selected() || StoreImplObject.get_selected().size() != gl.E_EMPTY) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,"SELECTED scope detected",gl.S_OK)); 
				 
				StoreImplObject.get_selected().forEach(a-> 
				{ 
						a.getStio().set_delete(true); 
						
						gl.sfn("Delete flag for ...,%s",a.getIdo().getName());
						
				}); 
				 
				return (owner.getStio().is_delete() && this.after(owner)); 
				 
			} 
			 
			 
				gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,"DEFAULT scope",gl.S_OK)); 
				 
				StoreImplObject.get_selected().add(owner); 
				 
				return (owner.getStio().is_delete() && this.after(owner)); 
			 
			 
	} 

	public static void main(String[] args) { 
		 

	} 

} 

