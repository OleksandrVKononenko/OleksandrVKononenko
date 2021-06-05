 
 
 
package ap.btn; 

import java.awt.Color; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.config.TConfig; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 
import ap.utils.CU; 

public class TBody extends TDataPanel { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	public TBody() { 
		 
	} 

	public TBody(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TBody(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TBody(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TBody(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TBody(String payload) { 
		super(payload); 
		 
	} 

	public TBody(BaseProperty prop) { 
		super(prop); 
		 
	} 
	public static void startUp() 
	{ 
		TConfig.start(TBody.getInstances(7)); 
	 
	} 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TBody sp = new TBody(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			sp.setValue(100); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
