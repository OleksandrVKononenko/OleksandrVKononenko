 
package ap.uat.bean; 

import java.awt.BorderLayout; 
import java.awt.Font; 
import java.awt.Rectangle; 
import java.awt.event.ActionListener; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays; 
import java.util.List; 
import java.util.Vector; 
import java.util.stream.Collectors; 

import javax.swing.Action; 
import javax.swing.SwingUtilities; 

import ap.global.gl; 
import ap.swing.IAlign; 
import ap.uat.ActionListenerImpl; 
import ap.uat.ApplicationA; 
import ap.uat.AtOm; 
import ap.uat.BaseActionA; 
import ap.uat.action.LookAndFillActionA; 


public class XComboBoxA extends AtOm implements PropertyChangeListener { 

	private static final long serialVersionUID = 1L; 
	 
	private ComboBoxImplA button ; 
	 
	private Vector<String> items ; 
		 
	private ActionListener action = null; 
	 
	 

	public ActionListener getAction() { 
		return action; 
	} 

	public void setAction(ActionListener action) { 
		this.action = action; 
	} 

	public Vector<String> getItems() { 
		return items; 
	} 

	public void setItems(Vector<String> items) { 
		this.items = items; 
	} 

	public ComboBoxImplA getButton() { 
		return button; 
	} 

	public void setButton(ComboBoxImplA button) { 
		this.button = button; 
	} 

	public XComboBoxA() { 
		addComponents(); 
	} 

	public XComboBoxA(Rectangle rect) { 
		 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		addComponents(); 
		 
	} 

	public XComboBoxA(Vector<String> items,Rectangle rect,ActionListener action) { 
		 
		super(rect); 
		 
		this.addMouseListener(this); 
		 
		this.setItems(items); 
		 
		this.setAction(action); 
		 
		addComponents(); 
		
		this.addPropertyChangeListener(this);
		 
	} 
	 
	 
	public void addComponents() 
	{ 
		ApplicationA.cio.setConstructionMode(this); 
		 
		this.setLayout(new BorderLayout()); 
		 
		this.setName(gl.sf("ComboBox_%d",this.getIdo().getId())); 
		 
		Vector<String> vector = new Vector<String>(); 
		 
		//for(int i=0;i<1000;i++) 
		//	vector.add(i,gl.sf("ComboBoxItem_%03d",i)); 
		 
		if(this.getItems() != null) 
		{ 
			vector.clear(); 
			 
			vector.addAll(this.getItems()); 
		} 
				 
		ComboBoxImplA 	cmb = new ComboBoxImplA(vector); 
		 
						cmb.setName(this.getName()); 
		 
						cmb.setFont(new Font("Tahoma",Font.PLAIN,11)); 
		 
						cmb.setSelectedIndex(gl.E_ERROR); 
		
						cmb.add_action_listener(this.getAction()); 
		 
						this.setButton(cmb); 
		 
						this.getButton().setOwner(this); 
		 
						this.add(BorderLayout.CENTER,this.getButton()); 
	} 
	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		this.getButton().revalidate(); 
	} 
	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
	 
		super.mouseMoved(e); 
		 
		this.getButton().setToolTipText(this.toString()); 
		 
	} 
	 
	@Override 
	public void mouseDragged(MouseEvent e) { 
	 
		super.mouseDragged(e); 
		 
		this.revalidate(); 
		 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		super.mouseEntered(e); 
		 
		this.getButton().addActionListener(ApplicationA.getCio().getListeners().get(this.getIdo().getName())); 
		 
	} 
			 
	 
	public static XComboBoxA get_instance(Rectangle rect) 
	{ 
		return new XComboBoxA(rect); 
	} 
	 
	public static XComboBoxA get_instance(Rectangle rect,int align) 
	{ 
	 
		XComboBoxA 	atom = XComboBoxA.get_instance(rect); 
	 
					atom.setBackground(gl.getRandomColor()); 
	 
					atom.getStio().set_deny_drag(false); 
				 
					atom.getDecoro().set_align(align); 
		 
					atom.getStio().set_move_bottom_right(true); 
				 
		 
		return atom; 
	 
	} 
	 
	public static XComboBoxA get_instance(Rectangle rect,Vector<String> items,int align,ActionListener action) 
	{ 
	 
		XComboBoxA 	atom = new XComboBoxA(items,rect,action); 
		 
					atom.setBackground(gl.getRandomColor()); 

					atom.getStio().set_deny_drag(false); 
	 
					atom.getDecoro().set_align(align); 

					atom.getStio().set_move_bottom_right(true); 

					 
		return 		atom; 
	 
	} 
	 
	public static XComboBoxA get_brew_choice_instance(Rectangle rc,int align,ActionListener action) 
	{ 
	 
		Vector<String> items = new Vector<String>(); 
		 
		items.add(""); 
	 	 
		items.addAll(ApplicationA.getCio().getStore().keySet().stream() 
				.collect(Collectors.toList())); 
	 
		XComboBoxA   atom = XComboBoxA.get_instance(rc,items,IAlign.indexOf("BASE_ZOOM_ALL"),action); 
		 
		ApplicationA.getCio().getListeners().put(atom.getIdo().getName(),action); 

		return atom; 
	} 
	 
	public static XComboBoxA get_look_and_fill_choice_instance(Rectangle rc,int align,ActionListener action) 
	{ 
	 
		Vector<String> items = new Vector<String>(); 
		 
		items.add(""); 
		 
		items.addAll(LookAndFillActionA.get_look_and_feel_list()); 
	 
		XComboBoxA   atom = XComboBoxA.get_instance(rc,items,IAlign.indexOf("BASE_ZOOM_ALL"),action); 
		 
		ApplicationA.getCio().getListeners().put(atom.getIdo().getName(),action); 
	 
		return atom; 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				ApplicationA.get_instance(); 
			} 
		}); 
		 
			 
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		
	} 

} 
