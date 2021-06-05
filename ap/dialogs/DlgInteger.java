 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.dialogs; 

import java.awt.EventQueue; 
import java.awt.GridLayout; 
import java.awt.Panel; 

import javax.swing.*; 

import ap.global.gl; 

public class DlgInteger { 

	public static int getInteger() { 

		JTextField fld_z_order = new JTextField(0) { 
			public void addNotify() { 
				super.addNotify(); 
				requestFocus(); 
			} 
		}; 

		JPanel panel = new JPanel(new GridLayout(0, 1)); 

		panel.add(new JLabel("Please input Z-order value [0-255]:")); 

		panel.add(fld_z_order); 

		fld_z_order.requestFocus(); 

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Get integer value ...", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 

		if (result == JOptionPane.OK_OPTION) { 
			int r_value = gl.E_ERROR; 

			try { 
				r_value = Integer.parseInt(fld_z_order.getText().trim()); 
			} catch (NumberFormatException e) { 

			} 
			return r_value; 
		} 
		return gl.E_ERROR; 
	} 

	private static String getString() { 

		JTextField fldString = new JTextField(""); 

		JPanel panel = new JPanel(new GridLayout(0, 1)); 

		panel.add(new JLabel("Please input string value :")); 

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

	public static void main(String[] args) { 

		new DlgInteger().getInteger(); 
		/* 
		 * EventQueue.invokeLater(new Runnable() { 
		 * 
		 * @Override public void run() { gl.smn("Result : " + getInteger()); } 
		 * }); 
		 */ 
		/* 
		JPanel panel = new JPanel(); 
		 
		panel.setLayout(null); 
		 
		 
		 
		int result = JOptionPane.showConfirmDialog(null, new Panel(), 
				"??? ?? ????? ????? ? ?? ???? ...", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 
		*/ 

	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
