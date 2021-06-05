package ap.uat;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import ap.global.gl;
import ap.uat.action.SetBrewActionA;

public class BrewActionListener extends ActionListenerImpl {

	public BrewActionListener() {
		
	}
	
	
	public static BrewActionListener get_instance()
	{
		return new BrewActionListener();
	}
	

	 @Override 
	   public void actionPerformed(ActionEvent e) { 
	    	 
	        JComboBox<String> combo = (JComboBox<String>) e.getSource(); 
	 
	        JComboBox<String> box = (JComboBox)e.getSource(); 
			 
			String item = (String)box.getSelectedItem(); 

			gl.tracex(new Object(){},gl.sf("Selected...%s",item)); 

			SetBrewActionA sba = SetBrewActionA.getInstance(); 
			 
			AtOm desktop = ApplicationA.getDio().get_desk_top(); 
			 
			CmdA	cmd = CmdA.get_instance(desktop,gl.sf(".br %s",item)); 
			 
			desktop.setFire_cmd(cmd); 
			 
			sba.actionPerformed(desktop); 
	 
	    } 
	

	public static void main(String[] args) {
		

	}

	
	
}
