 
 
 
 
 
 
 
package ap.action; 

import java.awt.event.ActionEvent; 
import java.util.List; 
import javax.swing.AbstractAction; 
import javax.swing.Action; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.swing.PanelXml; 

public class BaseAction extends AbstractAction { 

	private static final long serialVersionUID = 1L; 
	 
	private PanelXml owner = null; 
	 
	private List<String> cmds = null; 
	 
	public List<String> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<String> cmds) { 
		this.cmds = cmds; 
	} 
	 
	public PanelXml getOwner() { 
		return owner; 
	} 

	public void setOwner(PanelXml owner) { 
		this.owner = owner; 
	} 

	 
	public BaseAction() { 

	} 
	 
	public BaseAction(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon); 

		putValue(SHORT_DESCRIPTION, desc); 

		putValue(MNEMONIC_KEY, mnemonic); 

	} 

	public BaseAction(String text) { 
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
	} 
	 
	public BaseAction(String text,List<String> cmd_list) { 
		 
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
		 
		this.setCmds(cmd_list); 
	} 
	 
	public boolean before() 
	{ 
		return true; 
	} 
	 
	public boolean after(PanelXml owner) 
	{ 
		return true; 
	} 
	 
	public String getActionName() 
	{ 
		return gl.sf("%s",this.getValue(Action.NAME)); 
	} 
	 
	public boolean actionPerformed(PanelXml owner) { 
		 
		return true; 
		 
	} 
	 
	boolean checkParams(PanelXml owner) 
	{ 
		return true; 
	}; 
	 
	boolean actionProlog(PanelXml owner) 
	{ 
		return true; 
	}; 
		 
		 
	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
	} 
	 
	public static void main(String[] args) { 

		 

	} 

} 
