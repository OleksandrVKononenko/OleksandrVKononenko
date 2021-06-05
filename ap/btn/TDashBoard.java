 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.awt.event.MouseEvent; 
import java.util.ArrayList; 
import java.util.List; 

import javax.swing.UIManager; 



import ap.area.AreaManager; 
import ap.config.TConfig; 
import ap.global.gl; 
import ap.prop.BaseProperty; 
import ap.prop.SBounds; 
import ap.shape.Ru; 
import ap.shape.TRectangle; 
import ap.utils.CU; 
import ap.utils.GU; 

@SuppressWarnings("serial") 
public class TDashBoard extends TPanel { 
	 
	 
	private List<String> messages = new ArrayList<String>(); 
	 
	private List<TRectangle> areas = new ArrayList<TRectangle>(); 
	 

	public List<TRectangle> getAreas() { 
		return areas; 
	} 

	public void setAreas(List<TRectangle> areas) { 
		this.areas = areas; 
	} 

	public List<String> getMessages() { 
		return messages; 
	} 

	public void setMessages(List<String> messages) { 
		this.messages = messages; 
	} 

	public TDashBoard() { 
		 
	} 

	public TDashBoard(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TDashBoard(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TDashBoard(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 

	public TDashBoard(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TDashBoard(String payload) { 
		super(payload); 
		 
	} 

	public TDashBoard(BaseProperty prop) { 
		super(prop); 
		 
	} 
	 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		for (int i = 0; i < count; i++) { 

			TDashBoard sp = new TDashBoard(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128), 64,64), i); 

			sp.setBack_ground(UIManager.getColor("Panel.background")); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setFunction(sp.getClass().getSimpleName()); 
			 
			 
			sp.add("First"); 
			 
			sp.add("Second"); 
			 
			sp.add("Third"); 
			 
			 
			arr.add(sp); 

		} 

		return arr; 
	} 

	 
	 
	@Override 
	public void componentResized(ComponentEvent e) { 
		 
		super.componentResized(e); 
		 
		acceptAdd(); 
	} 

	@Override 
	public boolean acceptArea() { 
		super.acceptArea(); 
		 
		this.acceptAdd(); 
		 
		return true; 
	} 
	 
	private void add(String message) 
	{ 
		this.getMessages().add(message); 
		 
	} 
	 
	private void acceptAdd() 
	{ 
		Rectangle bounds = this.getBounds(); 
		 
		bounds = Ru.norm4g(bounds); 
		 
		 
		Dimension area  = new Dimension((int)bounds.getWidth(),(int)bounds.getHeight()); 
		 
		Dimension cells = new Dimension(1,this.getMessages().size()); 
		 
		Dimension size  = new Dimension(gl.E_OK,gl.E_OK); 
		 
		Dimension index = new Dimension(gl.E_EMPTY,gl.E_EMPTY); 
		 
		AreaManager am =  new AreaManager(area,cells,size,index); 
		 
		// Just test. 
		this.getAreas().clear(); 
		 
		 
		for(int j=0;j<am.getAreaInCells().height;j++) 
			for(int i=0;i<am.getAreaInCells().width;i++) 
			{ 
				 
				Rectangle rect = am.get(i,j); 
				 
				TRectangle tr = new TRectangle(rect,Color.gray,this.getMessages().get(j)); 
											 
				this.getAreas().add(tr); 
				 
			} 
		 
			this.mouseReleased(null); 
		} 
	 
	@Override 
	public void paintComponent(Graphics g) { 

		super.paintComponent(g); 
		 
		Graphics2D g2 = (Graphics2D) g; 

	    g2.setStroke(new BasicStroke(1.0f)); 

	    this.getAreas().forEach( 
	  		r->{ 
	    			GU.fill3DRect(g2,r); 
	    			 
	    			Dimension dim =  gl.getFontDim(g2,this.getFont(),r.getText()); 
	   			 
	   			 	int ty = (r.y + r.height/2) + dim.height/2; 
	   	 
	   			 	int tx = (r.x + r.width/2) - dim.width/2; 
	   			 	 
	   			 	GU.drawAlphaString(g2,r.getText(),tx,ty,Color.black,1.0f); 
	   			 	   			 	 
	  				 
	  		}); 
	 	 
	} 
	 
	@Override 
	public void mouseReleased(MouseEvent e) { 
	 
	 int delta = this.getBounds().height - ( this.getAreas().get(gl.E_EMPTY).getBounds().height * this.getAreas().size()); 
	 
	 
	 Rectangle target = this.getBounds(); 
	 
			  target.height -= delta; 
	 
			  this.setBou_nds(target); 
	 
	} 
	 
	@Override 
	public void mouseClicked(MouseEvent e) { 
		 
		super.mouseClicked(e); 
		 
		this.getAreas().forEach( 
		  		r->{ 
		  			 
		  			if ( r.contains(e.getPoint())) 
		  				 r.setRaised(!r.isRaised()); 
		  			 
		  		}); 
	} 
	 
	public static void startUp() { 
		 
		List<TPanel> list = TDashBoard.getInstances(gl.E_OK); 
		 
		list.add(TBtn.getInstance(gl.E_EMPTY)); 
		 
		list.add(TPushBtn.getInstance(gl.E_EMPTY)); 
		 
		TConfig.start(list); 

	} 
	 
	public static void main(String[] args) { 
		 
				startUp(); 
		 
} 

} 
