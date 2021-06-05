 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.config; 

import java.awt.Dimension; 
import java.io.IOException; 
import java.util.List; 

import ap.btn.TPanel; 
import ap.frame.TFrame; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.mng.SPanelManager; 
import ap.prop.SBounds; 
import ap.prop.SText; 
import ap.utils.Fu; 

public class TConfig { 

	private String  home = String.format( 
			"%s\\%s.config", Fu.get_path(Fu.getCurrentDir()), 
			"application"); 
	 
	private SText	version ; 
	 
	private SText	recent_form ; 
	 
	private SText   look_and_fill ; 
	 
	private SBounds desktop_bounds ; 
	 
	private String 	payload; 
	 
	 
	 
	public String getPayload() { 
		return payload; 
	} 

	public void setPayload(String payload) { 
		this.payload = payload; 
	} 

	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public SText getVersion() { 
		return version; 
	} 

	public void setVersion(SText version) { 
		this.version = version; 
	} 

	public SText getRecent_form() { 
		return recent_form; 
	} 

	public void setRecent_form(SText recent_form) { 
		this.recent_form = recent_form; 
	} 

	public SText getLook_and_fill() { 
		return look_and_fill; 
	} 

	public void setLook_and_fill(SText look_and_fill) { 
		this.look_and_fill = look_and_fill; 
	} 

	public SBounds getDesktop_bounds() { 
		return desktop_bounds; 
	} 

	public void setDesktop_bounds(SBounds desktop_bounds) { 
		this.desktop_bounds = desktop_bounds; 
	} 

	public TConfig() { 
		 
		version = new SText("version"); 
		 
		recent_form = new SText("recent_form"); 
		 
		look_and_fill = new SText("look_and_fill"); 
		 
		desktop_bounds = new SBounds("desktop_bounds"); 
		 
	} 
	 
	public TConfig(String home) { 
		 
		this(); 
		 
		this.setHome(home); 
	} 
	 
	public boolean read() 
	{ 
		if(!Fu.isaFile(this.getHome())) 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_OK,"The home file not exists yet",this.getHome())); 
			 
			return false; 
		} 
		 
		String source = ""; 
		 
		try { 
			 
			source = Fu.getFileAsStringScannerSkipComments(this.getHome()); 
			 
			this.setPayload(source.replace("\r\n","")); 
			 
		} catch (IOException e) { 
			 
			gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_ERROR,e.toString().substring(1,32))); 
			 
			return false; 
		} 
		 
			return this.parse(); 
	} 
	 
	public boolean read(String payload) 
	{ 
		 
		this.setPayload(payload); 
		 
		return this.parse(); 
	} 
	public boolean parse() 
	{ 
		return  version.parse(this.getPayload()) && 
				recent_form.parse(this.getPayload()) && 
				look_and_fill.parse(this.getPayload()) && 
				desktop_bounds.parse(this.getPayload()); 
				 
	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("%s%s%s%s", 
				this.getVersion().toString(), 
				this.getLook_and_fill().toString(), 
				this.getRecent_form().toString(), 
				this.getDesktop_bounds().toString()); 
	} 
	 
	public String toStringForWrite() 
	{ 
		return String.format("%s\n%s\n%s\n%s\n", 
				this.getVersion().toString(), 
				this.getLook_and_fill().toString(), 
				this.getRecent_form().toString(), 
				this.getDesktop_bounds().toString()); 
	} 
	 
	 
	public boolean write() 
	{ 
		Logger 	app_config_file = new Logger(this.getHome(),false); 
		 
				app_config_file.write(this.toStringForWrite(),false); 
		 
		return 	Fu.isaFile(this.getHome()); 
						 
	} 
	 
	public static void start(List<TPanel> items) 
	{ 
		 
		Object v = new Object()  {}; 

		SPanelManager mng = new SPanelManager(); 

		String msg = "Create GUI"; 
		 
		TConfig cfg = new TConfig(); 
		 
		boolean cfg_in_action = cfg.read(); 

		if(cfg_in_action) 
		{ 
			gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,msg,"read from config file")); 
						 
			TFrame.SCREEN_BOUNDS = cfg.getDesktop_bounds().getBounds(); 
		} 
		 
		if (!mng.createGUI()) { 
			gl.tracex(v, String.format("%s...%s",gl.S_ERROR,msg)); 

			return; 
		} 

			gl.tracex(v, String.format("%s...%s",gl.S_OK, msg )); 
		 
		if(cfg_in_action) 
		{ 
		 
			TFrame.HDC.setBounds(cfg.getDesktop_bounds().getBounds()); 
						 
			 if(!TFrame.setLookAndFill(cfg.getLook_and_fill().getText())) 
				 gl.tracex(v, String.format("%s...L&F setup...%s",gl.S_ERROR,cfg.getLook_and_fill().getText())); 
			 else 
				 gl.tracex(v, String.format("%s...L&F setup...%s",gl.S_OK,cfg.getLook_and_fill().getText())); 
				 		 
			msg = "Activation load from recent composite"; 
			 
			 if(mng.openFileBySK(cfg.getRecent_form().getText())) 
				{ 
					gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,msg,cfg.getRecent_form().getText())); 
					 
					mng.getFrame().updateTitle(cfg.getRecent_form().getText()); 
				} 
				else 
				{ 
					gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,msg,cfg.getRecent_form().getText())); 
				} 
			 
		} 
		 
		else 
		{ 
			 
			// Standard way. 
			 
			 
			if(items != null && !mng.addFromList(items)) 
			{ 
				gl.tracex(v,String.format("%s...without cfg...%s",gl.S_ERROR,msg)); 
				 
				return ; 
			} 
			 
				gl.tracex(v,String.format("%s...without cfg...%s",gl.S_OK,msg)); 
			 
				mng.getFrame().updateTitle(mng.getHome()); 
		} 
				TFrame.HDC.setVisible(true); 
		 
	} 
	 
	public static void Test_write() 
	{ 
		TConfig app = new TConfig(); 
		 
		String msg = "Testing of write method"; 
		 
		String payload = "version=some version;look_and_fill=some class of look and fill;recent_form=some recent form;desktop_bounds=0,0,0,0;"; 

		if(app.read(payload)) 
			gl.smn("Read Ok."); 
		 
		 
		if(!app.write()) 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_ERROR )); 
		else 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_OK )); 
				 
	} 
	 
	public static void Test_read() 
	{ 
		TConfig app = new TConfig(); 
		 
		String msg = "Testing of read method"; 

		if(!app.read()) 
			gl.tracex(new Object(){},String.format("%s...%s",msg,gl.S_ERROR )); 
		else 
		{ 
			gl.tracex(new Object(){},String.format("%s...%s...%s",msg,gl.S_OK,app.toString() )); 
		} 
				 
	} 
	 
	public static void Test_start() 
	{ 
			SText t = new SText("version"); 
		 
			t.parse("version=123-124;"); 
	 
			gl.smn(String.format("Name : %s Text : %s Payload : %s ",t.getName(),t.getText(),t.getPayLoad())); 
	 
	} 
	 
	 
	public static void Test_extractComment() 
	{ 
		 
		TConfig app = new TConfig(); 
		 
		String source; 
		 
		try { 
			source = Fu.getFileAsStringScannerExtractComments(app.getHome()); 
			 
			gl.smn(source); 
			 
		} catch (IOException e) { 
			gl.smn("Get error : " + e.toString()); 
		} 
		 
		 
	} 
	 
	public static void Test_extractSource() 
	{ 
		 
		TConfig app = new TConfig(); 
		 
		String source; 
		 
		try { 

			source = Fu.getFileAsStringScannerSkipComments(app.getHome()); 
			 
			gl.smn(source); 
			 
			gl.smn(source.replace("\r\n","")); 
			 
		} catch (IOException e) { 
			gl.smn("Get error : " + e.toString()); 
		} 
		 
		 
	} 
	 
	 
	 
	public static void main(String[] args) { 
		 
		// Test_write(); 
		 
		//Test_extractComment(); 
		 
		//Test_extractSource(); 
		 
		Test_read(); 
		 
			 
	} 

} 
