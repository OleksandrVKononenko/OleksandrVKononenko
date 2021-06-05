 
 
package ap.global; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.FontMetrics; 
import java.awt.Graphics; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.InetAddress; 
import java.net.UnknownHostException; 
import java.text.DecimalFormat; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Calendar; 
import java.util.GregorianCalendar; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Locale; 
import java.util.Map; 
import java.util.Random; 
import java.util.zip.Deflater; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipOutputStream; 
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea; 
import ap.action.*; 
import javax.swing.border.Border; 
import javax.swing.border.EtchedBorder; 
import ap.action.BaseAction; 
import ap.area.AreaManager; 
import ap.btn.Bar; 
import ap.btn.TLineStyle; 
import ap.btn.TMa; 
import ap.btn.TPivot; 
import ap.btn.TStroke;
import ap.gen.TIDGen;
import ap.uat.BaseActionA; 
import ap.uat.action.ApplicationCloseActionA; 
import ap.uat.action.CleanDesktopA; 
import ap.uat.action.CompositeOpenActionA; 
import ap.uat.action.CompositeSaveActionA; 
import ap.uat.action.DeleteActionA; 
import ap.uat.action.DeleteImageActionA; 
import ap.uat.action.FitImageActionA; 
import ap.uat.action.LookAndFillActionA; 
import ap.uat.action.ReadImageActionA; 
import ap.uat.action.ReadObjectsActionA; 
import ap.uat.action.ScreenSizeA; 
import ap.uat.action.SetActionA; 
import ap.uat.action.SetBrewActionA;
import ap.uat.action.SetColorActionA;
import ap.uat.action.SetSkipTextActionA; 
import ap.uat.action.WriteObjectsActionA; 
import ap.utils.MapUtils; 
import ap.utils.Nu; 
import ap.utils.Su; 


public class gl { 
	 

public static final int P = 0; 

public static final int R1 = 1; 

public static final int S1 = 2; 

public static final int R2 = 3; 

public static final int S2 = 4; 
	 
public static final int R3 = 5; 

public static final int S3 = 6; 

public static final int CANDLE = 0; 

public static final int BAR = 1; 

public static final int LINE = 2; 


public static final BasicStroke   	dashed_stroke_solid 	=   new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,3.0f, new float[]{4.0f}, 1.0f); 

public static final BasicStroke   	dashed_stroke 			=   new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,3.0f, new float[]{2.0f}, 1.0f); 

public static final BasicStroke   	dashed_stroke_thin 		=   new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,2.0f, new float[]{2.0f}, 2.0f); 

public static final BasicStroke   	dashed_stroke_thin_5 	=   new BasicStroke(/*height*/2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,1.0f, new float[]{/*wide*/5.0f},.0f); 


public static final List<Integer> 	ma_types = Arrays.asList(new Integer[]{TMa.M3,TMa.M7,TMa.M10,TMa.M14,TMa.M21,TMa.M55,TMa.M100,TMa.M200}); 

public static final String [] 		ma_names = new String[]{"M3","M7","M10","M14","M21","M55","M100","M200"}; 


//public static final List<Integer> 	ma_types_n = Arrays.asList(new Integer[]{Ma.M3,Ma.M5,Ma.M7,Ma.M10,Ma.M14,Ma.M21,Ma.M33,Ma.M55,Ma.M100,Ma.M144,Ma.M200}); 

public static final String [] 		ma_names_n = new String[]{"M3","M5","M7","M10","M14","M21","M33","M55","M100","M144","M200"}; 


public static final Integer []  	ma_metric_n = new Integer[]{3,5,7,10,14,21,33,55,100,144,200}; 

public static final List<Integer> 	ma_metric = Arrays.asList(ma_metric_n); 

 
public static final List<Integer> 	ohlc_types = Arrays.asList(new Integer[]{Bar.O,Bar.H,Bar.L,Bar.C}); 

public static final String [] 		ohlc_names = new String[]{"O","H","L","C"}; 

public static final List<String> 	ohlc = Arrays.asList(ohlc_names); 


public static final TLineStyle []  	ma_styles= new TLineStyle[]	{ 
	 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.white,0.8f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.blue,0.8f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.red,0.8f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.cyan,0.8f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.black,0.9f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,2)),Color.yellow,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,2)),Color.blue,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,2)),Color.white,1.0f), 
	 
	}; 


	public static final BaseAction[] actions = new BaseAction[] { 
			CompositeOpenAction.getInstance(), 
			CompositeSaveAction.getInstance(), 
			ReadObjectsAction.getInstance(), 
			ResetAction.getInstance(), 
			CreateObjectsAction.getInstance(), 
			ApplicationCloseAction.getInstance(), 
			WriteObjectsAction.getInstance(), 
			ReadImageAction.getInstance(), 
			SetSkipTextAction.getInstance(), 
			FitImageAction.getInstance(), 
			DeleteImageAction.getInstance(), 
			ShowKeyCodeAction.getInstance(), 
			RefreshObjects.getInstance(), 
			DeleteAction.getInstance(), 
			CleanDesktop.getInstance(), 
			ScreenSize.getInstance(), 
			SetText.getInstance(), 
			SetAction.getInstance(), 
			AlignAction.getInstance(), 
			LayoutAction.getInstance(), 
			SetBrewAction.getInstance(), 
			SetColorAction.getInstance(), 
			CloneColorAction.getInstance(), 
			LookAndFillAction.getInstance() 
						 
			}; 
	 
	public static final BaseAction[] actions_a = new BaseAction[] { 
		CompositeOpenAction.getInstance(), 
		CompositeSaveAction.getInstance(), 
		ReadObjectsAction.getInstance(), 
		ResetAction.getInstance(), 
		CreateObjectsAction.getInstance(), 
		ApplicationCloseAction.getInstance(), 
		WriteObjectsAction.getInstance(), 
		ReadImageAction.getInstance(), 
		SetSkipTextAction.getInstance(), 
		FitImageAction.getInstance(), 
		DeleteImageAction.getInstance(), 
		ShowKeyCodeAction.getInstance(), 
		RefreshObjects.getInstance(), 
		DeleteAction.getInstance(), 
		CleanDesktop.getInstance(), 
		ScreenSize.getInstance(), 
		SetText.getInstance(), 
		SetAction.getInstance(), 
		AlignAction.getInstance(), 
		LayoutAction.getInstance(), 
		SetBrewAction.getInstance(), 
		SetColorAction.getInstance(), 
		CloneColorAction.getInstance(), 
		LookAndFillAction.getInstance() 
		}; 
	 
	public static final BaseActionA[] actions_a_ = new BaseActionA[] { 
			CompositeOpenActionA.getInstance(), 
			CompositeSaveActionA.getInstance(), 
			ReadObjectsActionA.getInstance(), 
			//ResetAction.getInstance(), 
			//CreateObjectsAction.getInstance(), 
			ApplicationCloseActionA.getInstance(), 
			WriteObjectsActionA.getInstance(), 
			ReadImageActionA.getInstance(), 
			SetSkipTextActionA.getInstance(), 
			FitImageActionA.getInstance(), 
			DeleteImageActionA.getInstance(), 
			//ShowKeyCodeAction.getInstance(), 
			//RefreshObjects.getInstance(), 
			DeleteActionA.getInstance(), 
			CleanDesktopA.getInstance(), 
			ScreenSizeA.getInstance(), 
			//SetText.getInstance(), 
			SetActionA.getInstance(), 
			//AlignAction.getInstance(), 
			//LayoutActionA.getInstance(), 
			SetBrewActionA.getInstance(), 
			SetColorActionA.getInstance(), 
			//CloneColorAction.getInstance(), 
			LookAndFillActionA.getInstance() 
						 
			}; 
	 
	
	 

public static final int [] 				ma_period = {3,7,10,14,21,55,100,200}; 

public static final List<Integer> 		pivot_types = Arrays.asList(new Integer[]{TPivot.STANDARD,TPivot.DEMARK,TPivot.FIBONACCI}); 

public static final String [] 	    	pivot_type_names = new String[]{"STANDARD","DEMARK","FIBONACCI"}; 

public static final String [] 			bar_names = new String[]{"HIGH","BODY","LOW"}; 

public static final TLineStyle [][]  	bar_styles= new TLineStyle[][]	 
	{ 
		 
	{TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.black,0.9f),TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.white,0.9f)}, 
	{TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.yellow,0.9f),TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.blue,0.9f)}, 
	{TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.green,0.9f),TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.black,0.9f)} 
	 
}; 


								 
public static final TLineStyle []  	pivot_styles= new TLineStyle[]	{ 
	 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(2,1)),Color.white,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.yellow,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.black,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.blue.brighter(),1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.green.brighter(),1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.gray,1.0f), 
	TLineStyle.getInstance(TStroke.getInstance(new Dimension(0,1)),Color.pink,1.0f) 
	 
}; 


	public static final TLineStyle ma_on_select_style = TLineStyle.getInstance(TStroke.getInstance(new Dimension(3,2)),Color.white,1.0f); 

	public static final TLineStyle ma_on_selected_style = TLineStyle.getInstance(TStroke.getInstance(new Dimension(3,1)),Color.white,1.0f); 

	public static char DEF_CHAR = ' '; 
	 
	public static int DEF_WIDTH = 70; 
	 
	public static boolean BL_SKIP_TRACE; 
	 
	public enum AL { 
		LEFT, CENTER, RIGHT, TOP, BOTTOM; 
	} 
	 
	public class ALI { 
		 
		public static final int NONE = gl.E_ERROR; 
		public static final int LEFT = 0; 
		public static final int TOP = 1; 
		public static final int RIGHT =2; 
		public static final int BOTTOM = 3; 
		public static final int FULL = 4; 
		public static final int CENTER = 41; 
		 
		 
		public static final int GLUED_BOTTOM = 5; 
		public static final int GLUED_LEFT = 6; 
		public static final int GLUED_RIGHT = 7; 
		public static final int GLUED_TOP = 8; 
		 
		public static final int BOTTOM_LEFT = 9; 
		public static final int BOTTOM_CENTER = 10; 
		public static final int BOTTOM_RIGHT = 11; 
		 
		 
		public static final int GLUED_LEFT_SYNC = 12; 
		public static final int GLUED_RIGHT_SYNC = 13; 
		public static final int GLUED_TOP_SYNC = 14; 
		public static final int GLUED_BOTTOM_SYNC = 15; 
		 
		public static final int TOP_SYNC = 16; 
		public static final int BOTTOM_SYNC = 17; 
		 
				 
	} 
	 
	 
	 
	public class HVA  { 
		 
		public static final int LEFT = 0; 
		 
		public static final int CENTER = 1; 
		 
		public static final int RIGHT = 2; 
		 
		public static final int TOP = LEFT; 
		 
		public static final int BOTTOM = RIGHT; 
		 
		 
	}
	
	public class POINT  { 
		 
		public static final int START = 0; 
		 
		public static final int END = 1; 
		 
		public static final int CTRL1 = 2;
		
		public static final int CTRL2 = 3;
	}
	
	public static class FORM  { 
		
		public static final int DEFAULT 	= 0; 
		
		public static final int GRADIENT 	= 1; 
		 
		public static final int QUAD 		= 2; 
		 
		public static final int CUBIC 		= 3;
		
		
		public static String get_type(int type)
		{
			switch(type)
			{
			
			case FORM.DEFAULT:
				return "DEFAULT";
				
			case FORM.GRADIENT:
				return "GRADIENT";
			
			case FORM.QUAD:
				return "QUAD";
			
			case FORM.CUBIC:
				return "CUBIC";
				
				default:
					return "NONE";
			}
		}
		
	}
	
	public static long t_end(long t_start)
	{
		return (System.nanoTime() - t_start) /1000000;
	}
	public static boolean e_touch(List<Boolean> list)
	{
		return !list.contains(false);
	}

	 
	public static boolean isaHVA(Dimension dim) 
	{ 
		return isaHVA(dim.width)&& isaHVA(dim.height); 
	} 
	 
	public static boolean isaHVA(int value) 
	{ 
		return Nu.between(value,new Integer[]{ 
				gl.HVA.LEFT, 
				gl.HVA.RIGHT, 
				gl.HVA.CENTER}); 
			 
	} 
	 
	 
	 
	public static byte MOUSE_DRAGGED = 2; 

	public static byte MOUSE_DRAGGED_NORTH = 4; 

	public static byte MOUSE_DRAGGED_NORTH_WEST = 8; 

	public static byte MOUSE_DRAGGED_NORTH_EAST = 16; 

	public static byte MOUSE_DRAGGED_SOUTH = 32; 

	public static byte MOUSE_DRAGGED_SOUTH_WEST = 64; 

	public static short MOUSE_DRAGGED_SOUTH_EAST = 128; 

	public static short MOUSE_DRAGGED_WEST = 256; 

	public static short MOUSE_DRAGGED_EAST = 512; 

	 
	public static JTextArea journal = null; 
	/** 
	 * Global ID 
	 */ 
	public static int GID  = 0; 
	 
	/** 
	 * Global OK 
	 */ 
	public static final int E_OK = 1; 
	 
	/** 
	 * Global S_OK 
	 */ 
	public static final String S_OK = "Ok"; 
	 
	/** 
	 * Global S_ERROR 
	 */ 
	public static final String S_ERROR = "Error"; 
	 
	/** 
	 * Global S_WARN 
	 */ 
	public static final String S_WARN = "Warning"; 
	 
	/** 
	 * Global S_TRY 
	 */ 
	public static final String S_TRY = "Try to"; 
	
	/** 
	 * Global S_DEBUG 
	 */ 
	public static final String S_DEBUG = "Debugging"; 
	
	 
	/** 
	 * Global S_EXCEPTION 
	 */ 
	public static final String S_EXCEPTION = "Exception"; 
	 

	/** 
	 *  Global ERROR 
	 */ 
	public static final int E_ERROR = -1; 

	/** 
	 * Global EMPTY 
	 */ 
	public static final int E_EMPTY = 0; 
	 
	/** 
	 * Global trace ID 
	 */ 
	 
	public static int ZERO 	= 0; 
	 
	public static int ONE 	= 1; 
	 
	public static int TWO 	= 2; 
	 
	public static int THREE = 3; 
	 
	public static int FOUR 	= 4; 
	 
	 
	public static int TID = gl.E_EMPTY; 
	 
	public static final int E_SYNTAX_ERROR = -2; 
	 
	public static final int E_RUNTIME_ERROR = -3; 
	 
	public static final int E_FIND_ERROR = -4; 
	 
	public static final int E_BLANK_CMD = -5; 
	 
	public static final int E_PARAMS_PARSER = -6; 
	 
	public static final int E_PARAMS_MANGLING = -7; 
				 
	public static String get_error_type(int error) 
	{ 
		switch(error) 
		{ 
		case E_SYNTAX_ERROR : 
			 return "E_SYNTAX_ERROR"; 
		 
		case E_RUNTIME_ERROR : 
			return "E_RUNTIME_ERROR"; 
		 
		case E_FIND_ERROR : 
			return "E_FIND_ERROR"; 
			 
		case E_ERROR : 
			return S_ERROR; 
		 
		case E_OK : 
			return S_OK; 
			 
		case E_BLANK_CMD : 
			return "."; 
			 
		case E_PARAMS_PARSER: 
			return "E_PARAMS_PARSER"; 
		 
		case E_PARAMS_MANGLING: 
			return "E_PARAMS_MANGLING"; 
		 
			 
		} 
		 
			return "NONE"; 
		 
	} 
	 
	public static void smn(Object obj) { 

		sm(obj); 

		sm(System.lineSeparator()); 

	} 

	public static void smd(Object obj,boolean debug) { 

		if(!debug)  return; 
		 
		sm(obj); 

	} 

	 
	public static void smnd(Object obj,boolean debug) { 

		if(!debug)  return; 
		 
		sm(obj); 

		sm(System.lineSeparator()); 

	} 

	/** 
	 * @param obj 
	 */ 
	public static void sm(Object obj) { 

		System.out.print(String.valueOf(obj)); 
	} 
	 
	public static String tx_d(Object obj ,String message) 
	{ 
		return gl.tracex(obj,String.format("<<< %s >>>...%s",gl.S_DEBUG,message)); 
	} 
	
	public static String tx_e(Object obj ,String message) 
	{ 
		return gl.tracex(obj,String.format("%s...%s",gl.S_ERROR,message)); 
	} 
	 
	public static boolean  tx(Object obj ,boolean mode,String message) 
	{ 
		String msg = mode ? gl.tx_k(obj,message):gl.tx_e(obj,message); 
	
		return mode;
	} 
	
	public static boolean  tx_i(Object obj ,boolean mode,String message) 
	{ 
		String msg = mode ? gl.tx_k(obj,gl.sf("%s%s",message,mode)):gl.tx_e(obj,gl.sf("%s%s",message,mode)); 
	
		return mode;
	} 
	
	public static String  tx_k(Object obj ,String message) 
	{ 
		return gl.tracex(obj,String.format("%s...%s",gl.S_OK,message)); 
	} 
	 
	 
	/** 
	 * @return 
	 */ 
	 
	public static int decode(double a, double b) 
	{ 
		return (a>b) ? 1 : 0; 
		 
	} 
	 
	public static void blt(int[] a, int[] b,int[] r) 
	{ 
				 
		for(int i = 0 ; i < a.length; i++ ) 
		{ 
			// Change flag. 
			if(a[i] != b[i]) 
			{ 
			   r[i] = gl.E_OK; 
			} 
			else 
				r[i] = gl.E_EMPTY; 
		} 
		 
		 
	} 
	 
	 	 
	 
	/** 
	 * @return 
	 */ 
	public static int getNextId() 
	{ 
		return GID++; 
		 
	} 

	 
	/** 
	 * @param path 
	 * @return 
	 */ 
	public static long getFileSize(String path) 
	  { 
			File file = new File(path); 
		    		    		 
		    return file.length(); 
	  } 

	 

	public  static String tracex(Object value,String pmessage) 
	{ 
		if (gl.BL_SKIP_TRACE) return ""; 
		 
		String msg = String.format("%s.%s %s %s",gl.nrd(TIDGen.nextId()),"%s","%s",pmessage); 
		 
		String class_name = ""; 
		 
		try 
		{ 
			class_name = value.getClass().getEnclosingClass().getName(); 
		} 
		catch(java.lang.NullPointerException e) 
		{ 
			class_name = String.format("%s...",gl.S_WARN); 
		} 
		 
		if(class_name.trim().length() > 20) 
		{ 
			class_name = Su.AfterAtPeriod(class_name); 
		} 
		 
		String method_name = ""; 
		 
		try 
		{ 
			method_name = value.getClass().getEnclosingMethod().getName(); 
		} 
		catch(java.lang.InternalError e) 
		{ 
			method_name = "[Not found]"; 
		} 
		catch(java.lang.NullPointerException e) 
		{ 
			method_name = "[Not found]"; 
		} 

		String msg_new = String.format(msg,gl.format(class_name,gl.AL.LEFT,24),gl.format(method_name,gl.AL.LEFT,24)); 
			 
		gl.smn(msg_new); 
		 
		return msg_new; 
		 
	} 
	 
	public  static void tracex(Object value,String pmessage,JTextArea target) 
	{ 
		target.append(tracex(value,pmessage)); 
		 
	} 
	 
	public  void trace(String pmessage,JTextArea target) 
	{ 
		String msg = String.format("%d. %s%s",TID,pmessage,"\n"); 
			 
		target.append(msg); 
		 
		TID++; 
		 
	} 

	 
	 	 
	public static Rectangle scaleRect(Rectangle rect,int scale) 
	{ 
		 
		return new Rectangle(rect.x+(scale*gl.E_ERROR),rect.y+(scale*gl.E_ERROR),rect.width+scale,rect.height+scale); 
		 
	} 
	 
	public static String getHome() 
	{ 
		File file = new File("."); 
		 
		return file.getAbsolutePath(); 
	} 
	 
	 
	 
	public static String nestor(String phead,String ptail) { 

		return format(phead,gl.AL.LEFT,gl.DEF_WIDTH,'.').concat(ptail); 
	} 

	public static String nestor(String phead,String pbody,String ptail) { 

		return format((phead+pbody),gl.AL.LEFT,gl.DEF_WIDTH,'.').concat(ptail); 
	} 
	 
	public static String nr(String value) 
	{ 
		return gl.format(value,gl.AL.LEFT,16); 
	} 
	 
	public static String nr(String value,int width) 
	{ 
		return gl.format(value,gl.AL.LEFT,width); 
	} 
	 
	 
	public static String nrd(int value) 
	{ 
		return gl.format(""+value,gl.AL.LEFT,3,'.'); 
	} 
	 
	public static String format(String pstr, AL pe, int pwidth, char pchar) { 

		char prevChar = gl.DEF_CHAR; 

		gl.DEF_CHAR = pchar; 

		String tmpString = format(pstr, pe, pwidth); 

		gl.DEF_CHAR = prevChar; 

		return tmpString; 

	} 
	 
	public static String formatd(double value,AL pe ,int width) 
	{ 
		 
		Locale.setDefault(new Locale("en", "US")); 
		 
		DecimalFormat df = new DecimalFormat("######.##"); 
		 
		return gl.format(df.format(value),pe,width); 
		 
	} 
	 
	 
	public static String format2d(double value,AL pe ,int width) 
	{ 
			 
			Locale.setDefault(new Locale("en", "US")); 
			 
			DecimalFormat df = new DecimalFormat("#0.00"); 
			 
			return gl.format(df.format(value),pe,width); 
			 
	} 
	 
	public static String format3d(double value,AL pe ,int width) 
	{ 
			 
			Locale.setDefault(new Locale("en", "US")); 
			 
			DecimalFormat df = new DecimalFormat("#0.000"); 
			 
			return gl.format(df.format(value),pe,width); 
			 
	} 
	 
	public static String format4d(double value,AL pe ,int width) 
	{ 
			 
			Locale.setDefault(new Locale("en", "US")); 
			 
			DecimalFormat df = new DecimalFormat("#0.0000"); 
			 
			return gl.format(df.format(value),pe,width); 
			 
	} 
	 
	 
	public static String fmt12(double value) 
	{ 
		 
		return gl.formatd(value,AL.RIGHT,12); 
		 
	} 
	 
	public static String fmt10(double value) 
	{ 
		 
		return gl.formatd(value,AL.RIGHT,10); 
		 
	} 
	
	public static String fmt(double value,int len) 
	{ 
		 
		return gl.formatd(value,AL.RIGHT,len); 
		 
	} 
	 
	public static String fmt(double value) 
	{ 
		 
		return gl.formatd(value,AL.RIGHT,8); 
		 
	} 
	 
	public static String fmt8(double value) 
	{ 
		 
		return gl.format4d(value,AL.RIGHT,8); 
		 
	} 
	 
	public static String fmt3(double value) 
	{ 
		 
		return gl.format3d(value,AL.RIGHT,8); 
		 
	} 
	 
	public static String fmt2(double value) 
	{ 
		 
		return gl.format2d(value,AL.RIGHT,8); 
		 
	} 
	 
	 
	public static double getRei(double a , double b) 
	{ 
		if(a==gl.E_EMPTY || b == gl.E_EMPTY) 
			return 0.0; 
		else 
			return (a/b); 
	} 

	public static String format(String pstr, AL pe, int pwidth) { 

		String sResult = ""; 

		String sPstr = "<null>"; 

		if (pstr != null) 
			sPstr = pstr; 
		else 
			return format(sPstr, pe, pwidth); 

		boolean bEven = false; 

		int iLength = pstr.length(); 

		if ((iLength % 2) == 0) 
			bEven = true; 

		switch (pe) { 
		case LEFT: { 

			sResult = sPstr.trim() 
					+ gl.replicate(gl.DEF_CHAR, (pwidth - iLength)); 

			break; 
		} // LEFT 

		case CENTER: { 
			if (bEven) 
				sResult = gl.replicate(gl.DEF_CHAR, (pwidth - iLength) / 2) 
						+ sPstr.trim() 
						+ gl.replicate(gl.DEF_CHAR, (pwidth - iLength) / 2); 
			else 
				sResult = gl.replicate(gl.DEF_CHAR, (pwidth - iLength) / 2) 
						+ sPstr.trim() 
						+ gl.replicate(gl.DEF_CHAR, 
								((pwidth + 1) - iLength) / 2); 

			break; 
		} // CENTER 

		case RIGHT: { 

			sResult = gl.replicate(gl.DEF_CHAR, (pwidth - iLength)) 
					+ sPstr.trim(); 

			break; 
		} // RIGHT 

		} // switch 

		return sResult; 

	} 
	 
	public static String replicate(char pchar, int pcnt) { 
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < pcnt; i++) 
			sb.append(pchar); 

		return sb.toString(); 
	} 

	public static String replicate(String pchar, int pcnt) { 
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < pcnt; i++) 
			sb.append(pchar); 

		return sb.toString(); 
	} 

	  public static String readString(String prompt) 
		{ 

		  BufferedReader br = null; 
		 
		  String str_value = null; 
		 
		  gl.sm(prompt); 
		 
				try { 
					 
					br = new BufferedReader(new InputStreamReader(System.in)); 
					 
					str_value = br.readLine(); 
					 
				} catch (IOException e) { 
					 
					 
					gl.smn("Input error : " + e.toString()); 

					return "Error"; 
				} 
				 
				    str_value = str_value.replace("\r\n",""); 
				 
				    str_value = str_value.replace("\n",""); 
				 
					return str_value; 
		} 
		 
	  public static String getQueryDate(String pvalue) 
	  { 
		  return pvalue.substring(4,8)+"-"+ pvalue.substring(2,4) + "-" + pvalue.substring(0,2); 
	  } 
	 
	 
	 
		public static int getRandomInt(int random) 
		{ 
			Random gen = new Random(); 
			 
			return gen.nextInt(random) + 1; 
		} 
		 
		 
		  public static Color getRandomColor() 
		  { 

		    Random rand = new Random(); 

		    return new Color(rand.nextInt(256), rand.nextInt(256), 
		                     rand.nextInt(256)); 

		  } 
		  
		  public static Color get_random_color() 
		  { 

		    Random rand = new Random(); 

		    return new Color(rand.nextInt(256), rand.nextInt(256), 
		                     rand.nextInt(256),rand.nextInt(256)); 

		  } 

		public static boolean isNull(Object object) 
		{ 
			return (object == null); 
		} 
		 
		public static boolean isNullPair(Object one,Object two) 
		{ 
			return (one == null || two == null ); 
		} 
	 
		public static void changeFont(Component component,Font font) 
		{ 
			component.setFont(font); 
			 
			if(component instanceof Container) 
			{ 
				for (Component child : ((Container)component).getComponents()) 
				{ 
					changeFont(child,font); 
					 
					 
				} 
			} 
		} 
		 
		 
		public static boolean isAcceptToNumber(String value) 
		{ 
			return value.chars().allMatch(Character::isDigit); 
		} 
		 
		public static void traceOn() 
		{ 
			gl.BL_SKIP_TRACE = false; 
		} 
		 
		public static void traceOff() 
		{ 
			gl.BL_SKIP_TRACE = true; 
		} 
		 
		 
		public static Rectangle getSpannedRect(Rectangle rect,Insets insets) 
		 { 
			 return new Rectangle(new Rectangle(rect.x+insets.left,rect.y+insets.top,(rect.width - insets.left)-insets.right,(rect.height - insets.top)-insets.bottom)); 
		 } 

		public static Dimension getFontDim(Graphics g,Font font, String text) 
		{ 

			FontMetrics metrics = g.getFontMetrics(font); 

			int h = metrics.getHeight() - (metrics.getAscent() + metrics.getDescent())/2; 
			 
			int w = metrics.stringWidth(text); 
			 
			return new Dimension(w,h); 
			 
		} 

		public static Rectangle getRect(Rectangle rect,Dimension align) 
		{ 
			 
			AreaManager am = new AreaManager( 
					new Dimension(rect.width,rect.height), 
					new Dimension(3,3)); 
			 
			 return am.get(align); 
				 
		} 
		 
		 
		public static Rectangle getFontRect(Graphics g,Font font, String text,Rectangle rect,Rectangle align) 
		{ 
			 
			Dimension loc_align_one = new Dimension(align.x,align.y); 
			 
			Dimension loc_align_two = new Dimension(align.width,align.height); 
			 
			 
			if(!gl.isaHVA(loc_align_one)) 
			{ 
				loc_align_one = new Dimension(gl.HVA.CENTER,gl.HVA.CENTER); 
			} 
			 
			if(!gl.isaHVA(loc_align_two)) 
			{ 
				loc_align_two = new Dimension(gl.HVA.CENTER,gl.HVA.CENTER); 
			} 
			 
			 Dimension dim =  getFontDim(g,font,text); 
			 
			 Rectangle target = rect.getBounds(); 
			 
			 target.y = (target.y + target.height/2) + dim.height/2; 
	 
			 target.x = (target.x + target.width/2) - dim.width/2; 
			 
		  	 return new Rectangle(target); 
					 
		} 
		 
		 
		public static Rectangle getFontRectEx(Graphics g,Font font, String text,Rectangle rect,Rectangle align,Dimension delta) 
		{ 
			// For not initialized objects. 
			 
			Dimension loc_align_one = new Dimension(align.x,align.y); 
			 
			Dimension loc_align_two = new Dimension(align.width,align.height); 
			 
			 
			if(!gl.isaHVA(loc_align_one)) 
			{ 
				loc_align_one = new Dimension(gl.HVA.CENTER,gl.HVA.CENTER); 
			} 
			 
			Rectangle target = getRect(rect,loc_align_one); 
			 
			if(gl.isaHVA(loc_align_two)) 
			{ 
				Rectangle rt = getRect(target,loc_align_two); 
				 
				loc_align_one.width  = loc_align_two.width; 
				 
				loc_align_one.height  = loc_align_two.height; 
				 
				target =  new Rectangle(target.x+rt.x,target.y+rt.y,target.width,target.height); 
			} 
			 

			// Some correction for the center. 
			 
			 Dimension dim =  getFontDim(g,font,text); 
			 
	 target.y = (target.y + target.height/2) + dim.height/2; 
			 
			 if (loc_align_one.width == gl.HVA.CENTER) 
			 { 
				 target.x = (target.x + target.width/2) - dim.width/2; 
			 } else if (loc_align_one.width == gl.HVA.RIGHT) 
			 { 
				 target.x = target.x + (target.width - dim.width); 
			 } else if (loc_align_one.width == gl.HVA.LEFT) 
			 { 
				 target.x += delta.width; 
			 } 
			 
			  	return new Rectangle(target); 
					 
		} 

		 
		 
		public static Rectangle getFontRect(Graphics g,Font font, String text,Rectangle work_area,Rectangle align,Dimension delta) 
		{ 
			// For not initialized objects. 
			 
			Dimension loc_align_one = new Dimension(align.x,align.y); 
			 
			if(!gl.isaHVA(loc_align_one)) 
			{ 
				loc_align_one = new Dimension(gl.HVA.CENTER,gl.HVA.CENTER); 
			} 
			 
			 
			Dimension dim =  getFontDim(g,font,text); 
			 
			AreaManager am = new AreaManager( 
					new Dimension(work_area.width,work_area.height), 
					new Dimension(3,3)); 
			 
			 Rectangle target = am.get(loc_align_one); 

			 // Some correction for the center. 
			 
			 target.y = (target.y + target.height/2) + dim.height/2; 
			 
			 if (loc_align_one.width == gl.HVA.CENTER) 
			 { 
				 target.x = (target.x + target.width/2) - dim.width/2; 
			 } else if (loc_align_one.width == gl.HVA.RIGHT) 
			 { 
				 target.x = target.x + (target.width - dim.width); 
			 } else if (loc_align_one.width == gl.HVA.LEFT) 
			 { 
				 target.x += delta.width; 
			 } 
			 
			 
			 
			  	return new Rectangle(target); 
					 
		} 

		public static Border getBorderTypeByText(String msg) 
		{ 
			Object v = new Object(){}; 
			 
			gl.BL_SKIP_TRACE = true; 
			 
			try 
			{ 
			 
			String m = "Get type of border...%s...%s...%s"; 
					 
			gl.tracex(v,String.format(m,"input value",msg,"")); 
			 
			if(msg.equalsIgnoreCase("LoweredBevelBorder") || 
					msg.equalsIgnoreCase("Lowered") || 
					msg.equalsIgnoreCase("LBB") 
			   ) 
			{ 
				gl.tracex(v,String.format(m,"parsed as ","LoweredBevelBorder","Ok")); 
				 
				return  BorderFactory.createLoweredBevelBorder(); 
			} 
			else 
				if(msg.equalsIgnoreCase("RaisedBevelBorder") || 
					msg.equalsIgnoreCase("Raised") || 
					msg.equalsIgnoreCase("RBB") 
				   ) 
				{ 
					gl.tracex(v,String.format(m,"parsed as ","RaisedBevelBorder","Ok")); 
					 
					return  BorderFactory.createRaisedBevelBorder(); 
				} 
				else if(msg.equalsIgnoreCase("EtchedBorderRaised") || 
					msg.equalsIgnoreCase("EtchedRaised") || 
					msg.equalsIgnoreCase("ER") 
				   ) 
				{ 
					gl.tracex(v,String.format(m,"parsed as ","EtchedRaisedBorder","Ok")); 
					 
					return  BorderFactory.createEtchedBorder(EtchedBorder.RAISED); 
					 
				} 
				else if(msg.equalsIgnoreCase("EtchedBorderLowered") || 
						msg.equalsIgnoreCase("EtchedLowered") || 
						msg.equalsIgnoreCase("EL") 
					   ) 
					{ 
						gl.tracex(v,String.format(m,"parsed as ","EtchedLoweredBorder","Ok")); 
						 
						return  BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
					 
					} 
				else if(msg.equalsIgnoreCase("Dashed") || 
						msg.equalsIgnoreCase("Stroked") || 
						msg.equalsIgnoreCase("ST") || 
						msg.equalsIgnoreCase("DA") || 
						msg.equalsIgnoreCase("S") || 
						msg.equalsIgnoreCase("D") 
					   ) 
					{ 
						gl.tracex(v,String.format(m,"parsed as ","Dashed","Ok")); 
						 
						 
						return  BorderFactory.createStrokeBorder(gl.dashed_stroke); 
						 
					} 
		 
				 
					gl.tracex(v,String.format(m,"parsed as ","EmptyBorder","Ok")); 
					 
					return  BorderFactory.createEmptyBorder(); 
				 
			} // try 
			catch(Exception e) 
			{ 
				 
			} 
			finally 
			{ 
				gl.BL_SKIP_TRACE = false; 
			} 
			 
				return null; 
					 
		} 
		 
		 
		public static int getAlignTypeByText(String msg) 
		{ 
			if (msg .equalsIgnoreCase("LEFT")) 
				return ALI.LEFT; 
			else if (msg .equalsIgnoreCase("TOP")) 
				return ALI.TOP; 
			else if (msg .equalsIgnoreCase("RIGHT")) 
				return ALI.RIGHT; 
			else if (msg .equalsIgnoreCase("BOTTOM")) 
				return ALI.BOTTOM; 
			else if (msg .equalsIgnoreCase("FULL")) 
				return ALI.FULL; 
			else if (msg .equalsIgnoreCase("CENTER")) 
				return ALI.CENTER; 
			 
			 
			else if (msg .equalsIgnoreCase("GLUED_TOP")) 
				return ALI.GLUED_TOP; 
			else if (msg .equalsIgnoreCase("GLUED_LEFT")) 
				return ALI.GLUED_LEFT; 
			else if (msg .equalsIgnoreCase("GLUED_RIGHT")) 
				return ALI.GLUED_RIGHT; 
			else if (msg .equalsIgnoreCase("GLUED_BOTTOM")) 
				return ALI.GLUED_BOTTOM; 
			 
			else if (msg .equalsIgnoreCase("GLUED_TOP_SYNC")) 
				return ALI.GLUED_TOP_SYNC; 
			else if (msg .equalsIgnoreCase("GLUED_LEFT_SYNC")) 
				return ALI.GLUED_LEFT_SYNC; 
			else if (msg .equalsIgnoreCase("GLUED_RIGHT_SYNC")) 
				return ALI.GLUED_RIGHT_SYNC; 
			else if (msg .equalsIgnoreCase("GLUED_BOTTOM_SYNC")) 
				return ALI.BOTTOM_RIGHT; 
			 
			else if (msg .equalsIgnoreCase("GLUED_BOTTOM_SHADOW")) 
				return ALI.BOTTOM_LEFT; 
			else if (msg .equalsIgnoreCase("GLUED_TOP_SHADOW")) 
				return ALI.BOTTOM_CENTER; 
			 
			else if (msg .equalsIgnoreCase("BOTTOM_SYNC")) 
				return ALI.BOTTOM_SYNC; 
			else if (msg .equalsIgnoreCase("TOP_SYNC")) 
				return ALI.TOP_SYNC; 
			 
			else 
				return ALI.NONE; 
		} 
		 

		 
		public static String getAlignTypeTextByInt(int ali) 
		{ 
			 
				if( ali== ALI.LEFT ) 
					return "LEFT"; 
				if (ali == ALI.TOP ) 
					return  ("TOP"); 
			 	if (ali == ALI.RIGHT ) 
			 		return  ("RIGHT"); 
			 	if (ali == ALI.BOTTOM ) 
			 		return  ("BOTTOM"); 
			 	if (ali == ALI.FULL ) 
			 		return  ("FULL"); 
			 	 
			 	if (ali == ALI.CENTER ) 
			 		return  ("CENTER"); 
			 	 
			 	if (ali == ALI.GLUED_TOP) 
			 		return  ("GLUED_TOP"); 
			 	if (ali == ALI.GLUED_LEFT) 
			 		return  ("GLUED_LEFT"); 
			 	if (ali == ALI.GLUED_RIGHT) 
			 		return  ("GLUED_RIGHT"); 
				if (ali == ALI.GLUED_BOTTOM) 
					return  ("GLUED_BOTTOM"); 
				 
				if (ali == ALI.GLUED_TOP_SYNC) 
			 		return  ("GLUED_TOP_SYNC"); 
			 	if (ali == ALI.GLUED_LEFT_SYNC) 
			 		return  ("GLUED_LEFT_SYNC"); 
			 	if (ali == ALI.GLUED_RIGHT_SYNC) 
			 		return  ("GLUED_RIGHT_SYNC"); 
				if (ali == ALI.BOTTOM_RIGHT) 
					return  ("GLUED_BOTTOM_SYNC"); 
				 
				 
				 
				if (ali == ALI.BOTTOM_LEFT) 
					return  ("GLUED_BOTTOM_SHADOW"); 
				if (ali == ALI.BOTTOM_CENTER) 
					return  ("GLUED_TOP_SHADOW"); 
				 
				if (ali == ALI.BOTTOM_SYNC) 
					return  ("BOTTOM_SYNC"); 
				if (ali == ALI.TOP_SYNC) 
					return  ("TOP_SYNC"); 
				 
								 
			else 
					return "NONE"; 
		} 

		 
		public static int cmp(Double a,Double b) 
		{ 
			if(a > b) 
				return gl.E_OK; 
			else if(a < b) 
				return gl.E_ERROR; 
			else 
				return gl.E_EMPTY; 
		} 
		 
		 
		public static String getProperty(String source, String property,String delimiter) { 
			 
			String[] arr = source.split(delimiter); 
			 
			List<String> list = new ArrayList<String>(); 
			 
			for(int i=0;i<arr.length;i++) 
			{ 
				if(arr[i].trim().length() != gl.E_EMPTY) 
				{ 
					String temp = arr[i].trim(); 
					 
					if(!(temp.startsWith(":") || 
							temp.startsWith("-" ) || 
							temp.startsWith("!") 
							) 
						) 
						list.add(temp); 
				} 
			} 
			 
			Map<String,String> map = new HashMap<>(); 
			 
			list.forEach(a->{ 
			 
				if(a.trim().length() != gl.E_EMPTY) 
				{ 
			 
					String key = Su.BeforeAtFirst(a,'='); 
					 
					String value = Su.AfterAtFirst(a,"="); 
			 
					map.put(key, value); 
				} 
			}); 
			 
			String result = MapUtils.findValueByKey(map,property.trim()); 
			 
			return result; 
			 
		} 

		 
		public  static boolean isBit(int value,int test) 
		{ 
			return ((value & test)==test); 
		} 
		 
		public static String norm4DoubleS(String value) 
		{ 
			 
			double tmp = Double.parseDouble(value); 
			 
			String s = String.format("%.4f",tmp).replace(",","."); 
			 
			double r = Double.parseDouble(s); 
			 
			return String.format("%.4f",r); 
			 
		} 
		 
		public static double norm4Double(String value) 
		{ 
			 
			double tmp = Double.parseDouble(value); 
			 
			String s = String.format("%.4f",tmp).replace(",","."); 
			 
			return Double.parseDouble(s); 
		} 
		
		public static String norm4fbase(double value,int base,String pchar) 
		{ 
			 
			String intro = gl.sf("%.4f",value);
			
			return gl.sf("%s%s",gl.replicate(pchar,(base - intro.length())),intro);
			 
		} 
		
		public static String norm4sbase(String value,int base,String pchar) 
		{ 
			 
			String intro = gl.sf("%s",value);
			
			return gl.sf("%s%s",gl.replicate(pchar,(base - intro.length())),intro);
			 
		} 
		
		public static String norm4f_base_12(double value) 
		{ 
			return norm4fbase(value,14," ");
		}
		
		public static String norm4f_base_18(double value) 
		{ 
			return norm4fbase(value,18," ");
		}
	
		public static String norm4f_base_8(double value) 
		{ 
			return norm4fbase(value,8," ");
		}
		
		public static String norm4s_base_4(String value) 
		{ 
			return norm4sbase(value,4," ");
		}
		
		
		public static void main(String[] arg) 
		{ 
			 
			//gl.smn("Test result is : " + isBit(8,3)); 
			 
			/* 
			int [] b = {0,0,0,0,0,0,0,0}; 
			 
			int test = 254; 
			 
			ByteArrayUtil.toBitArrayFromInt(test,b); 
			 
			String msg = String.format("Input : %d == %d ",test,ByteArrayUtil.toIntFromArrayOfInt(b)); 
			 
			gl.smn(msg); 
			 
			*/ 
			 
			/* 
			Date c = gl.getDateFromString("--ss--"); 
			 
			if(c==null) 
				gl.smn("Null"); 
			*/ 
			 
			gl.smn(norm4Double("1.65656777")); 
			 
			 
		} 
		 
		public static boolean isValidFieldName(String pfieldname) 
		{ 
			if ( 
					pfieldname.equalsIgnoreCase("INOUT") || 
					pfieldname.equalsIgnoreCase("YEAR") || 
					pfieldname.equalsIgnoreCase("ERROR") || 
					pfieldname.equalsIgnoreCase("TYPE") || 
					pfieldname.equalsIgnoreCase("ENABLED") || 
					pfieldname.equalsIgnoreCase("DISABLED") || 
					pfieldname.equalsIgnoreCase("CD") || 
					pfieldname.equalsIgnoreCase("CT") || 
					pfieldname.equalsIgnoreCase("DEL") || 
					pfieldname.equalsIgnoreCase("DAY") || 
					pfieldname.equalsIgnoreCase("MONTH") || 
					pfieldname.equalsIgnoreCase("METHOD") || 
					pfieldname.equalsIgnoreCase("TIMESTAMP") || 
					pfieldname.equalsIgnoreCase("RIGHTS") || 
					pfieldname.equalsIgnoreCase("RIGHT") || 
					pfieldname.equalsIgnoreCase("RESULT") || 
					pfieldname.equalsIgnoreCase("TIME") || 
					pfieldname.equalsIgnoreCase("BYTES") || 
					pfieldname.equalsIgnoreCase("POSITION") || 
					pfieldname.equalsIgnoreCase("VALUE") || 
					pfieldname.equalsIgnoreCase("INTERVAL") || 
					pfieldname.equalsIgnoreCase("DATE") || 
					pfieldname.equalsIgnoreCase("PERCENT") || 
					pfieldname.equalsIgnoreCase("SECURITY") || 
					pfieldname.equalsIgnoreCase("CONSTRUCTOR") || 
					pfieldname.equalsIgnoreCase("ALIAS") || 
					pfieldname.equalsIgnoreCase("KEY") || 
					pfieldname.equalsIgnoreCase("FORMAT") || 
					pfieldname.equalsIgnoreCase("ROW_NUMBER") || 
					pfieldname.equalsIgnoreCase("ROWID") || 
					pfieldname.equalsIgnoreCase("JOURNAL") || 
					pfieldname.equalsIgnoreCase("SQLTEXT") 
					) 
				 
				return false; 
			else 
				return true; 
			 
			 
		} 

		public static String getCompressedName(String pvalue/*,int p*/) 
		{ 
			 
			 
			String s_result = ""; 
			 
				s_result =  pvalue.replaceAll("AGREEMENT","AGMT") 
				.replaceAll("BALANCE","BLNC") 
				.replaceAll("OBJECTIVE","OBJCTV") 
				.replaceAll("SOURCE","SRC") 
				.replaceAll("AMOUNT","AMNT") 
				.replaceAll("CURRENCY","CRNCY") 
				.replaceAll("SUBTYPE","SBTP") 
				.replaceAll("OPEN","OPN") 
				.replaceAll("CLOSE","CLS") 
				.replaceAll("TYPE","TP") 
				.replaceAll("ASSET","ASST") 
				.replaceAll("LIABILITY","LBLT") 
				.replaceAll("MAIL","ML") 
				.replaceAll("LEGALLY","LGLY") 
				.replaceAll("CREDIT","CRDT") 
				.replaceAll("FINANCIAL","FNNCL") 
				.replaceAll("MATURITY","MTRTY") 
				.replaceAll("MANAGEMENT","MGMT") 
				.replaceAll("EXPIRATION","EXPRTN") 
				.replaceAll("STATUS","STTS") 
				.replaceAll("REASON","RSN") 
				.replaceAll("PLANNED","PLNND") 
				.replaceAll("COUNT","CNT") 
				.replaceAll("DAILY","DLY") 
				.replaceAll("RESERVE","RSRV") 
				.replaceAll("ACTIVE","ACTV") 
				.replaceAll("INTEREST","INTRST") 
				.replaceAll("OVERDUE","OVRD") 
				.replaceAll("CHANGE","CHNG") 
				.replaceAll("ADVANCED","ADVNCD") 
				.replaceAll("SCHEDULE","SCHDL") 
				.replaceAll("PERIOD","PRD") 
				.replaceAll("CONTRAGENT","CNTRAGNT") 
				.replaceAll("RELATIVE","RLTV") 
				.replaceAll("ATTRIBUTE","ATTRBT") 
				.replaceAll("RANGE","RNG") 
				.replaceAll("PARAM","PRM") 
				.replaceAll("VALUE","VLE") 
				.replaceAll("ENTITY","ENTT") 
				.replaceAll("REFSWFCPID","RFSWFCPD") 
				.replaceAll("ERRORTEXT","ERRTXT") 
				.replaceAll("HISTORYID","HSTRID") 
				.replaceAll("ISDEFERRED","ISDFRRD") 
				.replaceAll("TYPE4REPORTID","TP4RPTID") 
				.replaceAll("REPOSINFO","RPSINF") 
				.replaceAll("EXECTIMECONSTRUCTOR","EXCTMCTOR") 
				.replaceAll("PREFERREDOUTPUTTYPE","PRFRRDOTPTTP") 
				.replaceAll("MODIFIEDBY","MDFDBY") 
				.replaceAll("DIGITALSIGNATURE","DGTLSGNTR") 
				.replaceAll("DOMAIN","DMN") 
				.replaceAll("MFPARENTPARAMESTIMATOR","MFPRNTPRMSTMTR") 
				.replaceAll("SHOULDUSEWARRANTY","SHLDUSWRRNT") 
				.replaceAll("SHORTQBEMODE","SHRTQBMD") 
				.replaceAll("INOUT","IN_OUT") 
				.replaceAll("YEAR","YR") 
				.replaceAll("ERROR","ERR") 
				.replaceAll("TYPE","TP") 
				.replaceAll("DISABLED","DSBLD") 
				.replaceAll("CD","C_D") 
				.replaceAll("CT","C_T") 
				.replaceAll("DEL","DL") 
				.replaceAll("MONTH","MNTH") 
				.replaceAll("METHOD","MTD") 
				.replaceAll("TIMESTAMP","TMSTMP") 
				.replaceAll("RIGHTS","RGHTS") 
				.replaceAll("RIGHT","RGHT") 
				.replaceAll("TIME","TM") 
				.replaceAll("BYTES","BTS") 
				.replaceAll("RESULT","RSLT") 
				.replaceAll("ACCESS","ACS") 
				.replaceAll("TEMPLATE","TMPLT") 
				.replaceAll("DAY","YAD") 
				.replaceAll("UNUSED","UNSD") 
				.replaceAll("COMMITTED","CMTD") 
				.replaceAll("INITIAL","INTL") 
				.replaceAll("MARGIN","MRGN") 
				.replaceAll("FUTURE","FTR") 
				.replaceAll("INTERVAL","INTRVL") 
				.replaceAll("DATE","DT") 
				.replaceAll("SECURITY","SCRTY") 
				.replaceAll("NEGATIV","NGTV") 
				.replaceAll("OVER","OVR") 
				.replaceAll("PLUS","PLS") 
				.replaceAll("ESTIMATE","ESTMT") 
				.replaceAll("PERCENT","PRCNT") 
				.replaceAll("CONSTRUCTOR","CTOR") 
				.replaceAll("ENABLED","ENBLD") 
				.replaceAll("ALIAS","ALS") 
				.replaceAll("SQLTEXT","SQLTXT") 
				.replaceAll("KEY","KY") 
				.replaceAll("INDIVIDUAL","INDVDL") 
				.replaceAll("DEPOSIT","DPST") 
				.replaceAll("PAYMENT","PMNT") 
				.replaceAll("SCALE","SCL") 
				.replaceAll("TARIFF","TRFF") 
				.replaceAll("FORMAT","FMT") 
				.replaceAll("ROWID","RWD") 
				.replaceAll("ROW_NUMBER","RWNMBR") 
				.replaceAll("JOURNAL","JRNL") 
				.replaceAll("POSITION","PSTN"); 
				 
				return s_result; 
			 
		} 


		  public static String getDateFromDts(String pdts) 
		  { 
			  return "to_date('" + pdts.substring(8,10) + "." + pdts.substring(5,7) + "." + pdts.substring(0,4) + "'," + "'dd.mm.yyyy')"; 
		  } 
		 
			public static String getHostName() { 

				String s_result = ""; 

				try { 
					InetAddress addr = InetAddress.getLocalHost(); 

					s_result = addr.getHostName(); 

				} catch (UnknownHostException e) { 
					 
				} 
				return s_result; 

			} 

			public static String getHostIp() { 

				String s_result = ""; 

				try { 
					InetAddress addr = InetAddress.getLocalHost(); 

					s_result = addr.getHostAddress(); 

				} catch (UnknownHostException e) { 
					 
				} 
				return s_result; 

			} 

			public static boolean Zip(String p_input,String p_output) 
			{ 
				 
				byte[] buffer = new byte[1024]; 
				 
		    	try{ 
		 
		    		FileOutputStream fos = new FileOutputStream(p_output); 
		    		 
		    		ZipOutputStream zos = new ZipOutputStream(fos); 
		    		 
		    		ZipEntry ze= new ZipEntry(p_input); 
		    		 
		    		zos.setLevel(Deflater.BEST_COMPRESSION); 
		    		 
		    		zos.putNextEntry(ze); 
		    		 
		    		FileInputStream in = new FileInputStream(p_input); 
		 
		    		int len; 
		    		while ((len = in.read(buffer)) > 0) { 
		    			zos.write(buffer, 0, len); 
		    		} 
		 
		    		in.close(); 
		    		 
		    		zos.closeEntry(); 
		 
		    		//remember close it 
		    		zos.close(); 
		 
		    		return true; 
		 
		    	}catch(IOException ex){ 
		    	 
		    		ex.printStackTrace(); 
		    		 
		    		return false; 
		    	 
		    	} 
		    } 

			  public static String replaceAutoIncrement(String value) 
			  { 
				 
				 if(value == null) return null; 
				 		 
				 String query_create = value.replace("\r\n",""); 
						  				 
				 String arr[] = query_create.split(","); 
				 
				 int i = 0; 
				 
				 for(String s : arr) 
					{ 
						int index =s.indexOf("GENERATED ALWAYS AS IDENTITY"); 
						 
						if(index != gl.E_ERROR) 
						{ 
							arr[i] =  s.substring(0,index); 
						} 

						i++; 
					} 
					 

					StringBuilder sb = new StringBuilder(); 
					 
					int j = 0; 
					 
					int j_len = arr.length; 
					 
					for(String s : arr) 
					{ 
						 
						sb.append("\n"); 
						 
						sb.append(s); 
							 
						if(j<j_len-1) 
						sb.append(","); 

						j++; 
						 
					} 
					 
					 return sb.toString(); 
					 
			  } 

			  public static String getTimeStamp() 
				{ 
					 
					Calendar now = GregorianCalendar.getInstance(); 
					 
					java.util.Date creationDate = now.getTime(); 
					 
					SimpleDateFormat dfo = new SimpleDateFormat("dd_MM_yy_HH_mm"); 
					 
					String s_head = dfo.format(creationDate); 
					 
					return s_head; 
					 
				} 
				 
				public static String getFullTimeStamp() 
				{ 
					 
					Calendar now = GregorianCalendar.getInstance(); 
					 
					java.util.Date creationDate = now.getTime(); 
					 
					SimpleDateFormat dfo = new SimpleDateFormat("dd-MM-yy HH:mm:ss"); 
					 
					String s_head = dfo.format(creationDate); 
					 
					return s_head; 
					 
				} 
				 
				public static void tracef(String pfile,String pvalue) { 

					try { 
						 
						 
						Calendar now = GregorianCalendar.getInstance(); 
						 
						java.util.Date creationDate = now.getTime(); 
						 
						SimpleDateFormat dfo = new SimpleDateFormat("dd_MM_yy_HH"); 

						String s_head = dfo.format(creationDate); 
						 
						FileWriter fw = new FileWriter(String.format(pfile,s_head,gl.DOP_LOG_NAME,"log"), true); 

						String str_date_time = gl.getDateTimeString(); 
						 
						fw.write("\n"); 
						 
						fw.write(str_date_time); 
						 
						fw.write(" "); 
						 
						fw.write(pvalue); 
									 

						fw.close(); 

					} catch (IOException e) { 
						 
						e.printStackTrace(); 
					} 

				} 

				public static String getDateTimeString() 
				{ 

					Calendar now = GregorianCalendar.getInstance(); 

					java.util.Date creation_date = now.getTime(); 
					 
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 

					String date_time_string = df.format(creation_date); 
					 
					return date_time_string; 

				} 

				public static boolean isAttrOnLine(String psource) 
				{ 
					int l = gl.getValueInDlm(psource,gl.PI_OPEN_TAG,gl.PI_CLOSE_TAG).trim().length(); 
					 
					return (l > 0); 
				} 

				public static String getAttrValue(String pvalue) 
				{ 
					if(gl.isAttrOnLine(pvalue)) 
					{ 
						String synName  = gl.TEMP_STRING; 
						 
						return synName; 
					} 
					 
						return ""; 
				} 

			 

				 
				public static String getOriginalName(String pvalue) 
				{ 
					if(gl.isSynonymOnLine(pvalue)) 
					{ 
						 
						int idx = pvalue.indexOf(gl.SYNONYM_OPEN_TAG); 
						 
						String origName = ""; 
						 
						if(idx != gl.E_ERROR) 
						{ 
							origName = pvalue.substring(0,idx); 
							 
							return origName; 
						} 
						else 
						return pvalue; 
					} 
					 
						return pvalue; 
				} 

				 
				public static String getValueInDlm(String psrc,String popen,String pclose) 
				{ 
					 
					TEMP_STRING = ""; 
					 
					int startIndex =  psrc.indexOf(popen)+ popen.trim().length(); 
					 
					//gl.smn(" Index : " + startIndex); 
					 
					if(startIndex== gl.E_ERROR) return psrc; 
							 
					int endIndex =  psrc.indexOf(pclose,startIndex); 
					 
					//gl.smn(" Start index : " + startIndex + " End index : " + endIndex); 
					 
					if(startIndex == gl.E_ERROR || endIndex == gl.E_ERROR) 
						return ""; 

					String strResult  = psrc.substring(startIndex, endIndex); 
					 
					TEMP_STRING = strResult; 
					 
					//gl.smn(" Return value : " + TEMP_STRING); 
					 
					return strResult; 
					 
					 
				} 

				public static boolean isSynonymOnLine(String psource) 
				{ 
					int l = gl.getValueInDlm(psource,gl.SYNONYM_OPEN_TAG,gl.SYNONYM_CLOSE_TAG).trim().length(); 
					 
					return (l > 0); 
				} 

				public static String getSynonymName(String pvalue) 
				{ 
					if(gl.isSynonymOnLine(pvalue)) 
					{ 
						String synName  = gl.TEMP_STRING; 
						 
						return synName; 
					} 
					 
						return ""; 
				} 

				public static final String SYNONYM_OPEN_TAG="["; 
				 
				public static final String SYNONYM_CLOSE_TAG="]"; 
				 
				public static final String PI_OPEN_TAG="("; 
				 
				public static final String PI_CLOSE_TAG=")"; 
				 
				public static ArrayList<String> resource = new ArrayList<String>(); 
				 
				public static String[] ALIAS_PREFIX = {"[","{","("}; 
				 
				public static String[] ALIAS_SUFIX = {"]","}",")"}; 
				 
				public static String TEMP_STRING = ""; 

				 
		public static final String BUFF_TABLE_PREFIX = "_BIP"; 

		public static final int  NESTOR_WIDTH = 70; 

		public static String HOST_NAME = gl.getHostName(); 
			 
		public static String HOST_IP = gl.getHostIp(); 
		 
		public static int  GOLDEN_CONFIG_ERROR_LIMIT = 3; 
		 
		public static String GOLDEN_TABLE_SUFF = "_INC"; 
		 
		public static String TASK_HOME = "task_home"; 
		 
		public static String BATCH_HOME = "batch_stat"; 
		 
		public static String MAGIC_WORD = "fcafkmn"; 
		 
		public static String INIT_TIMESTAMP = "1900-01-01 00:00:00.000000"; 
		 
		public static int  E_ERROR_DOUBLE = E_ERROR*2; 
		 
		public static final int MAX_BATCH_SIZE = 1000000; 
		 
		public static final int FIELD_NAME_MAX_LENGTH = 24; 
		 
		public static int       FIELDS_COUNT_MAX = 0; 
		 
		public static String    FIELDS_COUNT_MAX_OWNER = ""; 
		 
		public static final int DBTIMER_COUNT_PARAMS  = 12; 

		public static final String INDEX_FILE = "index_%s.%s"; 
		 
		public static final String CREATE_FILE = "create_%s%s.%s"; 
		 
		public static final String SELECT_FILE = "select_%s%s.%s"; 
		 
		public static final String INSERT_FILE = "insert_%s%s.%s"; 
		 
		public static final String ERROR_FILE = "error_%s%s.%s"; 
		 
		public static final String SQL_FILE = "sql_%s%s.%s"; 

		public static String DOP_LOG_NAME = ""; 
		 
		public static String  JOURNAL_TABLE = "x_journal_main"; 

		public static final int MICRO_SIZE_ROWS    = 10; 

		public static int  GE		= 0; 
		 
		public static int  GE_COUNT_LIMIT		= 100; 

		public static final int FLOW_MODE_MAIN = 0; 
		 
		public static final int FLOW_MODE_PROC = 1; 
		 
		public static String sf(String format,Object...args) 
		{ 
			return String.format(format, args); 
		} 

		public static void sfn(String format,Object...args) 
		{ 
			smn(String.format(format, args)); 
		} 

		 
		public static Dimension getScreenSize() 
		{ 
			return Toolkit.getDefaultToolkit().getScreenSize(); 
		} 
		
		
		public static boolean show_msg(String title,String message,int type,boolean mode)
		{
			try
			{
				JOptionPane.showMessageDialog(null,message,title,type);
			
				return mode;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		 
} 

/* 
-- raw 
 
disk=e 

data_dir=%s:\bin\spider\look\data\ava 

report_dir=%s:\bin\spider\look\report 

list_dir=%s:\bin\spider\look\list 


list_file=a.look 

start_date=01.01.2010 

end_date=31.12.2012 


output_forms=trace,file 

report_column_delimiter=, 

report_file_append_mode=1 

data_stream_from_db=0 

report_detail_mode=1 

portfolio_name=trio_pf 


:Cat 

:take_profit=7.5 

:stop_loss=-0.25 


: Trio 
 
:stop_loss=-0.0010 

:take_profit=0.0140 


: Jpy set. 
 
:stop_loss=-0.10 

:take_profit=1.75 


: Eur 
 
stop_loss=-0.0015 

take_profit=0.0170 



open=0,1,2,3,4,5,6,7 

close=0 

key_tool=1 
 
show_cross_xref=0 

version=v1 


-- look 

disk=e 

data_dir=%s:\bin\spider\look\data 

report_dir=%s:\bin\spider\look\report 

list_dir=%s:\bin\spider\look\list 

:report_file=a.txt 

list_file=a.look 

start_date=01.01.2010 

end_date=31.12.2012 

output_forms=trace,file 

report_column_delimiter=, 

report_file_append_mode=1 

data_stream_from_db=0 

report_detail_mode=0 

portfolio_name=jpy.stat 


:Curr like jpy cross. 

take_profit=0.75 - 2.75 

stop_loss=0.10 - 0.30 

range_step=0.05 



:Curr. 

:take_profit=0.0150 - 0.0200 

:stop_loss=0.0100 - 0.0200 

:range_step=0.0050 



:Dj30 

:take_profit=60.00 - 400.00 

:stop_loss=20.00 - 50.00 

:range_step=10.00 



:Nasdaq 

:take_profit=60.00 - 400.00 

:stop_loss=10.00 - 50.00 

:range_step=10.00 


:Sp500 

:take_profit=5.00 - 50.00 

:stop_loss=1.00 - 5.00 

:range_step=1.0 


:Cat 

:take_profit=1.5 - 20.00 

:stop_loss=0.25 - 1.5 

:range_step=0.25 


:Gm 

:take_profit=0.5 - 10.00 

:stop_loss=0.10 - 0.5 

:range_step=0.10 


:Gold 

:take_profit=15.00 - 35.00 

:stop_loss=0.50 - 1.5 

:range_step=0.5 


open=0 

:,1,2,3,4,5,6,7 

close=0 

key_tool=0 


show_cross_xref=0 

version=v1 

-- build.xml 

<?xml version="1.0" encoding="UTF-8" standalone="no"?> 
<project default="create_run_jar" name="Create Runnable Jar for Project Organizer"> 
<!--this file was created by Eclipse Runnable JAR Export Wizard--> 
<!--ANT 1.7 is required                                        --> 
<target name="create_run_jar"> 
<jar destfile="E:/bin/spider/look/lookup.jar" filesetmanifest="mergewithoutmain"> 
<manifest> 
<attribute name="Main-Class" value="ap.btn.TPackageOneManager"/> 
<attribute name="Class-Path" value="."/> 
</manifest> 
<fileset dir="E:/bin/eclipse/wsp/Organizer/bin"/> 
</jar> 
</target> 
</project> 


del /q .\report\*.* 

call ant 

java -jar lookup.jar .\jobs\start_raw.job  4321.1234 
 
 
*/ 



// Revision : 10.09.2018 12:49:15 
