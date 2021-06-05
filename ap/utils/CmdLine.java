 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.utils; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 09 ????. 2016 ?. 16:30:33 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : CmdLine.java 
* 
*/ 
public class CmdLine { 

	 
	private String connectionString =""; 
	 
	private String tableName =""; 
	 
	private String fieldDelimiter =""; 
	 
	private String fieldQuote =""; 
	 
	private String outputFile =""; 
	 
	private int    rowsLimit = 0; 
	 
	 
	 
		 
	 
	/** 
	 * @return the rowsLimit 
	 */ 
	public int getRowsLimit() { 
		return rowsLimit; 
	} 



	/** 
	 * @param rowsLimit the rowsLimit to set 
	 */ 
	public void setRowsLimit(int rowsLimit) { 
		this.rowsLimit = rowsLimit; 
	} 



	/** 
	 * @return the connectionString 
	 */ 
	public String getConnectionString() { 
		return connectionString; 
	} 



	/** 
	 * @param connectionString the connectionString to set 
	 */ 
	public void setConnectionString(String connectionString) { 
		this.connectionString = connectionString; 
	} 



	/** 
	 * @return the tableName 
	 */ 
	public String getTableName() { 
		return tableName; 
	} 



	/** 
	 * @param tableName the tableName to set 
	 */ 
	public void setTableName(String tableName) { 
		this.tableName = tableName; 
	} 



	/** 
	 * @return the fieldDelimiter 
	 */ 
	public String getFieldDelimiter() { 
		return fieldDelimiter; 
	} 



	/** 
	 * @param fieldDelimiter the fieldDelimiter to set 
	 */ 
	public void setFieldDelimiter(String fieldDelimiter) { 
		this.fieldDelimiter = fieldDelimiter; 
	} 



	/** 
	 * @return the fieldQuote 
	 */ 
	public String getFieldQuote() { 
		return fieldQuote; 
	} 



	/** 
	 * @param fieldQuote the fieldQuote to set 
	 */ 
	public void setFieldQuote(String fieldQuote) { 
		this.fieldQuote = fieldQuote; 
	} 



	/** 
	 * @return the outputFile 
	 */ 
	public String getOutputFile() { 
		return outputFile; 
	} 



	/** 
	 * @param outputFile the outputFile to set 
	 */ 
	public void setOutputFile(String outputFile) { 
		this.outputFile = outputFile; 
	} 



	/** 
	 * 
	 */ 
	public CmdLine() { 
		// REPLACE Auto-generated constructor stub 
	} 
	 
	 
	public boolean isValid() 
	{ 
		return ( 
				! 
				( 
						this.getConnectionString().trim().length() == 0 || 
						this.getTableName().trim().length() == 0 
				) 
				); 
	} 
	 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
