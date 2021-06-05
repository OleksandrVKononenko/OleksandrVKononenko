 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Point; 
import java.util.Date; 

import ap.prop.SDate; 

public class TPivotSuite { 

	private double base_point; 
	 
	private Bar bar; 
	 
	private TPivot standard; 
	 
	private TPivot fibonacci; 
	 
	private TPivot demark; 
	 
	 
	public double getBase_point() { 
		return base_point; 
	} 

	public void setBase_point(double base_point) { 
		this.base_point = base_point; 
	} 

	public Bar getBar() { 
		return bar; 
	} 

	public void setBar(Bar bar) { 
		this.bar = bar; 
	} 

	public TPivot getStandard() { 
		return standard; 
	} 

	public void setStandard(TPivot standard) { 
		this.standard = standard; 
	} 

	public TPivot getFibonacci() { 
		return fibonacci; 
	} 

	public void setFibonacci(TPivot fibonacci) { 
		this.fibonacci = fibonacci; 
	} 

	public TPivot getDemark() { 
		return demark; 
	} 

	public void setDemark(TPivot demark) { 
		this.demark = demark; 
	} 

	public TPivotSuite() { 
		 
	} 
	 
	public TPivotSuite(Bar bar) { 
		 
		this.setBar(bar); 
		 
		this.setDemark(TPivot.getInstance(bar,TPivot.DEMARK)); 
		 
		this.setStandard(TPivot.getInstance(bar,TPivot.STANDARD)); 
		 
		this.setFibonacci(TPivot.getInstance(bar,TPivot.FIBONACCI)); 
		 
	} 
	public TPivotSuite(Bar bar,int x,double base_point_value,double base_line_y,int owner_bounds_y,double form_y_zoom_factor) 
	{ 

		this(bar); 
		 
		this.getDemark().setPoint(x, base_point_value, base_line_y, owner_bounds_y, form_y_zoom_factor); 
		 
		this.getStandard().setPoint(x, base_point_value, base_line_y, owner_bounds_y, form_y_zoom_factor); 
		 
		this.getFibonacci().setPoint(x, base_point_value, base_line_y, owner_bounds_y, form_y_zoom_factor); 
		 
	 
	} 
	 
	 
	public TPivotSuite(TPivot demark,TPivot standard,TPivot fibonacci) 
	{ 
		this.setDemark(demark); 
		 
		this.setStandard(standard); 
		 
		this.setFibonacci(fibonacci); 
	} 
	 
	public static TPivotSuite get_instance(Bar bar,int x,double base_point_value,double base_line_y,int owner_bounds_y,double form_y_zoom_factor) 
	{ 
		return new TPivotSuite(bar,x, base_point_value, base_line_y, owner_bounds_y, form_y_zoom_factor); 
	} 
	 
	public TPivot getValueByType(int type) 
	{ 
		 
		if(type==TPivot.DEMARK) 
		{ 
			return this.getDemark(); 
		}else if(type==TPivot.STANDARD) 
		{ 
			return this.getStandard(); 
		} 
		 else if(type==TPivot.FIBONACCI) 
		 { 
			 return this.getFibonacci(); 
		 } 
		 
			return null; 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("\n%s\n%s\n%s\n%s", 
				SDate.toString(this.getBar().getDate()), 
				this.getDemark().toString(), 
				this.getStandard().toString(), 
				this.getFibonacci().toString() 
				); 
	} 
	 


	public static void main(String[] args) { 
		 
	} 

} 
