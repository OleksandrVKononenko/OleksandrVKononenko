package ap.orion;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.DefaultRowSorter;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ap.area.AreaManager;
import ap.btn.TGridLine;
import ap.gen.IDGen;
import ap.global.gl;
import ap.mercury.components.Button;
import ap.mercury.components.CheckBox;
import ap.mercury.components.ComboBox;
import ap.mercury.components.RadioButton;
import ap.mercury.components.SliderH;
import ap.mercury.components.SliderV;
import ap.mercury.components.ToggleButton;
import ap.mercury.components.parser.Parser;
import ap.mercury.render.DateOhlc;
import ap.mercury.components.Label;
import ap.orion.app.Application;
import ap.orion.components.ScrollPane;
import ap.orion.impl.ActionListenerImpl;
import ap.orion.impl.CollectionImpl;
import ap.orion.impl.DataImpl;
import ap.orion.impl.DecoroImpl;
import ap.orion.impl.IdentityImpl;
import ap.orion.impl.ImageImpl;
import ap.orion.impl.TempImpl;
import ap.orion.intf.IAlign;
import ap.orion.live.Live;
import ap.orion.lsnr.BrewActionListener;
import ap.orion.lsnr.BtnActionListener;
import ap.orion.lsnr.ListSelectionListenerImpl;
import ap.orion.state.ObjectRightsImpl;
import ap.orion.state.ObjectStateImpl;
import ap.orion.state.ObjectStyleImpl;
import ap.prop.SBounds;
import ap.prop.SDimension;
import ap.prop.SFont;
import ap.shape.Ru;
import ap.utils.Bau;
import ap.utils.CU;
import ap.utils.DateUtil;
import ap.utils.GU;
import ap.utils.Su;


@SuppressWarnings("serial")
public class Orion  extends JPanel implements  
MouseListener,
MouseMotionListener,
ComponentListener,
Comparable<Orion> ,
ListSelectionListener,
ChangeListener{
	

	public static int 				MIN_HEIGHT 			= ResizableBorder.DIST*3;
	
	private IdentityImpl 			ido;
	
	private DecoroImpl 				decoro;
	
	private ImageImpl 				image;
	
	private DataImpl 				data;
	
	private TempImpl 				temp;
	
	private Orion 					target;
	
	private 						Component 			child;
	
	private 						ObjectRightsImpl    object_rights;
	
	private 						ObjectStateImpl     object_state;
	
	private 						ObjectStyleImpl     object_style;
	
	private int 					cursor;
    
    private boolean 				group;
    
	
	public TempImpl getTemp() {
		return temp;
	}

	public void setTemp(TempImpl temp) {
		this.temp = temp;
	}

	public boolean is_group() {
		return group;
	}

	public void set_group(boolean group) {
		this.group = group;
	}

	public ObjectStyleImpl getStyle() {
		return object_style;
	}

	public void setStyle(ObjectStyleImpl object_style) {
		this.object_style = object_style;
	}

	public ObjectRightsImpl getObject_rights() {
		return object_rights;
	}

	public void setObject_rights(ObjectRightsImpl object_rights) {
		this.object_rights = object_rights;
	}

	public ObjectStateImpl getObject_state() {
		return object_state;
	}

	public void setObject_state(ObjectStateImpl object_state) {
		this.object_state = object_state;
	}

	public int get_Cursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	
	public DataImpl getData() {
		return data;
	}

	public void setData(DataImpl data) {
		this.data = data;
	}

	public ObjectStateImpl getState() {
		return object_state;
	}

	public void setState(ObjectStateImpl object_state) {
		this.object_state = object_state;
	}

	
	public ObjectRightsImpl getRights() {
		return object_rights;
	}

	public void setRights(ObjectRightsImpl object_rights) {
		this.object_rights = object_rights;
	}

	
	public Component getChild() {
		return child;
	}

	public void setChild(Component child) {
		this.child = child;
	}

	
	
	public Orion getTarget() {
		return target;
	}

	public void setTarget(Orion target) {
		this.target = target;
	}


	public DecoroImpl getDecoro() {
		return decoro;
	}

	public void setDecoro(DecoroImpl decoro) {
		this.decoro = decoro;
	}

	public IdentityImpl getIdo() {
		return ido;
	}

	public void setIdo(IdentityImpl ido) {
		this.ido = ido;
	}

	
	public ImageImpl getImage() {
		return image;
	}

	public void setImage(ImageImpl image) {
		this.image = image;
	}

	public Orion() {
		
		add_listeners();
		
	}

	
	public Orion(Component comp,Rectangle bounds) {
		
		
		this();
		

		setup_impls(); 
		
		setup_startup_attrs();
			
		// Register component.
		
		Application.register_component(this);
		
		// Add child suite.
		
		add_child_component(comp,bounds);
		
		// Update name.
		
		this.setName(update_name());
		
		((ResizableBorder) this.getBorder()).setOwner(this);
		
		this.getDecoro().set_text(""); 
		
		if (this.get_class().equalsIgnoreCase("Orion"))
			this.getDecoro().set_text(this.getName());
		
		
		if(
				this.get_class().equalsIgnoreCase("Label") ||
				this.get_class().equalsIgnoreCase("CheckBox") ||
				this.get_class().equalsIgnoreCase("RadioButton") ||
				this.get_class().equalsIgnoreCase("Button") ||
				this.get_class().equalsIgnoreCase("ToggleButton") 
		  )
		{
			if(this.getChild() instanceof Label)
			{
				((Label)this.getChild()).setText(this.getName());
				
			} else if(this.getChild() instanceof CheckBox)
			{
				((CheckBox)this.getChild()).setText(this.getName());
				
			}else if(this.getChild() instanceof RadioButton)
			{
				((RadioButton)this.getChild()).setText(this.getName());
				
			}else if(this.getChild() instanceof Button)
			{
				((Button)this.getChild()).setText(this.getName());
				
			}else if(this.getChild() instanceof ToggleButton)
			{
				((ToggleButton)this.getChild()).setText(this.getName());
				
			}
		}
		else
		this.getDecoro().set_text(""); 
		
		// Update background.
		
		
		if (! this.is_orion_pure())
		{
			this.setBackground(new Color(0,0,0,0));
		}
		

	}
	
	public boolean is_orion_pure()
	{
		return this.get_class().equalsIgnoreCase("Orion");
	}
	
	public void add_child_component(Component comp,Rectangle bounds)
	{
		/*
		gl.sfn("<add_child_component>...Component...%s...%s...Child...%s...%s",
				this.getClass().getSimpleName(),
				this.getName(),
				comp.getClass().getSimpleName(),
				comp.getName());
		*/
		
		this.setBorder(new ResizableBorder(ResizableBorder.DIST));
		
		if(comp instanceof JPanel )
    	{
			
			if(comp.getClass().getSimpleName().equalsIgnoreCase("JPanel"))
			{
				
				this.setChild(this);
	    		
	    		setLayout(null);
	    		
	    		setBounds(bounds);
	    		
				
	    		
	    	} else if(comp.getClass().getSimpleName().equalsIgnoreCase("Cmd"))
	    	{
	    		
	    		this.setChild(comp);
	    		
	    		this.setLayout(new BorderLayout());
	        		
	    		this.add(comp,BorderLayout.CENTER);
	    		
	    	} 
    	} 
		else if(comp instanceof Button)
        {
    		
    				((Button) comp).addActionListener(BtnActionListener.get_instance());
    				
    				((Button) comp).setOwner(this);
    				
            	  	add(comp,BorderLayout.CENTER);
              	
              		this.setChild(comp);
            	
         } else if(comp instanceof Label)
         {
        	 		((Label) comp).setOwner(this);
     				
             	  	add(comp,BorderLayout.CENTER);
               	
               		this.setChild(comp);
             	
          }else if(comp instanceof ToggleButton)
         {
     			
     				((ToggleButton) comp).setOwner(this);
     				
             	  	add(comp,BorderLayout.CENTER);
               	
               		this.setChild(comp);
             	
          }else if(comp instanceof ComboBox )
         {	

					((ComboBox) comp).setOwner(this);
				
        	 		this.setLayout(new BorderLayout());
            		
            		add(comp,BorderLayout.CENTER);
            		
            		this.setChild(comp);
            		
               	
         }else if(comp instanceof CheckBox  )
         {	

				((CheckBox) comp).setOwner(this);
				
		         add(comp,BorderLayout.CENTER);
		         
	             this.setChild(comp);
	     
				
         } else if(comp instanceof RadioButton   )
         {	
        	 	((RadioButton) comp).setOwner(this);
        	 	
                add(comp,BorderLayout.CENTER);
                
                this.setChild(comp);
     
				
         } else if(comp instanceof JTextArea )
         {
        	 		this.setChild(comp);
        	 		
            		JScrollPane sp = new JScrollPane(this.getChild());
            	
            		this.setLayout(new BorderLayout());
            	
            		add(sp,BorderLayout.CENTER);
            	
            		this.setChild(sp);
               	
            } else if(comp instanceof ap.mercury.components.List  )
            {
	            	this.setChild(comp);
	            	
	            	((ap.mercury.components.List) comp).setOwner(this);
	            	
	            	ScrollPane sp = ScrollPane.get_instance(this.getChild());
	            		
	            	this.setLayout(new BorderLayout());
	            	
	            	add(sp,BorderLayout.CENTER);
	            	
	            	this.setChild(sp);
	            	
	            	this.getData().setView("JList");
	            	
            }  else if(comp instanceof ap.mercury.components.Tree )
            {
            	this.setChild(comp);
            	
            	((ap.mercury.components.Tree) comp).setOwner(this);
            	
            	ScrollPane sp = ScrollPane.get_instance(this.getChild());
            		
            	this.setLayout(new BorderLayout());
            	
            	add(sp,BorderLayout.CENTER);
            	
            	this.setChild(sp);
            }                           
     
            else if(comp instanceof SliderV )
            {
            	
            	
            	((SliderV) comp).addChangeListener(this);
  	          
            	((SliderV) comp).setOwner(this);
				
        	  	add(comp,BorderLayout.CENTER);
          	
          		this.setChild(comp);
	    		
            }
            else if(comp instanceof SliderH )
            {

            	((SliderH) comp).addChangeListener(this);
  	          
            	((SliderH) comp).setOwner(this);
				
        	  	add(comp,BorderLayout.CENTER);
          	
          		this.setChild(comp);
            }
            
            else if(comp instanceof JTable )
            {
         
         		
    			ScrollPane sp = ScrollPane.get_instance(Orion.accept_default_table_model());
            	
            	this.setLayout(new BorderLayout());
            	
            	add(sp,BorderLayout.CENTER);
            	
            	this.setChild(sp);
            	
            	this.getData().setView("JTable");
            	 
            }  else if(comp instanceof ScrollPane )
            {
            	
            	add(comp,BorderLayout.CENTER);
                
                this.setChild(comp);
                
                Orion o = Orion.get_scroll_pane_child(comp);
	    		
	    		if(o != null)
	    		{
	    			((ScrollPane)comp).setOwner(this);
	    			
	    			o.getIdo().setOwner(this);
	    			
	    			o.getIdo().setPid(this.getIdo().getId());
	     			
	    		}

            }
            else 
            {
                	add(comp,BorderLayout.CENTER);
        
                	this.setChild(comp);
            }
            
     
	}
	
	public void add_listeners()
    {
    	
		    	addMouseListener(this);
		        
				addMouseMotionListener(this);
			
				addComponentListener(this);
				
				
			
	
    }

	private void resize() {

        if (getParent() != null) {
            getParent().revalidate();
        }
    }
	
	public static void main(String[] args) {
		

	}
	
	 public static Orion get_instance(JComponent comp,Rectangle bounds)
	 {
	    
	    	return new Orion(comp,bounds);
	    	
	 }
	
	 public static Orion get_instance(String clazz,Rectangle bounds)
	 {
		 
	    	return new Orion(Live.take_a_live(clazz),bounds);
	    	
	 }
	 
	 
	 public static Orion get_instance(String clazz)
	 {
		 
		 	Orion 	tmp = new Orion(/*JComponent*/Live.take_a_live(clazz),Ru.get_instance());
			
		 			// Setup delete by default.
		 			
		 			tmp.getRights().set_deletable(true);
		 	
		 	if(clazz.equalsIgnoreCase("CommandArea"))
		 	{
		 			//Setup non delete flag for CommandArea.
		 		
		 			tmp.getRights().set_deletable(false);
		 			
		 			//tmp.getRights().set_selectable(false);
		 			
		 			tmp.getState().set_skip_component_resized(true);
		 			
		 			
		 	}
		 	
		 			// Default bounds.
		 	
		 			tmp.setSize(Ru.get_scale_dim(
		 					Application.getCio().getStartup_size(),
		 					new Dimension(2,4))
		 					);
		 			
		 			return tmp;
	    	
	 }
	 
	 
		public void setup_impls() {

			
			this.setIdo(IdentityImpl.get_instance());

			this.setRights(ObjectRightsImpl.get_instance());

			this.setState(ObjectStateImpl.get_instance());

			this.setStyle(ObjectStyleImpl.get_instance());

			this.setDecoro(DecoroImpl.get_instance());
		
			this.setImage(ImageImpl.get_instance());
			
			this.setData(DataImpl.get_instance());
			
			this.setTemp(TempImpl.get_instance());
			

		}
	 
	 	public String update_name()
	 	{
	 		return gl.sf("%s_%03d",this.getChild() != null ? this.getChild().getClass().getSimpleName():this.getClass().getSimpleName(),this.getIdo().getId());
	 	}
	 	
		public void setup_startup_attrs() {

			this.setLayout(null);

			this.setBackground(gl.getRandomColor());

			this.setup_rights();
			
			this.setup_styles();
			
			this.getDecoro().set_align(IAlign.indexOf("none"));
				
			this.getData().setOwner(this);
			
		}
		
	public void setup_rights()
	{

		this.getRights().set_dropable(true);

		this.getRights().set_sizeable(true);

		this.getRights().set_deletable(true);

		this.getRights().set_accessable(true);

		this.getRights().set_focusable(true);

		this.getRights().set_dragable(true);

		this.getRights().set_selectorable(true);
		
		this.getRights().set_selectable(true);
		
		this.getRights().set_visible(true);
		
		
		//gl.tx_k(new Object() {},gl.sf("Установка прав для объекта...%s...%s",
		//		this.getName(),
		//		Bau.to_bin_str_from_int_enrich(this.getRights().get_rights())));
    	  
		
	}

	public void setup_styles()
	{
		
		this.getStyle().set_squared(true);
		
	//gl.tx_k(new Object() {},gl.sf("Установка стилей для объекта...%s...%s",
	//		this.getName(),
	//		Bau.to_bin_str_from_int_enrich(this.getStyle().get_style())));
	  
	
	}

	
	public void mouseExited(MouseEvent me) {
		
		//this.setPreferredSize(new Dimension(1,1));
		
		//this.revalidate();
		
		setCursor(Cursor.getDefaultCursor());
	
	}
	
	 @Override
     public void mouseMoved(MouseEvent me) {
		 
		 //super.mouseMoved(me);
	     //--- 
		 if (hasFocus()) {

			 ResizableBorder resizableBorder = (ResizableBorder) getBorder();
                
                setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(me)));
            }
    
	        //---
		 this.setToolTipText(this.toString());
		
	 }
	 
	 public String get_class()
	 {
		 return gl.sf("%s",Su.BeforeAtFirst(this.getName(),"_"));
	 }
	 
	 @Override
	 /*
	 public String toString()
	 {
		 return gl.sf("%s...%s...%s...%s...%s...%s...%s...%s...%s...%d...Owner...[%s]",
				 this.getIdo().toString(),
				 gl.format(this.getName(),gl.AL.RIGHT,24),
				 
				 Bau.to_bin_str_from_int_enrich(this.getRights().get_rights()),
				 Bau.to_hex_str_from_int(this.getRights().get_rights()),
				 Bau.to_hex_str_from_int_nat(this.getRights().get_rights()),
				 
				 Bau.to_bin_str_from_int_enrich(this.getState().get_state()),
				 SBounds.toString(this.getBounds()),
				 this.getDecoro().get_text(),
				 IAlign.indexOf(this.getDecoro().get_align()),
				 this.getData().getData() != null ? this.getData().getData().length() : gl.E_EMPTY,
				 this.getIdo().getOwner() != null ? this.getIdo().getOwner().getName() : "null"
				 );
	 }
	*/
	
	 /*
	 public String toString()
	 {
		 return gl.sf("%s...%s...state...[%s]...preffered...[%s]...*preffered...[%s]...data...[%d]...owner...[%s]",
				 this.getIdo().toString(),
				 gl.format(this.getName(),gl.AL.RIGHT,24),
				 Bau.to_bin_str_from_int_enrich(this.getState().get_state()),
				 
				 SDimension.toString(getPreferredSize()),
				 this.getData().getPrefered_size(),
				 this.getData().getData() != null ? this.getData().getData().length() : gl.E_EMPTY,
				 this.getIdo().getOwner() != null ? this.getIdo().getOwner().getName() : "null"
				 
				 );
	 }
	
*/
	 public String toString()
	 {
		 return gl.sf("%s...%s...state...[%s]...preffered...[%s]...*preffered...[%s]...data...[%d]...owner...[%s]...align...[%s]",
				
				 this.getIdo().toString(),
				 gl.format(this.getName(),gl.AL.RIGHT,24),
				 Bau.to_bin_str_from_int_enrich(this.getState().get_state()),
				 SDimension.toString(getPreferredSize()),
				 this.getData().getPrefered_size(),
				 this.getData().getData() != null ? this.getData().getData().length() : gl.E_EMPTY,
				 this.getIdo().getOwner() != null ? this.getIdo().getOwner().getName() : "null",
				 IAlign.indexOf(this.getDecoro().get_align())
				 
				 );
	 }
	
	 /*
	 public boolean check_pref_size()
	 {
		 if (
				 this.getPreferredSize().equals(new Dimension(1,1)) 
			)
		 {
			 if (
					  this.getDecoro().getPrefered_size() != null &&
					 !this.getDecoro().getPrefered_size().equals(new Dimension(0,0))
				)
			 {
				 gl.tx_k(new Object(){},gl.sf("Проверка необходимости обновления prefered size...%s","Ok"));
			 
				 return true;
			 }
		 }
		 	 	 return false; 
	 }
	 */
	 
	 /*
	 public String toString()
	 {
		 return gl.sf("Orig...%s...Bounds...%s...Zoom...%s",
				 	SBounds.toString(this.getDecoro().getOrigin_bounds()),
				 	SBounds.toString(this.getBounds()),
				 	this.getDecoro().getZoom() == null ? "null" : this.getDecoro().getZoom().toString()			 			
				 );
	 } 
	 */
	 
	 
	 
	 public Orion insert(List<Orion> orions) 
	 {
		 orions.forEach(o->
		 {
			 	this.insert(o);
		 });
		 
		 return orions.get(orions.size() - 1);
	 }
	

	 public Orion insert(Vector<Orion> orions) 
	 {
		 orions.forEach(o->
		 {
			 	this.insert(o);
		 });
		 
		 return orions.get(orions.size() - 1);
	 }
	
	 public Orion insert(Orion ... childs) 
	 { 
			for (Orion arg : childs) 
			{ 
				this.insert(arg); 
			} 
			
			return this;
	 } 
	 
	 public Orion insert(Orion child)
		{
			
		 
		 	try
		 	{
		 		
		 		if (child == null)
		 		{

			 		gl.tx_e(new Object() {},gl.sf("Объект отсутствует для лендинга...%s"));
	          		
		 			return null;
		 		}
		 		
		 			this.add(child);
		 		
				if(!this.is_desk_top())
				{	
				
					child.setBounds(Ru.norm4g(this.get_abs_bounds(),child.get_abs_bounds()));
					
				}
					// --- Update [Pid].
			
					child.getIdo().setPid(this.getIdo().getId());
			
					// --- Update [Owner]
			
					child.getIdo().setOwner(this);
					
					// --- Update [Level].
					
					child.getIdo().setLevel(child.getIdo().getOwner().getIdo().getLevel()+1);
					
					// --- Update background.
					
					if(!child.is_orion_pure())
						child.setBackground(this.getBackground());
					
					// --- Индекс.
					
					//child.getIdo().setIndex(this.get_components().size()-1);
					
					// --- Сброс флагов.
					
					child.getState().set_focused(false);
					
					//child.getState().set_selected(false);
					
					
					return this;
			
		 	}
		 	catch(Exception e)
		 	{
		 			gl.tx_e(new Object() {},gl.sf("Exception...%s",e.toString()));
          	  
		 			return null;
		 	}
			
		}

	 
	 @Override
	 // This string was written by Cat :-)
	 //public void 31`877oooooooooooooooooooooooooooooooooooooooiiiiiiiiiiiiiiiiiii88888u-(MouseEvent me)
	 public void mouseDragged (MouseEvent me)
     { 		
		 
		 // Надо четко определять мы изменяем размер объекта или перемещаем его.
     
		 if(this.get_Cursor() == Cursor.MOVE_CURSOR)
		 {
			 
			 if(this.getRights().is_dragable())
			 {
			 
				  		moved_only(me);

				  		move_other_selected_too();
			 		
			 	 		looking_for_target();
			 		
			 }
			 else
			 {
				 // Increment. 
				 
				 set_dx_dy(me);
				 
				 // Selection.
				 
				 activate_selector();
			
			 }
		 }
		 else
		 {
			 	resized_only(me);
			 	
			 	//resize_other_selected_too(me);
		 }
     
     }
	 
	 public boolean resize_other_selected_too(MouseEvent me)
		{
			
			try 
			{
				if (!this.getState().is_selected())
					return false;
				
				CollectionImpl.selected.forEach(a -> {

					if (a != this)
					{
			
						
						Rectangle b =  a.getBounds();
						
								if(me.isControlDown())
								{
									if (
											a.getDecoro().getDistribution().getWidth()  != gl.E_EMPTY &&
											a.getDecoro().getDistribution().getHeight() != gl.E_EMPTY
									   )
									{
									 b.x = b.x + this.getDecoro().getEndPos().x;
									
									 b.y = b.y + this.getDecoro().getEndPos().y;
									}
								}
						
								  	b.width = this.getBounds().width;
								  
								  	b.height = this.getBounds().height;
								  
								  	a.setBounds(b);
								  	
						
					}	
						
				});
			
					return true;
			 }
	 		catch(Exception e)
	 		{
	 				return false;
	 		}
			
		}
	 @Override
     public void mousePressed(MouseEvent me) {
		 
		 // ---
		 
		 	ResizableBorder resizableBorder = (ResizableBorder) getBorder();
            
            cursor 				= resizableBorder.getCursor(me);
            
            this.getDecoro().setStartPos(me.getPoint());


           	// Обработка состояния фокусировки.
            
            if(this.getRights().is_focusable())
           	{
            	
            	// Проверка если этот объект уже сфокусирован.
            	
            	if(this.getState().is_focused())
            	{
	            	gl.tx_k(new Object() {},gl.sf("Объект уже в ФОКУСЕ...%s",this.getName()));

	            	return;
            	}
	        
            	
            	
            	// Сброс ранее сфокусированного объекта.
            
	             CollectionImpl.reset_focused(null);
	        
	             // Установка фокуса.
	             
	             requestFocus();
	            
	             // Установка состояния.
	        
	         	this.getState().set_focused(true);
	            
	         	// Проверка захвата фокуса.
	         	
	         	gl.tx(new Object() {},this.getState().is_focused(),gl.sf("Установка фокуса на...%s",this.getName()));
	         	
	         	this.repaint();
	             
           	}
            else
            {
            	gl.tx_k(new Object() {},gl.sf("Установка фокуса ЗАПРЕЩЕНА на объекте...%s",this.getName()));
	         	
            }
           		
           	
   		 	// Установка состояния выборки.
            
            if(this.getRights().is_selectable() && me.isControlDown())
            {
            	if(this.getState().is_selected())
	            	{
	            		gl.tx_k(new Object() {},
	            				gl.sf("Объект уже СЕЛЕКТИРОВАН инверсия...%s",
	            						this.getName()));
	        		
						 this.getState().set_selected(!this.getState().is_selected());
						 
	            	}
	            	else
	            	{

	            		gl.tx_k(new Object() {},
	            				gl.sf("Объект СЕЛЕКТИРОВАН ...%s",
	            						this.getName()));

						 this.getState().set_selected(true);
						
	            	}
            	
            		 	 this.repaint();
            }
            else
            {
            	gl.tx_k(new Object() {},
        				gl.sf("Свойство выборки ЗАПРЕЩЕНО для объекта...%s",
        						this.getName()));
	            }
		 // ---
		 
		 if(!this.getRights().is_accessable())
		 {
			 gl.tx_k(new Object() {},gl.sf("ДОСТУП запрещен к объекту...%s",this.getName()));
				
			 return;
		 }
		 
		 if(!this.getRights().is_dragable())
		 {
			 gl.tx_k(new Object() {},gl.sf("Релокация ЗАПРЕЩЕНА для объекта...%s",this.getName()));
		 }
		 
		 if(!this.getRights().is_sizeable())
		 {
			 gl.tx_k(new Object() {},gl.sf("Реcайзинг ЗАПРЕЩЕН для объекта...%s",this.getName()));
		 }
			 
		 if(!this.is_desk_top())
		 {
			 pull_to_top();
		 }
		 else
		 {
			 
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
		 } 
		
	 }
	
	 
	@Override
	public void mouseClicked(MouseEvent me) 
	{
		CollectionImpl.reset_focused(this);
		 
		 CollectionImpl.reset_selected(this);
		 
		 gl.tx(new Object() {},( true),gl.sf("Сброс состояния объекта...[%s]",this.getName()));
		
	}
	
	 public void reset_drag()
	 {
	
			this.getState().set_dragged(false);
	 		
 			this.getState().set_resized(false);
 
	 }
	 
	
	 
	 @Override
	 public void mouseReleased(MouseEvent e) {
	        
		 	// ---
		 	
		 	this.getDecoro().setStartPos(null);
     	
     		this.getDecoro().setEndPos(null);
     	
     	
			this.getState().set_resized(false);
			
			this.getState().set_dragged(false);
			
		 
		 	// Dances on the desktop.
      
      if (!this.getRights().is_dragable())
		 {
   
    	  	Rectangle rect = this.getDecoro().get_selector();
    
    	 	gl.tx_k(new Object() {},gl.sf("Выполнена операция выделения области на объекте...%s...регион...%s",
    	  			this.getName(),
    	  			SBounds.toString(rect)));
     	 
     	 
     	 	// Check size.
     	 
     	 	if(!check_valid_size(rect) || selection_post_process() || check_none_process())
     	 	{
     	 		reset_mouse_released();
		     	
     	 		return;
     	 	}	
     	 	
     	 	
     	 			gl.tx_k(new Object() {},gl.sf("Попытка создать объект типа...%s...размер...%s",
     	 			Live.brew_type,
    	  			SBounds.toString(rect)));
     	 
	     	    // Make an Object.
     	    
	     	    make_an_object(rect);
	     	    
	     	    
	     	    reset_mouse_released();
		     	  
	     	  	return;
     	 			
		 } // Desktop area end.
      
      					
      			landing_at_target();
      		
      			// Сброс параметров.

      			reset_mouse_released();
      			
	 }

		public void reset_mouse_released()
		{
			gl.tx_k(new Object() {},gl.sf("Сброс параметров в точке mouse released...%s",this.getName()));
			
  			this.getState().set_resized(false); 

     	    this.getState().set_dragged(false); 
	     	
  			
     	    this.getDecoro().setOrigin_bounds(this.getBounds());
  			
  			this.getDecoro().setZoom(null);
  			
  			
  			this.get_all_childs().forEach(a->{
  				
  				a.getDecoro().setZoom(null);
  				
  				a.getDecoro().setOrigin_bounds(a.getBounds());
  			});

			
		}
		
		@Override 
		public void paintComponent(Graphics g) { 
				
				
				paint_body(g);
				
				paint_image(g);
				
				paint_grid(g);
			
				paint_selector(g);
				
				paint_text(g);
				
			
		}
		
		
		public void paint_body(Graphics g) { 
			 
				Graphics2D g2 = (Graphics2D)g; 
	
				Rectangle wr = new Rectangle(0,0,this.getBounds().width,this.getBounds().height);
				
				if(this.getStyle().is_rounded())
				GU.fillRoundRect(g2, wr,this.getBackground());
				else	
				GU.fillAlphaRect(g2,wr,this.getBackground());
			    
				/*
				GU.drawGradient(g2, wr, wr, 
						CU.getAlphaColor(Color.black,60),
						CU.getAlphaColor(Color.white,60),
						gl.ALI.RIGHT);
				*/
		} 
		
		public void paint_image(Graphics g) { 

				Graphics2D g2 = (Graphics2D) g; 
	
				Rectangle wr = Ru.norm4g(this.getBounds()); 
	
				if (this.getImage().getImage() != null) { 
					GU.drawImage(g2, this.getImage().getImage(), wr); 
				} 
		} 
		
		public void paint_selector(Graphics g) { 
			 
			if (!this.getRights().is_selectorable())
				return;
			
				Graphics2D g2 = (Graphics2D) g; 
				 
				Stroke p_stroke = g2.getStroke(); 
			
				g2.setStroke(gl.dashed_stroke_solid); 
				 
				Rectangle span = Ru.get_spanned_rect(this.getDecoro().get_selector(),new Insets(0,0,0,0)); 
				
				//GU.drawAlphaLine(g2, new Point(span.x,span.y),new Point(span.x+span.width,span.y),Color.white,0.5f);
				 
				GU.draw_alpha_rect(g2,span,Color.white,0.7f);
				
				GU.fillAlphaRect(g2,span,Color.white,0.1f); 
				
				
				g2.setStroke(p_stroke); 

		} 
		
		public void paint_selected(Graphics g) { 
			 
			if (!this.getState().is_selected())
				return;
			
				Graphics2D g2 = (Graphics2D) g; 
				 
				Stroke p_stroke = g2.getStroke(); 
				
				
				g2.setStroke(gl.dashed_stroke_solid); 
				 
				//Rectangle span = Ru.get_spanned_rect(this.getBounds(),new Insets(0,0,0,0)); 
				
				Rectangle span = Ru.get_spanned_rect(Ru.norm4g(this.getBounds()),new Insets(ResizableBorder.DIST,ResizableBorder.DIST,ResizableBorder.DIST*2,ResizableBorder.DIST*2)); 
				
				GU.drawAlphaRect(g2,span,Color.white); 
				
				
				g2.setStroke(p_stroke); 

		} 
		
		public void paint_text(Graphics g) { 
			 
			 
			if(this.getDecoro().get_text() == null)
			{
				return ;
			}
				
			Graphics2D g2 = (Graphics2D) g; 

			Rectangle wr = Ru.norm4g(this.getBounds()); 
	 
			Dimension font_dim = gl.getFontDim(g2, this.getFont(), this.getDecoro().get_text()); 

			Rectangle text_align = this.getDecoro().getTextAlign(); 

			AreaManager am = new AreaManager(new Dimension(wr.width, wr.height), 
					new Dimension(3, 3)); 

			Rectangle am_rect = am.get(new Dimension(text_align.x, text_align.y)); 

			Rectangle text_rect = null; 

			AreaManager am_intro = new AreaManager(new Dimension(am_rect.width, 
					am_rect.height), new Dimension(3, 3)); 

			text_rect = am_intro.get(new Dimension(text_align.width, 
					text_align.height)); 

			if (font_dim.width > text_rect.width) { 
				text_rect = am_rect; 
			} else { 
				text_rect.x += am_rect.x; 

				text_rect.y += am_rect.y; 
			} 

				GU.drawAlphaString(g2,this.getDecoro().get_text(), 
					(text_rect.x + (text_rect.width /2))   - font_dim.width / 2, 
					(text_rect.y + (text_rect.height / 2)) + font_dim.height /4 , 
					this.getDecoro().getTextColor(), this.getFont()); 
			
		} 
		
	
		public void paint_grid_1(Graphics g) { 

		
			if(this.getDecoro().getGrids() == null)
			{
				return;
			}
				
			//gl.sfn("---> paint...grids...%s",this.getDecoro().getGrids() != null);
			
			if (this.getTemp().getDims().size() == gl.E_EMPTY)
			{
				if(!		
						ap.mercury.components.parser.Dimension.parse(this.getDecoro().getGrids(),this.getTemp().getDims())
				)
				{
					
						//gl.sfn("---> paint...size...%d...",this.getTemp().getDims().size());
								
						return ;
				}
			}
			
			this.getTemp().getDims().forEach(di -> {
			
				Graphics2D 	g2 = (Graphics2D) g; 
				
				Rectangle 	wr = Ru.norm4g(this.getBounds()); 
					
				Dimension 	target_dim = di.getDimension();
			
				if(di.isFixed())
				{
					int e_width = wr.width/((target_dim.width == gl.E_EMPTY) ? 1 : target_dim.width);
				
					int e_height = wr.height/((target_dim.height == gl.E_EMPTY) ? 1 : target_dim.height);
				
					target_dim = new Dimension(e_width,e_height);
						
				}
				
				AreaManager grid_am = new AreaManager(new Dimension(wr.width, wr.height),target_dim); 

			// Default colors.
				
			Color  [] color_from 	= 	{ CU.getAlphaColor(Color.white,120)};
				
			Color  [] color_to 		=   { CU.getAlphaColor(Color.white,0)};
			
			// Catch.
			
			if(di.getColor_from() != null)
			{
				color_from[0] = di.getColor_from();
			}
			
			if(di.getColor_to() != null)
			{
				color_to[0] = di.getColor_to();
			}
			
			int f = (int)di.getFat();
				
			List<Integer> v_bans = Arrays.asList(new Integer[]{-1,-1});;//Arrays.asList(new Integer[]{0,target_dim.width});
			
			grid_am.getGridLinesV().forEach(v -> { 
					
				Color cf = color_from[0];
				
				Color ct = color_to[0];
				
						
				TGridLine top  = TGridLine.get_instance(
							v.getStart(),
							new Point(v.getEnd().x,v.getEnd().y/2),
							v.getIndex()
						);
				
				TGridLine bottom  = TGridLine.get_instance(
							new Point(v.getStart().x,v.getEnd().y/2),
							v.getEnd(),
							v.getIndex()
						);
				
				
				GU.draw_alpha_line_v_fat_ban(g2,wr,top,cf,ct,gl.ALI.TOP,f,v_bans);

				GU.draw_alpha_line_v_fat_ban(g2,wr,bottom,ct,cf,gl.ALI.TOP,f,v_bans);

				
			}); 
			
			
			
			
			grid_am.getGridLinesH().forEach(h -> { 


				Color cf = color_from[0];
				
				Color ct = color_to[0];
				
			
				TGridLine left  = TGridLine.get_instance(
						h.getStart(),
						new Point(h.getEnd().x/2,h.getEnd().y),h.getIndex());
				
				TGridLine right  = TGridLine.get_instance(
						new Point(h.getEnd().x/2,h.getStart().y),h.getEnd(),h.getIndex());
				
				
				GU.draw_alpha_line_h_fat_ban(g2,wr,left,cf,ct,gl.ALI.LEFT,f,v_bans);

				GU.draw_alpha_line_h_fat_ban(g2,wr,right,ct,cf,gl.ALI.LEFT,f,v_bans);
				
			});
			
			
			
		  });
			
		} 


		public void paint_grid(Graphics g) { 

		
			if(this.getDecoro().getGrids() == null)
			{
				return;
			}
				
			//gl.sfn("---> paint...grids...%s",this.getDecoro().getGrids() != null);
			
			if (this.getTemp().getDims().size() == gl.E_EMPTY)
			{
				if(!		
						ap.mercury.components.parser.Dimension.parse(this.getDecoro().getGrids(),this.getTemp().getDims())
				)
				{
					
						//gl.sfn("---> paint...size...%d...",this.getTemp().getDims().size());
								
						return ;
				}
			}
			
			this.getTemp().getDims().forEach(di -> {
			
				Graphics2D 	g2 = (Graphics2D) g; 
				
				Rectangle 	wr = Ru.norm4g(this.getBounds()); 
					
				Dimension 	target_dim = di.getDimension();
			
				if(di.isFixed())
				{
					int e_width = wr.width/((target_dim.width == gl.E_EMPTY) ? 1 : target_dim.width);
				
					int e_height = wr.height/((target_dim.height == gl.E_EMPTY) ? 1 : target_dim.height);
				
					target_dim = new Dimension(e_width,e_height);
						
				}
				
				AreaManager grid_am = new AreaManager(new Dimension(wr.width, wr.height),target_dim); 

			// Default colors.
				
			Color  [] color_from 	= 	{ CU.getAlphaColor(Color.white,120)};
				
			Color  [] color_to 		=   { CU.getAlphaColor(Color.white,0)};
			
			// Catch.
			
			if(di.getColor_from() != null)
			{
				color_from[0] = di.getColor_from();
			}
			
			if(di.getColor_to() != null)
			{
				color_to[0] = di.getColor_to();
			}
			
			int f = (int)di.getFat();
				
			List<Integer> v_bans = Arrays.asList(new Integer[]{-1,-1});;//Arrays.asList(new Integer[]{0,target_dim.width});
			
			
			grid_am.getRects().forEach(r->{
				
				//GU.draw_alpha_rect(g, rect, pcolor);
				//GU.draw_alpha_rect(g2, r,color_from[0]);
				
				GU.fill_alpha_rect(g2, Ru.Scale(r,new Dimension(f,f)),gl.get_random_color());
			});
			
		  });
			
		} 

		public List<Orion> get_childs() 
		{ 
			return CollectionImpl.items.stream().filter(b->
			( 
					b.getIdo().getPid() == this.getIdo().getId() && b.getRights().is_deletable() 
			)
			).collect(Collectors.toList());
			 
		}
		
		public void  gather_childs() 
		{ 
			List<Orion> wrk =  this.get_components();
			
			wrk.forEach(a->{
				
				a.gather_childs();
				
			});
				
			 CollectionImpl.childs.addAll(wrk);
		}
		

		public void  gather_childs_i() 
		{ 
			List<Orion> wrk =  this.get_childs();
			
			wrk.forEach(a->{
				
				a.gather_childs_i();
				
			});
				
			 CollectionImpl.childs.addAll(wrk);
		}
		
		public List<Orion> get_all_childs()
		{
			CollectionImpl.childs.clear();
			
			this.gather_childs();
			
			return CollectionImpl.childs;
			
		}
		
		public List<Orion> get_all_childs_i()
		{
			CollectionImpl.childs.clear();
			
			this.gather_childs_i();
			
			return CollectionImpl.childs;
			
		}
		
		
		public List<Orion> get_last()
		{
			return
					
		 this.get_all_childs()
			.stream() 
			.sorted(Comparator.comparing(s -> ((Orion)s).getIdo().getLevel()).reversed()) 
			.collect(Collectors.toList()); 
			
		}
		
		public Orion get_last_item()
		{
			
			if(get_last() != null && get_last().size() != gl.E_EMPTY )
				return get_last().get(gl.E_EMPTY);
			
				return null;
		}
		
		
		public boolean isa_top() 
		{ 
			return ( this.getIdo().getIndex() == gl.E_EMPTY); 
		} 
		 
		public boolean is_desk_top() 
		{ 
			return (this.getIdo().getPid() == gl.E_ERROR); 
		} 
		

		

		public  boolean pull_to_top() 
		{ 
						
						Orion parent = this.getIdo().getOwner();
			
						boolean bl_parent = (parent != null);
					
						if (!gl.tx(new Object() {},bl_parent,gl.sf("Родительский объект...%s",
					                          bl_parent ? parent.getName() : "null" )))
						{
							return false;
						}
			
						boolean bl_ready = this.isa_top() && (null == CollectionImpl.get_tops_except_me(parent.get_components(), this.getIdo().getId()));
								
						if (gl.tx(new Object() {},bl_ready ,
									gl.sf("Объект уже ТОП...%s",
									this.getName()
									))
						)
						{	
							return true;
						}
			  
						
						
					try 
					{			
						
				
						List<Orion> m_list = parent.get_components();
						
						// The rest components push to down;
						
						int [] m_index = {gl.E_OK}; 
						
						// Даем индексы сначала селектированным, затем остальным.
						
						m_list.forEach(a->
						{
							if (!a.getName().equalsIgnoreCase(this.getName())) 
							{
								parent.setComponentZOrder(a, m_index[0]);

								a.getIdo().setIndex(m_index[0]);
								
								m_index[0]++;

							}
							else
							{
								a.getIdo().setIndex(gl.E_EMPTY);
							
								parent.setComponentZOrder(a,a.getIdo().getIndex());

							}
							
							
							//gl.tx_k(new Object() {},
							//		gl.sf("Обновление индекса...[%d]...для...[%s]",
							//		a.getIdo().getIndex(),
							//		a.getName()));
				
						});
						
								return true; 
			} 
			 
			catch(Exception e) 
			{ 
								return gl.tx(new Object() {},false,gl.sf("[%s]",e.getMessage()));
			} 
				 
		} 

		public static List<Orion> layoutOf(List<Orion> orions,int dx,int dy,int width,int height)
		{
			
			int [] m_dx = { 0 };
			
			int [] m_dy = { 0 };
			
			orions.forEach(o->
			{
					m_dx[0] += dx;
					
					m_dy[0] += dy;
					
					o.setBounds(new Rectangle(m_dx[0],m_dy[0],width,height));
			});
			
			List<Orion> orions_to = new ArrayList<Orion>();
			
			orions_to.addAll(orions);
			
			//return orions.stream().collect(Collectors.toList());
			
			return orions_to;
		}
		
		public Rectangle get_abs_bounds() {
			

			List<Integer> 	m_x_acc = new ArrayList<Integer>();
			
			List<Integer> 	m_y_acc = new ArrayList<Integer>();
			
							this.get_root_x(m_x_acc);
					
							this.get_root_y(m_y_acc);
					
			int 			m_sum_x_acc = m_x_acc.stream().mapToInt(i->i).sum();
					
			int 			m_sum_y_acc = m_y_acc.stream().mapToInt(i->i).sum();
			
			if(this.getIdo().getOwner() != null && this.getIdo().getOwner().is_desk_top())
			   return this.getBounds();
			
			   return new Rectangle(m_sum_x_acc+this.getBounds().x,m_sum_y_acc+this.getBounds().y,this.getBounds().width,this.getBounds().height);
		
		}
		
		public void get_root_x(List<Integer> x_acc) 
		{ 
			Orion owner = this.getIdo().getOwner(); 
			
			if( owner == null || owner.is_desk_top()) 
				return ; 
			 
			 x_acc.add(owner.getBounds().x);
			 
			 		
			 owner.get_root_x(x_acc); 
		}
		
		public void get_root_y(List<Integer> y_acc) 
		{ 
			Orion owner = this.getIdo().getOwner(); 
			 
			if( owner == null || owner.is_desk_top()) 
				return ; 
			 
			 y_acc.add(owner.getBounds().y);
			
			 owner.get_root_y(y_acc); 
		} 
	
		public static Orion get_scroll_pane_child(Component comp)
		{
		
			try
			{
			
				ScrollPane child = (ScrollPane)comp;
		
			
			 if(child == null)
			 {
				 return null;
			 }
		 
			 	JViewport jvp 	=  child.getViewport();
			 
				Component jv 	= jvp.getView();
				
				Orion    o = (Orion)jv;
		 
				return o;
			}
				catch(Exception e)
				{
					return null;
				}
		}
		
		
		public List<Orion> get_components() 
		 { 
				Component [] cos = this.getComponents(); 
				 
				List<Orion>  Orions = new ArrayList<Orion>(); 
				 
				for(Component co : cos) 
				{ 
					if( co instanceof Orion) 
					{ 
						Orions.add((Orion)co); 
					}
					else
					{
						//gl.sfn("(get_components)------->%s",co.getClass().getSimpleName());
						
						if (co.getClass().getSimpleName().equalsIgnoreCase("ScrollPane"))
						{
							Orion o = this.get_scroll_pane_child(co);
							
							if(o != null)
							{
								Orions.add(o);
							}
							
							//gl.sfn("(get_components(Deep inside))------->%s",co.getClass().getSimpleName());
							
							//ScrollPane m_child = (ScrollPane)co;
					
							
							
							//gl.sfn("(get_components(Deep inside 1))------->%s",m_child.getClass().getSimpleName());
						
							/*
							 if(m_child != null)
							 {
								 	JViewport jvp 	=  m_child.getViewport();
								 
									Component jv 	= jvp.getView();
									
									Orion    o = (Orion)jv;
									
									if(o != null)
									{
										//gl.sfn("(get_components(Inside 2))------->%s...[%s]",
										//		o.getClass().getSimpleName(),
										//		o.toString()
										//		);
								
										// Добавляем в список дочерних объектов.
										
										Orions.add(o);
										
									}
							 }
								*/
							
						}
					}
				} 
			
					return 
							Orions.stream() 
							.sorted(Comparator.comparing(s -> ((Orion)s).getIdo().getIndex()).reversed()) 
							.collect(Collectors.toList()); 
		 } 
		 
		public boolean re_repaint() 
		 { 
			 
			 this.repaint(); 
			 
			 return true; 
		 } 
		
		public void move_dx_dy(int dx,int dy) 
		{ 
			this.setLocation(this.getLocation().x+dx,this.getLocation().y+dy); 
		} 
		
		
		public void accept_area() 
		{

			int m_align = this.getDecoro().get_align();
			
			if(m_align == IAlign.indexOf("none"))
			{
				//gl.tx_k(new Object() {},gl.sf("Компоновки нет для...%s",this.getName()));
				
				return;
			}
			
			//gl.tx_k(new Object() {},gl.sf("Применение компоновки...%s...для...%s",IAlign.indexOf(m_align),this.getName()));
			
			
			Rectangle 	m_bounds 		= this.getDecoro().getOrigin_bounds();
			
			Rectangle	m_parent		= this.getIdo().getOwner().getBounds();
			
			Rectangle	m_parent_orig	= this.getIdo().getOwner().getDecoro().getOrigin_bounds();
			

			if(m_bounds == null || m_parent == null)
			{
				gl.tx_k(new Object() {},gl.sf("Bounds is null...%s",this.getName()));
				
				return;
			}
			
			if(this.getDecoro().getZoom() == null)
			{
			
				this.getDecoro().setZoom(Zoom.get_instance(m_bounds,m_parent));
				
				//gl.tx_k(new Object() {},gl.sf("Инициализация зум...%s...%s",this.getName(),this.getDecoro().getZoom().toString()));

				return;
			}
			

				Rectangle	m_rect = null;


				if(m_align == IAlign.indexOf("basexy"))
				{
					

					double		m_x_dx        	= ( m_parent.width - m_parent_orig.width ) 		* this.getDecoro().getZoom().getZoom_rect().getX();
												
					double		m_y_dy          = ( m_parent.height - m_parent_orig.height ) 	* this.getDecoro().getZoom().getZoom_rect().getY();

					
					/*
					double		m_width_dx      = (m_parent.width - m_parent_orig.width) * this.getDecoro().getZoom().getZoom_rect().getWidth();

					double		m_height_dy     = (m_parent.height - m_parent_orig.height) * this.getDecoro().getZoom().getZoom_rect().getHeight();
				
					*/
				
					double		m_d_x			=  m_bounds.x		+ m_x_dx;
				
					double		m_d_y			=  m_bounds.y		+ m_y_dy;
				
					/*
					double 		m_d_width 		=  m_bounds.width 	+ m_width_dx;

					double 		m_d_height 		=  m_bounds.height 	+ m_height_dy;

					*/
								m_rect = new Rectangle(
										(int)m_d_x,
										(int)m_d_y,
										m_bounds.width,
										m_bounds.height);
				
				} else

			if(m_align == IAlign.indexOf("basexywh"))
			{
				

				double		m_x_dx        	= ( m_parent.width - m_parent_orig.width ) 		* this.getDecoro().getZoom().getZoom_rect().getX();
											
				double		m_y_dy          = ( m_parent.height - m_parent_orig.height ) 	* this.getDecoro().getZoom().getZoom_rect().getY();

				
				double		m_width_dx      = (m_parent.width - m_parent_orig.width) * this.getDecoro().getZoom().getZoom_rect().getWidth();

				double		m_height_dy     = (m_parent.height - m_parent_orig.height) * this.getDecoro().getZoom().getZoom_rect().getHeight();
			
			
				double		m_d_x			=  m_bounds.x		+ m_x_dx;
			
				double		m_d_y			=  m_bounds.y		+ m_y_dy;
			
				double 		m_d_width 		=  m_bounds.width 	+ m_width_dx;

				double 		m_d_height 		=  m_bounds.height 	+ m_height_dy;

			
							m_rect = new Rectangle((int)m_d_x,(int)m_d_y,(int)m_d_width,(int)m_d_height);
			
			}
			

							this.setBounds(m_rect);
		
			
		}
		
		@Override
		public void componentResized(ComponentEvent e) {
			
			//gl.sfn("Component resized...%s",this.getName());
			
			if(this.getBounds() == null)
			{	
				gl.tx_k(new Object() {},gl.sf("Bounds still is null...%s",this.getName()));
					
				return;
			}
			
			this.getState().set_resized(true);
			
			if(this.getDecoro().getOrigin_bounds() == null)
			{
				this.getDecoro().setOrigin_bounds(this.getBounds());
			}
			
			/*
			gl.sfn("---> %s...%s...%s...Child...%s",
					this.getName(),
					this.get_class(),
					this.getClass().getSimpleName(),
					this.getChild().getClass().getSimpleName());
			*/
			if ( this.getChild() instanceof Orion )
			{	
				// Выполняем обработку только при флаге.
				
				/*
				gl.sfn(" Inline ---> %s...%s...%s...Child...%s",
						this.getName(),
						this.get_class(),
						this.getClass().getSimpleName(),
						this.getChild().getClass().getSimpleName());
				*/
				
				if(!this.getState().is_skip_component_resized())
				{
					
					//this.get_childs().forEach(a->
					this.get_components().forEach(a->
					{
					
						//gl.sfn("---> Push accept for...%s",a.getName() );
						
						a.accept_area();
						
						a.componentResized(e);
					});
					
				}
				else
				{
					//gl.tx_k(new Object() {},gl.sf("Выключен режим обработки ресайзинга для компонента...%s",this.getName()));
				}
				
					this.getState().set_resized(false);
				
					return;
			}
			
					// Для дочернего компонента если это не панель.
				
					this.getChild().setBounds(Ru.get_spanned_rect(Ru.norm4g(this.getBounds()),new Insets(ResizableBorder.DIST,ResizableBorder.DIST,(ResizableBorder.DIST*2)-1,(ResizableBorder.DIST*2)-1)));
					
					this.getState().set_resized(false);
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			
		}

		public boolean move_other_selected_too()
		{
			
			try 
			{
				if (!this.getState().is_selected())
					return false;

				CollectionImpl.selected.forEach(a -> {

					if (a != this)
					{
						a.move_dx_dy(
								this.getDecoro().getEndPos().x, 
								this.getDecoro().getEndPos().y
								);
					}	
						if(!a.is_group())
							a.set_group(true);
						
					
				});

					return true;
			 }
	 		catch(Exception e)
	 		{
	 				return false;
	 		}
			
		}

		public boolean looking_for_target()
		{

			try
			{
			
				this.setTarget(CollectionImpl.get_target_item(this));
		
	 			return (this.getTarget() != null);
			}
	 		catch(Exception e)
		 	{
		 			return false;
		 	}	
		}


		
		public void activate_selector()
		{
			
            this.getDecoro().set_selector(
            		Ru.get_point_rect(
            				this.getDecoro().getStartPos(),
            				this.getDecoro().getEndPos()
            				));
      	  	
      	  	CollectionImpl.get_selection_items(this);
      	  
            this.repaint(Ru.Scale(this.getDecoro().get_selector(),new Dimension(20,20)));
          
      	  	
		}

		public boolean selection_post_process()
		{
		
     	 		reset_selector_process();
     	 		
     	 		gl.tx(new Object() {},true,gl.sf("Селектированы объекты для дальнейшего процессинга...%d",CollectionImpl.selected.size()));
     	 		
     	 		return (CollectionImpl.selected.size() != gl.E_EMPTY);
		}
		
		public void reset_selector_process()
		{
 	 			this.getDecoro().set_selector(Ru.get_init_rect(gl.E_EMPTY));
 	 		
 	 			this.repaint();
 	 	
		}
		
		public void shift(int dx,int dy)
		{

           Rectangle bounds = this.getBounds();
            
            bounds.translate(dx,dy);

            this.setBounds(bounds);
            
            this.resize();

			
		}
		
		public boolean make_an_object(Rectangle rect)
		{
	    	Orion 	just = Orion.get_instance(Live.brew_type);
	    	
	    	gl.tx_k(new Object() {},gl.sf("Попытка создать объект типа...[%s]...размер...[%s]...",Live.brew_type.toUpperCase(),SBounds.toString(rect)));
	 	    
	   	
 	    	if ( this.insert(just) != null)
 	    	{
 	    		just.setBounds(rect);
 	    			
 	    		just.repaint();
 	    		

 	    		gl.tx(new Object() {},true,gl.sf("Создан объект типа...[%s]...размер...[%s]...",Live.brew_type.toUpperCase(),SBounds.toString(rect)));
 	    		 	    		 
	      	    reset_selector_process();
	     	    
	      	    shift(gl.E_OK,gl.E_OK);
	      	    
	      	    shift(gl.E_ERROR,gl.E_ERROR);
	      	    
 	    		return true;
 	    	}
 	    	
 	    		gl.tx_e(new Object() {},gl.sf("Ошибка создания объекта типа...[%s]...размер...[%s]...",Live.brew_type.toUpperCase(),SBounds.toString(rect)));
	    			    		 
	      	    reset_selector_process();
	     	    		
 	    		return false;
 	    		
 	    }
		
		
		public boolean landing_at_target()
		{
			
			if(this.getTarget() == null )
			{
				//gl.tx_k(new Object() {},gl.sf("Target is null..."));
			     	  	
				return false;
			}
				
			
			
			if (
	      				this.getTarget().getIdo().getId() != this.getIdo().getPid() 	
	      		)
	      		{
	    
					gl.tx_k(new Object() {},gl.sf("Попытка лендинга...%s...на...%s",
	     	  			 this.getName(),
	       	  			 target.getName()));
	           	 
	           	  	
					if(CollectionImpl.selected.size() != gl.E_EMPTY)
					{
						//gl.tx_k(new Object() {},gl.sf("---> Попытка лендинга...%s...на...%s",
			     	  	//		 this.getName(),
			       	  	//		 target.getName()));
			           	 
						
						CollectionImpl.selected.forEach(a->
						{
							if (a.is_group() && 
								this.getTarget().getBounds().contains(a.getBounds()) 
								)
							{
								this.getTarget().insert(a);
								
								gl.tx_k(new Object() {},gl.sf("Лендинг селектированного...%s...на...%s",
					     	  			 a.getName(),
					       	  			 target.getName()));
					         
							}
						});
						
						// Reset.
						CollectionImpl.selected.forEach(a->
						{
							a.set_group(false);
							
							gl.tx_k(new Object() {},gl.sf("Сброс флага группы для...%s...таргет...%s",
				     	  			 a.getName(),
				       	  			 target.getName()));
						});
						
						
					}else
					{
						this.getTarget().insert(this);
						
						gl.tx_k(new Object() {},gl.sf("(S)Лендинг...%s...на...%s",
			     	  			 this.getName(),
			     	  			 this.getTarget().getName()));
			         
					}
	           	  	
	           	  	
	           	  	reset_selector_process();
	           	  	
	           	  	Application.getDio().get_desk_top().repaint();
	           	  	           	 
	           	  	return true;
	      		}
	      		
	      			gl.tx_k(new Object() {},gl.sf("%s","Reject Landing.The same target."));

	      			reset_selector_process();
	      			
	      			return false;
	      	
		}
		
		public boolean check_none_process()
		{
			if (Live.brew_type.equalsIgnoreCase("None"))
     	    {
     	    	gl.tx_k(new Object() {},gl.sf("Отказ в обслуживании.Не установлен ТИП инкарнации...%s",
     	    			Live.brew_type.toUpperCase() 
     	    			));

     	 		reset_selector_process();
     	 		
     	 		return true;
     	    }
			
				return false;
		}
		
		public boolean check_valid_size(Rectangle rect)
		{
			if(!Ru.is_valid_size(rect,20,20))
     	 	{
     	 		gl.tx_k(new Object() {},gl.sf("Не валидный размер...%s",
     	    			SBounds.toString(rect) 
     	    			));
         	
     	 		reset_selector_process();
     	 		
     	 		return false;
     	 	}
				return true;
		}
		
		@Override 
		public int compareTo(Orion o) { 
			return this.getIdo().getId() > o.getIdo().getId() ? 1 
					: (this.getIdo().getId() < this.getIdo().getId() ? -1 : 0); 
		} 
		
		public boolean set_action_router(String text) {
			
			if(this.get_class().equalsIgnoreCase("Button"))
			{	
				((Button)this.getChild()).setActionCommand(text);
				
				return true;
			}
			
				return false;
		}
		
		
		// TODO 23.01.2021  set_font_router
		
		public boolean set_font_router(Font font) {
		

			if(this.get_class().equalsIgnoreCase("Orion"))
			{	
				this.setFont(font);
				
			}else if(this.get_class().equalsIgnoreCase("ScrollPane"))
			{	
				
				
				this.getData().setKey(SFont.toString(font));
				
				JViewport jvp = ((JScrollPane)this.getChild()).getViewport();
				
				Component jv = jvp.getView();
				
				if(jv instanceof List)
				{
					
					jv.setFont(font);
					
				} else
				if(jv instanceof JTable)
				{
					jv.setFont(font);
					
					JTableHeader 	header = ((JTable)jv).getTableHeader();
					
					Font 			fo = new Font(font.getName(),font.getStyle(),font.getSize()+2);
					
									header.setFont(fo);
				}
				
				

			}
				else
			{
				
				this.getChild().setFont(font);
			}
			
				return true;
				
			
		}
		
		
		
		public boolean set_data(List<String> data_items , ActionListenerImpl lsnr) {
			
			// TODO 27.12.2020 set_data_router
			
			try
			{
			
			if(this.get_class().equalsIgnoreCase("ComboBox"))
			{	
				
				ComboBox<String> cmb = (ComboBox<String>)this.getChild();
			
				ActionListener []  als = cmb.getActionListeners();
				
				if(als.length == gl.E_EMPTY)
				{
					cmb.addActionListener(lsnr);
				}
				
				
				// Повторно читаем лисенер.

				als = cmb.getActionListeners();
				
				BrewActionListener bal = (BrewActionListener)als[gl.E_EMPTY];
			
				if(bal != null)
				{
					bal.update_headers(Su.get_str_from_list_for_data(data_items));
				}
				
				// Загрузка данных.
					
				java.util.List<String> li = CollectionImpl.get_items_from_combo(cmb);
				
				data_items.forEach(a->{
					
					// В контрол загружаем  только пролог строки данных.
					
					String item = Su.BeforeAtFirst(a,gl.sf("%s",Parser.FIELD_DLM));
					
					// Защита от дубликатов.
					
					if(!li.contains(item))
					   cmb.addItem(item);
					
					
				});
				
				return gl.tx(new Object() {},true,
						gl.sf("Обновление данных дочернего компонента класса...[%s]",
								this.getClass().getSimpleName()));
		
				
			} else
				if(this.get_class().equalsIgnoreCase("ScrollPane"))
				{
					
					JViewport jport 	= ((JScrollPane)this.getChild()).getViewport();
					
					// Дочерний компонент.
					
					Component jv 	= jport.getView();
						
					if(!gl.tx(new Object() {},(jv  != null),
								gl.sf("Проверка дочернего компонента класса...[%s][%s][%s]",
										this.getClass().getSimpleName(),
										this.get_class(),
										(jv  != null) ? "JTable" : "*null*"
										)))
					{	
						return false;
					}
					
			
					if(jv instanceof ap.mercury.components.List)
					{
						
						this.getData().setData(Su.get_str_from_list_for_data(data_items));
					
						return this.getData().push();
						
					} else if(jv instanceof JTable)
					{
						
						 this.getData().setData(Su.get_str_from_list_for_data(data_items));
						 
						 return this.getData().push();
						 
					}  // JTable.
						
					} // Global.
			
						return true;
			}
			catch(Exception e)
			{
				
						e.printStackTrace();
						
						return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0, 32)));
			}
		}
		
		

		public static JTable accept_table_model_simple(Vector data,Vector columns)
		{

			Color EVEN_COLOR = new Color(200, 200, 200);


			 final TableModel model = new DefaultTableModel(data, columns) {
			        @Override public Class<?> getColumnClass(int column) {
				        	return getValueAt(0, column).getClass();
			        }
			    };
			    
				JTable table = new JTable(model) {
			        @Override public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
			            Component c = super.prepareRenderer(tcr, row, column);
			            if (isRowSelected(row)) {
			                c.setForeground(getSelectionForeground());
			                c.setBackground(getSelectionBackground());
			            } else {
			                c.setForeground(getForeground());
			                c.setBackground(row % 2 == 0 ? EVEN_COLOR : getBackground());
			            }
			            return c;
			        }
			    };
			    
			    		
					table.setAutoCreateRowSorter(true);
		

			    	TableRowSorter<TableModel> 	sorter = new TableRowSorter<>(table.getModel());
				
			    	table.setRowSorter(sorter);
			   
				      sorter.setComparator(0, new Comparator<String>() {
					   
					    @Override
					    public int compare(String a, String b) {
					    	return DateUtil.to_date(a).compareTo(DateUtil.to_date(b));
					    }
					});
				      
				      	List<RowSorter.SortKey> sortKeys = new ArrayList<>();
					     
					    int columnIndexToSort = 0;
					    
					    sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
					     
					    sorter.setSortKeys(sortKeys);
					    
					    sorter.sort();
				
					return table;
		}
		
		

		public static JTable accept_default_table_model()
		{

			Object [] 	columns = new Object[] {"a","b","c"}; 
		
			Object rows[][] 	= new Object[][] {  { "s1", "s1", "s1" }, { "s2", "s2", "s2" }, { "s3", "s3", "s3" } }; 
		 
			DefaultTableModel 	model = new DefaultTableModel(rows ,columns); 
		
			return new JTable(model);
		}

	

		public void set_text_router(String text) {
			
				if(this.get_class().equalsIgnoreCase("Orion"))
				   this.getDecoro().set_text(text);
				else if(this.get_class().equalsIgnoreCase("Button"))
				{
					this.getDecoro().set_text("");
					
					((Button)this.getChild()).setText(text);
					
					((Button)this.getChild()).setToolTipText(gl.sf("Это кнопочка по имени...%s",this.getName()));
					
				} else if(this.get_class().equalsIgnoreCase("Label"))
				{
					this.getDecoro().set_text("");
					
					((Label)this.getChild()).setText(text);
					
					((Label)this.getChild()).setToolTipText(gl.sf("Это метка по имени...%s",this.getName()));
					
				}
				else if(this.get_class().equalsIgnoreCase("ToggleButton"))
				{
					this.getDecoro().set_text("");
					
					((JToggleButton)this.getChild()).setText(text);
					
					((JToggleButton)this.getChild()).setToolTipText(gl.sf("Это кнопочка по имени...%s",this.getName()));
					
			
				}
				else if(this.get_class().equalsIgnoreCase("CheckBox"))
				{
					this.getDecoro().set_text("");
					
					((CheckBox)this.getChild()).setText(text);
					
					((CheckBox)this.getChild()).setToolTipText(gl.sf("Это чек бокс по имени...%s",this.getName()));
					
				}else if(this.get_class().equalsIgnoreCase("RadioButton"))
				{
					this.getDecoro().set_text("");
					
					((RadioButton)this.getChild()).setText(text);
					
					((RadioButton)this.getChild()).setToolTipText(gl.sf("Это радиокнопочка по имени...%s",this.getName()));
					
				}
					
		} 
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
		    //if (e.getValueIsAdjusting() == false) {

		    	int m_index = ((JList)e.getSource()).getSelectedIndex();
		    			
		        if (m_index == gl.E_ERROR) {
		
		        	
		        } else {
		        	
		        	 gl.tx_k(new Object() {},gl.sf("List index...%d...value...%s",
		    				  
		        			 m_index,
		    				 
		    				 ((JList)e.getSource()).getSelectedValue()
		    				 
		    				 ));
		   
		        }
		    //}
		}
		
		public void set_dx_dy(MouseEvent me)
		{
			//gl.tx_k(new Object() {},gl.sf("Установка dx/dy...%s",this.getName()));
			
			int dx = me.getX() - this.getDecoro().getStartPos().x;
            
            int dy = me.getY() - this.getDecoro().getStartPos().y;
            
            this.getDecoro().setEndPos(new Point(dx,dy));
         
		}
		
		public void resized_only(MouseEvent me)
		{
			if (this.getDecoro().getStartPos() == null) 
        	{
        		return;
        	}

        	
            	set_dx_dy(me);
            	
                int x = getX();
                
                int y = getY();
                
                int w = getWidth();
                
                int h = getHeight();
        
                
                int dx = this.getDecoro().getEndPos().x;
                
                int dy = this.getDecoro().getEndPos().y;
                
                
                
                switch (cursor) {

                    case Cursor.N_RESIZE_CURSOR:

                        if (!(h - dy < MIN_HEIGHT)) {
                            setBounds(x, y + dy, w, h - dy);
                            resize();
                        }
                        break;

                    case Cursor.S_RESIZE_CURSOR:

                        if (!(h + dy < MIN_HEIGHT)) {
                            setBounds(x, y, w, h + dy);
                            this.getDecoro().setStartPos(me.getPoint());
                            resize();
                        }
                        break;

                    case Cursor.W_RESIZE_CURSOR:

                        if (!(w - dx < MIN_HEIGHT)) {
                            setBounds(x + dx, y, w - dx, h);
                            resize();
                        }
                        break;

                    case Cursor.E_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT)) {
                            setBounds(x, y, w + dx, h);
                            this.getDecoro().setStartPos(me.getPoint());
                            resize();
                        }
                        break;

                    case Cursor.NW_RESIZE_CURSOR:
                        if (!(w - dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)) {
                            setBounds(x + dx, y + dy, w - dx, h - dy);
                            resize();
                        }
                        break;

                    case Cursor.NE_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)) {
                            setBounds(x, y + dy, w + dx, h - dy);
                            this.getDecoro().setStartPos(new Point(me.getX(), this.getDecoro().getStartPos().y));
                            resize();
                        }
                        break;

                    case Cursor.SW_RESIZE_CURSOR:

                        if (!(w - dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                            setBounds(x + dx, y, w - dx, h + dy);
                            this.getDecoro().setStartPos(new Point(this.getDecoro().getStartPos().x, me.getY()));
                            resize();
                        }
                        break;

                    case Cursor.SE_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                            setBounds(x, y, w + dx, h + dy);
                            this.getDecoro().setStartPos(me.getPoint());
                            resize();
                        }
                        break;
                	}

                		setCursor(Cursor.getPredefinedCursor(cursor));
            	}
		
public void moved_only (MouseEvent me) {
        	
        	if (this.getDecoro().getStartPos() == null) 
        	{
        		return;
        	}

        		set_dx_dy(me);
                
                int dx = this.getDecoro().getEndPos().x;
                
                int dy = this.getDecoro().getEndPos().y;
                
                switch (cursor) {

                    case Cursor.MOVE_CURSOR:

                        Rectangle bounds = getBounds();
                        
                        bounds.translate(dx, dy);
        
                        setBounds(bounds);
                        
                        resize();
                        
                }

                		setCursor(Cursor.getPredefinedCursor(cursor));
            
                		CollectionImpl.update_preferred_size(this);
        }

	@Override
	public void componentShown(ComponentEvent e) {
		
		
		
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		ap.mercury.components.parser.Dimension dim = ap.mercury.components.parser.Dimension.get_instance(this.getData().getPrefered_size()) ;
		
		if(!this.getPreferredSize().equals(dim) && this.getData().getPrefered_size().length() != gl.E_EMPTY)
		{
			this.setPreferredSize(dim.getDimension());

			this.revalidate();
			
			this.repaint();
		}
		else
		{
			//gl.tx_k(new Object(){},gl.sf("Preferred =sizes= are equals."));
			
		}
		
			
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		JSlider source = (JSlider)e.getSource();
		
        //if (!source.getValueIsAdjusting()) {
            
        	int m_value  = (int)source.getValue();
            
        	//gl.sfn("JSlider...%d",m_value);
        	
        	CollectionImpl.update_selected();
        	
        	CollectionImpl.selected.forEach(a->
        	{
        		a.getDecoro().set_text(gl.sf("%s...%d",a.getName(),m_value));
        		
        		a.repaint();
        	
        	});
        	
        //}
		
		
	}

	
	public void update_id_pid_after_load()
	{
		
		this.getIdo().setId(IDGen.nextId());
		
		this.setName(this.update_name());
		
		this.getDecoro().set_text(this.getName());
		
		this.get_components().forEach(c->{
			
			
				
				c.getIdo().setId(IDGen.nextId());
				
				c.getIdo().setPid(this.getIdo().getId());
				
				c.update_name();
			
				c.update_id_pid_after_load();
			
		});
		
	}

}
