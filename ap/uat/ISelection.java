 
/** 
* 
*/ 
package ap.uat; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 

import ap.global.gl; 
import ap.shape.Ru; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 21 ???. 2020 ?. 15:29:09 
* Project  name : Organizer 
* Package  name : ap.uat 
* File     name : ISelection.java 
* 
*/ 
public interface ISelection { 
	 
	public static void update_selector(AtOm owner,MouseEvent e) 
	{ 
		Rectangle rc = Ru.get_instance(owner.getDrago().getStartPos(),e.getPoint()); 
		 
		owner.getSlio().set_selector(rc); 
		 
			 
		owner.update_selected_gather_rect(owner,rc); 
		 
		owner.update_selection_gather(); 
		 
		owner.repaint(); 
		 
	} 
} 
