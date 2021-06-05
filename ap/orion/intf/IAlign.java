package ap.orion.intf;

import java.util.Arrays;
import java.util.List;

public interface IAlign { 
	 
	
	public static final List<String> align = Arrays.asList(new String[]{"NONE","BASEXY","BASEXYWH"}); 
	 
	
	public static int indexOf(String p_align) 
	{ 
		return IAlign.align.indexOf(p_align.trim().toUpperCase()); 
	}; 
	 
	 
	public static String indexOf(int p_align) 
	{ 
		return IAlign.align.get(p_align); 
	}
	
}	 
