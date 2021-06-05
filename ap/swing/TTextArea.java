 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.EventQueue; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 


import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.JScrollBar; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.UIManager; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.btn.SKeyHandler; 
import ap.prop.BaseProperty; 
import ap.swing.TextArea; 
import ap.utils.Bau; 
import ap.utils.Fu; 


public class TTextArea extends TPanel { 

	 
	private static final long serialVersionUID = 1L; 
	 
	public SKeyHandler KeyHandler = new SKeyHandler(); 

	TextArea edit = new TextArea(); 
	 
	JScrollPane sp = new JScrollPane(edit); 
	 
	private boolean flag ; 
		 
	 
	public TextArea getEdit() { 
		return edit; 
	} 

	public void setEdit(TextArea edit) { 
		this.edit = edit; 
	} 

	public TTextArea() { 
		 
	} 

	public TTextArea(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TTextArea(Rectangle rect, int index) { 
		super(rect, index); 
		 
		addComponents(); 
		 
		edit.setLineWrap(true); 
		/* 
		this.getEdit().addKeyListener( 
				 
				new KeyListener() 
				{ 
					 
					public void keyPressed(KeyEvent e) { 
						 
						int keyCode = e.getKeyCode(); 
							 
						 
						switch (keyCode) { 
						 
						 
						case KeyEvent.VK_CONTROL: { 

							KeyHandler.setBlControl(true); 
							 
						} 

							break; 

						case KeyEvent.VK_SHIFT: { 

							KeyHandler.setBlShift(true); 

						} 

							break; 

						case KeyEvent.VK_ALT: { 

							KeyHandler.setBlAlt(true); 

						} 
 
						case KeyEvent.VK_C : 
						{ 
							KeyHandler.setBlC(true); 

						} 
						break; 

						case KeyEvent.VK_V : 
						{ 
							KeyHandler.setBlV(true); 

						} 
						break; 
						 
						case KeyEvent.VK_Z : 
						{ 
							KeyHandler.setBlZ(true); 

						} 
						break; 

						case KeyEvent.VK_S : 
						{ 
							KeyHandler.setBlS(true); 

						} 
						break; 
						 
						case KeyEvent.VK_O : 
						{ 
							KeyHandler.setBlO(true); 

						} 
						break; 
						 
						}//switch 
						 
						e.consume(); 
						 
						KeyHandler.handler(getEdit()); 
						 
						 
					} 
					 
					@Override 
					public void keyTyped(KeyEvent e) { 
						 
					} 

					@Override 
					public void keyReleased(KeyEvent e) { 
						 
						e.consume(); 
						 
						KeyHandler.freeTextArea(); 
						 
					} 
					 
					 
				} 
				 
				); */ 
		 
		 
	} 
	 
	public TTextArea(Rectangle rect,Color color) { 
		 
		this(rect,gl.E_EMPTY); 
		 
		this.setBack_ground(color); 
		 
		this.setGradient(this.getBackground()); 
		 
	} 
	 

	public TTextArea(String payload) { 
		super(payload); 
		 
	} 

	public TTextArea(BaseProperty prop) { 
		 
		super(prop); 
		 
		addComponents(); 
		 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		Rectangle rect = this.getBounds(); 
		 
		if(rect != null && this.edit != null && this.sp != null) 
		{ 
			this.applyResize(rect); 
			 
			// One time to PageUp. 
			 
			 
			if(flag == false) 
			{ 
			 
				JScrollBar v = sp.getVerticalScrollBar(); 
			 
				v.setValue(v.getMinimum()); 
				 
				flag = true; 
			 
			} 
			 
		} 
		 
		 
		 
	} 

	@Override 
	public String toString() 
	{ 
		String src = edit.getText(); 
		 
		if(src.trim().length() != gl.E_EMPTY) 
		{	 
			String hex = Bau.to_hex_str_from_byte_array(src.getBytes()); 
		 
			this.setText(hex); 
		 
		} 
		 
		return super.toString(); 
	} 
	 
	public void addComponents() 
	{ 
		Rectangle rect = this.getBounds(); 
		 
		if(rect != null) 
		{ 
			this.add(sp); 
			 
			this.applyResize(rect); 
			 
			this.getEdit().setOwner(this); 
			 
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
		 
		sp.setBounds(e_rect); 
		 
		this.sp.setVisible(true); 

	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		super.mouseEntered(e); 
		 
	} 
	 
	@Override 
	public void mouseExited(MouseEvent e) { 
		super.mouseExited(e); 
		 
	} 
	 
	public static TTextArea getInstance(Rectangle rect) { 

		return  new TTextArea(rect,UIManager.getColor("Panel.background")); 
				 
	} 
	 
	 
		 
	public static List<TPanel> getInstances(Dimension dim, int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TTextArea 	sp = new TTextArea(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 32, 32),i); 
			 
					sp.setBack_ground(UIManager.getColor("Panel.background")); 
			 
					sp.setGradient(sp.getBackground()) ; 
		 
					arr.add(sp); 

		} 

		return arr; 
} 
	 
	 
	public static void startUp() 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		if(!mng.createGUI()) 
		{ 
			gl.tracex(v,String.format("Try to create GUI...Error.")); 
			 
			return; 
		} 
		else 
			gl.tracex(v,String.format("Try to create GUI...Ok.")); 
		 
		 
		 
		mng.getAdded().addAll(TTextArea.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...Error.")); 
	 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		Runnable r = new Runnable() 
		{ 
			@Override 
			public void run() 
			{ 
				startUp(); 
			} 
		}; 
		 
		EventQueue.invokeLater(r); 
		 
		 
		 
		 
	} 

} 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
