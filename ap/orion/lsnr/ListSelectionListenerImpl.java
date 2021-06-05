package ap.orion.lsnr;

import java.util.EventListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ap.global.gl;
import ap.orion.Orion;

public class ListSelectionListenerImpl implements ListSelectionListener {

	
	private Orion owner;
	
	
	public Orion getOwner() {
		return owner;
	}

	public void setOwner(Orion owner) {
		this.owner = owner;
	}

	public ListSelectionListenerImpl() {
		
	}
	
	public ListSelectionListenerImpl(Orion owner) {
		
		this.setOwner(owner);
	}

	public static ListSelectionListenerImpl get_instance()
	{
		return new ListSelectionListenerImpl();
	}
	

	public static ListSelectionListenerImpl get_instance(Orion owner)
	{
		return new ListSelectionListenerImpl(owner);
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	    	int m_index = ((JList)e.getSource()).getSelectedIndex();
	    			
	        if (m_index == gl.E_ERROR) {
	
	        	
	        } else {
	        	
	        	
	        	((JList)e.getSource()).getSelectedValuesList().forEach(a->{
	        		
	        	
	        	 gl.tx_k(new Object() {},gl.sf("List selected value...%s",
	    				  
	    				 a
	    				 
	    				 ));
	        	});
	        	
	        	this.getOwner().getData().setSelected_index(m_index);
	        	
	        	gl.tx_k(new Object() {},gl.sf("Индекс у провайдера...%d",
	    				  
	        			this.getOwner().getData().getSelected_index()
	    				 
	    				 ));
	        	
	   
	        }
	    }
	}
	
	public static void main(String[] args) {
		

	}

}
