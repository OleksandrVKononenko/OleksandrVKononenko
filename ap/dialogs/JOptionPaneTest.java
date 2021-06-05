 
package ap.dialogs; 

import java.awt.EventQueue; 
import java.awt.GridLayout; 
import javax.swing.*; 


public class JOptionPaneTest { 

private static void display() { 
String[] items = {"One", "Two", "Three", "Four", "Five"}; 
JComboBox combo = new JComboBox(items); 
JTextField field1 = new JTextField("1234.56"); 
JTextField field2 = new JTextField("9876.54"); 
JPanel panel = new JPanel(new GridLayout(0, 1)); 
panel.add(combo); 
panel.add(new JLabel("Field 1:")); 
panel.add(field1); 
panel.add(new JLabel("Field 2:")); 
panel.add(field2); 
int result = JOptionPane.showConfirmDialog(null, panel, "Test", 
JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
if (result == JOptionPane.OK_OPTION) { 
System.out.println(combo.getSelectedItem() 
+ " " + field1.getText() 
+ " " + field2.getText()); 
} else { 
System.out.println("Cancelled"); 
} 
} 

public static void main(String[] args) { 
EventQueue.invokeLater(new Runnable() { 

@Override 
public void run() { 
display(); 
} 
}); 
} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
