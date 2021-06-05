package ap.orion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import ap.global.gl;
import ap.prop.SBounds;

public class OrionTest extends JFrame {

    List<Orion> m_list = new ArrayList<Orion>(); 
    
    public OrionTest() {

        initUI();
        
        m_list.add(Orion.get_instance(new JPanel(),new Rectangle(50,50,200,50)));
        					   
        m_list.add(Orion.get_instance(new JButton(),new Rectangle(50,100,200,50)));
        							   
        m_list.add(Orion.get_instance(new JTextField(),new Rectangle(50,150,200,50)));
        
        m_list.add(Orion.get_instance(new JTextArea(),new Rectangle(50,200,200,50)));
        
        m_list.add(Orion.get_instance(new JCheckBox(),new Rectangle(50,250,200,50)));
        
        m_list.add(Orion.get_instance(new JRadioButton(),new Rectangle(50,300,200,50)));
        
        m_list.add(Orion.get_instance(new JComboBox(),new Rectangle(50,350,200,50)));
        
        m_list.add(Orion.get_instance(new JLabel("Label"),new Rectangle(50,400,200,50)));
        
        m_list.add(Orion.get_instance(new JList(),new Rectangle(50,450,200,50)));
        
        m_list.add(Orion.get_instance(new JTree(),new Rectangle(50,500,200,50)));
        
        
        m_list.forEach(a->{ getContentPane().add(a);});
        
    }

    private void initUI() {
        

    	setLayout(null);
    	
    	setTitle("Resizable component");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.darkGray);
       
		setSize(new Dimension(320,240));
		
		//pack();
		
		setLocationRelativeTo(null);
		
		   addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent me) {

	                requestFocus();
	        
	                m_list.forEach(a->{ a.repaint();});
	                
	            }

	                      
	        });
	    
		
        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

        	OrionTest ex = new OrionTest();
            ex.setVisible(true);
        });
    }
}