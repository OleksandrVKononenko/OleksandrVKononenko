package ap.orion.lsnr;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

import ap.global.gl;

public class OrionTreeModelListener implements TreeModelListener {
	
    public void treeNodesChanged(TreeModelEvent e) {
    	
        DefaultMutableTreeNode node;
        
        node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());

        try
        {
        	int index = e.getChildIndices()[0];
            
        	node = (DefaultMutableTreeNode)(node.getChildAt(index));
        }
        catch(NullPointerException ex)
        {
        	gl.tx_e(new Object() {}, gl.sf("Редактирование корневого узла"));
        }
        	gl.tx_k(new Object() {}, gl.sf("Новое значение ноды...%s",node.getUserObject()));
        
        
    }
    
    public void treeNodesInserted(TreeModelEvent e) {
    }
    
    public void treeNodesRemoved(TreeModelEvent e) {
    }
    
    public void treeStructureChanged(TreeModelEvent e) {
    }
}
