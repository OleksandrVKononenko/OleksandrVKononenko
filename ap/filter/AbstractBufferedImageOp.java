 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.filter; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 25 ??? 2016 ?. 14:46:07 
* Project  name : Organizer 
* Package  name : ap.filter 
* File     name : AbstractBufferedImageOp.java 
* 
*/ 

import java.awt.*; 
import java.awt.geom.Point2D; 
import java.awt.geom.Rectangle2D; 
import java.awt.image.BufferedImage; 
import java.awt.image.BufferedImageOp; 
import java.awt.image.ColorModel; 

public abstract class AbstractBufferedImageOp implements BufferedImageOp { 
	public BufferedImage createCompatibleDestImage(BufferedImage src, 
			ColorModel dstCM) { 
		if (dstCM == null) { 
			dstCM = src.getColorModel(); 
		} 
		return new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster( 
				src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(), 
				null); 
	} 

	public Rectangle2D getBounds2D(BufferedImage src) { 
		return new Rectangle(0, 0, src.getWidth(), src.getHeight()); 
	} 

	public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) { 
		if (dstPt == null) { 
			dstPt = new Point2D.Double(); 
		} 
		dstPt.setLocation(srcPt.getX(), srcPt.getY()); 
		return dstPt; 
	} 

	public RenderingHints getRenderingHints() { 
		return null; 
	} 

	/** 
	 * A convenience method for getting ARGB pixels from an image. This tries to 
	 * avoid the performance penalty of BufferedImage.getRGB unmanaging the 
	 * image. 
	 */ 
	public int[] getRGB(BufferedImage image, int x, int y, int width, 
			int height, int[] pixels) { 
		int type = image.getType(); 
		if (type == BufferedImage.TYPE_INT_ARGB 
				|| type == BufferedImage.TYPE_INT_RGB) { 
			return (int[]) image.getRaster().getDataElements(x, y, width, 
					height, pixels); 
		} 
		return image.getRGB(x, y, width, height, pixels, 0, width); 
	} 

	/** 
	 * A convenience method for setting ARGB pixels in an image. This tries to 
	 * avoid the performance penalty of BufferedImage.setRGB unmanaging the 
	 * image. 
	 */ 
	public void setRGB(BufferedImage image, int x, int y, int width, 
			int height, int[] pixels) { 
		int type = image.getType(); 
		if (type == BufferedImage.TYPE_INT_ARGB 
				|| type == BufferedImage.TYPE_INT_RGB) { 
			image.getRaster().setDataElements(x, y, width, height, pixels); 
		} else { 
			image.setRGB(x, y, width, height, pixels, 0, width); 
		} 
	} 
} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
