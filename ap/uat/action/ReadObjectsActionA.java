 
/** 
* 
*/ 
package ap.uat.action; 


import java.util.Arrays; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
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
public class ReadObjectsActionA extends BaseActionA { 


	public static final String [] CMDS = new String[] {"read","read()","re()"}; 
	 
		 
	public ReadObjectsActionA() { 
				 
	} 
	 
	public ReadObjectsActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public ReadObjectsActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
	} 
	 

	public static ReadObjectsActionA getInstance() 
	{ 
		ReadObjectsActionA cmp = new ReadObjectsActionA(); 
		 
		return new ReadObjectsActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		try 
		 
		{ 
		 
		boolean [] bl_result = {false}; 
		 
		gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_TRY)); 
		 
		// Setup flag in ON construction process. 
		 
	 
		if(ApplicationA.getRio().read()) 
			{ 
				 
				AtOm 	desktop = ApplicationA.getDio().get_desk_top(); 
				 
						desktop.update_items(); 
				 
				AtOm.get_items().forEach(v->{ 
				{ 
					 
					//gl.sfn("---> Item...%s", v.getIdo().getName()); 
										 
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
					 
					} // for image. 
					 
				  } //for each cycle. 
				} 
				); 
					 
				 
						ApplicationA.getFio().get_frame().revalidate(); 
			} 
						 
			 
						return true; 
		} 
						catch(Exception e) 
						{ 
								gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,gl.S_ERROR,e.getMessage())); 
								 
								return false; 
						}	 
						 
	} 

	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
	} 

} 
