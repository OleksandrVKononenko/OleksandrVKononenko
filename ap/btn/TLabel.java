 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.event.ComponentEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.prop.BaseProperty; 
import ap.prop.SFont; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.utils.CU; 
import ap.utils.GU; 

public class TLabel extends TPanel { 

	 
	public boolean bl_first_resized_done; 
	 
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	public TLabel() { 
		 super(); 
	} 

	public TLabel(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TLabel(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TLabel(Rectangle rect, Color color) { 
		super(rect, color); 
		 
		this.setAlign(new Rectangle(1,0,0,1)); 
		 
		this.setText("Change it right now."); 
		 		 
	} 
	 
	public TLabel(String payload) { 
		super(payload); 
		 
	} 

	public TLabel(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		 
		int font_size = this.getBounds().height; 
		 				 
		if(this.getFont() != null && this.bl_first_resized_done) 
		{ 
			 
		Font f = new Font(this.getFont().getFontName(), 
				this.getFont().getStyle(), font_size	); 
		 
			this.setFont(f); 
		} 
		 
		if(this.getBounds() != null && this.getManager() != null) 
		this.getManager().getFrame().repaint(this.getBounds()); 
		 
		 
		this.bl_first_resized_done =true; 
		 
	} 
	 
	 
	 
	 
	public static TLabel getInstance(Rectangle rect) { 

		 
		return  new TLabel(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

				List<TPanel> arr = new ArrayList<TPanel>(); 

				for (int i = 0; i < count; i++) { 
			 
						TLabel sp = TLabel.getInstance(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32)); 
						 
						sp.setBack_ground(CU.getAlphaColor(Color.black,25)); 
						 
						sp.setGradient(CU.getAlphaColor(Color.black,25)) ; 
						 
						arr.add(sp); 
				} 

				return arr; 
	} 
	 
	public static void startUp() 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		if(!mng.createGUI()) 
			gl.tracex(v,String.format("Try to create GUI...Error.")); 
		else 
			gl.tracex(v,String.format("Try to create GUI...Ok.")); 
		 
		 
		mng.getAdded().addAll(TLabel.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...Error.")); 
	 
	} 
	 
	public static void main(String[] args) { 
		 
		startUp(); 

	} 

} 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
