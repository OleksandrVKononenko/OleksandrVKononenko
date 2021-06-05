package ap.utils;

import ap.global.gl;

public class OsUtil {

	public static String get_os_raw() {
		
		return gl.sf("%s",System.getProperty("os.name").toLowerCase());
	}

	
	public static void main(String[] args) {
		
		gl.sfn("%s",Su.BeforeAtFirst(get_os_raw()," "));
		

	}

}
