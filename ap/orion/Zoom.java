package ap.orion;

import java.awt.geom.Rectangle2D;

import ap.global.gl;
import ap.shape.Ru;

public class Zoom {
	
	private Rectangle2D		zoom_rect ;
		
	public Rectangle2D getZoom_rect() {
		
		return zoom_rect;
		
	}

	public void setZoom_rect(Rectangle2D zoom_rect) {
		
		this.zoom_rect = zoom_rect;
	}

	public Zoom()
	{
		this.setZoom_rect(new Rectangle2D.Double(1.0d,1.0d,1.0d,1.0d));
	}
	
	public Zoom(Rectangle2D b,Rectangle2D o)
	{
		this();
	
		this.setZoom_rect(new Rectangle2D.Double(b.getX() / o.getWidth(),b.getY() / o.getHeight(),b.getWidth() / o.getWidth(),b.getHeight() / o.getHeight()));
	}
	
	public String toString()
	{
		return gl.sf("Zoom...%s",Ru.toString2D(this.getZoom_rect()));
	}
	
	public static Zoom get_instance(Rectangle2D b,Rectangle2D o)
	{
		return new Zoom(b,o);
	}
	
	public static Zoom get_instance()
	{
		return new Zoom();
	}

}
