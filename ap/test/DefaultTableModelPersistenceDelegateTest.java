package ap.test;


import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class DefaultTableModelPersistenceDelegateTest
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
                try
                {
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                    
                    XMLEncoder 	xe = new XMLEncoder(os);
                    
                    			xe.setPersistenceDelegate(
                    					DefaultTableModel.class, 
                    					new DefaultTableModelPersistenceDelegate2());
                    
                    			xe.writeObject(model);
                    
                    			xe.close();

                    Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                   
                    textArea.read(r, null);
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
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
        
        		f.getContentPane().add(new DefaultTableModelPersistenceDelegateTest().makeUI());
        
        		f.setSize(420, 340);
        
        		f.setLocationRelativeTo(null);
        
        		f.setVisible(true);
    }
}

//  See following link for more information on Using XMLEncoder:
//  http://www.oracle.com/technetwork/java/persistence4-140124.html

class DefaultTableModelPersistenceDelegate extends DefaultPersistenceDelegate
{
    //  Initially creates an empty DefaultTableModel. The columns are created
    //  and finally each row of data is added to the model.

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder encoder)
    {
        DefaultTableModel model= (DefaultTableModel)oldInstance;

        //  Create XML to restore the column names

        Vector<String> columnNames = new Vector<String>(model.getColumnCount());

        for (int i = 0; i < model.getColumnCount(); i++)
        {
            columnNames.add( model.getColumnName(i) );
        }

        Object[] columnNamesData = new Object[] { columnNames };
        
        
        
        encoder.writeStatement(new Statement(oldInstance, "setColumnIdentifiers", columnNamesData));

        //  Create XML to restore row data

        Vector row = model.getDataVector();

        for (int i = 0; i < model.getRowCount(); i++)
        {
            Object[] rowData = new Object[] { row.get(i) };
            
            encoder.writeStatement(new Statement(oldInstance, "addRow", rowData));
        }
    }
}

class DefaultTableModelPersistenceDelegate2 extends DefaultPersistenceDelegate
{
    //  Initially creates a DefaultTableModel with rows and columns. Then the
    //  columns are reset and proper names are used. Finally data is set for each
    //  cell in the model.

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder encoder)
    {
        super.initialize(type, oldInstance, newInstance, encoder);

        DefaultTableModel model= (DefaultTableModel)oldInstance;

        //  Create XML to restore the column names

        Vector<String> columnNames = new Vector<String>(model.getColumnCount());

        for (int i = 0; i < model.getColumnCount(); i++)
        {
            columnNames.add( model.getColumnName(i) );
        }

        Object[] columnNamesData = new Object[] { columnNames };
        
        encoder.writeStatement(new Statement(oldInstance, "setColumnIdentifiers", columnNamesData));

        //  Create XML to reset the value of every cell to its value

        for (int row = 0; row < model.getRowCount(); row++)
        {
            for (int col = 0; col < model.getColumnCount(); col++)
            {
                Object[] o = new Object[] {model.getValueAt(row, col), row, col};
                
                encoder.writeStatement(new Statement(oldInstance, "setValueAt", o));
            }
        }
    }
}
