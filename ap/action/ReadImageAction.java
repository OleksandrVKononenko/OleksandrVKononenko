 
 
 
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
import ap.utils.Fu; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 30 ???. 2019 ?. 17:17:27 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : LoadImageAction.java 
* 
*/ 
@SuppressWarnings("serial") 
public class ReadImageAction extends BaseAction { 

	public static final List<String> CMDS = Arrays.asList(new String[] {"rimg","readimg","rimg()","readimg()"}); 
	 
	public static final List<String> VALID_PARAMS = Arrays.asList(new String[] {"/c","/clear","/null"}); 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public ReadImageAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public ReadImageAction(String text) { 
		super(text,CMDS); 
		 
	} 
	 
	public ReadImageAction() { 
			 
	} 

	public static ReadImageAction getInstance() 
	{ 
		ReadImageAction cmp = new ReadImageAction(); 
		 
		return new ReadImageAction(cmp.getClass().getSimpleName()); 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
		String [] m_bad_params_list = {""}; 
		 
		Cmd cmd = owner.getFire_cmd(); 
		 		 
		// Check params. 
		 
		if (!CmdParams.checkParams(cmd,VALID_PARAMS,m_bad_params_list)) 
	    { 
			gl.tracex(new Object() {},gl.sf("%s...not valid params list...%s...%s", msg,m_bad_params_list[gl.E_EMPTY], gl.S_ERROR)); 
		 
			return false; 
	    } 
		 
		int m_args_count = CmdParams.getArgsCount(cmd); 
		 
		int m_params_count = CmdParams.getParamsCount(cmd); 
		 
		if (m_params_count != gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
			 
			if (CmdParams.isaParams(cmd,VALID_PARAMS)) 
			{ 
				// Activate reset image. 
			 
				gl.tracex(new Object(){},gl.sf("%s...Activate reset of an image...%s",msg,gl.S_OK)); 
				 
				return owner.getImgo().clear() && owner.rerepaint(); 
				 
			} 
			 
			 
		} 
		 
		 
		 
		 
		 
		if (!Fu.import_image_dlg(owner,false,Application.getCio().getHome())) 
		{ 
				gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_ERROR)); 
				 
				return false; 
		} 
			 
			   gl.tracex(new Object(){},gl.sf("%s...image is...%s...%s",msg,(owner.getImgo().getImage() != null),gl.S_OK)); 
			 
			   return true; 
			 
	} 

	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
