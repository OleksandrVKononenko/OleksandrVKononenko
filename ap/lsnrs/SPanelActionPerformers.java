 
 
package ap.lsnrs; 

import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.RenderingHints; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import java.util.Optional; 
import java.util.OptionalInt; 
import java.util.Vector; 

import javax.swing.JColorChooser; 
import javax.swing.JFileChooser; 
import javax.swing.JOptionPane; 
import javax.swing.SwingConstants; 

import ap.font.JFontChooser; 
import ap.frame.TFrame; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.Bar; 
import ap.btn.TChart; 
import ap.btn.TIndexRange; 
import ap.btn.TLabel; 
import ap.btn.TPanel; 
import ap.btn.TPushBtn; 
import ap.prop.SAreaManager; 
import ap.prop.SBounds; 
import ap.prop.SColor; 
import ap.prop.SDimension; 
import ap.prop.SFont; 
import ap.shape.Ru; 
import ap.state.Fl; 
import ap.swing.TTextArea; 
import ap.swing.TTextField; 
import ap.utils.Bau; 
import ap.utils.DateUtil; 
import ap.utils.DialogUtil; 
import ap.utils.Fu; 
import ap.utils.ImageUtil; 
import ap.utils.IntArrayUtil; 
import ap.utils.MapUtils; 
import ap.utils.Su; 


public class SPanelActionPerformers implements ActionListener { 

	 
	private TPanel owner = null; 
	 
	 
	/** 
	 * @return the owner 
	 */ 
	public TPanel getOwner() { 
		return owner; 
	} 

	/** 
	 * @param owner the owner to set 
	 */ 
	public void setOwner(TPanel owner) { 
		this.owner = owner; 
	} 

	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	} 

	 
	 
	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
		 	Object v = new Object(){}; 
		 	 
		 	owner = this.getOwner(); 
		 
			String a = e.getActionCommand(); 
		 
			 
			if(a.equalsIgnoreCase("importImage")) 
			{ 
				owner.importImageDlg(owner,false); 
			} 
			else if(a.equalsIgnoreCase("fitToImage")) 
			{ 
				 Bau.fit_to_img(owner); 
			} 
			else if(a.equalsIgnoreCase("clearImage")) 
			{ 
				owner.clearImage(owner); 
			} 
			else if(a.equalsIgnoreCase("exportImage")) 
			{ 
				exportImage(owner); 
			} 
			else if(a.equalsIgnoreCase("editType")) 
			{ 
				editType(owner); 
			} 
			else if(a.equalsIgnoreCase("editData")) 
			{ 
				editData(owner); 
			} 
			else if(a.equalsIgnoreCase("editToolTip")) 
			{ 
				editToolTip(owner); 
			} 
			 
			else if(a.equalsIgnoreCase("editText")) 
			{ 
				editText(owner); 
			} 
			else if(a.equalsIgnoreCase("editFreeze")) 
			{ 
				editFreeze(owner); 
			} 
			else if(a.equalsIgnoreCase("editSelectable")) 
			{ 
				editSelectable(owner); 
			} 
			else if(a.equalsIgnoreCase("editBorder")) 
			{ 
				editBorder(owner); 
			} 
			else if(a.equalsIgnoreCase("editAction")) 
			{ 
				editAction(owner); 
			} 
			else if(a.equalsIgnoreCase("editBase")) 
			{ 
				editBase(owner); 
			} 
			else if(a.equalsIgnoreCase("editOffset")) 
			{ 
				editOffset(owner); 
			} 
					 
			else if(a.equalsIgnoreCase("editFunction")) 
			{ 
				editFunction(owner); 
			} 
			else if(a.equalsIgnoreCase("editAlign")) 
			{ 
				editAlign(owner); 
			} 
			 
			else if(a.equalsIgnoreCase("editFont")) 
			{ 
				editFont(owner); 
			} 
			else if(a.equalsIgnoreCase("editFontText")) 
			{ 
				editFontText(owner); 
			} 
			 
			else if(a.equalsIgnoreCase("editTextColor")) 
			{ 
				editTextColor(owner); 
			} 
			else if(a.equalsIgnoreCase("editGridColor")) 
			{ 
				editGridColor(owner); 
			} 
			else if(a.equalsIgnoreCase("editBounds")) 
			{ 
				editBounds(owner); 
			} 

			else if(a.equalsIgnoreCase("editChildArea")) 
			{ 
				String s_prev_value = SDimension.toString(owner.getChild_dim()); 
				 
				String s_input = ap.utils.DlgInteger.getString("Please input ChildArea values",s_prev_value); 
				 
				if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
				{ 
					gl.tracex(v,String.format("Input NONE value in the dialog.")); 
					 
					return; 
					 
				} 
				 
				if(s_prev_value.equalsIgnoreCase(s_input)) 
				{ 
					gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
					 
					return; 
				} 
				 
				SDimension sa = new SDimension(); 
				 
				gl.tracex(v,String.format("Input value is : %s ",s_input)); 
				 
				String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
				 
				if (sa.parse(payload)) 
				{ 
					gl.tracex(v,String.format("Child dim value is : %s","Ok")); 
					 
					if (!owner.setChild_dim(sa.getDimension())) 
						gl.tracex(v,String.format("Child dim value set : %s...%s",sa.toStringShort(),"Error")); 
					else 
					{ 
						owner.repaint(); 
						 
						gl.tracex(v,String.format("Child dim value set : %s...%s",sa.toStringShort(),"Ok")); 
					}	 
						 
				} 
				else 
					gl.tracex(v,String.format("Input value is : %s","Error")); 
						 
				 
				 
			} 
			else if(a.equalsIgnoreCase("editAreaManager")) 
			{ 
				 
				String s_prev_value = SAreaManager.toStringForDlgForm(owner.getArea_manager()); 
				 
				String s_input = ap.utils.DlgInteger.getString("Please input AreaManager values",s_prev_value); 
				 
				if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
				{ 
					gl.tracex(v,String.format("Input NONE value in the dialog.")); 
					 
					return; 
					 
				} 
				 
				if(s_prev_value.equalsIgnoreCase(s_input)) 
				{ 
					gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
					 
					return; 
				} 
				 
				SAreaManager sa = new SAreaManager(); 
				 
				gl.tracex(v,String.format("Input value is : %s ",s_input)); 
				 
				String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
				 
				if (sa.parse(payload)) 
				{ 
					gl.tracex(v,String.format("Input value is : %s","Ok")); 
					 
					owner.setArea_manager(sa.getArea_manager()); 
					 
				} 
				else 
					gl.tracex(v,String.format("Input value is : %s","Error")); 
				 
						 
			} 

			 
			else if(a.equalsIgnoreCase("setzorder")) 
			{ 
			 
				String msg = "Try z-order value"; 
				 
				int i_prev_z_order = owner.getZorder(); 
				 
				gl.tracex(v,String.format("%s...%s...%d ",msg,"extract",i_prev_z_order)); 
								 
				int i_z_order = ap.utils.DlgInteger.getInt("Please input z-order value : ",""+i_prev_z_order); 
				 
				if(i_z_order == gl.E_ERROR) 
				{ 
				 
					gl.tracex(v,String.format("%s...%s...%d ",msg,"error input value",i_z_order)); 
					 
					return; 
				} 
				 
				gl.tracex(v,String.format("%s...%s...%d ",msg,"get input",i_z_order)); 
				 
				if(i_z_order > owner.getManager().getCollector().getSize()) 
				{ 
					gl.tracex(v,String.format("%s...%s...%d...%s...%d ",msg,"check max z-order error ",i_z_order,"must be less or equal than",owner.getManager().getCollector().getSize())); 
					 
					return ; 
				} 
				 
								 
				if (owner.setZorder(i_z_order)) 
				{ 
					gl.tracex(v,String.format("%s...%s...%d ",msg,"Ok",i_z_order)); 
					 
					owner.getManager().refreshZorder(); 
					 
				} 
				else 
					gl.tracex(v,String.format("%s...%s...%d ",msg,"Error",i_z_order)); 
 
	 
			} 
			 
			else if(a.equalsIgnoreCase("Connect")) 
			{ 
				 editConnect(owner); 
				 
			}	 
			else if(a.equalsIgnoreCase("editGid")) 
			{ 
				 editGid(owner); 
				 
			}	 
			else if(a.equalsIgnoreCase("editId")) 
			{ 
				 editId(owner); 
				 
			}	 
			else if(a.equalsIgnoreCase("editStartDate")) 
			{ 
				 editStartDate(owner); 
				 
			}	 
			else if(a.equalsIgnoreCase("editStartIndex")) 
			{ 
				 editStartIndex(owner); 
				 
			}	 
		else if(a.equalsIgnoreCase("delete")) 
		{ 
			 deleteObject(owner); 
			 
		} 
		 
		else if(a.equalsIgnoreCase("clone")) 
		{ 
			 cloneObject(owner,1); 
		} 
		else if(a.equalsIgnoreCase("cloneByParams")) 
		{ 
			 cloneByParams(owner); 
		} 
		else if(a.equalsIgnoreCase("debugOn")) 
		{ 
			setDebug(true); 
			 
		} // debugOn 
		else if(a.equalsIgnoreCase("debugOff")) 
		{ 
			setDebug(false); 
			 
		} // debugOff 
			 
			 
		else if(a.equalsIgnoreCase("freezeOn")) 
		{ 
			owner.setFreeze(true); 
			 
		} // freezeOn 
		else if(a.equalsIgnoreCase("freezeOff")) 
		{ 
			owner.setFreeze(false); 
			 
		} // freezeOff 
			 
			 
		else if(a.equalsIgnoreCase("selectableOn")) 
		{ 
			owner.setUnSelectable(true); 
			 
		} // selectableOn 
		else if(a.equalsIgnoreCase("selectableOff")) 
		{ 
			owner.setUnSelectable(false); 
			 
		} // selectableOff 
			 
		else if(a.equalsIgnoreCase("visibleOn")) 
		{ 
			owner.setVisible(true); 
			 
		} 
		else if(a.equalsIgnoreCase("visibleOff")) 
		{ 
			owner.setVisible(false); 
			 
		} 
		else if(a.equalsIgnoreCase("enableOn")) 
		{ 
			owner.setEnable(true); 
			 
		} 
		else if(a.equalsIgnoreCase("enableOff")) 
		{ 
			owner.setEnable(false); 
			 
		} 
		else if(a.equalsIgnoreCase("denyxOn")) 
		{ 
			owner.setDenyx(true); 
			 
		} 
		else if(a.equalsIgnoreCase("denyxOff")) 
		{ 
			owner.setDenyx(false); 
			 
		} 
		else if(a.equalsIgnoreCase("denyyOn")) 
		{ 
			owner.setDenyy(true); 
			 
		} 
		else if(a.equalsIgnoreCase("denyyOff")) 
		{ 
			owner.setDenyy(false); 
			 
		} 
		else if(a.equalsIgnoreCase("moveinsideOff")) 
		{ 
			owner.setMoveInside(false); 
		} 
		else if(a.equalsIgnoreCase("moveinsideOn")) 
		{ 
			owner.setMoveInside(true); 
		} 
						 
		else if(a.equalsIgnoreCase("setbkgColorForm")) 
		{ 
			setBkgColorForm(owner); 
			 
		}//setBkgColorForm 
		else if(a.equalsIgnoreCase("setgrdColorForm")) 
		{ 
			setGrdColorForm(owner); 
			 
		}//setGrdColorForm 
		else if(a.equalsIgnoreCase("setbkgColorText")) 
		{ 
			setBkgColorText(owner); 
			 
		} // setbkgColorText 
		 
		else if(a.equalsIgnoreCase("setgrdColorText")) 
		{ 
			setGrdColorText(owner); 
			 
		} // setgrdColorText 
		else if(a.equalsIgnoreCase("refresh")) 
		{ 
			refresh(owner); 
			 
		} // setgrdColorText 
		else if(a.equalsIgnoreCase("loadquotes")) 
		{ 
			 ((TChart)owner).load_quotes_suite((TChart)owner); 
			 
		} // setgrdColorText 
			 
	} 

	public boolean exportImage(TPanel owner) 
	{ 
		Object v = new Object() {}; 
		 
		String fabula  = "Export image"; 
				 
		gl.tracex(v,String.format("%s...%s",fabula,"init")); 
		 
		JFileChooser save_file_chooser = new JFileChooser(); 
			 
		String default_dir = Fu.getCurrentDir(); 
		 
		save_file_chooser.setCurrentDirectory(new File(default_dir)); 
		 
		gl.tracex(v,String.format("%s...%s...%s",fabula,"set default dir",default_dir)); 
			 
		if (save_file_chooser.showSaveDialog(this.getOwner())== JFileChooser.APPROVE_OPTION) 
		{ 
				File file = save_file_chooser.getSelectedFile(); 
				 
				String file_where_will_be_saved_an_image = file.getAbsolutePath(); 
				 
			    owner.setImage(ImageUtil.get_bi(owner)); 
			    								 
				owner.setImg_type("png"); 
				 
				if(Bau.to_file_from_buf_img(file_where_will_be_saved_an_image,owner.getImage(),owner.getImg_type())) 
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
	 
	 
	public void editBounds(TPanel owner) 
	{ 
		Object v = new Object() {}; 
		 
		String s_prev_value = SBounds.toString(owner.getBounds()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input Bounds values",s_prev_value); 
		 
		if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("Input NONE value in the dialog.")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		 
		SBounds sa = new SBounds(); 
		 
		gl.tracex(v,String.format("Input value is : %s ",s_input)); 
		 
		String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
		 
		if (sa.parse(payload)) 
		{ 
			gl.tracex(v,String.format("Input value is : %s...%s ","Ok",sa.toStringShort())); 
			 
			 
				owner.setBou_nds(sa.getBounds()); 
				 
				gl.tracex(v,String.format("%s...%s...%s","Set bounds ","Ok",sa.toStringShort())); 
				 
				owner.setOriginal_bounds(owner.getBounds()); 
				 
				owner.getManager().getSelected().getData().forEach(e-> 
				{ 
					Rectangle rect = e.getBounds(); 
							 
					rect.width = owner.getBounds().width; 
					 
					rect.height = owner.getBounds().height; 
					 
					e.setBou_nds(rect); 
					 
					e.repaint(); 
				}); 
			 
		} 
		else 
			gl.tracex(v,String.format("Input value is : %s","Error")); 
				 
		 
		 
	} 
	public void setDebug(boolean value) 
	{ 
		Object v = new Object() {}; 

		 gl.tracex(v,String.format("Try to set debug attr ...before : %s",owner.isDebug())); 
		 
			if(!owner.setDebug(value)) 
				gl.tracex(v,String.format("Try to set debug attr ...%s",gl.S_ERROR)); 
			else 
				gl.tracex(v,String.format("Try to set debug attr to...%s...%s",owner.isDebug(),gl.S_OK)); 
			 
			owner.repaint(); 
	 
	} 
	 
	 
	public void setBkgColorForm(TPanel owner) { 
		Object v = new Object() {}; 

		Color c = JColorChooser.showDialog(null, "Choose a background color...", 
				owner.getBackground()); 

		if (c != null) { 
			if (!owner.setBack_ground(c)) { 
				gl.tracex(v, String.format("Set background...%s", "Error")); 

				return; 
			} 

			gl.tracex(v, String.format("Set background...%s...%s", 
					SColor.toString(c), "Ok")); 

			 // For group . 
			 
			owner.getManager().getSelected().getData().forEach(e-> 
			{ 
				 
				e.setBack_ground(owner.getBackground()); 
				 
				if(owner.getKeyHandler().isBlCtrl()) 
					e.setBack_ground(gl.getRandomColor()); 
				 
				e.repaint(); 
			} 
			);			 
			 
			 
			if(owner.getType().equalsIgnoreCase("TTextField")) 
		     { 
		    	 TTextField field = (TTextField)owner; 
		    	 
		    	 field.getEdit().setBackground(owner.getBackground()); 
		    	 
		     }else if(owner.getType().equalsIgnoreCase("TTextArea")) 
		     { 
		    	 TTextArea field = (TTextArea)owner; 
		    	 
		    	 field.getEdit().setBackground(owner.getBackground()); 
		     } 
	 
			 
				owner.repaint(); 
			 
			 
			 
			// Set global pick color. 
			 
			if (owner.getManager().getFrame().isBlPickMode()) 
			{ 
				owner.getManager().getFrame().setPickColor(owner.getBackground()); 
				 
				gl.tracex(v, String.format("Set global pickup color to ...%s", 
				SColor.toString(owner.getManager().getFrame().getPickColor()))); 
				 
			} 
		} 
	} 

	public void editTextColor(TPanel owner) { 
		Object v = new Object() { 
		}; 

		Color c = JColorChooser.showDialog(null, "Choose a color...", 
				owner.getText_color()); 

		if (c != null) { 
			if (!owner.setText_color(c)) { 
				gl.tracex(v, String.format("Set color...%s", "Error")); 

				return; 
			} 

			gl.tracex(v, String.format("Set color...%s...%s",SColor.toString(c), "Ok")); 
			 
			if(owner.getType().equalsIgnoreCase("TTextField")) 
		     { 
		    	 TTextField field = (TTextField)owner; 
		    	 
		    	 field.getEdit().setForeground(owner.getText_color()); 
		    	 
		     }else if(owner.getType().equalsIgnoreCase("TTextArea")) 
		     { 
		    	 TTextArea field = (TTextArea)owner; 
		    	 
		    	 field.getEdit().setForeground(owner.getText_color()); 
		     } 
	 

			owner.repaint(); 
		} 
	} 
	 
	public void editGridColor(TPanel owner) { 
		Object v = new Object() { 
		}; 

		Color c = JColorChooser.showDialog(null, "Choose a color...", 
				owner.getGrid_color()); 

		if (c != null) { 
			if (!owner.setGrid_color(c)) { 
				gl.tracex(v, String.format("Set color...%s", "Error")); 

				return; 
			} 

			gl.tracex(v, String.format("Set color...%s...%s",SColor.toString(c), "Ok")); 

			owner.repaint(); 
		} 
	} 
	 
	public void setGrdColorForm(TPanel owner) { 
		Object v = new Object() { 
		}; 

		Color c = JColorChooser.showDialog(null, "Choose a gradient color ...", 
				owner.getGradient()); 

		if (c != null) { 
			if (!owner.setGradient(c)) { 
				gl.tracex(v, String.format("Set gradient...%s", "Error")); 

				return; 
			} 

			gl.tracex(v, String.format("Set gradient...%s...%s", 
					SColor.toString(c), "Ok")); 

			owner.repaint(); 
			 
			// For group. 
			 
			owner.getManager().getSelected().getData().forEach(e-> 
			{ 
				if(owner.getKeyHandler().isBlCtrl()) 
				  e.setGradient(gl.getRandomColor()); 
				else 
				  e.setGradient(c); 
				 
				e.repaint(); 
			} 
			); 
			 
		} 
	} 
	 
	 
	public void refresh(TPanel owner) 
	{ 
		gl.tracex(new Object(){},String.format("Try to refresh of object")); 
		 
		if (!owner.getManager().isSuiteWasSavedBefore()) 
		{ 
			gl.tracex(new Object(){},String.format("Please save or re-open suite.Skip.")); 
			 
			return; 
		} 
		 
		if(!owner.getManager().openFileBySK(owner.getManager().getHome())) 
		{ 
			gl.tracex(new Object(){},String.format("Refresh operation completed...%s",gl.S_ERROR)); 
			 
			return; 
		} 
			gl.tracex(new Object(){},String.format("Refresh operation completed...%s",gl.S_OK)); 
		 
	} 
	 
	 
	 
	public void setGrdColorText(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = SColor.toString(owner.getGradient()); 
		 
		if(owner.getGradient_type() != gl.E_ERROR) 
		{ 
			s_prev_value = String.format("%s#%d",s_prev_value,owner.getGradient_type()); 
		} 
		 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input gradient Color values",s_prev_value); 
		 
		if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("Input NONE value in the dialog.")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		SColor sa = new SColor(); 
		 
		gl.tracex(v,String.format("Input value is : %s ",s_input)); 
		 
		String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
		 
		int gradient_type_int = gl.E_ERROR; 
		 
		if (sa.parse(payload)) 
		{ 
			gl.tracex(v,String.format("Input value is : %s...%s ","Ok",sa.toString())); 
			 
			if(sa.getTag_map().size() != gl.E_EMPTY) 
			{ 
				int KEY_INDEX = 3; 
				 
				String gradient_type = MapUtils.findValueByKey(sa.getTag_map(),KEY_INDEX); 

				if(gradient_type != null) 
				{ 
					gradient_type_int = Integer.parseInt(gradient_type); 
				} 
			} // 
			 
			if( ! 
					owner.setGradient(sa.getColor()) 
					) 
					{ 
						gl.tracex(v,String.format("Set gradient...%s","Error")); 
						 
						return; 
					} 
					 
			 
					owner.setGradient_type(gradient_type_int); 
			 
					gl.tracex(v,String.format("Set gradient...%s...%s...%2d",SColor.toString(sa.getColor()),"type",owner.getGradient_type())); 
					 
					owner.repaint(); 
					 

					owner.getManager().getSelected().getData().forEach(e-> 
					{ 
						if(owner.getKeyHandler().isBlCtrl()) 
						  e.setGradient(gl.getRandomColor()); 
						else 
						  e.setGradient(sa.getColor()); 
						 
						  e.setGradient_type(owner.getGradient_type()); 
						 
						  e.repaint(); 
					} 
					); 
					 
			 
		} 
		else 
			gl.tracex(v,String.format("Input value is : %s","Error")); 


	} 
	 
	public void editType(TPanel owner) 
	{ 
		Object v = new Object (){}; 
	 
		String s_prev_value = owner.getType(); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input type name ",s_prev_value); 
		 
		if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NONE value instead type name .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		this.getOwner().setType(s_input); 
		 
		if(this.getOwner().getType().equalsIgnoreCase(s_input)) 
			gl.tracex(v,String.format("Set type name...%s....%s",s_input,"Ok")); 
		else 
			gl.tracex(v,String.format("Set type name...%s....%s",s_input,"Error")); 
			 
	} 
	 
	public void editData(TPanel owner) 
	{ 
		String s_prev_value = owner.getData(); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input data",s_prev_value); 
		 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(new Object (){},String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setData(s_input); 
		 
		if(this.getOwner().getData().equalsIgnoreCase(s_input)) 
			gl.tracex(new Object (){},String.format("Set data...%s....%s",s_input,"Ok")); 
		else 
			gl.tracex(new Object (){},String.format("Set data...%s....%s",s_input,"Error")); 
			 
	} 
	 
	 
	public void editToolTip(TPanel owner) 
	{ 
		Object v = new Object (){}; 
	 
		String s_prev_value = owner.getTool_tip_text() ; 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input tooltip text",s_prev_value); 
		 
		if(     "None".equalsIgnoreCase(s_input)  || 
				"".equalsIgnoreCase(s_input)      || 
				s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tx_e(v,gl.sf("Недопустимое значение...[%s]")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tx_k(v,gl.sf("Изменений ввода не обнаружено...[%s = %s]",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		this.getOwner().setTool_tip_text(s_input); 
		 
		this.getOwner().setToolTipText(s_input); 
		 
		gl.tx(v,
				this.getOwner().getTool_tip_text().equalsIgnoreCase(s_input),
				gl.sf("Проверка ввода...[%s]",s_input)
			 ); 
		
	} 
	public void editFont(TPanel owner) 
	{ 
		 Object v = new Object (){}; 
		 
		 gl.tx_k(v,String.format("Редактирование фонта.")); 
		 
		 TFrame frame = owner.getManager().getFrame(); 
		 
	     JFontChooser jf =  new JFontChooser(); 
	    		 
	     Font font  = jf.showDialog(frame, "Укажите фонт."); 

	     owner.setFont(font); 
	 
	     if(owner.getType().equalsIgnoreCase("TTextField")) 
	     { 
	    	 TTextField field = (TTextField)owner; 
	    	 
	    	 field.getEdit().setFont(owner.getFont()); 
	    	 
	    	 field.getEdit().repaint(); 
	    	 
	     }else if(owner.getType().equalsIgnoreCase("TTextArea")) 
	     { 
	    	 TTextArea field = (TTextArea)owner; 
	    	 
	    	 field.getEdit().setFont(owner.getFont()); 
	    	 
	    	 field.getEdit().repaint(); 
	     } 
 
 
	     owner.repaint(); 
	} 
	 
	 
	public void deleteObject(TPanel owner) 
	{ 
		 
		owner.getManager().getDeleted().add(owner); 
		 
		Vector<TPanel> childs = owner.getChildsAll(owner.getId()); 
		 
		childs.forEach(a->{ owner.getManager().getDeleted().add(a);}); 

		owner.getManager().resetSelected(); 
		 
		owner.getManager().getSelected().clear(); 
		 
		owner.getManager().getDeleted().getData().forEach( 
				a-> 
				{ 
					owner.getManager().getSelected().add(a); 
				} 
				 
				); 
		 
		 
		owner.getManager().deleteSuite(); 
		 
		 
		 
	} 
	 
	 
	public void cloneObject(TPanel owner,int count) 
	{ 
		Object v = new Object() {}; 

		String msg = "Clone object ex"; 

		if (!owner.getManager().cloneCompositeSuite(owner.getManager().getHome(),count)) 
			gl.tracex(v, String.format("%s...%s", msg, "Error")); 
		else 
			gl.tracex(v, String.format("%s...%s", msg, "Ok")); 

	} 
	 
		 
		 
	public void cloneByParams(TPanel owner) 
	{ 
		Object v = new Object() {}; 

		String msg = "Clone objects by params"; 

		SBounds sb = new SBounds(); 

		if (!SBounds.doForm("Please input params", sb, "10,0,0,0")) { 
			gl.tracex(v, String.format("%s...%s...%s", msg, "get params by form", "Error")); 

			return; 
		} 
		gl.tracex(v, String.format("%s...%s...%s", msg, "get params by form", "Ok")); 

		Rectangle rect = sb.getBounds(); 
		 
		// Correct count 
		if(rect.x <= gl.E_EMPTY || rect.x > 300) 
		   rect.x = gl.E_OK; 
				 

		if (!owner.getManager().cloneCompositeSuite(owner.getManager().getHome(), rect.x)) 
			gl.tracex(v, String.format("%s...%s", msg, "Error")); 
		else 
			gl.tracex(v, String.format("%s...%s", msg, "Ok")); 

	} 
	 
	public void editAlign(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s",SBounds.toString(owner.getAlign())); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input ",s_prev_value); 
		 
		 
		// Check on null. 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		gl.tracex(v,String.format("Got value from form : %s",s_input)); 
		 
		 
		// Check on changes. 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set values on the object. 
		 
		Rectangle align = SBounds.parse("align",String.format("align=%s;",s_input)); 
		 
		SBounds salign = new SBounds("align"); 
		 
		if(!salign.parse(String.format("align=%s;",s_input))) 
		{ 
				//gl.tracex(v,String.format("read input...%s...%s",s_input,"Error"));				 
				 
				return; 
		} 
	 
			owner.setAlign(align); 
		 
		if(owner.getType().equalsIgnoreCase("TTextField")) 
	     { 
			 TTextField field = (TTextField)owner; 
			 
			 if(owner.getAlign().getX()== gl.HVA.LEFT) 
				 field.getEdit().setHorizontalAlignment(SwingConstants.LEFT); 
			 else if(owner.getAlign().getX()== gl.HVA.RIGHT) 
				 field.getEdit().setHorizontalAlignment(SwingConstants.RIGHT); 
			 if(owner.getAlign().getX()== gl.HVA.CENTER) 
				 field.getEdit().setHorizontalAlignment(SwingConstants.CENTER); 
				 
	     } 
		 
		owner.repaint(); 
		 
		// Re - check 
		if(SBounds.equal(align,owner.getAlign())) 
			gl.tracex(v,String.format("Check align...%s....%s...%s",s_input,SBounds.toString(owner.getAlign()),"Ok")); 
		else 
			gl.tracex(v,String.format("Check align...%s....%s...%s",s_input,SBounds.toString(owner.getAlign()),"Error")); 
			 
	} 
	 
	public void editConnect(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s%d","",owner.getPid()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int  new_pid = gl.E_ERROR; 
		 
		 
		int pid[] = {new_pid}; 
		 
		try 
		{ 
			new_pid = Integer.parseInt(s_input); 
			 
			pid[0] = new_pid; 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("There is not suitable value  for int...%s",s_input)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("Set new pid value...%d",new_pid)); 
		 
			if(owner.getManager().getSelected().size() != gl.E_EMPTY) 
			{ 
				owner.getManager().getSelected().getData().forEach(c-> 
				{ 
					c.setPid(pid[0]); 
					 
				}); 
				 
				gl.tracex(v,String.format("Update pid for group...%04d",pid[0])); 
			} 
			else 
			{ 
			 
				owner.setPid(new_pid); 
				 
				gl.tracex(v,String.format("Update pid for alone object id...%04d...%04d",owner.getId(),pid[0])); 
				 
			} 
	} 
	 
	public void editId(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s%d","",owner.getId()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int  new_id = gl.E_ERROR; 
		 
		 
		int id[] = {new_id}; 
		 
		try 
		{ 
			new_id = Integer.parseInt(s_input); 
			 
			id[0] = new_id; 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("There is not suitable value  for int...%s",s_input)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("Set new pid value...%d",new_id)); 
		 
			if(owner.getManager().getSelected().size() != gl.E_EMPTY) 
			{ 
				owner.getManager().getSelected().getData().forEach(c-> 
				{ 
					c.setId(id[0]); 
					 
				}); 
				 
				gl.tracex(v,String.format("Update Gid for group...%04d",id[0])); 
			} 
			else 
			{ 
			 
				owner.setId(new_id); 
				 
				gl.tracex(v,String.format("Update pid for alone object id...%04d...gid...%04d",owner.getId(),id[0])); 
				 
			} 
			 
	} 
	 
	public void editBase(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s%d","",owner.getBase()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int  new_base = gl.E_ERROR; 
		 
		 
		int base[] = {new_base}; 
		 
		try 
		{ 
			new_base = Integer.parseInt(s_input); 
			 
			base[0] = new_base; 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("There is not suitable value  for int...%s",s_input)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("Set new base value...%d",new_base)); 
		 
			if(owner.getManager().getSelected().size() != gl.E_EMPTY) 
			{ 
				owner.getManager().getSelected().getData().forEach(c-> 
				{ 
					c.setBase(base[0]); 
					 
				}); 
				 
				gl.tracex(v,String.format("Update Gid for group...%04d",base[0])); 
			} 
			else 
			{ 
			 
				owner.setBase(new_base); 
				 
				gl.tracex(v,String.format("Update pid for alone object id...%04d...gid...%04d",owner.getId(),base[0])); 
				 
			} 
	} 
	 
	public void editOffset(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s%d","",owner.getOffset()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int  new_offset = gl.E_ERROR; 
		 
		 
		int offset[] = {new_offset}; 
		 
		try 
		{ 
			new_offset = Integer.parseInt(s_input); 
			 
			offset[0] = new_offset; 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("There is not suitable value  for int...%s",s_input)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("Set new offset value...%d",new_offset)); 
		 
			if(owner.getManager().getSelected().size() != gl.E_EMPTY) 
			{ 
				owner.getManager().getSelected().getData().forEach(c-> 
				{ 
					c.setBase(offset[0]); 
					 
				}); 
				 
				gl.tracex(v,String.format("Update Gid for group...%04d",offset[0])); 
			} 
			else 
			{ 
			 
				owner.setBase(new_offset); 
				 
				gl.tracex(v,String.format("Update pid for alone object id...%04d...gid...%04d",owner.getId(),offset[0])); 
				 
			} 
	} 
	 
	 
	 
	public void editStartDate(TPanel owner) 
	{ 

		// Get current series. 
		 
		String series = ((TChart) owner).getSeries(); 
		 
		// Get the end date. 

		String last_end_date = DateUtil.to_string(Bar.get_max_date(((TChart) owner).getShow_bars().get(series))); 
		 
		String s_prev_value = String.format("%s",last_end_date); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input date ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(new Object(){},String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Get primary index. 
		 
		int primary_index =  Bar.get_id_by_date(((TChart) owner).getBars().get(series),s_input); 
		 
		if(primary_index == gl.E_ERROR) 
		{ 
			DialogUtil.doError(gl.sf("Error date...%s",s_input)); 
			 
			return; 
		} 
		 
		int right_bound =  ((TChart) owner).getIndex_range().get(series).getEnd(); 
		 
		int gap = 	(primary_index - right_bound); 
		 
		if(gap > gl.E_EMPTY) 
		{ 
			// Go to right direction. 
			owner.chartStepSuite(owner,true,gap); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Get index...%d...for date...%s...gap...%d..RIGHT",gl.S_OK,primary_index,s_input,gap)); 
		} 
		else 
		{ 
			// Go to left direction. 
			owner.chartStepSuite(owner,false,gap*gl.E_ERROR); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Get index...%d...for date...%s...gap...%d..LEFT",gl.S_OK,primary_index,s_input,gap)); 

		} 
		 
	} 
	 
	public void editStartIndex(TPanel owner) 
	{ 

		// Get current series. 
		 
		String series = ((TChart) owner).getSeries(); 
		 
		// Right bound. 
		int right_bound =  ((TChart) owner).getIndex_range().get(series).getEnd(); 
		 
		// Max available index. 
		int max_index =  ((TChart) owner).getIndex_range().get(series).getMax(); 
				 
		// Get the end date. 
		String s_prev_value = String.format("%d",right_bound); 
		 
		String s_input = ap.utils.DlgInteger.getString(gl.sf("Please input index value < %d",max_index+1),s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(new Object(){},String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int i_input = IntArrayUtil.parseInt(s_input); 
		 
		if(i_input == gl.E_ERROR || (i_input > max_index)) 
		{ 
			DialogUtil.doError(gl.sf("Bad integer value...%s",s_input)); 
			 
			return; 
		} 
		 
		int gap = 	(i_input - right_bound); 
		 
		if(gap > gl.E_EMPTY) 
		{ 
			// Go to right direction. 
			owner.chartStepSuite(owner,true,gap); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Get index...%d...for date...%s...gap...%d..RIGHT",gl.S_OK,i_input,s_input,gap)); 
		} 
		else 
		{ 
			// Go to left direction. 
			 
			owner.chartStepSuite(owner,false,gap*gl.E_ERROR); 
			 
			gl.tracex(new Object(){},gl.sf("%s...Get index...%d...for date...%s...gap...%d..LEFT",gl.S_OK,i_input,s_input,gap)); 

		} 
		 
		 
		 
	} 
	 
	 
	public void editGid(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = String.format("%s%d","",owner.getGid()); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		int  new_pid = gl.E_ERROR; 
		 
		 
		int pid[] = {new_pid}; 
		 
		try 
		{ 
			new_pid = Integer.parseInt(s_input); 
			 
			pid[0] = new_pid; 
		} 
		catch(NumberFormatException e) 
		{ 
			gl.tracex(v,String.format("There is not suitable value  for int...%s",s_input)); 
			 
			return ; 
		} 
		 
			gl.tracex(v,String.format("Set new pid value...%d",new_pid)); 
		 
			if(owner.getManager().getSelected().size() != gl.E_EMPTY) 
			{ 
				owner.getManager().getSelected().getData().forEach(c-> 
				{ 
					c.setGid(pid[0]); 
					 
				}); 
				 
				gl.tracex(v,String.format("Update Gid for group...%04d",pid[0])); 
			} 
			else 
			{ 
			 
				owner.setGid(new_pid); 
				 
				gl.tracex(v,String.format("Update pid for alone object id...%04d...gid...%04d",owner.getId(),pid[0])); 
				 
			} 
			 
	} 
	 
	 
	public void editText(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = owner.getText(); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if(s_prev_value.equals(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setText(s_input); 

		owner.translate(owner,gl.E_ERROR,gl.E_ERROR); 
		 
		owner.translate(owner,gl.E_OK,gl.E_OK); 
				 
		 
		if(owner.getText().equals(s_input)) 
		{ 
			gl.tracex(v,String.format("Set text...%s....%s",s_input,"Ok")); 
			 
			if(owner instanceof TLabel) 
			{ 
				owner.setBou_nds(owner.narrowText()); 
				 
			}else if(owner instanceof TPushBtn) 
			{ 
				((TPushBtn)owner).setText(s_input); 
			} 
			 
				owner.repaint(); 
			 
		} 
		else 
			gl.tracex(v,String.format("Set text...%s....%s",s_input,"Error")); 
			 
	} 

	public void editSelectable(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = ""; 
		 
		if(	owner.isSkipSelected()) 
				s_prev_value = "OFF"; 
		else 
				s_prev_value = "ON"; 
			 
		String s_input = ap.utils.DlgInteger.getString("Please input value [0|1]",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY || s_input.equalsIgnoreCase("None")) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		 
		if(s_input.equalsIgnoreCase("ON") || 
				s_input.equalsIgnoreCase("1") || 
				s_input.equalsIgnoreCase("Y") || 
				s_input.equalsIgnoreCase("Yes") 
				) 
		{ 
			 
			owner.setUnSelectable(false); 
			 
			owner.getManager().getSelected().getData().forEach(e-> 
			{ 
				e.setUnSelectable(false); 
			}); 
			 
		} 
		else 
		{ 
			owner.setUnSelectable(true); 
			 
			owner.getManager().getSelected().getData().forEach(e-> 
			{ 
				e.setUnSelectable(true); 
			}); 
			 
			 
		} 
		 
		 	owner.repaint(); 
			 
			gl.tracex(v,String.format("Set skip selected flags to...%s",owner.isSkipSelected())); 
		 
	} 
	 
	public void editFreeze(TPanel owner) { 

		String s_prev_value = owner.isFreeze() ? "ON" : "OFF"; 

		String s_input = ap.utils.DlgInteger.getString("Please input value ", 
				s_prev_value); 

		if ("".equalsIgnoreCase(s_input) 
				|| s_input.trim().length() == gl.E_EMPTY 
				|| s_input.equalsIgnoreCase("None")) { 
			gl.tracex(new Object() {}, String.format("We got NO value .")); 

			return; 

		} 

		if (s_prev_value.equalsIgnoreCase(s_input)) { 
			gl.tracex(new Object() {}, String.format( 
					"There is NO CHANGES between before/after value : %s = %s", 
					s_prev_value, s_input)); 

			return; 
		} 

		// Set. 

		if (s_input.equalsIgnoreCase("ON") || s_input.equalsIgnoreCase("1") 
				|| s_input.equalsIgnoreCase("Y") 
				|| s_input.equalsIgnoreCase("Yes")) { 
			owner.setFreeze(true); 

		} else { 
			owner.setFreeze(false); 
		} 

		owner.getManager().getSelected().data.forEach(a -> { 
			owner.setFreeze(owner.isFreeze()); 
		}); 

		owner.repaint(); 

		gl.tracex(new Object() {}, String.format("Set freeze to...%s", owner.isFreeze())); 

	} 

	 
	 
	public void editFontText(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		Font currentFont = owner.getFont(); 
				 
		String s_prev_value = SFont.toString((currentFont)); 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		SFont sf = new SFont("font"); 
		 
		String payload = String.format("%s=%s%s", sf.getName(),s_input,sf.getAttr_delimeter()); 

		if(!sf.parse(payload)) 
		{ 
			gl.tracex(v,String.format("Check input value...%s...%s",s_input,"Error")); 
			 
			return ; 
		} 
		 
		   if(!Su.isaFont(sf.getFontName())) 
		   { 
			 
			   gl.tracex(v,String.format("%s...%s...%s","Font name",sf.getFontName(),"is not valid")); 

			   return; 
		   } 
			 
		   	   owner.setFont(sf.getFont()); 
		   	 
		   	if(owner.getType().equalsIgnoreCase("TTextField")) 
		     { 
		    	 TTextField field = (TTextField)owner; 
		    	 
		    	 field.getEdit().setFont(owner.getFont()); 
		    	 
		    	 field.getEdit().repaint(); 
		    	 
		    	 
		     }else if(owner.getType().equalsIgnoreCase("TTextArea")) 
		     { 
		    	 TTextArea field = (TTextArea)owner; 
		    	 
		    	 field.getEdit().setFont(owner.getFont()); 
		    	 
		    	 field.getEdit().repaint(); 
		     } 
	 
			 
			   owner.repaint(); 
			 
			   gl.tracex(v,String.format("Check input value...%s...%s",s_input,"Ok")); 
		   			 
	} 
 
	 
	public void editBorder(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = owner.getBor_der(); 
		 
		if(s_prev_value == null) 
			s_prev_value = ""; 
		 
		 
		String s_input = ap.utils.DlgInteger.getString("Waiting input of border type (RBB|LBB|ER|EL|DA) ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("%s...no value",gl.S_OK)); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("%s...No changes...%s = %s",gl.S_OK,s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setBorder(s_input); 
		 
		owner.invalidate(); 
				 
			 
	} 
	 
	public void editAction(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = owner.getAction(); 
		 
		if(s_prev_value == null) 
			s_prev_value = ""; 
		 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setAction(s_input); 
		 
		gl.tracex(v,String.format("new value...%s",s_input)); 
			 
	} 
	 
	public void editName(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = owner.getName(); 
		 
		if(s_prev_value == null) 
			s_prev_value = ""; 
		 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setAction(s_input); 
		 
		gl.tracex(v,String.format("new value...%s",s_input)); 
			 
	} 
	 
	public void editFunction(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = owner.getFunction(); 
		 
		if(s_prev_value == null) 
			s_prev_value = ""; 
		 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input text ",s_prev_value); 
		 
		if("".equalsIgnoreCase(s_input) || s_input.trim().length() == gl.E_EMPTY) 
		{ 
			gl.tracex(v,String.format("We got NO value .")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		// Set. 
		 
		owner.setFunction(s_input); 
		 
		gl.tracex(v,String.format("new value...%s",s_input)); 
			 
	} 
		 
	public void setBkgColorText(TPanel owner) 
	{ 
		Object v = new Object (){}; 
		 
		String s_prev_value = SColor.toString(owner.getBackground()); 
		 
		// Read additional shadow tags. 
		 
		String s_tag =  ""; 
		 
		String s_input = ap.utils.DlgInteger.getString("Please input background color value",s_prev_value); 
		 
		if("None".equalsIgnoreCase(s_input) || "".equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("Input NONE value in the dialog.")); 
			 
			return; 
			 
		} 
		 
		if(s_prev_value.equalsIgnoreCase(s_input)) 
		{ 
			gl.tracex(v,String.format("There is NO CHANGES between before/after value : %s = %s",s_prev_value,s_input)); 
			 
			return; 
		} 
		 
		SColor sa = new SColor(); 
		 
		gl.tracex(v,String.format("Input value is : %s ",s_input)); 
		 
		String payload = String.format("%s=%s%s", sa.getName(),s_input,sa.getAttr_delimeter()); 
		 
		if (sa.parse(payload)) 
		{ 
			gl.tracex(v,String.format("Input value is : %s...%s ","Ok",sa.toString())); 
			 
			if( ! 
					owner.setBack_ground(sa.getColor()) 
					) 
					{ 
						gl.tracex(v,String.format("Set backgroud...%s","Error")); 
						 
						return; 
					} 
					 
					gl.tracex(v,String.format("Set gradient...%s...%s",SColor.toString(sa.getColor()),"Ok")); 
					 
					 
					 // For group . 
					 
					owner.getManager().getSelected().getData().forEach(e-> 
					{ 
						 
						e.setBack_ground(owner.getBackground()); 
						 
						if(owner.getKeyHandler().isBlCtrl()) 
							e.setBack_ground(gl.getRandomColor()); 
						 
						e.repaint(); 
					} 
					);			 
					 
					 
					owner.repaint(); 
			 
		} 
		else 
			gl.tracex(v,String.format("Input value is : %s","Error")); 


	} 
	 
	} 
	 

// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
