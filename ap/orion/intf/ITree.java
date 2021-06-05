package ap.orion.intf;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public interface ITree {
	
default	
public void clear(DefaultMutableTreeNode 	rootNode, DefaultTreeModel treeModel ) {
    	
        rootNode.removeAllChildren();
        
        treeModel.reload();
    }

default
public void removeCurrentNode(JTree tree,DefaultTreeModel treeModel) {
	
    TreePath currentSelection = tree.getSelectionPath();
    
    if (currentSelection != null) {
    	
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
                     (currentSelection.getLastPathComponent());
        
        MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
        
        if (parent != null) {
        	
            treeModel.removeNodeFromParent(currentNode);
            
            return;
        }
    } 
}


default 
public DefaultMutableTreeNode addObject(JTree tree,DefaultMutableTreeNode 	rootNode,DefaultTreeModel treeModel,Object child) {
	
    DefaultMutableTreeNode parentNode = null;
    
    TreePath parentPath = tree.getSelectionPath();
    
    if (parentPath == null) {
    	
        parentNode = rootNode;
        
    } else {
        parentNode = (DefaultMutableTreeNode)
                     (parentPath.getLastPathComponent());
    }

    return addObject(tree,parentNode, child,rootNode,treeModel, true);
}

default
public DefaultMutableTreeNode addObject(JTree tree,DefaultMutableTreeNode parent,
        Object child, DefaultMutableTreeNode 	rootNode,DefaultTreeModel treeModel,
        boolean shouldBeVisible) {

	DefaultMutableTreeNode childNode = 
				new DefaultMutableTreeNode(child);

	if (parent == null) {
		parent = rootNode;
	}

	//It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		treeModel.insertNodeInto(childNode, parent,parent.getChildCount());

		//Make sure the user can see the lovely new node.
	if (shouldBeVisible) {
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	}
		return childNode;
	}

default
public DefaultMutableTreeNode addObject(JTree tree,DefaultMutableTreeNode parent,
        Object child,DefaultMutableTreeNode rootNode,DefaultTreeModel treeModel) {
	
		return addObject(tree,parent, child,rootNode,treeModel, false);
	}

}


