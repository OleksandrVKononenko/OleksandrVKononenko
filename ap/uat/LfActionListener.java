package ap.uat;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import ap.global.gl;
import ap.uat.action.LookAndFillActionA;
import ap.uat.action.SetBrewActionA;

public class LfActionListener extends ActionListenerImpl {

	public LfActionListener() {
		
	}
	
	
	public static LfActionListener get_instance()
	{
		return new LfActionListener();
	}
	

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
	 
	
	public static void main(String[] args) {
		

	}

	
	
}
