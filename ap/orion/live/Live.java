package ap.orion.live;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import ap.global.gl;
import ap.orion.Orion;
import ap.orion.app.Application;


public class Live {

	
	// Default type.
	public static String  brew_type  = "None";
	
	// Header type.
	public static String  header_type = "None";
	
	
	// Primary index.
	public static List<String>  types = Arrays.asList(new String[] 
			{
					"None",
					"Configuration",
					"CommandArea",
					"Label",
					"Panel",
					"ScrollablePanel",
					"CheckBox",
					"RadioButton",
					"PasswordField",
					"TextField",
					"TextArea",
					"ComboBox",
					"Button",
					"ToggleButton",
					"List",
					"Tree",
					"SliderV",
					"SliderH",
					"Spinner",
					"Table"
					
			});
	
	public static Map<String,LiveBean> lives = new HashMap<String,LiveBean>(); 
	
	public static JComponent take_a_live(String clazz)
	{
		return LiveBean.get_instance(clazz).getComponent();
	}
	
	public static String indexOf(int index)
	{
		return types.get(index);
	}

	public static int indexOf(String value)
	{
		return types.indexOf(value);
	}

	public static List<Orion> get_primary_set()
	{
			List<Orion> primary_set = new ArrayList<Orion>();
		
		types.forEach(t->
		{	
			primary_set.add(Orion.get_instance(t));

		});
		
			return primary_set;
	}
	
	
	public static List<Orion> get_custom_set(int count)
	{
			List<Orion> primary_set = new ArrayList<Orion>();
		
						primary_set.add(Orion.get_instance("CommandArea"));
			
			Orion 		main =   Orion.get_instance("Panel");
			
						main.setPreferredSize(Application.getDio().get_desk_top().getPreferredSize());
			
						primary_set.add(main);
			
						return primary_set;
	}
}
