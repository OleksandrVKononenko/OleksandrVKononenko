 
package ap.btn; 

import java.awt.Graphics2D; 
import java.awt.Point;
import ap.global.gl; 
import ap.utils.GU; 

public class TGridLine { 

	private String 		name; 
	 
	private String 		type; 
	 
	private String 		subtype; 
	 
	private String 		series; 
	 
	private Point 		start; 
	 
	private Point 		end; 
	 
	private TLineStyle 	style; 
	 
	private int 		index;
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSubtype() { 
		return subtype; 
	} 

	public void setSubtype(String subtype) { 
		this.subtype = subtype; 
	 
		this.setName(this.toString()); 
	} 

	public TLineStyle getStyle() { 
		return style; 
	} 

	public void setStyle(TLineStyle style) { 
		this.style = style; 
	} 

	public String getSeries() { 
		return series; 
	} 

	public void setSeries(String series) { 
		this.series = series; 
	} 

	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 

	public String getType() { 
		return type; 
	} 

	public void setType(String type) { 
		this.type = type; 
	} 

	public Point getStart() { 
		return start; 
	} 

	public void setStart(Point start) { 
		this.start = start; 
	} 

	public Point getEnd() { 
		return end; 
	} 

	public void setEnd(Point end) { 
		this.end = end; 
	} 

	public TGridLine() { 
		 
	} 
	
	public TGridLine(Point start,Point end) { 
		 
		this.setStart(start); 
		 
		this.setEnd(end); 
		
	} 
	 
	public TGridLine(Point start,Point end,int index) { 
		 
		this(start,end); 
		 
		this.setIndex(index);
	} 
	 
	public TGridLine(Point start,Point end,String name) { 
		 
		this(start,end); 
		 
		this.setName(name); 
	} 
	
	public static TGridLine get_instance(Point start,Point end) 
	{ 
		return new TGridLine(start,end); 
	} 
	
	public static TGridLine get_instance(Point start,Point end,int index) 
	{ 
		return new TGridLine(start,end,index); 
	} 
	
	public static TGridLine getInstance(Point start,Point end,String name) 
	{ 
		return new TGridLine(start,end,name); 
	} 
	 
	public static TGridLine getInstance(Point start,Point end,String series,String type) 
	{ 
		return new TGridLine(start,end,series,type); 
	} 
	 
	public static TGridLine getInstance(Point start,Point end,String series,String type,TLineStyle style) 
	{ 
		return new TGridLine(start,end,series,type,style); 
	} 
	 
	public static TGridLine getInstance(Point start,Point end,String series,String type,String subtype,TLineStyle style) 
	{ 
		return new TGridLine(start,end,series,type,subtype,style); 
	} 
	 
	public static TGridLine getInstance(String series,String type,TLineStyle style) 
	{ 
		return new TGridLine(series,type,style); 
	} 
	 
	public static TGridLine getInstance(String series,String type,String subtype,TLineStyle style) 
	{ 
		return new TGridLine(series,type,subtype,style); 
	} 
	 
	public TGridLine(Point start,Point end,String series,String type) { 
		 
		this(start,end); 
		 
		this.setSeries(series); 
		 
		this.setType(type); 
		 
		this.setName(this.toString()); 
	} 
	 
	public TGridLine(Point start,Point end,String series,String type,TLineStyle style ) { 
		 
		this(start,end,series,type); 
		 
		this.setStyle(style); 
		 
	} 
	 
	public TGridLine(String series,String type,TLineStyle style ) { 
		 
		this.setSeries(series); 
		 
		this.setType(type); 
		 
		this.setStyle(style); 
		 
	} 

	public TGridLine(String series,String type,String subtype,TLineStyle style ) { 
		 
		this(series,type,style); 
		 
		this.setSubtype(subtype); 
		 
	} 
	 
	public TGridLine(Point start,Point end,String series,String type,String subtype,TLineStyle style ) { 
		 
		this(start,end,series,type,style); 
		 
		this.setSubtype(subtype); 
	} 
	 
	public void draw(Graphics2D g2) 
	{ 
		GU.drawAlphaLine(g2,this); 
	} 
	 

	

	@Override 
	public String toString() 
	{ 
		String msg = String.format("[%s].%s",this.getSeries(),this.getType()); 
		 
		if(this.getSubtype() != null) 
		{ 
			msg = String.format("[%s].%s.%s",this.getSeries(),this.getType(),this.getSubtype()); 
			 
			//gl.smn("---> Subtype : " + msg); 
		} 
		 
			return msg; 
	} 
	 
	public static void Test_toString(Point start,Point end) 
	{ 
		TGridLine gr = new TGridLine(start, end); 
		 
		gl.smn(gr.toString()); 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		Test_toString(new Point(1,1),new Point(10,10)); 

	} 

} 
