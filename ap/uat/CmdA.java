 
 
 
 
 
 
package ap.uat; 

import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Map.Entry; 

import ap.action.BaseAction; 
import ap.action.CmdParams; 
import ap.gen.CmdId; 
import ap.global.gl; 
import ap.swing.Application; 
import ap.uat.AtOm; 


public class CmdA { 

	public static List <String> SELECTION_SCOPE = Arrays.asList(new String[] {"/s","/sel"}); 
	 
	public static List <String> ALL_SCOPE 		= Arrays.asList(new String[] {"/a","/all","/gl"}); 
	 
	public static List <String> OP_ON 			= Arrays.asList(new String[] {"/e","/1","/on"}); 
	 
	public static List <String> OP_OFF 			= Arrays.asList(new String[] {"/d","/0","/off"}); 
	 
	public static List <String> OP_REVERSE 		= Arrays.asList(new String[] {"/r","/-1","/rev"}); 
	 
	public static List <String> OP_LIST 		= Arrays.asList(new String[] {"/i","/l","/7","/list","/show"}); 
	
	public static List <String> OP_BACK 		= Arrays.asList(new String[] {"/b","/back"});
	
	public static List <String> OP_FRONT 		= Arrays.asList(new String[] {"/f","/front"});
	
	public static List <String> OP_TEXT 		= Arrays.asList(new String[] {"/t","/text"});
	
	public static List <String> OP_FORM 		= Arrays.asList(new String[] {"/dlg","/form"});
	
	public static List <String> OP_SETUP 		= Arrays.asList(new String[] {"/set","/setup"});
	
		 
	private int 			id; 
	 
	private String 			cmd; 
	 
	private int  			state; 
	 
	private String 			description; 
	 
	private String 			action; 
	 
	private AtOm 			owner; 
	 
	private String 			parameters;	 
	 
	private List<String> 	parameter_list = new ArrayList<String>(); 
	 
	 
	public boolean isScope_selected() { 

		return CmdParamsA.isaParams(this,SELECTION_SCOPE); 
	} 

	 
	public boolean isScope_all() { 
		 
		return CmdParamsA.isaParams(this,ALL_SCOPE); 
		 
	} 
	 
	public boolean isOp_On() { 
		 
		return CmdParamsA.isaParams(this,OP_ON); 
		 
	} 
	 
	public boolean isOp_Off() { 
		 
		return CmdParamsA.isaParams(this,OP_OFF); 
		 
	} 

	public boolean isOp_Reverse() { 
		 
		return CmdParamsA.isaParams(this,OP_REVERSE); 
		 
	} 
	 
	public boolean isOp_List() { 
		 
		return CmdParamsA.isaParams(this,OP_LIST); 
		 
	} 
	
	public boolean isOp_Back() { 
		 
		return CmdParamsA.isaParams(this,OP_BACK); 
		 
	} 

	public boolean isOp_Front() { 
		 
		return CmdParamsA.isaParams(this,OP_FRONT); 
		 
	} 
	
	public boolean isOp_DialogForm() { 
		 
		return CmdParamsA.isaParams(this,OP_FORM); 
		 
	} 
	
	public boolean isOp_Setup() { 
		 
		return CmdParamsA.isaParams(this,OP_SETUP); 
		 
	} 
	
	 
	public int get_params_count() 
	{ 
		return this.getParameter_list().size(); 
	} 

	public boolean get_params_count_b() 
	{ 
		return (get_params_count() != gl.E_EMPTY); 
	} 
	 
	public List<String> getParameter_list() { 
		return parameter_list; 
	} 

	 
	public void setParameter_list(List<String> parameter_list) { 
		this.parameter_list = parameter_list; 
	} 

	public String getParameters() { 
		return parameters; 
	} 

	public void setParameters(String parameters) { 
		this.parameters = parameters; 
	} 

	public AtOm getOwner() { 
		return owner; 
	} 

	public void setOwner(AtOm owner) { 
		this.owner = owner; 
	} 

	public int getId() { 
		return id; 
	} 

	public void setId(int id) { 
		this.id = id; 
	} 

	public String getCmd() { 
		return cmd; 
	} 

	 
	public void setCmd(String cmd) { 
		 
		this.cmd = cmd; 
		 
	} 

	public int getState() { 
		return state; 
	} 

	public void setState(int status) { 
		this.state = status; 
	} 

	public String getDescription() { 
		return description; 
	} 

	public void setDescription(String description) { 
		this.description = description; 
	} 

	public String getAction() { 
		return action; 
	} 

	public void setAction(String action) { 
		this.action = action; 
	} 

	public CmdA() { 
		 
		this.setId(CmdId.nextId()); 
	} 

	public CmdA(String cmd) { 

		this(); 
		 
		this.setCmd(cmd); 
		 
		this.setState(gl.E_ERROR); 
		 
	} 
	 
	public CmdA(AtOm owner,String cmd) { 

		this(cmd); 
		 
		this.setOwner(owner); 
		 
		owner.setFire_cmd(this); 
		 
		this.accept(); 
		 
		// Re-request focus after each cmd. 
		ApplicationA.getDio().get_desk_top().requestFocus(); 
	} 
	 
	public CmdA(AtOm owner,String cmd,boolean owner_focused) { 

		this(cmd); 
		 
		this.setOwner(owner); 
		 
		owner.setFire_cmd(this); 
		 
		this.accept(); 
		 
		// Re-request focus after each cmd. 
		if(owner_focused) 
		ApplicationA.getDio().get_desk_top().requestFocus(); 
	} 
	 
	 
	public CmdA(String cmd,int  status) { 
		 
		this(cmd); 
		 
		this.setState(status); 
	} 
	 
	public boolean cmd_check_syntax(String cmd) 
	{ 
		 
		if(cmd.trim().length() == gl.E_EMPTY  || 
			!cmd.startsWith(".") 			 
			) 
			return false; 
		 
			return true; 
	} 
			 
	 
	public BaseAction cmd_find(String cmd) 
	{ 
		String cm = cmd.substring(gl.E_OK); 
						 
		for(Entry<String,BaseAction> e : Application.rio.actions_map.entrySet()) 
		{ 
			if (e.getValue().getCmds().contains(cm)) 
				return e.getValue(); 
		} 
		 
				return null; 
		 
	} 
	 
	public boolean cmd_find(String cmd,BaseActionA [] actions) 
	{ 
		 
		String cm = cmd.substring(gl.E_OK); 
						 
		for(Entry<String,BaseActionA> e : ApplicationA.rio.actions_map.entrySet()) 
		{ 
			if (e.getValue().getCmds().contains(cm)) 
				actions[gl.E_EMPTY] = e.getValue(); 
		} 
		 
				return actions[gl.E_EMPTY] != null; 
		 
	} 
	 
	 
	public boolean cmd_mangling() 
	{ 
		// Extract root command. 
		 
		try 
		{ 
			 
			this.setParameter_list(Arrays.asList(this.getCmd().split(" "))); 
		 
			this.cmd = this.getParameter_list().get(gl.ZERO); 
		 
			return true; 
			 
		} 
		catch(Exception e) 
		{ 
		 
			return  false; 
		} 
		 
		 
	} 
		 
	 
	 
	public int cmd_accept(String cm) 
	{ 
			// Set normalized value. 
			this.setCmd(CmdParams.normalize(this.getCmd())); 
			 
			// Check syntax. 
			if(!cmd_check_syntax(cm)) 
			{ 
				return gl.E_SYNTAX_ERROR; 
			} 
			 
			// Mangling. 
			 
			if(!cmd_mangling()) 
			{ 
				return gl.E_PARAMS_MANGLING; 
			} 
			 
			BaseActionA []  actions = {null}; 
			 
			if(!cmd_find(this.getCmd(),actions)) 
			{ 
				return gl.E_FIND_ERROR; 
			} 
				// Enter emulation. 
			 
				gl.smn(""); 
			 
			if (!actions[gl.E_EMPTY].actionPerformed(this.getOwner())) 
			{ 
				return gl.E_RUNTIME_ERROR; 
			} 
			 
				return gl.E_OK; 
	} 
	 
	 
	 
	public int cmd_buffer_add_() 
	{ 
	 
		this.getOwner().getCmio().getCmds().add(this); 
	 
		int size = this.getOwner().getCmio().getCmds().size(); 
				 
		this.getOwner().getCmio().setCmd_scroll(size); 
		 
		return size; 
		 
	} 
	 
	public int cmd_buffer_add() 
	{ 
	 
		this.getOwner().getCmds().add(this); 
	 
		int size = this.getOwner().getCmds().size(); 
				 
		this.getOwner().setCmd_scroll(size); 
		 
		return size; 
		 
	} 
	 
	public void cmd_buffer_clear_() 
	{ 
		this.getOwner().getCmio().getCmd().setLength(gl.E_EMPTY); 
	} 
	 
	public void cmd_buffer_clear() 
	{ 
		this.getOwner().getCmd().setLength(gl.E_EMPTY); 
	} 
	 
		 
	public int accept() 
	{ 
		 
		this.setState(cmd_accept(this.getCmd())); 
		 
		this.cmd_buffer_add(); 
		 
		this.cmd_buffer_clear(); 
		 
		return this.getState(); 
		 
	} 
	 
	public String get_state_message() 
	{ 
		if(this.getCmd().trim().length() == gl.E_EMPTY) 
		return System.lineSeparator(); 
		else 
		return gl.sf("[%s]...%s",this.getCmd(),gl.get_error_type(this.getState())); 
	} 
	 
	 
	public static CmdA get_instance(AtOm owner,String cmd) { 
		 
		return new CmdA(owner,cmd); 
	} 
	 

	@Override 
	public String toString() 
	{ 
		return gl.sf("%3d %s %s %02d",this.getId(),this.getCmd(),this.getParameters(),this.getState()); 
	} 
	 
	public String toParametersString() 
	{ 
		StringBuilder sb = new StringBuilder(); 
	 
		int [] index = {0}; 
		 
		this.getParameter_list().forEach(a->{ 
			 
			sb.append(a); 
			 
			if(index[0] < this.getParameter_list().size()-1) 
			   sb.append(System.lineSeparator()); 
			 
			   index[0]++; 
			 
		}); 
		 
			return sb.toString().trim(); 
	} 
	 
	public static String show(List<CmdA> items) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(System.lineSeparator()); 
		 
		items.forEach(a-> 
		{ 
			String msg = gl.sf("%s",a.toString()); 
			 
			sb.append(msg); 
			 
			sb.append(System.lineSeparator()); 
		}); 
		 
			return sb.toString(); 
	} 
	 
	public  boolean extract_bad_params(List<String> param_list,List<String> not_valid_params_list) 
	{ 
		 
		this.getParameter_list().forEach(s-> 
		{ 
			if(!param_list.contains(s) && (s.startsWith("/") || s.startsWith("-") || s.startsWith("$") )) 
			{ 
				if(!not_valid_params_list.contains(s)) 
					not_valid_params_list.add(s); 
			} 
			 
		}); 
		 
				return (not_valid_params_list.size() != gl.E_EMPTY); 
		 
	} 
	 
	 
	public boolean checkParams(List<String> valid_params,String [] invalid_params) 
	{ 
		List<String> list = new ArrayList<String>(); 
		 
		if (extract_bad_params(valid_params,list)) 
		   { 
			 StringBuilder sb = new StringBuilder(); 
			 
			 list.forEach(a->{ 
				 
				 sb.append(a); 
				 
				 sb.append(" "); 
				 
			 }); 
			 
			 	invalid_params[gl.E_EMPTY] = sb.toString(); 
			 	 
			 	return false; 
		   } 
		 
				return true; 
	} 
	 
	public List<String>  get_all_valid_switch() 
	{ 
		List<String> r = new ArrayList<String>(); 
		 
		r.addAll(SELECTION_SCOPE); 
		 
		r.addAll(ALL_SCOPE); 
		 
		r.addAll(OP_ON); 
		 
		r.addAll(OP_OFF); 
		 
		r.addAll(OP_REVERSE); 

		r.addAll(OP_BACK); 
		 
		r.addAll(OP_FRONT); 
		 
		r.addAll(OP_TEXT); 

		r.addAll(OP_FORM); 

		r.addAll(OP_SETUP); 

		return r; 
				 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 

