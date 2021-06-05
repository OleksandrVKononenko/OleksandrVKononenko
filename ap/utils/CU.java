 
 
/** 
* 
*/ 
package ap.utils; 

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ap.global.gl; 
import ap.prop.SColor; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 05 ??? 2016 ?. 14:48:07 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : ColorUtil.java 
* 
*/ 
public class CU { 

	 
	public static Color getAlphaColor(Color color, int alpha) 
	{ 
		return new Color( 
				 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				alpha 
				 
				); 

	} 
	 
	public static String toHtmlColor(Color color) 
	{ 
		return String.format("#%s%s%s%s", 
				Bau.to_hex_str_from_int(color.getRed()), 
						Bau.to_hex_str_from_int(color.getGreen()), 
								Bau.to_hex_str_from_int(color.getBlue()), 
										Bau.to_hex_str_from_int(color.getAlpha()) 
						); 
	} 
	 
	public static Color fromHtmlColor(String color) 
	{ 
		return getAlphaColor(Color.decode(color.substring(0,7)),Bau.to_int_from_hex_str(color.substring(7,9))); 
	} 
	 
	public static Color getSomeBrighter(Color color) 
	{ 
		return CU.getAlphaColor(color,128); 
	} 

	public static Color getSomeDarker(Color color) 
	{ 

		return CU.getAlphaColor(color,255); 
	} 

	public static String toString(Color color) 
	{ 
		return String.format("%d,%d,%d,%d", 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				color.getAlpha()); 
	} 
	
	public static Color toColor(String value)
	{
		
		if (value.equalsIgnoreCase("0"))
		{
			return new Color(0,0,0,0); 
		}
		// RGBA
		
		// Допустимые разделители.
		
		List<String> m_dlm = Arrays.asList(new String[] {",",".",":"}) ;
		
		String [] m = { value };
		
		m_dlm.forEach(a->{
			
			m[0]  = m[0].replace(a," ");
			
		});
			
		List<String> m_list = Arrays.asList(m[0].split(" "));
		
		List<Integer> m_int = new ArrayList<Integer>();
		
		m_list.forEach(a->{
			
			m_int.add(DU.to_int(a));
		});
	
		if(m_int.contains(gl.E_ERROR) || m_int.size() != 4)
		{
			return null;
		}
		
			return getAlphaColor(new Color(m_int.get(0),m_int.get(1),m_int.get(2)),m_int.get(3));
		
	}
	 
	public static Color getNullColor() 
	{ 
		return new Color(255,255,255,255); 
		 
	} 
	 
	public static boolean equal(Color color1 ,Color color2) 
	{ 
		 
		return ( 
				color1.getRed()== color2.getRed() && 
						color1.getGreen()== color2.getGreen() && 
								color1.getBlue()== color2.getBlue() && 
										color1.getAlpha()== color2.getAlpha()); 
										 
	} 
	 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		Color co = new Color(11,12,13,14); 
		 
		gl.smn("Color : " + SColor.toString(co)); 
		 
		gl.smn("Html color : " + toHtmlColor(co)); 
		 
		gl.smn("Check  : " + SColor.toString(fromHtmlColor(toHtmlColor(co)))); 
		 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
