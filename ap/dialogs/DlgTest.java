 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.dialogs; 

import java.awt.Dimension; 

import javax.swing.*; 

public class DlgTest extends JPanel { 
	private JTextArea txt1 = new JTextArea(); 
	private JTextArea txt2 = new JTextArea() { 
		public void addNotify() { 
			super.addNotify(); 
			requestFocus(); 
		} 
	}; 

	public DlgTest() { 
		setLayout(null); 
		setPreferredSize(new Dimension(200, 100)); 
		txt1.setBounds(20, 20, 220, 20); 
		txt2.setBounds(20, 45, 220, 20); 
		txt1.setText("Text Field #1"); 
		txt2.setText("Text Field #2"); 
		add(txt1); 
		add(txt2); 
	} 

	void display() { 
		Object[] options = { this }; 
		JOptionPane pane = new JOptionPane(); 
		pane.showOptionDialog(null, null, "Title", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, txt2); 
	} 
	 
	void display_1() 
	{ 
		Object[] options = { this }; 
		 
		JOptionPane pane = new JOptionPane(); 

		pane.showOptionDialog(null, null, "Title", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, "txt"); 
	} 

	public static void main(String[] args) { 
		new DlgTest().display_1(); 
	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
