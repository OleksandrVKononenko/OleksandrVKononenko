 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.image; 

import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.GradientPaint; 
import java.awt.Graphics2D; 
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
import javax.imageio.ImageWriteParam; 
import javax.imageio.ImageWriter; 
import javax.imageio.stream.ImageOutputStream; 
import javax.imageio.stream.MemoryCacheImageOutputStream; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.Fu; 

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
			 
			 
		} catch (FileNotFoundException e) { 
			 
			return false; 
			 
		} catch (IOException e) { 


			return false; 
		} 
		 
			return true; 
		 
	} 
	 
	public static void Test_thumb(String src_file,String dest_file,int scale_x , int scale_y) throws IOException 
	{ 
		 
		BufferedImage image = ImageIO.read(new File(src_file)); 
	 
		BufferedImage scaled = getScaledInstance(image, scale_x,scale_y, 
		 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		 
		writeJPG(scaled, new FileOutputStream(dest_file), 0.85f); 
		 
	} 
	 
	 
	 
	public static boolean createThumbnail(String src_file,String dest_file,int scale_x , int scale_y) throws IOException 
	{ 
		 
		BufferedImage image = ImageIO.read(new File(src_file)); 
	 
		BufferedImage scaled = getScaledInstance(image, scale_x,scale_y, 
		 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		 
		writeJPG(scaled, new FileOutputStream(dest_file), 0.85f); 
		 
		return Fu.isaFile(dest_file);		 
	} 
	 
	 
	 
	public static BufferedImage getScaledInstance(BufferedImage img, 
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

	  public static BufferedImage makeCustomBufferedImage(Rectangle rect) 
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

	public static BufferedImage makeRoundedImage(BufferedImage image, int paw, 
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

	 
	  public static BufferedImage makeBufferedImageFromFile(String file) 
	  { 

	    BufferedImage image = null; 

	    try 
	    { 
	      image = ImageIO.read(new File(file)); 
	 
	      return image; 
	 
	    } 
	    catch (Exception e) 
	    { 
	      gl.smn("makeBufferedImage(Exception) : " + e.toString()); 
	 
	      return null; 

	    } 
	  } 
	 
	  public static BufferedImage makeCustomBufferedImageGradient(Rectangle rect,Color from , Color to) 
	  { 

	    BufferedImage output = new BufferedImage(rect.width,rect.height, BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D pg = output.createGraphics(); 

	    GradientPaint gradient = new GradientPaint(0, 0, from,rect.width,rect.height,to); 
	 
	    pg.setPaint(gradient); 
	 
	    //pg.fillRect(0, 0, rect.width,rect.height); 

	    pg.fillRoundRect(0, 0, rect.width,rect.height,25,25); 
	 
	    pg.dispose(); 

	    return output; 
	  } 


	 
	 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
