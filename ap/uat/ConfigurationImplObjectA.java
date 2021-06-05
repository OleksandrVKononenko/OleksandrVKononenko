 
 
package ap.uat; 

import java.awt.Dimension; 
import java.awt.event.ActionListener; 
import java.util.LinkedHashMap; 
import java.util.Map;

import javax.swing.BorderFactory;

import ap.global.gl; 
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


public class ConfigurationImplObjectA implements IConfigurationA { 

	public static final int 	MW = 2; 
	 
	private String 				home; 
	 
	private Dimension 			preffered_size; 
	 
	private int 				min_width_height = 2; 
	 
	private String 				active_brew_object = "panel"; 
	 
	private Map<String,AtOm> 	store = new LinkedHashMap<String,AtOm>(); 
	 
	private Map<String,ActionListener> 	listeners = new LinkedHashMap<String,ActionListener>(); 
	 
	 
	public Map<String, ActionListener> getListeners() { 
		return listeners; 
	} 

	public void setListeners(Map<String, ActionListener> listeners) { 
		this.listeners = listeners; 
	} 

	public Map<String, AtOm> getStore() { 
		return store; 
	} 

	public void setStore(Map<String, AtOm> store) { 
		this.store = store; 
	} 

	public String getActive_brew_object() { 
		 
		return active_brew_object; 
	} 

	public void setActive_brew_object(String active_brew_object) { 
		 
		this.active_brew_object = active_brew_object; 
	} 

	public ConfigurationImplObjectA() { 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),"Default_home.xml")); 
		 
		this.setPrefferedSize(new Dimension(64,24)); 
		 
		this.set_min_width_height(MW); 
		 
		this.add_atrs(); 
	} 
	 
	public ConfigurationImplObjectA(String home) { 
	 
		this(); 
		 
		this.setHome(gl.sf("%s\\%s",System.getProperty("user.dir"),home)); 
	} 
	 
	public ConfigurationImplObjectA(String home,Dimension size) { 
		 
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
	public void set_min_width_height(int mwh) { 
		 
		this.min_width_height = mwh; 
	} 

	 
	@Override 
	public int get_min_width_height() { 
		 
		return this.min_width_height; 
		 
	} 
	 
	public static ConfigurationImplObjectA get_instance() 
	{ 
		return new ConfigurationImplObjectA(); 
	} 

	public static ConfigurationImplObjectA get_instance(String home) 
	{ 
		return new ConfigurationImplObjectA(home); 
	} 
	 
	public static ConfigurationImplObjectA get_instance(String home,Dimension size) { 
		 
		return new ConfigurationImplObjectA(home,size); 
	} 

	 
	@Override 
	public boolean show_brew() { 
		 
		gl.tracex(new Object() {}, gl.sf("Active brew...[ %s ]...%s ",this.getActive_brew_object().toUpperCase(),gl.S_OK)); 
		 
		return true; 
	} 
	 
	public void add_atrs() 
	{ 
		this.getStore().put("panel",AtOm.get_instance()); 
		
		this.getStore().put("panelsmile",PanelSmile.get_instance()); 
				
		this.getStore().put("point_start",PanelPoint.get_instance()); 
		
		this.getStore().put("point_end",PanelPoint.get_instance());
		 
		this.getStore().put("point_ctrl1",PanelPoint.get_instance()); 
		
		this.getStore().put("point_ctrl2",PanelPoint.get_instance());
				
		this.getStore().put("button",XButtonA.get_instance()); 
		 
		this.getStore().put("combobox",XComboBoxA.get_instance()); 
		 
		this.getStore().put("checkbox",XCheckBoxA.get_instance()); 
		 
		this.getStore().put("radiobutton",XRadioButtonA.get_instance()); 
		 
		this.getStore().put("scrbarx",ScrBarX.get_instance()); 
		 
		this.getStore().put("scrbary",ScrBarY.get_instance()); 
		 
		this.getStore().put("label",XLabelA.get_instance()); 
		 
		this.getStore().put("vslider",XSliderA.get_instance()); 
		 
		this.getStore().put("hslider",XSliderA.get_instance()); 
		 
		this.getStore().put("spinner",XSpinnerA.get_instance()); 
		 
		this.getStore().put("tabpane",XTabbedPaneA.get_instance()); 
		 
		this.getStore().put("tree",XTreeA.get_instance()); 
		 
		this.getStore().put("list",XListA.get_instance()); 
		 
		this.getStore().put("table",XTableA.get_instance()); 
		 
		this.getStore().put("editfield",XTextFieldA.get_instance());
		
		this.getStore().put("textarea",XTextAreaA.get_instance()); 
		 
				 
	} 
	 
	 
	@Override 
	public boolean setConstructionMode(AtOm owner) 
	{ 
		//owner.setBackground(ColorUtil.getAlphaColor(owner.getBackground(),30)); 
		 
		owner.setBorder(BorderFactory.createEmptyBorder(4,4,4,4)); 
		 
		return true; 
		 
	} 
	 
	 
} 

