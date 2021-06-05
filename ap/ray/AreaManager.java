 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Dimension; 
import java.awt.Rectangle; 

/** 
* @author Administrator 
* 
*/ 
public class AreaManager { 

	protected Rectangle Area ; 
	 
	protected I3 Index ; 
	 
	protected I3  Size ; 
	 
	private Dimension dimension; 
	 
	private AreaPointIndex point; 
	 
	public Dimension getDimension() { 
		return dimension; 
	} 

	public void setDimension(Dimension dimension) { 
		this.dimension = dimension; 
	} 

	 
	public Rectangle getArea() { 
		return Area; 
	} 

	public void setArea(Rectangle area) { 
		this.Area = area; 
	} 
	 
	public I3 getIndex() { 
		return Index; 
	} 

	public void setIndex(I3 index) { 
		Index = index; 
	} 

	public I3 getSize() { 
		return Size; 
	} 

	public void setSize(I3 size) { 
		Size = size; 
	} 

	public AreaPointIndex getPoint() { 
		return point; 
	} 

	public void setPoint(AreaPointIndex point) { 
		this.point = point; 
	} 


	public AreaManager() 
	{ 
		super(); 
	} 
	 
	public AreaManager(Rectangle p_area) 
	{ 
		this.setArea(p_area); 
	} 
	 
	public AreaManager(Rectangle p_area, I3 p_index) 
	{ 
		this(p_area); 
		 
		this.setIndex(p_index); 
	} 
	 
	public AreaManager(Dimension p_area, I3 p_index) 
	{ 
		this(new Rectangle(0,0,p_area.width,p_area.height),p_index); 
		 
		this.setIndex(p_index); 
	} 
	 
	 
	public AreaManager(Rectangle p_area, I3 p_index , I3 p_size) 
	{ 
		 
		this(p_area,p_index); 
		 
		this.setSize(p_size); 
		 
	} 
	 
	 
	public AreaManager(Dimension p_area, I3 p_index , I3 p_size) 
	{ 
		 
		this(new Rectangle(0,0,p_area.width,p_area.height),p_index); 
		 
		this.setSize(p_size); 
		 
	} 
	 
	public AreaManager(Dimension area, Dimension dim ,AreaPointIndex size) 
	{ 
		 
		this(area,new I3((int)dim.getWidth(),(int)dim.getHeight(),(int)size.getIndex()), 
				  new I3((int)size.getSize().getWidth(),(int)size.getSize().getHeight(),0)); 
		 
		this.setDimension(dim); 
		 
		this.setPoint(size); 
	} 
	 
	 
	 
	/** 
	 * Return area by param that set in dtor 
	 * @return 
	 */ 
	 
	/* 
	public Rectangle get() 
	 { 
		 
	   Rectangle r = new Rectangle(0, 0, 0, 0); 

	   double wx = Area.width * 1.0; 
	 
	   double sx = wx / getIndex().getA(); 
	 
	   double sy = Area.height / getIndex().getB(); 
	 
	   int index =  getIndex().getC(); 
	   	 
	   double dy = 0.0; 
	 
	   double dx = 0.0; 
	   	    	 
	   dy = (index/(getIndex().getA())) ; 
	 
	   dx = index - (dy*(getIndex().getA())); 

	   r.x = (int)(sx*dx); 
	 
	   r.y = (int)(sy*dy); 
	 
	   if(Area.x != 0) 
	      r.x += Area.x; 
	 
	   if(Area.y != 0) 
	      r.y += Area.y; 

	   r.width = (int)sx; 

	   r.height = (int)sy; 
	 
	   if(getSize() != null) 
	   { 
	      r.width    *= getSize().getA(); 
	 
	      r.height   *= getSize().getB(); 
	   } 
	 
	      return r; 
	 
	 } 
	 
	 */ 
	 
	 
	/* 
	public Rectangle get(I3 p_size) 
	 { 
		 
	   I3 old_size = this.getSize(); 
	 
	   int old_index = this.getIndex().getC(); 
	 
	   this.Index.setC(p_size.getC()); 
	 
	   this.setSize(p_size); 
		 
	   Rectangle r = new Rectangle(0, 0, 0, 0); 

	   double wx = Area.width * 1.0; 
	 
	   double sx = wx / getIndex().getA(); 
	 
	   double sy = Area.height / getIndex().getB(); 
	 
	   int index =  getIndex().getC(); 
	   	 
	   double dy = 0.0; 
	 
	   double dx = 0.0; 
	   	    	 
	   dy = (index/(getIndex().getA())) ; 
	 
	   dx = index - (dy*(getIndex().getA())); 

	   r.x = (int)(sx*dx); 
	 
	   r.y = (int)(sy*dy); 
	 
	   if(Area.x != 0) 
	      r.x += Area.x; 
	 
	   if(Area.y != 0) 
	      r.y += Area.y; 

	   r.width = (int)sx; 

	   r.height = (int)sy; 
	 
	   if(getSize() != null) 
	   { 
	      r.width    *= getSize().getA(); 
	 
	      r.height   *= getSize().getB(); 
	   } 
	 
	   // Restore size 
	 
	   this.setSize(old_size); 
	 
	   this.Index.setC(old_index); 
	 
	   return r; 
	 
	 } 

	*/ 
	 
	 
	 
	public Rectangle get(int p_index) 
	 { 

	   Rectangle r = new Rectangle(0, 0, 0, 0); 

	   double wx = Area.width * 1.0; 
	 
	   int sx = (int)wx / getIndex().getA(); 
	 
	   int sy = (int)Area.height / getIndex().getB(); 
	 
	   int index =  p_index;//getIndex().getC(); 
	   	 
	   int dy = 0; 
	 
	   int dx = 0; 
	   	    	 
	   dy = (index/(getIndex().getA())) ; 
	 
	   dx = index - (dy*(getIndex().getA())); 

	   if(dx != 0.0) 
	   r.x = (int)(sx*dx); 
	 
	   if(dy != 0.0) 
	   r.y = (int)(sy*dy); 
	 
	   if(Area.x != 0) 
	      r.x += Area.x; 
	 
	   if(Area.y != 0) 
	      r.y += Area.y; 

	   r.width = (int)sx; 

	   r.height = (int)sy; 
	 
	   if(getSize() != null) 
	   { 
	      r.width    *= getSize().getA(); 
	 
	      r.height   *= getSize().getB(); 
	   } 
	 
	      return r; 
	 
	 } 

	 
	public String toString() 
	{ 
		return this.getSize().ToString() + " " + this.getIndex().ToString(); 
	} 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
