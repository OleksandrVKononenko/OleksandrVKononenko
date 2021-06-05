package ap.mercury.intf;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.utils.Su;

public interface IStore {
	
	default
	public boolean put(Map<String,String> store,String key,String value) {

		store.put(key, value);
		
		return store.get(key).equalsIgnoreCase(value);
		
	}
	
	default
	public Map<String,String> read(String data) {

		List<String> 		li 		= Su.get_as_list_for_data(data);
		
		Map<String,String> 	map 	= new LinkedHashMap<String,String>();
		
		if(li == null)
			return map;
		
		li.forEach(a->{
			
			String key 		= Su.BeforeAtFirst(a, Parser.FIELD_DLM);
			
			String value 	= Su.AfterAtFirst(a,gl.sf("%s",Parser.FIELD_DLM));
			
			map.put(key, value);
			
		});
		
			return map;
	}
	

	default
	public String toString(Map<String,String> 	map) {
	
		
		StringBuilder sb = new StringBuilder();
		
		int [] index = {1};
		
		gl.sfn("Map size...[%d]",map.size());
		
		map.forEach( (k,v) -> {
			
			sb.append(gl.sf("%s%s%s%s",k,Parser.FIELD_DLM,v,index[0] == map.size() ? "" :Parser.ITEMS_DLM));
		
			index[0]++;
			
		});
		
			return sb.toString();
	}


}
