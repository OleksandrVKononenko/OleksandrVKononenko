 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.utils; 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.lang.*; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 19 ???? 2016 ?. 12:01:52 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : ShellTest.java 
* 
*/ 
public class Shell { 

	/** 
	 * 
	 */ 
	 
	 
	public Shell() { 
		// REPLACE Auto-generated constructor stub 
	} 

	/** 
	 * @param args 
	 */ 
	 
	public static String Echo(String value) 
	{ 
		return value.concat("$"); 
	} 
	 
	public static String CheckConnection(String text) { 

		Connection connection = null; 

		PreparedStatement ps = null; 

		String result = ""; 

		try { 

			 
			try { 
				 
				Class.forName("com.teradata.jdbc.TeraDriver"); 
					 
			} catch (ClassNotFoundException e) { 
				 
				result = "Class.forName Error : " + e.getMessage(); 
			 
				return result; 
			} 


			connection = DriverManager.getConnection("jdbc:default:connection"); 

			ps = connection.prepareStatement(text); 

			ResultSet rs = ps.executeQuery(); 
			 
			if(rs != null) 
				result = "Ok."; 

		} catch (SQLException e) { 

			result = "Error : " + e.getMessage(); 
		} 

		finally { 

			if (ps != null) 
				try { 
					ps.close(); 
				} catch (SQLException e) { 

					result = "Exception : " + e.getMessage(); 
				} 

			if (connection != null) 
				try { 
					connection.close(); 
				} catch (SQLException e) { 

					result = "Exception : " + e.getMessage(); 
				} 

		} 

		return result; 

	} 
	 
	public static void main(String[] args) { 

		System.out.println(Shell.Echo("Test init value.")); 
		 

	} 
} 



// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
