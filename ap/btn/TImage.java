 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Map; 

import javax.swing.BorderFactory; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.prop.BaseProperty; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.utils.CU; 
import ap.utils.GU; 
import ap.utils.MapUtils; 


public class TImage extends TPanel { 

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
	 
	 
	public TImage() { 
		 
	} 

	 
	public TImage(Rectangle rect) { 
		super(rect); 
		 
	} 

	 
	public TImage(Rectangle rect, int index) { 
		super(rect, index); 
		 
		 
		 
		 
	} 
	 
	public TImage(Rectangle rect,Color color) { 
		 
		this(rect,gl.E_EMPTY); 
		 
	} 

	 
	public TImage(String payload) { 
		super(payload); 
		 
	} 
	 
	 
	public static TImage getInstance(Rectangle rect) { 

		TImage img = new TImage(rect,UIManager.getColor("Panel.background")); 
		 
		img.setBack_ground(CU.getAlphaColor(UIManager.getColor("Panel.background"),50)); 
		 
		img.setGradient(CU.getAlphaColor(img.getBackground(),0)) ; 

				 
		return   img; 
				 
	} 
	 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 

					TImage 	sp = new TImage(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
					 
							sp.setBack_ground(CU.getAlphaColor(UIManager.getColor("Panel.background"),50)); 
					 
							sp.setGradient(CU.getAlphaColor(sp.getBackground(),0)) ; 
				 
							arr.add(sp); 

				} 

				return arr; 
	} 

	 
	@Override 
	public void paintComponent(Graphics g) { 
		 
		 
		Graphics2D g2 = (Graphics2D)g; 
	 
	    Rectangle wr = Ru.norm4g(this.getBounds()); 

	    if(this.isSelected()) 
	    { 

	    	if (!this.isSkipSelected()) 
	    	    g2.setStroke(gl.dashed_stroke); 
		} 
		else 
				g2.setStroke(new BasicStroke(0.5f)); 
	 
	 
		//Body 
	    GU.fillAlphaRect(g2,wr,this.getBackground(), 1.0f); 
	 
	    if(this.getImage() != null ) 
		{ 
			GU.drawImage(g2,this.getImage(),wr); 
		} 
	 
	 
	    AreaManager am = new AreaManager( 
				 new Dimension(wr.width,wr.height), 
				 new Dimension(3,3) 
	 		); 
		 
		 Map<Rectangle,Rectangle> map = am.getMap2(new Dimension(3,3),new Dimension(3,3)); 
			 
		 Rectangle r_align = this.getAlign(); 
		 
		 if(!Ru.isInit(r_align)) 
			 r_align = new Rectangle(0,0,0,0); 
		 
		 Rectangle map_rect = MapUtils.findValueByKey(map,r_align); 
		 
		 Rectangle fontRect = gl.getFontRect(g, this.getFont(),this.getText(),map_rect,this.getAlign(),new Dimension(3,0)); 
		 
		 GU.drawAlphaString( 
				 g2, 
				 this.getText(), 
				 fontRect.x,fontRect.y, 
				 this.getText_color(), 
				 this.getFont(), 
				 1.0f); 
 
			 
			GU.drawGradientEx(g2,wr,Ru.Scale(wr,new Dimension(0,0)), 
					this.getGradient(), 
					CU.getAlphaColor(this.getGradient(),0) 
					); 

		 
		if(this.isSelected()) 
		{ 	 
			if (!this.isSkipSelected()) 
			{ 
			 
				GU.drawGradientEx(g2,wr,Ru.Scale(wr,new Dimension(0,0)), 
					CU.getAlphaColor(Color.black,70), 
					CU.getAlphaColor(Color.black,0) 
					); 
			 
			 
			 
				 
				GU.drawAlphaRect(g2,wr,Color.white, 1.00f); 
			} 
		} 
		 
	} 
	 
	 
	 
	public TImage(BaseProperty prop) { 
		super(prop); 
		 
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
		 
			mng.getAdded().addAll(TImage.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...%s.",gl.S_ERROR)); 
	 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 
// Revision : 10.09.2018 12:49:13 
