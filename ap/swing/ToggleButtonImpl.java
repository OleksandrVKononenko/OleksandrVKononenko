 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 

import javax.swing.Action; 
import javax.swing.Icon; 
import javax.swing.JToggleButton; 

import ap.utils.GU; 



public class ToggleButtonImpl extends JToggleButton  implements MouseListener,MouseMotionListener,ActionListener{ 

	private static final long serialVersionUID = 1L; 
	 
	private XToggleButton proxy; 
	 
	public XToggleButton getProxy() { 
		return proxy; 
	} 

	public void setOwner(XToggleButton owner) { 
		this.proxy = owner; 
	} 

	public ToggleButtonImpl() { 
		 
	} 

	public ToggleButtonImpl(Icon icon) { 
		super(icon); 
		 
	} 

	public ToggleButtonImpl(String text) { 
		super(text); 
		 
		setupListeners() ; 
	} 
	 
	public void setupListeners() 
	{ 
	 
		this.addMouseListener(this); 
	 
		this.addMouseMotionListener(this); 
		 
		this.addActionListener(this); 
		 
	} 
	 
	public ToggleButtonImpl(Action a) { 
		super(a); 
		 
	} 

	public ToggleButtonImpl(String text, Icon icon) { 
		super(text, icon); 
		 
	} 

	public ToggleButtonImpl getInstance(XToggleButton owner,String text) 
	{ 
		this.setOwner(owner); 
		 
		return new ToggleButtonImpl(text); 
	} 
	 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
		 
		this.getProxy().mouseDragged(e); 
		 
	} 

	@Override 
	public void mouseMoved(MouseEvent e) { 
		 
		this.getProxy().mouseMoved(e); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		this.getProxy().mouseClicked(e); 
		 
		this.getProxy().repaint_childs(); 
	 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		this.getProxy().mousePressed(e); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		this.getProxy().mouseReleased(e); 
		 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		this.getProxy().mouseEntered(e); 
		 
		this.getProxy().repaint_childs(); 
		 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		this.getProxy().mouseExited(e); 
		 
		this.getProxy().repaint_childs(); 
	} 

	 
	@Override 
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
				 
		if (this.isSelected()) 
		{ 
			GU.draw_push_back(g,this.getBounds(),Color.white,0.35f); 
		} 
		 
			this.getProxy().repaint_childs(); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
		JToggleButton tBtn = (JToggleButton)e.getSource(); 
 
		if (tBtn.isSelected()) { 
			System.out.println("Selected ON"); 
			} else { 
			System.out.println("Selected OFF"); 
			} 
		 
	} 

	 
	 
} 
