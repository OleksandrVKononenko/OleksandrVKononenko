 
 
package ap.swing; 

import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Hashtable; 
import java.util.Vector; 

import javax.swing.JTree; 
import javax.swing.tree.TreeModel; 
import javax.swing.tree.TreeNode; 


@SuppressWarnings("serial") 
public class TreeImpl extends JTree implements MouseListener,MouseMotionListener { 

	private XTree proxy; 
	 
	public XTree getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTree owner) { 
		this.proxy = owner; 
	} 
	 
	public TreeImpl() { 
		 
		setupListeners(); 
		 
	} 

	public TreeImpl(Object[] value) { 
		super(value); 
		 
	} 

	public TreeImpl(Vector<?> value) { 
		super(value); 
		 
	} 

	public TreeImpl(Hashtable<?, ?> value) { 
		super(value); 
		 
	} 

	public TreeImpl(TreeNode root) { 
		super(root); 
		 
	} 

	public TreeImpl(TreeModel newModel) { 
		super(newModel); 
		 
	} 

	public TreeImpl(TreeNode root, boolean asksAllowsChildren) { 
		super(root, asksAllowsChildren); 
		 
	} 

	 
	 
	 
	public static void main(String[] args) { 
		 

	} 

	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseDragged(e); 
		else 
			this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 

		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseMoved(e); 
		else 
			this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseClicked(e); 
		else 
			this.getProxy().mouseClicked(e); 
 
				 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mousePressed(e); 
		else 
			this.getProxy().mousePressed(e); 
 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseReleased(e); 
		else 
			this.getProxy().mouseReleased(e); 
 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseEntered(e); 
		else 
			this.getProxy().mouseEntered(e); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 

		if (this.getProxy().getScroll_pane() != null) 
			this.getProxy().getScroll_pane().mouseExited(e); 
		else 
			this.getProxy().mouseExited(e); 
	} 

	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
	} 
	 
	 
	 

} 
