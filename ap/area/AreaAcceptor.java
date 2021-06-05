 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.area; 

import java.awt.Rectangle; 
import java.awt.geom.Rectangle2D; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 
import ap.prop.SBounds; 
import ap.shape.Ru; 


public class AreaAcceptor { 

	private List<Double> width_acceptors = new ArrayList<Double>(); 
	 
	private List<Double> height_acceptors = new ArrayList<Double>(); 
	 
	private AcceptorPair area; 
	 
	private AcceptorPair dimension; 
		 
	 
	 
	 
	public AcceptorPair getDimension() { 
		return dimension; 
	} 

	public void setDimension(AcceptorPair dimension) { 
		this.dimension = dimension; 
	} 

	public List<Double> getWidth_acceptors() { 
		return width_acceptors; 
	} 

	public void setWidth_acceptors(List<Double> width_acceptors) { 
		this.width_acceptors = width_acceptors; 
	} 

	public List<Double> getHeight_acceptors() { 
		return height_acceptors; 
	} 

	public void setHeight_acceptors(List<Double> height_acceptors) { 
		this.height_acceptors = height_acceptors; 
	} 

	public AcceptorPair getArea() { 
		return area; 
	} 

	public void setArea(AcceptorPair area) { 
		this.area = area; 
	} 

	 
	public AreaAcceptor() { 
		 
	} 
	 
	public AreaAcceptor(AcceptorPair area) { 
		 
		this.setArea(area); 
	} 
	 
	public AreaAcceptor(AcceptorPair area,List<Double> width_acceptors,List<Double> height_acceptors) { 
		 
		this.setArea(area); 
		 
		// Add an 0 element. 
		 
		List<Double> width_acceptors_update =  new ArrayList<Double>(); 
		 
		List<Double> height_acceptors_update =  new ArrayList<Double>(); 
		 
		 
		width_acceptors_update.add(new Double(0.0)); 
		 
		height_acceptors_update.add(new Double(0.0)); 
		 

		width_acceptors_update.addAll(width_acceptors); 
		 
		height_acceptors_update.addAll(height_acceptors); 
		 
				 
		this.setWidth_acceptors(width_acceptors_update); 
				 
		this.setHeight_acceptors(height_acceptors_update); 
				 
		 
		this.setDimension(new AcceptorPair(this.getWidth_acceptors().size(), 
					this.getHeight_acceptors().size() 
				)); 
	 
	} 
	 
	public  Rectangle getBounds(int x , int y) 
	{ 
		// Bounds check. 
		 
		int x_max_value = this.getWidth_acceptors().size(); 
		 
		int y_max_value = this.getHeight_acceptors().size(); 
		 
		if(x < gl.E_EMPTY || x > x_max_value) 
		{ 
			 
			gl.smn(String.format("Index out of bounds...%d...of...%d",x,x_max_value)); 
			 
			return null; 
		} 
		 
		if(y < gl.E_EMPTY || y > y_max_value) 
		{ 
			 
			gl.smn(String.format("Index out of bounds...%d...of...%d",y,y_max_value)); 
			 
			return null; 
		} 
		 
		 
		List<Double> x_coo =  new ArrayList<Double>(); 
		 
		List<Double> y_coo =  new ArrayList<Double>(); 
		 
		 
			 
		double acc_x = 0; 
		 
		for(int i=0;i<this.getWidth_acceptors().size();i++) 
		{ 
			 
			double x_acc = acc_x + (this.getWidth_acceptors().get(i)*this.getArea().getWidth_acceptor()); 
			 
			x_coo.add(x_acc); 
			 
			acc_x = x_acc; 
			 
		} 
		 
			x_coo.add(this.getArea().getWidth_acceptor()); 
		 
		 
		 
		double acc_y = 0; 
		 
		for(int i=0;i<this.getHeight_acceptors().size();i++) 
		{ 
			 
			double y_acc = acc_y + (this.getHeight_acceptors().get(i)*this.getArea().getHeight_acceptor()); 
			 
			y_coo.add(y_acc); 
			 
			acc_y = y_acc; 
			 
		} 
		 
			y_coo.add(this.getArea().getHeight_acceptor()); 
					 
			double xd = x_coo.get(x); 
			 
			double yd = y_coo.get(y); 
			 
			double wd = (x_coo.get(x+1)-x_coo.get(x)); 
			 
			double hd = (y_coo.get(y+1)-y_coo.get(y)); 
				 
			 
			 
			Rectangle r = new Rectangle( 
					 
					(int)xd, 
					(int)yd, 
					(int)wd, 
					(int)hd 
					 
					); 
		 
			 
			return r; 
	} 
	 
	/* 
	public double sum(List<Double> list, int index) 
	{ 
		double  result = gl.E_EMPTY; 
		 
		for(int i=0;i <= index;i++) 
			result += list.get(i); 
		 
		return result; 
		 
	} 
	 
	*/ 
	 
	 
	 
	public static void Test_main() 
	{ 
		List<Double>  width_acc = Arrays.asList(new Double[]{0.2,0.2,0.2,0.2}); 
		 
		//List<Double>  width_acc = Arrays.asList(new Double[]{}); 
		 
		List<Double>  height_acc = Arrays.asList(new Double[]{0.2,0.2,0.2,0.2}); 
		 
		//List<Double>  height_acc = Arrays.asList(new Double[]{}); 
		 
		AreaAcceptor aa = new AreaAcceptor(new AcceptorPair(100,100), width_acc, height_acc); 
		 
		 
		//gl.smn(String.format("Result...%s",SBounds.toString(rect))); 
		 
		for(int y=0;y< height_acc.size()+1;y++) 
			for(int x=0;x< width_acc.size()+1;x++) 
			{ 
				Rectangle rect = aa.getBounds(x,y); 
				 
				gl.smn(String.format("%d %d...%s",x,y,SBounds.toString(rect))); 
			} 
					 
		 
	} 
	 


	public static void main(String[] args) { 
		 
		Test_main(); 

	} 

} 
