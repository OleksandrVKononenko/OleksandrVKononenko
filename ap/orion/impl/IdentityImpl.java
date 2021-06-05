package ap.orion.impl;

import ap.gen.IDGen;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.intf.Identity;

public class IdentityImpl implements Identity {

	private int 			pid; 
	
	private int 			id; 
	
	private int 			index; 
	 
	private int 			level; 
	
	private Orion			owner;
	 
	 
	public IdentityImpl() { 
		
		this.setId(IDGen.nextId("DTOR")); 
		
		 
		this.setPid(gl.E_ERROR); 
		 
		this.setIndex(gl.E_ERROR);
		
		this.setLevel(gl.E_ERROR);
		
	} 

	 
	@Override 
	public int getId() { 
		 
		return this.id; 
	} 

	 
	@Override 
	public void setId(int id) { 
		 
		this.id = id; 
	} 

	 
	@Override 
	public int getPid() { 
	 
		return this.pid; 
	} 

	 
	@Override 
	public void setPid(int pid) { 
		 
		this.pid = pid; 
	} 

	 
	@Override 
	public void setIndex(int index) { 
		 
		this.index = index; 
	} 

	 
	@Override 
	public int getIndex() { 
		 
		return this.index; 
	} 



	 
	@Override 
	public void setLevel(int level) { 
		 
		this.level = level; 
	} 

	 
	@Override 
	public int getLevel() { 
		 
		return this.level; 
	} 

	
	
	public String toString()
	{
		return gl.sf("Pid...%3d...Id...%3d...Index...%3d...Level...%2d",
					 (this.getPid() == gl.E_ERROR) ? 0 : this.getPid(),this.getId(),this.getIndex(),this.getLevel()
				);
	}
	 
	public static IdentityImpl get_instance() 
	{ 
		return new IdentityImpl(); 
	}


	@Override
	public void setOwner(Orion comp) {
		
		this.owner = comp;
	}


	@Override
	public Orion getOwner() {
		
		return this.owner;
	} 

	

}
