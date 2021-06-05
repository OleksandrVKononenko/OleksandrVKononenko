 
 
 
 
 
 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics2D; 
import java.awt.Stroke; 
import java.awt.geom.*; 

import ap.global.gl; 
import ap.utils.GU; 

public class TGeneralPath{ 

	private GeneralPath origin; 
	 
	private String series; 
	 
	private int type; 
	 
	private TLineStyle style; 
	 
	private boolean bl_select; 
	 
	 

	public boolean isBl_select() { 
		return bl_select; 
	} 


	public void setBl_select(boolean bl_select) { 
		this.bl_select = bl_select; 
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


	public int getType() { 
		return type; 
	} 


	public void setType(int type) { 
		this.type = type; 
	 
		 
		 
	} 


	public GeneralPath getOrigin() { 
		return origin; 
	} 


	public void setOrigin(GeneralPath origin) { 
		this.origin = origin; 
	} 


	public TGeneralPath() 
	{ 
		 
	} 
	 
	public TGeneralPath(GeneralPath origin,String series,int type) 
	{ 
		this.setOrigin(origin); 
		 
		this.setSeries(series); 
		 
		this.setType(type); 
		 
		 
	} 
	 
	public static TGeneralPath getInstance(GeneralPath origin,String series,int type,TLineStyle style) 
	{ 
		return new TGeneralPath(origin,series,type,style); 
	} 
	 
	 
	public TGeneralPath(GeneralPath origin,String series,int type,TLineStyle style) 
	{ 
		this.setOrigin(origin); 
		 
		this.setSeries(series); 
		 
		this.setType(type); 
		 
		this.setStyle(style); 
		 
		 
	} 
	 
	public void draw(Graphics2D g) 
	{ 
		if(this.isBl_select()) 
		GU.drawAlphaPath(g,this.getOrigin(),gl.ma_on_select_style); 
		else 
		GU.drawAlphaPath(g,this.getOrigin(),this.getStyle()); 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		//return String.format("origin=%s,series=%s,type=%d",this.getOrigin().toString(),this.getSeries(),this.getType()); 

		return String.format("[%s].%s",this.getSeries(),gl.ma_names[this.getType()]); 
		 
	} 
	 
	public static void main(String[] args) { 
	 

	} 

} 
