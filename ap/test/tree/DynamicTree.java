package ap.test.tree;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import ap.orion.intf.ITree;
import ap.orion.lsnr.OrionTreeModelListener;

 
@SuppressWarnings("serial")
public class DynamicTree extends JPanel implements ITree{
	
	
    protected DefaultMutableTreeNode 	rootNode;
    
    protected DefaultTreeModel 			treeModel;
    
    protected JTree 					tree;
   
    public DynamicTree() {
    	
        super(new GridLayout(1,0));
         
        rootNode = new DefaultMutableTreeNode("Root Node");
        
        treeModel = new DefaultTreeModel(rootNode);
        
        treeModel.addTreeModelListener(new OrionTreeModelListener());
    
        tree = new JTree(treeModel);
        
        tree.setEditable(true);
        
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tree.setShowsRootHandles(true);
 
        JScrollPane scrollPane = new JScrollPane(tree);
        
        add(scrollPane);
    }
 
   
    public void clear()
    {
    	clear(rootNode,treeModel);
    }
 
    public void removeCurrentNode() {
    	
    	removeCurrentNode(tree,treeModel);
    }
   
   
    public DefaultMutableTreeNode addObject(Object child) {
    
    	return addObject(tree,rootNode,treeModel,child) ;
    	
    }
    
 	
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child) {
       
    	return addObject(tree,parent, child,rootNode,treeModel, false);
    }
 
    public void init()
    {
    	String p1Name = new String("Parent 1");
        
        String p2Name = new String("Parent 2");
        
        String c1Name = new String("Child 1");
        
        String c2Name = new String("Child 2");
 
        
        DefaultMutableTreeNode p1, p2;
 
        p1 = 	this.addObject(null, p1Name);
        
        p2 = 	this.addObject(null, p2Name);
 
        		this.addObject(p1, c1Name);
        
        		this.addObject(p1, c2Name);
 
        		this.addObject(p2, c1Name);
        
        		this.addObject(p2, c2Name);
    }
}