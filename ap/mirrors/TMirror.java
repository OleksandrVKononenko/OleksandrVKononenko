 
 
package ap.mirrors; 

import java.awt.Color; 
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 
import java.util.List; 

import ap.btn.TPanel; 
import ap.global.gl; 
import ap.utils.CU; 
import ap.utils.ImageUtil; 

public class TMirror { 

	public TMirror() { 
		 
	} 
	 
	public static void clear(TPanel owner) 
	{ 
		Object v = new Object(){}; 
		 
		gl.tracex(v, String.format( 
				"Clear mirrors...%3d...%s", owner.getId(), 
				owner.getAction())); 

		owner.getMirrors().forEach(a->{ 
		 
			gl.tracex(v, String.format( 
				"Clea mirror image on object...%3d...%s", a.getId(), 
				a.getAction())); 
		 
			a.setImage(null); 
			 
			a.repaint(); 
			 
		}); 
				return; 
	} 
	public static List<TPanel> update(TPanel owner) 
	{ 
		Object v = new Object(){}; 
		 
		// Looking for of imageReceiver objects. 
					gl.tracex(v, String.format( 
							"Wake up broadcast image from...%3d...%s", owner.getId(), 
							owner.getAction())); 

					List<TPanel> rc_local = owner.getManager().getCollector() 
							.get_list_by_pid_and_action(owner.getId(), "imageReceiver"); 
					 
					 
					 
					BufferedImage mirror[] = {null}; 
					 
					if(rc_local != null && rc_local.size() != gl.E_EMPTY) 
					{ 
						// What color on desktop. 

						Color color = owner.getManager().getFrame().getBackground(); 

						mirror[0] = ImageUtil.get_mirrored_bi(  
								owner, 
								CU.getAlphaColor(color, 128), 
								CU.getAlphaColor(color, 255)
								); 
						  
						 
					} 
					 
					rc_local.forEach(a->{ 
					 
						gl.tracex(v, String.format( 
							"Send to receiver...%3d...%s", a.getId(), 
							a.getAction())); 
						 
						a.setImage(mirror[0]); 
						 
						a.repaint(); 
					 
					}); 
					 
					return rc_local; 
					 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
