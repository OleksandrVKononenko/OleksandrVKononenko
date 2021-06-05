 
/** 
* 
*/ 
package ap.uat; 


import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import javax.swing.JScrollPane; 
import ap.global.gl; 
import ap.shape.Ru;


public class DesktopImplObjectA implements IDesktopA { 

	private AtOm desktop; 
	 
	public DesktopImplObjectA() { 
		 
	} 

	 
	public void add_attrs() 
	{ 
	 
		String msg = "Setup Desktop attributes."; 
	 
	try { 
		 
		 
		desktop.setBackground(Color.DARK_GRAY); 
		 
		desktop.setBackground(desktop.getBackground()); 
		 

		desktop.setName("Desktop"); 

		desktop.getStio().set_deny_drag(true); 
		 
		desktop.getStio().set_skip_text(true); 

		desktop.getStio().set_draw_selector(true); 

		desktop.getDecoro().setAreaManager(null); 

		desktop.setToolTipText(null); 

		gl.tracex(new Object() {}, gl.sf("%s...%s", msg, gl.S_OK)); 

	} catch (Exception e) { 

		gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,e.toString(), gl.S_OK)); 

	} 
	 
	} 

	 
	public DesktopImplObjectA(Rectangle rect ) { 
		 
			desktop = AtOm.get_instance(rect); 
			 
			add_attrs(); 
	} 
	 
	 
	 

	
	public DesktopImplObjectA(Dimension size) { 
			 
			desktop = AtOm.get_instance(Ru.norm_dim_to_rect(size)); 
			 
			add_attrs(); 
			 
		
			JScrollPane sp = new JScrollPane(); 
	
			sp.setViewportView(desktop); 
			
			
			
			ApplicationA.fio.get_frame().add(sp,BorderLayout.CENTER); 
			 
			
	} 
	 
	 
	public static DesktopImplObjectA get_instance(Rectangle rect) { 
		 
			return new DesktopImplObjectA(rect); 
	} 
	 
	public static DesktopImplObjectA get_instance(Dimension size) { 
		 
		return new DesktopImplObjectA(size); 
	} 

	 
	@Override 
	public AtOm get_desk_top() { 
		 
			return this.desktop; 
	} 

	@Override 
	public void set_desk_top(AtOm desktop) { 
		 
			this.desktop = desktop; 
		 
	} 
	 
	 

} 
