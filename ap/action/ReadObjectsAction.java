 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.action; 


import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.Bau; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 29 ???. 2019 ?. 17:43:59 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : ReadObjectsAction.java 
* 
*/ 
@SuppressWarnings("serial") 
public class ReadObjectsAction extends BaseAction { 


	public static final String [] CMDS = new String[] {"read","read()","re()"}; 
	 
		 
	public ReadObjectsAction() { 
				 
	} 
	 
	public ReadObjectsAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ReadObjectsAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 
	 

	public static ReadObjectsAction getInstance() 
	{ 
		ReadObjectsAction cmp = new ReadObjectsAction(); 
		 
		return new ReadObjectsAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
		boolean [] bl_result = {false}; 
		 
		gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_TRY)); 
		 
		// Setup flag in ON construction process. 
		 
		PanelXml.bl_skip_child_accept = true; 
	 
			if(Application.getRio().read()) 
			{ 
				PanelXml.items.forEach((k,v)-> 
				{ 
					if(v.getImgo().getImageText() != null) 
					{ 
							 
						byte [] ba = Bau.to_byte_array_from_hex_str(v.getImgo().getImageText()); 
						 
						if( ba != null) 
						{ 
							gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,"load to ba",gl.S_OK)); 
							 
							bl_result[gl.E_EMPTY] = true; 
						} 
						else 
						{ 
							gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,"load to ba",gl.S_ERROR)); 
							 
						} 
						 
						if (bl_result[gl.E_EMPTY] == true) 
						{ 
							v.getImgo().setImage(Bau.to_buf_img_from_byte_array(ba)); 
							 
							v.repaint(); 
							 
							gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,"set buffered image for ",v.getName())); 
						} 
					 
					} 
					 
				  } 
				);			 
				 
						Application.getFio().getFrame().revalidate(); 
			} 
						 
			 
						return true; 
	} 

	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
	} 

} 
