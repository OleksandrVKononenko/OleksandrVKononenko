 
package ap.btn; 

import java.awt.Color; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.config.TConfig; 
import ap.frame.TFrame; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 
import ap.utils.CU; 
import ap.utils.Fu; 

public class TBar extends TPanel { 

	public  static final String MODEL = String.format("%s\\data\\%s.txt",Fu.getCurrentDir(),"TCandle"); 
	 
	public static TFrame tmp_frame = null; 
	 
	public TBar() { 
		 
	} 

	public TBar(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TBar(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TBar(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TBar(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TBar(String payload) { 
		super(payload); 
		 
	} 

	public TBar(BaseProperty prop) { 
		super(prop); 
		 
	} 

	 
	 
	public static boolean getInstances(List<TPanel> target,int count) { 

		 
		Object v = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		String msg = "Get instances"; 
		 
		if (!mng.getCloneCompositeItems(TBar.MODEL,count,target)) 
		{ 
			gl.tracex(v,String.format("%s...clone items...%s",msg,gl.S_ERROR)); 
			 
			return false; 
		} 
		 
		// Temporary update. 
		 
		 
		 
		target.forEach(bar-> 
		{ 
		 	bar.setType("TBar"); 
		   				 
		}); 
		 
			return true; 

	} 
	public static void startUp() { 
		 
		List<TPanel> clone_collection = new ArrayList<TPanel>(); 
		 
		if(!TBar.getInstances(clone_collection,10)) 
			return; 
		 
		TConfig.start(clone_collection); 
		 
	 
		 
	} 
	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
