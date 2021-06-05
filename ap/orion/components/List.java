package ap.orion.components;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class List extends JList {

	
	public List() {
		
	}

	public List(ListModel dataModel) {
		super(dataModel);
		
	}

	public List(Object[] listData) {
		super(listData);
		
	}

	public List(Vector listData) {
		super(listData);
		
	}

	public static void main(String[] args) {
		

	}

}
