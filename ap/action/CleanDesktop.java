 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.swing.RoutineImplObject; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 30 ????. 2019 ?. 17:35:03 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : CleanDesktop.java 
* 
*/ 
@SuppressWarnings("serial") 
public class CleanDesktop extends BaseAction { 

	public static final String [] CMDS = new String[] {"cln","clean","clean()","cln()"}; 
	 
	/** 
	 * 
	 */ 
	public CleanDesktop() { 
		 
	} 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public CleanDesktop(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public CleanDesktop(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	/** 
	 * @param text 
	 * @param cmd_list 
	 */ 
	public CleanDesktop(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 

	public static CleanDesktop getInstance() 
	{ 
		CleanDesktop cmp = new CleanDesktop(); 
		 
		return new CleanDesktop(cmp.getClass().getSimpleName()); 
	} 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

			RoutineImplObject r = Application.getRio(); 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s",gl.sf("Action...%s",this.getValue(NAME)),gl.S_TRY)); 
			 
			return (r.aclean() && r.refresh()); 
	} 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
