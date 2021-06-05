package ap.orion.action;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.mercury.components.Cmd;
import ap.mercury.components.Gimension;
import ap.orion.Orion;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.orion.impl.CollectionImpl;
import ap.orion.impl.ImageImpl;
import ap.orion.live.Live;
import ap.orion.state.ObjectRights;
import ap.orion.state.ObjectState;
import ap.prop.SBounds;
import ap.prop.SDimension;
import ap.shape.Ru;
import ap.uat.ApplicationA;
import ap.utils.Bau;
import ap.utils.Fu;
import ap.utils.Su;



public class UploadImageAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"image","image()","img","img()","образ"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public UploadImageAction() {
		
	}

	public UploadImageAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public UploadImageAction(String text) {
		super(text);
		
	}

	public UploadImageAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}

	public boolean isa_fit_impl()
	{

			gl.tx_k(new Object(){},gl.sf("Фит образа...")); 
		
			List<Boolean> m_result = new ArrayList<Boolean>();
		
			CollectionImpl.update_selected();
		
			CollectionImpl.selected.forEach(a->{
			
			m_result.add(a.getImage().fit(a) && a.re_repaint());
	
		});
		
			return !m_result.contains(false); 

	}
	
	public boolean isa_clear_impl()
	{
	
		gl.tx_k(new Object(){},gl.sf("Очистка образа...")); 
		
		List<Boolean> m_result = new ArrayList<Boolean>();
		
		CollectionImpl.update_selected();
		
		CollectionImpl.selected.forEach(a->{
			
			m_result.add(a.getImage().clear() && a.re_repaint());
			
		});
		
		return !m_result.contains(false); 
	
	}
	
	public boolean isa_set_impl()
	{
		try
		
		{
		
		List<String> m_files = Fu.files_open_dlg(Application.getCio().getHome());
		 
		if(m_files.size() == gl.E_EMPTY)
		{
			
			this.setResponse(Arrays.asList(new String[] {"Файл не выбран."}));
			
			return false;
		}
		
		
		int 			m_images_files_size = m_files.size() ;
		
		List<Boolean> 	m_resulto = new ArrayList<Boolean>();			
		
		int 			m_selected_size = CollectionImpl.selected.size();

		int 			m_add_count = (m_images_files_size - m_selected_size);

		Rectangle 		m_rect = null;
		
		List<Orion> 	additional = new ArrayList<Orion>();
		
		Orion 			owner = CollectionImpl.selected.get(gl.E_EMPTY).getIdo().getOwner();
		
		
		// Берем размерность шаблона за эталон.
		
		m_rect = CollectionImpl.selected.get(gl.E_EMPTY).getBounds();
		
		gl.tx_k(new Object(){},gl.sf("Родительский объект...[%s]...Шаблон...[%s]",
				owner.getName(),
				SBounds.toString(m_rect)
				));		
	
		
		if(m_add_count != gl.E_EMPTY)
		{
			
			// Подготовка массива  дополнительных объектов, кроме выбранных.
			
			// Все выбранные файлы  образы загружаем.
			
			// Создаем объекты.
			
			
			for(int i=0; i < m_add_count;i++)
			{
				
				Orion 	orion = Orion.get_instance("Panel");
				
						orion.getState().set_selected(true);
		
						orion.setBounds(m_rect);
						
						additional.add(orion);
						
			}
			
			CollectionImpl.selected.addAll(additional);
		}
		
		
		// Загружаем образы.
		
		// Сначала селектированным объектам.
		
		int [] index = {0};
		
		
		CollectionImpl.selected.forEach(a->{
	
			if(index[0] > m_files.size()-1)
			   index[0] = gl.E_EMPTY;
			
			m_resulto.add(Bau.imp_buf_img_as_is(a, m_files.get(index[0]),false));
			
			index[0]++;
			
		});
		
		// Добавляем объекты к владельцу ПОКА частным методом не используем стандартный .insert(...)
		
		
		if(additional.size() != gl.E_EMPTY)
		{
			additional.forEach(a->{
				
				a.getIdo().setPid(owner.getIdo().getId());
				
				a.getIdo().setOwner(owner);
				
				owner.add(a);
				
			});
			
		   
		    
		    m_resulto.add((boolean)Cmd.exec(gl.sf(".order /. 3,3")).get("result"));
		    
		}
			    additional.clear();
				    
			    Application.getDio().get_desk_top().repaint();
			    
		
			    // Проверка преферед сайзинга.
			    
			    Dimension m_cur_pref = owner.getPreferredSize();
				
			    Dimension m_fut_pref = CollectionImpl.get_pref_size_fut(owner);
			    
			    Dimension m_eff_dim = Gimension.get_effective_dim(m_cur_pref, m_fut_pref);
			    
			    // Обновление преферед родительского объекта.
			    
			    owner.setPreferredSize(m_eff_dim);
			    
			    owner.getData().setPrefered_size(SDimension.toString(owner.getPreferredSize()));
			    
			    owner.revalidate();
		 
			    
				gl.tx_k(new Object(){},gl.sf(
						     "Текущий преферед...[%s]"
						+ "...Прокси преферед...[%s]"
						+ "...Эффективный преферед...[%s]",
						
						SDimension.toString(m_cur_pref),
						SDimension.toString(m_fut_pref),
						SDimension.toString(m_eff_dim)
						
						
						));		
			
			    
				
				return !m_resulto.contains(false);
				
			}
	
	
			catch(Exception e)
			{

				return gl.tx(new Object(){},false,gl.sf("[%s]",e.toString().substring(0,32)));		
				
			}
		}			
		
	
	
	@Override
	public boolean action_performed(CmdProcess process) { 
	
					this.setProcess(process);
		
					gl.tx_k(new Object(){},gl.sf("Activate action...%s",this.get_action_name()));		
					
					// Get a process.
		
					CmdProcess p = this.getProcess();
		
					this.setResponse(null);
		
		try 
		
		{
		
		 if 		(
				 		p.get_count_of_headers() 	== gl.E_OK 	
					)
		{
				
				if(!check_selected())
					return false;
			
				if(p.isa_set_option())
				{ 
					return isa_set_impl();
				}
				else if(p.isa_clear_option())
				{
					return isa_clear_impl();
				}
				else if(p.isa_fit_option())
				{
					return isa_fit_impl();
				}
				else
				{
					return false;
				}
		 }
			else
			{
					this.setResponse(null);
				
					return false;
			}
		 
		 		
		 
		} // try.
		catch(Exception e)
		{
					return false;
		}
		 
	} 
	
	

	public static UploadImageAction get_instance()
	{
		UploadImageAction lafa = new UploadImageAction(); 
		
		return new UploadImageAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}
