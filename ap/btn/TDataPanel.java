 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Color; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Locale; 

import javax.swing.UIManager; 

import ap.config.TConfig; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 
import ap.prop.SBounds; 
import ap.utils.CU; 
import ap.utils.Fu; 

public class TDataPanel extends TPanel { 
	 
	 
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	 
	private double value=0.0f; 

	 
	public double getValue() { 
		return value; 
	} 

	public void setValue(double value) { 
		this.value = value; 
		 
		Locale.setDefault(new Locale("en", "US")); 
		 
		this.setData(String.format("%.4f",this.getValue())); 
	} 

	public TDataPanel() { 
		 
	} 

	public TDataPanel(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TDataPanel(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TDataPanel(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TDataPanel(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TDataPanel(String payload) { 
		super(payload); 
		 
	} 

	public TDataPanel(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TDataPanel sp = new TDataPanel(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction("While not defined."); 
			 
			sp.setValue(1.4444); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	public static void startUp() 
	{ 
		TConfig.start(TDataPanel.getInstances(7)); 
	} 
	 
	@Override 
	public String toStringForToolTip() { 

		String msg = 

		String.format("%sfunction=%s;data=%s",super.toStringForToolTip(),this.getFunction(),this.getData()); 

		if (this.isBl_tool_tip()) 
			return msg; 
		else { 
			if (this.getImage() != null) 
				return Fu.get_pure_file_name((this.getImg_desc())); 
			else 
				return null; 
		} 
	} 
	 

	public static void main(String[] args) { 
		 
			startUp(); 

	} 

} 
