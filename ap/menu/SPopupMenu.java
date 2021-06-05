 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.menu; 

import javax.swing.JMenuItem; 
import javax.swing.JPopupMenu; 

import ap.global.gl; 




public class SPopupMenu extends JPopupMenu { 

	 
	private static final long serialVersionUID = 1L; 
	 
	int countSeparator; 
	 
	public SPopupMenu() 
	{ 
		super(); 
	} 
	 
	public SPopupMenu(String value) 
	{ 
		super(value); 
	} 
	 
	public JMenuItem add(SMenuItem item) 
	{ 
		if(item==null) 
		{ 
			super.addSeparator(); 
			return null; 
		} 
		else 
		return super.add(item); 
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
			{ 
				countSeparator++; 
				 
				if(countSeparator>gl.E_OK) 
				super.addSeparator(); 
				 
			} 
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
