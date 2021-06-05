 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.image.BufferedImage; 

public class ImageImplObject implements IImage { 

	 
	private BufferedImage 	image 		; 
	 
	private String 			image_text 	; 
	 
	private String 			image_type 	; 
	 
	 
	public ImageImplObject() 
	{ 
		 
	} 
	 
	@Override 
	public BufferedImage getImage() { 
		 
		return this.image; 
	} 

	@Override 
	public void setImage(BufferedImage image) { 
		 
		this.image = image; 
	} 

	@Override 
	public String getImageText() { 
	 
		return this.image_text; 
	} 

	@Override 
	public void setImageText(String image_text) { 
		 
		this.image_text = image_text; 
	} 

	 
	@Override 
	public String getImageType() { 
	 
		return this.image_type; 
	} 

	 
	@Override 
	public void setImageType(String image_type) { 
	 
		this.image_type = image_type; 
	} 
	 
	public static ImageImplObject get_instance() 
	{ 
		return new ImageImplObject(); 
	} 

	@Override 
	public boolean clear() { 
	 
		this.setImage(null); 
		 
		this.setImageText(null); 
		 
		this.setImageType(null); 
		 
		return (this.getImage() == null); 
	} 
	 
	 
	 
} 
