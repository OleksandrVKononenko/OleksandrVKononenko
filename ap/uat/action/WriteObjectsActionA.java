 
package ap.uat.action; 


import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.state.Fl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.CmdA; 
import ap.uat.CmdParamsA; 
import ap.utils.Bau; 
import ap.utils.Fu; 
import ap.utils.Su; 

@SuppressWarnings("serial") 
public class WriteObjectsActionA extends BaseActionA { 
	 
	public static final String [] CMDS = new String[] {"wr","write","wri","write()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/x","/f"}; 

	public WriteObjectsActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public WriteObjectsActionA() { 
	} 
	 
	public WriteObjectsActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 
	 
	public static WriteObjectsActionA getInstance() 
	{ 
		WriteObjectsActionA cmp = new WriteObjectsActionA(); 
		 
		return new WriteObjectsActionA(cmp.getClass().getSimpleName()); 
	} 
	 
	 
	@Override 
	public  boolean actionPerformed(AtOm owner) { 
		 
		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		try 
		{ 
		 
		String [] m_bad_params_list = {""}; 
		 
		CmdA fire_cmd = owner.getFire_cmd(); 
		 		 
		// Check params. 
		 
		if (!CmdParamsA.checkParams(fire_cmd,VALID_PARAMS,m_bad_params_list)) 
	    { 
			gl.tracex(new Object() {},gl.sf("%s...not valid params list...%s...%s", msg,m_bad_params_list[gl.E_EMPTY], gl.S_ERROR)); 
		 
			return false; 
	    } 
		 
		 
		// Prepare objects to save. 
			 
		AtOm 	desktop = ApplicationA.getDio().get_desk_top(); 
		 
				desktop.update_items(); 
		 
		AtOm.get_items().forEach(v->{ 
		{ 
		 
				if(v.getImgo().getImage() != null) 
				{ 
					 
					String image_text = Bau.to_hex_string_from_buffered_image(v.getImgo().getImage(),v.getImgo().getImageType()); 
					 
					v.getImgo().setImageText(image_text); 
					 
					gl.tracex(new Object(){},gl.sf("%s...%s...%d...%s",msg,"image size",v.getImgo().getImageText().length(),gl.S_OK)); 

				}}} 
		); 
			 
			 
			// Read default value. 
			String composite_file = ApplicationA.getCio().getHome(); 
						 
			String param = CmdParamsA.getParameters_map(fire_cmd).get(CmdParamsA.ARGS); 
			 
			if(CmdParamsA.isaParam(fire_cmd,"/f")) 
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
			 
			if(CmdParamsA.isaParam(fire_cmd,"/x")) 
			{ 
				if(!composite_file.toLowerCase().contains(".xml")) 
					composite_file = gl.sf("%s.%s",composite_file,"xml"); 
				 
			} 
		 
			ApplicationA.getCio().setHome(composite_file); 
			 
			// Save operation. 
			if(!ApplicationA.getRio().write() ) 
			{ 
				 gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_ERROR)); 
				 
				 return false; 
			} 
			 	 
				gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
				 
				ApplicationA.getDio().get_desk_top().get_items_by_state(Fl.VK_SELECTED).forEach(a->{					 
					 
					gl.sfn("Childs selected ...%s",a.getIdo().getName()); 
					 
				}); 
				
				// Clean.
				
				CleanDesktopA sba = CleanDesktopA.getInstance(); 
				 
				CmdA	cmd = CmdA.get_instance(desktop,gl.sf(".clean %s","")); 
				 
						desktop.setFire_cmd(cmd); 
				 
						sba.actionPerformed(desktop); 

				// Re - read.
						
				ReadObjectsActionA roa = ReadObjectsActionA.getInstance(); 
						 
				CmdA	roa_cmd = CmdA.get_instance(desktop,gl.sf(".read %s","")); 
						 
						desktop.setFire_cmd(roa_cmd); 
						 
						roa.actionPerformed(desktop); 
				
				return true; 
		 
	} 
	catch(Exception e) 
	{ 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,gl.S_ERROR,e.getMessage())); 
			 
				return false; 
	}	 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
