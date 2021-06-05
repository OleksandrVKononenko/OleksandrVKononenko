package ap.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class TextureUtil {
	
public static TexturePaint get_instance(int cs, Color color) {

    	int size = cs * cs;
    	
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = img.createGraphics();
        
        g2.setPaint(color);
        
        g2.fillRect(0, 0, size, size);
        for (int i = 0; i * cs < size; i++) {
            for (int j = 0; j * cs < size; j++) {
                if ((i + j) % 2 == 0) {
                    g2.fillRect(i * cs, j * cs, cs, cs);
                }
            }
        }
        
        	g2.dispose();
        
        	return new TexturePaint(img, new Rectangle(size, size));
    }


}
