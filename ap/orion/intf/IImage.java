package ap.orion.intf;


import java.awt.image.BufferedImage;

import ap.orion.Orion;


public interface IImage { 
	 
	 
	public BufferedImage getImage(); 
	 
	public void setImage(BufferedImage image); 

	 
	public String getImageText(); 
	 
	public void setImageText(String image_text); 
	 
	 
	public String getImageType(); 
	 
	public void setImageType(String image_type); 
	 
	 
	public boolean clear(); 
	
	public boolean fit(Orion owner);
	 
} 
