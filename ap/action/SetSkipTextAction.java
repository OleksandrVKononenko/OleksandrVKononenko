 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.action; 

import java.util.Arrays; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 


public class SetSkipTextAction extends BaseAction { 

	private static final long serialVersionUID = -7873062210281142997L; 
	 
	public static final String [] CMDS = new String[] {"flst","flst()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/all","/a","/s","/selected","/global","/gl"}; 
		 
	public SetSkipTextAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetSkipTextAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	public SetSkipTextAction() { 
				 
	} 

				 
	public static SetSkipTextAction getInstance() 
	{ 
		SetSkipTextAction cmp = new SetSkipTextAction(); 
		 
		return new SetSkipTextAction(cmp.getClass().getSimpleName()); 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		try { 
			 
			String [] NOT_VALID_PARAMS = {""}; 
					 
			if (!CmdParams.checkParams(owner.getFire_cmd(),VALID_PARAMS,NOT_VALID_PARAMS)) 
			    { 
					gl.tracex(new Object() {},gl.sf("Action...%s...not valid params list...%s...%s", this.getActionName(),NOT_VALID_PARAMS[gl.E_EMPTY], gl.S_ERROR)); 
				 
					return false; 
			    } 
			 
			if (owner.getFire_cmd().isScope_selected()) { 
				owner.getSelected().forEach((k, v) -> { 
					v.setSkipText(!v.isSkipText()); 
				}); 

			} else if (owner.getFire_cmd().isScope_all()) { 
				owner.getItems().forEach((k, v) -> { 
					v.setSkipText(!v.isSkipText()); 
				}); 
			} else { 
				owner.setSkipText(!owner.isSkipText()); 
			} 

			gl.tracex(new Object() {},gl.sf("Action...%s...%s", this.getActionName(), gl.S_OK)); 

			return RefreshObjects.getInstance().actionPerformed(owner); 
			 
		} catch (Exception e) { 

			gl.tracex(new Object() {},gl.sf("Action...%s...%s...%s",this.getActionName(),e.toString().substring(1,32), gl.S_ERROR)); 

			return false; 
		} 

	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
