package ap.uat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ap.global.*;
import ap.state.Fl;
import ap.utils.Biu;
import ap.utils.CU;
import ap.utils.DialogUtil;
import ap.utils.GU;


@SuppressWarnings("serial")
public class PanelPoint extends AtOm {
	
	
	private int type = gl.ALI.NONE;
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PanelPoint() {
		
	}

	public PanelPoint(Rectangle rect) {
		super(rect);
		
	}

	public PanelPoint(LayoutManager layout) {
		super(layout);
		
	}

	public PanelPoint(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public PanelPoint(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}
	
	@Override
	public void move_byself_dx_dy(int dx,int dy) 
	{
		super.move_byself_dx_dy(dx, dy);
		
		AtOm owner = (AtOm)this.getIdo().getOwner();
		
		update_self(owner);
		
		//owner.repaint();
		
		ApplicationA.getDio().get_desk_top().repaint(owner.getBounds());
	}
	
	public void update_self(AtOm owner)
	{	
		if(owner != null && owner instanceof PanelSmile)
		{
				
			if(this.getType() == gl.POINT.START)
			{
				((PanelSmile)owner).getPoints().get("start").setLocation(this.getLocation());
				
				((PanelSmile)owner).setColor_from(this.getBackground());
			}
			else if(this.getType() == gl.POINT.END)
			{
				((PanelSmile)owner).getPoints().get("end").setLocation(this.getLocation());
				
				((PanelSmile)owner).setColor_to(this.getBackground());
			}
			else if(this.getType() == gl.POINT.CTRL1)
				((PanelSmile)owner).getPoints().get("ctrl1").setLocation(this.getLocation());
			else if(this.getType() == gl.POINT.CTRL2)
				((PanelSmile)owner).getPoints().get("ctrl2").setLocation(this.getLocation());

			

			if(((PanelSmile)owner).getType() == gl.FORM.QUAD)
			{
				((PanelSmile)owner).getQuad().setCurve(
						((PanelSmile)owner).getPoints().get("start"),
						((PanelSmile)owner).getPoints().get("ctrl1"),
						((PanelSmile)owner).getPoints().get("end")); 
				
				
			} else if(((PanelSmile)owner).getType() == gl.FORM.CUBIC)
			{
				((PanelSmile)owner).getCubic().setCurve(
						((PanelSmile)owner).getPoints().get("start"),
						((PanelSmile)owner).getPoints().get("ctrl1"),
						((PanelSmile)owner).getPoints().get("ctrl2"),
						((PanelSmile)owner).getPoints().get("end")); 
				
				
			} 
			
		}
		
	}
	
	@Override
	public void paintBody(Graphics g)
	{
		Rectangle wr = new Rectangle(0,0,this.getBounds().width-1,this.getBounds().height-1);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setStroke(gl.dashed_stroke_thin); 
		
		GU.drawAlphaRect(g2, wr,CU.getAlphaColor(Color.white,128));
	}

	public static PanelPoint get_instance(Rectangle rect) 
	{ 
	 
		PanelPoint 	ps = new PanelPoint(rect); 
	 
					ps.getStio().set_deny_drag(false); 
				 
					ps.getStio().set_move_bottom_right(true); 
				 
		return 		ps; 
	 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return gl.sf("%s %s",
				super.toString(),
				gl.getAlignTypeTextByInt(this.getType())
						);
	}
	
	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		super.mouseClicked(e);
		 
		if (SwingUtilities.isRightMouseButton(e)) { 
			
			this.setBackground(DialogUtil.get_color(this.getBackground()));
		} 

		if (SwingUtilities.isLeftMouseButton(e)) { 
			 
		} 
		
	}
	
	public static PanelPoint get_instance(Rectangle rect,int align) 
	{ 
	 
		PanelPoint 	ps = PanelPoint.get_instance(rect); 
	 
					ps.getDecoro().set_align(align); 
				 
		return 		ps; 
	 
	} 
	
	public static PanelPoint get_instance(Rectangle rect,int align,int type) 
	{ 
	 
		PanelPoint 	ps = get_instance(rect,align); 
	 
					ps.setType(type);
					
		return 		ps; 
	 
	} 

}
