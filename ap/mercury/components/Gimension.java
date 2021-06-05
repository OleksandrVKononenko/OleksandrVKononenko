package ap.mercury.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import ap.global.gl;
import ap.mercury.components.parser.Color;
import ap.orion.cmd.CmdProcess;
import ap.utils.DU;
import ap.utils.MapUtils;
import ap.utils.Su;

@SuppressWarnings("serial")
public class Gimension extends Dimension {

	// Fixed size flag, for grid implementation. 
	
	private String 	raw;
	
	private int 	param_waiting = 3;

	private boolean fixed;
	
	private float 	fat;
	
	
	public static String ATTR_DLM = ",";
	
	public static String FIELD_DLM = ":";
	
	public static String ITEMS_DLM = ";";

	
	private int  	ctor;
	
	public int getCtor() {
		return ctor;
	}

	public void setCtor(int ctor) {
		this.ctor = ctor;
	}

	private Color color_from;
	
	private Color color_to;
	
	public Color getColor_from() {
		return color_from;
	}

	public void setColor_from(Color color_from) {
		this.color_from = color_from;
	}

	public Color getColor_to() {
		return color_to;
	}

	public void setColor_to(Color color_to) {
		this.color_to = color_to;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
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

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
	public boolean isEmpty() {
		return (this.width == gl.E_EMPTY && this.height == gl.E_EMPTY);
	}

	public Gimension() {
		
	}
	
	public Gimension(String payload) {
		
		this.setRaw(payload);
		
		this.parse();
		
		this.setCtor(1);

	}

	public Gimension(String payload,String color_from) {
		
		this(payload);
		
		this.setColor_from(Color.get_instance(color_from));

		this.setCtor(2);

	}
	
	public Gimension(String payload,String color_from,String color_to) {
		
		this(payload,color_from);
		
		this.setColor_to(Color.get_instance(color_to));

		this.setCtor(3);
	}
		
	
	public Gimension(Dimension d) {
		super(d);
		
	}

	public Gimension(int width, int height) {
		super(width, height);
		
	}
	public boolean parse(String payload)
	{
		this.setRaw(payload);
		
		return parse();
	}
	
	public static boolean parse_items(String p_ayload,java.util.List<Gimension> out)
	{

		String payload = p_ayload;
		
		if(!gl.tx(new Object(){},payload != null,gl.sf("Проверка пакета данных...%s",payload)))
			return false;
		
			
		if(payload.trim().length()> gl.E_EMPTY && !payload.contains(FIELD_DLM))
		{
			// Inject dlm.
		 
			payload = gl.sf("%s%s",payload,FIELD_DLM);
			
		}
		
		if(!gl.tx(
				new Object(){},
				(payload.contains(FIELD_DLM)),
				gl.sf("Проверка формата данных...%s...Разделитель...[%s]",
				payload,
				FIELD_DLM
				)))
			return false;
		
		java.util.List<String>    raw_list   =  Su.get_as_list(payload,FIELD_DLM);
		
		if(!gl.tx(
				new Object(){},
				(raw_list.size() > gl.E_EMPTY),
				gl.sf("Проверка первичного парсинга...объектов...%d",
				raw_list.size()
				)))
			return false;
		
		java.util.List<Boolean> state = new ArrayList<Boolean>();
		
		raw_list.forEach(a->{
		
			Gimension  	g 	= Gimension.get_instance(a);
			
			boolean 	bl 	= g.parse();
			
			if(bl)
			{
				out.add(g);
			}
			
				state.add(bl);
			
		});
		
				return !state.contains(false);
	}
	
	public boolean parse()
	{
		
		String payload = this.getRaw();
		
		if(!gl.tx(new Object(){},payload != null,gl.sf("Проверка пакета данных...%s",payload)))
			return false;
		
		Map<String,Integer> check_map = Su.isa_any_of(payload,CmdProcess.delim_switches);
	 	
		String dlm = MapUtils.findKeyByValue(check_map,this.getParam_waiting());
		
		if(!gl.tx(
				new Object(){},
				(check_map.size() > gl.E_EMPTY && dlm != null),
				gl.sf("Проверка формата данных...%s...Разделитель...[%s]...Ожидается полей...%d...В наличии...%d",
				payload,
				dlm,
				this.getParam_waiting()+1,
				check_map.get(ATTR_DLM)+1
				)))
			return false;
		
			String [] arr = payload.split(ATTR_DLM);
			
			int WIDTH = 0,HEIGHT=1,FIXED=2,FAT=3;
			
			this.width  = DU.to_int(arr[WIDTH]) ;
			
			this.height = DU.to_int(arr[HEIGHT]);
			
			this.setFixed(CmdProcess.on_switches.contains(arr[FIXED].toLowerCase()));
			
			this.setFat(DU.to_float(arr[FAT]));
			
			return this.is_valid();
			
	}
	
	public boolean is_valid()
	{
		boolean bl_result = false;
		
		if(this.getCtor() == 1)
		{
			gl.sfn("----> Ctor 1");
			
			bl_result =  (this.width != gl.E_ERROR && this.height != gl.E_ERROR && this.getFat() != -1.0f);
		}
		else if(this.getCtor() == 2)
		{

			bl_result =  (this.width != gl.E_ERROR && this.height != gl.E_ERROR && this.getFat() != -1.0f) && 
					this.getColor_from() != null;
		}	
		else if(this.getCtor() == 3)
		{

			bl_result =  (
					this.width != gl.E_ERROR && this.height != gl.E_ERROR && this.getFat() != -1.0f && 
					this.getColor_from() != null &&
					this.getColor_to() != null
					);
			
		}	
			return bl_result;
	}
	
	public static void test(String gimension) 
	{
		Gimension gim = Gimension.get_instance(gimension); 
					
		gl.tx(new Object(){},
				gim.is_valid(),
				gl.sf("Test of...%s...%s",gimension,gim.is_valid() ? gim.toStringFull() : "null"));
		
	}
	
	public static void test(String gimension,String color_from) 
	{
		Gimension gim = Gimension.get_instance(gimension,color_from); 
					
		gl.tx(new Object(){},
				gim.is_valid(),
				gl.sf("Test of...%s...%s",gimension,gim.is_valid() ? gim.toStringFull() : "null"));
	}
	
	public static void test(String gimension,String color_from,String color_to) 
	{
		Gimension gim = Gimension.get_instance(gimension,color_from,color_to); 
					
		gl.tx(new Object(){},
				gim.is_valid(),
				gl.sf("Test of...%s...%s",gimension,gim.is_valid() ? gim.toStringFull() : "null"));
	}
	
	

	public static void test_items(String payload) 
	{
		java.util.List<Gimension> list  = new ArrayList<Gimension>();
		
		boolean result = Gimension.parse_items(payload,list);
				
		if(!gl.tx(new Object(){},
				result,
				gl.sf("Test of...%s...list_size...%d...toString()...%s",
				payload,
				list.size(),
				toString(list)
				))
		   );
		
	}

	public static Gimension get_instance(String payload) {
		
		Gimension  g = new Gimension(payload);
		
		return g.is_valid() ? g : null;
		
	}
	
	
	public static Gimension get_instance(String payload,String color_from) {
		
		Gimension  g = new Gimension(payload,color_from);
		
		return g.is_valid() ? g : null;
		
	}

	public static Gimension get_instance(String payload,String color_from,String color_to) {
		
		Gimension  g = new Gimension(payload,color_from,color_to);
		
		return g.is_valid() ? g : null;
		
	}

	
	
	@Override
	public String toString()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
		return gl.sf("%d,%d,%s,%.2f",this.width,this.height,this.isFixed(),this.getFat());
		
		
	}
	
	public String toStringFull()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
		if(this.getCtor() == 1)
		return gl.sf("%s",this.toString());
		else
		if(this.getCtor() == 2)
		return gl.sf("%s%s%s",this.toString(),Gimension.FIELD_DLM,this.getColor_from().toString());
		else
		if(this.getCtor() == 3)
		return gl.sf("%s%s%s%s%s",
				this.toString(),
				Gimension.FIELD_DLM,
				this.getColor_from().toString(),
				Gimension.FIELD_DLM,
				this.getColor_to().toString()
				);
					
		return "";
	}
	
	public static String toString(java.util.List<Gimension> list)
	{
		StringBuilder sb = new StringBuilder();
		
		list.forEach(a->{
			
			sb.append(a.toString());
			
			sb.append(ITEMS_DLM);
			
		});
		
			return sb.toString();
	}
	
	public static Dimension get_effective_dim(Dimension a,Dimension b)
	{
		return new Dimension(a.width > b.width ? a.width : b.width,a.height > b.height ? a.height : b.height);
	}
	
	public static void main(String[] args) {
		
			// For ctor type 1;
		
			 test("10,10,ага,1:#FF707172");
			
			//test_items("10,10,ага,1.0:11,11,yes,2.0");
		
			//test("10,10,ага,1","#FF707172","200,200,200,100");
	
	}

}
