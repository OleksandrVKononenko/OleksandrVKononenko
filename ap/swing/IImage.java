 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.image.BufferedImage; 

public interface IImage { 
	 
	 
	public BufferedImage getImage(); 
	 
	public void setImage(BufferedImage image); 

	 
	public String getImageText(); 
	 
	public void setImageText(String image_text); 
	 
	 
	public String getImageType(); 
	 
	public void setImageType(String image_type); 
	 
	 
	public boolean clear(); 
	 
} 
