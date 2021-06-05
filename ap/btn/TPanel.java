 
package ap.btn; 

import java.awt.AlphaComposite; 
import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Frame; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Paint; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.Stroke; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.CubicCurve2D; 
import java.awt.geom.GeneralPath; 
import java.awt.geom.Line2D; 
import java.awt.geom.Point2D; 
import java.awt.geom.QuadCurve2D; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Comparator; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Map.Entry; 
import java.util.OptionalDouble; 
import java.util.OptionalInt; 
import java.util.stream.Collectors; 
import java.util.Vector; 

import javax.swing.JColorChooser; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 
import javax.swing.border.Border; 

import ap.action.ActionHelper; 
import ap.area.AreaManager; 
import ap.collectors.SPanelCollector; 
import ap.config.TConfig; 
import ap.frame.TFrame; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.lsnrs.SPanelActionPerformers; 
import ap.menu.SMenu; 
import ap.menu.SMenuItem; 
import ap.menu.SPopupMenu; 
import ap.menu.TPanelPopupFactory; 
import ap.mirrors.TMirror; 
import ap.mng.SPanelManager; 
import ap.btn.SKeyHandler; 
import ap.prop.BaseProperty; 
import ap.prop.SAreaManager; 
import ap.prop.SBounds; 
import ap.prop.SColor; 
import ap.prop.SDate; 
import ap.prop.SDimension; 
import ap.prop.SFont; 
import ap.shape.Ru; 
import ap.shape.TRect; 
import ap.state.Fl; 
import ap.utils.Biu; 
import ap.utils.Bau; 
import ap.utils.CU; 
import ap.utils.DateUtil; 
import ap.utils.DialogUtil; 
import ap.utils.Fu; 
import ap.utils.GU; 
import ap.utils.ImageUtil; 
import ap.utils.MapUtils; 
import ap.utils.PointUtil; 
import ap.utils.PropUtil; 
import ap.utils.Su; 

public class TPanel extends TPanelDef implements ComponentListener, 

MouseListener, MouseMotionListener, KeyListener, Comparable<TPanel> { 

	private static final long serialVersionUID = 1L; 

	private int ali = gl.ALI.NONE; 

	private boolean bl_tool_tip = false; 

	public boolean bl_skip_mouse_pressed; 

	public boolean bl_skip_mouse_released; 

	public boolean bl_skip_mouse_clicked; 

	public boolean bl_skip_draw_image; 

	private boolean bl_mouse_dragged; 

	private Vector<TPanel> childs = new Vector<TPanel>(); 

	private Rectangle parent_rect; 

	private Rectangle parent_original_rect; 

	private BaseProperty prop; 

	private int gradient_type = gl.E_ERROR; 

	private SPanelActionPerformers pal = new SPanelActionPerformers(); 

	private boolean blCut; 

	private Rectangle cutRect = Ru.get_init_rect(gl.E_EMPTY); 

	private List<TPanel> mirrors = new ArrayList<TPanel>(); 

	private List<Bar> quotes = null; 

	private boolean bl_accept_area_initialization; 

	private double zoom_at_width; 

	private double zoom_at_height; 

	private double zoom_at_x; 

	private double zoom_at_y; 

	private double start_delta_x; 

	private double start_delta_y; 

	private BufferedImage self_buffered_image = null; 

	private boolean skip_draw_text = false; 

	private double tick_form_x_zoom_factor; 

	private double tick_form_y_zoom_factor; 

	private int ticks_capacity_on_the_form = gl.E_EMPTY; 

	private TDateRange date_range; 

	private String modal_result = ""; 

	private TChartHelper chart_helper = new TChartHelper(); 

	private Map<String, List<TCubic>> cubics = new HashMap<String, List<TCubic>>(); 

	private Map<String, List<TPivotSuite>> pivots = new HashMap<String, List<TPivotSuite>>(); 

	private List<Rectangle> last_inside = new ArrayList<Rectangle>(); 

	public List<Rectangle> getLast_inside() { 
		return last_inside; 
	} 

	public boolean isDebug() { 
		return Biu.ISA(this.getFlags(), Fl.VK_DEBUG); 
	} 

	public boolean setDebug(boolean debug) { 

		if (debug) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DEBUG)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DEBUG)); 
		} 

		return (debug == Biu.ISA(this.getFlags(), Fl.VK_DEBUG)); 

	} 

	public boolean isEnable() { 
		return !Biu.ISA(this.getFlags(), Fl.VK_DISABLE); 
	} 

	public void setEnable(boolean enable) { 

		if (enable) { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DISABLE)); 
		} else { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DISABLE)); 
		} 

	} 

	public boolean isMoveInside() { 

		return Biu.ISA(this.getFlags(), Fl.VK_MOVE_INSIDE); 

	} 

	public boolean isVisible() { 

		return !Biu.ISA(this.getFlags(), Fl.VK_VISIBLE); 

	} 

	public void setVisible(boolean visible) { 

		if (visible) { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DISABLE)); 
		} else { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DISABLE)); 
		} 

	} 

	public void setMoveInside(boolean inside) { 

		if (inside) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_MOVE_INSIDE)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_MOVE_INSIDE)); 
		} 

	} 

	public boolean isDenyx() { 

		return Biu.ISA(this.getFlags(), Fl.VK_DENY_X); 

	} 

	public void setDenyx(boolean denyx) { 

		if (denyx) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DENY_X)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DENY_X)); 
		} 
	} 

	public boolean isDenyy() { 

		return Biu.ISA(this.getFlags(), Fl.VK_DENY_Y); 

	} 

	public void setDenyy(boolean denyy) { 

		if (denyy) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DENY_Y)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DENY_Y)); 
		} 

	} 

	public boolean isSelected() { 

		return Biu.ISA(this.getFlags(), Fl.VK_SELECTED); 
	} 

	public void setSelected(boolean selected) { 

		if (selected) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_SELECTED)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_SELECTED)); 
		} 

	} 

	public Map<String, List<TPivotSuite>> getPivots() { 
		return pivots; 
	} 

	public void setPivots(Map<String, List<TPivotSuite>> pivots) { 
		this.pivots = pivots; 
	} 

	private boolean bl_accept_area; 

	public boolean isBl_accept_area() { 
		return bl_accept_area; 
	} 

	public void setBl_accept_area(boolean bl_accept_area) { 
		this.bl_accept_area = bl_accept_area; 
	} 

	public Map<String, List<TCubic>> getCubics() { 
		return cubics; 
	} 

	public void setCubics(Map<String, List<TCubic>> cubics) { 
		this.cubics = cubics; 
	} 

	public TChartHelper getChart_helper() { 
		return chart_helper; 
	} 

	public void setChart_helper(TChartHelper chart_helper) { 
		this.chart_helper = chart_helper; 
	} 

	public boolean isBl_skip_draw_image() { 
		return bl_skip_draw_image; 
	} 

	public void setBl_skip_draw_image(boolean bl_skip_draw_image) { 
		this.bl_skip_draw_image = bl_skip_draw_image; 
	} 

	public String getModal_result() { 

		return modal_result; 
	} 

	public void setModal_result(String modal_result) { 
		this.modal_result = modal_result; 
	} 

	public TDateRange getDate_range() { 
		return date_range; 
	} 

	public void setDate_range(TDateRange date_range) { 
		this.date_range = date_range; 
	} 

	public double getTick_form_x_zoom_factor() { 
		return tick_form_x_zoom_factor; 
	} 

	public void setTick_form_x_zoom_factor(double tick_form_x_zoom_factor) { 
		this.tick_form_x_zoom_factor = tick_form_x_zoom_factor; 
	} 

	public double getTick_form_y_zoom_factor() { 
		return tick_form_y_zoom_factor; 
	} 

	public void setTick_form_y_zoom_factor(double tick_form_y_zoom_factor) { 
		this.tick_form_y_zoom_factor = tick_form_y_zoom_factor; 
	} 

	public boolean isSkip_draw_text() { 
		return skip_draw_text; 
	} 

	public void setSkip_draw_text(boolean skip_draw_text) { 
		this.skip_draw_text = skip_draw_text; 
	} 

	public BufferedImage getSelf_buffered_image() { 
		return self_buffered_image; 
	} 

	public void setSelf_buffered_image(BufferedImage self_buffered_image) { 
		this.self_buffered_image = self_buffered_image; 
	} 

	public boolean isBl_accept_area_initialization() { 
		return bl_accept_area_initialization; 
	} 

	public void setBl_accept_area_initialization( 
			boolean bl_accept_area_initialization) { 
		this.bl_accept_area_initialization = bl_accept_area_initialization; 
	} 

	public List<Bar> getQuotes() { 
		return quotes; 
	} 

	public void setQuotes(List<Bar> quotes) { 
		this.quotes = quotes; 
	} 

	private int dx; 

	private int dy; 

	public int getDx() { 
		return dx; 
	} 

	public void setDx(int dx) { 
		this.dx = dx; 
	} 

	public int getDy() { 
		return dy; 
	} 

	public int getDeltaWidth() { 
		return (this.getOriginal_bounds().width - this.getBounds().width); 
	} 

	public int getDeltaHeight() { 
		return (this.getOriginal_bounds().height - this.getBounds().height); 
	} 

	public void setDy(int dy) { 
		this.dy = dy; 
	} 

	public Rectangle getParent_original_rect() { 
		return parent_original_rect; 
	} 

	public void setParent_original_rect(Rectangle parent_original_rect) { 
		this.parent_original_rect = parent_original_rect; 
	} 

	public List<TPanel> getMirrors() { 
		return mirrors; 
	} 

	public void setMirrors(List<TPanel> mirrors) { 
		this.mirrors = mirrors; 
	} 

	public Rectangle getCutRect() { 
		return cutRect; 
	} 

	public void setCutRect(Rectangle cutRect) { 
		this.cutRect = cutRect; 
	} 

	public boolean isBlCut() { 
		return blCut; 
	} 

	public void setBlCut(boolean blCut) { 
		this.blCut = blCut; 
	} 

	public boolean isBl_mouse_dragged() { 
		return bl_mouse_dragged; 
	} 

	public void setBl_mouse_dragged(boolean bl_mouse_dragged) { 
		this.bl_mouse_dragged = bl_mouse_dragged; 
	} 

	public SPanelActionPerformers getPal() { 
		return pal; 
	} 

	public void setPal(SPanelActionPerformers pal) { 
		this.pal = pal; 
	} 

	public boolean isBl_skip_mouse_clicked() { 
		return bl_skip_mouse_clicked; 
	} 

	public void setBl_skip_mouse_clicked(boolean bl_skip_mouse_clicked) { 
		this.bl_skip_mouse_clicked = bl_skip_mouse_clicked; 
	} 

	public int getGradient_type() { 
		return gradient_type; 
	} 

	public void setGradient_type(int gradient_type) { 
		this.gradient_type = gradient_type; 
	} 

	public BaseProperty getProp() { 
		return prop; 
	} 

	public void setProp(BaseProperty prop) { 
		this.prop = prop; 
	} 

	public boolean isBlGrid() { 

		return Biu.ISA(this.getFlags(), Fl.VK_GRID); 
	} 

	 
	public void setBlGrid(boolean state) { 

		if (state) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_GRID)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_GRID)); 
		} 

	} 

	public Rectangle getParent_rect() { 
		return parent_rect; 
	} 

	public void setParent_rect(Rectangle parent_rect) { 
		this.parent_rect = parent_rect; 
	} 

	public void setChilds(Vector<TPanel> childs) { 
		this.childs = childs; 
	} 

	public Vector<TPanel> get_Childs() { 

		return this.childs; 
	} 

	public void setUnSelectable(boolean unselectable) { 

		if (unselectable) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_DENY_SELECT)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_DENY_SELECT)); 
		} 

	} 

	public boolean isBl_skip_mouse_released() { 
		return bl_skip_mouse_released; 
	} 

	public void setBl_skip_mouse_released(boolean bl_skip_mouse_released) { 
		this.bl_skip_mouse_released = bl_skip_mouse_released; 
	} 

	public boolean isBl_skip_parent_code() { 
		return bl_skip_mouse_pressed; 
	} 

	public void setBl_skip_parent_code(boolean bl_skip_parent_code) { 
		this.bl_skip_mouse_pressed = bl_skip_parent_code; 
	} 

	public boolean isBl_tool_tip() { 
		return bl_tool_tip; 
	} 

	public void setBl_tool_tip(boolean bl_tool_tip) { 
		this.bl_tool_tip = bl_tool_tip; 
	} 

	public int getAli() { 
		return ali; 
	} 

	public void setAli(int ali) { 
		this.ali = ali; 
	} 

	public SKeyHandler KeyHandler = new SKeyHandler(); 

	public SKeyHandler getKeyHandler() { 
		return KeyHandler; 
	} 

	public void setKeyHandler(SKeyHandler keyHandler) { 
		KeyHandler = keyHandler; 
	} 

	 

	public TPanel() { 

		addMouseListener(this); 

		addMouseMotionListener(this); 

		this.addComponentListener(this); 

		this.addKeyListener(this); 

		this.setLayout(null); 

		this.setFocusable(true); 

		this.setPid(gl.E_ERROR); 

		this.setGid(gl.E_ERROR); 

		this.setId(IDGen.nextId()); 

		this.setChild_dim(new Dimension(7, 7)); 

		this.setArea_manager(new AreaManager()); 

		this.setType(this.getClass().getSimpleName()); 

		this.setDebug(false); 

		this.setSelected(false); 

		this.setText(""); 

		this.setFont(new Font("Tahoma", 0, 9)); 

		this.setText_color(Color.black); 

		this.setGrid_color(Color.white); 

		this.setImg_desc("None"); 

		this.setImg_type("None"); 

		this.setImg_size(new Dimension(gl.E_ERROR, gl.E_ERROR)); 

		this.setImage(null); 

		this.setBack_ground(UIManager.getColor("Panel.background")); 

		this.setGradient(CU.getAlphaColor(this.getBackground(), 
				gl.E_EMPTY)); 

		this.setBorder("rbb"); 

		this.setText_color(Color.black); 

		this.setAlign(Ru.get_init_rect(gl.E_OK)); 

		this.setBorder("NONE"); 

		this.setText_color(Color.DARK_GRAY); 

		this.setName(String.format("%s_%04d", this.getClass().getSimpleName(), 
				this.getId())); 

		this.setUp_color(Color.white); 

		this.setDown_color(Color.black); 

	} 

	public TPanel(String name, boolean mode) { 

		this(); 

		this.setName(name); 

	} 

	public TPanel(Rectangle rect) { 

		this(); 

		this.setBou_nds(rect); 

	} 

	public TPanel(Rectangle rect, int index) { 

		this(rect); 

		this.setZorder(index); 

	} 

	public TPanel(Rectangle rect, Color color) { 

		this(rect); 

		this.setBack_ground(color); 

		this.setGradient(new Color(255, 255, 255, 0)); 

	} 

	public TPanel(String payload) { 

		this.setPayload(payload); 

	} 

	public TPanel(BaseProperty prop) { 

		this(); 

		this.setProp(prop); 

		this.boundsProp(); 

		this.setToolTipText(this.getTool_tip_text()); 

	} 

	public void boundsProp() { 

		BaseProperty prop = this.getProp(); 

		this.setPid(prop.getPid().getValue()); 

		this.setGid(prop.getGid().getValue()); 

		this.setId(prop.getId().getValue()); 

		this.setZorder(prop.getZorder().getValue()); 

		this.setBack_ground(prop.getBackground().getColor()); 

		// Add gradient type param. 

		this.setGradient(prop.getGradient().getColor()); 

		if (prop.getGradient().getTag_map().size() != gl.E_EMPTY) { 
			int KEY_INDEX = 3; 

			String gradient_type = MapUtils.findValueByKey(prop.getGradient() 
					.getTag_map(), KEY_INDEX); 

			if (gradient_type != null) { 

				int gr_type = Integer.parseInt(gradient_type); 

				this.setGradient_type(gr_type); 

			} 

		} 

		this.setBou_nds(prop.getBounds().getBounds()); 

		this.setOriginal_bounds(prop.getOriginal_bounds().getBounds()); 

		this.setChild_dim(prop.getChild_dim().getDimension()); 

		this.setArea_manager(prop.getArea_manager().getArea_manager()); 

		this.setType(prop.getType().getText()); 

		this.setText(Bau.to_str_from_hex_str(prop.getText() 
				.getText())); 

		this.setFont(prop.getFont().getFont()); 

		this.setText_color(prop.getText_color().getColor()); 

		this.setGrid_color(prop.getGrid_color().getColor()); 

		this.setUp_color(prop.getUp_color().getColor()); 

		this.setDown_color(prop.getDown_color().getColor()); 

		this.setImg_size(prop.getImage().getSize().getDimension()); 

		this.setImg_type(prop.getImage().getSource().getType().getText()); 

		this.setImg_desc(prop.getImage().getSource().getDescription().getText()); 

		this.setImage(prop.getImage().getSource().getBufferedImage()); 

		this.setAlign(prop.getAlign().getBounds()); 

		this.setBorder(prop.getBorder().getText()); 

		this.setBorder(gl.getBorderTypeByText(this.getBor_der())); 

		this.setFlags(prop.getState().getValue()); 

		this.setAction(prop.getAction().getText()); 

		this.setData(prop.getData().getText()); 

		this.setFunction(prop.getFunction().getText()); 

		this.setTool_tip_text(prop.getTool_tip().getText()); 

		this.setName(prop.getName().getText()); 

		this.setBase(prop.getBase().getValue()); 

		this.setOffset(prop.getOffset().getValue()); 

	} 

	public String toStringForToolTip() { 

		return String.format("%s", this.getTool_tip_text()); 

	} 

	public String toStringForTest() { 
		return String.format("pid=%d,id=%d,name=%s;", this.getPid(), 
				this.getId(), this.getName()); 

	} 

	 

	public boolean acceptArea() { 

		if (this.isBl_accept_area()) { 

			gl.tracex( 
					new Object() { 
					}, 
					gl.sf("%s...%s...ALREADY set accept area...type...%s...tool tip...%s...id...%d", 
							gl.S_OK, this.getType(), this.getTool_tip_text(), 
							this.getId())); 

			return true; 

		} 

		AreaManager am = this.getArea_manager(); 

		if (am.isEmpty()) { 

			// gl.tracex(new Object(){},gl.sf("%s...%s",gl.S_OK,"Unmanaged area presents.")); 

			return false; 
		} 

		if (this.getPid() == gl.E_ERROR) { 

			gl.tracex(new Object(){},gl.sf("%s...%s",gl.S_OK,"Unmanaged object presents.")); 

			return false; 
		} 

		Rectangle client_area = this.getBounds(); 

		Rectangle parent_area = this.getParent_rect(); 

		Rectangle area = this.getArea_manager().getBounds(); 

		Rectangle shift = this.getArea_manager().getShiftParamRect(); 

		if (!this.isBl_accept_area_initialization()) 

		{ 

			double wp = parent_area.width; 

			double wc = client_area.width; 

			double hp = parent_area.height; 

			double hc = client_area.height; 

			zoom_at_width = wc / wp; 

			zoom_at_height = hc / hp; 

			int dx = (client_area.x - parent_rect.x); 

			if (dx == gl.E_EMPTY) 
				dx = gl.E_OK; 

			start_delta_x = dx; 

			int dy = (client_area.y - parent_area.y); 

			if (dy == gl.E_EMPTY) 
				dy = gl.E_OK; 

			start_delta_y = dy; 

			zoom_at_y = start_delta_y / parent_rect.height; 

			zoom_at_x = start_delta_x / parent_rect.width; 

			this.setBl_accept_area_initialization(true); 

		} // if this.isBl_accept_area_initialization() 

		if (am.getDistributionType() == gl.ALI.NONE) { 

			area.x += parent_rect.x; 

			area.y += parent_rect.y; 

			this.setBou_nds(area); 

		} else if (am.getDistributionType() == gl.ALI.FULL) { 

			Ru.clear(area); 

			area.x = parent_rect.x + shift.x; 

			area.y = parent_rect.y + shift.y; 

			area.width = parent_rect.width + shift.width; 

			area.height = parent_rect.height + shift.height; 

			this.setBou_nds(area); 

		} else if (am.getDistributionType() == gl.ALI.CENTER) { 

			Ru.clear(area); 

			area.x = ((parent_rect.x + parent_rect.width / 2) - client_area.width / 2) 
					+ shift.x; 

			area.y = ((parent_rect.y + parent_rect.height / 2) - client_area.height / 2) 
					+ shift.y; 

			area.width = client_area.width + shift.width; 
			; 

			area.height = client_area.height + shift.height; 
			; 

			this.setBou_nds(area); 

		} 

		else if (am.getDistributionType() == gl.ALI.TOP) { 

			aliTop(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.TOP_SYNC) { 

			aliTopSync(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.GLUED_TOP) { 

			aliGluedTop(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.GLUED_BOTTOM) { 

			aliGluedBottom(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.BOTTOM) { 

			aliBottom(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.BOTTOM_SYNC) { 

			Ru.clear(area); 

			double d_height = parent_rect.height * zoom_at_height; 

			int i_height = (int) d_height; 

			area.x = parent_rect.x + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - i_height) 
					+ shift.y; 

			area.width = parent_rect.width + shift.width; 

			area.height = i_height; 

			this.setBou_nds(area); 

		} else if (am.getDistributionType() == gl.ALI.BOTTOM_LEFT) { 

			Ru.clear(area); 

			area.x = parent_rect.x + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBou_nds(area); 

		} else if (am.getDistributionType() == gl.ALI.BOTTOM_CENTER) { 

			Ru.clear(area); 

			area.x = (parent_rect.x + (parent_rect.width / 2)) 
					+ client_area.width / 2 + shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBou_nds(area); 

		} else if (am.getDistributionType() == gl.ALI.BOTTOM_RIGHT) { 

			Ru.clear(area); 

			area.x = ((parent_rect.x + parent_rect.width) - client_area.width) 
					+ shift.x; 

			area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
					+ shift.y; 

			area.width = parent_rect.x + shift.width; 

			area.height = client_area.height + shift.height; 

			this.setBou_nds(area); 

		} 

		else if (am.getDistributionType() == gl.ALI.LEFT) { 

			aliLeft(area, parent_rect, client_area, shift); 

		} else if (am.getDistributionType() == gl.ALI.RIGHT) { 

			 
			aliRight(area, parent_rect, client_area, shift); 

		} 
		// Glued's section. 
		else if (am.getDistributionType() == gl.ALI.GLUED_LEFT) { 

			// 
			aliGluedLeft(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT 
		else if (am.getDistributionType() == gl.ALI.GLUED_LEFT_SYNC) { 

			aliGluedLeftSync(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT_SYNC 
		else if (am.getDistributionType() == gl.ALI.GLUED_RIGHT) { 

			// 
			aliGluedRight(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_LEFT 
		else if (am.getDistributionType() == gl.ALI.GLUED_RIGHT_SYNC) { 

			aliGluedRightSync(area, parent_rect, client_area, shift); 

		} // gl.ALI.GLUED_RIGHT_SYNC 

		List<TPanel> childs = this.getChildsSorted(this.getId()); 

		childs.forEach( 

		a -> { 
			a.setParent_rect(this.getBounds()); 

			a.setParent_original_rect(this.getOriginal_bounds()); 

			a.getArea_manager().setArea( 
					new Dimension(this.getBounds().width, 
							this.getBounds().height)); 

			a.acceptArea(); 

			a.setBl_accept_area(true); 

		}); 

		// gl.tracex(new 
		// Object(){},String.format("%s...Accept area...type...%s...tool tip...%s...id...%d",gl.S_OK,this.getType(),this.getTool_tip_text(),this.getId())); 

		return true; 

	} 

	public String getImageHex() { 
		return Bau.to_hex_string_from_buffered_image(this.getImage(), this.getImg_type()); 
	} 

	@Override 
	public String toString() { 

		String msg = "pid=%d;gid=%d;id=%d;name=%s;zorder=%d;" + "type=%s;" 
				+ "function=%s;" + "model=%s;" + "text=%s;tool_tip=%s;" 
				+ "background=%s;" + "gradient=%s%s;" + "bounds=%s;" 
				+ "original_bounds=%s;" + "child_dim=%s;" + "area_manager=%s;" 
				+ "font=%s;" + "text_color=%s;" + "grid_color=%s;" 
				+ "up_color=%s;down_color=%s;" + "img_size=%s;" 
				+ "img_type=%s;" + "img_desc=%s;" 
				+ "align=%s;border=%s;state=%d;base=%s;offset=%s;" 
				+ "action=%s;data=%s;"; 

		StringBuilder sb = new StringBuilder(); 

		sb.append(String.format( 
				msg, 
				this.getPid(), 
				this.getGid(), 
				this.getId(), 
				this.getName(), 
				this.getZorder(), 

				this.getType(), 
				this.getFunction(), 
				this.getModel(), 
				this.getText(), 
				this.getTool_tip_text(), 

				SColor.toString(this.getBackground()), 
				SColor.toString(this.getGradient()), 

				(this.getGradient_type() == gl.E_ERROR) ? "" : String.format( 
						"#%d", this.getGradient_type()), 

				SBounds.toString(this.getBounds()), 

				SBounds.toString((this.getOriginal_bounds() == null) ? this 
						.getBounds() : this.getOriginal_bounds()), 

				SDimension.toString(this.getChild_dim()), 

				SAreaManager.toString(this.getArea_manager()), 

				SFont.toString(this.getFont()), 

				SColor.toString(this.getText_color()), 

				SColor.toString(this.getGrid_color()), 

				// New features from 26.11.2018 
				SColor.toString(this.getUp_color()), 

				SColor.toString(this.getDown_color()), 

				SDimension.toString(this.getImg_size()), this.getImg_type() 
						.equalsIgnoreCase("None") ? "gif" : this.getImg_type(), 
				this.getImg_desc(), SBounds.toString(this.getAlign()), this 
						.getBor_der(), this.getFlags(), this.getBase(), this 
						.getOffset(), this.getAction(), this.getData() 

		)); 

		String hex = ""; 

		BufferedImage bi_from = this.getImage(); 

		if (bi_from != null) { 
			String img_type = this.getImg_type(); 

			if (img_type.equalsIgnoreCase("None") 
					|| img_type.equalsIgnoreCase("ico")) 
				img_type = "gif"; 

			hex = Bau.to_hex_string_from_buffered_image(bi_from, img_type); 

		} 

		sb.append("img_data="); 

		sb.append(hex); 

		sb.append(";"); 

		return sb.toString(); 

	} 

	public SPanelManager getManager() { 
		return manager; 
	} 

	public void setManager(SPanelManager manager) { 
		this.manager = manager; 
	} 

	public static void startUp() { 

		// TConfig.start(TPanel.getInstances(100)); 

		TConfig.start(null); 
	} 

	public boolean connectTo(int pid) { 
		this.setPid(pid); 

		return (this.getPid() == pid); 
	} 

	
	public boolean chart_step_right(TPanel owner, String series, 
			boolean direction, int offset) { 

		TIndexRange tir = ((TChart) owner).getIndex_range().get(series); 

		gl.tracex(new Object() { 
		}, gl.sf("%s...Series...%s...direction...%s...offset...%d", gl.S_OK, 
				series, direction, offset)); 

		
		int x_off_set = tir.getOffset(); 

		if (direction) 
			x_off_set += offset; 
		else if (x_off_set > gl.E_EMPTY) 
			x_off_set -= offset; 

		if (x_off_set < gl.E_EMPTY) 
			x_off_set = gl.E_EMPTY; 
		
		int start 	= ((TChart) owner).getIndex_range().get(series).getStart(); 

		int end 	= ((TChart) owner).getIndex_range().get(series).getEnd(); 

		int max 	= ((TChart) owner).getIndex_range().get(series).getMax(); 
		

		int m_check = x_off_set + (end - start);
		
		gl.tx_d(new Object() {},gl.sf("Start...%d...End...%d...Max...%d...Check...%d",
				start,end,max,m_check));

		if (m_check <= max) 
		{
			((TChart) owner).getIndex_range().get(series).setOffset(x_off_set); 
		
			((TChart) owner).refresh_chart_series((TChart) (owner)); 

			((TChart) owner).getChart_helper().init(((TChart) owner)); 
		}
		else
			gl.tx_k(new Object() {},gl.sf("Index STOP...Start...%d...End...%d...Max...%d...Check...%d",
					start,end,max,m_check));
	

			this.requestFocusInWindow(); 

		return true; 
	} 

	

	public static void main(String[] args) { 

		startUp(); 

	} 

	@Override 
	public void keyTyped(KeyEvent e) { 
	} 

	@Override 
	public void keyPressed(KeyEvent e) { 

		int keyCode = e.getKeyCode(); 

		if (!this.isAt_selected()) 
			return; 

		switch (keyCode) { 

		case KeyEvent.VK_CONTROL: { 

			this.KeyHandler.setBlCtrl(true); 

		} 

			break; 

		case KeyEvent.VK_SHIFT: { 

			this.KeyHandler.setBlShift(true); 

		} 

			break; 

		case KeyEvent.VK_ALT: { 

			this.KeyHandler.setBlAlt(true); 

		} 

		// Left 
		case KeyEvent.VK_A: { 

			this.KeyHandler.setBlA(true); 
		} 
			break; 

		// Right 
		case KeyEvent.VK_D: { 

			this.KeyHandler.setBlD(true); 
		} 
			break; 

		// Up 
		case KeyEvent.VK_W: { 
			this.KeyHandler.setBlW(true); 
		} 
			break; 

		// Down 
		case KeyEvent.VK_S: { 
			this.KeyHandler.setBlS(true); 

		} 
			break; 

		case KeyEvent.VK_I: { 
			this.KeyHandler.setBlI(true); 

		} 
			break; 

		case KeyEvent.VK_T: { 
			this.KeyHandler.setBlT(true); 

		} 
			break; 

		case KeyEvent.VK_G: { 
			this.KeyHandler.setBlG(true); 

		} 
			break; 

		case KeyEvent.VK_C: { 
			this.KeyHandler.setBlC(true); 

		} 
			break; 

		case KeyEvent.VK_LEFT: { 
			this.KeyHandler.setBlLeft(true); 

		} 
			break; 

		case KeyEvent.VK_RIGHT: { 
			this.KeyHandler.setBlRight(true); 

		} 
			break; 

		case KeyEvent.VK_UP: { 
			this.KeyHandler.setBlUp(true); 

		} 
			break; 

		case KeyEvent.VK_DOWN: { 
			this.KeyHandler.setBlDown(true); 

		} 
			break; 

		case KeyEvent.VK_PAGE_UP: { 
			this.KeyHandler.setBlPageUp(true); 

		} 
			break; 

		case KeyEvent.VK_PAGE_DOWN: { 
			this.KeyHandler.setBlPageDown(true); 

		} 
			break; 

		}// switch 

		this.KeyHandler.handle(this); 
	} 

	@Override 
	public void keyReleased(KeyEvent e) { 

		int keyCode = e.getKeyCode(); 

		switch (keyCode) { 

		case KeyEvent.VK_CONTROL: { 

			// gl.smn("CTRL"); 

			// Y ^= Fl.VK_CTRL_MASK; 

			this.KeyHandler.setBlCtrl(false); 

		} 

			break; 

		case KeyEvent.VK_SHIFT: { 

			// gl.smn("SHIFT"); 

			// Y ^= Fl.VK_SHIFT_MASK; 

			this.KeyHandler.setBlShift(false); 

		} 

			break; 

		case KeyEvent.VK_ALT: { 

			// gl.smn("ALT"); 

			// Y ^= Fl.VK_ALT_MASK; 

			this.KeyHandler.setBlAlt(false); 

		} 

			break; 

		// Left 
		case KeyEvent.VK_A: { 

			this.KeyHandler.setBlA(false); 
		} 
			break; 

		// Right 
		case KeyEvent.VK_D: { 

			this.KeyHandler.setBlD(false); 
		} 
			break; 

		// Up 
		case KeyEvent.VK_W: { 
			this.KeyHandler.setBlW(false); 
		} 
			break; 

		// Down 
		case KeyEvent.VK_S: { 
			this.KeyHandler.setBlS(false); 

		} 
			break; 

		// Down 
		case KeyEvent.VK_O: { 
			this.KeyHandler.setBlO(false); 

		} 
			break; 

		case KeyEvent.VK_I: { 
			this.KeyHandler.setBlI(false); 

		} 
			break; 

		case KeyEvent.VK_T: { 
			this.KeyHandler.setBlT(false); 

		} 
			break; 

		case KeyEvent.VK_G: { 
			this.KeyHandler.setBlG(false); 

		} 
			break; 

		case KeyEvent.VK_C: { 
			this.KeyHandler.setBlC(false); 

		} 
			break; 

		case KeyEvent.VK_LEFT: { 
			this.KeyHandler.setBlLeft(false); 

		} 
			break; 

		case KeyEvent.VK_RIGHT: { 
			this.KeyHandler.setBlRight(false); 

		} 
			break; 

		case KeyEvent.VK_UP: { 
			this.KeyHandler.setBlUp(false); 

		} 
			break; 

		case KeyEvent.VK_DOWN: { 
			this.KeyHandler.setBlDown(false); 

		} 
			break; 

		case KeyEvent.VK_PAGE_UP: { 
			this.KeyHandler.setBlPageUp(false); 

		} 
			break; 

		case KeyEvent.VK_PAGE_DOWN: { 
			this.KeyHandler.setBlPageDown(false); 

		} 
			break; 

		}// switch 

		this.KeyHandler.free(this); 
	} 

	public synchronized Vector<TPanel> getChilds(int pid) { 
		return this.getManager().getCollector().getData().stream() 
				.filter((b -> (b.getPid() == pid))) 
				.collect(Collectors.toCollection(Vector::new)); 

	} 

	public synchronized List<TPanel> getChildsSorted(int pid) { 
		List<TPanel> tmp = 

		this.getManager().getCollector().getData().stream() 
				.filter((b -> (b.getPid() == pid))) 
				.sorted(Comparator.comparing(s -> s.getId())) 
				.collect(Collectors.toList()); 

		return tmp; 

	} 

	public synchronized Vector<TPanel> getChildsAll(int pid) { 

		if (this.getManager() == null) { 
			return null; 
		} 

		if (this.getManager().getCollector().getData() == null) { 
			return null; 
		} 

		Vector<TPanel> l = new Vector<TPanel>(); 

		getChilds(this.getId(), l); 

		this.setChilds(l); 

		return this.get_Childs(); 

	} 

	public synchronized boolean getChilds(int pid, Vector<TPanel> list) { 
		/* 
		 * long count = this.getManager().getCollector().getData().stream() 
		 * .filter((a -> (a.getPid() == pid))).count(); 
		 * 
		 * if (count == gl.E_EMPTY) { return false; } 
		 */ 

		// Previous version. 

		this.getManager().getCollector().getData().stream() 
				.filter((a -> (a.getPid() == pid))).forEach(b -> { 

					if (!list.contains(b)) 
						list.add(b); 

					b.getChilds(b.getId(), list); 
				}); 

		return true; 

	} 

	public boolean isFreeze() { 

		return Biu.ISA(this.getFlags(), Fl.VK_FREEZE); 
	} 

	public void setFreeze(boolean freeze) { 

		if (freeze) { 
			this.setFlags(Biu.ON(this.getFlags(), Fl.VK_FREEZE)); 
		} else { 
			this.setFlags(Biu.OFF(this.getFlags(), Fl.VK_FREEZE)); 
		} 

	} 

	public boolean isSkipSelected() { 

		return Biu.ISA(this.getFlags(), Fl.VK_DENY_SELECT); 

	} 

	public boolean initCubics(String key, Map<String, List<TCubic>> map) { 

		List<TPanel> sol = this 
				.getManager() 
				.getCollector() 
				.get_list_by_pid_and_function(this.getId(), 
						String.format("%s_start_point", key)); 

		List<TPanel> eol = this 
				.getManager() 
				.getCollector() 
				.get_list_by_pid_and_function(this.getId(), 
						String.format("%s_end_point", key)); 

		List<TPanel> cool = this 
				.getManager() 
				.getCollector() 
				.get_list_by_pid_and_function(this.getId(), 
						String.format("%s_control_point_1", key)); 

		List<TPanel> ctol = this 
				.getManager() 
				.getCollector() 
				.get_list_by_pid_and_function(this.getId(), 
						String.format("%s_control_point_2", key)); 

		gl.tracex(new Object() { 
		}, String.format( 
				"%s...%s...starts...%d...ends...%d...cp1...%d...cp2...%d", 

				gl.S_OK, this.getFunction(), sol.size(), eol.size(), 
				cool.size(), ctol.size())); 

		// Making some instantiation. 

		List<TCubic> cubics = new ArrayList<TCubic>(); 

		sol.forEach(s -> { 
			cubics.add(TCubic.getInstance( 
					this, 
					s, 
					this.getManager().getCollector() 
							.get_by_pid_and_gid(eol, s.getPid(), s.getGid()), 
					this.getManager().getCollector() 
							.get_by_pid_and_gid(cool, s.getPid(), s.getGid()), 
					this.getManager().getCollector() 
							.get_by_pid_and_gid(ctol, s.getPid(), s.getGid()))); 

		}); 

		if (map.containsKey(this.getFunction())) 
			map.replace(this.getFunction(), cubics); 
		else 
			map.put(this.getFunction(), cubics); 

		gl.tracex( 
				new Object() { 
				}, 
				String.format("%s...%s...%d", gl.S_OK, "Cubics count", 
						cubics.size())); 

		// Make some noise. 

		sol.get(gl.E_EMPTY).mouseDragged(null); 

		return (map.get(key).size() != gl.E_EMPTY); 
	} 

	public boolean isa_data_provider(String[] response) { 
		String function_raw = this.getFunction().trim(); 

		String function_pure = ""; 

		List<String> list = Arrays.asList(new String[] { "TQuad", "TCubic" }); 

		boolean bl_presents = function_raw.contains("_"); 

		if (bl_presents) { 
			function_pure = Su.BeforeAtFirst(function_raw, "_"); 

			if (list.contains(function_pure)) { 
				response[gl.E_EMPTY] = function_pure; 

				return true; 
			} 
		} 

		return false; 

	} 

	@Override 
	public void mouseDragged(MouseEvent me) { 

		Object v = new Object() { 
		}; 

		if (!this.isBlCut()) 
			this.setBl_mouse_dragged(true); 

		if (this.isFreeze()) { 
			if (this.getPid() != gl.E_ERROR) { 
				TPanel grand = this.getManager().getCollector() 
						.getGrandForm(this.getPid()); 

				// Moved parent form. 
				if (grand != null) { 
					grand.startPos = this.startPos; 

					grand.setDragType(Cursor.DEFAULT_CURSOR); 

					grand.mouseDragged(me); 

					return; 

				} 
			} 

			return; 
		} // end freeze 

		 

		if (startPos != null) { 

			int x = getX(); 

			int y = getY(); 

			int w = getWidth(); 

			int h = getHeight(); 

			int dx = (me.getX() - startPos.x); 

			int dy = (me.getY() - startPos.y); 

			// Deny section activation. 
			if (!this.isDenyx()) 
				this.setDx(dx); 

			if (!this.isDenyy()) 
				this.setDy(dy); 

			// Processing for VK_MOVE_INSIDE 

			if (this.getPid() != gl.E_ERROR && this.isMoveInside()) { 

				Rectangle parent_rect = this.getParent_rect(); 

				Rectangle this_rect = this.getBounds(); 

				Rectangle future_rect = new Rectangle(this_rect.x 
						+ this.getDx(), this_rect.y + this.getDy(), 
						this_rect.width + TPanel.MW, this_rect.height 
								+ TPanel.MW); 

				// Calculate constraints for inside objects. 

				if (!parent_rect.contains(future_rect) 
						&& (this.getArea_manager().getDistributionType() != gl.ALI.GLUED_RIGHT_SYNC && this 
								.getArea_manager().getDistributionType() != gl.ALI.GLUED_LEFT_SYNC)) { 

					return; 
				} 

				// Calculate constraints for outside objects. 
				if (this.getArea_manager().getDistributionType() == gl.ALI.GLUED_RIGHT_SYNC 
						|| this.getArea_manager().getDistributionType() == gl.ALI.GLUED_LEFT_SYNC) { 

					// gl.smn(">"); 

					if (this_rect.y + this.getDy() <= parent_rect.y) { 
						return; 
					} 
				} 

			} 

			switch (this.getDragType()) { 

			case Cursor.N_RESIZE_CURSOR: 

				if (!(h - dy < MW)) { 

					Rectangle r = new Rectangle(x, y + dy, w, h - dy); 

					setBou_nds(r); 

				} 

				break; 

			case Cursor.S_RESIZE_CURSOR: 

				if (!(h + dy < MW)) { 

					startPos = me.getPoint(); 

					setBou_nds(new Rectangle(x, y, w, h + dy)); 
				} 

				break; 

			case Cursor.W_RESIZE_CURSOR: 

				if (!(w - dx < MW)) { 

					setBou_nds(new Rectangle(x + dx, y, w - dx, h)); 

				} 

				break; 

			case Cursor.E_RESIZE_CURSOR: 

				if (!(w + dx < MW)) { 

					startPos = me.getPoint(); 

					setBou_nds(new Rectangle(x, y, w + dx, h)); 

				} 

				break; 

			case Cursor.NW_RESIZE_CURSOR: 

				if (!(w - dx < MW) && !(h - dy < MW)) { 

					setBou_nds(new Rectangle(x + dx, y + dy, w - dx, h - dy)); 

				} 

				break; 

			case Cursor.NE_RESIZE_CURSOR: 

				if (!(w + dx < MW) && !(h - dy < MW)) { 

					startPos = new Point(me.getX(), startPos.y); 

					setBou_nds(new Rectangle(x, y + dy, w + dx, h - dy)); 

				} 

				break; 

			case Cursor.SW_RESIZE_CURSOR: 

				if (!(w - dx < MW) && !(h + dy < MW)) { 

					startPos = new Point(startPos.x, me.getY()); 

					setBou_nds(new Rectangle(x + dx, y, w - dx, h + dy)); 

				} 

				break; 

			case Cursor.SE_RESIZE_CURSOR: 

				if (!(w + dx < MW) && !(h + dy < MW)) { 

					startPos = me.getPoint(); 

					setBou_nds(new Rectangle(x, y, w + dx, h + dy)); 

				} 

				break; 

			case Cursor.DEFAULT_CURSOR: { 

				// Make the alone state. 
				if (getBounds() != null) { 
					Rectangle bounds = getBounds(); 

					Rectangle parent_rect = this.getParent_rect(); 

					bounds.translate(this.getDx(), this.getDy()); 

					setBou_nds(bounds); 
				} 

				Vector<TPanel> movedChilds = this.moveChilds(this, 
						this.getDx(), this.getDy()); 

				if (movedChilds != null && movedChilds.size() != gl.E_EMPTY) { 
					this.getManager().getSelected().removeAll(movedChilds); 
				} 

				// If selected then move them. 
				if (this.getManager() != null 
						&& this.getManager().getSelected().size() != gl.E_EMPTY 
						&& this.isSelected()) { 
					// All selected group must be moved too. 
					for (TPanel s : this.getManager().getSelected().getData()) { 
						if (s.getId() != this.getId()) { 
							Rectangle b = s.getBounds(); 

							b.translate(this.getDx(), this.getDy()); 

							s.setBou_nds(b); 
						} 
					}// for 
				}// if 
			} // body dragged. 

				break; 

			} // switch 

		} // if startPos 

		if (startPos != null && this.isBlCut()) { 

			this.setDx(me.getX() - startPos.x); 

			this.setDy(me.getY() - startPos.y); 

			Rectangle temp = new Rectangle(startPos.x, startPos.y, 
					this.getDx(), this.getDy()); 

			this.setCutRect(temp.getBounds()); 

			this.repaint(this.getCutRect()); 

		} 

		String[] response = new String[] { "", "" }; 

		if (this.isa_data_provider(response)) { 

			String what = String.format("%s_", response[gl.E_EMPTY]); 

			if (this.getFunction().startsWith(what)) { 
				String function = Su.AfterAt(this.getFunction(), what) 
						.trim(); 

				if (function.length() != gl.E_EMPTY) { 

					TPanel parent = this.getManager().getCollector() 
							.get_by_id(this.getPid()); 

					if (parent != null) { 
						Point2D p2d = new Point2D.Double(); 

						p2d.setLocation((this.getX() - parent.getX()), 
								(this.getY() - parent.getY())); 

						List<TCubic> cubics = parent.getCubics().get( 
								parent.getFunction()); 

						TCubic cubic = TCubic.get_by_id(this.getGid(), cubics); 

						if (cubic != null) { 
							cubic.setPointHub(parent.getFunction(), 
									function.trim(), p2d); 

							parent.repaint(); 
						} 

					}// check parent 
				}// check function length 
			}// check by startsWith 

		} // isa_data_provider 

	} 

	@SuppressWarnings("unchecked") 
	@Override 
	public void mouseMoved(MouseEvent e) { 

		rects.forEach(name -> 

		{ 

			Ru ar = (Ru) name; 

			if (ar.contains(e.getPoint())) { 

				this.setDragType(ar.getServiceType()); 

				this.setCursor(new Cursor(ar.getServiceType())); 

			} 

		}); 

		this.setToolTipText(this.getTool_tip_text()); 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 

		Object v = new Object() { 
		}; 

		if (SwingUtilities.isRightMouseButton(e)) { 
			this.requestFocus(); 

			SPopupMenu pm = TPanelPopupFactory.createPopup(this, e); 

			if (!this.isBl_skip_mouse_clicked()) 
				pm.show(this, e.getX(), e.getY()); 

			return; 

		} 

		if (SwingUtilities.isLeftMouseButton(e)) { 
			if (!this.isBl_skip_parent_code()) { 
				if (!this.isSelected()) { 

					if (!this.isSkipSelected()) 
						this.setSelected(true); 

					if (!this.getManager().getSelected().getData() 
							.contains(this)) 
						this.getManager().getSelected().getData().add(this); 

				} else { 

					this.setSelected(false); 

					if (this.getManager().getSelected().getData() 
							.contains(this)) 
						this.getManager().getSelected().getData().remove(this); 

				} 

				this.repaint(); 

				this.getManager().getFrame().repaint(this.getBounds()); 

				Rectangle fect = this.getManager().getFrame().getBounds(); 

				fect = new Rectangle(fect.width - 200, fect.height - 200, 200, 
						200); 

				this.getManager().getFrame().repaint(fect); 

			} // bl_skip_parent_code 

			// Action resolver implementation. 

			String action = this.getAction(); 

			if (action != null && action.trim().length() != gl.E_EMPTY) { 
				gl.tracex(new Object() { 
				}, String.format("%s...%s...%s", gl.S_OK, "Try to resolve of", 
						action.trim())); 

				if (!this.actionResolver(action)) 
					gl.tracex(new Object() { 
					}, String.format("%s...%s...%s", gl.E_ERROR, 
							"Try to resolve action", action.toUpperCase())); 
				else 
					gl.tracex(new Object() { 
					}, String.format("%s...%s...%s", gl.S_OK, 
							"RESOLVED action", action.trim())); 

			} 

			// Check pick color. 

			if (this.getManager().getFrame().isBlPickMode()) { 

				this.setBack_ground(this.getManager().getFrame().getPickColor()); 

				this.repaint(); 

			} 

		} // Left mouse 

		requestFocus(); 

	} 

	public boolean isSensitivityArea(Point point) { 
		Object v = new Object() { 
		}; 

		// gl.smn("---> Rects size : " + this.getRects().size()); 

		if (this.getRects().size() == 9) { 
			ap.shape.Ru body = ((Ru) this.getRects().get(8)); 

			if (body != null) { 
				gl.traceOff(); 
			} 

			// Check sensitivity area. 
			if (!gl.getSpannedRect( 
					body, 
					new Insets(TPanelDef.MW, TPanelDef.MW, TPanelDef.MW, 
							TPanelDef.MW)).contains(point)) { 
				gl.tracex(v, String.format("%s...%s", 
						"Sensitivity out of body area", "On")); 

				gl.traceOn(); 

				return true; 
			} 

			gl.tracex(v, 
					String.format("%s...%s", "Sensitivity body area", "On")); 

			gl.traceOn(); 

			return false; 
		} 
		return false; 
	} 

	@Override 
	public void mousePressed(MouseEvent e) { 

		Object v = new Object() { 
		}; 

		startPos = e.getPoint(); 

		if (isSensitivityArea(e.getPoint())) 
			return; 

	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 

		Object v = new Object() { 
		}; 

		if (this.isBl_mouse_dragged() && !this.isBlCut()) { 

			// Get target. 
			int[] p_id = { gl.E_ERROR }; 

			if (this.getManager() != null) 
				p_id[0] = this.getManager().getTarget(this); 

			if (p_id[0] == gl.E_ERROR) { 

				this.setBl_mouse_dragged(false); 

				if (this.getType().equalsIgnoreCase("TChart")) { 
					// this.clearLocalDataAll(this); 

					// this.refreshChartSeries(this); 
				} 
				gl.tracex(v, String.format( 
						"%s...Just moving or click...id...%d", gl.S_OK, 
						this.getId())); 

				return; 
			} 

			if (this.getManager().getSelected().size() != gl.E_EMPTY) { 
				this.getManager().getSelected().getData().forEach(c -> { 
					c.setPid(p_id[0]); 
				}); 

				gl.tracex(v, String.format( 
						"Landing to parent set of object...%04d...pid...%d", 
						this.getManager().getSelected().size(), p_id[0])); 
			} else { 

				gl.tracex( 
						v, 
						String.format( 
								"Landing to parent alone ...Ok...pid...%d...this_id...%d", 
								p_id[0], this.getId())); 

				if (this.isFreeze()) { 
					gl.tracex( 
							v, 
							String.format("Reject landing because owner had a freeze state.")); 

				} else { 
					this.setPid(p_id[0]); 
				} 
			} 

		} // After dragged. 

		this.setStartPos(null); 

		this.setDx(gl.E_EMPTY); 

		this.setDy(gl.E_EMPTY); 

		if (this.getBounds() != null) 
			this.setPreferredSize(new Dimension(this.getBounds().width, this 
					.getBounds().height)); 

		this.setBl_mouse_dragged(false); 

	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 

		Object v = new Object() { 
		}; 

		if (!addRectComponents()) 
			gl.tracex(new Object() { 
			}, String.format("%s...add ctrl rects...%d...id...%d", gl.S_ERROR, 
					rects.size(), this.getId())); 

		requestFocus(); 

		this.setAt_selected(true); 

		TFrame desk = null; 

		if (this.getManager() != null) { 
			desk = this.getManager().getFrame(); 

			desk.setEntered(this); 
		} 

		// Old style. 

		if (desk != null && desk.isBl_desk_top_image()) { 
			if (this.getImage() != null) { 

				// Reload image . 
				TPanel tmp = TPanel.getInstance(new Rectangle(0, 0, 32, 32)); 

				tmp.setImg_desc(this.getImg_desc()); 

				if (!Bau.fit_to_img(tmp)) { 
					gl.tracex( 
							v, 
							String.format("Export to preview ...%s...%s", 
									tmp.getImg_desc(), "Error")); 
				} else { 
					gl.tracex( 
							v, 
							String.format("Export to preview ...%s...%s", 
									tmp.getImg_desc(), "Ok")); 

					desk.setDesktopImage(tmp.getImage()); 

					tmp = null; 

					desk.repaint(); 
				} 

			} 
		} // if desk_top_image 

		// New style. 

		// Find for receivers. 

		if (this.getAction() != null 
				&& this.getAction().equalsIgnoreCase("imageSender")) { 

			List<TPanel> mr = TMirror.update(this); 

			this.setMirrors(mr); 

		} 

	} 

	@Override 
	public void mouseExited(MouseEvent e) { 

		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 

		this.setAt_selected(false); 

		TFrame desk = null; 

		if (this.getManager() != null) 
			desk = this.getManager().getFrame(); 

		if (desk != null && desk.isBl_desk_top_image()) { 

			desk.setDesktopImage(null); 

			desk.repaint(); 

			desk.setEntered(null); 

		} 

		// Post action. 

		if (this.getAction() != null 
				&& this.getAction().equalsIgnoreCase("imageSender")) { 

			TMirror.clear(this); 

			this.setMirrors(null); 

		} 

	} 

	public void updateOriginalBounds() { 
		this.setOriginal_bounds(this.getBounds()); 
	} 

	@Override 
	public void componentResized(ComponentEvent e) { 

		Object v = new Object() { 
		}; 

		String msg = "Component resized"; 

		if (!addRectComponents()) { 
			gl.tracex(new Object() { 
			}, 
					String.format("%s...%s...%d...id...%d", msg, 
							"add rects components", "Error", rects.size(), 
							this.getId())); 

			return; 
		} 

		if (this.getOriginal_bounds() == null) { 
			this.setOriginal_bounds(this.getBounds()); 
		} 

		if (this.getManager() != null) { 

			Vector<TPanel> childs_reset = this.getChildsAll(this.getId()); 

			// Reset accept flag. 

			childs_reset.forEach(cr -> { 

				cr.setBl_accept_area(false); 
			}); 

			List<TPanel> childs = this.getChildsSorted(this.getId()); 

			childs.forEach( 

			a -> { 
				a.setParent_rect(this.getBounds()); 

				a.setParent_original_rect(this.getOriginal_bounds()); 

				a.getArea_manager().setArea( 
						new Dimension(this.getBounds().width, 
								this.getBounds().height)); 

				a.acceptArea(); 

			}); 

			// Set image flag. 

			if (this.getImage() != null) { 
				Dimension dim = new Dimension(this.getBounds().width, 
						this.getBounds().height); 

				this.setAli(SDimension.equalByAli(this.getImg_size(), dim)); 

				String s_ali = ""; 

				if (this.getAli() == gl.ALI.LEFT) 
					s_ali = "image are shrinking"; 
				else if (this.getAli() == gl.ALI.RIGHT) 
					s_ali = "image are stretching"; 
				else 
					s_ali = "image size equals to component area"; 

			} 
		} 

		if (this.getFunction().equalsIgnoreCase("TCubic") 
				|| this.getFunction().equalsIgnoreCase("TQuad")) { 
			initCubicSuite(this.getCubics()); 

		} 

	} 

	public boolean initCubicSuite(Map<String, List<TCubic>> items) { 

		boolean bl_result = initCubics(this.getFunction(), items); 

		if (bl_result) 
			gl.tracex(new Object() { 
			}, String.format("%s...Cubics initialization", gl.S_OK)); 
		else 
			gl.tracex(new Object() { 
			}, String.format("%s...Cubics initialization", gl.S_ERROR)); 

		return bl_result; 

	} 

	@Override 
	public void componentMoved(ComponentEvent e) { 

	} 

	@Override 
	public void componentShown(ComponentEvent e) { 

		//gl.smn("Component shown..." + this.getName()); 

	} 

	@Override 
	public void componentHidden(ComponentEvent e) { 

	} 

	public boolean addRectComponents() { 

		Rectangle rect = this.getBounds(); 

		if (rect == null) 
			return true; 

		int OFF = TPanel.MW; 

		rects.clear(); 

		int w = rect.width; 

		int h = rect.height; 

		// 1 
		Ru rec_north_west = new Ru(new Rectangle(0, 0, OFF, OFF), 

		Cursor.NW_RESIZE_CURSOR); 

		// 2 
		Ru rec_north_east = new Ru(new Rectangle((w - OFF), 0, OFF, OFF), 

		Cursor.NE_RESIZE_CURSOR); 

		// 3 
		Ru rec_south_east = new Ru(new Rectangle((w - OFF), (h - OFF), 

		OFF, OFF), Cursor.SE_RESIZE_CURSOR); 

		// 4 
		Ru rec_south_west = new Ru(new Rectangle(0, (h - OFF), OFF, OFF), 

		Cursor.SW_RESIZE_CURSOR); 

		// 5 
		Ru rec_west = new Ru(new Rectangle(0, OFF, OFF, (h - OFF * 2)), 

		Cursor.W_RESIZE_CURSOR, Color.red); 

		// 6 
		Ru rec_north = new Ru(new Rectangle(OFF, 0, (w - OFF * 2), OFF), 

		Cursor.N_RESIZE_CURSOR, Color.red); 

		// 7 
		Ru rec_east = new Ru(new Rectangle((w - OFF), OFF, OFF, 

		(h - OFF * 2)), Cursor.E_RESIZE_CURSOR, Color.red); 

		// 8 
		Ru rec_south = new Ru(new Rectangle(OFF, h - OFF, (w - OFF * 2), 

		OFF), Cursor.S_RESIZE_CURSOR, Color.red); 

		// 9 
		Ru rec_body = new Ru(new Rectangle(OFF, OFF, (w - OFF * 2), 

		(h - OFF * 2)), Cursor.DEFAULT_CURSOR, Color.blue); 

		rects.add(rec_north_west, rec_north_east, rec_south_east, 

		rec_south_west, rec_west, rec_north, rec_east, rec_south, 

		rec_body); 

		return (rects.size() == 9); 

	} 

	public boolean clearRectComponents() { 

		if (rects != null) 
			rects.clear(); 

		return (rects.size() == gl.E_EMPTY); 

	} 

	public int getDragType() { 

		return dragType; 

	} 

	public void setDragType(int dragType) { 

		this.dragType = dragType; 

	} 

	public static void drawRotate(Graphics2D g2d, Color color, double x, 
			double y, int angle, String text) { 
		Color old_color = g2d.getColor(); 

		g2d.setColor(color); 

		g2d.translate((float) x, (float) y); 

		g2d.rotate(Math.toRadians(angle)); 

		g2d.drawString(text, 0, 0); 

		g2d.rotate(-Math.toRadians(angle)); 

		g2d.translate(-(float) x, -(float) y); 

		g2d.setColor(old_color); 

	} 

	public void drawRotateText(Graphics2D g2, Color color, String value, 
			Font font, float angle, int dx, int dy) { 

		Color old_color = g2.getColor(); 

		Font old_font = g2.getFont(); 

		AffineTransform affineTransform = new AffineTransform(); 

		affineTransform.rotate(Math.toRadians(angle), 0, 0); 

		Font rotatedFont = font.deriveFont(affineTransform); 

		g2.setFont(rotatedFont); 

		g2.setColor(color); 

		g2.drawString(value, dx, dy); 

		g2.setColor(old_color); 

		g2.setFont(old_font); 

		g2.dispose(); 

	} 

	@Override 
	public void paintComponent(Graphics g) { 

		super.paintComponent(g); 

		update(g); 

		List<TCubic> cubics = this.getCubics().get(this.getFunction()); 

		Graphics2D g2 = (Graphics2D) g; 

		Stroke prev_stroke = g2.getStroke(); 

		if (this.getFunction().equalsIgnoreCase("TCubic")) { 
			g2.setStroke(new BasicStroke(5.0f)); 

			cubics.forEach(c -> { 

				Paint prev_paint = g2.getPaint(); 

				Rectangle paint_rect = new Rectangle((int) c.getStart().getX(), 
						(int) c.getStart().getY(), (int) (c.getEnd().getX() - c 
								.getStart().getX()), 
						(int) (c.getCtrl().getY() - c.getStart().getY())); 

				Paint paint = GU.getPaint(g2, paint_rect, 
						CU.getAlphaColor(Color.blue, 125), 
						CU.getAlphaColor(Color.green, 125), gl.ALI.LEFT); 

				g2.setPaint(paint); 

				g2.draw(c.getCubic()); 

				g2.setPaint(prev_paint); 

				Color prev_color = g2.getColor(); 

				g2.setColor(Color.black); 

				g2.setStroke(gl.dashed_stroke_thin); 

				GU.drawAlphaLine(g2, PointUtil.toPoint(c.getCtrl()), 
						PointUtil.toPoint(c.getStart()), Color.white, 0.5f); 

				GU.drawAlphaLine(g2, PointUtil.toPoint(c.getEnd()), 
						PointUtil.toPoint(c.getCtrl_two()), Color.white, 0.5f); 

				g2.setColor(prev_color); 

				g2.setStroke(prev_stroke); 

			}); 

		} else if (this.getFunction().equalsIgnoreCase("TQuad")) { 
			cubics.forEach(c -> { 

				Paint prev_paint = g2.getPaint(); 

				Color prev_color = g2.getColor(); 

				g2.setStroke(new BasicStroke(5.0f)); 

				Rectangle paint_rect = new Rectangle((int) c.getStart().getX(), 
						(int) c.getStart().getY(), (int) (c.getEnd().getX() - c 
								.getStart().getX()), 
						(int) (c.getCtrl().getY() - c.getStart().getY())); 

				Paint paint = GU.getPaint(g2, paint_rect, 
						CU.getAlphaColor(Color.blue, 125), 
						CU.getAlphaColor(Color.green, 125), gl.ALI.LEFT); 

				g2.setPaint(paint); 

				g2.draw(c.getQuad()); 

				g2.setStroke(gl.dashed_stroke_thin); 

				GU.drawAlphaLine(g2, PointUtil.toPoint(c.getCtrl()), 
						PointUtil.toPoint(c.getStart()), Color.white, 0.5f); 

				GU.drawAlphaLine(g2, PointUtil.toPoint(c.getEnd()), 
						PointUtil.toPoint(c.getCtrl()), Color.white, 0.5f); 

				g2.setPaint(prev_paint); 

				g2.setStroke(prev_stroke); 

				g2.setColor(prev_color); 

			}); 

		} 

	} 

	public void updateGrid(Graphics g) { 

		if (!this.isBlGrid()) 
			return; 

		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		AreaManager grid_am = new AreaManager( 
				new Dimension(wr.width, wr.height), this.getChild_dim()); 

		grid_am.getGridLinesV().forEach(a -> { 

			GU.drawAlphaLine(g2, a, this.getGrid_color(), 0.8f); 

		}); 

		grid_am.getGridLinesH().forEach(a -> { 

			GU.drawAlphaLine(g2, a, this.getGrid_color(), 0.8f); 

		}); 

	} 

	public boolean updateVText(Graphics2D g2, Rectangle wr, Dimension font_dim) { 

		if (this.getArea_manager() != null 
				&& 

				(( 

				this.getArea_manager().getDistributionType() == gl.ALI.GLUED_LEFT_SYNC 
						|| this.getArea_manager().getDistributionType() == gl.ALI.GLUED_RIGHT_SYNC 
						|| this.getArea_manager().getDistributionType() == gl.ALI.LEFT || this 
						.getArea_manager().getDistributionType() == gl.ALI.RIGHT 

				) && font_dim.width > wr.width)) { 

			int chk_y = (wr.height - font_dim.width); 

			if (chk_y < gl.E_EMPTY) { 
				chk_y = gl.E_EMPTY; 
			} 

			this.drawRotateText(g2, this.getText_color(), this.getText(), 
					this.getFont(), 90.0f, wr.width / 2 - font_dim.height / 2, 
					wr.y + chk_y / 2); 

			return true; 
		} else 
			return false; 

	} 

	public void updateTextSuite(Graphics g) { 
		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		Dimension font_dim = gl.getFontDim(g2, this.getFont(), this.getText()); 

		updateText(g2, wr, font_dim); 

	} 

	public void updateText(Graphics2D g2, Rectangle wr, Dimension font_dim) { 

		if (this.getBounds() == null) { 
			return; 
		} 

		if (this.isSkip_draw_text()) { 
			return; 
		} 

		Rectangle text_align = this.getAlign(); 

		if (!Ru.isInit(text_align)) 
			text_align = new Rectangle(1, 1, 1, 1); 

		AreaManager am = new AreaManager(new Dimension(wr.width, wr.height), 
				new Dimension(3, 3)); 

		Rectangle am_rect = am.get(new Dimension(text_align.x, text_align.y)); 

		Rectangle text_rect = null; 

		AreaManager am_intro = new AreaManager(new Dimension(am_rect.width, 
				am_rect.height), new Dimension(3, 3)); 

		text_rect = am_intro.get(new Dimension(text_align.width, 
				text_align.height)); 

		if (font_dim.width > text_rect.width) { 
			text_rect = am_rect; 
		} else { 
			text_rect.x += am_rect.x; 

			text_rect.y += am_rect.y; 
		} 

		// Debug rect. 
		// GraphicsUtil.drawAlphaRect(g2,text_rect,Color.white); 

		GU.drawAlphaString(g2, this.getText(), text_rect.x 
				+ (text_rect.width - font_dim.width) / 2, 
				(text_rect.y + text_rect.height / 2) + font_dim.height / 2, 
				this.getText_color(), this.getFont()); 

	} 

	public void update(Graphics g) { 

		if (this.getBounds() == null) { 
			return; 
		} 

		Graphics2D g2 = (Graphics2D) g; 

		Rectangle wr = Ru.norm4g(this.getBounds()); 

		Dimension font_dim = gl.getFontDim(g2, this.getFont(), this.getText()); 

		// Body 
		GU.fillAlphaRect(g2, wr, this.getBackground(), 1.0f); 

		if (this.getImage() != null && !this.isBl_skip_draw_image()) { 
			GU.drawImage(g2, this.getImage(), wr); 
		} 

		if (!updateVText(g2, wr, font_dim)) 
			updateText(g2, wr, font_dim); 

		updateGrid(g); 

		GU.drawGradient(g2, wr, Ru.Scale(wr, new Dimension(0, 0)), 
				this.getGradient(), 
				CU.getAlphaColor(this.getGradient(), 0), 
				this.getGradient_type()); 

		/* 
		 * if (this.isSelected()) { if (!this.isSkipSelected()) { 
		 * 
		 * GraphicsUtil.drawGradientEx(g2, wr, ARect.Scale(wr, new Dimension(0, 
		 * 0)), ColorUtil.getAlphaColor(Color.black, 70), 
		 * ColorUtil.getAlphaColor(Color.black, 0)); 
		 * 
		 * 
		 * } } 
		 */ 

		// Select suite. 
		if (this.isSelected()) { 
			if (!this.isSkipSelected()) { 

				Rectangle spanned = Ru.get_spanned_rect(wr, new Insets(4, 4, 
						8, 8)); 

				Stroke prev_stroke = g2.getStroke(); 

				g2.setStroke(gl.dashed_stroke_thin); 

				GU.drawAlphaRect(g2, spanned, Color.black, 0.7f); 

				g2.setStroke(prev_stroke); 

			} 
		} 

	} 

	public void translate(TPanel obj, int dx, int dy) { 

		Rectangle b = obj.getBounds(); 

		b.translate(dx, dy); 

		obj.setBounds(b); 

	} 

	public Vector<TPanel> moveChilds(TPanel owner, int pdx, int pdy) { 

		Vector<TPanel> childs = this.getChildsAll(owner.getId()); 

		if (childs != null) { 
			childs.forEach(a -> { 

				Rectangle b = a.getBounds(); 

				b.translate(pdx, pdy); 

				a.setBounds(b); 

			} 

			); 
		} 

		return childs; 
	} 

	public void moveSelected(SPanelCollector jpc, int pdx, int pdy) { 

		Vector<TPanel> childs = this.getChildsAll(this.getId()); 

		if (childs != null) { 
			childs.forEach(a -> { 

				Rectangle b = a.getBounds(); 

				b.translate(pdx, pdy); 

				a.setBounds(b); 

			} 

			); 
		} 
	} 

	public TPanel getTarget() { 
		return target; 
	} 

	public void setTarget(TPanel target) { 
		this.target = target; 
	} 

	public void getTarget(SPanelCollector jpc) { 

		ap.vec.SVector<TPanel> ve = jpc.getVariants(this); 

		if (ve != null && ve.size() != gl.E_EMPTY) { 

			if (ve.size() >= gl.E_OK) { 
				target = ve.getMinArea(); 
			} else { 
				target = null; 
			} 
		} else { 
			target = null; 
		} 
	} 

	public boolean delete() { 
		Object value = new Object() { 
		}; 

		gl.tracex(value, 
				String.format("Try to delete object id: %d ", this.getId())); 

		if (!this.isSelected()) { 
			gl.tracex( 
					value, 
					String.format("Object is not selected...%d.... %s ", 
							this.getId(), "Error")); 

			return false; 

		} 

		if (!this.setDeleted(true)) { 
			gl.tracex( 
					value, 
					String.format("Try to delete object id: %d.... %s ", 
							this.getId(), "Error")); 

			return false; 
		} 

		return (this.isDeleted()); 

	} 

	public TPanel readBin(String path) { 

		Object v = new Object() { 
		}; 

		String msg = String.format("Try to read bin...%s...%s", path, ""); 

		String objectStore = path; 

		ObjectInputStream objectInputStream = null; 

		TPanel result_object = null; 

		try { 
			objectInputStream = new ObjectInputStream(new FileInputStream( 
					objectStore)); 

			gl.tracex(v, String.format(msg, "create", "Ok")); 

		} catch (IOException e) { 

			gl.tracex(v, String.format(msg, "create", "Error :" + e.toString())); 

			return null; 
		} 

		try { 

			result_object = (TPanel) objectInputStream.readObject(); 

			gl.tracex(v, String.format(msg, "read", "Ok")); 

		} catch (ClassNotFoundException | IOException e) { 

			gl.tracex(v, 
					String.format(msg, "while read", "Error :" + e.toString())); 

			try { 
				objectInputStream.close(); 
			} catch (IOException e1) { 
				return null; 
			} 

			return null; 
		} 

		try { 

			objectInputStream.close(); 

		} catch (IOException e) { 

			gl.tracex(v, 
					String.format(msg, "while close", "Error :" + e.toString())); 

			return null; 
		} 

		return result_object; 
	} 

	public boolean writeBin(String path) { 

		ObjectOutputStream objectOutputStream = null; 

		try { 
			objectOutputStream = new ObjectOutputStream(new FileOutputStream( 
					path)); 

			objectOutputStream.writeObject(this); 

			return true; 

		} catch (IOException e) { 

			return false; 
		} finally { 
			try { 
				objectOutputStream.close(); 

			} catch (IOException e) { 

				return false; 
			} 

		} 

	} 

	public int getArea() { 
		return this.getBounds().width * this.getBounds().height; 
	} 

	@Override 
	public int compareTo(TPanel o) { 
		return this.getArea() > o.getArea() ? 1 
				: (this.getArea() < o.getArea() ? -1 : 0); 
	} 

	public boolean importImageDlg(TPanel owner, boolean fit_to_image) { 
		Object v = new Object() { 
		}; 

		String fabula = "Import BI Dlg"; 

		gl.tracex(v, String.format("%s...%s", fabula, "init")); 

		String fileName = owner.getManager().fileOpenDlg(); 

		if (fileName == null) { 
			gl.tracex(v, String.format("%s...%s", fabula, "not selected")); 

			return false; 
		} 

		return Bau.imp_buf_img_as_is(owner, fileName, fit_to_image); 
	} 

	public boolean clearImage(TPanel owner) { 

		String msg = "Clear image object"; 

		this.setImage(null); 

		if (this.getImage() != null) { 

			gl.tracex(new Object() { 
			}, gl.sf("%s...%s", gl.S_ERROR, msg, "error set to NULL.")); 

			return false; 
		} 

		gl.tracex(new Object() { 
		}, gl.sf("%s...%s", gl.S_OK, msg)); 

		this.repaint(); 

		return true; 
	} 

	public boolean importBI(TPanel owner, String path) { 
		Object v = new Object() { 
		}; 

		String fabula = "Import BI"; 

		gl.tracex(v, String.format("%s...%s", fabula, "init")); 

		if (!Fu.isaFile(path)) { 
			gl.tracex(v, String.format("%s...%s...%s...%s", fabula, 
					"not exist", "Error", path)); 

			return false; 
		} 

		String format = ImageUtil.get_bi_type(path); 

		if (format == null) { 
			format = Fu.get_file_extension(path); 

			if (format.equalsIgnoreCase("ico")) { 
				format = "gif"; 

				gl.tracex(v, String.format("%s...%s...%s", fabula, 
						"change image format ico->", format)); 
			} 
		} 

		gl.tracex(v, 
				String.format("%s...%s...%s", fabula, "set format", format)); 

		BufferedImage bi = Bau.to_buf_img_from_file(path); 

		if (bi != null) { 

			owner.setImg_type(format); 

			owner.setImage(bi); 

			owner.setImg_desc(String.format("%s", path)); 

			gl.tracex(v, String.format("%s...%s...%s", fabula, "get bi", "Ok")); 

			owner.repaint(); 

			return true; 

		} else { 
			gl.tracex(v, 
					String.format("%s...%s...%s", fabula, "get bi", "Error")); 

			return false; 
		} 

	} 

	public String getTypeUrl() { 
		return String.format("%s.%s", this.getType(), this.getFunction()); 
	} 

	public Rectangle narrowText() { 
		int x = this.getBounds().x; 

		int y = this.getBounds().y; 

		Rectangle rect = gl.getFontRect(this.getGraphics(), this.getFont(), 
				this.getText(), this.getBounds(), this.getAlign(), 
				new Dimension(0, 0)); 

		rect.x = x; 

		rect.y = y; 

		if (rect.height > this.getBounds().height) { 
			rect.height = this.getBounds().height; 
		} else if (rect.width > this.getBounds().width) { 
			rect.width = this.getBounds().width; 
		} 

		return rect; 
	} 

	public static List<TPanel> getInstances(Dimension dim, int count, 
			Border border) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TPanel sp = new TPanel(new Rectangle(gl.getRandomInt(128), 
					gl.getRandomInt(128), 32, 32), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(sp.getBackground()); 

			sp.setBorder(border); 

			arr.add(sp); 

		} 

		return arr; 
	} 

	public static TPanel getInstance(Rectangle rect) { 

		return new TPanel(rect, gl.getRandomColor()); 

	} 

	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TPanel sp = new TPanel(new Rectangle(gl.getRandomInt(128), 
					gl.getRandomInt(128), 64, 64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 

			sp.setFunction(sp.getClass().getSimpleName()); 

			sp.setData(String.format("%d.%d", gl.getRandomInt(10), 
					gl.getRandomInt(9999))); 

			arr.add(sp); 

		} 

		return arr; 
	} 

	public static List<TPanel> getInstances(Dimension dim, int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TPanel sp = new TPanel(new Rectangle(gl.getRandomInt(128), 
					gl.getRandomInt(128), 32, 32), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 

			arr.add(sp); 

		} 

		return arr; 
	} 

	public void stayOnTop() { 
		this.getManager().refreshZorder(this.getId()); 
	} 

	public Rectangle getFreaArea() { 
		List<TPanel> list = this.getManager().getCollector() 
				.get_list_by_pid(this.getPid(), this.getId()); 

		Rectangle parent_rect = this.getParent_rect(); 

		Rectangle result_rect = Ru.get_init_rect(gl.E_EMPTY); 

		int X_MIN = 0; 

		int X_MAX = 1; 

		int Y_MIN = 2; 

		int Y_MAX = 3; 

		int[] v = { parent_rect.x, (parent_rect.x + parent_rect.width), 
				parent_rect.y, (parent_rect.y + parent_rect.height) }; 

		list.forEach(a -> { 

			// Looking between TOP aligned 

			if (a.getArea_manager().getDistributionType() == gl.ALI.TOP) { 

				Rectangle top = a.getBounds(); 

				if ((top.y + top.height) > v[Y_MIN]) 
					v[Y_MIN] = (top.y + top.height); 

			} else if (a.getArea_manager().getDistributionType() == gl.ALI.LEFT) { 

				Rectangle top = a.getBounds(); 

				if ((top.x + top.width) > v[X_MIN]) 
					v[Y_MIN] = (top.x + top.width); 

			} else if (a.getArea_manager().getDistributionType() == gl.ALI.RIGHT) { 

				Rectangle top = a.getBounds(); 

				if (top.x < v[X_MAX]) 
					v[X_MAX] = top.x; 

			} else if (a.getArea_manager().getDistributionType() == gl.ALI.BOTTOM) { 

				Rectangle top = a.getBounds(); 

				if ((top.y + top.height) < v[Y_MAX]) 
					v[Y_MAX] = (top.y + top.height); 
			} 

		}); 

		result_rect.x = v[X_MIN]; 
		result_rect.y = v[Y_MIN]; 
		result_rect.width = v[X_MAX] - v[X_MIN]; 
		result_rect.height = v[Y_MAX] - v[Y_MIN]; 

		return result_rect; 
	} 
	
	public boolean chartStepSuite(TPanel owner, boolean direction, int step) { 

		TPanel[] grand = { owner }; 

		if (owner.getPid() != gl.E_ERROR) 
			grand[0] = this.getManager().getGrandForm(owner.getPid()); 

		boolean[] bl_result = { false }; 

		if (grand != null) { 
			((TChart) grand[0]) 
					.getBars() 
					.entrySet() 
					.stream() 
					.map(Map.Entry::getKey) 
					.collect(Collectors.toList()) 
					.forEach( 
							sr -> { 

								bl_result[0] = grand[0].chart_step_right( 
										grand[0], sr, direction, step); 

							}); 

		} 

					return bl_result[0]; 
	} 

	public boolean actionResolver(String action) { 
		return ActionHelper.execute(this, action); 
	} 

	public TPanel getParent(int id) { 
		return this.getManager().getCollector().get_by_id(id); 
	} 

	public void aliRight(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = (parent_rect.x + (parent_rect.width - client_area.width)) 
				+ shift.x; 

		double d_tmp = parent_rect.y + (int) (parent_rect.height * zoom_at_y); 

		area.y = (int) d_tmp; 

		double d_height = parent_rect.height * zoom_at_height; 

		area.width = client_area.width; 

		area.height = (int) d_height; 

		if (area.y < parent_rect.y) 
			area.y = parent_rect.y; 

		this.setBou_nds(area); 
	} 

	public void aliLeft(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = parent_rect.x + shift.x; 

		double d_tmp = parent_rect.y + (int) (parent_rect.height * zoom_at_y); 

		area.y = (int) d_tmp; 

		double d_height = parent_rect.height * zoom_at_height; 

		area.width = client_area.width; 

		area.height = (int) d_height; 

		if (area.y < parent_rect.y) 
			area.y = parent_rect.y; 

		this.setBou_nds(area); 

	} 

	public void aliGluedLeftSync(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = (parent_rect.x - client_area.width) + shift.x; 

		area.y = parent_rect.y + shift.y; 

		double d_width = parent_rect.width * zoom_at_width; 

		double d_height = parent_rect.height * zoom_at_height; 

		area.width = (int) d_width; 

		area.height = (int) d_height; 

		this.setBou_nds(area); 

	} 

	public void aliGluedRightSync(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = (parent_rect.x + parent_rect.width) + shift.x; 

		area.y = parent_rect.y + +shift.y; 

		double d_width = parent_rect.width * zoom_at_width; 

		double d_height = parent_rect.height * zoom_at_height; 

		area.width = (int) d_width; 

		area.height = (int) d_height; 

		this.setBou_nds(area); 

	} 

	public void aliGluedRight(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 

		Ru.clear(area); 

		area.x = (parent_rect.x + parent_rect.width) + shift.x; 

		area.y = parent_rect.y + shift.y; 

		area.width = client_area.width; 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

	public void aliGluedLeft(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = (parent_rect.x - client_area.width) + shift.x; 

		area.y = parent_rect.y + shift.y; 

		area.width = client_area.width; 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

	public void aliTopSync(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 

		Ru.clear(area); 

		area.x = parent_rect.x + shift.x; 

		area.y = parent_rect.y + shift.y; 

		area.width = parent_rect.width + shift.width; 

		double d_height = parent_rect.height * zoom_at_height; 

		area.height = (int) d_height; 

		this.setBou_nds(area); 

	} 

	public void aliGluedTop(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 

		// The same width with parent. 
		// Height will be native. 

		Ru.clear(area); 

		area.x = parent_rect.x + shift.x; 

		area.y = (parent_rect.y - client_area.height) + shift.y; 

		area.width = parent_rect.width + shift.width; 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

	public void aliTop(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 

		Ru.clear(area); 

		area.x = (int) parent_rect.x + (int) (parent_rect.width * zoom_at_x) 
				+ shift.x; 
		; 

		area.y = parent_rect.y + shift.y; 

		double d_width = parent_rect.width * zoom_at_width + shift.width; 

		area.width = (int) d_width; 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

	public void aliGluedBottom(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 

		Ru.clear(area); 

		area.x = parent_rect.x + shift.x; 

		area.y = (parent_rect.y + parent_rect.height) + shift.y; 

		area.width = parent_rect.width + shift.width; 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

	public void aliBottom(Rectangle area, Rectangle parent_rect, 
			Rectangle client_area, Rectangle shift) { 
		Ru.clear(area); 

		area.x = (int) parent_rect.x + (int) (parent_rect.width * zoom_at_x) 
				+ shift.x; 
		; 

		area.y = ((parent_rect.y + parent_rect.height) - client_area.height) 
				+ shift.y; 

		area.width = (int) (parent_rect.width * zoom_at_width + shift.width); 

		area.height = client_area.height; 

		this.setBou_nds(area); 

	} 

} 
