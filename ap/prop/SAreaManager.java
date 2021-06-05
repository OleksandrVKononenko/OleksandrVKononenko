 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.prop; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.io.Serializable; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import ap.area.AreaManager; 
import ap.global.gl; 
import ap.ray.APanelj; 
import ap.utils.Fu; 
import ap.utils.Su; 


public class SAreaManager extends SProperty  { 

	private AreaManager area_manager; 
	 
	public SAreaManager() { 
		super("area_manager"); 
	} 

	public SAreaManager(AreaManager manager) { 
		 
		this(); 
		 
		this.setArea_manager(manager); 
		 
	} 

	public SAreaManager(String name) { 
		super(name); 
		 
	} 

	public SAreaManager(String name, String pvalue) { 
		super(name, pvalue); 
		 
	} 

	public SAreaManager(String name, String pvalue, String dlm) { 
		super(name, pvalue, dlm); 
		 
	} 

	 
	public AreaManager getArea_manager() { 
		return area_manager; 
	} 

	public void setArea_manager(AreaManager area_manager) { 
		this.area_manager = area_manager; 
	} 

	public static boolean equal(AreaManager area1,AreaManager area2) 
	{ 
		return  ( 
				SDimension.equal(area1.getArea(),area2.getArea()) && 
				SDimension.equal(area1.getAreaInCells(),area2.getAreaInCells()) && 
				SDimension.equal(area1.getObjectSizeInCells(),area2.getObjectSizeInCells()) && 
				SDimension.equal(area1.getObjectCoordInCells(),area2.getObjectCoordInCells()) 
				); 
				 
	} 
	 
	public static AreaManager parse(String name, String payload) 
	{ 
		SAreaManager i = new SAreaManager(name); 
		 
		if(!i.parse(payload)) 
			return new AreaManager(); 
		 
		return i.getArea_manager(); 
	} 
	 
	 
	@Override 
	public boolean parse(String value) 
	{ 
		Object v  = new Object(){}; 
		 
		if (!super.parse(value)) 
			return false; 
		 
		int k = 0; 
		 
		 
			if ( this.getValue_map().size() != 9 ) 
			{	 
				gl.tracex(v,String.format("%s...%s...%s...%d",gl.nr("Parsing area manager "),"error","wrong params count != 9 ",this.getValue_map().size())); 
					 
				return false; 
				 
			} 


			try 
			{ 
				 
				 
				AreaManager am =  new AreaManager( 
						 
				new Dimension (Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++))), 
				new Dimension (Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++))), 
				new Dimension (Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++))), 
				new Dimension (Integer.parseInt(this.getValue_map().get(k++)), 
				Integer.parseInt(this.getValue_map().get(k++)))); 
			 
				 
				String s_distrib_type =  this.getValue_map().get(k++); 
				 
				int i_distrib_type = gl.E_ERROR; 
				 
				if (Su.isNumber(s_distrib_type)) 
				{ 
					i_distrib_type = Integer.parseInt(s_distrib_type); 
				} 
				else 
				{ 
					i_distrib_type = gl.getAlignTypeByText(s_distrib_type); 
				} 
				 
				am.setDistributionType(i_distrib_type); 
				 
				this.setArea_manager(am); 
							 
			 
		} 
		catch(NumberFormatException e) 
		{ 
				gl.tracex(v,String.format("%s...%s...%s","parsing area manager","error exception ",e.toString())); 
			 
				return false; 
		} 
	 
				if(this.isDebug()==true) 
				gl.tracex(v,String.format("%s...%s...%s",gl.nr("parsing area manager"),"Ok.",this.toStringValue())); 
		 
				return true; 
	} 
	 
	public static void doTest(String home) 
	{ 
			 
			Object v  = new Object(){}; 
		 
	 		Map<Integer,String> raw_map = new LinkedHashMap<Integer,String>(); 
	 		 
	 		String source = ""; 
	 		 
	 		int error_count = gl.E_EMPTY; 
	 		 
	 		try { 
				 
	 			source = Fu.get_str_from_file(home); 
	 			 
	 			String arr[] = source.split(System.lineSeparator()); 
	 			 
	 			for(int i=0;i < arr.length;i++) 
	 			{ 
	 				raw_map.put(i,arr[i]); 
	 				 
	 				SAreaManager sb = new SAreaManager("area_manager"); 
					 
					if (!sb.parse(arr[i])) 
						error_count++; 
						 
					 
	 			} 
	 			 
	 			if(error_count==gl.E_EMPTY) 
		 		gl.tracex(v,String.format("%s...%s...",gl.nr("Test done"),"Ok")); 
	 			else 
	 			gl.tracex(v,String.format("%s...%s...%d",gl.nr("Test done with"),"Errors.",error_count)); 

	 			 
	 			 
	 		} 
	 		catch(Exception e) 
	 		{ 
	 			gl.tracex(v,String.format("%s...%s...%s","Exception","Error.",e.toString())); 
				 
	 		} 
		} 
	 
	public static String toString(AreaManager area) 
	{ 
		 
		return String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d", 
				area.getArea().width, 
				area.getArea().height, 
				area.getAreaInCells().width, 
				area.getAreaInCells().height, 
				area.getObjectSizeInCells().width, 
				area.getObjectSizeInCells().height, 
				area.getObjectCoordInCells().width, 
				area.getObjectCoordInCells().height, 
				area.getDistributionType() 
				 
				); 
		 
	} 
	 
	 
	 
	public static String toStringForDlgForm(AreaManager area) 
	{ 
		 
		return String.format("%d,%d,%d,%d,  %s,%s,%s,%s, %s ", 
				area.getArea().width, 
				area.getArea().height, 
				area.getAreaInCells().width, 
				area.getAreaInCells().height, 
				 
				(area.getObjectSizeInCells().width==gl.E_ERROR) ? "0":(area.getObjectSizeInCells().width)+"", 
				(area.getObjectSizeInCells().height==gl.E_ERROR) ? "0":(area.getObjectSizeInCells().height)+"", 
 
				(area.getObjectCoordInCells().width==gl.E_ERROR) ? "0":(area.getObjectCoordInCells().width)+"", 
				(area.getObjectCoordInCells().height==gl.E_ERROR) ? "0":(area.getObjectCoordInCells().height)+"", 

				gl.getAlignTypeTextByInt(area.getDistributionType()) 
				 
				 ); 
		 
		 
	} 
	 
	@Override 
	public String toString() 
	{ 
		//Not parsing 
		if(this.getValue_map().size()==gl.E_EMPTY) 
					return String.format("%s=%s%s", 
							this.getName(), 
							toString(this.getArea_manager()), 
							this.getAttr_delimeter() 
							); 
							 
		else 
			return super.toString(); 
			 
	} 
	 
	public static void doTest_toString() 
	{ 
		SAreaManager a = new SAreaManager(new AreaManager(gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR)); 
		 
		gl.smn("AreaManager test info : " + a.toString()); 
	} 
	 
	 
	public static void doTest_parse(String payload) 
	{ 
		SAreaManager a = new SAreaManager(); 
		 
		if(!a.parse(payload)) 
		{ 
			gl.smn("Error while parse : " + payload); 
			 
			return ; 
		} 
		else 
			gl.smn("OK while parse : " + payload); 
			 
		gl.smn("AreaManager test info : " + a.toString()); 
	} 
	 
	 
	public static void main(String[] args) { 
		 
		//doTest(APanelj.home); 
		 
		//doTest_toString(); 
		 
		doTest_parse("area_manager=1,1,1,1,1,1,1,1;"); 

	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:46 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
