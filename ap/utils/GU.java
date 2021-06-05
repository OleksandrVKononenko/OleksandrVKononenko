 
/** 
* 
*/ 
package ap.utils; 

import java.awt.AlphaComposite; 
import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Composite; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GraphicsConfiguration; 
import java.awt.GraphicsEnvironment; 
import java.awt.Paint; 
import java.awt.Point; 
import java.awt.RadialGradientPaint; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.Shape; 
import java.awt.Stroke; 
import java.awt.Toolkit; 
import java.awt.font.FontRenderContext; 
import java.awt.font.GlyphVector; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.GeneralPath; 
import java.awt.geom.Point2D; 
import java.awt.image.BufferedImage; 
import java.util.List; 

import ap.area.AreaManager; 
import ap.btn.TBtn; 
import ap.btn.TGridLine; 
import ap.btn.TGridRect; 
import ap.btn.TLineStyle; 
import ap.btn.TStroke; 
import ap.global.gl; 
import ap.shape.Ru; 
import ap.shape.TRect; 
import ap.shape.TRectangle; 


public class GU { 

	/** 
	 * 
	 */ 

	public static void drawImage(Graphics2D g2,BufferedImage bi,Rectangle rect) 
	{ 
		g2.drawImage(bi,rect.x,rect.y,rect.width+1,rect.height+1,null); 
	} 
	 
	 
	public static void drawGradient(Graphics2D g2,Rectangle r,Color color_from ,Color color_to) 
	{ 
		drawGradient(g2,r.x,r.y,r.width,r.height,color_from,color_to); 
	} 
	 
	public static void drawGradient(Graphics2D g2,int x,int y,int w,int h,Color color_from ,Color color_to) 
	{ 
	 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

	    Paint p = new GradientPaint(x,y,color_from,w,h,color_to); 
	 
	    g2.setPaint(p); 
	 
	    g2.fillRect(x, y, w, h); 
		 
	} 

	 
	public static void drawGradientEx(Graphics2D g2,Rectangle grect,Rectangle rect,Color color_from ,Color color_to) 
	{ 
	 
		Paint old_paint = g2.getPaint(); 
				 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

		//Top->Bottom 
	    //Paint p = new GradientPaint(grect.x+grect.width,grect.y,color_from,grect.width,grect.height,color_to); 
		 
		// Right 
		//Paint p = new GradientPaint(grect.width,grect.height,color_from,grect.x,grect.y,color_to); 

		// Left 
		Paint p = new GradientPaint(grect.x,grect.y,color_from,grect.width,grect.y,color_to); 
		 
		// Angle 
		//Paint p = new GradientPaint(grect.width/2,grect.height,color_from,grect.x,grect.y,color_to); 
		 
		// Bottom -> Top 
		//Paint p = new GradientPaint(grect.x,grect.height,color_from,grect.x,grect.y,color_to); 
	 
		g2.setPaint(p); 
	 
	    g2.fillRect(rect.x,rect.y,rect.width,rect.height); 
	 
	    g2.setPaint(old_paint); 
	 
	} 
	 
	 
	public static void drawGradient(Graphics2D g2,Rectangle grect,Rectangle rect,Color color_from ,Color color_to,int gradient_type) 
	{ 
	 
		Paint old_paint = g2.getPaint(); 
				 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

		Paint p = null; 
		 
		switch(gradient_type) 
		{ 
			case gl.ALI.LEFT: 
			{ 
				p = new GradientPaint(grect.x,grect.y,color_from,grect.width,grect.y,color_to); 
				 
				break; 
			} 
		 
	 
			case gl.ALI.TOP: 
			{ 
				p = new GradientPaint(grect.x+grect.width,grect.y,color_from,grect.width,grect.height,color_to); 
				 
				break; 
			} 
	 
			case gl.ALI.RIGHT: 
			{ 
				 
				p = new GradientPaint(grect.width,grect.y,color_from,grect.x,grect.y,color_to); 
				 
				break; 
			} 
			 
			case gl.ALI.BOTTOM: 
			{ 
				p = new GradientPaint(grect.x,grect.height,color_from,grect.x,grect.y,color_to); 
				 
				break; 
			} 
		 
			default: 
				p = new GradientPaint(grect.x,grect.y,color_from,grect.x,grect.y,color_to); 
				 
			 
		} 
		 
		    g2.setPaint(p); 
			    
		    g2.fillRect(rect.x,rect.y,rect.width,rect.height); 
		 
		    g2.setPaint(old_paint); 
		 
	} 
	 
	public static Paint getPaint(Graphics2D g2,Rectangle rect,Color color_from ,Color color_to,int gradient_type) 
	{ 
	 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

		Paint p = null; 
		 
		switch(gradient_type) 
		{ 
			case gl.ALI.LEFT: 
			{ 
				p = new GradientPaint(rect.x,rect.y,color_from,rect.width,rect.y,color_to); 
				 
				break; 
			} 
		 
	 
			case gl.ALI.TOP: 
			{ 
				p = new GradientPaint(rect.x+rect.width,rect.y,color_from,rect.width,rect.height,color_to); 
				 
				break; 
			} 
	 
			case gl.ALI.RIGHT: 
			{ 
				 
				p = new GradientPaint(rect.width,rect.height,color_from,rect.x,rect.y,color_to); 
				 
				break; 
			} 
			 
			case gl.ALI.BOTTOM: 
			{ 
				p = new GradientPaint(rect.x,rect.height,color_from,rect.x,rect.y,color_to); 
				 
				break; 
			} 
		 
			default: 
				p = new GradientPaint(rect.x,rect.y,color_from,rect.x,rect.y,color_to); 
				 
			 
		} 
		 
			return p; 
	} 
	 
	 
	public static void drawByGradientType(Graphics2D g2,Rectangle rect,Color color_from ,Color color_to,int gradient_type) 
	{ 
	 			 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

		Rectangle gr = new Rectangle(0,0,0,0); 
		 
		switch(gradient_type) 
		{ 
			case gl.ALI.LEFT: 
			{ 
				gr.width = rect.width; 
				 
				break; 
			} 
			//Paint p = new GradientPaint(grect.x+grect.width,grect.y,color_from,grect.width,grect.height,color_to); 
	 
			case gl.ALI.TOP: 
			{ 
				gr.x = rect.x;//rect.width; 
				 
				gr.y = rect.y; 
				 
				gr.width = rect.x;//width; 
				 
				gr.height = rect.height; 
				 
				break; 
			} 
			
			//Paint p = new GradientPaint(grect.x+grect.width,grect.y,color_from,grect.width,grect.height,color_to); 
			
	 
			case gl.ALI.RIGHT: 
			{ 
				gr.x = rect.width; 
				 
				gr.width = rect.x; 
				 
				break; 
			} 
			 
		} 
		 
		drawGradientEx(g2,gr,rect,color_from ,color_to); 
			 
	} 
	 
	 
	public static AlphaComposite makeComposite(float alpha) { 
		int type = AlphaComposite.SRC_OVER; 
		return (AlphaComposite.getInstance(type, alpha)); 
	} 

	public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
			int py, Color pcolor) { 


		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawString(pvalue, px, py); 

		g.setColor(old); 

	} 
	 
	public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
			int py, Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawString(pvalue, px, py); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
			int py, Color pcolor, Font font, float alpha) { 
		 
		Font old_font = g.getFont(); 

		g.setFont(font); 

		drawAlphaString(g, pvalue, px, py, pcolor, alpha); 

		g.setFont(old_font); 

	} 
	 
	public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
			int py, Color pcolor, Font font) { 
		 
		Font old_font = g.getFont(); 

		g.setFont(font); 

		drawAlphaString(g, pvalue, px, py, pcolor); 

		g.setFont(old_font); 

	} 

	public static void drawAlphaLine(Graphics2D g, Point start, Point end, Color pcolor, float alpha) { 

		drawAlphaLine(g,start.x,start.y,end.x,end.y,pcolor,alpha); 
		 
		 
	} 
		 
		 
	public static void drawAlphaLine(Graphics2D g,TGridLine line) { 

		drawAlphaLine(g,line.getStyle().getStroke(),line,line.getStyle().getColor(),line.getStyle().getAlpha()); 
	} 
	 
	public static void drawAlphaLine(Graphics2D g,TGridLine grid_line , Color pcolor, float alpha) { 

		drawAlphaLine(g,grid_line.getStart(),grid_line.getEnd(),pcolor,alpha); 
	} 

	public static void drawAlphaLine(Graphics2D g)
	{
		
	}

	public static void draw_alpha_line_v_fat(
			Graphics2D g,
			Rectangle grect,
			TGridLine l , 
			Color color_from,
			Color color_to,
			int type,
			int fat
			) { 
		
		Rectangle rect_v = new Rectangle(
				l.getStart().x,
				l.getStart().y,
				fat,
				l.getEnd().y - l.getStart().y
				);
		
		
		drawGradient(g,grect,rect_v,color_from,color_to,type);
		
	} 
	
	public static void draw_alpha_line_v_fat_ban(
			Graphics2D g,
			Rectangle grect,
			TGridLine l , 
			Color color_from,
			Color color_to,
			int type,
			int fat,
			List<Integer> bans
			) { 
		
		Rectangle rect_v = new Rectangle(
				l.getStart().x,
				l.getStart().y,
				fat,
				l.getEnd().y - l.getStart().y
				);
		
		bans.forEach(a->{
			
			//gl.sfn("---> Bans member...%d...line index...%d",a,l.getIndex());
			
		});
		
		if(!bans.contains(l.getIndex()))
		    drawGradient(g,grect,rect_v,color_from,color_to,type);
		
	} 
	

	public static void draw_alpha_line_h_fat(
			Graphics2D g,
			Rectangle grect,
			TGridLine l , 
			Color color_from,
			Color color_to,
			int type,
			int fat
			) { 
		
		Rectangle rect_h = new Rectangle(
				l.getStart().x,
				l.getStart().y,
				l.getEnd().x - l.getStart().x,
				fat // new param
				);
		
		drawGradient(g,grect,rect_h,color_from,color_to,type);
		
	}
	
	public static void draw_alpha_line_h_fat_ban(
			Graphics2D g,
			Rectangle grect,
			TGridLine l , 
			Color color_from,
			Color color_to,
			int type,
			int fat,
			List<Integer> bans
			) { 
		
		Rectangle rect_h = new Rectangle(
				l.getStart().x,
				l.getStart().y,
				l.getEnd().x - l.getStart().x,
				fat // new param
				);
		
		if(! bans.contains(l.getIndex()))
		drawGradient(g,grect,rect_h,color_from,color_to,type);
		
	} 
	
	
	public static void drawAlphaLine(Graphics2D g,TStroke stroke,TGridLine grid_line , Color pcolor, float alpha) { 

		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(TStroke.getStrokeInstance(stroke.getDimension())); 
		 
		drawAlphaLine(g,grid_line.getStart(),grid_line.getEnd(),pcolor,alpha); 
		 
		g.setStroke(prev_stroke); 
		 
		 
	} 

	 
	 
	 
	public static void drawAlphaLine(Graphics2D g,Stroke stroke ,TGridLine grid_line , Color pcolor, float alpha) { 

		 
		Stroke p_stroke = g.getStroke(); 
		 
		g.setStroke(stroke); 
		 
		drawAlphaLine(g,grid_line.getStart(),grid_line.getEnd(),pcolor,alpha); 
		 
		g.setStroke(p_stroke); 
		 
	} 
	 
	public static void drawAlphaLine(Graphics2D g, int px, int py, int px1, 
			int py1, Color pcolor, float alpha) { 

		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawLine(px, py, px1, py1); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	 
	public static void drawAlphaPath(Graphics2D g,GeneralPath path,Stroke stroke,Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 
		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(stroke); 

		g.setColor(pcolor); 

		g.draw(path); 
		 
		g.setStroke(prev_stroke); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void drawAlphaPath(Graphics2D g,GeneralPath path,TLineStyle style) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(style.getAlpha())); 

		Color old = g.getColor(); 
		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(TStroke.getStrokeInstance(style.getStroke().getDimension())); 

		g.setColor(style.getColor()); 

		g.draw(path); 
		 
		g.setStroke(prev_stroke); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	 
	public static void drawAlphaPath(Graphics2D g,GeneralPath path,BasicStroke stroke,Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 
		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(stroke); 

		g.setColor(pcolor); 

		g.draw(path); 
		 
		g.setStroke(prev_stroke); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void drawAlphaCircle(Graphics2D g, int px, int py, int width, 
			int height, Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawOval(px, py, width,height); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	public static void fillAlphaCircle(Graphics2D g, int px, int py, int width, 
			int height, Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillOval(px, py, width,height); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void drawAlphaCircle(Graphics2D g,Rectangle rect, Color pcolor, float alpha) 
	{ 
		drawAlphaCircle(g,rect.x,rect.y,rect.width,rect.height,pcolor,alpha); 
	} 
	 
	public static void fillAlphaCircle(Graphics2D g,Rectangle rect, Color pcolor, float alpha) 
	{ 
		fillAlphaCircle(g,rect.x,rect.y,rect.width,rect.height,pcolor,alpha); 
	} 
	 
	 
	public static void drawAlphaRect(Graphics2D g,Rectangle rect, Color pcolor, float alpha) 
	{ 
		drawAlphaRect(g,rect.x,rect.y,rect.width,rect.height,pcolor,alpha); 
	} 
	 
	public static void drawAlphaRect(Graphics2D g,Rectangle rect, Color pcolor) 
	{ 
		drawAlphaRect(g,rect.x,rect.y,rect.width,rect.height,pcolor); 
	} 
	 
	public static void drawAlphaRectB(Graphics2D g,Rectangle rect, Color pcolor,boolean condition) 
	{ 
		if(condition) 
		drawAlphaRect(g,rect.x,rect.y,rect.width,rect.height,pcolor); 
	} 
	 
	public static void drawAlphaRectB(Graphics2D g,Rectangle rect,TLineStyle style,boolean condition) 
	{ 
		 
		 	Composite originalComposite = g.getComposite(); 

			g.setComposite(makeComposite(style.getAlpha())); 

			Color old = g.getColor(); 
			 
			Stroke prev_stroke = g.getStroke(); 
			 
			g.setStroke(TStroke.getStrokeInstance(style.getStroke().getDimension())); 

			g.setColor(style.getColor()); 

			if(condition) 
			drawAlphaRect(g,rect.x,rect.y,rect.width,rect.height,style.getColor()); 
				 
			g.setStroke(prev_stroke); 
			 
			g.setColor(old); 

			g.setComposite(originalComposite); 
		 
		 
	} 
	 
	 
	/* 
	 Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(style.getAlpha())); 

		Color old = g.getColor(); 
		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(TStroke.getStrokeInstance(style.getStroke().getDimension())); 

		g.setColor(style.getColor()); 

		g.draw(path); 
		 
		g.setStroke(prev_stroke); 
		 
		g.setColor(old); 

		g.setComposite(originalComposite); 

	 */ 
	 
	public static void drawAlphaRect(Graphics2D g, int px, int py, int px1, 
			int py1, Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawRect(px, py, px1, py1); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	
	public static void draw_alpha_rect(Graphics2D g, Rectangle rect, Color pcolor, float alpha) { 

		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawRect(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	
	public static void draw_alpha_rect(Graphics2D g, Rectangle rect, Color pcolor) { 


		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawRect(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

	} 
	
	public static void fill_alpha_rect(Graphics2D g, Rectangle rect, Color pcolor) { 


		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillRect(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

	} 
	
	 
	public static void drawAlphaRect(Graphics2D g, int px, int py, int px1, 
			int py1, Color pcolor) { 

		 
		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawRect(px, py, px1, py1); 

		g.setColor(old); 

	} 

	public static void drawAlphaRoundRect(Graphics2D g, TRect rect, Color pcolor, float alpha) 
	{ 
		drawAlphaRoundRect(g, rect.getBoundsRect(),rect.getArc_width(),rect.getArc_height(),pcolor,alpha); 
	} 

	 
	public static void drawAlphaRoundRect(Graphics2D g, Rectangle rect,int paw, int pah, Color pcolor, float alpha) 
	{ 
		drawAlphaRoundRect(g,rect.x,rect.y,rect.width,rect.height,paw,pah,pcolor,alpha); 
	} 

	 
	 
	public static void drawAlphaRoundRect(Graphics2D g, int px, int py, 
			int px1, int py1, int paw, int pah, Color pcolor, float alpha) { 

	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.drawRoundRect(px, py, px1-1, py1-1, paw, pah); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	public static void fillAlphaRoundRect(Graphics2D g, TRect rect, Color pcolor, float alpha) 
	{ 
		fillAlphaRoundRect(g, rect.getBoundsRect(),rect.getArc_width(),rect.getArc_height(),pcolor,alpha); 
	} 

	public static void fillAlphaRoundRect(Graphics2D g, Rectangle rect, Color pcolor, float alpha) 
	{ 
		fillAlphaRoundRect( 
				g,rect.x,rect.y,rect.width,rect.height,rect.height/3,rect.height/3,pcolor,alpha 
				); 
 
	} 


	public static void fillRoundRect(Graphics2D g, Rectangle rect, Color pcolor) 
	{ 
		fillAlphaRoundRect( 
				g,rect.x,rect.y,rect.width,rect.height,rect.height/3,rect.height/3,pcolor,1.0f 
				); 
 
	} 

	public static void fillAlphaRoundRect(Graphics2D g, Rectangle rect,int paw, int pah, Color pcolor, float alpha) { 

		fillAlphaRoundRect( 
				g,rect.x,rect.y,rect.width,rect.height,paw,pah,	pcolor,alpha 
				); 

	} 
	 
	public static void fillAlphaRoundRect(Graphics2D g, int px, int py, 
			int px1, int py1, int paw, int pah, Color pcolor, float alpha) { 

		g.setRenderingHint(
				/*RenderingHints.KEY_ANTIALIASING*/
				/*RenderingHints.VALUE_ANTIALIAS_ON*/
				 
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
				); 
		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillRoundRect(px, py, px1, py1, paw, pah); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void fill3DRect(Graphics2D g, TRectangle r) 
	{ 
		Color old = g.getColor(); 

		g.setColor(r.getBackground()); 
		 
		g.fill3DRect(r.x,r.y,r.width,r.height,r.isRaised()); 
		 
		g.setColor(old); 
	} 
	 
	public static void fillAlphaRect(Graphics2D g,TGridRect gr) 
	{ 
		fillAlphaRect(g,gr.getBounds(),gr.getLine()); 
	} 
	 
	//drawGradient(Graphics2D g2,Rectangle grect,Rectangle rect,Color color_from ,Color color_to,int gradient_type) 
	 
	public static void fillAlphaRect(Graphics2D g,Rectangle rect,TGridLine line) { 

		 
		Stroke prev_stroke = g.getStroke(); 
		 
		g.setStroke(TStroke.getStrokeInstance(line.getStyle().getStroke().getDimension())); 
		 
		fillAlphaRect(g,rect,line.getStyle().getColor(),line.getStyle().getAlpha()); 
		 
		g.setStroke(prev_stroke); 
		 
	} 
	 
	public static void fillAlphaRect(Graphics2D g, Rectangle rect,  Color pcolor, float alpha) { 

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillRect(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void fillAlphaOval(Graphics2D g, Rectangle rect,  Color pcolor, float alpha) { 

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillOval(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 
	 
	public static void fillAlphaRect(Graphics2D g, Rectangle rect,  Color pcolor) { 

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		 
		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillRect(rect.x,rect.y,rect.width,rect.height); 

		g.setColor(old); 

	} 
	 
	public static void fillAlphaRect(Graphics2D g, int px, int py, 
			int px1, int py1,  Color pcolor, float alpha) { 

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		 
		Composite originalComposite = g.getComposite(); 

		g.setComposite(makeComposite(alpha)); 

		Color old = g.getColor(); 

		g.setColor(pcolor); 

		g.fillRect(px, py, px1, py1); 

		g.setColor(old); 

		g.setComposite(originalComposite); 

	} 

	public static void drawRoundRectShadow(Graphics2D g2 , Rectangle rect , int rad , Color color, float alpha,int solid) 
	{ 

		for(int i=0;i<solid;i++) 
		{ 
			GU.drawAlphaRoundRect(g2,i,i,rect.width-(i*2),rect.height-(i*2), rad,rad,color,0.1f*i) ; 
			 
		} 
		 
	} 
	 
	 
	public static void drawRect(Graphics g, Rectangle rect) { 
		g.drawRect(rect.x, rect.y, rect.width, rect.height); 
	} 

	public static void fillRect(Graphics g, Rectangle rect) { 
		g.fillRect(rect.x, rect.y, rect.width, rect.height); 
	} 
	 
	public static void drawSphere(Graphics2D g2, int p_width, int p_height, 
			Color color,boolean select) { 

		int x = 0;// r.x; 

		int y = 0;// r.y; 

		int w = p_width; 

		int h = p_height; 

		boolean pshadow = false; 

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 

		// Retains the previous state 
		Paint oldPaint = g2.getPaint(); 

		g2.setColor(color); 
			 
		// Main picture 
		g2.fillOval(x, y, w, h); 

		// Adds shadows at the top 
		Paint p; 
		 
		p = new GradientPaint(x, y, new Color(0.0f, 0.0f, 0.0f, 0.4f), w, h, 
				new Color(0.0f, 0.0f, 0.0f, 0.0f)); 

		g2.setPaint(p); 

		g2.fillOval(x,y,w,h); 
		 

		// Adds highlights at the bottom 
		 
		p = new GradientPaint(x, y, new Color(1.0f, 1.0f, 1.0f, 0.0f), w, h, 
				new Color(1.0f, 1.0f, 1.0f, 0.8f)); 
		 
		g2.setPaint(p); 

		 g2.fillOval(x,y, w - 1, h - 1); 

		// Creates dark edges for 3D effect 
		 
		 
			p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
					+ (h / 2.0)), x + (w / 2.0f), new float[] { 0.0f, 0.8f }, 
			new Color[] { 
			CU.getAlphaColor(color, 20), 
			CU.getAlphaColor(Color.black, 200)}); 
		 
		g2.setPaint(p); 

		// if(!pshadow) 
		// Main inner transparent mask 
		 
		g2.fillOval(x, y, w, h); 

		// Adds oval inner highlight at the bottom + 
		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
				+ (h * 1.7)), x + (w / 2.3f), new Point2D.Double(x + (w / 2.0), 
				y + (h * 1.75) + 6), new float[] { 0.0f, 0.9f }, 
				new Color[] { new Color(255, 255, 255, 255), 
						new Color(255, 255, 255, 0) /* 
													 * new Color(64, 142, 203, 
													 * 255),new Color(64, 142, 
													 * 203, 0) 
													 */}, 
				RadialGradientPaint.CycleMethod.NO_CYCLE, 
				RadialGradientPaint.ColorSpaceType.SRGB, 

				AffineTransform.getScaleInstance(1.0, 0.5f)); 

		g2.setPaint(p); 

		if(select) 
		g2.fillOval(x, y, w, h); 

		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2), y 
				+ (h / 2)), x + (w / ((pshadow) ? 3.0f : 5.0f)), 
				new Point2D.Double(x + (w / 2.0), y + (h * 1.75)), 
				new float[] { 0.0f, 0.9f }, new Color[] { 
						new Color(255, 255, 0, 128), 
						new Color(255, 255, 0, 0) }, 
				RadialGradientPaint.CycleMethod.NO_CYCLE, 
				RadialGradientPaint.ColorSpaceType.SRGB, 
				AffineTransform.getScaleInstance(1.0, 1.0f)); 

		g2.setPaint(p); 

	    if(select) 
		g2.fillOval(x, y, w, h); 

		// Restores the previous state 
		g2.setPaint(oldPaint); 
	} 

	 
	 
	public static void drawSphere(Graphics2D g2, int p_width, int p_height, 
			Color color) { 

		int x = 0;// r.x; 

		int y = 0;// r.y; 

		int w = p_width; 

		int h = p_height; 

		boolean pshadow = false; 

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 

		// Retains the previous state 
		Paint oldPaint = g2.getPaint(); 

		g2.setColor(color); 

		// if(!pshadow) 
		// Main picture 
		g2.fillOval(x, y, w, h); 

		// Adds shadows at the top 
		Paint p; 
		p = new GradientPaint(x, y, new Color(0.0f, 0.0f, 0.0f, 0.4f), w, h, 
				new Color(0.0f, 0.0f, 0.0f, 0.0f)); 

		g2.setPaint(p); 

		// g2.fillOval(x,y,w,h); 

		// Adds highlights at the bottom 
		 
		p = new GradientPaint(x, y, new Color(1.0f, 1.0f, 1.0f, 0.0f), w, h, 
				new Color(1.0f, 1.0f, 1.0f, 0.4f)); 

		g2.setPaint(p); 

		// g2.fillOval(x,y, w - 1, h - 1); 

		// Creates dark edges for 3D effect 
		/* 
		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
				+ (h / 2.0)), x + (w / 2.0f), new float[] { 0.0f, 0.8f }, 
				new Color[] { new Color(0, 0, 0, 0.2f), 
						new Color(0.0f, 0.0f, 0.0f, 0.9f) }); 
*/ 

		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
				+ (h / 2.0)), x + (w / 2.0f), new float[] { 0.0f, 0.8f }, 
				//new Color[] { new Color(1, 0, 0, 0.2f), 
				//		new Color(1, 0.0f, 0.0f, 0.9f) }); 
		 
		new Color[] { 
		CU.getAlphaColor(color, 20), 
		CU.getAlphaColor(Color.black, 120)}); 
		 
		g2.setPaint(p); 

		// if(!pshadow) 
		// Main inner transparent mask 
		g2.fillOval(x, y, w, h); 

		// Adds oval inner highlight at the bottom + 
		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
				+ (h * 1.7)), x + (w / 2.3f), new Point2D.Double(x + (w / 2.0), 
				y + (h * 1.75) + 6), new float[] { 0.0f, 0.9f }, 
				new Color[] { new Color(255, 255, 255, 255), 
						new Color(255, 255, 255, 0) /* 
													 * new Color(64, 142, 203, 
													 * 255),new Color(64, 142, 
													 * 203, 0) 
													 */}, 
				RadialGradientPaint.CycleMethod.NO_CYCLE, 
				RadialGradientPaint.ColorSpaceType.SRGB, 

				AffineTransform.getScaleInstance(1.0, 0.5f)); 

		g2.setPaint(p); 

		if (!pshadow) 
			g2.fillOval(x, y, w, h); 

		p = new RadialGradientPaint(new Point2D.Double(x + (w / 2.0), y 
				+ (h / 4.0)), x + (w / ((pshadow) ? 2.0f : 5.0f)), 
				new Point2D.Double(x + (w / 4.0), y + (h * 1.75) + 6), 
				new float[] { 0.0f, 0.9f }, new Color[] { 
						new Color(255, 255, 255, 64), 
						new Color(255, 255, 255, 0) }, 
				RadialGradientPaint.CycleMethod.NO_CYCLE, 
				RadialGradientPaint.ColorSpaceType.SRGB, 
				AffineTransform.getScaleInstance(1.0, 0.5f)); 

		g2.setPaint(p); 

		// if(!pshadow) 
		// g2.fillOval(x, y, w, h); 

		// Restores the previous state 
		g2.setPaint(oldPaint); 
	} 

	public static Dimension getDisplayDimension() { 

		return Toolkit.getDefaultToolkit().getScreenSize(); 
	} 
	 
	public void drawRotateWithGlyph(Graphics2D g2d,Color color ,Rectangle rect,double angle,String s) 
	{ 
	 
		Color old_color = g2d.getColor(); 

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 

		Font font = new Font("Courier", Font.PLAIN, 12); 

		g2d.translate(10, 10); 

		FontRenderContext frc = g2d.getFontRenderContext(); 

		GlyphVector gv = font.createGlyphVector(frc, s); 

		int length = gv.getNumGlyphs(); 

		// Rectangle2D d_rect = new 
		// Rectangle2D.Double(rect.x,rect.y,rect.width,rect.height); 

		g2d.setColor(color); 

		// g2d.draw(d_rect); 

		for (int i = 0; i < length; i++) { 

			Point2D p = gv.getGlyphPosition(i); 

			AffineTransform at = AffineTransform.getTranslateInstance(p.getX(), 
					p.getY()); 

			// at.rotate((double) i / (double) (length - 1) * Math.PI / 2); 

			at.rotate(Math.toRadians(angle)); 

			Shape glyph = gv.getGlyphOutline(i); 

			Shape transformedGlyph = at.createTransformedShape(glyph); 

			g2d.fill(transformedGlyph); 

		} 

		g2d.setColor(old_color); 
	}	 
	 
	 
	public static  BufferedImage toCompatibleImage(BufferedImage image) 
	{ 
	        // obtain the current system graphical settings 
	        GraphicsConfiguration gfx_config = GraphicsEnvironment. 
	                getLocalGraphicsEnvironment().getDefaultScreenDevice(). 
	                getDefaultConfiguration(); 

	        if (image.getColorModel().equals(gfx_config.getColorModel())) 
	                return image; 

	        // image is not optimized, so create a new image that is 
	        BufferedImage new_image = gfx_config.createCompatibleImage( 
	                        image.getWidth(), image.getHeight(), image.getTransparency()); 

	        // get the graphics context of the new image to draw the old image on 
	        Graphics2D g2d = (Graphics2D) new_image.getGraphics(); 

	        // actually draw the image and dispose of context no longer needed 
	        g2d.drawImage(image, 0, 0, null); 
	 
	        g2d.dispose(); 

	        // return the new optimized image 
	        return new_image; 
	} 
	 
	public static void draw_push_back(Graphics g,Rectangle rect,Color color,float alpha) 
	{ 
		Graphics2D g2 = (Graphics2D)g; 
		 
	    Rectangle wr = Ru.norm4g(rect); 
	    	 
	    	AreaManager am = new AreaManager( 
	    			new Dimension(wr.width,wr.height), 
	    			new Dimension(10,10), 
	    			new Dimension(1,1), 
	    			new Dimension(0,0) 
	    			); 
	    	 
	    	List<TGridLine> h_lines = am.getGridLinesH();	 
	    	 
	    	List<TGridLine> v_lines = am.getGridLinesV(); 
	    	 
	    	Stroke prev_stroke = g2.getStroke(); 
	    		    	 
	    	g2.setStroke(new BasicStroke(1.0f)); 
	 
	    	h_lines.forEach(a->{ 
	    		 
	    		GU.drawAlphaLine(g2,a,color,alpha); 
	    		 
	    		 v_lines.forEach(b->{ 
	    			 
	    			 GU.drawAlphaLine(g2,b,color,alpha); 
	    			 
	    		 }); 
	    	}); 
	    	 
	    	g2.setStroke(prev_stroke); 
	    	 
	    	 
	} 
	 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
