 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Color; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.image.BufferedImage; 
import java.io.Serializable; 

import javax.swing.JPanel; 

import ap.area.AreaManager; 
import ap.collections.JKVector; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.prop.BaseProperty; 
import ap.prop.SAreaManager; 
import ap.prop.SBounds; 
import ap.prop.SColor; 
import ap.prop.SDimension; 
import ap.prop.SInt; 
import ap.prop.SText; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.utils.CU; 


public class TPanelDef extends JPanel implements Serializable { 
	 
	 
	public static final  int GLUE_SHADOW_INIT_SIZE  = 5; 
	 
	public static final  int GLUE_SHADOW_MIN_SIZE  = 2; 
			 
	private static final long serialVersionUID = 1L; 

	int Y = 0x0000; 
		 
	public static int speed = 5; 
		 	 
	int dragType = Cursor.DEFAULT_CURSOR; 

	public static int MW = 2; 
	 
	int deleted ; 
	 
	boolean at_selected = false; 
	 
	SPanelManager manager = null; 
		 
	JKVector<Ru> rects = new JKVector<Ru>(); 
	 
	Point startPos = null; 
	 
	TPanel target = null; 
	 
	private String payload; 
	 
	private int pid; 
	 
	private int gid; 
	 
	private int id; 
	 
	private int zorder; 
	 
	//private int debug; 
	 
	//private int selected; 
		 
	private Color background; 
	 
	private Color gradient; 
	 
	private Rectangle bounds; 
	 
	private Rectangle original_bounds; 
	 
	private Dimension child_dim; 
	 
	private AreaManager area_manager; 
	 
	private String type=""; 
	 
	private String text=""; 
	 
	private String tool_tip_text=""; 
	 
	private Font font; 
	 
	private Color text_color; 
	 
	private Color grid_color; 
	 
	private Color up_color; 
	 
	private Color down_color; 
	 
	private BufferedImage image; 
	 
	private String img_type=""; 
	 
	private String img_desc=""; 
	 
	private Dimension img_size; 
	 
	private int flags = 0x0000; 
	 
	private int base; 
	 
	private int offset; 
	 
	private Rectangle align; 
	 
	private String border=""; 
	 
	private String action=""; 
	 
	private String data="" ; 
	 
	private String function=""; 
	 
	private String name =""; 
	 
	private String model =""; 
		 
	 
	public String getModel() { 
		return model; 
	} 

	public void setModel(String model) { 
		this.model = model; 
	} 

	public int getBase() { 
		return base; 
	} 

	public void setBase(int base) { 
		this.base = base; 
	} 

	public int getOffset() { 
		return offset; 
	} 

	public void setOffset(int offset) { 
		this.offset = offset; 
	} 

	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 

	public String getTool_tip_text() { 
		return tool_tip_text; 
	} 

	public void setTool_tip_text(String tool_tip_text) { 
		this.tool_tip_text = tool_tip_text; 
	} 

	public Color getUp_color() { 
		return up_color; 
	} 

	public void setUp_color(Color up_color) { 
		this.up_color = up_color; 
	} 

	public Color getDown_color() { 
		return down_color; 
	} 

	public void setDown_color(Color down_color) { 
		this.down_color = down_color; 
	} 

	public Color getGrid_color() { 
		return grid_color; 
	} 

	public boolean setGrid_color(Color grid_color) { 
		this.grid_color = grid_color; 
		 
		return CU.equal(this.getGrid_color(),grid_color); 
		 
	} 

	public String getFunction() { 
		return function; 
	} 

	public void setFunction(String function) { 
		this.function = function; 
	} 

	public String getData() { 
		return data; 
	} 

	public void setData(String data) { 
		this.data = data; 
		 
	} 

	public String getAction() { 
		return action; 
	} 

	public void setAction(String action) { 
		this.action = action; 
	} 

	public String getBor_der() { 
		return border; 
	} 

	public void setBorder(String border) { 
		this.border = border; 
		 
		this.setBorder(gl.getBorderTypeByText(this.getBor_der())); 
		 
		 
	} 
	 

	 
	public Rectangle getAlign() { 
		return align; 
	} 

	public void setAlign(Rectangle align) { 
		this.align = align; 
	} 

	 
	public int getFlags() { 
		return flags; 
	} 
	 

	public void setFlags(int flags) { 
		this.flags = flags; 
	} 

	public Dimension getImg_size() { 
		return img_size; 
	} 

	public void setImg_size(Dimension img_size) { 
		this.img_size = img_size; 
	} 

	public String getImg_type() { 
		return img_type; 
	} 

	public void setImg_type(String img_type) { 
		this.img_type = img_type; 
	} 

	public String getImg_desc() { 
		return img_desc; 
	} 

	public void setImg_desc(String img_desc) { 
		this.img_desc = img_desc; 
	} 

	public BufferedImage getImage() { 
		return image; 
	} 

	public void setImage(BufferedImage image) { 
		this.image = image; 
		 
		if(image != null) 
		{ 
			this.setImg_size(new Dimension(image.getWidth(),image.getHeight())); 
			 
			 
		} 
		 
	} 

	public boolean isAt_selected() { 
		return at_selected; 
	} 

	public void setAt_selected(boolean at_selected) { 
		this.at_selected = at_selected; 
	} 

	public Color getText_color() { 
		return text_color; 
	} 

	public boolean setText_color(Color text_color) { 
		this.text_color = text_color; 
		 
		return CU.equal(this.getText_color(),text_color); 
	} 

	public String getText() { 
		return text; 
	} 

	public void setText(String text) { 
		this.text = text; 
	} 

	public Font getFont() { 
		return font; 
	} 

	public void setFont(Font font) { 
		this.font = font; 
	} 

	public int getDeleted() { 
		return deleted; 
	} 

	public boolean isDeleted() { 
		return (deleted==gl.E_OK); 
	} 


	public boolean setDeleted(boolean deleted) { 
		 
		this.deleted = (deleted) ? gl.E_OK : gl.E_EMPTY; 
		 
		return (this.isDeleted()==deleted); 
		 
	} 



	public SPanelManager getManager() { 
		return manager; 
	} 





	public void setManager(SPanelManager manager) { 
		this.manager = manager; 
	} 





	public JKVector<Ru> getRects() { 
		return rects; 
	} 





	public void setRects(JKVector<Ru> rects) { 
		this.rects = rects; 
	} 





	public Point getStartPos() { 
		return startPos; 
	} 





	public void setStartPos(Point startPos) { 
		this.startPos = startPos; 
		 
		 
	} 





	public TPanel getTarget() { 
		return target; 
	} 





	public void setTarget(TPanel target) { 
		this.target = target; 
	} 




	public String getPayload() { 
		return payload; 
	} 





	public void setPayload(String payload) { 
		this.payload = payload; 
	} 





	public int getPid() { 
		return pid; 
	} 





	public boolean setPid(int pid) { 
		this.pid = pid; 
		 
		return (this.pid == pid); 
	} 





	public int getGid() { 
		return gid; 
	} 





	public void setGid(int gid) { 
		this.gid = gid; 
	} 





	public int getId() { 
		return id; 
	} 





	public boolean setId(int id) { 
		this.id = id; 
		 
		return (this.id == id); 
	} 





	public int getZorder() { 
		return zorder; 
	} 





	public boolean setZorder(int zorder) { 
		this.zorder = zorder; 
		 
		return (this.getZorder() == zorder); 
	} 

	public void setDeleted(int deleted) { 
		this.deleted = deleted; 
	} 


	public Color getBackground() { 
		return background; 
	} 


	public boolean setBack_ground(Color background) { 
			 
		this.background = background; 

		this.setBackground(background); 

		return CU.equal(this.getBackground(), background); 
		 
	} 





	public Color getGradient() { 
		return gradient; 
	} 





	public boolean setGradient(Color gradient) { 
		this.gradient = gradient; 
		 
		return CU.equal(this.getGradient(),gradient); 
		 
	} 





	public Rectangle getBounds() { 
		return bounds; 
	} 





	public void setBou_nds(Rectangle bounds) { 
		 
		this.bounds = bounds; 
		 
		if(bounds.height <= TPanel.MW*4) 
			bounds.height = TPanel.MW*4; 
		 
		if(bounds.width <= TPanel.MW*4) 
			bounds.width = TPanel.MW*4; 
		 
		this.setBounds(bounds); 
		 
		 
	} 





	public Rectangle getOriginal_bounds() { 
		return original_bounds; 
	} 





	public boolean setOriginal_bounds(Rectangle original_bounds) { 
		this.original_bounds = original_bounds; 
		 
		return SBounds.equal(this.getOriginal_bounds(),original_bounds); 
	} 


	public Dimension getChild_dim() { 
		return child_dim; 
	} 





	public boolean setChild_dim(Dimension child_dim) { 
		 
		/* 
		if(! 
		  (child_dim.width>100  || child_dim.width < gl.E_EMPTY) || 
		  (child_dim.height>100 || child_dim.height < gl.E_EMPTY) 
		  ) 
		{ 
		*/ 
		 
		this.child_dim = child_dim; 
				 
		return SDimension.equal(this.getChild_dim(),child_dim); 
		 
	} 





	public AreaManager getArea_manager() { 
		return area_manager; 
	} 





	public boolean setArea_manager(AreaManager area_manager) { 
		this.area_manager = area_manager; 
		 
		return SAreaManager.equal(this.getArea_manager(),area_manager); 
		 
	} 

	public String getType() { 
		return type; 
	} 


	public boolean setType(String type) { 
		this.type = type; 
		 
		return this.getType().equalsIgnoreCase(type); 
	} 

	public static void main(String[] args) { 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:14 
