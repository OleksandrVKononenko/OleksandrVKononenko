 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
package ap.menu; 

import java.awt.Font; 

import javax.swing.JMenu; 



public class SMenu extends JMenu { 

	 
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	private Font font = new Font("Tahoma",0,9); 
	 
	public Font getFont() { 
		return font; 
	} 

	public void setFont(Font font) { 
		this.font = font; 
	 
		super.setFont(font); 
	} 

	public SMenu() 
	{ 
		super(); 
		 
		this.setFont(this.getFont()); 
	} 
	 
	public SMenu(String value) 
	{ 
		super(value); 
		 
		this.setFont(this.getFont()); 
	} 
	 
		 
	public SMenu(String value, Font font ) 
	{ 
		super(value); 
		 
		this.setFont(font); 
	} 
	 
	 
	public void add(SMenuItem...items) 
	{ 
		 
		for(SMenuItem item : items) 
		{ 
			if(item==null) 
				super.addSeparator(); 
			else 
				super.add(item); 
		} 
	} 
	 
	public void add(SMenu...items) 
	{ 
		for(SMenu item : items) 
		{ 
			if(item==null) 
				super.addSeparator(); 
			else 
				super.add(item); 
		} 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
