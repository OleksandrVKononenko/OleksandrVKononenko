 
 
/** 
* 
*/ 
package ap.utils; 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
 
/** 
* Simple Java program to connect to MySQL database running on localhost and 
* running SELECT and INSERT query to retrieve and add data. 
* @author Javin Paul 
*/ 
public class TestMySql { 
 
// JDBC URL, username and password of MySQL server 
	 
private static final String url = "jdbc:mysql://10.3.45.102:3306/ok_work"; 
private static final String user = "akononenko"; 
private static final String password = "paSSw0rd!4"; 
 
// JDBC variables for opening and managing connection 
private static Connection con; 
private static Statement stmt; 
private static ResultSet rs; 
 
public static void main(String args[]) { 
String query = "select count(*) from chat_addressbook"; 
 
try { 
// opening database connection to MySQL server 
con = DriverManager.getConnection(url, user, password); 
 
// getting Statement object to execute query 
stmt = con.createStatement(); 
 
// executing SELECT query 
rs = stmt.executeQuery(query); 
 
while (rs.next()) { 
int count = rs.getInt(1); 
System.out.println("Total number of books in the table : " + count); 
} 
 
} catch (SQLException sqlEx) { 
sqlEx.printStackTrace(); 
} finally { 
//close connection ,stmt and resultset here 
try { con.close(); } catch(SQLException se) { /*can't do anything */ } 
try { stmt.close(); } catch(SQLException se) { /*can't do anything */ } 
try { rs.close(); } catch(SQLException se) { /*can't do anything */ } 
} 
} 
 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
