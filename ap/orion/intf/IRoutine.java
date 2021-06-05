package ap.orion.intf;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Map;
import ap.orion.Orion;
import ap.uat.BaseActionA;

public interface IRoutine { 
	 
	boolean 		start(); 
	 
	public boolean 	clear(); 
	 
	public boolean 	read(); 
	 
	public boolean 	write(boolean all_objects); 
	 
	public boolean 	make(Dimension matrix,boolean p_append_only); 
	 
	public boolean 	make(Dimension dim,Dimension dim_size,boolean p_append_only); 
	 
	public boolean 	aclean() ; 
		 
	public boolean 	add_items(int count) ; 
	 
	public boolean 	refresh_index_suite(int top_id,int gap); 
	 
	public boolean 	refresh(); 
	 
	public void 	clear_tool_tips(Orion panel); 
	 
	public void 	post_clearing_suite(Orion target); 
	 
	public boolean 	nop() ; 
	 
	//public boolean 	load_actions_map(); 
	 
	//public String 	show_actions_map(Map<String,BaseAction> actions_map); 
	 
	public Orion 	get_brew_instance(String brew,Rectangle rect); 
	 
	public boolean 	set_original_bounds_on_the_frame(); 
	 

} 
