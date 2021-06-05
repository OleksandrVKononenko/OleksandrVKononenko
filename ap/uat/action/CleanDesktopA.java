 
 
 
 
/** 
* 
*/ 
package ap.uat.action; 

import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.swing.RoutineImplObject; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.RoutineImplObjectA; 

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
public class CleanDesktopA extends BaseActionA { 

	public static final String [] CMDS = new String[] {"cln","clean","clean()","cln()"}; 
	 
	/** 
	 * 
	 */ 
	public CleanDesktopA() { 
		 
	} 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public CleanDesktopA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public CleanDesktopA(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	/** 
	 * @param text 
	 * @param cmd_list 
	 */ 
	public CleanDesktopA(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 

	public static CleanDesktopA getInstance() 
	{ 
		CleanDesktopA cmp = new CleanDesktopA(); 
		 
		return new CleanDesktopA(cmp.getClass().getSimpleName()); 
	} 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

			RoutineImplObjectA r = ApplicationA.getRio(); 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s",gl.sf("Action...%s",this.getActionName()),gl.S_TRY)); 
			 
			return r.aclean() ; 
	} 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
