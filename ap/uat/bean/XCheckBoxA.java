 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XCheckBoxA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private CheckBoxImplA button ; 
	 
	 
	public CheckBoxImplA getButton() { 
		return button; 
	} 

	public void setButton(CheckBoxImplA button) { 
		this.button = button; 
	} 

	public XCheckBoxA() { 
		add_components(); 
	} 

	public XCheckBoxA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		add_components(); 
		 
	} 

	 
	 
	public void add_components() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("CheckBox_%d",this.getIdo().getId())); 
		 
		this.setButton(new CheckBoxImplA(this.getName())); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getButton().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getButton().setToolTipText(this.toString()); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 
	 
	public static XCheckBoxA get_instance(Rectangle rect) 
	{ 
		return new XCheckBoxA(rect); 
	} 
	 
	public static XCheckBoxA get_instance(Rectangle rect,int align) 
	{ 
	 
		XCheckBoxA 	atom = XCheckBoxA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 


} 
