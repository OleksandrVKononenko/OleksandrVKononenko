 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.JTextField; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.config.TConfig; 
import ap.prop.BaseProperty; 
import ap.swing.TextField; 

public class TTextField extends TPanel { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	TextField edit = new TextField(); 
	 
	 
	public JTextField getEdit() { 
		return edit; 
	} 

	public void setEdit(TextField edit) { 
		this.edit = edit; 
	} 
	 
	public void setEditText(String text) { 
		 
		this.getEdit().setText(text); 
	} 

	public TTextField() { 
		 
		 
		 
	} 

	public TTextField(Rectangle rect) { 
		super(rect); 
		 
		addComponents(); 
		 
	} 

	public TTextField(Rectangle rect, int index) { 
		 
		super(rect,index); 
		 
		addComponents(); 
		 
	} 
	 
	public TTextField(Rectangle rect,Color color) { 
		super(rect,gl.E_EMPTY); 
		 
		addComponents(); 
		 
		this.setBack_ground(color); 
		 
	} 

	public TTextField(String payload) { 
		super(payload); 
		 
	} 

	public TTextField(BaseProperty prop) { 
		super(prop); 
		 
		addComponents(); 
		 
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
		this.setText(edit.getText()); 
		 
		return super.toString(); 
	} 
	 
	 
	public void addComponents() 
	{ 
		Rectangle rect = this.getBounds(); 
		 
		if(rect != null) 
		{ 
					 
			this.add(edit); 
			 
			this.applyResize(rect); 
			 
			edit.setBackground(Color.white); 
			 
		} 
		 
		 
	} 
	 
		 
	public void applyResize(Rectangle rect) 
	{ 
		Rectangle rect_ = new Rectangle(rect); 
		 
		rect_.x = 0; 
		 
		rect_.y = 0; 
		 
		Rectangle e_rect = gl.getSpannedRect(rect_,new Insets(2,2,2,2)); 
		 
		this.edit.setBounds(e_rect); 
		 
		this.edit.setVisible(true); 
		 
		this.edit.setOwner(this); 

	} 
	 
		 
	public static TTextField getInstance(Rectangle rect) { 

		return  new TTextField(rect,UIManager.getColor("Panel.background")); 
						 
				 
} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TTextField 	sp = new TTextField(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
			 
					sp.setBack_ground(UIManager.getColor("Panel.background")); 
			 
					arr.add(sp); 

		} 

					return arr; 
} 
	 
	 
	 
	 
	public static void startUp() 
	{ 
		TConfig.start(TTextField.getInstances(10)); 
	 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 

