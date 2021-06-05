 
package ap.utils; 

import java.awt.Dimension;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import ap.global.gl;
import ap.mercury.components.Gimension;
import ap.prop.SDimension; 

public class ParseUtil { 
	
	public static String [] DLMS = new String[] {".",",",":","::","#","$","@","%","&","^","!","*","|"};
	
	public static String SKIP = " ";
	

	public static boolean parse_font(String [] payload,Font [] out)
	{
		List<String> m_list = Arrays.asList(payload);
		
		String m_payload = m_list.stream().collect(Collectors.joining(","));
		
		return parse_font(m_payload,",",out);
		
	}
	
	public static boolean parse_font(List<String> payload,Font [] out)
	{
		
		String m_payload = payload.stream().collect(Collectors.joining(","));
		
		return parse_font(m_payload,",",out);
		
	}
	
	public static boolean parse_font(String payload,String sep,Font [] out)
	{
		if(!payload.contains(sep))
		{
			return false;
		}
		
		String 	m_payload	= payload;
				
		String 	m_font_name  = Su.BeforeAtFirst(payload,sep);
		
		if(m_font_name.trim().length()==gl.E_EMPTY)
		{
			return false;
		}
		
				m_payload 	= m_payload.replace(m_font_name,"");
		

		String 	m_font_size  = Su.AfterAt(payload,sep);
		
		if(m_font_size.trim().length()==gl.E_EMPTY)
		{
			return false;
		}
				
				m_payload 	= m_payload.replace(m_font_size,"").replace(sep,"").trim();
		
		// Проверка преобразований.
				
		int i_font_bold = DU.to_int(m_payload);
		
		int i_font_size = DU.to_int(m_font_size); 
		
		if(i_font_bold == gl.E_ERROR || i_font_size == gl.E_ERROR)
		{
			return false;
		}
		
			//gl.sfn("font...%s...bold...%d..size...%d",m_font_name,i_font_bold,i_font_size);
		
			out[0] = new Font(m_font_name,i_font_bold,i_font_size);
					
			return true;
		
	}
	
	public static boolean parse_font(String payload,String sep,Font [] out,boolean [] state)
	{
		if(!payload.contains(sep))
		{
			return false;
		}
		
		String 	m_payload	= payload;
				
		String 	m_font_name  = Su.BeforeAtFirst(payload,sep);
		
		if(m_font_name.trim().length()==gl.E_EMPTY)
		{
			state[0] = true;
		}
		
				m_payload 	= m_payload.replace(m_font_name,"");
		

		String 	m_font_size  = Su.AfterAt(payload,sep);
		
		if(m_font_size.trim().length()==gl.E_EMPTY)
		{
			state[2] = true;
		}
				
				m_payload 	= m_payload.replace(m_font_size,"").replace(sep,"").trim();
		
		if(m_payload.trim().length() == gl.E_EMPTY)
		{
			state[1] = true;
		}
				
		// Проверка преобразований.
				
		int i_font_bold = DU.to_int(m_payload);
		
		int i_font_size = DU.to_int(m_font_size); 
		
		

		if(m_font_name.trim().length() == gl.E_EMPTY  && !state[0])
		{
			return false;
		}
		
		
		if(i_font_bold == gl.E_ERROR  && !state[1])
		{
			return false;
		}
		
		if(i_font_size == gl.E_ERROR && !state[2])
		{
			return false;
		}
		
			gl.sfn("font...%s...%s...bold...%d...%s...size...%d...%s",
					m_font_name,
					state[0],
					i_font_bold,
					state[1],
					i_font_size,
					state[2]
					);
		
			out[0] = new Font(m_font_name,i_font_bold,i_font_size);
					
			return true;
		
	}
	
	public static String replace_dlm(String payload,String [] dlms)
	{
		
		String m_payload = payload.trim();
		
		for(int i=0;i<dlms.length;i++)
		{
			m_payload = m_payload.replace(dlms[i]," ");
		}
		
			return m_payload;
	}
	
	public static void test_replace_dlm(String payload)
	{
		gl.sfn("%s %s %s",payload,replace_dlm(payload,DLMS),parse_dim(payload)==null?"null":SDimension.toString(parse_dim(payload)));
		
	}
	
	public static Dimension parse_dim(String payload)
	{
		String 	m_payload 	= replace_dlm(payload,DLMS);
		
		//gl.sfn("---> Debug...%s",m_payload);
		
		int 	m_columns 	= DU.to_int(Su.BeforeAt(m_payload,SKIP));
		
		int 	m_rows 		= DU.to_int(Su.AfterAt(m_payload,SKIP));
		
		if( m_columns == gl.E_ERROR || m_rows == gl.E_ERROR )
			return null;
		
		return new Dimension(m_columns,m_rows);
		
		
	}
	
	public static Gimension parse_gim(String payload)
	{
		String 	m_payload 	= replace_dlm(payload,DLMS);
		
		int 	m_columns 	= DU.to_int(Su.BeforeAt(m_payload,SKIP));
		
		int 	m_rows 		= DU.to_int(Su.AfterAt(m_payload,SKIP));
		
		if( m_columns == gl.E_ERROR || m_rows == gl.E_ERROR )
			return null;
		
		return new Gimension(m_columns,m_rows);
		
		
	}
	
	public static void test()
	{
		Font [] font = {null};
		
		String payload = "Tahoma,1,12";
		
		String [] m_payload = new String[] {"Tahoma","1","12"}; 
				
		List<String> m_l_payload = Arrays.asList(m_payload);
		
		//if(!parse_font(payload,",",font))
		//if(!parse_font(m_payload,font))
		if(!parse_font(m_l_payload,font))
			gl.sfn("Error.");
		else
			gl.sfn("Ok.");
		
	}
	
	public static void test_ex()
	{
		Font [] font = {null};
		
		boolean [] state = {false,false,false};
		
		
		String payload = ",,";
		
		if(!parse_font(payload,",",font,state))
			gl.sfn("Error.");
		else
			gl.sfn("Ok.");
		
	}
	
	public static void test_dim()
	{
		test_replace_dlm("11:12");
		
		test_replace_dlm("11,12");
		
		test_replace_dlm("11::12");
		
		test_replace_dlm("11.12");
		
		test_replace_dlm("11 , 12 ");
		
		
		
	}
	
	public static void main(String [] args)
	{
		test_dim();
	}
	
} 
// Revision : 10.09.2018 12:49:17 
