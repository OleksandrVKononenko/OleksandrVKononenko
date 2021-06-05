 
package ap.utils; 

import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.GridLayout; 

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Container; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import ap.global.*; 

public class DialogUtil { 

	public DialogUtil() { 
	} 


	public static Color get_color(Color color)
	{
		Color result = JColorChooser.showDialog(null, "Выберите цвет...",color);
		
		return result; 
				
	}
	 
	public static int doYesNo(String message) 
	{ 
		int response = JOptionPane.showConfirmDialog(null,message,"Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 
		 
		return response; 
		 
	} 
	 
	public static int doError(String message) 
	{ 
		int response = JOptionPane.showConfirmDialog(null,message,"Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE); 
		 
		return response; 
		 
	} 
	 
	public static int get_int (String caption,String edit) { 

		JTextField fld_z_order = new JTextField(0) { 
			public void addNotify() { 
				super.addNotify(); 
				requestFocus(); 
			} 
		}; 

		JPanel panel = new JPanel(new GridLayout(0, 1)); 

		panel.add(new JLabel(edit)); 

		panel.add(fld_z_order); 
		 
		fld_z_order.setText("[Please input number value]"); 

		fld_z_order.requestFocus(); 

		int result = JOptionPane.showConfirmDialog(null, panel, 
				caption, JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 

		if (result == JOptionPane.OK_OPTION) { 
			int return_value = gl.E_ERROR; 

			try { 
				return_value = Integer.parseInt(fld_z_order.getText().trim()); 
				 
			} catch (NumberFormatException e) { 

				 
			} 
			 
			return return_value; 
		} 
		return gl.E_ERROR; 
	} 
	 
	public static int get_combo_box(String caption,String edit,String [] result_item) { 

		 
		String [] items = {"1","2","3","4","5"}; 
		 
		JPanel panel = new JPanel(); 

		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); 
		 
		Font font = new Font("Tahoma",Font.PLAIN,12); 
		 
		JComboBox cmb = new JComboBox(items); 
		 
		cmb.setFont(font); 
		 
		cmb.setEditable(true); 
		 
		Dimension d = cmb.getPreferredSize(); 
				 
		cmb.setPreferredSize(new Dimension(80,d.height)); 
		 
		 
		//cmb.setAlignmentX(0); 
		 
		ActionListener lsnr = new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{ 
				JComboBox box = (JComboBox)e.getSource(); 
				 
				String item = (String)box.getSelectedItem(); 
				 
				result_item[gl.E_EMPTY] = item; 
						 
				gl.smn("Selected item : " + item); 
			} 
		}; 
		 
		cmb.addActionListener(lsnr); 
		 
		panel.add(cmb); 
		 
				 
		int result = JOptionPane.showConfirmDialog(null, panel, 
				caption, JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE); 

		if (result == JOptionPane.OK_OPTION) { 
			int return_value = gl.E_ERROR; 
			 
		} 
			 
			return result; 
			 
		} 
	 
	 
	public static void Test_get_int() 
	{ 
		gl.smn(String.format("Was input value : %05d",DialogUtil.get_int("Waiting for int value","Please input in value"))); 
	} 
	 
	public static void Test_get_combo() 
	{ 
		get_combo_box("1","2",new String[]{"","",""}); 
		 
	} 
	 
	 
	public static void Test_doYesNo() 
	{ 
		if(DialogUtil.doYesNo("Delete items..")==JOptionPane.OK_OPTION) 
			gl.smn("Ok"); 
		else 
			gl.smn("No"); 
		 
	} 
	public static void main(String[] args) { 
		 
		//Test_get_int(); 
		 
		Test_get_combo(); 

	} 

} 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
