 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.JTable; 
import javax.swing.JTable; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.config.TConfig; 
import ap.prop.BaseProperty; 
import ap.state.Fl; 
import ap.utils.Biu; 

public class TTable extends TPanel { 

	private static final long serialVersionUID = 1L; 
	 
	JTable edit = new JTable(); 
	 
	 
	public JTable getEdit() { 
		return edit; 
	} 


	public TTable() { 
		 
	} 

	public TTable(Rectangle rect) { 
		super(rect); 
		 
		addComponents(this); 
		 
	} 

	public TTable(Rectangle rect, int index) { 
		 
		this(rect); 
		 
		this.setZorder(index); 
		 
		addComponents(this); 
		 
	} 
	 
	public TTable(Rectangle rect,Color color) { 
		 
		this(rect,gl.E_EMPTY); 
		 
		addComponents(this); 
		 
		this.setBack_ground(color); 
		 
	} 

	public TTable(String payload) { 
		super(payload); 
		 
	} 

	public TTable(BaseProperty prop) { 
		super(prop); 
			 
		addComponents(this); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		Rectangle rect = this.getBounds(); 
		 
		if(rect != null && this.edit != null) 
		{ 
			this.applyResize(rect); 
		} 
		 
		 
	} 

	 
	@Override 
	public String toString() 
	{ 
		return super.toString(); 
	} 
	 
	 
	public void addComponents(TPanel owner) 
	{ 
		Rectangle rect = this.getBounds(); 

		this.add(edit); 

		this.applyResize(rect); 

		edit.setBackground(Color.white); 

	} 
	 
		 
	public void applyResize(Rectangle rect) 
	{ 
		Rectangle rect_ = new Rectangle(rect); 
		 
		rect_.x = 0; 
		 
		rect_.y = 0; 
		 
		Rectangle e_rect = gl.getSpannedRect(rect_,new Insets(2,2,2,2)); 
		 
		this.edit.setBounds(e_rect); 
		 
		this.edit.setVisible(true); 
		 

	} 
	 
		 
	public static TTable getInstance(Rectangle rect) { 

		return  new TTable(rect,UIManager.getColor("Panel.background")); 
						 
				 
} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TTable 	sp = new TTable(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
			 
					sp.setBack_ground(UIManager.getColor("Panel.background")); 
			 
					arr.add(sp); 

		} 

					return arr; 
} 
	 
	 
	 
	 
	public static void startUp() 
	{ 
		TConfig.start(TTable.getInstances(10)); 
	 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 


