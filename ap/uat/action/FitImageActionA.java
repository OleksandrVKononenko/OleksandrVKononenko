 
 
 
/** 
* 
*/ 
package ap.uat.action; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.Arrays; 

import javax.swing.ImageIcon; 

import ap.global.gl; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 



/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 02 ????. 2019 ?. 14:40:04 
* Project  name : Organizer 
* Package  name : ap.action 
* File     name : ImageFitAction.java 
* 
*/ 
public class FitImageActionA extends BaseActionA { 

	private static final long serialVersionUID = -03L; 
	 
	public static final String [] CMDS = new String[] {"imgfit","imgf","imgfit()","imgf()"}; 
	 
	public FitImageActionA(String text, ImageIcon icon, String desc, 
			Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	/** 
	 * @param text 
	 */ 
	public FitImageActionA() { 
			 
	} 
	public FitImageActionA(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 
	public static FitImageActionA getInstance() 
	{ 
		FitImageActionA cmp = new FitImageActionA(); 
		 
		return new FitImageActionA(cmp.getClass().getSimpleName()); 
	} 
	 
		 
	@Override 
	public boolean actionPerformed(AtOm owner) { 

		String msg = gl.sf("Action...%s...owner...%s",this.getValue(NAME),owner.getIdo().getName()); 
		 
		try 
		{ 
		 
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
		catch(Exception e) 
		{ 
				gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,gl.S_ERROR,e.getMessage())); 
			 
				return false; 
		} 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
