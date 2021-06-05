 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

import java.beans.XMLDecoder; 
import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.Serializable; 
import java.util.Map; 
import java.util.Vector; 

public class ACollector<T> implements IXml,Serializable { 

	 
	private static final long serialVersionUID = 1L; 
	 
	public int nextid; 
	 
	public String home; 

	public Vector<T> data = null; 
	 
	//public Map<String,T> data = null; 

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

	public ACollector() { 

	} 

	public ACollector(String phome) { 
		this(); 

		this.setHome(phome); 

	} 

	public int getNextId() 
	{ 
		 
		int max_id = gl.E_ERROR; 
		 
		return 1; 
		 
		 
	} 
	 
	 
	public boolean load() { 

		if (this.getHome() == null) 
			return false; 

		try { 

			FileInputStream fis = new FileInputStream(this.getHome()); 

			BufferedInputStream bis = new BufferedInputStream(fis); 

			java.beans.XMLDecoder xmlDecoder = new XMLDecoder(bis); 

			Vector<T> data = (Vector<T>) xmlDecoder.readObject(); 

			this.setData(data); 

			if (data != null && data.size() != 0) 
				return true; 

		} catch (FileNotFoundException e) { 
 
			return false; 
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

		// gl.smn("Vector : " + users.size()); 

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

	public static void main(String[] args) { 
		 
	    String home = System.getProperty("user.dir") + "\\collect.xml"; 
		 
		ACollector<String> cs = new ACollector<String>(home); 
		 
		if(cs != null) 
		{ 
			cs.add("Test 1"); 
			 
			if(cs.write()) 
			gl.smn("Ok."); 
			 
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
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
