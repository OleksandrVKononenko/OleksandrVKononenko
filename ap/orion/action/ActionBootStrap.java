package ap.orion.action;


public class ActionBootStrap {
	
	public static final BaseAction [] actions = new BaseAction[] { 
			
			LookAndFillAction.get_instance() ,
			
			CloseApplicationAction.get_instance(), 
				
			CleanAction.get_instance(),
			
			ReadAction.get_instance(),
			
			WriteAction.get_instance(),
			
			BrewAction.get_instance(),
			
			UploadImageAction.get_instance(),
			
			OrderAction.get_instance(),
			
			ZoomAction.get_instance(),
			
			PropAction.get_instance(),
			
			SelectionAction.get_instance(),
			
			ZipAction.get_instance(),
			
			ModeAction.get_instance(),
			
			BitAction.get_instance(),
			
			NopAction.get_instance(),
			
			FileAction.get_instance(),
			
			RunAction.get_instance(),
			
			ShowAction.get_instance(),
			
			MaAction.get_instance()
			
			
			}; 
	 
	
	
	
	

	public ActionBootStrap() {
		
	}

	public static void main(String[] args) {
		
	}

}
