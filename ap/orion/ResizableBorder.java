package ap.orion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import ap.global.gl;
import ap.utils.CU;
import ap.utils.GU;

public class ResizableBorder implements Border {

    public static  int DIST = 8;
  
    private Orion owner;
    
    private Rectangle client_rect;
    
    
    int locations[] = {
            SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST,
            SwingConstants.EAST, SwingConstants.NORTH_WEST,
            SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
            SwingConstants.SOUTH_EAST
    };

    int cursors[] = {
            Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR,
            Cursor.E_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR,
            Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR
    };

  
    


	public Rectangle getClient_rect() {
		return client_rect;
	}

	public void setClient_rect(Rectangle client_rect) {
		this.client_rect = client_rect;
	}

	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}

	public ResizableBorder() {
    
    }
	
	public ResizableBorder(int dist) {
        DIST = dist;
    }

	public ResizableBorder(int id,int dist) {
        
		this(dist);
	
    }
    
   

	@Override
    public Insets getBorderInsets(Component component) {
        return new Insets(DIST, DIST, DIST, DIST);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    
    public void paint_rect(Graphics g,int x, int y,int w, int h)
    {
    	Graphics2D g2 = (Graphics2D)g;
        
   	 	Stroke p_stroke = g2.getStroke();
        
        g2.setStroke(gl.dashed_stroke_solid);
       
        GU.draw_alpha_rect(g2,new Rectangle(x + DIST / 2, y + DIST / 2, w - DIST, h - DIST),CU.getAlphaColor(Color.white,128));
        
        g2.setStroke(p_stroke);
    	
    }
    
    public void paint_inner_rect(Graphics g,int x, int y,int w, int h)
    {
    	Graphics2D g2 = (Graphics2D)g;
        
   	 	Stroke p_stroke = g2.getStroke();
        
        g2.setStroke(gl.dashed_stroke_thin);
        
        this.setClient_rect(new Rectangle(x + DIST, y + DIST , (w - DIST*2), (h - DIST*2)));
        
        GU.draw_alpha_rect(g2,this.getClient_rect(),CU.getAlphaColor(Color.white,96));
        
        g2.setStroke(p_stroke);
    	
    }
    
    
    public void paint_squares(Graphics g, int x, int y,int w, int h)
    {

    	for (int i = 0; i < locations.length; i++) {

            Rectangle rect = getRectangle(x, y, w, h, locations[i]);
      
            GU.fill_alpha_rect((Graphics2D)g,new Rectangle(rect.x, rect.y, rect.width - 1, rect.height - 1),CU.getAlphaColor(Color.white,96));
               
            GU.draw_alpha_rect((Graphics2D)g,new Rectangle(rect.x, rect.y, rect.width - 1, rect.height - 1),CU.getAlphaColor(Color.black,128));
            
    	}
    	
    }
    
    @Override
    /*
    public void paintBorder_(Component component, Graphics g, int x, int y,
                            int w, int h) {
    	
    		// Paint border.
        	paint_rect(g,x,y,w,h);
        	
        	// Paint border.
        	//paint_inner_rect(g,x,y,w,h);
        	
        	
    	   	// Squares.
        	paint_squares(g,x,y,w,h);
     
    }
    */

	public void paintBorder(Component component, Graphics g, int x, int y, int w, int h) {

// No draw if desktop flag.
		if (this.getOwner().is_desk_top()) {
			return;
		}

//Draw only rect border.
		if (this.getOwner().getState().is_selected()) {
// Paint border.
			paint_rect(g, x, y, w, h);

		}

// Draw bothes, rect border and squares.

//if(component.hasFocus())
		if (this.getOwner().getState().is_focused()) {

// Paint border.
			paint_rect(g, x, y, w, h);

// Paint border.
//paint_inner_rect(g,x,y,w,h);

// Squares.
			paint_squares(g, x, y, w, h);
		}

	}
    
    private Rectangle getRectangle(int x, int y, int w, int h, int location) {

        switch (location) {

            case SwingConstants.NORTH:
                return new Rectangle(x + w / 2 - DIST / 2, y, DIST, DIST);

            case SwingConstants.SOUTH:
                return new Rectangle(x + w / 2 - DIST / 2, y + h - DIST, DIST, DIST);

            case SwingConstants.WEST:
                return new Rectangle(x, y + h / 2 - DIST / 2, DIST, DIST);

            case SwingConstants.EAST:
                return new Rectangle(x + w - DIST, y + h / 2 - DIST / 2, DIST, DIST);

            case SwingConstants.NORTH_WEST:
                return new Rectangle(x, y, DIST, DIST);

            case SwingConstants.NORTH_EAST:
                return new Rectangle(x + w - DIST, y, DIST, DIST);

            case SwingConstants.SOUTH_WEST:
                return new Rectangle(x, y + h - DIST, DIST, DIST);

            case SwingConstants.SOUTH_EAST:
                return new Rectangle(x + w - DIST, y + h - DIST, DIST, DIST);
        }
        		return null;
    }

    public int getCursor(MouseEvent me) {

        Component c = me.getComponent();
        
        int w = c.getWidth();
        
        int h = c.getHeight();

        for (int i = 0; i < locations.length; i++) {

            Rectangle rect = getRectangle(0, 0, w, h, locations[i]);

            if (rect.contains(me.getPoint())) {
                return cursors[i];
            }
        }

        return Cursor.MOVE_CURSOR;
    }
}
