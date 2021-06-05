 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.menu; 

import java.awt.Component; 
import java.awt.Font; 
import java.awt.event.MouseEvent; 

import javax.swing.JMenuItem; 
import javax.swing.JPopupMenu; 

import ap.global.gl; 
import ap.lsnrs.SManagerActionPerformers; 




public class TManagerPopupFactory { 

	public TManagerPopupFactory() { 
		 
	} 
	 
	public static void createPopup(ap.mng.SPanelManager owner,MouseEvent pevent) 
	{ 
	 
	JPopupMenu 	pm = new JPopupMenu(); 

	SManagerActionPerformers pal = new SManagerActionPerformers(); 

						   pal.setOwner(owner); 

	 
	SMenu	 	newMenu = new SMenu("New..."); 
	 
	 
	SMenuItem	newTPanel = new SMenuItem("TPanel"); 
				 
				newTPanel.setActionCommand("TPanel"); 
			 
				newTPanel.addActionListener(pal); 

	SMenuItem	newTCanvas = new SMenuItem("TCanvas"); 
				 
				newTCanvas.setActionCommand("TCanvas"); 
			 
				newTCanvas.addActionListener(pal); 

				 
	SMenuItem	newTSphere = new SMenuItem("TSphere"); 
				 
				newTSphere.setActionCommand("TSphere"); 
			 
				newTSphere.addActionListener(pal); 

	SMenuItem	newTPushBtn = new SMenuItem("TPushBtn"); 
				 
				newTPushBtn.setActionCommand("TPushBtn"); 
			 
				newTPushBtn.addActionListener(pal); 
				 
				 
	SMenuItem	newTPopupPushBtn = new SMenuItem("TPopupPushBtn"); 
				 
				newTPopupPushBtn.setActionCommand("TPopupPushBtn"); 
			 
				newTPopupPushBtn.addActionListener(pal); 
					 
	 
	SMenuItem	newTBtn = new SMenuItem("TBtn"); 
				 
				newTBtn.setActionCommand("TBtn"); 
			 
				newTBtn.addActionListener(pal); 
				 
	SMenuItem	newTPopupBtn = new SMenuItem("TPopupBtn"); 
				 
				newTPopupBtn.setActionCommand("TPopupBtn"); 
			 
				newTPopupBtn.addActionListener(pal); 
				 

	SMenuItem	newTTextField = new SMenuItem("TTextField"); 
							 
				newTTextField.setActionCommand("TTextField"); 
					 
				newTTextField.addActionListener(pal); 
							 


	SMenuItem	newTTextArea = new SMenuItem("TTextArea"); 
										 
				newTTextArea.setActionCommand("TTextArea"); 
							 
				newTTextArea.addActionListener(pal); 
				 

	SMenuItem	newTRadioButton = new SMenuItem("TRadioButton"); 
				 
				newTRadioButton.setActionCommand("TRadioButton"); 
							 
				newTRadioButton.addActionListener(pal); 
	 
				 
	SMenuItem	newTCheckBox = new SMenuItem("TCheckBox"); 
				 
				newTCheckBox.setActionCommand("TCheckBox"); 
							 
				newTCheckBox.addActionListener(pal); 
				 

	SMenuItem	newTLabel = new SMenuItem("TLabel"); 
				 
				newTLabel.setActionCommand("TLabel"); 
							 
				newTLabel.addActionListener(pal); 

				 
	SMenuItem	newTImage = new SMenuItem("TImage"); 
				 
				newTImage.setActionCommand("TImage"); 
							 
				newTImage.addActionListener(pal); 
	 
	SMenuItem	newTChart = new SMenuItem("TChart"); 
				 
				newTChart.setActionCommand("TChart"); 
							 
				newTChart.addActionListener(pal); 
	 
				 
				 
				SMenuItem  items[] = new SMenuItem[]{ 
						newTPanel,null, 
						newTSphere,null, 
						newTPushBtn,null, 
						newTPopupPushBtn,null, 
						newTPopupBtn,null, 
						newTBtn,null, 
						newTTextField,null, 
						newTTextArea,null, 
						newTCheckBox,null, 
						newTRadioButton,null, 
						newTLabel,null, 
						newTImage,null, 
						newTChart,null, 
						newTCanvas 
						}; 
				 
				newMenu.add(items); 
				 
				 
	 
						 
JMenuItem		openImages = new JMenuItem("Open images"); 
 
				openImages.setActionCommand("openImages"); 

				openImages.addActionListener(pal); 
	 
						 
JMenuItem	establishOrder = new JMenuItem("Establish order"); 
 
				establishOrder.setActionCommand("establishOrder"); 

				establishOrder.addActionListener(pal); 
	 
				establishOrder.setEnabled( 
			 
						( 
								owner.getSelected().size() !=gl.E_EMPTY) ? true : false 
			 
						); 
	 
JMenuItem	exportImage = new JMenuItem("Export image"); 
				 
				exportImage.setActionCommand("exportImage"); 

				exportImage.addActionListener(pal); 
	 
				/* 
				exportImage.setEnabled( 
			 
						( 
								owner.getSelected().size() !=gl.E_EMPTY) ? true : false 
			 
						); 
	 
				*/ 
 
JMenuItem 	selectAll = new JMenuItem("SelectAll"); 

				selectAll.setActionCommand("selectAll"); 

				selectAll.addActionListener(pal); 
				 
				selectAll.setEnabled( 
						 
						(owner.getSize()==gl.E_EMPTY) ? false : true 
						 
						); 
				 
JMenuItem 	deselectAll = new JMenuItem("DeSelectAll"); 

				deselectAll.setActionCommand("DeSelectAll"); 

				deselectAll.addActionListener(pal); 
				 
				deselectAll.setEnabled( 
						 
						(owner.getSelected().size() !=gl.E_EMPTY) ? true : false 
						 
						); 
				 
						 

	JMenuItem 	refreshObject = new JMenuItem("Refresh"); 

				refreshObject.setActionCommand("refreshLite"); 

				refreshObject.addActionListener(pal); 


	JMenuItem 	fileOpen = new JMenuItem("Open..."); 

				fileOpen.setActionCommand("fileOpen"); 

				fileOpen.addActionListener(pal); 
				 
				 
								 
	JMenuItem 	cloneComposite = new JMenuItem("Clone..."); 

				cloneComposite.setActionCommand("cloneComposite"); 

				cloneComposite.addActionListener(pal); 
				 
				cloneComposite.setEnabled(owner.isWasSaved()); 
		 		 
				 

	JMenuItem 	saveFile = new JMenuItem("Save"); 

				saveFile.setActionCommand("fileSave"); 

				saveFile.addActionListener(pal); 
				 
				saveFile.setEnabled(owner.getSize() != gl.E_EMPTY); 
				 
						 
			 
		 
	JMenuItem 	saveFileAs = new JMenuItem("Save As ..."); 

				saveFileAs.setActionCommand("fileSaveAs"); 

				saveFileAs.addActionListener(pal); 
				 
				saveFileAs.setEnabled(owner.getSize() != gl.E_EMPTY); 
				 
				 
	JMenuItem 	deleteObjects = new JMenuItem("Delete..."); 

				deleteObjects.setActionCommand("deleteSelected"); 

				deleteObjects.addActionListener(pal); 
				 
				deleteObjects.setEnabled(owner.getCollector().getSelected().size() != gl.E_EMPTY); 
		 
	 
				pm.add(newMenu); 
				 
				pm.add(fileOpen); 
	 
				pm.addSeparator(); 
		 
				 
				pm.add(openImages); 
				 
				pm.addSeparator(); 

				 
				pm.add(refreshObject); 
	 
				pm.addSeparator(); 
	 
				 
				pm.add(selectAll); 
				 
				pm.addSeparator(); 
				 
				 
				pm.add(deselectAll); 
				 
				pm.addSeparator(); 
				 

				pm.add(establishOrder); 
				 
				pm.addSeparator(); 
				 
				 
				pm.add(exportImage); 
				 
				pm.addSeparator(); 
				 
				 
				pm.add(cloneComposite); 
				 
				pm.addSeparator(); 
				 
				 
				 
				pm.add(deleteObjects); 
				 
				pm.addSeparator(); 
	 
				 
				pm.add(saveFile); 
	 
				pm.addSeparator(); 
				 
	 
				pm.add(saveFileAs); 
	 
				gl.changeFont(pm, new Font("Tahoma", 1, 9)); 

				pm.show(owner.getFrame().HDC, pevent.getX(), pevent.getY()); 
	 
	} 
	 
	 

	public static void main(String[] args) { 
		 

	} 

} 
