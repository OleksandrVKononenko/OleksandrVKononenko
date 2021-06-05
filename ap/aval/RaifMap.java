package ap.aval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RaifMap extends HashMap<Integer,String> {

	private static final long serialVersionUID = 1L;
	
	private String [] args;
	
	
	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public RaifMap()
	{
		
	}
	
	public RaifMap(String [] args)
	{
		this.setArgs(args);
	}
	
	
	@Override
	public String toString()
	{
		return this.toString(System.lineSeparator());
	}
	
	public String toString(String row_dlm)
	{
		return this.entrySet()
		         .stream()
		         .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
		         .collect(Collectors.joining(row_dlm));
	}
	
	public static RaifMap getInstance()
	{
		return new RaifMap(); 
	}
	

	public static RaifMap getInstance(String [] args)
	{
		return new RaifMap(args); 
	}
	
	
	public  HashMap<String, Integer> getMirror() {
		
	    return this.getMirror(this);
	}
	
	private  <K,V> HashMap<V,K> getMirror(Map<K,V> map) {
		
	    HashMap<V,K> rev = new HashMap<V, K>();
	    
	    for(Map.Entry<K,V> entry : map.entrySet())
	        rev.put(entry.getValue(), entry.getKey());
	    
	    return rev;
	}
	

	public RaifMap get(String [] args)
	{
		this.setArgs(args);
		
		return get();
	}
	
	public RaifMap get()
	{
		return Arrays.asList(this.getArgs()).stream()
		    .collect(RaifMap::new, (map, s) -> map.put(map.size(),s), Map::putAll);
	}
	
	public RaifCommand isaCommand()
	{
		if(this.containsValue("--p") && this.size() == 2)
			return RaifCommand.PAYLOAD;
		
			return RaifCommand.NONE;
		
	}
	
	public static void main(String[] args) {
		
	}

}
