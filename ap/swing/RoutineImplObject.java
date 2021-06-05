 
 
package ap.swing; 

import java.awt.BorderLayout; 
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Rectangle; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 

import javax.swing.Action; 
import javax.swing.SwingUtilities; 

import ap.action.BaseAction; 
import ap.area.AreaManager; 
import ap.collectors.Collector; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.prop.SBounds; 
import ap.prop.SDimension; 
import ap.shape.Ru; 
import ap.utils.Nu; 
import ap.utils.Su; 

public class RoutineImplObject implements IRoutine { 
	 
	public static final Map<String,BaseAction> actions_map = new LinkedHashMap<String,BaseAction>(); 
	 
	public RoutineImplObject() { 
		 
	} 

	@Override 
	public boolean start() { 
		 
		return (clear() && add_desk_top() && load_actions_map() && set_original_bounds_on_the_frame() && Application.cio.show_brew()); 
		 
	} 

	@Override 
	public boolean clear() { 
		 
		String msg = "Clear of instances."; 

		try { 

			int m_component_count = Application.getFio().getFrame().getContentPane().getComponentCount(); 
			 
			if(Application.getFio().getFrame().getContentPane().getComponentCount() == gl.E_EMPTY) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...While there is darkness and emptiness...%d...%s", msg,m_component_count, gl.S_OK)); 
				 
				return true; 
			} 
			 
			PanelXml.items.clear(); 

			Application.getDio().getDeskTop().removeAll(); 
			 
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
	public boolean add_desk_top() { 
		 
		String msg = "Add desktop."; 

		try { 
					 
			PanelXml 	desktop =  Application.getDio().getDeskTop(); 
					 			 
			// Check existing of desktop. 
			int m_component_count = Application.getFio().getFrame().getContentPane().getComponentCount(); 
			 
			if(m_component_count != gl.E_EMPTY) 
			{ 
				gl.tracex(new Object() {}, gl.sf("%s...Desktop exists and must be CLEAN...%d...%s", msg,m_component_count, gl.S_OK)); 
				 
				PanelXml m_desktop  = ((PanelXml)Application.getFio().getFrame().getContentPane().getComponent(gl.E_EMPTY)); 
				 
				int  m_real_desktop_childs_components = m_desktop.getChildsNearOwner(gl.E_OK).size(); 
				 
				if(m_real_desktop_childs_components != gl.E_EMPTY) 
				{ 
					m_desktop.removeAll(); 
					 
					m_desktop.revalidate(); 
					 
					gl.tracex(new Object() {}, gl.sf("%s...Remove all from desktop...%s", msg,gl.S_OK)); 
					 
				} 
				 
					return true; 
			} 
			 

				Application.getFio().getFrame().getContentPane().add(desktop,gl.E_EMPTY); 
				 
				Application.getFio().getFrame().revalidate(); 
				 
				Application.getFio().getFrame().repaint(); 
				 
				Application.getDio().getDeskTop().requestFocus(); 
				 
				gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,SDimension.toString(Application.getCio().getPrefferedSize()), gl.S_OK)); 
	 
				return true; 

		} catch (Exception e) { 

				gl.tracex(new Object() {}, gl.sf("%s...%s...%s...%s", msg, "exception", e.getMessage(), gl.S_ERROR)); 
	 
				return false; 

		} 

		 
	} 

	@Override 
	public boolean read() { 
		 
		if (!clear()) 
			return false; 

		String msg = "Load of instance from repositary."; 

		try { 
		 
			Collector<PanelXml> cs = new Collector<PanelXml>(Application.getCio().getHome()); 

			cs.clear(); 

			if (!cs.read()) { 

				gl.tracex(new Object() { 
				}, gl.sf("%s...%s...%s", msg, "JAXB read get an error.", 
						gl.S_ERROR)); 

				return false; 
			} 

			PanelXml desktop_panel = (PanelXml) cs.getData().get(gl.E_EMPTY); 

			if (desktop_panel == null) { 
				gl.tracex(new Object() { 
				}, gl.sf("%s...%s...%s", msg, 
						"JAXB object is null after read.", gl.S_ERROR)); 

				return false; 
			} 

			Application.getFio().getFrame().getContentPane().removeAll(); 

			Application.getFio().getFrame().getContentPane().add(desktop_panel); 

			Application.getFio().getFrame().getContentPane().repaint(); 

			// Set desktop panel. 
			Application.getDio().setDeskTop(desktop_panel); 
			 
			Application.getFio().getFrame().setBounds(Application.getDio().getDeskTop().getDecoro().getFrameBounds()); 
		 
			gl.tracex(new Object() {}, gl.sf("%s...read frame bounds...%s...%s", msg,SBounds.toString(Application.getFio().getFrame().getBounds()), gl.S_OK)); 
			 
			 
			// Load items to brew. 
			PanelXml.items.clear(); 

			for (int i = 0; i < desktop_panel.getComponentCount(); i++) { 
				 
				Object obj =  desktop_panel.getComponent(i); 
				 
				PanelXml.items.put(((PanelXml)obj).getIdo().getIndex(), (PanelXml)obj); 
				 
			} 
			 
					gl.tracex(new Object() {}, gl.sf("%s...%s", msg, gl.S_OK)); 

					return true; 

		} catch (Exception e) { 

			 
					gl.tracex(new Object() {}, gl.sf("%s...%s...%s...%s", msg, "exception",e.toString(), gl.S_ERROR)); 
		 
					return false; 

		} 

	} 
	 
	 

	@Override 
	public boolean make(Dimension dim, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear()) && add_items(dim)); 
	} 
	 
	@Override 
	public boolean make(Dimension dim, Dimension dim_size, boolean p_append_only) { 
		 
		return (((p_append_only)? nop() : clear())&& add_items(dim,dim_size)); 
	} 

	@Override 
	public boolean add_items(Dimension matrix) { 
				 
				String msg = "Add items (dim)."; 

				try { 
								 
					 
					AreaManager am = new AreaManager(Application.getDio().getDeskTop().getDecoro().getSize(),matrix); 
					 
					// Update am variable of the desktop. 
					Application.getDio().getDeskTop().getDecoro().setAreaManager(am); 
					 
					int[] index = { 0 }; 

					// To PanelXml.items. 
					am.getRects().forEach(a -> { 
								 
						PanelXml cell = get_brew_instance(Application.cio.getActive_brew_object(),a); 
						 
						cell.getIdo().setIndex(index[0]); 
					 
						cell.connect(Application.getDio().getDeskTop()); 
						 
						PanelXml.items.put(cell.getIdo().getId(), cell); 

						index[0]++; 

					}); 
					 
							 
						gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,SDimension.toString(Application.getCio().getPrefferedSize()), gl.S_OK)); 

						Application.getDio().getDeskTop().revalidate(); 
					 
						return refresh(); 
					 
				} catch (Exception e) { 

					gl.tracex(new Object() {}, gl.sf("%s...%s...%s...%s", msg, "exception",e.toString().substring(1, 32), gl.S_ERROR)); 

					return false; 
				} 

	} 
	 
	 
	 
	@Override 
	public  boolean aclean() 
	{ 

		try { 

			PanelXml.items.forEach((k, v) -> { 

				v.setDelete(true); 

			}); 

			gl.tracex(new Object() {}, gl.sf("%s...%s","Clean of the Desktop.",gl.S_OK)); 

			return true; 

		} catch (Exception e) { 
			return false; 
		} 
 
	} 
	 
	@Override 
	public boolean add_items(int count) 
	{ 
		 
		// App must started. 
		// Desktop must added. 
		 
		String msg = "Add items (int)."; 

		try { 
				 
			// Get matrix. 
			 
			// Insert child PanelXml.items. 
			AreaManager am = new AreaManager(Application.getDio().getDeskTop().getDecoro().getSize(),Nu.get_dimension(count)); 
			 
			// Update am variable of the desktop. 
			Application.getDio().getDeskTop().getDecoro().setAreaManager(am); 
			 
			int[] index = { 0 }; 

			// To PanelXml.items. 
			am.getRects().forEach(a -> { 
				 
				PanelXml cell = get_brew_instance(Application.cio.getActive_brew_object(),a); 
								 
				cell.getIdo().setIndex(index[0]); 
				 
				cell.getIdo().setPid(Application.getDio().getDeskTop().getIdo().getId()); 
				 
				PanelXml.items.put(cell.getIdo().getId(), cell); 

				index[0]++; 

			}); 
			 
					 
				gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,SDimension.toString(Application.getCio().getPrefferedSize()), gl.S_OK)); 

				return refresh(); 
			 
		} catch (Exception e) { 

			gl.tracex(new Object() { 
			}, gl.sf("%s...%s...%s...%s", msg, "exception",e.toString().substring(1, 32), gl.S_ERROR)); 

			return false; 
		} 

	} 
	 
	@Override 
	public boolean add_items(Dimension matrix,Dimension size) { 
				 
				String msg = "Add items (dim)."; 

				try { 
								 
					 
					AreaManager am = new AreaManager(Application.getDio().getDeskTop().getDecoro().getSize(),matrix); 
					 
					// Update am variable of the desktop. 
					Application.getDio().getDeskTop().getDecoro().setAreaManager(am); 
					 
					int[] index = { 0 }; 

					// To PanelXml.items. 
					am.getRects().forEach(a -> { 
								 
						Rectangle m_bounds = new Rectangle(a.x,a.y,size.width,size.height); 
						 
						PanelXml cell = get_brew_instance(Application.cio.getActive_brew_object(),m_bounds); 
						 
						cell.getIdo().setIndex(index[0]); 
					 
						cell.connect(Application.getDio().getDeskTop()); 
						 
						PanelXml.items.put(cell.getIdo().getId(), cell); 

						index[0]++; 

					}); 
					 
							 
						gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,SDimension.toString(Application.getCio().getPrefferedSize()), gl.S_OK)); 

						Application.getDio().getDeskTop().revalidate(); 
					 
						return refresh(); 
					 
				} catch (Exception e) { 

					gl.tracex(new Object() {}, gl.sf("%s...%s...%s...%s", msg, "exception",e.toString().substring(1, 32), gl.S_ERROR)); 

					return false; 
				} 

	} 
	 
	@Override 
	public boolean refresh_index_suite(int top_id,int gap) 
	{ 
		 
		Application.getDio().getDeskTop().removeAll(); 

		int[] index = { gap }; 

		// Mark for correct index. 
		PanelXml.items.forEach((k, v) -> { 

			if(top_id == v.getIdo().getId()) 
				v.getIdo().setIndex(gl.E_EMPTY); 
			else 
			{ 
				v.getIdo().setIndex(index[0]); 
				 
				index[0]++; 
			} 
			 
		}); 

		// Push them to the desktop. 
		PanelXml.items.forEach((k, v) -> { 

			Application.getDio().getDeskTop().add(v, v.getIdo().getIndex()); 
		}); 

			Application.getDio().getDeskTop().repaint(); 

		return (Application.getDio().getDeskTop().getComponentCount() == PanelXml.items.size()); 

	} 
	 
	@Override 
	public boolean refresh() 
	{ 

		List<Integer> deleted = new ArrayList<Integer>(); 

		Application.getDio().getDeskTop().removeAll(); 

		// Gathered for a deleted objects. 
		PanelXml.items.forEach((k, v) -> { 

			if (v.isDelete()) 
				deleted.add(k); 

		}); 

		// Remove them from repositary. 
		deleted.forEach(a -> { 
			PanelXml.items.remove(a); 
		}); 

		int[] index = { 0 }; 

		// Mark for correct index. 
		PanelXml.items.forEach((k, v) -> { 

			v.getIdo().setIndex(index[0]); 

			index[0]++; 
		}); 

		// Push them to the desktop. 
		PanelXml.items.forEach((k, v) -> { 

			Application.getDio().getDeskTop().add(v, v.getIdo().getIndex()); 
			 
		}); 
		 
			Application.getDio().getDeskTop().repaint(); 
								 
			return (Application.getDio().getDeskTop().getComponentCount() == PanelXml.items.size()); 

	} 

	@Override 
	public boolean write() 
	{ 
		 
			DesktopImplObject 	dio = Application.getDio(); 
			 
			FrameImplObject 	fio = Application.getFio(); 
					 
			String msg = "Save of instances to the file."; 
			 
			 
		if (dio.getDeskTop() == null) { 
			 
			gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg, "desktop is null", gl.S_ERROR)); 

			return false; 

		} 
		 
			Collector<PanelXml> cs = new Collector<PanelXml>(Application.getCio().getHome()); 
	 
			dio.setDeskTop((PanelXml) fio.getFrame().getContentPane().getComponent(gl.E_EMPTY)); 
	 
			dio.getDeskTop().getDecoro().setFrameBounds(fio.getFrame().getBounds()); 
			 
			gl.tracex(new Object() {}, gl.sf("%s...frame bounds...%s...%s", msg,SBounds.toString(dio.getDeskTop().getDecoro().getFrameBounds()), gl.S_OK)); 
					 
			post_clearing_suite(dio.getDeskTop()); 
	 
			cs.clear(); 
	 
			cs.add(dio.getDeskTop()); 

		if (!cs.write()) {gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg, "while write object", gl.S_ERROR)); 

			return false; 
		} 

			gl.tracex(new Object() {}, gl.sf("%s...%s...%s", msg,Application.getCio().getHome(), gl.S_OK)); 

			return true; 

	} 
		 
	@Override 
	public void post_clearing_suite(PanelXml target) 
	{ 
		clear_tool_tips(target); 
	} 
	 
	@Override 
	public void clear_tool_tips(PanelXml panel) 
	{ 
		 
		panel.setToolTipText(null); 
		 
		Component[] cos = panel.getComponents(); 

		for (Component c : cos) { 
		 
			((PanelXml)c).setToolTipText(null); 
			 
		} 
		 
	} 

	 
	public static RoutineImplObject get_instance() 
	{ 
		return new RoutineImplObject(); 
	} 

	@Override 
	public boolean load_actions_map() { 
	 
			String msg = "Load actions map"; 
			 
			// Keys. 
			List<BaseAction> acts = Arrays.asList(gl.actions); 
			 
			actions_map .clear(); 
		 
			acts.forEach(a-> 
			{ 
				actions_map.put(Su.get_delim_str_from_list(a.getCmds(),","),a); 
			}); 
			 
			boolean bl_result = (actions_map.size() == gl.actions.length); 
			 
			if(bl_result) 
				gl.smn(show_actions_map(actions_map)); 
			 
			 
			gl.tracex(new Object() {}, gl.sf("%s...items...%d...%s", msg,actions_map.size(),bl_result ? gl.S_OK : gl.S_ERROR)); 
			 
			return bl_result; 
	 
	} 
	 
	@Override 
	public String show_actions_map(Map<String,BaseAction> actions_map) 
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
	public PanelXml get_brew_instance(String brew,Rectangle rect) { 
		 
			PanelXml [] cell = {null}; 
		 
		if(brew.equalsIgnoreCase("panel") || brew.equalsIgnoreCase("pnl")) 
		{ 
			cell[0] = new PanelXml(rect); 
			 
		}else if(brew.equalsIgnoreCase("button") || brew.equalsIgnoreCase("btn")) 
		{ 
			 
			cell[0] = XButton.get_instance(rect); 
			 
			 
		} else if(brew.equalsIgnoreCase("textfield") || brew.equalsIgnoreCase("fld")) 
		{ 
			cell[0] = new XTextField(rect); 
		} 
		else if(brew.equalsIgnoreCase("textarea") || brew.equalsIgnoreCase("area")) 
		{ 
			cell [0]= new XTextArea(rect); 
			 
		}else if(brew.equalsIgnoreCase("checkbox") || brew.equalsIgnoreCase("chk")) 
		{ 
			cell [0]= new XCheckBox(rect); 
			 
		}else if(brew.equalsIgnoreCase("label") || brew.equalsIgnoreCase("lbl")) 
		{ 
			cell [0] = new XLabel(rect); 
			 
		}else if(brew.equalsIgnoreCase("combobox") || brew.equalsIgnoreCase("cmb")) 
		{ 
			cell [0] = new XComboBox(rect); 
			 
		}else if(brew.equalsIgnoreCase("radiobutton") || brew.equalsIgnoreCase("rbt")) 
		{ 
			cell [0] = new XRadioButton(rect); 
			 
		}else if(brew.equalsIgnoreCase("spinner") || brew.equalsIgnoreCase("spn")) 
		{ 
			cell[0] = XSpinner.get_instance(rect); 
			 
		}else if(brew.equalsIgnoreCase("togglebutton") || brew.equalsIgnoreCase("tgbtn")) 
		{ 
			cell[0] = new XToggleButton(rect); 
			 
		}else if(brew.equalsIgnoreCase("table") || brew.equalsIgnoreCase("tbl")) 
		{ 
			 
			cell[0] = XScrollPane.get_instance(new XTable(rect),rect); 
			 
		}  else if(brew.equalsIgnoreCase("tree") || brew.equalsIgnoreCase("tr")) 
		{ 
			 
			cell[0] = XScrollPane.get_instance(new XTree(rect),rect); 
			 
		}  else if(brew.equalsIgnoreCase("toolbar") || brew.equalsIgnoreCase("tb")) 
		{ 
			cell[0] = new XToolBar(rect); 
			 
		}else if(brew.equalsIgnoreCase("list") || brew.equalsIgnoreCase("ls")) 
		{ 
			cell[0] = XScrollPane.get_instance(new XList(rect),rect); 
		}else if(brew.equalsIgnoreCase("scrollpane") || brew.equalsIgnoreCase("scr")) 
		{ 
			cell[0] = XScrollPane.get_instance(rect); 
		} else if(brew.equalsIgnoreCase("tabpane") || brew.equalsIgnoreCase("tbpan")) 
		{ 
			cell[0] = XTabbedPane.get_instance(rect); 
		} else if(brew.equalsIgnoreCase("prgbar") || brew.equalsIgnoreCase("pbar")) 
		{ 
			cell[0] = XProgressBar.get_instance(rect); 
		} else if(brew.equalsIgnoreCase("prgbari") || brew.equalsIgnoreCase("pbari")) 
		{ 
			cell[0] = XProgressBar.get_instance_inf(rect); 
			 
		} else if(brew.equalsIgnoreCase("slider") || brew.equalsIgnoreCase("sld")) 
		{ 
			cell[0] = XSlider.get_instance(rect); 
			 
		} 
		 
		 	return cell[0]; 
	} 

	 
	@Override 
	public boolean set_original_bounds_on_the_frame() { 
	 
		try 
		{ 
			Application.getFio().setOriginal_bounds(Application.getFio().getFrame().getBounds()); 
				 
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
