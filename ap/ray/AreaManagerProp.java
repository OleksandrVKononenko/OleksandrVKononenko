 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

public class AreaManagerProp extends AProperty { 

	 
	private  int area_width; 
	private  int area_height; 
	private  int area_cell_x; 
	private  int area_cell_y; 
	private  int object_size_in_cell_x; 
	private  int object_size_in_cell_y; 
	private  int object_start_index_x; 
	private  int object_start_index_y; 
	 
	 
	/** 
	 * @return the area_width 
	 */ 
	public int getArea_width() { 
		return area_width; 
	} 

	/** 
	 * @param area_width the area_width to set 
	 */ 
	public void setArea_width(int area_width) { 
		this.area_width = area_width; 
	} 

	/** 
	 * @return the area_hight 
	 */ 
	public int getArea_height() { 
		return area_height; 
	} 

	/** 
	 * @param area_hight the area_hight to set 
	 */ 
	public void setArea_height(int area_height) { 
		this.area_height = area_height; 
	} 

	/** 
	 * @return the area_cell_x 
	 */ 
	public int getArea_cell_x() { 
		return area_cell_x; 
	} 

	/** 
	 * @param area_cell_x the area_cell_x to set 
	 */ 
	public void setArea_cell_x(int area_cell_x) { 
		this.area_cell_x = area_cell_x; 
	} 

	/** 
	 * @return the area_cell_y 
	 */ 
	public int getArea_cell_y() { 
		return area_cell_y; 
	} 

	/** 
	 * @param area_cell_y the area_cell_y to set 
	 */ 
	public void setArea_cell_y(int area_cell_y) { 
		this.area_cell_y = area_cell_y; 
	} 

	/** 
	 * @return the object_size_in_cell_x 
	 */ 
	public int getObject_size_in_cell_x() { 
		return object_size_in_cell_x; 
	} 

	/** 
	 * @param object_size_in_cell_x the object_size_in_cell_x to set 
	 */ 
	public void setObject_size_in_cell_x(int object_size_in_cell_x) { 
		this.object_size_in_cell_x = object_size_in_cell_x; 
	} 

	/** 
	 * @return the object_size_in_cell_y 
	 */ 
	public int getObject_size_in_cell_y() { 
		return object_size_in_cell_y; 
	} 

	/** 
	 * @param object_size_in_cell_y the object_size_in_cell_y to set 
	 */ 
	public void setObject_size_in_cell_y(int object_size_in_cell_y) { 
		this.object_size_in_cell_y = object_size_in_cell_y; 
	} 

	/** 
	 * @return the object_start_index 
	 */ 
	public int getObject_start_index_x() { 
		return object_start_index_x; 
	} 

	/** 
	 * @param object_start_index the object_start_index to set 
	 */ 

	public void setObject_start_index_x(int object_start_index_x) { 
		this.object_start_index_x = object_start_index_x; 
	} 

	public int getObject_start_index_y() { 
		return object_start_index_y; 
	} 

	/** 
	 * @param object_start_index the object_start_index to set 
	 */ 
	public void setObject_start_index_y(int object_start_index_y) { 
		this.object_start_index_y = object_start_index_y; 
	} 
	 
	 
	 
	public AreaManagerProp() 
	{ 
		super("area_manager"); 
	} 
	 
	public AreaManagerProp(String name) 
	{ 
		super(name); 
		 
	} 
	 
	 
	public AreaManagerProp(String name , String value) 
	{ 
		super(name,value); 
		 
	} 
	 
	public boolean getValue() 
	{ 
		int value = gl.E_ERROR; 
		 
		String s_value = toStringValueOnly(); 
				 
		String a[] = s_value.split(","); 
				 
		 
		try 
		{ 
			 
			this.setArea_width(Integer.parseInt(a[0])); 
			 
			this.setArea_height(Integer.parseInt(a[1])); 
			 
			this.setArea_cell_x(Integer.parseInt(a[2])); 
			 
			this.setArea_cell_y(Integer.parseInt(a[3])); 
			 
			this.setObject_size_in_cell_x(Integer.parseInt(a[4])); 
			 
			this.setObject_size_in_cell_y(Integer.parseInt(a[5])); 
			 
			this.setObject_start_index_x(Integer.parseInt(a[6])); 
			 
			this.setObject_start_index_y(Integer.parseInt(a[7])); 
			 
		} 
		catch(NumberFormatException ex) 
		{ 
			gl.smn("<AreaManagerProp.getValue(Exception)"); 
			 
			return false; 
		} 
		 
		   	return true; 
	} 
	 
	 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
