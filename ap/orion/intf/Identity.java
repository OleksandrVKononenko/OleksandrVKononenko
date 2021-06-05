package ap.orion.intf;

import ap.orion.Orion;

public interface Identity { 
	 
	 
	public int getId(); 
	 
	public void setId(int id); 
	 
	 
	public int getPid(); 
	 
	public void setPid(int pid); 
	 
	 
	public void setIndex(int index); 
	 
	public int getIndex(); 
	 
	
	public void setOwner(Orion comp); 
	 
	public Orion getOwner(); 
	 
	 
	public void setLevel(int level); 
	 
	public int getLevel(); 
	
	
	 
} 
