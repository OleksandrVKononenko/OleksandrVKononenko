 
/** 
* 
*/ 
package ap.utils; 

import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GraphicsConfiguration; 
import java.awt.GraphicsDevice; 
import java.awt.GraphicsEnvironment; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.Transparency; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.RoundRectangle2D; 
import java.awt.image.AffineTransformOp; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream; 
import java.util.Iterator; 

import javax.imageio.IIOImage; 
import javax.imageio.ImageIO; 
import javax.imageio.ImageReader; 
import javax.imageio.ImageWriteParam; 
import javax.imageio.ImageWriter; 
import javax.imageio.stream.ImageInputStream; 
import javax.imageio.stream.ImageOutputStream; 
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.JComponent;

import ap.btn.TPanel; 
import ap.frame.TFrame; 
import ap.global.gl;
import ap.orion.Orion;
import ap.prop.SBounds; 
import ap.shape.Ru; 
import ap.swing.PanelXml; 

/** 
* Author : Oleksandr V. Kononenko 
* 
* 12 ????? 2016 ?. 9:29:27 Project name : Organizer Package name : ap.image 
* File name : ImageUtil.java 
* 
*/ 
public class ImageUtil { 

	public static void main(String[] args) throws IOException { 

		gl.smn("Start."); 
		 
		Test_thumb("Test.jpg","Test_new_150.jpg",150,150); 
		 
		Test_thumb("Test.jpg","Test_new_120.jpg",120,120); 
		 
		Test_thumb("Test.jpg","Test_new_100.jpg",100,100); 
		 
		Test_thumb("Test.jpg","Test_new_80.jpg",80,80); 
			 
		 
		gl.smn("Done."); 
		 
	} 

	 
	 
	public static boolean write(BufferedImage image ,String dest_file,float scale) 
	{ 
		try { 
			 
			writeJPG(image, new FileOutputStream(dest_file),scale); 
			 
			return true; 
			 
		} catch (FileNotFoundException e) { 
			 
			return false; 
			 
		} catch (IOException e) { 


			return false; 
		} 
		 
			 
		 
	} 
	 
	public static void Test_thumb(String src_file,String dest_file,int scale_x , int scale_y) throws IOException 
	{ 
		 
		BufferedImage image = ImageIO.read(new File(src_file)); 
	 
		BufferedImage scaled = get_scaled_bi(image, scale_x,scale_y, 
		 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		 
		writeJPG(scaled, new FileOutputStream(dest_file), 0.85f); 
		 
	} 
	 
	 
	 
	public static boolean make_thumbnail(String src_file,String dest_file,int scale_x , int scale_y) throws IOException 
	{ 
		 
		BufferedImage image = ImageIO.read(new File(src_file)); 
	 
		BufferedImage scaled = get_scaled_bi(image, scale_x,scale_y, 
		 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		 
		writeJPG(scaled, new FileOutputStream(dest_file), 0.85f); 
		 
		return Fu.isaFile(dest_file);		 
	} 
	 
	 
	 
	public static BufferedImage get_scaled_bi(BufferedImage img, 
			int targetWidth, int targetHeight, Object hint, 
			boolean higherQuality) { 
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB 
				: BufferedImage.TYPE_INT_ARGB; 
		BufferedImage ret = (BufferedImage) img; 
		int w, h; 
		if (higherQuality) { 
			// Use multi-step technique: start with original size, then 
			// scale down in multiple passes with drawImage() 
			// until the target size is reached 
			w = img.getWidth(); 
			h = img.getHeight(); 
		} else { 
			// Use one-step technique: scale directly from original 
			// size to target size with a single drawImage() call 
			w = targetWidth; 
			h = targetHeight; 
		} 

		do { 
			if (higherQuality && w > targetWidth) { 
				w /= 2; 
				if (w < targetWidth) { 
					w = targetWidth; 
				} 
			} 

			if (higherQuality && h > targetHeight) { 
				h /= 2; 
				if (h < targetHeight) { 
					h = targetHeight; 
				} 
			} 

			BufferedImage tmp = new BufferedImage(w, h, type); 
			Graphics2D g2 = tmp.createGraphics(); 
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint); 
			g2.drawImage(ret, 0, 0, w, h, null); 
			g2.dispose(); 

			ret = tmp; 
		} while (w != targetWidth || h != targetHeight); 

		return ret; 
	} 

	public static void writeJPG(BufferedImage bufferedImage, 
			OutputStream outputStream, float quality) throws IOException { 
		 
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpg"); 
		 
		ImageWriter imageWriter = iterator.next(); 
		 
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam(); 
		 
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); 
		 
		imageWriteParam.setCompressionQuality(quality); 
		 
		ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(outputStream); 
		 
		imageWriter.setOutput(imageOutputStream); 
		 
		IIOImage iioimage = new IIOImage(bufferedImage, null, null); 
		 
		imageWriter.write(null, iioimage, imageWriteParam); 
		 
		imageOutputStream.flush(); 
		 
	} 

	  public static BufferedImage get_comp_bi(Rectangle rect) 
	  { 

		  return get_comp_bi(new Dimension(rect.width,rect.height)); 
	  } 
	 
	  public static BufferedImage get_comp_bi(Dimension dim) 
	  { 

		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		 
		  GraphicsDevice gd = ge.getDefaultScreenDevice(); 
		 
		  GraphicsConfiguration gc = gd.getDefaultConfiguration(); 
		  	 
		  return gc.createCompatibleImage(dim.width,dim.height); 
		 
	  } 

	  public static BufferedImage get_cust_bi(Rectangle rect) 
	  { 

	    BufferedImage output =  new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D pg = output.createGraphics(); 

	    pg.setComposite(AlphaComposite.Src); 

	    pg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                        RenderingHints.VALUE_ANTIALIAS_ON); 


	    pg.setComposite(AlphaComposite.SrcAtop); 

	    pg.dispose(); 

	    return output; 
	 
	  } 

	  public static BufferedImage concat(BufferedImage source,BufferedImage target,int align) 
	  { 

		int GAP = gl.E_EMPTY; 
				 
		Rectangle rect = Ru.concat(new Rectangle(0,0,source.getWidth(),source.getHeight()), 
									  new Rectangle(0,0,target.getWidth(),target.getHeight()), 
									  align); 
		 
	    BufferedImage result = new BufferedImage(rect.width,rect.height, BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D g2 = result.createGraphics(); 
	 
	    g2.drawImage(source,GAP,GAP,source.getWidth(),source.getHeight(),null); 
	 
	    g2.drawImage(target,GAP,source.getHeight(),target.getWidth(),target.getHeight(),null); 

	    g2.dispose(); 

	    return result; 
	 
	  } 

	public static BufferedImage get_rounded_bi(BufferedImage image, int paw, 
			int pah) { 

		int w = image.getWidth(); 

		int h = image.getHeight(); 

		BufferedImage output = new BufferedImage(w, h, 
				BufferedImage.TYPE_INT_ARGB); 

		Graphics2D pg = output.createGraphics(); 

		pg.setComposite(AlphaComposite.Src); 

		pg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 

		pg.setColor(Color.WHITE); 

		pg.fill(new RoundRectangle2D.Float(0, 0, w, h, paw, pah)); 

		pg.setComposite(AlphaComposite.SrcAtop); 

		pg.drawImage(image, 0, 0, null); 

		pg.dispose(); 

		return output; 
	} 

	 
	  public static BufferedImage to_buffered_image(String file) 
	  { 

	    BufferedImage image = null; 

	    try 
	    { 
	      image = ImageIO.read(new File(file)); 
	 
	      return image; 
	 
	    } 
	    catch (Exception e) 
	    { 
	      gl.tx_e(new Object() {},gl.sf("%s",e.toString())); 
	 
	      return null; 

	    } 
	  } 
	
	  
	  public static byte[] to_byte_array(String source) 
	  {
		  		BufferedImage imgBuffer = null;
		
		try {
				
				imgBuffer = ImageIO.read(new File(source));
				
		} catch (IOException e) {
				
				return null;
			
		}

				byte[]  area = (byte[])imgBuffer.getRaster().getDataElements(0, 0, imgBuffer.getWidth(), imgBuffer.getHeight(), null);

				return area;
	  }

	 
	  public static BufferedImage get_cust_gradient_bi(Rectangle rect,Color from , Color to) 
	  { 

	    BufferedImage output = new BufferedImage(rect.width,rect.height, BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D pg = output.createGraphics(); 

	    GradientPaint gradient = new GradientPaint(0, 0, from,rect.width,rect.height,to); 
	 
	    pg.setPaint(gradient); 

	    pg.dispose(); 

	    return output; 
	  } 
	 
	  public static BufferedImage get_cust_gradient_bi(BufferedImage image,Color from , Color to) 
	  { 

	    BufferedImage output = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D pg = output.createGraphics(); 
	 
	    pg.drawImage(image,null,0,0); 

	    GradientPaint gradient = new GradientPaint(0,0,from,0,image.getHeight(),to); 
	 
	    pg.setPaint(gradient); 
	 
	    pg.fillRect(0, 0,image.getWidth(),image.getHeight() ); 

	    pg.dispose(); 

	    return output; 
	  } 
	 
	 

	  public static String get_bi_type(String path) 
	  { 
		 
		  String format = null; 
		 
		  try { 
			 
			  ImageInputStream input = ImageIO.createImageInputStream(new File(path)); 
			 
			  try 
			  { 
				  Iterator<ImageReader> readers = ImageIO.getImageReaders(input); 
				 
				  try 
				  { 
					  if(readers.hasNext()) 
					  { 
						  ImageReader reader = readers.next(); 
						 
						  reader.setInput(input); 
						 
						  BufferedImage bi = reader.read(0); 
						 
						  format = reader.getFormatName(); 
						 
					  } 
				  } 
				  catch(Exception e) 
				  { 
					  return null; 
				  } 
				 
				 
			  } 
			  catch(Exception e) 
			  { 
				  return null; 
			  } 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		 
		  	return format; 
		 
	  } 
	 
	  public static String get_bi_type(BufferedImage image) 
	  { 
		 
		  String format = null; 
		 
		  try { 
			 
			  ImageInputStream input = ImageIO.createImageInputStream(image); 
			 
			  try 
			  { 
				  Iterator<ImageReader> readers = ImageIO.getImageReaders(input); 
				 
				  try 
				  { 
					  if(readers.hasNext()) 
					  { 
						  ImageReader reader = readers.next(); 
						 
						  reader.setInput(input); 
						 
						  BufferedImage bi = reader.read(0); 
						 
						  format = reader.getFormatName(); 
						 
					  } 
				  } 
				  catch(Exception e) 
				  { 
					  return null; 
				  } 
				 
				 
			  } 
			  catch(Exception e) 
			  { 
				  return null; 
			  } 
			 
			 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
		 
		  	return format; 
		 
	  } 

	  public static void paint_bi(Graphics2D g2,Rectangle rect,BufferedImage image) 
	  { 
		  g2.drawImage(image,rect.x,rect.y,rect.width,rect.height,null); 
	  } 
	 
	 
	  public static void paint_mirrored_bi( 
				Graphics g, 
				BufferedImage image, 
				Dimension component_dim, 
				int gap, 
				float opacity, 
				float fadeHeight, 
				GradientPaint paint 
				) 
		{ 
			Graphics2D g2d = (Graphics2D) g; 
			 
			int width = component_dim.width; 
			 
			int height = component_dim.height; 
			 
			int imageWidth = image.getWidth(); 
			 
			int imageHeight = image.getHeight(); 

			g2d.setPaint(paint); 

			g2d.fillRect(0, 0, width, height); 

			g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight); 

			g2d.drawRenderedImage(image, null); 

			g2d.translate( 0, 2*imageHeight+gap ); 

			g2d.scale(1, -1); 

			BufferedImage reflection = new BufferedImage(imageWidth, imageHeight, 
					BufferedImage.TYPE_INT_ARGB); 
			 
			Graphics2D rg = reflection.createGraphics(); 

			rg.drawRenderedImage(image, null); 

			rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); 

			 
			rg.setPaint(new GradientPaint(0, imageHeight * fadeHeight, new Color( 
					0.0f, 0.0f, 0.0f, 0.0f), 0, imageHeight, new Color(0.0f, 0.0f, 
					0.0f, opacity))); 

			rg.fillRect(0, 0, imageWidth, imageHeight); 
			 
			rg.dispose(); 

			g2d.drawRenderedImage(reflection, null); 
			 
			 
			 
		} 
	 
	  public static BufferedImage get_mirrored_bi( 
				Graphics g, 
				BufferedImage image, 
				Dimension component_dim, 
				int gap, 
				float opacity, 
				float fadeHeight, 
				GradientPaint paint 
				) 
		{ 
			Graphics2D g2d = (Graphics2D) g; 
			 
			int width = component_dim.width; 
			 
			int height = component_dim.height; 
			 
			int imageWidth = image.getWidth(); 
			 
			int imageHeight = image.getHeight(); 

			g2d.setPaint(paint); 

			g2d.fillRect(0, 0, width, height); 

			g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight); 

			//g2d.drawRenderedImage(image, null); 

			g2d.translate( 0, 2*imageHeight+gap ); 

			g2d.scale(1, -1); 

			BufferedImage reflection = new BufferedImage(imageWidth, imageHeight, 
					BufferedImage.TYPE_INT_ARGB); 
			 
			Graphics2D rg = reflection.createGraphics(); 

			rg.drawRenderedImage(image, null); 

			rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); 

			 
			rg.setPaint(new GradientPaint(0, imageHeight * fadeHeight, new Color( 
					0.0f, 0.0f, 0.0f, 0.0f), 0, imageHeight, new Color(0.0f, 0.0f, 
					0.0f, opacity))); 

			rg.fillRect(0, 0, imageWidth, imageHeight); 
			 
			rg.dispose(); 

			//g2d.drawRenderedImage(reflection, null); 
			 
			return reflection; 
			 
		} 

	  public static BufferedImage get_mirrored_bi_ex( 
				BufferedImage image, 
				Dimension component_dim, 
				int gap, 
				float opacity, 
				float fadeHeight, 
				GradientPaint paint 
				) 
		{ 
		 
		 
			Graphics2D g2d = (Graphics2D)image.getGraphics(); 
			 
			int width = component_dim.width; 
			 
			int height = component_dim.height; 
			 
			int imageWidth = image.getWidth(); 
			 
			int imageHeight = image.getHeight()*2; 

			g2d.setPaint(paint); 

			//g2d.fillRect(0, 0, width, height); 

			//g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight); 

			 
			 
			//BufferedImage inverted = ImageUtil.flipBufferedImage(image); 
			 
			g2d.drawRenderedImage(image, null); 

			g2d.translate( 0, 2*imageHeight+gap ); 

			g2d.scale(1, -1); 

			BufferedImage reflection = new BufferedImage(imageWidth, imageHeight, 
					BufferedImage.TYPE_INT_ARGB); 
			 
			Graphics2D rg = reflection.createGraphics(); 

			rg.drawRenderedImage(image, null); 

			rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); 

			 
			rg.setPaint(new GradientPaint(0, imageHeight * fadeHeight, new Color( 
					0.0f, 0.0f, 0.0f, 0.0f), 0, imageHeight, new Color(0.0f, 0.0f, 
					0.0f, opacity))); 

			rg.fillRect(0, 0, imageWidth, imageHeight); 
			 
			rg.dispose(); 

			return reflection; 
			 
		} 

	 
	  public static BufferedImage flip_bi_v(BufferedImage image) 
	  { 
		 
		  AffineTransform at = AffineTransform.getScaleInstance(1,-1); 
		 
		  at.translate(0,-image.getHeight(null)); 
		 
		  AffineTransformOp op = new AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 

		  BufferedImage bi_op =  op.filter(image,null); 
		 
		  return bi_op; 
		 
	  } 
	 
	  public static BufferedImage flip_bi_h(BufferedImage image) 
	  { 
		 
		  AffineTransform at = AffineTransform.getScaleInstance(-1,1); 
		 
		  at.translate(-image.getWidth(null),0); 
		 
		  AffineTransformOp op = new AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 

		  BufferedImage bi_op =  op.filter(image,null); 
		 
		  return bi_op; 
		 
	  } 

	 
	 
	 
	 
	 
	  public static void paint_mirrored_bi_ex( 
				Graphics g, 
				BufferedImage image, 
				Dimension component_dim, 
				int gap, 
				float opacity, 
				float fadeHeight, 
				GradientPaint paint 
				) 
		{ 
			Graphics2D g_main = (Graphics2D) g; 
		 
			int imageWidth = image.getWidth(); 
			 
			int imageHeight = image.getHeight(); 
		 
		  BufferedImage target_image = new BufferedImage(imageWidth, imageHeight, 
					BufferedImage.TYPE_INT_ARGB); 
			 
			Graphics2D g2d = target_image.createGraphics(); 
			 
			int width = component_dim.width; 
			 
			int height = component_dim.height; 
			 

			g2d.setPaint(paint); 

			g2d.fillRect(0, 0, width, height); 

			g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight); 
			 
			g2d.drawRenderedImage(image, null); 
			 
			g2d.translate( 0,imageHeight+gap ); 

			g2d.scale(1, -1); 

			 
			g2d.drawRenderedImage(image, null); 

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN)); 

			 
			g2d.setPaint(new GradientPaint(0, imageHeight * fadeHeight, new Color( 
					0.0f, 0.0f, 0.0f, 0.0f), 0, imageHeight, new Color(0.0f, 0.0f, 
					0.0f, opacity))); 

			g2d.fillRect(0, 0, imageWidth, imageHeight); 
			 
			g2d.dispose(); 

			g_main.drawRenderedImage(target_image, null); 
			 

		} 
	 
	 
	  // REPLACE 19.06.2018 drawReflectionBufferedImage(new) 
	 
	  public static void drawReflectionBufferedImage(Graphics2D gg,TPanel owner,TPanel target,Color from,Color to) 
	  { 
			 
			BufferedImage inverted = ImageUtil.flip_bi_v(owner.getImage()); 
			 
			BufferedImage shadow = ImageUtil.get_cust_gradient_bi(inverted, 
					from, 
					to); 
			 
			Rectangle targetRect = Ru.Shift(target.getBounds(),0,target.getBounds().height); 
					 
			ImageUtil.paint_bi(gg,targetRect,shadow); 
		 
	  } 

	  public static BufferedImage get_mirrored_bi(TPanel owner,Color from,Color to) 
	  { 
			 
			BufferedImage inverted = ImageUtil.flip_bi_v(owner.getImage()); 
			 
			BufferedImage mirror = ImageUtil.get_cust_gradient_bi(inverted, 
					from, 
					to); 
			 
			return mirror; 
		 
	  } 
	 
	  public static BufferedImage get_sub_bi(BufferedImage image,Rectangle region) 
	  { 
		   return image.getSubimage(region.x,region.y,region.width,region.height); 
	  } 
		 
	  public static BufferedImage get_bi(TPanel owner) 
	  { 
	 
	    Rectangle bounds = owner.getBounds(); 
		 
		BufferedImage result_buffered_image =  new BufferedImage(bounds.width,bounds.height, BufferedImage.TYPE_INT_ARGB); 

		Graphics2D g2 = result_buffered_image.createGraphics(); 
		 
	    g2.setComposite(AlphaComposite.Src); 

	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                        RenderingHints.VALUE_ANTIALIAS_ON); 

	    g2.setComposite(AlphaComposite.SrcAtop); 
	 
	    owner.paint(g2); 

	    g2.dispose(); 
	 
	    return result_buffered_image; 
	 
	  } 
	 
	  public static BufferedImage get_bi(PanelXml owner) 
	  { 
	 
	    Rectangle bounds = owner.getBounds(); 
		 
		BufferedImage result_buffered_image =  new BufferedImage(bounds.width,bounds.height, BufferedImage.TYPE_INT_ARGB); 

		Graphics2D g2 = result_buffered_image.createGraphics(); 
		 
	    g2.setComposite(AlphaComposite.Src); 

	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                        RenderingHints.VALUE_ANTIALIAS_ON); 

	    g2.setComposite(AlphaComposite.SrcAtop); 
	 
	    owner.paint(g2); 

	    g2.dispose(); 
	 
	    return result_buffered_image; 
	 
	  } 
	  		
	  public BufferedImage get_buffered_image(Orion owner)
	  {
			 
		Rectangle 		bounds 	= owner.getBounds(); 
				 
		BufferedImage 	result_buffered_image =  new BufferedImage(bounds.width,bounds.height, BufferedImage.TYPE_INT_ARGB); 

		Graphics2D 		g2 		= result_buffered_image.createGraphics(); 
				 
						g2.setComposite(AlphaComposite.Src); 

						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			                        RenderingHints.VALUE_ANTIALIAS_ON); 

						g2.setComposite(AlphaComposite.SrcAtop); 
			 
						owner.paint(g2); 

						g2.dispose(); 
			 
		return 			result_buffered_image; 
			 
		} 
	  	 
	  public static BufferedImage get_bi(TFrame owner) 
	  { 
	 
	    Rectangle bounds = owner.getBounds(); 
		 
		BufferedImage result_buffered_image =  new BufferedImage(bounds.width,bounds.height, BufferedImage.TYPE_INT_ARGB); 

		Graphics2D g2 = result_buffered_image.createGraphics(); 
		 
	    g2.setColor(owner.getBackground()); 
	 
	    g2.fillRect(0,0,owner.getBounds().width,owner.getBounds().height); 
	 
	    owner.paint(g2); 
	 
	    g2.dispose(); 
	 
	    return result_buffered_image; 
	 
	  } 
	  
	  public static BufferedImage get_component_bi(JComponent owner) 
	  { 
	 
		  try
		  {
				  
		    Rectangle 		bounds = owner.getBounds(); 
			 
			BufferedImage 	m_bi_result =  new BufferedImage(bounds.width,bounds.height, BufferedImage.TYPE_INT_ARGB); 
	
			Graphics2D g2 = m_bi_result.createGraphics(); 
			 
		    g2.setColor(owner.getBackground()); 
		 
		    g2.fillRect(0,0,owner.getBounds().width,owner.getBounds().height); 
		 
		    owner.paint(g2); 
		 
		    g2.dispose(); 
		 
		    return m_bi_result; 
	    
	  	}
	    catch(Exception e)
	    {
	    	return null;
	    }
	 
	  } 
	  
	  	  
	 
	  public static BufferedImage get_sub_bi(TFrame owner,Rectangle region) 
	  { 
		 
		 	 
		 BufferedImage desktop = get_bi(owner); 
		 
		 if(desktop == null) 
		 { 
			 gl.tracex(new Object(){},String.format("Get desktop image...%s",gl.S_ERROR)); 
			 			 
			 return null; 
		 } 
		 
		 	gl.tracex(new Object(){},String.format("Get desktop image...%s",gl.S_OK)); 
		 
		 	return desktop.getSubimage(region.x,region.y,region.width,region.height); 
		 	 
	 
	  } 
	 
	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
