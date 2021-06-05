 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.menu; 

import java.awt.event.MouseEvent; 

import ap.btn.TPanel; 
import ap.lsnrs.SPanelActionPerformers; 
import ap.ray.gl; 

public class TPanelPopupFactory { 

	public TPanelPopupFactory() { 
		 
	} 
	 
	public static SPopupMenu createPopup(TPanel owner, 
			MouseEvent pevent) { 

		SPopupMenu pm = new SPopupMenu(); 

		SPanelActionPerformers pal = owner.getPal(); 
		 
		pal.setOwner(owner); 
				 
		SMenu editMenu = new SMenu("Edit..."); 
		 
		SMenu loadData = new SMenu("Load..."); 

		SMenu textMenu = new SMenu("Text..."); 
		 
		SMenu gridMenu = new SMenu("Grid..."); 
		 
		SMenu chartMenu = new SMenu("Chart..."); 
		 
			  chartMenu.setEnabled(false); 
			 
		SMenuItem setStartDate  = new SMenuItem("Set start date", "editStartDate", pal); 
				 
		SMenuItem setStartIndex = new SMenuItem("Set start index", "editStartIndex", pal); 
		 
				  //setStartDate.setEnabled(owner.getShow_ticks().size() != gl.E_EMPTY); 
				 
				  setStartIndex.setEnabled(setStartDate.isEnabled()); 
				 
				  chartMenu.add(setStartDate); 
				 
				  chartMenu.add(setStartIndex); 
				 
			 
		SMenu colorsMenu = new SMenu("Colors..."); 

		SMenu imageMenu = new SMenu("Image..."); 

		SMenu fontMenu = new SMenu("Font..."); 

		SMenu menuColorByForm = new SMenu("By forms..."); 

		SMenu menuColorByText = new SMenu("By text..."); 

		colorsMenu.add(menuColorByForm, null, menuColorByText); 

		SMenuItem importImage = new SMenuItem("Import", "importImage", pal); 

		SMenuItem clearImage = new SMenuItem("Clear", "clearImage", pal); 
		 
		SMenuItem exportImage = new SMenuItem("Export", "exportImage", pal); 

		SMenuItem fitToImage = new SMenuItem("FitToImage", "fitToImage", pal); 

		fitToImage.setEnabled((((TPanel) owner).getImage() == null ? false 
				: true)); 

		SMenuItem editText = new SMenuItem("Edit", "editText", pal); 

		SMenuItem editAlign = new SMenuItem("Align", "editAlign", pal); 

		SMenuItem editBorder = new SMenuItem("Border", "editBorder", pal); 

		SMenuItem editAction = new SMenuItem("Action", "editAction", pal); 

		SMenuItem editBase   = new SMenuItem("Base", "editBase", pal); 
		 
		SMenuItem editOffset = new SMenuItem("Offset", "editOffset", pal); 
		 
		 
		SMenuItem editFunction = new SMenuItem("Function", "editFunction", pal); 
		 
		SMenuItem editFont = new SMenuItem("By form", "editFont", pal); 

		SMenuItem editFontText = new SMenuItem("By text", "editFontText", pal); 

		SMenuItem editTextColor = new SMenuItem("Color", "editTextColor", pal); 
		 
		SMenuItem editGridColor = new SMenuItem("Color", "editGridColor", pal); 

		SMenuItem editType = new SMenuItem("Type", "editType", pal); 
		 
		SMenuItem editData = new SMenuItem("Data", "editData", pal); 
				 
		SMenuItem editToolTip = new SMenuItem("ToolTip", "editToolTip", pal); 

		SMenuItem editBounds = new SMenuItem("Bounds", "editBounds", pal); 

		SMenuItem editChildArea = new SMenuItem("Child area", "editChildArea", 
				pal); 

		SMenuItem editAreaManager = new SMenuItem("Areamanager", 
				"editareamanager", pal); 

		SMenuItem deleteObject = new SMenuItem("Delete", "Delete", pal); 

		SMenuItem cloneObject = new SMenuItem("Clone", "Clone", pal); 

		SMenuItem cloneByParams = new SMenuItem("Clone by params...", 
				"CloneByParams", pal); 

		SMenuItem connectObject = new SMenuItem("Connect", "Connect", pal); 
		 
		SMenuItem editGid = new SMenuItem("Gid", "editGid", pal); 
		 
		SMenuItem editId = new SMenuItem("Id", "editId", pal); 
		 
		SMenuItem refreshObject = new SMenuItem("Refresh", "refresh", pal); 

		SMenuItem zorderObject = new SMenuItem("Z-order", "setzorder", pal); 

		SMenuItem bkgColorForm = new SMenuItem("Background", "setbkgcolorform", 
				pal); 

		SMenuItem grdColorForm = new SMenuItem("Gradient", "setgrdcolorform", 
				pal); 

		SMenuItem bkgColorText = new SMenuItem("Background", "setbkgcolortext", 
				pal); 

		SMenuItem grdColorText = new SMenuItem("Gradient", "setgrdcolortext", 
				pal); 
		 
		 
		SMenuItem loadQuotes = new SMenuItem("Quotes", "loadquotes", 
				pal); 
				 
		 
		if(owner.getType().equalsIgnoreCase("TChart") && owner.getFunction().equalsIgnoreCase("TChart")) 
		{ 
			chartMenu.setEnabled(true); 
		} 
		else 
			chartMenu.setEnabled(false); 
			 

		menuColorByForm.add(bkgColorForm, null, grdColorForm); 

		menuColorByText.add(bkgColorText, null, grdColorText); 
		 
		 
		 
		 

		String menuDebug = String.format("Debug %s", "%s"); 
		 
		String menuFreeze = String.format("Freeze %s", "%s"); 
		 
		String menuSelectable = String.format("Selectable %s", "%s"); 
		 
		 
		String menuVisible = String.format("Visible %s", "%s"); 
		 
		String menuEnable = String.format("Enable %s", "%s"); 
		 
		String menuDenyx = String.format("Deny X %s", "%s"); 
		 
		String menuDenyy = String.format("Deny Y %s", "%s"); 
		 
		String menuMoveInside = String.format("Move inside %s", "%s"); 
		 
		 
		String menuDebugActionCommand = "debugOff"; 

		String menuFreezeActionCommand = "freezeOff"; 
		 
		String menuSelectableActionCommand = "selectableOff"; 
		 
		 
		String menuVisibleActionCommand = "visibleOff"; 
 
		String menuEnableActionCommand = "enableOff"; 
				 
		String menuDenyxActionCommand = "denyxOff"; 
				 
		String menuDenyyActionCommand = "denyyOff"; 
		 
		String menuMoveInsideActionCommand = "moveinsideOff"; 
		 
			 

		 
		//menuDenyY 
		if (owner.isDenyy()) { 
			menuDenyy = String.format(menuDenyy, "...On"); 

			menuDenyyActionCommand = "denyyOff"; 
		} else { 
			menuDenyy = String.format(menuDenyy, "...Off"); 

			menuDenyyActionCommand = "denyyOn"; 
		} 

		//menuDenyX 
		if (owner.isDenyx()) { 
			menuDenyx = String.format(menuDenyx, "...On"); 

			menuDenyxActionCommand = "denyxOff"; 
		} else { 
			menuDenyx = String.format(menuDenyx, "...Off"); 

			menuDenyxActionCommand = "denyxOn"; 
		} 
		 
		//menuEnable 
		if (owner.isEnable()) { 
			menuEnable = String.format(menuEnable, "...On"); 

			menuEnableActionCommand = "enableOff"; 
		} else { 
			menuEnable = String.format(menuEnable, "...Off"); 

			menuEnableActionCommand = "enableOn"; 
		} 
		 
		//menuVisible 
		if (owner.isVisible()) { 
			menuVisible = String.format(menuVisible, "...On"); 

			menuVisibleActionCommand = "visibleOff"; 
		} else { 
			menuVisible = String.format(menuVisible, "...Off"); 

			menuVisibleActionCommand = "visibleOn"; 
		} 

		 
		 
		//menuDebug 
		if (owner.isDebug()) { 
			menuDebug = String.format(menuDebug, "...On"); 

			menuDebugActionCommand = "debugOff"; 
		} else { 
			menuDebug = String.format(menuDebug, "...Off"); 

			menuDebugActionCommand = "debugOn"; 
		} 
		 
		//menuFreeze 
		if (owner.isFreeze()) { 
			menuFreeze = String.format(menuFreeze, "...On"); 

			menuFreezeActionCommand = "freezeOff"; 
		} else { 
			menuFreeze = String.format(menuFreeze, "...Off"); 

			menuFreezeActionCommand = "freezeOn"; 
		} 
		 
		//menuSelectable 
		if (!owner.isSkipSelected()) { 
			menuSelectable = String.format(menuSelectable, "...On"); 

			menuSelectableActionCommand = "selectableOn"; 
		} else { 
			menuSelectable = String.format(menuSelectable, "...Off"); 

			menuSelectableActionCommand = "selectableOff"; 
		} 
					 
		// menuMoveInside 
		if (owner.isMoveInside()) { 
			menuMoveInside = String.format(menuMoveInside, "...On"); 

			menuMoveInsideActionCommand = "moveinsideOff"; 
		} else { 
			menuMoveInside = String.format(menuMoveInside, "...Off"); 

			menuMoveInsideActionCommand = "moveinsideOn"; 
		} 
		 

		imageMenu.add(importImage, null,clearImage, null,fitToImage, null, exportImage); 

		SMenuItem debug = new SMenuItem(menuDebug,menuDebugActionCommand, pal); 
		 
		SMenuItem freeze = new SMenuItem(menuFreeze,menuFreezeActionCommand, pal); 
		 
		SMenuItem selectable = new SMenuItem(menuSelectable,menuSelectableActionCommand, pal); 
		 
		 
		SMenuItem enable = new SMenuItem(menuEnable,menuEnableActionCommand, pal); 
		 
		SMenuItem visible = new SMenuItem(menuVisible,menuVisibleActionCommand, pal); 
		 
		SMenuItem denyx = new SMenuItem(menuDenyx,menuDenyxActionCommand, pal); 
		 
		SMenuItem denyy = new SMenuItem(menuDenyy,menuDenyyActionCommand, pal); 
		 
		SMenuItem moveinside = new SMenuItem(menuMoveInside,menuMoveInsideActionCommand, pal); 
		 
		moveinside.setEnabled(owner.getPid() != gl.E_ERROR); 
		 
		visible.setEnabled(false); 
		 
		textMenu.add(editText, null, editAlign, null,editTextColor); 

		fontMenu.add(editFont, null, editFontText); 

		textMenu.add(fontMenu); 

		editMenu.add(textMenu); 

		editMenu.add(imageMenu); 
		 
		gridMenu.add(editChildArea); 
		 
		gridMenu.add(editGridColor); 
		 
		editMenu.add(gridMenu); 
		 
		editMenu.add(chartMenu); 

		editMenu.add(editType, null,editData,null,editToolTip,null,connectObject,editId,editGid,null); 

		editMenu.add(colorsMenu); 

		editMenu.add(zorderObject, null, editBounds, null, editBorder, null, 
				editAction, null, 
				editBase, null, 
				editOffset, null, 
				editFunction,null, 
				editAreaManager); 

		loadData.add(loadQuotes); 
		 
		pm.add(editMenu, null); 
		 
		pm.add(loadData); 

		pm.add(deleteObject, null, cloneObject, null, cloneByParams, null, 
				refreshObject, null,enable,visible,denyx,denyy, debug,freeze,selectable,moveinside); 

		return pm; 

	} 


	public static void main(String[] args) { 
		 
	} 

} 
