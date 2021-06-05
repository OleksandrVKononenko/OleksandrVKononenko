package ap.orion.impl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener; 
import java.util.LinkedHashMap; 
import java.util.Map;
import javax.swing.BorderFactory;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.intf.IConfiguration;
import ap.uat.bean.XButtonA; 
import ap.uat.bean.XCheckBoxA; 
import ap.uat.bean.XComboBoxA; 
import ap.uat.bean.XLabelA; 
import ap.uat.bean.XListA; 
import ap.uat.bean.XRadioButtonA; 
import ap.uat.bean.XSliderA; 
import ap.uat.bean.XSpinnerA; 
import ap.uat.bean.XTabbedPaneA; 
import ap.uat.bean.XTableA;
import ap.uat.bean.XTextAreaA;
import ap.uat.bean.XTextFieldA; 
import ap.uat.bean.XTreeA; 
import ap.uat.scrollbar.ScrBarX; 
import ap.uat.scrollbar.ScrBarY;
import ap.utils.OsUtil;
import ap.utils.Su; 


public class ConfigurationImpl implements IConfiguration { 

	public static  int 					MIN_WIDTH_HEIGHT 	= 4; 
	
	public static Dimension 			PREFFERED_SIZE 		= new Dimension(1420,817);
	 
	private String 						home; 
	 
	private String 						active_brew_object 	= "panel"; 
		 
	private Map<String,ActionListener> 	listeners 			= new LinkedHashMap<String,ActionListener>(); 
	 
	private Dimension 					screen_size 		;
	
	private	Dimension					startup_size	= new Dimension(640,480);
	
	private String						os_name;
	
	private String						startup_composite ;//= "D:\\bin\\ecl\\wsp\\Explorer\\composites\\03032021\\тест";
	
	
	public String getStartup_composite() {
		return startup_composite;
	}

	public void setStartup_composite(String startup_composite) {
		this.startup_composite = startup_composite;
	}

	public String getOs_name() {
		return os_name;
	}

	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}

	public Dimension getStartup_size() {
		return startup_size;
	}

	public void setStartup_size(Dimension startup_size) {
		this.startup_size = startup_size;
	}

	public Map<String, ActionListener> getListeners() { 
		return listeners; 
	} 

	public void setListeners(Map<String, ActionListener> listeners) { 
		this.listeners = listeners; 
	} 

	public String getActive_brew_object() { 
		 
		return active_brew_object; 
	} 

	public void setActive_brew_object(String active_brew_object) { 
		 
		this.active_brew_object = active_brew_object; 
	} 

	public ConfigurationImpl() { 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),"Default_home.xml")); 
		 
		this.set_min_width_height(MIN_WIDTH_HEIGHT); 
		 
		this.add_atrs(); 
	} 
	 
	public ConfigurationImpl(String home) { 
	 
		this(); 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),home)); 
		
		this.setOs_name(Su.BeforeAtFirst(OsUtil.get_os_raw()," ").trim());
	} 
	 
	public ConfigurationImpl(String home,Dimension size) { 
		 
		this(home); 

		this.setPrefferedSize(size); 
	} 
	 
	 
	public String update(String home)
	{
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),home));
		
		return this.getHome();
	}
	
	public String update_abs(String home)
	{
		this.setHome(home);
		
		return this.getHome();
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
	 
		PREFFERED_SIZE = size; 
	} 

	 
	@Override 
	public Dimension getPrefferedSize() { 
	 
		return PREFFERED_SIZE; 
	} 

	 
	@Override 
	public void set_min_width_height(int value) { 
		 
		MIN_WIDTH_HEIGHT = value; 
	} 

	 
	@Override 
	public int get_min_width_height() { 
		 
		return MIN_WIDTH_HEIGHT; 
		 
	} 
	 
	public static ConfigurationImpl get_instance() 
	{ 
		return new ConfigurationImpl(); 
	} 

	public static ConfigurationImpl get_instance(String home) 
	{ 
		return new ConfigurationImpl(home); 
	} 
	 
	 
	@Override 
	public boolean show_brew() { 
		 
		gl.tracex(new Object() {}, gl.sf("Active brew...[ %s ]...%s ",this.getActive_brew_object().toUpperCase(),gl.S_OK)); 
		 
		return true; 
	}
	

	public void add_atrs() 
	{
		setScreen_size(Toolkit.getDefaultToolkit().getScreenSize());
	}
	 
	
	public Dimension getScreen_size() {
		return screen_size;
	}

	public void setScreen_size(Dimension screen_size) {
		this.screen_size = screen_size;
	}

	 
	@Override 
	public boolean setConstructionMode(Orion owner) 
	{ 
		//owner.setBackground(ColorUtil.getAlphaColor(owner.getBackground(),30)); 
		 
		owner.setBorder(BorderFactory.createEmptyBorder(4,4,4,4)); 
		 
		return true; 
		 
	} 
	 
	 
} 

