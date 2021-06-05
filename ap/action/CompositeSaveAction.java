 
 
 
 
/** 
* 
*/ 
package ap.action; 

import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
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
public class CompositeSaveAction extends BaseAction { 

	private static final long serialVersionUID = 8606313428010001285L; 
	 
	public static final String [] CMDS = new String[] {"save","save()"}; 
		 
	public CompositeSaveAction() { 
		 
	} 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public CompositeSaveAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public CompositeSaveAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	public static CompositeSaveAction getInstance() 
	{ 
		CompositeSaveAction cmp = new CompositeSaveAction(); 
		 
		return new CompositeSaveAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		String composite_file = Fu.file_save_dlg(Application.getCio().getHome()); 

		if (composite_file == null) { 
			gl.tracex(new Object() { 
			}, gl.sf("%s...%s...%s", msg, 
					"File choosing operation was canceled", gl.S_ERROR)); 
		 
			return false; 
		} 
		 
		Application.getCio().setHome(Fu.isaGetExtension(composite_file,"xml")); 

		boolean bl_result = WriteObjectsAction.getInstance().actionPerformed(owner); 
		 
		gl.tracex(new Object() {}, gl.sf("%s...Choose composite file...%s...items...%d...%s", msg, Application.getCio().getHome(),owner.getItems().size(), 
				bl_result ? gl.S_OK : gl.S_ERROR)); 
	 
		return bl_result; 

	} 
	 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
