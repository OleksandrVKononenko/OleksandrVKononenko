 
 
 
 
/** 
* 
*/ 
package ap.uat.action; 

 
import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.PanelXml; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 02 ????. 2019 ?. 16:40:51 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : ApplicationCloseAction.java 
* 
*/ 
@SuppressWarnings("serial") 
public class ApplicationCloseActionA extends BaseActionA { 
	 
	public static final String [] CMDS = new String[] {"exit","quit","stop","esc","quit()","close()","exit()"}; 

	public ApplicationCloseActionA(String text, ImageIcon icon, String desc,Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ApplicationCloseActionA() { 
		 
	} 

	public ApplicationCloseActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 

	 
	public static ApplicationCloseActionA getInstance() 
	{ 
		ApplicationCloseActionA cmp = new ApplicationCloseActionA(); 
		 
		return new ApplicationCloseActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
		 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s",this.getActionName()); 
		 
		gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
		 
		//WriteObjectsAction.getInstance().actionPerformed(owner); 
		 
		System.exit(gl.E_EMPTY); 
		 
		return true; 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
