 
package ap.ray; 

import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.Composite; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.Random; 
import java.util.Scanner; 

public class gl { 
	 
	public static int  IID = gl.E_OK; 
	 
	public static final int E_OK = 1; 

	public static final int E_ERROR = -1; 

	public static final int E_EMPTY = 0; 

	  public static final int ALIGN_LEFT  = 20; 
	 
	  public static final int ALIGN_RIGHT  = 21; 
	 
	  public static final int ALIGN_TOP = 22; 
	 
	  public static final int ALIGN_BOTTOM  = 23; 
	 
	  public static final int ALIGN_CENTER  = 24; 

	  public static final int ALIGN_NONE  = 25; 
	 
	  public static final int ACTION_NONE    = 26; 
	 
	  public static final int ACTION_LOAD_DATA_FILE  = 27; 
	 
	  public static final int ACTION_LOAD_IMAGE_FILE  = 277; 
	 
	  public static final int ACTION_LOAD_POOL  = 28; 
	 
	  public static final int ACTION_APP_EXIT  = 29; 
	 
	  public static final int ACTION_IMPORT_DATA  = 30; 
	 
	  public static final int ACTION_LOAD_CONFIG  = 31; 
	 
	  public static final int ACTION_NODE    = 32; 

	  public static final int ACTION_FORM_CLOSE    = 33; 
	 
	  public static final int ACTION_VALUE_INCREMENT    = 34; 
	 
	  public static final int ACTION_VALUE_DECREMENT    = 35; 
	 
	  public static final int ACTION_SCROLL_LEFT   = 36; 
	 
	  public static final int ACTION_SCROLL_RIGHT  = 37; 
	 
	  public static final int ACTION_SCROLL_UP   = 38; 
	 
	  public static final int ACTION_SCROLL_DOWN  = 39; 
	 
	  public static final int ACTION_OBJECT_CLOSE  = 40; 
	 
	  public static final int ACTION_OBJECT_TREASURE  = 41; 
	 
	  public static final int ACTION_OBJECT_SELECTED  = 42; 
	 
	  public static final int ACTION_FORM_MIN = 43; 
	 
	  public static final int ACTION_FORM_MAX = 44; 
	 
	  public static final int ACTION_FORM_ASIS = 45; 
	 
	  public static final int ACTION_HOMOGENEITY_TEST = 46; 
	 
	  public static final int ACTION_COMBOBOX_ITEMS_SHOW = 47; 
	 
	  public static final int ACTION_COMBOBOX_ITEMS_SELECTED = 48; 
	 
	  public static final int ACTION_OBJECT = 49; 
	 
	  public static final int ACTION_WM_CLOSE = 50; 
	 
	  public static final int ATOM_FACTORY_NONE  = 500; 
	 
	  public static final int ATOM_FACTORY_BASE  = 501; 
	 
	  public static final int ATOM_FACTORY_FORM_CAPTION  = 502; 
	 
	  public static final int ATOM_FACTORY_LAST  = 599; 
	 
	  public static final int ALIGN_TYPE_NONE = 700; 
	 
	  public static final int ALIGN_TYPE_ELASTIC = 701; 
	 
	  public static final int ALIGN_TYPE_DUO = 702; 
	 
	  public static final int ALIGN_TYPE_TRIO = 703; 
	 
	  public static final int ALIGN_TYPE_ONE   = 704; 
	 
	  public static final int ALIGN_TYPE_BASE   = 705; 


	  public static int DRAG_TYPE_LEFT = 0; 
	 
	  public static int DRAG_TYPE_LEFT_TOP = 1; 
	 
	  public static int DRAG_TYPE_TOP = 2; 
	 
	  public static int DRAG_TYPE_RIGHT_TOP = 3; 
	 
	  public static int DRAG_TYPE_RIGHT = 4; 
	 
	  public static int DRAG_TYPE_RIGHT_BOTTOM = 5; 
	 
	  public static int DRAG_TYPE_BOTTOM = 6; 
	 
	  public static int DRAG_TYPE_LEFT_BOTTOM = 7; 
	 
	  public static int DRAG_TYPE_BODY = 8; 
	 
	  public static int DRAG_ZONE_WIDTH = 5; 
	 
	 
	  public static boolean DeleteFile(String pvalue) 
	  { 

	    if(pvalue == null || pvalue.trim().length() == 0) 
	    { 
	       //gl.smn("<gl.isaFile()> File name is null."); 
	 
	       return false; 
	    } 

	    File f = new File(pvalue); 

	    if(f.isFile() && f.canRead()) 
	    { 
	    	return f.delete(); 
	    } 
	 
	    	return f.canRead(); 
	  } 

	public static void smn(Object obj) { 
		System.out.println(String.valueOf(obj)); 
	} 

	public static void sm(Object obj) { 
		System.out.print(String.valueOf(obj)); 
	} 
	  public static String getFileAsStringScanner(String pathname) throws IOException { 

	      File file = new File(pathname); 
	      StringBuilder fileContents = new StringBuilder((int)file.length()); 
	      Scanner scanner = new Scanner(file); 
	      String lineSeparator = System.getProperty("line.separator"); 

	      try { 
	          while(scanner.hasNextLine()) { 
	              fileContents.append(scanner.nextLine() + lineSeparator); 
	          } 
	          return fileContents.toString(); 
	      } finally { 
	          scanner.close(); 
	      } 
	  } 

	  public static boolean isaFile(String pvalue) 
			    throws java.io.FileNotFoundException 
			  { 

			    if(pvalue == null || pvalue.trim().length() == 0) 
			    { 
			       //gl.smn("<gl.isaFile()> File name is null."); 
			 
			       return false; 
			    } 

			    File f = new File(pvalue); 

			    return (f.isFile() && f.canRead()); 
			 
			  } 
	 
		public static Point getCellCoord( int index , int cell_x_pow, int cell_y_pow) 
		{ 
				return new Point(index - (((index/cell_y_pow))*cell_x_pow),(index/cell_y_pow)); 
		} 


		  public static Color getRandomColor() 
		  { 

		    Random rand = new Random(); 

		    return new Color(rand.nextInt(256), rand.nextInt(256), 
		                     rand.nextInt(256)); 

		  } 
		 
			public static int getDragType(Rectangle rect,Point point) 
			{ 
				Rectangle r[] = gl.getRectsforDragType(rect); 
				 
				for(Rectangle a : r ) 
					gl.smn(a.toString()); 
				 
				if(r[gl.DRAG_TYPE_LEFT].contains(point)) 
					return gl.DRAG_TYPE_LEFT; 
				else if(r[gl.DRAG_TYPE_LEFT_TOP].contains(point)) 
					return gl.DRAG_TYPE_LEFT_TOP; 
				else if(r[gl.DRAG_TYPE_TOP].contains(point)) 
					return gl.DRAG_TYPE_TOP; 
				else if(r[gl.DRAG_TYPE_RIGHT_TOP].contains(point)) 
					return gl.DRAG_TYPE_RIGHT_TOP; 
				else if(r[gl.DRAG_TYPE_RIGHT].contains(point)) 
					return gl.DRAG_TYPE_RIGHT; 
				else if(r[gl.DRAG_TYPE_RIGHT_BOTTOM].contains(point)) 
					return gl.DRAG_TYPE_RIGHT_BOTTOM; 
				else if(r[gl.DRAG_TYPE_BOTTOM].contains(point)) 
					return gl.DRAG_TYPE_BOTTOM; 
				else if(r[gl.DRAG_TYPE_LEFT_BOTTOM].contains(point)) 
					return gl.DRAG_TYPE_LEFT_BOTTOM; 
				else 
					return gl.DRAG_TYPE_BODY; 
			} 
			 
			public static String decodeDragType(int type) 
			{ 
				 
				if(type==gl.DRAG_TYPE_LEFT  ) 
					return "DRAG_TYPE_LEFT"; 
				else if(type==gl.DRAG_TYPE_LEFT_TOP  ) 
					return "gl.DRAG_TYPE_LEFT_TOP"; 
				else if(type==gl.DRAG_TYPE_TOP  ) 
					return "gl.DRAG_TYPE_TOP"; 
				else if(type==gl.DRAG_TYPE_RIGHT_TOP  ) 
					return "gl.DRAG_TYPE_RIGHT_TOP"; 
				else if(type==gl.DRAG_TYPE_RIGHT  ) 
					return "gl.DRAG_TYPE_RIGHT"; 
				else if(type==gl.DRAG_TYPE_RIGHT_BOTTOM ) 
					return "gl.DRAG_TYPE_RIGHT_BOTTOM"; 
				else if(type==gl.DRAG_TYPE_BOTTOM  ) 
					return "gl.DRAG_TYPE_BOTTOM"; 
				else if(type==gl.DRAG_TYPE_LEFT_BOTTOM  ) 
					return "gl.DRAG_TYPE_BOTTOM_LEFT"; 
				else 
					return "gl.DRAG_TYPE_BODY"; 
			} 

			public static Rectangle getSizedRect(int type,Rectangle input,int dx, int dy) 
			{ 
				 
				Rectangle rect = new Rectangle(); 
				 
				rect  = input; 
				 
				int ldx = dx / 2 ; 
				 
				int ldy = dy / 2 ; 
						 
				if(type==gl.DRAG_TYPE_LEFT  ) 
				{ 
						rect.x =  input.x + ldx; 
						 
						rect.width = input.width + ldx*gl.E_ERROR; 
						 
				} else if(type==gl.DRAG_TYPE_RIGHT  ) 
				{ 
					 
						rect.width = input.width + ldx; 
						 
				} else if(type==gl.DRAG_TYPE_TOP  ) 
				{ 
						rect.y = input.y + ldy; 
						 
						rect.height = input.height + ldy*gl.E_ERROR; 
						 
				} 
				else if(type==gl.DRAG_TYPE_BOTTOM  ) 
				{ 
						 
						rect.height = input.height + ldy; 
						 
				} 
				else if(type==gl.DRAG_TYPE_LEFT_TOP  ) 
				{ 
					 
					rect.x =  input.x + ldx; 
					 
					rect.width = input.width + ldx; 
					 
					rect.y =  input.y + ldy; 
					 
					rect.height = input.height + ldy*gl.E_ERROR; 
					 
						 
				} 
				else if(type==gl.DRAG_TYPE_RIGHT_TOP  ) 
				{ 
						 
					rect.width = input.width + ldx; 
					 
					rect.y =  input.y + ldy; 
					 
					rect.height = input.height + ldy*gl.E_ERROR; 
					 
						 
				} else if(type==gl.DRAG_TYPE_LEFT_BOTTOM  ) 
				{ 
						 
					rect.x =  input.x + ldx; 
					 
					rect.width = input.width + ldx*gl.E_ERROR; 

					rect.height = input.y + ldy; 
					 
						 
				} else if(type==gl.DRAG_TYPE_LEFT_TOP  ) 
				{ 
						 
					rect.x +=  ldx; 
					 
					rect.y +=  ldy; 
					 
					rect.width = input.width  + ldx*gl.E_ERROR; 
					 
					rect.height = input.y + ldy; 
						 
				} 

				 
				return rect; 
			} 
			 
			public static Rectangle getSizedRectEx(int type,Rectangle input,Point point) 
			{ 
				 
				Rectangle rect = new Rectangle(); 
				 
				rect  = input; 
				 
				int ldx = point.x - input.x; 
				 
				int ldy = point.y - input.y; 
						 
				if(type==gl.DRAG_TYPE_LEFT  ) 
				{ 
						rect.x =  input.x + ldx; 
						 
						rect.width = input.width + ldx; 
						 
				} else if(type==gl.DRAG_TYPE_RIGHT  ) 
				{ 
					 
						rect.width = input.width + ldx; 
						 
				} else if(type==gl.DRAG_TYPE_TOP  ) 
				{ 
						rect.y = input.y + ldy; 
						 
						rect.height = input.height + ldy*gl.E_ERROR; 
						 
				} 
				else if(type==gl.DRAG_TYPE_BOTTOM  ) 
				{ 
						 
						rect.height = input.height + ldy; 
						 
				} 
				else if(type==gl.DRAG_TYPE_LEFT_TOP  ) 
				{ 
					 
					rect.x =  input.x + ldx; 
					 
					rect.width = input.width + ldx; 
					 
					rect.y =  input.y + ldy; 
					 
					rect.height = input.height + ldy*gl.E_ERROR; 
					 
						 
				} 
				else if(type==gl.DRAG_TYPE_RIGHT_TOP  ) 
				{ 
						 
					rect.width = input.width + ldx; 
					 
					rect.y =  input.y + ldy; 
					 
					rect.height = input.height + ldy*gl.E_ERROR; 
					 
						 
				} else if(type==gl.DRAG_TYPE_LEFT_BOTTOM  ) 
				{ 
						 
					rect.x =  input.x + ldx; 
					 
					rect.width = input.width + ldx*gl.E_ERROR; 

					rect.height = input.y + ldy; 
					 
						 
				} else if(type==gl.DRAG_TYPE_LEFT_TOP  ) 
				{ 
						 
					rect.x +=  ldx; 
					 
					rect.y +=  ldy; 
					 
					rect.width = input.width  + ldx*gl.E_ERROR; 
					 
					rect.height = input.y + ldy; 
						 
				} 

				 
				return rect; 
			} 

			public static Dimension getScreenDimension() 
			{ 
				return Toolkit.getDefaultToolkit().getScreenSize(); 
				 
			} 
	 
			public static Rectangle[] getRectsforDragType(Rectangle rect) 
			{ 
			 
				Rectangle rects[] = new Rectangle[]{ 
				 
				//Left		 
				new Rectangle(rect.x,rect.y+gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH,rect.height-(gl.DRAG_ZONE_WIDTH*2)-1), 
				 
				//LeftTop		 
				new Rectangle(rect.x,rect.y,gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH), 
						 
				//Top 
				new Rectangle(rect.x+gl.DRAG_ZONE_WIDTH,rect.y,rect.width-(gl.DRAG_ZONE_WIDTH*2)-1,gl.DRAG_ZONE_WIDTH), 
				 
				//TopRight 
				new Rectangle((rect.x+rect.width)-gl.DRAG_ZONE_WIDTH-1,rect.y,gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH), 
						 
				 
				//Right 
				new Rectangle((rect.x+rect.width)-gl.DRAG_ZONE_WIDTH-1,rect.y+gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH,rect.height-(gl.DRAG_ZONE_WIDTH*2)-1), 
				 
				//RightBottom 
				new Rectangle((rect.x+rect.width)-gl.DRAG_ZONE_WIDTH-1,(rect.y+rect.height) - gl.DRAG_ZONE_WIDTH-1,gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH), 
						 
				//Bottom 
				new Rectangle(rect.x+gl.DRAG_ZONE_WIDTH,(rect.y+rect.height) - gl.DRAG_ZONE_WIDTH-1,rect.width-(gl.DRAG_ZONE_WIDTH*2)-1,gl.DRAG_ZONE_WIDTH), 

				//BottomLeft 
				new Rectangle(rect.x,(rect.y+rect.height) - gl.DRAG_ZONE_WIDTH-1,gl.DRAG_ZONE_WIDTH,gl.DRAG_ZONE_WIDTH) 

				}; 
				 
				return rects; 
			} 

			public static Dimension getAligmentDimension(Dimension size,Dimension cells) 
			{ 
				Dimension dim_d = getDimensionDelta(size,cells); 
				 
				if (!isInPlace(dim_d)) 
				{ 
					return new Dimension(size.width - dim_d.width,size.height - dim_d.height); 
				} 
				 
					return size; 
				 
			} 
			 
			public static boolean isInPlace(Dimension delta) 
			{ 
				return (delta.width== gl.E_EMPTY && delta.height == gl.E_EMPTY); 
			} 

			public static Dimension getDimensionDelta(Dimension size,Dimension cells) 
			{ 
				return new Dimension(size.width % cells.width,size.height % cells.height); 
			} 

			  public static int getRandomedInt(int pvalue) 
			  { 

			    Random rand = new Random(); 

			    int r = 0; 
			 
			    r +=rand.nextInt(pvalue-r); 
			 
			    return r; 

			  } 

			  public static String getRandomedBinString(int count) 
			  { 

			    StringBuilder sb = new StringBuilder(); 
			 
			    for(int i=0; i< count ;i++) 
			    	sb.append(gl.getRandomedBin() +""); 
					 

			     return sb.toString(); 
			  } 
			 
			 
			  public static int getRandomedBin() 
			  { 

			    Random rand = new Random(); 
			 
			    return rand.nextInt(2); 
			 

			  } 

			  public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
int py, Color pcolor, float alpha) 
{ 

Composite originalComposite = g.getComposite(); 

g.setComposite(gl.makeComposite(alpha)); 

Color old = g.getColor(); 

g.setColor(pcolor); 

g.drawString(pvalue, px, py); 

g.setColor(old); 

g.setComposite(originalComposite); 

} 
			  public static AlphaComposite makeComposite(float alpha) 
			  { 
			    int type = AlphaComposite.SRC_OVER; 
			    return (AlphaComposite.getInstance(type, alpha)); 
			  } 

			 
public static void drawAlphaString(Graphics2D g, String pvalue, int px, 
int py, Color pcolor,Font font, float alpha) 
{ 
Font old_font = g.getFont(); 

g.setFont(font); 

drawAlphaString(g,pvalue,px,py,pcolor,alpha); 

g.setFont(old_font); 



} 

public static void trace(String pfile,String pvalue) { 

	try { 
		FileWriter fw = new FileWriter(pfile, true); 

		fw.write(pvalue); 

		fw.close(); 

	} catch (IOException e) { 
		// REPLACE Auto-generated catch block 
		e.printStackTrace(); 
	} 

} 

public static int getNextId() 
{ 
	  return gl.IID++; 
} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
