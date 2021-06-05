 
 
package ap.uat; 


import java.awt.Dimension; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import ap.global.gl; 
import ap.prop.SDimension; 
import ap.utils.DU; 
import ap.utils.Nu; 
import ap.utils.Su; 


public class CmdParamsA { 


	public static final int NONE = 3; 
	 
	public static final int ARGS = gl.E_EMPTY; 
	 
	public static final int PARAMS = gl.E_OK; 
	 
	public static Map<String,String> additional_params = new LinkedHashMap<String,String>(); 
	 
	public static boolean isaParams(CmdA owner,String [] params) 
	{ 
		return isaParams(owner,Arrays.asList(params)); 
		 
	} 
	 
	public static boolean isaParams(CmdA owner,List<String> param_list) 
	{ 
		 
		boolean []  bl_result = {false}; 
		 
		param_list.forEach(p-> 
		{ 
			owner.getParameter_list().forEach(s-> 
			{ 
				 
				if(s.equalsIgnoreCase(p)) 
					bl_result[gl.E_EMPTY] = true; 
			}); 
		}); 
		 
				return bl_result[gl.E_EMPTY]; 
		 
	} 
	 
	public static boolean isaParam(CmdA owner,String param) 
	{ 
		return owner.getParameter_list().contains(param); 
	} 
	 
	public static String get_parameter(CmdA cmd,String parameter) 
	{ 
		return getParameters_map(cmd).get(parameter); 
	} 
	 
	 
	public static Map<String,String> getParameters_map(CmdA cmd) 
	{ 
		 
		Map<String,String>  map = new LinkedHashMap<String,String>(); 
			 
			int [] index = {0}; 
					 
			try 
			{ 
				 
			 
			cmd.getParameter_list().forEach(a-> 
			{ 
				String key = ""; 
						 
				if(a.startsWith("/") || 
						a.startsWith("-") || 
						a.startsWith("$") ) 
				{ 
					key = gl.sf("p%03d",index[0]); 
					 
					index[0]++; 
					 
					map.put(key,a); 
				} 
				 
				 
			}); 
			 
			index[0] = 0; 
			 
			cmd.getParameter_list().forEach(a-> 
			{ 
				String key = ""; 
						 
				if(!(a.startsWith("/") || 
						a.startsWith("-") || 
						a.startsWith("$") ) 
					) 
				{ 
					key = gl.sf("a%03d",index[0]); 
					 
					index[0]++; 
					 
					map.put(key,a); 
				} 
				 
				 
			}); 
			 
					return map; 
			} 
			catch(Exception e) 
			{ 
				return null; 
			} 
		} 

	public static boolean extractArgs(CmdA fire_cmd,List<String> params) 
	{ 
		return extractParams(fire_cmd,"a",params); 
	} 
	 
	public static Map<Integer,List<String>> extractCmdMap(CmdA fire_cmd) 
	{ 
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>(); 
				 
		boolean [] result = {false,false}; 
		 
		map.put(CmdParamsA.ARGS,extractArgsList(fire_cmd,result)); 
		 
		map.put(CmdParamsA.PARAMS,extractParamList(fire_cmd,result)); 
		 
		return map; 
	} 
	 
	public static int getArgsCount(CmdA fire_cmd) 
	{ 
		try 
		{ 
			boolean [] state = {false,false}; 
			 
			List<String> params = new ArrayList<String>(); 
			 
			params = extractArgsList(fire_cmd,state); 
			 
			return params.size(); 
		} 
		catch(Exception e) 
		{ 
			return gl.E_ERROR; 
		} 
		 
	} 
	 
	public static int getParamsCount(CmdA fire_cmd) 
	{ 
		try 
		{ 
			boolean [] state = {false,false}; 
			 
			List<String> params = new ArrayList<String>(); 
			 
			params = extractParamList(fire_cmd,state); 
			 
			return params.size(); 
		} 
		catch(Exception e) 
		{ 
			return gl.E_ERROR; 
		} 
		 
	} 
	 
	public static List<String> extractArgsList(CmdA fire_cmd,boolean [] state) 
	{ 
		List<String> params = new ArrayList<String>(); 
		 
		state[CmdParamsA.ARGS] = extractParams(fire_cmd,"a",params); 
		 
		return params; 
	} 
	 
	public static List<String> extractArgsSuite(CmdA fire_cmd) 
	{ 
		List<String> params = new ArrayList<String>(); 
		 
		boolean [] state = {false,false}; 
		 
		state[CmdParamsA.ARGS] = extractParams(fire_cmd,"a",params); 
		 
		if(!state[CmdParamsA.ARGS]) 
			params = null; 
		 
		return params; 
	} 
	 
	public static List<String> extractParamsSuite(CmdA fire_cmd) 
	{ 
		List<String> params = new ArrayList<String>(); 
		 
		boolean [] state = {false,false}; 
		 
		state[CmdParamsA.PARAMS] = extractParams(fire_cmd,"p",params); 
		 
		if(!state[CmdParamsA.PARAMS]) 
			params = null; 
		 
		return params; 
	} 
	 
	public static List<String> extractParamList(CmdA fire_cmd,boolean [] state) 
	{ 
		List<String> params = new ArrayList<String>(); 
		 
		state[CmdParamsA.PARAMS] = extractParams(fire_cmd,"p",params); 
		 
		return params; 
	} 
	 
	public static boolean extractParams(CmdA fire_cmd,List<String> params) 
	{ 
		return extractParams(fire_cmd,"p",params); 
	} 
	 
	 
	public static boolean extractParams(CmdA fire_cmd,String filter,List<String> params) 
	{ 
		 
		Map<String,String> map = CmdParamsA.getParameters_map(fire_cmd); 
		 
		map.forEach((k,v)-> 
		{ 
			 if(k.startsWith(filter)) 
			 { 
				 params.add(v); 
			 } 
		}); 
		 
			return (params.size() != gl.E_EMPTY); 
		 
	} 
 
	public static boolean extract_bad_params(CmdA owner,List<String> param_list,List<String> not_valid_params_list) 
	{ 
		 
		owner.getParameter_list().forEach(s-> 
		{ 
			if(!param_list.contains(s) && (s.startsWith("/") || s.startsWith("--") || s.startsWith("-") || s.startsWith("$") )) 
			{ 
				if(!not_valid_params_list.contains(s)) 
					not_valid_params_list.add(s); 
			} 
			 
		}); 
		 
				return (not_valid_params_list.size() != gl.E_EMPTY); 
		 
	} 
	 
	 
	public static boolean extract_bad_params(CmdA owner,String [] valid_params,List<String> not_valid_params_list) 
	{ 
		List<String> param_list = Arrays.asList(valid_params); 
		 
		owner.getParameter_list().forEach(s-> 
		{ 
			 
			if(!param_list.contains(s) && (s.startsWith("/") || s.startsWith("-") )) 
			{ 
				if(!not_valid_params_list.contains(s)) 
					not_valid_params_list.add(s); 
			} 
			 
		}); 
		 
				return (not_valid_params_list.size() != gl.E_EMPTY); 
		 
	} 
	 
	public static boolean checkParams(CmdA cmd,List<String> valid_params,String [] invalid_params) 
	{ 
		List<String> list = new ArrayList<String>(); 
		 
		if (CmdParamsA.extract_bad_params(cmd,valid_params,list)) 
		   { 
			 StringBuilder sb = new StringBuilder(); 
			 
			 list.forEach(a->{ 
				 
				 sb.append(a); 
				 
				 sb.append(" "); 
				 
			 }); 
			 
			 	invalid_params[gl.E_EMPTY] = sb.toString(); 
			 	 
			 	return false; 
		   } 
		 
				return true; 
	} 
	 
	public static boolean isa_bad_params(CmdA cmd,List<String> valid_params,List<String> bad_params) 
	{ 
		return (CmdParamsA.extract_bad_params(cmd,valid_params,bad_params)); 
	} 
	 
	public static List<String> get_bad_params_list(CmdA cmd,List<String> valid_params) 
	{ 
		List<String> list = new ArrayList<String>(); 
		 
		CmdParamsA.extract_bad_params(cmd,valid_params,list); 
		 
		return list; 
	} 
	 
	public static int get_bad_params_list_count(CmdA cmd,List<String> valid_params) 
	{ 
		return get_bad_params_list(cmd,valid_params).size(); 
	} 
	 
	public static boolean get_bad_params_list_count_b(CmdA cmd,List<String> valid_params) 
	{ 
		return (get_bad_params_list_count(cmd,valid_params) != gl.E_EMPTY); 
	} 
	 
	 
	public static boolean checkParams(CmdA cmd,String [] valid_params,String [] invalid_params) 
	{ 
		List<String> list = new ArrayList<String>(); 
		 
		if (CmdParamsA.extract_bad_params(cmd,valid_params,list)) 
		   { 
			 StringBuilder sb = new StringBuilder(); 
			 
			 list.forEach(a->{ 
				 
				 sb.append(a); 
				 
				 sb.append(" "); 
				 
			 }); 
			 
			 	invalid_params[gl.E_EMPTY] = sb.toString(); 
			 	 
			 	return false; 
		   } 
		 
				return true; 
	} 
	 
	public static String show_params(CmdA cmd) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		cmd.getParameter_list().forEach(a-> 
		{ 
			sb.append(a); 
			 
			sb.append(" "); 
		}); 
		 
		return sb.toString(); 
	} 
	 

	public static Dimension get_dim(CmdA cmd ) 
	{ 
		String msg = "get dimension"; 
		 
		int m_args_count = CmdParamsA.getArgsCount(cmd); 
		 
		int m_params_count = CmdParamsA.getParamsCount(cmd); 
		 
		gl.tracex(new Object(){},gl.sf("%s...count...args...%d...params...%d",msg,m_args_count,m_params_count)); 
		 
		try 
		{ 
			Dimension matrix = new Dimension(2,2); 
						 
			if(m_args_count == gl.ONE) 
			{ 
				return matrix; 
			} 
			else if(m_args_count == gl.TWO) 
			{ 
				// Get args. 
				String arg = CmdParamsA.get_parameter(cmd,"a001"); 
				 
				// Convert to int. 
				int i_col_rows = DU.to_int(arg); 
						 
				if(i_col_rows > gl.TWO) 
				{ 
					matrix = Nu.get_dimension(arg); 
							 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept ONE arg case",SDimension.toString(matrix),gl.S_OK)); 
				} 
				else 
				{ 
					gl.tracex(new Object(){},gl.sf("%s...bad count of object request...%d...must be more than 2...%s",msg,i_col_rows,gl.S_ERROR)); 
				} 
				 
			} else if(m_args_count == gl.THREE) 
			{ 
				 
				matrix  = Nu.get_dimension(CmdParamsA.get_parameter(cmd,"a001"),CmdParamsA.get_parameter(cmd,"a002")); 
								 
				if (matrix.width <= gl.E_EMPTY || matrix.height <=  gl.E_EMPTY) 
				{ 
					return null; 
				} 
				 
					gl.tracex(new Object(){},gl.sf("%s...%s...%s...%s",msg,"accept TWO arg case",SDimension.toString(matrix),gl.S_OK)); 
				 
			} else if(m_args_count == gl.E_OK*4) 
			{ 
					gl.tracex(new Object(){},gl.sf("%s...%d...%s",msg,"Bad count args for execute.",m_args_count,gl.S_ERROR)); 
					 
					gl.smn(CmdParamsA.show_params(cmd)); 
					 
					return matrix; 
			} 
			 
					return matrix; 
			 
		} 
		catch(Exception e) 
		{ 
			gl.tracex(new Object(){},gl.sf("%s...%s...%s",msg,e.getMessage().toString(),gl.S_ERROR)); 
						 
			return null; 
		} 
		 
	}			 

	public CmdParamsA() { 

	} 

	public static boolean isaCmdOnly(CmdA cmd) 
	{ 
		 
		return (CmdParamsA.getArgsCount(cmd) == gl.E_EMPTY && CmdParamsA.getParamsCount(cmd) == gl.E_EMPTY); 
		 
	} 
	 
	public static String normalize(String value) 
	{ 
		 
		List<String> list = Arrays.asList(value.split(" ")); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		list.forEach(a->{ 
			 
			if(a.trim().length() >gl.E_EMPTY) 
			{ 
				String b = gl.sf("%s ", 
						a.replace("\r","") 
						.replace("\n","") 
						.replace("\t","") 
						.replace("\b","")) 
						.replace("/ ","/") 
						.replace("("," ") 
						.replace(")"," ") 
						.replace("{"," ") 
						.replace("}"," ") 
						.replace(","," ") 
						.replace("/ ","/") 
						 
						; 
						 
				sb.append(b); 
				 
			} 
			 
			 
		}); 
		 
			return sb.toString(); 
	} 
	 
	public static String extract(String value) 
	{ 
		int [] offset_left  = {0}; 
		 
		int [] offset_right = {0}; 
		 
		String msg = Su.BeforeAtFirst(Su.AfterAt(value,"("),")"); 
		 
		String  msg_index = gl.sf("extract()...[%d,%d]...%s",offset_left[gl.ZERO],offset_right[gl.ZERO],value.substring(offset_left[gl.ZERO],offset_left[gl.ZERO]+msg.length())); 

		gl.smn(msg_index); 
		 
		if(msg.trim().length()== gl.E_ERROR) 
		{ 
			return null; 
		} 
		 
		return value.replace(msg,""); 
		 
	} 
	 
	public static void test_extract() 
	{ 
		// String msg = "10,20 (11,21) {12,22}"; 
		 
		String msg = ".make(10,20) /s{param1=subdue}"; 
		 
		String mso = gl.sf("%s...%s",msg,extract(msg)); 
		 
		gl.smn(mso); 
		 
	} 
	 
	public static void test_normalize() 
	{ 
		//String params = "10 20 (  30\n\n  ,  40\r\n   ) { 12\t\t  ,  16\b\b  } /  d:   {12,11} /  g  :{puffy='1';duty='2'}"; 
		 
		String params = ".make(10,10) /s{some_param=some_value}"; 
		 
		String msg = gl.sf("Input...%s...Output...%s",params,normalize(params)); 
		 
		gl.smn(msg); 
		 
	} 
	public static void main(String[] args) { 
		 
		 test_normalize(); 
		 
		//test_extract(); 
		 

	} 

} 
