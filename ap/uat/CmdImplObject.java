 
 
 
 
 
 
package ap.uat; 

import java.util.List; 

import ap.action.Cmd; 
import ap.global.gl; 

public class CmdImplObject { 
	 
	 
	private 	StringBuilder 		cmd 	  ; 
	 
	private 	CmdA 				fire_cmd  ; 
	 
	private  	List<CmdA> 			cmds ; 
	 
	private 	int 				cmd_scroll 	= gl.E_EMPTY; 
	 
	 

	public int getCmd_scroll() { 
		return cmd_scroll; 
	} 

	public void setCmd_scroll(int cmd_scroll) { 
		this.cmd_scroll = cmd_scroll; 
	} 

	public List<CmdA> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<CmdA> cmds) { 
		this.cmds = cmds; 
	} 

	public StringBuilder getCmd() { 
		return cmd; 
	} 

	public void setCmd(StringBuilder cmd) { 
		this.cmd = cmd; 
	} 

	public CmdA getFire_cmd() { 
		return fire_cmd; 
	} 

	public void setFire_cmd(CmdA fire_cmd) { 
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
