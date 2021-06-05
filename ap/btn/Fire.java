 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import ap.gen.IDGen; 
import ap.thread.Panel; 

public class Fire { 

	private int id; 
	 
	private int owner; 
	 
	private int state; 
	 
	private boolean mode; 
	 
	 
	 
	public int getOwner() { 
		return owner; 
	} 

	public void setOwner(int owner) { 
		this.owner = owner; 
	} 

	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public int getState() { 
		return state; 
	} 

	public void setState(int state) { 
		this.state = state; 
	} 

	public boolean isMode() { 
		return mode; 
	} 

	public void setMode(boolean mode) { 
		this.mode = mode; 
	} 

	public Fire() { 
		 
		this.setId(IDGen.nextId()); 
	} 
	 
	public Fire(int fire,boolean mode) { 
		 
		this(); 
		 
		this.setState(fire); 
		 
		this.setMode(mode); 
	} 
	 
	public Fire(int owner,int fire,boolean mode) { 
		 
		this(fire,mode); 
		 
		this.setOwner(owner); 
		 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%d %d %s",this.getOwner(),this.getId(),Panel.getEventName(this.getState())); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
