 
 
 
 
 
 
 
package ap.action; 


import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Map.Entry; 

import ap.gen.CmdId; 
import ap.global.gl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 



public class Cmd { 

	 
	private int id; 
	 
	private String cmd; 
	 
	private int  state; 
	 
	private String description; 
	 
	private String action; 
	 
	private PanelXml owner; 
	 
	private String parameters;	 
	 
	private boolean scope_all; 
	 
	private boolean scope_selected; 
	 
	private List<String> parameter_list = new ArrayList<String>(); 
	 
	 
	public boolean isScope_selected() { 

		this.setScope_selected(CmdParams.isaParams(this,new String[] {"/selected","/sel","/s"})); 
		 
		return scope_selected; 
	} 

	public void setScope_selected(boolean scope_selected) { 
		this.scope_selected = scope_selected; 
	} 

	public boolean isScope_all() { 
		 
		this.setScope_all(CmdParams.isaParams(this,new String[] {"/all","/a","/global","/gl"})); 
		 
		return scope_all; 
		 
	} 

	public void setScope_all(boolean scope_all) { 
		this.scope_all = scope_all; 
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

	public PanelXml getOwner() { 
		return owner; 
	} 

	public void setOwner(PanelXml owner) { 
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

	public Cmd() { 
		 
		this.setId(CmdId.nextId()); 
	} 

	public Cmd(String cmd) { 

		this(); 
		 
		this.setCmd(cmd); 
		 
		this.setState(gl.E_ERROR); 
		 
	} 
	 
	public Cmd(PanelXml owner,String cmd) { 

		this(cmd); 
		 
		this.setOwner(owner); 
		 
		owner.setFire_cmd(this); 
		 
		this.accept(); 
		 
		// Request focus after each cmd. 
		owner.getDio().getDeskTop().requestFocus(); 
	} 
	 
	 
	public Cmd(String cmd,int  status) { 
		 
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
	 
	public boolean cmd_find(String cmd,BaseAction [] actions) 
	{ 
		 
		String cm = cmd.substring(gl.E_OK); 
						 
		for(Entry<String,BaseAction> e : Application.rio.actions_map.entrySet()) 
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
			 
			BaseAction []  actions = {null}; 
			 
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
	 
	 
	public int cmd_buffer_add() 
	{ 
	 
		this.getOwner().getCmds().add(this); 
	 
		int size = this.getOwner().getCmds().size(); 
				 
		this.getOwner().setCmd_scroll(size); 
		 
		return size; 
		 
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
	 
	 
	public static Cmd get_instance(PanelXml owner,String cmd) { 
		 
		return new Cmd(owner,cmd); 
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
	 
	public static String show(List<Cmd> items) 
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
	 
	public static void main(String[] args) { 
		 

	} 

} 
