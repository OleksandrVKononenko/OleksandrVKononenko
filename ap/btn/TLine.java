 
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

public class TLine extends TPanel { 

	public TLine() { 
		 
	} 

	public TLine(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TLine(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TLine(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TLine(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TLine(String payload) { 
		super(payload); 
		 
	} 

	public TLine(BaseProperty prop) { 
		super(prop); 
		 
	} 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TLine sp = new TLine(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,2), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			sp.setData(String.format("%d.%d",gl.getRandomInt(10),gl.getRandomInt(9999))); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	public static void startUp() { 
		 
		TConfig.start(TLine.getInstances(10)); 

	} 
	 
	public static void main(String[] args)  { 

		startUp(); 

	} 
} 
