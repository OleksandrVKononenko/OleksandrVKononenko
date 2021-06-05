 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.btn; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.prop.BaseProperty; 
import ap.state.Fl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 14 ???. 2017 ?. 16:43:29 
* Project  name : Organizer 
* Package  name : ap.btn 
* File     name : TPopupPushBtn.java 
* 
*/ 
public class TPopupPushBtn extends TPushBtn { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	/** 
	 * 
	 */ 
	public TPopupPushBtn() { 
	 
	} 

	/** 
	 * @param rect 
	 */ 
	public TPopupPushBtn(Rectangle rect) { 
		super(rect); 
		 
	} 

	/** 
	 * @param rect 
	 * @param index 
	 */ 
	public TPopupPushBtn(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	/** 
	 * @param rect 
	 * @param color 
	 */ 
	public TPopupPushBtn(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	/** 
	 * @param payload 
	 */ 
	public TPopupPushBtn(String payload) { 
		super(payload); 
		 
	} 

	/** 
	 * @param prop 
	 */ 
	public TPopupPushBtn(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		super.mouseEntered(e); 
		 
		if (this.getState() != TBtn.PUSHED) 
			this.changeState(TBtn.NORMAL); 
		 
			this.repaint(); 
		 
	} 
	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		super.mouseReleased(e); 
		 
		if (this.getState()== TBtn.NORMAL) 
			this.changeState(TBtn.SLEEP); 
		 
			this.repaint(); 
		 
	} 
	 
	 
	 
	 

	public static TPopupPushBtn getInstance(Rectangle rect) { 

		return  new TPopupPushBtn(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 

					TPopupPushBtn 	sp = new TPopupPushBtn(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
					 
							sp.setBack_ground(UIManager.getColor("Panel.background")); 
					 
							sp.setGradient(sp.getBackground()) ; 
				 	 
							sp.setBorder("none"); 
							 
							 
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
		 
			mng.getAdded().addAll(TPopupPushBtn.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...%s.",gl.S_ERROR)); 
	 
	} 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
// Revision : 10.09.2018 12:49:14 
