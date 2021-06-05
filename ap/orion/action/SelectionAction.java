package ap.orion.action;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.area.AreaManager;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.state.ObjectRights;
import ap.prop.SBounds;
import ap.prop.SColor;
import ap.prop.SDimension;
import ap.utils.CU;
import ap.utils.DU;
import ap.utils.Su;


public class SelectionAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"sel","sel()","s","s()","выбрать"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public SelectionAction() {
		
	}

	public SelectionAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public SelectionAction(String text) {
		super(text);
		
	}

	public SelectionAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_reset_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		// Reset.
		
		CollectionImpl.selected.forEach(a->
		{
		
			a.getState().set_selected(false);
			
			a.repaint();
			
		});
		
			return true;
	}
	
	public boolean isa_set_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		// Reset.
		
		CollectionImpl.selected.forEach(a->
		{
		
			a.getState().set_selected(false);
			
			a.repaint();
			
		});
		
		
		// Clear selected before.
		
		CollectionImpl.selected.clear();
		
		// Refresh. 
		
		CollectionImpl.refresh();
		
		
		// Add to selected.
		
		CollectionImpl.selected.addAll(CollectionImpl.items);
		
		CollectionImpl.selected.removeAll(CollectionImpl.get_by_right_not(CollectionImpl.items,ObjectRights.DELETABLE));
		
		
		int m_selected_size = CollectionImpl.selected.size();
	
		gl.tx_k(new Object(){},gl.sf("Селектировано объектов...%d",m_selected_size));		
		
		if(m_selected_size == gl.E_EMPTY)
		{
			return true;
		}
		
		// Turn On.
		
		CollectionImpl.selected.forEach(a->
		{
			if(a.getRights().is_selectable())	
			   a.getState().set_selected(true);
					
			   a.repaint();
					
		});
		
			return true;
		
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		gl.tx_k(new Object(){},gl.sf("Activate action...%s",this.get_action_name()));		
		
		CmdProcess p = this.getProcess();
		
		try
		{
		
		 if 	(p.get_count_of_headers() 	== gl.E_OK 	&&
					
		    	 p.get_count_of_switches() 	== gl.E_OK  &&
		
		    	 p.get_count_of_params() 	== gl.E_EMPTY 
				)
		 {
	
		 	// Check of the switch.
		 
			if(p.isa_set_option())
			{ 
				return isa_set_impl();
				
			} else if(p.isa_reset_option())
			{ 
				return isa_reset_impl();
				
			}		
			else
			{
				return false;
			}
		 }
		else
		{	
				return false;
		}
	 
	 		
	 
	} // try.
	catch(Exception e)
	{
				return false;
	}
	 
	} 
	
	

	public static SelectionAction get_instance()
	{
		SelectionAction lafa = new SelectionAction(); 
		
		return new SelectionAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}


