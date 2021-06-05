 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.io.File; 
import java.io.IOException; 
import java.util.List; 

import ap.global.gl; 
import ap.prop.SText; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 

public class TSvnJob { 

	private String home; 
	 
	private String source; 

	private String disk; 
	 
	private String svn_source_dir; 
	 
	private String svn_time_control; 
	 
	private String svn_time_direction; 
	 
	private String svn_update_file; 
	 
	 
	 
	 
	public String getDisk() { 
		return disk; 
	} 

	public void setDisk(String disk) { 
		this.disk = disk; 
	} 

	public String getSvn_source_dir() { 
		return svn_source_dir; 
	} 

	public void setSvn_source_dir(String svn_source_dir) { 
		this.svn_source_dir = svn_source_dir; 
	} 

	public String getSvn_time_control() { 
		return svn_time_control; 
	} 

	public void setSvn_time_control(String svn_time_control) { 
		this.svn_time_control = svn_time_control; 
	} 

	public String getSvn_time_direction() { 
		return svn_time_direction; 
	} 

	public void setSvn_time_direction(String svn_time_direction) { 
		this.svn_time_direction = svn_time_direction; 
	} 

	public String getSvn_update_file() { 
		return svn_update_file; 
	} 

	public void setSvn_update_file(String svn_update_file) { 
		this.svn_update_file = svn_update_file; 
	} 

	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public String getSource() { 
		return source; 
	} 

	public void setSource(String source) { 
		this.source = source; 
	} 

	 
	public TSvnJob() { 
		 
		 
	} 

	public TSvnJob(String home) 
	{ 
		this(); 
		 
		this.setHome(home); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		 
		String NL = System.lineSeparator(); 
				 
		StringBuilder sb = new StringBuilder(); 

		sb.append(this.getDisk()); 
		 
		sb.append(NL); 

		sb.append(this.getSvn_source_dir()); 
		 
		sb.append(sb.append(NL)); 
		 
		sb.append(this.getSvn_time_control()); 
		 
		sb.append(NL); 
		 
		sb.append(this.getSvn_time_direction()); 
		 
		sb.append(NL); 
		 
		sb.append(this.getSvn_update_file()); 
		 
		sb.append(NL); 
		 
		return String.format("%s",sb.toString()); 
		 
	} 
	 
	public boolean read() 
	{ 
		String source_text = null; 
		 
		try { 
			 
			source_text = Fu.get_str_from_file(this.getHome()); 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},String.format("Error while read from file...%s",this.getHome())); 
			 
			return false; 
		} 
		 
		if(source_text == null || source_text.trim().length()==gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("Error while read from file...%s",this.getHome())); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},String.format("Read from file...%s...%s",this.getHome(),gl.S_OK)); 
		 
			this.setSource(source_text.trim()); 
			 
			return true; 
	} 
	 
	public boolean parse() 
	{ 

		String msg = "Read source file...%s...%s"; 
		 
		if(!read()) 
		{ 
			gl.tracex(new Object(){},String.format(msg,gl.S_ERROR,this.getSource())); 
			 
			return false; 
		} 
		 
		gl.tracex(new Object(){},String.format(msg,gl.S_OK,"")); 
		 
		String temp = this.getSource(); 
		 
		boolean bl_result = false; 
		 
		try 
		{ 
			 
			 
		String d = System.lineSeparator(); 
		 
		this.setDisk(gl.getProperty(source,"disk",d)); 
		 
		this.setSvn_source_dir(gl.getProperty(source,"svn_source_dir",d)); 
		 
		this.setSvn_time_control(gl.getProperty(source,"svn_time_control",d)); 
		 
		this.setSvn_time_direction(gl.getProperty(source,"svn_time_direction",d)); 
		 
		this.setSvn_update_file(gl.getProperty(source,"svn_update_file",d)); 
		 
		} 
		catch(NullPointerException e) 
		{ 
			 
			gl.tracex(new Object(){},String.format("%s...%s","a parsing variable is not initialize",gl.S_ERROR)); 
			 
			return false; 
		} 
		 
		return true; 
	} 
	 
	public static void Test(String file_source) 
	{ 
		TSvnJob bat = new TSvnJob(file_source); 
		 
		String msg = "Test completed...%s"; 
		 
		if (!bat.parse()) 
			gl.tracex(new Object(){},String.format(msg,gl.S_ERROR)); 
		else 
		{ 
			gl.tracex(new Object(){},String.format(msg,gl.S_OK)); 
			 
			gl.smn(bat.toString()); 
		} 
	} 
	 
	 
	public static boolean run(String file_source) 
	{ 
		TSvnJob bat = new TSvnJob(file_source); 
		 
		String msg = "Run process completed...%s"; 
		 
		if (!bat.parse()) 
		{ 
			gl.tracex(new Object(){},String.format(msg,gl.S_ERROR)); 
			 
			return false; 
		} 
					 
			 
		return 	getSvnPathFile( 
					bat.getSvn_source_dir(), 
					bat.getSvn_time_control(), 
					bat.getSvn_time_direction(), 
					bat.getSvn_update_file() 
					); 
					 
	} 
	 
	 
	public static boolean getSvnPathFile(String path,String dt,String cond,String target_file) 
	{ 

		gl.smn("Path :" + path); 
		 
		List<File> files = Fu.getFilesInDirRecByDateTimeFilter(path,new String[]{"java"},dt,cond); 
		 
		gl.smn(String.format("Files count : %s date %s cond %s", files.size(),dt,cond)); 
		 
		String s_dlm = "#E_A_E_F#\n".replace("_",""); 
		 
		boolean [] bl_result = {true}; 
		files.forEach(a->{ 
			 
			gl.smn(a.getAbsolutePath() +" " + DateUtil.getDateStampFile(a.lastModified())); 
			 
			try { 
				 
				if (!Fu.to_file(target_file,s_dlm,true)) 
				{ 
					gl.smn(String.format("Error while write prefix to file :%s",target_file)); 
					 
					bl_result[0] = false; 
					 
				} 
				 
				gl.smn(String.format("Ok while write prefix to file :%s",target_file)); 
				 
				 
				String source = Fu.get_str_from_file(a.getAbsolutePath()); 
				 
				if (!Fu.to_file(target_file,source,true)) 
				{ 
					gl.smn(String.format("Error while write to file :%s",target_file)); 
					 
					bl_result[0] = false; 
				} 
				else 
					gl.smn(String.format("Ok while write to file :%s",target_file)); 
				 
				 
			} catch (Exception e) { 
				 
				 
				bl_result[0] = false; 
			} 
		}); 
		 
				return bl_result[0]; 
		 
	} 
	 
	 
	public static void start() 
	{ 
		String source = "e:\\exp\\stat\\jobs\\svn.job"; 
		 
		String msg = "The job completed with...%s"; 
		 
		if (!run(source)) 
		{ 
			gl.tracex(new Object(){},String.format(msg,gl.S_ERROR)); 
			 
		} 
		else 
			gl.tracex(new Object(){},String.format(msg,gl.S_OK)); 

	} 
	 
	public static void main(String[] args) { 
		 
			start(); 
	} 

} 
// Revision : 10.09.2018 12:49:14 
