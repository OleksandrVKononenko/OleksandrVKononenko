 
 
 
 
 
 
/** 
* 
*/ 
package ap.uat; 

import java.util.ArrayList; 
import java.util.List; 


/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 22 ???. 2020 ?. 15:20:53 
* Project  name : Organizer 
* Package  name : ap.uat 
* File     name : DataStoreImplObject.java 
* 
*/ 
public class StoreImplObject { 

	 
	public  static List<AtOm> items = new ArrayList<AtOm>(); 
	 
	public  static List<AtOm> selected = new ArrayList<AtOm>(); 
		 
		 
	public static List<AtOm> get_items() { 
		return items; 
	} 

	public static void set_items(List<AtOm> items) { 
		StoreImplObject.items = items; 
	} 

	public static List<AtOm> get_selected() { 
		return selected; 
	} 

	public static void set_selected(List<AtOm> selected) { 
		StoreImplObject.selected = selected; 
	} 

	public StoreImplObject() { 
	 
	} 

	public static StoreImplObject get_instance() { 
		 
		return new StoreImplObject(); 
	} 
	 
	public static void main(String[] args) { 
	 

	} 

} 
