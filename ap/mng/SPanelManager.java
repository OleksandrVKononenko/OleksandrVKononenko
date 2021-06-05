 
package ap.mng; 

import ap.btn.TSphere; 

import java.awt.Color; 
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Panel; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import java.io.UnsupportedEncodingException; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.Set; 
import java.util.Spliterators; 
import java.util.Vector; 
import java.util.concurrent.atomic.AtomicLong; 
import java.util.stream.Collectors; 
import java.util.stream.StreamSupport; 

import javax.swing.JFileChooser; 
import javax.swing.JMenuItem; 
import javax.swing.JOptionPane; 
import javax.swing.JPopupMenu; 
import javax.swing.SwingConstants; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 
import javax.swing.filechooser.FileNameExtensionFilter; 

import ap.area.AreaManager; 
import ap.btn.TBar; 
import ap.btn.TBtn; 
import ap.btn.TChart; 
import ap.btn.TDashBoard; 
import ap.btn.TDataPanel; 
import ap.btn.TImage; 
import ap.btn.TPopupBtn; 
import ap.btn.TPopupPushBtn; 
import ap.btn.TPushBtn; 
import ap.btn.TLabel; 
import ap.btn.TLine; 
import ap.btn.TTick; 
import ap.btn.TSphere; 
import ap.collectors.Collector; 
import ap.collectors.SPanelCollector; 
import ap.config.TConfig; 
import ap.frame.TFrame; 
import ap.gen.GIDGen; 
import ap.gen.IDGen; 
import ap.global.gl; 
import ap.log.Logger; 
import ap.lsnrs.SManagerActionPerformers; 
import ap.lsnrs.SPanelActionPerformers; 
import ap.menu.SMenu; 
import ap.menu.SMenuItem; 
import ap.btn.TPanel; 
import ap.prop.BaseProperty; 
import ap.prop.SBounds; 
import ap.prop.SColor; 
import ap.prop.SDimension; 
import ap.prop.SInt; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.swing.TCheckBox; 
import ap.swing.TRadioButton; 
import ap.swing.TTextArea; 
import ap.swing.TTextField; 
import ap.utils.Bau; 
import ap.utils.CU; 
import ap.utils.DialogUtil; 
import ap.utils.Fu; 
import ap.utils.GU; 
import ap.utils.ImageUtil; 
import ap.utils.MapUtils; 
import ap.utils.Su; 

public class SPanelManager extends SPanelManagerDef { 

	private static final long serialVersionUID = 2L; 

	private Dimension establish_order = new Dimension(100,1); 

	private BufferedImage snapImage = null; 

	private TPanel snapObject = null; 

	private TConfig config = new TConfig(); 

	public TConfig getConfig() { 
		return config; 
	} 

	public void setConfig(TConfig config) { 
		this.config = config; 
	} 

	public BufferedImage getSnapImage() { 
		return snapImage; 
	} 

	public void setSnapImage(BufferedImage snapImage) { 
		this.snapImage = snapImage; 
	} 

	public TPanel getSnapObject() { 
		return snapObject; 
	} 

	public void setSnapObject(TPanel snapObject) { 
		this.snapObject = snapObject; 
	} 

	public Dimension getEstablish_order() { 
		return establish_order; 
	} 

	public void setEstablish_order(Dimension establish_order) { 
		this.establish_order = establish_order; 
	} 

	public Map<String, Rectangle> getForm_map() { 
		return form_map; 
	} 

	public void setForm_map(Map<String, Rectangle> form_map) { 
		this.form_map = form_map; 
	} 

	public SPanelCollector getSelectors() { 
		return selectors; 
	} 

	public void setSelectors(SPanelCollector selectors) { 
		this.selectors = selectors; 
	} 

	public SPanelCollector getAdded() { 
		return added; 
	} 

	public void setAdded(SPanelCollector added) { 
		this.added = added; 
	} 

	public SPanelCollector getSelected() { 
		return selected; 
	} 

	public void setSelected(SPanelCollector selected) { 
		this.selected = selected; 
	} 

	public SPanelCollector getDeleted() { 
		return deleted; 
	} 

	public void setDeleted(SPanelCollector deleted) { 
		this.deleted = deleted; 
	} 

	public String getHome() { 
		return this.getCollector().getHome(); 
	} 

	public void setHome(String home) { 
		this.getCollector().setHome(home); 
	} 

	public SPanelCollector getCollector() { 
		return data; 
	} 

	public void setCollector(SPanelCollector collector) { 
		this.data = collector; 
	} 

	public boolean updateIds(List<TPanel> list_to_update, int collector_max_id) { 
		 
		 
		boolean need_to_action = (this.getCollector().getSize() != gl.E_EMPTY ); 

		String msg = "Refresh ids"; 

		try { 

			if (need_to_action) { 
				 
				 
				gl.tracex(new Object() { 
				}, String.format("%s...%s...%s",gl.S_OK, msg, 
						"there is NEED some action")); 


				int [] i_gap = {(collector_max_id - this.getCollector().get_min_id( 
						list_to_update)) + 1}; 

				// Set start new sequence. 

				IDGen.counter = new AtomicLong(collector_max_id); 
				 
				list_to_update.forEach(a -> { 
					 
				int next_id = IDGen.nextId(); 
							 
					a.setId(next_id); 

					if (a.getPid() != gl.E_ERROR) { 
						 
						int i_pid = a.getPid() + i_gap[0]; 
						 
						a.setPid(i_pid); 
												 
					} 

						a.setZorder(a.getId()); 

				}); 

			} else { 

				gl.tracex(new Object() { 
				}, String.format("%s...%s...%s",gl.S_OK, msg, 
						"there is NOT need any action")); 
				 
				return true; 
			} 

		} catch (Exception e) { 

			gl.tracex(new Object() { 
			}, String.format("%s...%s",gl.S_ERROR,msg)); 

			return false; 
		} 
		 
			return true; 
		 
	} 

		public boolean read() { 

		String msg = "Read objects from file."; 

		if (!this.getFile_type().equalsIgnoreCase("TXT")) { 

			gl.tx_e(new Object() {}, gl.sf("%s...Bad file format...%s",
					msg, 
					this.getFile_type())); 

			return false; 

		} 
		
			return this.readTxt();
	} 
		
			
		public boolean getLisOfProps(String file_source, List<String> props) { 
		
			Object v = new Object() {}; 

			String source = null; 

		try { 

			// TODO Чтение проекта.  
			
			source = Fu.read_utf_8_str(file_source); 
			
			//source =  Fu.getFileAsStringScannerSkipComments(file_source); 

			//source = Fu.get_file_as_string_nio(file_source);
					
			gl.tx_k(v, 
					gl.sf( 
					 "Чтение данных...[%s][%5d]", file_source, source.length()
					 )
					); 

		} catch (Exception e) { 

			e.printStackTrace();
			
			return false; 
		} 

		String msg = "Load to primary array"; 

		String arr[] = source.split(System.lineSeparator()); 

		if (arr.length == gl.E_EMPTY) { 
			gl.tracex(v, String.format("%s...%s...size...%06d", gl.S_ERROR,msg, 
					arr.length)); 

			return false; 
		} 

		gl.tracex(v, String.format("%s...%s...size...%06d",gl.S_OK,msg, arr.length 
				)); 

		msg = "Try load to the secondary list of props"; 

		List<String> proxy_list = Arrays.asList(arr); 

		if (props == null) { 

			msg = "The receiver collection is null."; 

			gl.tracex(v, String.format("%s...%s",gl.S_ERROR, msg)); 

			return false; 
		} 

		props.clear(); 

		props.addAll(proxy_list); 

		if ((proxy_list.size() == props.size()) && (props.size() == arr.length)) 

			gl.tracex(v, String.format("%s...%s...size...%06d",gl.S_OK, msg, 
					arr.length)); 
		else { 
			gl.tracex(v, String.format("%s...%s...size...%06d <> %06d",gl.S_ERROR,msg, 
					props.size(), arr.length)); 

			return false; 
		} 

		return (props.size() != gl.E_EMPTY); 

	} 

	public boolean parsePropList(List<String> props, List<TPanel> items_store) { 
		Object v = new Object() { 
		}; 

		String msg = "Try to parse prop list"; 

		if (props == null) { 

			gl.tracex(v, String.format("%s...%s",gl.S_ERROR,"The source collection of props is null")); 

			return false; 
		} 

		if (props.size() == gl.E_EMPTY) { 
			 
			gl.tracex(v, String.format("%s...%s...%06d",gl.S_ERROR,msg, props.size())); 

			return false; 
		} 

		int error_count[] = { gl.E_EMPTY }; 

		BaseProperty prop = new BaseProperty(); 

		ArrayList<TPanel> items = new ArrayList<TPanel>(); 

		if (items_store == null) { 

			String msg_null = "The target collection object is null."; 

			gl.tracex(v, String.format("%s...%s",gl.S_ERROR,msg_null)); 

			return false; 
		} 

		items_store.clear(); 

		props.forEach(a -> { 

			if (!(a.startsWith(":") || a.startsWith("--") || a.startsWith("//") || a 
					.trim().length() == gl.E_EMPTY)) { 

				if (!prop.read(a)) 
					error_count[0]++; 
				else { 

					if (Su.in(prop.getType().getText(), new String[] { 
							"tpanel", "tsphere", "tpushbtn", "tpopuppushbtn", 
							"tpopupbtn", "tbtn", "ttextfield", "ttextarea", 
							"tradiobutton", "tlabel", "timage", "tcheckbox", 
							"tline", "tbar", "tdatapanel","ttick","tchart", 
							"tdashboard","tcanvas" ,"tcombobox" 

					})) { 
						if ("TPanel".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TPanel sp = new TPanel(prop); 

							items.add(sp); 
						}else if ("TDashBoard".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TDashBoard sp = new TDashBoard(prop); 

							items.add(sp); 
				 
						}else if ("TSphere".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TSphere sp = new TSphere(prop); 

							items.add(sp); 
						} else if ("TLine".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TLine sp = new TLine(prop); 

							items.add(sp); 
						} 

						else if ("TPushBtn".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TPushBtn sp = new TPushBtn(prop); 

							sp.setState(TBtn.NORMAL); 

							if (sp.getBor_der().equalsIgnoreCase("lbb")) 
								sp.setState(TBtn.PUSHED); 

							items.add(sp); 

						} else if ("TRadioButton".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 

							TRadioButton sp = new TRadioButton(prop); 
							 
							items.add(sp); 
						} 

						else if ("TPopupPushBtn".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TPopupPushBtn sp = new TPopupPushBtn(prop); 

							if (sp.getBor_der().equalsIgnoreCase("lbb")) 
								sp.setState(TBtn.PUSHED); 

							items.add(sp); 
						} else if ("TBtn".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							 
							TBtn sp = new TBtn(prop); 
								 
								 sp.setState(TBtn.NORMAL); 
							 
							items.add(sp); 
							 
						} else if ("TPopupBtn".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TPopupBtn sp = new TPopupBtn(prop); 

							items.add(sp); 

						} else if ("TTextField".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TTextField sp = new TTextField(prop); 

							sp.getEdit().setText(sp.getText()); 
							 
							//sp.getEdit().setBackground(sp.getBackground()); 
							 
							sp.getEdit().setForeground(sp.getText_color()); 
							 
							sp.getEdit().setFont(sp.getFont()); 
							 
							 if(sp.getAlign().getX()== gl.HVA.LEFT) 
								 sp.getEdit().setHorizontalAlignment(SwingConstants.LEFT); 
							 else if(sp.getAlign().getX()== gl.HVA.RIGHT) 
								 sp.getEdit().setHorizontalAlignment(SwingConstants.RIGHT); 
							 if(sp.getAlign().getX()== gl.HVA.CENTER) 
								 sp.getEdit().setHorizontalAlignment(SwingConstants.CENTER); 
							 
							 	items.add(sp); 
						} 

						else if ("TTextArea".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							 
							TTextArea sp = new TTextArea(prop); 

							String proxyText = ""; 

							try { 
								proxyText = Bau 
										.to_str_from_hex_str(sp.getText()); 

							} catch (Exception e) { 

								gl.smn("readTxt(ERROR) : " + e.toString()); 

							} 

							sp.getEdit().setText(proxyText); 
							 
							 
							sp.getEdit().setText(sp.getText()); 
							 
							//sp.getEdit().setBackground(sp.getBackground()); 
							 
							sp.getEdit().setForeground(sp.getText_color()); 
							 
							sp.getEdit().setFont(sp.getFont()); 
							 
							items.add(sp); 
							 
						} else if ("TLabel".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TLabel sp = new TLabel(prop); 

							items.add(sp); 

						} else if ("TImage".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TImage sp = new TImage(prop); 

							items.add(sp); 

						} else if ("TCheckBox".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TCheckBox sp = new TCheckBox(prop); 
						 
							items.add(sp); 

						} else if ("TBar".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							TBar sp = new TBar(prop); 

							items.add(sp); 

						} else if ("TTick".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							 
							TTick sp = new TTick(prop); 
							 
							items.add(sp); 

						} 
						else if ("TChart".trim().equalsIgnoreCase( 
								prop.getType().getText())) { 
							 
							TChart sp = new TChart(prop); 
							 
							items.add(sp); 

						} 
						 
					} else { 
						// Read prop was success but type name temporary absent. 
						// Set type to SNone 

						TPanel sp = new TPanel(prop); 

						sp.setType("SNone"); 

						items.add(sp); 

					} 

				} // if prop read 
			}// if valid begin of string. 
		}); // for each. 

		items_store.addAll(items); 

		boolean bl_result = (items_store.size() == props.size()) 
				&& (error_count[0] == gl.E_EMPTY); 

		if (bl_result) 
			gl.tracex(v, 
					String.format("%s...%s...%06d",gl.S_OK,msg, props.size())); 
		else 
			gl.tracex(v, String.format( 
					"%s...%s...store...%06d...props...%06d...errors...%06d", 
					gl.S_ERROR,msg, items_store.size(), props.size(), error_count[0] 
					)); 

		return bl_result; 

	} 

	 
	public boolean readTxt() { 
		
		List<String> props = new ArrayList<String>(); 

		if (!getLisOfProps(this.getHome(), props)) 
			return false; 

		List<TPanel> items = new ArrayList<TPanel>(); 

		if (!this.parsePropList(props, items)) 
			return false; 

		this.getAdded().clear(); 

		this.getAdded().addAll(items); 
		 
		boolean [] bl_result  = {false}; 
		 
			    List<TPanel> list = new ArrayList<TPanel>(); 
		 
				list.addAll(this.getAdded().getData()); 
				 
				int max_id =  getCollector().get_max_id(); 
				 
				if(max_id == gl.E_ERROR || max_id == gl.E_EMPTY) 
				{ 
					gl.tracex(new Object(){},String.format("%s...RESET MAX_ID...%d...%d",gl.S_OK,max_id,gl.E_OK)); 
					 
					max_id = gl.E_OK; 
					 
				} 
		 
				if (!this.updateIds(list,max_id)) 
				{ 
					gl.tracex(new Object(){},String.format("%s...Update IDS",gl.S_ERROR)); 
					 
					return false; 
				} 
		 
				bl_result[0] = (this.getAdded().size() == items.size()); 
				 
				if(bl_result[0]) 
				gl.tracex(new Object(){},String.format("%s...Load composite...%s",gl.S_OK,this.getHome())); 
				else 
				gl.tracex(new Object(){},String.format("%s...Load composite...%s",gl.S_ERROR,this.getHome())); 
								 
		 		return (bl_result[0]); 

	} 

	public boolean write() { 

		this.setFile_type("TXT"); 

		if (this.getFile_type().equalsIgnoreCase("TXT")) 
		{ 
			return this.writeTxt(); 
		} 

			return false; 
	} 

	public boolean writeTxt() { 

		Object v = new Object() {}; 
		
		String home = String.format("%s%s.%s", 
				Fu.get_path(this.getHome()), 
				Fu.get_pure_file_name(this.getHome()), "txt"); 
		
		this.setHome(home); 

		List<Boolean> 	state = new ArrayList<Boolean>(); 

		boolean 		bl_selected = (this.getSelected().size() != gl.E_EMPTY); 
		
		StringBuilder 	sb = new StringBuilder();
		
		List<TPanel> 	target_list = new ArrayList<TPanel>();
		
		// Консолидируем информацию в единый источник.
		
		if (bl_selected) { 

			gl.tx(v,true,gl.sf("Селектировано объектов для записи...[%02d] [%s]",this.getSelected().size(),bl_selected));
	
			target_list.addAll(this.getSelected().getData());
			
		} else {
			
			gl.tx(v,true,gl.sf("Объектов для записи...[%02d] [%s]",this.getCollector().getData().size(),bl_selected));

			target_list.addAll(this.getCollector().getData());
		}
		
		
		// Формируем единый пакет для записи.
		
		target_list.forEach(t->{
				
				t.setOriginal_bounds(t.getBounds());
				
				sb.append(gl.sf("%s%s",t.toString(),System.lineSeparator()));

			});


		String out = sb.toString();
		
		//gl.sfn("---> %s",sb.toString());
				
		state.add(Fu.to_file_x(this.getHome(),out,false));

		gl.tx(v,
				!state.contains(false),
				gl.sf("Сохранение проекта в файл...[%s]",this.getHome())
			 );
		
		
		this.getConfig().getLook_and_fill() 
				.updateValue(UIManager.getLookAndFeel().getName()); 

		this.getConfig().getDesktop_bounds() 
				.updateValue(SBounds.toString(TFrame.HDC.getBounds())); 

		this.getConfig().getRecent_form().updateValue(this.getHome()); 

		gl.tx(v,this.getConfig().write(),gl.sf("Обновление файла конфигурации."));
	
		this.getFrame().updateTitle(this.getHome()); 

		return !state.contains(false); 
	} 

	 
	public boolean remove() { 
		return this.getCollector().gc(); 
	} 

	public int getSize() { 
		return this.getCollector().getSize(); 
	} 

	public void printAll() { 
		this.getCollector().getData().forEach(a -> { 
			System.out.println(a.toString()); 
		} 

		); 
	} 

	public SPanelManager() { 
		this("default_home.xml"); 
	} 

	public SPanelManager(String home) { 

		this.setCollector(new SPanelCollector(home)); 

	} 

	public TFrame getFrame() { 
		return frame; 
	} 

	public void setFrame(TFrame frame) { 
		this.frame = frame; 
	} 

	public boolean createGUI() { 
		 
		TFrame frame = TFrame.createUI(); 

		frame.setManager(this); 

		this.setFrame(frame); 

		return (this.getFrame() != null); 
	} 

	public boolean OnSelect(TPanel panel) { 
		this.getFrame().remove(panel); 

		return !this.getFrame().isa(panel); 

	} 

	public boolean removeAll() { 
		Object value = new Object() { 
		}; 

		String msg = "Try to remove all components"; 

		gl.tracex(value, String.format("%s...", msg)); 

		if (this.getFrame().getComponentCount() != gl.E_EMPTY) 
			this.getFrame().removeAll(); 

		if (this.getFrame().getComponentCount() != gl.E_EMPTY) { 
			gl.tracex(value, String.format("%s...%s", msg, "Error")); 

			return false; 
		} 

		gl.tracex(value, String.format("%s...%s", msg, "Ok")); 

		this.getFrame().repaint(); 

		return true; 

	} 
	public boolean insertAll(Vector<TPanel> list_to_add) { 
		Object value = new Object() { 
		}; 

		String msg = String.format("Insert components"); 

		try { 

			list_to_add.forEach(a -> { 

				a.setManager(this); 

				}); 

			this.getCollector().addAll(list_to_add); 

			list_to_add.forEach(a -> { 

				this.getFrame().add(a); 

			}); 
			 
			gl.tracex(value, String.format("%s...%s...%s...%d....%s...%d",gl.S_OK,msg, 
					"frames count", this.getFrame().getComponentCount(), 
					"mngr count :", this.getSize())); 


			this.getAdded().clear(); 

			list_to_add.clear(); 

			this.getFrame().repaint(); 

		} catch (java.lang.IllegalArgumentException e) { 

			gl.tracex(value, String.format("%s...%s...%s",gl.S_ERROR,msg, e.toString().substring(0,e.toString().length()), 
					"exception")); 

			return false; 

		} 

		if (this.getFrame().getComponentCount() != this.getSize()) { 
			gl.tracex(value, String.format("%s...%s...%s...%d<>%d", msg, 
					gl.S_ERROR, "check", this.getFrame().getComponentCount(), this 
							.getCollector().getData().size())); 

			return false; 
		} 

		gl.tracex(value, String.format("%s...%s...%s...%d=%d",gl.S_OK, msg, 
				"check", this.getFrame().getComponentCount(), this.getSize())); 

		return true; 

	} 

	public boolean pushNewObjectToFly(TPanel sp) { 

		return (this.getAdded().add(sp) 
				&& this.insertAll(this.getAdded().getData()) && this 
					.refreshZorder()); 
	} 

	// Must be set parent_id. 
	public TPanel getGrandForm(int pid) { 
		int form_id = this.getCollector().getParentFormId(pid); 

		if (form_id == gl.E_ERROR) 
			return null; 

		return this.getCollector().get_by_id(form_id); 

	} 
	 
	public TPanel getGrandForm(TPanel owner) { 
		 
		if(owner.getPid() == gl.E_ERROR) 
			return owner; 
		 
		int form_id = this.getCollector().getParentFormId(owner.getPid()); 

		if (form_id == gl.E_ERROR) 
			return null; 

		return this.getCollector().get_by_id(form_id); 

	} 

	public boolean createObject(String type_name) { 
		Object v = new Object() { 
		}; 

		String msg = "Check input type"; 

		gl.tracex(v, String.format("%s...%s...%s", msg, "input", type_name)); 

		Rectangle rect = null; 

		if (this.getSelectors().size() != gl.E_EMPTY) { 

			TPanel pp = this.getSelectors().getIndex( 
					this.getSelectors().getSize() - 1); 

			rect = pp.getBounds(); 

		} else { 
			rect = Ru.getCenterWindow(this.getFrame().getBounds(), 
					new Rectangle(0, 0, 32, 32)); 
		} 

		if (type_name.equalsIgnoreCase("TPanel")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TPanel sp = TPanel.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		 
		} else if (type_name.equalsIgnoreCase("TSphere")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TSphere sp = TSphere.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} 

		else if (type_name.equalsIgnoreCase("TPushBtn")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TPushBtn sp = TPushBtn.getInstance(gl.E_OK); 
	 
			return pushNewObjectToFly(sp); 

		} else if (type_name.equalsIgnoreCase("TPopupPushBtn")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TPopupPushBtn sp = TPopupPushBtn.getInstance(rect); 

			sp.setState(TBtn.SLEEP); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TPopupBtn")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TPopupBtn sp = TPopupBtn.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TBtn")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TBtn sp = TBtn.getInstance(rect,gl.E_EMPTY); 
			 
			return pushNewObjectToFly(sp); 
			 
		} else if (type_name.equalsIgnoreCase("TTextField")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TTextField sp = TTextField.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TTextArea")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TTextArea sp = TTextArea.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TCheckBox")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TCheckBox sp = TCheckBox.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TRadioButton")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TRadioButton sp = TRadioButton.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TLabel")) { 
			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TLabel sp = TLabel.getInstance(rect); 
				 
				   	 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TImage")) { 

			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TImage sp = TImage.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else 
		if (type_name.equalsIgnoreCase("TBar")) { 

			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "try to ", type_name)); 

			if (!this.cloneCompositeSuite(TBar.MODEL, 1)) { 
				gl.tracex(v, String.format("%s...%s...%s...$s", msg, "try to ", 
						type_name, gl.S_ERROR)); 

				return false; 
			} 

			gl.tracex(v, String.format("%s...%s...%s...$s", msg, "try to ", 
					type_name, gl.S_OK)); 

			return true; 
		} else if (type_name.equalsIgnoreCase("TChart")) { 

			gl.tracex(v, 
					String.format("%s...%s...%s", msg, "in process", type_name)); 

			TChart sp = TChart.getInstance(rect); 
			 
					sp.setFunction("TChart"); 

			return pushNewObjectToFly(sp); 
			 
		} 
			return false; 

	} 

	public boolean createObject(String type_name, Rectangle rect) { 
		Object v = new Object() { 
		}; 

		String msg = "Create object"; 

		gl.tracex(v, String.format("%s...%s...%s", msg, "input", type_name)); 

		if (type_name.equalsIgnoreCase("TPanel")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TPanel sp = TPanel.getInstance(rect); 

			return pushNewObjectToFly(sp); 

		} else if (type_name.equalsIgnoreCase("TSphere")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TSphere sp = TSphere.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TPushBtn")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			this.getAdded().addAll( 
					TPushBtn.getInstances(new Dimension(10, 10), 1)); 

			if (!this.insertAll(this.getAdded().getData())) { 
				gl.tracex(v, String.format( 
						"Error while add manager object...%s.", gl.S_ERROR)); 

				return false; 
			} else 
				return true; 

		} else if (type_name.equalsIgnoreCase("TPopupPushBtn")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TPopupPushBtn sp = TPopupPushBtn.getInstance(rect); 

			sp.setState(TBtn.SLEEP); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TPopupBtn")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TPopupBtn sp = TPopupBtn.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TBtn")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TBtn sp = TBtn.getInstance(rect); 

			this.getAdded().addAll(TBtn.getInstances(new Dimension(10, 10), 1)); 

			if (!this.insertAll(this.getAdded().getData())) { 
				gl.tracex(v, String.format( 
						"Error while add manager object...%s.", gl.S_ERROR)); 

				return false; 
			} else 
				return true; 
		} else if (type_name.equalsIgnoreCase("TTextField")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TTextField sp = TTextField.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TTextArea")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TTextArea sp = TTextArea.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TCheckBox")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TCheckBox sp = TCheckBox.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TRadioButton")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TRadioButton sp = TRadioButton.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TLabel")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TLabel sp = TLabel.getInstance(rect); 
			 
					sp.setText("Change it."); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TImage")) { 

			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TImage sp = TImage.getInstance(rect); 

			return pushNewObjectToFly(sp); 
		} else if (type_name.equalsIgnoreCase("TBar")) { 

			gl.tracex(v, String.format("%s...%s...%s", msg, "catch", type_name)); 

			TPanel sp = TBar.getInstance(rect); 

			return pushNewObjectToFly(sp); 

		} 

		else 
			return false; 

	} 

	public List<TPanel> getReverseOrder() { 
		List<TPanel> result_list = new ArrayList<TPanel>(); 

		List<TPanel> work_list = new ArrayList<TPanel>(); 

		work_list.addAll(this.getCollector().getData()); 

		StreamSupport.stream( 
				Spliterators.spliterator(MapUtils.revit(work_list), 
						work_list.size(), 0), false).forEachOrdered(b -> { 
			result_list.add(b); 
		}); 

		return result_list; 

	} 

	public boolean refreshZorder() { 
		this.getFrame().removeAll(); 

		List<TPanel> l = this.getReverseOrder(); 

		if (l.size() == gl.E_EMPTY) { 
			gl.tracex(new Object() { 
			}, String.format("%s...%s",gl.S_ERROR,"Refresh order")); 

			return false; 
		} 

		int c_size = l.size(); 

		for (int i = 0; i < c_size; i++) { 
			l.get(i).setZorder(i); 
		} 

		l.forEach(a -> { 

			this.getFrame().add(a); 

			a.componentResized(null); 

		}); 

		gl.tracex(new Object() { 
		}, String.format("%s...%s",gl.S_OK, "Refresh order")); 

		return true; 

	} 

	public boolean refreshZorder(int target) { 
		this.getFrame().removeAll(); 

		List<TPanel> l = this.getReverseOrder(); 

		int c_size = l.size(); 

		for (int i = 0; i < c_size; i++) { 
			l.get(i).setZorder(gl.E_ERROR); 
		} 

		for (int i = 0; i < c_size; i++) { 
			if (l.get(i).getId() == target) 
				l.get(i).setZorder(gl.E_EMPTY); 
		} 

		for (int i = 0; i < c_size; i++) { 
			if (l.get(i).getZorder() == gl.E_ERROR) 
				l.get(i).setZorder(i); 
		} 

		l.forEach(a -> { 

			this.getFrame().add(a); 

			a.componentResized(null); 

		}); 

		this.getFrame().repaint(); 

		return true; 

	} 

	public boolean refreshLite() { 
		Object value = new Object() { 
		}; 

		String msg = String.format("Try to refresh lite...%s...%d", "count", 
				this.getSize()); 

		gl.tracex(value, String.format("%s", msg)); 

		this.getFrame().removeAll(); 

		if (!this.insertAll(this.getAdded().getData())) { 

			gl.tracex(value, 
					String.format("%s...%s", msg, "error while execute")); 

			return false; 
		} 

		gl.tracex(value, String.format("%s...%s...%s...%d", msg, "Ok.", 
				"frame consist", this.getFrame().getComponentCount())); 

		this.getFrame().repaint(); 

		return true; 
	} 

	 
	public String getFile_type() { 
		return file_type; 
	} 

	public void setFile_type(String file_type) { 
		this.file_type = file_type; 
	} 

	 
	public static void startUp() { 
		Object v = new Object() { 
		}; 

		SPanelManager mng = new SPanelManager(); 

		if (!mng.createGUI()) 
			gl.tracex(v, String.format("Try to create GUI...Error.")); 
		else 
			gl.tracex(v, String.format("Try to create GUI...Ok.")); 

		gl.tracex(v, String.format("Try to generate some test object...")); 

		/* 
		mng.getAdded().addAll( 
				TPanel.getTestSPanelInstances(new TPanel(), new Dimension(10, 
						10), 1)); 
		*/ 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v, 
					String.format("Error while add manager object...Error.")); 

	} 

	public static void main(String[] args) { 

		String home = "data/a_test_template.txt"; 

		// doTest_startUp(home); 

		startUp(); 

	} 

	public void resetSelected() { 
		this.getCollector().getData().forEach(a -> { 
			if (a.isSelected()) { 
				a.setSelected(false); 

				a.repaint(); 

			} 

		}); 
	} 

	public void resetSelected(SPanelCollector list) { 
		list.getData().forEach(a -> { 
			a.setSelected(false); 

			a.repaint(); 

		}); 
	} 

	public boolean fileSave() { 

		Object v = new Object() { 
		}; 

		gl.tracex(v, "Try to save file ..."); 

		if (!this.write()) { 
			gl.tracex( 
					v, 
					String.format("Try save to home...%s...%s...%d", 
							this.getHome(), "Error", this.getSize())); 

			return false; 
		} 

		gl.tracex(v, String.format("Try save to home...%s...%s...%d", 
				this.getHome(), "Ok", this.getSize())); 

		return true; 

	} 

	public boolean isSuiteWasSavedBefore() { 
		return !"default_home.xml".equalsIgnoreCase(this.getHome()); 
	} 

	public boolean fileSaveAs(String home) { 
		Object v = new Object() { 
		}; 

		gl.tracex(v, String.format("Try to save to ...%s...by shorkeys", home)); 

		if (!this.write()) { 
			gl.tracex(v, String.format( 
					"Save suite to the file...%s...by shorkeys...%s", home, 
					"Error")); 

			return false; 
		} 
		gl.tracex(v, String.format( 
				"Save suite to the file...%s...by shorkeys...%s", home, "Ok")); 

		this.setHome(home); 

		return true; 

	} 

	public boolean fileSaveAsDlg(String[] file_that_will_be_saved) { 
		Object v = new Object() { 
		}; 

		JFileChooser save_file_chooser = new JFileChooser(); 

		String default_dir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		save_file_chooser 
				.setCurrentDirectory(new File(Fu.getCurrentDir())); 

		gl.tracex(v, String.format("Set file default dir...%s", default_dir)); 

		if (save_file_chooser.showSaveDialog(this.getFrame()) == JFileChooser.APPROVE_OPTION) { 
			File file = save_file_chooser.getSelectedFile(); 

			file_that_will_be_saved[0] = file.getAbsolutePath(); 

			return true; 
		} 

		return false; 

	} 

	 

	public String fileOpenDlg() { 

		Object v = new Object() { 
		}; 

		JFileChooser fo = new JFileChooser(); 

		String defaultDir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		fo.setCurrentDirectory(new File(defaultDir)); 

		gl.tracex(v, 
				String.format("Try to set file default dir...%s", defaultDir)); 

		fo.setCurrentDirectory(new File(this.getHome())); 

		int i_result = fo.showOpenDialog(null); 

		if (i_result != JFileChooser.APPROVE_OPTION) { 
			gl.tracex(v, 
					String.format("%s...%s", "Open file", "operation canceled")); 

			return null; 

		} 

		File selected_file = fo.getSelectedFile(); 

		gl.tracex( 
				v, 
				String.format("%s...%s", "Open file", 
						selected_file.getAbsolutePath())); 

		return selected_file.getAbsolutePath(); 

	}// fileOpenDlg 

	public String fileOpenDlg(String caption) { 

		Object v = new Object() { 
		}; 

		JFileChooser fo = new JFileChooser(); 

		String defaultDir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		fo.setCurrentDirectory(new File(defaultDir)); 

		gl.tracex(v, 
				String.format("%s...Set DEFAULT dir...%s",gl.S_OK,defaultDir)); 

		fo.setCurrentDirectory(new File(this.getHome())); 

		fo.setDialogTitle(caption); 

		fo.setDoubleBuffered(true); 

		int i_result = fo.showOpenDialog(null); 

		if (i_result != JFileChooser.APPROVE_OPTION) { 
			gl.tracex(v, 
					String.format("%s...%s...%s",gl.S_WARN, caption, "operation canceled")); 

			return null; 

		} 

		File selected_file = fo.getSelectedFile(); 

		gl.tracex( 
				v, 
				String.format("%s...%s...%s", 
						gl.S_OK, 
						caption, 
						selected_file.getAbsolutePath())); 

		return selected_file.getAbsolutePath(); 

	}// fileOpenDlg 

	public File[] getFilesDlg(String defaultDir, 
			FileNameExtensionFilter[] filters) { 

		Object v = new Object() { 
		}; 

		String fabula = "Open file(s) dialog"; 

		File[] files = new File[] {}; 

		JFileChooser fo = new JFileChooser(); 

		fo.setCurrentDirectory(new File(defaultDir)); 

		fo.setAcceptAllFileFilterUsed(true); 

		List<FileNameExtensionFilter> list = Arrays.asList(filters); 

		list.forEach(a -> 

		{ 
			fo.addChoosableFileFilter(a); 
		}); 

		gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,fabula, defaultDir)); 

		fo.setCurrentDirectory(new File(this.getHome())); 

		fo.setMultiSelectionEnabled(true); 

		int i_result = fo.showOpenDialog(null); 

		if (i_result != JFileChooser.APPROVE_OPTION) { 
			 
			gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,fabula,"cancel dialog")); 

			return null; 
		} 

		files = fo.getSelectedFiles(); 

		gl.tracex(v, String.format("%s...%s...%s...%d",gl.S_OK, fabula, "selected files :",files.length)); 

		return files; 

	}// fileOpenDlgMulti 

	// 

	public boolean createCloneCollection(String file_name, int count, 
			List<TPanel> target) { 

		// Load primary set into Added collection. 

		Object v = new Object() { 
		}; 

		List<TPanel> clone_collection = new ArrayList<TPanel>(); 

		// Load props. 
		List<String> props = new ArrayList<String>(); 

		if (!getLisOfProps(file_name, props)) { 
			gl.tracex(v, String.format("Read composite file...%s", gl.S_ERROR)); 

			return false; 

		} 

		BaseProperty prop = new BaseProperty(); 

		int collector_max_id = this.getCollector().get_max_id(); 

		boolean startup_set_gap = false; 

		int gid[] = { GIDGen.nextId() }; 

		for (int i = 0; i < count; i++) { 
			props.forEach(a -> { 
				// Create n-times copy. 

				if (prop.read(a)) { 
					TPanel panel = new TPanel(prop); 

					panel.setGid(gid[0]); 

					clone_collection.add(panel); 

				} 

			}); 

			// One time action. 
			if (!startup_set_gap) { 
				if (collector_max_id == gl.E_ERROR) { 
					// Set to equals as template_max_id. 

					int collector_max_id_new = this.getCollector().get_max_id( 
							clone_collection); 

					gl.tracex(v, String.format( 
							"Reset MAX id from ...%d...to...%d", 
							collector_max_id, collector_max_id_new)); 

					collector_max_id = collector_max_id_new; 

				} 

				startup_set_gap = true; 
			} 

			if (!this.updateIds(clone_collection, collector_max_id)) { 
				gl.tracex(v, String.format("Update ids...%s", gl.S_ERROR)); 
			} 

			gid[0] = GIDGen.nextId(); 

		} // end for cycle. 

		target.addAll(clone_collection); 

		return (target.size() != gl.E_EMPTY); 

	} 

	public boolean getCloneCompositeItems(String file_name, int count, 
			List<TPanel> target) { 

		Object v = new Object() { 
		}; 

		List<TPanel> clone_collection = new ArrayList<TPanel>(); 

		if (!createCloneCollection(file_name, count, clone_collection)) 
			return false; 

		if (clone_collection == null || clone_collection.size() == gl.E_EMPTY) { 

			gl.tracex(v, String.format("Source collection is empty...%s", 
					gl.S_ERROR)); 

			return false; 
		} 

		// Some shift. 
		List<TPanel> owners = this.getCollector().get_list_by_pid_cloned( 
				clone_collection, gl.E_ERROR); 

		int dx[] = { 0 }, dy[] = { 0 }; 

		owners.forEach(a -> { 
			Rectangle bounds = a.getBounds(); 

			bounds = Ru.Shift(bounds, dx[0], dy[0]); 

			dx[0] += 5; 

			dy[0] += 5; 

			a.setBou_nds(bounds); 

			a.acceptArea(); 

		}); 

		if (owners == null || clone_collection.size() == gl.E_EMPTY) { 
			gl.tracex(v, String.format("Error to get clone collection....%s", 
					gl.S_ERROR)); 

			return false; 
		} 

		gl.tracex( 
				v, 
				String.format("Create clone collection....%05d...%s", 
						target.size(), gl.S_OK)); 

		target.addAll(clone_collection); 

		return true; 

	} 

	 
		 
	public boolean addFromList(List<TPanel> clone_collection) { 
		 
	int  input_collection_size = clone_collection.size(); 
		 
		 
		if(input_collection_size == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...input collection size...%05d",gl.S_ERROR,input_collection_size)); 
			 
			return false; 
		} 
			gl.tracex(new Object() {}, String.format("%s...input collection size...%05d",gl.S_OK,input_collection_size)); 


			clone_collection.forEach(a->{a.setManager(this);}); 
			 
			this.getAdded().clear(); 

			this.getAdded().addAll(clone_collection); 
			 
			clone_collection.clear(); 
		 

		if (!this.insertAll(this.getAdded().getData())) { 
			return false; 
		} 

		if (!refreshZorder()) { 
			return false; 
		} 

			return true; 

	} 
	 
	public boolean updateAfterRead() { 
		 
		int input_collection_size = this.getAdded().getData().size(); 
				 
		if(input_collection_size == gl.E_EMPTY ) 
		{ 
			gl.tracex(new Object() {}, String.format("%s...Collection size...%05d",gl.S_ERROR,input_collection_size)); 
			 
			return false; 
		} 
		 
		if (!this.insertAll(this.getAdded().getData()) || !refreshZorder()) { 
			return false; 
		} 
		 
			gl.tracex(new Object() {}, String.format("%s...Load composite...collection size...%05d...added",gl.S_OK,input_collection_size)); 
	 
			updateIDS(); 
			 
			return true; 

	} 
	 
	public void updateIDS() 
	{ 

		int max_id = this.getCollector().get_max_id(); 

		IDGen.counter = new AtomicLong(max_id); 

		int max_gid = this.getCollector().get_max_gid(); 

		GIDGen.counter = new AtomicLong(max_gid); 

		gl.tracex(new Object() { 
		}, String.format("%s...SETUP IDS...id...%d...gid...%d", gl.S_OK, 
				max_id, max_gid));	 
		 
		 
	 
	} 
		 
	public boolean cloneCompositeSuite(String file_name, int count) { 

		Object v = new Object() { 
		}; 

		String msg = "Clone composite"; 

		List<TPanel> clone_collection = new ArrayList<TPanel>(); 

		if (!getCloneCompositeItems(file_name, count, clone_collection)) { 
			gl.tracex(v, 
					String.format("%s...clone items...%s", msg, gl.S_ERROR)); 

			return false; 
		} 

		if (!addFromList(clone_collection)) { 
			gl.tracex(v, String.format("%s...update after clone...%s", msg, 
					gl.S_ERROR)); 

			return false; 
		} 

		gl.tracex(v, String.format("%s...%s", msg, gl.S_OK)); 

		return true; 

	} 

		 
	public boolean openFileBySK(String composite) { 

		if (!fileOpen(composite)) 
			return false; 

			return updateAfterRead(); 
	} 

	 
	public boolean getFilesSuite() { 
		Object v = new Object() { 
		}; 

		String msg = "Open file(s) suite"; 

		String defDir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[] { 

		new FileNameExtensionFilter("Composite files (*.txt)", "txt"), 
				new FileNameExtensionFilter("Solutione files (*.sln)", "sln") 

		}; 

		File[] files = this.getFilesDlg(defDir, filters); 

		if (files == null || files.length == gl.E_EMPTY) { 
			gl.tracex(v, 
					String.format("%s...%s...%s",gl.S_ERROR,msg, "null or no file selected ")); 

			return false; 
		} 

		gl.tracex(v, String.format("%s...%s...%s...%d",gl.S_OK,msg,"count of selected files :", files.length)); 

		for (File f : files) { 

			String file_name = f.getAbsolutePath(); 

			gl.tracex(v, String.format("%s...%s...%s...%s",gl.S_OK, msg,"try to open file ", file_name)); 

			if (!openFileBySK(file_name)) 
				return false; 
			 

		} // for 

		this.getFrame().updateTitle(this.getHome()); 

		return true; 
	} 

	public boolean openFiles(List<String> file_list) { 
		 

		String msg = "Open file(s) suite"; 

		String defDir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[] { 
		new FileNameExtensionFilter("Files (*.txt)", "txt") 
		}; 

		File[] files = this.getFilesDlg(defDir, filters); 

		if (files == null || files.length == gl.E_EMPTY) { 
			 
			gl.tracex(new Object(){},String.format("%s...%s...%s",gl.S_ERROR,msg, "NULL")); 

			return false; 
		} 
		 
			if(file_list==null) 
			file_list = new ArrayList<String>(); 
			 
			for (File f : files) { 
				 
				String file_name = f.getAbsolutePath(); 
				 
				file_list.add(file_name); 
			} 

				gl.tracex(new Object(){}, String.format("%s...%s...%s...%d",gl.S_OK,msg,"COUNT :", files.length)); 

				return true; 
	} 

	 
	public boolean fileOpen(String file_name) { 
		
		Object v = new Object() {}; 

		try { 

			String type = Fu.get_file_extension(file_name).toUpperCase();//Fu.get_type_of_form_file_by_file_name(source).toUpperCase(); 
			
			gl.tx_k(v, gl.sf("Type of file is...%s",type)); 
			
			this.setFile_type(type); 
			
			this.setHome(file_name); 

			
			if (!this.read()) { 
				 
				gl.tx_e(v,gl.sf("Error read file...[%s]",file_name)); 

				return false; 
				
			} 
			

			gl.tx_k(v,gl.sf("Read file...[%s]",file_name)); 

			return true;
			
		} catch (Exception e) { 

			e.printStackTrace();

			return false; 
		} 
		
	}// fileOpen 

	public boolean selectAll() { 
		Object v = new Object() { 
		}; 

		String msg = "Try to select items"; 

		if (this.getCollector().getData().size() == gl.E_EMPTY) { 

			gl.tracex(v, String.format("%s...%s...%s",gl.S_OK,msg, "The Storage is empty")); 

			return false; 

		} 

		this.getSelected().clear(); 

		this.getCollector().getData().forEach(a -> { 
			a.setSelected(true); 

			a.repaint(); 
		}); 
		this.getSelected().addAll(this.getCollector().getData()); 

		this.getFrame().repaint(); 

		return (this.getSelected().size() == this.getCollector().getData() 
				.size()); 

	} 

	public boolean deselectAll() { 

		Object v = new Object() { 
		}; 

		String msg = "Try to de-select items"; 

		if (this.getSelected().size() == gl.E_EMPTY) { 
			gl.tracex(v, String.format("%s...%s", msg, "Empty")); 

			return false; 

		} 

		this.getSelected().getData().forEach(a -> { 
			a.setSelected(false); 

			a.repaint(); 
		}); 
		this.getSelected().clear(); 

		this.getFrame().repaint(); 

		return (this.getSelected().size() == this.getCollector().getData() 
				.size()); 

	} 

	public boolean cloneComposite() { 
		// Get count of clone's by form 

		int count = DialogUtil.get_int("Waiting for integer value [1-250]", 
				"Set count of copies"); 

		if (count > gl.E_EMPTY) { 
			gl.tracex(new Object() { 
			}, String.format("Get request for copies : %05d", count)); 
		} else { 
			gl.tracex(new Object() { 
			}, String.format("Get request for copies : %05d...%s", count, 
					gl.S_ERROR)); 

			return false; 
		} 

		boolean bl_result = this.cloneCompositeSuite(this.getHome(), count); 

		if (bl_result) 
			gl.tracex(new Object() { 
			}, String.format("Accept request for copies : %05d...%s", count, 
					gl.S_OK)); 
		else 
			gl.tracex(new Object() { 
			}, String.format("Accept request for copies : %05d...%s", count, 
					gl.S_ERROR)); 

		return bl_result; 

	} 
	 
	 
	public boolean exportImage() 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Export image"; 
				 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		JFileChooser save_file_chooser = new JFileChooser(); 
			 
		String default_dir = Fu.getCurrentDir(); 
		 
		save_file_chooser.setCurrentDirectory(new File(default_dir)); 
		 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"set default dir",default_dir)); 
			 
		if (save_file_chooser.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) 
		{ 
				File file = save_file_chooser.getSelectedFile(); 
				 
				String file_where_will_be_saved_an_image = file.getAbsolutePath(); 
				 
				BufferedImage frame_img = null; 
	 
				if(this.getFrame().getPrevious_selector().isEmpty()) 
				{ 
					frame_img = ImageUtil.get_bi(this.getFrame()); 
				} 
				else 
					frame_img = ImageUtil.get_sub_bi(this.getFrame(),this.getFrame().getPrevious_selector()); 
								 
				 
				if(Bau.to_file_from_buf_img(file_where_will_be_saved_an_image,frame_img,"png")) 
				{ 
					gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"Save to file",file_where_will_be_saved_an_image,"Ok")); 
					 
					return true; 
				} 
				else 
					gl.tracex(v,String.format("%s...%s...%s...%s",fabula,"Save to file",file_where_will_be_saved_an_image,"Error")); 
				 
				return false; 
		} 
				return false; 
	} 

	public boolean establishOrder() { 

		Object v = new Object() {}; 

		// Activate of form. 

		String s_default = SDimension.toString(this.getEstablish_order()); 

		String s_input = ap.utils.DlgInteger.getString( 
				"Please input Order table params", s_default); 

		if ("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) { 
			gl.tracex(v, 
					String.format("No values or no changes in the dialog.")); 

			return false; 

		} 

		String param = "tmp"; 

		String payload = String.format("%s=%s;", param, s_input); 

		SDimension sd = new SDimension(param); 

		if (!sd.parse(payload)) { 
			gl.tracex(v, String.format("Check input data format...%s...%s", 
					"Error", s_input)); 

			return false; 

		} 

		Dimension input_dim = sd.getDimension(); 

		this.setEstablish_order(input_dim); 

		return this.establishOrder(input_dim, false); 

	} 

		 
	public boolean establishOrder(Dimension input_order_dim, boolean up) { 

		Object v = new Object() { 
		}; 

		String msg = "Establish order for items (width,height)"; 

		if (this.getSelected().size() == gl.E_EMPTY) { 
			gl.tracex(v, String.format("%s...%s", msg, 
					"there is not exists selectable items")); 

			return false; 

		} 

		Dimension input_dim = input_order_dim; 

		int i_capacity = (input_dim.width * input_dim.height); 

		Dimension frame_dim = new Dimension(this.getFrame().getBounds().width, 
				this.getFrame().getBounds().height); 

		Dimension display_dim = GU.getDisplayDimension(); 

		gl.tracex( 
				v, 
				String.format("Display info : %s", 
						SDimension.toString(display_dim))); 

		// Get the first element sizing. 

		Dimension image_dim = this.getSelected().getData().get(gl.E_EMPTY) 
				.getSize(); 

		if (up) 
			image_dim = new Dimension(image_dim.width + 2, image_dim.height + 2); 

		gl.tracex( 
				v, 
				String.format("Object sizing...%s...up...%s", 
						SDimension.toString(image_dim), up)); 

		int i_area_width = (image_dim.width * input_dim.width) 
				+ input_dim.width; 

		int i_area_height = (image_dim.height * input_dim.height) 
				+ input_dim.height; 

		display_dim.width = i_area_width; 

		display_dim.height = i_area_height; 

		int i_shift_x = (frame_dim.width - i_area_width) / 2; 

		int i_shift_y = (frame_dim.height - i_area_height) / 2; 

		AreaManager am = new AreaManager(display_dim, input_dim, new Dimension( 
				1, 1), new Dimension(0, 0)); 

		for (TPanel a : this.getSelected().getData()) { 
			a.setSelected(false); 

			a.repaint(); 
		} 

		int i = gl.E_EMPTY; 

		boolean bl_pick_mode = this.getFrame().isBlPickMode(); 

		Graphics2D g2 = null; 

		Graphics g_desk_top = null; 

		AreaManager am_t = null; 

		BufferedImage local_bi = null; 

		if (bl_pick_mode) { 
			gl.tracex(v, String.format("Pick mode detected...%s", this 
					.getFrame().isBlPickMode())); 

			local_bi = new BufferedImage(this.getEstablish_order().width, 
					this.getEstablish_order().height, 
					BufferedImage.TYPE_INT_ARGB); 

			g2 = local_bi.createGraphics(); 

			am_t = new AreaManager(this.getEstablish_order(), 
					this.getEstablish_order(), new Dimension(1, 1), 
					new Dimension(0, 0)); 

		} 

		// Select only parent objects. 

		List<TPanel> list = this.getSelected().getData().stream() 
				.filter((b -> (b.getPid() == gl.E_ERROR))) 
				.collect(Collectors.toList()); 

		for (TPanel s : list) { 

			Point p = AreaManager.getCellCoordByLinearIndex(i, 
					am.getAreaInCells().width, am.getAreaInCells().height); 

			Rectangle rect = am.get(p); 

			if (i_shift_x > gl.E_EMPTY) { 
				rect.x += i_shift_x; 
			} 

			if (i_shift_y > gl.E_EMPTY) { 
				// rect.y += i_shift_y/2; 
			} 

			rect = gl.getSpannedRect(rect, new Insets(1, 1, 1, 1)); 
 
			if(this.getFrame().isBlEstablishFormat() || s.getType().equalsIgnoreCase("TTick")) 
			{ 
				rect.height = s.getBounds().height; 
			} 
			 
			 
			 
			s.setBou_nds(rect); 
			 

			s.updateOriginalBounds(); 

			if (bl_pick_mode) { 
				Point bp = AreaManager.getCellCoordByLinearIndex(i, 
						am_t.getAreaInCells().width, 
						am_t.getAreaInCells().height); 

				Rectangle target_rect = am_t.get(bp); 

				Color color_prev = g2.getColor(); 

				g2.setColor(s.getBackground()); 

				GU.fillRect(g2, target_rect); 

				g2.setColor(color_prev); 

			} 

			i++; 
		} 

		if (g2 != null) 
			g2.dispose(); 

		// Save to file. 

		if (bl_pick_mode) { 
			String path = Fu.getTempFile(Fu.get_path(new File(this 
					.getHome()).getAbsolutePath()), "png"); 

			if (!Bau.to_file_from_buf_img(path, local_bi, "PNG")) 
				gl.tracex(v, String.format("Export bitmap to file...%s...%s", 
						path, gl.S_ERROR)); 
			else 
				gl.tracex(v, String.format("Export bitmap to file...%s...%s", 
						path, gl.S_OK)); 
		} 

		return true; 
	} 

	 
	 
	 
	public boolean do_form(String composite) { 
		 
		boolean bl =  this.openFileBySK(composite); 
		 
		return true; 
		 
	} 
	 
	public boolean action_form_close(TPanel obj) { 

		this.getSelected().clear(); 

		this.getSelected().addAll(obj.getChildsAll(obj.getId())); 

		// Add object's owner. 
		this.getSelected().add(this.getCollector().get_by_id(obj.getId())); 

		return this.deleteSuite(); 

	} 

	public boolean actUpdateAction(int id, String action) { 
		this.getCollector().get_by_id(id).setAction(action); 

		return this.getCollector().get_by_id(id).getAction() 
				.equalsIgnoreCase(action); 

	} 

	public boolean deleteSelected() { 

		Object v = new Object() { 
		}; 

		if (this.getSelected().size() == gl.E_EMPTY) { 
			gl.tracex(v, String.format("Try to delete items ...%s", "Empty")); 

			return false; 

		} 

		boolean bl_result = this.deleteSuite(); 

		if (bl_result) 
			this.getFrame().updateTitle(this.getHome()); 

		return bl_result; 

	}// deleteSelected 

	public boolean deleteSuite() { 
		if (this.getSelected() == null) 
			return false; 

		Object v = new Object() { 
		}; 

		if (this.getSelected().size() == gl.E_EMPTY) { 

			return true; 
		} 

		int items_selected = this.getSelected().size(); 

		this.getFrame().removeAll(this.getSelected().getData()); 

		this.getCollector().getData().removeAll(this.getSelected().getData()); 

		this.getSelected().clear(); 

		this.getFrame().repaint(); 

		// Reset id's gen. 

		IDGen.counter = new AtomicLong(this.getCollector().get_max_id()); 

		GIDGen.counter =  new AtomicLong(this.getCollector().get_max_gid()); 

		boolean bl_result = (this.getSize() == this.getFrame() 
				.getComponentCount()); 

		if (bl_result) { 
			gl.tracex(v, String.format("Attempt to delete %d items...%s...%d", 
					items_selected, "Success", this.getSize())); 

			this.getFrame().updateTitle(this.getHome()); 
		} else 
			gl.tracex(v, String.format("Attempt to delete %d items...%s...%d", 
					items_selected, "Error", this.getSize())); 

		return bl_result; 

	} 

	public void moveSelected(int dx, int dy) { 

		this.getSelected().getData().forEach(a -> { 
			Rectangle b = a.getBounds(); 

			b.translate(dx, dy); 

			a.setBou_nds(b); 

		}); 
	} 

	public void moveAllParents(int dx, int dy) { 

		this.getCollector().getData().forEach( 

		a -> { 

			// if (a.getPid() == gl.E_ERROR) 
			// { 
				Rectangle b = a.getBounds(); 

				b.translate(dx, dy); 

				a.setBou_nds(b); 

				// } 
			} 

		); 

	} 

	public void moveObjectsByWheel(int dx, int dy) { 

		if (this.getSelected().size() != gl.E_EMPTY) 
			this.moveSelected(dx, dy); 
		else { 
			this.moveAllParents(dx, dy); 

		} 

	} 

	public void setIdsGap(List<TPanel> list, int gap) { 
		list.forEach(a -> { 

			gl.smn(String.format("---> Prev value: %d/%d/%d Gap : %d ", 
					a.getPid(), a.getId(), a.getZorder(), gap)); 

			int prev_pid = a.getPid(); 

			int prev_id = a.getId(); 

			int prev_zo = a.getZorder(); 

			if (prev_pid != gl.E_ERROR) 
				a.setPid(prev_pid + gap); 

			if (prev_id != gl.E_ERROR) 
				a.setId(prev_id + gap); 

			if (prev_zo != gl.E_ERROR) 
				a.setZorder(prev_zo + gap); 

			gl.smn(String.format("---> After value: %d/%d/%d", a.getPid(), 
					a.getId(), a.getZorder())); 

		}); 
	} 

	public boolean cloneInstances(String type_name, int count) { 
		boolean bl_result = false; 

		for (int i = 0; i < count; i++) { 
			bl_result = createObject(type_name); 
		} 

		return bl_result; 

	} 

	public Vector<TPanel> emitInstances(Rectangle rect, int count, int dx, 
			int dy, int pid) { 

		Vector<TPanel> list = new Vector<TPanel>(); 

		Rectangle lastRect = rect; 

		int z_order = this.getFrame().getComponentCount(); 

		for (int i = 0; i < count; i++) { 
			lastRect = Ru.Shift(lastRect, dx, dy); 

			TImage sp = new TImage(lastRect, z_order); 

			sp.setBack_ground(CU.getAlphaColor(Color.blue, gl.E_EMPTY)); 

			sp.setGradient(sp.getBackground()); 

			if (pid != gl.E_ERROR) 
				sp.setPid(pid); 

			list.add(sp); 
		} 

		return list; 
	} 

	public boolean openGraphicsFilesSuite() { 
		Object v = new Object() { 
		}; 

		String fabula = "Open graphics file(s) suite"; 

		String defDir = Fu.get_path(new File(this.getHome()) 
				.getAbsolutePath()); 

		FileNameExtensionFilter[] filters = new FileNameExtensionFilter[] { 
				new FileNameExtensionFilter("Image files (*.ico)", "ico"), 
				new FileNameExtensionFilter("Image files (*.gif)", "gif"), 
				new FileNameExtensionFilter("Image files (*.jpg)", "jpg"), 
				new FileNameExtensionFilter("Image files (*.png)", "png"), 
				new FileNameExtensionFilter("Image files (*.bmp)", "bmp") 

		}; 

		File[] files = this.getFilesDlg(defDir, filters); 

		if (files == null || files.length == gl.E_EMPTY) { 
			gl.tracex(v, String.format("%s...%s", fabula, 
					"null or no file selected ")); 

			return false; 
		} 

		gl.tracex(v, String.format("%s...%s...%d", fabula, 
				"count of selected files :", files.length)); 

		Vector<TPanel> list = new Vector<TPanel>(); 

		Rectangle rect = new Rectangle(0, 0, 32, 32); 

		if (this.getSelectors().size() != gl.E_EMPTY) { 
			if (this.getSelectors().getData().get(gl.E_EMPTY).getBounds().width < 160) { 
				rect = new Rectangle(0, 0, this.getSelectors().getData() 
						.get(gl.E_EMPTY).getBounds().width, this.getSelectors() 
						.getData().get(gl.E_EMPTY).getBounds().height); 
			} 
		} 

		list.addAll(this.emitInstances(rect, files.length, 
				(rect.width + TPanel.MW), gl.E_EMPTY, gl.E_ERROR)); 

		int index = gl.E_EMPTY; 

		this.getAdded().clear(); 

		String log_file_name = String.format("%s/%s", 
				Fu.get_path(files[0].getAbsolutePath()), "a_error.log"); 

		if (Fu.isaFile(log_file_name)) 
			if (!Fu.deleteFile(log_file_name)) { 
				String msg = String.format("%s...%s...%s...%s", fabula, 
						"delete prev log file", log_file_name, "Error"); 

				return false; 
			} 

		Logger log = new Logger(log_file_name); 

		for (File f : files) { 
			String fileName = f.getAbsolutePath(); 

			String msg = String.format("%s...%s...%s", fabula, "open file :", 
					fileName); 

			gl.tracex(v, msg); 

			TPanel sp = list.get(index); 

			if (!Bau.imp_buf_img_by_scale_factor(sp, fileName, 10, log)) { 

				msg = String.format("%s...%s...%s...%s", fabula, "import BI :", 
						fileName, "Error"); 

				gl.tracex(v, msg); 

				return false; 
			} else { 

				msg = String.format("%s...%s...%s...%d...%s", fabula, 
						"import BI :", fileName, index, "Ok"); 

				gl.tracex(v, msg); 

				this.getAdded().add(sp); 

				if (!this.insertAll(this.getAdded().getData())) { 
					gl.tracex(v, String.format("%s...%s...%s", fabula, 
							"insert", "Error")); 

					return false; 
				} else { 
					gl.tracex(v, String.format("%s...%s...%s", fabula, 
							"insert", "Ok")); 
				} 
			} 

			index++; 

		}// end for 

		return true; 

	} 
	 
	public boolean exportActionList() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		this.getCollector().get_action_list(gl.E_ERROR).forEach(a-> 
		{ 
			String msg = gl.sf("%05d. %s",a.getId(),a.getAction()); 
			 
			sb.append(msg); 
			 
			sb.append(System.lineSeparator()); 
									 
		}); 
		 
		return Fu.dlgSaveStringToFile(sb.toString()); 
		 
	} 

	public int getTarget(TPanel owner) { 

		SPanelCollector spc = owner.getManager().getCollector(); 

		Vector<TPanel> list = spc.getVariants(owner); 

		if (list == null || list.size() == gl.E_EMPTY) 
			return gl.E_ERROR; 

		return owner.getManager().getCollector().get_max_id(list); 

	} 

	public boolean isWasSaved() { 
		return !this.getHome().equalsIgnoreCase("default_home.xml"); 
	} 

} 
// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
