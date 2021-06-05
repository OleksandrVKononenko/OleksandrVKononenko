package ap.orion.impl;
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import ap.orion.app.Application;
import ap.orion.intf.IFrame; 

public class FrameImpl implements IFrame { 

	private JFrame frame; 
	 
	private Rectangle original_bounds; 

	public Rectangle getOriginal_bounds() { 
		return original_bounds; 
	} 

	public void setOriginal_bounds(Rectangle original_bounds) { 
		this.original_bounds = original_bounds; 
	} 

	public FrameImpl(String caption) { 

		initUI(caption);
	} 
	 

private void initUI(String caption) {
	
	frame = new JFrame(caption); 
	
	frame.setLayout(new BorderLayout());
	 
	frame.getContentPane().setBackground(Color.gray); 
	 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 
	frame.setPreferredSize(Application.getCio().getPrefferedSize()); 
	
	frame.setSize(Application.getCio().getStartup_size());
	
	//frame.getContentPane().setLayout(new BorderLayout()); 
					 
	// frame.pack(); 
	 
	frame.setLocationRelativeTo(null); 
	
	frame.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent me) {

        	frame.requestFocus();
    
           // m_list.forEach(a->{ a.repaint();});
            
        }

                  
    });

	 
	frame.setVisible(true); 
	 
	 
}

private void initUI_() {
	
	frame = new JFrame("");     

	frame.setLayout(null);
    	
	frame.setTitle("Resizable component");
        
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	frame.getContentPane().setBackground(Color.darkGray);
       
	frame.setSize(new Dimension(320,240));
		
		//pack();
		
	frame.setLocationRelativeTo(null);
		
	frame.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent me) {

	            	frame.requestFocus();
	        
	               // m_list.forEach(a->{ a.repaint();});
	                
	            }

	                      
	        });
	    
	frame.setVisible(true); 	
        
    }
	public FrameImpl(String caption,Dimension preffered) { 
		 
		this(caption); 
		 
		this.get_frame().setPreferredSize(preffered); 
	} 
	 
	 

	public static FrameImpl get_instance(String caption) 
	{ 		 
		return new FrameImpl(caption); 
	} 
	 
	public static FrameImpl get_instance(String caption,Dimension preffered) 
	{ 		 
		return new FrameImpl(caption,preffered); 
	} 
	 
	 
	@Override 
	public JFrame get_frame() { 
		 
		return this.frame; 
	} 
 
	 

} 