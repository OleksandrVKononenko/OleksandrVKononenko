 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 

public class AreaManagerEx extends AreaManagerProp{ 


	public AreaManagerEx() 
	{ 
		 
	} 
	 
	public AreaManagerEx(int p_area_width,int p_area_hight,int p_area_cell_x, int p_area_cell_y , int p_object_size_in_cell_x,int p_object_size_in_cell_y,int p_object_start_index_x,int p_object_start_index_y) 
	{ 
		 
		this.setArea_width(p_area_width); 
		 
		this.setArea_height(p_area_hight); 
		 
		this.setArea_cell_x(p_area_cell_x); 
		 
		this.setArea_cell_y(p_area_cell_y); 
		 
		this.setObject_size_in_cell_x(p_object_size_in_cell_x); 
		 
		this.setObject_size_in_cell_y(p_object_size_in_cell_y); 
		 
		this.setObject_start_index_x(p_object_start_index_x); 
		 
		this.setObject_start_index_y(p_object_start_index_y); 
		 
	} 
	 public Rectangle get(Dimension coord) 
	 { 
			return this.get(coord.width,coord.height); 
	 } 
	 
	 public Rectangle get(Point coord) 
	 { 
			return this.get(coord.x,coord.y); 
	 } 
	 
	 public Dimension getSize(Point coord) 
	 { 
		 	Rectangle r = this.get(coord); 
		 	 
			return new Dimension(r.width,r.height); 
	 } 
	 
	 public Point getLocation() 
	 { 
		 Rectangle r = this.get(this.getObject_start_index_x(), 
					this.getObject_start_index_y()); 

			return new Point(r.x, r.y); 
	 } 
	 
	 
	 public Dimension getAreaSize() 
	 { 
		 return new Dimension(this.getArea_width(),this.getArea_height()); 
	 } 
	 
	 public Dimension get() 
	 { 
		Rectangle r = this.get(this.getObject_start_index_x(), 
				this.getObject_start_index_y()); 

		return new Dimension(r.width, r.height); 
	 } 
	 

	 public Rectangle getBounds() 
	 { 
		Rectangle r = this.get(this.getObject_start_index_x(), 
				this.getObject_start_index_y()); 

		return r; 
	 } 

	 
	public Rectangle get(int p_index_x, int p_index_y) 
	 { 
		 
		//gl.smn("get() input : " + this.getAreaSize().toString()); 
		 
		this.setObject_start_index_x(p_index_x); 
		 
		this.setObject_start_index_y(p_index_y); 
		 
		Rectangle r = new Rectangle(0, 0, 0, 0); 
		 

		int cell_size_x = this.getArea_width() / this.getArea_cell_x(); 
				 
		int cell_size_y = this.getArea_height() / this.getArea_cell_y(); 
		 
		 
		int x_index = this.getObject_start_index_x(); 
		 
			x_index = (x_index % this.getArea_cell_x()); 
		 
		int y_index = this.getObject_start_index_y(); 
		 
			y_index = (y_index % this.getArea_cell_y()); 
		 
			 
		int cell_x_pos = (int)(cell_size_x *x_index); 
		 
		int cell_y_pos = (int)(cell_size_y *y_index); 
					 
		r.x = (int)cell_x_pos; 
		 
		r.y = (int)cell_y_pos; 
		 
		r.width = cell_size_x; 
		 
		r.height = cell_size_y; 
		 
		 
		if(this.getObject_size_in_cell_x() != gl.E_EMPTY && ((r.width * this.getObject_size_in_cell_x()) <=this.getArea_width())) 
		   	r.width *= this.getObject_size_in_cell_x(); 
		 
		if(this.getObject_size_in_cell_y() != gl.E_EMPTY && ((r.height * this.getObject_size_in_cell_y()) <= this.getArea_height())) 
		   	r.height *= this.getObject_size_in_cell_y(); 
		 
		 
		//gl.smn("get() output : " + r.toString()); 
		 
		return r; 
		 
		 
	 
	 } 
	 
	 
	public String toString() 
	{ 
		 
		return 
				 
		this.getArea_width()+","+ 
		 
		this.getArea_height()+","+ 
		 
		this.getArea_cell_x()+","+ 
		 
		this.getArea_cell_y()+","+ 
		 
		this.getObject_size_in_cell_x()+","+ 
		 
		this.getObject_size_in_cell_y()+","+ 
		 
		this.getObject_start_index_x()+","+ 
		 
		this.getObject_start_index_y(); 
		 
		 
	} 

	 
	public static void Test() 
	{ 
		 
		AreaManagerEx amex = new AreaManagerEx(121,61,10,10,1,1,0,0); 
			 
			for(int j=0;j<amex.getArea_cell_y();j++) 
				for(int i=0;i<amex.getArea_cell_x();i++) 
					gl.smn(" Index : [" +  j +"," + i + "] "  + amex.get(i,j).toString()); 
		 
		 
	} 
	 
	public static void TestIndex( int size , int cell_x, int cell_y) 
	{ 
		 
		for(int i=0; i < size;i++) 
		{ 
			Point p = gl.getCellCoord(i, cell_x, cell_y); 
			 
			gl.smn("" + i + " [" + p.x+"," + p.y +  "]"); 
		} 
		 
		 
	} 
	 
	 
	 
	public static void TestSingleObject() 
	{ 
		 
		AreaManagerEx amex = new AreaManagerEx(121,61,10,10,1,1,0,0); 
		 
		 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

		AreaManagerEx.TestIndex(5*5,5,5); 
			 
	 
	 	} 
} 
	 

// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
