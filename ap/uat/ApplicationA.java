
package ap.uat; 


import java.awt.Dimension; 
import javax.swing.JFrame; 
import javax.swing.SwingUtilities; 

 
public class ApplicationA implements IApplicationA{ 
	 
	public	static String 	  				AP = "Organizer 3.20 UAT"; 
	 
	public	static DesktopImplObjectA  		dio; 
	 
	public 	static FrameImplObjectA			fio; 
	 
	public 	static ConfigurationImplObjectA	cio; 
	 
	public 	static RoutineImplObjectA		rio; 
	 
	public 	static StoreImplObject			dsio; 
	 
		 

	public ApplicationA() { 
		 
		ApplicationA.cio = ConfigurationImplObjectA.get_instance("application_snapshot.xml",new Dimension(640,480)); 
		 
		ApplicationA.fio = FrameImplObjectA.get_instance(AP); 
		 
		ApplicationA.dio = DesktopImplObjectA.get_instance(new Dimension(621,438)/*ApplicationA.cio.getPrefferedSize()*/) ; 
		 
		ApplicationA.rio = RoutineImplObjectA.get_instance(); 
		 
		ApplicationA.rio.start(); 
		 
		 
	} 

	@Override 
	public void run() { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
	 
			public void run() { 
				 
			} 
		}); 

	} 

	public static void main(String[] args) { 
		 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				 
						ApplicationA.get_instance(); 
			} 
		}); 

	} 


	public static DesktopImplObjectA getDio() { 
		return dio; 
	} 

	public static void setDio(DesktopImplObjectA dio) { 
		ApplicationA.dio = dio; 
	} 

	public static FrameImplObjectA getFio() { 
		return fio; 
	} 

	public static void setFio(FrameImplObjectA fio) { 
		ApplicationA.fio = fio; 
	} 
	 
	public static ConfigurationImplObjectA getCio() { 
		return ApplicationA.cio; 
	} 

	public static void setCio(ConfigurationImplObjectA coo) { 
		ApplicationA.cio = coo; 
	} 
	 
	public static RoutineImplObjectA getRio() { 
		return ApplicationA.rio; 
	} 

	public static void setRio(RoutineImplObjectA rio) { 
		ApplicationA.rio = rio; 
	} 
	 
	 
	public static StoreImplObject getDsio() { 
		return dsio; 
	} 

	public static void setDsio(StoreImplObject dsio) { 
		ApplicationA.dsio = dsio; 
	} 

	public static ApplicationA get_instance() 
	{ 
		return new ApplicationA(); 
	} 

	 
	@Override 
	public JFrame get_frame() { 
	 
		return fio.get_frame(); 
	} 

	 
	@Override 
	public AtOm get_desk_top() { 
	 
		return dio.get_desk_top(); 
	} 
	 
	 
	 

} 
