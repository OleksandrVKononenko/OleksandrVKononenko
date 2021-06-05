package ap.mercury.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import ap.global.gl;
import ap.orion.cmd.CmdProcess;
import ap.utils.Bau;
import ap.utils.DU;
import ap.utils.MapUtils;
import ap.utils.Su;

public class Golor {
	

	private String raw;
	
	private int param_waiting = 4;
	
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public boolean setColor(Color color) {
		this.color = color;
		
		return (this.color != null);
	}

	public int getParam_waiting() {
		return param_waiting;
	}

	public void setParam_waiting(int param_waiting) {
		this.param_waiting = param_waiting;
	}
	
	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}
	
	public Golor()
	{
		
	}
	
	public Golor(String payload)
	{
		this.setRaw(payload);
		
		this.parse();
	}
	
	public boolean parse(String payload)
	{
		this.setRaw(payload);
		
		return parse();
	}
	
	public boolean parse()
	{
		
		String payload = this.getRaw();
		
		if(!gl.tx(new Object(){},payload != null,gl.sf("Проверка пакета данных...%s",payload)))
			return false;
		

		// Check of HTML
		
		if(     
				payload.startsWith("#") && 
				payload.trim().length() == 9 && 
				Su.isa_any_of(Su.AfterAt(payload,"#"),CmdProcess.delim_switches).size() == gl.E_EMPTY &&
				gl.tx(new Object(){},this.setColor(Golor.html_to_color(payload)),gl.sf("Установка HTML...%s",payload))
		  )
		  {
				return false;
		  }
		
		
		Map<String,Integer> check_map = Su.isa_any_of(payload,CmdProcess.delim_switches);
		
		String dlm = MapUtils.findKeyByValue(check_map,this.getParam_waiting()-1);
		
		if(!gl.tx(
				new Object(){},
				(check_map.size() > gl.E_EMPTY && dlm != null),
				gl.sf("Проверка формата данных...%s...Разделитель...[%s]...Ожидается полей...%d...В наличии...%d",
				payload,
				dlm,
				this.getParam_waiting(),
				check_map.get(",")+1
				)))
			return false;
		
			String [] arr = payload.split(dlm);
			
			int RED = 0, GREEN = 1, BLUE = 2, ALPHA = 3; 

			
			int r = DU.to_int(arr[RED]) ;
			
			int g = DU.to_int(arr[GREEN]) ;

			int b = DU.to_int(arr[BLUE]) ;

			int a = DU.to_int(arr[ALPHA]) ;
			
			if(!gl.tx(
					new Object(){},
					(r != gl.E_ERROR && g != gl.E_ERROR && b != gl.E_ERROR && a != gl.E_ERROR),
					gl.sf("Финальная проверка преобразований в тип...%s...%s",
					payload,
					this.getColor()
					)))
			{
				return false;
			}
			
				try
				{
					this.setColor(new Color(r,g,b,a));
					
				}
				catch(Exception e)
				{
					return false;
				}
				
					return true;
			
	}


	public static Golor get_instance(String payload)
	{
		  			return new Golor(payload);	
	}
	
	public static void test(String payload) 
	{
		Golor golor = Golor.get_instance(payload); 
				
		gl.tx(new Object(){},golor.getColor() != null,gl.sf("Test of...%s...%s...html...%s...from html...%s",
				payload,
				golor.toString(),
				Golor.to_html_color(golor.getColor()),
				Golor.toString(Golor.html_to_color(Golor.to_html_color(golor.getColor())))
				));
		
	}
	
	public String toString()
	{
		return toString(this.getColor());
	}
	
	public static String toString(Color color) 
	{ 
		return String.format("%d,%d,%d,%d", 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				color.getAlpha()); 
	} 
	
	public static String to_html_color(Color color) 
	{ 
		return String.format("#%s%s%s%s", 
				Bau.to_hex_str_from_int(color.getRed()), 
						Bau.to_hex_str_from_int(color.getGreen()), 
								Bau.to_hex_str_from_int(color.getBlue()), 
										Bau.to_hex_str_from_int(color.getAlpha()) 
						); 
	} 
	
	public static Color html_to_color(String color) 
	{ 
		return get_alpha_color(Color.decode(color.substring(0,7)),Bau.to_int_from_hex_str(color.substring(7,9))); 
	} 
	
	public static Color get_alpha_color(Color color, int alpha) 
	{ 
		return new Color( 
				 
				color.getRed(), 
				color.getGreen(), 
				color.getBlue(), 
				alpha 
				 
				); 

	} 
	
	public static String toString(java.util.List<Golor> list)
	{
		StringBuilder sb = new StringBuilder();
		
		list.forEach(a->{
			
			sb.append(a.toString());
			
			sb.append(Gimension.FIELD_DLM);
			
		});
		
			return sb.toString();
	}
	
	public static boolean parse_items(String p_ayload,java.util.List<Golor> out)
	{

		String payload = p_ayload;
		
		if(!gl.tx(new Object(){},payload != null,gl.sf("Нет данных...%s",payload)))
			return false;
		
			
		if(payload.trim().length()> gl.E_EMPTY && !payload.contains(Gimension.FIELD_DLM))
		{
			// Inject dlm.
		 
			payload = gl.sf("%s%s",payload,Gimension.FIELD_DLM);
			
		}
		
		if(!gl.tx(
				new Object(){},
				(payload.contains(Gimension.FIELD_DLM)),
				gl.sf("Проверка формата данных...%s...Разделитель...[%s]",
				payload,
				Gimension.FIELD_DLM
				)))
			return false;
		
		java.util.List<String>    raw_list   =  Su.get_as_list(payload,Gimension.FIELD_DLM);
		
		if(!gl.tx(
				new Object(){},
				(raw_list.size() > gl.E_EMPTY),
				gl.sf("Проверка первичного парсинга...объектов...%d",
				raw_list.size()
				)))
			return false;
		
		java.util.List<Boolean> state = new ArrayList<Boolean>();
		
		raw_list.forEach(a->{
		
			Golor  	g 	= Golor.get_instance(a);
			
			boolean 	bl 	= g.getColor() != null;
			
			if(bl)
			{
				out.add(g);
			}
			
				state.add(bl);
			
		});
		
				return !state.contains(false);
	}
	
	public static void test_items(String payload) 
	{
		java.util.List<Golor> list  = new ArrayList<Golor>();
		
		boolean result = Golor.parse_items(payload,list);
				
		if(!gl.tx(new Object(){},
				result,
				gl.sf("Test of...%s...list_size...%d...toString()...%s",
				payload,
				list.size(),
				toString(list)
				))
		   );
		
	}
	
	public static void main(String[] args) {
		
			//test("111,112,113,114");
			
			//test("#6F707172");
		
			//test("#FFFFFFFF");
		
			test_items("#6F707172;111,112,113,114;#6F707172;111,112,113,114;#6F707172;");
			
	}

}
