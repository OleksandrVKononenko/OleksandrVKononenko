package ap.orion;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.components.ScrollPane;
import ap.orion.impl.CollectionImpl;
import ap.orion.impl.DataImpl;
import ap.orion.lsnr.BtnActionListener;
import ap.orion.state.ObjectRightsImpl;
import ap.orion.state.ObjectStateImpl;
import ap.prop.SBounds;
import ap.shape.Ru;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class JResizable extends JPanel implements MouseListener,MouseMotionListener,ComponentListener {
	
	public static int 				MIN_HEIGHT 			= ResizableBorder.DIST*3;
	
	private 						Component 			child;
	
	private 						ObjectRightsImpl    object_rights;
	
	private 						ObjectStateImpl     object_state;
	
	private DataImpl 				data;
	
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

	public JResizable() {
			
		
		this.setData(DataImpl.get_instance());

		add_listeners();

	 }
    
    public JResizable(Component comp,Rectangle bounds) {
    	
    	
    	this(comp, new ResizableBorder(ResizableBorder.DIST),bounds);
     	
    }

   
   
    public JResizable(Component comp, ResizableBorder border,Rectangle bounds) {

    
    	this();
    	
    	if(comp instanceof JPanel )
    	{
    		this.setChild(this);
    		
    		setLayout(null);
        
    		setBorder(border);
    		
    		setBounds(bounds);
    	
    	}else
    	{
    	
    		this.setChild(comp);
    	
    	if(comp instanceof JButton)
        {
    		
    				((JButton) comp).addActionListener(BtnActionListener.get_instance());
    				
            	  	add(comp,BorderLayout.CENTER);
              	
              		this.setChild(comp);
            	
         }else if(comp instanceof JComboBox )
         {	
            		this.setLayout(new BorderLayout());
            
            		add(comp,BorderLayout.CENTER);
               	
         } else if(comp instanceof JTextArea )
         {
            		JScrollPane sp = new JScrollPane(this.getChild());
            	
            		this.setLayout(new BorderLayout());
            	
            		add(sp,BorderLayout.CENTER);
            	
            		this.setChild(sp);
               	
            } else if(comp instanceof JList )
            {
            	
                
            	ScrollPane sp =ScrollPane.get_instance(this.getChild());
            		
            	this.setLayout(new BorderLayout());
            	
            	add(sp,BorderLayout.CENTER);
            	
            	this.setChild(sp);
            }                     
     
            else if(comp instanceof JSlider )
            {
     
	            ((JSlider)this.getChild()).setMajorTickSpacing(10); 
	            
	            ((JSlider)this.getChild()).setMinorTickSpacing(1); 
	     
	            ((JSlider)this.getChild()).setPaintTicks(true); 
	     
	            ((JSlider)this.getChild()).setPaintLabels(true);
	            
	            add(comp,BorderLayout.CENTER);
            	
            	this.setChild(comp);
	    		
            }else if(comp instanceof JTable )
            {
         
            	Object [] model = new Object[] {"a","b","c"}; 
   			 
    			Object data[][] = new Object[][] { 
    			{ "s1", "s1", "s1" }, { "s2", "s2", "s2" }, { "s3", "s3", "s3" } }; 
    			 
    			DefaultTableModel dtm = new DefaultTableModel(data , model); 
    			 
    			JTable table = new JTable(dtm); 
    			
     			//table.setAutoCreateRowSorter(true); 
    			
    			JScrollPane sp = new JScrollPane(table);
            	
            	this.setLayout(new BorderLayout());
            	
            	add(sp,BorderLayout.CENTER);
            	
            	this.setChild(sp);
            	 
            } else
            {
                add(comp,BorderLayout.CENTER);
        
                this.setChild(comp);
            }
            
            	// Common section.

            	setBorder(border);
	            
	            setBounds(bounds);
	      
	            this.getChild().setBounds(Ru.get_spanned_rect(Ru.norm4g(this.getBounds()),new Insets(ResizableBorder.DIST,ResizableBorder.DIST,(ResizableBorder.DIST*2)-1,(ResizableBorder.DIST*2)-1)));
	    		
	            
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

    
    @Override
     public void mouseMoved(MouseEvent me) {

            if (hasFocus()) {

            	ResizableBorder resizableBorder = (ResizableBorder) getBorder();
                
                setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(me)));
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setCursor(Cursor.getDefaultCursor());
        }

        private int 	cursor;
        
        private Point 	startPos = null;
        
        private Point 	endPos   = null;
        
        
        

        public int get_Cursor() {
			return cursor;
		}

		

		public Point getStartPos() {
			return startPos;
		}

		public void setStartPos(Point startPos) {
			this.startPos = startPos;
		}

		public Point getEndPos() {
			return endPos;
		}

		public void setEndPos(Point endPos) {
			this.endPos = endPos;
		}

		@Override
        public void mousePressed(MouseEvent me) {

			ResizableBorder resizableBorder = (ResizableBorder) getBorder();
	            
	            cursor 				= resizableBorder.getCursor(me);
	            
	            startPos 			= me.getPoint();


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
	            
	            	gl.tx_k(new Object() {},gl.sf("---> Перед сбросом фокуса...[%s]",this.getName()));

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
	    }
		
		
		public void set_dx_dy(MouseEvent me)
		{
			//gl.tx_k(new Object() {},gl.sf("Установка dx/dy...%s",this.getName()));
			
			int dx = me.getX() - startPos.x;
            
            int dy = me.getY() - startPos.y;
            
            this.setEndPos(new Point(dx,dy));
         
		}

		public void resized_only(MouseEvent me)
		{
			if (startPos == null) 
        	{
        		return;
        	}

        	
            	set_dx_dy(me);
            	
                int x = getX();
                
                int y = getY();
                
                int w = getWidth();
                
                int h = getHeight();
        
                
                int dx = this.getEndPos().x;
                
                int dy = this.getEndPos().y;
                
                
                
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
                            startPos = me.getPoint();
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
                            startPos = me.getPoint();
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
                            startPos = new Point(me.getX(), startPos.y);
                            resize();
                        }
                        break;

                    case Cursor.SW_RESIZE_CURSOR:

                        if (!(w - dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                            setBounds(x + dx, y, w - dx, h + dy);
                            startPos = new Point(startPos.x, me.getY());
                            resize();
                        }
                        break;

                    case Cursor.SE_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                            setBounds(x, y, w + dx, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;
                	}

                		setCursor(Cursor.getPredefinedCursor(cursor));
            

		}
		
		
		
        @Override
        public void mouseDragged(MouseEvent me) {
        
        	
        }
        
        
        public void moved_only (MouseEvent me) {
        	
        	if (startPos == null) 
        	{
        		return;
        	}

        		set_dx_dy(me);
                
                int dx = this.getEndPos().x;
                
                int dy = this.getEndPos().y;
                
                switch (cursor) {

                    case Cursor.MOVE_CURSOR:

                        Rectangle bounds = getBounds();
                        
                        bounds.translate(dx, dy);
        
                        setBounds(bounds);
                        
                        	resize();
                        
                }

                		setCursor(Cursor.getPredefinedCursor(cursor));
            
        }

        public void mouseDragged_new(MouseEvent me) {

        	if (startPos == null) 
        	{
        		return;
        	}

        	
            {
            	boolean m_bl_sizeable = this.getRights().is_sizeable();
            	
            	boolean m_bl_dragable = this.getRights().is_dragable();
            	
            	set_dx_dy(me);
            	
            	if(!m_bl_sizeable &&  m_bl_dragable)
            	{
            		gl.tx_k(new Object() {},gl.sf("Запрещено изменять размер объекту...%s",this.getName()));
    	     	}
            	
                int x = getX();
                
                int y = getY();
                
                int w = getWidth();
                
                int h = getHeight();
        
                
                int dx = this.getEndPos().x;
                
                int dy = this.getEndPos().y;
                
                
                
                switch (cursor) {

                    case Cursor.N_RESIZE_CURSOR:

                        if (!(h - dy < MIN_HEIGHT) && m_bl_sizeable) {
                            setBounds(x, y + dy, w, h - dy);
                            resize();
                        }
                        break;

                    case Cursor.S_RESIZE_CURSOR:

                        if (!(h + dy < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x, y, w, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;

                    case Cursor.W_RESIZE_CURSOR:

                        if (!(w - dx < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x + dx, y, w - dx, h);
                            resize();
                        }
                        break;

                    case Cursor.E_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x, y, w + dx, h);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;

                    case Cursor.NW_RESIZE_CURSOR:
                        if (!(w - dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x + dx, y + dy, w - dx, h - dy);
                            resize();
                        }
                        break;

                    case Cursor.NE_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x, y + dy, w + dx, h - dy);
                            startPos = new Point(me.getX(), startPos.y);
                            resize();
                        }
                        break;

                    case Cursor.SW_RESIZE_CURSOR:

                        if (!(w - dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x + dx, y, w - dx, h + dy);
                            startPos = new Point(startPos.x, me.getY());
                            resize();
                        }
                        break;

                    case Cursor.SE_RESIZE_CURSOR:

                        if (!(w + dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)&& m_bl_sizeable) {
                            setBounds(x, y, w + dx, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;

                    case Cursor.MOVE_CURSOR:

                    	

                    	if(!m_bl_dragable)
                    	{
                    		gl.tx_k(new Object() {},gl.sf("Запрещено перемещать объект...%s",this.getName()));
                    		
                    		
            	     	}
                    	
                        Rectangle bounds = getBounds();
                        
                        bounds.translate(dx, dy);
                        /*
                        if(!m_bl_dragable)
                    	{
                    		gl.tx_k(new Object() {},gl.sf("Запрещено перемещать объект...%s",this.getName()));
                    		
                    		
            	     	}
                        else
                        {
                        	setBounds(bounds);
                        }
                        */
                        if(m_bl_dragable)
                          setBounds(bounds);
                        
                        // Требует дополнительного анализа.
                        
                        resize();
                }

                setCursor(Cursor.getPredefinedCursor(cursor));
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            
        	this.setStartPos(null);
        	
        	this.setEndPos(null);
        	
        	
			this.getState().set_resized(false);
			
			this.getState().set_dragged(false);
			
        	
        }

		@Override
		public void mouseClicked(MouseEvent me) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
            
		}
		
		public static JResizable get_instance(Component comp,Rectangle bounds)
		{
			
			return new JResizable(comp,bounds);
			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			
			//gl.sfn("Component moved...%s", this.getName());
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			
			//gl.sfn("Component shown...%s", this.getName());
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			
			//gl.sfn("Component hidden...%s", this.getName());
			
			
		}
    
        
}
