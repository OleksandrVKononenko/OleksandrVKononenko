package ap.orion.action;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.utils.Bau;
import ap.utils.DU;
import ap.utils.Fu;
import ap.utils.Su;


public class FileAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"f","f()","file","file()","файл"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public FileAction() {
		
	}

	public FileAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public FileAction(String text) {
		super(text);
		
	}

	public FileAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	@Override
	public List<String> get_usage_info()
	{
		
				return Arrays.asList(new String[] {
						
						gl.sf(" Команда...%s",this.get_action_name()),
						" выполняет работу с файлами",
						" Ключи активации ,-"
					
				});
				
	}
	
	public boolean isa_test_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_param  = m_params.stream().collect(Collectors.joining(""));
		
		gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s",m_param));		

			try
			{
				
				// Для двоичного параметра 11111110001b
				
				// Для hex 0x1bd
				
				
				int 			[] m_value_int = {gl.E_ERROR};
				
					m_param = m_param.toLowerCase();
				
				if(m_param.startsWith("0x") && m_param.endsWith("h"))
				{
					// HEX.
				
					m_value_int[0] = Integer.parseUnsignedInt(m_param.replace("0x","").replace("h",""),16);
					
				} else if(!m_param.startsWith("0x") && m_param.endsWith("b"))
				{
					// BITS.
				
					m_value_int[0] = Integer.parseUnsignedInt(m_param.replace("b",""),2);
				}
				else 
				{
					// DECIMAL.
					
					m_value_int[0] = DU.to_int(m_param);
					
				}
				
				String msg =	gl.sf("Попытка преобразовать...%s...в int",m_param);
				
				gl.tx(new Object(){},m_value_int[0]!=gl.E_ERROR,msg);
						
				if(m_value_int[0] ==gl.E_ERROR)
				{
					this.setResponse(Arrays.asList(new String[] {msg}));
					
					return false;

				}
				
					String m_msg = gl.sf("Параметр...%s...Результат...%d",
						   m_param,
						   m_value_int[0]);
								
					gl.tx_k(new Object(){},m_msg);		
						
					this.setResponse(Arrays.asList(new String[] {m_msg}));

					return true;
						
						
			}
			catch(Exception e)
			{
					return false;
			}
				
		
	}
	
	public boolean isa_show_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		if(!check_selected())
		{
			return false;
		}
		
		
		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_param = m_params.get(gl.E_EMPTY);
		
		if(!check_type_of(m_param))
		{
			return false;
		}
		
		
		
		//  Параметр м.б. state or right
		
		gl.tx_k(new Object(){},gl.sf("Представлен параметр...%s",m_param));		

		if(!m_param.equalsIgnoreCase("state") && !m_param.equalsIgnoreCase("right"))
		{
		
			String m_msg = gl.sf("Невалидный параметр...%s",m_param);
					
			gl.tx_e(new Object(){},m_msg);		

			this.setResponse(Arrays.asList(new String[] {m_msg}));
			
			return false;
		}
		
		
			try
			{
				
				List<String> m_response = new ArrayList<String>();
				
				if(m_param.equalsIgnoreCase("state"))
				{
					CollectionImpl.selected.forEach(a->
					{
						String m_item = gl.sf("%s...%s...%d...%s",a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getState().get_state()),
								a.getState().get_state(),
								m_param
								);
						
						m_response.add(m_item);
						
					});
					
					
				} else if(m_param.equalsIgnoreCase("right"))
				{
				

					CollectionImpl.selected.forEach(a->
					{
						String m_item = gl.sf("%s...%s...%d...%s",
								a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getRights().get_rights()),
								a.getRights().get_rights(),
								m_param
								);
						
						m_response.add(m_item);
					});
				} else if(m_param.equalsIgnoreCase("style"))
				{
				
					CollectionImpl.selected.forEach(a->
					{
						String m_item = gl.sf("%s...%s...%d...%s",
								a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getStyle().get_style()),
								a.getStyle().get_style(),
								m_param
								);
						
						m_response.add(m_item);
					});
					
				
				}

				
				else
					return false;
				
				// В консоль.
				
				
						m_response.forEach(a->{
						

							gl.tx_k(new Object(){},gl.sf("%s",a));		

						});
						
						this.setResponse(m_response);
		
						return true;
			}
			catch(Exception e)
			{
						return false;
			}
				
		
	}
	
	
	public boolean check_type_of(String value)
	{

		String m_msg =	gl.sf("Тип операции...%s",value);

		boolean bl_type_of = Su.in(value,new String[] {"right","state","style"});
				
				gl.tx(new Object(){},bl_type_of,m_msg);
		
				if(!bl_type_of)
				{
					this.setResponse(Arrays.asList(new String[] {gl.sf("Недопустимый %s",m_msg.toLowerCase())}));
				}
	
		return bl_type_of;
	}
	
	public boolean isa_set_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		if(!check_selected())
		{
			return false;
		}
		
		// Читаем параметр.

		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_type_of = m_params.get(gl.E_EMPTY);
		
		if(!check_type_of(m_type_of))
		{
			
			return false;
		}
		
		
		String 			m_value = m_params.stream().collect(Collectors.joining(""));
		
						m_value = m_value.replace(m_type_of,"");
						
						m_value = m_value.toLowerCase();
						
		
						gl.tx_k(new Object(){},gl.sf("Параметр после упаковки...[%s]",m_value));
		
				if(m_value.contains("("))
				{
					for(int i=0;i<10;i++)
					{
						m_value = m_value.replace(gl.sf("(%d)",i),"");
					}
					
					gl.tx_k(new Object(){},gl.sf("После повторной перепаковки...[%s]",m_value));
					
				}
						
		// Для двоичного параметра 11111110001b
		
		// Для hex 0x1bd
		

		gl.tx_k(new Object(){},gl.sf("Попытка преобразовать параметр...[%s]",m_value));
						
		
		int 			[] m_value_int = {gl.E_ERROR};
		
		if(m_value.startsWith("0x") && m_value.endsWith("h"))
		{
			// HEX.
		
			m_value_int[0] = Integer.parseUnsignedInt(m_value.replace("0x",""),16);
			
		} else if(!m_value.startsWith("0x") && m_value.endsWith("b"))
		{
			// BITS.
		
			m_value_int[0] = Integer.parseUnsignedInt(m_value.replace("b",""),2);
		}
		else 
		{
			// DECIMAL.
			
			m_value_int[0] = DU.to_int(m_value);
			
		}
		
		
		String msg =	gl.sf("Попытка преобразовать...%s...в int...%d",m_value,m_value_int[0]);
		
		gl.tx(new Object(){},m_value_int[0]!=gl.E_ERROR,msg);
				
		if(m_value_int[0] ==gl.E_ERROR)
		{
			this.setResponse(Arrays.asList(new String[] {msg}));
			
			return false;

		}
		
		
		//  Параметр м.б. state or right
		

			try
			{
				
				String [] m_type_of_arr = {m_type_of};
				
				List<String> m_response = new ArrayList<String>();
				
				// Replace of selected.
				
				List<Orion> m_solo = new ArrayList<Orion>();
				
							m_solo.addAll(CollectionImpl.selected);
	
							
				if(m_type_of.equalsIgnoreCase("state"))
				{
					
					m_solo.forEach(a->
					{
						a.getState().set_state(m_value_int[0]);
						
						String m_item = gl.sf("%s...%s...%d...%s",a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getState().get_state()),
								a.getState().get_state(),
								m_type_of_arr[0]
								);
						
						m_response.add(m_item);
						
						a.repaint();
						
					});
					
					
				} else if(m_type_of.equalsIgnoreCase("right"))
				{
				
					m_solo.forEach(a->
					{

						a.getRights().set_rights(m_value_int[0]);
					
						String m_item = gl.sf("%s...%s...%d...%s",a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getRights().get_rights()),
								a.getRights().get_rights(),
								m_type_of_arr[0]
								);
						
						m_response.add(m_item);
						
						a.repaint();
					});
					
				
				} else if(m_type_of.equalsIgnoreCase("style"))
				{
				
					m_solo.forEach(a->
					{

						a.getStyle().set_style(m_value_int[0]);
					
						String m_item = gl.sf("%s...%s...%d...%s",a.getName(),
								Bau.to_bin_str_from_int_enrich(a.getStyle().get_style()),
								a.getStyle().get_style(),
								m_type_of_arr[0]
								);
						
						m_response.add(m_item);
						
						a.repaint();
					});
					
				
				}
				
				
						// В консоль.
				
				
						m_response.forEach(a->{
						

							gl.tx_k(new Object(){},gl.sf("%s",a));		

						});
						
						this.setResponse(m_response);
		
						return true;
			}
			catch(Exception e)
			{
						return false;
			}
				
		
	}
	
	public boolean isa_test_option_impl(){
		
		String m_file = this.getProcess().get_param_by_index(gl.E_EMPTY);
				
		if(!Files.isReadable(Paths.get(m_file)))
		{

			return this.setResponseHelper(
					new String [] {
							gl.sf("Файл...%s... вне области видимости",
					m_file) 
					}
					,
					false
					);
			
		}
		
		try {
			
			List<String> m_source = Files.readAllLines(Paths.get(m_file));
		

			return this.setResponseHelper(
					new String [] {
							gl.sf("Тип файла...%s...строк...%s...проба...%s",
					Fu.get_file_extension(m_file), 
					m_source.size(),
					m_source.get(gl.E_EMPTY))
					
					},
					 true
					);
			
		} catch (IOException e) {
			return this.setResponseHelper(
					new String [] {
							gl.sf("Ошибка...%s",
					e.toString()) 
					},
					 false
					);
			
		}
				
		
	}
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
		this.setProcess(process);
		
		gl.tx_k(new Object(){},gl.sf("Activate action...%s",this.get_action_name()));		
		
		CmdProcess p = this.getProcess();
		
		this.setResponse(null);

		try {

			if ((p.isa_no_switches() && p.isa_no_params()) || p.isa_help_option()) {

				// Usage message.

				this.setResponse(this.get_usage_info());

				return true;

			} else if (p.isa_test_option() && p.get_count_of_params() > gl.E_EMPTY)
			{
				
				return isa_test_option_impl();
			}
			
			/*
			else if (p.isa_test_option() && !( p.isa_help_option() || p.isa_list_option()) && !p.isa_no_params() ) {
				return isa_test_impl();
			} else if (p.isa_set_option() && ! ( p.isa_help_option() || p.isa_list_option() || p.isa_test_option())  && (p.get_count_of_params() >= 2)) {
				return isa_set_impl();
			} else if (p.isa_list_option() &&  ( p.get_count_of_params() == gl.E_OK ) && !( p.isa_help_option() || p.isa_test_option())) {
				return isa_show_impl();
			} 
			*/
			else {
				
				this.setResponse(Arrays.asList(new String[] { "Ошибка параметров командной строки." }));

				return false;
			}

		} // try.
		catch (Exception e) {
			return false;
		}

	}	
	

	public static FileAction get_instance()
	{
		FileAction lafa = new FileAction(); 
		
		return new FileAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}