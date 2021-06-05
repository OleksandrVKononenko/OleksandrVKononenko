package ap.orion.action;

import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import ap.global.gl;
import ap.orion.app.Application;
import ap.orion.cmd.CmdProcess;
import ap.utils.Fu;
import ap.utils.OsUtil;
import ap.utils.Su;
import ap.utils.ZipUtils;


public class ZipAction extends BaseAction {

	
	public 	static final  	List <String> 	ACTIVATORS 	= Arrays.asList(new String[] {"zip","zip()","arh","arh()","архив"}); 
	 
	public 	static final  	List <String> 	SWITCHES 	= Arrays.asList(new String[] {"/a","/s","-s"}); 

	private static final 	long 			serialVersionUID 		= 1L;
	
	public ZipAction() {
		
	}

	public ZipAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon, desc, mnemonic);
		
	}

	public ZipAction(String text) {
		super(text);
		
	}

	public ZipAction(String text, List<String> activators, List<String> switches) {
		super(text, activators, switches);
		
	}
	
	public boolean isa_set_impl()
	{
		
		CmdProcess proc = this.getProcess();
		
		if(!proc.isa_set_option())
		{
			return this.check_flow("Нет запроса на активацию",false);
		}
		
		
		// Читаем параметр.

		List<String> 	m_params = proc.get_list_of_type(CmdProcess.indexOf(CmdProcess.types,"PARAMETER"));

		String 			m_param = m_params.get(gl.E_EMPTY);

			try
			{
						String os = Application.getCio().getOs_name();//Su.BeforeAtFirst(OsUtil.get_os_raw()," ").trim();
				
						gl.tx_k(new Object(){},gl.sf("Операционная система...%s....Параметр...%s",os,m_param));		
						
						String m_root = Fu.getCurrentDir();
						
						if(os.startsWith("mac"))
							m_root = "/Users/alexplus/eclipse/wsp/Organizer";

						
						String source = gl.sf("%ssrc\\ap\\",m_root);
		    			
				    	String target = gl.sf("%szip\\%s_%s.zip",m_root,m_param,os);
				    	
				    	
				    	gl.tx_k(new Object(){},gl.sf("Архив...%s",target));		
						
				    	
				    	ZipUtils Z = ZipUtils.get_instance(source, target);
				    		
				    	if(!Z.run(true))
				    	{
				    		gl.tx_e(new Object() {},gl.sf("Создание архива...%s...для раздела...%s",Z.getTarget(),Z.getSource()));
				    		
				    		return false;
				    	}
				    	else 
				    	{
				    			String m_msg = gl.sf("\n%s%s\n%s%s\n%s%d байт",
				    				gl.format("Создан архив",gl.AL.LEFT,27,'.'),
				    				Z.getTarget(),
				    				gl.format("Раздел",gl.AL.LEFT,27,'.'),
				    				Z.getSource(),
				    				gl.format("Размер файла",gl.AL.LEFT,27,'.'),
				    				Fu.getFileSize(Z.getTarget())
				    				);
				    				
				    		gl.tx_k(new Object() {},m_msg);
				    	
				    		this.setResponse(Arrays.asList(new String[] {m_msg}));
				    		
				    	}
						
							return true;
			}
			catch(Exception e)
			{
							return false;
			}
				
		
	}
	
	@Override
	public boolean action_performed(CmdProcess p) { 

		this.setProcess(p);

		welcome();

		try {

			if (	p.get_count_of_headers() == gl.E_OK &&

					p.get_count_of_switches() == gl.E_OK &&

					p.get_count_of_params() == gl.E_OK
				) 
			{
				return isa_set_impl();
			} else {
				return this.check_flow(gl.sf("Ошибка командной строки...[%s]%s%s", p.getText(), System.lineSeparator(),
						"Usage : .zip acceptor archive_name "), false);
			}

		} // try.

		catch (Exception e) {
			return this.check_flow(gl.sf("Ошибка процесса...[%s]", e.toString()), false);

		}

	} 
	
	

	public static ZipAction get_instance()
	{
		ZipAction lafa = new ZipAction(); 
		
		return new ZipAction(lafa.getClass().getSimpleName(),ACTIVATORS,SWITCHES);
			
	}
	

	
	public static void main(String[] args) {
		

	}

}

