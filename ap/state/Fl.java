 
package ap.state; 

import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 
import ap.utils.Biu; 

public  class Fl { 
	 

	public static int VK_VISIBLE 			= 0; 
	 
	public static int VK_DISABLE 			= 1; 
	 
	public static int VK_MOVE_INSIDE 		= 2; 
	 
	public static int VK_MOVE_BOTTOM_RIGHT	= 3; 
	 
	public static int VK_MOVE_TOP_LEFT		= 4; 
		 
	public static int VK_DENY_SELECT 		= 5; 
	 
	public static int VK_FREEZE 			= 6; 
	 
	public static int VK_GRID 				= 7; 
	 
	public static int VK_SELECTED 			= 8; 
	 
	public static int VK_CHECKED 			= 9; 
		 
	public static int VK_DRAGGED 			= 10; 
	 
	public static int VK_RESIZING 			= 11; 
		 
	public static int VK_DROP_TARGET		= 12; 
	 
	public static int VK_DRAW_SELECTOR		= 13; 
	 
	public static int VK_MOUSE_LEFT_CLICK 	= 14; 
	 
	public static int VK_MOUSE_RIGHT_CLICK 	= 15; 
	 
	public static int VK_SKIP_TEXT 	 		= 16; 
	 
	public static int VK_IMAGE_FIT 	 		= 17; 
	 
	public static int VK_DENY_X		 		= 18; 
	 
	public static int VK_DENY_Y		 		= 19; 
	 
	public static int VK_DENY_WIDTH	 		= 20; 
	 
	public static int VK_DENY_HEIGHT 		= 21; 
	 
	public static int VK_DENY_DROP 			= 22; 
	 
	public static int VK_DENY_DRAG 			= 23; 
	 	 
	public static int VK_SHOW_KEY_CODE 		= 24; 
	 
	public static int VK_DELETE 			= 25; 
				 
	public static int VK_DEBUG 				= 26; 
	 
	public static int VK_SKIP_MOVED			= 27; 
	 
	public static int VK_SKIP_RESIZING		= 28; 
	
	public static int VK_DENY_INSIDE_SELECTION		= 29; 
	
	 
	 
	public static int ETL_WEEK = 1; 
	 
	public static int ETL_WEEK_MASK = 0; 
	 
	public static int ETL_MONTH = 2; 
	 
	public static int ETL_MONTH_MASK = 1; 
	 
	public static int ETL_QUARTAL = 4; 
	 
	public static int ETL_QUARTAL_MASK = 2; 
	 
	public static int ETL_YEAR = 8; 
	 
	public static int ETL_YEAR_MASK = 3; 
	 
	 
	public static List<String> FLAGS = Arrays.asList(new String[]{ 
			"VK_VISIBLE", 		
			"VK_DISABLE", 		
			"VK_MOVE_INSIDE", 	
			"VK_MOVE_BOTTOM_RIGHT",
			"VK_MOVE_TOP_LEFT",	
			"VK_DENY_SELECT",
			"VK_FREEZE", 		
			"VK_GRID", 		
			"VK_SELECTED", 		
			"VK_CHECKED", 		
			"VK_DRAGGED", 		
			"VK_RESIZING", 		
			"VK_DROP_TARGET",	
			"VK_DRAW_SELECTOR",	
			"VK_MOUSE_LEFT_CLICK", 	
			"VK_MOUSE_RIGHT_CLICK", 	
			"VK_SKIP_TEXT", 	 	
			"VK_IMAGE_FIT", 	 	
			"VK_DENY_X",		
			"VK_DENY_Y",		
			"VK_DENY_WIDTH",	 	
			"VK_DENY_HEIGHT", 	
			"VK_DENY_DROP", 		
			"VK_DENY_DRAG", 		
			"VK_SHOW_KEY_CODE", 	
			"VK_DELETE", 		
			"VK_DEBUG", 		
			"VK_SKIP_MOVED",		
			"VK_SKIP_RESIZING",	
			"VK_DENY_INSIDE_SELECTION"
			}) ; 
	 
	 
	public static void Test_BitShift() 
	{ 
		byte state = 0; 
		 
		gl.smn(String.format("Value : %d",state)); 
		 
	} 
	 
	/* 
	public static String get_drag_type_name(int state) 
	{ 
			StringBuilder sb = new StringBuilder(); 
			 
		    if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_INIT_OVER_DESKTOP)) 
		    { 
		    	sb.append("VK_DRAG_TYPE_INIT_OVER_DESKTOP"); 
		    	 
		    	sb.append(System.lineSeparator()); 
		    	 
		    } 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_INIT_OTHER_OBJECT_FROM_DESKTOP)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_INIT_OTHER_OBJECT_FROM_DESKTOP"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_REDRAG_OVER_DESKTOP)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_REDRAG_OVER_DESKTOP"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_DESKTOP)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_FROM_OBJECT_TO_DESKTOP"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_DESKTOP_TO_OBJECT)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_FROM_DESKTOP_TO_OBJECT"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_IN_THE_SAME_PARENT_OBJECT_AREA)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_IN_THE_SAME_PARENT_OBJECT_AREA"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_DOWN)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_DOWN"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 
			else if (BitUtils.ISA(state,Fl.VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_UP)) 
			{ 
		    	sb.append("VK_DRAG_TYPE_FROM_OBJECT_TO_OBJECT_UP"); 
		    	 
		    	sb.append(System.lineSeparator()); 
				 
			} 

		    	return sb.toString(); 
	} 
	*/ 
	 
	public static String show_flags(int state) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		FLAGS.forEach(a-> 
		{ 
			int index_of = FLAGS.indexOf(a.toUpperCase().trim()); 
	 
			String msg = gl.sf("%02d.%s...%1d",index_of,gl.format(a.toUpperCase(),gl.AL.LEFT,50,'.'),Biu.ISA(state,index_of)?1:0); 
			 
			sb.append(msg); 
			 
			sb.append(System.lineSeparator());    
			 
		}); 
		 
		return sb.toString(); 
	} 
	 
	public static void main(String[] args) { 
		 
		gl.smn(show_flags(65535)); 
		 
		gl.smn("IndexOf VK_GRID : " + FLAGS.indexOf("VK_GRID")); 
		 
	} 
	 
	 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
