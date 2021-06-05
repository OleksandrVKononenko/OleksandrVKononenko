 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.action; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.Arrays; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 



/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 02 ????. 2019 ?. 14:40:04 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : ImageFitAction.java 
* 
*/ 
public class FitImageAction extends BaseAction { 

	private static final long serialVersionUID = -5918554530895751603L; 
	 
	public static final String [] CMDS = new String[] {"fimg","fitimg","fimg()","fitimg()"}; 
	 
	/** 
	 * @param text 
	 * @param icon 
	 * @param desc 
	 * @param mnemonic 
	 */ 
	public FitImageAction(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public FitImageAction() { 
			 
	} 
	public FitImageAction(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	public static FitImageAction getInstance() 
	{ 
		FitImageAction cmp = new FitImageAction(); 
		 
		return new FitImageAction(cmp.getClass().getSimpleName()); 
	} 
	 
		 
	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s",this.getValue(NAME)); 
		 
		if(owner.getImgo().getImage() != null) 
		{ 
			Rectangle rect = owner.getBounds(); 
			 
			Dimension dim = new Dimension(owner.getImgo().getImage().getWidth(),owner.getImgo().getImage().getHeight()); 
				 
			rect.width  = dim.width; 
				 
			rect.height = dim.height; 
				 
			owner.setBounds(rect); 
			 
			owner.repaint(); 
			 
			gl.tracex(new Object(){},gl.sf("%s...%s",msg,gl.S_OK)); 
				 
		} 
		 
			return true; 
	} 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
