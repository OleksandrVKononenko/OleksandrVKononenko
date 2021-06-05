 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.collectors; 

import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.Comparator; 
import java.util.List; 
import java.util.Map; 
import java.util.OptionalInt; 
import java.util.Vector; 
import java.util.stream.Collectors; 

import ap.global.gl; 
import ap.btn.Order; 
import ap.btn.TMaPoint; 
import ap.btn.TOrder; 
import ap.btn.TPanel; 
import ap.btn.TTick; 
import ap.prop.SBounds; 
import ap.utils.Biu; 
import ap.utils.DateUtil;
import ap.utils.Su;
import ap.vec.SVector; 


public class SPanelCollector extends Collector<TPanel> { 

	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 1L; 
	 
	public static Vector<TPanel> childs = new Vector<TPanel>(); 
	 
	public SPanelCollector() 
	{ 
		 
	} 
	 
	public SPanelCollector(String home) 
	{ 
		super(home); 
	} 
	 
	@Override 
	public void clear() 
	{ 
		super.clear(); 
	} 
	 
	public TPanel get_by_id(int id) 
	{ 
		 
		return 
		 
		this.getData().stream() 
				.filter((b-> 
				( 
						b.getId()   == id 
				))).findFirst().orElse(null); 
		 
			 
	} 
	 
	public boolean checkId(int id) 
	{ 

		for(TPanel a : this.getData() ) 
		{ 
			if(((ap.btn.TPanel)a).getId() == id) 
			{ 
				return true; 
				 
			} 
		} 
		 
			return false; 
	} 
	 
	public TPanel getIndex(int index) 
	{ 
		return this.getData().get(index); 
	} 
	 
	public TPanel get_by_index(int index) 
	{ 
		return 

		this.getData().stream().filter((b -> (b.getZorder() == index))) 
				.findFirst().orElse(null);				 
			 
	} 

	public TPanel get_by_pid_and_function(int pid,String function) 
	{ 
		return 

		this.getData().stream().filter( 
				( 
						b -> ( 
								b.getPid() == pid && 
								b.getFunction().equalsIgnoreCase(function) 
						     ) 
								 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public TPanel get_by_pid_and_action(int pid,String action) 
	{ 
		return 

		this.getData().stream().filter( 
				( 
						b -> ( 
								b.getPid() == pid && 
								b.getAction().equalsIgnoreCase(action) 
						     ) 
								 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public TPanel get_by_pid_and_gid(List<TPanel> list,int pid,int gid) 
	{ 
		return 

		list.stream().filter( 
				( 
						b -> ( 
								b.getPid() == pid && 
								b.getGid() == gid 
						     ) 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public TPanel get_by_gid_and_action(int gid,String action) 
	{ 
		return 

		this.getData().stream().filter( 
				( 
						b -> ( 
								b.getGid() == gid && 
								b.getFunction().equalsIgnoreCase(action) 
						     ) 
								 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public TPanel get_by_pid_and_function_start_with(int pid,String action) 
	{ 
		return 

		this.getData().stream().filter( 
				( 
						b -> ( 
								b.getPid() == pid && 
								b.getFunction().startsWith(action) 
						     ) 
								 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public synchronized  Vector<TPanel> get_by_pid(int pid) 
	{ 

		Vector<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid 
					))).collect(Collectors.toCollection(Vector :: new)); 
				 
				return list; 
		 
	} 
	 
	public synchronized  Vector<TPanel> get_by_pid(Vector<TPanel> source_collection,int pid) 
	{ 

		Vector<TPanel> list =  source_collection.stream().filter( 
				(b-> ( 
						b.getPid() == pid 
					))).collect(Collectors.toCollection(Vector :: new)); 
				 
				return list; 
		 
	} 

	public synchronized  List<String> get_list_series(Map<String,List<TTick>> input_map) 
	{ 
	 
		return input_map.entrySet() 
				.stream() 
				.map(Map.Entry::getKey) 
				.collect(Collectors.toList()); 
	 
	} 
	 
	public synchronized  List<String> get_list_series_points(Map<String,List<TMaPoint>> input_map) 
	{ 
	 
		return input_map.entrySet() 
				.stream() 
				.map(Map.Entry::getKey) 
				.collect(Collectors.toList()); 
	 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid(int pid) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid(int pid,int skip_id) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getId() != skip_id 
					))).sorted(Comparator.comparing(s->s.getId())).collect(Collectors.toList()); 
		 
				return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid_cloned(List<TPanel> source_list, int pid) 
	{ 

		List<TPanel> list =  source_list.stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getGid() != gl.E_ERROR 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	 
	 
	public synchronized  List<TPanel> get_sub_objects_by_func(List<TPanel> source_list,String func) 
	{ 

		List<TPanel> list =  source_list.stream().filter( 
				(b-> ( 
						b.getPid() == gl.E_ERROR && 
						b.getFunction().equalsIgnoreCase(func) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_action(int pid,String action) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getAction().trim().equalsIgnoreCase(action) 
					))).collect(Collectors.toList()); 
				 
				return list; 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_function(int pid,String action) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getFunction().trim().equalsIgnoreCase(action) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_action_starts_with(int pid,String action) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getAction().trim().startsWith(action) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	
	public synchronized  List<TPanel> get_list_by_pid_and_action_and_type(int pid,String action,String type) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getAction().trim().equalsIgnoreCase(action) && 
						b.getType().trim().equalsIgnoreCase(type) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 

	public synchronized  List<TPanel> get_list_by_pid_and_actions(int pid,String [] actions) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						
						Su.in(
						b.getAction().trim().toLowerCase(),actions) 
						
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 

	 
	public synchronized  List<TPanel> get_action_list(int pid) 
	{ 

		// For All. 
		List<TPanel> list = null; 
		 
		if(pid == gl.E_ERROR) 
		{ 
			 list =  this.getData().stream().filter( 
						(b-> ( 
								b.getAction().trim().length() != gl.E_EMPTY 
							))).collect(Collectors.toList()); 
		} 
		else 
		{ 
		 list =  this.getData().stream().filter( 
				(b-> ( 
						 
						b.getPid() == pid && 
						b.getAction().trim().length() != gl.E_EMPTY 
						 
					))).collect(Collectors.toList()); 
				 
		} 
		 
			return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_type(int pid,String type) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getType().trim().equalsIgnoreCase(type) && 
						b.getPid() != gl.E_ERROR 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_type(List<TPanel> source,int pid,String type) 
	{ 

		List<TPanel> list =  source.stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getType().trim().equalsIgnoreCase(type) && 
						b.getPid() != gl.E_ERROR 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_pid_and_type_and_subtype(List<TPanel> source,int pid,String type,String subtype) 
	{ 

		List<TPanel> list =  source.stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getType().trim().equalsIgnoreCase(type) && 
						b.getFunction().trim().equalsIgnoreCase(subtype) && 
						b.getPid() != gl.E_ERROR 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public synchronized  List<TTick> to_tick_list(List<TPanel> source) 
	{ 
		List<TTick> r = new ArrayList<TTick>(); 
		 
		source.forEach(a->{ 
			 
			r.add((TTick)a); 
		}); 
		 
			return r; 
	} 
	 
	public synchronized  List<TPanel> get_list_by_type(String type) 
	{ 

		List<TPanel> list =  this.getData().stream().filter( 
				(b-> ( 
						b.getType().trim().equalsIgnoreCase(type) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	public synchronized  List<TPanel> get_list_by_type(List<TPanel> source,String type) 
	{ 

		List<TPanel> list =  source.stream().filter( 
				(b-> ( 
						b.getType().trim().equalsIgnoreCase(type) 
					))).collect(Collectors.toList()); 
				 
				return list; 
		 
	} 
	 
	// Return child collection of any type. 
	public synchronized  List<String> get_list_series_by_chart(List<TPanel> source,int pid,String type) 
	{ 

		List<TPanel> list =  source.stream().filter( 
				(b-> ( 
						b.getPid() == pid && 
						b.getType().trim().equalsIgnoreCase(type) 
						 
					))).collect(Collectors.toList()); 
				 
		 
		// Reload of unique value list. 
		 
		List<String> result = new ArrayList<String>(); 
		 
		list.forEach(a-> 
		{ 
			String series = a.getFunction(); 
			 
			if(!result.contains(series)) 
				result.add(series); 
			 
		} 
		 
		); 
		 
		 
			return result; 
		 
		 
	} 

	 
	public  synchronized void updateAction(TPanel obj,String prev_action,String new_action) 
	{ 
		 
		Vector<TPanel> chi = obj.getChildsAll(obj.getId()); 
		 
		chi.forEach(a-> 
		{ 
				if(a.getAction() != null &&  a.getAction().equalsIgnoreCase(prev_action)) 
				{ 
					a.setAction(new_action); 
				} 
		} 
		 
		); 
	} 
	 
	 
	 
	public void getParents(int pid) 
	{ 
		 
		for(TPanel a : this.getData()) 
		{ 
			if( ((ap.btn.TPanel)a).getId() == pid) 
			{ 
				childs.add(a); 
					 
				if(a.getPid() != gl.E_ERROR) 
				getParents(a.getPid()); 
				 
			} 
			 
		} 
	} 
	 
	 
	 
	public TPanel getGrandForm(int pid) 
	{ 
		int grand_id = getParentFormId(pid); 
				 
		if(grand_id == gl.E_ERROR) 
			return null; 
		 
		return get_by_id(grand_id); 
		 
	} 
	 
	 
	public int getParentFormId(int pid) 
	{ 
		 
		childs.clear(); 

		getParents(pid); 

		// For debug. 
		if (childs.size() == gl.E_EMPTY) 
			return gl.E_ERROR; 
		else 
			return childs.get(childs.size() - 1).getId(); 

	} 
	 
	 
	 
	public TPanel getParent(int pid) 
	{ 
		 
		return this.getData().stream().filter((b -> (b.getId() == pid))) 
		.findFirst().orElse(null);	 
		 
	} 

	// 

	/* 
	public TPanel getGround(int gid,List<TPanel> items) 
	{ 
		 
		return items.stream().filter( 
				 
				(b -> 
				( 
						b.getGid() == gid && 
						 
						b.getFunction().equalsIgnoreCase("ground") 
				 
				) 
				) 
				) 
		.findFirst().orElse(null);	 
		 
	} 
	 

	public TPanel getBody(int gid,List<TPanel> items) 
	{ 
		 
		return items.stream().filter( 
				 
				(b -> 
				( 
						b.getGid() == gid && 
						 
						b.getFunction().equalsIgnoreCase("body") 
				 
				) 
				) 
				) 
		.findFirst().orElse(null);	 
		 
	} 
	 

	public TPanel getHigh_Shadow(int gid,List<TPanel> items) 
	{ 
		 
		return items.stream().filter( 
				 
				(b -> 
				( 
						b.getGid() == gid && 
						 
						b.getFunction().equalsIgnoreCase("high_shadow") 
				 
				) 
				) 
				) 
		.findFirst().orElse(null);	 
		 
	} 
	 
	public TPanel getLow_Shadow(int gid,List<TPanel> items) 
	{ 
		 
		return items.stream().filter( 
				 
				(b -> 
				( 
						b.getGid() == gid && 
						 
						b.getFunction().equalsIgnoreCase("low_shadow") 
				 
				) 
				) 
				) 
		.findFirst().orElse(null);	 
		 
	} 
	 */ 
	 
	/*	 
	 
	public SVector<TPanel> getSelected() 
	{ 
		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for(TPanel a : this.getData() ) 
		{ 
			if( ((ap.btn.TPanel)a).getSelected() == gl.E_OK) 
			{ 
				vc.add(a); 
			} 
		} 
		 
			return vc; 
	} 
	 
	*/ 
	 
	public SVector<TPanel> getSelected() 
	{ 
		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for(TPanel a : this.getData() ) 
		{ 
			if( ((ap.btn.TPanel)a).isSelected()) 
			{ 
				vc.add(a); 
			} 
		} 
		 
			return vc; 
	} 
	 
	 
	 
	 
	public SVector<TPanel> getBySelector(Rectangle rect) { 

		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for (TPanel a : this.getData()) { 
			 
			if (rect.contains(a.getBounds().getBounds())) { 
				 
				 
				if  (!vc.contains(a)) 
					  vc.add(a); 

				Vector<TPanel> childs = new Vector<TPanel>(); 

				childs = a.getChildsAll(a.getId()); 

				for (TPanel b : childs) { 
					if (!vc.contains(b)) { 
						vc.add(b); 
					} 
										} 
				 
					} // if 
		} // for 
		 

		return vc; 
	}	 
	 
	public SVector<TPanel> getBySelector(Rectangle rect,SVector<TPanel> items) { 

		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for (TPanel a : items) { 
			 
			if (rect.contains(a.getBounds().getBounds())) { 
				 
				 
				if  (!vc.contains(a)) 
					  vc.add(a); 

				Vector<TPanel> childs = new Vector<TPanel>(); 

				childs = a.getChildsAll(a.getId()); 
				 
				for (TPanel b : childs) { 
					if (!vc.contains(b)) { 
						vc.add(b); 
					} 
										} 
				 
					} // if 
		} // for 
		 

		return vc; 
	}	 
	 
	 
	public SVector<TPanel> getDeleted() 
	{ 
		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for(TPanel a : this.getData() ) 
		{ 
			if( ((ap.btn.TPanel)a).getDeleted() == gl.E_OK) 
			{ 
				vc.add(a); 
			} 
		} 
		 
			return vc; 
	} 
	 
	 
	public SVector<TPanel> getVariants(TPanel value) 
	{ 
		SVector<TPanel> vc = new SVector<TPanel>(); 
		 
		for(TPanel a : this.getData() ) 
		{ 
			if(a.getBounds().contains(value.getBounds()) 
					&& (a.getId() != value.getId()) 
					) 
					vc.add(a); 
		} 
		 
			return vc; 
	} 
	 
	public boolean gc() 
	{ 
		 
		try 
		{ 
		this.getData().removeIf( 
				a -> { 
					return a.isDeleted(); 
				}); 
		 
				return true; 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 

	} 

	 
	public void show() 
	{ 
		this.getData().forEach( 
					a-> 
					{ 
						gl.smn("Item : " + a.toString()); 
					} 
				); 
	} 
	 
	public int getSize() 
	{ 
		return this.getData().size(); 
	} 
	 
	public int getCountOfDeleted() 
	{ 
		return (int)this.getData().stream().filter(TPanel::isDeleted).count(); 
	} 
	 
	public int get_max_id() 
	{ 
		 
		OptionalInt max_id = 
		 
		this.getData().stream().mapToInt(TPanel::getId).max(); 
		 
		if(max_id.isPresent()) 
			return max_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 
	 
	public int get_max_gid() 
	{ 
		 
		OptionalInt max_id = 
		 
		this.getData().stream().mapToInt(TPanel::getGid).max(); 
		 
		if(max_id.isPresent()) 
			return max_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 
	 
	public int get_max_id(List<TPanel> list) 
	{ 
		 
		OptionalInt max_id = 
		 
		list.stream().mapToInt(TPanel::getId).max(); 
		 
		if(max_id.isPresent()) 
			return max_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 
	 
	public int get_min_id(List<TPanel> list) 
	{ 
		 
		OptionalInt min_id = 
		 
		list.stream().mapToInt(TPanel::getId).min(); 
		 
		if(min_id.isPresent()) 
			return min_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 
	 
	public int get_tick_min_id(List<TTick> list) 
	{ 
		 
		OptionalInt min_id = 
		 
		list.stream().mapToInt(TTick::getId).min(); 
		 
		if(min_id.isPresent()) 
			return min_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 
	 
	public int get_tick_max_id(List<TTick> list) 
	{ 
		 
		OptionalInt max_id = 
		 
		list.stream().mapToInt(TTick::getId).max(); 
		 
		if(max_id.isPresent()) 
			return max_id.getAsInt(); 
		 
			return gl.E_ERROR; 
	 
	} 

	 
	 
	 
	public int get_max_id(Vector<TPanel> list) 
	{ 
		OptionalInt max_id = 
				 
				list.stream().mapToInt(TPanel::getId).max(); 
				 
				if(max_id.isPresent()) 
					return max_id.getAsInt(); 
				 
					return gl.E_ERROR; 
				 
	} 
	 
	public boolean removeAll(@SuppressWarnings("rawtypes") Collection collection) 
	{ 
		 
		if(collection == null) 
			return true; 
		 
		this.getData().removeAll(collection); 
		 
		return (!this.getData().containsAll(collection)); 
		 
	} 
	 
	public int get_min_id() 
{ 
		OptionalInt min_id = 

		this.getData().stream().mapToInt(TPanel::getId).min(); 

		if (min_id.isPresent()) 
			return min_id.getAsInt(); 

		return gl.E_ERROR; 

	} 
	 
	public long get_count_of_type_by_pid(String type,int pid) 
	{ 
	 
		long count = this.getData().stream().filter( 
			(a-> ( 
					a.getType().trim().equalsIgnoreCase(type) && 
					 
					a.getPid() == pid 
			)) 
			).count(); 
		 
		return count; 
	 
	} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
