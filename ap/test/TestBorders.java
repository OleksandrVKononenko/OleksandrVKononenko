 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

/** 
* 
*/ 
package ap.test; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 

import javax.swing.BorderFactory; 
import javax.swing.border.Border; 

import ap.global.gl; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.state.Fl; 
import ap.utils.CU; 

public class TestBorders { 

	public TestBorders() { 
		 
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
		 
		Border brd = BorderFactory.createEtchedBorder(); 
		 
		Border titled = BorderFactory.createTitledBorder(brd,"Etched"); 
		 
		 
		Border loweredBB = BorderFactory.createLoweredBevelBorder(); 
		 
		Border loweredBevelBorder = BorderFactory.createTitledBorder(loweredBB,"loweredBB"); 
				 
		 
		Border raisedBB = BorderFactory.createRaisedBevelBorder(); 
		 
		Border raisedBevelBorder = BorderFactory.createTitledBorder(raisedBB,"raisedBB"); 
	 
		 
		Border lineBB = BorderFactory.createLineBorder(Color.white); 
		 
		Border lineBorder = BorderFactory.createTitledBorder(lineBB,"lineBB"); 
		 

		Border matteBB1 = BorderFactory.createMatteBorder(2,2,2,2,CU.getAlphaColor(Color.BLACK, 20)); 
		 
		Border matteBB2 = BorderFactory.createMatteBorder(1,1,1,1,CU.getAlphaColor(Color.BLACK, 10)); 
		 
		Border matteCompound = BorderFactory.createCompoundBorder(matteBB2,matteBB1); 
		 
		Border emptyBB = BorderFactory.createEmptyBorder(); 
		 
		Border emptyBorder = BorderFactory.createTitledBorder(emptyBB,"emptyBB"); 
		 
		 
		float dash[] = { 2.0f }; 
		 
	    BasicStroke   	dashed =   new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,5.0f, dash, 0.3f); 

		Border 			strokeBorder = BorderFactory.createStrokeBorder(dashed); 
		 
		mng.getAdded().addAll(TPanel.getInstances(new Dimension(10,10),10,strokeBorder)); 
		 
		if (!mng.insertAll(mng.getAdded().getData())) 
			gl.tracex(v,String.format("Error while add manager object...Error.")); 
	 
		 
		 	 
	} 

	public static void main(String[] args) { 
		 
		startUp(); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
