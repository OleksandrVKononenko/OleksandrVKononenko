package ap.orion.lsnr;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import ap.global.gl;
import ap.orion.action.BaseAction;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.ActionListenerImpl;

public class BtnActionListener extends ActionListenerImpl {

	public BtnActionListener() {
		
	}

	 @Override 
	 public void actionPerformed(ActionEvent e) { 
	  
		 String m_action_cmd = e.getActionCommand();
		 
		 // Параметр.
		 

			gl.tx_k(new Object(){},gl.sf("Команда на входе...[%s]",m_action_cmd));                 
		
		 // Попытка парсинга.
		 
		 CmdProcess p = CmdProcess.get_instance(m_action_cmd);
			
			if(!m_action_cmd.startsWith(".") )
			{	
		 
				gl.tx_k(new Object(){},gl.sf("Button action...[%s]",m_action_cmd));                 
			}
			else
			{
				
				// Парсинг.
					
				gl.tx(new Object(){},p.parse(),gl.sf("Попытка парсинга...[%s]",m_action_cmd));                 
			
				BaseAction ba = CmdProcess.lookup_action_by_activator(p.getName());
			
				if(ba == null)
				{
					gl.tx_e(new Object(){},gl.sf("Попытка активации незарег. экшна...[%s]",m_action_cmd));                 
			
					return;
				}
				
				SwingUtilities.invokeLater( new Runnable() {
			        public void run() {
						
			        	gl.tx(new Object(){},ba.action_performed(p),gl.sf("Активация экшна...[%s]",m_action_cmd));                 
				
			        }});
				
				
			}
	 }          
	 
	
	public static BtnActionListener get_instance()
	{
		 return new BtnActionListener();
	}
    
	public static void main(String[] args) {
		

	}

}
