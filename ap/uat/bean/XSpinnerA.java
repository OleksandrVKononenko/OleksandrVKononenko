 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XSpinnerA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private SpinnerImplA button ; 
	 
	 
	public SpinnerImplA getButton() { 
		return button; 
	} 

	public void setButton(SpinnerImplA button) { 
		this.button = button; 
	} 

	public XSpinnerA() { 
		addComponents(); 
	} 

	public XSpinnerA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	 
	public void addComponents() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("Spinner_%d",this.getIdo().getId())); 

		SpinnerImplA si = SpinnerImplA.get_instance(this,new String[] {"1","2","3"}); 
				 
		si.setName(this.getName()); 
		 
		this.setButton(si); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
		 
		this.add(this.getButton()); 
		 
		 
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
	 
	public static XSpinnerA get_instance(Rectangle rect) 
	{ 
	 
		return new XSpinnerA(rect); 
		 
	} 
	 
	public static XSpinnerA get_instance(Rectangle rect,int align) 
	{ 
	 
		XSpinnerA 	atom = XSpinnerA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
					 
					atom.setBorder(BorderFactory.createEmptyBorder(0,0,0,4)); 
				 
		 
		return atom; 
	 
	} 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 

