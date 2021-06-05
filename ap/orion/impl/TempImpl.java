package ap.orion.impl;

import java.util.ArrayList;
import java.util.List;

import ap.mercury.components.parser.Dimension;

public class TempImpl {

	private List<Dimension> dims;
	
	
	public List<Dimension> getDims() {
		return dims;
	}

	public void setDims(List<Dimension> gims) {
		this.dims = gims;
	}

	public TempImpl() {
		
		this.setDims(new ArrayList<Dimension>());
		
	}

	public static TempImpl get_instance()
	{
		return new TempImpl();
	}
	
	public static void main(String[] args) {
		

	}
	

}
