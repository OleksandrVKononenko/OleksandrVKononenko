 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.prop; 

import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.Serializable; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import javax.imageio.ImageIO; 

import ap.global.gl; 
import ap.btn.TPanel; 
import ap.utils.Bau; 
import ap.utils.Fu; 

public class BaseProperty implements Serializable { 

	private static final long serialVersionUID = -288464268026952455L; 

	private String payload; 
	 
	private SInt pid; 
	 
	private SInt gid; 
	 
	private SInt id; 
	 
	private SInt zorder; 
	 
	 
		 
	private SColor background; 
	 
	private SColor gradient; 
	 
	private SBounds bounds; 
	 
	private SBounds original_bounds; 
	 
	private SDimension child_dim; 
	 
	private SAreaManager area_manager; 
	 
	private SText type; 
	 
	private SText  text; 
	 
	private SFont font; 
	 
	private SColor text_color; 
	 
	private SColor grid_color; 
	 
	private SColor up_color; 
	 
	private SColor down_color; 
		 
	private SImage image; 
	 
	private SBounds align; 
	 
	private SText  border; 
	 
	private SInt state; 
	 
	private SInt base; 
	 
	private SInt offset; 
	 
	private SText  action; 
	 
	private SText  data; 
	 
	private SText  function; 
	 
	private SText  tool_tip; 
	 
	private SText  name; 
	 
	private SText  model; 	 
	 	 
	 
	 
	public SText getModel() { 
		return model; 
	} 

	public void setModel(SText model) { 
		this.model = model; 
	} 

	public SInt getBase() { 
		return base; 
	} 

	public void setBase(SInt base) { 
		this.base = base; 
	} 

	public SInt getOffset() { 
		return offset; 
	} 

	public void setOffset(SInt offset) { 
		this.offset = offset; 
	} 

	public SText getName() { 
		return name; 
	} 

	public void setName(SText name) { 
		this.name = name; 
	} 

	public static long getSerialversionuid() { 
		return serialVersionUID; 
	} 

	public SText getTool_tip() { 
		return tool_tip; 
	} 

	public void setTool_tip(SText tool_tip) { 
		this.tool_tip = tool_tip; 
	} 

	public SColor getUp_color() { 
		return up_color; 
	} 

	public void setUp_color(SColor up_color) { 
		this.up_color = up_color; 
	} 

	public SColor getDown_color() { 
		return down_color; 
	} 

	public void setDown_color(SColor down_color) { 
		this.down_color = down_color; 
	} 

	public SText getFunction() { 
		return function; 
	} 

	public void setFunction(SText function) { 
		this.function = function; 
	} 

	public SText getData() { 
		return data; 
	} 

	public void setData(SText data) { 
		this.data = data; 
	} 

	public SText getAction() { 
		return action; 
	} 

	public void setAction(SText action) { 
		this.action = action; 
	} 

	public SInt getState() { 
		return state; 
	} 

	public void setState(SInt state) { 
		this.state = state; 
	} 

	public SBounds getAlign() { 
		return align; 
	} 

	public void setAlign(SBounds align) { 
		this.align = align; 
	} 

	public SText getBorder() { 
		return border; 
	} 

	public void setBorder(SText border) { 
		this.border = border; 
	} 

	 
	public SImage getImage() { 
		return image; 
	} 

	public void setImage(SImage image) { 
		this.image = image; 
	} 

	 
	public SColor getGrid_color() { 
		return grid_color; 
	} 

	public void setGrid_color(SColor grid_color) { 
		this.grid_color = grid_color; 
	} 

	public SColor getText_color() { 
		return text_color; 
	} 

	public void setText_color(SColor text_color) { 
		this.text_color = text_color; 
	} 

	public SText getText() { 
		return text; 
	} 

	public void setText(SText text) { 
		this.text = text; 
	} 

	public SFont getFont() { 
		return font; 
	} 

	public void setFont(SFont font) { 
		this.font = font; 
	} 

	public SColor getGradient() { 
		return gradient; 
	} 

	public void setGradient(SColor gradient) { 
		this.gradient = gradient; 
	} 

	public SText getType() { 
		return type; 
	} 

	public void setType(SText type) { 
		this.type = type; 
	} 

	public SInt getPid() { 
		return pid; 
	} 

	public void setPid(SInt pid) { 
		this.pid = pid; 
	} 

	public SInt getGid() { 
		return gid; 
	} 

	public void setGid(SInt gid) { 
		this.gid = gid; 
	} 

	public SInt getId() { 
		return id; 
	} 

	public void setId(SInt id) { 
		this.id = id; 
	} 

	public SInt getZorder() { 
		return zorder; 
	} 

	public void setZorder(SInt zorder) { 
		this.zorder = zorder; 
	} 

	 

	public SColor getBackground() { 
		return background; 
	} 

	public void setBackground(SColor background) { 
		this.background = background; 
	} 

	public SBounds getBounds() { 
		return bounds; 
	} 

	public void setBounds(SBounds bounds) { 
		this.bounds = bounds; 
	} 

	public SBounds getOriginal_bounds() { 
		return original_bounds; 
	} 

	public void setOriginal_bounds(SBounds original_bounds) { 
		this.original_bounds = original_bounds; 
	} 

	public SDimension getChild_dim() { 
		return child_dim; 
	} 

	public void setChild_dim(SDimension child_dim) { 
		this.child_dim = child_dim; 
	} 

	public SAreaManager getArea_manager() { 
		return area_manager; 
	} 

	public void setArea_manager(SAreaManager area_manager) { 
		this.area_manager = area_manager; 
	} 

	 
	 
	public String getPayload() { 
		return payload; 
	} 

	public void setPayload(String payload) { 
		this.payload = payload; 
	} 

	 

	public BaseProperty() { 
		 
		pid = new SInt("pid",gl.E_ERROR); 
		 
		gid = new SInt("gid"); 
		 
		id = new SInt("id"); 
		 
		name = new SText("name"); 
				 
		zorder = new SInt("zorder"); 
		 
		background = new SColor("background"); 
		 
		gradient = new SColor("gradient"); 
		 
		bounds = new SBounds("bounds"); 
		 
		original_bounds = new SBounds("original_bounds"); 
		 
		child_dim = new SDimension("child_dim"); 
		 
		area_manager = new SAreaManager("area_manager"); 
		 
		type = new SText("type"); 
		 
		text = new SText(); 
		 
		font = new SFont(); 
		 
		text_color = new SColor("text_color"); 
		 
		grid_color = new SColor("grid_color"); 
		 
		up_color = new SColor("up_color"); 
		 
		down_color = new SColor("down_color"); 
		 
		image = new SImage(); 
		 
		align = new SBounds("align"); 
		 
		border = new SText("border"); 
		 
		state = new SInt("state"); 
		 
		action = new SText("action"); 
		 
		data = new SText("data"); 
		 
		function = new SText("function"); 
		 
		tool_tip = new SText("tool_tip"); 
		 
		base = new SInt("base"); 
		 
		offset = new SInt("offset"); 
		 
		model = new SText("model"); 
		 
	} 

	public BaseProperty(String payload) { 
		 
		this(); 
		 
		this.setPayload(payload); 
	} 

	public boolean read(String payload) 
	{ 
		this.setPayload(payload); 
		 
		return this.read(); 
	} 
	 
	public boolean read() 
	{ 
		Object v  = new Object(){}; 
		 
		String s = this.getPayload(); 
		 
				 
		boolean bl_result = ( 
				 
				pid.parse(s) 		&& 
				 
				gid.parse(s) 		&& 
				 
				id.parse(s) 		&& 
				 
				name.parse(s) 		&& 
				 
				zorder.parse(s) 	&& 
				 
				background.parse(s) && 
				 
				gradient.parse(s) 	&& 
				 
				bounds.parse(s) 	&& 
				 
				original_bounds.parse(s) 	&& 
				 
				child_dim.parse(s) 			&& 
				 
				area_manager.parse(s) 		&& 
				 
				type.parse(s) 		&& 
				 
				text.parse(s) 		&& 
				 
				font.parse(s) 		&& 
				 
				text_color.parse(s) && 
				 
				grid_color.parse(s) && 
				 
				up_color.parse(s) 	&& 
				 
				down_color.parse(s) && 
								 
				image.parse(s)  	&& 
				 
				align.parse(s) 		&& 
				 
				border.parse(s) 	&& 
				 
				state.parse(s)  	&& 
				 
				action.parse(s) 	&& 
				 
				data.parse(s) 		&& 
				 
				function.parse(s) 	&& 
				 
				tool_tip.parse(s)   && 
				 
				model.parse(s) 
								 
				); 
		 
		// Optional params. 
		 
		boolean bl_dop_result = ( base.parse(s) && offset.parse(s)); 
 
			 
		if(!bl_result) 
			gl.tracex(v,String.format("%s...%s",gl.S_WARN,"Some errors while parsing.")); 
				 
		if(!bl_dop_result) 
			gl.tracex(v,String.format("%s...%s",gl.S_WARN,"Some errors while optional params parsing.")); 

		 
			return true; 
		 
	} 
	 
	public boolean write() 
	{ 
		return true; 
	} 
	 
	public String toString() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
			sb.append(pid.toString()); 
		 
			sb.append(gid.toString()); 
		 
			sb.append(id.toString()); 
			 
			sb.append(type.toString()); 
			 
			sb.append(name.toString()); 
			 
			sb.append(function.toString()); 
			 
			sb.append(model.toString()); 
			 
			sb.append(text.toString()); 
			 
			sb.append(tool_tip.toString()); 

			sb.append(zorder.toString()); 
		 
			sb.append(background.toString()); 
		 
			sb.append(gradient.toString()); 
			 
			sb.append(bounds.toString()); 
		 
			sb.append(original_bounds.toString()); 
		 
			sb.append(child_dim.toString()); 
		 
			sb.append(area_manager.toString()); 

			sb.append(font.toString()); 
			 
			sb.append(text_color.toString()); 
			 
			sb.append(grid_color.toString()); 
			 
			sb.append(up_color.toString()); 
			 
			sb.append(down_color.toString()); 
									 
			sb.append(image.toString()); 
			 
			sb.append(align.toString()); 
			 
			sb.append(border.toString()); 
			 
			sb.append(state.toString()); 
			 
			sb.append(action.toString()); 
			 
			sb.append(data.toString()); 
			 
			 
			 
			return sb.toString(); 
		 
	} 

	public static void doTest_toString() { 
		 
		BaseProperty prop = new BaseProperty(); 
		 
		gl.smn("Test toString() : " + prop.toString()); 
		 
		 
	} 
	public static void test_checkParse_full(String home) { 

		Object v = new Object() {}; 

		int error_count = 0; 
		 
		String source = ""; 

		try { 

			source = Fu.get_str_from_file(home); 

			String arr[] = source.split(System.lineSeparator()); 

			BaseProperty jkp = new BaseProperty(); 
			 
			for (int i = 0; i < arr.length; i++) { 
				//raw_map.put(i, arr[i]); 

				if (!jkp.read(arr[i])) 
					error_count++; 
				else 
				{ 
					 
					gl.tracex(v,String.format("%s...%s", gl.nr("Data parsing"),jkp.toString())); 
					 
					TPanel sp = new TPanel(jkp); 
					 
					gl.tracex(v,String.format("%s...%s", gl.nr("Get instance"),sp.toString())); 
					 
					if(sp.toString().equalsIgnoreCase(jkp.toString())) 
					  gl.tracex(v,String.format("%s...", gl.nr("Success."))); 
					else 
					 gl.tracex(v,String.format("%s...", gl.nr("Error prone."))); 
					 
					 
					 
					BufferedImage bi = jkp.getImage().getSource().getBufferedImage(); 
					 
					sp.setImage(bi); 
					 
					if(bi == null) 
					{ 
						gl.tracex(v,String.format("%s...%s","get BufferedImage","Error")); 
						 
						return; 
					} 
						gl.tracex(v,String.format("%s...%s","get BufferedImage","Ok")); 
						 
						 
					// Save gif. 
						 
					String img_type = sp.getImg_type(); 
					 
					String mask = String.format("images/file_%d_pp_1.%s",gl.getRandomInt(10),img_type); 

						try { 
							ImageIO.write(sp.getImage(),img_type,new File(mask)); 
						} catch (Exception e) { 
							gl.tracex(v,String.format("%s...%s...%s","write to ",img_type,"Exception")); 
						} 
					 
					// Save hex. 
				 
				String mask_hex = String.format("images/file_%d_bi_2.%s",gl.getRandomInt(10),"hex"); 
				 
					if(			Bau.to_file_from_str( 
								Bau.to_hex_string_from_buffered_image(sp.getImage(),img_type), 
								mask_hex) 
							) 
					{ 
							gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex,"Ok")); 
					 
					} 
						else 
							gl.tracex(v,String.format("%s...%s...%s","Re-check to hex file ",mask_hex,"Error")); 
				 
				 
				 
				 
					 

			} 

			if (error_count == gl.E_EMPTY) 
			{ 
				gl.tracex(v,String.format("%s...%s", gl.nr("Test done"), "Ok")); 
			} 
			else 
				gl.tracex(v, String.format("%s...%s...%d",gl.nr("Test done with"), "Errors.", error_count)); 

		} // for 
		}// try 
		catch (Exception e) { 
			 
			gl.tracex(v,String.format("%s...%s...%s", "Exception", "Error.",e.toString())); 

		} 

	} 
	 
	public static void test_checkParse(String home) { 

		Object v = new Object() {}; 

		int error_count = 0; 
		 
		String source = ""; 

		try { 

			source = Fu.get_str_from_file(home); 

			String arr[] = source.split(System.lineSeparator()); 

			BaseProperty jkp = new BaseProperty(); 
			 
			for (int i = 0; i < arr.length; i++) { 
				//raw_map.put(i, arr[i]); 

				if (!jkp.read(arr[i])) 
					error_count++; 
				else 
				{ 
					 
					gl.tracex(v,String.format("%s...%s", gl.nr("Data parsing"),jkp.toString())); 
					 
					TPanel sp = new TPanel(jkp); 
					 
					BufferedImage bi = jkp.getImage().getSource().getBufferedImage(); 
					 
					sp.setImage(bi); 
					 
					 
					gl.tracex(v,String.format("%s...%s", gl.nr("Get instance"),sp.toString())); 
					 
					if(sp.toString().equalsIgnoreCase(jkp.toString())) 
					  gl.tracex(v,String.format("%s...", gl.nr("Success."))); 
					else 
					 gl.tracex(v,String.format("%s...", gl.nr("Error prone."))); 
					 
				} // row read 

			if (error_count == gl.E_EMPTY) 
			{ 
				gl.tracex(v,String.format("%s...%s", gl.nr("Test done"), "Ok")); 
			} 
			else 
				gl.tracex(v, String.format("%s...%s...%d",gl.nr("Test done with"), "Errors.", error_count)); 

		} // for 
		}// try 
		catch (Exception e) { 
			 
			gl.tracex(v,String.format("%s...%s...%s", "Exception", "Error.",e.toString())); 

		} 

	} 
	 
	 
	public static void main(String[] args) { 
		 
		//String source = "data/a_v2.txt";//"data/a_test_template.txt"; 
		 
		String source = "data/byte_1302_1.txt";//"data/a_test_template.txt"; 
		 
		//test_checkParse(source); 
		 
		test_checkParse(source); 
		 
		//doTest_toString(); 
		 
		 
		 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
