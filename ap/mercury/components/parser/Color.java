package ap.mercury.components.parser;

import java.util.ArrayList;
import java.util.Arrays;

import ap.global.gl;
import ap.utils.Bau;
import ap.utils.DU;
import ap.utils.Su;

public class Color extends Parser {

	private java.awt.Color 		color;
	
	public static int  ATTR_COUNT = 4; 
		
	public java.awt.Color getColor() {
		return color;
	}

	public void setColor(java.awt.Color color) {
		this.color = color;
	}

	public Color() {
		
		super();
		
	}

	public Color(String payload) {
		
		super(payload);
		
	}
	
	public boolean is_html_color(String payload)
	{
	
		boolean bl = 
				this.getRaw_list().size() == gl.E_OK 	&& 
				payload.startsWith("#") 				&& 
				payload.trim().length() == 9; 	


		return bl;

	}
	
	
	
	public int accept()
	{
		if(this.is_html_color(this.getRaw_list().get(gl.E_EMPTY)))
		{		
			try
			{
				this.setColor(Color.html_to_color(this.getRaw_list().get(gl.E_EMPTY)));
				
				return gl.E_OK;
			}
			catch(Exception e)
			{
				return gl.E_ERROR;
			}
		}	
		else if(this.getRaw_list().size() == Color.ATTR_COUNT)
		{
		
			try
			{
				this.setColor(new java.awt.Color(
						DU.to_int(this.getRaw_list().get(0)),
						DU.to_int(this.getRaw_list().get(1)),
						DU.to_int(this.getRaw_list().get(2)),
						DU.to_int(this.getRaw_list().get(3))
						));
				
				return gl.E_OK;
			}
			catch(Exception e)
			{
				return gl.E_ERROR;
			}
		}
		else
				return gl.E_ERROR;
				
	}
	
	public static String init(String payload)
	{
		// Заглушки для обработки сокращений для 0 и ,
		return  // "0"
				payload.trim().equalsIgnoreCase("0") ? 
				gl.sf("%d%s%d%s%d%s%d",0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0) : 
				// ","
				payload.trim().equalsIgnoreCase(gl.sf("%s",Parser.ATTR_DLM)) ? 
				gl.sf("%d%s%d%s%d%s%d",0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0) :
				// (0) повторов
				Su.remove_copies_in_brackets(payload);	
		
	}
	
	public static String dilator(String payload)
	{
		String m_result = payload;
		
		if(payload.trim().contains(gl.sf("%s",Parser.DILATOR)))
		{
			
			String 	s_value	  = Su.BeforeAtFirst(payload,gl.sf("%s",Parser.DILATOR));	
			
			boolean bl_random = false;
			
			int 	m_value   = gl.E_EMPTY;
			
			if(s_value.trim().equalsIgnoreCase("."))
			{
				bl_random = true;
			}
			else
			{
				m_value = DU.to_int(Su.BeforeAtFirst(payload,gl.sf("%s",Parser.DILATOR)));
			}

			String 	m_dilator = Su.AfterAt(payload,gl.sf("%s",Parser.DILATOR)).trim();
			
			int 	m_repeat  =  
					
						m_dilator.equalsIgnoreCase(".") ? Color.ATTR_COUNT :
					
						DU.to_int(Su.AfterAt(payload,gl.sf("%s",Parser.DILATOR)));
			
			
			if(m_value != gl.E_ERROR && m_repeat != gl.E_ERROR)
			{
				//m_result = gl.replicate(gl.sf("%d%s",bl_random ? gl.getRandomInt(255):m_value,gl.sf("%s",Parser.ATTR_DLM)),m_repeat);
				
				StringBuilder sb = new StringBuilder();
				
				for(int i=0;i<m_repeat;i++)
				{
				
					sb.append(gl.sf("%d",bl_random ? gl.getRandomInt(255):m_value));
					
					if(i < (m_repeat - 1))
					sb.append(gl.sf("%s",Parser.ATTR_DLM));
					
				}
				
					//m_result = Su.BeforeAt(m_result,gl.sf("%s",Parser.ATTR_DLM));
				
					m_result = sb.toString();
			}
		}
		
				//gl.sfn("Dilator...%s",m_result);
				
				return m_result;
	}
	
	public static String decode_eq(String payload)
	{
		
		String m_payload  = Su.remove_copies_in_brackets(payload.trim());
		
		if(Su.in(m_payload,new String [] {"","0",","}))
		   {
			  m_payload = gl.sf("%d%s%d%s%d%s%d",0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0,Parser.ATTR_DLM,0);
		   }
		else if(Su.in(m_payload,new String [] {"~"}))
		   {
			  m_payload = gl.sf("%d%s%d%s%d%s%d",gl.getRandomInt(255),
					  			Parser.ATTR_DLM,gl.getRandomInt(255),
					  			Parser.ATTR_DLM,gl.getRandomInt(255),
					  			Parser.ATTR_DLM,gl.getRandomInt(255)
					  			);
		   }
		   else if(Su.in(m_payload,new String [] {"~."}))
		   {
			  m_payload = gl.sf("%d%s%d%s%d%s%d",gl.getRandomInt(255),
					  			Parser.ATTR_DLM,gl.getRandomInt(255),
					  			Parser.ATTR_DLM,gl.getRandomInt(255),
					  			Parser.ATTR_DLM,255
					  			);
		   }
			  return m_payload;
			  
	}		 

	public static Color get_instance(String payload) {
		
		String 	m_payload = decode_eq(payload);//Color.init(payload);
		
				//m_payload = Color.dilator(m_payload);
				
		Color 	c = new Color(m_payload);
		
				// Перекодировку выполняем перед парсингом в классе после парсинга в базовом.
		
				java.util.List<String> tmp = new ArrayList<String>();
				
				if(c.getRaw_list().size() != 4 )
				{
					
					if (    c.getRaw_list().size() == gl.E_OK && 
							c.getRaw_list().get(gl.E_EMPTY).startsWith("~.") &&
							c.getRaw_list().get(gl.E_EMPTY).trim().length() > 2
					   )
					{
						
						int alpha = DU.to_int(Su.AfterAt(c.getRaw_list().get(gl.E_EMPTY),"."));
						
						if(
								alpha == gl.E_ERROR
						  )
								alpha  =  gl.E_EMPTY;
						
						for(int i=0;i<3;i++)
						{
							 tmp.add(gl.sf("%d",gl.getRandomInt(255)));
						}
						
						 	 tmp.add(gl.sf("%d",alpha));
						
					} else if (
							c.getRaw_list().size() == gl.E_OK && 
							c.getRaw_list().get(gl.E_EMPTY).endsWith(".~") &&
							c.getRaw_list().get(gl.E_EMPTY).trim().length() > 2
					   )
					{
						
						int def = DU.to_int(Su.BeforeAt(c.getRaw_list().get(gl.E_EMPTY),"."));
						
						if(
								def == gl.E_ERROR
						  )
								def  =  gl.E_EMPTY;
						
						for(int i=0;i<3;i++)
						{
							 tmp.add(gl.sf("%d",def));
						}
						
						 	 tmp.add(gl.sf("%d",gl.getRandomInt(255)));
						
					}	
					
					
					else
					{
						int def = DU.to_int(c.getRaw_list().get(gl.E_EMPTY));
						
						if(def  == gl.E_ERROR)
							def = gl.E_EMPTY;
						
						for(int i=0;i<4;i++)
						{
							 tmp.add(gl.sf("%d",def));
						}
						
					}
					
				} 
				
				else if(c.getRaw_list().size() == 4)
				{
				
				c.getRaw_list().forEach(a->{
					
					if(a == null || a.trim().length() == gl.E_EMPTY)
					{
						
						tmp.add(gl.sf("%d",gl.E_EMPTY));
						
					} else  if(a.trim().equalsIgnoreCase("~"))
					{
						tmp.add(gl.sf("%d",gl.getRandomInt(255)));
					}
					else
					{
						tmp.add(a);
					}
					
				});
				
				}
				
				c.getRaw_list().clear();
				
				c.getRaw_list().addAll(tmp);
				
				c.setState(c.accept());
				
				//gl.sfn("Color state...%d",c.getState());
		
		return 	c;
	}
	
	public String toString()
	{
		return toString(this.getColor());
	}
	
	public static String toString(java.awt.Color color) 
	{ 
		return String.format("%d,%d,%d,%d", 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				color.getAlpha()); 
	} 
	
	public static String to_html_color(java.awt.Color color) 
	{ 
		return String.format("#%s%s%s%s", 
				Bau.to_hex_str_from_int(color.getRed()), 
						Bau.to_hex_str_from_int(color.getGreen()), 
								Bau.to_hex_str_from_int(color.getBlue()), 
										Bau.to_hex_str_from_int(color.getAlpha()) 
						); 
	} 
	
	public static java.awt.Color html_to_color(String color) 
	{ 
		return get_alpha_color(java.awt.Color.decode(color.substring(0,7)),Bau.to_int_from_hex_str(color.substring(7,9))); 
	} 
	
	public static java.awt.Color get_alpha_color(java.awt.Color color, int alpha) 
	{ 
		return new java.awt.Color( 
				 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				alpha 
				 
				); 

	} 
	
	

	public static void test(String [] items)
	{
	
		Arrays.asList(items).forEach(a->
		{
			test(a);
		});
	}
	
	public static void test(String payload)
	{
		Color color = Color.get_instance(payload);
		
		gl.tx(new Object(){},color.getState() > gl.E_EMPTY,gl.sf("Test of...[%s] [%s]",payload,color.toString()));
		
	}

	public static void main(String[] args) {
		
		
		String [] items = new String[]{"l;;",",","a,b","","0","~","~.","~.125","~,255,~,100","~,~,~,120","255.~","~.125"};
		
		test(items);
		
		//test("");
	}

}
