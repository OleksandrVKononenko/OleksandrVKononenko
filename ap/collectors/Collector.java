 
package ap.collectors; 

import java.beans.XMLDecoder;
import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.Serializable; 
import java.util.Collection; 
import java.util.Vector; 
import ap.global.gl; 
import ap.intf.IXml;

public class Collector<T> implements IXml,Serializable { 

	 
	private static final long serialVersionUID = 1L; 
	 
	public String home; 

	public Vector<T> data = new Vector<T>(); 
	 
	public String getHome() { 
		return home; 
	} 

	public void setHome(String home) { 
		this.home = home; 
	} 

	public Vector<T> getData() { 
		return data; 
	} 

	public void setData(Vector<T> data) { 
		this.data = data; 
	} 

	public Collector() { 

	} 

	public Collector(String phome) { 
		 
		this(); 

		this.setHome(phome); 

	} 

	public int size() 
	{ 
		return this.getData().size(); 
	} 
	 
	@SuppressWarnings("unchecked") 
	public boolean read() { 

		if (this.getHome() == null) 
			return false; 
		 
		 
		java.beans.XMLDecoder xmlDecoder = null; 
		 
		FileInputStream fis = null; 
		 
		BufferedInputStream bis = null; 
				 

		try { 

			fis = new FileInputStream(this.getHome()); 

			bis = new BufferedInputStream(fis); 

			xmlDecoder = new XMLDecoder(bis); 

			data = (Vector<T>) xmlDecoder.readObject(); 

			this.setData(data); 

			if (data != null && data.size() != 0) 
				return true; 

		} catch (FileNotFoundException e) { 
 
			return false; 
		} 
		finally 
		{ 
			if (fis != null) 
				try { 
					fis.close(); 
				} catch (IOException e) { 
				 
					return false; 
				} 
			 
			if (bis != null) 
				try { 
					bis.close(); 
				} catch (IOException e) { 
				 
					return false; 
				} 
			 
			 
			 
			if (xmlDecoder != null) 
				try { 
					xmlDecoder.close(); 
				} catch (Exception e) { 
				 
					return false; 
				} 
						 
		} 
		 
			return false; 
	} 

	public boolean write() { 

		if (this.getHome() == null) 
			return false; 

		FileOutputStream fos = null; 
		
		try { 
			
			fos = new FileOutputStream(this.getHome()); 
			
		} catch (FileNotFoundException e) { 
			 
			return false; 

		} 

		BufferedOutputStream bos = new BufferedOutputStream(fos); 

		java.beans.XMLEncoder xe = new java.beans.XMLEncoder(bos); 

		try { 

			
			xe.writeObject(this.getData()); 

			xe.flush(); 

			xe.close(); 

		} catch (Exception e) { 
			 
			return false; 
		} 

		return true; 

	} 
	
	/*
	protected void initialize(XMLEncoder encoder) {
	    encoder.setPersistenceDelegate(ImmutableList.class, new PersistenceDelegate() {

	        protected boolean mutatesTo(Object oldInstance, Object newInstance) {
	            return oldInstance.equals(newInstance);
	        }

	        protected Expression instantiate(Object oldInstance, Encoder out) {
	            ImmutableList list = (ImmutableList) oldInstance;
	            if (!list.hasEntries()) {
	                return getExpression(oldInstance, ImmutableList.class, "new");
	            }
	            Object object = list.getLast();
	            ImmutableList shortenedList = list.removeLast();
	            return getExpression(oldInstance, shortenedList, "add", object);
	        }

	        private Expression getExpression(Object value, Object target, String method, Object... args) {
	            return new Expression(value, target, method, args);
	        }
	    });
	}
	 */
	
	public void clear() 
	{ 
		if (this.data != null) 
			this.data.clear(); 
			 
	} 
	 
	public boolean add(T value) { 

		if (this.data == null) 
			this.data = new Vector<T>(); 

		try { 
			this.data.add(value); 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
			return true; 
	} 
	 
	public boolean remove(Object o) 
	{ 
		return this.getData().remove(o); 
	} 
	 
	public boolean addAll(Collection<T> value) { 

		if (this.data == null) 
			this.data = new Vector<T>(); 

		try { 
			this.data.addAll(value); 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
			return true; 
	} 

	public static void main(String[] args) { 
		 
	    String home = System.getProperty("user.dir") + "\\collect.xml"; 
		 
		Collector<String> cs = new Collector<String>(home); 
		 
		String testValue = "Some test value."; 
		 
		if(cs != null) 
		{ 
			cs.add(testValue); 
			 
			if(cs.write() && cs.read()) 
			{ 
				if(cs.getData().contains((Object)testValue)) 
					gl.smn("Test Ok."); 
				else 
					gl.smn("Test Error."); 
					 
			} 
		} 
	} 
	 
	class Emulator 
	{ 
		public int getId() 
		{ 
			return 0; 
		} 
	} 
} 
