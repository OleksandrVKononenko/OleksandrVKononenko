package ap.mercury.intf;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;
import ap.global.gl;
import ap.mercury.components.Rect;
import ap.shape.Ru;
import ap.utils.CU;
import ap.utils.GU;

public interface IMoving {

	public static final int DIST = 6;
	
	public static  int MIN_HEIGHT 			= DIST*4;
	
	
	default
	public void mousePressed(JComponent owner,MouseEvent e) {
	
		owner.putClientProperty("start",e.getPoint());
	}
	
	default
	public void mouseReleased(JComponent owner,MouseEvent e) {
	
		owner.putClientProperty("start",null);
	}
	
	default
	public void mouseDragged(JComponent owner,MouseEvent e) {
		
		
		Point p = (Point)owner.getClientProperty("start");
		
		owner.putClientProperty("dxdy",new Point(
				e.getX() - p.x,
				e.getY() - p.y
				));
		
		Rectangle r = owner.getBounds(); 
		 
		Point to = (Point)owner.getClientProperty("dxdy");
		
		int cursor = owner.getCursor().getType();
		
		
		int x = r.x;
        
        int y = r.y;
        
        int w = r.width;
        
        int h = r.height;

        
        int dx = to.x;
        
        int dy = to.y;
				
        
				switch (cursor) {

                case Cursor.N_RESIZE_CURSOR:

                    if (!(h - dy < MIN_HEIGHT)) {
                    	
                        r.setBounds(new Rectangle(x, y + dy, w, h - dy));
                        
                        resize(owner);
                    }
                    break;

                case Cursor.S_RESIZE_CURSOR:

                    if (!(h + dy < MIN_HEIGHT)) {
                    	
                        
                        r.setBounds(new Rectangle(x, y, w, h + dy));
                        
                        owner.putClientProperty("start",e.getPoint());
                    	
                        resize(owner);
                    }
                    break;

                case Cursor.W_RESIZE_CURSOR:

                    if (!(w - dx < MIN_HEIGHT)) {
                        
                    	r.setBounds(x + dx, y, w - dx, h);
                        
                        resize(owner);
                    }
                    break;

                case Cursor.E_RESIZE_CURSOR:

                    if (!(w + dx < MIN_HEIGHT)) {
                    	
                        r.setBounds(x, y, w + dx, h);
                        
                        owner.putClientProperty("start",e.getPoint());
                    	
                        resize(owner);
                    }
                    break;

                case Cursor.NW_RESIZE_CURSOR:
                    
                	if (!(w - dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)) {
                        
                        r.setBounds(x + dx, y + dy, w - dx, h - dy);
                        
                        resize(owner);
                    }
                    break;

                case Cursor.NE_RESIZE_CURSOR:

                    if (!(w + dx < MIN_HEIGHT) && !(h - dy < MIN_HEIGHT)) {
                        
                        r.setBounds(x, y + dy, w + dx, h - dy);

                    	owner.putClientProperty("start",new Point(e.getX(),p.y));
                    	
                        resize(owner);
                    }
                    break;

                case Cursor.SW_RESIZE_CURSOR:

                    if (!(w - dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                        
                    	r.setBounds(x + dx, y, w - dx, h + dy);

                    	owner.putClientProperty("start",new Point(p.x, e.getY()));
                    	
                        resize(owner);
                        
                    }
                    break;

                case Cursor.SE_RESIZE_CURSOR:

                    if (!(w + dx < MIN_HEIGHT) && !(h + dy < MIN_HEIGHT)) {
                        
                    	r.setBounds(x, y, w + dx, h + dy);

                        owner.putClientProperty("start",e.getPoint());
                    	
                        resize(owner);
                        
                    }
                    break;
                
                    
                case Cursor.DEFAULT_CURSOR:
                	
                	r.translate(to.x, to.y);
                	
                	break;
                    
            	}
				
				if(cursor != Cursor.DEFAULT_CURSOR)
				{
					add_rects(owner);
					
					owner.repaint();
				}
		 
					owner.setBounds(r); 
		
	}
	
	default
	public void mouseEntered(JComponent owner) {
		
		add_rects(owner);
		
		owner.repaint();
	}
	
	default
	public void mouseExited(JComponent owner) {
	
	
		owner.putClientProperty("rects",null);
		
		owner.repaint();
	}
	
	default
	public void mouseMoved(JComponent owner,MouseEvent e) {
		
		owner.setCursor(new Cursor(get_cursor(owner,e)));
	}
	
	default 
	public void add_rects(JComponent owner)
	{
		int x =0;
		
		int y =0;
		
		int w = owner.getWidth();
        
        int h = owner.getHeight();
        
        /*
         
		owner.putClientProperty("rects",
				
				Arrays.asList(
						
						new Rect(new Rectangle(x + w / 2 - DIST / 2, y, DIST, DIST),Cursor.N_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w / 2 - DIST / 2, y + h - DIST, DIST, DIST),Cursor.S_RESIZE_CURSOR),
						new Rect(new Rectangle(x, y + h / 2 - DIST / 2, DIST, DIST),Cursor.W_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - DIST, y + h / 2 - DIST / 2, DIST, DIST),Cursor.E_RESIZE_CURSOR),
						new Rect(new Rectangle(x, y, DIST, DIST),Cursor.NW_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - DIST, y, DIST, DIST),Cursor.NE_RESIZE_CURSOR),
						new Rect(new Rectangle(x, y + h - DIST, DIST, DIST),Cursor.SW_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - DIST, y + h - DIST, DIST, DIST),Cursor.SE_RESIZE_CURSOR)
						
						
						)
				
				);
			*/
		         
		owner.putClientProperty("rects",
				
				Arrays.asList(
						
						new Rect(new Rectangle(x + w / 2 - DIST / 2, y+DIST/2, DIST, DIST),Cursor.N_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w / 2 - DIST / 2, y + h - (DIST + DIST/2), DIST, DIST),Cursor.S_RESIZE_CURSOR),
						new Rect(new Rectangle(x + DIST/2, y + h / 2 - DIST /2 , DIST, DIST),Cursor.W_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - (DIST + DIST/2), y + h / 2 - DIST / 2, DIST, DIST),Cursor.E_RESIZE_CURSOR),
						new Rect(new Rectangle(x + DIST /2, y + DIST /2, DIST, DIST),Cursor.NW_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - (DIST + DIST/2), y + DIST/2, DIST, DIST),Cursor.NE_RESIZE_CURSOR),
						new Rect(new Rectangle(x + DIST /2 , y + h - (DIST + DIST/2), DIST, DIST),Cursor.SW_RESIZE_CURSOR),
						new Rect(new Rectangle(x + w - (DIST + DIST/2), y + h - (DIST + DIST/2), DIST, DIST),Cursor.SE_RESIZE_CURSOR)
						
						
						)
				
				);
		
		  }
		
	default 
	public int get_cursor(JComponent owner,MouseEvent e)
	{
		List<Rect>  list = (List<Rect>)owner.getClientProperty("rects");
		
		int [] m_result =  { Cursor.DEFAULT_CURSOR };
		
		list.forEach(a->{
			
			if(a.contains(e.getPoint()))
			{
				m_result[0] = a.getService();
			}
		});
		
		
				return m_result[0];
	}
	
	default 
	public void paint_rect(Graphics g,int x, int y,int w, int h)
    {
    	Graphics2D g2 = (Graphics2D)g;
        
   	 	Stroke p_stroke = g2.getStroke();
        
        g2.setStroke(gl.dashed_stroke_solid);
       
        GU.draw_alpha_rect(g2,new Rectangle(x + DIST / 2, y + DIST / 2, w - DIST, h - DIST),CU.getAlphaColor(Color.white,128));
        
        g2.setStroke(p_stroke);
    	
    }
	 
	default
	public void paint_rect(Graphics g,Rectangle r)
    {
		paint_rect(g,r.x,r.y,r.width,r.height);
		
    }
	
	//JComponent owner
	
	default
	public void paint_squares(Graphics g,Rect r)
    {
    	
	    GU.fill_alpha_rect((Graphics2D)g,new Rectangle(r.x, r.y, r.width - 1, r.height - 1),CU.getAlphaColor(Color.white,96));
        
        GU.draw_alpha_rect((Graphics2D)g,new Rectangle(r.x, r.y, r.width - 1, r.height - 1),CU.getAlphaColor(Color.black,128));
    
		
    }
	
	
	default
	public void paintComponent(JComponent owner,Graphics g) { 
	
		
		List<Rect> list = (List<Rect>)owner.getClientProperty("rects");
		
		if(list == null)
			return;
		
		paint_rect(g,Ru.Scale(Ru.norm4g(owner.getBounds()),new Dimension(-DIST/2,-DIST/2)));
		
		list.forEach(a->{
			
			paint_squares(g,a);
			
		});
		
	}
	
	default
	public void resize(JComponent owner) {
		
		if (owner.getParent() != null) {
	            owner.getParent().revalidate();
	        }
	    
	}
	
	}
