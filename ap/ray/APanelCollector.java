 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Point; 
import java.io.Serializable; 
import java.util.ArrayList; 

public class APanelCollector extends ACollector<APanelj>{ 
	 
	 
	public static int OBJECTS_COUNT = 10; 

	public APanelCollector() { 
		//  Auto-generated constructor stub 
	} 

	 
	 
	public APanelCollector(String home) 
	{ 
	 
		super(home); 
		 
	} 
	 
	public String toString() 
	{ 
		if(data == null ) return ""; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		for(APanelj apj : data) 
		{ 
			sb.append(apj.toString()+"\n"); 
		} 
		 
		return sb.toString(); 
	} 
	 
	 
	public static void TestPanel() 
	{ 
		 
		String home = System.getProperty("user.dir") + "\\panel_collector.xml"; 
		 
		 APanelCollector apc = new APanelCollector(home); 
		 
		 apc.load(); 
		 
		 apc.clear(); 
		 
		 if (gl.DeleteFile(home)) 
		 { 
			 gl.smn("Ok. Store file : " + home  + " is successfully deleted . "); 
		 } 
			 
			 
		 Dimension dim_a = gl.getScreenDimension(); 
		 

		 Dimension dim = new Dimension(121,61); 
		 
		 
		 for(int i = 0; i < OBJECTS_COUNT ; i++) 
		 { 
			 	 
		   Dimension dim_t = new Dimension(3,3); 
				 
		 
		   dim = gl.getAligmentDimension(dim,dim_t); 
		 
			APanelj some = new APanelj(new AreaManager(dim, dim_t, new AreaPointIndex(new Dimension(/* 
														 * Size of new area in 
														 * cell 
														 */3,3), 0)), 
														 
														 
					gl.getRandomColor()); 

			some.setLocation(new Point( 
					gl.getRandomedInt((int) dim_a.getWidth() - 120), gl 
							.getRandomedInt((int) dim_a.getHeight() - 60 ))); 
			 
			AreaManager am = new AreaManager(gl.getAligmentDimension(some.getSize(),dim_t),dim_t, new AreaPointIndex(new Dimension(/* 
					 * Size of new area in 
					 * cell 
					 */1, 1),4)); 
			 
					 
			APanelj some_child = new APanelj(am,gl.getRandomColor()); 
			 

			Dimension d_in = some_child.getSize(); 
			 
			Dimension d_out = gl.getAligmentDimension(some_child.getSize(),dim_t); 
			 
			gl.smn("In : " + d_in.toString()) ; 
			 
			gl.smn("Out : " + d_out.toString()) ; 
						 
					 
			AreaManager am_1 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),4)); 
					 
			APanelj some_child_1 = new APanelj(am_1,gl.getRandomColor()); 
			 
			 
			AreaManager am_2 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),3)); 
			 
			APanelj some_child_2 = new APanelj(am_2,gl.getRandomColor()); 

			AreaManager am_3 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),5)); 
			 
			APanelj some_child_3 = new APanelj(am_3,gl.getRandomColor()); 

			some_child_1.setNoMove(); 
			 
			some_child_2.setBl_v(true); 
			 
			some_child_3.setBl_h(true); 
			 
			 
			some_child_1.setGum(true); 
			 
			some_child_2.setGum(true); 
			 
			some_child_3.setGum(true); 
			 
			 
			 
			 
			some_child.add(some_child_1); 
			 
			some_child.add(some_child_2); 
			 
			some_child.add(some_child_3); 
			 
			some.add(some_child); 
			 
			apc.add(some);				 
			 
			 
		 } 
		 
		 if(apc.write()) 
			 gl.smn("Write Ok."); 
		 
		 

		 
	} 

	public static void TestPanelOpt() 
	{ 
		 
		 String home = System.getProperty("user.dir") + "\\panel_collector.xml"; 
		 
		 
		 if (gl.DeleteFile(home)) 
		 { 
			 gl.smn("Ok. Store file : " + home  + " is successfully deleted . "); 
		 } 
			 
			 
		 Dimension dim_a = gl.getScreenDimension(); 
		 
		 Dimension dim = new Dimension(121,61); 
		 
		 Dimension dim_t = new Dimension(3,3); 
		 
		 for(int i = 0; i < OBJECTS_COUNT ; i++) 
		 { 
			 	 
		 
		   dim = gl.getAligmentDimension(dim,dim_t); 
		 
			APanelj some = new APanelj(new AreaManager(dim, dim_t, new AreaPointIndex(new Dimension(/* 
														 * Size of new area in 
														 * cell 
														 */3,3), 0)), 
					gl.getRandomColor()); 

			some.setLocation(new Point( 
					gl.getRandomedInt((int) dim_a.getWidth() / 2), gl 
							.getRandomedInt((int) dim_a.getHeight() / 2))); 
			 
			some.setHome(home); 
			 
			some.write(); 
			 
			AreaManager am = new AreaManager(gl.getAligmentDimension(some.getSize(),dim_t),dim_t, new AreaPointIndex(new Dimension(/* 
					 * Size of new area in 
					 * cell 
					 */1, 1),4)); 
			 
					 
			APanelj some_child = new APanelj(am,gl.getRandomColor()); 
			 
			Dimension d_in = some_child.getSize(); 
			 
			Dimension d_out = gl.getAligmentDimension(some_child.getSize(),dim_t); 
			 
			//gl.smn("In : " + d_in.toString()) ; 
			 
			//gl.smn("Out : " + d_out.toString()) ; 
						 
					 
			AreaManager am_1 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),4)); 
					 
			APanelj some_child_1 = new APanelj(am_1,gl.getRandomColor()); 
			 
			 
			AreaManager am_2 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),3)); 
			 
			APanelj some_child_2 = new APanelj(am_2,gl.getRandomColor()); 

			 
			AreaManager am_3 = new AreaManager(gl.getAligmentDimension(some_child.getSize(),dim_t),dim_t,new AreaPointIndex(new Dimension(1, 1),5)); 
			 
			APanelj some_child_3 = new APanelj(am_3,gl.getRandomColor()); 

			 
			APanelj obj[] = new APanelj[]{some_child_1,some_child_2,some_child_3}; 

			some_child_1.setBl_h(true); 
			 
			 
			some_child_2.setBl_h(true); 
			 
			some_child_2.setOut_of_bounds(true); 
			 
			 
			some_child_3.setBl_h(true); 
			 
			 
			some_child_1.setGum(true); 
			 
			some_child_2.setGum(true); 
			 
			some_child_3.setGum(true); 
			 
			 
			some_child.add(some_child_1); 
			 
			some_child.add(some_child_2); 
			 
			some_child.add(some_child_3); 
			 
			 
			some.add(some_child); 
			 
			some_child.write(); 
			 
			some_child_1.write(); 
			 
			some_child_2.write(); 
			 
			some_child_3.write(); 
			 
		 } 
		 } 
		 
		 
		 public static void TestPanelEx() 
			{ 
				 
				//Prepare actions 
			 
			 	String home = System.getProperty("user.dir") + "\\panel_collector.xml"; 
			 	 
			 	APanelj.home  = home; 
				 
				 
				 if (!gl.DeleteFile(home)) 
				 { 
					 gl.smn("Error while delete of store file : " + home); 
				 } 
					 
					 
				 Dimension dim_a = gl.getScreenDimension(); 
				 
				 for(int i = 0; i < OBJECTS_COUNT ; i++) 
				 { 
					 
					APanelj root = new APanelj(121,61,3,3,2,4,0,Color.red); 

					root.setLocation(new Point(gl.getRandomedInt((int) dim_a.getWidth()), gl 
									.getRandomedInt((int) dim_a.getHeight()))); 
					 
					root.write(); 
					 
					APanelj child_c = new APanelj(root.getSize().width,root.getSize().height,3,3,1,3,5,Color.yellow); 
					 
					root.add(child_c); 
					 
					child_c.write(); 
					 
				 } 
			} 
		 
				 public static void TestPanelNew() 
					{ 
						 
						//Prepare actions 
					 
					 	String home = System.getProperty("user.dir") + "\\panel_collector.xml"; 
					 	 
					 	APanelj.home  = home; 
						 
						 
						 if (!gl.DeleteFile(home)) 
						 { 
							 gl.smn("Error while delete of store file : " + home); 
						 } 
							 
							 
						 Dimension dim_a = gl.getScreenDimension(); 
						 
						 for(int i = 0; i < OBJECTS_COUNT ; i++) 
						 { 
							 
							 
							AreaManagerEx am = new AreaManagerEx(121,61,3,3,3,3,0,0); 
							 
							APanelj root = new APanelj(am,Color.red); 

							root.setLocation(new Point(gl.getRandomedInt((int) 30), gl 
											.getRandomedInt((int) 30))); 
							 
							root.setName("Root"); 
							 
							root.write(); 
							 
							 
							AreaManagerEx am1 = new AreaManagerEx(am.get().width,am.get().height, 10, 10, 10, 1, 0,0); 
				 
							AreaManagerEx am2 = new AreaManagerEx(am.get().width,am.get().height, 10, 10, 10, 1, 0,9); 
							 
							AreaManagerEx am_left = new AreaManagerEx(am.get().width,am.get().height, 10, 10, 1, 10, 0,0); 
							 
							AreaManagerEx am_right = new AreaManagerEx(am.get().width,am.get().height, 10, 10, 1,10, 9,0); 
							 
							 
							APanelj child_l = new APanelj(am_left, gl.getRandomColor()); 
							 
									child_l.setBl_v(true); 
									 
									child_l.setGum(true); 
									 
									child_l.setName("Left"); 
							 
							APanelj child_r = new APanelj(am_right, gl.getRandomColor()); 
							 
									child_r.setBl_v(true); 
									 
									child_r.setGum(true); 
									 
									child_r.setName("Right"); 
									 
							 
							APanelj child_t = new APanelj(am1, gl.getRandomColor()); 
								 
									child_t.setOut_of_bounds(true); 
									 
									child_t.setName("Top"); 
							 
							 
							APanelj child_b = new APanelj(am2, gl.getRandomColor()); 
									 
									child_b.setName("Bottom"); 
							 
							 
							APanelj a[] = new APanelj[]{child_l,child_r,child_t,child_b}; 
							 
							 
							for(APanelj ap : a) 
							{ 
								root.add(ap); 
								 
								ap.write(); 
							} 
						 } 
						 
						 		gl.smn("Done."); 
						 
				 
	} 
	 
	public static void main(String[] args) { 
		//  Auto-generated method stub 

			APanelCollector.TestPanelNew(); 
			 
		 
		 	} 
} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
