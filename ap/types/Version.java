 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.types; 

import ap.global.gl; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 29 ???. 2016 ?. 14:47:07 
* Project  name : Organizer 
* Package  name : ap.types 
* File     name : Version.java 
* 
*/ 
public class Version { 

	public static int BUILD_MAX_VALUE = 999; 
	 
	public static int SUBVERSION_MAX_VALUE = 99; 
	 
	public static int VERSION_MAX_VALUE = 99; 
	 
	 
	private int version = gl.E_EMPTY; 
	 
	private int subversion = gl.E_EMPTY; 
	 
	private int build = gl.E_EMPTY; 
	 
		 
	 
	/** 
	 * @return the version 
	 */ 
	public int getVersion() { 
		return version; 
	} 

	/** 
	 * @param version the version to set 
	 */ 
	public void setVersion(int version) { 
		this.version = version; 
	} 

	/** 
	 * @return the subversion 
	 */ 
	public int getSubversion() { 
		return subversion; 
	} 

	/** 
	 * @param subversion the subversion to set 
	 */ 
	public void setSubversion(int subversion) { 
		 

		if(this.getSubversion() == Version.SUBVERSION_MAX_VALUE) 
		{ 
			this.subversion = (gl.E_EMPTY); 
			 
			int version = this.getVersion(); 
			 
			this.setVersion(++version); 
		} 
		else 
		this.subversion = subversion; 
	} 

	 
	/** 
	 * @return the buildn 
	 */ 
	public int getBuild() { 
		return build; 
	} 

	/** 
	 * @param buildn the build to set 
	 */ 
	public void setBuild(int pbuild) { 

		 
		if(pbuild > Version.BUILD_MAX_VALUE) 
		{ 
			this.build = gl.E_EMPTY; 
			 
			int subversion = this.getSubversion(); 
			 
			this.setSubversion(++subversion); 
		} 
		else 
		this.build = pbuild; 
		 
	} 

	public boolean read(String value) 
	{ 

		String DOT = "."; 
		 
		int indexA = value.indexOf(DOT); 
		 
		int indexB = value.indexOf(DOT,indexA+1); 
		 
		if(value == null || indexA == gl.E_ERROR || indexB == gl.E_ERROR || (indexB-indexA)== gl.E_OK) 
		{ 
			gl.smn(String.format("Version.read(Error while CHECK input string ) value %s",value)); 
			 
			return false; 
		} 

		 
		String build = value.substring(++indexB); 
		 
		String version = value.substring(0,indexA); 
		 
		String subversion = value.substring(++indexA,--indexB); 
				 
		try 
		{ 
			this.setVersion(Integer.parseInt(version)); 
			 
			this.setSubversion(Integer.parseInt(subversion)); 
			 
			this.setBuild(Integer.parseInt(build)); 
			 
			//gl.smn(String.format("Input : %s Version : %s Subversion :%s Build : %s  Result : %s",value,version,subversion,build,this.getVersion())); 
			 
			 
			if(this.getVersion() != gl.E_ERROR && 
					this.getSubversion() != gl.E_ERROR && 
						this.getBuild() != gl.E_ERROR && 
						 
						this.getBuild() <= Version.BUILD_MAX_VALUE && 
						this.getSubversion() <= Version.SUBVERSION_MAX_VALUE && 
						this.getVersion() <= Version.VERSION_MAX_VALUE 
						 
				) 
				return true; 
			 
			 
			return false;			 
			 
		} 
		catch(Exception e ) 
		{ 
			//gl.smn(String.format("Version.read(Error while PARSING value %s Error : %s ",value,e.getMessage())); 
			 
			return false; 
		} 
		 
	} 
	 
	public boolean write(String file) 
	{ 
		return false; 
	} 
	 
	 
	public void add() 
	{ 
		int build = this.getBuild(); 
		 
		this.setBuild(++build); 
								 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%d.%d.%d", 
				this.getVersion(), 
				this.getSubversion(), 
				this.getBuild() 
				); 
	} 
	 
	 
	/** 
	 * 
	 */ 
	public Version() { 
		// REPLACE Auto-generated constructor stub 
	} 

	 
	public static void Test_Version(int count) 
	{ 
		Version version = new Version(); 
			 
		for(int i=0;i<count;i++) 
		{ 
			 
			version.add(); 
			 
			gl.smn(version.toString()); 
			 
		} 
		 
		 
	} 
	 
	public static void Test_Version_Read(String[] value) 
	{ 
		Version version = new Version(); 
		 
		 
		for(int i=0; i < value.length;i++) 
		{ 
				gl.smn(""); 
				 
			if(!version.read(value[i])) 
				gl.smn(String.format("Test failure for value : %s",value[i])); 
			else 
				gl.smn(String.format("Test Ok : %s==%s",value[i],version.toString())); 
	 
		} 
		 
	} 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		 
		//Test_Version(1000000); 
		 
		//Test_Version_Read("1"); 
		 
		Test_Version_Read(new String[]{"Test.test.ert","1.0","2.00","1.22.33","999.99.999","99.999.999","333.333.333.333"}); 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:49 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
