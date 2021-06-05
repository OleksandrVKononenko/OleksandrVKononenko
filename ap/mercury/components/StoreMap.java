package ap.mercury.components;

import ap.collectors.Collector;
import ap.global.gl;

public class StoreMap extends Collector<String>{

	private String text;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StoreMap() {
		
	}
	
	@Override
	public boolean read()
	{
		boolean bl_read = super.read();
		
		if(bl_read)
		{
			this.setText(this.getData().get(gl.E_EMPTY));
			
			return true;
		}
		
			return false;
	}
	
	@Override
	public boolean write()
	{
		this.getData().clear();
		
		this.getData().add(this.getText());
		
		return super.write();
	}
	
	public boolean add(String key,String value)
	{
		
		Store store = Store.get_instance(this.getText());
		
		if(!store.read() || !store.put(key,value))
		{
			return false;
		}
			this.setText(store.toString());

			return this.getText().equalsIgnoreCase(store.toString());
	}
	
	public boolean remove(String key)
	{
		
		Store store = Store.get_instance(this.getText());
		
		if(!store.read() || !store.remove(key))
		{
			return false;
		}
		
			gl.sfn("Store text...[%s]", store.toString());
			
			this.setText(store.toString());

			return this.getText().equalsIgnoreCase(store.toString());
	}
	
	public boolean removeAll()
	{
			this.setText("");

			return this.getText().equalsIgnoreCase("");
	}
	
	
	public StoreMap(String home) {
		
		this.setHome(home);
	}
	
	
	public static StoreMap get_instance(String home) {
		
		return new StoreMap(home);

	}

	public static void test_read(String home) {
		
		StoreMap 	sm = StoreMap.get_instance(home);
					
		gl.tx(new Object() {},sm.read(),gl.sf("Чтение...[%s]",sm.getText()));


	}
	
	public static void test_update(String home) {
		
		StoreMap 	sm = StoreMap.get_instance(home);
					
		gl.tx(new Object() {},sm.read(),gl.sf("Чтение...[%s]",sm.getText()));

		gl.tx(new Object() {},sm.add("a2","b2") && sm.write(),gl.sf("Обновление/Запись...[%s]",sm.getText()));

	}


	public static void test_remove(String home,String key) {
		
		StoreMap 	sm = StoreMap.get_instance(home);
					
		gl.tx(new Object() {},sm.read(),gl.sf("Чтение...[%s]",sm.getText()));

		gl.tx(new Object() {},sm.remove(key) && sm.write(),gl.sf("Обновление/Удаление...[%s]",sm.getText()));

	}

	public static void test_write(String home,String data) {
		

		StoreMap 	sm = StoreMap.get_instance(home);
		
					sm.setText(data);
		
		gl.tx(new Object() {},sm.write(),"Запись.");
		
		

	}


	public static void main(String[] args) {
		
		String home = "test.xml";
		
		String data = "abc:123";
		
		//test_write(home,data);
		
		//test_read(home);
		
		// test_update(home);
		
		test_remove(home,"abc");
		
		

	}

}
