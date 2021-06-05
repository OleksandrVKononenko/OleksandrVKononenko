package ap.orion.action;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import ap.global.gl;
import ap.mercury.components.ComboBox;
import ap.mercury.components.parser.Parser;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.lsnr.BrewActionListener;
import ap.orion.lsnr.ListSelectionListenerImpl;
import ap.orion.state.ObjectRights;
import ap.orion.state.ObjectState;
import ap.shape.Ru;
import ap.utils.Su;


public class CleanAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"del","delete()","clean","clean()","очистить"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public CleanAction() {
		
	}

	public CleanAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public CleanAction(String text) {
		super(text);
		
	}

	public CleanAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	protected List<String> get_usage_info()
	{
		return Arrays.asList(new String[] {
				
				gl.sf("Команда...%s",this.get_action_name()),
				"выполняет удаление объектов",
				"Ключи активации ,-",
				" -s удаляет выделенные селектором объекты",
				" -a удаляет все объекты",
				" -v сопровождает выполнение отчетом об удаленных объектах",
				" допустимые комбинации ключей : {/s /v} { /a /v}",
				" Пример запуска: .clean /a --v"
		});
	}
	
	public boolean isa_all_option_impl()
	{
		
		return CollectionImpl.clean_all();
		
	}
	
	public boolean isa_selected_option_impl()
	{
		
		try
		{
			
		if (this.getProcess().isa_quote_option())
		{
			
			List<Boolean> state = new ArrayList<Boolean>();
			
			CollectionImpl.selected.forEach(a->{
				
				if(a.get_class().equalsIgnoreCase("Orion"))
				{
					a.setFont(new Font("Tahoma",Font.PLAIN,9));
					
					a.getData().setHeaders(null);
					
					a.getData().setData(null);
					
					a.getDecoro().set_text(null);
					
				} else if(a.get_class().equalsIgnoreCase("ComboBox"))
				{
					
						a.getData().setHeaders(null);
					
						a.getData().setData(null);
					 	
						ComboBox<String> cmb = (ComboBox<String>)a.getChild();
						
						cmb.removeAllItems();
						
						ActionListener []  als = cmb.getActionListeners();
						
						if(als.length != gl.E_EMPTY)
						{
								BrewActionListener bal = (BrewActionListener)als[gl.E_EMPTY];
					
								if(this.check_flow("Лисенер на борту ",(bal != null)))
								{
									cmb.removeActionListener(bal);
								}
						
								 als = null;
								 
								 als = cmb.getActionListeners();
								
							if( ! this.check_flow("!!! Проверка удаления лисенера",(als.length == gl.E_EMPTY)))
							{
								state.add(false) ;
							}
						}
						else
						{
							this.check_flow("!!! Лисенеров нет на борту ",(1==1));
								
						}
						
						
				}  else if(a.get_class().equalsIgnoreCase("ScrollPane"))	
				{	
					
					JViewport jvp 	= ((JScrollPane)a.getChild()).getViewport();
					
					Component jv 	= jvp.getView();
			
					if(jv instanceof ap.mercury.components.List)
					{
						jvp.setView(ap.mercury.components.List.get_instance(Ru.get_instance()));
						
					} else if(jv instanceof JTable)
					{
						// ((JTable)jv).setModel(a.getData().getTable_model());
						jvp.setView(new JTable());
					}

				} // global if.
				
						a.repaint();
				
			});
			
				return !state.contains(false);
		}
			
				return CollectionImpl.clean_only_selected();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean isa_verbose_impl(boolean mode)
	{
		
		if(mode)
		{
			this.setResponseHelper(new String[] {CollectionImpl.toString("Удаленные объекты",CollectionImpl.getDeleted())});
		}
		
			return true;
	}
	
	
	@Override
	public boolean action_performed(ap.orion.cmd.CmdProcess process) { 
	
		this.setProcess(process);
		
		welcome();
		
		// Get a process.
		
		CmdProcess p = this.getProcess();
		
		this.setResponse(null);
		
		try 
		
		{
			
			if(p.isa_no_switches() && p.isa_no_params() || !p.isa_set_option())
			{
				// Usage message.
				
				this.setResponse(this.get_usage_info());
				
				return true;
			}
			else
			{
				// Presents switches and params.
				if(p.isa_all_option() && p.isa_selection_option())
				{
						return false;
				}
				else if(p.isa_all_option())
				{
						return isa_all_option_impl() && isa_verbose_impl(p.isa_verbose_option());
							
				} else	if(p.isa_selection_option())
				{
					if(!check_selected())
						return false;
					
						return isa_selected_option_impl()&& isa_verbose_impl(p.isa_verbose_option());
				} 
						
			}
						return false;
		}
		
		catch(Exception e)
		{
						return false;
		}
		 
	} 
	
	

	public static CleanAction get_instance()
	{
		CleanAction lafa = new CleanAction(); 
		
		return new CleanAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
