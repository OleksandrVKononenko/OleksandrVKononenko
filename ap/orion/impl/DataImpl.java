package ap.orion.impl;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import ap.global.gl;
import ap.mercury.components.ComboBox;
import ap.mercury.components.List;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion;
import ap.orion.lsnr.ListSelectionListenerImpl;
import ap.shape.Ru;
import ap.utils.DU;
import ap.utils.Su;

public class DataImpl {
	
	
	private Orion 		owner;
	
	private String		headers;
	
	private String 		data;
	
	private int 		selected_index;
	
	private String 		group;
			 
	private 			String     prefered_size = "";

	private String 		view;
	
	private String		key;
	
	private String		value;
	
	
	
	public void put(String key_value)
	{
		java.util.List<String> store   = Su.get_as_list_for_data(key);
		
		if(store == null)
		   store = new ArrayList<String>();
			
		java.util.List<String> values = Su.get_as_list_for_data(value);
		
		if(values == null)
		   values = new ArrayList<String>();
	
	}

	public String getKey() {
		return key;
	}


	public void setKey(String key_data) {
		this.key = key_data;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value_data) {
		this.value = value_data;
	}


	public String getView() {
		return view;
	}


	public void setView(String view) {
		this.view = view;
	}


	public String getPrefered_size() {
		return prefered_size;
	}


	public void setPrefered_size(String prefered_size) {
		this.prefered_size = prefered_size;
	}


	public String getHeaders() {
		return headers;
	}


	public void setHeaders(String headers) {
		this.headers = headers;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}

	public int getSelected_index() {
		return selected_index;
	}


	public void setSelected_index(int selected_index) {
		this.selected_index = selected_index;
	}


	public Orion getOwner() {
		return owner;
	}


	public void setOwner(Orion owner) {
		this.owner = owner;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public DataImpl()
	{
		this.setSelected_index(gl.E_ERROR);
	}
	
	public DataImpl(Orion owner)
	{
		this();
		
		this.setOwner(owner);
	}
	
	public DataImpl(String value)
	{
		this();
		
		this.setData(value);
	}
	
	public static DataImpl get_instance()
	{
		return new DataImpl();
	}

	public static DataImpl get_instance(Orion owner)
	{
		return new DataImpl(owner);
	}

	public static DataImpl get_instance(String value)
	{
		return new DataImpl(value);
	}
	

	
	public boolean pop()
	{
		try
		{
			if(this.getOwner().get_class().equalsIgnoreCase("ScrollPane"))
			{
				
				JViewport jvp 	= ((JScrollPane)this.getOwner().getChild()).getViewport();
				
				Component jv 	= jvp.getView();

				if(jv instanceof JTable)
				{
					
					jvp.setView(new JTable());
					
					gl.tx_k(new Object() {},gl.sf("Пересоздание таблицы у компонента...%s",
		    				  
		        			this.getOwner().getName()
		    				 
		    				 ));
		        
						
				}
		
			}
				return true;
		}
		catch(Exception e)
		{
				return false;
		}

	}
	
	
	
	// TODO 27.12.2020 push.
	
	public boolean push()
	{
		try
		{
			
			if(this.getOwner().get_class().equalsIgnoreCase("ComboBox"))
			{
				
				ComboBox combo =  ((ComboBox<String>)this.getOwner().getChild());
						
				java.util.List<String> l = CollectionImpl.get_items_from_combo(combo);
				
				Su.get_as_list_for_data(this.getData()).forEach(a->{
					
		
					// Загружаем только пролог.
					
					String item = Su.BeforeAtFirst(a,gl.sf("%s",Parser.FIELD_DLM));
					
					if(!l.contains(item))
					   combo.addItem(item);
					
					
					
				});
		
			} // JComboBox
			else if(this.getOwner().get_class().equalsIgnoreCase("ScrollPane"))
			{
				
				JViewport jvp 	= ((JScrollPane)this.getOwner().getChild()).getViewport();
				
				Component jv 	= jvp.getView();
				
				// ВАЖНО !!! Обнаружена потеря вью при сохранении скроллпейна  как дочернего объекта
				// например у Panel.
				
				// Будем лечить, путем введения нового аттрибута [view] в Data под системе.
				
				gl.tx_k(new Object(){},gl.sf("View...[%s]...Font...[%s]",
						
						this.getOwner().getData().getView() != null ? this.getOwner().getData().getView() : "null",
						this.getOwner().getData().getKey()  != null ? this.getOwner().getData().getKey() : "null" 
								
						));
				
				if(jv == null)
				{
					
					Component jv_component = null;
					
					if(this.getOwner().getData().getView() != null)
					{
						if(this.getOwner().getData().getView().equalsIgnoreCase("JTable"))
							jv_component = new JTable();
						else if(this.getOwner().getData().getView().equalsIgnoreCase("JList"))
							jv_component = List.get_instance(Ru.get_instance());
								
					}
					
					// Обновляем фонт.
					
					jvp.setView(jv_component);
					
					jv 	= jvp.getView();
				
				} else
				
				if(jv instanceof List)
				{
					
					
					Vector<String> v = new Vector<String>();
					
					v.addAll(Su.get_as_list_for_data(this.getData()));
					
					List<String> m_list = new List<String>(v);
					
					m_list.addListSelectionListener(ListSelectionListenerImpl.get_instance(this.getOwner()));
				
					gl.tx_k(new Object() {},gl.sf("Индекс у провайдера...%d",
		    				  
		        			this.getOwner().getData().getSelected_index()
		    				 
		    				 ));
		        
					if(this.getSelected_index() != gl.E_ERROR)
					{	
						
						m_list.setSelectedIndex(this.getSelected_index());
						
						m_list.ensureIndexIsVisible(m_list.getSelectedIndex());
						
						m_list.scrollRectToVisible(m_list.getCellBounds(m_list.getSelectedIndex(), m_list.getSelectedIndex()));
						
					}
					
						jvp.setView(m_list);
									
				} else
					if(jv instanceof JTable)
					{
						
						// TODO Чтение параметрв фонта. 

						Font font = null;
						
						try
						{
						if ( this.getOwner().getData().getKey() != null)
						{
							java.util.List<String> fo = Su.get_as_list(this.getOwner().getData().getKey(),gl.sf("%s",Parser.ATTR_DLM));
						 
						 if(fo != null)
						 {
							font = new Font(
									 fo.get(0),
									 DU.to_int(fo.get(1)),
									 DU.to_int(fo.get(2))
							 );
						  }
						}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				
						
						java.util.List<String> m_data = new ArrayList<String>(
								 Su.get_as_list_for_data(
										 this.getData()
									)
								 );
					
						 String 				m_header = m_data.get(gl.E_EMPTY);
						 
	 											m_header = Su.BeforeAtFirst(
	 													Su.AfterAtFirst(m_header, gl.sf("%s",Parser.FIELD_DLM)),
	 													gl.sf("%s",Parser.FIELD_DLM)
	 													);
	 								
		
	 					Vector<String> 			fields = new Vector<String>();
	 
	 					Vector<Object> 			data = new Vector<Object>();
	 
	 					java.util.List<String> 	m_headers = Su.get_as_list(m_header);
	 
						 						fields.addAll(m_headers);

						// Удаляем первую запись,- перечень полей в пакете данных.
						
						 						m_data.remove(gl.E_EMPTY);
						 		

												 for(int j=0;j<m_data.size();j++)
												 {
													 Vector row = new Vector(m_headers.size());

													 for(int i=0; i< m_headers.size(); i++) {
												            
														 java.util.List<String> m_row = Su.get_as_list(m_data.get(j),gl.sf("%s",Parser.ATTR_DLM));
														 
														 	row.addElement(m_row.get(i));
														 	
												        }
												        
												        data.addElement(row);
												 }
						 	
												 JTable jt = Orion.accept_table_model_simple(data, fields);
					
												 // TODO Установка фонта .
							
												 if(font != null)
												 {
												 
													 Font header_font = new Font(font.getName(),font.getStyle(),font.getSize()+2);
													 
													 jt.getTableHeader().setFont(header_font);
												 
													 jt.setFont(font);
												 
												 }
												 
												 
												 jvp.setView(jt);
																			
												return gl.tx(new Object() {},true,
															gl.sf("ВАЖНО !!! Обновление данных дочернего компонента класса...[%s]",
																	this.getClass().getSimpleName()));
						 							
					}
			}
		
			return true;
		}
	catch(Exception e)
	{
			return false;
	}

	}
}
