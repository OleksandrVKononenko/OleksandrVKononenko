 
package ap.action; 


import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.Bau; 
import ap.utils.Fu; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class WriteObjectsAction extends BaseAction { 
	 
	public static final String [] CMDS = new String[] {"wr","write","wri","write()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/x","/f"}; 

	public WriteObjectsAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public WriteObjectsAction() { 
	} 
	 
	public WriteObjectsAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 
	 
	public static WriteObjectsAction getInstance() 
	{ 
		WriteObjectsAction cmp = new WriteObjectsAction(); 
		 
		return new WriteObjectsAction(cmp.getClass().getSimpleName()); 
	} 
	 
	 
	@Override 
	public  boolean actionPerformed(PanelXml owner) { 
		 
		String msg = gl.sf("Action...%s",this.getActionName()); 
		 
		String [] m_bad_params_list = {""}; 
		 
		Cmd fire_cmd = owner.getFire_cmd(); 
		 		 
		// Check params. 
		 
		if (!CmdParams.checkParams(fire_cmd,VALID_PARAMS,m_bad_params_list)) 
	    { 
			gl.tracex(new Object() {},gl.sf("%s...not valid params list...%s...%s", msg,m_bad_params_list[gl.E_EMPTY], gl.S_ERROR)); 
		 
			return false; 
	    } 
		 
		 
		// Prepare objects to save. 
			PanelXml.items.forEach((k,v)-> 
			{ 
				if(v.getImgo().getImage() != null) 
				{ 
					 
					String image_text = Bau.to_hex_string_from_buffered_image(v.getImgo().getImage(),v.getImgo().getImageType()); 
					 
					v.getImgo().setImageText(image_text); 
					 
					gl.tracex(new Object(){},gl.sf("%s...%s...%d...%s",msg,"image size",v.getImgo().getImageText().length(),gl.S_OK)); 

				} 
			  } 
			); 
			 
			 
			// Read default value. 
			String composite_file = Application.getCio().getHome(); 
						 
			String param = CmdParams.getParameters_map(fire_cmd).get(CmdParams.ARGS); 
			 
			if(CmdParams.isaParam(fire_cmd,"/f")) 
			{ 
			 
				if( param.startsWith(".")) 
				{ 
					// Relative path. 
					 
					composite_file = gl.sf("%s%s",Fu.getCurrentDir(),Su.AfterAtFirst(param,".")); 
					 
				}else if(param.contains(":\\")) 
				{ 
					// Absolute. 
					composite_file = gl.sf("%s",param); 
						 
				} 
			} // Force case. 
			 			 
			 
			// Wake xml extension if not exists yet. 
			 
			if(CmdParams.isaParam(fire_cmd,"/x")) 
			{ 
				if(!composite_file.toLowerCase().contains(".xml")) 
					composite_file = gl.sf("%s.%s",composite_file,"xml"); 
				 
			} 
		 
			Application.getCio().setHome(composite_file); 
			 
			// Save operation. 
			if(!Application.getRio().write() ) 
			{ 
				 gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_ERROR)); 
				 
				 return false; 
			} 
			 	 
				PanelXml.bl_skip_child_accept = true; 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
				 
				 
				return true; 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
