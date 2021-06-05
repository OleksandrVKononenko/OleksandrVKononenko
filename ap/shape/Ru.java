 
/** 
* 
*/ 
package ap.shape; 

import java.awt.Color; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.geom.Rectangle2D; 

import ap.area.AreaManager; 
import ap.btn.TPanel; 
import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 



@SuppressWarnings("serial") 
public class Ru extends Rectangle{ 

	 
	public Color color = Color.yellow; 
	 
	public int ServiceType = Cursor.DEFAULT_CURSOR; 
	 
	private String name = ""; 
	 
	 
	public static String toString2D(Rectangle2D rect) 
	{
		return gl.sf("%.4f %.4f %.4f %.4f",rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
	}
	

	public static String toString(Rectangle rect) 
	{
		return gl.sf("%d %d %d %d",rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
	}
	
	public static Rectangle translate(Rectangle rect,int dx,int dy) 
	{ 
		Rectangle r = rect; 
		 
		r.translate(dx, dy); 
		 
		return r; 
		 
	} 
	
	public static boolean is_valid_size(Rectangle rect,int width,int height) 
	{ 
		return (rect.width > width && rect.height > height);
	}
	
	public static Rectangle get_init_rect(int value) 
	{ 
		return new Rectangle(value,value,value,value); 
	} 
	 
	public static Rectangle get_preffered_rect(Rectangle rect,Dimension dim) 
	{ 
		return new Rectangle(rect.x,rect.y,dim.width,dim.height); 
	} 
	 

	public static Dimension get_preffered_dim(Dimension a,Dimension b) 
	{ 
		return new Dimension(
								(a.width >= b.width) ? a.width : b.width,
								(a.height >= b.height) ? a.height : b.height
							); 
	} 
	 
	public static boolean dim_greate_than(Dimension a,Dimension b) 
	{ 
		return (a.width > b.width) || (a.height > b.height);
		
	} 
	
	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 

	/** 
	 * @return the serviceType 
	 */ 
	public int getServiceType() { 
		return ServiceType; 
	} 

	/** 
	 * @param serviceType the serviceType to set 
	 */ 
	public void setServiceType(int serviceType) { 
		ServiceType = serviceType; 
	} 
	public Ru() 
	{ 
		super(); 
	} 
	 
	public Ru(int x,int y,int width,int height) 
	{ 
		super(x,y,width,height); 
	} 
	 
	public Ru(Rectangle r) 
	{ 
		super(r.x,r.y,r.width,r.height); 
	} 
	 
	public Ru(Rectangle r,Color color) 
	{ 
		this(r); 
		 
		this.setColor(color); 
	} 
	 
	public Ru(Rectangle r,int service) 
	{ 
		this(r); 
		 
		this.setServiceType(service); 
	} 
	 
	public Ru(Rectangle r,int service,Color color) 
	{ 
		this(r,service); 
		 
		this.setColor(color); 
	} 
	 
	public Ru(Rectangle r,int service,Color color,String name) 
	{ 
		this(r,service,color); 
		 
		this.setName(name); 
	} 
	 
	 
	public static boolean equals(Rectangle a,Rectangle b) 
	{ 
		return  ((a.x ==  b.x) && (a.y == b.y) && (a.width == b.width) && (a.height==b.height)); 
	} 
	 
	public static boolean equalsPoz(Rectangle a,Rectangle b) 
	{ 
		return  ((a.x ==  b.x) && (a.y == b.y)); 
	} 

	public static boolean equalsSize(Rectangle a,Rectangle b) 
	{ 
		return  ((a.width ==  b.width) && (a.height == b.height)); 
	} 

	public  boolean isContains(Rectangle a) 
	{ 
		return this.contains(a); 
	} 
	 
	public  static Rectangle getHalfHight(Rectangle a) 
	{ 
		return new Rectangle(a.x,a.y,a.width,a.height/2); 
	} 
	 
	public  static Rectangle getTopHalf(Rectangle a) 
	{ 
		return new Rectangle(a.x,a.y,a.width,a.height/2); 
	} 
	 
	public  static Rectangle getBottomHalf(Rectangle a) 
	{ 
		return new Rectangle(a.x,a.height/2,a.width,a.height/2); 
	} 
	 
	public  static Rectangle getCenterWindow(Rectangle owner,Rectangle client) 
	{ 
		return new Rectangle(owner.width/2 - client.width/2, 
							 owner.height/2 - client.height/2, 
							 client.width,client.height); 
	} 
	 
	 
	public  static boolean isInit(Rectangle a) 
	{ 
		return (a.x != gl.E_ERROR && a.y != gl.E_ERROR && a.width != gl.E_ERROR && a.height != gl.E_ERROR); 
	} 
	 
	public  static boolean isNull(Rectangle a) 
	{ 
		return (a==null); 
	} 
	 
	public static boolean is_empty(Rectangle rect) 
	{ 
		return ((rect.x+rect.y+rect.width+rect.height) == gl.E_EMPTY); 
	} 
	 
	public  static boolean isNull(Rectangle a,Rectangle b) 
	{ 
		return (isNull(a) || isNull(b)); 
	} 
	 
	public static Point normPoint(Rectangle owner,Point in) 
	{ 
			return new Point(in.x - owner.x,in.y - owner.y); 
	} 
	 
	public static Point normPointY(Rectangle owner,Point in) 
	{ 
			return new Point(in.x,in.y - owner.y); 
	} 
	public static Rectangle norm4g(Rectangle owner,Rectangle rect) 
	{ 
			return new Rectangle(rect.x - owner.x,rect.y - owner.y,rect.width-1,rect.height-1); 
	} 
	 
	public static Rectangle norm4ge(Rectangle owner) 
	{ 
			return new Rectangle(owner.x - owner.x,owner.y - owner.y,owner.width,owner.height); 
	} 
	 
	 
	public static Point norm4g(Point owner,Point client) 
	{ 
			return new Point(client.x - owner.x,client.y - owner.y); 
	} 
	 
	public static Rectangle norm4gp(Rectangle owner,Rectangle rect) 
	{ 
			return new Rectangle(rect.x + owner.x,rect.y + owner.y,rect.width,rect.height); 
	} 
	 
	public static synchronized Rectangle norm4g(Rectangle rect) 
	{ 
			return (rect == null) ? Ru.get_init_rect(gl.E_EMPTY) :	new Rectangle(0,0,rect.width,rect.height); 
	} 
	 
	 
	public  static Rectangle ShiftSizeRight(Rectangle rect,Dimension dim) 
	{ 
		return new Rectangle(rect.x,rect.y,rect.width+dim.width,rect.height+dim.height); 
	} 
	 
	public  static Rectangle ShiftSizeLeft(Rectangle rect,Dimension dim) 
	{ 
		return new Rectangle(rect.x-dim.width,rect.y+dim.height,rect.width,rect.height); 
	} 
	 
	 
	public  static Rectangle Scale(Rectangle rect,Dimension dim) 
	{ 
		int MW = 2; 
		 
		Rectangle result = new 
				Rectangle(rect.x+(dim.width*gl.E_ERROR), 
						  rect.y+(dim.height*gl.E_ERROR), 
						  (rect.width+(dim.width*2)), 
						  (rect.height+(dim.height*2))); 
		 
		if(result.width < MW || result.height < MW) 
		{ 
			result.x = rect.x; 
					 
			result.y = rect.y; 
			 
			result.width = MW*4; 
			 
			result.height = MW*4; 
		} 
			return result; 
	} 
	
	public static Dimension get_scale_dim(Dimension  dim , Dimension scale)
	{
		return new Dimension(dim.width/scale.width,dim.height/scale.height);
	}
	
	public static void clear(Rectangle rect) 
	{ 
		rect.x 		= gl.E_EMPTY; 
		 
		rect.y 		= gl.E_EMPTY; 
		 
		rect.width 	= gl.E_EMPTY; 
		 
		rect.height = gl.E_EMPTY; 
		 
	} 
	 
	public static Rectangle insetsToRect(Insets insets) 
	{ 
		return new Rectangle(insets.left,insets.top,insets.right,insets.bottom); 
	} 
	 
	public static Rectangle sumRects(Rectangle a, Rectangle b) 
	{ 
		return new Rectangle(a.x+b.x,a.y+b.y,a.width+b.width,a.height+b.height); 
	} 
	 
	public static Rectangle sumRectsByXY(Rectangle a, Rectangle b) 
	{ 
		return new Rectangle(a.x+b.x,a.y+b.y,a.width,a.height); 
	} 
	 
	public  static Rectangle concat(Rectangle a,Rectangle b,int align) 
	{ 
		Rectangle result = new Rectangle(0,0,0,0); 
		 
		switch(align) 
		{ 
		case Cursor.N_RESIZE_CURSOR: 
		{ 
			result.x = a.x; 
			 
			result.y = a.y; 
			 
			result.width = a.width; 
			 
			result.height = a.height+b.height; 
			 
			 
		} 
			break; 
		} 
		 
		return result; 
	} 
	 

	 
	 
	 
	/** 
	 * @param args 
	 */ 
	 
	public Ru getNext(Rectangle base,AreaManager am,int x_offset,int y_offset) 
	{ 
		int x_cell_size = am.getCellSize().width; 
		 
		int y_cell_size = am.getCellSize().height; 
		 
		return new Ru(base.x+(x_cell_size*x_offset), 
				         base.y+(y_cell_size*y_offset), 
				         base.width, 
				         base.height 
				         ); 
		 
	} 
	 
	 
	public static void Test_get() 
	{ 
		AreaManager am = new AreaManager(new Dimension(100,100),new Dimension(10,10),new Dimension(1,1),new Dimension(0,0)); 
		 
		int index = 0; 
		 
		Ru  ar = new Ru(); 
		 
		for(int j=0;j<am.getAreaInCells().height;j++) 
			for(int i=0;i<am.getAreaInCells().width;i++) 
			{ 
				//index = am.getIndex(am.getArea_width_in_cell(),i,j); 

				ar.setBounds(am.get(i,j).getBounds()); 
				 
				//gl.smn("Index : " + index + " Base area : " + am.get(i,j).toString() + " Shift area : " + ar.getNext(am.get(i,j), am,0,1).toString()); 
				 
			} 
	} 
	 
	public void draw( Graphics g) 
	{ 
		Color old = g.getColor(); 
		 
		g.setColor(this.getColor()); 
		 
		g.drawRect(this.x, this.y,this.width,this.height); 
		 
		g.setColor(old); 
		 
	} 
	 
	public static void drawRect2D(Graphics2D  g, Rectangle rect) 
	{ 
		g.drawRect(rect.x, rect.y,rect.width,rect.height); 
	} 
	 
 
	public void fill( Graphics g) 
	{ 
		Color old = g.getColor(); 
		 
		g.setColor(this.getColor()); 
		 
		g.fillRect(this.x, this.y,this.width,this.height); 
		 
		g.setColor(old); 
		 
	} 
	 
	public static void fillRoundRect(Rectangle rect, Graphics g) 
	{ 
		 
		g.fillRoundRect(gl.E_EMPTY,gl.E_EMPTY,rect.width,rect.height,rect.width/3,rect.height/3); 
		 
	} 
	 
	public static Rectangle Shift(Rectangle rect,int dx,int dy) { 
		 
		return new Rectangle(rect.x+dx,rect.y+dy,rect.width,rect.height); 
		 
	} 
	 
	public static boolean is_valid_size(Rectangle rect) { 
		 
		 
		if(rect.width >= TPanel.MW && rect.height >= TPanel.MW) 
			return true; 
		 
		return false; 

	} 
	 
	public static Rectangle2D getRect2D(Rectangle rect) 
	{ 
		return new Rectangle2D.Double((float)rect.x,(float)rect.y,(float)rect.width,(float)rect.height); 
	} 
	 
	public Color getColor() { 
		return color; 
	} 

	 
	public void setColor(Color color) { 
		this.color = color; 
	} 
	 
	 
	public static boolean isDimEqual(Dimension a ,Dimension b) 
	{ 
		return ((a.width == b.width) && (a.height == b.height)); 
	} 
	 
	public static Rectangle get_instance(Point start,Point end) 
	{ 
		if(start == null || end == null) 
			return get_instance(); 
					 
			return new Rectangle(start.x,start.y,(end.x - start.x),(end.y - start.y)); 
	} 
	 
	public static Rectangle get_instance() 
	{ 
		return new Rectangle(0,0,0,0); 
	} 
	 
	public static Rectangle get_instance(int x, int y, int w, int h) 
	{ 
		return new Rectangle(x,y,w,h); 
	} 
	
	public static Rectangle norm_metric(Rectangle owner,Rectangle metric) 
	{ 
		return new Rectangle(owner.x+metric.x,owner.y+metric.y,owner.width + (metric.width * gl.E_ERROR) + (metric.x * gl.E_ERROR),owner.height + (metric.height* gl.E_ERROR) + (metric.y * gl.E_ERROR)); 
	} 
	 
	public static Rectangle norm_dim_to_rect(Dimension dim) 
	{ 
		return new Rectangle(0,0,dim.width,dim.height); 
	} 
	 
	public static Dimension norm_rect_to_dim(Rectangle rect) 
	{ 
		return new Dimension(rect.width,rect.height); 
	} 
	 
	 
	public static Rectangle get_spanned_rect(Rectangle rect,Insets insets) 
	 { 
		return new Rectangle( 
				rect.x+insets.left, 
				rect.y+insets.top, 
				(rect.width - (insets.right)), 
				(rect.height-(insets.bottom))); 
	 } 
	 
	public static Dimension norm_to_state_dim(Rectangle rect) 
	{ 
	 
		 
		return new Dimension( 
				(rect.x < 0 || rect.width < 0) ? 1 : 0  , 
				(rect.y < 0 || rect.height < 0) ? 1 : 0 
				); 
	} 
	
	public static Rectangle norm_to_state_rect(Rectangle rect) 
	{ 
	
		int Z = gl.E_ERROR;
	
		return new Rectangle(rect.x*Z,rect.y*Z,rect.width*Z,rect.height*Z);
	
	}
	 
	public static Rectangle a_minus_b(Rectangle a,Rectangle b) 
	{ 
		if(a == null || b == null) 
			return Ru.get_init_rect(gl.E_EMPTY); 
		 
		return new Rectangle(a.x+b.width,a.y,a.width - b.width,a.height); 
	} 
	
	
	public static Rectangle get_point_rect(Point a, Point b) 
	{
		return new Rectangle(a.x,a.y,b.x,b.y);
	}
	
	
	public static void main(String[] args) { 

		Test_get(); 

	} 

	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:48 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
