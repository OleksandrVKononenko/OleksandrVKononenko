 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Point; 
import java.util.Vector; 

public class APanelInfo { 

	public int id; 
	 
	public int pid; 
	 
	public String name ; 
	 
	public Dimension size; 
	 
	public Point location; 
	 
	public Color color; 
	 
	public int index; 
	 
	public APanelInfo() 
	{ 
		 
	} 
	 
	public APanelInfo(int id, String name,Dimension size,Color color) 
	{ 
		this.setId(id); 
		 
		this.setName(name); 
		 
		this.setSize(size); 
		 
		this.setColor(color); 
		 
	} 
	 
	public APanelInfo(int id, String name,Dimension size,Point location,Color color) 
	{ 
		this.setId(id); 
		 
		this.setName(name); 
		 
		this.setSize(size); 
		 
		this.setLocation(location); 
		 
		this.setColor(color); 
		 
	} 
	 
	 
	public APanelInfo(int pid,int id, String name,Dimension size,Color color) 
	{ 
		this(id,name,size,color); 
		 
		this.setPid(pid); 
		 
	} 
	 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 
	 
	public Point getLocation() { 
		return location; 
	} 

	public void setLocation(Point location) { 
		this.location = location; 
	} 

	 
	public int getId() { 
		return id; 
	} 


	public void setId(int id) { 
		this.id = id; 
	} 



	public int getPid() { 
		return pid; 
	} 



	public void setPid(int pid) { 
		this.pid = pid; 
	} 



	public String getName() { 
		return name; 
	} 



	public void setName(String name) { 
		this.name = name; 
	} 



	public Dimension getSize() { 
		return size; 
	} 



	public void setSize(Dimension size) { 
		this.size = size; 
	} 



	public Color getColor() { 
		return color; 
	} 



	public void setColor(Color color) { 
		this.color = color; 
	} 


	public String toString() 
	{ 
		//return this.getIndex() + " " + this.getId() + " " + this.getName() + " " + this.getLocation().toString() + " "+ this.getColor().toString(); 
		return this.getPid() + "/" + this.getId(); 
	} 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
