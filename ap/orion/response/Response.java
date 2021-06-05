package ap.orion.response;

import java.util.List;

import ap.global.gl;

public class Response {
	
	// 27.08.2020 Delete numeration.
	public static String get_response_prev(List<String> value) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		try { 

				int [] index = {1}; 
			 
			value.forEach(l->{ 
				 
				sb.append(gl.sf("%2d.%s",index[0],l)); 
				 
				sb.append(System.lineSeparator()); 
				 
				index[0]++; 
				 
			}); 
			 
				return sb.toString(); 
		 
		} catch (Exception e) { 

				return null; 
		 
		}
	}
	
		public static String get_response(List<String> value) 
		{ 
			StringBuilder sb = new StringBuilder(); 
			 
			try { 
				 
				value.forEach(l->{ 
					 
					sb.append(gl.sf("%s",l)); 
					 
					sb.append(System.lineSeparator()); 
					 
				}); 
				 
					return sb.toString(); 
			 
			} catch (Exception e) { 

					return null; 
			 
			} 

	} 

}
