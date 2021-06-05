 
/** 
* 
*/ 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.config.TConfig; 
import ap.prop.BaseProperty; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.utils.CU; 
import ap.utils.GU; 

public class TPushBtn extends TBtn { 


	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	public int clickCount ; 
	 
	public TPushBtn() { 
		 
	} 

	/** 
	 * @param rect 
	 */ 
	public TPushBtn(Rectangle rect) { 
		super(rect); 
		 
	} 

	/** 
	 * @param rect 
	 * @param index 
	 */ 
	public TPushBtn(Rectangle rect, int index) { 
		super(rect, index); 
		 
		 
		 
		 
	} 

	/** 
	 * @param rect 
	 * @param color 
	 */ 
	public TPushBtn(Rectangle rect, Color color) { 
		super(rect, color); 
		 
		this.setText(gl.sf("P%d",this.getId())); 
		 
	} 

	/** 
	 * @param payload 
	 */ 
	public TPushBtn(String payload) { 
		super(payload); 
		 
	} 

	/** 
	 * @param prop 
	 */ 
	public TPushBtn(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	 
	 
	@Override 
	public String toStringForToolTip() 
	{ 
		return String.format("%s,state=%d",super.toStringForToolTip(),this.getState()); 
	} 
	 
	 
	@Override 
	public void mouseReleased(MouseEvent e) 
	{ 
		 
	} 
	 
	@Override 
	public void paintComponent(Graphics g) { 
		 
		super.setBl_skip_draw_image(true); 
		 
		super.paintComponent(g); 
		 
		Graphics2D g2 = (Graphics2D)g; 
	 
	    Rectangle wr = Ru.norm4g(this.getBounds()); 
	    	 
	    if(this.getState()==TBtn.PUSHED) 
	    { 
	    	GU.draw_push_back(g,this.getBounds(),Color.yellow,0.15f); 
	    		    	 
	    }  // Pushed 
	 
	 		// Redraw image. 
		if (this.getImage() != null) 
			 
		GU.drawImage(g2, this.getImage(),wr); 
 
		updateTextSuite(g); 
		 
		} 
	 
	 
	@Override 
	public void mousePressed(MouseEvent e) { 
		 
		super.setBl_skip_parent_code(true); 
		 
		super.mousePressed(e); 
		 
		if(this.isSensitivityArea(e.getPoint())) 
			return; 
		 
		if(SwingUtilities.isRightMouseButton(e)) 
		{ 
			 
		} else if(SwingUtilities.isLeftMouseButton(e)) 
		{ 
			 
			if(this.getState()==TBtn.NORMAL) 
			{ 
				this.changeState(TBtn.PUSHED); 
			} 
			else if(this.getState()==TBtn.PUSHED) 
			{ 
				this.changeState(TBtn.NORMAL); 
			} 
		} // RightMouseButton 
		 
		 
	} 
	public static TPushBtn getInstance(Rectangle rect) { 

		return  new TPushBtn(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	public static TPushBtn getInstance(int index) 
	{ 
		 
		TPushBtn 	sp = new TPushBtn(Ru.get_init_rect(32),index); 
		 
					sp.setBorder("RBB"); 
		 
					sp.setText(gl.sf("P%d",sp.getId())); 
					 
					return sp; 
		 
	} 
	 
	 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 
			 							 
					arr.add(getInstance(i)); 

		} 
		 
					return arr; 
	} 
	 
	public static void startUp() 
	{ 
		TConfig.start(TPushBtn.getInstances(10)); 
	 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
	} 

} 
// Revision : 10.09.2018 12:49:14 
