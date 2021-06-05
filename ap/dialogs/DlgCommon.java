 
 
package ap.dialogs; 

import java.awt.Dimension; 

import javax.swing.*; 

public class DlgCommon extends JPanel { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea txt = new JTextArea() { 
		public void addNotify() { 
			super.addNotify(); 
			requestFocus(); 
		} 
	}; 

	public DlgCommon() { 
		setLayout(null); 

		setPreferredSize(new Dimension(200, 100)); 

		txt.setBounds(20, 20, 220, 20); 

		add(txt); 

	} 

	void display() { 

		Object[] options = { this }; 

		JOptionPane pane = new JOptionPane(); 

		pane.showOptionDialog(null, null, "Title", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, txt); 

	} 

	public static void main(String[] args) { 
		new DlgCommon().display(); 
	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
