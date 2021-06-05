 
package ap.swing; 

import java.awt.Dimension; 

public interface IConfiguration { 
	 
	public void  		setHome(String home); 
	 
	public String 		getHome(); 
	 
	 
	public void 		setPrefferedSize(Dimension size); 
	 
	public Dimension	getPrefferedSize(); 
	 
	 
	public void 		setMinWidthHeight(int mwh); 
	 
	public int	 		getMinWidthHeight(); 
	 
	public boolean 		show_brew(); 
	 
	public boolean setConstructionMode(PanelXml owner); 
	 
	 

} 
