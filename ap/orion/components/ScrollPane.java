package ap.orion.components;

import java.awt.Component;

import javax.swing.JScrollPane;

import ap.orion.Orion;

public class ScrollPane extends JScrollPane {
	
	
	private Orion owner;

	
	
	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}

	public ScrollPane() {
		
	}

	private  ScrollPane(Component view) {
		super(view);
		
	}

	public ScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		
	}

	public ScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		
	}

	public static ScrollPane get_instance(Component view) 
	{
		return new ScrollPane(view);
	}
	
	public static void main(String[] args) {
		

	}

}
