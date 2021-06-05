package ap.orion.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

import ap.global.gl;
import ap.orion.Orion;

public class ScrollablePanel {


	
	public static ScrollPane get_instance(Rectangle bounds) {
		
		// Это рабочий стол внутри объекта ScrollablePanel.
		
		// Объекты на этом рабочем столе полностью функциональны.
		
		// Но сам рабочий стол НЕ селектируется и НЕ фокусируется.
		
		Orion 		object = Orion.get_instance("Panel",bounds); 
		 			
					object.setPreferredSize(new Dimension(320,240));
					
					// Не перемещается.
					
					object.getRights().set_dragable(false);
					
					// Не фокусируется.
					
					object.getRights().set_focusable(false);
					
					// Не селектируется.
					
					object.getRights().set_selectable(false);
					
					object.getIdo().setIndex(gl.E_EMPTY);
					
					object.getIdo().setLevel(gl.E_EMPTY);
					
					
		ScrollPane 	sp = ScrollPane.get_instance(object);
		
					sp.setBackground(new Color(0,0,0,0));

					return sp;
	}
	

	public static void main(String[] args) {
		

	}

}
