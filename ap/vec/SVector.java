 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
package ap.vec; 

import java.awt.Rectangle; 
import java.util.Vector; 
import ap.global.gl; 



public class SVector<SPanel> extends Vector<SPanel> { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 

	public SPanel getById(int id) 
	{ 

		for(SPanel a : this ) 
		{ 
			if(((ap.btn.TPanel)a).getId() == id) 
			{ 
				return a; 
				 
			} 
		} 
		 
			return null; 
	} 

	public SPanel getByIndex(int index) 
	{ 

		for(SPanel a : this ) 
		{ 
			if(((ap.btn.TPanel)a).getZorder() == index) 
			{ 
				return a; 
				 
			} 
		} 
		 
			return null; 
	} 
	 
	 
	public Vector<SPanel> getByPid(int pid) 
	{ 

		Vector<SPanel> vc = new Vector<SPanel>(); 
		 
		for(SPanel a : this ) 
		{ 
			if( ((ap.btn.TPanel)a).getPid() == pid) 
			{ 
				vc.add(a); 
			} 
		} 
		 
			return vc; 
	} 
	 
	public SVector<SPanel> getDeleted() 
	{ 
		SVector<SPanel> vc = new SVector<SPanel>(); 
		 
		for(SPanel a : this ) 
		{ 
			if( ((ap.btn.TPanel)a).isDeleted()) 
			{ 
				vc.add(a); 
			} 
		} 
		 
			return vc; 
	} 
	 
	public SPanel getMinArea() 
	{ 
		SPanel jp = null; 
		 
		Rectangle r = new Rectangle(0,0,1000,1000); 
		 
		for(SPanel a : this ) 
		{ 
			Rectangle rect  = ((ap.btn.TPanel)a).getBounds(); 
			 
			if((rect.width < r.width) && (rect.height < r.height)) 
			{ 
				r = rect; 
				 
				jp = a; 
			} 
		} 
		 
			return jp; 
	} 
	 
	 
	public boolean gc() 
	{ 
		 
		SVector<SPanel> deleted = new SVector<SPanel>(); 

		deleted = this.getDeleted(); 

		gl.smn(String.format("<gc>Add to delete %d items.", deleted .size())); 

		this.removeAll(deleted); 

		return !this.containsAll(deleted); 

	} 
	 
	public void show() 
	{ 
		this.forEach(a-> 
		{ 
			System.out.print(" "+((ap.btn.TPanel)a).getId()); 
		} 
		); 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
