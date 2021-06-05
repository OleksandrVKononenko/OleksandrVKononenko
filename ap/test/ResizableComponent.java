package ap.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ap.global.gl;

public class ResizableComponent extends JFrame {

    private Resizable res;

    public ResizableComponent() {

        initUI();
    }

    private void initUI() {

       JPanel pnl = new JPanel(null);
        
        //final JScrollPane scroll = new JScrollPane(null);

   		//scroll.setViewportView(pnl); 
   
        
        add(pnl);

        JTextField area = new JTextField();
        
        //area.setBackground(Color.white);

        //var area = new JCheckBox();
        
        res = new Resizable(area);
        
        res.setBounds(50, 50, 200, 150);
        
        pnl.add(res);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                requestFocus();
                res.repaint();
            }

                      
        });

        setSize(350, 300);
        
        //setPreferredSize(new Dimension(100, 100));
        
        //revalidate();
        
        
        setTitle("Resizable component");
        
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

        	ResizableComponent ex = new ResizableComponent();
            
        	ex.setVisible(true);
        });
    }
}