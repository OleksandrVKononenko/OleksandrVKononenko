 
package ap.uat; 

import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import javax.swing.Action; 
import javax.swing.JSlider;
import javax.swing.ToolTipManager;
import javax.swing.event.AncestorListener;

import ap.collectors.Collector; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.swing.IAlign; 
import ap.uat.bean.XButtonA; 
import ap.uat.bean.XCheckBoxA; 
import ap.uat.bean.XComboBoxA; 
import ap.uat.bean.XLabelA; 
import ap.uat.bean.XListA; 
import ap.uat.bean.XRadioButtonA; 
import ap.uat.bean.XScrollPaneA; 
import ap.uat.bean.XSliderA; 
import ap.uat.bean.XSpinnerA; 
import ap.uat.bean.XTabbedPaneA; 
import ap.uat.bean.XTableA;
import ap.uat.bean.XTextAreaA;
import ap.uat.bean.XTextFieldA; 
import ap.uat.bean.XTreeA; 
import ap.uat.scrollbar.ScrBarX; 
import ap.uat.scrollbar.ScrBarY; 
import ap.utils.Su; 

public class RoutineImplObjectA implements IRoutineA { 
	 
	public static final Map<String,BaseActionA> actions_map = new LinkedHashMap<String,BaseActionA>(); 
	 
	public	StoreImplObject   ds = ApplicationA.dsio; 
	 
	public RoutineImplObjectA() { 
		 
	} 

	@Override 
	public boolean start() { 
		 
		return (clear() && load_actions_map() && set_original_bounds_on_the_frame() && ApplicationA.cio.show_brew()&& add_dummy_set()); 
		 
	} 
	 
	
	 
	
	public boolean add_dummy_set() 
	{ 
		int m_align = IAlign.indexOf("BASE_ZOOM_ALL"); 
				 
		XComboBoxA	brew = XComboBoxA.get_brew_choice_instance(new Rectangle(322,30,300,21),m_align,BrewActionListener.get_instance());
		
		XComboBoxA	lf = XComboBoxA.get_look_and_fill_choice_instance(new Rectangle(322,60,300,21),m_align,LfActionListener.get_instance()); 
				
		ApplicationA.getDio().get_desk_top().insert_ipo(brew,lf); 
				 
		return true; 

		 
	} 
		 
	@Override 
	public boolean clear() { 
		 
		String msg = "Clear of instances."; 

		try { 

			int m_component_count = ApplicationA.getFio().get_frame().getContentPane().getComponentCount(); 
			 
			if(ApplicationA.getFio().get_frame().getContentPane().getComponentCount() == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...While there is darkness and emptiness...%d...%s", msg,m_component_count, gl.S_OK)); 
				 
				return true; 
			} 
			 
			ds.items.clear(); 

			ApplicationA.getDio().get_desk_top().removeAll(); 
			 
			IDGen.reset(); 
			 
			IDGen.nextId(); 
			 
			gl.tracex(new Object() {}, gl.sf("%s...init idgen...%05d...%s", msg,IDGen.counter.get(), gl.S_OK)); 

			return true; 

		} catch (Exception e) { 
			 
			gl.tracex(new Object() {}, gl.sf("%s...exception...%s...%s", msg,e.getMessage(), gl.S_ERROR)); 
			 
			return false; 
		} 

	} 

	 
	@Override 
	public boolean read() { 
		 
		if (!clear()) 
			return false; 

		String msg = "Load of instance from repositary."; 

		try { 
		 
			Collector<AtOm> cs = new Collector<AtOm>(ApplicationA.getCio().getHome()); 

			cs.clear(); 

			if (!cs.read()) { 

				gl.tracex(new Object() { 
				}, gl.sf("%s...%s...%s", msg, "JAXB read get an error.", 
						gl.S_ERROR)); 

				return false; 
			} 

			AtOm 	dt = ApplicationA.getDio().get_desk_top(); 
			 
				 	dt.removeAll(); 
				 
				cs.getData().forEach(a-> 
				{ 
					dt.add(a); 
				}); 
					dt.repaint(); 
			 
					gl.tracex(new Object() {}, gl.sf("%s...%s", msg, gl.S_OK)); 

					return true; 

		} catch (Exception e) { 

			 
					gl.tracex(new Object() {}, gl.sf("%s...%s...%s...%s", msg, "exception",e.toString(), gl.S_ERROR)); 
		 
					return false; 

		} 

	} 
	 
	 

	@Override 
	public boolean make(Dimension dim, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear()) /* && add_items(dim)*/); 
	} 
	 
	 
	@Override 
	public boolean make(Dimension dim, Dimension dim_size, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear())/*&& add_items(dim,dim_size)*/); 
	} 

	 
	@Override 
	public  boolean aclean() 
	{ 

		try { 

			AtOm dt = ApplicationA.getDio().get_desk_top(); 
			 
			dt.removeAll(); 
			 
			dt.repaint(); 
			 
			 
			gl.tracex(new Object() {}, gl.sf("%s...%s","Clean of the Desktop.",gl.S_OK)); 

			return true; 

		} catch (Exception e) { 
			 
			return false; 
		} 

	} 
	 
	@Override 
	public boolean add_items(int count) 
	{ 
		return true; 
		 
	} 
	 
	 
	@Override 
	public boolean refresh_index_suite(int top_id,int gap) 
	{ 
		return true; 
	} 
		 
	@Override 
	public boolean refresh() 
	{ 
		return true; 
	} 
	 
	public boolean gc() 
	{ 
		StoreImplObject.get_selected().forEach( 
				a->{ 
			 
					if(a.getStio().is_delete() && a.getIdo().getOwner() != null) 
					{ 
						
						a.getIdo().getOwner().remove(a);
						
						if(a.getIdo().getOwner() instanceof PanelSmile && a instanceof PanelPoint)
						{
							((PanelSmile)a.getIdo().getOwner()).update_type();
						}
						
						 
					} 
		}); 
		 
			StoreImplObject.get_selected().clear(); 
		 
			ApplicationA.getDio().get_desk_top().repaint(); 
		 
		return (StoreImplObject.get_selected().size() == gl.E_EMPTY); 
	} 
	
	 
	@Override 
	public boolean write() 
	{ 
		 
			AtOm desktop = ApplicationA.getDio().get_desk_top(); 
			 		 
			String msg = "Save of instances to the file."; 
			 
			desktop.revalidate(); 
			 
			desktop.update_childs(); 
		
			/*
				if (desktop == null) { 
					 
					gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg, "desktop is null", gl.S_ERROR)); 
		
					return false; 
		
				} 
		 
		 */
		
			Collector<AtOm> cs = new Collector<AtOm>(ApplicationA.getCio().getHome()); 
	 
			List<AtOm> rescue = desktop.get_childs(); 
			
			
			desktop.get_childs().forEach(a->{ 
				 
				 
				a.setToolTipText(null); 
				
				ToolTipManager.sharedInstance().setEnabled(false);
				
				ToolTipManager.sharedInstance().unregisterComponent(a);
				
				ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
	
				a.removeNotify();
				
				
				/*
				List<AncestorListener>  m_l = Arrays.asList(a.getAncestorListeners());
				
				m_l.forEach(b->{
					
					a.removeAncestorListener(b);
				});
				
				List<PropertyChangeListener>  m_la = Arrays.asList(a.getPropertyChangeListeners());
				
				m_la.forEach(c->{
					
					a.removePropertyChangeListener(c);
				});
				
				List<MouseListener>  m_ls = Arrays.asList(a.getMouseListeners());
				
				m_ls.forEach(s->{
					
					a.removeMouseListener(s);
				});
				
				*/
				
				 cs.add(a); 
				 
			}); 
				 
			 
		if (!cs.write()) {gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg, "while write object", gl.S_ERROR)); 

			return false; 
		} 
		
			ToolTipManager.sharedInstance().setEnabled(true);

			gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,ApplicationA.getCio().getHome(), gl.S_OK)); 

			return true; 

	} 
	 
		 
	@Override 
	public void post_clearing_suite(AtOm target) 
	{ 
		clear_tool_tips(target); 
	} 
	 
	@Override 
	public void clear_tool_tips(AtOm panel) 
	{ 
		 
		panel.setToolTipText(null); 
		 
		Component[] cos = panel.getComponents(); 

		for (Component c : cos) { 
		 
			((AtOm)c).setToolTipText(null); 
			 
		} 
	} 
	 
	public static RoutineImplObjectA get_instance() 
	{ 
		return new RoutineImplObjectA(); 
	} 

	@Override 
	//public boolean load_actions_map() { return true;} 
	 
	 
	public boolean load_actions_map() { 
	 
			String msg = "Load actions map"; 
			 
			// Keys. 
			List<BaseActionA> acts = Arrays.asList(gl.actions_a_); 
			 
			actions_map .clear(); 
		 
			acts.forEach(a-> 
			{ 
				actions_map.put(Su.get_delim_str_from_list(a.getCmds(),","),a); 
			}); 
			 
			boolean bl_result = (actions_map.size() == gl.actions_a_.length); 
			 
			if(bl_result) 
				gl.smn(show_actions_map(actions_map)); 
			 
			 
			gl.tracex(new Object() {}, gl.sf("%s...items...%d...%s", msg,actions_map.size(),bl_result ? gl.S_OK : gl.S_ERROR)); 
			 
			return bl_result; 
	 
	} 
	 
	 
	 
	@Override 
	public String show_actions_map(Map<String,BaseActionA> actions_map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		int [] npp = {1}; 
		 
		actions_map.forEach((k,v)-> 
		{ 
			String m = gl.sf("%3d.%s %s",npp[gl.E_EMPTY],gl.format(k,gl.AL.LEFT,64,'.'),v.getValue(Action.NAME)); 
			 
			sb.append(m); 
			 
			sb.append(System.lineSeparator()); 
			 
			npp[gl.E_EMPTY]++; 
			 
		}); 
		 
			return sb.toString(); 
	} 

	 
	@Override 
	public AtOm get_brew_instance(String brew,Rectangle rect) { 
		 
		AtOm [] cell = {null}; 
		 
		if(brew.equalsIgnoreCase("panel") || brew.equalsIgnoreCase("pnl")) 
		{ 
			cell[0] = AtOm.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}if(brew.equalsIgnoreCase("point_start") ) 
		{ 
			cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.START); 
			 
		} else  
		if(brew.equalsIgnoreCase("point_end") ) 
		{ 
			cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.END); 
			 
		} else  
		if(brew.equalsIgnoreCase("point_ctrl1") ) 
		{ 
				cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.CTRL1); 
				 
		} else  
		if(brew.equalsIgnoreCase("point_ctrl2") ) 
		{ 
				cell[0] = PanelPoint.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),gl.POINT.CTRL2); 
					 
		}
		else if(brew.equalsIgnoreCase("panelsmile")) 
		{ 
			cell[0] = PanelSmile.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
		}
		else if(brew.equalsIgnoreCase("button") || brew.equalsIgnoreCase("btn")) 
		{ 
			cell[0] = XButtonA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("radiobutton") || brew.equalsIgnoreCase("rbtn")) 
		{ 
			cell[0] = XRadioButtonA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("checkbox") || brew.equalsIgnoreCase("chk")) 
		{ 
			cell[0] = XCheckBoxA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("combobox") || brew.equalsIgnoreCase("cmb")) 
		{ 
			cell[0] = XComboBoxA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}else if(brew.equalsIgnoreCase("scrbarx") ) 
		{ 
			cell[0] = ScrBarX.get_instance(rect,IAlign.indexOf("SCROLLBARX")); 
			 
		} else if(brew.equalsIgnoreCase("scrbary") ) 
		{ 
			cell[0] = ScrBarY.get_instance(rect,IAlign.indexOf("SCROLLBARY")); 
			 
		}else if(brew.equalsIgnoreCase("label") || brew.equalsIgnoreCase("lbl")) 
		{ 
			cell[0] = XLabelA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("vslider") || brew.equalsIgnoreCase("vsldr")) 
		{ 
			cell[0] = XSliderA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),JSlider.VERTICAL,0,255,50); 
			 
		} else if(brew.equalsIgnoreCase("hslider") || brew.equalsIgnoreCase("hsldr")) 
		{ 
			cell[0] = XSliderA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL"),JSlider.HORIZONTAL,0,255,50); 
			 
		} else if(brew.equalsIgnoreCase("spinner") || brew.equalsIgnoreCase("spn")) 
		{ 
			cell[0] = XSpinnerA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		}   else if(brew.equalsIgnoreCase("tabpane") || brew.equalsIgnoreCase("tpane")) 
		{ 
			cell[0] = XTabbedPaneA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("tree")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XTreeA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("list")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XListA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("table")) 
		{ 
			cell[0] = XScrollPaneA.get_instance(XTableA.get_instance(rect),rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			 
		} else if(brew.equalsIgnoreCase("editfield")) 
		{ 
			cell[0] = XTextFieldA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
			
		} else if(brew.equalsIgnoreCase("textarea")) 
		{ 
			cell[0] = XTextAreaA.get_instance(rect,IAlign.indexOf("BASE_ZOOM_ALL")); 
		} 
		 
		 
	 	return cell[0]; 
	} 
	 
	@Override 
	public boolean set_original_bounds_on_the_frame() { 
	 
		try 
		{ 
			ApplicationA.getFio().setOriginal_bounds(ApplicationA.getFio().get_frame().getBounds()); 
				 
			return true; 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
	} 
	 

	@Override 
	public boolean nop() { 
		 
		return true; 
	} 


} 

