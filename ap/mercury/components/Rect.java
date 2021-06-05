package ap.mercury.components;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Rect extends Rectangle {
	
	
	private int service = Cursor.DEFAULT_CURSOR;

	
	
	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public Rect() {
		
	}

	public Rect(Rectangle r) {
		super(r);
		
	}

	public Rect(Rectangle r,int service) {
		
		super(r);
		
		this.setService(service);
		
	}
	
	public Rect(Point p) {
		super(p);
		
	}

	public Rect(Dimension d) {
		super(d);
		
	}

	public Rect(int width, int height) {
		super(width, height);
		
	}

	public Rect(Point p, Dimension d) {
		super(p, d);
		
	}

	public Rect(int x, int y, int width, int height) {
		super(x, y, width, height);
	}	

	public static Rect get_instance(Rectangle rect, int service)
	{
		return new Rect(rect,service);
	}
	
	public static void main(String[] args) {
		
	}

}
