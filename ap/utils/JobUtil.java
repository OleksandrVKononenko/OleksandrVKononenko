 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.io.IOException; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import ap.btn.TExpression; 
import ap.global.gl; 

public class JobUtil { 

	private String job_name; 
	 
	private Map<String,String> define_map = new HashMap<String,String>(); 
	 
	private Map<String,String> code_map = new HashMap<String,String>(); 
	 
	public String getJob_name() { 
		return job_name; 
	} 

	public void setJob_name(String job_name) { 
		this.job_name = job_name; 
	} 

	public Map<String, String> getDefine_map() { 
		return define_map; 
	} 

	public void setDefine_map(Map<String, String> define_map) { 
		this.define_map = define_map; 
	} 

	public Map<String, String> getCode_map() { 
		return code_map; 
	} 

	public void setCode_map(Map<String, String> code_map) { 
		this.code_map = code_map; 
	} 

	 
	public boolean loadDefines(String source,Map<String,TExpression> map) 
	{ 
		 
		String msg = "Try to parse define section"; 
				 
		Map<String,TExpression>  l_map = this.parseSection(getDefineSection(source,"define").toString()); 

		if(l_map.size() != gl.E_EMPTY) 
		gl.tracex(new Object(){},String.format("%s...count items...%04d...%s",msg,l_map.size(),gl.S_OK)); 
		else 
		{ 
			gl.tracex(new Object(){},String.format("%s...count items...%04d...%s",msg,l_map.size(),gl.S_ERROR)); 
			 
			return false; 
		} 
			map.putAll(l_map); 
		 
			return (l_map.size() > gl.E_EMPTY); 
	} 
	 
	public boolean loadCode(String source,Map<String,TExpression> map) 
	{ 
		 
		String msg = "Try to parse code section"; 

		Map<String,TExpression> l_map = this.parseSection(getCodeSection(source,"code").toString()); 

		if(l_map.size() != gl.E_EMPTY) 
		gl.tracex(new Object(){},String.format("%s...count items...%04d...%s",msg,l_map.size(),gl.S_OK)); 
		else 
		{ 
			gl.tracex(new Object(){},String.format("%s...count items...%04d...%s",msg,l_map.size(),gl.S_ERROR)); 
			 
			return false; 
		} 
			 
		map.putAll(l_map); 
		 
		return (l_map.size() > gl.E_EMPTY); 
	} 
	 
	public StringBuilder getDefineSection(String source,String section) 
	{ 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(Su.BeforeAtFirst( Su.AfterAtFirst(source,section),"code")); 
		 
		return sb; 
	} 
	 
	public Map<String,TExpression> parseSection(String input) 
	{ 
		// Parse lines firstly. 
		 
		String source = input.replace("{","").replace("}",""); 
		 
		String lines[] = source.split(";"); 
		 
		List<String> list = Arrays.asList(lines); 
		 
		Map<String,TExpression>  map = new LinkedHashMap<String,TExpression>(); 
		 
		 
		list.stream().sequential().forEach(a-> 
		{ 

			TExpression te = new TExpression(a); 
			 
			if(te.parse()){ 

				map.put(te.getLvalue(),te); 
			} 
			 
		}); 
		 
			return map; 
		 
	} 
	 
	 
	 
	public StringBuilder getCodeSection(String source,String section) 
	{ 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(Su.AfterAtFirst(source,section)); 
		 
		return sb; 
		 
	} 
	 
	public boolean checkItem(String source) 
	{ 
		 

		if(source.indexOf("define") == gl.E_ERROR) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s","Source file have bad format","missed section [define]")); 
			 
			return false; 
		} 
		 
		if(source.indexOf("code") == gl.E_ERROR) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s","Source file have bad format","missed section [code]")); 
			 
			return false; 
		} 
		 
		if(source.indexOf("{") == gl.E_ERROR) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s","Source file have bad format","missed section [{]")); 
			 
			return false; 
		} 
		 
		if(source.indexOf("}") == gl.E_ERROR) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s","Source file have bad format","missed section [}]")); 
			 
			return false; 
		} 
	 
			return true; 
	} 
	 
	 
	public boolean Test_expression(String path,String split) 
	{ 
		String arr[] = path.split(split); 
		 
		List<String> list = Arrays.asList(arr); 
		 
		list.forEach(a-> 
		{ 
			gl.smn(a); 
		} 
		); 
		 
			return true; 
	} 
	 
	public boolean Test_loadItem(String path) 
	{ 
		gl.tracex(new Object(){},String.format("%s...%s","Try to check of item",path)); 
		 
		if(!Fu.isaFile(path)) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s","File not exists",path)); 
			 
			return false; 
		} 
		 
		String source = ""; 
		 
		try { 
			source = Fu.getFileAsStringScannerSkipComments(path); 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},String.format("%s...%s","Error while read file",path)); 
			 
			return false; 
		} 
	 
			gl.tracex(new Object(){},String.format("%s...%4d...%s","Source file size",source.length(),path)); 
		 

			if(source.trim().length() == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object(){},String.format("%s...%s","Source file is empty",path)); 
				 
				return false; 
			} 
			 
			if(!checkItem(source)) 
				return false; 
			 
			 
			Map<String,TExpression> defines = new LinkedHashMap<String,TExpression>(); 

			Map<String,TExpression> codes =  new LinkedHashMap<String,TExpression>(); 
			 
			if(!loadDefines(source,defines)) 
				return false; 
			 
			if(!loadCode(source,codes)) 
				return false; 
			 
			gl.smn("Defines :  \n" + MapUtils.showMapV(defines) ); 
			 
			gl.smn("Code    :  \n" + MapUtils.showMapV(codes)); 
			 
							 
			 return true; 
	} 
	 
	 
	 
	public JobUtil() { 
		 
	} 
	 
	public JobUtil(String job) { 
		 
		this.setJob_name(job); 
	} 

	public static void main(String[] args) { 
		 
		String job_file = "test.job"; 
		 
		JobUtil ju = new JobUtil(job_file); 
		 
		ju.Test_loadItem(job_file); 
		 
		//ju.Test_expression("a+b-c","+"); 
		 

	} 

} 

/* 
 
:Define section 

define 
{ 

	ora_connection_string="some ora_connectionstring" ; 

} 

:Code section 

code 
{ 

	ora_connection_string += "_some_value_"; 


	show(ora_connection_string); 


} 

 
*/ 
// Revision : 10.09.2018 12:49:17 
