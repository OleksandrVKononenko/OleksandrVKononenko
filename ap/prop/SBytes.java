 
package ap.prop; 

import java.awt.image.BufferedImage; 
import java.io.*; 
import java.util.List; 
import javax.imageio.ImageIO; 
import ap.global.gl; 
import ap.btn.TPanel; 
import ap.utils.Bau; 
import ap.utils.Fu;import ap.utils.ImageUtil; 



@SuppressWarnings("serial") 
public class SBytes extends SProperty { 

	private SText source ; 
	 
	private SText type; 
	 
	private SText description; 
	 
	 
	public byte[] getData() 
	{ 
				Object v  = new Object(){}; 
		 
		if(this.getSource().getText().length() == gl.E_EMPTY) 
		{ 
				if(this.isDebug()) 
				gl.tracex(v,String.format("%s...%s","Text source is empty","Error")); 
			 
				return null; 
		} 
		 
		 
		 		byte[] tmp = Bau.to_byte_array_from_hex_str(this.getSource().getText()); 
		 
		 if(tmp == null || tmp.length==gl.E_EMPTY) 
		 { 
			 
			 	gl.tracex(v,String.format("%s...%s","Byte array is empty","Error")); 
			 
			 	return null; 
		 } 
		 
		 		return tmp; 
	} 
	 
	public BufferedImage getBufferedImage() 
	{ 
		byte[] tmp = this.getData(); 
		 
		if(tmp == null) 
			return null; 
				 
		InputStream is = new ByteArrayInputStream(tmp); 
		 
		try { 
			return ImageIO.read(is); 
		} catch (IOException e) { 
			 
			return null; 
		} 
		 
	} 
	 
	 
	public SText getType() { 
		return type; 
	} 

	public void setType(SText type) { 
		this.type = type; 
	} 

	public SText getDescription() { 
		return description; 
	} 

	public void setDescription(SText description) { 
		this.description = description; 
	} 

	public SText getSource() { 
		return source; 
	} 

	public void setSource(SText source) { 
		this.source = source; 
	} 

	public SBytes() { 
		super("data"); 
	} 

	public SBytes(String name) { 
		super(name); 
				 
		 
	} 

	public SBytes(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SBytes(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%s%s%s", 
				this.getType().toString(), 
				this.getDescription().toString(), 
				this.getSource().toString() 
				); 
	} 
	 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		this.source = new SText("img_data"); 
		 
		this.description = new SText("img_desc"); 
		 
		this.type = new SText("img_type"); 
		 
		if (! 
			 ( 
			  this.getSource().parse(value)   		&& 
			  this.getDescription().parse(value)   	&& 
			  this.getType().parse(value) 
			 ) 
			) 
			 
			return false; 
				 
		try 
		{ 
			 
			return true; 
			 
		} 
		catch(NumberFormatException e) 
		{ 
			if(this.isDebug()) 
			gl.tracex(v,String.format("%s...%s...%s","NumberFormatException","Error",e.toString())); 
			 
			return false; 
		} 
		 
	} 
	 
 
	 
	public static void test_BytesFromFile(String path) 
	{ 
		Object v  = new Object(){}; 
		 
		SBytes bytes = new SBytes(); 
		 
			   bytes.setDebug(true); 
		 
		List<String> list = Fu.getListOfStringFromFile(path); 

		if(list != null && list.size() != gl.E_EMPTY) 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Ok",list.size())); 
		else 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Error"));			 
		 
		list.forEach(a-> 
		{ 
			if(bytes.parse(a)) 
			{ 
				gl.tracex(v,String.format("%s...%s...%s","Parse data","Ok",bytes.toString())); 
				 
			} 
			else 
			{ 
				gl.tracex(v,String.format("%s...%s","Parse data","Error")); 
			} 
		 
		} 
		); 
		 
	} 
	 
	 
	 
	 
	public static void test_BytesFromFileBufferedImage(String path) 
	{ 
		Object v  = new Object(){}; 
		 
		SBytes bytes = new SBytes(); 
		 
			   bytes.setDebug(true); 
		 
		List<String> list = Fu.getListOfStringFromFile(path); 

		if(list != null && list.size() != gl.E_EMPTY) 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Ok",list.size())); 
		else 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Error"));			 
		 
		list.forEach(a-> 
		{ 
			if(bytes.parse(a)) 
			{ 
				gl.tracex(v,String.format("%s...%s...%s","Parse data","Ok",bytes.toString())); 
				 
				BufferedImage bi = bytes.getBufferedImage(); 
				 
				String img_type = "gif"; 
				 
				if(bi != null) 
				{ 
					gl.tracex(v,String.format("%s...%s","get BufferedImage","Ok")); 
					 
					String mask = String.format("images/file_%d_step_1.%s",gl.getRandomInt(10),img_type); 
					 
					try { 
						ImageIO.write(bi,img_type,new File(mask)); 
					} catch (Exception e) { 
						gl.tracex(v,String.format("%s...%s","get BufferedImage","Exception")); 
					} 
					 
					// Re-check 
					 
					byte[] chk_bytes =  Bau.to_byte_array_from_buffered_image(bi,img_type); 
					 
					if(chk_bytes != null) 
						gl.tracex(v,String.format("%s...%s","Re-check get bytes from bi","Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s","Re-check get bytes from bi","Error")); 
					 
					String check_mask = String.format("images/file_%d_step_2.%s",gl.getRandomInt(10),img_type); 
							 
					if (!Bau.to_file_from_byte_array(chk_bytes, check_mask)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check get bytes from bi to file ",check_mask,"Error")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check get bytes from bi to file ",check_mask,"Ok")); 
						 
					String check_mask_hex = String.format("images/file_%d_step_3.%s",gl.getRandomInt(10),"hex"); 
					 
					if (Bau.to_file_from_str(Bau.to_hex_string_from_buffered_image(bi,img_type),check_mask_hex)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_hex,"Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_hex,"Error")); 
					 
					String check_mask_bi_to_hex = String.format("images/file_%d_step_4.%s",gl.getRandomInt(10),"hex"); 
					 
					if (Bau.to_file_from_str(Bau.to_hex_string_from_buffered_image(bi,img_type),check_mask_bi_to_hex)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_bi_to_hex,"Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_bi_to_hex,"Error")); 
					 
					 
					//StringUtil.getDiffOfStringsSuite(bytes.getSource().getText(),ByteArrayUtil.bi2Hex(bi,img_type)); 
					 
				} 
				else 
					gl.tracex(v,String.format("%s...%s","get BufferedImage","Error")); 
			} 
			else 
			{ 
				gl.tracex(v,String.format("%s...%s","Parse data","Error")); 
			} 
		 
		} 
		); 
	} 

	 
	public static void test_BufferedImage(String path) 
	{ 
		Object v  = new Object(){}; 
		 
		SBytes bytes = new SBytes(); 
		 
			   bytes.setDebug(true); 
		 
		List<String> list = Fu.getListOfStringFromFile(path); 

		if(list != null && list.size() != gl.E_EMPTY) 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Ok",list.size())); 
		else 
		gl.tracex(v,String.format("%s...%s...%d","Load strings from file ","Error"));			 
		 
		list.forEach(a-> 
		{ 
			if(!bytes.parse(a)) 
			{ 
				gl.tracex(v,String.format("%s...%s","Parse of data","Error")); 
				 
				return; 
			} 
			 
				gl.tracex(v,String.format("%s...%s...%s","Parse data","Ok",bytes.toString())); 
				 
				BufferedImage bi = Bau.to_buf_img_from_hex_str(bytes.getSource().getText()); 
				 
				 
				String format = ImageUtil.get_bi_type(bi); 
				 
				if(format != null) 
				gl.tracex(v,String.format("%s...%s","Image type format",format)); 
				else 
				gl.tracex(v,String.format("%s...%s...%s","Image type format",format,"Error")); 
				 
				String img_type = "gif"; 
				 
				if(bi == null) 
				{ 
					gl.tracex(v,String.format("%s...%s","get BufferedImage","Error")); 
					 
					return; 
					 
				} 
				 
					TPanel sp = new TPanel(); 
					 
						   sp.setImage(bi); 
						 
						   sp.setImg_type("gif"); 
						 
					String mask = String.format("images/file_%d_bi_1.%s",gl.getRandomInt(10),img_type); 

					try { 
						ImageIO.write(bi,img_type,new File(mask)); 
					} catch (Exception e) { 
						gl.tracex(v,String.format("%s...%s","get BufferedImage","Exception")); 
					} 

					String mask_hex = String.format("images/file_%d_bi_2.%s",gl.getRandomInt(10),"hex"); 
					 
					if(				Bau.to_file_from_str( 
									Bau.to_hex_string_from_buffered_image(sp.getImage(),img_type), 
									mask_hex) 
					   ) 
					{ 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex,"Ok")); 
						 
					} 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex,"Error")); 
					 
					 
					String mask_hex_rep = String.format("images/file_%d_bi_3.%s",gl.getRandomInt(10),"hex"); 
					 
					if(				Bau.to_file_from_str( 
									sp.getImageHex(), 
									mask_hex_rep) 
					   ) 
					{ 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex_rep,"Ok")); 
						 
					} 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex_rep,"Error")); 
					 
					 
					 
					 
				 
				/* 
				if(bi != null) 
				{ 
					gl.tracex(v,String.format("%s...%s","get BufferedImage","Ok")); 
					 
					String mask = String.format("images/file_%d_step_1.%s",gl.getRandomInt(10),img_type); 
					 
					try { 
						ImageIO.write(bi,img_type,new File(mask)); 
					} catch (Exception e) { 
						gl.tracex(v,String.format("%s...%s","get BufferedImage","Exception")); 
					} 
					 
					// Re-check 
					 
					byte[] chk_bytes =  ByteArrayUtil.getBytesArrayFromBI(bi,img_type); 
					 
					if(chk_bytes != null) 
						gl.tracex(v,String.format("%s...%s","Re-check get bytes from bi","Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s","Re-check get bytes from bi","Error")); 
					 
					String check_mask = String.format("images/file_%d_step_2.%s",gl.getRandomInt(10),img_type); 
							 
					if (!ByteArrayUtil.saveBAToFile(chk_bytes, check_mask)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check get bytes from bi to file ",check_mask,"Error")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check get bytes from bi to file ",check_mask,"Ok")); 
						 
					String check_mask_hex = String.format("images/file_%d_step_3.%s",gl.getRandomInt(10),"hex"); 
					 
					if (ByteArrayUtil.toHexFile(ByteArrayUtil.bi2Hex(bi,img_type),check_mask_hex)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_hex,"Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_hex,"Error")); 
					 
					String check_mask_bi_to_hex = String.format("images/file_%d_step_4.%s",gl.getRandomInt(10),"hex"); 
					 
					if (ByteArrayUtil.toHexFile(ByteArrayUtil.bi2Hex(bi,img_type),check_mask_bi_to_hex)) 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_bi_to_hex,"Ok")); 
					else 
						gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",check_mask_bi_to_hex,"Error")); 
					 
					 
					//StringUtil.getDiffOfStringsSuite(bytes.getSource().getText(),ByteArrayUtil.bi2Hex(bi,img_type)); 
					 
				} 
				else 
					gl.tracex(v,String.format("%s...%s","get BufferedImage","Error")); 
			 
				*/ 
				 
				 
		} 
		); 
	} 
	 
	public static void test_imageTypeFormat(String[] path) 
	{ 
		Object v = new Object(){}; 
		 
		for(String s : path) 
		{ 
			 
			String format = ImageUtil.get_bi_type(s); 
			 
			if(format != null) 
			gl.tracex(v,String.format("%s...%s...%s","Check image type format",s,format)); 
			else 
			gl.tracex(v,String.format("%s...%s...%s...%s","Image type format",s,"Error",format)); 
		 
		} 
		 
	} 
	 
	public static void main(String[] args) { 
		 
		//String path = "e:/bin/eclipse/wsp/organizer/data/byte_1302_1.txt"; 
		 
		String[] path = new String[]{ 
				"e:/bin/eclipse/wsp/organizer/images/Test.gif", 
				"e:/bin/eclipse/wsp/organizer/images/ACE.ico", 
				"e:/bin/eclipse/wsp/organizer/images/ACE_Racer.bmp", 
				"e:/bin/eclipse/wsp/organizer/images/start.jpg", 
				"e:/bin/eclipse/wsp/organizer/images/test.png" 
		}; 
		 
		 
		//test_BytesFromFile(path); 
		 
		//test_BytesFromFileBufferedImage(path); 
		 
		//test_BufferedImage(path); 
		 
		test_imageTypeFormat(path); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
