 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
package ap.mng; 

import java.awt.Rectangle; 
import java.io.Serializable; 
import java.util.HashMap; 
import java.util.Map; 
import ap.collectors.SPanelCollector; 
import ap.frame.TFrame; 


public class SPanelManagerDef implements Serializable { 

	 
	 
	private static final long serialVersionUID = 1L; 

	String file_type = null; 
	 
	SPanelCollector data = null; 
	 
	TFrame frame = null; 

	SPanelCollector added = new SPanelCollector(); 
	 
	SPanelCollector selected = new SPanelCollector(); 
	 
	SPanelCollector deleted = new SPanelCollector(); 
	 
	SPanelCollector selectors = new SPanelCollector(); 
	 
	Map<String,Rectangle>  form_map = new HashMap<String,Rectangle>(); 

	 
	 
	 
	 
} 

// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:45 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:15 
