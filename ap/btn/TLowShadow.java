 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Color; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 
import ap.utils.CU; 

public class TLowShadow extends TDataPanel { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	public TLowShadow() { 
		 
	} 

	public TLowShadow(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TLowShadow(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TLowShadow(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TLowShadow(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TLowShadow(String payload) { 
		super(payload); 
		 
	} 

	public TLowShadow(BaseProperty prop) { 
		super(prop); 
		 
	} 
	public static void startUp() 
	{ 
		 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		String msg = "Create GUI"; 
		 
		if(!mng.createGUI()) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_ERROR)); 
			 
			return ; 
		} 
		 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_OK)); 
		 
			mng.getAdded().addAll(TLowShadow.getInstances(10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(new Object(){},String.format("Error while add manager object...%s.",gl.S_ERROR)); 
	 
	} 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TLowShadow sp = new TLowShadow(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			sp.setValue(1.7777); 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
