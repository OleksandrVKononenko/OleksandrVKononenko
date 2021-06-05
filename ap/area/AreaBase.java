 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.area; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Insets; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.io.Serializable; 
import java.util.Map; 
import java.util.Map.Entry; 

import ap.btn.TGridLine; 
import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 

public class AreaBase implements Serializable { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	private Dimension Area = null; 
	 
	private Dimension AreaInCells = null; 
	 
	private Dimension ObjectSizeInCells = null; 
	 
	private Dimension ObjectCoordInCells = null; 
	 
	private int DistributionType = gl.E_ERROR; 
	 
	private Point basePoint; 
	 
	 
	public Point getBasePoint() { 
		return basePoint; 
	} 


	public void setBasePoint(Point basePoint) { 
		this.basePoint = basePoint; 
	} 

	 
	 
	public AreaBase(Dimension area) 
	{ 
		this(); 
		 
		this.setArea(area); 
		 
	} 
	 
	public AreaBase(Dimension area,Dimension area_in_cells) 
	{ 
		this(area); 
		 
		this.setAreaInCells(area_in_cells); 
		 
		 
	} 
	 
	public AreaBase(Dimension area,Dimension area_in_cells,Dimension object_size_in_cells) 
	{ 
		 
		this(area,area_in_cells); 
		 
		this.setObjectSizeInCells(object_size_in_cells); 
		 
	} 

	public AreaBase(Dimension area,Dimension area_in_cells,Dimension object_size_in_cells, Dimension object_coord_in_cells) 
	{ 
		 
		this(area,area_in_cells,object_size_in_cells); 
		 
		this.setObjectCoordInCells(object_coord_in_cells); 
		 
	} 
	 
	public AreaBase( 
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
		this 
		( 
		new Dimension(area_width,area_height), 
		new Dimension(area_in_cells_width,area_in_cells_height), 
		new Dimension(object_size_in_cells_width,object_size_in_cells_height), 
		new Dimension(object_coord_in_cells_width,object_coord_in_cells_height) 
		); 
	 
		} 
	 
	public int getDistributionType() { 
		return DistributionType; 
	} 

	public void setDistributionType(int distributionType) { 
		DistributionType = distributionType; 
	} 

	/** 
	 * @return the objectCoordInCells 
	 */ 
	public Dimension getObjectCoordInCells() { 
		return ObjectCoordInCells; 
	} 

	/** 
	 * @param objectCoordInCells the objectCoordInCells to set 
	 */ 
	public void setObjectCoordInCells(Dimension objectCoordInCells) { 
		ObjectCoordInCells = objectCoordInCells; 
	} 

	/** 
	 * @return the objectSizeInCells 
	 */ 
	public Dimension getObjectSizeInCells() { 
		return ObjectSizeInCells; 
	} 

	/** 
	 * @param objectSizeInCells the objectSizeInCells to set 
	 */ 
	public void setObjectSizeInCells(Dimension objectSizeInCells) { 
		ObjectSizeInCells = objectSizeInCells; 
	} 

	/** 
	 * @return the areaInCells 
	 */ 
	public Dimension getAreaInCells() { 
		return AreaInCells; 
	} 

	/** 
	 * @param areaInCells the areaInCells to set 
	 */ 
	public void setAreaInCells(Dimension areaInCells) { 
		AreaInCells = areaInCells; 
	} 

	/** 
	 * @return the area 
	 */ 
	public Dimension getArea() { 
		return Area; 
	} 

	/** 
	 * @param area the area to set 
	 */ 
	public void setArea(Dimension area) { 
		Area = area; 
	} 
	 
	 
	 public Dimension getCellSize() 
	 { 
		 if(this.getAreaInCells().width == gl.E_EMPTY ) 
			this.setAreaInCells(new Dimension(gl.E_OK,this.getAreaInCells().height)); 
		 else if(this.getAreaInCells().height == gl.E_EMPTY ) 
			 this.setAreaInCells(new Dimension(this.getAreaInCells().width,gl.E_OK)); 
			 
		 return new Dimension 
		 ( 
		 this.getArea().width / this.getAreaInCells().width, 
		 this.getArea().height / this.getAreaInCells().height 
		 ); 
		 
	 } 
	 
	 public Rectangle get(int px, int py) 
	 { 
		 Dimension cz = this.getCellSize(); 

		 Rectangle rect = Ru.get_init_rect(gl.E_EMPTY); 
		 /* 
		 if(this.getAreaInCells().width == (this.getObjectCoordInCells().width+1) && py != gl.E_EMPTY) 
			 rect = new Rectangle( 
					 
					 (cz.width * px), 
					 (this.getArea().height - (cz.height*this.getObjectSizeInCells().height)), 
					 (cz.width*this.getObjectSizeInCells().width), 
					 (cz.height*this.getObjectSizeInCells().height) 

					 ); 

		 else 
			 */ 
		 rect = new Rectangle( 
				 
				 (cz.width * px), 
				 (cz.height * py), 
				 (cz.width*this.getObjectSizeInCells().width), 
				 (cz.height*this.getObjectSizeInCells().height) 
				 
				 ); 
		 
		 return rect; 
		 
	 } 
	 
	 public AreaManager getO(Dimension dm,Dimension dim) 
	 { 
		 return this.getO(dm.width,dm.height,dim); 
	 } 
	 
	 public AreaManager getO(int px, int py,Dimension dim) 
	 { 
		 Dimension cz = this.getCellSize(); 

		 Rectangle rect = new Rectangle( 
				 
				 (cz.width * px), 
				 (cz.height * py), 
				 (cz.width*this.getObjectSizeInCells().width), 
				 (cz.height*this.getObjectSizeInCells().height) 
				 
				 ); 
		 
		 
		 
		 AreaManager result = new AreaManager(new Dimension(rect.width,rect.height),dim); 
		 
		 result.setBasePoint(new Point(rect.x,rect.y)); 
		 
		 return result; 
		 
	 } 
	 
	 
	 public AreaBase() 
	 { 
		 
	 } 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:39 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:13 
