package ap.test;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ap.ray.gl;
import ap.swing.IAlign;
import ap.test.layouts.DragLayout;
import ap.uat.ApplicationA;
import ap.uat.AtOm;
import ap.uat.DesktopImplObjectA;

public class Scroller1 extends JFrame {

       public Scroller1() throws HeadlessException {
           
    	   AtOm panel = AtOm.get_instance(new Rectangle(0,0,800,600));
    	   
       			panel.setBorder(BorderFactory.createLineBorder(Color.red));
           
    	   		panel.setPreferredSize(new Dimension(800, 600));
    	   		
    	   		panel.getStio().set_deny_drag(true);
    	   		
    	   		// Desktop.
    	   		panel.getIdo().setPid(gl.E_ERROR);
    	   		
    	   		ApplicationA.setDio(DesktopImplObjectA.get_instance(panel.getBounds()));

    	   		ApplicationA.getDio().set_desk_top(panel);
    	   		
    	   AtOm at = AtOm.get_instance(new Rectangle(0,0,100,50),IAlign.indexOf("BASE_ZOOM_ALL"));
    	   
    	   		at.getIdo().setOwner(panel);
    	   		           		
    	   		
    	   AtOm at1 = AtOm.get_instance(new Rectangle(0,50,100,50),IAlign.indexOf("BASE_ZOOM_ALL"));
    	    	   
    	   		at1.getIdo().setOwner(panel);
    	   		
           
    	   		panel.insert(at1,at);
           
    	   
           final JScrollPane scroll = new JScrollPane();

           		scroll.setViewportView(panel); 
           
           		//scroll.setAutoscrolls(true);
           		
           	 scroll.getViewport().addChangeListener(
           		   new ChangeListener() {
           		   
					@Override
					public void stateChanged(ChangeEvent e) {
						
						 JScrollBar sv = scroll.getVerticalScrollBar();
						 
						 JScrollBar sh = scroll.getHorizontalScrollBar();
	           		        //sb.setValue(sb.getMaximum());
						 //sb.setValue(sb.getMinimum());
							//sv.updateUI();
							
							//sh.updateUI();
					}});
           		
           		
           		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
           		setLayout(new BorderLayout());
           
           		add(scroll, BorderLayout.CENTER);
           
           		setSize(850,650);
           
           		setVisible(true);
       }

       public static void main(final String[] args) throws Exception {
           SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                   new Scroller1().setVisible(true);
               }
           });
       }
}
