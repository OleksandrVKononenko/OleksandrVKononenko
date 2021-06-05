package ap.orion.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ap.orion.action.BaseAction;
import ap.global.gl;
import ap.orion.app.Application;
import ap.utils.MapUtils;
import ap.utils.Su;

public class CmdProcess {

	public static List<String> foreground_switches = Arrays.asList(new String[] 
			{"-frg","--frg","/frg","-foreground","--foreground","/foreground"});
	
	public static List<String> bind_switches = Arrays.asList(new String[] 
			{"-bnd","--bnd","/bnd","-bind","--bind","/bind"});
	
	public static List<String> quote_switches = Arrays.asList(new String[] 
			{"-q","--q","/q","-quote","--quote","/quote"});
	
	public static List<String> dlg_switches = Arrays.asList(new String[] 
			{"-frm","--frm","/frm","-dlg","--dlg","/dlg"});
	
	public static List<String> set_switches = Arrays.asList(new String[] 
			{"-.","--.","/.","-set","--set","/set"});
	
	public static List<String> clear_switches = Arrays.asList(new String[] 
			{"-c","--c","/c","-clr","--clr","/clr"});
	
	public static List<String> all_switches = Arrays.asList(new String[] 
			{"-a","--a","/a","-all","--all","/all"});
	
	public static List<String> selection_switches = Arrays.asList(new String[] 
			{"-sle","--sle","/sle","-sel","--sel","/sel"});
	
	public static List<String> fit_switches = Arrays.asList(new String[] 
			{"-f","--f","/f","-fit","--fit","/fit"});
	
	public static List<String> debug_switches = Arrays.asList(new String[] 
			{"-d","--d","/d","-dbg","--dbg","/dbg"});
	
	public static List<String> in_switches = Arrays.asList(new String[] 
			{"-i","--i","/i","-in","--in","/in"});
	
	public static List<String> out_switches = Arrays.asList(new String[] 
			{"-o","--o","/o","-out","--out","/out"});
	
	public static List<String> bkg_switches = Arrays.asList(new String[] 
			{"-b","--b","/b","-bkg","--bkg","/bkg"});
	
	public static List<String> reset_switches = Arrays.asList(new String[] 
			{"-r","--r","/r","-res","--res","/res"});
	
	public static List<String> txt_switches = Arrays.asList(new String[] 
			{"-t","--t","/t","-txt","--txt","/txt"});
	
	public static List<String> help_switches = Arrays.asList(new String[] 
			{"-?","--?","/?","-help","--help","/help"});
	
	public static List<String> test_switches = Arrays.asList(new String[] 
			{"-try","--try","/try","-test","--test","/test"});
	
	public static List<String> font_switches = Arrays.asList(new String[] 
			{"-fnt","--fnt","/fnt","-font","--font","/font"});
	
	public static List<String> align_switches = Arrays.asList(new String[] 
			{"-alg","--alg","/alg","-align","--align","/align"});
	
	public static List<String> action_switches = Arrays.asList(new String[] 
			{"-act","--act","/act","-action","--action","/action"});
	
	public static List<String> grid_switches = Arrays.asList(new String[] 
			{"-g","--g","/g","-grid","--grid","/grid"});
	
	public static List<String> dac_switches = Arrays.asList(new String[] 
			{"-dac","--dac","/dac","-direct","--direct","/direct"});
	
	public static List<String> verbose_switches = Arrays.asList(new String[] 
			{"-v","--v","/v","-verbose","--verbose","/verbose"});
	
	public static List<String> list_switches = Arrays.asList(new String[] 
			{"-l","--l","/l","-list","--list","/list"});
	
	public static List<String> border_switches = Arrays.asList(new String[] 
			{"-brd","--brd","/brd","-border","--border","/border"});
	
	public static List<String> for_switches = Arrays.asList(new String[] 
			{"-for","--for","/for","-loop","--loop","/loop"});
	
	public static List<String> delim_switches = Arrays.asList(new String[] 
			{",",":","-","#","@","$","%","^","&"});
	
	public static List<String> fixed_switches = Arrays.asList(new String[] 
			{"-fd","--fd","/fd","-fix","--fix","/fix"});
	
	public static List<String> compile_switches = Arrays.asList(new String[] 
			{"-com","--com","/com","-compile","--compile","/compile"});
	
	public static List<String> on_switches = Arrays.asList(new String[] 
			{"true","on","yes","1","+","да","ага"});
	
	public static List<String> max_switches = Arrays.asList(new String[] 
			{"-mx","--mx","/mx","-max","--max","/max"});
	
	public static List<String> min_switches = Arrays.asList(new String[] 
			{"-mn","--mn","/mn","-min","--min","/min"});

	public static List<String> init_switches = Arrays.asList(new String[] 
			{"-ini","--ini","/ini","-setup","--setup","/setup"});
	
	public static List<String> slider_switches = Arrays.asList(new String[] 
			{"-slider","--slider","/slider","-sldr","--sldr","/sldr"});
	
	public static List<String> header_switches = Arrays.asList(new String[] 
			{"-h","--h","/h","-hdr","--hdr","/hdr"});
	
	public static List<String> startup_switches = Arrays.asList(new String[] 
			{"-start","--start","/start","-boot","--boot","/boot","-logo","--logo","/logo"});
	
	
	public static List<String> types = Arrays.asList(new String[] 
			{"NONE","HEADER","PARAMETER","SWITCH"});
	
	
	public static List<String> errors = Arrays.asList(new String[] 
			{"NONE","FLOW_EXCEPTION","NO_NAME","BAD_HEADER","BAD_OR_NULL_INPUT_PARAM","OK","PARSER_ERROR"});
	
	
	public static String indexOf(List<String> list,int index)
	{
		return list.get(index);
	}


	public static int indexOf(List<String> list,String index)
	{
		return list.indexOf(index);
	}
	
	
	private String 						Name;
	
	private Map<String,Integer> 		args;
	
	private Map<String,List<String>> 	tokens; 
	
	private Map<String,List<String>> 	headers; 

	private Map<Integer,String> 		headers_index; 

	private String 						text;
	
	private int 						state;
	
	


	public Map<Integer, String> getHeaders_index() {
		return headers_index;
	}


	public void setHeaders_index(Map<Integer, String> headers_index) {
		this.headers_index = headers_index;
	}


	public Map<String, List<String>> getHeaders() {
		return headers;
	}


	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}


	public Map<String, List<String>> getTokens() {
		return tokens;
	}


	public void setTokens(Map<String,List<String>> tokens) {
		this.tokens = tokens;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Map<String, Integer> getArgs() {
		return args;
	}

	public void setArgs(Map<String, Integer> args) {
		this.args = args;
	}

	public CmdProcess() {
	
		this.setArgs( new LinkedHashMap<String,Integer>());
		
		this.setTokens(new LinkedHashMap<String,List<String> >());
		
		this.setHeaders(new LinkedHashMap<String,List<String> >());
		
		this.setHeaders_index(new LinkedHashMap<Integer,String>());
	}

	public CmdProcess(String text) {

		this();
		
		this.setText(text);
		
		
	}
	
	public CmdProcess(String text,boolean other) {

		this();
		
		this.setText(text);
		
		if(other)
		{
			
			if(!this.parse(this.getText()))
			{
				this.setState(errors.indexOf("PARSER_ERROR"));
			}
			else
			{
				this.setState(errors.indexOf("OK"));
			}
			
		}
		
		
		
	}
	
	public boolean parse(String text)
	{
		
			if(text == null)
			{
				
				this.setState(errors.indexOf("BAD_OR_NULL_INPUT_PARAM"));
				
				return false;
	
			}
			
				this.setText(text);
				
				return this.parse();
			
	}
	
	public boolean parse()
	{
				
			//gl.sfn("---> Input...[%s]",this.getText());
		
		try
		{
			String [] ar = this.getText().split(" ");
		
			List<Boolean> results = new ArrayList<Boolean>();
		
		for(int i = 0;i< ar.length;i++)
		{
			/*
			gl.sfn("Value...[%s]...Header...%s...Param...%s...Switch...%s",
					ar[i],
					isa_header(ar[i]),
					isa_param(ar[i]),
					isa_switch(ar[i])
					);
			*/
			
			if(ar[i].trim().length() != gl.E_EMPTY)
			{
					int type = who_is(ar[i]);
					
					// if name conflict has been occur.
					if(this.getArgs().containsKey(ar[i]))
					{
					
							int counter = 0;
						
						while(this.getArgs().containsKey(gl.sf("%s(%d)",ar[i],counter)))
						{
							counter++;
						}
							this.getArgs().put(gl.sf("%s(%d)",ar[i],counter),type);
						
						
					}
					else
							this.getArgs().put(ar[i],type);
						
			
							results.add(type != indexOf(types,"NONE"));
			}
		} // length() != 0
			
		// Load tokens.
		
		this.getArgs().forEach((k,v)->{
			
			if(v == indexOf(types,"SWITCH") || v == indexOf(types,"HEADER"))
			{
				//gl.sfn("Token...%s...childs...%d",k,this.get_childs(k).size());
				
				this.getTokens().put(k,this.get_childs(k));
			}
			
		});
		
		// Load headers.
		
		String [] m_cur_header = {null};

		this.getArgs().forEach((k,v)->{
			
			if(v == indexOf(types,"HEADER"))
			{
				this.getHeaders().put(k,new ArrayList<String>());
					
				m_cur_header[0] = k;
				
			}
			else
			{
				this.getHeaders().get(m_cur_header[0]).add(k);
			}
				
			
		});
		
		// Load headers index.
		
		int [] index = {gl.E_EMPTY};
		
		this.getHeaders().forEach((k,v)->{
			
			this.getHeaders_index().put(index[0],k);
			
			index[0]++;
			
		});
		
		/*
		this.getHeaders_index().forEach((k,v)->{
			
			gl.sfn("Headers info...index...%d...%s",k,v);
		});
		*/
		
		//Load name.
		
		String m_name = MapUtils.findKeyByValue(this.getArgs(),indexOf(types,"HEADER"));
		
		if(m_name == null)
		{
			
			this.setState(indexOf(errors,"NO_NAME"));
			
			return false;
		}
		
			this.setName(Su.AfterAtPeriod(m_name));
		
			//gl.sfn("--> Name...%s",this.getName());
		
			return !results.contains(false) ;
			
	}
		catch(Exception e)
		{
			this.setState(indexOf(errors,"FLOW_EXCEPTION"));
			
			return false;
		}
		
	}
	
	public int who_is(String value)
	{
		int result = indexOf(types,"NONE"); 
				
		if(isa_header(value))	
			result = indexOf(types,"HEADER");
		else if(isa_param(value))	
			result = indexOf(types,"PARAMETER");
		else if(isa_switch(value))	
			result = indexOf(types,"SWITCH");
		
			return result;
			
	}
	
	public List<String> remove_delims(List<String> list)
	{
		
		List<String> out = new ArrayList<String>();
		
		for(int i=0;i<list.size();i++)
		{
			if(Su.isAlpha(list.get(i)) || Su.isNumber(list.get(i)))
			   out.add(list.get(i));
		}
		
			return out;
	}
	
	
	private boolean isa_header(String value)
	{
		return value.startsWith(".");
	}
	
	private boolean isa_switch(String value)
	{
		return value.startsWith("/") || value.startsWith("-") || value.startsWith("--") ;
	}
	
	private boolean isa_param(String value)
	{
		return !(isa_header(value) || isa_switch(value));
	}
	
	
	public static CmdProcess get_instance()
	{
		return new CmdProcess();
	}
	
	public static CmdProcess get_instance(String text)
	{
		return new CmdProcess(text);
	}
	
	public static CmdProcess get_instance(String text,boolean other)
	{
		return new CmdProcess(text,other);
	}
	
	public List<String> get_params()
	{
		return this.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
	}
	
	public List<String> get_switches()
	{
		return this.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"SWITCH"));
	}
	
	
	public List<String> get_childs(String value)
	{
		List<String> m_items = new ArrayList<String>();
		
		String [] m_k = {""};
		
		this.getArgs().forEach((k,v)->{
		
		// Make a target. 
			
		if(v != indexOf(types,"SWITCH") && v != indexOf(types,"HEADER") && m_k[0].equalsIgnoreCase(value))
		{
			//gl.sfn("   ---> %s...%s...%s...Child of...%s",m_k[0],k,indexOf(types,v),m_k[0]);
		
			m_items.add(k);
		}
		
		// Make a filter.
			
		if(v == indexOf(types,"SWITCH") || v == indexOf(types,"HEADER"))
			m_k[0] = k;
				
		});
		
			return m_items;
	}
	
	public static BaseAction lookup_action_by_activator(String activator) 
	{ 					 
		for(Entry<String,BaseAction> e : Application.rio.actions_map.entrySet()) 
		{ 
			if (e.getValue().getActivators().contains(activator)) 
				return e.getValue(); 
		} 
		 
				return null; 
		 
	} 
	
	public int get_count_of(int type)
	{
		
		return this.getArgs().values().stream().filter(b->(
				b == type
				)
				).collect(Collectors.toList()).size();
	
	}
	
	public List<String> get_list_of_type(int type)
	{
		List<String>  m_result_list = new ArrayList<String>();
		
		this.getArgs().forEach((k,v)->{
			
			if(v==type)
			{
				m_result_list.add(k);
			}
		});
	
				return m_result_list;
	}
	
	public  String get_of(int type)
	{
		
		return MapUtils.findKeyByValue(this.getArgs(),type);
		
	}
	

	public boolean isaKey(String key)
	{
		//return this.getArgs().containsKey(key);
		
		//gl.sfn("----> Cmd name...%s",this.getName());
		
		//this.getHeaders().forEach((k,v)->{
			
		//	gl.sfn("Header...[%s]...value size...%d",k,v.size());
			
		//});
		
		return this.getHeaders().get(gl.sf(".%s",this.getName())).contains(key);
	}
	
	
	
	public int get_count_of_headers()
	{
		return this.get_count_of(indexOf(types,"HEADER"));
	}
	
	public int get_count_of_switches()
	{
		return this.get_count_of(indexOf(types,"SWITCH"));
	}
	
	public int get_count_of_params()
	{
		return this.get_count_of(indexOf(types,"PARAMETER"));
	}
	
	public String  get_param_by_index(int index)
	{
		try
		{
			List<String> 	m_params = this.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));
	
			String 			m_param = m_params.get(index);
			
			return 			m_param;
		}
		catch(Exception e)
		{
			return null;
		}

	}
	
	public boolean isa_set_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		set_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_bkg_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		bkg_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_text_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		txt_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_verbose_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		verbose_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_action_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		action_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_startup_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		startup_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_foreground_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		foreground_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_delim_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		delim_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_font_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		font_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_align_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		align_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_dlg_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		dlg_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_reset_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		reset_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_in_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		in_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_for_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		for_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}

	public boolean isa_out_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		out_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_header_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		header_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_test_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		test_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_dac_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		dac_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_debug_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		debug_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	public boolean isa_list_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		list_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_help_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		help_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_clear_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		clear_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_quote_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		quote_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_fixed_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		fixed_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_border_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		border_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_max_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		max_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_min_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		min_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_ini_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		init_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_slider_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		slider_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_fit_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		fit_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_grid_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		grid_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_bind_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		bind_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	
	public boolean isa_selection_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		selection_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	
	public boolean isa_all_option()
	{
	
		List<Boolean> bl_result = new ArrayList<Boolean>();
		
		all_switches.forEach(s->{
			
			if(this.isaKey(s))
			{
				bl_result.add(true);
			}
		});
				
			return (bl_result.size() != gl.E_EMPTY);
	}
	

	public boolean isa_no_switches()
	{
		return (this.get_count_of_switches() == gl.E_EMPTY);
	}
	
	public boolean isa_no_params()
	{
		return (this.get_count_of_params() == gl.E_EMPTY);
	}
	
	public static void test() {
		
		//String cmd = ".lf 10 15 param1 param2 param3 /s -1 -2 -3  ";// ".br appload /order 1,2,3 1 #13 /f grid 17 /k up +1 25
																// /d /f {::17} /f {::18} /f {::19} /f ::";

		String cmd = ".prop /. /g 10,10,1,2 .~. .~.";
		
		CmdProcess process = CmdProcess.get_instance(cmd);

		if (process.parse()) {

			process.getArgs().forEach((k, v) -> {

				gl.sfn("Value...%12s...is...%s", k, indexOf(types, v));

			});

			// Check get child.

			/*
			 * process.get_childs(".br(0)").forEach(a-> {
			 * gl.sfn("Childs...%s...of...%s",a,".br(0)");
			 * 
			 * });
			 */

			// gl.sfn("Count of
			// tokens...%d...%d",process.getTokens().size(),process.getTokens().get(gl.sf(".%s",process.getName())).size());

			gl.sfn("Count of tokens...%d...%d", process.getTokens().size(),
					process.getTokens().get(gl.sf("/s", "")).size());

			/*
			 * gl.sfn("Headers...%d...Switches...%d...Params...%d",
			 * process.get_count_of(indexOf(types,"HEADER")),
			 * process.get_count_of(indexOf(types,"SWITCH")),
			 * process.get_count_of(indexOf(types,"PARAMETER")) );
			 */

			gl.sfn("Headers...%d...Switches...%d...Params...%d", process.get_count_of_headers(),
					process.get_count_of_switches(), process.get_count_of_params());

			StringBuilder sb = new StringBuilder();

			process.getTokens().forEach((k, v) -> {

				sb.append(gl.sf("%s [", k));

				v.forEach(l -> {
					sb.append(gl.sf("%s ", l));
				});

				sb.append(gl.sf("]%s", System.lineSeparator()));

			});

			gl.sfn("%s", sb.toString());

		} else {
			gl.sfn("Error...%s", indexOf(errors, process.getState()));
		}

		// Checks of list.
		
		types.forEach(t->{
			
			process.get_list_of_type(indexOf(types,t)).forEach(a->
			{
				gl.sfn("%sS...%s",t.toUpperCase(),a);
				
			});
			
		});		
		
		
		
	}

	public static void test_delitor() {
		
		
		String cmd = ".prop /. /g 10,10,1,2 255.~ ~.255";
		
		CmdProcess process = CmdProcess.get_instance(cmd);

		if (process.parse()) {

			process.getArgs().forEach((k, v) -> {

				gl.sfn("Value...%12s...is...%s", k, indexOf(types, v));

			});

			
			gl.sfn("\nHeaders...%d...Switches...%d...Params...%d\n", process.get_count_of_headers(),
					process.get_count_of_switches(), process.get_count_of_params());

			StringBuilder sb = new StringBuilder();

			process.getTokens().forEach((k, v) -> {

				sb.append(gl.sf("%s [", k));

				v.forEach(l -> {
					sb.append(gl.sf("%s ", l));
				});

				sb.append(gl.sf("]%s", System.lineSeparator()));

			});

				gl.sfn("%s", sb.toString());

		} else {
				gl.sfn("Error...%s", indexOf(errors, process.getState()));
		}

		// Checks of list.
		
		types.forEach(t->{
			
			process.get_list_of_type(indexOf(types,t)).forEach(a->
			{
				gl.sfn("%sS...%s",t.toUpperCase(),a);
				
			});
			
		});		
		
		
		
	}

	public static void test_font() {
		
		String cmd = ".prop 21,22,23 /s 16,17,18 /font Arial Black,1,12 какой то левый текст 503&510|125 .set /s Panel";
		
		// ".br appload /order 1,2,3 1 #13 /f grid 17 /k up +1 25
																// /d /f {::17} /f {::18} /f {::19} /f ::";

		CmdProcess process = CmdProcess.get_instance(cmd);

		if (process.parse()) {

			process.getArgs().forEach((k, v) -> {

				gl.sfn("Value...%12s...is...%s", k, indexOf(types, v));

			});

			// Check get child.

			/*
			 * process.get_childs(".br(0)").forEach(a-> {
			 * gl.sfn("Childs...%s...of...%s",a,".br(0)");
			 * 
			 * });
			 */

			// gl.sfn("Count of
			// tokens...%d...%d",process.getTokens().size(),process.getTokens().get(gl.sf(".%s",process.getName())).size());

			gl.sfn("Count of tokens...%d...%d", process.getTokens().size(),
					process.getTokens().get(gl.sf("/s", "")).size());

			/*
			 * gl.sfn("Headers...%d...Switches...%d...Params...%d",
			 * process.get_count_of(indexOf(types,"HEADER")),
			 * process.get_count_of(indexOf(types,"SWITCH")),
			 * process.get_count_of(indexOf(types,"PARAMETER")) );
			 */

			gl.sfn("Headers...%d...Switches...%d...Params...%d", process.get_count_of_headers(),
					process.get_count_of_switches(), process.get_count_of_params());

			StringBuilder sb = new StringBuilder();

			process.getTokens().forEach((k, v) -> {

				sb.append(gl.sf("%s [", k));

				v.forEach(l -> {
					sb.append(gl.sf("%s ", l));
				});

				sb.append(gl.sf("]%s", System.lineSeparator()));

			});

			gl.sfn("%s", sb.toString());

		} else {
			gl.sfn("Error...%s", indexOf(errors, process.getState()));
		}

		// Checks of list.
		
		types.forEach(t->{
			
			process.get_list_of_type(indexOf(types,t)).forEach(a->
			{
				gl.sfn("%sS...%s",t.toUpperCase(),a);
				
			});
			
		});		
		
	}
	
	
public static void test_for_() {
		
		//String cmd = ".read 1 /for 21 22 23 24 /loop 31 32 33 34 /for 1 2 3";
		
		String cmd = ".prop /. /g 10,10,1,2 .~. .~.";
		
		CmdProcess p = CmdProcess.get_instance(cmd);

		if(! gl.tx(new Object(){}, p.parse(),"Создание регистра управления."))
		{
			return ;
		}
		
		// Commom list.
		
		p.getTokens().forEach((k,v)->{
			
			gl.sfn("%s %s",k,Su.get_str_from_list(p.getTokens().get(k)));
			
		});
		
		// Extract items for one token.
		
		List<String> childs = new ArrayList<String>();
		
		p.getTokens().forEach((k,v)->{
			
			gl.sfn("Test %s %s",k,Su.get_str_from_list(Su.remove_value_in_bounds(p.getTokens().get(k),new String[]{"(",")"})));
			
			if(CmdProcess.for_switches.contains(Su.remove_value_in_bounds(k,new String[]{"(",")"})))
			{
				childs.add(gl.sf("%s",
						//Su.remove_value_in_bounds(k,new String[]{"(",")"}),
						//Su.get_str_from_list(v))
						Su.get_str_from_list(Su.remove_value_in_bounds(v,new String[]{"(",")"})
						)));
			}
		});
		
			gl.sfn("%s",Su.get_str_from_list(childs));
	}



public static void test_for() {
	
	String cmd = ".read 1 /for 21 22 23 24 /loop 31 32 33 34 /for 1 2 3 /loop 10 20 30 /for one two free";
	
	CmdProcess p = CmdProcess.get_instance(cmd);

	if(! gl.tx(new Object(){}, p.parse(),"Создание регистра управления."))
	{
		return ;
	}
	
	// Extract items for one token.
	
		gl.sfn("%s",Su.get_str_from_list(get_childs_of_token(p,for_switches,new String []{"(",")"})));
}



public static List<String> get_childs_of_token(CmdProcess p,List<String> list,String [] bounds)
{
	List<String> childs = new ArrayList<String>();
	
	p.getTokens().forEach((k,v)->{
		
		if(CmdProcess.for_switches.contains(Su.remove_value_in_bounds(k,bounds)))
		{
			childs.add(gl.sf("%s",
					Su.get_str_from_list(Su.remove_value_in_bounds(v,bounds)
					)));
		}
		
	});
	
		return childs;
}

public static void test_action() {
		
		// String cmd = ".prop /s /act .write";
		
		String cmd = ".z /. /i 100 , , , ";
	
		CmdProcess process = CmdProcess.get_instance(cmd);

		if (process.parse()) {

			process.getArgs().forEach((k, v) -> {

				gl.sfn("Value...%12s...is...%s", k, indexOf(types, v));

			});

			
			StringBuilder sb = new StringBuilder();

			process.getHeaders().forEach((k, v) -> {

				sb.append(gl.sf("%s [", k));

				v.forEach(l -> {
					sb.append(gl.sf("%s ", l));
				});

				sb.append(gl.sf("]%s", System.lineSeparator()));

			});

			    gl.sfn("%s", sb.toString());

		} else {
			
				gl.sfn("Error...%s", indexOf(errors, process.getState()));
		}

		// Checks of list.
		
		types.forEach(t->{
			
			process.get_list_of_type(indexOf(types,t)).forEach(a->
			{
				gl.sfn("%sS...%s",t.toUpperCase(),a);
				
			});
			
		});		
		
		// Emulation.
		
		String m_cmd = gl.sf("%s %s",
				
				process.getHeaders_index().get(gl.E_EMPTY),
				Su.get_str_from_list(process.getHeaders().get(process.getHeaders_index().get(gl.E_EMPTY)))
				);
		
		gl.sfn("Команда...[%s]",m_cmd);
		
		
		
	}
	public static void main(String[] args) {
		
		// test_action();
		
		test_delitor();
	}

}
