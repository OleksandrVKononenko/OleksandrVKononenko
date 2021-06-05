 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.SwingUtilities; 
import ap.global.gl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 

public class XLabelA extends AtOm /* implements MouseWheelListener */ { 

	private static final long serialVersionUID = 1L; 

	private LabelImplA button; 

	public LabelImplA getButton() { 
		return button; 
	} 

	public void setButton(LabelImplA button) { 
		this.button = button; 
	} 

	public XLabelA() { 
		addComponents(); 
	} 

	public XLabelA(Rectangle rect) { 
		super(rect); 

		this.addMouseListener(this); 

		addComponents(); 

	} 

	 

	public void addComponents() { 
		ApplicationA.cio.setConstructionMode(this); 

		this.setLayout(new BorderLayout()); 

		this.setName(gl.sf("Label_%d", this.getIdo().getId())); 

		this.setButton(new LabelImplA(this.getName())); 

		this.getButton().setOwner(this); 

		this.add(BorderLayout.CENTER, this.getButton()); 

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

	/* 
	 * @Override public void mouseWheelMoved(MouseWheelEvent e) { 
	 * 
	 * super.mouseWheelMoved(e); 
	 * 
	 * gl.smn(gl.sf("*Class...%s...wheel event...%3d",this.getClass(). 
	 * getSimpleName(),gl.getRandomInt(999))); 
	 * 
	 * } 
	 * 
	 */ 

	public static XLabelA get_instance(Rectangle rect) 
	{ 
		return new XLabelA(rect); 
	} 
	 
	public static XLabelA get_instance(Rectangle rect,int align) 
	{ 
	 
		XLabelA 	atom = XLabelA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
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
