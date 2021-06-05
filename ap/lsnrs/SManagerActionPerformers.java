 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.lsnrs; 


import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import ap.mng.SPanelManager; 
import ap.utils.Fu; 




public class SManagerActionPerformers implements ActionListener { 

	 
	private SPanelManager owner = null; 
	 
	 
	/** 
	 * @return the owner 
	 */ 
	public SPanelManager getOwner() { 
		return owner; 
	} 

	/** 
	 * @param owner the owner to set 
	 */ 
	public void setOwner(SPanelManager owner) { 
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

		if (a.equalsIgnoreCase("refreshLite")) { 
							 
			owner.refreshZorder(); 
		 
		} // refresh 
		else if (a.equalsIgnoreCase("fileSave")) { 
			 
			owner.fileSave(); 

		} // fileSave 
		else if (a.equalsIgnoreCase("fileSaveAs")) { 

			Fu.to_file_dlg_save_as(owner); 
			 
		} // saveFileAs 

		else if (a.equalsIgnoreCase("fileOpen")) { 

			owner.getFilesSuite(); 

		} // fileOpen 
		else if (a.equalsIgnoreCase("openImages")) { 

			owner.openGraphicsFilesSuite(); 
			 
		} // openImages 

		else if (a.equalsIgnoreCase("deleteSelected")) { 

			owner.deleteSelected(); 
		} // fileOpen 
		else if (a.equalsIgnoreCase("SelectAll")) { 

			owner.selectAll(); 
			 
		} // selectAll 
		else if (a.equalsIgnoreCase("DeSelectAll")) { 

			owner.deselectAll(); 
			 
		} // DeSelectAll 
		else if (a.equalsIgnoreCase("establishOrder")) { 

			owner.establishOrder(); 
			 
		} // establishOrder 
		else if (a.equalsIgnoreCase("exportImage")) { 

			owner.exportImage(); 
			 
		} // exportImage 
		 
		 
		 
		 
		else if (a.equalsIgnoreCase("cloneComposite")) { 

			owner.cloneComposite(); 
			 
		} // cloneComposite 
		 
		 		 
		else if (a.equalsIgnoreCase("TPanel") || 
				 a.equalsIgnoreCase("TSphere") || 
				 a.equalsIgnoreCase("TPushBtn") || 
				 a.equalsIgnoreCase("TPopupPushBtn") || 
				 a.equalsIgnoreCase("TPopupBtn") || 
				 a.equalsIgnoreCase("TBtn") || 
				 a.equalsIgnoreCase("TTextField") || 
				 a.equalsIgnoreCase("TTextArea") || 
				 a.equalsIgnoreCase("TCheckBox") || 
				 a.equalsIgnoreCase("TRadioButton") || 
				 a.equalsIgnoreCase("TLabel") || 
				 a.equalsIgnoreCase("TImage") || 
				 a.equalsIgnoreCase("TChart") || 
				 a.equalsIgnoreCase("TCanvas") 
				 				 
				) { 

			if (owner.createObject(a)) 
			 	owner.refreshZorder(); 
			 
		} // establishOrder 
		 
		 
	} 
	 
	} 
	 


// Revision : 20.01.2017 15:56:38 
// Revision : 28.01.2017 15:14:44 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
