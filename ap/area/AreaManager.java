 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.area; 


import java.awt.Dimension; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Map.Entry; 



import ap.btn.TGridLine; 

import ap.global.*; 
import ap.prop.SBounds; 



public class AreaManager extends AreaBase{ 

	private static final long serialVersionUID = 1L; 

	public AreaManager() 
	{ 
		super(gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR); 
	} 
	 
	 
	public AreaManager(Dimension area,Dimension area_in_cells,Dimension object_size_in_cells,Dimension start_index_in_cells ) 
	{ 
		super(area,area_in_cells,object_size_in_cells,start_index_in_cells); 
	} 
	 
	public AreaManager(Dimension area,Dimension area_in_cells) 
	{ 
		super(area,area_in_cells,new Dimension(1,1),new Dimension(0,0)); 
	} 
	 
	public AreaManager(AreaManager manager) 
	{ 
		this( 
				manager.getArea(), 
				manager.getAreaInCells(), 
				manager.getObjectSizeInCells(), 
				manager.getObjectCoordInCells() 
				); 
	} 
	public AreaManager( 
			int 
			area_width, 
			int 
			area_height, 
			int 
			area_in_cells_width, 
			int 
			area_in_cells_height, 
			int 
			object_size_in_cells_width, 
			int 
			object_size_in_cells_height, 
			int 
			object_coord_in_cells_width, 
			int 
			object_coord_in_cells_height 
			) 
			{ 
				super( 
						 
						area_width, 
						 
						area_height, 
						 
						area_in_cells_width, 
						 
						area_in_cells_height, 
						 
						object_size_in_cells_width, 
						 
						object_size_in_cells_height, 
						 
						object_coord_in_cells_width, 
						 
						object_coord_in_cells_height 
										 
						 
						); 
			} 
	 
	 
	 public Rectangle get(int linear_index) 
	 { 
		 	Point coord = AreaManager.getCellCoordByLinearIndex( 
		 			linear_index, 
		 			this.getAreaInCells().width, 
		 			this.getAreaInCells().height 
		 			); 
		 
			return this.get(coord.x,coord.y); 
	 } 
	 

	 public int getLinearIndex(int x, int y) 
	 { 
		 return (y*this.getAreaInCells().width) + x; 
	 } 
	 
	 
	 
	 public List<Rectangle> getRectList(Dimension dim) 
	 { 
		 List<Rectangle> list = new ArrayList<Rectangle>(); 
		 
			for(int j=0;j<this.getAreaInCells().height;j++) 
				for(int i=0;i<this.getAreaInCells().width;i++) 
				{ 
					Rectangle rect  = this.get(i,j); 
					    	 
				   	gl.smn(SBounds.toString(rect)); 
					 
					list.add(new Rectangle((rect.x+dim.width),(rect.y+dim.height),rect.width,rect.height)); 
					 
				} 
				 
					return list; 
	 } 
	 
	 public List<Rectangle> getRects() 
	 { 
		 List<Rectangle> list = new ArrayList<Rectangle>(); 
		 
			for(int j=0;j<this.getAreaInCells().height;j++) 
				for(int i=0;i<this.getAreaInCells().width;i++) 
				{ 
					Rectangle rect  = this.get(i,j); 
					    	 
					list.add(rect); 
					 
				} 
				 
					return list; 
	 } 
	 
	 
	 
	 public Rectangle get(Dimension coord) 
	 { 
		 	return get(coord.width,coord.height); 
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
	 
	 public Dimension getDelta() 
	 { 
		 int w_delta = gl.E_EMPTY;
		 
		 int h_delta = gl.E_EMPTY;
		 
		 try {
			 
			 	w_delta = (this.getArea().width % this.getAreaInCells().width); 
		 
		 		h_delta = (this.getArea().height % this.getAreaInCells().height); 
		 

		 		return new Dimension(w_delta,h_delta);
		 }
		 catch(Exception e)
		 {
			 	return new Dimension(0,0);
		 } 
	 } 

	 
	 public Point getLocation() 
	 { 
		 Rectangle r = this.get(this.getObjectCoordInCells()); 

		 return new Point(r.x, r.y); 
	 } 
	 
	 
	 public Dimension get() 
	 { 
		Rectangle r = this.get(this.getObjectCoordInCells()); 

		return new Dimension(r.width, r.height); 
	 } 
	 

	 public Rectangle getBoundsNormalize(Rectangle owner) 
	 { 
		 Rectangle rect = getBounds(); 
		 
		 		   rect.x = rect.x + owner.x; 
		 		 
		 		   rect.y = rect.y + owner.y; 
		 		 
		return rect; 
	 } 
	 
	 public Rectangle getBounds() 
	 { 
		return new Rectangle(this.get(this.getObjectCoordInCells())); 
	 } 

	 
	 
	 public Rectangle get(Dimension start ,Dimension end) 
	 { 
		 
		 return this.get(start.width,start.height,end.width,end.height); 
	 } 
	 
	 public Rectangle get(int p_index_x, int p_index_y ,int p_width, int p_height) 
	 { 
		 Rectangle first  = this.get(p_index_x,p_index_y); 
		 
		 int width  = p_width * this.getCellSize().width; 
		 
		 int height = p_height * this.getCellSize().height; 
		 		 
		 Rectangle result = new Rectangle(first.x,first.y,width,height); 
		 
		 return result; 
				 
	 } 
	 
	 public int getCapacity() 
	 { 
		 return (this.getAreaInCells().width * this.getAreaInCells().height); 
	 } 
	 
	 
	public String toString() 
	{ 
		 
		return  "Size : " + this.getArea().toString() + " " + 
		 
				"CellSize : " + this.getAreaInCells().toString() + " " + 
				 
				"ObjectSizeInCells : " + this.getObjectSizeInCells().toString() + " " + 
				 
				"ObjectStartIndexInCells : " + this.getObjectSizeInCells().toString(); 
		 
	} 
	 
	public String toStringShort() 
	{ 
		return String.format("%d,%d,%d,%d,%d,%d,%d,%d", this.getArea().width, 
				this.getArea().height, 

				this.getAreaInCells().width, this.getAreaInCells().height, 

				this.getObjectSizeInCells().width, 
				this.getObjectSizeInCells().height, 

				this.getObjectCoordInCells().width, 
				this.getObjectCoordInCells().height); 
	 
	} 
	 
	public boolean isEmpty() 
	{ 
		 
		return 
	   ( 
		 this.getAreaInCells().width == gl.E_ERROR  && 
		 this.getAreaInCells().height == gl.E_ERROR 
	   ); 
		 
	} 
	 
	 
	 
	public Rectangle getShiftParamRect() 
	{ 
	 
	return 
	new Rectangle( 
			 
			this.getObjectSizeInCells().width, 
			this.getObjectSizeInCells().height, 
			 
			this.getObjectCoordInCells().width, 
			this.getObjectCoordInCells().height 
			 
			); 

	} 
	 

	public static Point getCellCoordByLinearIndex( int index , int cell_x_pow, int cell_y_pow) 
	 { 
		 
		int y = (index / cell_x_pow); 
		 
		int x =  index - (cell_x_pow * y); 
		 
		return new Point(x,y); 
		 
	 } 
	 
	public static int getLinearIndex(int width_in_cells, int x, int y) 
	 { 
		 return (y*width_in_cells) + x; 
	 } 
	 
	 
	public static void Test_toListRects() 
	{ 
		AreaManager am = new AreaManager( 
				new Dimension(100,100), 
				new Dimension(1,10) 
		); 
		 
		/* 
		am.getRectList(new Dimension(0,0)) 
		.forEach(a-> 
			{ 
				gl.smn(a.toString()); 
			} 
		); 
		*/ 
		 
		am.getRects() 
		.forEach(a-> 
			{ 
				gl.smn(a.toString()); 
			} 
		); 
	} 
	 
	public Map<Rectangle,Rectangle> getMap2(Dimension dim,Dimension delta) 
	{ 
		Map<Rectangle,Rectangle> map = new HashMap<Rectangle,Rectangle>(); 
	 
		for(int j=0;j<this.getAreaInCells().height;j++) 
			for(int i=0;i<this.getAreaInCells().width;i++) 
			{ 
				 
				Rectangle r = this.get(i,j); 
				 
				AreaManager  al = new AreaManager( 
						new Dimension(r.width,r.height), 
						dim 
						); 
				 
				for(int k=0;k<al.getAreaInCells().height;k++) 
					for(int l=0;l<al.getAreaInCells().width;l++) 
					{ 
						Rectangle rl = al.get(new Dimension(l,k)); 
						 
						Rectangle tr = new Rectangle((r.x+rl.x)+delta.width,(r.y+rl.y)+delta.height,rl.width,rl.height); 
						 
						map.put(new Rectangle(i,j,l,k),tr); 
						 
					} 
				 
			} 
		 
				return map; 
	} 
	 
	public Map<Rectangle,Rectangle> getMap() 
	{ 
		Map<Rectangle,Rectangle> map = new HashMap<Rectangle,Rectangle>(); 
	 
		for(int j=0;j<this.getAreaInCells().height;j++) 
			for(int i=0;i<this.getAreaInCells().width;i++) 
			{ 
				 
				Rectangle r = this.get(i,j); 
						 
				map.put(new Rectangle(i,j,0,0),r); 
				 
			} 
		 
				return map; 
	} 
	 
	 
	public List<TGridLine> getGridLinesV() { 
		
		List<TGridLine> result_list = new ArrayList<TGridLine>(); 
		 
		// Get delta. 
		 
		Dimension 	dim_delta 	= this.getDelta(); 
				 
		int 		h_delta 	= dim_delta.width /2; 
				 
		int 		y_delta 	= dim_delta.height /2; 
				 

		// V - Line 
		
		for (int i = 0; i <= this.getAreaInCells().width; i++) { 

			Point start = new Point((i * this.getCellSize().width)+h_delta,y_delta ); 

			Point end = new Point((i * this.getCellSize().width)+h_delta, 
					(this.getAreaInCells().height * this.getCellSize().height)+y_delta); 

			result_list.add(new TGridLine(start, end,i)); 

		} 

			return result_list; 
	} 

	    public List<TGridLine> getGridLinesH() { 

		List<TGridLine> result_list = new ArrayList<TGridLine>(); 

		// Get delta. 
		 
		Dimension dim_delta = this.getDelta(); 
		 
		int h_delta = dim_delta.width /2; 
		 
		int y_delta = dim_delta.height /2; 
		 
		 
		//gl.smn("--->h_delta : " + h_delta + " " + "y_delta : " + y_delta ); 
		 
		// H - Line 

		for (int i = 0; i <= this.getAreaInCells().height; i++) { 
			 
			int start_x = h_delta; 
			 
			int start_y =  (i * this.getCellSize().height)+y_delta; 
					 
			Point start = new Point(start_x,start_y); 

			int end_x = (this.getAreaInCells().width * this.getCellSize().width); 
			 
			int end_y = (i * this.getCellSize().height)+y_delta; 
					 
			int etalon = (this.getArea().width - dim_delta.width); 
					 
			int real = (end_x - start_x); 
			 
			int fix  = (etalon - real); 
			 
			//gl.smn("--->h_fix : " + fix); 
			 
			end_x += fix; 
			 
			Point end = new Point(end_x,end_y); 

			result_list.add(new TGridLine(start, end,i)); 

		} 

		return result_list; 
	} 
	 
	public static void Test_Queue1() 
	{ 
		AreaManager am = new AreaManager( 
				new Dimension(100,100), 
				new Dimension(3,3) 
		); 
		 
		Map<Rectangle,Rectangle> map = am.getMap2(new Dimension(3,3),new Dimension(0,0)); 
		 
		int index = gl.E_OK; 
		 
		for(Entry<Rectangle,Rectangle> e : map.entrySet()) 
		{ 
			Rectangle r_key = e.getKey(); 
			 
			Rectangle r_value = e.getValue(); 
			 
			gl.smn(String.format("%04d key : %s value : %s",index,SBounds.toString(r_key),SBounds.toString(r_value))); 
			 
			index++; 
			 
		} 
		 
		 
	} 
	 
	 
	 
	 
	public static void Test_getO() 
	{ 
		AreaManager am = new AreaManager( 
				new Dimension(100,100), 
				new Dimension(10,10) 
		); 
		 
		for(int j=0;j<am.getAreaInCells().height;j++) 
			for(int i=0;i<am.getAreaInCells().width;i++) 
			{ 
				AreaManager al = am.getO(i,j,new Dimension(3,3)); 
				 
				for(int k=0;k<al.getAreaInCells().height;k++) 
					for(int l=0;l<al.getAreaInCells().width;l++) 
					{ 
						 
						gl.smn("Data :" + al.get(i,j).toString()); 
						 
					} 
		} 
	} 
	 
	 
	 
	public static void Test_getArea() 
	{ 
		 
		AreaManager am = new AreaManager( 
				new Dimension(121,61), 
				new Dimension(10,10), 
				new Dimension(2,3), 
				new Dimension(0,0) 
		); 
			 
			for(int j=0;j<am.getAreaInCells().height;j++) 
				for(int i=0;i<am.getAreaInCells().width;i++) 
				{ 
					gl.smn(" Index : [" +  j +"," + i + "] "  + am.get(i,j).toString() + " index : " + am.getLinearIndex(i, j) + " size : " + am.getObjectSizeInCells()); 
				} 
			 
	} 

	public static void Test_Positioning() 
	{ 
		 
		AreaManager am = new AreaManager( 
				new Dimension(121,61), 
				new Dimension(10,10), 
				new Dimension(2,3), 
				new Dimension(0,0) 
		); 
			 
			for(int j=0;j<am.getAreaInCells().height;j++) 
				for(int i=0;i<am.getAreaInCells().width;i++) 
				{ 
					//gl.smn(" Index : [" +  j +"," + i + "] "  + am.get(i,j).toString() + " index : " + am.getLinearIndex(i, j) + " size : " + am.getObjectSizeInCells()); 
					 
					 am.setObjectCoordInCells(new Dimension(i,j)); 
					 
					gl.smn(" Index  : [" +  j +"," + i + "] "  + am.get(i,j).toString() + 
						   " Linear : " + am.getLinearIndex(i, j) + 
						   " Bounds : " + am.getBounds().toString() + 
						   " Coord  : " + am.getObjectCoordInCells().toString() 
						 
							); 
							 
				} 
			 
	} 

	public static void Test_Positioning_Ex() 
	{ 
		 
		AreaManager am = new AreaManager( 
				new Dimension(100,100), 
				new Dimension(11,10), 
				new Dimension(1,1), 
				new Dimension(0,0) 
		); 
			 
			for(int j=0;j<am.getAreaInCells().height;j++) 
				for(int i=0;i<am.getAreaInCells().width;i++) 
				{ 
					//gl.smn(" Index : [" +  j +"," + i + "] "  + am.get(i,j).toString() + " index : " + am.getLinearIndex(i, j) + " size : " + am.getObjectSizeInCells()); 
					 
					 am.setObjectCoordInCells(new Dimension(i,j)); 
					 
					gl.smn(" Index  : [" +  j +"," + i + "] "  + am.get(i,j).toString() + 
						   " Linear : " + am.getLinearIndex(i, j) + 
						   " Bounds : " + am.getBounds().toString() + 
						   " Coord  : " + am.getObjectCoordInCells().toString() 
						 
							); 
							 
				} 
			 
	} 
	 
	 
	 
	public static void Test_Index(AreaManager am) 
	{ 
		 
		Object v = new Object(){}; 
		 
		int i_cell_x = am.getAreaInCells().width; 
		 
		int i_cell_y = am.getAreaInCells().height; 
				 
		int size = (i_cell_x*i_cell_y); 
		 
		for(int i=0; i < size;i++) 
		{ 
			Point p = AreaManager.getCellCoordByLinearIndex(i, i_cell_x, i_cell_y); 
			 
			int i_chk = AreaManager.getLinearIndex(i_cell_x,p.x,p.y); 
			 
			Rectangle rect = am.get(i); 
			 
			gl.tracex(v,String.format("%s...%d...[%d,%d]...%d...%s...%s","Index",i,p.x,p.y,i_chk,(i_chk==i)?"Ok":"Error",SBounds.toString(rect))); 
			 
		} 
	} 
	 
	public static AreaManager getInstance(Dimension dim) 
	{ 
		return new AreaManager(new Dimension(1,1),dim,new Dimension(1,1),new Dimension(1,1)); 
	} 
	public static void Test_getGridLines() 
	{ 
		 
		AreaManager am = new AreaManager( 
				new Dimension(100,100), 
				new Dimension(10,10), 
				new Dimension(1,10), 
				new Dimension(0,0) 
		); 
		 
		List<TGridLine> v_lines = am.getGridLinesV(); 
		 
		v_lines.forEach(a->{ gl.smn("V : " + a.toString());}); 
		 
		 
		List<TGridLine> h_lines = am.getGridLinesH(); 
		 
		h_lines.forEach(a->{ gl.smn("H : " + a.toString());}); 
		 
			 
		 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		 
			//AreaManager.Test_Index(am); 
			 
			//AreaManager.Test_getArea(); 
			 
			//AreaManager.Test_Positioning_Ex(); 
			 
			Test_toListRects(); 
			 
			//Test_getO(); 
			 
			//Test_Queue1(); 
			 
			//Test_getMapRects(new Rectangle(0,0,0,0)); 
			 
			//Test_getMapRects(new Rectangle(1,1,1,1)); 
		 
			//Test_getGridLines(); 
			 			 
	 
	 	} 


} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:39 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:13 
