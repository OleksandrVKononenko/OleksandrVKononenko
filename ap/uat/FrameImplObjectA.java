 
package ap.uat; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import javax.swing.JFrame; 

public class FrameImplObjectA implements IFrameA { 

	private JFrame frame; 
	 
	private Rectangle original_bounds; 

	public Rectangle getOriginal_bounds() { 
		return original_bounds; 
	} 

	public void setOriginal_bounds(Rectangle original_bounds) { 
		this.original_bounds = original_bounds; 
	} 

	public FrameImplObjectA(String caption) { 
		 
		frame = new JFrame(caption); 
		 
		frame.getContentPane().setBackground(Color.gray); 
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 
		frame.setPreferredSize(ApplicationA.getCio().getPrefferedSize()); 
		
		frame.getContentPane().setLayout(new BorderLayout()); 
						 
		frame.pack(); 
		 
		frame.setLocationRelativeTo(null); 
		 
		frame.setVisible(true); 
		 
	} 
	 
	public FrameImplObjectA(String caption,Dimension preffered) { 
		 
		this(caption); 
		 
		this.get_frame().setPreferredSize(preffered); 
	} 
	 
	 

	public static FrameImplObjectA get_instance(String caption) 
	{ 		 
		return new FrameImplObjectA(caption); 
	} 
	 
	public static FrameImplObjectA get_instance(String caption,Dimension preffered) 
	{ 		 
		return new FrameImplObjectA(caption,preffered); 
	} 
	 
	 
	@Override 
	public JFrame get_frame() { 
		 
		return this.frame; 
	} 
 
	 

} 
