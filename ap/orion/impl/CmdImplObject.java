 
package ap.orion.impl; 

import java.util.List; 

import ap.action.Cmd; 
import ap.global.gl; 

public class CmdImplObject { 
	 
	 
	private 	StringBuilder 		cmd 	  ; 
	 
	private 	Cmd 				fire_cmd  ; 
	 
	private  	List<Cmd> 			cmds ; 
	 
	private 	int 				cmd_scroll 	= gl.E_EMPTY; 
	 
	 

	public int getCmd_scroll() { 
		return cmd_scroll; 
	} 

	public void setCmd_scroll(int cmd_scroll) { 
		this.cmd_scroll = cmd_scroll; 
	} 

	public List<Cmd> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<Cmd> cmds) { 
		this.cmds = cmds; 
	} 

	public StringBuilder getCmd() { 
		return cmd; 
	} 

	public void setCmd(StringBuilder cmd) { 
		this.cmd = cmd; 
	} 

	public Cmd getFire_cmd() { 
		return fire_cmd; 
	} 

	public void setFire_cmd(Cmd fire_cmd) { 
		this.fire_cmd = fire_cmd; 
	} 

	public CmdImplObject() { 

		this.setCmd(new StringBuilder()); 
		 
	} 
	 
	public static CmdImplObject get_instance() { 

		return new CmdImplObject(); 
	} 


	public static void main(String[] args) { 


	} 

} 
