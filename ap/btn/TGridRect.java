 
 
package ap.btn; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D; 
import java.awt.Rectangle;
import java.util.Map;

import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.utils.DU;
import ap.utils.GU;
import ap.utils.Su; 


public class TGridRect { 

	private 	TGridLine 	line; 
	 
	private 	Rectangle 	bounds; 

	public TGridLine getLine() { 
		return line; 
	} 

	public void setLine(TGridLine line) { 
		this.line = line; 
	}
	 
	private Map<String,Object> data;
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	

	public  TGridRect() 
	{ 
		 
	} 
	 
	public  TGridRect(Rectangle bounds) 
	{ 
		this.setBounds(bounds); 
	} 
	 
	 
	public Rectangle getBounds() { 
		return bounds; 
	} 

	public void setBounds(Rectangle bounds) { 
		this.bounds = bounds; 
	} 
	 
	public void draw(Graphics2D g2) 
	{ 
		//int type = DU.to_int(Su.BeforeAtFirst(this.getType(),Parser.ATTR_DLM));
		
		//if(type == 30)
		GU.fillAlphaRect(g2,this); 
		
		if(this.getLine().getType().equalsIgnoreCase("BODY"))
		{
			g2.setStroke(new BasicStroke(2.0f));
			
			GU.draw_alpha_rect(g2,this.getBounds(),Color.gray,0.75f);
		}
		
		// Дорисовываем тип.
		
		if(this.getLine().getType().equalsIgnoreCase("LOW"))
		{
			// Необходимо прочитать состояние.
			/*
			int state = DU.to_int(Su.AfterAt(this.getType(),Parser.ATTR_DLM));
			
			GU.drawAlphaString(g2,
					state >= gl.E_OK ? gl.sf("%s",Su.BeforeAtFirst(this.getType(),Parser.ATTR_DLM)) : "",
					this.getBounds().x - 5,
					(this.getBounds().y+this.getBounds().height) + 7 , 
					Color.black,new Font("Tahoma",1,8),0.85f);
			*/
			/*

			GU.fillAlphaCircle(g2,
					new Rectangle( 
							
									this.getBounds().x +2 ,
									(this.getBounds().y + this.getBounds().height)+1,
									this.getBounds().width,
									this.getBounds().width
							),
							Color.BLUE,0.5f);
		
		Stroke p = g2.getStroke();
		
		g2.setStroke(new BasicStroke(2.0f));
		
		GU.drawAlphaCircle(g2,
				new Rectangle( 
								this.getBounds().x  + 2,
								(this.getBounds().y + this.getBounds().height)+1,
								this.getBounds().width,
								this.getBounds().width
						),
						Color.WHITE,0.75f);
		g2.setStroke(p);
		
		*/
		
		}
	} 

	public static 	TGridRect 	get_instance(Rectangle bounds,String series,String type,String subtype,TLineStyle style) 
	{ 
					TGridLine 	gl = TGridLine.getInstance(series,type,subtype,style); 
		 
					TGridRect 	gr = new TGridRect(bounds); 
		 
								gr.setLine(gl); 
		 
					return 	  	gr; 
	} 


	public static void main(String[] args) { 
		 
	} 

} 
