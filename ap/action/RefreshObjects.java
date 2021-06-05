 
 
 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 

@SuppressWarnings("serial") 
public class RefreshObjects extends BaseAction { 

	public static final String [] CMDS = new String[] {"update","refresh","update()","refresh()"}; 
	 
	public RefreshObjects() { 
		 
	} 

	public RefreshObjects(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 
	 
	public RefreshObjects(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public RefreshObjects(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 

	public static RefreshObjects getInstance() 
	{ 
		RefreshObjects cmp = new RefreshObjects(); 
		 
		return new RefreshObjects(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

			String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
			gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_TRY)); 
			 
			return Application.getRio().refresh(); 
	} 

	 
	public static void main(String[] args) { 
		 

	} 

} 
