package ap.orion.live;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import ap.mercury.components.Button;
import ap.mercury.components.CheckBox;
import ap.mercury.components.Cmd;
import ap.mercury.components.ComboBox;
import ap.mercury.components.Label;
import ap.mercury.components.List;
import ap.mercury.components.RadioButton;
import ap.mercury.components.SliderH;
import ap.mercury.components.SliderV;
import ap.mercury.components.ToggleButton;
import ap.orion.components.ScrollablePanel;
import ap.shape.Ru;

public class LiveBean {
	
	private String clazz;
	
	private JComponent component;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JComponent component) {
		this.component = component;
	}
	
	public LiveBean()
	{
		
	}
	
	public LiveBean(String clazz,JComponent component)
	{
		this.setClazz(clazz);
		
		this.setComponent(component);
	}
	
	public static LiveBean get_instance(String clazz)
	{
		if(clazz.equalsIgnoreCase("Panel"))
			return new LiveBean(clazz,new JPanel());
		
		else if(clazz.equalsIgnoreCase("CheckBox"))
			return new LiveBean(clazz,new CheckBox());
		
		else if(clazz.equalsIgnoreCase("RadioButton"))
			return new LiveBean(clazz,new RadioButton());
		
		else if(clazz.equalsIgnoreCase("Button"))
			return new LiveBean(clazz,new Button());
		
		else if(clazz.equalsIgnoreCase("ToggleButton"))
			return new LiveBean(clazz,new ToggleButton());
		
		else if(clazz.equalsIgnoreCase("Label"))
			return new LiveBean(clazz,new Label());
		
		else if(clazz.equalsIgnoreCase("TextField"))
			return new LiveBean(clazz,new JTextField());
		
		else if(clazz.equalsIgnoreCase("PasswordField"))
			return new LiveBean(clazz,new JPasswordField());
		
		else if(clazz.equalsIgnoreCase("List"))
			return new LiveBean(clazz,new List<String>());
		
		else if(clazz.equalsIgnoreCase("Tree"))
			return new LiveBean(clazz,new JTree());
		
		else if(clazz.equalsIgnoreCase("TextArea"))
			return new LiveBean(clazz,new JTextArea());
		
		else if(clazz.equalsIgnoreCase("ComboBox"))
			return new LiveBean(clazz,new ComboBox<String>());
		
		else if(clazz.equalsIgnoreCase("SliderV"))
			return new LiveBean(clazz,new SliderV(JSlider.VERTICAL,0,100,50));
		
		else if(clazz.equalsIgnoreCase("SliderH"))
			return new LiveBean(clazz,new SliderH(JSlider.HORIZONTAL,0,100,50));
		
		else if(clazz.equalsIgnoreCase("Spinner"))
			return new LiveBean(clazz,new JSpinner());
		
		else if(clazz.equalsIgnoreCase("Table"))
			return new LiveBean(clazz,new JTable());
		
		else if(clazz.equalsIgnoreCase("CommandArea"))
			return new LiveBean(clazz,Cmd.get_instance());
		
		else if(clazz.equalsIgnoreCase("ScrollablePanel"))
			return new LiveBean(clazz,ScrollablePanel.get_instance(Ru.get_instance()));
		
		
			return null;
			
	}
	

}
