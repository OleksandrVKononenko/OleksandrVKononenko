 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.event.KeyEvent; 
import java.awt.event.MouseEvent; 

import ap.gen.IDGen; 
import ap.thread.Panel; 

public class Event { 

	private int id ; 
	 
	private Fire fire; 
		 
	private MouseEvent mouse_event; 
	 
	private KeyEvent key_event; 
	 
		 
	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public MouseEvent getMouse_event() { 
		return mouse_event; 
	} 

	public void setMouse_event(MouseEvent mouse_event) { 
		this.mouse_event = mouse_event; 
	} 

	public KeyEvent getKey_event() { 
		return key_event; 
	} 

	public void setKey_event(KeyEvent key_event) { 
		this.key_event = key_event; 
	} 
		 

	public Fire getFire() { 
		return fire; 
	} 

	public void setFire(Fire fire) { 
		this.fire = fire; 
	} 

	public Event() { 
		 
		this.setId(IDGen.nextId()); 
	} 
	 
	public Event(Fire fire,MouseEvent me) { 
		 
		this(); 
		 
		this.setFire(fire); 
		 
		this.setMouse_event(me); 
	} 
	 
	public Event(Fire fire) { 
		 
		this(); 
		 
		this.setFire(fire); 
	} 
	 
	 
	public Event(Fire fire,KeyEvent ke) { 
		 
		this(); 
		 
		this.setFire(fire); 
		 
		this.setKey_event(ke); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return this.getFire().toString(); 
	} 
	 
	@Override 
	public boolean equals(Object obj) 
	{ 
		Event evt = (Event)obj; 
		 
		if( 
			evt.getFire().getOwner() == this.getFire().getOwner() && 
			evt.getFire().getState() == this.getFire().getState() 
		   ) 
		return true; 
		else 
		return false; 
			 
	} 
	@Override 
	public int hashCode() 
	{ 
		String msg = String.format("%d%d",this.getFire().getOwner(),this.getFire().getState()); 
		 
		return msg.hashCode(); 
	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
