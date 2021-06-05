 
 
 
 
 
 
 
 
 
 
 
 
package ap.swing; 

import java.awt.Component; 

import ap.gen.IDGen; 

public interface IIdentity { 
	 
		 
		public int getId(); 
		 
		public void setId(int id); 
		 
		 
		public int getPid(); 
		 
		public void setPid(int pid); 
		 
		 
		public void setIndex(int index); 
		 
		public int getIndex(); 
		 
				 
		public void setName(String name); 
		 
		public String getName(); 
		 
		 
		public void setOwner(PanelXml comp); 
		 
		public PanelXml getOwner(); 
		 
		 
		public void setLevel(int level); 
		 
		public int getLevel(); 
		 
		 
} 
