 
package ap.uat; 

import java.awt.event.ActionEvent; 
import java.util.List; 
import javax.swing.AbstractAction; 
import javax.swing.Action; 
import javax.swing.ImageIcon; 
import ap.global.gl; 
import ap.uat.AtOm; 

public class BaseActionA extends AbstractAction { 

	private static final long serialVersionUID = 1L; 
	 
	private AtOm owner = null; 
	 
	private List<String> cmds = null; 
	 
	public List<String> getCmds() { 
		return cmds; 
	} 

	public void setCmds(List<String> cmds) { 
		this.cmds = cmds; 
	} 
	 
	public AtOm getOwner() { 
		return owner; 
	} 

	public void setOwner(AtOm owner) { 
		this.owner = owner; 
	} 

	 
	public BaseActionA() { 

	} 
	 
	public BaseActionA(String text, ImageIcon icon, String desc, Integer mnemonic) { 
		super(text, icon); 

		putValue(SHORT_DESCRIPTION, desc); 

		putValue(MNEMONIC_KEY, mnemonic); 

	} 

	public BaseActionA(String text) { 
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
	} 
	 
	public BaseActionA(String text,List<String> cmd_list) { 
		 
		super(text,null); 

		putValue(SHORT_DESCRIPTION, text); 
		 
		this.setCmds(cmd_list); 
	} 
	 
	public boolean before() 
	{ 
		return true; 
	} 
	 
	public boolean after(AtOm owner) 
	{ 
		return true; 
	} 
	 
	public String getActionName() 
	{ 
		return gl.sf("%s",this.getValue(Action.NAME)); 
	} 
	 
	public boolean actionPerformed(AtOm owner) { 
		 
		return true; 
		 
	} 
	 
	boolean checkParams(AtOm owner) 
	{ 
		return true; 
	}; 
	 
	boolean actionProlog(AtOm owner) 
	{ 
		return true; 
	}; 
		 
		 
	@Override 
	public void actionPerformed(ActionEvent e) { 
		 
	} 
	 
	public static void main(String[] args) { 

		 

	} 

} 
