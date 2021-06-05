 
 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 

@SuppressWarnings("serial") 
public class DeleteAction extends BaseAction { 

	public static final String [] CMDS = new String[] {"del","rem","del()","rem()"}; 
	 
	public DeleteAction() { 
		 
	} 

	public DeleteAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public DeleteAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public DeleteAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static DeleteAction getInstance() 
	{ 
		DeleteAction cmp = new DeleteAction(); 
		 
		return new DeleteAction(cmp.getClass().getSimpleName()); 
		 
	} 

	@Override 
	public boolean after(PanelXml owner) 
	{ 
		return Application.getRio().refresh() ; 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

			gl.tracex(new Object(){},gl.sf("%s...%s",gl.sf("Action...%s",this.getValue(NAME)),gl.S_TRY)); 
			 
			owner.setDelete(true); 
			 
			owner.getSelected().forEach((k,v)->{ 
				v.setDelete(owner.isDelete()); 
			}); 
			 
			return (owner.isDelete() && this.after(owner)); 
			 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
