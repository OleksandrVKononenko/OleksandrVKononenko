 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.action; 

import java.util.Arrays; 
import java.util.List; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 


@SuppressWarnings("serial") 
public class SetText extends BaseAction { 

	public static final String [] CMDS = new String[] {"text","txt","txt()","text()"}; 
	 
	public static final String [] VALID_PARAMS = new String[] {"/all","/a","/s","/selected","/global","/gl","/g"}; 
	 
	 
	public SetText() { 
		 
	} 

	public SetText(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon, desc, mnemonic); 
		 
	} 

	public SetText(String text) { 
		super(text,Arrays.asList(CMDS)); 
		 
	} 

	public SetText(String text, List<String> cmd_list) { 
		super(text, cmd_list); 
		 
	} 
	 
	public static SetText getInstance() 
	{ 
		SetText cmp = new SetText(); 
		 
		return new SetText(cmp.getClass().getSimpleName()); 
	} 

	@Override 
	public boolean actionPerformed(PanelXml owner) { 

		String msg = gl.sf("Action...%s", this.getValue(NAME)); 

		String [] NOT_VALID_PARAMS = {""}; 
		 
		Cmd fire_cmd = owner.getFire_cmd(); 
				 
		try 
		{ 
			if (!CmdParams.checkParams(fire_cmd,VALID_PARAMS,NOT_VALID_PARAMS)) 
		    { 
				gl.tracex(new Object() {},gl.sf("%s...%s...not valid params list...%s...%s",msg, this.getActionName(),NOT_VALID_PARAMS[gl.E_EMPTY], gl.S_ERROR)); 
			 
				return false; 
		    } 
			 
			 if (CmdParams.extractCmdMap(fire_cmd).containsKey(CmdParams.ARGS)) 
			 { 
				 gl.smn("args"); 
					 
				 CmdParams.extractCmdMap(fire_cmd).get(CmdParams.ARGS).forEach(a-> 
				 { 
					 gl.smn(a); 
				 }); 
			 } 
			 
			 if (CmdParams.extractCmdMap(fire_cmd).containsKey(CmdParams.PARAMS)) 
			 { 
				 gl.smn("params"); 
					 
				 CmdParams.extractCmdMap(fire_cmd).get(CmdParams.PARAMS).forEach(a-> 
				 { 
					 gl.smn(a); 
				 }); 
			 } 
			 
				return true; 
				 
		} 
		catch(Exception e) 
		{ 
			gl.tracex(new Object() {},gl.sf("Action...%s...%s...%s",this.getActionName(),e.toString(), gl.S_ERROR)); 

			return false; 
		} 

	} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
