package ap.orion.app;


import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.impl.CollectionImpl;
import ap.orion.impl.ConfigurationImpl;
import ap.orion.impl.DesktopImpl;
import ap.orion.impl.FrameImpl;
import ap.orion.impl.RoutineImpl;
import ap.orion.state.ApplicationStateImpl;
import ap.orion.state.ObjectRights;
import ap.orion.state.ObjectState;
import ap.utils.Biu;


 
public class Application { 
	 
	public	static 	String 	  				AP = "Organizer 10.08012021.1451"; 
	 
	public	static 	DesktopImpl  			dio; 
	 
	public 	static 	FrameImpl				fio; 
	 
	public 	static 	ConfigurationImpl		cio; 
	 
	public 	static 	RoutineImpl				rio; 
	 
	public 	static 	CollectionImpl			dbio; 
	
	public	static	ApplicationStateImpl	state;
	 
		 

	public Application() { 
		 
		Application.cio 	= ConfigurationImpl.get_instance("application_snapshot.xml"); 
		 
		Application.fio 	= FrameImpl.get_instance(AP); 
		 
		Application.dio 	= DesktopImpl.get_instance(new JPanel(),Application.cio.getStartup_size()) ; 
		
		Application.state 	= ApplicationStateImpl.get_instance();
		 
		Application.rio 	= RoutineImpl.get_instance(); 
		
		 
		Application.rio.start(); 
		 
		 
	} 

	
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

	

	public static ApplicationStateImpl getState() {
		return state;
	}


	public static void setState(ApplicationStateImpl state) {
		Application.state = state;
	}


	public static DesktopImpl getDio() { 
		return dio; 
	} 

	public static void setDio(DesktopImpl dio) { 
		Application.dio = dio; 
	} 

	public static FrameImpl getFio() { 
		return fio; 
	} 

	public static void setFio(FrameImpl fio) { 
		Application.fio = fio; 
	} 
	 
	public static ConfigurationImpl getCio() { 
		return Application.cio; 
	} 

	public static void setCio(ConfigurationImpl coo) { 
		Application.cio = coo; 
	} 
	 
	public static RoutineImpl getRio() { 
		return Application.rio; 
	} 

	public static void setRio(RoutineImpl rio) { 
		Application.rio = rio; 
	} 
	 
	 
	public static CollectionImpl getDsio() { 
		return dbio; 
	} 

	public static void setDsio(CollectionImpl dsio) { 
		Application.dbio = dsio; 
	} 

	public static Application get_instance() 
	{ 
		return new Application(); 
	} 

	 
	public JFrame get_frame() { 
	 
		return fio.get_frame(); 
	} 

	
	public Orion get_desk_top() { 
	 
		return dio.get_desk_top(); 
	} 
	 
	 
	/*
	public static void reset_selected()
	{

		if(CollectionImpl.selected.size()==gl.E_EMPTY)
		{
			return;
		}
		
		gl.tx_k(new Object() {},gl.sf("Сброс селектированых объектов...%d",CollectionImpl.selected.size()));
		
		CollectionImpl.selected.forEach(a->{
			
			 a.getState().set_selected(false);
			 
			 a.repaint();
						 
		});	 
		
			CollectionImpl.selected.clear();
		
		
	}
	

	public static boolean  reset_focused(Orion owner)
	{
		
		List<Orion> m_focused = owner.get_all_childs().stream().filter(b->(b.getState().is_focused())).collect(Collectors.toList());
		
		m_focused.forEach(a->
		{
			a.getState().set_focused(false);
			 
			 a.repaint();
			
		});
		
		boolean bl_result = (m_focused.size() == gl.E_EMPTY);
		
		m_focused.addAll(owner.get_all_childs().stream().filter(b->(b.getState().is_focused())).collect(Collectors.toList()));
		
		return gl.tx(new Object() {},bl_result,gl.sf("Сброс фокусированых объектов...[%d]",m_focused.size()));
		
	}
	
	*/
	
	public static boolean reset_target()
	{
		Application.getDio().get_desk_top().get_all_childs().forEach(a->{
			
			a.setTarget(null);
			
		});
		
			return true;
	}
	
	
	

	
	public static void repaint_all()
	{
		CollectionImpl.items.forEach(a->
	    {
	    	a.repaint();
	    });
	 
	}
	
	public static List<Orion> get_items_by_rights(int right) 
	{ 
		return CollectionImpl.items.stream().filter(b->( Biu.ISA(b.getRights().get_rights(),right))).collect(Collectors.toList()); 
	} 


	public static List<Orion> get_items_by_state(int state) 
	{ 
		return CollectionImpl.items.stream().filter(b->( Biu.ISA(b.getState().get_state(),state))).collect(Collectors.toList()); 
	} 

	public static List<Orion> get_items_by_state_c(int state) 
	{ 
		return Application.getDio().get_desk_top().get_all_childs().stream().filter(b->( Biu.ISA(b.getState().get_state(),state))).collect(Collectors.toList()); 
	} 
	
	
	public static void register_component(Orion comp)
	{
		
		if(!comp.is_desk_top())
		CollectionImpl.get_items().add(comp);
		
		//CollectionImpl.show("After create an object", CollectionImpl.get_items());
	}
	

} 
