 
 
package ap.uat.scrollbar; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.LayoutManager; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 

import ap.global.gl; 
import ap.prop.SBounds; 
import ap.prop.SPoint; 
import ap.shape.Ru; 
import ap.uat.AtOm; 
import ap.utils.GU; 
public class ScrBarX extends AtOm { 
	 
	private List<AtOm> items_to_move = new ArrayList<AtOm>(); 
	 
	private int x_max; 
	 
	 
	public List<AtOm> getItems_to_move() { 
		return items_to_move; 
	} 

	public void setItems_to_move(List<AtOm> items_to_move) { 
		this.items_to_move = items_to_move; 
	} 

	public int getX_max() { 
		return x_max; 
	} 

	public void setX_max(int x_max) { 
		this.x_max = x_max; 
	} 

	public ScrBarX() { 
		 
	} 

	public ScrBarX(Rectangle rect) { 
		super(rect); 
		 
	} 

	public ScrBarX(LayoutManager layout) { 
		super(layout); 
		 
	} 

	public ScrBarX(boolean isDoubleBuffered) { 
		super(isDoubleBuffered); 
		 
	} 

	public ScrBarX(LayoutManager layout, boolean isDoubleBuffered) { 
		super(layout, isDoubleBuffered); 
		 
	} 
	 
	public static ScrBarX get_instance(Rectangle rect) 
	{ 
		 ScrBarX 	atom = new  ScrBarX(rect); 
		 
				atom.add_attrs(); 
		 
				atom.getIdo().setName(gl.sf("%s_%03d",atom.getClass().getSimpleName(),atom.getIdo().getId())); 
					 
				return 	atom; 
	} 
	 
	public static ScrBarX get_instance(Rectangle rect,int align) 
	{ 
		 ScrBarX 	atom = new  ScrBarX(rect); 
		 
				atom.add_attrs(); 
		 
				atom.getIdo().setName(gl.sf("%s_%03d",atom.getClass().getSimpleName(),atom.getIdo().getId())); 
					 
				atom.getDecoro().set_align(align); 
				 
				return 	atom; 
	} 
	 
	public void add_attrs() 
	{ 
				this.setBackground(UIManager.getColor("Panel.background")); 
		 
				this.getDecoro().setTextColor(Color.black); 
				 
				//this.getStio().set_visible(true); 
		 
				 
				this.getStio().set_deny_drag(false); 
				 
				//this.getStio().set_deny_y(true); 
				 
				this.getStio().set_move_inside(true); 
				 
				this.getStio().set_skip_moved(true); 
				 
				this.getStio().set_skip_resizing(true); 
				 
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

		this.getStio().set_skip_resizing(true); 
		 
		 
		int m_max_x = this.getLocation().x + dx ; 
		 
		int m_max_y = this.getLocation().y + dy ; 
		 
		if(this.getStio().is_move_inside()) 
		{ 
			if( 
					m_max_x < gl.E_EMPTY  || 
					m_max_x + this.getDecoro().getBounds().width > (this.getDecoro().getParents_bounds().width - 1) 
			  ) 
		 
			{ 
				m_max_x = this.getLocation().x; 
			} 
			else 
			{ 
				this.setLocation(m_max_x,m_max_y); 
				 
				this.getDecoro().setBounds(this.getBounds()); 
				 
				this.getItems_to_move().forEach(a->{ 
							a.move_dx_dy(-dx, dy); 
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
	 
		//this.setItems_to_move(this.getIdo().getOwner().get_all_childs()); 
		 
		this.getItems_to_move().forEach(a->{ 
			 
			gl.sfn("Moved items...%s", a.getIdo().getName()); 
		}); 
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
		 
		GU.fillAlphaRoundRect(g2,Ru.norm4g(span),span.height/2,span.height/2,Color.white,.35f); 
				 
		 
		Rectangle v_rect = new Rectangle((span.width / 2),(span.height/4),2,span.height - (span.height/2)); 
		 
		Rectangle v_rect_l = new Rectangle((span.width / 2)-4,(span.height/4),2,span.height - (span.height/2)); 
		 
		Rectangle v_rect_r = new Rectangle((span.width / 2)+4,(span.height/4),2,span.height - (span.height/2)); 
				 
		GU.fillAlphaRect(g2,v_rect,Color.white,.25f); 
		 
		GU.fillAlphaRect(g2,v_rect_l,Color.white,.25f); 
		 
		GU.fillAlphaRect(g2,v_rect_r,Color.white,.25f); 
		 
		 
		GU.drawAlphaRoundRect(g2,Ru.norm4g(span),span.height/2,span.height/2,Color.white,.55f); 
		 
					 
		g2.setStroke(p_stroke); 

	} 
	 
		 
	public static void main(String[] args) { 
		 

	} 

} 
