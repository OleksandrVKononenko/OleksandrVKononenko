 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.utils; 

import java.awt.EventQueue; 
import java.awt.GridLayout; 

import javax.swing.*; 

import ap.global.gl; 

public class DlgInteger { 

	public static String getString(String caption) { 

		JTextField fldString = new JTextField(""); 

		JPanel panel = new JPanel(new GridLayout(0, 1)); 

		panel.add(new JLabel(caption)); 

		fldString.requestFocus(); 

		panel.add(fldString); 

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Get string value ...", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 

		if (result == JOptionPane.OK_OPTION) { 
			return fldString.getText().trim(); 
		} else 
			return "None"; 
	} 
	 
	public static int getInt(String caption,String default_value) 
	{ 
		String s_value = getString(caption,default_value); 
		 
		try 
		{ 
			int i_value = Integer.parseInt(s_value.trim()); 
			 
			return i_value; 
		} 
		catch(NumberFormatException e ) 
		{ 
			return gl.E_ERROR; 
		} 
		 
	} 

	public static String getString(String caption,String default_value) { 

		JTextField fldString = new JTextField(default_value); 

		JPanel panel = new JPanel(new GridLayout(0, 1)); 

		panel.add(new JLabel(caption)); 

		fldString.requestFocus(); 

		panel.add(fldString); 

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Get string value ...", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 

		//if (result == JOptionPane.OK_OPTION || result == JOptionPane.CANCEL_OPTION ) { 
			return fldString.getText().trim(); 
		//} 
	} 
	 
	public static void main(String[] args) { 

		  EventQueue.invokeLater(new Runnable() { 
		 
		  @Override public void run() { gl.smn("Result : " + getString("Please input string value :","default_value")); } 
		  }); 
		 

	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
