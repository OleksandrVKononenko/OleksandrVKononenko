 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.menu; 

import java.awt.Font; 
import java.awt.event.ActionListener; 

import javax.swing.JMenuItem; 




public class SMenuItem extends JMenuItem { 

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
	 
	 
	public SMenuItem() 
	{ 
		super(); 
		 
		this.setFont(this.getFont()); 
	} 
	 
	public SMenuItem(String value) 
	{ 
		super(value); 
		 
		this.setFont(this.getFont()); 
		 
	} 
	 
	public SMenuItem(String value,Font font) 
	{ 
	 
		this(value); 
		 
		this.setFont(font); 
		 
	} 
	 
	public SMenuItem(String value,String actionCommand,ActionListener listener) 
	{ 
		this(value); 
		 
		this.setActionCommand(actionCommand); 
		 
		this.addActionListener(listener); 
		 
	} 
	 
	public static void main(String[] args) { 
	 

	} 

} 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
