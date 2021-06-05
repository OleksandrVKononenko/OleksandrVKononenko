 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.test; 

import java.awt.Dimension; 

import ap.btn.TBtn; 
import ap.btn.TLabel; 
import ap.btn.TPopupBtn; 
import ap.btn.TPopupPushBtn; 
import ap.btn.TPushBtn; 
import ap.swing.TRadioButton; 
import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.swing.TTextArea; 
import ap.swing.TTextField; 

public class TestAny { 

	public TestAny() { 
		 
	} 
	 
	public static void startUp() 
	{ 
		Object v  = new Object(){}; 
		 
		SPanelManager mng = new SPanelManager(); 
		 
		if(!mng.createGUI()) 
			gl.tracex(v,String.format("Try to create GUI...Error.")); 
		else 
			gl.tracex(v,String.format("Try to create GUI...Ok.")); 
		 
		gl.tracex(v,String.format("Try to generate some test object...")); 
		 
		 
		mng.getAdded().addAll(TPopupBtn.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TBtn.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TPopupPushBtn.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TPushBtn.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TTextField.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TTextArea.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TRadioButton.getInstances(new Dimension(10,10),10)); 
		 
		mng.getAdded().addAll(TLabel.getInstances(new Dimension(10,10),10)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...Error.")); 
	 
	} 

	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
