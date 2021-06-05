 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Dimension; 
import javax.swing.JFrame; 
import javax.swing.SwingUtilities; 

public class Application implements IApplication { 
	 
	 
	public	static DesktopImplObject  		dio; 
	 
	public 	static FrameImplObject			fio; 
	 
	public 	static ConfigurationImplObject	cio; 
	 
	public 	static RoutineImplObject		rio; 
	 
		 

	public Application() { 
		 
		Application.cio = ConfigurationImplObject.get_instance("application_snapshot.xml",new Dimension(640,480)); 
		 
		Application.fio = FrameImplObject.get_instance(); 
		 
		Application.dio = DesktopImplObject.get_instance(Application.cio.getPrefferedSize()) ; 
		 
		Application.rio = RoutineImplObject.get_instance(); 
		 
		Application.rio.start(); 
		 
		 
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
				 
						Application.get_instance(); 
			} 
		}); 

	} 

	@Override 
	public JFrame getFrame() { 
		 
		return null; 
	} 

	@Override 
	public PanelXml getDesktop() { 
		 
		return dio.getDeskTop(); 
	} 

	public static DesktopImplObject getDio() { 
		return dio; 
	} 

	public static void setDio(DesktopImplObject dio) { 
		Application.dio = dio; 
	} 

	public static FrameImplObject getFio() { 
		return fio; 
	} 

	public static void setFio(FrameImplObject fio) { 
		Application.fio = fio; 
	} 
	 
	public static ConfigurationImplObject getCio() { 
		return Application.cio; 
	} 

	public static void setCio(ConfigurationImplObject coo) { 
		Application.cio = coo; 
	} 
	 
	public static RoutineImplObject getRio() { 
		return Application.rio; 
	} 

	public static void setRio(RoutineImplObject rio) { 
		Application.rio = rio; 
	} 

	public static Application get_instance() 
	{ 
		return new Application(); 
	} 
	 

} 
