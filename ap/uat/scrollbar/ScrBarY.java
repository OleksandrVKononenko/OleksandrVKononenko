 
 
package ap.uat.scrollbar; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.LayoutManager; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.prop.SBounds; 
import ap.shape.Ru; 
import ap.uat.AtOm; 
import ap.utils.GU; 

public class ScrBarY extends AtOm { 
	 
	private int y_max; 
	 
	private List<AtOm> items_to_move = new ArrayList<AtOm>(); 
	 
	public List<AtOm> getItems_to_move() { 
		return items_to_move; 
	} 

	public void setItems_to_move(List<AtOm> items_to_move) { 
		this.items_to_move = items_to_move; 
	} 
	 
	 
	public int getY_max() { 
		return y_max; 
	} 

	public void setY_max(int y_max) { 
		this.y_max = y_max; 
	} 

	public ScrBarY() { 
		 
	} 

	public ScrBarY(Rectangle rect) { 
		super(rect); 
		 
	} 

	public ScrBarY(LayoutManager layout) { 
		super(layout); 
		 
	} 

	public ScrBarY(boolean isDoubleBuffered) { 
		super(isDoubleBuffered); 
		 
	} 

	public ScrBarY(LayoutManager layout, boolean isDoubleBuffered) { 
		super(layout, isDoubleBuffered); 
		 
	} 
	 
	public static ScrBarY get_instance(Rectangle rect) 
	{ 
		 ScrBarY 	atom = new  ScrBarY(rect); 
		 
				atom.add_attrs(); 
		 
				atom.getIdo().setName(gl.sf("%s_%03d",atom.getClass().getSimpleName(),atom.getIdo().getId())); 
					 
				return 	atom; 
	} 
	 
	public static ScrBarY get_instance(Rectangle rect,int align) 
	{ 
		 ScrBarY 	atom = new  ScrBarY(rect); 
		 
				atom.add_attrs(); 
		 
				atom.getIdo().setName(gl.sf("%s_%03d",atom.getClass().getSimpleName(),atom.getIdo().getId())); 
					 
				atom.getDecoro().set_align(align); 
				 
				return 	atom; 
	} 
	 
	public void add_attrs() 
	{ 
				this.setBackground(UIManager.getColor("Panel.background")); 
		 
				this.getDecoro().setTextColor(Color.black); 
				 
				this.getStio().set_deny_drag(false); 
				 
				this.getStio().set_move_inside(true); 
				 
				this.getStio().set_skip_moved(true); 
				 
				this.getStio().set_deny_select(false); 
	 
	} 
	 
	 
	@Override 
	public void move_byself_dx_dy(int dx,int dy) 
	{ 
		if (this.getIdo().getOwner().is_desk_top()) 
		{ 
			super.move_byself_dx_dy(dx, dy); 
			 
			return ; 
		} 
		 
		this.getStio().set_skip_resizing(false); 
		 
		int m_max_x = this.getLocation().x + dx ; 
		 
		int m_max_y = this.getLocation().y + dy ; 
		 
		if(this.getStio().is_move_inside()) 
		{ 
				 
			if( 
					m_max_y + this.getDecoro().getBounds().height > (this.getDecoro().getParents_bounds().height - 1) || 
					m_max_y < gl.E_EMPTY 
		 
			  ) 
			{ 
				m_max_y = this.getLocation().y; 
			} 
			else 
			{ 
					this.setLocation(m_max_x,m_max_y); 
					 
					this.getDecoro().setBounds(this.getBounds()); 
					 
					this.getItems_to_move().forEach(a->{ 
								a.move_dx_dy(dx,-dy); 
							}); 
				 
			} 
		} 
				 
		 
				 
	} 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		super.mouseEntered(e); 
		 
		if(this.getIdo().getOwner() == null && this.getIdo().getOwner().is_desk_top()) 
			return; 
		 
		this.getItems_to_move().clear(); 
		 
		this.getItems_to_move().addAll(this.getIdo().getOwner().get_moved_items()); 
	 
		 
	} 
	 
	@Override 
	public void mouseReleased(MouseEvent e) { 
		 
		super.mouseReleased(e); 
		 
		this.getStio().set_skip_resizing(false); 
		
		this.getIdo().getOwner().re_validate(); 
		
		this.repaint();
	} 
	 
	@Override 
	public void paintComponent(Graphics g) { 

		if(!this.getStio().is_visible()) 
			return; 
	 
		paintScrollBar(g); 
		 
	} 
	 
	public void paintScrollBar(Graphics g) { 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(new BasicStroke(1.0f)); 
		 
		Rectangle span = Ru.get_spanned_rect(this.getDecoro().getBounds(),new Insets(0,0,0,0)); 
		 
		float m_alpha = .20f; 
		 
		if(this.getIdo().getOwner().is_desk_top()) 
		GU.fillAlphaRoundRect(g2,Ru.norm4g(span),span.height/2,span.height/2,Color.white,m_alpha); 
		else 
		GU.fillAlphaRoundRect(g2,Ru.norm4g(span),span.width/2,span.width/2,Color.white,m_alpha); 
				 
		Rectangle v_rect 	= null; 
		 
		Rectangle v_rect_l = null; 
		 
		Rectangle v_rect_r = null; 
		 
		if(this.getIdo().getOwner().is_desk_top()) 
		{ 
			v_rect = new Rectangle((span.width / 2),(span.height/4),2,span.height - (span.height/2)); 
		 
			v_rect_l = new Rectangle((span.width / 2)-4,(span.height/4),2,span.height - (span.height/2)); 
		 
			v_rect_r = new Rectangle((span.width / 2)+4,(span.height/4),2,span.height - (span.height/2)); 
		}	 
		else 
		{ 
			 
			v_rect_l = new Rectangle((span.width / 4),(span.height/2) - 4 ,span.width - (span.width/2),2); 
			 
			v_rect = new Rectangle((span.width / 4),(span.height/2) ,span.width - (span.width/2),2); 
			 
			v_rect_r = new Rectangle((span.width / 4),(span.height/2) + 4 ,span.width - (span.width/2),2); 
			 
			 
		} 
		 
		 
		GU.fillAlphaRect(g2,v_rect,Color.white,m_alpha); 
		 
		GU.fillAlphaRect(g2,v_rect_l,Color.white,m_alpha); 
		 
		GU.fillAlphaRect(g2,v_rect_r,Color.white,m_alpha); 
		 
		if(this.getIdo().getOwner().is_desk_top()) 
		GU.drawAlphaRoundRect(g2,Ru.norm4g(span),span.height/2,span.height/2,Color.white,m_alpha*2); 
		else 
		GU.drawAlphaRoundRect(g2,Ru.norm4g(span),span.width/2,span.width/2,Color.white,m_alpha*2); 
				 
					 
		g2.setStroke(p_stroke); 

	} 
	 
	/* 
	public void paintScrollBar(Graphics g) { 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Stroke p_stroke = g2.getStroke(); 
		 
		g2.setStroke(new BasicStroke(2.0f)); 
		 
		Rectangle span = Ru.get_spanned_rect(this.getDecoro().getBounds(),new Insets(0,0,0,0)); 
		 
		if(this.getIdo().getOwner().is_desk_top()) 
		GraphicsUtil.drawAlphaRoundRect(g2,Ru.norm4g(span),span.height/2,span.height/2,Color.white,.45f); 
		else 
		GraphicsUtil.drawAlphaRoundRect(g2,Ru.norm4g(span),span.width/2,span.width/2,Color.white,.45f); 
			 
		 
		if(this.getIdo().getOwner().is_desk_top()) 
		GraphicsUtil.fillAlphaRoundRect(g2,span,span.height/2,span.height/2,UIManager.getColor("Panel.background"),0.45f); 
		else 
		GraphicsUtil.fillAlphaRoundRect(g2,span,span.width/2,span.width/2,UIManager.getColor("Panel.background"),0.45f); 
		 
		g2.setStroke(p_stroke); 

	} 
	 
	*/ 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	public static void main(String[] args) { 
		 

	} 

} 
