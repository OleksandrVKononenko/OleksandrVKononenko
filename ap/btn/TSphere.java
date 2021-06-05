 
 
 
 
 
 
 
 
 
 
 
 
 
 
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

import javax.swing.UIManager; 





import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.prop.BaseProperty; 
import ap.state.Fl; 
import ap.utils.GU; 



public class TSphere extends TPanel { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	/** 
	 * @param rectangle 
	 * @param i 
	 */ 
	 
	 
	 
	 
	public TSphere(Rectangle rect) { 
		super(rect); 
	} 
	public TSphere(Rectangle rect, int i) { 
		 
		super(rect,i); 
	} 

	public TSphere(String prop) { 
		super(prop); 
	} 
	 
	public TSphere(BaseProperty prop) { 
		super(prop); 
	} 
	 
	/** 
	 * 
	 */ 
	public TSphere() { 
		super(); 
	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 
		 super.mouseEntered(e); 
		 
		 this.repaint(); 
		 
	} 
	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		 super.mouseExited(e); 
		 
		 this.repaint(); 
		 
	} 
	public static TSphere getInstance(Rectangle rect) { 

		TSphere  sp = new TSphere(rect); 
		 
		sp.setBack_ground(UIManager.getColor("Panel.background")); 
		 
		sp.setGradient(sp.getBackground()) ; 

		return sp; 
		 
} 
	 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TSphere 	sp = new TSphere(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
			 
					sp.setBack_ground(UIManager.getColor("Panel.background")); 
			 
					sp.setGradient(sp.getBackground()) ; 
		 
					arr.add(sp); 

		} 

		return arr; 
} 
	 
	public static void startUp(String factory) 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		if(!mng.createGUI()) 
			gl.tracex(v,String.format("Try to create GUI...Error.")); 
		else 
		{ 
			mng.getFrame().setObjectFactory(factory); 
			 
			gl.tracex(v,String.format("Try to create GUI...Ok.")); 
		} 
		 
		gl.tracex(v,String.format("Try to generate some test object...")); 
		 
		mng.getAdded().addAll(TSphere.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...Error.")); 
	 
	} 
	 
		 
	@Override 
	public void paintComponent(Graphics g) { 

		Graphics2D g2 = (Graphics2D)g; 
		 
		 
		if(this.isAt_selected()) 
		GU.drawSphere(g2,this.getWidth(),this.getHeight(),this.getBackground(),true); 
		else 
		GU.drawSphere(g2,this.getWidth(),this.getHeight(),this.getBackground(),false); 
			 
		 
		 
	} 
	 

	public static void main(String[] args) { 
		 
		startUp("TSphere"); 
	} 

} 
// Revision : 28.01.2017 15:14:39 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
