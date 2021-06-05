 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.area; 

import java.util.Locale; 

import ap.global.gl; 

public class AcceptorPair { 

	private  double  width_acceptor; 
	 
	private  double  height_acceptor; 
	 
		 
	public double getWidth_acceptor() { 
		return width_acceptor; 
	} 

	public void setWidth_acceptor(double width_acceptor) { 
		this.width_acceptor = width_acceptor; 
	} 

	public double getHeight_acceptor() { 
		return height_acceptor; 
	} 

	public void setHeight_acceptor(double height_acceptor) { 
		this.height_acceptor = height_acceptor; 
	} 

	public AcceptorPair() { 
		 
	} 
	 
	public AcceptorPair(double width,double height) { 
		 
		this.setWidth_acceptor(width); 
		 
		this.setHeight_acceptor(height); 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format("%.4f , %.4f",this.getWidth_acceptor(),this.getHeight_acceptor()); 
	} 
	 
	public static void Test_dtor(double width,double height) 
	{ 
		AcceptorPair ap = new AcceptorPair(width, height); 
		 
		gl.smn(String.format(" Input width : %.4f height : %.4f Result : %s ",width,height,ap.toString())); 
		 
	} 
	 
	 

	public static void main(String[] args) { 
		 
		Test_dtor(0.4344,0.5544); 

	} 

} 
