 
/** 
* 
*/ 
package ap.action; 

 
import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.PanelXml; 


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
public class ApplicationCloseAction extends BaseAction { 
	 
	public static final String [] CMDS = new String[] {"exit","quit","stop","escape","close()","exit()"}; 

	public ApplicationCloseAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ApplicationCloseAction() { 
		 
	} 

	public ApplicationCloseAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 

	 
	public static ApplicationCloseAction getInstance() 
	{ 
		ApplicationCloseAction cmp = new ApplicationCloseAction(); 
		 
		return new ApplicationCloseAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
		 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
		gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
		 
		//WriteObjectsAction.getInstance().actionPerformed(owner); 
		 
		System.exit(gl.E_EMPTY); 
		 
		return true; 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
