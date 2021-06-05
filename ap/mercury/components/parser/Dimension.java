package ap.mercury.components.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import ap.global.gl;
import ap.mercury.components.Gimension;
import ap.orion.cmd.CmdProcess;
import ap.utils.DU;
import ap.utils.Su;

public class Dimension extends Parser {

	private java.awt.Dimension 	dimension;
	
	private boolean fixed;
	
	private float 	fat;
	
	private int  	ctor;
	
	private java.awt.Color 	color_from;
	
	private java.awt.Color 	color_to;
	
	
	public java.awt.Color getColor_from() {
		return color_from;
	}

	public void setColor_from(java.awt.Color color_from) {
		this.color_from = color_from;
	}

	public java.awt.Color getColor_to() {
		return color_to;
	}

	public void setColor_to(java.awt.Color color_to) {
		this.color_to = color_to;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}


	public int getCtor() {
		return ctor;
	}

	public void setCtor(int ctor) {
		this.ctor = ctor;
	}
		
	public java.awt.Dimension getDimension() {
		return dimension;
	}

	public void setDimension(java.awt.Dimension dimension) {
		this.dimension = dimension;
	}

	public Dimension() {
		
		super();
		
	}

	// Only dimension.
	
	public Dimension(String payload) {
	
		super(payload.contains(gl.sf("%s",Parser.FIELD_DLM)) ? Su.BeforeAtFirst(payload,gl.sf("%s",Parser.FIELD_DLM)) : payload);
		
		if(payload.contains(gl.sf("%s",Parser.FIELD_DLM)))
		{
			String []  arr = payload.split(gl.sf("%s",Parser.FIELD_DLM));
			
			
			Arrays.asList(arr).forEach(a->
			{
				gl.tx_k(new Object(){},gl.sf("Конструктор...%s",a));
			});
			
			
			if(arr.length == 2)
			{	
				
				this.setColor_from(Color.get_instance(arr[1]).getColor());
				
			}
			else 
				if(arr.length == 3)
				{	
					//Color c = Color.get_instance(arr[1]);
				
					//gl.sfn("Under debug...%d...%s...state...%d",arr.length,arr[1],c.getState());
				
					this.setColor_from(Color.get_instance(arr[1]).getColor());
					
					//Color c1 = Color.get_instance(arr[2]);
					
					//gl.sfn("Under debug...%d...%s...state...%d",arr.length,arr[2],c1.getState());
				
					this.setColor_to(Color.get_instance(arr[2]).getColor());
				}
				
		}
	}
	
	
	
	public int accept()
	{
		if(this.getRaw_list().size() == 2)
		{
		
			try
			{
				this.setCtor(2);
				
				this.setDimension(new java.awt.Dimension(
						DU.to_int(this.getRaw_list().get(0)),
						DU.to_int(this.getRaw_list().get(1))
						));
				
				return  this.getDimension().width >= gl.E_EMPTY && this.getDimension().height >= gl.E_EMPTY ? gl.E_OK : gl.E_ERROR ;
			}
			catch(Exception e)
			{
				return gl.E_ERROR;
			}
		} else
			if(this.getRaw_list().size() == 3)
			{
			
				try
				{
					this.setCtor(3);
					
					this.setDimension(new java.awt.Dimension(
							DU.to_int(this.getRaw_list().get(0)),
							DU.to_int(this.getRaw_list().get(1))
							));
					
					this.setFixed(CmdProcess.on_switches.contains(this.getRaw_list().get(2)));
					
					return  this.getDimension().width  >= gl.E_EMPTY  && 
							this.getDimension().height >= gl.E_EMPTY 
							? gl.E_OK : gl.E_ERROR ;
				}
				catch(Exception e)
				{
					return gl.E_ERROR;
				}
			}
		else 
			if(this.getRaw_list().size() == 4)
				{
				
					try
					{
						this.setCtor(4);
						
						this.setDimension(new java.awt.Dimension(
								DU.to_int(this.getRaw_list().get(0)),
								DU.to_int(this.getRaw_list().get(1))
								));
						
						this.setFixed(CmdProcess.on_switches.contains(this.getRaw_list().get(2)));
						
						this.setFat(DU.to_float(this.getRaw_list().get(3)));
						
						return  this.getDimension().width  >= gl.E_EMPTY  && 
								this.getDimension().height >= gl.E_EMPTY  &&
								this.getFat() != gl.E_ERROR
								? gl.E_OK : gl.E_ERROR ;
					}
					catch(Exception e)
					{
						return gl.E_ERROR;
					}
				}
						return gl.E_ERROR;
				
	}

	public static String init(String payload)
	{
		return payload.trim().equalsIgnoreCase("0") ? 
				gl.sf("%s,%s",payload,payload) : 
				payload.trim().equalsIgnoreCase(gl.sf("%s",Parser.ATTR_DLM)) ? 
				gl.sf("0%s0",gl.sf("%s",Parser.ATTR_DLM)) :
				payload;	
		
	}
	public static Dimension get_instance(String payload) {
		
		// Для обнуления реализуем краткий формат.
		// На базе суже существующей логики для данного класса : Dimension(0,0)
		// Символы 0 ? ',' - инициализаторы.
		
		String m_payload = Dimension.init(payload) ;
		
		Dimension 	c = new Dimension(m_payload);
					
					c.setState(c.accept());
		
		return 		c;
	}
	
	@Override
	public String toString()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
			return gl.sf("%d,%d,%d,%.2f%s%s%s%s", 
						        this.getDimension().width, 
						        this.getDimension().height,
						        this.isFixed() ? 1 : 0,
						        this.getFat(),
						        this.getColor_from() != null ? Parser.FIELD_DLM : "",
						        this.getColor_from() != null ? Color.toString(this.getColor_from()): "",
								this.getColor_to()   != null ? Parser.FIELD_DLM : "",
								this.getColor_to()   != null ? Color.toString(this.getColor_to()): ""
												      		        		
						 ); 
		
	}
	
	public String toString_()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
		String r = "";
		
		if(this.getCtor() == 2)
			r = 	gl.sf("%d,%d", 
					        this.getDimension().width, 
					        this.getDimension().height
					 ); 
		else
		if(this.getCtor() == 3)
			r = 	gl.sf("%d,%d,%d", 
					        this.getDimension().width, 
					        this.getDimension().height,
					        this.isFixed() ? 1 : 0
					 ); 
		else
		if(this.getCtor() == 4)
			r = 	gl.sf("%d,%d,%d,%.2f", 
						        this.getDimension().width, 
						        this.getDimension().height,
						        this.isFixed() ? 1 : 0,
						        this.getFat()
						 ); 
			
		return r;
		
	}
	
	public static String toString(java.util.List<Dimension> list)
	{
		StringBuilder sb = new StringBuilder();
		
		list.forEach(a->{
			
			sb.append(a.toString());
			
			sb.append(ITEMS_DLM);
			
		});
		
			return sb.toString();
	}
	
	public static boolean parse(String payload,java.util.List<Dimension> out)
	{

		if(payload == null)
			return false;
		
		java.util.List<String>    	raw_list   =  Su.get_as_list(payload,gl.sf("%s",ITEMS_DLM));
		
		java.util.List<Boolean> 	state = new ArrayList<Boolean>();
		
		raw_list.forEach(a->{
			
				Dimension d = Dimension.get_instance(a);
				
				out.add(d);
				
				state.add(d.getState() != gl.E_ERROR);
			
		});
		
				return !state.contains(false);
	}
	
	public boolean is_empty() {
		return (this.getDimension().width == gl.E_EMPTY && this.getDimension().height == gl.E_EMPTY);
	}
	
	public static void test(String payload)
	{
		Dimension dimension = Dimension.get_instance(payload);
		
		if( gl.tx_i(new Object(){},dimension.getState() > gl.E_EMPTY,gl.sf("Test of...[%s]...is...",payload)))
		{
			gl.sfn("Result...[%s]", dimension.toString());
		}
		
	}
	

	public static void test_items(String payload)
	{
		java.util.List<Dimension> list = new ArrayList<Dimension>();
		
		if (gl.tx(new Object(){},Dimension.parse(payload,list),gl.sf("Test of...[%s] ",payload)))
		{
			list.forEach(a->
			{
				gl.sfn("Result...[%s]",a.toString());
				
			});
		}
		
		
	}

	public static void main(String[] args) {
		
		// test("#6F707172");
		
		//test("255,254,253,252");
		
		//test("10,10,1,2:~.120:~.200");
		
		//test_items("10,10,1,2:255~.:155~.;10,10,1,2:255~.:~.155;10,10,1,2:255~.:155~.;");
		
		//test(",");
		
		test("10,10,0,5:255.~:~.255");
	}

}

