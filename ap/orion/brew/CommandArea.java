package ap.orion.brew;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import ap.global.gl;
import ap.orion.action.BaseAction;
import ap.orion.action.LookAndFillAction;
import ap.orion.cmd.CmdProcess;
import ap.orion.response.Response;
import ap.utils.Su;

@SuppressWarnings("serial")
public class CommandArea extends JTextArea implements KeyListener{
	
	private List<Integer> 		deny_chars = Arrays.asList(new Integer[] {8,16,17,18,19,157,524});
	
	private Map<Integer,String> key_map ; 
	
	private Map<Integer,String> cmd_history ; 
	
	private StringBuilder 		cmd ;
	
	

	public Map<Integer, String> getCmd_history() {
		return cmd_history;
	}

	public void setCmd_history(Map<Integer, String> cmd_history) {
		this.cmd_history = cmd_history;
	}

	public StringBuilder getCmd() {
		return cmd;
	}

	public void setCmd(StringBuilder cmd) {
		this.cmd = cmd;
	}

	public void setKey_map(Map<Integer, String> key_map) {
		this.key_map = key_map;
	}

	public Map<Integer, String> getKey_map() {
		return key_map;
	}

	public void setKeymap(Map<Integer, String> key_map) {
		this.key_map = key_map;
	}

	public CommandArea() {
		
		this.setKey_map(new LinkedHashMap<Integer,String>());
		
		this.setCmd_history(new LinkedHashMap<Integer,String>());
		
		this.setCmd(new StringBuilder());
		
		this.addKeyListener(this);
		
	}

	public CommandArea(String text) {
		super(text);
		
	}

	public CommandArea(Document doc) {
		super(doc);
		
	}

	public CommandArea(int rows, int columns) {
		super(rows, columns);
		
	}

	public CommandArea(String text, int rows, int columns) {
		super(text, rows, columns);
		
	}

	public CommandArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		
	}


	public static CommandArea get_instance() {
		
		return new CommandArea();

	}

	

	public static void main(String[] args) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	
		int key = e.getKeyCode(); 
		
		String value = "";
		
		if(!deny_chars.contains(key) ||
				
				key == 46 ||
				key == 55// ||
				//key == 10
				
				)
		{
			value = gl.sf("%s",Su.removeNL(gl.sf("%s",e.getKeyChar())));
		}
		else if(key == 8)
		{
			//gl.smn("---> BackSpace");
			
			if (this.getCmd().toString().length() != gl.E_EMPTY)
			    this.getCmd().setLength(this.getCmd().toString().length() - 1);
			else
				return;
				
			
		}
		
		// To build command.
		this.getCmd().append(value);
		
		// To recognize some later.
		this.getKey_map().put(key, value);
		
		// To show current input for each char.
		this.getKey_map().forEach((k,v)->{
		
			gl.sfn("%d %s %s",k,v,(key == KeyEvent.VK_ENTER) ? "Enter" : "");
			
		});
		
		String m_bad = "Bad command or file name";
		
		// If press Enter.
		if(key == KeyEvent.VK_ENTER)
		{
			
			//gl.sfn("Cmd : %s ",this.getCmd().toString());
			
			// Invoke action by command.
			
			String m_cmd = this.getCmd().toString();
					
			CmdProcess process = CmdProcess.get_instance(m_cmd);
			
			if(!process.parse())
			{		

						
					this.setText(gl.sf("%s%s[%s] %s",this.getText(),System.lineSeparator(),m_cmd,m_bad));
							
					if(m_cmd.trim().length() > gl.E_EMPTY )
					{
						this.getCmd_history().put(this.getCmd_history().size()+1,m_cmd);
					}	
					
					// Reset cmd  buffer.
					this.getCmd().setLength(gl.E_EMPTY);
			
					// Show the error.
						
					gl.tx_e(new Object() {},gl.sf("%s",ap.orion.cmd.CmdProcess.indexOf(ap.orion.cmd.CmdProcess.errors,process.getState())));
			
					//Show cmd history.
					
					//gl.sfn("%s",get_cmd_history());
					
					return;
					
			} // If not parsed.
			
			
			
				BaseAction ba = CmdProcess.lookup_action_by_activator(process.getName());
					
				try
				{
					// Response catch.
					
					String m_response = "";
					
					boolean bl_performed = ba.action_performed(process);
					
					m_response = (ba.getResponse() == null) ? "" : Response.get_response(ba.getResponse());
					
					
					if (bl_performed)
					{
						
						this.setText(gl.sf("%s%s{%s} %s%s%s",
								this.getText(),
								System.lineSeparator(),
								m_cmd,
								gl.S_OK,
								//Response section.
								System.lineSeparator(),
								m_response
								
								));
						
					}
					else
					{
						
						this.setText(gl.sf("%s%s{%s} %s%s%s",
								this.getText(),
								System.lineSeparator(),
								m_cmd,
								m_bad,
								System.lineSeparator(),
								m_response
								));
					}
					
					
						if(m_cmd.trim().length() > gl.E_EMPTY )
						{
							this.getCmd_history().put(this.getCmd_history().size()+1,
									m_cmd
									);
						}
						
							this.getCmd().setLength(gl.E_EMPTY);
					
						
				}
				catch(NullPointerException npe)
				{
					
						this.setText(gl.sf("%s%s[%s] %s",this.getText(),System.lineSeparator(),m_cmd,m_bad));
					
						if(m_cmd.trim().length() > gl.E_EMPTY)
						{
							this.getCmd_history().put(this.getCmd_history().size()+1,m_cmd);
								
						}
							
				}
				finally
				{
						this.getCmd().setLength(gl.E_EMPTY);
					
				}
			
				
						// Optional to  show of the cmds history.
			
						gl.sfn("%s",get_cmd_history());
						
						this.requestFocus();
			
		}
		
	}

	public String get_cmd_history()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(gl.sf("%s%s%s%s",gl.replicate('-',32),"Cmd history",gl.replicate('-',32),System.lineSeparator()));
		
		this.getCmd_history().forEach((k,v)->{
			
			sb.append(gl.sf("%s %s%s",k,v,System.lineSeparator()));
			
		});
		
		return sb.toString();
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode(); 
		
		this.getKey_map().remove(key);
		
	}

}
