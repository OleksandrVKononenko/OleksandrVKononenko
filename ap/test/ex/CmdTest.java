package ap.test.ex;


import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class CmdTest
{
    private File file = new File("TableModel.xml");
    
    private final JTextArea textArea = new JTextArea();

    private final String[] columnNames = {"Column1", "Column2"};

    private final Object[][] data =
    {
        {"aaa", new Integer(1)},
        {"bbb\u2600", new Integer(2)}
    };

    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    
    private final JTable table = new JTable(model);

    public JComponent makeUI()
    {
        model.setColumnCount(5);
        
        JSplitPane 	sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        			sp.setResizeWeight(.3);
        
        			table.setAutoCreateRowSorter(true);
        
        			sp.setTopComponent(new JScrollPane(table));
        
        			sp.setBottomComponent(new JScrollPane(textArea));

        JPanel 		p = new JPanel();
        
        p.add(new JButton(new AbstractAction("XMLEncoder")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
               
            }
        }));

        p.add(new JButton(new AbstractAction("XMLDecoder")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    InputStream is = new BufferedInputStream( new FileInputStream( file ));
                    
                    XMLDecoder xd = new XMLDecoder(is);
                    
                    model = (DefaultTableModel)xd.readObject();
                    
                    table.setModel(model);
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }));

        p.add(new JButton(new AbstractAction("clear")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                	model = new DefaultTableModel();
                
                	table.setModel(model);
                	
                
            }
        }));

        JPanel 		pnl = new JPanel(new BorderLayout());
        
        			pnl.add(sp);
        
        			pnl.add(p, BorderLayout.SOUTH);
        
        			return pnl;
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                	createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI()
    {
        JFrame 	f = new JFrame();
        
        		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        		f.getContentPane().add(new CmdTest().makeUI());
        
        		f.setSize(420, 340);
        
        		f.setLocationRelativeTo(null);
        
        		f.setVisible(true);
    }
}

//  See following link for more information on Using XMLEncoder:
//  http://www.oracle.com/technetwork/java/persistence4-140124.html




