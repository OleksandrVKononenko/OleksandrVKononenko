package ap.uat.bean;


import java.awt.BorderLayout; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.JScrollPane; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA;
import ap.uat.AtOm; 


public class XTextAreaA extends AtOm  { 

	private static final long serialVersionUID = 1L; 
	 
	private TextAreaImpl button ; 
	 
	private JScrollPane sp ; 
	 
	public TextAreaImpl getButton() { 
		return button; 
	} 

	public void setButton(TextAreaImpl button) { 
		this.button = button; 
	} 

	public XTextAreaA() { 
		addComponents(); 
	} 

	public XTextAreaA(Rectangle rect) { 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	 
	public void addComponents() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("TextArea_%d",this.getIdo().getId())); 
			 
		this.setButton(new TextAreaImpl(this.getName())); 
		 
		this.getButton().setEditable(true); 
		 
		this.getButton().setOwner(this); 
		 
		sp = new JScrollPane(this.getButton()); 
		 
		this.add(BorderLayout.CENTER,sp); 
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
	
	public static XTextAreaA get_instance(Rectangle rect) 
	{ 
		return new XTextAreaA(rect); 
	} 
	
	
	public static XTextAreaA get_instance(Rectangle rect,int align) 
	{ 
	 
		XTextAreaA 	atom = XTextAreaA.get_instance(rect); 
	 
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

