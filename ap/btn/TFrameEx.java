 
 
 
package ap.btn; 

import java.awt.Color; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.config.TConfig; 
import ap.global.gl; 
import ap.prop.BaseProperty; 
import ap.utils.CU; 

public class TFrameEx extends TPanel { 

	public TFrameEx() { 
		 
	} 

	public TFrameEx(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TFrameEx(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TFrameEx(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TFrameEx(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TFrameEx(String payload) { 
		super(payload); 
		 
	} 

	public TFrameEx(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TFrameEx sp = new TFrameEx(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			sp.setData(String.format("%d.%d",gl.getRandomInt(10),gl.getRandomInt(9999))); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	public static void startUp() { 
		 
		TConfig.start(TFrameEx.getInstances(10)); 

	} 

	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
