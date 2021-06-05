package ap.mercury.components.parser;

import java.util.ArrayList;
import java.util.stream.Collectors;
import ap.global.gl;
import ap.utils.Su;


public class Parser {


	public static String 	HEADER   = "~@~";
	
	public static char 		DILATOR  = '~';
	
	public static char 		ATTR_DLM  = ',';
	
	public static char 		FIELD_DLM = ':';
	
	public static char 		ITEMS_DLM = ';';

	private java.util.List<String> raw_list;
	
	private int state;
	
	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public java.util.List<String> getRaw_list() {
		return raw_list;
	}

	public void setRaw_list(java.util.List<String> raw_list) {
		this.raw_list = raw_list;
	}

	private String payload;
	
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Parser() {
	
		this.setRaw_list(new ArrayList<String>());
	}
	
	
	// Парсер не вносит искажений в данные
	// При необходимости реализуем дополнительную логику 
	// в наследуемых классах.
	
	public Parser(String payload) {
		
		this();
		
		//this.setPayload(!payload.contains(gl.sf("%s",Parser.ATTR_DLM))?gl.sf("%s%s",payload,Parser.ATTR_DLM):payload);
		
		this.setPayload(payload);
		
		this.setState(this.parse());
		
	}
	
	public int parse(String payload)
	{
		this.setPayload(payload);
		
		return this.parse();
	}
	
	public int parse()
	{
		
		this.getRaw_list().addAll(Su.split(this.getPayload(),Parser.ATTR_DLM));
		
		return this.getRaw_list().size();
	}
	
	public static Parser get_instance(String payload) {
		
		return new Parser(payload);
	}
	
	@Override
	public String toString()
	{
		return this.getRaw_list().stream().collect(Collectors.joining(gl.sf("%s",Parser.ATTR_DLM)));
	}

	public static void test(String payload)
	{
		Parser parser = Parser.get_instance(payload);
		
		parser.getRaw_list().forEach(a->{
			
			gl.sfn("Parser...%s",a);
			
		});
		
		if( gl.tx_i(new Object(){},parser.getState() > gl.E_EMPTY,gl.sf("Test of...[%s]...is...",payload)))
		{
			gl.sfn("Result...[%s]...size...[%d]", 
					parser.getRaw_list().stream().collect(Collectors.joining(gl.sf("%s",Parser.ATTR_DLM))),
					parser.getRaw_list().size()
					);
		}
		
	}
	
	public static void main(String[] args) {
		
		//test(",,5,,7,,");
		
		//test("#ff7017273");
		
		//test("255,254,253,252");
		
		// test("fai,ia254,aa253,##252");
		
		test("~.125");
		
		
	}
	
}
