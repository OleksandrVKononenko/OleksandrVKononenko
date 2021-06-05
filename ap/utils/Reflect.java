 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 

import javax.imageio.*; 
import javax.swing.*; 

import ap.global.*; 

public class Reflect extends JComponent { 

	private static final long serialVersionUID = 1L; 
	 
	private BufferedImage image; 

	public Reflect() { 

		image = ImageUtil.to_buffered_image("e:\\arc_doc_screen.jpg"); 
	} 

	 
	public void paintReflectedImage( 
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
	 
	public void paintComponent_old(Graphics g) { 

		Graphics2D g2d = (Graphics2D) g; 
		 
		int width = getWidth(); 
		 
		int height = getHeight(); 
		 
		int imageWidth = image.getWidth(); 
		 
		int imageHeight = image.getHeight(); 
		 
		int gap = 5; 
		 
		float opacity = 0.6f; 
		 
		float fadeHeight = 0.4f; 

		g2d.setPaint(new GradientPaint(0, 0, Color.black, 0, height, 
				Color.darkGray)); 

		g2d.fillRect(0, 0, width, height); 

		g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight); 

		//image = gl.makeRoundedImage(image, 20, 10); 

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

	public void paintComponent(Graphics g) { 
		 
		/* 
		paintReflectedImage( 
				g, 
				image, 
				this.getSize(), 
				5, 
				0.6f, 
				0.4f, 
				new GradientPaint(0, 0, Color.black, 0,this.getSize().height,Color.white) 
				); 
		*/ 
		 
		/* 
		BufferedImage bi = ImageUtil.getReflectedImageEx(image, 
				this.getSize(), 
				5, 
				0.6f, 
				0.4f, 
				new GradientPaint(0, 0, Color.black, 0,this.getSize().height,Color.white) 
				); 
		*/ 
		 
		  //ByteArrayUtil.toFileFromBI(String.format("e:\\a_test_%04.png",gl.getRandomInt(999)), bi,"png"); 
		/* 
		Graphics2D g2= (Graphics2D)g; 
		 
		g2.drawImage(bi,null,0,0); 
		*/ 
		 
		BufferedImage inverted = ImageUtil.flip_bi_h(image); 
		 
		Graphics2D g2= (Graphics2D)g; 

		g2.drawImage(image,null,0,0); 
		 
		 
		BufferedImage shadow = ImageUtil.get_cust_gradient_bi(inverted, 
				CU.getAlphaColor(Color.gray,0), 
				CU.getAlphaColor(Color.gray,255)); 
		 
		g2.drawImage(shadow,null,0,image.getHeight()); 
		 
		 
		 
	} 

	 
	 

	public Dimension getPreferredSize() { 
		return new Dimension(64, 64); 
	} 

	public static void main(String args[]) { 
		 
		JFrame f = new JFrame(); 
		 
		Reflect r = new Reflect(); 
		 
		f.getContentPane().add(r); 
		 
		f.pack(); 
		 
		f.setVisible(true); 
		 
	} 
} 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
