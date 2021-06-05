 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

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
import ap.prop.BaseProperty; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 16 ???. 2017 ?. 9:44:09 
* Project  name : Organizer 
* Package  name : ap.btn 
* File     name : TPopupBtn.java 
* 
*/ 
public class TPopupBtn extends TBtn { 

	 
	private static final long serialVersionUID = 1L; 

	 
	public TPopupBtn() { 
		 
	} 

	 
	public TPopupBtn(Rectangle rect) { 
		super(rect); 
		 
	} 

	 
	public TPopupBtn(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	 
	public TPopupBtn(Rectangle rect, Color color) { 
		super(rect, color); 
		 
		this.changeState(TBtn.SLEEP); 
		 
	} 

	 
	public TPopupBtn(String payload) { 
		super(payload); 
		 
	} 

	 
	public TPopupBtn(BaseProperty prop) { 
		super(prop); 
		 
	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		super.setBl_skip_parent_code(true); 
		 
		super.mouseClicked(e); 
		 
		super.setBl_skip_parent_code(false); 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		super.mouseEntered(e); 
		 
		if (this.getState() == TBtn.SLEEP) 
			this.changeState(TBtn.NORMAL); 
		 
				 
	} 
	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		super.mouseExited(e); 
		 
		if (this.getState()== TBtn.NORMAL) 
			this.changeState(TBtn.SLEEP); 
		 
				 
	} 
	 
	 
	public static TPopupBtn getInstance(Rectangle rect) { 

		return  new TPopupBtn(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 
			 
						TPopupBtn sp = TPopupBtn.getInstance(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32)); 
						 
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
		 
			mng.getAdded().addAll(TPopupBtn.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...%s.",gl.S_ERROR)); 
	 
	} 
	 
	public static void main(String[] args) { 
		 
		startUp() ; 
	} 


} 
// Revision : 10.09.2018 12:49:14 
