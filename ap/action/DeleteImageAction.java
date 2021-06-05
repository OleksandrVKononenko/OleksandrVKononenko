 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.action; 

import java.util.Arrays; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 


public class DeleteImageAction extends BaseAction { 

	private static final long serialVersionUID = 7423352669557701236L; 
	 
	public static final String [] CMDS = new String[] {"dimg","delimg","delimg()","dimg()"}; 

	public DeleteImageAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public DeleteImageAction() { 
		 
	} 
	 
	public DeleteImageAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	 
	 
	public static DeleteImageAction getInstance() 
	{ 
		DeleteImageAction cmp = new DeleteImageAction(); 
		 
		return new DeleteImageAction(cmp.getClass().getSimpleName()); 
		 
	} 
	 
	 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
		if(owner.getImgo().getImage() != null) 
		{ 
			owner.getImgo().setImage(null); 
			 
			owner.repaint(); 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
				 
		} 
		 
			return true; 
	} 


	public static void main(String[] args) { 
		 

	} 

} 
