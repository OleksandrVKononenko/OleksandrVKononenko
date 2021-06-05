 
/** 
* 
*/ 
package ap.utils; 

import java.awt.GraphicsEnvironment;
import java.awt.datatransfer.StringSelection;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Calendar; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List;
import java.util.Locale;
import java.util.Map; 
import java.util.function.Function; 
import java.util.stream.Collectors; 
import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.cmd.CmdProcess;

public class Su { 
	
	
	public static List<String> clear_param_one(String value)
	{
		List<String> result = new ArrayList<String>();
		
		String m_clear = "";
		
		if(Su.isa_any_of_b(value,CmdProcess.delim_switches))
		{
			m_clear = Su.remove_delims(value,CmdProcess.delim_switches,' ');
				
			String [] arr = m_clear.split(" ");
			
			result.addAll(Arrays.asList(arr));
		}
		else
		{
			result.add(value);
		}
		
			return result;
	}
	
	public static List<String> clear_params_many(List<String> 	m_params)
	{
		List<String>  	result 	=  new ArrayList<String>(); 
		
		String 			params  = m_params.stream().collect(Collectors.joining(" "));
		
		if(Su.isa_any_of_b(params,CmdProcess.delim_switches))
		{
			result.addAll(Su.clear_params(m_params));
		}
		else
		{
			result.addAll(m_params);
		}
		
			return result;
	}
	
	
	public static String remove_copies_in_brackets(String str){
		
		return (str.contains("(") && str.contains(")") ? Su.BeforeAtFirst(str,"(") : str);
		
	}

	public static String removeNL(String str){
		
		return str.replace(System.lineSeparator(),"").replace("\r","").replace("\n","");
	}
	
	public static String remove_value_in_bounds(String value,String [] bounds)
	{
		String result = value;
		
		for(int i=0;i<bounds.length;i++)
		{
			result = result.replace(bounds[i]," ");
		}
		
			return Su.BeforeAtFirst(result," ");
	}
	
	public static List<String> remove_value_in_bounds(List<String> list,String [] bounds)
	{
		List<String> result = new ArrayList<String>();
		
		String  [] s = {""};
		
		list.forEach(a->
		{
			s[0] = remove_value_in_bounds(a,bounds);
			
			gl.sfn("Add to list...%s",s[0]);
			
			result.add(s[0]);
			
		});
		
			return result;
	}
	
	public static List<String> clear_params(List<String> params)
	{
		List<String> result = new ArrayList<String>();
		
		String  [] s = {""};
		
		params.forEach(a->
		{
			if (!Su.isa_any_of_b(a,CmdProcess.delim_switches))
				{
					result.add(a);
				}
			
		});
		
			return result;
	}
	
	public static String replace(String str, int index, char replace){     
	    if(str==null){
	        return str;
	    }else if(index<0 || index>=str.length()){
	        return str;
	    }
	    char[] chars = str.toCharArray();
	    chars[index] = replace;
	    return String.valueOf(chars);       
	}

	public static String fmt8d2(double value)
	{
		Locale.setDefault(new Locale("en", "US"));
		
		return gl.sf("%8s",gl.sf("%.2f",value));
		
	}
	
	public static String fmt10d2(double value)
	{
		Locale.setDefault(new Locale("en", "US"));
		
		return gl.sf("%10s",gl.sf("%.2f",value));
		
	}
	
	public static String fmt6d4(double value)
	{
		Locale.setDefault(new Locale("en", "US"));
		
		return gl.sf("%6s",gl.sf("%.4f",value));
		
	}
	
	public static String fmt8d4(double value)
	{
		Locale.setDefault(new Locale("en", "US"));
		
		return gl.sf("%8s",gl.sf("%.4f",value));
		
	}

	public static String fmt10d4(double value)
	{
		Locale.setDefault(new Locale("en", "US"));
		
		return gl.sf("%10s",gl.sf("%.4f",value));
		
	}
	
	
	
	public static int index = gl.E_EMPTY; 

	public static String BeforeAtFirst(String value, char what) { 
		int index = value.indexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(0, index).trim(); 

	} 

	public static String BeforeAtFirst(String value, String what) { 
		 
		int index = value.indexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(0, index).trim(); 

	} 
	 
	public static String BeforeAtFirst(String value, String what,int [] offset) { 
		 
		offset[gl.ZERO] = value.indexOf(what); 

		if (offset[gl.ZERO] == gl.E_ERROR) 
			return null; 

		return value.substring(gl.ZERO,offset[gl.ZERO]).trim(); 

	} 

	 
	public static String BeforeAtFirstStrong(String value, String what) { 
		int index = value.indexOf(what); 

		if (index == gl.E_ERROR) 
			return null; 

		return value.substring(0, index).trim(); 

	} 
	 
		 
	
	 public static String exclude(String value,String open,String close)
	    {
	    	
	    	String a = value;
	    	
	    	boolean flag = true;
	    	
	    	while(flag )
			{
				String r = Su.Between(a,open,close);
				
				if(!(a.contains(open)  || a.contains(close)))
				{
					flag = false;
					
					return a;
					
					//gl.sfn("Exit from cycle...%s",a);
					
				}
				else
				{
					String find  = gl.sf("%s%s%s",open,r,close);
					
					String rest = Su.AfterAt(a,find);
					
					a = a.replace(find,"");
					
					//gl.sfn("Pass...%s...value...%s...target...%s",a,r,rest);
				}
			}
	    	
	    			return value;
	    }
	 
	public static String Between(String source,String a, String b) 
	{ 
		return Su.BeforeAt(Su.AfterAt(source,a),b); 
	} 
	

	public static boolean isUnicode(String value) { 
		
		return value.chars().allMatch(o -> Character.isUnicodeIdentifierPart(o)); 
	} 

	
	public static boolean isAlpha(String value) { 
		
		return value.chars().allMatch(o -> Character.isLetter(o)); 
	} 

	public static boolean isNumber(String value) { 
		return value.chars().allMatch(o -> Character.isDigit(o)); 
	} 
	
	public static boolean isAlphaNumber(String value) { 
		
		return value.chars().allMatch(o -> Character.isAlphabetic(o)) ; 
	} 

	public static boolean isWhitespace(String value) { 
		return value.chars().anyMatch(o -> Character.isWhitespace(o)); 
	} 

	public static String BeforeAtFirst(String value, char what, int offset) { 
		int index = value.indexOf(what, offset); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(0, index).trim(); 

	} 

	public static String BeforeAt(String value, String what) { 
		int index = value.lastIndexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(0, index).trim(); 

	} 
	 
	public static String BeforeAt(String value, char what) { 
		int index = value.lastIndexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(0, index).trim(); 

	} 
	
	
	
	public static List<String> split(String value, char what)
	{
		List<String> list = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i< value.length();i++)
		{
			char c = value.charAt(i);
			
			if(c != what)
			{
				sb.append(value.charAt(i));
			}
			else
			{
				if(sb.toString().trim().length() == gl.E_EMPTY)
				{
					list.add(null);
				}
				else
				{
					
					list.add(sb.toString());
					
					sb.setLength(gl.E_EMPTY);
				}
			}
		}
				// Tail append to  the list.
				
				list.add(sb.toString());
		
				return list;
	}

	public static String removeFirstLF(String value) { 

		String arr[] = value.split(System.lineSeparator()); 

		StringBuilder sb = new StringBuilder(); 

		List<String> list = Arrays.asList(arr); 

		list.forEach(obj -> 

		{ 

			if (index > 1) { 
				sb.append(obj); 

				sb.append(System.lineSeparator()); 
			} 

			index++; 
		}); 

		return sb.toString(); 

	} 

		 
	public static String extractVariable(String source,String left,String right) 
	{ 
		 
		if(!(source.contains(left) && source.contains(right))) 
			return null; 
					 
		int[] off = {-1}; 
		 
		String stop = AfterAtFirst(BeforeAtFirst(source,right,off),left); 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(stop); 
		 
		sb.append(System.lineSeparator()); 
		 
		sb.append(String.format("%4d", off[0])); 
		 
		String rest = source.substring(off[0]); 
		 
		 
		//gl.smn("Source : " + source); 
		 
		gl.smn("Rest : " + rest); 
		 
		return extractVariable(rest,left,right); 
		 
		 
	} 
	 
	// Extract table name from sql script. 
	 
	public static String extractTableNameFromSql(String sql_text,int index) 
	{ 
		 
		String extract = BeforeAt(AfterAtFirst(sql_text,"from"),"where"); 
		 
		if(sql_text == null || sql_text.trim().length() == gl.E_EMPTY || extract == null || extract.trim().length()== gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("input or extract value is null for input :%s",sql_text)); 
		} 
		 
		List<String> list = Su.get_as_list(extract); 
		 
		if(index > list.size()-1) 
		{ 
			gl.tracex(new Object(){},String.format("Invalid index value: %d for input :%s",index,extract)); 
			 
			return null; 
		} 
			 
		String proxy = list.get(index).trim(); 
		 
		if(proxy.indexOf(" ") != gl.E_ERROR) 
		{ 
			List<String> proxy_list = Su.get_as_list(proxy," "); 
			 
			if(proxy_list.size() == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object(){},String.format("Proxy list is empty for input :%s",proxy)); 
				 
				return null; 
			} 
			else 
			{ 
				return proxy_list.get(gl.E_EMPTY); 
			} 
		} 
		else 
			return proxy; 
		 
	} 
	 
	 
	public static String extractVar(String source,String left,String right) 
	{ 
		return BeforeAt(AfterAtFirst(source,left),right); 
	} 
	 
	public static String AfterAt(String value, char what) { 
		
		int index = value.lastIndexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(index + 1).trim(); 

	} 
	 
	public static String AfterAt(String value,String what,int [] offset) { 
		 
		offset[gl.ZERO] = value.indexOf(what); 

		if (offset[gl.ZERO] == gl.E_ERROR) 
			return null; 

		return value.substring(offset[gl.ZERO]).trim(); 
	} 
	 
		 
	 

	public static String AfterAt(String value, String what) { 
		 
		int index = value.lastIndexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 
		 
		return value.substring(index + what.trim().length()).trim(); 

	} 

	public static String ExtractClassName(String value) { 

		String checkString = "public class "; 

		String checkStringTwo = "public abstract class "; 

		String checkBefore = Su.BeforeAtFirst(value, '{'); 

		if (checkBefore.indexOf(checkString) == gl.E_ERROR 
				&& checkBefore.indexOf(checkStringTwo) == gl.E_ERROR) { 
			return "___None___"; 
		} 

		String str = Su.BeforeAtFirst( 
				Su.AfterAtFirst(checkBefore, checkString), ' '); 

		return str; 

	} 

	public static String ExtractPackageName(String value) { 

		String chkPackage = "package "; 

		boolean blPackage = false; 

		String chkBefore = Su.BeforeAtFirst(value, ';'); 

		// If comment string is occur then repeat 
		if (chkBefore.contains("/*")) { 
			int chkIndex = value.indexOf("*/"); 

			chkBefore = Su.BeforeAtFirst(value, ';', chkIndex); 

		} 

		if (chkBefore.indexOf(chkPackage) != gl.E_ERROR) { 
			blPackage = true; 
		} 

		String strTarget = ""; 

		if (blPackage) 
			strTarget = Su.BeforeAtFirst( 
					Su.AfterAtFirst(chkBefore, chkPackage), ' '); 

		if (strTarget.trim().length() == gl.E_EMPTY) 
			strTarget = ""; 

		return strTarget; 

	} 

	public static String ExtractClassNameEx(String value) { 

		String chkPublicClass = "public class "; 

		boolean blPublicClass = false; 

		String chkPublicAbstractClass = "public abstract class "; 

		boolean blPublicAbstractClass = false; 

		String chkPublicInterface = "public interface "; 

		boolean blPublicInterface = false; 

		String chkPrivateClass = "class "; 

		boolean blPrivateClass = false; 

		String chkBefore = Su.BeforeAtFirst(value, '{'); 

		// If comment string is occur then repeat 
		if (chkBefore.contains("/*")) { 
			int chkIndex = value.indexOf("*/"); 

			chkBefore = Su.BeforeAtFirst(value, '{', chkIndex); 

		} 

		if (chkBefore.indexOf(chkPublicClass) != gl.E_ERROR) { 
			blPublicClass = true; 
		} else if (chkBefore.indexOf(chkPublicAbstractClass) != gl.E_ERROR) { 
			blPublicAbstractClass = true; 
		} else if (chkBefore.indexOf(chkPublicInterface) != gl.E_ERROR) { 
			blPublicInterface = true; 
		} else if (chkBefore.indexOf(chkPrivateClass) != gl.E_ERROR) { 
			blPrivateClass = true; 
		} 

		String strTarget = ""; 

		if (blPublicClass) 
			strTarget = Su.BeforeAtFirst( 
					Su.AfterAtFirst(chkBefore, chkPublicClass), ' '); 
		else if (blPublicAbstractClass) 
			strTarget = Su.BeforeAtFirst( 
					Su.AfterAtFirst(chkBefore, chkPublicAbstractClass), 
					' '); 
		else if (blPublicInterface) 
			strTarget = Su 
					.BeforeAtFirst(Su.AfterAtFirst(chkBefore, 
							chkPublicInterface), ' '); 
		else if (blPrivateClass) 
			strTarget = Su.BeforeAtFirst( 
					Su.AfterAtFirst(chkBefore, chkPrivateClass), ' '); 

		if (strTarget.trim().length() == gl.E_EMPTY) 
			strTarget = "__NONE__"; 

		// Last check. 

		int t_index = strTarget.indexOf('<'); 

		if (t_index != gl.E_ERROR) 
			strTarget = strTarget.substring(0, t_index); 

		return strTarget; 

	} 

	public static String AfterAtFirst(String value, String what) { 
		int index = value.indexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		return value.substring(index + what.trim().length()).trim(); 

	} 

	
	public static String AfterAtFirstStrong(String value, String what) { 
		int index = value.indexOf(what); 

		if (index == gl.E_ERROR) 
			return null; 

		return value.substring(index + what.trim().length()).trim(); 

	} 

	public static String AfterAtTerm(String value, String what, String term) { 
		int index = value.lastIndexOf(what); 

		if (index == gl.E_ERROR) 
			return value; 

		int real_start_index = index + what.trim().length(); 

		String proxy = value.substring(real_start_index); 

		int index_extends = proxy.indexOf("extends"); 

		if (index_extends != gl.E_ERROR) { 
			proxy = proxy.substring(0, index_extends); 
		} 

		int index_impl = proxy.indexOf("implements"); 

		if (index_impl != gl.E_ERROR) { 
			proxy = proxy.substring(0, index_impl); 
		} 

		return proxy; 

	} 

	public static String reverse(String value) { 
		return new StringBuffer(value).reverse().toString(); 

	} 

	public static String getBrew(String value) { 

		String value_ = reverse(value); 

		int indexOf = value_.indexOf("\\"); 

		if (indexOf == gl.E_ERROR) 
			return ""; 

		int indexOf_ = value_.indexOf("\\", ++indexOf); 

		if (indexOf_ == gl.E_ERROR) 
			return ""; 

		// gl.smn(indexOf+","+indexOf_); 

		return reverse(value_.substring(indexOf, indexOf_)); 

	} 

	public static Date parseDate(String value, String format) { 

		SimpleDateFormat formatter = new SimpleDateFormat(format); 

		Date date = null; 

		try { 

			date = formatter.parse(value); 

			// System.out.println(date); 

			// System.out.println(formatter.format(date)); 

			return date; 

		} catch (ParseException e) { 

			gl.smn("parseDate(error) : " + e.toString()); 

		} 

		catch (NullPointerException e) { 

			gl.smn("parseDate(error) : " + e.toString()); 

		} 

		return date; 
	} 

	public static String getDateFullString(Date value) { 
		return getDayOf(value).concat(getMonthOf(value)).concat( 
				getYearOf(value)); 
	} 

	public static String getDayOf(Date value) { 

		Calendar cal = Calendar.getInstance(); 

		try { 

			cal.setTime(value); 

			SimpleDateFormat sdf = new SimpleDateFormat("dd"); 

			return sdf.format(cal.getTime()); 

		} catch (NullPointerException e) { 

			gl.smn("getMonthOf(error) : " + e.toString()); 

		} 
		return ""; 

	} 

	public static String getMonthOf(Date value) { 

		Calendar cal = Calendar.getInstance(); 

		try { 
			cal.setTime(value); 

			SimpleDateFormat sdf = new SimpleDateFormat("mm"); 

			return sdf.format(cal.getTime()); 

		} catch (NullPointerException e) { 

			gl.smn("getMonthOf(error) : " + e.toString()); 

		} 
		return ""; 
	} 

	public static String getYearOf(Date value) { 

		Calendar cal = Calendar.getInstance(); 

		try { 
			cal.setTime(value); 

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 

			return sdf.format(cal.getTime()); 

		} catch (NullPointerException e) { 

			gl.smn("getMonthOf(error) : " + e.toString()); 

		} 
		return ""; 

	} 

	/** 
	 * @param pvalue 
	 *            format '2016-03-01' 
	 * @return pvalue format '01032016' 
	 */ 
	public static String getUADateFromUSDate(String pvalue) { 
		return pvalue.substring(8, 10) + pvalue.substring(5, 7) 
				+ pvalue.substring(0, 4); 

	} 

	public static String BeforeAtPeriod(String value) { 
		return BeforeAtSome(value, '.'); 
	} 

	public static String AfterAtPeriod(String value) { 
		return AfterAtSome(value, '.'); 
	} 

	public static String AfterAtSome(String value,char some) { 
		return AfterAt(value, some); 
	} 
	
	public static String BeforeAtSome(String value,char some) { 
		return BeforeAt(value, some); 
	} 
	
	public static String BeforeAtComma(String value) { 
		return BeforeAtSome(value, ','); 
	} 

	public static String AfterAtComma(String value) { 
		return AfterAtSome(value, ','); 
	} 
	

	public static String StringRound(String value, int wide) { 
		int index = value.indexOf("."); 

		if (index == gl.E_ERROR) 
			return value; 

		if ((index + wide) + 1 > value.length()) 
			return value; 

		int rest = (value.length() - index) + 1; 

		if (rest >= wide) 
			return value.substring(0, (index + wide) + 1); 
		else 
			return value; 
	} 
	 
	 

	public static void TestCase(String value) { 
		gl.smn("Input : " + value); 

		gl.smn("Before : " + BeforeAtPeriod(value)); 

		gl.smn("After : " + AfterAtPeriod(value)); 

	} 

	public static List<String> getFontList() { 
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment() 
				.getAvailableFontFamilyNames(); 

		return Arrays.asList(fonts); 
	} 

	public static void Test_fontList() { 
		List<String> list = Su.getFontList(); 

		list.forEach(a -> { 
			gl.smn(a); 
		}); 

	} 
	 
	public static void test_getVariable_list(String source,String delimiter) { 
		List<String> list = Su.getVariable(source,delimiter); 

		list.forEach(a -> { 
			gl.smn(a); 
		}); 

	} 

	public static void Test_isaFont(String font) { 

		if (isaFont(font)) 
			gl.smn("Font : " + font + " is correct."); 
		else 
			gl.smn("Font : " + font + " is not correct."); 
	} 

	public static boolean isaFont(String font_name) { 
		List<String> list = Su.getFontList(); 

		// list.forEach(a-> {gl.smn(a);}); 

		return list.contains(font_name); 

	} 

	public static void Test_extractFilename(String[] test) { 
		for (int i = 0; i < test.length; i++) { 
			String value = Fu.get_file_name(test[i]); 

			gl.smn("Input: " + test[i] + " Result : " + value); 

		} 
	} 

	public static void Test_getBrew(String[] test) { 
		for (int i = 0; i < test.length; i++) { 
			String value = getBrew(test[i]); 

			gl.smn("Input: " + test[i] + " Result : " + value); 

		} 
	} 

	public static void Test_parseDate(String value) { 

		Date date = parseDate(value, "dd/mm/yyyy"); 

		gl.smn(String.format("Test of date : %s Result is %s %s %s", value, 
				getDayOf(date), getMonthOf(date), getYearOf(date))); 

	} 

	public static void Test_extractClassName(String value) { 
		String str_before = Su.BeforeAt(value, '{'); 

		gl.smn("ExtractClassName : " + str_before); 

	} 

	public static void getDiffOfStringsSuite(String source, String source1) { 
		Su.getDiff(source, source1).forEach( 

		a -> { 
			System.out.println(a); 
		} 

		); 

	} 

	public static void test_DiffOfStrings(String source, String source1) { 
		Su.getDiffOfStringsSuite(source, source1); 

	} 

	 

	private String[] addStringArray(String sArray[], String value) { 
		String result[] = null; 
		if (sArray == null) { 
			result = new String[1]; 
			result[0] = value; 
		} else { 
			result = new String[sArray.length + 1]; 
			for (int i = 0; i < sArray.length; i++) 
				result[i] = sArray[i]; 

			result[sArray.length] = value; 
		} 
		return result; 
	} 

	private boolean startsWithStringArray(String sArray[], String value) { 
		if (value == null) 
			return false; 

		for (int i = 0; i < sArray.length; i++) 
			if (value.startsWith(sArray[i])) 
				return true; 

		return false; 
	} 

	public static String getProperty(String source, String property,String delimiter) 
	{ 
		 
		Object v = new Object() {}; 
		 
		Map<String,String> map = new HashMap<String,String>(); 
		 
		String[] arr = source.split(delimiter); 
		 
		if(arr.length== gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("%s...split empty...%s",gl.S_ERROR,source)); 
			 
			return null; 
		} 
		 
			for(int i=0;i< arr.length;i++) 
			{ 
				String key = Su.BeforeAtFirst(arr[i],"=").trim(); 
				 
				String value = Su.AfterAt(arr[i],"=").trim(); 
				 
				map.put(key.toLowerCase(), value); 
			} 
		 	 
			Map<String,String> result = map.entrySet().stream() 
					.sorted(Map.Entry.<String, String> comparingByKey()).collect(Collectors. 
				toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new)); 
			 
			return result.get(property.toLowerCase()); 
		 
	} 

	 
	public static String getProperty_prev(String source, String property, 
			String delimiter) { 
		Object v = new Object() { 
		}; 

		int indexOf = source.indexOf(property); 

		if (indexOf == gl.E_ERROR) { 
			return null; 
		} 

		// Check property name by length; 

		int i_equal = source.indexOf("=", indexOf + 1); 

		if (i_equal == gl.E_ERROR) { 
			return null; 
		} 

		String s_name = source.substring(indexOf, i_equal).trim(); 

		if (!s_name.equalsIgnoreCase(property)) { 
			// gl.tracex(v,String.format("%s...%s...%s...%d...%d","Missing property",property,s_name,property.length(),s_name.length())); 

		 
			return Su.getProperty(source.substring(i_equal + 1), 
					property, delimiter); 

		} 

		String check = Su.reverse(source.substring(0, indexOf)); 

		int i_check = check.indexOf(delimiter); 

		if ((check.trim().length() != gl.E_EMPTY || i_check != gl.E_ERROR) 
				&& i_check != gl.E_EMPTY) { 

			// gl.smn("Check value repeat call for : " + 
			// source.substring(indexOf+1) + "i_check :" + i_check); 

			int nextIndexOf = source.indexOf(delimiter, indexOf + 1); 

			if (nextIndexOf != gl.E_ERROR) 
				return Su.getProperty( 
						source.substring(nextIndexOf + 1), property, delimiter); 

		} 

		int indexOf_equals = source.indexOf("=", indexOf); 

		if (indexOf_equals == gl.E_ERROR) { 
			return null; 
		} 

		int indexOf_prop_delim = source.indexOf(delimiter, indexOf_equals); 

		if (indexOf_prop_delim == gl.E_ERROR) { 
			return null; 
		} 

		return source.substring(indexOf_equals + 1, indexOf_prop_delim); 

	} 
	 
	 
	public static boolean in(String value, String[] list) { 
		
		List<String> l = Arrays.asList(list); 

		return l.contains(value.toLowerCase()); 

	} 


	public static String remove_delims(String value,List<String> list,char what) { 
		
		StringBuilder sb = new StringBuilder();
		
		value.chars().forEach(c->
		{
			String a = gl.sf("%s",(char)c);
			
			if(!list.contains(a))
			{
				sb.append(a);
			}
			else
			{
				sb.append(what);
			}
			
		});
		
				return sb.toString();

	} 
	
	public static String extract_alpha(String value) { 
		
		StringBuilder sb = new StringBuilder();
		
		value.chars().forEach(c->
		{
			
			
			if( Character.isLetter(c))
			{
				String a = gl.sf("%s",(char)c);
				
				sb.append(a);
			}
			
		});
		
				return sb.toString();

	} 


	public static Map<String,Integer> isa_any_of(String value,List<String> list) { 
		
		Map<String,Integer> state = new LinkedHashMap<String,Integer>();
		
		
		value.chars().forEach(c->
		{
			String a = gl.sf("%s",(char)c);
			
			if(list.contains(a))
			{
				int count = state.containsKey(a) ? state.get(a) : 0;
				
				state.put(a,count+1);
			}
			
		});
		
				return state; 

	} 

	public static String extract_ticker(String source)
	{		
		return Su.BeforeAtFirst(Su.AfterAt(source,Parser.HEADER),Parser.ATTR_DLM);
	}
	
public static boolean isa_any_of_b(String value,List<String> list) { 
		
		List<Boolean> state = new ArrayList<Boolean>();
		
		value.chars().forEach(c->
		{
			String a = gl.sf("%s",(char)c);
			
			state.add(list.contains(a));
							
		});
			
			return state.contains(true); 

	} 
	
	public static List<String> getDiff(String source, String source1) { 
		// gl.smn(String.format("%d %d",source.length(),source1.length())); 

		if (source.length() < source1.length()) 
			return getDiff(source1, source); 

		List<String> list = new ArrayList<String>(); 

		String mask = "%06d %s != %s "; 

		for (int i = 0; i < source.length(); i++) { 
			char s = source.charAt(i); 

			char t = '#'; 

			try { 
				t = source1.charAt(i); 
			} catch (java.lang.StringIndexOutOfBoundsException e) { 

			} 

			if (s != t) 
				list.add(String.format(mask, i, s, t)); 
		} 

		return list; 
	} 
	 
	public static List<String> getVariable(String source,String delimiter) 
	{ 
		String[] arr = source.split(delimiter); 
		 
		if(arr == null || arr.length == gl.E_EMPTY) 
			return null; 
		 
		List<String> list = Arrays.asList(arr); 
		 
		return list; 
		 
	} 
	 
	 
	public static List<String> getListFromSql(String source) 
	{ 
		String[] arr = source.split(" "); 
		 
		List<String> proxy_list = Arrays.asList(arr); 
		 
		List<String> list = new ArrayList(); 
		 
		proxy_list.forEach(a-> 
		{ 
			if(!a.startsWith("#") 	|| 
				!a.startsWith("::") || 
				!a.startsWith("//") || 
				!a.startsWith("--") 
			  ) 
			{ 
			     if(a.indexOf(",") == gl.E_ERROR) 
			     { 
			    	 // Normal case. 
			    	 if(a.trim().length() != gl.E_EMPTY) 
			    	 { 
			    		 list.add(a); 
			    	 } 
			     } 
			     else 
			     { 
			    	 //There need of parsing. 
			    	 String[] larr = a.split(","); 
			    			 
			    	 List<String> l = Arrays.asList(larr); 
			    	 
			    	 l.forEach(li-> 
			    	 { 
			    		  list.add(li); 
			    	 } 
			    	 
			    	 ); 
			    	 
			     } 
			} 
		} 
		); 
		 
			return list; 
	} 
	 
	public static long getWordCount(String source,String value) 
	{ 
		 
		List<String> list = getListFromSql(source); 
		 
		if(list == null || list.size() == gl.E_EMPTY) 
		{ 
				gl.tracex(new Object(){},String.format("Input source is not listable...%s...%s",source,gl.S_ERROR)); 
			 
				return gl.E_ERROR; 
		} 
		 
		Map<String,Long> counted = list.stream().collect ( 
				 
				Collectors.groupingBy(Function.identity(),Collectors.counting()) 
				 
				); 

		try 
		{ 
				long find = MapUtils.findValueByKey(counted,value); 
		 
				return find; 
		} 
		catch(java.lang.NullPointerException e) 
		{ 
				return gl.E_ERROR; 
		} 
		
		 
	} 
	 
	public static long getWordCount_raw(String source,String value) 
	{ 
		 
		List<String> list = getListFromSql(source); 
		 
		Map<String,Long> counted = list.stream().collect ( 
				 
				Collectors.groupingBy(Function.identity(),Collectors.counting()) 
				 
				); 
		 
		//gl.smn(counted); 
		 
		 
		/* 
		Map<String,List<String>> counted = list.stream().collect( 
				 
				Collectors.groupingBy(o->o)); 
		*/ 
		 
		//gl.smn(MapUtils.showMapKV(counted)); 
		 
		 
		counted.entrySet().forEach(a-> 
		{ 
			gl.smn("Key : " + a.getKey() + " Value : " + a.getValue()); 
		} 
		); 
		 
		 
		return MapUtils.findValueByKey(counted,value); 
		 
		 
	} 
	 
	public static boolean Test_getWordCount(String source,String word) 
	{ 
		 
		List<String> list = getListFromSql(source); 
		 
		list.forEach(a-> 
		{ 
			String msg = String.format("%s=%d",a,getWordCount(source,a)); 
			 
			gl.smn(msg); 
			 
		} 
		); 
		 
		 
		 
		return true; 
	} 
	 

	public static String get_date_format(String source) 
	{ 
		
		String a = Su.BeforeAtFirst(source,Parser.ATTR_DLM);
		
		String s = 
					Su.AfterAt(
					a,
					"."
					).trim();
		
		//gl.sfn("----> a...[%s]...b...[%s]",a,s);
		
		if (a.length() != 10)
			return "none";
		
		if (s.length() == 4)
			return "normal";
		else if (s.length() == 2)
			return "fibo";
		else
			return "none";
		
	} 
	
	public static List<String> get_as_list(String source) 
	{ 
			return get_as_list(source,","); 
	} 

	public static List<String> get_as_list_for_data(String source) 
	{ 
			return get_as_list(source,gl.sf("%s",Parser.ITEMS_DLM)); 
	} 
	
	public static List<String> get_as_list_for_quote_file(String source) 
	{ 
			return get_as_list(source,gl.sf("%s",System.lineSeparator())); 
	} 
	 
	public static List<Integer> get_as_list_of_int(String source) 
	{ 
			return get_as_list_of_int(source,","); 
	} 
	 
	public static List<Integer> get_as_list_of_int(String source,String dlm) 
	{ 
		 
		String[] arr = source.split(dlm); 
		 
		if(arr == null || arr.length ==gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("Error while split...%s",source)); 
						 
			return null; 
		} 
		 
		List<Integer> list = new ArrayList<Integer>(); 
		 
		for(int i=0;i<arr.length;i++) 
		{ 
			try 
			{ 
				String value = arr[i].trim(); 
				 
				int i_value = Integer.parseInt(value); 
				 
				list.add(i_value); 
				 
			} 
			catch(java.lang.NumberFormatException e) 
			{ 
				gl.tracex(new Object(){},String.format("Invalid type to convert to integer...%s",arr[i].trim())); 
			} 
		} 
		 
			return list; 
	} 
	 
	public static List<String> get_as_list(String source,String dlm) 
	{ 
		
		if( source == null ) 
		{	 
			return null; 
		} 
		
		String [] arr = source.split(dlm); 
		 
		if(arr == null || arr.length == gl.E_EMPTY) 
		{	 
			return null; 
		} 
		 
			return Arrays.asList(arr); 
	} 
	 
	public static String get_delim_str_from_list(List<String> items,String dlm) 
	{ 
		return String.join(dlm,items); 
	} 
	 
	public static String get_str_from_list(List<String> items) 
	{ 
		if(items.size() == gl.E_EMPTY)
		return "";
		else
		return String.join(" ",items); 
	} 
	
	public static String get_str_from_list_zpt(List<String> items) 
	{ 
		return String.join(",",items); 
	}
	
	public static String get_str_from_list_for_data(List<String> items) 
	{ 
		return String.join(gl.sf("%s",Parser.ITEMS_DLM),items); 
	}
	
	public static String get_str_from_list(List<String> items,String dlm) 
	{ 
		return String.join(dlm,items); 
	} 
	
	public static boolean parse(String source,String delimiter,List<String> items) 
	{ 
		 
		String[] arr = source.split(delimiter); 
		 
		if(arr.length == gl.E_EMPTY) 
			return false; 
		 
		List<String> tmp_list = Arrays.asList(arr); 
		 
		if(items == null) 
			items = new ArrayList<String>(); 
		 
		items.clear(); 
		 
		items.addAll(tmp_list); 
	 
		return (items.size() != gl.E_EMPTY); 
		 
	} 
	 
	 
	public static boolean isaPair(String a,String b,String c) 
	{ 
		int a_cnt = count_of_substr(a,b); 
		 
		int b_cnt = count_of_substr(a,c); 
		 
		return  (a_cnt != gl.E_EMPTY && a_cnt == b_cnt ); 
		 
	} 
	 
	 
	public static int count_of_substr(String a,String b) 
	{ 
		 
		int count = gl.E_EMPTY; 
		 
		for(int i=0;i<=(a.length() - b.length());i++) 
		{ 
		 
			String part =  a.substring(i, i+b.length()); 
			 
			if(part.equalsIgnoreCase(b)) 
			{ 
				count++; 
			} 
			 
		} 
		 
		return count; 
		 
	} 
	
	
	public static String enrich(String source,int base,char rich) 
	{
			int len = source.trim().length();
			
			if(len >= base)
			{
				return source;
			}
			
			return  gl.sf("%s%s",gl.replicate(rich,base - len),source);
	}
	

	public static void show(String caption,List<String> value)
	{
		if(value==null)
			return;
		
		gl.sfn("%s%s%s",caption,
				System.lineSeparator(),
				gl.replicate("-",80)
				);
		
		value.forEach(a->
		{
			gl.sfn("%s",a.toString());
			
		});
	}
	
	public static String append_header_record(Orion owner,String record)
	{
		String 			base   = owner.getData().getHeaders();
		
		List<String>    l_base = Su.get_as_list_for_data(base);
		
				String  w_base = base == null ? "" : base;
				
		 		return	w_base.concat(gl.sf("%s%s%s%s%d%s",
		 				record,
		 				Parser.FIELD_DLM,
		 				owner.getName(),
		 				Parser.ATTR_DLM,
		 				l_base == null ? 0 : l_base.size(),
		 				gl.sf("%s",Parser.ITEMS_DLM)
		 				));
		 			    
	}
	
	public static String append_data_record(String base,String record)
	{
		
		String  w_base = base == null ? "" : base;
		
 		return	w_base.concat(gl.sf("%s%s",
 				base == null ? "" : System.lineSeparator(),
 				record
 				));

	}
	
	
	public static String Test_getVariable(String source,String left,String right) 
	{ 
		String var = extractVar(source,left,right); 
		 
		gl.smn("Result :" + var); 
		 
		return var; 
	} 
	 
	public static void Test_getProperty(String source, String property,String delimiter) 
	{ 
		 
		//gl.smn( property + "=" + getProperty(source,property,delimiter) ); 
		 
		gl.smn( property + "=" + getProperty(source,property,delimiter) ); 
		 
	} 
	 
	public static void main(String[] args) { 

		// TestCase("12345.6789"); 

		// TestCase("12345"); 

		// Test_extractFilename(new 
		// String[]{"e:\\txt\\bin\\filename1.txt","e:\\txt\\bin\\filename2.txt","e:\\txt\\bin\\filename3.txt"}); 

		// Test_getBrew(new 
		// String[]{"e:\\txt1\\bin1\\filename1.txt","e:\\txt2\\bin2\\filename2.txt","e:\\txt3\\bin3\\filename3.txt"}); 

		// Test_parseDate("12/12/2016"); 

		// Test_parseDate("05/03/2016"); 

		// Test_parseDate("5.3.2016"); 

		// Test_extractClassName("import io.ap; import io.bt; public class TestClass {some_text;}"); 

		// test_DiffOfStrings("source","source "); 

		// Test_fontList(); 

		//Test_isaFont("TTT"); 

		//Test_isaFont("Tahoma"); 

		//Test_isaFont("TAHOME"); 

		//Test_isaFont("TAHOMa"); 
		 
		//String test = "{ a },{b},{c},{e}"; 
		 		 
		//String a = Test_getVariable(test,"{","}"); 
		 
		//String b = Test_getVariable(a,"{","}"); 
		 
		//gl.smn(extractVariable(test,"{","}")); 
		 
		/* 
		getListOfIntegers("1,2,3,4,5,6,7,,",",").forEach(a-> 
		{ 
			gl.smn(a); 
		} 
		); 
		*/ 
		 
		//test_getVariable_list("1#2#3","#"); 
		 
		//String sql_text = "select select select top top top  1 1 1  from from from test_stg_b2.dummy1 t1,test_stg_b2.dummy2 t2 where t1.name = t2.name"; 
		 
		/* 
		String extract_value =  StringUtil.extractTableNameFromSql(sql_text,1); 
		 
		gl.smn(String.format("In : %s \nOut: %s", sql_text,extract_value)); 
		 
		*/ 
		 		 
		//Test_getWordCount(sql_text,"select"); 
				 
		//Test_getVariable("test(k=09,b=08,c=07)","(",")"); 

		/* 
		gl.smn("Test : " + countOfSubstr("asdfg(((haser)t)as","(")); 
		 
		gl.smn("Test : " + countOfSubstr("asdfg(((haser)t)as",")")); 
		 
		gl.smn("Test : " + countOfSubstr("asdfg(((haser)t)as","a")); 
		 
		gl.smn("Test : " + countOfSubstr("???????? ??????","?")); 
		*/ 
		 
		//gl.smn("Test : " + isaPair("???????? ????()??(","(",")")); 
		 
		 
		//gl.smn("Test : " + countOfSubstr("asdfghas(((t)))as","(")); 
		 
		//gl.smn("Test : " + countOfSubstr("asdfghas(er((t)as",")")); 

		String t = "1,2,3,4";
		
		List<String>  r = split(t,',');
		
		if (gl.tx_i(new Object(){},r.size() > gl.E_EMPTY,gl.sf("Test of...%s...is...",t)))
		{
			gl.sfn("Result...[%s]", r.stream().collect(Collectors.joining(gl.sf("%s",Parser.ATTR_DLM))));
		}
	
		
	} 
	 
} 



// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
