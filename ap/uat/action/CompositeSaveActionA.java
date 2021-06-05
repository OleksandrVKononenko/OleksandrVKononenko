 
 
 
/** 
* 
*/ 
package ap.uat.action; 

import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.utils.Fu; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 04 ????. 2019 ?. 14:53:31 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : CompositeSaveAction.java 
* 
*/ 
public class CompositeSaveActionA extends BaseActionA { 

	private static final long serialVersionUID = 8606313428010001285L; 
	 
	public static final String [] CMDS = new String[] {"save","save()"}; 
		 
	public CompositeSaveActionA() { 
		 
	} 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public CompositeSaveActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public CompositeSaveActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	public static CompositeSaveActionA getInstance() 
	{ 
		CompositeSaveActionA cmp = new CompositeSaveActionA(); 
		 
		return new CompositeSaveActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		String composite_file = Fu.file_save_dlg(ApplicationA.getCio().getHome()); 

		if (composite_file == null) { 
			gl.tracex(new Object() { 
			}, gl.sf("%s...%s...%s", msg, 
					"File choosing operation was canceled", gl.S_ERROR)); 
		 
			return false; 
		} 
		 
		ApplicationA.getCio().setHome(Fu.isaGetExtension(composite_file,"xml")); 

		boolean bl_result = WriteObjectsActionA.getInstance().actionPerformed(owner); 
		 
		gl.tracex(new Object() {}, gl.sf("%s...Choose composite file...%s...items...%d...%s", msg, ApplicationA.getCio().getHome(),owner.get_childs().size(), 
				bl_result ? gl.S_OK : gl.S_ERROR)); 
	 
		return bl_result; 

	} 
	 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
