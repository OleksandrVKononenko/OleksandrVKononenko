 
package ap.action; 

import java.awt.Color; 
import java.awt.Frame; 
import java.awt.Rectangle; 
import java.util.List; 
import javax.swing.JColorChooser;

import ap.btn.TBtn;
import ap.btn.TChart; 
import ap.btn.TPanel;
import ap.btn.TPushBtn;
import ap.frame.TFrame; 
import ap.global.gl; 
import ap.prop.SBounds; 
import ap.prop.SColor; 
import ap.utils.CU; 
import ap.utils.Fu; 
import ap.utils.MapUtils; 
import ap.utils.Su; 

public class ActionHelper { 

	 
	
	public static boolean execute(TPanel initiator, String action) 
	{ 
	 
			Object v = new Object() {}; 
			 
			String do_prefix = "do_form_"; 
			 
			if (action.startsWith(do_prefix)) 
			   { 
				 
				TPanel form = initiator.getManager().getGrandForm(initiator.getPid()); 

				boolean bl_form_presents = (form != null); 
				 
				gl.tracex(v, String.format("%s...find main form...action...%s...isa form...%s", gl.S_OK,action,bl_form_presents)); 

				// Extract composite name. 
				String composite = Su.AfterAtFirst(action,do_prefix); 
				 
				// Make path. 
				String composite_file = String.format("%s\\test\\form\\%s.txt",Fu.getCurrentDir(),composite); 
				 
				gl.tracex(v, String.format("%s...Composite name...%s...path...%s", gl.S_OK,composite,composite_file)); 
				 
				 
				return initiator.getManager().do_form(composite_file); 
				 
			} 
			else if (action.equalsIgnoreCase("close") 
					|| action.equalsIgnoreCase("form.close") 
					|| action.equalsIgnoreCase("form.cancel") 
					|| action.equalsIgnoreCase("cancel") 
					) { 
				TPanel form = initiator.getManager().getGrandForm(initiator.getPid()); 

				if (form == null) { 

					gl.tracex(v, String.format("Find main form...%s", "Error")); 

					return false; 
				} 

				gl.tracex( 
						v, 
						String.format("Find main form...%s...%s", 
								form.toStringForTest(), "Ok")); 

				int form_id = form.getId(); 

				if (form_id != gl.E_ERROR) 
				{ 
					 
					initiator.getManager().getFrame().setModal_result(action); 
					 
					return initiator.getManager().action_form_close(form); 
				} 
				else 
					return false; 
				 
			} else	if (action.equalsIgnoreCase("ok") 
					|| action.equalsIgnoreCase("form.ok") 
					|| action.equalsIgnoreCase("gut") 
					) { 
				TPanel form = initiator.getManager().getGrandForm(initiator.getPid()); 

				if (form == null) { 

					gl.tracex(v, String.format("Find main form...%s", "Error")); 

					return false; 
				} 

				gl.tracex( 
						v, 
						String.format("Find main form...%s...%s", 
								form.toStringForTest(), "Ok")); 

				int form_id = form.getId(); 

				if (form_id != gl.E_ERROR) 
				{ 
					 
					initiator.getManager().getFrame().setModal_result(action); 
					 
					return initiator.getManager().action_form_close(form); 
				} 
				else 
					return false; 
				 
				 
			} else if (action.equalsIgnoreCase("exit") 
					|| action.equalsIgnoreCase("app.exit")) { 
				System.exit(gl.E_EMPTY); 

				return true; 
			} else if (action.equalsIgnoreCase("icon") 
					|| action.equalsIgnoreCase("app.icon")) { 

				TFrame.HDC.setState(Frame.ICONIFIED); 

				return true; 
			} else if (action.equalsIgnoreCase("max") 
					|| action.equalsIgnoreCase("maximize") 
					|| action.equalsIgnoreCase("form.max")) { 

				// Get client_area 

				initiator.getManager().getForm_map().clear(); 

				Rectangle client_rect = initiator.getManager().getFrame().getBounds(); 

				initiator.getManager().getForm_map().put("max", client_rect); 

				TPanel form = initiator.getManager().getGrandForm(initiator.getPid()); 

				if (form == null) { 

					gl.tracex(v, String.format("Find main form...%s", "Error")); 

					return false; 
				} 

				gl.tracex( 
						v, 
						String.format("Find main form...%s...%s...%s", 
								form.toStringForTest(), action, "Ok")); 

				initiator.getManager().getForm_map() 
						.put("original_rect", form.getBounds()); 

				gl.tracex( 
						v, 
						String.format("Set form to max mode...%s...%s", 
								SBounds.toString(client_rect), "Try")); 

				form.setBou_nds(MapUtils.findValueByKey(initiator.getManager() 
						.getForm_map(), "max")); 
				 
					 
				String new_action = "min"; 

				initiator.getManager().getCollector() 
						.updateAction(form, "max", "min"); 

				gl.tracex(v, 
						String.format("Update action ...%s...%s", new_action, "Ok")); 

				return true; 
			} 

			else if (action.equalsIgnoreCase("min") 
					|| action.equalsIgnoreCase("minimaze") 
					|| action.equalsIgnoreCase("form.minimaze")) { 

				TPanel form = initiator.getManager().getGrandForm(initiator.getPid()); 

				if (form == null) { 

					gl.tracex(v, String.format("Find main form...%s", "Error")); 

					return false; 
				} 

				gl.tracex( 
						v, 
						String.format("Find main form...%s...%s...%s", 
								form.toStringForTest(), action, "Ok")); 

				form.setBou_nds(MapUtils.findValueByKey(initiator.getManager() 
						.getForm_map(), "original_rect")); 

				String new_action = "max"; 

				initiator.getManager().getCollector() 
						.updateAction(form, "min", "max"); 

				gl.tracex(v, 
						String.format("Update action ...%s...%s", new_action, "Ok")); 

				return true; 
			} 
			 
				else if (action.equalsIgnoreCase("clear data all") || action.equalsIgnoreCase("cda")) 
			{ 
					//return initiator.clearChart(); 
			 
			}else if (action.equalsIgnoreCase("clear data local") || action.equalsIgnoreCase("cdl")) 
			{ 
					//return initiator.clearChart(); 
			} 
			else if (action.equalsIgnoreCase("refresh local data") || action.equalsIgnoreCase("rld")) 
			{ 
				//return (initiator.clearLocalDataAll(this) && initiator.refreshChartSeries(this)); 
			} 
			else if (action.equalsIgnoreCase("load data") || action.equalsIgnoreCase("ld")) 
			{ 
				 
				TPanel owner = initiator.getManager().getGrandForm(initiator.getPid()); 
				 
				if(owner != null) 
				{ 
					return ((TChart)owner).load_quotes_suite((TChart)owner); 
				} 
					return true; 
					 
			} 
			else if (action.equalsIgnoreCase("chart_step_right") || action.equalsIgnoreCase("csr")) 
			{ 
				return initiator.chartStepSuite(initiator,true,gl.E_OK); 
				 
			} 
			else if (action.equalsIgnoreCase("chart_step_left") || action.equalsIgnoreCase("csl")) 
			{ 
				return initiator.chartStepSuite(initiator,false,gl.E_OK); 
				 
			} 
			else if (action.equalsIgnoreCase("chart_page_left") || action.equalsIgnoreCase("cpl")) 
			{ 
				 return initiator.chartStepSuite(initiator,false,TChart.PAGE_SIZE); 
						 
			} 
			else if (action.equalsIgnoreCase("chart_page_right") || action.equalsIgnoreCase("cpr")) 
			{ 
				return initiator.chartStepSuite(initiator,true,TChart.PAGE_SIZE); 
				 
			} 
			 
			 
			else if (action.equalsIgnoreCase("setup_color_up") || action.equalsIgnoreCase("scu")) 
			{ 
					TPanel main_form = initiator.getManager().getGrandForm(initiator.getPid()); 

					if (main_form == null) { 

						gl.tracex(v, String.format("%s...find main form...action...%s",gl.S_ERROR,action)); 

						return false; 
					} 

					gl.tracex(v, String.format("%s...find main form id ...%d...action...%s",gl.S_OK,main_form.getId(),action)); 
					 
					Color c = JColorChooser.showDialog(null, "Choose a color for up direction...", main_form.getBackground()); 
					 
					if(c == null) 
					{ 
						 
						gl.tracex(v, String.format("%s...get color from the form...action...%s",gl.S_ERROR,action)); 
						 
						return false; 
					} 
					 
						gl.tracex(v, String.format("%s...get color from the form...%s...action...%s",gl.S_OK,SColor.toString(c),action)); 
					 
						main_form.setUp_color(c); 
					 
					    // Send value to the subscribers. 
						String lookup = String.format("%s_receiver",action); 
								 
						List<TPanel> receivers = main_form.getManager().getCollector().get_list_by_pid_and_action_and_type(main_form.getId(),lookup,"TLabel"); 
						 
						// gl.smn("Lookup for an action " + lookup + " size " + receivers.size() ); 
						 
						initiator.setGradient(CU.getAlphaColor(c,255)); 
						 
						initiator.setGradient_type(gl.E_EMPTY); 
						 
						initiator.repaint(); 
						 
						receivers.forEach(a-> 
						{ 
							a.setText_color(c); 
							 
							a.repaint(); 
						} 
						); 
						 
						return true; 
				} 

				 
				else if (action.equalsIgnoreCase("setup_color_down") || action.equalsIgnoreCase("scd")) 
				{ 
				TPanel main_form = initiator.getManager().getGrandForm(initiator.getPid()); 

				if (main_form == null) { 

					gl.tracex(v, String.format("%s...find main form...action...%s",gl.S_ERROR,action)); 

					return false; 
				} 

				gl.tracex(v, String.format("%s...find main form id ...%d...action...%s",gl.S_OK,main_form.getId(),action)); 
				 
				Color c = JColorChooser.showDialog(null, "Choose a color for down direction...", main_form.getBackground()); 
				 
				if(c == null) 
				{ 
					 
					gl.tracex(v, String.format("%s...get color from the form...action...%s",gl.S_ERROR,action)); 
					 
					return false; 
				} 
				 
					gl.tracex(v, String.format("%s...get color from the form...%s...action...%s",gl.S_OK,SColor.toString(c),action)); 
				 
					main_form.setDown_color(c); 
				 
				    // Send value to the subscribers. 
					String lookup = String.format("%s_receiver",action); 
							 
					List<TPanel> receivers = main_form.getManager().getCollector().get_list_by_pid_and_action_and_type(main_form.getId(),lookup,"TLabel"); 
					 
					initiator.setGradient(CU.getAlphaColor(c,255)); 
					 
					initiator.setGradient_type(gl.E_EMPTY); 
					 
					initiator.repaint(); 
					 
					 
					receivers.forEach(a-> 
					{ 
						a.setText_color(c); 
			 
						a.repaint(); 
					} 
					); 
				 
					 
							return true; 

							 
			}else 
				 
			// TODO ACTION SWITCH.
				
			if (action.startsWith("switch_")) 
			{ 
			 
				TPanel owner = initiator.getManager().getGrandForm(initiator.getPid()); 
			 
				if(owner == null)
					return false;
				
				//Отловить событие GroupBox для взимозависимых кнопок.
			
				// Группа зависимых.
				
				String [] groups = new String [] {
						 "switch_bar_paint",
						 "switch_candle_paint",
						 "switch_line_paint"
						 };
				
				if(Su.in(action.trim().toLowerCase(),groups))
				{
					
					if(((TPushBtn)initiator).getState() == TBtn.PUSHED)
					{
						initiator.getManager().getCollector().get_list_by_pid_and_actions(initiator.getPid(),groups).stream()
						.filter(b->(!b.getAction().equalsIgnoreCase(initiator.getAction()))).forEach(a->{
							
							// Сброс всех остальных кроме ИНИЦИАТОРА.
							/*
							gl.sfn("----> [%s] [%s] State [%d] [%d]",
									initiator.getAction(),
									a.getAction(),
									((TPushBtn)initiator).getState(),
									((TPushBtn)a).getState()
									);
							*/
							
							((TPushBtn)a).changeState(TBtn.NORMAL);
							
							// Состояние ИНИЦИАТОРА уже зафиксировано.
							
						});
						
					}  // Если нажата.	
				
			} // Группа графических.
			else if(action.trim().toLowerCase().equalsIgnoreCase("switch_type_paint"))
			{
					//gl.sfn("------> [%s]",action);
					
			}
	
			    return gl.tx(new Object(){},
			    		((TChart)owner).update_paint_map() && ((TChart)owner).updateComponents(),
			    		gl.sf("Обновление после выполнения...[%s]",action)); 
					
			} // switch ... 
			else if (action.equalsIgnoreCase("spin_width_inc")) 
			{ 

				TPanel owner = initiator.getManager().getGrandForm(initiator.getPid()); 
			 
				if(owner != null) 
				{ 
					if(TChart.MIN_WIDTH < 100) 
					   TChart.MIN_WIDTH += 1; 
			
					return gl.tx(v,
							initiator.chartStepSuite(initiator,true,gl.E_EMPTY),
							gl.sf("%s...Инкремент ширины БАРа...[%d]",
									action,
									TChart.MIN_WIDTH)
							);
							
			 		 
				} 
		 
			} // spin_inc... 
			else if (action.equalsIgnoreCase("spin_width_dec")) 
			{ 

				TPanel owner = initiator.getManager().getGrandForm(initiator.getPid()); 
			 
				if(owner != null) 
				{ 
					 
					if(TChart.MIN_WIDTH > gl.E_EMPTY) 
					   TChart.MIN_WIDTH -= 1; 
					  
					return gl.tx(v,
							initiator.chartStepSuite(initiator,true,gl.E_EMPTY),
							gl.sf("%s...Декремент ширины БАРа...%d",
									action,
									TChart.MIN_WIDTH)
							);
							
			 
				} 
		 
			} // spin_dec ... 
			else if (action.trim().equalsIgnoreCase("export_action_list") || action.trim().equalsIgnoreCase("eal")) 
			{ 
				return initiator.getManager().exportActionList(); 
			} 


				gl.tracex(v, String.format("%s...Not yet implementation for action...%s",gl.S_OK,action)); 
		 
				return true; 
		 
	} 
	 
	 
	public ActionHelper() { 
		 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
