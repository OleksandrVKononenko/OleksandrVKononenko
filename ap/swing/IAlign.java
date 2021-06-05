package ap.swing; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.Arrays; 
import java.util.List; 

import ap.global.gl; 
import ap.prop.SBounds; 
import ap.shape.Ru; 
import ap.uat.AtOm; 

public interface IAlign { 
	 
	public static final List<String> align = Arrays.asList(new String[]{"NONE","SCROLLBARX","SCROLLBARY","BASE","BASE_ZOOM_POSITION","BASE_ZOOM_ALL","BASE_CAPTION","LEFT_GLUED","LEFT_GLUED_ZOOM","LEFT_TOP","LEFT_BOTTOM","LEFT","TOP","RIGHT","BOTTOM","CENTER","FULL"}); 
	 
	 
	public static int indexOf(String align) 
	{ 
		return IAlign.align.indexOf(align.trim().toUpperCase()); 
	}; 
	 
	 
	public static String indexOf(int align) 
	{ 
		return IAlign.align.get(align); 
	} 
	 
	 
	 
	 
	public static Rectangle get_area(AtOm owner) 
			{ 
		 
				//gl.sfn("Get area...%s",owner.getName()); 
						 
		 		int align_type = owner.getDecoro().get_align(); 
		 		 
		 
				Rectangle rc_par = Ru.norm4g(owner.getDecoro().getParents_bounds()); 
				 
		 		Rectangle rc_res = Ru.get_init_rect(gl.E_EMPTY); 
				 
				Rectangle rc_cli = owner.getDecoro().getBounds(); 
				 
				 
				if(owner.getShift_rect() == null && owner.getShift_dim() == null) 
				{ 
					owner.setShift_rect(rc_cli); 
					 
					Dimension dim = new Dimension(rc_par.width - (rc_cli.x+rc_cli.width),rc_par.height - (rc_cli.y + rc_cli.height)); 
					 
					owner.setShift_dim(dim); 
					
					//gl.sfn("Get area(shift)...%s",owner.getName()); 
					 
				} 
				 
				 
				// Common section for zoom s. 
				AtOm p  =  owner.getIdo().getOwner(); 
				 
				double  m_zoom_width =  gl.E_OK; 
				 
				if(p != null) 
				m_zoom_width = p.getDecoro().getZoom_width(); 
				 
				double m_delta_x = owner.getShift_rect().x * m_zoom_width; 
			 
				double  m_zoom_height =  gl.E_OK; 
				 
				if(p != null) 
					m_zoom_height = p.getDecoro().getZoom_height(); 
				else
					gl.smn("---> Null.");
				 
				double m_delta_y = owner.getShift_rect().y * m_zoom_height; 
				 
				 
				double m_delta_width = owner.getShift_rect().width * m_zoom_width; 
				 
				double m_delta_height = owner.getShift_rect().height * m_zoom_height; 
				
				//gl.sfn("Get area for...%s... %.4f... %.4f",owner.getIdo().getName(),m_zoom_width,m_zoom_height);
				 
				if(align_type == indexOf("SCROLLBARX")) 
				{ 	 
					// Push to bottom by default. 
						 
					rc_res.x = rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = (rc_par.x + rc_par.height) - rc_cli.height; 
					 
					rc_res.width = (int)m_delta_width; 
							 
					rc_res.height = rc_cli.height; 
					 
					 
				} else if(align_type == indexOf("SCROLLBARY")) 
				{ 	 
					// Push to right by default. 
					 
					rc_res.x = (rc_par.x + rc_par.width) - rc_cli.width;//rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = rc_par.y + (int)m_delta_y ; 
					 
					rc_res.width = rc_cli.width; 
							 
					rc_res.height = (int)m_delta_height; 
					 
					 
				} 
				else if(align_type == indexOf("BASE")) 
				{ 	 
					// No zoomed at all. 
					// Height no zoomed. 
					 
					rc_res.x = rc_cli.x ; 
					 
					rc_res.y = rc_cli.y; 
					 
					rc_res.width = rc_cli.width; 
					 
					rc_res.height = rc_cli.height; 
				 
				} 
				 
				else if(align_type == indexOf("BASE_ZOOM_POSITION")) 
				{ 	 
					// Zoomed only position. 

					rc_res.x = rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = rc_par.y + (int)m_delta_y ; 
					 
					rc_res.width = rc_cli.width; 
					 
					rc_res.height = rc_cli.height; 
				 
				} 
				 
				else if(align_type == indexOf("BASE_ZOOM_ALL")) 
				{ 	 
					 
					rc_res.x = rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = rc_par.y + (int)m_delta_y ; 
					 
					rc_res.width = (int)m_delta_width; 
							 
					rc_res.height = (int)m_delta_height; 
					 
				} 
				else if(align_type == indexOf("BASE_CAPTION")) 
				{ 	 
					 
					rc_res.x = rc_par.x + owner.getShift_rect().x; 
					 
					rc_res.y = rc_par.y + owner.getShift_rect().y; 
					 
					rc_res.width = rc_par.width - (owner.getShift_rect().x *2); 
					 
					rc_res.height = rc_cli.height; 
					 
					 
				} 
				 
				 
				else if(align_type == indexOf("LEFT_GLUED")) 
				{ 	 
							 
					 
					rc_res.x = rc_par.x; 
					 
					rc_res.y = rc_par.y; 
					 
										 
					rc_res.width = rc_cli.width; 
					 
					rc_res.height = rc_par.height; 
					 
					 
				} else if(align_type == indexOf("LEFT_GLUED_ZOOM")) 
				{ 	 
							 
					 
					rc_res.x = rc_par.x; 
					 
					rc_res.y = rc_par.y; 
					 
					 
					double m_w_1 = owner.getShift_rect().width; 
					 
					double m_w_2 = (m_w_1 * m_zoom_width); 
					 
										 
					rc_res.width = (int)m_w_2; 
					 
					rc_res.height = rc_par.height; 
					 
					 
				} 
				 
				 
				else if(align_type == indexOf("LEFT_BOTTOM")) 
				{ 	 
							 
					 
					rc_res.x = rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = rc_par.y + (int)m_delta_y ; 
					 
					 
					double m_w_1 = owner.getShift_rect().width; 
					 
					double m_w_2 = (m_w_1 * m_zoom_width); 
						 
										 
					rc_res.width = (int)m_w_2; 
					 
					rc_res.height = rc_par.height - rc_cli.y; 
					 
					 
				} else if(align_type == indexOf("LEFT_TOP")) 
				{ 	 
							 
					 
					rc_res.x = rc_par.x + (int)m_delta_x ; 
					 
					rc_res.y = rc_par.y; 
					 
					 
					double m_w_1 = owner.getShift_rect().width; 
					 
					double m_w_2 = (m_w_1 * m_zoom_width); 
						 
										 
					rc_res.width = (int)m_w_2; 
					 
					rc_res.height = rc_cli.height; 
					 
					 
				} else if(align_type == indexOf("RIGHT")) 
				{ 
						 
						m_delta_x =  (owner.getShift_dim().width * m_zoom_width ); 
					 
						 
						rc_res.x = (rc_par.width - rc_cli.width) - (int)m_delta_x; 
						 
						rc_res.y = rc_par.y + (int)m_delta_y ; 
						 
					 
						double m_w_1 = owner.getShift_rect().width; 
						 
						double m_w_2 = (m_w_1 * m_zoom_width); 
							 

						rc_res.width = (int)m_w_2; 
						 
						rc_res.height = rc_cli.height; 
						 
						 
					} else if(align_type == indexOf("NONE")) 
					{ 
						rc_res.setBounds(rc_cli); 
					} 
				 
						return rc_res; 
					 
		 
			} 
			 
	public static Rectangle get_area_(AtOm owner) 
	{ 
 
		gl.sfn("Get area...%s",owner.getName()); 
				 
		int align_type = owner.getDecoro().get_align(); 
		 
 
		Rectangle rc_par = Ru.norm4g(owner.getDecoro().getParents_bounds()); 
		 
		Rectangle rc_res = Ru.get_init_rect(gl.E_EMPTY); 
		 
		Rectangle rc_cli = owner.getDecoro().getBounds(); 
		 
		 
		if(owner.getShift_rect() == null && owner.getShift_dim() == null) 
		{ 
			owner.setShift_rect(rc_cli); 
			 
			Dimension dim = new Dimension(rc_par.width - (rc_cli.x+rc_cli.width),rc_par.height - (rc_cli.y + rc_cli.height)); 
			 
			owner.setShift_dim(dim); 
			 
		} 
		 
		 
		// Common section for zoom s. 
		AtOm p  =  owner.getIdo().getOwner(); 
		 
		double  m_zoom_width =  gl.E_OK; 
		 
		if(p != null) 
		m_zoom_width = p.getDecoro().getZoom_width(); 
		 
		double m_delta_x = owner.getShift_rect().x * m_zoom_width; 
	 
		double  m_zoom_height =  gl.E_OK; 
		 
		if(p != null) 
			m_zoom_height = p.getDecoro().getZoom_height(); 
		 
		 
		double m_delta_y = owner.getShift_rect().y * m_zoom_height; 
		 
		 
		double m_delta_width = owner.getShift_rect().width * m_zoom_width; 
		 
		double m_delta_height = owner.getShift_rect().height * m_zoom_height; 
	 
		 
		if(align_type == indexOf("SCROLLBARX")) 
		{ 	 
			// Push to bottom by default. 
				 
			rc_res.x = rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = (rc_par.x + rc_par.height) - rc_cli.height; 
			 
			rc_res.width = (int)m_delta_width; 
					 
			rc_res.height = rc_cli.height; 
			 
			 
		} else if(align_type == indexOf("SCROLLBARY")) 
		{ 	 
			// Push to right by default. 
			 
			rc_res.x = (rc_par.x + rc_par.width) - rc_cli.width;//rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = rc_par.y + (int)m_delta_y ; 
			 
			rc_res.width = rc_cli.width; 
					 
			rc_res.height = (int)m_delta_height; 
			 
			 
		} 
		else if(align_type == indexOf("BASE")) 
		{ 	 
			// No zoomed at all. 
			// Height no zoomed. 
			 
			rc_res.x = rc_cli.x ; 
			 
			rc_res.y = rc_cli.y; 
			 
			rc_res.width = rc_cli.width; 
			 
			rc_res.height = rc_cli.height; 
		 
		} 
		 
		else if(align_type == indexOf("BASE_ZOOM_POSITION")) 
		{ 	 
			// Zoomed only position. 

			rc_res.x = rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = rc_par.y + (int)m_delta_y ; 
			 
			rc_res.width = rc_cli.width; 
			 
			rc_res.height = rc_cli.height; 
		 
		} 
		 
		else if(align_type == indexOf("BASE_ZOOM_ALL")) 
		{ 	 
			 
			rc_res.x = rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = rc_par.y + (int)m_delta_y ; 
			 
			rc_res.width = (int)m_delta_width; 
					 
			rc_res.height = (int)m_delta_height; 
			 
		} 
		else if(align_type == indexOf("BASE_CAPTION")) 
		{ 	 
			 
			rc_res.x = rc_par.x + owner.getShift_rect().x; 
			 
			rc_res.y = rc_par.y + owner.getShift_rect().y; 
			 
			rc_res.width = rc_par.width - (owner.getShift_rect().x *2); 
			 
			rc_res.height = rc_cli.height; 
			 
			 
		} 
		 
		 
		else if(align_type == indexOf("LEFT_GLUED")) 
		{ 	 
					 
			 
			rc_res.x = rc_par.x; 
			 
			rc_res.y = rc_par.y; 
			 
								 
			rc_res.width = rc_cli.width; 
			 
			rc_res.height = rc_par.height; 
			 
			 
		} else if(align_type == indexOf("LEFT_GLUED_ZOOM")) 
		{ 	 
					 
			 
			rc_res.x = rc_par.x; 
			 
			rc_res.y = rc_par.y; 
			 
			 
			double m_w_1 = owner.getShift_rect().width; 
			 
			double m_w_2 = (m_w_1 * m_zoom_width); 
			 
								 
			rc_res.width = (int)m_w_2; 
			 
			rc_res.height = rc_par.height; 
			 
			 
		} 
		 
		 
		else if(align_type == indexOf("LEFT_BOTTOM")) 
		{ 	 
					 
			 
			rc_res.x = rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = rc_par.y + (int)m_delta_y ; 
			 
			 
			double m_w_1 = owner.getShift_rect().width; 
			 
			double m_w_2 = (m_w_1 * m_zoom_width); 
				 
								 
			rc_res.width = (int)m_w_2; 
			 
			rc_res.height = rc_par.height - rc_cli.y; 
			 
			 
		} else if(align_type == indexOf("LEFT_TOP")) 
		{ 	 
					 
			 
			rc_res.x = rc_par.x + (int)m_delta_x ; 
			 
			rc_res.y = rc_par.y;//rc_par.y + (int)m_delta_y ; 
			 
			 
			double m_w_1 = owner.getShift_rect().width; 
			 
			double m_w_2 = (m_w_1 * m_zoom_width); 
				 
								 
			rc_res.width = (int)m_w_2; 
			 
			rc_res.height = rc_cli.height; 
			 
			 
		} else if(align_type == indexOf("RIGHT")) 
		{ 
				 
				m_delta_x =  (owner.getShift_dim().width * m_zoom_width ); 
			 
				 
				rc_res.x = (rc_par.width - rc_cli.width) - (int)m_delta_x; 
				 
				rc_res.y = rc_par.y + (int)m_delta_y ; 
				 
			 
				double m_w_1 = owner.getShift_rect().width; 
				 
				double m_w_2 = (m_w_1 * m_zoom_width); 
					 

				rc_res.width = (int)m_w_2; 
				 
				rc_res.height = rc_cli.height; 
				 
				 
			} else if(align_type == indexOf("NONE")) 
			{ 
				rc_res.setBounds(rc_cli); 
			} 
		 
				return rc_res; 
			 
 
	} 
	 
	 
} 
