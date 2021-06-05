package ap.mercury.components;

import java.util.LinkedHashMap;
import java.util.Map;
import ap.global.gl;
import ap.mercury.intf.IStore;

public class Store implements IStore{

	private String 				data;
	
	private Map<String,String> 	map;
	
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Store() {
		
		map 	= new LinkedHashMap<String,String>();
		
	}
	
	public Store(String data) {
		
		this();
		
		this.setData(data);
		
	}
	
	public boolean put(String key,String value) {

		return put(this.getMap(),key, value);
		
	}
	

	public String get(String key) {

		return this.getMap().get(key);
		
	}

	public boolean read() {

		this.getMap().clear();
		
		this.getMap().putAll(read(this.getData()));
		
		return (this.getMap().size() != gl.E_EMPTY);
		
	}
	
	public boolean remove(String key) {

		if(this.getMap().containsKey(key))
		this.getMap().remove(key);
		
		return !this.getMap().containsKey(key);
	}
	

	public String toString()
	{
		return toString(this.getMap());
	}

	
	public static Store get_instance() {
		
		return new Store();
	}
	
	public static Store get_instance(String data) {
		
		return new Store(data);
	}

	public static void main(String[] args) {
		
		Store store = Store.get_instance();
		
		for(int i=0;i<10;i++)
		store.put(gl.sf("%d",i),gl.sf("Value_%d",i));
		
		String data = store.toString();
				
		gl.sfn("Get...[%s]",data);
		
		store.setData(data);
		
		store.read();
		
		store.remove(gl.sf("%d",1));
		
		store.put("NewKey","NewValue");
		
		gl.sfn("Get/Read...[%s]",store.toString());
		
		
		

	}

}
