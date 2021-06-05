 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
package ap.force; 

import java.util.HashSet; 


//container that stores all of the game objects 
public class Stage 
{ 
	private HashSet<Actable> act = new HashSet<Actable>(); 
	private HashSet<Actable> add = new HashSet<Actable>(); 
	private HashSet<Actable> remove = new HashSet<Actable>(); 
	 
	public Stage() 
	{ 
		act = new HashSet<Actable>(); 
		add = new HashSet<Actable>(); 
		remove = new HashSet<Actable>(); 
	} 
	 
	public void update() 
	{ 
		act.addAll(add); 
		add.clear(); 
		act.removeAll(remove); 
		remove.clear(); 
	} 
	 
	public void add(Actable a) 
	{ 
		add.add(a); 
	} 
	 
	public void remove(Actable a) 
	{ 
		remove.remove(a); 
	} 
	 
	public HashSet<Actable> getActables() 
	{ 
		return act; 
	} 
	 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
