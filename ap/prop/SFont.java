 
package ap.prop; 

import java.awt.Color; 
import java.awt.Font; 

import ap.global.gl; 
import ap.utils.CU; 
import ap.utils.Su; 

public class SFont extends SProperty { 

	 
	private static final long serialVersionUID = 1L; 

	private String 	fontName; 
	 
	private int 	fontBold; 
	 
	private int 	fontSize; 
	 
	private Font 	font; 
	 
	 
	 
	public Font getFont() { 
		 
		 
		return font; 
	} 

	public void setFont(Font font) { 
		this.font = font; 
	} 

	public String getFontName() { 
		return fontName; 
	} 

	public void setFontName(String fontName) { 
		this.fontName = fontName; 
	} 

	public int getFontBold() { 
		return fontBold; 
	} 

	public void setFontBold(int fontBold) { 
		this.fontBold = fontBold; 
	} 

	public int getFontSize() { 
		return fontSize; 
	} 

	public void setFontSize(int fontSize) { 
		this.fontSize = fontSize; 
	} 

	public SFont() { 
		 super("font"); 
	} 

	public SFont(String name) { 
		super(name); 
		 
	} 

	public SFont(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SFont(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 
	 
	public String getText() 
	{ 
		return this.toStringShort(); 
	} 
	 
	public static String toString(Font font) 
	{ 
		Font opFont = font; 
		 
		if(opFont == null) 
			opFont = new Font("Tahoma",0,6); 
		 
		return String.format("%s,%d,%d",opFont.getFontName(),opFont.getStyle(),opFont.getSize()); 
		 
	} 
	 
	public String toStringShort() 
	{ 
		return this.toStringValue(); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		//for not parsing case. 
		if(this.getValue_map().size()==gl.E_EMPTY) 
			return String.format("%s=%s%s",this.getName(),this.toStringShort(),this.getAttr_delimeter()); 
		else 
			return super.toString(); 
			 
	} 
	 
	public static Font parse(String name, String payload) 
	{ 
		SFont font = new SFont(name); 
		 
		if(!font.parse(payload)) 
			return new Font("Tahoma",0,2); 
		 
		return font.getFont(); 
	} 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)|| this.getValue_map().size() != 3) 
			return false; 
				 
		try 
		{ 
			this.setFontName(this.getValue_map().get(gl.E_EMPTY)); 
			 
			this.setFontBold(Integer.parseInt(this.getValue_map().get(gl.E_OK))); 
			 
			this.setFontSize(Integer.parseInt(this.getValue_map().get(gl.E_OK*2))); 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("%s...%s...%s","NumberFormatException","Error",e.toString())); 
			 
			return false; 
		} 
		 
			this.setFont(new Font(this.getFontName(),this.getFontBold(),this.getFontSize())); 
		 
			return true; 
		 
	} 
	 
	public static boolean doTest_toString(String value) 
	{ 
		String property_name = Su.BeforeAtFirst(value,'='); 
		 
		SFont st = new SFont(property_name); 
		 
		if(st.parse(value)) 
		{ 
			gl.smn("Input : " + value + " Output : " + st.toString() + " Short : " + st.toStringShort()); 
			 
			return true; 
		} 
		 
			return false; 
		 
	} 

	public static void main(String[] args) { 
		 
		doTest_toString("font=\"Tahoma\",1,10;"); 
		 
		Font font = new Font("Tahoma",0,12); 
		 
		gl.smn("Font : " + SFont.toString(font)); 
			 
		 
		  
		 gl.smn (   "Font.PLAIN  : "  + Font.PLAIN 
				  + "\nFont.BOLD   : "  + Font.BOLD 
				  + "\nFont.ITALIC : "  + Font.ITALIC 
				  + "\nFont.BOLD | Font.ITALIC  : "  + (Font.BOLD | Font.ITALIC) 
				 ); 
		  
		 
		 
	} 

} 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
