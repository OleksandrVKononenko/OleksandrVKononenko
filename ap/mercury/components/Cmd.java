package ap.mercury.components;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import ap.global.gl;
import ap.orion.action.BaseAction;
import ap.orion.cmd.CmdProcess;
import ap.orion.response.Response;
import ap.utils.Fu;
import ap.utils.Su;

@SuppressWarnings("serial")
public class Cmd extends JPanel {

	private JTextArea 				area 	= new JTextArea();
	
	private JTextArea 				cmd 	= new JTextArea();
	
	private JSplitPane 				sp 		= new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	private java.util.List<String> 	history = new ArrayList<String>();
	
	public boolean 					bl_continue = false;
	
	private int 					history_ptr;
	
	
	
	public int getHistory_ptr() {
		return history_ptr;
	}

	public void setHistory_ptr(int history_ptr) {
		this.history_ptr = history_ptr;
	}

	public static Map<String,Object> exec(String text)
	{	
   	
				Map<String,Object> r = new LinkedHashMap<String,Object>(); 
				
				try
				{
					
				
            	CmdProcess p = CmdProcess.get_instance(text);
          
            	r.put("process",(p != null));
            	
        		gl.tx(new Object(){},(boolean)r.get("process"),gl.sf("Инициализация процесса...%s",text));
        	
        		
        		r.put("parse",p.parse());
            	
        		gl.tx(new Object(){},(boolean)r.get("parse"),gl.sf("Парсинг команды...%s",text));
        		
        		
            	BaseAction ba = CmdProcess.lookup_action_by_activator(p.getName());
			        
            	r.put("action",ba.action_performed(p));
          
            	gl.tx(new Object(){},(boolean)r.get("action"),gl.sf("Выполнение команды...%s...%s",text,r.get("action")));
		       
            	
            	r.put("response",Response.get_response(ba.getResponse()));
                
            	r.put("result",((boolean)r.get("process") && (boolean)r.get("parse") && (boolean)r.get("action")));
				
				}
				catch(Exception e)
				{	
					r.put("exception",e.toString());
					
					gl.tx(new Object(){},false,gl.sf("Ошибка...%s",r.get("exception")));
		        
					r.put("result",false);
				}
				
					return r;
				
	}
	
	public java.util.List<String> getHistory() {
		return history;
	}

	public void setHistory(java.util.List<String> history) {
		this.history = history;
	}

	public Cmd() {
		
		this.setLayout(new BorderLayout());
		
		sp.setResizeWeight(.65);
		
		sp.setDividerSize(2);
		
		area.setFocusable(false);
		
		java.util.List<String> filter = Arrays.asList(new String[] {"clear","write","read","\\"}); 
		
		cmd.addKeyListener(new KeyAdapter() {
			
			final int cmd_exec=0,cmd_read = 1,cmd_write=2,cmd_clear=3,cmd_continue=4,cmd_up=5,cmd_down=6;
			

			void on_exec()
	    	{
				
				String m_text = Su.removeNL(cmd.getText());
				
        		// If was continue mode.
        		
        		// gl.sfn("State...%s",bl_continue);
        		
        		if(bl_continue)
        		{
        		
        			m_text = Su.removeNL(m_text).replace("\\", "").replace("#"," ");
        			
        			//m_text = m_text.replace("\\", "");
        			
        			//gl.sfn("After remove NL...%s",m_text);
        		
        			bl_continue = false;
        		}
    
        		// Проверка конкатенации команд.
        		
        		java.util.List<String> bat = new ArrayList<String>(); 
        		
        		if(m_text.contains(";"))
        		{
        			bat = Su.get_as_list(m_text,";");
        		}
        		else
        		{
        			bat.add(m_text);
        		}
        		
        		bat.forEach(a->{
        		{
	        		
	        		Map<String,Object> m = exec(a);
	        		
	        		boolean bl_result = (boolean)m.get("result");
	        				
	        		String record = gl.sf("%s %s%s%s",
	        				 a,
	        				 bl_result ? gl.S_OK : gl.S_ERROR,
	        				 System.lineSeparator(),
	        				 m.get("response") == null ? "" : m.get("response") 
	        				);
	        			
	        			area.append(record);
	        	    
	        	    if(bl_result)
	        	    {
	        	    	history.add(gl.sf("%s%s",System.lineSeparator(),a));
	        		
	        	    	setHistory_ptr(getHistory().size()-1);
	        	    }
	        	    
	        	    	cmd.setText("");
        		}
        		});
        		
	    	}
	    	
			void on_cmd_clear()
			{
				cmd.setText("");
			}
	    	void on_read()
	    	{
	    		
	    		try
	    		{
	    		String m_file = Su.AfterAt(cmd.getText(),"read");
		    	
		    	if(m_file.trim().length() == gl.E_EMPTY)
		    	{
		    		
		    		area.append(gl.sf("%sОжидается наименование файла."));
			    	
		    		return;
		    	}
		    		
		    		m_file = gl.sf("%slog\\%s.txt",Fu.getCurrentDir(),m_file);
		    	
		    	if(!Fu.isaFile(m_file))
		    	{
		    		area.append(gl.sf("%sФайл...[%s]...не доступен.",System.lineSeparator(),m_file));
			    	
		    		return;
		    	}
		    		
		    		
		    		java.util.List<String> h = Fu.get_list_from_file(m_file);
		    			
		    	if(h.size() == gl.E_EMPTY)
		    	{
		    		area.append(gl.sf("%sФайл...%s...не содержит записей.",System.lineSeparator(),m_file));
		    	
		    		return;
		    	}
		    	
		    		area.setText("");
		    				
		    		getHistory().clear();
		    				
		    		getHistory().addAll(h);
		    				
		    		area.append(gl.sf("%sЗагружен файл журнала...%s...%d...строк.",
		    						System.lineSeparator(),m_file,h.size()));
		    				
		    		h.forEach(a->
		    		{
		    			area.append(gl.sf("%s%s",System.lineSeparator(),a));
		    		});
		    				
		    		int m_ptr_history = h.size()-1;
		    				
		    		setHistory_ptr(m_ptr_history);
		    				
			    	area.append(gl.sf("%sИндекс скроллинга...%d",
			    						System.lineSeparator(),m_ptr_history));
	    		}
	    		catch(Exception e)
	    		{
	    			area.append(gl.sf("%s%s",
    						System.lineSeparator(),e.toString()));
	    			return;
	    		}
	    		finally
	    		{
	    			on_cmd_clear();
	    		}
	    	}
	    	
	    	
	    	void on_write()
	    	{
	    			String m_file = Su.AfterAt(cmd.getText(),"write");
		    	
		    	if(m_file.trim().length() == gl.E_EMPTY)
		    	{
		    		area.append(gl.sf("%sНе указано название файла журнала.",
		    				System.lineSeparator()
		    				));
	    		
		    		return;
		    	}
		    		// Some injection to the file name.
		    		
		    		//m_file = gl.sf("%slog\\%s_%s.txt",Fu.getCurrentDir(),m_file,DateUtil.get_stamp_for_zip());
		    		
		    		m_file = gl.sf("%slog\\%s.txt",Fu.getCurrentDir(),m_file);
		    		 
		    		boolean bl_save = Fu.saveStringToFile(m_file,Su.get_str_from_list(history),false,true);
		    		
		    		if(!bl_save)
		    		area.append(gl.sf("%sОшибка при записи журнала в файл...%s",System.lineSeparator(),m_file));
		    		else
		    		area.append(gl.sf("%sЖурнал сохранен в файл...%s",System.lineSeparator(),m_file));
		    		
		    		cmd.setText("");
		    		
	    	}
	    	
	    	void on_clear()
	    	{
	    		// gl.sfn("OnClear in");
	    		 
	    		area.setText("");
	    		
	    		cmd.setText("");
        		
        		getHistory().clear();
        		
        	    setHistory_ptr(gl.E_ERROR);
        	
	    		// gl.sfn("OnClear out");
	    	
	    	}
	    	
	    	
	    	void on_continue()
	    	{
	    		if(!bl_continue)
	    			bl_continue = true;
	    		
	    		// Накапливаем ввод в буфер.
	    
	    		// Необходимо удалять пробелы в процессе накопления.
	    		
	    		String 	m_tmp = cmd.getText();
	    		
	    		if(m_tmp.indexOf(" ") != gl.E_ERROR)
	    		   m_tmp = m_tmp.replace(" ","");
	    		else
	    			if(m_tmp.indexOf("#") != gl.E_ERROR)
			    		   m_tmp = m_tmp.replace("#"," ");


	    		   cmd.setText(m_tmp);
	    		
	    		//gl.sfn("Продолжение ввода...%s...%s",cmd.getText(),bl_continue);
	    		
	    	}
	    	
	    	void on_up()
	    	{
	    		if(getHistory().size() == gl.E_EMPTY)
	    			return;
	    		
	    		int next = getHistory_ptr()+1;
    			
	        	if(next < getHistory().size())
	        	   setHistory_ptr(next);
	        	else
	        		setHistory_ptr(gl.E_EMPTY);
	        	
	        	//gl.sfn("(Up) History ptr...%d",getHistory_ptr());
	        	
	        	cmd.setText(getHistory().get(getHistory_ptr()).replace("Ok","").replace("Error", "").trim());
	        	
	    	}
	    	

	    	void on_down()
	    	{
	    		if(getHistory().size() == gl.E_EMPTY)
	    			return;
	    		
	    		int next = getHistory_ptr()-1;

	        	if(next >= gl.E_EMPTY)
	        	   setHistory_ptr(next);
	        	else	    
	        		setHistory_ptr(getHistory().size()-1);
			        	    

	        	cmd.setText(getHistory().get(getHistory_ptr()).replace("Ok","").replace("Error", "").trim());
	        	
	        	//cmd.setText(getHistory().get(getHistory_ptr()));
	        	
	        	//gl.sfn("(Down) History ptr...%d",getHistory_ptr());
	    		
	    	}
	    	
	    	void on_finally()
	    	{
	    			
	        		cmd.requestFocus();
	        }
	    	
	    	void on_exception(Exception e)
	    	{
	    		
	    		area.append(gl.sf("%s...%s...%s",System.lineSeparator(),cmd.getText(),e.toString()));
	        		
	        	cmd.setText("");
	        	
	    	}
	    	

		    public void keyPressed(KeyEvent e) { 
		    	
		    	int cmd_op = gl.E_ERROR; 
		    	
		    	if (cmd.getText().length() >= 255 ) 
		            e.consume();
		    	  
		        try
		        {

		        if(e.getKeyCode()==KeyEvent.VK_ENTER)
		        {
		        	if(history.size() == gl.E_EMPTY)
	        		{
	        			area.setFocusable(true);
	        		}
	        	
		        	if (cmd.getText().trim().startsWith("clear"))
			    	{
		        		cmd_op = cmd_clear;
		        		
			    	} else if (cmd.getText().trim().startsWith("write"))
			    	{
			    		cmd_op = cmd_write;
			    		
			    	} else if (cmd.getText().trim().startsWith("read"))
			    	{
			    		cmd_op = cmd_read;
			    		
			    	}else if (Su.reverse(cmd.getText()).trim().startsWith("\\"))
			    	{
			    		cmd_op = cmd_continue;
			    	}
			    	else if (cmd.getText().trim().startsWith("."))
			    	{
			    		cmd_op = cmd_exec;
			    	}
			    	else
			    	{
			    		if(!bl_continue)
		        		   cmd.setText("");
			    	}
			    		
		        } else if(e.getKeyCode()==KeyEvent.VK_UP)
			    {
		        		cmd_op = cmd_up;
			    }
			    else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			    {
			    	    cmd_op = cmd_down;
			        	
			    }
		      
				        
				        switch(cmd_op)
				        {
				        	case cmd_exec :
				        	on_exec();
				        	break;
				        	
				        	case cmd_clear :
					        on_clear();
					        break;
		
				        	case cmd_continue :
					        on_continue();
					        break;
					        
				        	case cmd_write :
						    on_write();
						    break;
						    
				        	case cmd_read :
							on_read();
							break;
							
				        	case cmd_up :
							on_up();
							break;
							    
					        case cmd_down :
							on_down();
							break;
				        
				        } // switch
		        
		        } // try
		        catch(Exception  ex)
		        {
		        			on_exception(ex);
		        }
		        finally
		        {
		        			on_finally();
		        }
		        
		        
		        
		    } // keyPressed
		}); // keyAdapter
		
					sp.setTopComponent(new JScrollPane(area));
					
					sp.setBottomComponent(new JScrollPane(cmd));
				
					this.add(sp,BorderLayout.CENTER);
					
					this.setName(gl.sf("%s_%s","Cmd",this.getClass().getSimpleName()));
					
		
	}

	public Cmd(LayoutManager layout) {
		super(layout);
		
	}

	public Cmd(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public Cmd(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}

	public static Cmd get_instance() {
		
		return new Cmd();
	}

	 public static void createAndShowGUI()
	    {
	        JFrame 	f = new JFrame();
	        
	        		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
	        		f.getContentPane().add(Cmd.get_instance());
	        
	        		f.setSize(420, 340);
	        
	        		f.setLocationRelativeTo(null);
	        
	        		f.setVisible(true);
	    }
	 
	 
	public static void main(String[] args) {
		
		 EventQueue.invokeLater(new Runnable()
	        {
	            @Override public void run()
	            {
	                	createAndShowGUI();
	            }
	        });
		
	}

	
}
