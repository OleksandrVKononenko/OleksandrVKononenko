 
 
 
 
 
 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 

import ap.global.gl; 

public class DesktopImplObject implements IDesktop { 

	private PanelXml desktop; 
	 
	public DesktopImplObject() { 
		 
	} 

	 
	public void addAttributes() 
	{ 
	 
		String msg = "Setup Desktop attributes."; 
	 
	try { 
		 
		 
		desktop.setBackground(Color.DARK_GRAY); 

		desktop.setName("Desktop"); 

		desktop.setDenyDrag(true); 

		desktop.setDrawSelector(true); 

		//desktop.setLayout(new BorderLayout()); 
		 
		desktop.getDecoro().setAreaManager(null); 

		desktop.setToolTipText(null); 

		gl.tracex(new Object() {}, gl.sf("%s...%s", msg, gl.S_OK)); 

	} catch (Exception e) { 

		gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,e.toString(), gl.S_OK)); 

	} 
	 
	} 

	public DesktopImplObject(Rectangle rect ) { 
		 
			desktop = new PanelXml(rect); 
			 
			addAttributes(); 
	} 
	 
	public DesktopImplObject(Dimension size) { 
			 
			desktop = new PanelXml(size); 
			 
			addAttributes(); 
	} 
		 
	public static DesktopImplObject get_instance(Dimension size) { 
		 
			return new DesktopImplObject(size); 
	} 
	 
	public static DesktopImplObject get_instance(Rectangle rect) { 
		 
			return new DesktopImplObject(rect); 
	} 

	@Override 
	public PanelXml getDeskTop() { 
		 
			return this.desktop; 
	} 

	@Override 
	public void setDeskTop(PanelXml desktop) { 
		 
			this.desktop = desktop; 
		 
	} 
	 
	 

} 
