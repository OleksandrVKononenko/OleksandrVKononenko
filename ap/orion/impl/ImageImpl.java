package ap.orion.impl;


import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import ap.global.gl;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.intf.IImage;
import ap.prop.SDimension;
import ap.swing.PanelXml;
 

public class ImageImpl implements IImage { 

	 
	private BufferedImage 	image 	; 
	 
	private String 			image_text 	; 
	 
	private String 			image_type 	; 
	 
	 
	public ImageImpl() 
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
	 
	public static ImageImpl get_instance() 
	{ 
		return new ImageImpl(); 
	} 

	
	@Override 
	public boolean clear() { 
	 
		this.setImage(null); 
		 
		this.setImageText(null); 
		 
		this.setImageType(null); 
		 
		return (this.getImage() == null); 
	} 
	
	@Override 
	public boolean fit(Orion owner) { 
	
		try {
			if (getImage() != null) {
				
				Rectangle rect = owner.getBounds();

				Dimension dim = new Dimension(
						getImage().getWidth(),
						getImage().getHeight());

				rect.width = dim.width;

				rect.height = dim.height;

				owner.setBounds(rect);

				// Проверить преферед размерность рабочего стола и откорректировать 
				// если она оказалась меньше размера изображения.
				
				Dimension m_dim_dt 		= Application.getDio().get_desk_top().getPreferredSize();
				
				Dimension m_dim_owner 	= owner.getSize();
				
				Dimension m_dim_fut 	= m_dim_dt;
				
				
				if(m_dim_owner.width > m_dim_dt.width)
				   m_dim_fut.width = m_dim_owner.width;
				
				if(m_dim_owner.height > m_dim_dt.height)
				   m_dim_fut.height = m_dim_owner.height;
					
						
				gl.tx_k(new Object() {},gl.sf("Текущий преферед...%s....объекта...%s...будет применен...%s",
						
						SDimension.toString(m_dim_dt),
						SDimension.toString(m_dim_owner),
						SDimension.toString(m_dim_fut)
						
						));
				

				Application.getDio().get_desk_top().setPreferredSize(m_dim_fut);
				
				
				
			}
				return true;
				
		} catch (Exception e) {
			
				return false;
		}
	}
	
	 
} 
