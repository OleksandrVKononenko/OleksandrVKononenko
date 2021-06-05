 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.test; 

import java.awt.BorderLayout; 
import java.awt.Dimension; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 


public class SomeTest { 

	 
	public SomeTest() { 
		 
	} 

	 
	public static void main(String[] args) { 
	 
JFrame frame = new JFrame(); 
 
JPanel panel = new JPanel(); 
 
for (int i = 0; i < 10; i++) { 
 
	panel.add(new JButton("Hello-" + i)); 
 
panel.setBounds(i*10,i*10,30,10); 
 
} 
 
JScrollPane scrollPane = new JScrollPane(panel); 
 
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
 
//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
 
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
 
scrollPane.setBounds(50, 30, 100,100); 
 
 
JPanel contentPane = new JPanel(new BorderLayout()); 
 
//contentPane.setPreferredSize(new Dimension(500, 400)); 
 
contentPane.add(scrollPane,BorderLayout.CENTER); 
 
frame.setContentPane(contentPane); 
 
frame.pack(); 
 
 
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
 
frame.setVisible(true); 


	} 

} 
