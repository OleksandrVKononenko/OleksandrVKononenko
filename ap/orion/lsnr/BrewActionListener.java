package ap.orion.lsnr;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import ap.global.gl;
import ap.mercury.components.Cmd;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.impl.ActionListenerImpl;
import ap.orion.impl.CollectionImpl;
import ap.utils.DU;
import ap.utils.Su;


public class BrewActionListener extends ActionListenerImpl {
	
	
	private String context;
	
	
	public String update_context(String value)
	{
		if(!this.getContext().contains(value))
		this.setContext(gl.sf("%s%s%s",this.getContext(),Parser.FIELD_DLM,value));
		
		return this.getContext();
	}
	
	public String clear_context(String value)
	{
		this.setContext(Su.BeforeAtFirst(this.getContext(),gl.sf("%s",Parser.FIELD_DLM)));
		
		return this.getContext();
	}
	
	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public BrewActionListener() {
		
	}
	
	public BrewActionListener(String context) {
		
		this.setContext(context);
	}
	
	/*
	public BrewActionListener(String context,Orion producer) {
		
		this(context);
		
		this.set_producer(producer);
		
	}
	
	*/
	
	public static BrewActionListener get_instance()
	{
		return new BrewActionListener();
	}
	
	public static BrewActionListener get_instance(String context)
	{
		return new BrewActionListener(context);
	}

	/*
	public static BrewActionListener get_instance(String context,Orion producer)
	{
		return new BrewActionListener(context,producer);
	}
	*/
	public Orion extract_publisher(String header)
	{

		if(header == null || !header.contains(gl.sf("%s",Parser.FIELD_DLM)))
			return null;
		
		String pub_name = Su.BeforeAt( Su.AfterAt(header,Parser.FIELD_DLM),Parser.ATTR_DLM);
			
		return CollectionImpl.get_by_name(CollectionImpl.get_all_components(),pub_name);
		
	}

	public int extract_data_index(String header)
	{

		if(header == null || !header.contains(gl.sf("%s",Parser.FIELD_DLM)))
			return gl.E_ERROR;
		
		String index = Su.AfterAt( Su.AfterAt(header,Parser.FIELD_DLM),Parser.ATTR_DLM);
			
		return DU.to_int(index);
		
	}
	
	
	
	 @Override 
	   public void actionPerformed(ActionEvent e) { 
	    	 
	        JComboBox<String> combo = (JComboBox<String>) e.getSource(); 
	 
	        JComboBox<String> box 	= (JComboBox<String>)e.getSource(); 
	         
			String item = (String)box.getSelectedItem(); 
			
			int m_selected_index = box.getSelectedIndex();

			gl.tx_k(new Object(){},gl.sf("Выбран пункт...%s",item)); 
				
			if(this.getContext().startsWith("brew"))
			{
				String cmd = gl.sf(".set /. %s",item);
	
				gl.tx(new Object(){},
						(boolean)Cmd.exec(cmd).get("result"),
						gl.sf("Команда активации...[%s]",cmd
						));
				
			}
			else if(this.getContext().startsWith("lf"))
			{

				String extract = Su.BeforeAt(Su.AfterAt(item,"."),"Look");
						
				String cmd =  gl.sf(".lf /. %s",extract);
				
				gl.tx(new Object(){},
						(boolean)Cmd.exec(cmd).get("result"),
						gl.sf("Команда активации...[%s]",cmd
						));
				
					
			} else if(this.getContext().startsWith("header"))
			{
				String cmd =  gl.sf(".prop /. /hdr %s",item);
				
				
				// TODO 30.12.2020 header:: 
				
				// Заголовки данных от поставщика.
				
				List<String> m_headers = Su.get_as_list_for_data(this.getHeaders());
				
				gl.sfn("Заголовки в строке...[%s]",this.getHeaders());
				
				Su.show("Список заголовков",m_headers);
				
				if(m_selected_index == gl.E_ERROR)
				{
					return;
				}
				
				Orion 	publisher 	= extract_publisher(m_headers.get(m_selected_index));
				
				gl.tx(new Object(){},publisher != null,
						gl.sf("ВАЖНО !!! Проверка издателя...[%s]",publisher != null ? publisher.getName() : "null")); 
				
				if(publisher == null)
					return;
				
				int 	data_index 	= extract_data_index(m_headers.get(m_selected_index));
				
				gl.sfn("----> Data index....[%d]",data_index);
				
				String 	data = Su.get_as_list_for_quote_file(publisher.getData().getData()).get(data_index);
				
						
				Su.show("Селектированый индекс",Arrays.asList(new String[]{gl.sf("%s [%s %d length:[%d]]",
						m_headers.get(m_selected_index),
						publisher == null ? "null" : publisher.getName(),
						data_index ,
						data.length()
						)
				        }));
				
				
				// Исходные данные,- 
				// продюсер,индекс пакета данных,потребитель (в контексте после биндера)  все в рав пакете .
				// item,m_selected_index
				

				List<String> m_subs = this.getContext().contains(gl.sf("%s",Parser.FIELD_DLM)) ? Su.get_as_list(
						
						Su.AfterAtFirst(this.getContext(),gl.sf("%s",Parser.FIELD_DLM)),
						
						gl.sf("%s",Parser.FIELD_DLM)) : null;
				
				Su.show("Подписчики",m_subs);
				
				
				if(m_subs == null)
				{
					
					gl.tx(new Object(){},true,
							gl.sf("ВАЖНО !!! Подписчиков пока нет...[%s]",item)); 
					
					return;
				}

				// 
				//CollectionImpl.show("---> Все объекты ",CollectionImpl.get_all_components());
				
				// Данные преобразовываем в список и отсылаем подписчику.
				
				m_subs.forEach(s->{
					

					Orion subscriber = CollectionImpl.get_by_name(CollectionImpl.get_all_components(),s);
					
					if(subscriber != null)
					{
						gl.tx(new Object(){},subscriber.set_data(Su.get_as_list_for_data(data),null),
								gl.sf("Отправка данных подписчику...[%s]",item)); 
						
					}
					
					
				});
			}
			else
			{

						gl.tx_e(new Object(){},gl.sf("Вне контекста...[%s]",item)); 

			}
	    } 
	

	public static void main(String[] args) {
		

	}

	
	
}
