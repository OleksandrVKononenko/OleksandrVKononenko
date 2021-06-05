 
 
 
 
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
import ap.utils.Su; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 02 ????. 2019 ?. 17:24:52 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : CompositeOpenAction.java 
* 
*/ 
@SuppressWarnings("serial") 
public class CompositeOpenAction extends BaseAction { 

	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	 
	public static final String [] CMDS = new String[] {"open","open"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/x","/f"}; 
	 
	public CompositeOpenAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public CompositeOpenAction() { 
				 
	} 
	 
	public CompositeOpenAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	 
	public static CompositeOpenAction getInstance() 
	{ 
		CompositeOpenAction cmp = new CompositeOpenAction(); 
		 
		return new CompositeOpenAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
		 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		String [] NOT_VALID_PARAMS = {""}; 
		 
		Cmd fire_cmd = owner.getFire_cmd(); 
				 
		try 
		{ 
		if (!CmdParams.checkParams(fire_cmd,VALID_PARAMS,NOT_VALID_PARAMS)) 
	    { 
			gl.tracex(new Object() {},gl.sf("Action...%s...not valid params list...%s...%s", this.getActionName(),NOT_VALID_PARAMS[gl.E_EMPTY], gl.S_ERROR)); 
		 
			return false; 
	    } 

		// Accept params influence. 
		 
		String [] composite_file = {""}; 
	 	 
		String args = CmdParams.getParameters_map(fire_cmd).get("a000"); 
		 
		// Check force. 
		if (CmdParams.isaParam(fire_cmd,"/f")) 
		{ 
			if( args.startsWith(".")) 
			{ 
				// Relative path. 
				 
				composite_file[gl.E_EMPTY] = gl.sf("%s%s",Fu.getCurrentDir(),Su.AfterAtFirst(args,".")); 
				 
			}else if(args.contains(":\\")) 
			{ 
				// Absolute. 
				composite_file[gl.E_EMPTY] = gl.sf("%s",args); 
					 
			} 
		} 
		else 
		{ 
			composite_file[gl.E_EMPTY] = Fu.file_open_dlg(Application.getCio().getHome()); 
		} 
		 
		// Check xml. 
		if (CmdParams.isaParam(fire_cmd,"/x")) 
		{ 
			if(!Fu.isaExtension(composite_file[gl.E_EMPTY])) 
				{ 
						composite_file[gl.E_EMPTY] = gl.sf("%s.%s",composite_file[gl.E_EMPTY],"xml"); 
				} 
		} 
		 
		if(!Fu.isaFile(composite_file[gl.E_EMPTY])) 
		{ 
			 
			gl.tracex(new Object() {}, gl.sf("%s...File not exists...%s", msg,composite_file[gl.E_EMPTY])); 
			 
			return false; 
		} 
		 
		Application.getCio().setHome(composite_file[gl.E_EMPTY]); 
		 
		boolean bl_result = ReadObjectsAction.getInstance().actionPerformed(owner); 
		 
		gl.tracex(new Object() {}, gl.sf("%s...Choose composite file...%s...items...%d...%s", msg, Application.getCio().getHome(),owner.getItems().size(), 
				bl_result ? gl.S_OK : gl.S_ERROR)); 
		 
		return bl_result; 
		 
		 
		} 
		catch(Exception e) 
		{ 
			gl.tracex(new Object() {},gl.sf("Action...%s...%s...%s",this.getActionName(),e.toString(), gl.S_ERROR)); 

			return false; 
		} 

	} 


	public static void main(String[] args) { 
		 

	} 

} 
