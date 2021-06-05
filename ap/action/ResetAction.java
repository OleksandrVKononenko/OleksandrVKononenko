 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.action; 

 
import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 29 ???. 2019 ?. 17:50:31 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : DeleteObjectsAction.java 
* 
*/ 
@SuppressWarnings("serial") 
public class ResetAction extends BaseAction { 

	public static final String [] CMDS = new String[] {"rst","reset","reset()","rst()"}; 
	 
	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public ResetAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public ResetAction() { 
		 
	} 
	 
	public ResetAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public static ResetAction getInstance() 
	{ 
		ResetAction cmp = new ResetAction(); 
		 
		return new ResetAction(cmp.getClass().getSimpleName()); 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

			gl.tracex(new Object(){},gl.sf("%s...%s",gl.sf("Action...%s",this.getValue(NAME)),gl.S_TRY)); 
			 
			return (Application.getRio().start()); 
	} 

	 
	public static void main(String[] args) { 
		 

	} 

} 
