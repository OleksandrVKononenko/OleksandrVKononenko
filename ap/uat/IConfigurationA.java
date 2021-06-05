 
 
 
 
 
 
/** 
* 
*/ 
package ap.uat; 

import java.awt.Dimension; 

public interface IConfigurationA { 
	 
	public void  		setHome(String home); 
	 
	public String 		getHome(); 
	 
	 
	public void 		setPrefferedSize(Dimension size); 
	 
	public Dimension	getPrefferedSize(); 
	 
	 
	public void 		set_min_width_height(int mwh); 
	 
	public int	 		get_min_width_height(); 
	 
	public boolean 		show_brew(); 
	 
	public boolean setConstructionMode(AtOm owner); 
	 
	 

} 
