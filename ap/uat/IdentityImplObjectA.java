 
 
 
 
 
 
package ap.uat; 

public class IdentityImplObjectA implements IIdentityA { 

	 
	private int 			id; 
	 
	private int 			pid; 
	 
	private int 			index; 
	 
	private AtOm 			owner; 
	 
	private int 			level; 
		 
	private String 			name; 
	 
	 
	 
	public IdentityImplObjectA() { 
		 
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
	public void setOwner(AtOm owner) { 
		 
		this.owner = owner; 
	} 

	 
	@Override 
	public AtOm getOwner() { 
		 
		return this.owner; 
	} 

	 
	@Override 
	public void setLevel(int level) { 
		 
		this.level = level; 
	} 

	 
	@Override 
	public int getLevel() { 
		 
		return this.level; 
	} 

	 
	@Override 
	public void setName(String name) { 
		 
		this.name = name; 
	} 

	@Override 
	public String getName() { 
		 
		return this.name; 
	} 
	 
	public static IdentityImplObjectA get_instance() 
	{ 
		return new IdentityImplObjectA(); 
	} 

} 

