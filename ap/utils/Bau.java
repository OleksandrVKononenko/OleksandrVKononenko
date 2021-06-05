 
 
 
/** 
* 
*/ 
package ap.utils; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.image.BufferedImage; 
import java.io.ByteArrayInputStream; 
import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.UnsupportedEncodingException; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List; 

import javax.imageio.ImageIO; 
import javax.swing.UIManager; 
import javax.xml.bind.DatatypeConverter; 

import ap.global.gl; 
import ap.log.Logger;
import ap.orion.Orion;
import ap.btn.TImage; 
import ap.btn.TPanel;
import ap.explorer.Group;
import ap.prop.SColor; 
import ap.swing.PanelXml; 
import ap.uat.AtOm; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 04 ????. 2016 ?. 15:24:18 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : ByteArrayUtil.java 
* 
*/ 
public class Bau { 
	 
	 
	public static BufferedImage to_buf_imf_in_target_rect(BufferedImage bi_source,Rectangle rect) 
	{ 
		int scale_x = bi_source.getWidth() / rect.width; 
				 
		int scale_y = bi_source.getHeight() / rect.height; 
						 
		BufferedImage bi_scaled = ImageUtil.get_scaled_bi(bi_source, 
				scale_x, 
				scale_y, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		 
		return bi_scaled; 
	 
	} 
	 
	public static BufferedImage to_buf_img_as_error(Rectangle rect) 
	  { 

	    BufferedImage output = new BufferedImage(rect.width,rect.height, BufferedImage.TYPE_INT_ARGB); 

	    Graphics2D pg = output.createGraphics(); 

	    GradientPaint grd = new GradientPaint(0, 0, Color.red, rect.width,rect.height, Color.black); 
	 
	    pg.setPaint(grd); 
	 
	    pg.fillRect(0, 0,rect.width,rect.height); 

	    pg.dispose(); 

	    return output; 
	  } 
	 
	 

	public static BufferedImage to_buf_img_from_ico(String path) 
	{ 
		 
		BufferedImage image = null; 
		 
		try { 
	 
					 
			image = ImageIO.read(new File(path)); 
			 
			return image; 
			 
		} catch (Exception e) { 
		 
			return null; 
		} 
	} 
	 
	 
	 
	public static byte[] to_byte_array_from_hex_file(String input_file) 
	{ 
		 
		try { 
		 
			String source_hex = Fu.get_str_from_file(input_file); 
			 
			return Bau.to_byte_array_from_hex_str(source_hex); 
			 
		} catch (IOException e) { 
			return null; 
		} 
		 
	} 
	 
		 
	public static String to_hex_str_from_byte_array_2(byte[] bytes) 
	{ 
		 
		 
		 
		StringBuilder sb = new StringBuilder(); 

		try 
		{ 
			for(byte b : bytes) 
			{ 
				sb.append(String.format("%02X", b)); 
			} 
			 
			 
			return sb.toString(); 

		} 
		catch(Exception e ) 
		{ 
			return null; 
		} 
		 
	} 
	 
	public static String to_hex_str_from_int(int value) 
	{ 
		
		return String.format("%02X", value&0xFFFFF); 
	} 
	 
	public static String to_hex_str_from_int_nat(int value) 
	{ 
		
		return Integer.toHexString(value); 
	} 
	
	public static int to_int_from_hex_str(String value) 
	{ 
		return Integer.parseInt(value,16); 
	} 
	 
	public static int to_int_from_binary_str(String value) 
	{ 
		return Integer.parseInt(value,2); 
	} 
	 
	public static long to_long_from_binary_str(String value) 
	{ 
		return Long.parseLong(value.replace(" " , "").replace("-","").replace("+",""),2); 
	} 
	 
	 
	public static String to_hex_str_from_byte_array(byte[] bytes) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		try 
		{ 
			for(byte b : bytes) 
			{ 
				 
				sb.append(Character.forDigit((b>>4)&0xF, 16)); 
				 
				sb.append(Character.forDigit((b&0xF), 16)); 
			} 
			 
			return sb.toString(); 
		} 
		catch(Exception e) 
		{ 
			return null; 
		} 
	} 
	 
	public static String to_bin_str_from_int(int value) 
	{ 
		return Integer.toBinaryString(value); 
	} 
	
	public static String to_bin_str_from_int_enrich(int value) 
	{ 
		return Su.enrich(Integer.toBinaryString(value),8,'0'); 
	} 
	
	public static String to_bin_str_from_long(long value) 
	{ 
		return Long.toBinaryString(value); 
	} 
	
	public static String to_bin_str_from_long_enrich(long value) 
	{ 
		
		return Su.enrich(to_bin_str_from_long(value),63,'0'); 
	} 
	
	 
	public static boolean is_on_any(int [] v) 
	{ 
		for(int i=0;i < v.length;i++) 
		{ 
			if(v[i]==1) 
				return true; 
		} 
		 
			return false; 
	} 
	 
	 
	public static void to_bit_array_from_int(int a,int [] b ) 
	{ 
		 
		for(int i=0;i<b.length;i++) 
		{ 
			int test = 1 << i; 
			 
			if(gl.isBit(a,test)) 
			{ 
				b[(b.length - 1) - i] = 1; 
			} 
			 
		} 
		 
	} 
	 
	public static boolean is_on(int [] v,int type) 
	{ 
		return (v[type]==1); 
	} 
	 
	public static boolean to_file_from_byte_array(byte[] bytes,String fileName) 
	{ 
		 
		FileWriter output_file = null; 
		 
		try 
		{ 
		 
		try { 
			 
			output_file = new FileWriter(fileName,false); 
			 
			 
		} catch (IOException e) { 
			 
			 
			return false; 
			 
		} 
		 
			for(byte b : bytes) 
			{ 
				 
				try { 
					 
					output_file.write(Character.forDigit((b>>4)&0xF, 16)); 
					 
					output_file.write(Character.forDigit((b&0xF), 16)); 
					 
				} catch (IOException e) { 
					return false; 
				} 
			} 
			 
				return true; 
			 
		} 
		finally 
		{ 
			try { 
			 
				output_file.flush(); 
				 
				output_file.close(); 
				 
			} catch (IOException e) { 

				return false; 
				 
			} 
		} 
	} 
	 
	//From BufferedImage to ByteArray ... 
	public static byte[] to_byte_array_from_buffered_image(BufferedImage image,String image_type) 
	{ 
	 
		byte[] image_bytes = null; 
				 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		 
		try { 
			 
			ImageIO.write(image, image_type, baos); 
			 
			baos.flush(); 
			 
			image_bytes = baos.toByteArray(); 
			 
			baos.close(); 
			 
		} catch (IOException e) { 
			return null; 
		} 
		 
			return image_bytes; 
		 
	} 
	 
	public static boolean to_file_from_str(String source,String fileName) 
	{ 
		 
		FileWriter output_file = null; 
		 
		try 
		{ 
		 
				try { 
					 
					output_file = new FileWriter(fileName,false); 
					 
					output_file.write(source); 
					 
					 
					} 
				 
				catch (IOException e) { 
			 
					return false; 
			 
					} 
		 
			return true; 
			 
		} 
		finally 
		{ 
			try { 
			 
				output_file.flush(); 
				 
				output_file.close(); 
				 
			} catch (IOException e) { 

				return false; 
				 
			} 
		} 
	} 
	 
	public static byte[] to_byte_array_from_hex_str_2(String s) { 
		int len = s.length(); 
		try { 
			byte[] data = new byte[len / 2]; 

			for (int i = 0; i < len; i += 2) { 
				data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character 
						.digit(s.charAt(i+1), 16)); 
			} 
			return data; 
		} finally { 
		} 

	} 

	public static String to_str_from_hex_str(String hex_string) 
	{ 
		 
		try 
		{ 
			return to_str_from_byte_array(to_byte_array_from_hex_str(hex_string),"cp1251");	 
		} 
		catch (java.lang.IllegalArgumentException ex) 
		{ 
			return hex_string; 
		} 
		 
	} 
	 
	public static String to_str_from_byte_array(byte[] bytes,String code_page) 
	{ 
		try { 
			return new String(bytes,code_page); 
		} catch (UnsupportedEncodingException e) { 
			return null; 
		} 	 
	} 
	 
	public static byte[] to_byte_array_from_hex_str(String s) 
	{ 
		return DatatypeConverter.parseHexBinary(s); 
		 
	} 
	 
	public static boolean imp_buf_img_by_scale_factor(TPanel owner,String path,int scale_factor,Logger logger) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Import BI"; 
		 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"not exist","Error",path)); 
			 
			return false; 
		} 
	 
		 
		String format = ImageUtil.get_bi_type(path); 
		 
		String fileExt = Fu.get_file_extension(path); 
				 
		BufferedImage bi = null; 
		 
		BufferedImage bi_scaled = null; 
		 
		String msg = ""; 
		 
		if(fileExt.equalsIgnoreCase("ico")) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s","accept image format ",format,fileExt)); 
			 
			format = fileExt; 
			 
			bi = Bau.to_buf_img_from_ico(path); 
			 
		} 
		else 
		{ 
			gl.tracex(v,String.format("%s...%s...%s",fabula,"set format",format)); 
			 
			bi = Bau.to_buf_img_from_file(path); 
		} 
		 
		if(bi == null) 
		{ 
			String cmd_delete = String.format("del /F /Q \"%s\"",path); 
			 
			msg = String.format("%s...%s...%s...%s...%s...%s...\t\t%s",fabula,"image has bad format",format,path,"Error","Run to delete of the bad file",cmd_delete); 
					 
			logger.awrite(msg); 
			 
			gl.tracex(v,msg); 
			 
			bi = Bau.to_buf_img_as_error(owner.getBounds()); 

			if(bi != null) 
			{ 
				msg = String.format("%s...%s...%s...%s...%s",fabula,"re create default image ",format,path,"Ok"); 
						 
				gl.tracex(v,msg); 
			} 
			else 
			{ 
				msg =  String.format("%s...%s...%s...%s...%s",fabula,"re create default image ",format,path,"Error") ; 
						 
				gl.tracex(v,msg); 
			 
				return false; 
			} 
		} 
		 
		if(bi.getWidth() > 320 && bi.getHeight() > 200) 
		{ 
			int scale_x = bi.getWidth() / scale_factor; 
				 
			int scale_y = bi.getHeight() / scale_factor; 
		 
			bi_scaled = ImageUtil.get_scaled_bi(bi, 
				scale_x, 
				scale_y, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true); 
		} 
		else 
			bi_scaled = bi; 
		 
		 
		 
		if(bi_scaled == null) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s",fabula,"get bi","Error")); 
			 
			return false; 
		} 
		 
		owner.setImg_type(format); 
			 
		owner.setImage(bi_scaled); 
			 
		owner.setImg_desc(String.format("%s",path)); 
			 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"get bi","Ok")); 
			 
		owner.repaint(); 
			 
		return true; 
			 
	} 

	 
	 
	 
	public static boolean fit_to_img(TPanel owner) 
	{ 
		return imp_buf_img_as_is(owner,owner.getImg_desc(),true); 
	} 
	 
	 
	 
	public static boolean imp_buf_img_as_is(TPanel owner,String path,boolean  fit_to_image) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Import BI"; 
		 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"not exist","Error",path)); 
			 
			return false; 
		} 
	 	 
		String format = ImageUtil.get_bi_type(path); 
		 
		BufferedImage bi = null; 
		 
		String fileExt = Fu.get_file_extension(path); 
		 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"set format",format)); 
		 
		if (Fu.get_file_extension(path).equalsIgnoreCase("ico")) 
		{ 
			bi = Bau.to_buf_img_from_ico(path); 
			 
			format = fileExt; 
		} 
		else 
			bi = Bau.to_buf_img_from_file(path); 
		 
		if(bi == null) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"bi is null",format,"Error")); 
			 
			return false; 
		} 
		 
			Rectangle rect = owner.getBounds(); 
			 
			owner.setImg_type(format); 
			 
			owner.setImg_desc(String.format("%s",path)); 
			 
			 
			 
			if(fit_to_image) 
			{ 
				Dimension dim = new Dimension(bi.getWidth(),bi.getHeight()); 
				 
				rect.width  = dim.width; 
				 
				rect.height = dim.height; 
				 
				owner.setBounds(rect); 
				 
			} 
			 
							 
			owner.setImage(bi); 
			 
			if(owner instanceof TImage) 
			{ 
				owner.setBack_ground(CU.getAlphaColor(UIManager.getColor("Panel.background"),0)); 
			} 
			 
			owner.repaint(); 
			 
			gl.tracex(v,String.format("%s...%s...%s",fabula,"set bi on owner","Ok")); 
			 
			return true; 
			 
	}	
	
	public static boolean imp_buf_img_as_is(Orion owner,String path,boolean  fit_to_image) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Import BI"; 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tx_e(v,gl.sf("%s...%s...%s",fabula,"not exist",path)); 
			 
			return false; 
		} 
	 	 
		String 			format = ImageUtil.get_bi_type(path); 
		 
		BufferedImage 	bi = null; 
		 
		String 			fileExt = Fu.get_file_extension(path); 
		 
		if (Fu.get_file_extension(path).equalsIgnoreCase("ico")) 
		{ 
						bi = Bau.to_buf_img_from_ico(path); 
			 
						format = fileExt; 
		} 
		else 
						bi = Bau.to_buf_img_from_file(path); 
		 
		if(bi == null) 
		{ 
						gl.tx_e(v,gl.sf("%s...%s...%s",fabula,"bi is null",format)); 
			 
						return false; 
		} 
		 
			Rectangle 	rect = owner.getBounds(); 
			 
			if(fit_to_image) 
			{ 
				Dimension dim = new Dimension(bi.getWidth(),bi.getHeight()); 
				 
				rect.width  = dim.width; 
				 
				rect.height = dim.height; 
				 
				owner.setBounds(rect); 
				 
			} 
			 
						 
			owner.getImage().setImage(bi); 
			
			owner.getImage().setImageType(format); 
		
			//owner.setImg_desc(String.format("%s",path)); 
			
			 
			return true; 
			 
	}	 
	
	 
	public static boolean imp_buf_img_as_is(AtOm owner,String path,boolean  fit_to_image) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Import BI"; 
		 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"not exist","Error",path)); 
			 
			return false; 
		} 
	 	 
		String format = ImageUtil.get_bi_type(path); 
		 
		BufferedImage bi = null; 
		 
		String fileExt = Fu.get_file_extension(path); 
		 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"set format",format)); 
		 
		if (Fu.get_file_extension(path).equalsIgnoreCase("ico")) 
		{ 
			bi = Bau.to_buf_img_from_ico(path); 
			 
			format = fileExt; 
		} 
		else 
			bi = Bau.to_buf_img_from_file(path); 
		 
		if(bi == null) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"bi is null",format,"Error")); 
			 
			return false; 
		} 
		 
			Rectangle rect = owner.getBounds(); 
			 
			owner.getImgo().setImageType(format); 
			 
			//owner.setImg_desc(String.format("%s",path)); 
			 
			 
			 
			if(fit_to_image) 
			{ 
				Dimension dim = new Dimension(bi.getWidth(),bi.getHeight()); 
				 
				rect.width  = dim.width; 
				 
				rect.height = dim.height; 
				 
				owner.setBounds(rect); 
				 
			} 
			 
						 
			owner.getImgo().setImage(bi); 
			 
			/* 
			if(owner instanceof TImage) 
			{ 
				owner.setBack_ground(ColorUtil.getAlphaColor(UIManager.getColor("Panel.background"),0)); 
			} 
			*/ 
			 
			owner.repaint(); 
			 
			gl.tracex(v,String.format("%s...%s...%s",fabula,"set bi on owner","Ok")); 
			 
			return true; 
			 
	}	 
	 
	 
	 
	public static boolean imp_buf_img_as_is(PanelXml owner,String path,boolean  fit_to_image) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Import BI"; 
		 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"not exist","Error",path)); 
			 
			return false; 
		} 
	 	 
		String format = ImageUtil.get_bi_type(path); 
		 
		BufferedImage bi = null; 
		 
		String fileExt = Fu.get_file_extension(path); 
		 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"set format",format)); 
		 
		if (Fu.get_file_extension(path).equalsIgnoreCase("ico")) 
		{ 
			bi = Bau.to_buf_img_from_ico(path); 
			 
			format = fileExt; 
		} 
			else 
			bi = Bau.to_buf_img_from_file(path); 
		 
		if(bi == null) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"bi is null",format,"Error")); 
			 
			return false; 
		} 
		 
				Rectangle rect = owner.getBounds(); 
			 
				owner.getImgo().setImageType(format); 
			 
			if(fit_to_image) 
			{ 
				Dimension dim = new Dimension(bi.getWidth(),bi.getHeight()); 
				 
				rect.width  = dim.width; 
				 
				rect.height = dim.height; 
				 
				owner.setBounds(rect); 
				 
			} 
			 
							 
				owner.getImgo().setImage(bi); 
			 
				owner.repaint(); 
			 
				gl.tracex(v,String.format("%s...%s...%s",fabula,"set bi on owner","Ok")); 
			 
				return true; 
			 
	}	 
	 
	 
	public static String to_hex_string_from_buffered_image(BufferedImage image , String image_type) 
	{ 
		byte[] bi = to_byte_array_from_buffered_image(image, image_type); 
		 
		return to_hex_str_from_byte_array(bi); 
	}	 

		 
	public static BufferedImage to_buf_img_from_file(String path) 
	{ 
		BufferedImage bi = null; 
		 
		try { 
			 
			 
			bi = ImageIO.read(new File(path)); 
		} catch (IOException e) { 
			return null; 
		} 
		 
		return bi; 
	} 
	 
	 
	 
	public static BufferedImage to_buf_img_from_hex_str(String hex_source) 
	{ 
		 
		byte[] bytes = Bau.to_byte_array_from_hex_str(hex_source); 
			 
			if(bytes == null) 
				return null; 
					 
			InputStream is = new ByteArrayInputStream(bytes); 
			 
			try { 
				return ImageIO.read(is); 
			} catch (IOException e) { 
				 
				return null; 
			} 
			 
	}	 

	public static BufferedImage to_buf_img_from_byte_array(byte[] bytes) 
	{ 
			 
			if(bytes == null) 
				return null; 
					 
			InputStream is = new ByteArrayInputStream(bytes); 
			 
			try { 
				return ImageIO.read(is); 
			} catch (IOException e) { 
				 
				return null; 
			} 
			 
	}	 

	 
	  public static boolean to_file_from_buf_img(String export_file_name,BufferedImage image,String format) 
	  { 
		  Object v = new Object(){}; 
		 
		  try { 
			 
			   String file_name = export_file_name; 
			 
			   if (! Fu.isaExtension(export_file_name)) 
			   { 
				   file_name = String.format("%s.%s",export_file_name,format.toLowerCase()); 
				 
				   gl.tracex(v,String.format("Add extension to file...%s...result name is...%s",export_file_name,file_name)); 
				 
			   } 
			 
			   ImageIO.write(image,format,new File(file_name)); 
			 
		  } 
		  catch(Exception e) 
		  { 
			  return false; 
		  } 
		 
		  	  return true; 
	  } 
		 
	  public static boolean to_file_from_byte_array_param(byte[] bytes , String fileName) 
	  { 
		  Path path = Paths.get(fileName); 
		 
		  try { 
			java.nio.file.Files.write(path,bytes); 
		} catch (IOException e) { 
			return false; 
		} 
		 
		  	return true; 
	  } 

	    public static int[] to_int_array_from_byte_array(byte[] wordBytes) { 
	        int blen = wordBytes.length; 
	        int[] result = new int[(blen + 3) / 4]; 
	        for (int i = 0; i < blen; ++i) { 
	            int x = wordBytes[i] & 0xFF; 

	            if (++i < blen) { 
	                x = (x << 8) | (wordBytes[i] & 0xFF); 
	                if (++i < blen) { 
	                    x = (x << 8) | (wordBytes[i] & 0xFF); 
	                    if (++i < blen) { 
	                        x = (x << 8) | (wordBytes[i] & 0xFF); 
	                    } 
	                } 
	            } 
	            result[i >> 2] = x; 
	        } 
	        return result; 
	    } 

	 
	    public static void test_toBIFromICO(String path) 
	    { 
	    	Object v = new Object(){}; 
	    	 
			gl.tracex(v,String.format("%s...%s","Start","Ok")); 
			 
			if(!Fu.isaFile(path)) 
			{ 
				gl.tracex(v,String.format("%s...%s...%s","Invalid path",path,"Error")); 
					 
				return; 
				 
			} 
	   	 
			List<BufferedImage> images = null; 
			 
		 
	        try { 
				ImageIO.write(images.get(0), "png", new File("data/1_decode.png")); 
			} catch (IOException e) { 
	 
			} 
	  	 
	    	 
	    	BufferedImage bi = images.get(0); 
	    					 
	    	 
	    	if(bi != null) 
	    	{ 
	    		gl.tracex(v,String.format("%s...%s...%d...%d","Image not null","Ok",bi.getWidth(),bi.getHeight())); 
	    		 
	    	} 
	    	else 
	    		gl.tracex(v,String.format("%s...%s","Image is null","Error")); 
	    	 
			 
	    	 
	    } 
	 
	    public static void test_ReadFileToHexStringAndSave(String input_file,String output_file) 
	    { 
	    	Object v = new Object(){}; 
	    		    			 
	    	gl.tracex(v,String.format("%s...%s","Start","")); 
	    	 
	    	try 
	    	{ 
	    		byte[] source = Fu.getFileAsByteArrayNIO(input_file); 
	    		 
	    		gl.tracex(v,String.format("%s...%s...%d","Read from ",input_file,source.length)); 
	    		 
	    		to_file_from_byte_array(source,output_file); 
			 
				byte[] check = to_byte_array_from_hex_file(output_file); 
				 
				gl.tracex(v,String.format("%s...%s...%d","Check from ",output_file,check.length)); 
	 
				if(check.length == source.length) 
				{ 
					gl.tracex(v,String.format("%s...%d...%d...%s","Success.",source.length,check.length,"Ok")); 
				} 
				else 
					gl.tracex(v,String.format("%s...%d...%d...%s","Source not equal target",source.length,check.length,"Error")); 
			 
	    	} 
	    	catch(Exception e) 
	    	{ 
	    		gl.tracex(v,String.format("%s...%s","Error",e.getMessage().substring(0,100))); 
	    	} 
	    	 
	    	 
	    	 
	    } 
	    public static void Test_ShiftBit_1() 
	    { 
	 
	    	int a = 8; 
	    	 
	    	int mask = 8; 
	    	 
	    	int r  = (a & mask)>> 1; 
	    	 
	    	String msg = String.format("a: %d mask : %d  r : %d ", a,mask,r); 
	    	 
	    	gl.smn(msg); 
	    	 
	    } 
	 
	    public static boolean Test_Diff(String source,String target) 
	    { 
	    	try { 
				 
	    		diff(source.getBytes("cp1251"),target.getBytes("cp1251")); 
				 
				return true; 
				 
			} catch (UnsupportedEncodingException e) { 
				 
				return false; 
			} 
	    } 
	 
		 
	    public static int OFF(int target,int mask) 
	    { 
	    	return target &= ~(1<<mask); 
	    } 
	 
	    public static int ON(int target,int mask) 
	    { 
	    	return target |= (1<<mask); 
	    } 
	 
	    public static int TEST(int target,int mask) 
	    { 
	    	return (target & (1<<mask) >> mask/2); 
	    } 
	 
	 
	 
	 
	 
	    public static void Test_HexColor() 
	    { 
	    	 
	    	int i_nt =255; 
	    	 
	    	gl.smn(String.format("Input int : %d  Output hex :%s Back : %d ",i_nt,to_hex_str_from_int(i_nt),to_int_from_hex_str(to_hex_str_from_int(i_nt))));	 
	    	 
	    	i_nt =114; 
	    	 
	    	gl.smn(String.format("Input int : %d  Output hex :%s Back :%d",i_nt,to_hex_str_from_int(i_nt),to_int_from_hex_str(to_hex_str_from_int(i_nt))));	 
	    	 
	    	 
	    	Color testColor = new Color(114,113,112,111); 
	    	 
	    	gl.smn(String.format("Input color : %s Html color : %s Back %s ", 
	    							 
	    			SColor.toString(testColor), 
	    			CU.toHtmlColor(testColor), 
	    			SColor.toString(CU.fromHtmlColor(CU.toHtmlColor(testColor))) 
	    			 
	    			)); 
	    } 
	 
	 
	    public static void test_Before() 
	    { 
	    	 
	    	String test = "Ntcnjdfz aasss "; 
	    	 
	    	 List<Integer> list = new ArrayList<Integer>(); 
	    	 
	    	 int[] i_arr = to_int_array_from_byte_array(test.getBytes()); 
	    	 
	    	 gl.smn("Input length : "  + test.length()); 
	    	 
	    	 gl.smn("Input length in bytes : "  + test.getBytes().length); 
	    	 
	    	 for(int j=0;j< i_arr.length;j++) 
	    		 list.add(i_arr[j]); 
	    	 
		    	 list.forEach(obj-> 
		    	 { 
		    		 gl.sm( 
		    				 obj.toString()+ " " 
		    				 ); 
		    		 
		    		 
		    	 } 
		    	 ); 
	    	 
	    } 
	 
	 
	    public static String diff(byte[] arr,byte[] trr) 
	    { 
	    	 
	    	StringBuilder sb = new StringBuilder(); 
	    	 
	    	int i = gl.E_EMPTY; 
	    	 
	    	int trr_length = trr.length; 
	    	 
	    	for(byte b : arr) 
			{ 
	    		 
				byte t = (byte)0x0; 
				 
				if(i<trr.length) 
				   t = trr[i]; 
				 
				if( b != t) 
				{ 
					sb.append(String.format("%d %02X %02X %s",i, b,t,(b==t))); 
					 
					sb.append("\n"); 
				} 
				 
					i++; 
			} 
	    	 
	    			gl.smn(sb.toString()); 
	    			 
	    			return sb.toString(); 
	    } 
	 
	 
	    public static int toIntFromArrayOfInt(int [] input_array) 
	    { 
	    	if (input_array.length == gl.E_EMPTY) 
	    	{ 
	    		return gl.E_ERROR; 
	    	} 
	    	 
	    	 
	    	StringBuilder sb = new StringBuilder(); 
			 
			for(int i=0; i < input_array.length;i++) 
			{ 
				sb.append(input_array[i]); 
			} 
						 
			return Bau.to_int_from_binary_str(sb.toString()); 
	    	 
	    } 
	    
	    

	    public static int countOfEnabledBits(long l,int length)
	    {
	    	int count = gl.E_EMPTY;
	    	
	    	for(int i=0; i <= length;i++)
	    	{
	    		if
	    		(
	    				 ((l >> i) & 1 ) != gl.E_EMPTY 
	    		)
	    		{
	    				count++;
	    		}
	    	}
	    	
	    		return count;
	    }

	 
	 
	    public static void test_ByteEquals() 
	    { 
	    	byte[] a = {(byte)0x04,(byte)0x03,(byte)0x02,(byte)0x01,}; 
	    	 
	    	//byte[] b = {(byte)0x04,(byte)0x07,(byte)0x02,(byte)0x01,}; 
	    	 
	    	byte[] b = {(byte)0x07,(byte)0x02,(byte)0x01}; 
	    	 
	    	//String s = diff(a,b); 
	    	 
	    	//gl.sm(s); 
	    	 
	    } 
	 
	    public static void test_ShowImageTypes() 
	    { 
	        String[] types = ImageIO.getReaderFileSuffixes(); 
	        System.out.println("This JRE supports image types:"); 
	        for (String type : types) { 
	            System.out.println("Type: " + type); 
	        } 
	    } 
	 
	    public static void test_Binary(String value) 
	    { 
	    	int i_chk = to_int_from_binary_str(value); 
	    	 
	    	String msg = String.format("From bin str:  %s Integer output: %4d",value,i_chk); 
	    	 
	    	gl.smn(msg); 
	    	 
	    	String chk = String.format("To   bin str:  %s ",to_bin_str_from_int_enrich(i_chk)); 
	    	 
	    	gl.smn(chk); 
	    	 
	    	 
	    } 
	 
	    public static void test_Binary_Long(String value) 
	    { 
	    	long 	i_chk = to_long_from_binary_str(Su.exclude(value,"[","]")); 
	    	 
	    			gl.sfn("Input binary string:  %s Integer output: %d",value,i_chk); 
	     	 
	    			gl.sfn("To    binary string:  %s Zero : %s ",to_bin_str_from_long_enrich(i_chk),to_bin_str_from_long(0)); 
	    	
	    	long 	l = to_long_from_binary_str(Su.exclude("1000 0000 0000 0000 0000 0000 0000 0010","[","]"));
	    	
	    			gl.sfn("Test...%s...%d...%s",to_bin_str_from_long_enrich(i_chk & (l)),l,((l >> 31)&1) != 0); 
	    	
	    	 
	    } 
	 
	 
	    public static void test_BinConvert(int v) 
	    { 
	    	 
	    	int [] ref = {0,0,0,0,0,0,0,0}; 
	    	 
	    	to_bit_array_from_int(v,ref); 
	    	 
	    	//String msg = String.format("Input : %d Output : %d",v,ByteArrayUtil.toIntFromArrayOfInt(ref)); 
	    	 
	    	//gl.smn(msg); 
	    	 
	    	for(int i=0;i<ref.length;i++) 
	    		gl.sm(ref[i]); 
	    	 
	    } 
	 
	    public static void test_Between(String value,String open,String close)
	    {
	    	
			gl.sfn("Input...%s...Result...%s",value,Su.exclude(value, open, close));
			
	    }
	    

	    public static String get_mask_for_to_string(int value)
	    {
	  	  return gl.replicate("%s " , Bau.countOfEnabledBits(value,Group.RESULT_DEALS));
	    }

	    public static void test_countOf()
	    {
	    	int m_test = 15;
	    	
	    	gl.sfn("Input...%d...Result...%d",m_test,Bau.countOfEnabledBits(m_test,32));
	    	
	    	gl.sfn("Input...%d...Mask...[%s]",m_test,get_mask_for_to_string(m_test));
			
			
	    }
	    
	    
	    public static void main(String[] args) 
	    { 
	    	test_countOf();
	    	
	    	//test_Between("[22]1[23]4[...25...]16251 1 2","[","]");
	    	
	    	//test_Binary_Long("[m3] 1111 [m7] 1111 [m14] 1111 [m21] 1111 [m33] 1111 [m55] 1111 [m100] 1111 [m200] 1111"); 
	    	 
	    	//test_Binary("00001110"); 
	    	 
	    	//test_Binary("00001100"); 
	    	 
	    	//test_BinConvert(128); 
	    	 
	    	//test_BinConvert(255); 
	    	 
	    	//test_BinConvert(8); 
	    	 
	    	//Test_ShiftBit_1(); 
	    	 
	    	/* 
	    	String source = ""; 
	    	 
	    	String target = ""; 
	    	 
			try { 
				source = FileUtil.getFileAsStringScanner("e:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\app\\ConvolveApp.java"); 
				 
				target = FileUtil.getFileAsStringScanner("e:\\bin\\spider\\TestReduce\\out\\ap\\app\\ConvolveApp.java"); 
				 
			} catch (IOException e) { 
				 
				gl.smn("Exception : " + e.toString()); 
				 
			} 
			 
			if(source.equals(target)) 
			{ 
				gl.smn("Equals."); 
			} 
	    			 
	    	Test_Diff(source,target); 
	    	 
	    	 */ 
	    	 
	    } 
	 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
