package ap.orion.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
import javax.swing.AbstractAction; 
import javax.swing.Action; 
import javax.swing.ImageIcon; 
import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.lsnr.BrewActionListener;
import ap.utils.Fu; 


public class BaseAction extends AbstractAction { 

	
	private static final long serialVersionUID = 1L; 
	 
	private List<String> 	activators 	= null; 
	 
	private List<String> 	switches 	= null; 
	
	private ap.orion.cmd.CmdProcess 		process;
	
	private List<String>				response ;
	
	
	public List<String> getResponse() {
		return response;
	}

	public void setResponse(List<String> resp) {
		
		this.response = resp;
		
	}

	
	public void setResponseHelper(String [] message)
	{
		this.setResponse(Arrays.asList(message ));
	}
	
	public boolean setResponseHelper(String [] message,boolean result)
	{
		this.setResponse(Arrays.asList(message ));
		
		return result;
	}
	
	protected List<String> get_usage_info()
	{
		return new ArrayList<String>();
	}
	
	
	public CmdProcess getProcess() {
		return process;
	}

	public void setProcess(ap.orion.cmd.CmdProcess process) {
		this.process = process;
	}

	public List<String> getActivators() { 
		return activators; 
	} 

	public void setActivators(List<String> activators) { 
		this.activators = activators; 
	} 

	public List<String> getSwitches() { 
		return switches; 
	} 

	public void setSwitches(List<String> switches) { 
		this.switches = switches; 
	} 

	 
	public BaseAction() { 
		
		

	} 
	 
	public static BaseAction get_instance()
	{
		return new BaseAction();
	}
	
	public BaseAction(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		
		super(text, icon); 

		putValue(SHORT_DESCRIPTION, desc); 

		putValue(MNEMONIC_KEY, mnemonic); 

	} 

	public BaseAction(String text) { 
	
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
	} 
	 
	public BaseAction(String text,List<String> activators,List<String> switches) { 
		 
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
		 
		this.setActivators(activators);
		
		this.setSwitches(switches);
		
	} 

	public boolean check_selected()
	{
		
		
			int m_selected_size = CollectionImpl.update_selected();
	
			String m_msg = gl.sf("Выбранных объектов...[%d]",m_selected_size);
		
			boolean bl_result = (m_selected_size != gl.E_EMPTY);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_one_selected()
	{
		
			int m_selected_size = CollectionImpl.update_selected();
	
			String m_msg = gl.sf("Выбранных объектов д.б. [1]...в наличии...[%d]",m_selected_size);
		
			boolean bl_result = (m_selected_size == gl.E_OK);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_two_selected()
	{
		
			int m_selected_size = CollectionImpl.update_selected();
	
			String m_msg = gl.sf("Выбранных объектов д.б. [2]...в наличии...[%d]",m_selected_size);
		
			boolean bl_result = (m_selected_size == gl.E_OK*2);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	
	public boolean check_data_header(Orion target)
	{
		
		    boolean bl_result = !(  target == null 									||
		    		                target.getData() == null					 	|| 
		    		                target.getData().getHeaders() == null 			|| 
		    		                target.getData().getHeaders().length() == gl.E_EMPTY
		    		             );
		    
		    int m_header_size = !bl_result ? 0 : target.getData().getHeaders().length();
	
			String m_msg = 
					gl.sf("Проверка наличия заголовка данных...[%d][%s]",
					m_header_size,
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_object(Orion target)
	{
		
		    boolean bl_result = (  target != null );
		    
			String m_msg = 
					gl.sf("Проверка наличия объекта...[%s][%s][%s]",
					target == null ? "null" : target.get_class(),
					target == null ? "null" : target.getName(),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
	}
	
	
	
	
	public boolean check_object_class(Orion target,String clazz)
	{
		
		    boolean bl_result = ( target.get_class().equalsIgnoreCase(clazz) );
		    
			String m_msg = 
					gl.sf("Проверка класса объекта...[%s][%s][%s]",
					target == null ? "null" : target.get_class(),
					target == null ? "null" : target.getName(),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_object(String desc,Orion target)
	{
		
		    boolean bl_result = (  target != null );
		    
			String m_msg = 
					gl.sf("Проверка наличия объекта %s...[%s][%s][%s]",
					desc,
					target == null ? "null" : target.get_class(),
					target == null ? "null" : target.getName(),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
	}
	
	public boolean check_object(String desc,Object target)
	{
		
		    boolean bl_result = (  target != null );
		    
			String m_msg = 
					gl.sf("%s...[%s][%s]",
					desc,
					target == null ? "null" : target.getClass().getSimpleName(),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
	}

	public boolean check_update_lsnr(String desc,BrewActionListener target,String context)
	{
		
		    boolean bl_result = (target != null) && (target.update_context(context).contains(context));
		    
			String m_msg = 
					gl.sf("%s...[%s][%s]...context...[%s]",
					desc,
					target == null ? "null" : target.getClass().getSimpleName(),
					bl_result,
					target.getContext()
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
	}
	
	
	public boolean check_flow(String desc,boolean mode)
	{
		
		    boolean bl_result = (mode);
		    
			String m_msg = 
					gl.sf("%s",
					desc
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	
	public boolean check_action_lsnrs(ActionListener [] target)
	{
		
		    boolean bl_result = (  target != null  && target.length != gl.E_EMPTY);
		    
			String m_msg = 
					gl.sf("Проверка лисенеров...[%s][%s]",
					target == null ? "null" : gl.sf("%d",target.length),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	
	public boolean check_objects(List<Orion> target)
	{
		
		    boolean bl_result = (  target != null  && target.size() > gl.E_EMPTY);
		    
			String m_msg = 
					gl.sf("Проверка наличия объектов...[%d][%s]",
					target == null ? "null" : target.size(),
					bl_result
					);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	
	public boolean check_level(String level)
	{
		    
			String m_msg = 
					gl.sf("Ошибка...[%s]",level==null ? "null" : level
					);
				
			this.setResponseHelper(new String[] {m_msg});
	
			return gl.tx(new Object(){},false,m_msg);		
		
	}
	
	public boolean check_file(String source)
	{
		
			String m_msg = gl.sf("Проверка наличия файла...[%s]",source);
		
			boolean bl_result = Fu.isaFile(source);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_str(String source)
	{
		
			String m_msg = gl.sf("Проверка данных ...[%s...]",source.substring(0, 12));
		
			boolean bl_result = (source.length() > gl.E_EMPTY);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_header_syntax(String header)
	{
		
			String m_msg = gl.sf("Проверка заголовка файла данных ...[%s]",header);
		
			boolean bl_result = header.startsWith(Parser.HEADER);
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_list(List<String> source)
	{
		
			String m_msg = gl.sf("Проверка загрузки массива...[%d]",source.size());
		
			boolean bl_result = (source.size() > gl.E_EMPTY );
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_list_caption(String caption,List<String> source)
	{
		
			String m_msg = gl.sf("%s...[%d]",caption,source.size());
		
			boolean bl_result = (source.size() > gl.E_EMPTY );
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_list_one_item(String caption,List<Orion> source)
	{
		
			String m_msg = gl.sf("%s...[%d]",caption,source.size());
		
			boolean bl_result = (source.size() == gl.E_OK );
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	public boolean check_list_no_empty(String caption,List<String> source)
	{
		
			String m_msg = gl.sf("%s...[%d]",caption,source.size());
		
			boolean bl_result = (source.size() != gl.E_EMPTY );
				
		if(!bl_result)
		{
			this.setResponseHelper(new String[] {m_msg});
		}
		
			return gl.tx(new Object(){},bl_result,m_msg);		
		
	}
	
	
	public String get_action_name() 
	{ 
		return gl.sf("%s",this.getValue(Action.NAME)); 
	} 
	 
	public void welcome()
	{
		
		gl.tx_k(new Object() {},gl.sf("Activate action...[%s]",this.get_action_name()));

		this.setResponse(null);
	}
	
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
		 
		return true; 
		 
	} 
	 	 
		 
	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
	} 
	 
	
	public static void main(String[] args) { 

		 

	} 

} 
