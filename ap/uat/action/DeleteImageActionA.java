 
 
 
package ap.uat.action; 

import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 


public class DeleteImageActionA extends BaseActionA { 

	private static final long serialVersionUID = 77L; 
	 
	public static final String [] CMDS = new String[] {"imgdel","imgd","imgdel()","imgd()"}; 

	public DeleteImageActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public DeleteImageActionA() { 
		 
	} 
	 
	public DeleteImageActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	 
	public static DeleteImageActionA getInstance() 
	{ 
		DeleteImageActionA cmp = new DeleteImageActionA(); 
		 
		return new DeleteImageActionA(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		try 
		{ 
			if(owner.getImgo().getImage() != null) 
			{ 
				owner.getImgo().setImage(null); 
				 
				owner.repaint(); 
				 
				gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
					 
			} 
			 
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
