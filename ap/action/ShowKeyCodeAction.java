 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 


@SuppressWarnings("serial") 
public class ShowKeyCodeAction extends BaseAction { 

	public static final String [] CMDS = new String[] {"skc","skc()"}; 
	 
	 
	public ShowKeyCodeAction() { 
		 
	} 

	public ShowKeyCodeAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ShowKeyCodeAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public ShowKeyCodeAction(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static ShowKeyCodeAction getInstance() 
	{ 
		ShowKeyCodeAction cmp = new ShowKeyCodeAction(); 
		 
		return new ShowKeyCodeAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		boolean bl_primary_state = owner.isShowKeyCode(); 
		 
		owner.setShowKeyCode(!owner.isShowKeyCode()); 
		 
		boolean bl_result = owner.isShowKeyCode() == ! bl_primary_state;; 
		 
		gl.tracex(new Object() {}, gl.sf("%s...state is...%s...%s", msg, owner.isShowKeyCode(),	bl_result ? gl.S_OK : gl.S_ERROR)); 
	 
		return bl_result; 

	} 

	public static void main(String[] args) { 
		 

	} 

} 
