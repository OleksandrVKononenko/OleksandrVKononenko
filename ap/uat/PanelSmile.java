package ap.uat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import ap.global.gl;
import ap.shape.Ru;
import ap.swing.IAlign;
import ap.thread.Teurg;
import ap.utils.CU;
import ap.utils.GU;
import ap.utils.PointUtil;

@SuppressWarnings("serial")
public class PanelSmile extends AtOm {
	
	
	private Color color_from;
	
	private Color color_to;
	
	private int type = gl.FORM.DEFAULT;
	
	private Map<String,Point> 	points 	= new HashMap<String,Point>();
	
	private CubicCurve2D.Double cubic 	= new CubicCurve2D.Double(); 
	
	private QuadCurve2D.Double 	quad 	= new QuadCurve2D.Double(); 
	

	
	
	public Color getColor_from() {
		return color_from;
	}

	public void setColor_from(Color color_from) {
		this.color_from = color_from;
	}

	public Color getColor_to() {
		return color_to;
	}

	public void setColor_to(Color color_to) {
		this.color_to = color_to;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public CubicCurve2D.Double getCubic() {
		return cubic;
	}

	public void setCubic(CubicCurve2D.Double cubic) {
		this.cubic = cubic;
	}

	public QuadCurve2D.Double getQuad() {
		return quad;
	}

	public void setQuad(QuadCurve2D.Double quad) {
		this.quad = quad;
	}

	public Map<String, Point> getPoints() {
		return points;
	}

	public void setPoints(Map<String, Point> points) {
		this.points = points;
	}

	public PanelSmile() {
		
	}

	public PanelSmile(Rectangle rect) {
		super(rect);
		
		init();
	}
	
	public void init()
	{
		
		this.getPoints().put("start",new Point(-1,-1));
		
		this.getPoints().put("end",new Point(-1,-1));
		
		this.getPoints().put("ctrl1",new Point(-1,-1));
		
		this.getPoints().put("ctrl2",new Point(-1,-1));
		
	
		this.setGrok(new Teurg(25,this));
		
		this.setColor_from(CU.getAlphaColor(this.getBackground(),255));
		
		this.setColor_to(CU.getAlphaColor(Color.WHITE,0));
		 		
	}

	public PanelSmile(LayoutManager layout) {
		super(layout);
		
	}

	public PanelSmile(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public PanelSmile(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}
	@Override
	public void paintBody(Graphics g)
	{
		
		Rectangle wr = new Rectangle(0,0,this.getBounds().width,this.getBounds().height);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(this.getType() == gl.FORM.GRADIENT)
		{
			paint_gradient(g2,wr);
			
		} else if(this.getType() == gl.FORM.QUAD)
		{
			ApplicationA.getDio().get_desk_top().repaint(wr);
			
			paint_quad(g2,wr);
			
		} else if(this.getType() == gl.FORM.CUBIC)
		{
			ApplicationA.getDio().get_desk_top().repaint(wr);
			
			paint_cubic_1(g2,wr);
		}
		 else
			//GraphicsUtil.fillAlphaRect(g2, wr,ColorUtil.getAlphaColor(this.getBackground(),this.getOpaque()));
			 GU.fillAlphaRect(g2, wr,this.getBackground());
	}
	
	public void paint_quad(Graphics2D g2,Rectangle wr)
	{
		Paint prev_paint = g2.getPaint(); 

		Color prev_color = g2.getColor(); 

		Stroke prev_stroke = g2.getStroke();
		
		
		g2.setStroke(new BasicStroke(5.0f));
		
		Point2D start = this.getPoints().get("start");
		
		Point2D ctrl = this.getPoints().get("ctrl1");
		
		Point2D end = this.getPoints().get("end");

		Rectangle paint_rect = new Rectangle((int)start.getX(), 
				(int) start.getY(), (int) (end.getX() - start.getX()), 
				(int) (ctrl.getY() - start.getY())); 

		Paint paint = GU.getPaint(g2, paint_rect, 
				//ColorUtil.getAlphaColor(Color.black, 125), 
				//ColorUtil.getAlphaColor(Color.green, 125),
				this.getColor_from(),
				this.getColor_to(),
				
				gl.ALI.LEFT); 

		g2.setPaint(paint); 

		g2.draw(this.getQuad()); 

		g2.setStroke(gl.dashed_stroke_thin); 

		GU.drawAlphaLine(g2, PointUtil.toPoint(ctrl), 
				PointUtil.toPoint(start), Color.white, 0.5f); 

		GU.drawAlphaLine(g2, PointUtil.toPoint(end), 
				PointUtil.toPoint(ctrl), Color.white, 0.5f); 

		g2.setPaint(prev_paint); 

		g2.setStroke(prev_stroke); 

		g2.setColor(prev_color); 

	}

	public void paint_cubic(Graphics2D g2,Rectangle wr)
	{
		
		Stroke prev_stroke = g2.getStroke(); 

		g2.setStroke(new BasicStroke(5.0f)); 

		Paint prev_paint = g2.getPaint(); 

		
		Point2D start = this.getPoints().get("start");
		
		Point2D ctrl1 = this.getPoints().get("ctrl1");
		
		Point2D ctrl2 = this.getPoints().get("ctrl2");
		
		Point2D end = this.getPoints().get("end");

		
		Rectangle paint_rect = new Rectangle((int)start.getX(), 
				(int) start.getY(), (int) (end.getX() - start.getX()), 
				(int) (ctrl1.getY() - start.getY())); 

		
		Paint paint = GU.getPaint(g2, paint_rect, 
					CU.getAlphaColor(Color.black, 125), 
					CU.getAlphaColor(Color.green, 125), gl.ALI.LEFT); 

			g2.setPaint(paint); 

			g2.draw(this.getCubic()); 

			g2.setPaint(prev_paint); 

			Color prev_color = g2.getColor(); 

			g2.setColor(Color.black); 

			g2.setStroke(gl.dashed_stroke_thin); 

			GU.drawAlphaLine(g2, PointUtil.toPoint(ctrl1), 
					PointUtil.toPoint(start), Color.white, 0.5f); 

			GU.drawAlphaLine(g2, PointUtil.toPoint(end), 
					PointUtil.toPoint(ctrl2), Color.white, 0.5f); 

			g2.setColor(prev_color); 

			g2.setStroke(prev_stroke); 

	}
	
	public void paint_cubic_1(Graphics2D g2,Rectangle wr)
	{
		
		Stroke prev_stroke = g2.getStroke(); 

		g2.setStroke(new BasicStroke(5.0f)); 

		Paint prev_paint = g2.getPaint(); 

		
		Point2D start = this.getPoints().get("start");
		
		Point2D ctrl1 = this.getPoints().get("ctrl1");
		
		Point2D ctrl2 = this.getPoints().get("ctrl2");
		
		Point2D end = this.getPoints().get("end");

		
		Rectangle paint_rect = new Rectangle((int)start.getX(), 
				(int) start.getY(), (int) (end.getX() - start.getX()), 
				(int) (ctrl1.getY() - start.getY())); 

		
		Paint paint = GU.getPaint(g2, paint_rect, 
					//ColorUtil.getAlphaColor(Color.black, 125), 
					//ColorUtil.getAlphaColor(Color.green, 125), 
					this.getColor_from(),
					this.getColor_to(),
				
					gl.ALI.LEFT); 

			g2.setPaint(paint); 

			//g2.draw(this.getCubic()); 
			
			 GeneralPath path = new GeneralPath();
 						path.append(this.getCubic(),true);
 			//path.append(bottom, true);
 			path.closePath();
 			
 			//g.setColor(fillClr);
 			g2.fill(path);
 			
 			g2.setColor(Color.black);
 			g2.draw(this.getCubic());
 			//g.draw(bottom);

			g2.setPaint(prev_paint); 

			Color prev_color = g2.getColor(); 

			g2.setColor(Color.black); 

			g2.setStroke(gl.dashed_stroke_thin); 

			GU.drawAlphaLine(g2, PointUtil.toPoint(ctrl1), 
					PointUtil.toPoint(start), Color.white, 0.5f); 

			GU.drawAlphaLine(g2, PointUtil.toPoint(end), 
					PointUtil.toPoint(ctrl2), Color.white, 0.5f); 

			g2.setColor(prev_color); 

			g2.setStroke(prev_stroke); 

	}
	
	public void paint_gradient(Graphics2D g2,Rectangle wr)
	{

		Rectangle top = wr;//Ru.getTopHalf(wr);
		
		 GU.fillAlphaRect(g2, wr,this.getBackground());
			
			GradientPaint blackToGray = new GradientPaint(
					this.getPoints().get("start").x,
					this.getPoints().get("start").y, 
					//ColorUtil.getAlphaColor(Color.white,128),
					this.getColor_from(),
					this.getPoints().get("end").x,
					this.getPoints().get("end").y, 
					//ColorUtil.getAlphaColor(Color.white,32)
					this.getColor_to()
					);
		
		 
	        g2.setPaint(blackToGray);
	    
	        g2.fill(new Rectangle2D.Double(top.x,top.y,top.width,top.height));
		
	}
	
	private void paintMatcher(Graphics2D g, Color fillClr, 
	        int leftX, int rightX, int upL, int upR, int doR, int doL) {
	    
		int topY = Math.min(upL, upR), bottomY = Math.max(doL, doR);
	   
	    // try rendering only curves in viewable area
	    
	    if (!g.hitClip(leftX, topY, rightX - leftX, bottomY - topY)) {
	        return;
	    }
	    
	    CubicCurve2D upper = new CubicCurve2D.Float(leftX, upL,
	            (rightX -leftX)*.3f, upL,
	            (rightX -leftX)*.7f, upR,
	            rightX, upR);
	    
	    CubicCurve2D bottom = new CubicCurve2D.Float(rightX, doR,
	            (rightX - leftX)*.7f, doR,
	            (rightX -leftX)*.3f, doL,
	            leftX, doL);
	    
	    GeneralPath path = new GeneralPath();
	    			path.append(upper, false);
	    			path.append(bottom, true);
	    			path.closePath();
	    			g.setColor(fillClr);
	    			g.fill(path);
	    			g.setColor(Color.black);
	    			g.draw(upper);
	    			g.draw(bottom);
	}
	
	@Override
	public boolean insert(AtOm  value) 
	{
		super.insert(value);
		
		this.update_type();
		
		return true;
	}
	
	public static PanelSmile get_instance(Rectangle rect) 
	{ 
	 
		PanelSmile 	ps = new PanelSmile(rect); 
	 
					ps.getStio().set_deny_drag(false); 
				 
					ps.getStio().set_move_bottom_right(true); 
				 
		return 		ps; 
	 
	} 
	 
	 
	public static PanelSmile get_instance(Rectangle rect,int align) 
	{ 
	 
		PanelSmile 	ps = PanelSmile.get_instance(rect); 
	 
					ps.getDecoro().set_align(align); 
					
					ps.getGrok().start();
				 
		return 		ps; 
	 
	} 
	

	public void update_type()
	{
		int m_count = this.get_components_by_name_start_with("PanelPoint").size();
				
		if (m_count == 2 )
		{
			
			this.setType(gl.FORM.GRADIENT);
			
		} else if (m_count == 3 )
		{
			
			this.setType(gl.FORM.QUAD);
			
		} else if (m_count == 4 )
		{
			this.setType(gl.FORM.CUBIC);
			
		} else
			this.setType(gl.FORM.DEFAULT);
		
		
		gl.sfn("Form type...%d...%s",this.getType(),gl.FORM.get_type(this.getType()));
		
		
	}
	
	public void update_cp()
	{
		this.get_components_by_name_start_with("PanelPoint").forEach(a->
		{
			((PanelPoint)a).update_self(this);
		});
	}
	@Override 
	public void componentResized(ComponentEvent e) 
	{
		super.componentResized(e);
		
		update_cp();
		
		//this.repaint();
	}
	
	/*
	@Override
	public void update_call_back(long counter)
	{

		if(this.getOpaque() >= 100)
			this.setOpaque(gl.E_EMPTY);
		else
		{
			int o = this.getOpaque();
			
			this.setOpaque(++o);
		}
	 	this.setCall_back_counter(counter);
	 	
		String	m_text = String.format("%s %s %06d",
				this.getIdo().getName(),
				IAlign.indexOf(this.getDecoro().get_align()),
				this.getCall_back_counter()
				); 
		 
		this.getDecoro().set_text(m_text); 
	 	
		//gl.sfn("Call back...%s...%06d...%s",this.getIdo().getName(),this.getCall_back_counter(),this.getClass().getSimpleName());
		
		this.setBackground(ColorUtil.getAlphaColor(this.getBackground(),this.getOpaque()));
		
		this.repaint();
	}
	*/
	
	 @Override
	 public void update_call_back(long counter)
	 {
		// super.update_call_back(counter);
		 
		// this.repaint();
		  	
		 //ApplicationA.getDio().get_desk_top().repaint(this.getBounds());
		 	
	 }
	
	
	public static void main(String[] args) {
		

	}

}
