 
 
 
 
 
 
 
 
 
 
 
 
/** 
* 
*/ 
package ap.btn; 

import java.awt.event.KeyEvent; 
import java.util.List; 

import javax.swing.JTextArea; 

import ap.btn.TPanel; 
import ap.global.gl; 
import ap.state.Fl; 
import ap.swing.Application; 
import ap.swing.PanelXml; 
import ap.utils.Biu; 
import ap.utils.Fu; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 21 ???? 2017 ?. 15:30:32 
* Project  name : Organizer 
* Package  name : ap.panels 
* File     name : SKeyHandler.java 
* 
*/ 
public class SKeyHandler extends SKeyDef { 
	 
	 

	public SKeyHandler() { 

	} 

	public static void main(String[] args) { 

	} 

		 
	public void handler(JTextArea owner) 
	{ 
		 
		if(this.isBlCtrl() && this.isBlV()) 
		{ 
			gl.smn("---> Paste"); 
		} else if(this.isBlCtrl() && this.isBlC()) 
		{ 
			gl.smn("---> Copy"); 
		} 
		else if(this.isBlCtrl() && this.isBlA()) 
		{ 
				gl.smn("---> Copy All"); 
		} 
		 
		else if(this.isBlCtrl() && this.isBlZ()) 
		{ 
				gl.smn("---> Clear All"); 
				 
				owner.setText(""); 
		} else if( this.isBlCtrl() && this.isBlS()) 
		{ 
				gl.smn("---> Save to file "); 
				/* 
				if (FileUtil.fileSaveDlg(owner.getText())) 
				{ 
					gl.smn("Save to file.Ok."); 
				} 
				*/ 
				 
			 
				 
		} else if((this.isBlO() && this.isBlAlt())) 
		{ 
			 
			 
			dispatcher("openFile",owner); 
			 
		} 
		 
		//gl.smn("Alt : " + this.isBlAlt() + " S : " + this.isBlS() + " O : " + this.isBlO()); 
			 
	} 
	 
	 
	public void freeTextArea() 
	{ 
		this.setBlC(false); 
		 
		this.setBlV(false); 
		 
		this.setBlA(false); 
		 
		this.setBlZ(false); 
		 
		this.setBlS(false); 
		 
		this.setBlG(false); 
		 
		this.setBlO(false); 
				 
		this.setBlCtrl(false); 
		 
		this.setBlAlt(false); 
		 
		this.setBlShift(false); 
		 
		 
		 
		 
		 
	} 

	 
	public void dispatcher(String cmd,JTextArea owner) 
	{ 
		if(cmd.equalsIgnoreCase("openFile")) 
		{ 
				gl.smn("---> Open a file "); 
			 
				owner.setText(Fu.dlgGetFileSource()); 
		} 
			 
	} 
	 
	public void handle(PanelXml owner) 
	{ 
		if(this.isBlCtrl() && this.isBlI() && !isBlAlt() && isBlL()) 
		{ 
				gl.smn("Load image..." + owner.getName()); 

				Fu.import_image_dlg(owner,false,Application.getCio().getHome()); 
				 
		} 
		 
			 
		 
	 
	} 
	 
	 
	public void handle(TPanel owner) 
	{ 
	 
		if(this.isBlAlt() && this.isBlS()) 
		{ 
			if (Fu.dlgSaveStringToFile(owner.getClass().getSimpleName())) 
			{ 
				gl.smn("Save to file.Ok."); 
			} 
		} 
	 
		 
		 
		if(this.isBlAlt() && this.isBlT()) 
		{ 
				owner.setBl_tool_tip(!owner.isBl_tool_tip()); 
				 
				gl.smn(String.format("ToolTip is %s for object id : %04d",owner.isBl_tool_tip(),owner.getId())); 
				 
				 
		} 
		 
		if(this.isBlAlt() && this.isBlG()) 
		{ 
				owner.setBlGrid(!owner.isBlGrid()); 
				 
				owner.repaint(); 
				 
				if(owner.isBlGrid()) 
				owner.setFlags(Biu.ON(owner.getFlags(),Fl.VK_GRID)); 
				else 
				owner.setFlags(Biu.OFF(owner.getFlags(),Fl.VK_GRID)); 
				 
				gl.smn(String.format("Grid is %s for object id : %04d ...state...%d",owner.isBlGrid(),owner.getId(),owner.getFlags())); 
				 
					 
					 
		} 
		 
		if(this.isBlCtrl() && this.isBlAlt() && this.isBlC()) 
		{ 
				//owner.setBlCut(!owner.isBlCut()); 
				 
				//owner.repaint(); 
				 
				gl.smn(String.format("Setup cut mode in %s for object id : %04d",owner.isBlCut(),owner.getId())); 
				 
		} 
		 
		if(this.isBlCtrl() && !this.isBlAlt() && !this.isBlC()) 
		{ 
				//gl.smn(String.format("Ctrl is ON.")); 
		} 
		 
		 
		
		if(this.isBlRight()) 
		{ 
			owner.actionResolver("csr"); 
		} 
		 
		if(this.isBlLeft()) 
		{ 
			owner.actionResolver("csl"); 
		} 
		 
		if(this.isBlPageUp()) 
		{ 
			owner.actionResolver("cpr"); 
		} 
		 
		if(this.isBlPageDown()) 
		{ 
			owner.actionResolver("cpl"); 
		} 
		 
		if(this.isBlUp()) 
		{ 
			gl.smn("---> Up"); 
		} 
		 
		if(this.isBlDown()) 
		{ 
			gl.smn("---> Down"); 
		} 
		 
		 

	   		 
	} 
	 
	public void free(TPanel owner) 
	{ 
		dx=dy=gl.E_EMPTY; 
		 
	} 
	 
	public void free(PanelXml owner) 
	{ 
		dx=dy=gl.E_EMPTY; 
		 
		owner.requestFocus(false); 
	} 
	 
} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:13 
