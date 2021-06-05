 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Color; 
import java.awt.Component; 
import java.awt.Container; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.image.BufferedImage; 
import java.util.Vector; 

import javax.swing.JPanel; 
import javax.swing.JRootPane; 
import javax.swing.SwingUtilities; 



public class APanel extends JPanel implements MouseListener,MouseMotionListener{ 

	public int index = gl.E_ERROR; 
	 
	public int target = gl.E_ERROR; 
	 
	public boolean captured = false; 
	 
	public Rectangle Area = new Rectangle(0,0,0,0); 
	 
	public Rectangle OriginArea = new Rectangle(0,0,0,0); 
	 
	public static Vector<APanel> list = null; 
	 
	public Point dragStart ; 
	 
	public String ToolTipHeader = " Index "; 
	 
	 
	public APanel() 
	{ 
		addMouseListener(this); 
		 
		addMouseMotionListener(this); 
		 
	} 
		 
	 
	public void mouseEntered(MouseEvent e) { 
		 
		 
	} 

	public void mouseExited(MouseEvent e) { 
		 
	} 
	public void mousePressed(MouseEvent e) { 
		 
	} 
	public void mouseReleased(MouseEvent e) { 
		 
//		 
//		if(this.isCaptured()) 
//		{ 
//				if(this.getIndex() != target && target != gl.E_ERROR) 
//				{ 
//					if(TestDialog.swap(target,this.getIndex())) 
//					{ 
//						gl.smn(" Dropped Ok captured index : " + this.getIndex() + " Target index : " + target); 
//						 
//						TestDialog.mv[target] = this.getIndex(); 
//						 
//						TestDialog.mv[this.getIndex()] = target; 
//						 
//						this.lookup(); 
//						 
//						for(int i=0;i < TestDialog.mv.length;i++) 
//						{ 
//							if(i < 10) 
//							  gl.sm("0"); 
//							 
//							if((i+1)%TestDialog.dim.getWidth()==0) 
//								gl.sm(TestDialog.mv[i]+" "+"\n"); 
//							else 
//								gl.sm(TestDialog.mv[i]+" "); 
//								 
//						} 
//					 
//					} 
//					else 
//						gl.smn(" Dropped Error captured index : " + this.getIndex() + " Target index : " + target); 
//						 
//					 
//				} 
//				else if(target == gl.E_ERROR) 
//				{ 
//					gl.smn(" Equals  captured index : " + this.getIndex() + " Target index : " + target); 
//					 
//					//this.setBounds(this.Area); 
//					 
//					this.setBounds(this.OriginArea); 
//					 
//				} 
//			 
//					this.setCaptured(false); 
//		 
//					TestDialog.capturedIndex = gl.E_ERROR; 
//				 
//		} 
	} 

	public void mouseClicked(MouseEvent e) { 
	 
	} 
	 
	 
	public void mouseDragged(MouseEvent e) { 
		 
		if(!this.isCaptured()) 
		{ 
			this.setCaptured(true); 
			 
			this.setFocusable(true); 
			 
			this.dragStart = e.getPoint(); 
			 
			this.getBounds(this.Area); 
			 
			this.getBounds(this.OriginArea); 
			 
			//TestDialog.capturedIndex = this.getIndex(); 
			 
			((Container)this.getParent()).setComponentZOrder(this,0); 
			 
		} 
		 
		if(this.isCaptured()) 
		{ 
			 
			Point point = new Point(this.getX() + (e.getX() - this.dragStart.x),this.getY() + (e.getY() - this.dragStart.y )); 
			 
			Point target_point = new Point(this.getX() + (e.getX() - this.dragStart.x) + this.getWidth()/2,this.getY() + (e.getY() - this.dragStart.y ) + this.getHeight()/2); 
			 
			this.setLocation(point); 
			 
			//target = TestDialog.getTargetIndex(this.getIndex(),target_point); 
					 
			//gl.smn(" Captured index : " + this.getIndex() + " Target index : " + target); 
			 
			 
		} 
		 
	} 
	 
	public void mouseMoved(MouseEvent e) { 
		 
			 
	} 

	public boolean isCaptured() { 
		return captured; 
	} 

	public void setCaptured(boolean captured) { 
		this.captured = captured; 
	} 

	public int getIndex() { 
		return index; 
	} 

	public void setIndex(int index) { 
		this.index = index; 
	} 

		 
	public void paintComponent(Graphics g) 
	{ 
		//super.paintComponent(g); 
		 
		//gl.drawSphere((Graphics2D)g,this.OriginArea.width-1,OriginArea.height-1,super.getBackground()); 
		 
	} 
	 
	public boolean lookup() 
	{ 
		 
		if (list == null) 
			list = new Vector<APanel>(); 
		 
		// Our location 
		//int row = (this.getIndex() / (int)EA.td.dim.getWidth()) - 1; 
		 
		//int col = this.getIndex() - (int)((row + 1) * EA.td.dim.getWidth()); 

		//gl.smn("<lookup> " + row + ","+ (col -1)); 
				 
		return false; 
		 
	} 

	public String getToolTipHeader() { 
		return ToolTipHeader; 
	} 


	public void setToolTipHeader(String toolTipHeader) { 
		ToolTipHeader = toolTipHeader; 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
