 
 
 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.collectors; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 15 ???. 2016 ?. 11:39:33 
* Project  name : Organizer 
* Package  name : ap.collectors 
* File     name : FileInfo.java 
* 
*/ 
public class FileInfo 
	{ 
		private String name = null; 
		 
		private String package_name = null; 
		 
		private String source = null; 
		 
		private String clazz_name = null; 
		 
		private String package_dir = null; 
		 
		 
		/** 
		 * @return the package_dir 
		 */ 
		public String getPackage_dir() { 
			return package_dir; 
		} 

		/** 
		 * @param package_dir the package_dir to set 
		 */ 
		public void setPackage_dir(String package_dir) { 
			this.package_dir = package_dir; 
		} 

		/** 
		 * @return the clazz_name 
		 */ 
		public String getClazz_name() { 
			return clazz_name; 
		} 

		/** 
		 * @param clazz_name the clazz_name to set 
		 */ 
		public void setClazz_name(String clazz_name) { 
			this.clazz_name = clazz_name; 
		} 

		/** 
		 * @return the source 
		 */ 
		public String getSource() { 
			return source; 
		} 

		/** 
		 * @param source the source to set 
		 */ 
		public void setSource(String source) { 
			this.source = source; 
		} 

		/** 
		 * @return the name 
		 */ 
		public String getName() { 
			return name; 
		} 

		/** 
		 * @param name the name to set 
		 */ 
		public void setName(String name) { 
			this.name = name; 
		} 

		/** 
		 * @return the package_name 
		 */ 
		public String getPackage_name() { 
			return package_name; 
		} 

		/** 
		 * @param package_name the package_name to set 
		 */ 
		public void setPackage_name(String package_name) { 
			this.package_name = package_name; 
		} 

		public FileInfo() 
		{ 
			 
		} 
		 
		public FileInfo(String name) 
		{ 
			this.setName(name); 
		} 
				 
		public FileInfo(String name,String package_name) 
		{ 
			this(name); 
			 
			this.setPackage_name(package_name); 
			 
			this.setPackage_dir(package_name.replace(".","\\")); 
			 
			 
		} 

		@Override 
		public String toString() 
		{ 
			return this.getName() + " " + this.getPackage_dir(); 
		} 
		 
		public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:40 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:14 
