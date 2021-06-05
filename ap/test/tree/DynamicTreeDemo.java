package ap.test.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DynamicTreeDemo extends JPanel 
                             implements ActionListener {
    
	private int newNodeSuffix = 1;
    
    private static String 	ADD_COMMAND 		= "add";
    
    private static String 	REMOVE_COMMAND 		= "remove";
    
    private static String 	CLEAR_COMMAND 		= "clear";
     
    private DynamicTree 	treePanel;
 
    public DynamicTreeDemo() {
    	
        super(new BorderLayout());
         
        //Create the components.
        treePanel = new DynamicTree();
        
        populateTree(treePanel);
 
        JButton addButton = new JButton("Add");
        
        addButton.setActionCommand(ADD_COMMAND);
        
        addButton.addActionListener(this);
         
        JButton removeButton = new JButton("Remove");
        
        removeButton.setActionCommand(REMOVE_COMMAND);
        
        removeButton.addActionListener(this);
         
        JButton clearButton = new JButton("Clear");
        
        clearButton.setActionCommand(CLEAR_COMMAND);
        
        clearButton.addActionListener(this);
 
        //Lay everything out.
        treePanel.setPreferredSize(new Dimension(300, 150));
        
        add(treePanel, BorderLayout.CENTER);
 
        JPanel panel = new JPanel(new GridLayout(0,3));
        
        panel.add(addButton);
        
        panel.add(removeButton); 
        
        panel.add(clearButton);
        
        add(panel, BorderLayout.SOUTH);
    
    }
 
    public void populateTree(DynamicTree treePanel) {
    	
    	treePanel.init();
    	
    }
     
    public void actionPerformed(ActionEvent e) {
    	
        	String command = e.getActionCommand();
         
        if (ADD_COMMAND.equals(command)) {
            
        	//Add button clicked
            
        	treePanel.addObject("New Node " + newNodeSuffix++);
            
        } else if (REMOVE_COMMAND.equals(command)) {
        	
            //Remove button clicked
            
        	treePanel.removeCurrentNode();
            
        } else if (CLEAR_COMMAND.equals(command)) {
        	
            //Clear button clicked.
        	
        	treePanel.clear();
        }
    }
 
    
    private static void createAndShowGUI() {
        //Create and set up the window.
    	
        JFrame frame = new JFrame("DynamicTreeDemo");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        
        DynamicTreeDemo newContentPane = new DynamicTreeDemo();
        
        newContentPane.setOpaque(true); //content panes must be opaque
        
        frame.setContentPane(newContentPane);
 
        //Display the window.
        
        frame.pack();
        
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
    	
        //creating and showing this application's GUI.
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                createAndShowGUI();
            }
        });
    }
}