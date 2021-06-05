 
package ap.uat.bean; 

import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.util.Hashtable; 
import java.util.Vector; 

import javax.swing.JTree; 
import javax.swing.tree.TreeModel; 
import javax.swing.tree.TreeNode; 


@SuppressWarnings("serial") 
public class TreeImplA extends JTree implements MouseListener,MouseMotionListener { 

	private XTreeA proxy; 
	 
	public XTreeA getProxy() { 
		return proxy; 
	} 

	public void setOwner(XTreeA owner) { 
		this.proxy = owner; 
	} 
	 
	public TreeImplA() { 
		 
		setupListeners(); 
		 
	} 

	public TreeImplA(Object[] value) { 
		super(value); 
		 
	} 

	public TreeImplA(Vector<?> value) { 
		super(value); 
		 
	} 

	public TreeImplA(Hashtable<?, ?> value) { 
		super(value); 
		 
	} 

	public TreeImplA(TreeNode root) { 
		super(root); 
		 
	} 

	public TreeImplA(TreeModel newModel) { 
		super(newModel); 
		 
	} 

	public TreeImplA(TreeNode root, boolean asksAllowsChildren) { 
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
