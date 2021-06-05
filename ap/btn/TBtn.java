
/** 
* 
*/ 
package ap.btn; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.config.TConfig; 
import ap.prop.BaseProperty; 
import ap.state.Fl; 
import ap.utils.CU; 


public class TBtn extends TPanel { 

	private static final long serialVersionUID = 1L; 

	private int state = SLEEP; 
	 
	public static final int PUSHED = gl.E_OK; 
	 
	public static final int SLEEP  = gl.E_ERROR*2; 
	 
	public static final int NORMAL = gl.E_EMPTY; 
	 
	 

	public int getState() { 
		return state; 
	} 

	public void setState(int state) { 
		this.state = state; 
	} 
	 
	public void changeState(int state) 
	{ 
		 
		this.setState(state); 
		 
				 
		switch(this.getState()) 
		{ 

		case TBtn.NORMAL: 
		{ 
			this.setBorder("rbb"); 
			 
		} 
			break; 
		 
		case TBtn.PUSHED: 
		{ 

			this.setBorder("lbb"); 
			 
			 
		} 
			break; 
			 
		case TBtn.SLEEP: 
		{ 

			this.setBorder("NONE"); 
			 
		} 
			break; 
	 
		}  // switch 
		 
		 	 
	} 
	 
	 
		 
	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		super.mousePressed(e); 
		 
		if(this.isSensitivityArea(e.getPoint())) 
		{ 
			 
			return; 
		} 
		 
		if(SwingUtilities.isRightMouseButton(e)) 
		{ 
			 
		} else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			 
			 
			 
			if(!this.isBl_skip_parent_code()) 
			{ 
				 
				 
				if(this.getState()==TBtn.NORMAL) 
					this.changeState(TBtn.PUSHED); 
				 

				 
			} 
			 
		} 
		 
				 
		 
	} 
	 
	 
	@Override 
	public void mouseReleased(MouseEvent e) { 

		super.mouseReleased(e); 
		 
		 
		if(SwingUtilities.isRightMouseButton(e)) 
		{ 
			 
		} else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			if(!this.isBl_skip_mouse_released()) 
			if(this.getState()==TBtn.PUSHED) 
			   this.changeState(TBtn.NORMAL); 
		 	 
			this.getManager().getFrame().mouseReleased(e); 
						 
		} 
		 
		 
		 
	} 
	 
	public TBtn() { 
		 
	} 

	 
	public TBtn(Rectangle rect) { 
		super(rect); 
		 
	} 

	 
	public TBtn(Rectangle rect, int index) { 
		super(rect, index); 
		 
		this.setState(TBtn.NORMAL); 
		 
		 
		 
	} 
	 
	public TBtn(Rectangle rect,Color color) { 
		 
		 
		this(rect,gl.E_EMPTY); 
		 
		this.setBack_ground(color); 
		 
		this.setGradient(this.getBackground()); 
		 
		this.setUnSelectable(true); 
		 
		this.setBorder("rbb"); 
		 
		this.setText_color(Color.black); 
		 
		this.setAlign(new Rectangle(1,1,1,1)); 
		 
		 
	} 

	 
	public TBtn(String payload) { 
		super(payload); 
		 
	} 
	 
	@Override 
	public void paintComponent(Graphics g) { 
	 
			super.paintComponent(g); 
	} 
	 
	@Override 
	public String toStringForToolTip() 
	{ 
		String msg = String.format("%s,state=%d",super.toStringForToolTip(),this.getState()); 
		 
		if(this.getAction().trim().length() != gl.E_EMPTY) 
			msg = String.format("pid=%d,id=%d,action=%s",this.getPid(),this.getId(),this.getAction()); 

		return msg; 
		 
	} 
	 
	public static TBtn getInstance(Rectangle rect) { 

		return  new TBtn(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	public static TBtn getInstance(int index) 
	{ 
		 
		TBtn 	sp = new TBtn(new Rectangle(16,16,16,16),index); 
		 
		sp.setBack_ground(UIManager.getColor("Panel.background")); 
 
		sp.setGradient(CU.getAlphaColor(sp.getBackground(),gl.E_EMPTY)) ; 
		 
		sp.setBorder("rbb"); 
		 
		sp.setText_color(Color.black); 
		 
		sp.setAlign(new Rectangle(1,1,1,1)); 
		 
		sp.setUnSelectable(true); 
		 
		return sp; 
		 
	} 
	 
	public static TBtn getInstance(Rectangle rect ,int index) 
	{ 
		 
		TBtn 	sp = new TBtn(rect,index); 
		 
		sp.setBack_ground(UIManager.getColor("Panel.background")); 
 
		sp.setGradient(CU.getAlphaColor(sp.getBackground(),gl.E_EMPTY)) ; 
			 
		sp.setBorder("rbb"); 
		 
		sp.setText_color(Color.black); 
		 
		sp.setAlign(new Rectangle(1,1,1,1)); 
		 
		sp.setUnSelectable(true); 
		 
		return sp; 
		 
		 
	} 
	 
	public static List<TPanel> getInstances(int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 
					 							 
							arr.add(getInstance(i)); 

				} 
				 
							return arr; 
	} 

	 

	 
	 
	public TBtn(BaseProperty prop) { 
		super(prop); 
		 
		 
	} 

	public static void startUp() 
	{ 
		TConfig.start(TBtn.getInstances(10)); 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 
// Revision : 10.09.2018 12:49:13 
