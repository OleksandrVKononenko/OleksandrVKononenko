 
package ap.uat; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 


public class ActionListenerImpl implements ActionListener{ 

	public ActionListenerImpl() { 
		
		super();
	
		
	}
	

	public static void main(String[] args) { 
		 

	} 
	 
	/*
	public static ActionListener get_brew_listener() 
	{ 
		return new ActionListener() { 
			 
		    @Override 
		    public void actionPerformed(ActionEvent e) { 
		    	 
		        JComboBox<String> combo = (JComboBox<String>) e.getSource(); 
		 
		        JComboBox box = (JComboBox)e.getSource(); 
				 
				String item = (String)box.getSelectedItem(); 

				gl.tracex(new Object(){},gl.sf("Selected...%s",item)); 

				SetBrewActionA sba = SetBrewActionA.getInstance(); 
				 
				AtOm desktop = ApplicationA.getDio().get_desk_top(); 
				 
				CmdA	cmd = CmdA.get_instance(desktop,gl.sf(".br %s",item)); 
				 
				desktop.setFire_cmd(cmd); 
				 
				sba.actionPerformed(desktop); 
		 
		    } 
		}; 
		 
	} 
	 
	public static ActionListener get_look_and_fill_listener() 
	{ 
		return new ActionListener() { 
			 
		    @Override 
		    public void actionPerformed(ActionEvent e) { 
		    	 
		        JComboBox<String> combo = (JComboBox<String>) e.getSource(); 
		 
		        JComboBox box = (JComboBox)e.getSource(); 
				 
				String item = (String)box.getSelectedItem(); 
				 
				int index = box.getSelectedIndex(); 

				gl.tracex(new Object(){},gl.sf("Selected l&f class...%s",item)); 

				 
				LookAndFillActionA sba = LookAndFillActionA.getInstance(); 
				 
				AtOm desktop = ApplicationA.getDio().get_desk_top(); 
				 
				CmdA	cmd = CmdA.get_instance(desktop,gl.sf(".lf /s  %d",index)); 
				 
				desktop.setFire_cmd(cmd); 
				 
				sba.actionPerformed(desktop); 
		 
		    } 
		}; 
		 
	} 
	
	*/
	public static ActionListenerImpl get_instance()
	{
		return new ActionListenerImpl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

} 
