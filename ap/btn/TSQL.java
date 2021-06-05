 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.util.List; 

public class TSQL { 

	 
	private List<TSQL> items; 
	 
	private int parent_id; 
	 
	private int id; 
	 
	private int type; 
	 
	private int name; 
	 
	 
	 
	public int getName() { 
		return name; 
	} 

	public void setName(int name) { 
		this.name = name; 
	} 

	public List<TSQL> getItems() { 
		return items; 
	} 

	public void setItems(List<TSQL> items) { 
		this.items = items; 
	} 

	public int getParent_id() { 
		return parent_id; 
	} 

	public void setParent_id(int parent_id) { 
		this.parent_id = parent_id; 
	} 

	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	private String text; 
	 
	public String getText() { 
		return text; 
	} 

	public void setText(String text) { 
		this.text = text; 
	} 

	public TSQL() { 
		 
	} 
	 
	public TSQL(String text) { 
		 
		this.setText(text); 
	} 

	@Override 
	public String toString() 
	{ 
		String msg = String.format("name : %s pid : %3d id : %3d type : %3d name : %s text :%s", 
				 
				this.getName(), 
				this.getParent_id(), 
				this.getId(), 
				this.getType(), 
				this.getName(), 
				this.getText() 
				 
				); 
		 
		return this.getText(); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
