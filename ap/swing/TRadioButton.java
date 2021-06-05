 
 
 
 
 
 
 
 
 
 
 
 
 
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

import javax.swing.JRadioButton; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.config.TConfig; 
import ap.prop.BaseProperty; 
import ap.state.Fl; 
import ap.swing.RadioButton; 
import ap.utils.Biu; 

public class TRadioButton extends TPanel { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	RadioButton edit = new RadioButton(); 
	 
	private boolean bl_selected; 
	 
	 
	public boolean isBl_selected() { 
		return super.isSelected(); 
	} 

	public void setBl_selected(boolean bl_selected) { 
		super.setSelected(bl_selected); 
	} 

	public JRadioButton getEdit() { 
		return edit; 
	} 

	public void setEdit(RadioButton edit) { 
		this.edit = edit; 
	} 
	 
	public void setEditText(String text) { 
		 
		this.getEdit().setText(text); 
	} 

	public TRadioButton() { 
		 
		 
		 
	} 

	public TRadioButton(Rectangle rect) { 
		super(rect); 
		 
		addComponents(this); 
		 
	} 

	public TRadioButton(Rectangle rect, int index) { 
		 
		this(rect); 
		 
		this.setZorder(index); 
		 
		addComponents(this); 
		 
	} 
	 
	public TRadioButton(Rectangle rect,Color color) { 
		 
		this(rect,gl.E_EMPTY); 
		 
		addComponents(this); 
		 
		this.setBack_ground(color); 
		 
	} 

	public TRadioButton(String payload) { 
		super(payload); 
		 
	} 

	public TRadioButton(BaseProperty prop) { 
		super(prop); 
		 

		if(Biu.ISA(this.getFlags(),Fl.VK_CHECKED)) 
		{ 
			this.getEdit().setSelected(true); 
		} 
		 
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
		 
		if(rect != null) 
		{ 
					 
			edit.addItemListener(new ItemListener(){ 
				 
				@Override 
				public void itemStateChanged(ItemEvent e) 
				{ 
					 
					if(e.getStateChange() == ItemEvent.SELECTED) 
						owner.setFlags(Biu.ON(owner.getFlags(),Fl.VK_CHECKED)); 
					else 
						owner.setFlags(Biu.OFF(owner.getFlags(),Fl.VK_CHECKED)); 
				} 
			}); 
			 
			 
			 
			this.add(edit); 
			 
			this.applyResize(rect); 
			 
			edit.setBackground(this.getBackground()); 
						 
			 
		} 
		 
		 
	} 
	 
		 
	public void applyResize(Rectangle rect) 
	{ 
		Rectangle rect_ = new Rectangle(rect); 
		 
		rect_.x = 0; 
		 
		rect_.y = 0; 
		 
		Rectangle e_rect = gl.getSpannedRect(rect_,new Insets(2,2,2,2)); 
		 
		e_rect.width = 20; 
		 
		this.edit.setBounds(e_rect); 
		 
		this.edit.setVisible(true); 
		 
		this.edit.setOwner(this); 

	} 
	 
		 
	public static TRadioButton getInstance(Rectangle rect) { 

		return  new TRadioButton(rect,UIManager.getColor("Panel.background")); 
						 
				 
} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TRadioButton 	sp = new TRadioButton(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
			 
					sp.setBack_ground(UIManager.getColor("Panel.background")); 
			 
					arr.add(sp); 

		} 

					return arr; 
} 
	 
	 
	 
	 
	public static void startUp() 
	{ 
		TConfig.start(TRadioButton.getInstances(10)); 
	 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 


