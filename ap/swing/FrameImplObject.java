 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Rectangle; 
import javax.swing.JFrame; 

public class FrameImplObject implements IFrame { 

	private JFrame frame; 
	 
	private Rectangle original_bounds; 

	public Rectangle getOriginal_bounds() { 
		return original_bounds; 
	} 

	public void setOriginal_bounds(Rectangle original_bounds) { 
		this.original_bounds = original_bounds; 
	} 

	public FrameImplObject(String caption) { 
		 
		frame = new JFrame(caption); 
		 
		frame.getContentPane().setBackground(Color.gray); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 
		frame.setLocationRelativeTo(null); 
		 
		frame.setVisible(true); 
		 
	} 

	public static FrameImplObject get_instance() 
	{ 
		String ca = "Main App"; 
				 
		return new FrameImplObject(ca); 
	} 
	 
	@Override 
	public JFrame getFrame() { 
		 
		return this.frame; 
	} 
	 
	 

} 
