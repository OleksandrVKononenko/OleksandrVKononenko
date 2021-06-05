 
 
 
 
 
 
 
 
 
 
 
 
package ap.test; 

import java.awt.BorderLayout; 
import java.awt.GridLayout; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.ScrollPaneConstants; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 

public class SPTest { 


	  public static void main(String[] a) { 
	    JFrame f = new JFrame(); 
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    f.add(new JScrollPaneDemo()); 
	    f.setSize(500, 500); 
	    f.setVisible(true); 
	  } 

} 

class JScrollPaneDemo extends JPanel { 

	public JScrollPaneDemo() 
	{ 
		init(); 
	} 
	 
	  public void init() { 
	    try { 
	      SwingUtilities.invokeLater(new Runnable() { 
	        public void run() { 
	          makeGUI(); 
	        } 
	      }); 
	    } catch (Exception exc) { 
	      System.out.println("Can't create because of " + exc); 
	    } 
	  } 

	  private void makeGUI() { 

		gl.smn("Start"); 
		 
		int COUNT = 5; 
		 
	    setLayout(new BorderLayout()); 
		 
	    JPanel jp = new JPanel(); 
	 
	    jp.setLayout(new GridLayout(COUNT,COUNT)); 
	 
	    int b = 0; 
	 
	    for (int i = 0; i < COUNT; i++) { 
	 
	    	for (int j = 0; j < COUNT; j++) { 
	        jp.add(new JButton("Button " + b)); 
	        ++b; 
	      } 
	    } 

	    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED; 
	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED; 
	    JScrollPane jsp = new JScrollPane(jp, v, h); 

	    add(jsp, BorderLayout.CENTER); 
	  } 
	} 
