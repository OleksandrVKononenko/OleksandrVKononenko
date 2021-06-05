package ap.uat.bean; 


import java.awt.BorderLayout; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 


public class XTextFieldA extends AtOm { 

	private static final long serialVersionUID = 1L; 
	 
	private TextFieldImplA button ; 
	 
	 
	public TextFieldImplA getButton() { 
		return button; 
	} 

	public void setButton(TextFieldImplA button) { 
		this.button = button; 
	} 

	public XTextFieldA() { 
		addComponents(); 
	} 

	public XTextFieldA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

		 
	public void addComponents() 
	{ 

		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TextField_%d",this.getIdo().getId())); 
			 
		this.setButton(new TextFieldImplA(this.getName())); 
		 
		this.getButton().setOwner(this); 
		 
		this.add(BorderLayout.CENTER,this.getButton()); 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
	 
		super.mouseEntered(e); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getButton().setToolTipText(this.toString()); 
		 
	} 
	public static XTextFieldA get_instance(Rectangle rect) 
	{ 
		return new XTextFieldA(rect); 
	} 
	 
	public static XTextFieldA get_instance(Rectangle rect,int align) 
	{ 
	 
		XTextFieldA 	atom = XTextFieldA.get_instance(rect); 
	 
						atom.setBackground(gl.getRandomColor()); 
	 
						atom.getStio().set_deny_drag(false); 
				 
						atom.getDecoro().set_align(align); 
		 
						atom.getStio().set_move_bottom_right(true); 
				 
		 
				return	atom; 
	 
	} 

	 
	 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	} 

} 

