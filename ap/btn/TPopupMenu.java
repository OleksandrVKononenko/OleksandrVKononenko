 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.btn; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 31 ???. 2017 ?. 14:35:29 
* Project  name : Organizer 
* Package  name : ap.btn 
* File     name : TPopupMenu.java 
* 
*/ 
public class TPopupMenu extends TPanel { 

	private static final long serialVersionUID = 1L; 
	 
	public TPopupMenu() { 
		 
	} 

	/** 
	 * @param name 
	 * @param mode 
	 */ 
	public TPopupMenu(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	/** 
	 * @param rect 
	 */ 
	public TPopupMenu(Rectangle rect) { 
		super(rect); 
		 
	} 

	/** 
	 * @param rect 
	 * @param index 
	 */ 
	public TPopupMenu(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	/** 
	 * @param rect 
	 * @param color 
	 */ 
	public TPopupMenu(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	/** 
	 * @param payload 
	 */ 
	public TPopupMenu(String payload) { 
		super(payload); 
		 
	} 

	/** 
	 * @param prop 
	 */ 
	public TPopupMenu(BaseProperty prop) { 
		super(prop); 
		 
	} 

	public static TPopupMenu getInstance(Rectangle rect) { 

		return  new TPopupMenu(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 

					TPopupMenu 	sp = new TPopupMenu(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
					 
							sp.setBack_ground(UIManager.getColor("Panel.background")); 
					 
							sp.setGradient(sp.getBackground()) ; 
				 			 
							sp.setBorder("rbb"); 
							 
							arr.add(sp); 

				} 

							return arr; 
	} 

	 
	public static void startUp() 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		String msg = "Create GUI"; 
		 
		if(!mng.createGUI()) 
		{ 
			gl.tracex(v,String.format("%s...%s",msg,gl.S_ERROR)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("%s...%s",msg,gl.S_OK)); 
		 
			mng.getAdded().addAll(TPopupMenu.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...%s.",gl.S_ERROR)); 
	 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 
	 
	 
} 
// Revision : 10.09.2018 12:49:14 
