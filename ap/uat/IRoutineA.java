 
 
 
 
 
 
/** 
* 
*/ 
package ap.uat; 

import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.Map; 

import ap.action.BaseAction; 

public interface IRoutineA { 
	 
	boolean 		start(); 
	 
	public boolean 	clear(); 
	 
	//public boolean 	add_desk_top(); 
	 
	public boolean 	read(); 
	 
	public boolean 	write(); 
	 
	public boolean 	make(Dimension matrix,boolean p_append_only); 
	 
	public boolean 	make(Dimension dim,Dimension dim_size,boolean p_append_only); 
	 
	public boolean 	aclean() ; 
	 
	//public boolean 	add_items(Dimension matrix) ; 
	 
	public boolean 	add_items(int count) ; 
	 
	//public boolean 	add_items(Dimension area,Dimension size); 
	 
	public boolean 	refresh_index_suite(int top_id,int gap); 
	 
	public boolean 	refresh(); 
	 
	public void 	clear_tool_tips(AtOm panel); 
	 
	public void 	post_clearing_suite(AtOm target); 
	 
	public boolean 	nop() ; 
	 
	public boolean 	load_actions_map(); 
	 
	public String 	show_actions_map(Map<String,BaseActionA> actions_map); 
	 
	public AtOm 	get_brew_instance(String brew,Rectangle rect); 
	 
	public boolean 	set_original_bounds_on_the_frame(); 
	 

} 
