package ap.orion.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.intf.IDesktop;
import ap.shape.Ru;


public class DesktopImpl implements IDesktop { 

	private Orion desktop; 
	 
	public DesktopImpl() { 
		 
	} 

	public void setup_rights()
	{
		
		String msg = "Setup desktop rights.";
		
		desktop.getRights().set_dragable(false);
		
		desktop.getRights().set_focusable(false);
		
		desktop.getRights().set_deletable(false);
		
		desktop.getRights().set_selectable(false);
		
		desktop.getState().set_skip_component_resized(true);
			

		gl.tx_k(new Object() {}, gl.sf("%s", msg)); 
		
		
	}
	
	public void setup_attrs() 
	{ 
	 
		String msg = "Setup desktop attributes."; 
	 
	try { 
		 
		 
		desktop.setLayout(null);
		
		desktop.setBackground(Color.darkGray); 
	
		desktop.setName("Desktop"); 
			
		desktop.getDecoro().setAreaManager(null); 

		desktop.setToolTipText(null); 
		
		gl.tx_k(new Object() {}, gl.sf("%s", msg)); 

	} catch (Exception e) { 

		gl.tx_k(new Object() {}, gl.sf("%s...%s", msg,e.toString())); 

	} 
	 
	} 

	 
	public DesktopImpl(JComponent comp,Rectangle rect ) { 
		 
			desktop = Orion.get_instance(comp,rect); 
			 
			this.setup_attrs();
			
			this.setup_rights();
	} 
	 
	 
	public DesktopImpl(JComponent comp,Dimension size) { 
			 
			desktop = Orion.get_instance(comp,Ru.norm_dim_to_rect(size)); 
			 
			comp.setBackground(Color.darkGray);
			
			setup_attrs(); 
			
			setup_rights();
			
			JScrollPane sp = new JScrollPane(desktop);
			
			desktop.setPreferredSize(Application.getCio().getPrefferedSize());
			
			Application.fio.get_frame().add(sp,BorderLayout.CENTER); 
			 
			
	} 
	 
	 
	public static DesktopImpl get_instance(JComponent comp,Rectangle rect) { 
		 
			return new DesktopImpl(comp,rect); 
	} 
	 
	public static DesktopImpl get_instance(JComponent comp,Dimension size) { 
		 
		return new DesktopImpl(comp,size); 
	} 

	 
	@Override 
	public Orion get_desk_top() { 
		 
			return this.desktop; 
	} 

	@Override 
	public void set_desk_top(Orion desktop) { 
		 
			this.desktop = desktop; 
		 
	} 
	 
	 

} 