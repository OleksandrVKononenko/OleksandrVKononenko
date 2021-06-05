 
 
 
 
 
 
 
 
package ap.swing; 


import java.awt.Dimension; 
import javax.swing.BorderFactory; 
import ap.global.gl; 
import ap.utils.CU; 

public class ConfigurationImplObject implements IConfiguration { 

	private static final int MW = 2; 
	 
	private String 		home; 
	 
	private Dimension 	preffered_size; 
	 
	private int 		min_width_height; 
	 
	private String 		active_brew_object = "btn"; 
	 
		 
	public String getActive_brew_object() { 
		return active_brew_object; 
	} 

	public void setActive_brew_object(String active_brew_object) { 
		this.active_brew_object = active_brew_object; 
	} 

	public ConfigurationImplObject() { 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),"Default_home.xml")); 
		 
		this.setPrefferedSize(new Dimension(64,24)); 
		 
		this.setMinWidthHeight(MW); 
	} 
	 
	public ConfigurationImplObject(String home) { 
	 
		this(); 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),home)); 
	} 
	 
	public ConfigurationImplObject(String home,Dimension size) { 
		 
		this(home); 

		this.setPrefferedSize(size); 
	} 
	 
	 

	@Override 
	public void setHome(String home) { 
		 
		this.home = home; 

	} 

	@Override 
	public String getHome() { 
		 
		return this.home; 
	} 

	@Override 
	public void setPrefferedSize(Dimension size) { 
	 
		this.preffered_size = size; 
	} 

	 
	@Override 
	public Dimension getPrefferedSize() { 
	 
		return this.preffered_size; 
	} 

	 
	@Override 
	public void setMinWidthHeight(int mwh) { 
		 
		this.min_width_height = mwh; 
	} 

	 
	@Override 
	public int getMinWidthHeight() { 
		 
		return this.min_width_height; 
		 
	} 
	 
	public static ConfigurationImplObject get_instance() 
	{ 
		return new ConfigurationImplObject(); 
	} 

	public static ConfigurationImplObject get_instance(String home) 
	{ 
		return new ConfigurationImplObject(home); 
	} 
	 
	public static ConfigurationImplObject get_instance(String home,Dimension size) { 
		 
		return new ConfigurationImplObject(home,size); 
	} 

	 
	@Override 
	public boolean show_brew() { 
		 
		gl.tracex(new Object() {}, gl.sf("Active brew...[ %s ]...%s ",this.getActive_brew_object().toUpperCase(),gl.S_OK)); 
		 
		return true; 
	} 
	 
	 
	@Override 
	public boolean setConstructionMode(PanelXml owner) 
	{ 
		owner.setBackground(CU.getAlphaColor(owner.getBackground(),30)); 
		 
		owner.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); 
					 
		 
		return true; 
		 
	} 
	 
	 
} 
